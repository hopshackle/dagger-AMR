package amr.classic

import dagger.core._
import amr._

case class ClassicTransitionState(nodesToProcess: List[Int], currentGraph: DependencyTree,
  previousActions: List[ClassicAction], originalInput: Option[Sentence],
  startingDT: DependencyTree, processedEdges: Set[(Int, Int)], processedNodes: Set[Int], 
  fragments: Map[Int, List[Int]], fragmentHeads: Map[Int, String] = Map(), phase: Int = 1) extends TransitionState {

    def nodesInTokenOrder: List[Int] = {
    val spans = currentGraph.nodeSpans
    currentGraph.nodes.keys.toList.sortWith {
      case (a1, a2) =>
        spans.getOrElse(a1, (0, 0))._1 > spans.getOrElse(a2, (0, 0))._1
    }
  }
}
