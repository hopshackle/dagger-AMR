package amr
import ImportConcepts.{conceptIndex}
/**
 * @author James
 */
class WangXueExpert extends WangXueExpertBasic {

  override def chooseTransition(data: Sentence, state: WangXueTransitionState): WangXueAction = {
    if (debug) println("Considering current node: " + state.nodesToProcess.head +
      " with child " + (if (state.childrenToProcess isEmpty) "Nil" else state.childrenToProcess.head))

    val SIGMA = state.nodesToProcess.head
    val SIGMAPARENT = if (SIGMA == 0) -1 else state.currentGraph.parentsOf(SIGMA)(0)
    // Note - this currently assumes we always retain a Tree structure, with a single parent for each node.
    // This is true given the current action set...but will change in the future.
    val BETA = if (state.childrenToProcess.isEmpty) 0 else state.childrenToProcess.head
    val SIGMAAMR = data.positionToAMR.get(SIGMA)
    val BETAAMR = data.positionToAMR.get(BETA)
    val SIGMAAMRPARENT = SIGMAAMR match {
      case None => List[String]()
      case Some(amr) => data.amr.get.parentsOf(amr)
    }
    val SIGMAAMRPARENTPOS = SIGMAAMRPARENT map { data.AMRToPosition.getOrElse(_, -1) }

    val chosenAction = (SIGMA, SIGMAAMR, SIGMAAMRPARENTPOS.contains(-1), BETA, BETAAMR) match {
      // cases to cover: no beta - Delete, Insert or NextNode
      //    If sigmaAMR and the parent AMR node is unmatched to DT, then we InsertNode (and match it)
      //    If sigmaAMR then we NextNode 
      //    If sigmaAMR is blank, then we DeleteNode
      // beta non-zero: NextEdge, Reattach, InsertNode are our options
      //    If sigmaAMR and the parent AMR node is unmatched to DT, then we InsertNode (and match it)
      //    If betaAMR and its parent AMR node is matched with another DT node that is not sigma, then Reattach (if valid)
      //    If betaAMR and its parent is matched to sigma, then NextEdge
      //    If betaAMR and its parent is unmatched (and sigmaAMR) then...? [Next Edge ... we need a MERGE action for this one]
      //    If betaAMR and its parent is unmatched (and NOT sigmaAMR) then...? [Next Edge...similarly, we need further actions to cope]
      //    If NOT betaAMR...should be impossible, as we assign all lower nodes to AMR nodes as we climb the tree...which might 
      //      happen if Classifier policy has been making some decisions, even if it would never happen with only this expert policy.
      //      If beta has no children in this case, then we Reattach to the next node to be processed, with the intention of Deleting it later (a recovery action)
      //      If beta does have children, then currently irrecoverable. We NextEdge and move on.
      case (sigma, Some(sigmaAMR), true, _, _) => 
        val amrToMatch = SIGMAAMRPARENT(SIGMAAMRPARENTPOS.indexWhere { _ == -1 })
        val amrConcept = data.amr.get.nodes(amrToMatch)
        // Now we need to match up AMR and DT in data
        Insert(conceptIndex(amrConcept))
    }

    if (debug) println("Action chosen: " + chosenAction)
    chosenAction
  }

}