package amr
import dagger.core._
import amr.ImportConcepts.{ relationMaster, conceptMaster, relation, concept, relationIndex, conceptIndex, insertableConcepts }

abstract class WangXueAction extends TransitionAction[WangXueTransitionState] {
  def isPermissible(state: WangXueTransitionState): Boolean
  def name: String
}

case class NextEdge(relIndex: Int) extends WangXueAction {

  def apply(conf: WangXueTransitionState): WangXueTransitionState = {
    // label current arc
    // pop current arc from arc stack
    val tree = conf.currentGraph.labelArc(conf.nodesToProcess.head, conf.childrenToProcess.head, relation(relIndex))
    conf.copy(childrenToProcess = conf.childrenToProcess.tail, currentGraph = tree)
  }
  override def toString: String = "NextEdge: " + relIndex + " -> " + relation(relIndex)
  override def name: String = "NextEdge"
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
      case _ => tree.childrenOf(newNodesToProcess.head)
    }
    conf.copy(nodesToProcess = newNodesToProcess, childrenToProcess = childrenOfNewNode, currentGraph = tree)
  }
  override def toString: String = "NextNode: " + conceptIndex + " -> " + concept(conceptIndex)
  override def name: String = "NextNode"
  override def isPermissible(state: WangXueTransitionState): Boolean = state.childrenToProcess.isEmpty
}

object NextNode {

  def all(): Array[WangXueAction] = {
    (conceptMaster.keys map (i => NextNode(i))).toArray
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
      case _ => tree.childrenOf(newNodesToProcess.head)
    }
    state.copy(nodesToProcess = newNodesToProcess, childrenToProcess = childrenOfNewTopNode, currentGraph = tree)
  }
  override def isPermissible(state: WangXueTransitionState): Boolean = {
    state.childrenToProcess.isEmpty && state.nodesToProcess.nonEmpty && state.currentGraph.isLeafNode(state.nodesToProcess.head)
  }
  override def name: String = "DeleteNode"
}

case class Insert(conceptIndex: Int, otherRef: String = "") extends WangXueAction {
  // otherRef is a hack to allow us to track mapping of AMR and DT nodes during expert parsing
  // Really should be encapsulated elsewhere

  def apply(state: WangXueTransitionState): WangXueTransitionState = {
    // We create a new node, and insert it as parent of this node
    // We then continue processing the current node
    // We try a few heuristics to match up AMR node to the newly inserted node
    val amrRef = if (otherRef == "") estimatedAMRRef(state) else otherRef
    val (newNode, tree) = state.currentGraph.insertNodeAbove(state.nodesToProcess.head, conceptIndex, amrRef)
    state.copy(nodesToProcess = Insert.insertNodeIntoProcessList(newNode, tree, state.nodesToProcess), currentGraph = tree)
  }
  // we can Insert a node as long as we have no edges, and are not processing the root node (always the last node processed)
  override def isPermissible(state: WangXueTransitionState): Boolean = state.nodesToProcess.size > 1
  override def name: String = "Insert"
  override def toString: String = "InsertNode: " + concept(conceptIndex) + " (Ref: " + otherRef + ")"

  def estimatedAMRRef(state: WangXueTransitionState): String = {
    def getAMRParents(nodeAMR: Option[String]): List[String] = nodeAMR match {
      case None => List[String]()
      case Some(amrKey) => state.originalInput.get.amr.get.parentsOf(amrKey)
    }
    if (state.originalInput.isEmpty || state.originalInput.get.amr.isEmpty) "" else {
      val conceptToMatch = concept(conceptIndex)
      val amr = state.originalInput.get.amr.get
      val fullMapDTtoAMR = state.originalInput.get.positionToAMR ++ state.currentGraph.insertedNodes
      val fullMapAMRtoDT = fullMapDTtoAMR map { case (key, value) => (value -> key) }
      val currentNode = state.nodesToProcess.head
      val currentParents = if (currentNode == 0) List() else state.currentGraph.parentsOf(currentNode)
      val currentNodeAMR = fullMapDTtoAMR.get(currentNode)
      val sigmaAMRParents = getAMRParents(currentNodeAMR)
      val unmatchedParents = sigmaAMRParents filter (!fullMapAMRtoDT.contains(_))
      val unmatchedParentLabels = unmatchedParents map (amr.nodes(_))
      val actualMatches = (unmatchedParentLabels zip unmatchedParents) filter { case (label, ref) => label == conceptToMatch }
      actualMatches match {
        case Nil => ""
        case head :: tail => head._2
      }
    }
  }

}

object Insert {
  val transSystem = new WangXueTransitionSystem

