package amr.classic
import dagger.core._
import amr._
import amr.ImportConcepts.{ conceptIndex, concept, conceptMaster, relation, relationIndex }

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

  def nodesInTokenOrder(graph: DependencyTree, fragments: Map[Int, List[Int]]): List[Int] = {
    val fragmentNodes = fragments.values.flatten.toSeq diff fragments.keys.toSeq
    val spans = graph.nodeSpans
    graph.nodes.keys.toList.sortWith {
      case (a1, a2) =>
        spans.getOrElse(a1, (0, 0))._1 > spans.getOrElse(a2, (0, 0))._1
    } filterNot fragmentNodes.contains
  }
}

import ClassicAction._

case class NextNode(conceptIndex: Int) extends ClassicAction {

  def apply(conf: ClassicTransitionState): ClassicTransitionState = {
    val conceptToUse = conceptIndex match {
      case -2 => conf.currentGraph.nodeLemmas(conf.nodesToProcess.head) + "-01" // VERB-FORM
      case -1 => conf.currentGraph.nodeLemmas(conf.nodesToProcess.head) // LEMMA
      case 0 => conf.currentGraph.nodes(conf.nodesToProcess.head) // WORD
      case i => concept(i)
    }
    val tree = conf.currentGraph.labelNode(conf.nodesToProcess.head, conceptToUse)

    val (newPhase, newNodesToProcess) = if (conf.nodesToProcess.tail.isEmpty) (2, nodesInTokenOrder(tree, conf.fragments)) else (1, conf.nodesToProcess.tail)
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
    val (newPhase, newNodesToProcess) = if (state.nodesToProcess.tail.isEmpty) (2, nodesInTokenOrder(tree, state.fragments)) else (1, state.nodesToProcess.tail)

    state.copy(nodesToProcess = newNodesToProcess, currentGraph = tree, previousActions = this :: state.previousActions, phase = newPhase)
  }
  override def isPermissible(state: ClassicTransitionState): Boolean = state.phase == 1 && state.nodesToProcess.nonEmpty
  override def name: String = "DeleteNode"
}

case object CreateFragment extends ClassicAction {
  def apply(state: ClassicTransitionState): ClassicTransitionState = {
    val newFragments = state.fragments + (state.nodesToProcess.head -> List(state.nodesToProcess.head))
    val (newPhase, newNodesToProcess) = if (state.nodesToProcess.tail.isEmpty) (3, nodesInTokenOrder(state.currentGraph, Map())) else (2, state.nodesToProcess.tail)

    state.copy(nodesToProcess = newNodesToProcess, previousActions = this :: state.previousActions, fragments = newFragments, phase = newPhase)
  }
  override def isPermissible(state: ClassicTransitionState): Boolean = state.phase == 2 && state.nodesToProcess.nonEmpty
  override def name: String = "CreateFragment"
}

case class AssignToFragment(parameterNode: Int) extends ClassicAction with hasNodeAsParameter {
  override def getMasterLabel = AssignToFragment(0)
  def apply(state: ClassicTransitionState): ClassicTransitionState = {
    //  val tree = state.currentGraph.removeNode(state.nodesToProcess.head)
    val newFragments = state.fragments + (parameterNode -> (state.nodesToProcess.head :: state.fragments(parameterNode)))
    val (newPhase, newNodesToProcess) = if (state.nodesToProcess.tail.isEmpty) (3, nodesInTokenOrder(state.currentGraph, Map())) else (2, state.nodesToProcess.tail)
    state.copy(nodesToProcess = newNodesToProcess, previousActions = this :: state.previousActions,
      fragments = newFragments, phase = newPhase)
  }
  override def toString: String = "AssignToFragment: " + parameterNode
  override def name: String = "AssignToFragment"
  override def isPermissible(state: ClassicTransitionState): Boolean = state.phase == 2 && state.nodesToProcess.nonEmpty &&
    state.fragments.contains(parameterNode)
}

