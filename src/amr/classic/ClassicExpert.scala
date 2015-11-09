package amr.classic

import dagger.core._
import amr.Sentence
import amr.ImportConcepts._

class ClassicExpert extends HeuristicPolicy[Sentence, ClassicAction, ClassicTransitionState] {

  val debug = false

  override def chooseTransition(data: Sentence, state: ClassicTransitionState): ClassicAction = {
    if (data.positionToAMR.contains(state.nodesToProcess.head))
      NextNode(conceptForCurrentNode(data, state))
    else
      DeleteNode
  }

  def conceptForCurrentNode(data: Sentence, state: ClassicTransitionState): Int = {

    val conceptKey = data.positionToAMR.getOrElse(state.nodesToProcess.head, "NULL")
    val concept = quote.replaceAllIn(data.amr.get.nodes.getOrElse(conceptKey, "NULL"), "")
    if (debug) {
      println("Concept: " + conceptKey + " -> " + concept)
      println("Index: " + conceptIndex(concept))
    }
    if (!ClassicTransitionSystem.preferKnown) {
      if (concept == state.currentGraph.nodeLemmas(state.nodesToProcess.head))
        -1
      else if (concept == state.currentGraph.nodeLemmas(state.nodesToProcess.head) + "-01")
        -2
      else if (concept == state.currentGraph.nodes(state.nodesToProcess.head))
        0
      else
        conceptIndex(concept)
    } else {
      conceptIndex(concept)
    }
  }
}