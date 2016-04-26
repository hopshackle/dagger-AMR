package amr
import dagger.core._
import java.util.concurrent.atomic._
import amr.ImportConcepts._

abstract class WangXueAction extends TransitionAction[WangXueTransitionState] {
  def isPermissible(state: WangXueTransitionState): Boolean
  def name: String
  def construct(name: String): WangXueAction = WangXueAction.construct(name)
  def ignoreAction: Boolean = false
}

object WangXueAction {

  private val idFountain = new AtomicInteger(1)

  def construct(name: String): WangXueAction = {
    name match {
      case x if x startsWith "NextEdge" =>
        val label = x.replaceAll("NextEdge", "")
        val exists = relationStringToIndex contains label
        if (!exists) println("Unknown Edge " + label)
        val index = relationIndex(x.replaceAll("NextEdge", ""))
        NextEdge(index)
      case x if x startsWith "NextNode" =>
        val concept = x.replaceAll("NextNode", "")
        val exists = conceptStringToIndex contains concept
        if (!exists) println("Unknown Concept " + concept)
        val index = conceptIndex(x.replaceAll("NextNode", ""))
        NextNode(index)
      case "DeleteNode" => DeleteNode
      case x if x startsWith "InsertBelow" =>
        val concept = x.replaceAll("InsertBelow", "")
        val exists = conceptStringToIndex contains concept
        if (!exists) println("Unknown Concept (IB) " + concept)
        val index = conceptIndex(concept)
        InsertBelow(index)
      case x if x startsWith "Insert" =>
        val concept = x.replaceAll("Insert", "")
        val exists = conceptStringToIndex contains concept
        if (!exists) println("Unknown Concept (I) " + concept)
        val index = conceptIndex(concept)
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
        val assignConcept = x.replaceAll("Reentrance", "")
        if (idFountain.get > 20000) idFountain.set(1)
        if (assignConcept == "") Reentrance(0) else {
          val action = Reentrance(idFountain.getAndIncrement)
          action.assignConcept = assignConcept
          action
        }
      case "DoNothing" => DoNothing
      case x if x startsWith "AddParent" =>
        val parentString = x.replaceAll("AddParent", "")
        AddParent(parentString)
      case x if x startsWith "Wikify" =>
        val wikiString = x.replaceAll("Wikify", "")
        Wikify(wikiString)
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
    // You never change phase as part of a NextEdge action, and it is valid in either
    conf.copy(childrenToProcess = conf.childrenToProcess.tail, currentGraph = tree, previousActions = this :: conf.previousActions,
      processedEdges = conf.processedEdges + ((conf.nodesToProcess.head, conf.childrenToProcess.head))).fastForward
  }
  override def toString: String = "NextEdge: " + relation(relIndex)
  override def name: String = "NextEdge" + relation(relIndex)
  override def isPermissible(state: WangXueTransitionState): Boolean = state.childrenToProcess.nonEmpty &&
    (if (WangXueTransitionSystem.nameConstraints) {
      val sigma = state.nodesToProcess.head;
      val beta = state.childrenToProcess.head;
      val graph = state.currentGraph;
      !(graph.nodes(sigma) == "name" && !(relation(relIndex) == "opN")) &&  // only allow opN labels out of "name"
        !(graph.nodes(sigma) == "name" && !(graph.isLeafNode(beta))) // and only if beta is a leaf node (else we have to move it)
    } else true)
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
      case -2 => conf.currentGraph.nodeLemmas(conf.nodesToProcess.head) + "-01" // VERB-FORM
      case -1 => conf.currentGraph.nodeLemmas(conf.nodesToProcess.head) // LEMMA
      case 0 => conf.currentGraph.nodes(conf.nodesToProcess.head) // WORD
      case i => concept(i)
    }
    val tree = conf.currentGraph.labelNode(conf.nodesToProcess.head, conceptToUse) removeSelfLoops
    val (newPhase, newNodesToProcess) = (conf.phase, conf.nodesToProcess.tail) match {
      case (1, Nil) =>
        val rootNodes = conf.currentGraph.getRoots.toList
        val allNodes = conf.currentGraph.getDescendants(rootNodes)
        (2, allNodes.toList)
      case (2, Nil) => (2, Nil)
      case (_, _) => (conf.phase, conf.nodesToProcess.tail)
    }
    val childrenOfNewNode = (conf.phase, newNodesToProcess) match {
      case (_, Nil) => Nil
      case (1, _) => tree.childrenOf(newNodesToProcess.head)
      case (_, _) => Nil
    }
    // We can only ever move to phase 2 after a NextNode action (when we're out of nodes)
    // OK..we could delete the root node if it's the last one left

