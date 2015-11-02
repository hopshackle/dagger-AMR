package amr
import dagger.core._
import java.util.concurrent.atomic._
import amr.ImportConcepts.{ relationMaster, conceptMaster, relation, concept, relationIndex, conceptIndex, insertableConcepts, conceptsPerLemma, edgesPerLemma }

abstract class WangXueAction extends TransitionAction[WangXueTransitionState] {
  def isPermissible(state: WangXueTransitionState): Boolean
  def name: String
}

object WangXueAction {
  
  private val idFountain = new AtomicInteger(1)
  
  def construct(name: String): WangXueAction = {
    name match {
      case x if x startsWith "NextEdge" =>
        val index = relationIndex(x.replaceAll("NextEdge", ""))
        NextEdge(index)
      case x if x startsWith "NextNode" =>
        val index = conceptIndex(x.replaceAll("NextNode", ""))
        NextNode(index)
      case "DeleteNode" => DeleteNode
      case x if x startsWith "Insert" =>
        val index = conceptIndex(x.replaceAll("Insert", ""))
        Insert(index)
      case x if x startsWith "Reattach" => 
        val concept = x.replaceAll("Reattach", "")
        val action = if (concept == "") Reattach(0) else Reattach(idFountain.getAndIncrement)
        if (idFountain.get > 20000) idFountain.set(1)
        action.attachConcept = concept
        action
      case "Swap" => Swap
      case "ReplaceHead" => ReplaceHead
      case "ReversePolarity" => ReversePolarity
      case x if x startsWith "Reentrance" => 
        Reentrance(0)
    }
  }
}

trait hasNodeAsParameter {
  def parameterNode: Int
}

case class NextEdge(relIndex: Int) extends WangXueAction {

  def apply(conf: WangXueTransitionState): WangXueTransitionState = {
    val edge = (conf.nodesToProcess.head, conf.childrenToProcess.head)
    val relationToUse = if (conf.processedEdges contains edge) relationIndex(conf.currentGraph.arcs(edge)) else relIndex
    val tree = conf.currentGraph.labelArc(conf.nodesToProcess.head, conf.childrenToProcess.head, relation(relationToUse))
    conf.copy(childrenToProcess = conf.childrenToProcess.tail, currentGraph = tree, previousActions = this :: conf.previousActions,
      processedEdges = conf.processedEdges + ((conf.nodesToProcess.head, conf.childrenToProcess.head))).fastForward
  }
  override def toString: String = "NextEdge: " + relIndex + " -> " + relation(relIndex)
  override def name: String = "NextEdge" + relation(relIndex)
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
    val conceptToUse = conceptIndex match {
      case -2 => conf.currentGraph.nodeLemmas(conf.nodesToProcess.head) + "-01"  // VERB-FORM
      case -1 => conf.currentGraph.nodeLemmas(conf.nodesToProcess.head)          // LEMMA
      case 0 => conf.currentGraph.nodes(conf.nodesToProcess.head)                // WORD
      case i => concept(i)
    }
    val tree = conf.currentGraph.labelNode(conf.nodesToProcess.head, conceptToUse)
    val newNodesToProcess = conf.nodesToProcess.tail
    val childrenOfNewNode = newNodesToProcess match {
      case Nil => Nil
      case _ => tree.childrenOf(newNodesToProcess.head)
    }
    conf.copy(nodesToProcess = newNodesToProcess, childrenToProcess = childrenOfNewNode.toList, currentGraph = tree, previousActions = this :: conf.previousActions,
      processedNodes = conf.processedNodes + conf.nodesToProcess.head).fastForward
  }
  override def toString: String = "NextNode: " + conceptIndex + " -> " + concept(conceptIndex)
  override def name: String = "NextNode" + concept(conceptIndex)
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
    state.copy(nodesToProcess = newNodesToProcess, childrenToProcess = childrenOfNewTopNode.toList, currentGraph = tree, previousActions = this :: state.previousActions).fastForward
  }
  override def isPermissible(state: WangXueTransitionState): Boolean = {
      state.childrenToProcess.isEmpty && state.nodesToProcess.nonEmpty && state.currentGraph.isLeafNode(state.nodesToProcess.head) &&
      !state.currentGraph.insertedNodes.contains(state.nodesToProcess.head)
  }
  override def name: String = "DeleteNode"
}

