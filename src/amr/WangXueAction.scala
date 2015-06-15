package amr
import dagger.core._
import amr.ImportConcepts.{ relationMaster, conceptMaster, relation, concept, relationIndex, conceptIndex }

abstract class WangXueAction extends TransitionAction[WangXueTransitionState] {
  def isPermissible(state: WangXueTransitionState): Boolean
}

case class NextEdge(relIndex: Int) extends WangXueAction {

  def apply(conf: WangXueTransitionState): WangXueTransitionState = {
    // label current arc
    // pop current arc from arc stack
    val tree = conf.currentGraph.labelArc(conf.nodesToProcess.head, conf.childrenToProcess.head, relation(relIndex))
    conf.copy(childrenToProcess = conf.childrenToProcess.tail, currentGraph = tree)
  }
  override def toString: String = "NextEdge: " + relIndex + " -> " + relation(relIndex)
  override def isPermissible(state: WangXueTransitionState): Boolean = state.childrenToProcess.nonEmpty
}

object NextEdge {

  def all(): Array[WangXueAction] = {
    (relationMaster.keys map (i => NextEdge(i))).toArray
  }
}

case class NextNode(conceptIndex: Int) extends WangXueAction {

  def apply(conf: WangXueTransitionState): WangXueTransitionState = {
    // label current node
    // pop current node from node stack
    val tree = conf.currentGraph.labelNode(conf.nodesToProcess.head, concept(conceptIndex))
    val newNodesToProcess = conf.nodesToProcess.tail
    val childrenOfNewNode = newNodesToProcess match {
      case Nil => Nil
      case _ => NextNode.childrenOfNode(newNodesToProcess.head, tree)
    }
    WangXueTransitionState(newNodesToProcess, childrenOfNewNode, tree)
  }
  override def toString: String = "NextNode: " + conceptIndex + " -> " + concept(conceptIndex)
  override def isPermissible(state: WangXueTransitionState): Boolean = state.childrenToProcess.isEmpty
}

object NextNode {

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

case class Insert(conceptIndex: Int) extends WangXueAction {

  def apply(conf: WangXueTransitionState): WangXueTransitionState = {
    // We create a new node, and insert it as parent of this node
    // We then continue processing the current node
    val (newNode, tree) = conf.currentGraph.insertNodeAbove(conf.nodesToProcess.head, conceptIndex)
    conf.copy(nodesToProcess = Insert.insertNodeIntoProcessList(newNode, tree, conf.nodesToProcess), currentGraph = tree)
  }
  // we can Insert a node as long as we have no edges, and are not processing the root node (always the last node processed)
  override def isPermissible(state: WangXueTransitionState): Boolean = state.childrenToProcess.isEmpty && state.nodesToProcess.size > 1
  override def toString: String = "InsertNode: " + concept(conceptIndex)
}

object Insert {
  val transSystem = new WangXueTransitionSystem
  
  def insertNodeIntoProcessList(node: Int, tree: DependencyTree, currentList: List[Int]): List[Int] = {
    // we create the nodesToProcess list de novo
    // and then remove all those nodes that are not the new one to insert, or any of the existing ones
    val fullList = transSystem.init(tree).nodesToProcess
    fullList intersect (node :: currentList) // intersect retains the order of the first list
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