    conf.copy(nodesToProcess = newNodesToProcess, childrenToProcess = childrenOfNewNode.toList, currentGraph = tree, previousActions = this :: conf.previousActions,
      processedNodes = conf.processedNodes + conf.nodesToProcess.head, phase = newPhase).fastForward
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
    val (newPhase, childrenOfNewTopNode) = newNodesToProcess match {
      case Nil => (2, Nil)
      case _ => (1, tree.childrenOf(newNodesToProcess.head))
    }
    state.copy(nodesToProcess = newNodesToProcess, childrenToProcess = childrenOfNewTopNode.toList, currentGraph = tree,
      previousActions = this :: state.previousActions, phase = newPhase).fastForward
  }
  override def isPermissible(state: WangXueTransitionState): Boolean = {
    state.childrenToProcess.isEmpty && state.nodesToProcess.nonEmpty && state.currentGraph.isLeafNode(state.nodesToProcess.head) &&
      state.phase == 1 && !state.currentGraph.insertedNodes.contains(state.nodesToProcess.head)
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
    state.copy(nodesToProcess = Insert.insertNodesIntoProcessList(List(newNode), tree, state.nodesToProcess), currentGraph = tree,
      processedNodes = state.processedNodes + newNode, previousActions = this :: state.previousActions).fastForward
  }

  override def isPermissible(state: WangXueTransitionState): Boolean = Insert.isPermissible(state) && {
    if (WangXueTransitionSystem.nameConstraints) {
      !(conceptMaster(conceptIndex) == "name" && !state.currentGraph.isLeafNode(state.nodesToProcess.head))
    } else true
  }
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

  def isPermissible(state: WangXueTransitionState): Boolean = {
    state.phase == 1 &&
      {
        val sigma = state.nodesToProcess.head
        val graph = state.currentGraph
        (graph.parentsOf(sigma) intersect graph.insertedNodes.mapValues(_._1).toSeq) isEmpty
      }
  }
  def insertNodesIntoProcessList(nodes: List[Int], tree: DependencyTree, currentList: List[Int]): List[Int] = {
    // we create the nodesToProcess list de novo
    // and then remove all those nodes that are not the new ones to insert, or any of the existing ones
    // we also need to make sure that the first node in the returned List is unchanged
    val fullList = transSystem.init(tree).nodesToProcess
    val finalList = currentList.head :: (fullList intersect (nodes ++ currentList.tail)) // intersect retains the order of the first list
    finalList
  }

  def all(): Array[WangXueAction] = {
    (insertableConcepts.values.flatten.toList.distinct.map(i => Insert(conceptIndex(i)))).toArray
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
      val fullMapDTtoAMR = state.originalInput.get.positionToAMR ++ (state.currentGraph.insertedNodes map
        { case (i, (_, ref)) => (i, ref) } filter { _._2 != "" })
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

object AddParent {
  def mapToAMR(allConcepts: Seq[String], sentence: Sentence, cursor: String): Seq[String] = {
    allConcepts match {
      case Nil => Seq()
      case head +: tail =>
        // check against AMR
        if (cursor == "")
          Seq()
        else {
          val cursorAMRParent = sentence.amr.get.parentsOf(cursor)
          val parentIsUnmapped = cursorAMRParent.size == 1 && !sentence.AMRToPosition.contains(cursorAMRParent(0))
          if (parentIsUnmapped && sentence.amr.get.nodes(cursorAMRParent(0)) == head) {
            // if so, then return as mapped, along with later items 
            Seq(cursorAMRParent(0)) ++ mapToAMR(tail, sentence, cursorAMRParent(0))
          } else
            // if not, then stop
            Seq()
        }
    }
  }

  def all(): Array[WangXueAction] = {
    (compositeNodes.values.flatten.toSet map { i: String => AddParent(i) }).toArray
  }
}

case class AddParent(parentString: String) extends WangXueAction {
  import AddParent._
  def apply(state: WangXueTransitionState): WangXueTransitionState = {
    // now, we need to add in compositeNode as the parent of the fragment. This also means that we really want this as the node in the graph, 
    // so that the fragment just lists its children
    // parentString is of format concept:relation:concept:relation:concept.....
    val allConcepts = parentString.split(":").zipWithIndex filter { case (c, i) => i % 2 == 0 } map { _._1 }
    val allRelations = (parentString.split(":").zipWithIndex filter { case (c, i) => i % 2 != 0 } map { _._1 }) ++ Array("XXX")
    val mappedConcepts = state.originalInput.get.amr match {
      case Some(goldAMR) => mapToAMR(allConcepts.toSeq.reverse, state.originalInput.get,
        state.originalInput.get.positionToAMR.getOrElse(state.nodesToProcess.head, ""))
      case None =>
        var out = Seq[String]("")
        for (i <- 1 until allConcepts.size) { out = "" +: out }
        out
    }

    var runningGraph = state.currentGraph
    var insertedNodes = List(state.nodesToProcess.head)
    var insertedEdges = List.empty[(Int, Int)]
    for (((c, r), amrKey) <- allConcepts.reverse zip allRelations.reverse zip mappedConcepts) {
      val (newNode, tree) = runningGraph.insertNodeAbove(insertedNodes.head, conceptIndex(c), amrKey, true)
      insertedEdges = (newNode, insertedNodes.head) :: insertedEdges
      runningGraph = if (r != "XXX") tree.labelArc(newNode, insertedNodes.head, r) else tree
      insertedNodes = newNode :: insertedNodes
    }

    val newProcessedEdges = state.processedEdges ++ (if (insertedEdges.size > 1) insertedEdges.reverse.tail else List())
    state.copy(nodesToProcess = Insert.insertNodesIntoProcessList(insertedNodes.reverse.tail.reverse, runningGraph, state.nodesToProcess), currentGraph = runningGraph,
      processedEdges = newProcessedEdges, previousActions = this :: state.previousActions).fastForward
  }

  override def toString: String = "AddParent" + parentString
  override def name: String = "AddParent" + parentString
  override def isPermissible(state: WangXueTransitionState): Boolean = !(state.currentGraph.insertedNodes contains state.nodesToProcess.head) && Insert.isPermissible(state)
}

case class Reattach(newNode: Int) extends WangXueAction with hasNodeAsParameter {
  // We need to pop beta from edge stack, and add a new edge to the partial graph. 
  // We have two scenarios:
  // i) The node we connect to has not yet been processed (no issues - we'll deal with this when we get to it)
  // ii) The node has already been processed. This is a problem in that we would need to revisit it.  Easiest
  // way to do this is to add it back into the list of nodesToProcess...although this will also re-visit all the
  // other edges...
  var attachConcept = ""
  override val parameterNode = newNode
  override def getMasterLabel = Reattach(0)
  def apply(conf: WangXueTransitionState): WangXueTransitionState = {
    // check node is yet to be processed
    if (Reattach.assertionChecking) assert(isPermissible(conf))
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
      conf.copy(nodesToProcess = Insert.insertNodesIntoProcessList(List(parameterNode), tree, conf.nodesToProcess),
        childrenToProcess = conf.childrenToProcess.tail, currentGraph = tree, previousActions = this :: conf.previousActions).fastForward
    } else {
      conf.copy(childrenToProcess = conf.childrenToProcess.tail, currentGraph = tree, previousActions = this :: conf.previousActions).fastForward
    }
  }
  override def name: String = "Reattach" + attachConcept
  def isPermissible(state: WangXueTransitionState): Boolean = {
    !Reattach.disableReattach &&
      state.childrenToProcess.nonEmpty && newNode != state.nodesToProcess.head &&
      state.phase == 1 && state.currentGraph.nodes.contains(newNode) &&
      !(state.currentGraph.reattachedNodes contains state.childrenToProcess.head) &&
      !(state.currentGraph.subGraph(state.childrenToProcess.head) contains newNode) &&
      (state.currentGraph.getDistanceBetween(state.childrenToProcess.head, newNode) <= Reattach.REATTACH_RANGE ||
        math.abs(state.currentGraph.nodeSpans.getOrElse(newNode, (-100, -100))._1 -
          state.currentGraph.nodeSpans.getOrElse(state.childrenToProcess.head, (100, 100))._1) <= 2) &&
        {
          if (WangXueTransitionSystem.nameConstraints) {
            !(state.currentGraph.nodes.getOrElse(newNode, "") == "name" &&  // firstly do not reattach to a name node is we are not a leaf
              !state.currentGraph.isLeafNode(state.childrenToProcess.head)) &&
              !(state.currentGraph.parentsOf(newNode) map state.currentGraph.nodes contains "name") // secondly don;t attach to a leaf of a name
          } else true
        }
    // Do not reattach to somewhere within subgraph of beta - or you'll create a loop!
    // And also only consider attachment points range in the current graph
  }
}

