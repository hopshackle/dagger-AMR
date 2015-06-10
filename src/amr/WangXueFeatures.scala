package amr
import scala.collection.Map
import coref.util._

class WangXueFeatures(dict: Index) {

  import scala.collection.JavaConversions.mapAsScalaMap

  def add(map: java.util.HashMap[Int, Double], feat: String, value: Double = 1.0) = {
    map.put(dict.index(feat), value)
  }

  def features(sentence: Sentence, state: WangXueTransitionState): Map[Int, Double] = {
    state.childrenToProcess match {
      case Nil => sigmaFeatures(sentence, state)
      case head :: tail => sigmaBetaFeatures(sentence, state)
    }
  }

  def sigmaFeatures(sentence: Sentence, state: WangXueTransitionState): Map[Int, Double] = {
    val hmap = new java.util.HashMap[Int, Double]

    val sigma = state.nodesToProcess.head
    val sigmaWord = state.currentGraph.nodes(sigma)

    add(hmap, "BIAS")
    add(hmap, "SIGMA-WORD=" + sigmaWord)

    if (sigma > 0) {  // sigma == 0 indicates the dummy root node (which has no parent)
      val (_, sigmaParent, parentLabel) = (state.currentGraph.arcs filter { case (p, c, l) => (c == sigma) })(0)
      add(hmap, "PARENT-SIGMA-DEP-LABEL=" + parentLabel)
    }
    hmap
  }

  def sigmaBetaFeatures(sentence: Sentence, state: WangXueTransitionState): Map[Int, Double] = {
    val hmap = new java.util.HashMap[Int, Double]

    val sigma = state.nodesToProcess.head
    val beta = state.childrenToProcess.head
    val dependencyLabel = state.currentGraph.arcs.filter { case (p, c, l) => (p == sigma && c == beta) }(0)._3
    val sigmaWord = state.currentGraph.nodes(sigma)
    val betaWord = state.currentGraph.nodes(beta)
    val distance = Math.abs(state.currentGraph.nodeSpans(sigma)._1 - state.currentGraph.nodeSpans(beta)._1)

    add(hmap, "BETA-WORD=" + sigmaWord)
    add(hmap, "SIGMA-BETA-WORDS=" + sigmaWord + "-" + betaWord)
    add(hmap, "SIGMA-BETA-DISTANCE=" + distance)
    add(hmap, "SIGMA-BETA-DEP-LABEL=" + dependencyLabel)

    sigmaFeatures(sentence, state) ++ hmap
  }

}