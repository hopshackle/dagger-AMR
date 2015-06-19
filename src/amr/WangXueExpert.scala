package amr
import ImportConcepts.{ conceptIndex, relationIndex }
/**
 * @author James
 */
class WangXueExpert extends WangXueExpertBasic {

  override def chooseTransition(data: Sentence, state: WangXueTransitionState): WangXueAction = {
    if (debug) println("Considering current node: " + state.nodesToProcess.head +
      " with child " + (if (state.childrenToProcess isEmpty) "Nil" else state.childrenToProcess.head))

    def findNodeAMRMatch(node: Int): Option[String] = {
      data.positionToAMR.get(node) match {
        case None => // now look for a match on an inserted node
          println("No matching AMR")
          val label = state.currentGraph.nodes(node)
          if (data.amr.get.nodes.values.toList contains label) {
            val amrNode = (data.amr.get.nodes filter { case (i, s) => s == label } map { case (i, s) => i }).toList(0)
            if (data.amr.get.nodeSpans.getOrElse(amrNode, (0, 0)) == (0, 0)) Some(amrNode) else None
          } else None
        case Some(index) => 
          println("Matched AMR")
          Some(index) // if a match on initial process, we just return that
      }
    }

    val sigma = state.nodesToProcess.head
    val SIGMAPARENTS = if (sigma == 0) List() else state.currentGraph.parentsOf(sigma)
    val parentLabels = SIGMAPARENTS map (state.currentGraph.nodes(_))
    val BETA = if (state.childrenToProcess.isEmpty) 0 else state.childrenToProcess.head
    val SIGMAAMR = findNodeAMRMatch(sigma)
    val BETAAMR = findNodeAMRMatch(BETA)
    val SIGMAAMRPARENT = SIGMAAMR match {
      case None => List[String]()
      case Some(amr) => data.amr.get.parentsOf(amr)
    }
    println(SIGMAAMRPARENT)
    val betaAMRParents = BETAAMR match {
      case None => List[String]()
      case Some(amr) => data.amr.get.parentsOf(amr)
    }
    val nodesToProcessAMR = state.nodesToProcess map (data.positionToAMR.getOrElse(_, "")) filter (_ != "")
    val unmatchedParents = SIGMAAMRPARENT filter { x => !data.AMRToPosition.contains(x) } // those with initial AMR to DT mapping
    println(unmatchedParents)
    val unmatchedParentLabels = unmatchedParents map (data.amr.get.nodes(_)) diff parentLabels // and remove those for which we've already Inserted a node
    // TODO: This currently won't insert two levels of missing parent nodes - just the ones above DT nodes originally mapped to AMR
    println(unmatchedParentLabels)

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
      case (Some(sigmaAMR), false, _, _, _) => Insert(conceptIndex(unmatchedParentLabels.head))
      case (Some(sigmaAMR), _, 0, _, _) => NextNode(conceptIndex(data.amr.get.nodes(sigmaAMR)))
      case (None, _, 0, _, _) => if (DeleteNode.isPermissible(state)) DeleteNode else NextNode(0)
      case (Some(sigmaAMR), _, beta, Some(betaAMR), false) =>
        if (betaAMRParents contains sigmaAMR) NextEdge(relationIndex(data.amr.get.labelsBetween(sigmaAMR, betaAMR)(0)))
        else if (nodesToProcessAMR contains betaAMRParents.head) {
          val parentIndex = state.nodesToProcess(nodesToProcessAMR.indexOf(betaAMRParents.head))
          Reattach(parentIndex)
        } else NextEdge(0)
      case (_, _, beta, None, _) if beta > 0 =>
        if (state.nodesToProcess.size > 2 && state.nodesToProcess.tail.head != beta) Reattach(state.nodesToProcess.tail.head) else NextEdge(0)
      case (_, _, beta, _, _) if beta > 0 => NextEdge(0)
      case (_, _, _, _, _) => NextNode(0)

    }

    if (debug) println("Action chosen: " + chosenAction)
    chosenAction
  }

}