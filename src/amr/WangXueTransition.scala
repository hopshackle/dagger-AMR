package amr
import dagger.core._

case class WangXueTransitionState(nodesToProcess: List[Int], childrenToProcess: List[Int], currentGraph: DependencyTree,
  previousActions: List[WangXueAction], originalInput: Option[Sentence],
  startingDT: DependencyTree, processedEdges: Set[(Int, Int)], processedNodes: Set[Int],
  phaseTwo: Boolean = false) extends TransitionState {

  def fastForward: WangXueTransitionState = {
    // We skip any Edges or Nodes that have already been processed
    if (!phaseTwo) {
      val sigmaOption = nodesToProcess.headOption
      val betaOption = childrenToProcess.headOption
      val ffState = (sigmaOption, betaOption) match {
        case (Some(sigma), Some(beta)) => if (processedEdges contains (sigma, beta)) NextEdge(0)(this).fastForward else this
        case (Some(sigma), None) => if (processedNodes contains sigma) NextNode(0)(this).fastForward else this
        case (None, _) => this
      }
      if (ffState.nodesToProcess.isEmpty && !phaseTwo) {
        val rootNode = currentGraph.getRoots.head
        ffState.copy(nodesToProcess = currentGraph.getAllChildren(Seq(rootNode)).toList,
            childrenToProcess = List(), processedNodes = Set(), phaseTwo = true)
      } else {
        ffState
      }
    } else {
      this
    }
  }

  // At each state in the search trajectory we have a currentGraph, which is initialised from the DependencyTree obtained
  // from our parser du jour. 
  override def toString: String = {
    "\nNodesToGo:\t" + nodesToProcess.toString +
      "\nChildren:\t" + childrenToProcess.toString +
      "\nPartialGraph:\t" + currentGraph.toString +
      (if (originalInput.isEmpty) "" else
        "\nMappings:\t" + ((originalInput.get.positionToAMR ++ currentGraph.insertedNodes) map (x => x._1 + " -> " + x._2 + "\n")).mkString) +
      (previousActions match {
        case first :: second :: third :: tail => first + "\n" + second + "\n" + third + "\n"
        case first :: second :: tail => first + "\n" + second + "\n"
        case first :: tail => first + "\n"
        case Nil => ""
      })
  }
}
