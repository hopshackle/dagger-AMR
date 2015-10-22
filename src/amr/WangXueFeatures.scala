package amr
import scala.collection.Map
import ImportConcepts.{ concept }
import java.io._
import scala.util.Random
import gnu.trove.map.hash.THashMap
import dagger.ml.Instance
import java.util.concurrent.atomic._
import dagger.core._

class WangXueFeatureFactory(options: DAGGEROptions, dict: Index = new MapIndex) extends FeatureFunctionFactory[Sentence, WangXueTransitionState, WangXueAction] {
  override def newFeatureFunction: FeatureFunction[Sentence, WangXueTransitionState, WangXueAction] = {
    new WangXueFeatures(options, dict)
  }
}

object WangXueFeatures {
  var debug = false
  var includeChildren = false
  var includeParents = false
  var includeShenanigans = false
  var includeActionHistory = false
  var includeWords = false
  var includeDeletions = false
  val idFountain = new AtomicLong(1)

}

class WangXueFeatures(options: DAGGEROptions, dict: Index) extends FeatureFunction[Sentence, WangXueTransitionState, WangXueAction] {

  import scala.collection.JavaConversions.mapAsScalaMap
  import WangXueFeatures._

  val random = new Random()
  var cachedFeatures = new gnu.trove.map.hash.THashMap[Int, Float]()
  var cachedState: WangXueTransitionState = null

  def add(map: gnu.trove.map.hash.THashMap[Int, Float], feat: String, value: Float = 1.0f) = {
    map.put(dict.index(feat), value)
  }

  override def features(sentence: Sentence, state: WangXueTransitionState, action: WangXueAction): gnu.trove.map.hash.THashMap[Int, Float] = {
    // if we have cached Features for this state, and the action does not have a node parameter,
    // then we can save ourselves the effort (and memory) of re-calculating everything
    val parameterisedAction = action.isInstanceOf[hasNodeAsParameter]
    if (cachedState != null && cachedState.eq(state) && !parameterisedAction) {
      cachedFeatures
    } else {

      val thisDebug = if (debug && random.nextDouble < 0.001) true else false
      val quadraticTurbo = options.contains("--quadratic")

      val linearOutput = sigmaFeatures(sentence, state, action) ++ {
        (state.childrenToProcess, parameterisedAction) match {
          case (Nil, false) => Nil
          case (Nil, true) => kappaFeatures(sentence, state, action)
          case (head :: tail, false) => betaFeatures(sentence, state, action)
          case (head :: tail, true) => betaFeatures(sentence, state, action) ++ kappaFeatures(sentence, state, action)
        }
      }
      val output = linearOutput ++ (if (quadraticTurbo) quadraticCombination(linearOutput) else Map())
      if (thisDebug) {
        val featuresDebug = new FileWriter(options.DAGGER_OUTPUT_PATH + "WXfeatures_debug.txt", true)
        featuresDebug.write(state.toString)
        output foreach (_ match { case (index, value) => featuresDebug.write(f"$index : ${dict.elem(index)} = $value%.2f\n") })
        featuresDebug.close
      }

      if (!parameterisedAction) {
        cachedFeatures = Instance.scalaMapToTrove(output)
        cachedState = state
      }
      Instance.scalaMapToTrove(output)
    }
  }

