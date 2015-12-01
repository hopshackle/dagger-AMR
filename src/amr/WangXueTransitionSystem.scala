package amr

import dagger.core.TransitionSystem

import ImportConcepts._

object WangXueTransitionSystem extends TransitionSystem[Sentence, WangXueAction, WangXueTransitionState] {

  val expert = new WangXueExpert
  var prohibition = true
  var reentrance = false
  var reentrancePhase = true
  var useCompositeNodes = false
  var preferKnown = true
  var wikification = true
  val alwaysInsertable = Set("name")
  val alwaysEdgePossibilities = Set("opN")

  // We currently just use the whole flipping dictionary to define the full set of actions
  lazy override val actions: Array[WangXueAction] = Array(DeleteNode) ++ Array(ReplaceHead) ++ Array(Swap) ++ Array(ReversePolarity) ++ Array(DoNothing) ++
    Insert.all ++ NextNode.all ++ NextEdge.all ++ (if (useCompositeNodes) AddParent.all else Nil)

  // and then add on the actions specific to the nodes of the DependencyTree 
  def actions(state: WangXueTransitionState): Array[WangXueAction] = {
    if (state.nodesToProcess.isEmpty) return Array[WangXueAction]()

    val sigma = state.nodesToProcess.head
    val beta = state.childrenToProcess.headOption

    val edgeKeys = (beta match { case Some(index) => state.currentGraph.nodeLemmas.getOrElse(index, "") + "-IN" :: List(state.currentGraph.nodeLemmas.getOrElse(sigma, "") + "-OUT"); case None => List() })
    val permissibleEdges = beta match {
      case None => Set.empty[Int]
      case Some(_) => (edgeKeys flatMap { lemma => edgesPerLemma.getOrElse(lemma, Set()) }).toSet + 0 ++ (alwaysEdgePossibilities map relationIndex toSet)
    }
    val nextEdgeActions = permissibleEdges map (NextEdge(_))

    val reentranceActions = if (reentrance && ((reentrancePhase && state.phase == 2) || (!reentrancePhase && state.phase == 1))) {
      val tokenPos = state.currentGraph.nodeSpans.getOrElse(sigma, (100, 100))._1
      val neighbouringTokens = state.currentGraph.nodes.keySet filter { n => math.abs(state.currentGraph.nodeSpans.getOrElse(n, (-100, -100))._1 - tokenPos) <= 2 }
      (state.currentGraph.getNeighbourhood(sigma, 4) ++ neighbouringTokens - sigma) map (Reentrance(_)) filter (_.isPermissible(state))
    } else Set[Reentrance]()

    val wikifyActions = if (Wikify("FORWARD").isPermissible(state))
      wikifications(state.currentGraph.nodes(sigma)) map { Wikify(_) } toArray
    else
      Array.empty[Wikify]

    val reattachActions = (if (state.phase == 2 || Reattach.disableReattach || state.childrenToProcess.isEmpty ||
      (state.currentGraph.reattachedNodes contains beta.get)) {
      Set[Reattach]()
    } else {
      val tokenPos = state.currentGraph.nodeSpans.getOrElse(beta.get, (100, 100))._1
      val neighbouringTokens = state.currentGraph.nodes.keySet filter { n => math.abs(state.currentGraph.nodeSpans.getOrElse(n, (-100, -100))._1 - tokenPos) <= 2 }
      val possibleNodes = state.currentGraph.getNeighbourhood(sigma, Reattach.REATTACH_RANGE - 1) ++ neighbouringTokens - sigma -- state.currentGraph.subGraph(beta.get)
      possibleNodes map (Reattach(_))
    }).toArray

    val insertActions: Set[WangXueAction] = if (useCompositeNodes) {
      if (state.phase == 1 && Insert.isPermissible(state) && !(state.currentGraph.insertedNodes contains state.nodesToProcess.head)) {
        val insertable = (state.currentGraph.nodeLemmas.values flatMap { lemma => compositeNodes.getOrElse(lemma, Set()) }).toSet
        insertable map (AddParent(_))
      } else Set()
    } else {
      val insertNodes = if (Insert.isPermissible(state)) Seq(sigma) filter (state.currentGraph.nodeLemmas contains _) else Seq()
      val prohibitedNodes = if (prohibition && state.phase == 1) {
        val parents = state.currentGraph.parentsOf(sigma)
        val grandParents = parents flatMap state.currentGraph.parentsOf
        val children = state.currentGraph.childrenOf(sigma)
        val grandChildren = children flatMap state.currentGraph.childrenOf
        ((sigma +: parents) ++ grandParents ++ children ++ grandChildren).toSet map state.currentGraph.nodes
      } else Set[String]()
      val insertable = ((insertNodes map state.currentGraph.nodeLemmas flatMap { lemma => insertableConcepts.getOrElse(lemma.toLowerCase, Set()) }).toSet ++ alwaysInsertable diff prohibitedNodes map conceptIndex)
      if (state.phase == 1) insertable map (Insert(_)) else Set()
    }
    val wordIndex = conceptIndex(state.currentGraph.nodes(sigma))
    val permissibleConcepts = (beta, state.phase) match {
      case (Some(b), _) => Set()
      case (_, 2) => Set()
      case (_, _) =>
        // if the word of the sigma node does not exist, then wordIndex is 0, so adding it will default to WORD
        val pc1 = conceptsPerLemma.getOrElse(state.currentGraph.nodeLemmas.getOrElse(sigma, "UNKNOWN"), Set()) + wordIndex
        if (preferKnown) {
          pc1
        } else {
          val lemmaIndex = conceptIndex(state.currentGraph.nodeLemmas(sigma))
          val verbIndex = conceptIndex(state.currentGraph.nodeLemmas(sigma) + "-01")
          val wordEqualsLemma = wordIndex == lemmaIndex && wordIndex != 0
          pc1 - wordIndex - lemmaIndex - verbIndex ++ (if (wordEqualsLemma) Set(-1, -2) else Set(0, -1, -2))
        }
    }
    val nextNodeActions = permissibleConcepts map (NextNode(_))

    reattachActions ++ nextNodeActions ++ nextEdgeActions ++ insertActions ++ reentranceActions ++ wikifyActions ++
      (Array(DeleteNode, ReplaceHead, Swap, ReversePolarity, DoNothing)).filter(action => isPermissible(action, state))
  }

