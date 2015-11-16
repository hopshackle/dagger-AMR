package amr.classic

import dagger.core._
import amr.Sentence
import amr.ImportConcepts._
import amr.DependencyTree

object ClassicTransitionSystem extends TransitionSystem[Sentence, ClassicAction, ClassicTransitionState] {
  var preferKnown = true
  val expert = new ClassicExpert
  val alwaysEdgePossibilities = Set("opN")

  def actions(): Array[ClassicAction] = NextNode.all ++ Array(DeleteNode)

  def actions(state: ClassicTransitionState) = {
    val sigma = state.nodesToProcess.head
    val lemma = state.currentGraph.nodeLemmas.getOrElse(sigma, "UNKNOWN")
    val wordIndex = conceptIndex(state.currentGraph.nodes(sigma))

    state.phase match {
      case 1 =>
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
      case 2 =>
        val output: Array[ClassicAction] = (state.fragments.keys map { f => AssignToFragment(f) }).toArray ++ Array(CreateFragment)
        output
      case 3 =>
        val output: Array[ClassicAction] = (compositeNodes.toArray map { cn => AddParent(cn) }) ++
          (if (NoParent.isPermissible(state)) Array(NoParent) else Array.empty[ClassicAction])
        output
      case 4 =>
        val nextEdgeIndices = if (state.nodePair == (0, 0)) Set(0) else {
          val concept1 = if (numbers.replaceAllIn(state.currentGraph.nodes(state.nodePair._1), "") == "") "##" else state.currentGraph.nodes(state.nodePair._1)
          val concept2 = if (numbers.replaceAllIn(state.currentGraph.nodes(state.nodePair._2), "") == "") "##" else state.currentGraph.nodes(state.nodePair._2)
          val edgeKeys1 = List(concept1 + "-OUT", concept2 + "-IN")
          val edgeKeys2 = List(concept1 + "-IN", concept2 + "-OUT")
          val permissibleEdges1 = (edgeKeys1 flatMap { concept => edgesPerConcept.getOrElse(concept, Set()) })
          val permissibleEdges2 = (edgeKeys1 flatMap { concept => edgesPerConcept.getOrElse(concept, Set()) }) map { i => -i }

          permissibleEdges1.toSet ++ permissibleEdges2.toSet + 0 ++
            (alwaysEdgePossibilities map relationIndex toSet) ++ (alwaysEdgePossibilities map (i => -relationIndex(i)) toSet)
        }
        val output: Array[ClassicAction] = (nextEdgeIndices map NextEdge).toArray
        output
      case _ =>
        Array.empty[ClassicAction]
    }
  }

  def approximateLoss(datum: Sentence, state: ClassicTransitionState, action: ClassicAction): Double = ???

  def chooseTransition(datum: Sentence, state: ClassicTransitionState): ClassicAction = expert.chooseTransition(datum, state)

  def construct(state: ClassicTransitionState, datum: Sentence): Sentence = {
    Sentence(datum.rawText, state.currentGraph, Some(state.currentGraph.copy(nodes = state.currentGraph.nodes,
      arcs = state.currentGraph.arcs).toAMR))
  }
  def expertApprox(datum: Sentence, state: ClassicTransitionState): amr.Sentence = ???

  def init(datum: Sentence): ClassicTransitionState = {

    val allNodes = remainingNodesInTokenOrder(datum)

    val startingGraph = DependencyTree(datum.dependencyTree.nodes, datum.dependencyTree.nodeLemmas, datum.dependencyTree.nodePOS,
      datum.dependencyTree.nodeNER, datum.dependencyTree.nodeSpans, Map(), Map(), List(), Map(), Map(), Set(), Map())
    // remove all edges
    ClassicTransitionState(allNodes, startingGraph, Nil, Some(datum), (0, 0), datum.dependencyTree, Set(), Set(), Map())
  }

  def remainingNodesInTokenOrder(datum: Sentence): List[Int] = {
    val spans = datum.dependencyTree.nodeSpans
    datum.dependencyTree.nodes.keys.toList.sortWith {
      case (a1, a2) =>
        spans.getOrElse(a1, (0, 0))._1 > spans.getOrElse(a2, (0, 0))._1
    }
  }

  override def isPermissible(action: ClassicAction, state: ClassicTransitionState): Boolean = action.isPermissible(state)

  override def isTerminal(state: ClassicTransitionState): Boolean = state.phase == 5

  override def permissibleActions(state: ClassicTransitionState): Array[ClassicAction] = {
    actions(state)
  }

}
