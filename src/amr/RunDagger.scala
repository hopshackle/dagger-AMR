package amr
import java.io._
import dagger.core._
import dagger.ml.MultiClassClassifier
import dagger.ml.AROWClassifier

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

    preInitialise(options)
    val trainData = getTrainingData(options)
    postInitialise(options)
    
    def extractSentences(file: String): Iterable[Sentence] = {
      if (file == "") Iterable.empty else AMRGraph.importFile(file) map {
        case (english, amr, id) => {
          val mappedAMR = AMRGraph(amr, english, id)
          mappedAMR match {
            case null => Sentence(english, None, id)
            case _ => Sentence(english, Some(mappedAMR), id)
          }
        }
      }
    }

    val devFile = options.getString("--validation.data", "")
    val devData = extractSentences(devFile)

    val testFile = options.getString("--test.data", "")
    val testData = extractSentences(testFile)

    val lossToUse = options.getString("--lossFunction", "")

    val featureIndex = new MapIndex

    val startingClassifier = if (options.getBoolean("--prelimOracleRun", false)) {
      val sc = oracleRun[A](options, featureIndex, trainData, devData)
      sc.writeToFile(options.DAGGER_OUTPUT_PATH + "StartingClassifier.txt", x => x.name)
      featureIndex.writeToFile(options.DAGGER_OUTPUT_PATH + "StartingFeatureIndex.txt")
      sc
    } else if (options.getBoolean("--startingClassifier", false)) {
      AROWClassifier.fromFile(options.DAGGER_OUTPUT_PATH + "../StartingClassifier.txt", y => WangXueAction.construct(y))
    } else null

    if (featureIndex.array.isEmpty && startingClassifier != null) {
      featureIndex.initialiseFromFile(options.DAGGER_OUTPUT_PATH + "../FeatureIndex.txt")
    }

    val (fc, aa) = {
      val WXFeatures = new WangXueFeatureFactory(options, featureIndex)
      val lossFunctionFactory = new WangXueLossFunctionFactory(lossToUse)
      val dagger = new DAGGER[Sentence, WangXueAction, WangXueTransitionState](options)
      val classifier = dagger.train(trainData, new WangXueExpert, WXFeatures, WangXueTransitionSystem, lossFunctionFactory, devData, testData,
        corpusSmatchScore(options),
        actionToString = (x => x.name),
        stringToAction = (y => WangXueAction.construct(y)),
        AMROutput.AMROutputFunction,
        startingClassifier.asInstanceOf[MultiClassClassifier[WangXueAction]])
      (classifier, WangXueTransitionSystem.actions ++
        Array(Reattach(0)) ++ (if (options.getBoolean("--reentrance", false)) Array(Reentrance(0)) else Array[WangXueAction]()))
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

    (options.getBoolean("--featureAnalysis", false), true, finalClassifier) match {
      case (true, true, classifier: AROWClassifier[WangXueAction]) => FeatureAnalyser.analyserRun(options, devData, classifier, featureIndex)
      case _ =>
    }

  }

  def preInitialise(options: DAGGEROptions): Unit = {
    AMRGraph.textEncoding = options.getString("--textEncoding", "UTF-8")
    WangXueTransitionSystem.prohibition = false // temp value for lemma / concept extraction
    WangXueTransitionSystem.reentrance = true // temp value for lemma / concept extraction
    WangXueTransitionSystem.reentrancePhase = true
    PourdamghaniAligner.useHeadMapping = (options.getBoolean("--forwardPDG", true))
    val alignerToUse = options.getString("--aligner", "")
    Reattach.REATTACH_RANGE = options.getInt("--reattachRange", 6)
    DependencyTree.excludePunctuation = !options.getBoolean("--punctuation", false)
    AMRGraph.setAligner(alignerToUse)
    WangXueFeatures.includeChildren = (options.getString("--WXfeatures", "") contains "C")
    WangXueFeatures.debug = (options.getString("--WXfeatures", "") contains "D")
    WangXueFeatures.includeParents = (options.getString("--WXfeatures", "") contains "P")
    WangXueFeatures.includeSiblings = (options.getString("--WXfeatures", "") contains "S")
    WangXueFeatures.includeWords = (options.getString("--WXfeatures", "") contains "W")
    WangXueFeatures.includeActionHistory = (options.getString("--WXfeatures", "") contains "A")
    WangXueFeatures.includeDeletions = (options.getString("--WXfeatures", "") contains "X")
    WangXueFeatures.includeKappaChildren = (options.getString("--WXfeatures", "") contains "K")
    WangXueFeatures.includeWordNet = (options.getString("--WXfeatures", "") contains "H")
    WangXueTransitionSystem.insertBelow = options.getBoolean("--insertBelow", true)
    if (WangXueFeatures.includeWordNet) {
      val fString = options.getString("--WXfeatures", "")
      val limit = fString.charAt(fString.indexOf("H") + 1).toString.toInt
      WangXueFeatures.hypernymLimit = limit
    }
    WangXueTransitionSystem.preferKnown = options.getBoolean("--preferKnown", false)
    WangXueTransitionSystem.nameConstraints = options.getBoolean("--nameConstraints", false)
    WangXueTransitionSystem.wikification = options.getBoolean("--wikification", true)
    Reattach.assertionChecking = options.getBoolean("--assertionChecking", false)
    Reentrance.assertionChecking = options.getBoolean("--assertionChecking", false)
  }
  
  def postInitialise(options: DAGGEROptions): Unit = {
    WangXueTransitionSystem.prohibition = options.getBoolean("--insertProhibition", true)
    WangXueTransitionSystem.reentrance = options.getBoolean("--reentrance", false)
    WangXueTransitionSystem.reentrancePhase = options.getBoolean("--reentrancePhase", true)
    WangXueTransitionSystem.useCompositeNodes = options.getBoolean("--composite", false)
    WangXueTransitionSystem.preferKnown = options.getBoolean("--preferKnown", false)
  }

  def getTrainingData(options: DAGGEROptions): IndexedSeq[Sentence] = {
    ImportConcepts.initialise(options.getString("--train.data", "C:\\AMR\\AMR2.txt"))
    (ImportConcepts.allAMR zip ImportConcepts.allSentencesAndAMR) map (all => Sentence(all._2._1, Some(all._1), ""))
  }

  def main(args: Array[String]): Unit = {
    val options = new DAGGEROptions(args)
    testDAGGERrun(options)
  }

  def oracleRun[A](options: DAGGEROptions, featureIndex: MapIndex, trainingData: Iterable[Sentence], devData: Iterable[Sentence]): MultiClassClassifier[A] = {
    val newOptions = new DAGGEROptions(options.args)
    newOptions.ORACLE_LOSS = true
    newOptions.addOption("--reducedActions", "false")
    newOptions.addOption("--fileCache", "false")
    newOptions.addOption("--dagger.iterations", "1")
    newOptions.addOption("--algorithm", "Dagger")
    newOptions.addOption("--minTrainingSize", options.getInt("--maxTrainingSize", 100).toString)
    val lossToUse = options.getString("--lossFunction", "")

    val (sc, aa) = {
      val WXFeatures = new WangXueFeatureFactory(options, featureIndex)
      val lossFunctionFactory = new WangXueLossFunctionFactory(lossToUse)
      val dagger = new DAGGER[Sentence, WangXueAction, WangXueTransitionState](newOptions)
      val classifier = dagger.train(trainingData, new WangXueExpert, WXFeatures, WangXueTransitionSystem, lossFunctionFactory, devData, Iterable.empty,
        corpusSmatchScore(newOptions),
        actionToString = null,
        stringToAction = null,
        null,
        null.asInstanceOf[MultiClassClassifier[WangXueAction]])
      (classifier, WangXueTransitionSystem.actions ++
        Array(Reattach(0)) ++ (if (WangXueTransitionSystem.reentrance) Array(Reentrance(0)) else Array[WangXueAction]()))
    }
    val startingClassifier = sc.asInstanceOf[MultiClassClassifier[A]]
    startingClassifier
  }
}
