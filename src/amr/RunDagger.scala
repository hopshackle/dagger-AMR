package amr
import java.io._
import dagger.core._
import dagger.ml.MultiClassClassifier
import dagger.ml.AROWClassifier
import amr.classic._

import scala.reflect.ClassTag

object RunDagger {

  val debug = false

  def sampleTrajectory[S <: TransitionState, A <: TransitionAction[S]](data: Sentence, logFile: String = "", expert: HeuristicPolicy[Sentence, A, S],
    expertSystem: TransitionSystem[Sentence, A, S]): Sentence = {
    val output = if (logFile != "") new FileWriter(logFile) else null
    var nextState = expertSystem.init(data)
    var finished = false
    while (!finished) {
      var nextAction = expert.chooseTransition(data, nextState)
      if (debug) println(nextState + "\t" + nextAction)
      var lastState = nextState
      nextState = nextAction(lastState)
      finished = expertSystem.isTerminal(nextState)
      if (logFile != "") {
        output.write(lastState.toString + "\n")
        output.write(nextAction.toString + "\n")
        if (finished) output.write(nextState.toString + "\n")
      }
    }
    if (logFile != "") output.close
    expertSystem.construct(nextState, data)
  }

  def corpusSmatchScore(options: DAGGEROptions)(i: Iterable[(Sentence, Sentence)]): List[(String, Double)] = {
    val amrToCompare = i map {
      case (s1, s2) =>
        val amr1 = s1.amr match {
          case None => s1.dependencyTree.toAMR
          case Some(graph) => graph
        }
        val amr2 = s2.amr match {
          case None => s2.dependencyTree.toAMR
          case Some(graph) => graph
        }
        (amr1, amr2)
    }
    corpusSmatchScoreAMR(options)(amrToCompare)
    //  the commented out line is the mean F-Score - rather than the corpus-level F-Score that we need
    //      (amrToCompare map { a: (AMRGraph, AMRGraph) => a match { case (x, y) => Smatch.fScore(x, y, 1)._1 } }).sum / i.size
  }

  def corpusSmatchScoreAMR(options: DAGGEROptions)(i: Iterable[(AMRGraph, AMRGraph)], iterations: Int = 4, attempts: Int = 1000, naive: Boolean = false): List[(String, Double)] = {
    val fScoreFn = if (naive) Smatch.naiveFScore _ else Smatch.fScore _
    val results = i map { a: (AMRGraph, AMRGraph) => a match { case (x, y) => fScoreFn(x, y, iterations, attempts) } }

    if (false) {
      val naiveResults = i map { a: (AMRGraph, AMRGraph) => a match { case (x, y) => Smatch.naiveFScore(x, y, iterations, attempts) } }
      val outputFile = new FileWriter(options.DAGGER_OUTPUT_PATH + "SmatchScoreComparison.txt", true)
      for (all <- results zip naiveResults) {
        outputFile.write(f"${all._1._1}%.3f \t ${all._2._1}%.3f \n")
      }
      outputFile.close()
    }

    //   val totalTriples = i map { case (x, y) => (x.nodes.size + x.arcs.size, y.nodes.size + y.arcs.size) } reduce { (a, b) => (a._1 + b._1, a._2 + b._2) }
    val totalTriples1 = (results map (_._6) sum)
    val totalTriples2 = (results map (_._7) sum)
    val totalMatches = results map (_._5) sum
    val overallRecall = totalMatches.toDouble / totalTriples1.toDouble
    val overallPrecision = totalMatches.toDouble / totalTriples2.toDouble
    val overallScore = (2 * overallPrecision * overallRecall) / (overallPrecision + overallRecall)
    List(("F-Score", overallScore), ("Precision", overallPrecision), ("Recall", overallRecall))
  }

