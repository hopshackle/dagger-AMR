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
    val fullMapDTtoAMR = (state.currentGraph.insertedNodes filter { case (i, ref) => ref != "" }) ++ data.positionToAMR
    val fullMapAMRtoDT = fullMapDTtoAMR map { case (key, value) => (value -> key) }

    val sigma = state.nodesToProcess.head
    val BETA = if (state.childrenToProcess.isEmpty) -1 else state.childrenToProcess.head
    val SIGMAAMR = fullMapDTtoAMR.get(sigma)
    if (debug) println(s"SIGMAAMR = $SIGMAAMR")

    val BETAAMR = if (BETA == -1) None else fullMapDTtoAMR.get(BETA)

    val sigmaAMRParents = getAMRParents(SIGMAAMR)
    val sigmaAMRChildren = getAMRChildren(SIGMAAMR).toSet

    val sigmaChildren = state.currentGraph.childrenOf(sigma)
    val sigmaChildrenAMR = sigmaChildren map (fullMapDTtoAMR.getOrElse(_, "")) filter (_ != "") toSet

    val unlinkedAMRChildren = sigmaAMRChildren filter (fullMapAMRtoDT contains _) diff sigmaChildrenAMR

    if (debug) println(sigmaAMRParents)
    val betaAMRParents = getAMRParents(BETAAMR)
    val nodesToProcessAMR = state.nodesToProcess map (fullMapDTtoAMR.getOrElse(_, "")) filter (_ != "")
    val allNodesAMR = fullMapDTtoAMR.values.toSet

    val unmatchedParents = sigmaAMRParents filter { x => !fullMapAMRtoDT.contains(x) }
    val unmatchedChildren = sigmaAMRChildren filter { x => !fullMapAMRtoDT.contains(x) }
    //    if (debug) println(unmatchedParents)
    val unmatchedParentLabels = unmatchedParents map (data.amr.get.nodes(_))
    //    if (debug) println(unmatchedParentLabels)
    val unmatchedChildrenLabels = unmatchedChildren map (data.amr.get.nodes(_))
    val unmatchedPolarityChild = unmatchedChildrenLabels contains "-"

    val chosenAction = (SIGMAAMR, unmatchedParentLabels.isEmpty, BETA, BETAAMR, betaAMRParents.isEmpty, state.phaseTwo) match {
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
      case (Some(sigmaAMR), false, _, _, _, false) if (Insert.isPermissible(state)) => Insert(conceptIndex(unmatchedParentLabels.head), unmatchedParents.head)
      case (Some(sigmaAMR), _, _, _, _, false) if (unmatchedPolarityChild) => ReversePolarity
      case (Some(sigmaAMR), _, -1, _, _, false) =>
        val concept = quote.replaceAllIn(data.amr.get.nodes.getOrElse(sigmaAMR, "UNKNOWN"), "")
        if (concept == "UNKNOWN") {
          println("AMR key not found: " + sigmaAMR + " for " + sigma + " -> " + state.currentGraph.nodes(sigma))
          println(state.currentGraph.insertedNodes)
        }
        //      val conceptToUse = if (state.currentGraph.nodes(state.nodesToProcess.head) == concept) 0 else conceptIndex(concept)
        NextNode(conceptIndex(concept))
      case (None, _, -1, _, _, false) if (DeleteNode.isPermissible(state)) => DeleteNode
      case (None, _, beta, Some(betaAMR), _, false) if (ReplaceHead.isPermissible(state)) => ReplaceHead
      case (Some(sigmaAMR), _, beta, Some(betaAMR), false, _) if (sigmaAMR != "") =>
        if (betaAMRParents contains sigmaAMR) {
          val relationText = data.amr.get.labelsBetween(sigmaAMR, betaAMR)(0)
          val relationRequired = relationIndex(relationText)
          //        val relationToUse = if (state.currentGraph.arcs((sigma, beta)) == relationText) 0 else relationRequired
          NextEdge(relationRequired)
        } else if ((sigmaAMRParents contains betaAMR) && Swap.isPermissible(state)) Swap //  || (sigmaParentsAMR contains betaAMR) for consideration
        else if (allNodesAMR contains betaAMRParents.head) {
          val parentIndex = fullMapAMRtoDT(betaAMRParents.head)
          if (Reattach(parentIndex).isPermissible(state)) Reattach(parentIndex) else NextEdge(0)
        } else NextEdge(0)
      //     case (_, _, beta, None, _) if beta > -1 =>
      //       if (state.nodesToProcess.size > 2 && state.nodesToProcess.tail.head != beta &&
      //         Reattach(state.nodesToProcess.tail.head).isPermissible(state))
      //         Reattach(state.nodesToProcess.tail.head) else NextEdge(0)
      case (_, _, beta, _, _, _) if beta > -1 => NextEdge(0)
      case (_, _, _, _, _, false) => NextNode(0)
      case (_, _, _, _, _, true) if unlinkedAMRChildren.nonEmpty =>
        val kappa = unlinkedAMRChildren.toList map fullMapAMRtoDT filter state.currentGraph.nodes.contains filter (Reentrance(_).isPermissible(state))
        if (kappa.nonEmpty) Reentrance(kappa(0)) else DoNothing
      case (_, _, _, _, _, true) => DoNothing

    }

    if (debug) println("Action chosen: " + chosenAction)
    chosenAction
  }

}