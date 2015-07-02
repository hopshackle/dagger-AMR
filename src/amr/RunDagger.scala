package amr
import java.io._
import dagger.core._
import dagger.ml.MultiClassClassifier

object RunDagger {

  val debug = false

  def sampleTrajectory(data: Sentence, logFile: String = "", expert: WangXueExpertBasic = new WangXueExpert): Sentence = {
    val output = if (logFile != "") new FileWriter(logFile) else null
    val expertSystem = new WangXueTransitionSystem
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

  def testDAGGERrun(options: DAGGEROptions): MultiClassClassifier[WangXueAction] = {

    val dagger = new DAGGER[Sentence, WangXueAction, WangXueTransitionState](options)
    ImportConcepts.initialise(options.getString("--train.data", "C:\\AMR\\AMR2.txt"))
    val trainData = AMRGraph.importFile(options.getString("--train.data", "C:\\AMR\\AMR2.txt")) map { case (english, amr) => Sentence(english, amr) }
    val devFile = options.getString("--validation.data", "")
    val devData = if (devFile == "") Iterable.empty else AMRGraph.importFile(devFile) map { case (english, amr) => Sentence(english, amr) }

    def score = (i: Iterable[(Sentence, Sentence)]) => {
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

      (amrToCompare map {a: (AMRGraph, AMRGraph) => a match {case (x, y) => Smatch.fScore(x, y, 1)._1}}).sum / i.size
    }
    val featureIndex = new MapIndex
    def featFn = (d: Sentence, s: WangXueTransitionState, a: WangXueAction) => (new WangXueFeatures(options, featureIndex)).features(d, s, a)
    dagger.train(trainData, new WangXueExpert, featFn, new WangXueTransitionSystem, new WangXueLossFunction, devData, score,
      GraphViz.graphVizOutputFunction)
  }

  def main(args: Array[String]): Unit = {

    //   val args = List("--dagger.output.path", "C:\\AMR\\daggerTest_",
    //    "--dagger.iterations", "3",
    //     "--debug", "true",
    //     "--dagger.print.interval", "1",
    //     "--train.data", "C:\\AMR\\initialTrainingSet.txt",
    //     "--validation.data", "C:\\AMR\\initialValidationSet.txt").toArray

    val options = new DAGGEROptions(args)
    testDAGGERrun(options)
  }

}