case class Insert(conceptIndex: Int, otherRef: String = "") extends WangXueAction {
  // otherRef is a hack to allow us to track mapping of AMR and DT nodes during expert parsing
  // Really should be encapsulated elsewhere

  def apply(state: WangXueTransitionState): WangXueTransitionState = {
    // We create a new node, and insert it as parent of this node (this includes providing the concept - which could be overridden,
    // but we need to not add the node to the processed list as we might want to put some Reentrant arcs in)
    // We then continue processing the current node
    // We try a few heuristics to match up AMR node to the newly inserted node
    val amrRef = if (otherRef == "") Insert.estimatedAMRRef(state, conceptIndex) else otherRef
    val (newNode, tree) = state.currentGraph.insertNodeAbove(state.nodesToProcess.head, conceptIndex, amrRef)
    state.copy(nodesToProcess = Insert.insertNodeIntoProcessList(newNode, tree, state.nodesToProcess), currentGraph = tree,
      previousActions = this :: state.previousActions).fastForward
  }

  override def isPermissible(state: WangXueTransitionState): Boolean = Insert.isPermissible(state)
  override def name: String = "Insert" + concept(conceptIndex)
  override def toString: String = "InsertNode: " + concept(conceptIndex) + " (Ref: " + otherRef + ")"
  override def equals(other: Any): Boolean = {
    other match {
      case Insert(i, ref) => i == conceptIndex // as ref is just used for expert tracking
      case _ => false
    }
  }
  override def hashCode: Int = {
    conceptIndex
  }

}

object Insert {
  val transSystem = WangXueTransitionSystem

  // we can Insert a node as long as we have no edges
  // We also apply a restriction that we can only insert a node if this is the first time we are visiting sigma
  // We also hard-code a restriction that the inserted concept cannot be within two arcs of sigma (to avoid one form of pathology)
  def isPermissible(state: WangXueTransitionState): Boolean = {
      (state.previousActions.isEmpty || (state.previousActions.head match {
        case NextNode(_) => true
        case ReplaceHead => true
        case Swap => true
        case DeleteNode => true
        case _ => false
      }))
  }
  def insertNodeIntoProcessList(node: Int, tree: DependencyTree, currentList: List[Int]): List[Int] = {
    // we create the nodesToProcess list de novo
    // and then remove all those nodes that are not the new one to insert, or any of the existing ones
    // we also need to make sure that the first node in the returned List is unchanged
    val fullList = transSystem.init(tree).nodesToProcess
    currentList.head :: (fullList intersect (node :: currentList.tail)) // intersect retains the order of the first list
  }

  def all(): Array[WangXueAction] = {
    (insertableConcepts.values.flatten.map(i => Insert(conceptIndex(i)))).toArray
  }
  def estimatedAMRRef(state: WangXueTransitionState, conceptIndex: Int): String = {
    // first we check immediate parents
    // then we check immediate children
    def getAMRParents(nodeAMR: Option[String]): Seq[String] = nodeAMR match {
      case None => List[String]()
      case Some(amrKey) => state.originalInput.get.amr.get.parentsOf(amrKey)
    }
    def getAMRChildren(nodeAMR: Option[String]): Seq[String] = nodeAMR match {
      case None => List[String]()
      case Some(amrKey) => state.originalInput.get.amr.get.childrenOf(amrKey)
    }
    if (state.originalInput.isEmpty || state.originalInput.get.amr.isEmpty) "" else {
      val conceptToMatch = concept(conceptIndex)
      val amr = state.originalInput.get.amr.get
      val fullMapDTtoAMR = state.originalInput.get.positionToAMR ++ (state.currentGraph.insertedNodes filter { case (i, ref) => ref != "" })
      val fullMapAMRtoDT = fullMapDTtoAMR map { case (key, value) => (value -> key) }
      val currentNode = state.nodesToProcess.head
      val currentParents = if (currentNode == 0) List() else state.currentGraph.parentsOf(currentNode)
      val currentChildren = if (currentNode == 0) List() else state.currentGraph.parentsOf(currentNode)
      val currentNodeAMR = fullMapDTtoAMR.get(currentNode)
      val sigmaAMRParents = getAMRParents(currentNodeAMR)
      val sigmaAMRChildren = getAMRChildren(currentNodeAMR)
      val unmatchedNodes = (sigmaAMRParents ++ sigmaAMRChildren) filter (!fullMapAMRtoDT.contains(_))
      val unmatchedNodeLabels = unmatchedNodes map (amr.nodes(_))
//      println(unmatchedNodeLabels)
      val actualMatches = (unmatchedNodeLabels zip unmatchedNodes) filter { case (label, ref) => label == conceptToMatch }
      actualMatches match {
        case Nil => ""
        case head +: tail => head._2
      }
    }
  }
}