object NoParent extends ClassicAction {
  def apply(state: ClassicTransitionState): ClassicTransitionState = {
    val (newPhase, newNodesToProcess) = if (state.nodesToProcess.tail.isEmpty) (4, nodesInTokenOrder(state.currentGraph, Map())) else (3, state.nodesToProcess.tail)
    val newNodePair = if (newPhase == 4 && newNodesToProcess.size > 1) (newNodesToProcess.head, newNodesToProcess.tail.head) else (0, 0)
    state.copy(nodesToProcess = newNodesToProcess, nodePair = newNodePair, phase = newPhase)
  }
  override def toString: String = "NoParent"
  override def name: String = "NoParent"
  override def isPermissible(state: ClassicTransitionState): Boolean = state.phase == 3 && state.nodesToProcess.nonEmpty &&
    state.fragments.contains(state.nodesToProcess.head) && state.fragments(state.nodesToProcess.head).size == 1
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

}

case class AddParent(parentString: String) extends ClassicAction {
  import AddParent._
  def apply(state: ClassicTransitionState): ClassicTransitionState = {
    // now, we need to add in compositeNode as the parent of the fragment. This also means that we really want this as the node in the graph, 
    // so that the fragment just lists its children
    // parentString is of format concept:relation:concept:relation:concept.....
    val allConcepts = parentString.split(":").zipWithIndex filter { case (c, i) => i % 2 == 0 } map { _._1 }
    val topConcept = allConcepts(0)
    val mappedConcepts = mapToAMR(allConcepts.toSeq.reverse, state.originalInput.get,
      state.originalInput.get.positionToAMR.getOrElse(state.nodesToProcess.head, ""))
    // we insert the composite node first, with a concept of just the top of the chain
    // we then remove the childNode it was inserted above
    // allConcepts are in order of insertion. so we can iterate over them as we go up the AMR tree
    val (newNode, tree) = state.currentGraph.insertNodeAbove(state.nodesToProcess.head, conceptIndex(topConcept), "", false)
    val newAMRMap = mappedConcepts map ((_ -> newNode)) toMap
    val newFragments = state.fragments + (newNode -> (state.fragments.getOrElse(state.nodesToProcess.head, List.empty[Int]))) - state.nodesToProcess.head
    val newFragmentHeads = state.fragmentHeads + (newNode -> parentString)
    val (newPhase, newNodesToProcess) = if (state.nodesToProcess.tail.isEmpty) (4, nodesInTokenOrder(tree, Map())) else (3, state.nodesToProcess.tail)
    val newNodePair = if (newPhase == 4 && newNodesToProcess.size > 1) (newNodesToProcess.head, newNodesToProcess.tail.head) else (0, 0)
    state.copy(nodesToProcess = newNodesToProcess, currentGraph = tree, fragments = newFragments, fragmentHeads = newFragmentHeads,
      amrMap = state.amrMap ++ newAMRMap, nodePair = newNodePair, phase = newPhase)
  }
  override def toString: String = "AddParent" + parentString
  override def name: String = "AddParent" + parentString
  override def isPermissible(state: ClassicTransitionState): Boolean = state.phase == 3 && state.nodesToProcess.nonEmpty
}

case class NextEdge(relationIndex: Int) extends ClassicAction {
  def apply(state: ClassicTransitionState): ClassicTransitionState = {
    val parent = if (relationIndex < 0) state.nodePair._2 else state.nodePair._1
    val child = if (relationIndex < 0) state.nodePair._1 else state.nodePair._2
    val tree = if (relationIndex == 0) state.currentGraph else state.currentGraph.labelArc(parent, child, relation(math.abs(relationIndex)))
    val nextNodePosition = state.nodesToProcess.indexOf(state.nodePair._2) + 1
    val updateOfFirstNodeRequired = (nextNodePosition >= state.nodesToProcess.size)
    val lastFirstNode = state.nodesToProcess.size == 2
    val (newPhase, newNodesToProcess, nextNodePair) = (updateOfFirstNodeRequired, lastFirstNode) match {
      case (true, true) => (5, List.empty[Int], (0, 0))
      case (true, false) => (4, state.nodesToProcess.tail, (state.nodesToProcess.tail.head, state.nodesToProcess.tail.tail.head))
      case (false, _) => (4, state.nodesToProcess, (state.nodesToProcess.head, state.nodesToProcess(nextNodePosition)))
    }
    state.copy(nodesToProcess = newNodesToProcess, currentGraph = tree,  phase = newPhase, nodePair = nextNodePair)
  }
  override def toString: String = "NextEdge " + relationIndex + " : "  + relation(relationIndex)
  override def name: String = "NextEdge" + relation(relationIndex)
  override def isPermissible(state: ClassicTransitionState): Boolean = state.phase == 4 && state.nodesToProcess.size > 1 && state.nodePair != (0, 0)
}