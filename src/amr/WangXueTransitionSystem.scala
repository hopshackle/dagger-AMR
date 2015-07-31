package amr

import dagger.core.TransitionSystem

import ImportConcepts.{ conceptsPerLemma, edgesPerLemma, relationIndex, insertableConcepts, conceptIndex, concept }

object WangXueTransitionSystem extends TransitionSystem[Sentence, WangXueAction, WangXueTransitionState] {

  val expert = new WangXueExpert
  var prohibition = true
  var reentrance = false
  val alwaysInsertable = Set("name")
  val alwaysEdgePossibilities = Set("opN", "ROOT")

  // We currently just use the whole flipping dictionary to define the full set of actions
  lazy override val actions: Array[WangXueAction] = Array(DeleteNode) ++ Array(ReplaceHead) ++ Array(Swap) ++ Array(ReversePolarity) ++
    Insert.all ++ NextNode.all ++ NextEdge.all ++ Array(DoNothing)

  // and then add on the actions specific to the nodes of the DependencyTree 
  def actions(state: WangXueTransitionState): Array[WangXueAction] = {
    if (state.nodesToProcess.isEmpty) return Array[WangXueAction]()

    val sigma = state.nodesToProcess.head
    val beta = state.childrenToProcess.headOption

    val edgeKeys = (beta match { case Some(index) => state.currentGraph.nodeLemmas.getOrElse(index, "") + "-IN" :: List(state.currentGraph.nodeLemmas.getOrElse(sigma, "") + "-OUT"); case None => List() })
    val permissibleEdges = (edgeKeys flatMap { lemma => edgesPerLemma.getOrElse(lemma, Set()) }).toSet + 0 ++ (alwaysEdgePossibilities map relationIndex toSet)

    val nextEdgeActions = permissibleEdges map (NextEdge(_))

    if (state.phaseTwo) {
      val reentranceActions = state.currentGraph.getNeighbourhood(sigma, 4) map (Reentrance(_)) filter (_.isPermissible(state))
      nextEdgeActions.toArray ++ reentranceActions ++ Array(DoNothing)
    } else {
      val reattachActions = (if (state.childrenToProcess.isEmpty || (state.currentGraph.reattachedNodes contains beta)) {
        Set[Reattach]()
      } else {
        val possibleNodes = state.currentGraph.getNeighbourhood(sigma, 5) - sigma -- state.currentGraph.subGraph(beta.get)
        possibleNodes map (Reattach(_))
      }).toArray
      val insertNodes = if (Insert.isPermissible(state)) Seq(sigma) filter (state.currentGraph.nodeLemmas contains _) else Seq()
      val prohibitedNodes = if (prohibition) {
        val parents = state.currentGraph.parentsOf(sigma)
        val grandParents = parents flatMap state.currentGraph.parentsOf
        val children = state.currentGraph.childrenOf(sigma)
        val grandChildren = children flatMap state.currentGraph.childrenOf
        ((sigma +: parents) ++ grandParents ++ children ++ grandChildren).toSet map state.currentGraph.nodes
      } else Set[String]()
      val insertable = ((insertNodes map state.currentGraph.nodeLemmas flatMap { lemma => insertableConcepts.getOrElse(lemma.toLowerCase, Set()) }).toSet ++ alwaysInsertable diff prohibitedNodes map conceptIndex)
      val insertActions = insertable map (Insert(_))
      val wordIndex = conceptIndex(state.currentGraph.nodes(sigma))
      val permissibleConcepts = beta match {
        case Some(b) => Set()
        case None =>
          val pc1 = conceptsPerLemma.getOrElse(state.currentGraph.nodeLemmas.getOrElse(sigma, "UNKNOWN"), Set())
          if (wordIndex != 0 && (pc1 contains wordIndex)) pc1 else pc1 + 0
      }
      val nextNodeActions = permissibleConcepts map (NextNode(_))

      reattachActions ++ nextNodeActions ++ nextEdgeActions ++ insertActions ++ Array(DeleteNode) ++ Array(ReplaceHead) ++ Array(Swap) ++ Array(ReversePolarity)
    }
  }

  def approximateLoss(datum: Sentence, state: WangXueTransitionState, action: WangXueAction): Double = ???
  def chooseTransition(datum: Sentence, state: WangXueTransitionState): WangXueAction = expert.chooseTransition(datum, state)

  def construct(state: WangXueTransitionState, datum: Sentence): Sentence = {
    Sentence(datum.rawText, state.currentGraph, Some(state.currentGraph.toAMR))
  }

  def expertApprox(datum: Sentence, state: WangXueTransitionState): Sentence = ???

  def init(datum: Sentence): WangXueTransitionState = {

    val rootNode = datum.dependencyTree.getRoots.head
    val allNodes = datum.dependencyTree.getAllChildren(List(rootNode)) // we start with the root node, which is usually 0

    // all Nodes with leaves first, so we finish with the root
    // the children of the top node (which will always be Nil at initialisation)
    // and the complete dependency tree
    WangXueTransitionState(allNodes.toList, Nil, datum.dependencyTree, List(), Some(datum), datum.dependencyTree, Set(), Set(0))
  }

  // helper method - as we don't always have the full Sentence
  def init(datum: DependencyTree): WangXueTransitionState = init(Sentence("", datum, None))

  override def isPermissible(action: WangXueAction, state: WangXueTransitionState): Boolean = action.isPermissible(state)

  override def isTerminal(state: WangXueTransitionState): Boolean = if (reentrance) (state.nodesToProcess.isEmpty && state.phaseTwo) else
    (state.nodesToProcess.isEmpty || state.phaseTwo)

  override def permissibleActions(state: WangXueTransitionState): Array[WangXueAction] = {
    actions(state).filter(action => isPermissible(action, state))
  }
}
