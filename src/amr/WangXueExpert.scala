package amr
import ImportConcepts.{ conceptIndex, relationIndex }
/**
 * @author James
 */
class WangXueExpert extends WangXueExpertBasic {

  val quote = """"""".r

  override def chooseTransition(data: Sentence, state: WangXueTransitionState): WangXueAction = {

    if (debug) println("Considering current node: " + state.nodesToProcess.head +
      " with child " + (if (state.childrenToProcess isEmpty) "Nil" else state.childrenToProcess.head))

    val reentrance = WangXueTransitionSystem.reentrance
    val reentrancePhase = WangXueTransitionSystem.reentrancePhase
    val useCompositeNodes = WangXueTransitionSystem.useCompositeNodes

    def getAMRParents(nodeAMR: Option[String]): Seq[String] = nodeAMR match {
      case None => List[String]()
      case Some(amrKey) => data.amr.get.parentsOf(amrKey)
    }
    def getAMRChildren(nodeAMR: Option[String]): Seq[String] = nodeAMR match {
      case None => List[String]()
      case Some(amrKey) => data.amr.get.childrenOf(amrKey)
    }

    // nodes inserted without an AMR ref are ignored by the expert - if AMR exists, then we estimate
    // the AMR ref of a node Inserted by a non-expert policy at the time of insertion
    val fullMapDTtoAMR = (state.currentGraph.insertedNodes map
      { case (i, (_, ref)) => (i, ref) } filter { _._2 != "" }) ++ data.positionToAMR
    val fullMapAMRtoDT = fullMapDTtoAMR map { case (key, value) => (value -> key) }

    val sigma = state.nodesToProcess.head
    val BETA = if (state.childrenToProcess.isEmpty) -1 else state.childrenToProcess.head
    val SIGMAAMR = fullMapDTtoAMR.get(sigma)
    if (debug) println(s"SIGMAAMR = $SIGMAAMR")

    val BETAAMR = if (BETA == -1) None else fullMapDTtoAMR.get(BETA)

    val sigmaAMRParents = getAMRParents(SIGMAAMR)
    val sigmaAMRChildren = getAMRChildren(SIGMAAMR).toSet

    val sigmaAMRAncestors = SIGMAAMR match {
      case Some(sigma) => data.amr.get.getAncestors(Seq(sigma)).toSet
      case None => Set[String]()
    }
    val sigmaAMRDescendants = SIGMAAMR match {
      case Some(sigma) => data.amr.get.getDescendants(Seq(sigma)).toSet
      case None => Set[String]()
    }

    if (debug) println(sigmaAMRAncestors)

    val sigmaChildren = state.currentGraph.childrenOf(sigma)
    val sigmaChildrenAMR = sigmaChildren map (fullMapDTtoAMR.getOrElse(_, "")) filter (_ != "") toSet

    val unlinkedAMRChildren = sigmaAMRChildren filter (fullMapAMRtoDT contains _) diff sigmaChildrenAMR

    if (debug) println(s"sigmaAMRParents = ${sigmaAMRParents.toString}")
    val betaAMRParents = getAMRParents(BETAAMR)
    if (debug) println(s"betaAMR = ${BETAAMR}")
    //   if (debug) println(s"betaAMRParents = ${betaAMRParents.toString}")
    //    if (debug) println(s"Swap permissible ${Swap.isPermissible(state)}")
    val nodesToProcessAMR = state.nodesToProcess map (fullMapDTtoAMR.getOrElse(_, "")) filter (_ != "")
    val allNodesAMR = fullMapDTtoAMR.values.toSet

    val unmatchedParents = sigmaAMRParents filter { x => !fullMapAMRtoDT.contains(x) }

    val unmatchedChildren = sigmaAMRChildren filter data.amr.get.isLeafNode filter { x => !fullMapAMRtoDT.contains(x) }
    //    if (debug) println(unmatchedParents)
    val unmatchedParentLabels = unmatchedParents map (data.amr.get.nodes(_))
    //    if (debug) println(unmatchedParentLabels)
    val (unmatchedPolarityChildren, unmatchedOtherChildren) = unmatchedChildren filter (c => data.amr.get.arcs((SIGMAAMR.get, c)) != "wiki") partition (c => data.amr.get.nodes(c) == "-")
    val insertableChildren = unmatchedOtherChildren filter (c => InsertBelow(conceptIndex(data.amr.get.nodes(c))).isPermissible(state))

    val needsToBeWikified = SIGMAAMR match {
      case None => false
      case Some(sigma) => WangXueTransitionSystem.wikification && !Wikify.isWikified(state) && getWikiString(data.amr.get, sigma) != ""
    }

    val kappa = unlinkedAMRChildren.toList map fullMapAMRtoDT filter state.currentGraph.nodes.contains filter (Reentrance(_).isPermissible(state))
//    println(s"Sigma: $sigma, $SIGMAAMR, Beta: $BETA, BetaParent: $betaAMRParents")
    val chosenAction = (state.phase, SIGMAAMR, unmatchedParentLabels.isEmpty, BETA, BETAAMR) match {
      // cases to cover: no beta - Delete, Insert or NextNode
      //    If sigmaAMR and the parent AMR node is unmatched to DT, then we InsertNode
      //    If sigmaAMR then we NextNode 
      //    If sigmaAMR is blank, then we DeleteNode
      // beta non-zero: NextEdge, Reattach, InsertNode are our options
      //    If sigmaAMR and the parent AMR node is unmatched to DT, then we InsertNode
      //    If betaAMR and its parent AMR node is matched with another DT node that is not sigma, then Reattach (if valid)
      //    If betaAMR and its parent is matched to sigma, then NextEdge
      //    If betaAMR and its parent is unmatched (and sigmaAMR) then...? [Next Edge ... we need a MERGE action for this one]
      //    If betaAMR and its parent is unmatched (and NOT sigmaAMR) then we ReplaceHead - and delete sigma, merging it into beta
      //    If NOT betaAMR...should be impossible, as we assign all lower nodes to AMR nodes as we climb the tree...which might 
      //      happen if Classifier policy has been making some decisions, even if it would never happen with only this expert policy.
      //      If beta has no children in this case, then we Reattach to the next node to be processed, with the intention of Deleting it later (a recovery action)
      //      If beta does have children, then currently irrecoverable. We NextEdge and move on.
      case (1, Some(sigmaAMR), _, beta, Some(betaAMR)) if (sigmaAMR != "" &&
        (sigmaAMRAncestors contains betaAMR) &&
        !(sigmaAMRDescendants contains betaAMR) &&
        Swap.isPermissible(state)) => Swap
      case (1, Some(sigmaAMR), false, _, _) if (Insert.isPermissible(state)) =>
        if (useCompositeNodes) {
          val parentString = getParentString(data, state, fullMapAMRtoDT)
          AddParent(parentString)
        } else
          Insert(conceptIndex(unmatchedParentLabels.head), unmatchedParents.head)
      case (1, Some(sigmaAMR), _, _, _) if (unmatchedPolarityChildren nonEmpty) => ReversePolarity
      case (1, Some(sigmaAMR), _, _, _) if (insertableChildren nonEmpty) =>
        val amrRef = insertableChildren.head
        val conceptToInsert = data.amr.get.nodes(amrRef)
        assert(InsertBelow(conceptIndex(conceptToInsert)).isPermissible(state), "Invalid " + conceptToInsert + "(" + conceptIndex(conceptToInsert) + ") in \n" + state)
        InsertBelow(conceptIndex(conceptToInsert), amrRef)
      case (2, Some(sigmaAMR), _, -1, _) if needsToBeWikified => Wikify(getWikiString(data.amr.get, sigmaAMR))
      case (_, Some(sigmaAMR), _, -1, _) if kappa.nonEmpty => Reentrance(kappa(0))
      case (1, Some(sigmaAMR), _, -1, _) =>
        val concept = quote.replaceAllIn(data.amr.get.nodes.getOrElse(sigmaAMR, "UNKNOWN"), "")
        if (concept == "UNKNOWN") {
          println("AMR key not found: " + sigmaAMR + " for " + sigma + " -> " + state.currentGraph.nodes(sigma))
        }
        NextNode(conceptToUse(concept, state))
      case (1, None, _, -1, _) if (DeleteNode.isPermissible(state)) => DeleteNode
      case (1, None, _, beta, Some(betaAMR)) if (ReplaceHead.isPermissible(state)) => ReplaceHead
      case (_, Some(sigmaAMR), _, beta, Some(betaAMR)) if (betaAMRParents contains sigmaAMR) =>
        val relationText = data.amr.get.labelsBetween(sigmaAMR, betaAMR)(0)
        val relationRequired = relationIndex(relationText)
        NextEdge(relationRequired)
      case (_, _, _, beta, Some(betaAMR)) if (allNodesAMR intersect betaAMRParents.toSet nonEmpty) =>
        def edgeIsWrongWayRound: Boolean = SIGMAAMR match {
          case Some(sigmaAMR) => data.amr.get.arcs contains (betaAMR, sigmaAMR)
          case None => false
        }
        if (edgeIsWrongWayRound && Swap.isPermissible(state)) Swap else {
          val parentAMRNodes = allNodesAMR intersect betaAMRParents.toSet
          val parentIndices = parentAMRNodes map fullMapAMRtoDT filter { Reattach(_).isPermissible(state) }
          if (parentIndices.nonEmpty) Reattach(parentIndices.head) else NextEdge(0)
        }
      case (_, _, _, beta, _) if beta > -1 => NextEdge(0)
      case _ if state.phase == 1 => NextNode(0)
      case _ => DoNothing
    }

    if (debug) println("Action chosen: " + chosenAction)
    chosenAction
  }

