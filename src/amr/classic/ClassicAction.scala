package amr.classic
import dagger.core._
import amr.ImportConcepts.{conceptIndex, concept, conceptMaster}


abstract class ClassicAction extends TransitionAction[ClassicTransitionState] {
  def isPermissible(state: ClassicTransitionState): Boolean
  def name: String
}

object ClassicAction {
  
  def construct(name: String): ClassicAction = {
    name match {
      case x if x startsWith "NextNode" =>
        val index = conceptIndex(x.replaceAll("NextNode", ""))
        NextNode(index)
    }
  }
}

case class NextNode(conceptIndex: Int) extends ClassicAction {

  def apply(conf: ClassicTransitionState): ClassicTransitionState = {
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
    conf.copy(nodesToProcess = newNodesToProcess, currentGraph = tree, previousActions = this :: conf.previousActions,
      processedNodes = conf.processedNodes + conf.nodesToProcess.head)
  }
  override def toString: String = "NextNode: " + conceptIndex + " -> " + concept(conceptIndex)
  override def name: String = "NextNode" + concept(conceptIndex)
  override def isPermissible(state: ClassicTransitionState): Boolean = state.nodesToProcess.size > 0
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
    val newNodesToProcess = state.nodesToProcess.tail
    state.copy(nodesToProcess = newNodesToProcess, currentGraph = tree, previousActions = this :: state.previousActions)
  }
  override def isPermissible(state: ClassicTransitionState): Boolean = {
      state.nodesToProcess.nonEmpty
  }
  override def name: String = "DeleteNode"
}