package amr
import java.io._
import dagger.core._
import dagger.ml.MultiClassClassifier

object SampleExpertTrajectory {

  def sampleTrajectory(data: Sentence, logFile: String = ""): Sentence = {
    val output = if (logFile != "") new FileWriter(logFile) else null
    val expert = new WangXueExpert
    val expertSystem = new WangXueTransitionSystem
    var nextState = expertSystem.init(data)
    var finished = false
    while (!finished) {
      var nextAction = expert.chooseTransition(data, nextState)
      println(nextState.nodesToProcess.head + "\t" + nextAction)
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
    val trans = new WangXueTransitionSystem
    trans.construct(nextState, data)
  }

  def testDAGGERrun: MultiClassClassifier[WangXueAction] = {
    val args = List("--dagger.output.path", "C:\\AMR\\daggerTest.txt", "--dagger.iterations", "1", "--debug").toArray
    val options = new DAGGEROptions(args)
    val dagger = new DAGGER[Sentence, WangXueAction, WangXueTransitionState](options)
    
    val trainData = AMRGraph.importFile("C:\\AMR\\AMR2.txt") map {case (english, amr) => Sentence(english, amr)}
    
    dagger.train(trainData, new WangXueExpert, (new WangXueFeatures).features, new WangXueTransitionSystem, new WangXueLossFunction, null, null)
  }
}