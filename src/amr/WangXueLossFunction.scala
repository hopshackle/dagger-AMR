package amr
import dagger.core._
import amr.ImportConcepts.{ concept, relation }

class WangXueLossFunction extends LossFunction[Sentence, WangXueAction, WangXueTransitionState] {

  private var nodeDefault: Double = 0.0
  private var edgeDefault: Double = 0.0
  private var node, edge: Boolean = false

  override def clearCache: Unit = {
    node = false
    edge = false
  }

  override def apply(gold: Sentence, test: Sentence, testActions: Array[WangXueAction], trialAction: WangXueAction): Double = {
    def conceptNotInAMR(c: Int): Boolean = {
      gold.amr match {
        case None => false
        case Some(amr) => !(amr.nodes.values.toSet contains concept(c))
      }
    }
    def labelNotInAMR(l: Int): Boolean = {
      gold.amr match {
        case None => false
        case Some(amr) => !(amr.arcs.values.toSet contains relation(l))
      }
    }

    trialAction match {
      case NextNode(nodeConcept) => if (conceptNotInAMR(nodeConcept)) {
        if (node) nodeDefault else { nodeDefault = apply(gold, test, testActions); node = true; nodeDefault }
      } else apply(gold, test, testActions)
      case NextEdge(edgeLabel) => if (labelNotInAMR(edgeLabel)) {
        if (edge) edgeDefault else { edgeDefault = apply(gold, test, testActions); edge = true; edgeDefault }
      } else apply(gold, test, testActions)
      case _ => apply(gold, test, testActions)
    }
  }

  override def apply(gold: Sentence, test: Sentence, testActions: Array[WangXueAction]): Double = {
    // TODO: Clarify what the testActions are for...at the moment we just calculate the loss
    // based on the gold and test inputs

    val testAMR = test.amr match {
      case None => test.dependencyTree.toAMR
      case Some(graph) => graph
    }

    1.0 - Smatch.fScore(gold.amr.get, testAMR, 1)
  }
  def max(gold: Sentence): Double = 1.0

}