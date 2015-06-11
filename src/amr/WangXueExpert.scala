package amr
import dagger.core._

class WangXueExpert extends HeuristicPolicy[Sentence, WangXueAction, WangXueTransitionState] {

  val debug = false

  override def chooseTransition(data: Sentence, state: WangXueTransitionState): WangXueAction = {

    if (debug) println("Considering current node: " + state.nodesToProcess.head + 
        " with child " + (if (state.childrenToProcess isEmpty) "Nil" else state.childrenToProcess.head))
    val chosenAction = state.childrenToProcess match {
      case Nil =>
        if (currentNodeIsNotIncludedInAGoldSpan(data, state) && state.currentGraph.isLeafNode(state.nodesToProcess.head)) DeleteNode
        else NextNode(conceptForCurrentNode(data, state))
      case head :: tail => NextEdge(relationBetweenSigmaAndBeta(data, state))
    }
    if (debug) println("Action chosen: " + chosenAction)
    chosenAction
  }

  def currentNodeIsNotIncludedInAGoldSpan(data: Sentence, state: WangXueTransitionState): Boolean = {
    val span @ (position, _) = data.dependencyTree.nodeSpans.getOrElse(state.nodesToProcess.head, (0, 0))
    if (debug) {
      println("Position: " + position)
      println("InGoldSpan: " + (data.positionToAMR contains position))
    }
    !(data.positionToAMR contains position)
  }

  def conceptForCurrentNode(data: Sentence, state: WangXueTransitionState): Int = {
    val conceptKey = data.mapFromDTtoAMR.getOrElse(state.nodesToProcess.head, "NONE")
    val conceptString = data.amr.get.nodes.getOrElse(conceptKey, "NONE")
    if (debug) {
      println("Concept: " + conceptKey + " -> " + conceptString)
      println("Index: " + NextNode.getConceptIndex(conceptString))
    }
    NextNode.getConceptIndex(conceptString)
  }

  def relationBetweenSigmaAndBeta(data: Sentence, state: WangXueTransitionState): Int = {
    val currentNode = data.mapFromDTtoAMR(state.nodesToProcess.head)
    val childNode = data.mapFromDTtoAMR(state.childrenToProcess.head)
    val relationString = data.amr.get.arcs((currentNode, childNode))
    if (debug) {
      println("Edge: " + currentNode + "\t" + childNode + "\t" + relationString)
      println("Index: " + NextEdge.getRelationIndex(relationString))
    }
    NextEdge.getRelationIndex(relationString)
  }

}