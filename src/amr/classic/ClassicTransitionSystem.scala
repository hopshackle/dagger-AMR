package amr.classic

import dagger.core._
import amr.Sentence
import amr.ImportConcepts._
import amr.DependencyTree

object ClassicTransitionSystem extends TransitionSystem[Sentence, ClassicAction, ClassicTransitionState] {
  var preferKnown = true
  val expert = new ClassicExpert

  def actions(): Array[ClassicAction] = NextNode.all ++ Array(DeleteNode)

  def actions(state: ClassicTransitionState) = {
    val sigma = state.nodesToProcess.head
    val lemma = state.currentGraph.nodeLemmas.getOrElse(sigma, "UNKNOWN")
    val wordIndex = conceptIndex(state.currentGraph.nodes(sigma))

    val permissibleConcepts = {
      // if the word of the sigma node does not exist, then wordIndex is 0, so adding it will default to WORD
      val pc1 = conceptsPerLemma.getOrElse(lemma, Set()) + wordIndex
      if (preferKnown) {
        pc1
      } else {
        val lemmaIndex = conceptIndex(state.currentGraph.nodeLemmas(sigma))
        val verbIndex = conceptIndex(state.currentGraph.nodeLemmas(sigma) + "-01")
        val wordEqualsLemma = wordIndex == lemmaIndex && wordIndex != 0
        pc1 - wordIndex - lemmaIndex - verbIndex ++ (if (wordEqualsLemma) Set(-1, -2) else Set(0, -1, -2))
      }
    }
    val nextNodeActions: Array[ClassicAction] = permissibleConcepts.toArray map (NextNode(_))

    nextNodeActions ++ Array(DeleteNode)
  }

  def approximateLoss(datum: Sentence, state: ClassicTransitionState, action: ClassicAction): Double = ???

  def chooseTransition(datum: Sentence, state: ClassicTransitionState): ClassicAction = expert.chooseTransition(datum, state)

  def construct(state: ClassicTransitionState, datum: Sentence): Sentence = {
    Sentence(datum.rawText, state.currentGraph, Some(state.currentGraph.toAMR))
  }
  def expertApprox(datum: Sentence, state: ClassicTransitionState): amr.Sentence = ???

  def init(datum: Sentence): ClassicTransitionState = {

    val spans = datum.dependencyTree.nodeSpans
    val allNodes = datum.dependencyTree.nodes.keys.toList.sortWith {
      case (a1, a2) =>
        spans.getOrElse(a1, (0, 0))._1 > spans.getOrElse(a2, (0, 0))._1
    }

    val startingGraph = DependencyTree(datum.dependencyTree.nodes, datum.dependencyTree.nodeLemmas, datum.dependencyTree.nodePOS,
    datum.dependencyTree.nodeNER, datum.dependencyTree.nodeSpans, Map(), Map(), List(), Map(), Map(), Set(), Map()) 
    // remove all edges
    ClassicTransitionState(allNodes, startingGraph, Nil, Some(datum), datum.dependencyTree, Set(), Set())
  }

  override def isPermissible(action: ClassicAction, state: ClassicTransitionState): Boolean = action.isPermissible(state)

  override def isTerminal(state: ClassicTransitionState): Boolean = state.nodesToProcess.isEmpty

  override def permissibleActions(state: ClassicTransitionState): Array[ClassicAction] = {
    actions(state)
  }

}
