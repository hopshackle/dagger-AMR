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
      // also; even if we do have a parameterNode, we can still share the core features

      val thisDebug = if (debug && random.nextDouble < 0.001) true else false
      val quadraticTurbo = options.contains("--quadratic")

      if (cachedState != null && cachedState.eq(state)) {
        // we can reuse the existing cache
      } else {
        cachedState = state
        cachedFeatures = Instance.scalaMapToTrove(sigmaFeatures(sentence, state, action) ++ {
          state.childrenToProcess match {
            case Nil => Nil
            case head :: tail => betaFeatures(sentence, state, action)
          }
        })
        cachedFeatures.compact
      }

      val linearOutput = cachedFeatures ++ Instance.scalaMapToTrove({
        parameterisedAction match {
          case false => Map()
          case true => kappaFeatures(sentence, state, action)
        }
      })
      val output = linearOutput ++ (if (quadraticTurbo) quadraticCombination(linearOutput) else Map())
      if (thisDebug) {
        val featuresDebug = new FileWriter(options.DAGGER_OUTPUT_PATH + "WXfeatures_debug.txt", true)
        featuresDebug.write(state.toString)
        output foreach (_ match { case (index, value) => featuresDebug.write(f"$index : ${dict.elem(index)} = $value%.2f\n") })
        featuresDebug.close
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
      if (state.previousActions.size > 0) add(hmap, "LAST-A=" + state.previousActions.head.name)
      if (state.previousActions.size > 1) add(hmap, "LAST-B1-A=" + state.previousActions.tail.head.name)
      if (state.previousActions.size > 2) add(hmap, "LAST-B2-A=" + state.previousActions.tail.tail.head.name)
      if (state.previousActions.size > 3) add(hmap, "LAST-B3-A=" + state.previousActions.tail.tail.tail.head.name)
      if (state.previousActions.size > 1) add(hmap, "LAST-TWO-A=" + state.previousActions.head.name + state.previousActions.tail.head.name)
      if (state.previousActions.size > 2) add(hmap, "LAST-THREE-A=" + state.previousActions.head.name + state.previousActions.tail.head.name + state.previousActions.tail.tail.head.name)
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
    if (sigmaPOS != "") add(hmap, "S-POS=" + sigmaPOS)
    if (sigmaLemma != "") add(hmap, "S-LEM=" + sigmaLemma)
    if (sigmaWord != sigmaLemma && includeWords) add(hmap, "S-WRD=" + sigmaWord)
    if (sigmaNER != "") add(hmap, "S-NER=" + sigmaNER)
    if (sigmaInserted) add(hmap, "S-INSERTED")
    val sigmaDL = state.currentGraph.depLabels.getOrElse(sigma, "") // state.startingDT.edgesToParents(sigma) map state.startingDT.arcs
    add(hmap, "S-DL=" + sigmaDL)

    val mergedNodes = state.currentGraph.mergedNodes.get(sigma) match {
      case None => Nil
      case Some(mergedNodes) => mergedNodes
    }
    mergedNodes foreach { case (n, label) => add(hmap, "S-REPH=" + label) }

    if (includeDeletions) {
      val deletedNodes = state.currentGraph.deletedNodes.get(sigma) match {
        case None => Nil
        case Some(deletedNodes) => deletedNodes
      }
      deletedNodes foreach { case (n, label) => add(hmap, "S-DEL=" + label) }
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
            val parentDL = state.currentGraph.depLabels.getOrElse(parent, "") // state.startingDT.edgesToParents(parent) map state.startingDT.arcs
            if (state.currentGraph.insertedNodes contains parent) {
              add(hmap, "P-INSERTED=" + parentWord)
              if (state.currentGraph.insertedNodes contains parent) add(hmap, "PARENT-SIGMA-BOTH-INSERTED")
            }

            sigmaGrandparents foreach { gp =>
              if (state.currentGraph.nodeLemmas contains gp)
                add(hmap, "GP-P-S=" + state.currentGraph.nodeLemmas(gp) + "-" + parentLemma + "-" + sigmaLemma)
            }

            if (!(sigmaDL contains label)) add(hmap, "P-S-LAB=" + label)
            if (parentLemma != "") add(hmap, "P-LEM=" + parentLemma)
            if (parentLemma != parentWord && includeWords) add(hmap, "P-WRD=" + parentWord)
            if (parentPOS != "") add(hmap, "P-POS=" + parentPOS)
            if (parentNER != "") add(hmap, "P-NER=" + parentNER)
            add(hmap, "P-DL=" + parentDL)
            if (!quadraticTurbo) {
              if (includeWords) add(hmap, "P-S-WRD=" + parentWord + "-" + sigmaWord)
              add(hmap, "P-S-LEM=" + parentLemma + "-" + sigmaLemma)
              if (parentLemma != "" && sigmaPOS != "") add(hmap, "P-LEM-S-POS=" + parentLemma + "-" + sigmaPOS)
              if (parentPOS != "" && sigmaLemma != "") add(hmap, "P-POS-S-LEM=" + parentPOS + "-" + sigmaLemma)
              if (parentDL != "" && sigmaLemma != "") add(hmap, "P-DL-S-LEM=" + parentDL + "-" + sigmaLemma)
              if (parentLemma != "" && sigmaDL != "") add(hmap, "P-LEM-S-DL=" + parentLemma + "-" + sigmaDL)
              if (parentNER != "" && sigmaNER != "") add(hmap, "P-S-NER=" + parentNER + "-" + sigmaNER)
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
        add(hmap, "C-S-NO=" + sigmaChildren.size)
        val childrenLabelCombos = for {
          child <- sigmaChildren
          label <- state.currentGraph.labelsBetween(sigma, child)
        } yield (child, label)

        childrenLabelCombos foreach {
          case (child, label) =>
            val childWord = state.currentGraph.nodes(child)
            val childLemma = state.currentGraph.nodeLemmas.getOrElse(child, "")
            val childNER = state.currentGraph.nodeNER.getOrElse(child, "")
            val childPOS = state.currentGraph.nodePOS.getOrElse(child, "")
            if (state.currentGraph.insertedNodes contains child) {
              add(hmap, "C-INS=" + childLemma)
              if (!quadraticTurbo) if (state.currentGraph.insertedNodes contains sigma) add(hmap, "S-C-BOTH-INSERTED")
            }
            if (label != "") add(hmap, "C-S-LAB=" + label)
            if (childPOS != "") add(hmap, "C-POS=" + childPOS)
            if (childNER != "") add(hmap, "C-NER=" + childNER)
            if (childLemma != "") {
              add(hmap, "C-LEM-LAB=" + childLemma + "-" + label)
              add(hmap, "C-LEM=" + childLemma)
              if (!quadraticTurbo) {
                add(hmap, "S-C-LEM=" + sigmaLemma + "-" + childLemma)
                if (childLemma != "" && sigmaPOS != "") add(hmap, "P-LEM-S-POS=" + childLemma + "-" + sigmaPOS)
                if (childPOS != "" && sigmaLemma != "") add(hmap, "P-POS-S-LEM=" + childPOS + "-" + sigmaLemma)
                if (label != "" && sigmaLemma != "") add(hmap, "P-DL-S-LEM=" + label + "-" + sigmaLemma)
                if (childLemma != "" && sigmaDL != "") add(hmap, "P-LEM-S-DL=" + childLemma + "-" + sigmaDL)
                if (childNER != "" && sigmaNER != "") add(hmap, "P-S-NER=" + childNER + "-" + sigmaNER)
              }
            }
            if (includeWords) add(hmap, "C-WRD=" + childWord)

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
    val label = state.currentGraph.arcs.getOrElse((sigma, beta), "")
    if (!state.currentGraph.arcs.contains((sigma, beta))) {
      println("No Sigma-Beta arc in : ")
      println(state)
    }
    val sigmaDL = state.currentGraph.depLabels.getOrElse(sigma, "") // state.startingDT.edgesToParents(sigma) map state.startingDT.arcs
    val betaDL = state.currentGraph.depLabels.getOrElse(beta, "") // state.startingDT.edgesToParents(beta) map state.startingDT.arcs
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

    if (betaPosition > 0 && sigmaPosition > 0) {
      val sigmaDTNode = if (!sigmaInserted) sigma else {
        (state.startingDT.nodeSpans filter { case (_, (position, _)) => position == sigmaPosition } map { case (node, (_, _)) => node }).toSeq(0)
      }
      val betaDTNode = if (!betaInserted) beta else {
        (state.startingDT.nodeSpans filter { case (_, (position, _)) => position == betaPosition } map { case (node, (_, _)) => node }).toSeq(0)
      }
      val path = state.startingDT.getPathBetween(sigmaDTNode, betaDTNode)
      add(hmap, "S-B-PATH=" + path)
      add(hmap, "S-B-PATH-LEM=" + sigmaLemma + "-" + path + "-" + betaLemma)
      add(hmap, "S-B-PATH-DIST=" + distance + "-" + path)
    }

    if (betaInserted) add(hmap, "BETA-INSERTED")
    if (!quadraticTurbo) if (sigmaInserted && betaInserted) add(hmap, "SIGMA-BETA-INSERTED")
    if (!quadraticTurbo && includeWords) add(hmap, "S-B-WRD=" + sigmaWord + "-" + betaWord)

    // WangXue features
    if (!quadraticTurbo) {
      if (sigmaLemma != "" && betaPOS != "") add(hmap, "S-LEM-B-POS=" + sigmaLemma + "-" + betaPOS)
      if (sigmaLemma != "" && label != "") add(hmap, "S-LEM-B-DL=" + sigmaLemma + "-" + label)
      if (sigmaPOS != "" && betaLemma != "") add(hmap, "S-POS-B-LEM=" + sigmaPOS + "-" + betaLemma)
      if (sigmaDL != "" && betaLemma != "") add(hmap, "S-DL-B-LEM=" + sigmaDL + "-" + betaLemma)
      if (sigmaLemma != "" && betaDL != "") add(hmap, "B-DL-S-LEM=" + betaDL + "-" + betaLemma)
      if (sigmaLemma != "" && label != "") add(hmap, "S-LEM-LAB=" + sigmaLemma + "-" + label)
      if (betaLemma != "" && label != "") add(hmap, "LAB-B-LEM=" + label + "-" + betaLemma)
      if (sigmaNER != "" && betaNER != "") add(hmap, "S-B-NER=" + sigmaNER + "-" + betaNER)
    }
    // end WangXue binaries

    if (distance > 0) add(hmap, "SIGMA-BETA-DISTANCE", distance)
    add(hmap, "S-B-DISTANCE=" + distance) // distance indicator feature
    if (sigmaPosition == 0 || betaPosition == 0) add(hmap, "SIGMA-BETA-DISTANCE-UNKNOWN")
    if (betaPOS != "") add(hmap, "B-POS=" + betaPOS)
    if (betaLemma != "") add(hmap, "B-LEM=" + betaLemma)
    if (betaLemma != betaWord && includeWords) add(hmap, "B-WRD=" + betaWord)
    if (betaNER != "") add(hmap, "B-NER=" + betaNER)
    if (betaDL != "") add(hmap, "B-DL=" + betaDL)
    if (betaDL != label) add(hmap, "S-B-LABEL=" + label)

    val prefix = betaLemma.split("-")
    if (prefix.size > 1) {
      add(hmap, "B-PREFIX=" + prefix(0))
      add(hmap, "B-SUFFIX=" + prefix(1))
    }

    if (state.currentGraph.swappedArcs contains ((beta, sigma))) add(hmap, "SWAPPED-ARC")

    val mergedNodes = state.currentGraph.mergedNodes.get(beta) match {
      case None => Nil
      case Some(mergedNodes) => mergedNodes
    }
    mergedNodes foreach { case (n, label) => add(hmap, "B-REPH=" + label) }

    if (includeDeletions) {
      val deletedNodes = state.currentGraph.deletedNodes.get(beta) match {
        case None => Nil
        case Some(deletedNodes) => deletedNodes
      }
      deletedNodes foreach { case (n, label) => add(hmap, "B-DEL=" + label) }
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
    if (kappaNER != "") add(hmap, "K-NER=" + kappaNER)
    if (kappaPOS != "") add(hmap, "K-POS=" + kappaPOS)
    if (kappaLemma != "") add(hmap, "K-LEM=" + kappaLemma)
    if (includeWords && kappaWord != kappaLemma) add(hmap, "K-WRD=" + kappaWord)
    //    if (kappaInserted && betaInserted) add(hmap, "KAPPA-BETA-INSERTED")
    //    add(hmap, "KAPPA-BETA-WORDS=" + kappaWord + "-" + betaWord)
    //    if (kappaPOS != "" && betaPOS != "") add(hmap, "KAPPA-BETA-POS=" + kappaPOS + "-" + betaPOS)
    //    if (kappaNER != "" && betaNER != "") add(hmap, "KAPPA-BETA-NER=" + kappaNER + "-" + betaNER)

    val kappaDL = state.currentGraph.depLabels.getOrElse(parameterNode, "") // state.startingDT.edgesToParents(parameterNode) map state.startingDT.arcs
    val kappaParents = state.currentGraph.parentsOf(parameterNode)
    val kappaLabels = kappaParents map (state.currentGraph.arcs(_, parameterNode))
    for (kl <- kappaLabels) if (kl != kappaDL) add(hmap, "K-LAB=" + kl)
    if (kappaDL != "") add(hmap, "K-DL=" + kappaDL)

    betaOption match {
      case Some(beta) =>
        val betaWord = state.currentGraph.nodes(beta)
        val betaInserted = state.currentGraph.insertedNodes contains beta
        val (betaPosition, _) = state.currentGraph.nodeSpans.getOrElse(beta, (0, 0))
        val distance = if (kappaPosition > 0 && betaPosition > 0) Math.abs(kappaPosition - betaPosition) else 0
        val betaPOS = state.currentGraph.nodePOS.getOrElse(beta, "")
        val betaLemma = state.currentGraph.nodeLemmas.getOrElse(beta, "")
        val betaNER = state.currentGraph.nodeNER.getOrElse(beta, "")
        if (distance > 0) add(hmap, "K-B-DIST", distance)
        if (betaPosition > 0 && kappaPosition > 0) {
          val kappaDTNode = if (!kappaInserted) parameterNode else {
            (state.startingDT.nodeSpans filter { case (_, (position, _)) => position == kappaPosition } map { case (node, (_, _)) => node }).toSeq(0)
          }
          val betaDTNode = if (!betaInserted) beta else {
            (state.startingDT.nodeSpans filter { case (_, (position, _)) => position == betaPosition } map { case (node, (_, _)) => node }).toSeq(0)
          }
          val path = state.startingDT.getPathBetween(betaDTNode, kappaDTNode)
          add(hmap, "K-B-PATH=" + path)
          add(hmap, "K-B-PATH-LEM=" + kappaLemma + "-" + path + "-" + betaLemma)
          add(hmap, "K-B-PATH-DIST=" + distance + "-" + path)
        }
        add(hmap, "K-B-DIST=" + distance) // distance indicator feature
        if (kappaPosition == 0 || betaPosition == 0) add(hmap, "KAPPA-BETA-DISTANCE-UNKNOWN")
        val betaDL = state.currentGraph.depLabels.getOrElse(beta, "") // state.startingDT.edgesToParents(beta) map state.startingDT.arcs
        val betaLabel = state.currentGraph.arcs((sigma, beta))

        // WangXue features
        if (!quadraticTurbo) {
          if (betaLemma != "" && kappaDL != "") add(hmap, "B-LEM-K-DL=" + betaLemma + "-" + kappaDL)
          if (kappaPOS != "" && betaLemma != "") add(hmap, "K-POS-B-LEM=" + kappaPOS + "-" + betaLemma)
          if (kappaLemma != "" && betaDL != "") add(hmap, "K-LEM-B-DL=" + kappaLemma + "-" + betaDL)
          if (kappaNER != "" && betaNER != "") add(hmap, "K-B-NER=" + kappaNER + "-" + betaNER)
        }
      case None =>
        val (sigmaPosition, _) = state.currentGraph.nodeSpans.getOrElse(sigma, (0, 0))
        val sigmaInserted = state.currentGraph.insertedNodes contains sigma
        val distance = if (kappaPosition > 0 && sigmaPosition > 0) Math.abs(kappaPosition - sigmaPosition) else 0
        val sigmaPOS = state.currentGraph.nodePOS.getOrElse(sigma, "")
        val sigmaLemma = state.currentGraph.nodeLemmas.getOrElse(sigma, "")
        val sigmaNER = state.currentGraph.nodeNER.getOrElse(sigma, "")
        if (distance > 0) add(hmap, "K-S-DIST", distance)
        if (sigmaPosition > 0 && kappaPosition > 0) {
          val sigmaDTNode = if (!sigmaInserted) sigma else {
            (state.startingDT.nodeSpans filter { case (_, (position, _)) => position == sigmaPosition } map { case (node, (_, _)) => node }).toSeq(0)
          }
          val kappaDTNode = if (!kappaInserted) parameterNode else {
            (state.startingDT.nodeSpans filter { case (_, (position, _)) => position == kappaPosition } map { case (node, (_, _)) => node }).toSeq(0)
          }
          val path = state.startingDT.getPathBetween(sigmaDTNode, kappaDTNode)
          add(hmap, "K-S-PATH=" + path)
          add(hmap, "K-S-PATH-LEM=" + kappaLemma + "-" + path + "-" + sigmaLemma)
          add(hmap, "K-S-PATH-DIST=" + distance + "-" + path)
        }
        add(hmap, "K-S-DIST=" + distance) // distance indicator feature
        if (kappaPosition == 0 || sigmaPosition == 0) add(hmap, "KAPPA-SIGMA-DISTANCE-UNKNOWN")
        val sigmaDL = state.currentGraph.depLabels.getOrElse(sigma, "") // state.startingDT.edgesToParents(sigma) map state.startingDT.arcs
        // WangXue features
        if (!quadraticTurbo) {
          if (sigmaLemma != "" && kappaDL != "") add(hmap, "S-LEM-K-DL=" + sigmaLemma + "-" + kappaDL)
          if (kappaPOS != "" && sigmaLemma != "") add(hmap, "K-POS-S-LEM=" + kappaPOS + "-" + sigmaLemma)
          if (kappaLemma != "" && sigmaDL != "") add(hmap, "K-LEM-S-DL=" + kappaLemma + "-" + sigmaDL)
          if (kappaNER != "" && sigmaNER != "") add(hmap, "K-S-NER=" + kappaNER + "-" + sigmaNER)
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
