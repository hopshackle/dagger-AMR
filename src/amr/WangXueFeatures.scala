package amr
import scala.collection.Map
import ImportConcepts.{ concept }
import java.io._
import scala.util.Random
import dagger.core._

class WangXueFeatures(options: DAGGEROptions, dict: Index = new MapIndex) {

  import scala.collection.JavaConversions.mapAsScalaMap

  val debug = false
  val random = new Random()
  val numeric = "[0-9,.]".r

  def add(map: java.util.HashMap[Int, Double], feat: String, value: Double = 1.0) = {
    map.put(dict.index(feat), value)
  }

  def features(sentence: Sentence, state: WangXueTransitionState, action: WangXueAction): Map[Int, Double] = {
    val thisDebug = if (debug && random.nextDouble < 0.01) true else false

    val quadraticTurbo = options.contains("--quadratic")

    val linearOutput = state.childrenToProcess match {
      case Nil => sigmaFeatures(sentence, state, action)
      case head :: tail => sigmaBetaFeatures(sentence, state, action)
    }
    val output = linearOutput ++ (if (quadraticTurbo) quadraticCombination(linearOutput) else Map())
    if (thisDebug) {
      val featuresDebug = new FileWriter(options.DAGGER_OUTPUT_PATH + "WXfeatures_debug.txt", true)
      featuresDebug.write(state.toString)
      output foreach (_ match { case (index, value) => featuresDebug.write(f"$index : ${dict.elem(index)} = $value%.2f\n") })
      featuresDebug.close
    }
    output
  }

