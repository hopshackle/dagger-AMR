package amr

import dagger.core.TransitionSystem

class WangXueTransitionSystem extends TransitionSystem[Sentence, WangXueAction, WangXueTransitionState] {

  // We currently just use the whole flipping dictionary to define the full set of actions
  override val actions: Array[WangXueAction] = NextEdge.all ++ NextNode.all ++ Array(DeleteNode) ++ Insert.all

  // and then add on the actions specific to the nodes of the DependencyTree 
  def actions(state: WangXueTransitionState): Array[WangXueAction] = {
    val reattachActions = (state.nodesToProcess.tail map (i => Reattach(i))).toArray
    actions ++ reattachActions
  }

  def approximateLoss(datum: Sentence, state: WangXueTransitionState, action: WangXueAction): Double = ???
  def chooseTransition(datum: Sentence, state: WangXueTransitionState): WangXueAction = ???

  def construct(state: WangXueTransitionState, datum: Sentence): Sentence = {
    Sentence(datum.rawText, state.currentGraph, Some(state.currentGraph.toAMR))
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
    WangXueTransitionState(allNodes, Nil, datum.dependencyTree, Some(datum))
  }

  // helper method - as we don't always have the full Sentence
  def init(datum: DependencyTree): WangXueTransitionState = init(Sentence("", datum, None))

  def isPermissible(action: WangXueAction, state: WangXueTransitionState): Boolean = action.isPermissible(state)

  def isTerminal(state: WangXueTransitionState): Boolean = state.nodesToProcess.isEmpty

  override def permissibleActions(state: WangXueTransitionState): Array[WangXueAction] = {
    actions(state).filter(action => isPermissible(action, state))
  }
}