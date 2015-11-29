package amr

class WangXueLossFunctionActionPenalty extends WangXueLossFunction {
  override def apply(gold: Sentence, test: Sentence, testActions: Array[WangXueAction]): Double = {
    val testAMR = test.amr match {
      case None => test.dependencyTree.toAMR
      case Some(graph) => graph
    }
    1.0 - Smatch.fScore(gold.amr.get, testAMR, 2, 500)._1 + ((testActions filterNot (a => a.ignoreAction) size) / 1000.0)
  }
}

class WXConstantLoss extends WangXueLossFunction {
  override def apply(gold: Sentence, test: Sentence, testActions: Array[WangXueAction]): Double = {
    0.0
  }
}