  def sigmaFeatures(sentence: Sentence, state: WangXueTransitionState, action: WangXueAction): Map[Int, Double] = {
    val quadraticTurbo = options.contains("--quadratic")
    val hmap = new java.util.HashMap[Int, Double]
    val sigma = state.nodesToProcess.head
    val sigmaWord = state.currentGraph.nodes.getOrElse(sigma, "!!??")
    assert(sigmaWord != "!!??", "Sigma not found: " + state)
    val sigmaInserted = state.currentGraph.insertedNodes contains sigma
    val wordTokens: Double = sentence.dependencyTree.nodes.size
    val insertedNodes: Double = state.currentGraph.insertedNodes.size
    if (insertedNodes > 0) add(hmap, "RATIO-INSERT-WORDS", insertedNodes / wordTokens)
    val insertedConcepts = state.currentGraph.insertedNodes.keys map (node => state.currentGraph.nodes.getOrElse(node, "DELETED"))
    val conceptSet = insertedConcepts
    conceptSet foreach (c => add(hmap, "INSERT-COUNT-" + c, insertedConcepts.count { x => x == c }))

    add(hmap, "BIAS")
    if (state.previousActions.size > 0) add(hmap, "LAST-ACTION=" + state.previousActions.head.name)
    if (state.previousActions.size > 1) add(hmap, "LAST-B1-ACTION=" + state.previousActions.tail.head.name)
    if (state.previousActions.size > 2) add(hmap, "LAST-B2-ACTION=" + state.previousActions.tail.tail.head.name)
    if (!quadraticTurbo) {
      if (state.previousActions.size > 1) add(hmap, "LAST-TWO-ACTIONS=" + state.previousActions.head.name + state.previousActions.tail.head.name)
      if (state.previousActions.size > 2) add(hmap, "LAST-THREE-ACTIONS=" + state.previousActions.head.name + state.previousActions.tail.head.name + state.previousActions.tail.tail.head.name)
    }
    add(hmap, "SIGMA-WORD=" + sigmaWord)
    val sigmaLemma = state.currentGraph.nodeLemmas.getOrElse(sigma, "")
    val sigmaPOS = state.currentGraph.nodePOS.getOrElse(sigma, "")
    val sigmaNER = state.currentGraph.nodeNER.getOrElse(sigma, "")
    if (sigmaPOS != "") add(hmap, "SIGMA-POS=" + sigmaPOS)
    if (sigmaLemma != "") add(hmap, "SIGMA-LEMMA=" + sigmaLemma)
    if (sigmaNER != "") add(hmap, "SIGMA-NER=" + sigmaNER)
    if (numeric.replaceAllIn(sigmaWord, "") == "") add(hmap, "SIGMA-NUMERIC")
    if (sigmaInserted) add(hmap, "SIGMA-INSERTED")
    val sigmaDL = state.startingDT.edgesToParents(sigma) map state.startingDT.arcs
    for (sdl <- sigmaDL) add(hmap, "SIGMA-DL=" + sdl)
    val sigmaParents = state.currentGraph.parentsOf(sigma)
    if (sigmaParents.nonEmpty) {
      val parentLabelCombos = for {
        parent <- sigmaParents
        label <- state.currentGraph.labelsBetween(parent, sigma)
      } yield (parent, label)
      add(hmap, "PARENT-SIGMA-NO", sigmaParents.size)

      parentLabelCombos foreach {
        case (parent, label) =>
          val parentWord = state.currentGraph.nodes(parent)
          val parentLemma = state.currentGraph.nodeLemmas.getOrElse(parent, "")
          val parentNER = state.currentGraph.nodeNER.getOrElse(parent, "")
          val parentPOS = state.currentGraph.nodePOS.getOrElse(parent, "")
          val parentDL = state.startingDT.edgesToParents(parent) map state.startingDT.arcs
          if (state.currentGraph.insertedNodes contains parent) {
            add(hmap, "PARENT-INSERTED=" + parentWord)
            if (state.currentGraph.insertedNodes contains parent) add(hmap, "PARENT-SIGMA-BOTH-INSERTED")
          }
          add(hmap, "PARENT-SIGMA-LABEL=" + label)
          add(hmap, "PARENT-WORD=" + parentWord)
          add(hmap, "PARENT-LEMMA=" + parentLemma)
          add(hmap, "PARENT-POS=" + parentPOS)
          add(hmap, "PARENT-NER=" + parentNER)
          for (pdl <- parentDL) add(hmap, "PARENT-DL=" + pdl)
          if (numeric.replaceAllIn(parentWord, "") == "") add(hmap, "PARENT-NUMERIC")
          if (!quadraticTurbo) {
            add(hmap, "PARENT-SIGMA-WORDS=" + parentWord + "-" + sigmaWord)
            if (parentLemma != "" && sigmaPOS != "") add(hmap, "PARENT-LEMMA-SIGMA-POS=" + parentLemma + "-" + sigmaPOS)
            if (parentPOS != "" && sigmaLemma != "") add(hmap, "PARENT-POS-SIGMA-LEMMA=" + parentPOS + "-" + sigmaLemma)
            if (parentDL.nonEmpty && sigmaLemma != "") for (pdl <- parentDL) add(hmap, "PARENT-DL-SIGMA-LEMMA=" + pdl + "-" + sigmaLemma)
            if (parentLemma != "" && sigmaDL.nonEmpty) for (sdl <- sigmaDL) add(hmap, "PARENT-LEMMA-SIGMA-DL=" + parentLemma + "-" + sdl)
            if (parentNER != "" && sigmaNER != "") add(hmap, "PARENT-SIGMA-NER=" + parentNER + "-" + sigmaNER)
          }
      }

      val sigmaChildren = state.currentGraph.childrenOf(sigma) diff // we exclude beta from children - as that is covered elsewhere
        (state.childrenToProcess match {
          case Nil => List()
          case head :: tail => List(head)
        })
      if (sigmaChildren.nonEmpty) {
        add(hmap, "CHILDREN-SIGMA-NO", sigmaParents.size)
        val childrenLabelCombos = for {
          child <- sigmaChildren
          label <- state.currentGraph.labelsBetween(sigma, child)
        } yield (child, label)

        childrenLabelCombos foreach {
          case (child, label) =>
            val childWord = state.currentGraph.nodes(child)
            if (state.currentGraph.insertedNodes contains child) {
              add(hmap, "CHILD-INSERTED=" + childWord)
              if (!quadraticTurbo) if (state.currentGraph.insertedNodes contains sigma) add(hmap, "SIGMA-CHILD-BOTH-INSERTED")
            }
            add(hmap, "CHILD-SIGMA-LABEL=" + label)
            add(hmap, "CHILD-WORD=" + childWord)
            if (!quadraticTurbo) add(hmap, "SIGMA-CHILD-WORDS=" + child + "-" + sigmaWord)
        }
      }
    }
    hmap
  }

