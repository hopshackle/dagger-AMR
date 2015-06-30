package amr
import scala.collection.Map
import ImportConcepts.{ concept }
import java.io._
import scala.util.Random
import dagger.core._

class WangXueFeatures(options: DAGGEROptions, dict: Index = new MapIndex) {

  import scala.collection.JavaConversions.mapAsScalaMap

  val debug = true
  val random = new Random()

  def add(map: java.util.HashMap[Int, Double], feat: String, value: Double = 1.0) = {
    map.put(dict.index(feat), value)
  }

  def features(sentence: Sentence, state: WangXueTransitionState, action: WangXueAction): Map[Int, Double] = {
    val thisDebug = if (debug && random.nextDouble < 0.01) true else false

    val output = state.childrenToProcess match {
      case Nil => sigmaFeatures(sentence, state, action)
      case head :: tail => sigmaBetaFeatures(sentence, state, action)
    }

    if (thisDebug) {
      val featuresDebug = new FileWriter(options.DAGGER_OUTPUT_PATH + "_WXfeatures_debug.txt", true)
      featuresDebug.write(state.toString)
      output foreach (_ match { case (index, value) => featuresDebug.write(f"$index : ${dict.elem(index)} = $value%.2f\n") })
      featuresDebug.close
    }
    output
  }

