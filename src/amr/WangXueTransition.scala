package amr
import dagger.core._

case class WangXueTransitionState(nodesToProcess: List[Int], childrenToProcess: List[Int], currentGraph: DependencyTree) extends TransitionState {

  // At each state in the search trajectory we have a currentGraph, which is initialised from the DependencyTree obtained
  // from our parser du jour. 
  override def toString: String = {
    "\nNodesToGo:\t" + nodesToProcess.toString +
      "\nChildren:\t" + childrenToProcess.toString +
      "\nPartialGraph:\t" + currentGraph.toString
  }
}