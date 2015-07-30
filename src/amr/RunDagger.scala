package amr
import java.io._
import dagger.core._
import dagger.ml.MultiClassClassifier

object RunDagger {

  val debug = false

  def sampleTrajectory(data: Sentence, logFile: String = "", expert: WangXueExpertBasic = new WangXueExpert): Sentence = {
    val output = if (logFile != "") new FileWriter(logFile) else null
    val expertSystem = WangXueTransitionSystem
    var nextState = expertSystem.init(data)
    var finished = false
    while (!finished) {
      var nextAction = expert.chooseTransition(data, nextState)
      if (debug) println(nextState.nodesToProcess.head + "\t" + nextAction)
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

  def corpusSmatchScore(i: Iterable[(Sentence, Sentence)]): List[(String, Double)] = {
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
    corpusSmatchScoreAMR(amrToCompare)
    //  the commented out line is the mean F-Score - rather than the corpus-level F-Score that we need
    //      (amrToCompare map { a: (AMRGraph, AMRGraph) => a match { case (x, y) => Smatch.fScore(x, y, 1)._1 } }).sum / i.size
  }

  def corpusSmatchScoreAMR(i: Iterable[(AMRGraph, AMRGraph)], iterations: Int = 4, attempts: Int = 1000, naive: Boolean = false): List[(String, Double)] = {
    val fScoreFn = if (naive) Smatch.naiveFScore _ else Smatch.fScore _
    val results = i map { a: (AMRGraph, AMRGraph) => a match { case (x, y) => fScoreFn(x, y, iterations, attempts) } }
    val totalTriples = i map { case (x, y) => (x.nodes.size + x.arcs.size, y.nodes.size + y.arcs.size) } reduce { (a, b) => (a._1 + b._1, a._2 + b._2) }
    val totalMatches = results map (_._5) sum
    val overallPrecision = totalMatches / totalTriples._1
    val overallRecall = totalMatches / totalTriples._2
    val overallScore = (2 * overallPrecision * overallRecall) / (overallPrecision + overallRecall)
    List(("F-Score", overallScore), ("Precision", overallPrecision), ("Recall", overallRecall))
  }

  def testDAGGERrun(options: DAGGEROptions): MultiClassClassifier[WangXueAction] = {

    val dagger = new DAGGER[Sentence, WangXueAction, WangXueTransitionState](options)
    val alignerToUse = options.getString("--aligner", "")
    AMRGraph.setAligner(alignerToUse)

    ImportConcepts.initialise(options.getString("--train.data", "C:\\AMR\\AMR2.txt"))
    val trainData = (ImportConcepts.allAMR zip ImportConcepts.allSentencesAndAMR) map (all => Sentence(all._2._1, Some(all._1)))
    //   val trainData = AMRGraph.importFile(options.getString("--train.data", "C:\\AMR\\AMR2.txt")) map { case (english, amr) => Sentence(english, amr) }
    val devFile = options.getString("--validation.data", "")
    val devData = if (devFile == "") Iterable.empty else AMRGraph.importFile(devFile) map { case (english, amr) => Sentence(english, amr) }

    val w2vDict = Word2VecReader.load(options.DAGGER_OUTPUT_PATH + "../Globe.6B.50d.txt", filter = x => ImportConcepts.conceptStrings.contains(x))
    devData map (s => s.copy())
     
    val lossToUse = options.getString("--lossFunction", "")
    val lossFunctionFactory = new WangXueLossFunctionFactory(lossToUse)
    val featureIndex = new MapIndex
    val WXFeatures = new WangXueFeatureFactory(options, featureIndex)
    val insertProhibition = options.getBoolean("--insertProhibition", true)
    WangXueTransitionSystem.setProhibition(insertProhibition)
    val classifier = dagger.train(trainData, new WangXueExpert, WXFeatures, WangXueTransitionSystem, lossFunctionFactory, devData, corpusSmatchScore,
      GraphViz.graphVizOutputFunction)
    //   if (options.DEBUG) classifier.writeToFile(options.DAGGER_OUTPUT_PATH + "ClassifierWeightsFinal.txt")

    val outputFile = new FileWriter(options.DAGGER_OUTPUT_PATH + "FeatureIndex.txt")
    for (j <- (WangXueTransitionSystem.actions ++ Array(Reattach(0)))) {
      outputFile.write(j + "\n")
      var relevantFeatures = List[(Int, Float)]()
      for (i <- 1 to featureIndex.size) {
        val weight = classifier.weightOf(j, i)
        if (weight != 0.0) relevantFeatures = (i, weight) :: relevantFeatures
      }
      val sortedFeatures = relevantFeatures.sortWith((a, b) => Math.abs(a._2) > Math.abs(b._2))
      for (i <- 0 to Math.min(sortedFeatures.size - 1, 50)) {
        val (feat, weight) = sortedFeatures(i)
        outputFile.write(feat + ", " + featureIndex.elem(feat) + ", " + f"$weight%.3f" + "\n")
      }
      outputFile.write("\n")
    }
    outputFile.close()

    classifier
  }

  def main(args: Array[String]): Unit = {

    //   val args = List("--dagger.output.path", "C:\\AMR\\daggerTest_",
    //    "--dagger.iterations", "3",
    //     "--debug", "true",
    //     "--dagger.print.interval", "1",
    //     "--train.data", "C:\\AMR\\initialTrainingSet.txt",
    //     "--validation.data", "C:\\AMR\\initialValidationSet.txt").toArray

    val options = new DAGGEROptions(args)
    val classifier = testDAGGERrun(options)
  }

}