  def insertNodeIntoProcessList(node: Int, tree: DependencyTree, currentList: List[Int]): List[Int] = {
    // we create the nodesToProcess list de novo
    // and then remove all those nodes that are not the new one to insert, or any of the existing ones
    // we also need to make sure that the first node in the returned List is unchanged
    val fullList = transSystem.init(tree).nodesToProcess
    currentList.head :: (fullList intersect (node :: currentList.tail)) // intersect retains the order of the first list
  }

  def all(): Array[WangXueAction] = {
    (insertableConcepts map (i => Insert(conceptIndex(i)))).toArray
  }
}

case class Reattach(newNode: Int) extends WangXueAction {
  // For the moment we just change the edge - but do NOT label it
  // TODO: Update Reattach to include the label as a parameter - as per Wang Xue algo

  // We need to pop beta from edge stack, and add a new edge to the partial graph. 
  // We have two scenarios:
  // i) The node we connect to has not yet been processed (no issues - we'll deal with this when we get to it)
  // ii) The node has already been processed. This is a problem in that we would need to revisit it.  Easiest
  // way to do this is to add it back into the list of nodesToProcess...although this will also re-visit all the
  // other edges...hence I assume why it makes sense to label the relation at the same time, to avoid this.
  // In which case, I will exclude this possibility until Phase II - when I include label as a parameter to the action
  //...may also make sense to have two Actions...ReattachUp and ReattachDown...? After all, if I Reattach an edge to a 
  // node that us yet to be processed, it makes sense to delay the naming of the relation too!

  def apply(conf: WangXueTransitionState): WangXueTransitionState = {
    // check node is yet to be processed
    assert(isPermissible(conf))
    // then just pop out edge, and update currentGraph
    val currentEdgeKey = (conf.nodesToProcess.head, conf.childrenToProcess.head)
    val edgeLabel = conf.currentGraph.arcs.getOrElse(currentEdgeKey, "UNKNOWN")
    val newEdgeKey = (newNode, conf.childrenToProcess.head)
    val tree = conf.currentGraph.copy(arcs = conf.currentGraph.arcs - currentEdgeKey + (newEdgeKey -> edgeLabel))
    conf.copy(childrenToProcess = conf.childrenToProcess.tail, currentGraph = tree)
  }
  override def name: String = "Reattach"
  def isPermissible(state: WangXueTransitionState): Boolean = {
    false
    //    state.childrenToProcess.nonEmpty && (state.nodesToProcess contains newNode) && state.nodesToProcess.head != newNode
  }
}

case object Swap extends WangXueAction {

  def apply(state: WangXueTransitionState): WangXueTransitionState = {
    // We swap the direction of the arc - and make beta the new currentNode (keeping sigma in the list)
    // We also make Beta the head of the subgraph - i.e. arcs that link sigma to its parents are moved to beta, with a single arc from beta to sigma
    val sigma = state.nodesToProcess.head
    val beta = state.childrenToProcess.head
    val tree = state.currentGraph.swapArc(sigma, beta)
    state.copy(nodesToProcess = sigma :: beta :: state.nodesToProcess.tail, childrenToProcess = state.childrenToProcess.tail, currentGraph = tree)
  }
  override def name: String = "Swap"
  override def isPermissible(state: WangXueTransitionState): Boolean = state.childrenToProcess.nonEmpty &&
    !(state.currentGraph.swappedArcs contains ((state.childrenToProcess.head, state.nodesToProcess.head)))

}

case object ReplaceHead extends WangXueAction {

  def apply(state: WangXueTransitionState): WangXueTransitionState = {
    // We delete sigma, and move all arcs in/out of sigma to be in/out of beta
    // and move beta to top of stack, without otherwise changing the order
    val tree = state.currentGraph.mergeNodes(state.nodesToProcess.head, state.childrenToProcess.head)
    state.copy(nodesToProcess = state.childrenToProcess.head :: state.nodesToProcess.tail,
      childrenToProcess = tree.childrenOf(state.childrenToProcess.head), currentGraph = tree)
  }
  override def name: String = "ReplaceHead"
  def isPermissible(state: WangXueTransitionState): Boolean = state.childrenToProcess.nonEmpty &&
    !(state.currentGraph.insertedNodes contains state.nodesToProcess.head)
}

case class Reentrance(newNode: Int) extends WangXueAction {
}
case object Reentrance extends WangXueAction {

  def apply(conf: WangXueTransitionState): WangXueTransitionState = ???
  def isPermissible(state: WangXueTransitionState): Boolean = false
  override def name: String = "Reentrance"

}