  def testDAGGERrun[S <: TransitionState, A <: TransitionAction[S]](options: DAGGEROptions): Unit = {

    val trainData = initialiseAndGetTrainingData(options)
    val maxNodesForTraining = options.getInt("--maxTrainingSize", 100)
    val filteredTrainingData = trainData filter { s => s.amr.get.nodes.size <= maxNodesForTraining }

    val devFile = options.getString("--validation.data", "")
    val devData = if (devFile == "") Iterable.empty else AMRGraph.importFile(devFile) map { case (english, amr) => Sentence(english, amr) }

    val lemmaReplacement = options.getString("--lemmaReplace", "None")
    val correctedDevData = lemmaReplacement match {
      case "glove" => replaceLemmasGlove(devData, options.DAGGER_OUTPUT_PATH + "../glove.6B.50d.txt")
      case "wordnet" => replaceLemmasWordnet(devData)
      case _ => devData
    }

    val lossToUse = options.getString("--lossFunction", "")

    val featureIndex = new MapIndex

    val insertProhibition = options.getBoolean("--insertProhibition", true)
    val useReentrance = options.getBoolean("--reentrance", false)
    val reentrancePhase = options.getBoolean("--reentrancePhase", true)
    val fileCache = options.getBoolean("--fileCache", false)
    WangXueTransitionSystem.prohibition = insertProhibition
    WangXueTransitionSystem.reentrance = useReentrance
    WangXueTransitionSystem.reentrancePhase = reentrancePhase
    WangXueTransitionSystem.useCompositeNodes = options.getBoolean("--composite", false)
    WangXueTransitionSystem.preferKnown = options.getBoolean("--preferKnown", true)
    ClassicTransitionSystem.preferKnown = options.getBoolean("--preferKnown", true)

    val startingClassifier = if (options.contains("--prelimOracleRun")) {
      val sc = oracleRun[A](options, featureIndex, filteredTrainingData, correctedDevData)
      sc.writeToFile(options.DAGGER_OUTPUT_PATH + "StartingClassifier.txt", x => x.name)
      featureIndex.writeToFile(options.DAGGER_OUTPUT_PATH + "StartingFeatureIndex.txt")
      sc
    } else if (options.getBoolean("--startingClassifier", false)) {
      if (options.getBoolean("--WangXue", true)) {
        AROWClassifier.fromFile(options.DAGGER_OUTPUT_PATH + "../StartingClassifier.txt", y => WangXueAction.construct(y))
      } else {
        AROWClassifier.fromFile(options.DAGGER_OUTPUT_PATH + "../StartingClassifier.txt", y => ClassicAction.construct(y))
      }
    } else null

    if (featureIndex.array.isEmpty && startingClassifier != null) {
      featureIndex.initialiseFromFile(options.DAGGER_OUTPUT_PATH + "../FeatureIndex.txt")
    }

    val (fc, aa) = if (options.getBoolean("--WangXue", true)) {
      val WXFeatures = new WangXueFeatureFactory(options, featureIndex)
      val lossFunctionFactory = new WangXueLossFunctionFactory(lossToUse)
      val dagger = new DAGGER[Sentence, WangXueAction, WangXueTransitionState](options)
      val classifier = dagger.train(filteredTrainingData, new WangXueExpert, WXFeatures, WangXueTransitionSystem, lossFunctionFactory, correctedDevData, corpusSmatchScore(options),
        actionToString = if (fileCache) (x => x.name) else null,
        stringToAction = if (fileCache) (y => WangXueAction.construct(y)) else null,
        AMROutput.AMROutputFunction,
        startingClassifier.asInstanceOf[MultiClassClassifier[WangXueAction]])
      (classifier, WangXueTransitionSystem.actions ++
        Array(Reattach(0)) ++ (if (useReentrance) Array(Reentrance(0)) else Array[WangXueAction]()))
    } else {
      val dagger = new DAGGER[Sentence, ClassicAction, ClassicTransitionState](options)
      val classicFeatures = new ClassicFeatureFactory(options, featureIndex)
      val lossFunctionFactory = new ClassicLossFunctionFactory(lossToUse)
      val classifier = dagger.train(filteredTrainingData, new ClassicExpert, classicFeatures, ClassicTransitionSystem, lossFunctionFactory, correctedDevData, corpusSmatchScore(options),
        actionToString = if (fileCache) (x => x.name) else null,
        stringToAction = if (fileCache) (y => ClassicAction.construct(y)) else null,
        AMROutput.AMROutputFunction,
        startingClassifier.asInstanceOf[MultiClassClassifier[ClassicAction]])
      (classifier, ClassicTransitionSystem.actions ++ Array(AssignToFragment(0)))
    }

    val finalClassifier = fc.asInstanceOf[MultiClassClassifier[A]]
    val allActions = aa.asInstanceOf[Array[A]]
    val outputFile = new FileWriter(options.DAGGER_OUTPUT_PATH + "FeatureSummaryByAction.txt")
    for (j <- allActions) {
      val f: A = j.asInstanceOf[A];
      outputFile.write(j + "\n")
      var relevantFeatures = List[(Int, Float)]()
      for (i <- 1 to featureIndex.size) {
        val weight = finalClassifier.weightOf(f, i)
        if (weight != 0.0) relevantFeatures = (i, weight) :: relevantFeatures
      }
      val sortedFeatures = relevantFeatures.sortWith((a, b) => Math.abs(a._2) > Math.abs(b._2))
      for (i <- 0 to Math.min(sortedFeatures.size - 1, 100)) {
        val (feat, weight) = sortedFeatures(i)
        outputFile.write(feat + ", " + featureIndex.elem(feat) + ", " + f"$weight%.3f" + "\n")
      }
      outputFile.write("\n")
    }
    outputFile.close()

    finalClassifier.writeToFile(options.DAGGER_OUTPUT_PATH + "FinalClassifier.txt", x => x.name)
    featureIndex.writeToFile(options.DAGGER_OUTPUT_PATH + "FeatureIndex.txt")
  }

