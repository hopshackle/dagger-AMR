package amr

object DebugTester {

  def main(args: Array[String]): Unit = {
    val expert = new WangXueExpert
    val expertSystem = new WangXueTransitionSystem
    val testData = AMRGraph.importFile("C:\\AMR\\initialTrainingSetAmended.txt")
    val s1 = Sentence(testData(1)._1, testData(1)._2)

    var state = expertSystem.init(s1)

    val action = new Array[WangXueAction](26)
    val stateHistory = new Array[WangXueTransitionState](26)

    for (i <- 0 to 25) {
      stateHistory(i) = state
      action(i) = expert.chooseTransition(s1, state)
      state = action(i)(state)
    }
  }

}