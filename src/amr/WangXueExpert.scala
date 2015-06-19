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

    // Perhaps I could insert something here...on the basis that some actions *might* have been taken by
    // a non-expert since we last did anything. And in this case we need to inspect the Inserted Nodes, and see
    // if they should be mapped to an AMR node in any obvious way.
    // We can get a list of these nodes by looking for all insertedNodes with otherRef = ""
    // We then iterate through them to get children
    // we're looking for children that map to AMR nodes which have unmapped AMR node parents
    // with the same concept as the new inserted node. We assign greedily if we find a match on this basis.
    val initialMapDTtoAMR = state.currentGraph.insertedNodes ++ data.positionToAMR
    val initialMapAMRtoDT = initialMapDTtoAMR map { case (key, value) => (value -> key) }
    val nodesInsertedWithoutRefs = state.currentGraph.insertedNodes filter { case (_, amrRef) => amrRef == "" } map { case (dtRef, _) => dtRef }
    val childrenOfEachUnreferencedNode = nodesInsertedWithoutRefs map state.currentGraph.childrenOf
    val childrenByAMRRef = childrenOfEachUnreferencedNode map { for { childRef <- _ } yield initialMapDTtoAMR.get(childRef) }
    val unmatchedParentsOfChildren = childrenByAMRRef map { x =>
      x map { amrRef => getAMRParents(amrRef) filter (z => !(initialMapAMRtoDT contains z)) } flatten
    }
    // We now have a List of the unmatched AMR Refs of parents for each child that IS matched
    // Run through it until we find one that matches the concept of the current unmatched inserted node
    // Greedily make this assignation of inserted node -> unmatched parental AMR Ref
    val mappingsToAdd = (for {
      (nodeToAssign, parents) <- nodesInsertedWithoutRefs zip unmatchedParentsOfChildren
      val firstMatch = parents.find { x => data.amr.get.nodes(x) == state.currentGraph.nodes(nodeToAssign) }
      if !firstMatch.isEmpty
    } yield (nodeToAssign, firstMatch.get)) 

    val fullMapDTtoAMR = initialMapDTtoAMR ++ mappingsToAdd
    // Hmm...there is no way from here to update the state...
    // that would have to be done in WXActions...but that does not have access to the AMR in Sentence!
    // State *is* the best place to put all of this information...which suggests I need to put the AMR 
    // into State as well as Sentence purely for the convenience of the Expert policy in training.
    
    val fullMapAMRtoDT = initialMapDTtoAMR ++ mappingsToAdd map { case (key, value) => (value -> key) }

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
      case (Some(sigmaAMR), _, -1, _, _) => NextNode(conceptIndex(data.amr.get.nodes(sigmaAMR)))
      case (None, _, -1, _, _) => if (DeleteNode.isPermissible(state)) DeleteNode else NextNode(0)
      case (Some(sigmaAMR), _, beta, Some(betaAMR), false) =>
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