package amr.classic

import dagger.core._
import amr._

case class ClassicTransitionState(nodesToProcess: List[Int], currentGraph: DependencyTree,
  previousActions: List[ClassicAction], originalInput: Option[Sentence],
  startingDT: DependencyTree, processedEdges: Set[(Int, Int)], processedNodes: Set[Int], 
  fragments: Map[Int, List[Int]], fragmentHeads: Map[Int, String] = Map(), phase: Int = 1) extends TransitionState {
  
    
    override def toString: String = {
      "NodesToProcess: " + nodesToProcess.mkString(", ") + "\n" +
      "Current Graph: " +
      currentGraph.toString + "\n" +
      "Fragments: " + fragments + "\n" +
      "FragmentHeads: " + fragmentHeads + "\n"
    }
}
