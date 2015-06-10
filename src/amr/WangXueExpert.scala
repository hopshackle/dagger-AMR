package amr
import dagger.core._

class WangXueExpert extends HeuristicPolicy[Sentence, WangXueAction, WangXueTransitionState] {

  override def chooseTransition(data: Sentence, state: WangXueTransitionState): WangXueAction = {

    state.childrenToProcess match {
      case Nil =>
        if (currentNodeIsNotIncludedInAGoldSpan(data, state)) DeleteNode
        else NextNode(conceptForCurrentNode(data, state))
      case head :: tail => NextEdge(relationBetweenSigmaAndBeta(data, state))
    }
  }

  def currentNodeIsNotIncludedInAGoldSpan(data: Sentence, state: WangXueTransitionState): Boolean = {
    val position = data.dependencyTree.nodeSpans(state.nodesToProcess.head)._1
    data.positionToAMR contains position
  }

  def conceptForCurrentNode(data: Sentence, state: WangXueTransitionState): Int = {
    val conceptString = data.mapFromDTtoAMR(state.nodesToProcess.head)
    NextNode.getConceptIndex(conceptString)
  }

  def relationBetweenSigmaAndBeta(data: Sentence, state: WangXueTransitionState): Int = {
    val currentNode = data.mapFromDTtoAMR(state.nodesToProcess.head)
    val childNode = data.mapFromDTtoAMR(state.childrenToProcess.head)
    val relationString = data.amr.get.arcs((currentNode, childNode))
    NextEdge.getRelationIndex(relationString)
  }

}