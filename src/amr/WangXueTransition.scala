package amr
import dagger.core._
import ImportConcepts.relationIndex

case class WangXueTransitionState(nodesToProcess: List[Int], childrenToProcess: List[Int], currentGraph: DependencyTree,
  previousActions: List[WangXueAction], originalInput: Option[Sentence],
  startingDT: DependencyTree, processedEdges: Set[(Int, Int)], processedNodes: Set[Int], phase: Int = 1) extends TransitionState {

  def fastForward: WangXueTransitionState = {
    // We skip any Edges or Nodes that have already been processed
    import Wikify._
    val sigmaOption = nodesToProcess.headOption
    val betaOption = childrenToProcess.headOption
    val ffState = (sigmaOption, betaOption, phase) match {
      case (Some(sigma), Some(beta), _) => if (processedEdges contains (sigma, beta)) NextEdge(relationIndex(currentGraph.arcs((sigma, beta))))(this).fastForward else this
      case (Some(sigma), None, 1) => if (processedNodes contains sigma) NextNode(0)(this).fastForward else this
      case (Some(sigma), None, 2) if WangXueTransitionSystem.wikification && hasExactMatch(this) && !isWikified(this) => Wikify("DEFAULT")(this).fastForward
      case (_, _, _) => this
    }
    ffState
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