  def sigmaFeatures(sentence: Sentence, state: WangXueTransitionState, action: WangXueAction): Map[Int, Float] = {
    val quadraticTurbo = options.contains("--quadratic")
    val hmap = new gnu.trove.map.hash.THashMap[Int, Float]
    val sigma = state.nodesToProcess.head
    val sigmaWord = state.currentGraph.nodes.getOrElse(sigma, "!!??")
    assert(sigmaWord != "!!??", "Sigma not found: " + state)
    val sigmaInserted = state.currentGraph.insertedNodes contains sigma
    //    val wordTokens: Double = sentence.dependencyTree.nodes.size
    //    val insertedNodes: Double = state.currentGraph.insertedNodes.size
    //    if (insertedNodes > 0) add(hmap, "RATIO-INSERT-WORDS", insertedNodes / wordTokens)
    //    val insertedConcepts = state.currentGraph.insertedNodes.keys map (node => state.currentGraph.nodes.getOrElse(node, "DELETED"))
    //    val conceptSet = insertedConcepts
    //    conceptSet foreach (c => add(hmap, "INSERT-COUNT-" + c, insertedConcepts.count { x => x == c }))

    add(hmap, "BIAS")
    if (includeShenanigans) add(hmap, "SHENANIGAN=" + idFountain.getAndIncrement)
    if (includeActionHistory) {
      if (state.previousActions.size > 0) add(hmap, "LAST-ACTION=" + state.previousActions.head.name)
      if (state.previousActions.size > 1) add(hmap, "LAST-B1-ACTION=" + state.previousActions.tail.head.name)
      if (state.previousActions.size > 2) add(hmap, "LAST-B2-ACTION=" + state.previousActions.tail.tail.head.name)
      if (state.previousActions.size > 3) add(hmap, "LAST-B3-ACTION=" + state.previousActions.tail.tail.tail.head.name)
      if (state.previousActions.size > 1) add(hmap, "LAST-TWO-ACTIONS=" + state.previousActions.head.name + state.previousActions.tail.head.name)
      if (state.previousActions.size > 2) add(hmap, "LAST-THREE-ACTIONS=" + state.previousActions.head.name + state.previousActions.tail.head.name + state.previousActions.tail.tail.head.name)
    }

    val sigmaLemma = state.currentGraph.nodeLemmas.getOrElse(sigma, "")

    if (sigmaWord.endsWith("er") || sigmaWord.endsWith("est")) add(hmap, "COMPARATOR")
    if (sigmaWord.startsWith("un") || sigmaWord.startsWith("in") || sigmaWord.startsWith("il") || sigmaWord.startsWith("anti")) add(hmap, "NEGATION")

    val prefix = sigmaWord.split("-")
    if (prefix.size > 1) {
      add(hmap, "PREFIX=" + prefix(0))
      add(hmap, "SUFFIX=" + prefix(1))
    }

    val sigmaPOS = state.currentGraph.nodePOS.getOrElse(sigma, "")
    val sigmaNER = state.currentGraph.nodeNER.getOrElse(sigma, "")
    if (sigmaPOS != "") add(hmap, "SIGMA-POS=" + sigmaPOS)
    if (sigmaLemma != "") add(hmap, "SIGMA-LEMMA=" + sigmaLemma)
    if (sigmaWord != sigmaLemma && includeWords) add(hmap, "SIGMA-WORD=" + sigmaWord)
    if (sigmaNER != "") add(hmap, "SIGMA-NER=" + sigmaNER)
    if (sigmaInserted) add(hmap, "SIGMA-INSERTED")
    val sigmaDL = state.startingDT.edgesToParents(sigma) map state.startingDT.arcs
    for (sdl <- sigmaDL) add(hmap, "SIGMA-DL=" + sdl)

    val mergedNodes = state.currentGraph.mergedNodes.get(sigma) match {
      case None => Nil
      case Some(mergedNodes) => mergedNodes
    }
    mergedNodes foreach { case (n, label) => add(hmap, "SIGMA-REPH=" + label) }

    if (includeDeletions) {
      val deletedNodes = state.currentGraph.deletedNodes.get(sigma) match {
        case None => Nil
        case Some(deletedNodes) => deletedNodes
      }
      deletedNodes foreach { case (n, label) => add(hmap, "SIGMA-DEL=" + label) }
    }
    if (includeParents) {
      val sigmaParents = state.currentGraph.parentsOf(sigma)

      val sigmaGrandparents = (sigmaParents flatMap state.currentGraph.parentsOf).distinct

      if (sigmaParents.nonEmpty) {

        val parentLabelCombos = for {
          parent <- sigmaParents
          label <- state.currentGraph.labelsBetween(parent, sigma)
        } yield (parent, label)
        //      add(hmap, "PARENT-SIGMA-NO", sigmaParents.size)

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

            sigmaGrandparents foreach { gp =>
              if (state.currentGraph.nodeLemmas contains gp)
                add(hmap, "GP-PARENT-SIGMA=" + state.currentGraph.nodeLemmas(gp) + "-" + parentLemma + "-" + sigmaLemma)
            }

            if (!(sigmaDL contains label)) add(hmap, "PARENT-SIGMA-LABEL=" + label)
            if (parentLemma != "") add(hmap, "PARENT-LEMMA=" + parentLemma)
            if (parentLemma != parentWord && includeWords) add(hmap, "PARENT-WORD=" + parentWord)
            if (parentPOS != "") add(hmap, "PARENT-POS=" + parentPOS)
            if (parentNER != "") add(hmap, "PARENT-NER=" + parentNER)
            for (pdl <- parentDL) add(hmap, "PARENT-DL=" + pdl)
            if (!quadraticTurbo) {
              if (includeWords) add(hmap, "PARENT-SIGMA-WORDS=" + parentWord + "-" + sigmaWord)
              add(hmap, "PARENT-SIGMA-LEMMAS=" + parentLemma + "-" + sigmaLemma)
              if (parentLemma != "" && sigmaPOS != "") add(hmap, "PARENT-LEMMA-SIGMA-POS=" + parentLemma + "-" + sigmaPOS)
              if (parentPOS != "" && sigmaLemma != "") add(hmap, "PARENT-POS-SIGMA-LEMMA=" + parentPOS + "-" + sigmaLemma)
              if (parentDL.nonEmpty && sigmaLemma != "") for (pdl <- parentDL) add(hmap, "PARENT-DL-SIGMA-LEMMA=" + pdl + "-" + sigmaLemma)
              if (parentLemma != "" && sigmaDL.nonEmpty) for (sdl <- sigmaDL) add(hmap, "PARENT-LEMMA-SIGMA-DL=" + parentLemma + "-" + sdl)
              if (parentNER != "" && sigmaNER != "") add(hmap, "PARENT-SIGMA-NER=" + parentNER + "-" + sigmaNER)
            }
        }
      }
    }
    if (includeChildren) {
      val sigmaChildren = state.currentGraph.childrenOf(sigma) diff // we exclude beta from children - as that is covered elsewhere
        (state.childrenToProcess match {
          case Nil => List()
          case head :: tail => List(head)
        })
      if (sigmaChildren.nonEmpty) {
        add(hmap, "CHILDREN-SIGMA-NO=" + sigmaChildren.size)
        val childrenLabelCombos = for {
          child <- sigmaChildren
          label <- state.currentGraph.labelsBetween(sigma, child)
        } yield (child, label)

        childrenLabelCombos foreach {
          case (child, label) =>
            val childWord = state.currentGraph.nodes(child)
            val childLemma = state.currentGraph.nodeLemmas.getOrElse(child, "")
            if (state.currentGraph.insertedNodes contains child) {
              add(hmap, "CHILD-INSERTED=" + childWord)
              if (!quadraticTurbo) if (state.currentGraph.insertedNodes contains sigma) add(hmap, "SIGMA-CHILD-BOTH-INSERTED")
            }
            add(hmap, "CHILD-SIGMA-LABEL=" + label)
            if (childLemma != "") {
              add(hmap, "CHILD-LEMMA-LABEL=" + childLemma + "-" + label)
              add(hmap, "CHILD-LEMMA=" + childLemma)
              if (!quadraticTurbo) add(hmap, "SIGMA-CHILD-LEMMA=" + sigmaLemma + "-" + childLemma)
            }
            if (includeWords) add(hmap, "CHILD-WORD=" + childWord)

        }
      }
    }

