package amr
import dagger.core._

abstract class WangXueAction extends TransitionAction[WangXueTransitionState]

case class NextEdge(relation: Int) extends WangXueAction {

  def apply(conf: WangXueTransitionState): WangXueTransitionState = {
    // label current arc
    // pop current arc from arc stack
    val tree = conf.currentGraph.labelArc(conf.nodesToProcess.head, conf.childrenToProcess.head, NextEdge.getRelation(relation))
    conf.copy(childrenToProcess = conf.childrenToProcess.tail, currentGraph = tree)
  }
  override def toString: String = "NextEdge: " + relation + " -> " + NextEdge.relationMaster(relation)

}

object NextEdge {
  private val relationMaster = Map[Int, String]((0 -> "UNKNOWN"), (1 -> "ARG0"), (2 -> "ARG1"), (3 -> "ARG2"),
    (4 -> "ARG3"), (5 -> "ARG4"), (6 -> "opN"), (7 -> "mod"), (8 -> "time"), (9 -> "manner"), (10 -> "location"))
  private val stringToIndex = relationMaster map (_ match { case (index, text) => (text -> index) })

  def all(): Array[WangXueAction] = {
    (relationMaster.keys map (i => NextEdge(i))).toArray
  }

  def getRelation(index: Int): String = relationMaster.getOrElse(index, "UNKNOWN")
  def getRelationIndex(string: String): Int = stringToIndex.getOrElse(string, 0)

}

case class NextNode(concept: Int) extends WangXueAction {

  def apply(conf: WangXueTransitionState): WangXueTransitionState = {
    // label current node
    // pop current node from node stack
    val tree = conf.currentGraph.labelNode(conf.nodesToProcess.head, NextNode.getConcept(concept))
    conf.copy(nodesToProcess = conf.nodesToProcess.tail, currentGraph = tree)
  }
  override def toString: String = "NextNode: " + concept + " -> " + NextNode.conceptMaster(concept)

}

object NextNode {
  private val conceptMaster = Map[Int, String]((0 -> "UNKNOWN"), (1 -> "attack-01"), (2 -> "train-01"), (3 -> "threaten-01"),
    (4 -> "computer"), (5 -> "military"), (6 -> "container"), (7 -> "network"), (8 -> "thing"), (9 -> "kill-01"), (10 -> "explosive"))
  private val stringToIndex = conceptMaster map (_ match { case (index, text) => (text -> index) })

  def all(): Array[WangXueAction] = {
    (conceptMaster.keys map (i => NextNode(i))).toArray
  }

  def getConcept(index: Int): String = conceptMaster.getOrElse(index, "UNKNOWN")
  def getConceptIndex(string: String): Int = stringToIndex.getOrElse(string, 0)
}

case object DeleteNode extends WangXueAction {

  def apply(conf: WangXueTransitionState): WangXueTransitionState = {
    // delete node from graph (always a leaf, so no complications)
    // and pop it from the stack
    val tree = conf.currentGraph.removeNode(conf.nodesToProcess.head)
    conf.copy(nodesToProcess = conf.nodesToProcess.tail, currentGraph = tree)
  }

}

case object Swap extends WangXueAction {

  def apply(conf: WangXueTransitionState): WangXueTransitionState = ???

}

case object Reattach extends WangXueAction {

  def apply(conf: WangXueTransitionState): WangXueTransitionState = ???

}

case object ReplaceHead extends WangXueAction {

  def apply(conf: WangXueTransitionState): WangXueTransitionState = ???

}

case object Reentrance extends WangXueAction {

  def apply(conf: WangXueTransitionState): WangXueTransitionState = ???

}

case object Merge extends WangXueAction {

  def apply(conf: WangXueTransitionState): WangXueTransitionState = ???

}
