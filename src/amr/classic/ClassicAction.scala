package amr.classic
import dagger.core._
import amr._
import amr.ImportConcepts.{ conceptIndex, concept, conceptMaster }

abstract class ClassicAction extends TransitionAction[ClassicTransitionState] {
  def isPermissible(state: ClassicTransitionState): Boolean
  def name: String
  def construct(name: String): ClassicAction = ClassicAction.construct(name)
}

object ClassicAction {

  def construct(name: String): ClassicAction = {
    name match {
      case x if x startsWith "NextNode" =>
        val index = conceptIndex(x.replaceAll("NextNode", ""))
        NextNode(index)
      case "DeleteNode" => DeleteNode
    }
  }

  def nodesInTokenOrder(graph: DependencyTree): List[Int] = {
    val spans = graph.nodeSpans
    graph.nodes.keys.toList.sortWith {
      case (a1, a2) =>
        spans.getOrElse(a1, (0, 0))._1 > spans.getOrElse(a2, (0, 0))._1
    }
  }
}

case class NextNode(conceptIndex: Int) extends ClassicAction {

  def apply(conf: ClassicTransitionState): ClassicTransitionState = {
    val conceptToUse = conceptIndex match {
      case -2 => conf.currentGraph.nodeLemmas(conf.nodesToProcess.head) + "-01" // VERB-FORM
      case -1 => conf.currentGraph.nodeLemmas(conf.nodesToProcess.head) // LEMMA
      case 0 => conf.currentGraph.nodes(conf.nodesToProcess.head) // WORD
      case i => concept(i)
    }
    val tree = conf.currentGraph.labelNode(conf.nodesToProcess.head, conceptToUse)

    val (newPhase, newNodesToProcess) = if (conf.nodesToProcess.tail.isEmpty) (2, ClassicAction.nodesInTokenOrder(tree)) else (1, conf.nodesToProcess.tail)
    conf.copy(nodesToProcess = newNodesToProcess, currentGraph = tree, previousActions = this :: conf.previousActions,
      processedNodes = conf.processedNodes + conf.nodesToProcess.head, phase = newPhase)
  }
  override def toString: String = "NextNode: " + conceptIndex + " -> " + concept(conceptIndex)
  override def name: String = "NextNode" + concept(conceptIndex)
  override def isPermissible(state: ClassicTransitionState): Boolean = state.phase == 1 && state.nodesToProcess.nonEmpty
}

object NextNode {
  def all(): Array[ClassicAction] = {
    (conceptMaster.keys map (i => NextNode(i))).toArray
  }
}

case object DeleteNode extends ClassicAction {

  def apply(state: ClassicTransitionState): ClassicTransitionState = {
    // delete node from graph (always a leaf, so no complications)
    // and pop it from the stack
    val tree = state.currentGraph.removeNode(state.nodesToProcess.head)
    val (newPhase, newNodesToProcess) = if (state.nodesToProcess.tail.isEmpty) (2, ClassicAction.nodesInTokenOrder(tree)) else (1, state.nodesToProcess.tail)

    state.copy(nodesToProcess = newNodesToProcess, currentGraph = tree, previousActions = this :: state.previousActions, phase = newPhase)
  }
  override def isPermissible(state: ClassicTransitionState): Boolean = state.phase == 1 && state.nodesToProcess.nonEmpty
  override def name: String = "DeleteNode"
}

case object CreateFragment extends ClassicAction {
  def apply(state: ClassicTransitionState): ClassicTransitionState = {
    val newFragments = state.fragments + (state.nodesToProcess.head -> List(state.nodesToProcess.head))
    val (newPhase, newNodesToProcess) = if (state.nodesToProcess.tail.isEmpty) (3, ClassicAction.nodesInTokenOrder(state.currentGraph)) else (2, state.nodesToProcess.tail)

    state.copy(nodesToProcess = newNodesToProcess, previousActions = this :: state.previousActions, fragments = newFragments, phase = newPhase)
  }
  override def isPermissible(state: ClassicTransitionState): Boolean = state.phase == 2 && state.nodesToProcess.nonEmpty
  override def name: String = "CreateFragment"
}

case class AssignToFragment(parameterNode: Int) extends ClassicAction with hasNodeAsParameter {
  override def getMasterLabel = AssignToFragment(0)
  def apply(state: ClassicTransitionState): ClassicTransitionState = {
    val tree = state.currentGraph.removeNode(state.nodesToProcess.head)
    val (newPhase, newNodesToProcess) = if (state.nodesToProcess.tail.isEmpty) (3, ClassicAction.nodesInTokenOrder(tree)) else (2, state.nodesToProcess.tail)
    val newFragments = state.fragments + (parameterNode -> (state.nodesToProcess.head :: state.fragments(parameterNode)))
    state.copy(nodesToProcess = newNodesToProcess, currentGraph = tree, previousActions = this :: state.previousActions,
      fragments = newFragments, phase = newPhase)
  }
  override def toString: String = "AssignToFragment: " + parameterNode
  override def name: String = "AssignToFragment"
  override def isPermissible(state: ClassicTransitionState): Boolean = state.phase == 2 && state.nodesToProcess.nonEmpty &&
    state.fragments.contains(parameterNode)
}

object NoParent extends ClassicAction {
  def apply(state: ClassicTransitionState): ClassicTransitionState = {
    val (newPhase, newNodesToProcess) = if (state.nodesToProcess.tail.isEmpty) (4, ClassicAction.nodesInTokenOrder(state.currentGraph)) else (3, state.nodesToProcess.tail)
    state.copy(nodesToProcess = newNodesToProcess, phase = newPhase)
  }
  override def toString: String = "NoParent"
  override def name: String = "NoParent"
  override def isPermissible(state: ClassicTransitionState): Boolean = state.phase == 3 && state.nodesToProcess.nonEmpty &&
    state.fragments(state.nodesToProcess.head).size == 1
}

case class AddParent(parentString: String) extends ClassicAction {
  def apply(state: ClassicTransitionState): ClassicTransitionState = {
    // now, we need to add in compositeNode as the parent of the fragment. This also means that we really want this as the node in the graph, 
    // so that the fragment just lists its children
    val topConcept = parentString.split(":")(0)
    // we insert the composite node first, with a concept of just the top of the chain
    // we then remove the childNode it was inserted above
    // TODO: We're going to need to update the AMR References I suspect, so that the expert knows what to link 
    // to the node when we get to relation edges
    val (newNode, stageOne) = state.currentGraph.insertNodeAbove(state.nodesToProcess.head, conceptIndex(topConcept), "")
    val stageTwo = stageOne.removeNode(state.nodesToProcess.head)
    val newFragments = state.fragments + (newNode -> (state.fragments(state.nodesToProcess.head))) - state.nodesToProcess.head
    val newFragmentHeads = state.fragmentHeads + (newNode -> parentString)
    val (newPhase, newNodesToProcess) = if (state.nodesToProcess.tail.isEmpty) (4, ClassicAction.nodesInTokenOrder(stageTwo)) else (3, state.nodesToProcess.tail)
    state.copy(nodesToProcess = newNodesToProcess, currentGraph = stageTwo, fragments = newFragments, fragmentHeads = newFragmentHeads,
      phase = newPhase)
  }
  override def toString: String = "AddParent" + parentString
  override def name: String = "AddParent" + parentString
  override def isPermissible(state: ClassicTransitionState): Boolean = state.phase == 3 && state.nodesToProcess.nonEmpty
}