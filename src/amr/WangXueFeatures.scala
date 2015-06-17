package amr
import scala.collection.Map
import coref.util._

class WangXueFeatures(dict: Index = new MapIndex) {

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
    val numeric = "[0-9,.]".r
    val sigma = state.nodesToProcess.head
    val sigmaWord = state.currentGraph.nodes(sigma)

    add(hmap, "BIAS")
    add(hmap, "SIGMA-WORD=" + sigmaWord)

    if (sigma > 0) { // sigma == 0 indicates the dummy root node (which has no parent)
      val allParents = (state.currentGraph.arcs filter { case ((p, c), l) => (c == sigma) } map { case ((p, c), _) => p }).toList
      val ((_, sigmaParent), parentLabel) = ((state.currentGraph.arcs filter { case ((p, c), l) => (c == sigma) }).toList)(0)
      add(hmap, "PARENT-SIGMA-NO", allParents.size)
      add(hmap, "PARENT-SIGMA-DEP-LABEL=" + parentLabel)
      add(hmap, "PARENT-WORD=" + state.currentGraph.nodes(sigmaParent))
      if (numeric.replaceAllIn(sigmaWord, "") == "") add(hmap, "SIGMA-NUMERIC")
      //     add(hmap, "PARENT-SIGMA-WORDS=" + state.currentGraph.nodes(sigmaParent) + "-" + sigmaWord)
    }
    hmap
  }

  def sigmaBetaFeatures(sentence: Sentence, state: WangXueTransitionState): Map[Int, Double] = {
    val hmap = new java.util.HashMap[Int, Double]

    val sigma = state.nodesToProcess.head
    val beta = state.childrenToProcess.head
    val dependencyLabel = state.currentGraph.arcs((sigma, beta))
    val sigmaWord = state.currentGraph.nodes(sigma)
    val betaWord = state.currentGraph.nodes(beta)
    val (sigmaPosition, _) = state.currentGraph.nodeSpans.getOrElse(sigma, (0, 0))
    val (betaPosition, _) = state.currentGraph.nodeSpans.getOrElse(beta, (0, 0))
    val distance = if (sigmaPosition > 0 && betaPosition > 0) Math.abs(sigmaPosition - betaPosition) else 0

    add(hmap, "BETA-WORD=" + sigmaWord)
    add(hmap, "SIGMA-BETA-WORDS=" + sigmaWord + "-" + betaWord)
    add(hmap, "SIGMA-BETA-DISTANCE", + distance)
    if (distance == 0) add(hmap, "SIGMA-BETA-DISTANCE-UNKNOWN")
    add(hmap, "SIGMA-BETA-DEP-LABEL=" + dependencyLabel)

    sigmaFeatures(sentence, state) ++ hmap
  }

}