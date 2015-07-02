package amr

import dagger.core.TransitionSystem

class WangXueTransitionSystem extends TransitionSystem[Sentence, WangXueAction, WangXueTransitionState] {

  // We currently just use the whole flipping dictionary to define the full set of actions
  lazy override val actions: Array[WangXueAction] = NextEdge.all ++ NextNode.all ++ Array(DeleteNode) ++ Insert.all ++
    Array(ReplaceHead) ++ Array(Swap) ++ Array(ParentFlip)

  // and then add on the actions specific to the nodes of the DependencyTree 
  def actions(state: WangXueTransitionState): Array[WangXueAction] = {
    val reattachActions = ((state.currentGraph.nodes.keySet - state.nodesToProcess.head) map (i => Reattach(i))).toArray
    actions ++ reattachActions
  }

  def approximateLoss(datum: Sentence, state: WangXueTransitionState, action: WangXueAction): Double = ???
  def chooseTransition(datum: Sentence, state: WangXueTransitionState): WangXueAction = ???

  def construct(state: WangXueTransitionState, datum: Sentence): Sentence = {
    Sentence(datum.rawText, state.currentGraph, Some(state.currentGraph.toAMR))
  }

  def expertApprox(datum: Sentence, state: WangXueTransitionState): Sentence = ???

  def init(datum: Sentence): WangXueTransitionState = {

    def getAllChildren(parentList: List[Int]): List[Int] = {
      val newChildren = ((parentList flatMap datum.dependencyTree.childrenOf).toSet diff parentList.toSet).toList
      // we don't care what order the new children are in
      if (newChildren.isEmpty)
        parentList // we're done
      else
        getAllChildren(newChildren) ++ parentList
    }

    val rootNode = datum.dependencyTree.getRoots.head
    val allNodes = getAllChildren(List(rootNode)) // we start with the root node, which is usually 0

    // all Nodes with leaves first, so we finish with the root
    // the children of the top node (which will always be Nil at initialisation)
    // and the complete dependency tree
    WangXueTransitionState(allNodes, Nil, datum.dependencyTree, List(), Some(datum), datum.dependencyTree)
  }

  // helper method - as we don't always have the full Sentence
  def init(datum: DependencyTree): WangXueTransitionState = init(Sentence("", datum, None))

  override def isPermissible(action: WangXueAction, state: WangXueTransitionState): Boolean = action.isPermissible(state)

  override def isTerminal(state: WangXueTransitionState): Boolean = state.nodesToProcess.isEmpty

  override def permissibleActions(state: WangXueTransitionState): Array[WangXueAction] = {
    actions(state).filter(action => isPermissible(action, state))
  }
}