package amr
import ImportConcepts.{ conceptIndex, relationIndex }
/**
 * @author James
 */
class WangXueExpert extends WangXueExpertBasic {

  override def chooseTransition(data: Sentence, state: WangXueTransitionState): WangXueAction = {
    if (debug) println("Considering current node: " + state.nodesToProcess.head +
      " with child " + (if (state.childrenToProcess isEmpty) "Nil" else state.childrenToProcess.head))

    def getAMRParents(nodeAMR: Option[String]): List[String] = nodeAMR match {
      case None => List[String]()
      case Some(amrKey) => data.amr.get.parentsOf(amrKey)
    }

    val fullMapDTtoAMR = state.currentGraph.insertedNodes ++ data.positionToAMR
    val fullMapAMRtoDT = fullMapDTtoAMR map { case (key, value) => (value -> key) }

    val sigma = state.nodesToProcess.head
    val sigmaParents = if (sigma == 0) List() else state.currentGraph.parentsOf(sigma)
    val BETA = if (state.childrenToProcess.isEmpty) -1 else state.childrenToProcess.head
    val SIGMAAMR = fullMapDTtoAMR.get(sigma)
    if (debug) println(s"SIGMAAMR = $SIGMAAMR")

    val BETAAMR = if (BETA == -1) None else fullMapDTtoAMR.get(BETA)

    val sigmaAMRParents = getAMRParents(SIGMAAMR)
    if (debug) println(sigmaAMRParents)
    val betaAMRParents = getAMRParents(BETAAMR)
    val nodesToProcessAMR = state.nodesToProcess map (fullMapDTtoAMR.getOrElse(_, "")) filter (_ != "")

    val unmatchedParents = sigmaAMRParents filter { x => !fullMapAMRtoDT.contains(x) }
    //    if (debug) println(unmatchedParents)
    val unmatchedParentLabels = unmatchedParents map (data.amr.get.nodes(_))
    //    if (debug) println(unmatchedParentLabels)

    val chosenAction = (SIGMAAMR, unmatchedParentLabels.isEmpty, BETA, BETAAMR, betaAMRParents.isEmpty) match {
      // cases to cover: no beta - Delete, Insert or NextNode
      //    If sigmaAMR and the parent AMR node is unmatched to DT, then we InsertNode
      //    If sigmaAMR then we NextNode 
      //    If sigmaAMR is blank, then we DeleteNode
      // beta non-zero: NextEdge, Reattach, InsertNode are our options
      //    If sigmaAMR and the parent AMR node is unmatched to DT, then we InsertNode
      //    If betaAMR and its parent AMR node is matched with another DT node that is not sigma, then Reattach (if valid)
      //    If betaAMR and its parent is matched to sigma, then NextEdge
      //    If betaAMR and its parent is unmatched (and sigmaAMR) then...? [Next Edge ... we need a MERGE action for this one]
      //    If betaAMR and its parent is unmatched (and NOT sigmaAMR) then...? [Next Edge...similarly, we need further actions to cope]
      //    If NOT betaAMR...should be impossible, as we assign all lower nodes to AMR nodes as we climb the tree...which might 
      //      happen if Classifier policy has been making some decisions, even if it would never happen with only this expert policy.
      //      If beta has no children in this case, then we Reattach to the next node to be processed, with the intention of Deleting it later (a recovery action)
      //      If beta does have children, then currently irrecoverable. We NextEdge and move on.
      case (Some(sigmaAMR), false, _, _, _) => Insert(conceptIndex(unmatchedParentLabels.head), unmatchedParents.head)
      case (Some(sigmaAMR), _, -1, _, _) =>
        val concept = data.amr.get.nodes.getOrElse(sigmaAMR, "UNKNOWN")
        if (concept == "UNKNOWN") {
          println("AMR key not found: " + sigmaAMR + " for " + sigma + " -> " + state.currentGraph.nodes(sigma))
          println(state.currentGraph.insertedNodes)
        }
        NextNode(conceptIndex(concept))
      case (None, _, -1, _, _) => if (DeleteNode.isPermissible(state)) DeleteNode else NextNode(0)
      case (Some(sigmaAMR), _, beta, Some(betaAMR), false) if (sigmaAMR != "") =>
        if (betaAMRParents contains sigmaAMR) NextEdge(relationIndex(data.amr.get.labelsBetween(sigmaAMR, betaAMR)(0)))
        else if (nodesToProcessAMR contains betaAMRParents.head) {
          val parentIndex = state.nodesToProcess(nodesToProcessAMR.indexOf(betaAMRParents.head))
          if (Reattach(parentIndex).isPermissible(state)) Reattach(parentIndex) else NextEdge(0)
        } else NextEdge(0)
      case (_, _, beta, None, _) if beta > -1 =>
        if (state.nodesToProcess.size > 2 && state.nodesToProcess.tail.head != beta) Reattach(state.nodesToProcess.tail.head) else NextEdge(0)
      case (_, _, beta, _, _) if beta > -1 => NextEdge(0)
      case (_, _, _, _, _) => NextNode(0)

    }

    if (debug) println("Action chosen: " + chosenAction)
    chosenAction
  }

}