  def approximateLoss(datum: Sentence, state: WangXueTransitionState, action: WangXueAction): Double = ???
  def chooseTransition(datum: Sentence, state: WangXueTransitionState): WangXueAction = expert.chooseTransition(datum, state)

  def construct(state: WangXueTransitionState, datum: Sentence): Sentence = {
    Sentence(datum.rawText, state.currentGraph, Some(state.currentGraph.toAMR))
  }

  def expertApprox(datum: Sentence, state: WangXueTransitionState): Sentence = ???

  def init(datum: Sentence): WangXueTransitionState = {

    val rootNodes = datum.dependencyTree.getRoots.toList
    val allNodes = datum.dependencyTree.getDescendants(rootNodes)

    // all Nodes with leaves first, so we finish with the roots
    // the children of the top node (which will always be Nil at initialisation)
    // and the complete dependency tree
    WangXueTransitionState(allNodes.toList, Nil, datum.dependencyTree, List(), Some(datum), datum.dependencyTree, Set(), Set(0))
  }

  // helper method - as we don't always have the full Sentence
  def init(datum: DependencyTree): WangXueTransitionState = init(Sentence("", datum, None))

  override def isPermissible(action: WangXueAction, state: WangXueTransitionState): Boolean = action.isPermissible(state)

  override def isTerminal(state: WangXueTransitionState): Boolean = state.nodesToProcess.isEmpty && state.phase == 2

  override def permissibleActions(state: WangXueTransitionState): Array[WangXueAction] = {
    actions(state)
  }
}