  def sigmaBetaFeatures(sentence: Sentence, state: WangXueTransitionState, action: WangXueAction): Map[Int, Double] = {
    val hmap = new java.util.HashMap[Int, Double]
    val quadraticTurbo = options.contains("--quadratic")
    val sigma = state.nodesToProcess.head
    val beta = state.childrenToProcess.head
    val label = state.currentGraph.arcs((sigma, beta))
    val sigmaDL = state.startingDT.edgesToParents(sigma) map state.startingDT.arcs
    val betaDL = state.startingDT.edgesToParents(beta) map state.startingDT.arcs
    val sigmaParents = state.currentGraph.parentsOf(sigma)
    val sigmaWord = state.currentGraph.nodes.getOrElse(sigma, "!!??")
    assert(sigmaWord != "!!??", "Sigma not found: " + state)
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

    if (!betaInserted && !sigmaInserted) {
      val path = state.startingDT.getPathBetween(sigma, beta)
      add(hmap, "SIGMA-BETA-PATH=" + path)
      add(hmap, "SIGMA-BETA-PATH-LEMMAS=" + sigmaLemma + "-" + path + "-" + betaLemma)
      add(hmap, "SIGMA-BETA-PATH-DISTANCE=" + distance + "-" + path)
    }

    if (betaInserted) add(hmap, "BETA-INSERTED")
    if (!quadraticTurbo) if (sigmaInserted && betaInserted) add(hmap, "SIGMA-BETA-INSERTED")
    add(hmap, "BETA-WORD=" + betaWord)
    if (numeric.replaceAllIn(betaWord, "") == "") add(hmap, "BETA-NUMERIC")
    if (!quadraticTurbo) add(hmap, "SIGMA-BETA-WORDS=" + sigmaWord + "-" + betaWord)

    // WangXue features
    if (!quadraticTurbo) {
      if (sigmaLemma != "" && betaPOS != "") add(hmap, "SIGMA-LEMMA-BETA-POS=" + sigmaLemma + "-" + betaPOS)
      if (sigmaLemma != "" && label != "") add(hmap, "SIGMA-LEMMA-BETA-DL=" + sigmaLemma + "-" + label)
      if (sigmaPOS != "" && betaLemma != "") add(hmap, "SIGMA-POS-BETA-LEMMA=" + sigmaPOS + "-" + betaLemma)
      if (sigmaDL.nonEmpty && betaLemma != "") for (sdl <- sigmaDL) add(hmap, "SIGMA-DL-BETA-LEMMA=" + sdl + "-" + betaLemma)
      if (sigmaLemma != "" && betaDL.nonEmpty) for (bdl <- betaDL) add(hmap, "BETA-DL-SIGMA-LEMMA=" + bdl + "-" + betaLemma)
      if (sigmaLemma != "" && label != "") add(hmap, "SIGMA-LEMMA-LABEL=" + sigmaLemma + "-" + label)
      if (betaLemma != "" && label != "") add (hmap, "LABEL-BETA-LEMMA=" + label + "-" + betaLemma)
      if (sigmaNER != "" && betaNER != "") add(hmap, "SIGMA-BETA-NER=" + sigmaNER + "-" + betaNER)
    }
    // end WangXue binaries

    if (distance > 0) add(hmap, "SIGMA-BETA-DISTANCE", distance)
    add(hmap, "SIGMA-BETA-DISTANCE=" + distance) // distance indicator feature
    if (sigmaPosition == 0 || betaPosition == 0) add(hmap, "SIGMA-BETA-DISTANCE-UNKNOWN")
    if (betaPOS != "") add(hmap, "BETA-POS=" + betaPOS)
    if (betaLemma != "") add(hmap, "BETA-LEMMA=" + betaLemma)
    if (betaNER != "") add(hmap, "BETA-NER=" + betaNER)
    add(hmap, "SIGMA-BETA-LABEL=" + label)
    for (bdl <- betaDL) add(hmap, "BETA-DL=" + bdl)

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
    val quadraticTurbo = options.contains("--quadratic")
    val beta = state.childrenToProcess.head
    val sigma = state.nodesToProcess.head
    val betaWord = state.currentGraph.nodes(beta)
    val kappaWord = state.currentGraph.nodes(parameterNode)
    if (numeric.replaceAllIn(kappaWord, "") == "") add(hmap, "KAPPA-NUMERIC")
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
    if (!betaInserted && !kappaInserted) {
      val path = state.startingDT.getPathBetween(beta, parameterNode)
      add(hmap, "KAPPA-BETA-PATH=" + path)
      add(hmap, "KAPPA-BETA-PATH-LEMMAS=" + kappaLemma + "-" + path + "-" + betaLemma)
      add(hmap, "KAPPA-BETA-PATH-DISTANCE=" + distance + "-" + path)
    }

    if (kappaInserted) add(hmap, "KAPPA-INSERTED")
    if (kappaNER != "") add(hmap, "KAPPA-NER=" + kappaNER)
    if (kappaPOS != "") add(hmap, "KAPPA-POS=" + kappaPOS)
    if (kappaLemma != "") add(hmap, "KAPPA-LEMMA=" + kappaLemma)
    add(hmap, "KAPPA-WORD=" + kappaWord)
    //    if (kappaInserted && betaInserted) add(hmap, "KAPPA-BETA-INSERTED")
    //    add(hmap, "KAPPA-BETA-WORDS=" + kappaWord + "-" + betaWord)
    //    if (kappaPOS != "" && betaPOS != "") add(hmap, "KAPPA-BETA-POS=" + kappaPOS + "-" + betaPOS)
    //    if (kappaNER != "" && betaNER != "") add(hmap, "KAPPA-BETA-NER=" + kappaNER + "-" + betaNER)
    if (distance > 0) add(hmap, "KAPPA-BETA-DISTANCE", distance)
    add(hmap, "KAPPA-BETA-DISTANCE=" + distance) // distance indicator feature
    if (kappaPosition == 0 || betaPosition == 0) add(hmap, "KAPPA-BETA-DISTANCE-UNKNOWN")

    val kappaDL = state.startingDT.edgesToParents(parameterNode) map state.startingDT.arcs
    val betaDL = state.startingDT.edgesToParents(beta) map state.startingDT.arcs
    val betaLabel = state.currentGraph.arcs((sigma, beta))
    val kappaParents = state.currentGraph.parentsOf(parameterNode)
    val kappaLabels = kappaParents map (state.currentGraph.arcs(_, parameterNode))
    for (kl <- kappaLabels) add(hmap, "KAPPA-LABEL=" + kl)
    for (kdl <- kappaDL) add(hmap, "KAPPA-DL=" + kdl)

    // WangXue features
    if (!quadraticTurbo) {
      if (betaLemma != "" && kappaDL.nonEmpty) for (kdl <- kappaDL) add(hmap, "BETA-LEMMA-KAPPA-DL=" + betaLemma + "-" + kdl)
      if (kappaPOS != "" && betaLemma != "") add(hmap, "KAPPA-POS-BETA-LEMMA=" + kappaPOS + "-" + betaLemma)
      if (kappaLemma != "" && betaDL.nonEmpty) for (bdl <- betaDL) add(hmap, "KAPPA-LEMMA-BETA-DL=" + kappaLemma + "-" + bdl)
      if (kappaNER != "" && betaNER != "") add(hmap, "KAPPA-BETA-NER=" + kappaNER + "-" + betaNER)
    }
    // end WangXue binaries

    hmap
  }

  def quadraticCombination(linearFeatures: Map[Int, Double]): Map[Int, Double] = {
    // We wish to create a quadratic feature from every combination of the input
    // 'linear' features. The value we calculate by multiplication.
    val linearKeys = linearFeatures.keys.toList.sorted filterNot (x => (dict.elem(x) contains "PATH") || (dict.elem(x) contains "BIAS"))
    val quadFeaturesByString = for {
      f1 <- linearKeys
      f2 <- linearKeys.dropWhile { _ <= f1 }
    } yield (dict.elem(f1) + ":" + dict.elem(f2) -> linearFeatures(f1) * linearFeatures(f2))
    (quadFeaturesByString map { case (s, v) => (dict.index(s) -> v) }).toMap
  }

}