case class Reattach(newNode: Int) extends WangXueAction with hasNodeAsParameter {
  // We need to pop beta from edge stack, and add a new edge to the partial graph. 
  // We have two scenarios:
  // i) The node we connect to has not yet been processed (no issues - we'll deal with this when we get to it)
  // ii) The node has already been processed. This is a problem in that we would need to revisit it.  Easiest
  // way to do this is to add it back into the list of nodesToProcess...although this will also re-visit all the
  // other edges...
  val disableReattach = false;
  var attachConcept = ""
  override val parameterNode = newNode
  override def getMasterLabel = Reattach(0)
  def apply(conf: WangXueTransitionState): WangXueTransitionState = {
    // check node is yet to be processed
    assert(isPermissible(conf))
    // then just pop out edge, and update currentGraph
    val currentEdgeKey = (conf.nodesToProcess.head, conf.childrenToProcess.head)
    attachConcept = conf.currentGraph.nodeLemmas.getOrElse(newNode, "")
    val edgeLabel = conf.currentGraph.arcs.getOrElse(currentEdgeKey, "UNKNOWN")
    val newEdgeKey = (newNode, conf.childrenToProcess.head)
    val edgeAlreadyExists = conf.currentGraph.arcs.contains(newEdgeKey)
    val reattachmentNodeHasBeenProcessed = !conf.nodesToProcess.contains(parameterNode)
    val newArcs = if (edgeAlreadyExists) 
      conf.currentGraph.arcs - currentEdgeKey
    else 
      conf.currentGraph.arcs - currentEdgeKey + (newEdgeKey -> edgeLabel)
      
    val tree = conf.currentGraph.copy(arcs = newArcs,
      reattachedNodes = conf.childrenToProcess.head :: conf.currentGraph.reattachedNodes)
    if (reattachmentNodeHasBeenProcessed) {
      conf.copy(nodesToProcess = Insert.insertNodeIntoProcessList(parameterNode, tree, conf.nodesToProcess),
        childrenToProcess = conf.childrenToProcess.tail, currentGraph = tree, previousActions = this :: conf.previousActions).fastForward
    } else {
      conf.copy(childrenToProcess = conf.childrenToProcess.tail, currentGraph = tree, previousActions = this :: conf.previousActions).fastForward
    }
  }
  override def name: String = "Reattach" + attachConcept
  def isPermissible(state: WangXueTransitionState): Boolean = {
    !disableReattach && 
      state.childrenToProcess.nonEmpty && newNode != state.nodesToProcess.head &&
      state.currentGraph.nodes.contains(newNode) &&
      !(state.currentGraph.reattachedNodes contains state.childrenToProcess.head) &&
      !(state.currentGraph.subGraph(state.childrenToProcess.head) contains newNode) &&
      (state.currentGraph.getDistanceBetween(state.childrenToProcess.head, newNode) <= Reattach.REATTACH_RANGE ||
        math.abs(state.currentGraph.nodeSpans.getOrElse(newNode, (-100, -100))._1 - 
            state.currentGraph.nodeSpans.getOrElse(state.childrenToProcess.head, (100, 100))._1) <= 2)
    // Do not reattach to somewhere within subgraph of beta - or you'll create a loop!
    // And also only consider attachment points range in the current graph
  }
}

case object Reattach {
  var REATTACH_RANGE = 6
}

case object Swap extends WangXueAction {

  def apply(state: WangXueTransitionState): WangXueTransitionState = {
    // We swap the direction of the arc - and make beta the new currentNode (keeping sigma in the list)
    // We also make Beta the head of the subgraph - i.e. arcs that link sigma to its parents are moved to beta, with a single arc from beta to sigma
    val sigma = state.nodesToProcess.head
    val beta = state.childrenToProcess.head
    val tree = state.currentGraph.swapArc(sigma, beta)
    val newNodesToProcess = if (state.nodesToProcess.tail contains beta) {
      state.nodesToProcess
    } else {
      sigma :: beta :: state.nodesToProcess.tail
    }
    state.copy(nodesToProcess = newNodesToProcess, childrenToProcess = state.childrenToProcess.tail, currentGraph = tree,
      previousActions = this :: state.previousActions).fastForward
  }
  override def name: String = "Swap"
  override def isPermissible(state: WangXueTransitionState): Boolean = state.childrenToProcess.nonEmpty &&
    !(state.currentGraph.swappedArcs contains ((state.childrenToProcess.head, state.nodesToProcess.head))) &&
    !subgraphOfSigmaIncludesBeta(state)

  def subgraphOfSigmaIncludesBeta(state: WangXueTransitionState): Boolean = {
    val sigma = state.nodesToProcess.head
    val beta = state.childrenToProcess.head
    val subgraphsContainBeta = for {
      child <- state.currentGraph.childrenOf(sigma)
      if child != beta
    } yield state.currentGraph.subGraph(child) contains beta
    subgraphsContainBeta contains true
  }
}

