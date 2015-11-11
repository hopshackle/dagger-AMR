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
        val output: Array[ClassicAction] = (compositeNodes.toArray map AddParent) ++
          (if (NoParent.isPermissible(state)) Array(NoParent) else Array.empty[ClassicAction])
        output
      case _ =>
        Array.empty[ClassicAction]
    }
  }

  def approximateLoss(datum: Sentence, state: ClassicTransitionState, action: ClassicAction): Double = ???

  def chooseTransition(datum: Sentence, state: ClassicTransitionState): ClassicAction = expert.chooseTransition(datum, state)

  def construct(state: ClassicTransitionState, datum: Sentence): Sentence = {
    // Before constructing AMR from the dependencyTree, we need to unwrap all of the composite nodes
    // We iterate through each fragmentHead - as this defines the set of composite nodes
    // For each of them we insert a new node for each split (from : markers), and then 
    // one node for each fragment content
    // Then we need to insert the edges. This is easy for the final step, as we just use 'opN'.
    // For the earlier edges we need to extract the information from fragmentHead
    var conceptNumber = 501
    def getOdd(input: Array[String]): Array[String] = { ((0 until input.length) filter (_ % 2 != 0) map input).toArray }
    def getEven(input: Array[String]): Array[String] = { ((0 until input.length) filter (_ % 2 == 0) map input).toArray }
    def extractNodesAndArcs(startingNode: Int, input: Array[String]): (Map[Int, String], Map[(Int, Int), String]) = {
      val concepts = getEven(input)
      val relations = getOdd(input)
      val allN = (conceptNumber until conceptNumber + concepts.size - 1) map { k => (k -> concepts(k - conceptNumber + 1)) }
      val allR = if (relations.isEmpty) Map.empty[(Int, Int), String] else Map((startingNode, conceptNumber) -> relations(0)) ++
        ((conceptNumber + 1 until conceptNumber + concepts.size - 1) map { a: Int => (((a - 1, a) -> relations(a - conceptNumber))) })
      conceptNumber += concepts.size
      (allN.toMap, allR)
    }

    val fragmentHeaders = state.fragmentHeads map { case (k, v) => (k, v.split(":")) }
    val blah = for {
      (p, stringArray) <- fragmentHeaders.toList
      (n, a) = extractNodesAndArcs(p, stringArray)
      bottomConcept = if (n.isEmpty) p else n.keys.max
    } yield (n, a, bottomConcept)
    val deletedNodes = state.currentGraph.deletedNodes.values.flatten.toList
    val fragmentTerminals = state.fragments.values.flatten.toList
    val fragmentChildNodes = deletedNodes filter { case (k, v) => fragmentTerminals contains k }
    val allNodes = (blah map { _._1 }).flatten ++ fragmentChildNodes
    // the fragment header key node is already in the graph
    val leafArcs = (fragmentHeaders.keys.zip(blah map { _._3 }) flatMap { case (f, b) => state.fragments(f) map (v => ((b, v) -> "opN")) }).toMap
    val allArcs = (blah map { _._2 }).flatten.toMap ++ leafArcs

    println(allNodes)
    println(allArcs)

    Sentence(datum.rawText, state.currentGraph, Some(state.currentGraph.copy(nodes = state.currentGraph.nodes ++ allNodes,
      arcs = state.currentGraph.arcs ++ allArcs).toAMR))
  }
  def expertApprox(datum: Sentence, state: ClassicTransitionState): amr.Sentence = ???

  def init(datum: Sentence): ClassicTransitionState = {

    val allNodes = remainingNodesInTokenOrder(datum)

    val startingGraph = DependencyTree(datum.dependencyTree.nodes, datum.dependencyTree.nodeLemmas, datum.dependencyTree.nodePOS,
      datum.dependencyTree.nodeNER, datum.dependencyTree.nodeSpans, Map(), Map(), List(), Map(), Map(), Set(), Map())
    // remove all edges
    ClassicTransitionState(allNodes, startingGraph, Nil, Some(datum), datum.dependencyTree, Set(), Set(), Map())
  }

  def remainingNodesInTokenOrder(datum: Sentence): List[Int] = {
    val spans = datum.dependencyTree.nodeSpans
    datum.dependencyTree.nodes.keys.toList.sortWith {
      case (a1, a2) =>
        spans.getOrElse(a1, (0, 0))._1 > spans.getOrElse(a2, (0, 0))._1
    }
  }

  override def isPermissible(action: ClassicAction, state: ClassicTransitionState): Boolean = action.isPermissible(state)

  override def isTerminal(state: ClassicTransitionState): Boolean = state.phase == 4

  override def permissibleActions(state: ClassicTransitionState): Array[ClassicAction] = {
    actions(state)
  }

}
