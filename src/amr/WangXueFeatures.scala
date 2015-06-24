package amr
import scala.collection.Map
import coref.util._
import ImportConcepts.{ concept }

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

    add(hmap, "SIGMA-WORD=" + sigmaWord)
    add(hmap, "SIGMA-POS=" + state.currentGraph.nodePOS.getOrElse(sigma, "NONE"))
    add(hmap, "SIGMA-LEMMA=" + state.currentGraph.nodeLemmas.getOrElse(sigma, "NONE"))
    add(hmap, "SIGMA-NER=" + state.currentGraph.nodeNER.getOrElse(sigma, "NONE"))

    val sigmaParents = state.currentGraph.parentsOf(sigma)
    if (sigmaParents.nonEmpty) {
      val parentLabelCombos = for {
        parent <- sigmaParents
        label <- state.currentGraph.labelsBetween(parent, sigma)
      } yield (parent, label)
      add(hmap, "PARENT-SIGMA-NO", sigmaParents.size)
      if (numeric.replaceAllIn(sigmaWord, "") == "") add(hmap, "SIGMA-NUMERIC")
      parentLabelCombos foreach {
        case (parent, label) =>
          val parentWord = concept(parent)
          add(hmap, "PARENT-SIGMA-DEP-LABEL=" + label)
          add(hmap, "PARENT-WORD=" + state.currentGraph.nodes(parent))
          add(hmap, "PARENT-SIGMA-WORDS=" + parentWord + "-" + sigmaWord)
      }

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
    val betaPOS = state.currentGraph.nodePOS.getOrElse(beta, "NONE")
    val betaLemma = state.currentGraph.nodeLemmas.getOrElse(beta, "NONE")
    val betaNER = state.currentGraph.nodeNER.getOrElse(beta, "NONE")
    val sigmaPOS = state.currentGraph.nodePOS.getOrElse(sigma, "NONE")
    val sigmaLemma = state.currentGraph.nodeLemmas.getOrElse(sigma, "NONE")
    val sigmaNER = state.currentGraph.nodeNER.getOrElse(sigma, "NONE")
    val sigmaInserted = state.currentGraph.insertedNodes contains sigma
    val betaInserted = state.currentGraph.insertedNodes contains beta

    add(hmap, "BIAS-BETA")
    if (sigmaInserted) add(hmap, "SIGMA-INSERTED")
    if (betaInserted) add(hmap, "BETA-INSERTED")
    if (sigmaInserted && betaInserted) add(hmap, "SIGMA-BETA-INSERTED")
    add(hmap, "BETA-WORD=" + sigmaWord)
    add(hmap, "SIGMA-BETA-WORDS=" + sigmaWord + "-" + betaWord)
    add(hmap, "SIGMA-BETA-POS=" + sigmaPOS + "-" + betaPOS)
    add(hmap, "SIGMA-BETA-NER=" + sigmaNER + "-" + betaNER)
    add(hmap, "SIGMA-BETA-DISTANCE", +distance)
    if (distance == 0) add(hmap, "SIGMA-BETA-DISTANCE-UNKNOWN")
    add(hmap, "SIGMA-BETA-DEP-LABEL=" + dependencyLabel)
    add(hmap, "BETA-POS=" + betaPOS)
    add(hmap, "BETA-LEMMA=" + betaLemma)
    add(hmap, "BETA-NER=" + betaNER)
    sigmaFeatures(sentence, state) ++ hmap
  }

}