  def conceptToUse(concept: String, state: WangXueTransitionState): Int = {
    if (!WangXueTransitionSystem.preferKnown) {
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

  def getParentString(data: Sentence, state: WangXueTransitionState, amrMap: Map[String, Int]): String = {
    def getCompositeNode(amrKey: String, acc: String): String = {
      // we find the node, plus all unmapped nodes further up the tree iff there is a single line of ascent
      // in other words, we stop once we hit a node that has other children, or which has more than one parent
      val amr = data.amr.get
      val parents = amr.parentsOf(amrKey)
      val concept = amr.nodes(amrKey)
      val unmappedParents = parents filterNot data.AMRToPosition.contains filterNot amrMap.contains
      val newAcc = if (acc == "") amr.nodes(amrKey) else amr.nodes(amrKey) + ":" + acc
      if (parents.isEmpty || parents.size > 1 || unmappedParents.isEmpty)
        newAcc
      else {
        val relation = amr.arcs((unmappedParents(0), amrKey))
        getCompositeNode(unmappedParents(0), relation + ":" + newAcc)
      }
    }
    /*
         * Possibilities are:
         *  All the nodes in the fragment have the same parent, and this is unmapped
         *      Ideal situation. AddParent with the correct composite node.
         *  All the nodes in the fragment have the same parent, and it is mapped
         *      Ideal if we have just one node - we use NoParent.
         *      Non-ideal for more than one node. Not much we can do. Insert a name node, and hope for the best.
         *  The nodes in the fragment do not have a common parent, but only one of them is unmapped
         *      Non-ideal, but straightforward. Insert a composite node in line with the sole unmapped parent.
         *  The nodes in the fragment do not have a common parent, and more than one is unmapped
         *      Non-ideal. Pick the first unmapped parent, and insert the relevant composite node
         *  The nodes in the fragment do not have a common parent, and all of them are mapped
         *      Non-ideal. Not much we can do. Insert a name node, and hope for the best.
         */
    val fragmentNodes = state.nodesToProcess.head
    val fragmentAMR = data.positionToAMR.getOrElse(fragmentNodes, "U")
    val fragmentParents = data.amr.get.parentsOf(fragmentAMR)
    val unmappedFragmentParents = fragmentParents filterNot amrMap.contains

    if (unmappedFragmentParents.isEmpty) "" else getCompositeNode(unmappedFragmentParents(0), "")
  }

  def getWikiString(amr: AMRGraph, node: String): String = {
    val wikiAttributes = amr.edgesToChildren(node) filter { amr.arcs(_) == "wiki" } map (_._2) map amr.nodes
    val fullWikiString = wikiAttributes.size match {
      case 0 => ""
      case 1 => wikiAttributes(0)
      case _ => assert(false, "Too many wiki attributes on node " + node + "in AMR: \n" + amr); ""
    }
    //   println("Full wikiString = " + fullWikiString)
    val forward = Wikify.forwardConcatenationOfNameArgs(amr, node)
    //   println("ForwardConcat = " +forward)
    //   val backward = Wikify.backwardConcatenationOfNameArgs(amr, node)
    val quoteString = """""""
    fullWikiString.replaceAll(quoteString, "") match {
      case "" => ""
      case `forward` => "FORWARD"
      //    case `backward` => "BACKWARD"
      case _ => "DEFAULT"
    }
  }

}