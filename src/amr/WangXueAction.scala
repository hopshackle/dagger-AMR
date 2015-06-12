package amr
import dagger.core._

abstract class WangXueAction extends TransitionAction[WangXueTransitionState] {
  def isPermissible(state: WangXueTransitionState): Boolean
}

case class NextEdge(relation: Int) extends WangXueAction {

  def apply(conf: WangXueTransitionState): WangXueTransitionState = {
    // label current arc
    // pop current arc from arc stack
    val tree = conf.currentGraph.labelArc(conf.nodesToProcess.head, conf.childrenToProcess.head, NextEdge.getRelation(relation))
    conf.copy(childrenToProcess = conf.childrenToProcess.tail, currentGraph = tree)
  }
  override def toString: String = "NextEdge: " + relation + " -> " + NextEdge.relationMaster(relation)
  override def isPermissible(state: WangXueTransitionState): Boolean = state.childrenToProcess.nonEmpty

}

object NextEdge {
  private val relationStrings = ImportConcepts.loadRelations("C:\\AMR\\AMR2.txt")
  private val relationMaster = (for {
    (relation, index) <- relationStrings zipWithIndex
  } yield ((index + 1) -> relation)).toMap + (0 -> "UNKNOWN")

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
    val newNodesToProcess = conf.nodesToProcess.tail
    val childrenOfNewNode = newNodesToProcess match {
      case Nil => Nil
      case _ => NextNode.childrenOfNode(newNodesToProcess.head, tree)
    }
    WangXueTransitionState(newNodesToProcess, childrenOfNewNode, tree)
  }
  override def toString: String = "NextNode: " + concept + " -> " + NextNode.conceptMaster(concept)
  override def isPermissible(state: WangXueTransitionState): Boolean = state.childrenToProcess.isEmpty

}

object NextNode {
  private val conceptStrings = ImportConcepts.loadConcepts("C:\\AMR\\AMR2.txt")
  private val conceptMaster = (for {
    (concept, index) <- conceptStrings zipWithIndex
  } yield ((index + 1) -> concept)).toMap + (0 -> "UNKNOWN")
  private val stringToIndex = conceptMaster map (_ match { case (index, text) => (text -> index) })

  def all(): Array[WangXueAction] = {
    (conceptMaster.keys map (i => NextNode(i))).toArray
  }

  def childrenOfNode(newHead: Int, tree: DependencyTree): List[Int] = {
    (for {
      edge @ ((parent, child), _) <- tree.arcs
      if parent == newHead
    } yield child)
      .toList
  }

  def getConcept(index: Int): String = conceptMaster.getOrElse(index, "UNKNOWN")
  def getConceptIndex(string: String): Int = stringToIndex.getOrElse(string, 0)
}

case object DeleteNode extends WangXueAction {

  def apply(state: WangXueTransitionState): WangXueTransitionState = {
    // delete node from graph (always a leaf, so no complications)
    // and pop it from the stack
    val tree = state.currentGraph.removeNode(state.nodesToProcess.head)
    val newNodesToProcess = state.nodesToProcess.tail
    val childrenOfNewTopNode = newNodesToProcess match {
      case Nil => Nil
      case _ => NextNode.childrenOfNode(newNodesToProcess.head, tree)
    }
    WangXueTransitionState(newNodesToProcess, childrenOfNewTopNode, tree)
  }
  override def isPermissible(state: WangXueTransitionState): Boolean = {
    state.childrenToProcess.isEmpty && state.nodesToProcess.nonEmpty && state.currentGraph.isLeafNode(state.nodesToProcess.head)
  }
}

case object Swap extends WangXueAction {

  def apply(conf: WangXueTransitionState): WangXueTransitionState = ???
  def isPermissible(state: WangXueTransitionState): Boolean = false

}

case object Reattach extends WangXueAction {

  def apply(conf: WangXueTransitionState): WangXueTransitionState = ???
  def isPermissible(state: WangXueTransitionState): Boolean = false
}

case object ReplaceHead extends WangXueAction {

  def apply(conf: WangXueTransitionState): WangXueTransitionState = ???
  def isPermissible(state: WangXueTransitionState): Boolean = false

}

case object Reentrance extends WangXueAction {

  def apply(conf: WangXueTransitionState): WangXueTransitionState = ???
  def isPermissible(state: WangXueTransitionState): Boolean = false

}

case object Merge extends WangXueAction {

  def apply(conf: WangXueTransitionState): WangXueTransitionState = ???
  def isPermissible(state: WangXueTransitionState): Boolean = false

}