  def initialiseAndGetTrainingData(options: DAGGEROptions): IndexedSeq[Sentence] = {
    WangXueTransitionSystem.prohibition = false // temp value for lemma / concept extraction
    WangXueTransitionSystem.reentrance = true // temp value for lemma / concept extraction
    val alignerToUse = options.getString("--aligner", "")
    Reattach.REATTACH_RANGE = options.getInt("--reattachRange", 6)
    DependencyTree.excludePunctuation = !options.getBoolean("--punctuation", true)
    AMRGraph.setAligner(alignerToUse)
    WangXueFeatures.includeChildren = (options.getString("--WXfeatures", "") contains "C")
    WangXueFeatures.debug = (options.getString("--WXfeatures", "") contains "D")
    WangXueFeatures.includeParents = (options.getString("--WXfeatures", "") contains "P")
    WangXueFeatures.includeShenanigans = (options.getString("--WXfeatures", "") contains "S")
    WangXueFeatures.includeWords = (options.getString("--WXfeatures", "") contains "W")
    ClassicFeatures.includeWords = (options.getString("--WXfeatures", "") contains "W")
    WangXueFeatures.includeActionHistory = (options.getString("--WXfeatures", "") contains "A")
    WangXueFeatures.includeDeletions = (options.getString("--WXfeatures", "") contains "X")
    WangXueFeatures.includeWordNet = (options.getString("--WXfeatures", "") contains "H")
    if (WangXueFeatures.includeWordNet) {
      val fString = options.getString("--WXfeatures", "")
      val limit = fString.charAt(fString.indexOf("H") + 1).toString.toInt
      WangXueFeatures.hypernymLimit = limit
    }
    WangXueTransitionSystem.preferKnown = options.getBoolean("--preferKnown", true)
    ClassicTransitionSystem.preferKnown = options.getBoolean("--preferKnown", true)

    Reattach.assertionChecking = options.getBoolean("--assertionChecking", false)
    Reentrance.assertionChecking = options.getBoolean("--assertionChecking", false)
    ImportConcepts.initialise(options.getString("--train.data", "C:\\AMR\\AMR2.txt"))
    (ImportConcepts.allAMR zip ImportConcepts.allSentencesAndAMR) map (all => Sentence(all._2._1, Some(all._1)))
  }

  def main(args: Array[String]): Unit = {
    val options = new DAGGEROptions(args)
    testDAGGERrun(options)
  }

