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
    val conceptString = data.amr.get.nodes.getOrElse(conceptKey, "NULL")
    if (debug) {
      println("Concept: " + conceptKey + " -> " + conceptString)
      println("Index: " + conceptIndex(conceptString))
    }
    conceptIndex(conceptString)
  }
}