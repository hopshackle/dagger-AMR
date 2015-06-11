package amr
import java.io._

object SampleExpertTrajectory {

  def sampleTrajectory(data: Sentence, logFile: String = ""): AMRGraph = {
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
    nextState.currentGraph.toAMR
  }
}