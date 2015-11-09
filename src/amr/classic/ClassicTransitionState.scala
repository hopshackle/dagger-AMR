package amr.classic

import dagger.core._
import amr._

case class ClassicTransitionState(nodesToProcess: List[Int], currentGraph: DependencyTree,
  previousActions: List[ClassicAction], originalInput: Option[Sentence],
  startingDT: DependencyTree, processedEdges: Set[(Int, Int)], processedNodes: Set[Int]) extends TransitionState {

}