case object ReplaceHead extends WangXueAction {

  def apply(state: WangXueTransitionState): WangXueTransitionState = {
    // We delete sigma, and move all arcs in/out of sigma to be in/out of beta
    // and move beta to top of stack, without otherwise changing the order
    val tree = state.currentGraph.mergeNodes(state.nodesToProcess.head, state.childrenToProcess.head)
    state.copy(nodesToProcess = state.childrenToProcess.head :: (state.nodesToProcess.tail diff List(state.childrenToProcess.head)),
      childrenToProcess = tree.childrenOf(state.childrenToProcess.head).toList, currentGraph = tree, previousActions = this :: state.previousActions).fastForward
  }
  override def name: String = "ReplaceHead"
  def isPermissible(state: WangXueTransitionState): Boolean = state.childrenToProcess.nonEmpty &&
    !(state.currentGraph.insertedNodes contains state.nodesToProcess.head) &&
    !Swap.subgraphOfSigmaIncludesBeta(state)
}

case object ReversePolarity extends WangXueAction {
  def apply(state: WangXueTransitionState): WangXueTransitionState = {
    // We simply insert a new child polarity node
    val amrRef = Insert.estimatedAMRRef(state, conceptIndex("-"))
 //   println("ReversePolarity AMR Ref: "+ amrRef + " from " + state.nodesToProcess.head + ", Last Action: " + state.previousActions.headOption)
    val (newNode, tree) = state.currentGraph.insertNodeBelow(state.nodesToProcess.head, conceptIndex("-"), amrRef, label = "polarity")
    state.copy(nodesToProcess = Insert.insertNodeIntoProcessList(newNode, tree, state.nodesToProcess),
      childrenToProcess = newNode :: state.childrenToProcess, currentGraph = tree, previousActions = this :: state.previousActions,
      processedEdges = state.processedEdges + ((state.nodesToProcess.head, newNode)), processedNodes = state.processedNodes + newNode).fastForward
  }

  override def isPermissible(state: WangXueTransitionState): Boolean =
      (state.previousActions.isEmpty || (state.previousActions.head match {
        case NextNode(_) => true
        case ReplaceHead => true
        case Swap => true
        case DeleteNode => true
        case Insert(_, _) => true
        case _ => false
      }))
  override def name: String = "ReversePolarity"
  override def toString: String = "ReversePolarity"
}

case class Reentrance(kappa: Int) extends WangXueAction with hasNodeAsParameter {
  override val parameterNode = kappa
  override def getMasterLabel = Reentrance(0)
  override def name: String = "Reentrance"
  def apply(conf: WangXueTransitionState): WangXueTransitionState = {
    assert(isPermissible(conf), "Trying to Reenter in inadmissible state.\n" + conf)
    val newEdgeKey = (conf.nodesToProcess.head, kappa)
    val tree = conf.currentGraph.copy(arcs = conf.currentGraph.arcs + (newEdgeKey -> "reentrance"))
    conf.copy(childrenToProcess = List(kappa), currentGraph = tree, previousActions = this :: conf.previousActions).fastForward
  }
  def isPermissible(state: WangXueTransitionState): Boolean = WangXueTransitionSystem.reentrance && 
    state.nodesToProcess.nonEmpty && state.childrenToProcess.isEmpty &&
    state.currentGraph.nodes.contains(kappa) &&
    !(state.currentGraph.subGraph(kappa) contains state.nodesToProcess.head) &&
    (state.currentGraph.getDistanceBetween(kappa, state.nodesToProcess.head) < 5 ||
        math.abs(state.currentGraph.nodeSpans.getOrElse(kappa, (-100, -100))._1 - 
            state.currentGraph.nodeSpans.getOrElse(state.nodesToProcess.head, (100, 100))._1) <= 2)

}

