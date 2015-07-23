package amr
import dagger.core._
import amr.ImportConcepts.{ concept, relation }

class WangXueLossFunctionAbs extends WangXueLossFunction {
  // Inherits everything from WXLoss - it just then uses the absolute number of errors in the Smatch
  // as the loss, rather than the F-Score

  override def apply(gold: Sentence, test: Sentence, testActions: Array[WangXueAction]): Double = {

    val testAMR = test.amr match {
      case None => test.dependencyTree.toAMR
      case Some(graph) => graph
    }

    Smatch.fScore(gold.amr.get, testAMR, 1, 500)._4
  }
  
  // we set the maximum to be double the number of nodes in the dependency tree of the original sentence
  override def max(gold: Sentence): Double = gold.dependencyTree.nodes.size * 2

}

class WangXueLossFunctionAbsPenalty extends WangXueLossFunction {
  // Inherits everything from WXLoss - it just then uses the absolute number of errors in the Smatch
  // as the loss, rather than the F-Score

  override def apply(gold: Sentence, test: Sentence, testActions: Array[WangXueAction]): Double = {

    val testAMR = test.amr match {
      case None => test.dependencyTree.toAMR
      case Some(graph) => graph
    }

    Smatch.fScore(gold.amr.get, testAMR, 4, 500)._4 + (testActions.size / 5.0)
  }
  
  // we set the maximum to be double the number of nodes in the dependency tree of the original sentence
  override def max(gold: Sentence): Double = gold.dependencyTree.nodes.size * 2

}
