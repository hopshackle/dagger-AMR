package amr

import dagger.core.TransitionSystem

class WangXueTransitionSystem extends TransitionSystem[Sentence, WangXueAction, WangXueTransitionState] {

  override def actions: Array[WangXueAction] = NextEdge.all ++ NextNode.all ++ Array(DeleteNode)
  
  def approximateLoss(datum: Sentence, state: WangXueTransitionState, action: WangXueAction): Double = ???
  def chooseTransition(datum: Sentence, state: WangXueTransitionState): WangXueAction = ???
  
  def construct(state: WangXueTransitionState, datum: Sentence): Sentence = {
    Sentence(datum.rawText, state.currentGraph, state.currentGraph.toAMR)
  }
  
  def expertApprox(datum: Sentence, state: WangXueTransitionState): Sentence = ???

  def init(datum: Sentence): WangXueTransitionState = {

    def getAllNodes(previousLevel: List[Int]): List[Int] = {
      previousLevel match {
        case Nil => Nil
        case _ =>
          val nextLevel = for {
            ((parent, child), label) <- datum.dependencyTree.arcs
            if previousLevel contains parent
          } yield child
          getAllNodes(nextLevel.toList) ++ previousLevel
      }
    }

    val allNodes = getAllNodes(List(0)) // we start with the root node, which is always 0

    // all Nodes with leaves first, so we finish with the root
    // the children of the top node (which will always be Nil at initialisation)
    // and the complete dependency tree
    WangXueTransitionState(allNodes, Nil, datum.dependencyTree)
  }

  def isPermissible(action: WangXueAction, state: WangXueTransitionState): Boolean = ???
  
  def isTerminal(state: WangXueTransitionState): Boolean = state.nodesToProcess.isEmpty

}