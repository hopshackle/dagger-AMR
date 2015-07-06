package amr

class WangXueLossFunctionActionPenalty extends WangXueLossFunction {
  override def apply(gold: Sentence, test: Sentence, testActions: Array[WangXueAction]): Double = {
    val testAMR = test.amr match {
      case None => test.dependencyTree.toAMR
      case Some(graph) => graph
    }
    1.0 - Smatch.fScore(gold.amr.get, testAMR, 1)._1 + (testActions.size / 1000.0)
  }
}