  def sigmaFeatures(sentence: Sentence, state: WangXueTransitionState, action: WangXueAction): Map[Int, Double] = {
    val hmap = new java.util.HashMap[Int, Double]
    val numeric = "[0-9,.]".r
    val sigma = state.nodesToProcess.head
    val sigmaWord = state.currentGraph.nodes(sigma)
    
    val wordTokens: Double = sentence.dependencyTree.nodes.size
    val insertedNodes: Double = state.currentGraph.insertedNodes.size
    add(hmap, "RATIO-INSERT-WORDS", insertedNodes / wordTokens)
    val insertedConcepts = state.currentGraph.insertedNodes.keys map (node => state.currentGraph.nodes.getOrElse(node, "DELETED"))
    val conceptSet = insertedConcepts.toSet
    conceptSet foreach (c => add(hmap, "INSERT-COUNT-"+ c, insertedConcepts.count { x => x == c }))

    add(hmap, "ACTION-TYPE=" + action.name)
    add(hmap, "SIGMA-WORD=" + sigmaWord)
    if (state.currentGraph.nodePOS contains sigma) add(hmap, "SIGMA-POS=" + state.currentGraph.nodePOS(sigma))
    if (state.currentGraph.nodeLemmas contains sigma) add(hmap, "SIGMA-LEMMA=" + state.currentGraph.nodeLemmas(sigma))
    if (state.currentGraph.nodeNER contains sigma) add(hmap, "SIGMA-NER=" + state.currentGraph.nodeNER(sigma))

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
          val parentWord = state.currentGraph.nodes(parent)
          if (state.currentGraph.insertedNodes contains parent) {
            add(hmap, "PARENT-INSERTED=" + parentWord)
            if (state.currentGraph.insertedNodes contains parent) add(hmap, "PARENT-SIGMA-BOTH-INSERTED")
          }
          add(hmap, "PARENT-SIGMA-DEP-LABEL=" + label)
          add(hmap, "PARENT-WORD=" + parentWord)
          add(hmap, "PARENT-SIGMA-WORDS=" + parentWord + "-" + sigmaWord)
      }

    }
    hmap
  }

  def sigmaBetaFeatures(sentence: Sentence, state: WangXueTransitionState, action: WangXueAction): Map[Int, Double] = {
    val hmap = new java.util.HashMap[Int, Double]

    val sigma = state.nodesToProcess.head
    val beta = state.childrenToProcess.head
    val dependencyLabel = state.currentGraph.arcs((sigma, beta))
    val sigmaWord = state.currentGraph.nodes(sigma)
    val betaWord = state.currentGraph.nodes(beta)
    val (sigmaPosition, _) = state.currentGraph.nodeSpans.getOrElse(sigma, (0, 0))
    val (betaPosition, _) = state.currentGraph.nodeSpans.getOrElse(beta, (0, 0))
    val distance = if (sigmaPosition > 0 && betaPosition > 0) Math.abs(sigmaPosition - betaPosition) else 0
    val betaPOS = state.currentGraph.nodePOS.getOrElse(beta, "")
    val betaLemma = state.currentGraph.nodeLemmas.getOrElse(beta, "")
    val betaNER = state.currentGraph.nodeNER.getOrElse(beta, "")
    val sigmaPOS = state.currentGraph.nodePOS.getOrElse(sigma, "")
    val sigmaLemma = state.currentGraph.nodeLemmas.getOrElse(sigma, "")
    val sigmaNER = state.currentGraph.nodeNER.getOrElse(sigma, "")
    val sigmaInserted = state.currentGraph.insertedNodes contains sigma
    val betaInserted = state.currentGraph.insertedNodes contains beta

    add(hmap, "BIAS-BETA")
    if (sigmaInserted) add(hmap, "SIGMA-INSERTED")
    if (betaInserted) add(hmap, "BETA-INSERTED")
    if (sigmaInserted && betaInserted) add(hmap, "SIGMA-BETA-INSERTED")
    add(hmap, "BETA-WORD=" + betaWord)
    add(hmap, "SIGMA-BETA-WORDS=" + sigmaWord + "-" + betaWord)
    if (sigmaPOS != "" && betaPOS != "") add(hmap, "SIGMA-BETA-POS=" + sigmaPOS + "-" + betaPOS)
    if (sigmaNER != "" && betaNER != "") add(hmap, "SIGMA-BETA-NER=" + sigmaNER + "-" + betaNER)
    add(hmap, "SIGMA-BETA-DISTANCE", +distance)
    if (sigmaPosition == 0 || betaPosition == 0) add(hmap, "SIGMA-BETA-DISTANCE-UNKNOWN")
    add(hmap, "SIGMA-BETA-DEP-LABEL=" + dependencyLabel)
    if (betaPOS != "") add(hmap, "BETA-POS=" + betaPOS)
    if (betaLemma != "") add(hmap, "BETA-LEMMA=" + betaLemma)
    if (betaNER != "") add(hmap, "BETA-NER=" + betaNER)

    if (state.currentGraph.swappedArcs contains ((beta, sigma))) add(hmap, "SWAPPED-ARC")

    val mergedNodes = state.currentGraph.mergedNodes.get(beta) match {
      case None => Nil
      case Some(mergedNodes) => mergedNodes
    }
    mergedNodes foreach { case (n, label) => add(hmap, "BETA-REPH=" + label) }

    if (action.isInstanceOf[hasNodeAsParameter])
      kFeatures(sentence, state, action, action.asInstanceOf[hasNodeAsParameter].parameterNode) ++ sigmaFeatures(sentence, state, action) ++ hmap
    else
      sigmaFeatures(sentence, state, action) ++ hmap
  }

  def kFeatures(sentence: Sentence, state: WangXueTransitionState, action: WangXueAction, parameterNode: Int): Map[Int, Double] = {
    val hmap = new java.util.HashMap[Int, Double]

    val beta = state.childrenToProcess.head
    val betaWord = state.currentGraph.nodes(beta)
    val kappaWord = state.currentGraph.nodes(parameterNode)
    val (betaPosition, _) = state.currentGraph.nodeSpans.getOrElse(beta, (0, 0))
    val (kappaPosition, _) = state.currentGraph.nodeSpans.getOrElse(parameterNode, (0, 0))
    val distance = if (kappaPosition > 0 && betaPosition > 0) Math.abs(kappaPosition - betaPosition) else 0
    val betaPOS = state.currentGraph.nodePOS.getOrElse(beta, "")
    val betaLemma = state.currentGraph.nodeLemmas.getOrElse(beta, "")
    val betaNER = state.currentGraph.nodeNER.getOrElse(beta, "")
    val kappaPOS = state.currentGraph.nodePOS.getOrElse(parameterNode, "")
    val kappaLemma = state.currentGraph.nodeLemmas.getOrElse(parameterNode, "")
    val kappaNER = state.currentGraph.nodeNER.getOrElse(parameterNode, "")
    val kappaInserted = state.currentGraph.insertedNodes contains parameterNode
    val betaInserted = state.currentGraph.insertedNodes contains beta

    if (kappaInserted) add(hmap, "KAPPA-INSERTED")
    if (kappaInserted && betaInserted) add(hmap, "KAPPA-BETA-INSERTED")
    add(hmap, "KAPPA-BETA-WORDS=" + kappaWord + "-" + betaWord)
    if (kappaPOS != "" && betaPOS != "") add(hmap, "KAPPA-BETA-POS=" + kappaPOS + "-" + betaPOS)
    if (kappaNER != "" && betaNER != "") add(hmap, "KAPPA-BETA-NER=" + kappaNER + "-" + betaNER)
    add(hmap, "KAPPA-BETA-DISTANCE", +distance)
    if (kappaPosition == 0 || betaPosition == 0) add(hmap, "KAPPA-BETA-DISTANCE-UNKNOWN")

    hmap
  }

}
