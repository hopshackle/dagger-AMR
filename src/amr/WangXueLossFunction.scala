package amr
import dagger.core._

class WangXueLossFunction extends LossFunction[Sentence, WangXueAction, WangXueTransitionState] {

  def apply(gold: Sentence, test: Sentence, testActions: Array[WangXueAction]): Double = {
    // TODO: Clarify what the testActions are for...at the moment we just calculate the loss
    // based on the gold and test inputs
    
    val testAMR = test.amr match {
      case None => test.dependencyTree.toAMR
      case Some(graph) => graph
    }
    
    1.0 - Smatch.fScore(gold.amr.get, testAMR)
  }
  def max(gold: Sentence): Double = 1.0

}