  def replaceLemmasGlove(devData: Iterable[Sentence], location: String): Iterable[Sentence] = {
    val w2vDict = Word2VecReader.load(location)
    val lemmasInDict = ImportConcepts.conceptsPerLemma.keys filter (w2vDict.contains(_))
    devData map (s => {
      val dt = s.dependencyTree
      val lemmas = dt.nodeLemmas
      val replacedLemmas = for {
        (node, lemma) <- lemmas
        if !(ImportConcepts.conceptsPerLemma contains lemma) // A known lemma, so we can skip onwards
        if (w2vDict.contains(lemma)) // and we have a W2V mapping for it
        val (nearestLemma, distance) = lemmasInDict map (x => (x, w2vDict.euclidean(x, lemma))) minBy (_._2)
      } yield (node, nearestLemma)
      val newDT = dt.copy(nodeLemmas = lemmas map { case (k, v) => if (replacedLemmas contains k) (k, replacedLemmas(k)) else (k, v) })
      replacedLemmas foreach { case (k, v) => println(s"New Lemma ${lemmas(k)} replaced with $v") }
      s.copy(dependencyTree = newDT)
    })
  }

  def replaceLemmasWordnet(devData: Iterable[Sentence]): Iterable[Sentence] = {
    val workInProgress = ImportConcepts.conceptsPerLemma.keys flatMap (x => Wordnet.synonyms(x) map (_ -> x)) groupBy (_._1)
    val lemmasBySynonym = workInProgress map { case (key, listOfTuples) => (key -> (listOfTuples map (_._2)).toList.distinct) }
    devData map (s => {
      val dt = s.dependencyTree
      val lemmas = dt.nodeLemmas
      val replacedLemmas = for {
        (node, lemma) <- lemmas
        if !(ImportConcepts.conceptsPerLemma contains lemma) // A known lemma, so we can skip onwards
        if lemmasBySynonym contains lemma // but it does exist in the WordNet dictionary as a synonym of a lemma we know
      } yield (node, lemmasBySynonym(lemma)(0))
      val newDT = dt.copy(nodeLemmas = lemmas map { case (k, v) => if (replacedLemmas contains k) (k, replacedLemmas(k)) else (k, v) })
      replacedLemmas foreach { case (k, v) => println(s"New Lemma ${lemmas(k)} replaced with $v") }
      s.copy(dependencyTree = newDT)
    })
  }

  def oracleRun[A](options: DAGGEROptions, featureIndex: MapIndex, trainingData: Iterable[Sentence], devData: Iterable[Sentence]): MultiClassClassifier[A] = {
    val newOptions = new DAGGEROptions(options.args)
    newOptions.ORACLE_LOSS = true
    newOptions.addOption("--reducedActions", "false")
    newOptions.addOption("--fileCache", "false")
    newOptions.addOption("--dagger.iterations", "1")
    val lossToUse = options.getString("--lossFunction", "")

    val (sc, aa) = if (options.getBoolean("--WangXue", true)) {
      val WXFeatures = new WangXueFeatureFactory(options, featureIndex)
      val lossFunctionFactory = new WangXueLossFunctionFactory(lossToUse)
      val dagger = new DAGGER[Sentence, WangXueAction, WangXueTransitionState](newOptions)
      val classifier = dagger.train(trainingData, new WangXueExpert, WXFeatures, WangXueTransitionSystem, lossFunctionFactory, devData, corpusSmatchScore(newOptions),
        actionToString = null,
        stringToAction = null,
        null,
        null.asInstanceOf[MultiClassClassifier[WangXueAction]])
      (classifier, WangXueTransitionSystem.actions ++
        Array(Reattach(0)) ++ (if (WangXueTransitionSystem.reentrance) Array(Reentrance(0)) else Array[WangXueAction]()))
    } else {
      val dagger = new DAGGER[Sentence, ClassicAction, ClassicTransitionState](newOptions)
      val classicFeatures = new ClassicFeatureFactory(options, featureIndex)
      val lossFunctionFactory = new ClassicLossFunctionFactory(lossToUse)
      val classifier = dagger.train(trainingData, new ClassicExpert, classicFeatures, ClassicTransitionSystem, lossFunctionFactory, devData, corpusSmatchScore(newOptions),
        actionToString = null,
        stringToAction = null,
        null,
        null.asInstanceOf[MultiClassClassifier[ClassicAction]])
      (classifier, ClassicTransitionSystem.actions ++ Array(AssignToFragment(0)))
    }
    val startingClassifier = sc.asInstanceOf[MultiClassClassifier[A]]
    startingClassifier
  }
}