    hmap
  }

  def betaFeatures(sentence: Sentence, state: WangXueTransitionState, action: WangXueAction): Map[Int, Float] = {
    val hmap = new gnu.trove.map.hash.THashMap[Int, Float]
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
    if (!quadraticTurbo) add(hmap, "SIGMA-BETA-WORDS=" + sigmaWord + "-" + betaWord)

    // WangXue features
    if (!quadraticTurbo) {
      if (sigmaLemma != "" && betaPOS != "") add(hmap, "SIGMA-LEMMA-BETA-POS=" + sigmaLemma + "-" + betaPOS)
      if (sigmaLemma != "" && label != "") add(hmap, "SIGMA-LEMMA-BETA-DL=" + sigmaLemma + "-" + label)
      if (sigmaPOS != "" && betaLemma != "") add(hmap, "SIGMA-POS-BETA-LEMMA=" + sigmaPOS + "-" + betaLemma)
      if (sigmaDL.nonEmpty && betaLemma != "") for (sdl <- sigmaDL) add(hmap, "SIGMA-DL-BETA-LEMMA=" + sdl + "-" + betaLemma)
      if (sigmaLemma != "" && betaDL.nonEmpty) for (bdl <- betaDL) add(hmap, "BETA-DL-SIGMA-LEMMA=" + bdl + "-" + betaLemma)
      if (sigmaLemma != "" && label != "") add(hmap, "SIGMA-LEMMA-LABEL=" + sigmaLemma + "-" + label)
      if (betaLemma != "" && label != "") add(hmap, "LABEL-BETA-LEMMA=" + label + "-" + betaLemma)
      if (sigmaNER != "" && betaNER != "") add(hmap, "SIGMA-BETA-NER=" + sigmaNER + "-" + betaNER)
    }
    // end WangXue binaries

    if (distance > 0) add(hmap, "SIGMA-BETA-DISTANCE", distance)
    add(hmap, "SIGMA-BETA-DISTANCE=" + distance) // distance indicator feature
    if (sigmaPosition == 0 || betaPosition == 0) add(hmap, "SIGMA-BETA-DISTANCE-UNKNOWN")
    if (betaPOS != "") add(hmap, "BETA-POS=" + betaPOS)
    if (betaLemma != "") add(hmap, "BETA-LEMMA=" + betaLemma)
    if (betaLemma != betaWord) add(hmap, "BETA-WORD=" + betaWord)
    if (betaNER != "") add(hmap, "BETA-NER=" + betaNER)
    for (bdl <- betaDL) add(hmap, "BETA-DL=" + bdl)
    if (!(betaDL contains label)) add(hmap, "SIGMA-BETA-LABEL=" + label)

    val prefix = betaLemma.split("-")
    if (prefix.size > 1) {
      add(hmap, "BETAPREFIX=" + prefix(0))
      add(hmap, "BETASUFFIX=" + prefix(1))
    }

    if (state.currentGraph.swappedArcs contains ((beta, sigma))) add(hmap, "SWAPPED-ARC")

    val mergedNodes = state.currentGraph.mergedNodes.get(beta) match {
      case None => Nil
      case Some(mergedNodes) => mergedNodes
    }
    mergedNodes foreach { case (n, label) => add(hmap, "BETA-REPH=" + label) }

    if (includeDeletions) {
      val deletedNodes = state.currentGraph.deletedNodes.get(beta) match {
        case None => Nil
        case Some(deletedNodes) => deletedNodes
      }
      deletedNodes foreach { case (n, label) => add(hmap, "BETA-DEL=" + label) }
    }
    hmap
  }

  def kappaFeatures(sentence: Sentence, state: WangXueTransitionState, action: WangXueAction): Map[Int, Float] = {
    val parameterNode = action.asInstanceOf[hasNodeAsParameter].parameterNode
    val hmap = new gnu.trove.map.hash.THashMap[Int, Float]
    val quadraticTurbo = options.contains("--quadratic")
    val betaOption = state.childrenToProcess.headOption
    val sigma = state.nodesToProcess.head
    val kappaWord = state.currentGraph.nodes(parameterNode)
    val (kappaPosition, _) = state.currentGraph.nodeSpans.getOrElse(parameterNode, (0, 0))
    val kappaPOS = state.currentGraph.nodePOS.getOrElse(parameterNode, "")
    val kappaLemma = state.currentGraph.nodeLemmas.getOrElse(parameterNode, "")
    val kappaNER = state.currentGraph.nodeNER.getOrElse(parameterNode, "")
    val kappaInserted = state.currentGraph.insertedNodes contains parameterNode

    if (kappaInserted) add(hmap, "KAPPA-INSERTED")
    if (kappaNER != "") add(hmap, "KAPPA-NER=" + kappaNER)
    if (kappaPOS != "") add(hmap, "KAPPA-POS=" + kappaPOS)
    if (kappaLemma != "") add(hmap, "KAPPA-LEMMA=" + kappaLemma)
    if (kappaWord != kappaLemma) add(hmap, "KAPPA-WORD=" + kappaWord)
    //    if (kappaInserted && betaInserted) add(hmap, "KAPPA-BETA-INSERTED")
    //    add(hmap, "KAPPA-BETA-WORDS=" + kappaWord + "-" + betaWord)
    //    if (kappaPOS != "" && betaPOS != "") add(hmap, "KAPPA-BETA-POS=" + kappaPOS + "-" + betaPOS)
    //    if (kappaNER != "" && betaNER != "") add(hmap, "KAPPA-BETA-NER=" + kappaNER + "-" + betaNER)

    val kappaDL = state.startingDT.edgesToParents(parameterNode) map state.startingDT.arcs
    val kappaParents = state.currentGraph.parentsOf(parameterNode)
    val kappaLabels = kappaParents map (state.currentGraph.arcs(_, parameterNode))
    for (kl <- kappaLabels) add(hmap, "KAPPA-LABEL=" + kl)
    for (kdl <- kappaDL) add(hmap, "KAPPA-DL=" + kdl)

    betaOption match {
      case Some(beta) =>
        val betaWord = state.currentGraph.nodes(beta)
        val betaInserted = state.currentGraph.insertedNodes contains beta
        val (betaPosition, _) = state.currentGraph.nodeSpans.getOrElse(beta, (0, 0))
        val distance = if (kappaPosition > 0 && betaPosition > 0) Math.abs(kappaPosition - betaPosition) else 0
        val betaPOS = state.currentGraph.nodePOS.getOrElse(beta, "")
        val betaLemma = state.currentGraph.nodeLemmas.getOrElse(beta, "")
        val betaNER = state.currentGraph.nodeNER.getOrElse(beta, "")
        if (distance > 0) add(hmap, "KAPPA-BETA-DISTANCE", distance)
        if (!betaInserted && !kappaInserted) {
          val path = state.startingDT.getPathBetween(beta, parameterNode)
          add(hmap, "KAPPA-BETA-PATH=" + path)
          add(hmap, "KAPPA-BETA-PATH-LEMMAS=" + kappaLemma + "-" + path + "-" + betaLemma)
          add(hmap, "KAPPA-BETA-PATH-DISTANCE=" + distance + "-" + path)
        }
        add(hmap, "KAPPA-BETA-DISTANCE=" + distance) // distance indicator feature
        if (kappaPosition == 0 || betaPosition == 0) add(hmap, "KAPPA-BETA-DISTANCE-UNKNOWN")
        val betaDL = state.startingDT.edgesToParents(beta) map state.startingDT.arcs
        val betaLabel = state.currentGraph.arcs((sigma, beta))

        // WangXue features
        if (!quadraticTurbo) {
          if (betaLemma != "" && kappaDL.nonEmpty) for (kdl <- kappaDL) add(hmap, "BETA-LEMMA-KAPPA-DL=" + betaLemma + "-" + kdl)
          if (kappaPOS != "" && betaLemma != "") add(hmap, "KAPPA-POS-BETA-LEMMA=" + kappaPOS + "-" + betaLemma)
          if (kappaLemma != "" && betaDL.nonEmpty) for (bdl <- betaDL) add(hmap, "KAPPA-LEMMA-BETA-DL=" + kappaLemma + "-" + bdl)
          if (kappaNER != "" && betaNER != "") add(hmap, "KAPPA-BETA-NER=" + kappaNER + "-" + betaNER)
        }
      case None =>
        val (sigmaPosition, _) = state.currentGraph.nodeSpans.getOrElse(sigma, (0, 0))
        val sigmaInserted = state.currentGraph.insertedNodes contains sigma
        val distance = if (kappaPosition > 0 && sigmaPosition > 0) Math.abs(kappaPosition - sigmaPosition) else 0
        val sigmaPOS = state.currentGraph.nodePOS.getOrElse(sigma, "")
        val sigmaLemma = state.currentGraph.nodeLemmas.getOrElse(sigma, "")
        val sigmaNER = state.currentGraph.nodeNER.getOrElse(sigma, "")
        if (distance > 0) add(hmap, "KAPPA-SIGMA-DISTANCE", distance)
        if (!sigmaInserted && !kappaInserted) {
          val path = state.startingDT.getPathBetween(sigma, parameterNode)
          add(hmap, "KAPPA-SIGMA-PATH=" + path)
          add(hmap, "KAPPA-SIGMA-PATH-LEMMAS=" + kappaLemma + "-" + path + "-" + sigmaLemma)
          add(hmap, "KAPPA-SIGMA-PATH-DISTANCE=" + distance + "-" + path)
        }
        add(hmap, "KAPPA-BETA-DISTANCE=" + distance) // distance indicator feature
        if (kappaPosition == 0 || sigmaPosition == 0) add(hmap, "KAPPA-SIGMA-DISTANCE-UNKNOWN")
        val sigmaDL = state.startingDT.edgesToParents(sigma) map state.startingDT.arcs
        // WangXue features
        if (!quadraticTurbo) {
          if (sigmaLemma != "" && kappaDL.nonEmpty) for (kdl <- kappaDL) add(hmap, "SIGMA-LEMMA-KAPPA-DL=" + sigmaLemma + "-" + kdl)
          if (kappaPOS != "" && sigmaLemma != "") add(hmap, "KAPPA-POS-SIGMA-LEMMA=" + kappaPOS + "-" + sigmaLemma)
          if (kappaLemma != "" && sigmaDL.nonEmpty) for (sdl <- sigmaDL) add(hmap, "KAPPA-LEMMA-SIGMA-DL=" + kappaLemma + "-" + sdl)
          if (kappaNER != "" && sigmaNER != "") add(hmap, "KAPPA-SIGMA-NER=" + kappaNER + "-" + sigmaNER)
        }
    }
    hmap
  }

  def quadraticCombination(linearFeatures: Map[Int, Float]): Map[Int, Float] = {
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