case object Reattach {
  val disableReattach = false;
  var assertionChecking = false;
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
    state.phase == 1 && !subgraphOfSigmaIncludesBeta(state)

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
    state.phase == 1 && !Swap.subgraphOfSigmaIncludesBeta(state)
}

case object ReversePolarity extends WangXueAction {
  def apply(state: WangXueTransitionState): WangXueTransitionState = {
    // We simply insert a new child polarity node
    val amrRef = Insert.estimatedAMRRef(state, conceptIndex("-"))
    //   println("ReversePolarity AMR Ref: "+ amrRef + " from " + state.nodesToProcess.head + ", Last Action: " + state.previousActions.headOption)
    val (newNode, tree) = state.currentGraph.insertNodeBelow(state.nodesToProcess.head, conceptIndex("-"), amrRef, label = "polarity")
    state.copy(nodesToProcess = Insert.insertNodesIntoProcessList(List(newNode), tree, state.nodesToProcess),
      childrenToProcess = newNode :: state.childrenToProcess, currentGraph = tree, previousActions = this :: state.previousActions,
      processedEdges = state.processedEdges + ((state.nodesToProcess.head, newNode)), processedNodes = state.processedNodes + newNode).fastForward
  }

  override def isPermissible(state: WangXueTransitionState): Boolean = state.phase == 1 &&
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

object InsertBelow {
  def all(): Array[WangXueAction] = {
    (subInsertableConcepts.values.flatten.toList.distinct.map(i => Insert(conceptIndex(i)))).toArray
  }
}

case class InsertBelow(conceptIndex: Int, otherRef: String = "") extends WangXueAction {

  def apply(state: WangXueTransitionState): WangXueTransitionState = {
    // We create a new node, and insert it as parent of this node (this includes providing the concept - which could be overridden,
    // but we need to not add the node to the processed list as we might want to put some Reentrant arcs in)
    // We then continue processing the current node
    // We try a few heuristics to match up AMR node to the newly inserted node
    val amrRef = if (otherRef == "") Insert.estimatedAMRRef(state, conceptIndex) else otherRef
    val (newNode, tree) = state.currentGraph.insertNodeBelow(state.nodesToProcess.head, conceptIndex, amrRef)
    state.copy(currentGraph = tree, childrenToProcess = newNode :: state.childrenToProcess,
      processedNodes = state.processedNodes + newNode, previousActions = this :: state.previousActions).fastForward
  }

  override def isPermissible(state: WangXueTransitionState): Boolean = {
    WangXueTransitionSystem.insertBelow && state.phase == 1 && state.nodesToProcess.nonEmpty && {
      val newConcept = concept(conceptIndex)
      val sigma = state.nodesToProcess.head
      val currentChildren = state.currentGraph.childrenOf(sigma)
      conceptIndex > 0 && !(state.currentGraph.childrenOf(sigma) map state.currentGraph.nodes contains newConcept)
    }
  }
  override def name: String = "InsertBelow" + concept(conceptIndex)
  override def toString: String = "InsertBelow: " + concept(conceptIndex) + " (Ref: " + otherRef + ")"
  override def equals(other: Any): Boolean = {
    other match {
      case InsertBelow(i, ref) => i == conceptIndex // as ref is just used for expert tracking
      case _ => false
    }
  }
  override def hashCode: Int = {
    conceptIndex
  }
}

case object Reentrance {
  var assertionChecking = false
}

case class Reentrance(kappa: Int) extends WangXueAction with hasNodeAsParameter {
  override val parameterNode = kappa
  override def getMasterLabel = Reentrance(0)
  override def name: String = "Reentrance" + assignConcept
  override def toString: String = s"Reentrance($kappa) $assignConcept"
  var assignConcept = ""
  def apply(conf: WangXueTransitionState): WangXueTransitionState = {
    if (Reentrance.assertionChecking) assert(isPermissible(conf), "Trying to Reenter in inadmissible state.\n" + conf)
    val newEdgeKey = (conf.nodesToProcess.head, kappa)
    assignConcept = conf.currentGraph.nodeLemmas.getOrElse(kappa, "")
    val tree = conf.currentGraph.copy(arcs = conf.currentGraph.arcs + (newEdgeKey -> "reentrance"))
    conf.copy(childrenToProcess = List(kappa), currentGraph = tree, previousActions = this :: conf.previousActions).fastForward
  }
  def isPermissible(state: WangXueTransitionState): Boolean = WangXueTransitionSystem.reentrance &&
    ((WangXueTransitionSystem.reentrancePhase && state.phase == 2) || (!WangXueTransitionSystem.reentrancePhase && state.phase == 1)) &&
    state.nodesToProcess.nonEmpty && state.childrenToProcess.isEmpty &&
    state.currentGraph.nodes.contains(kappa) && state.nodesToProcess.head != kappa &&
    !(state.currentGraph.subGraph(kappa) contains state.nodesToProcess.head) &&
    !state.currentGraph.arcs.contains((state.nodesToProcess.head, kappa)) &&
    (state.currentGraph.getDistanceBetween(kappa, state.nodesToProcess.head) < 5 ||
      math.abs(state.currentGraph.nodeSpans.getOrElse(kappa, (-100, -100))._1 -
        state.currentGraph.nodeSpans.getOrElse(state.nodesToProcess.head, (100, 100))._1) <= 2)
}

case object DoNothing extends WangXueAction {
  def apply(state: WangXueTransitionState): WangXueTransitionState = {
    state.copy(nodesToProcess = state.nodesToProcess.tail, previousActions = this :: state.previousActions).fastForward
  }

  override def isPermissible(state: WangXueTransitionState): Boolean = state.phase == 2 && state.childrenToProcess.isEmpty && state.nodesToProcess.nonEmpty
  override def name: String = "DoNothing"
  override def toString: String = "DoNothing"
  override def ignoreAction: Boolean = true
}

case class Wikify(wikiString: String) extends WangXueAction {
  import Wikify._
  def apply(conf: WangXueTransitionState): WangXueTransitionState = {
    val sigma = conf.nodesToProcess.head
    val graph = conf.currentGraph
    val stringToUse = wikiString match {
      case "FORWARD" => forwardWikiString(conf)
      case "DEFAULT" => wikifications.getOrElse(graph.nodes(sigma) + "|" + forwardConcatenationOfNameArgs(graph, sigma), "-")
      case _ => wikiString.replaceAll(quoteString, "")
    }
    val (newNode, tree) = conf.currentGraph.insertNodeBelow(conf.nodesToProcess.head, quoteString + stringToUse + quoteString, "", "wiki")
    conf.copy(currentGraph = tree, previousActions = this :: conf.previousActions, processedEdges = conf.processedEdges + ((conf.nodesToProcess.head, newNode)),
      processedNodes = conf.processedNodes + newNode).fastForward
  }
  override def toString: String = "Wikify: " + wikiString
  override def name: String = "Wikify" + wikiString
  override def isPermissible(state: WangXueTransitionState): Boolean = WangXueTransitionSystem.wikification &&
    state.phase == 2 && state.childrenToProcess.isEmpty && state.nodesToProcess.nonEmpty && !isWikified(state) && {
      state.currentGraph.arcs exists { case ((from, to), edgeName) => from == state.nodesToProcess.head && edgeName == "name" }
    }
}

object Wikify {
  val quoteString = """""""
  def all(): Array[WangXueAction] = {
    (Seq("FORWARD", "-")) map { Wikify(_) } toArray
  }
  def hasExactMatch(state: WangXueTransitionState): Boolean = {
    val sigma = state.nodesToProcess.head
    val graph = state.currentGraph
    wikifications contains (graph.nodes(sigma) + "|" + forwardConcatenationOfNameArgs(graph, sigma))
  }
  def isWikified(state: WangXueTransitionState): Boolean = {
    state.nodesToProcess.headOption match {
      case None => true
      case Some(sigma) =>
        state.currentGraph.edgesToChildren(sigma) map state.currentGraph.arcs contains "wiki"
    }
  }
  def forwardWikiString(conf: WangXueTransitionState): String = wikiString(conf, false)
  private def wikiString(conf: WangXueTransitionState, reverse: Boolean): String = {
    val sortedNodes = sortedNameNodes(conf.currentGraph, conf.nodesToProcess.head)
    (if (reverse) sortedNodes.reverse else sortedNodes) map conf.currentGraph.nodes mkString ("_")
  }
  def isConcatenationOfNameArgs(amr: AMRGraph, node: String, wikification: String): Boolean = {
    (wikification.replaceAll(quoteString, "") == forwardConcatenationOfNameArgs(amr, node))
  }
  def forwardConcatenationOfNameArgs[K](amr: Graph[K], node: K): String = {
    sortedNameNodes(amr, node) map amr.nodes mkString ("_") replaceAll (quoteString, "")
  }

  private def sortedNameNodes[K](graph: Graph[K], node: K): Seq[K] = {
    val nameNodes = graph.childrenOf(node) filter (graph.nodes(_) == "name") flatMap graph.childrenOf
    nameNodes.sortBy { x => graph.nodeSpans.getOrElse(x, (0, 0))._1 }
  }
}