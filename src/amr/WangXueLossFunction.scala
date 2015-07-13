package amr
import dagger.core._
import amr.ImportConcepts.{ concept, relation }

class WangXueLossFunctionFactory(lossToUse: String) extends LossFunctionFactory[Sentence, WangXueAction, WangXueTransitionState] {
  override def newLossFunction = lossToUse match {
    case "Penalty" => new WangXueLossFunctionActionPenalty
    case "Abs" => new WangXueLossFunctionAbs
    case _ => new WangXueLossFunction
  }
}
class WangXueLossFunction extends LossFunction[Sentence, WangXueAction, WangXueTransitionState] {

  private var nodeDefault: Array[Double] = Array(1)
  private var edgeDefault: Array[Double] = Array(1)
  private var insertDefault: Array[Double] = Array(1)
  private var node, edge, insert: Boolean = false
  private var sampleSize: Int = 1
  private var count: Int = 0

  override def clearCache: Unit = {
    node = false
    edge = false
    insert = false
    count = 0
  }
  override def setSamples(s: Int): Unit = {
    sampleSize = s
    nodeDefault = new Array[Double](s)
    edgeDefault = new Array[Double](s)
    insertDefault = new Array[Double](s)
  }

  override def apply(gold: Sentence, test: Sentence, testActions: Array[WangXueAction], expertActions: Array[WangXueAction],
    trialAction: WangXueAction, lastExpertAction: WangXueAction): Double = {
    
    def conceptNotInAMR(c: Int): Boolean = {
      if (c == 0) false else {
        gold.amr match {
          case None => false
          case Some(amr) => !(amr.nodes.values.toSet contains concept(c))
        }
      }
    }
    def labelNotInAMR(l: Int): Boolean = {
      if (l == 0) false else {
        gold.amr match {
          case None => false
          case Some(amr) => !(amr.arcs.values.toSet contains relation(l))
        }
      }
    }

    count += 1
    if (count > sampleSize) count = 1

    trialAction match {
      case NextNode(nodeConcept) => if (conceptNotInAMR(nodeConcept)) {
        if (node)
          nodeDefault(count - 1)
        else {
          nodeDefault(count - 1) = apply(gold, test, testActions)
          if (count == sampleSize) node = true
          nodeDefault(count - 1)
        }
      } else apply(gold, test, testActions)
      case NextEdge(edgeLabel) => if (labelNotInAMR(edgeLabel)) {
        if (edge)
          edgeDefault(count - 1)
        else {
          edgeDefault(count - 1) = apply(gold, test, testActions)
          if (count == sampleSize) edge = true
          edgeDefault(count - 1)
        }
      } else apply(gold, test, testActions)
      case Insert(nodeConcept, otherRef) => if (conceptNotInAMR(nodeConcept)) {
        if (insert)
          insertDefault(count - 1)
        else {
          insertDefault(count - 1) = apply(gold, test, testActions)
          if (count == sampleSize) insert = true
          insertDefault(count - 1)
        }
      } else apply(gold, test, testActions)
      case _ => apply(gold, test, testActions)
    }
  }

  override def apply(gold: Sentence, test: Sentence, testActions: Array[WangXueAction]): Double = {
    val testAMR = test.amr match {
      case None => test.dependencyTree.toAMR
      case Some(graph) => graph
    }

    1.0 - Smatch.fScore(gold.amr.get, testAMR, 1, 500)._1
  }

  // we set the maximum to be double the number of nodes in the dependency tree of the original sentence
  def max(gold: Sentence): Double = 1.0

}
