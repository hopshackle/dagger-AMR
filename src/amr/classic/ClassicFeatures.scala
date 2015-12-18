package amr.classic

import scala.collection.Map
import java.io._
import amr._
import scala.util.Random
import gnu.trove.map.hash.THashMap
import dagger.ml.Instance
import java.util.concurrent.atomic._
import dagger.core._

class ClassicFeatureFactory(options: DAGGEROptions, dict: Index = new MapIndex) extends FeatureFunctionFactory[Sentence, ClassicTransitionState, ClassicAction] {
  override def newFeatureFunction: FeatureFunction[Sentence, ClassicTransitionState, ClassicAction] = {
    new ClassicFeatures(options, dict)
  }
}

object ClassicFeatures {
  var debug = false
  var includeWords = false
  val idFountain = new AtomicLong(1)

}

class ClassicFeatures(options: DAGGEROptions, dict: Index) extends FeatureFunction[Sentence, ClassicTransitionState, ClassicAction] {

  import scala.collection.JavaConversions.mapAsScalaMap
  import ClassicFeatures._

  val random = new Random()
  var cachedFeatures = new gnu.trove.map.hash.THashMap[Int, Float]()
  var cachedState: ClassicTransitionState = null

  // import scala.collection.JavaConversions.mapAsScalaMap
  def add(map: gnu.trove.map.hash.THashMap[Int, Float], feat: String, value: Float = 1.0f) = {
    map.put(dict.index(feat), value)
  }
  override def featureName(key: Int): String = dict.elem(key)
  override def features(sentence: Sentence, state: ClassicTransitionState, action: ClassicAction): gnu.trove.map.hash.THashMap[Int, Float] = {

    val parameterisedAction = action.isInstanceOf[hasNodeAsParameter]
    val dt = state.originalInput.get.dependencyTree
    val currentDT = state.currentGraph
    if (cachedState != null && cachedState.eq(state)) {
      if (!parameterisedAction)
        cachedFeatures
      else {
        val hmap = new gnu.trove.map.hash.THashMap[Int, Float]
        val paramFeatures = nodeFeatures("K", action.asInstanceOf[hasNodeAsParameter].parameterNode, dt, currentDT, state)
        hmap.putAll(cachedFeatures)
        hmap.putAll(paramFeatures)
        hmap
      }
    } else {
      val hmap = new gnu.trove.map.hash.THashMap[Int, Float]
      add(hmap, "BIAS")
      //     val coreFeatures = sentenceFeatures("C", dt, currentDT, state)
      val sigmaFeatures = nodeFeatures("S", state.nodesToProcess.head, dt, currentDT, state)
      val parents = dt.parentsOf(state.nodesToProcess.head)
      val parentFeatures = if (parents.nonEmpty) nodeFeatures("P", parents.head, dt, currentDT, state) else new THashMap[Int, Float]
      val grandParents = if (parents.nonEmpty) dt.parentsOf(parents.head) else Nil
      val gpFeatures = if (grandParents.nonEmpty) nodeFeatures("GP", grandParents.head, dt, currentDT, state) else new THashMap[Int, Float]
      val paramFeatures = if (parameterisedAction) nodeFeatures("K", action.asInstanceOf[hasNodeAsParameter].parameterNode, dt, currentDT, state) else new THashMap[Int, Float]
      if (state.nodePair._2 != 0) assert(state.nodePair._1 == state.nodesToProcess.head)
      val betaFeatures = if (state.nodePair._2 != 0) nodeFeatures("B", state.nodePair._2, dt, currentDT, state) else new THashMap[Int, Float]
      val sigmaBeta = if (state.nodePair._2 != 0) nodeFeatures("SB", state.nodePair._1, state.nodePair._2, dt, currentDT, state) else new THashMap[Int, Float]
      val sigmaParam = if (parameterisedAction) nodeFeatures("SK", state.nodesToProcess.head, action.asInstanceOf[hasNodeAsParameter].parameterNode, dt, currentDT, state) else new THashMap[Int, Float]
      //     val parentCombo = if (parents.nonEmpty) nodeFeatures("SP", state.nodesToProcess.head, parents.head, dt, currentDT, state) else new THashMap[Int, Float]
      hmap.putAll(sigmaFeatures)
      hmap.putAll(parentFeatures)
      hmap.putAll(gpFeatures)
      hmap.putAll(betaFeatures)
      hmap.putAll(sigmaBeta)
      hmap.putAll(sigmaParam)
      //     hmap.putAll(parentCombo)
      //    hmap.putAll(coreFeatures)
      cachedFeatures = hmap    // we only cache the feature not dependent on a parameter node specific to an action
      cachedState = state

      hmap.putAll(paramFeatures)
      hmap
    }
  }

  def nodeFeatures(prefix: String, node: Int, dt: DependencyTree, currentDT: DependencyTree, state: ClassicTransitionState): gnu.trove.map.hash.THashMap[Int, Float] = {
    val hmap = new gnu.trove.map.hash.THashMap[Int, Float]

    val word = currentDT.nodes.getOrElse(node, "!!??")
    val labelsIn = currentDT.parentsOf(node) flatMap { p => currentDT.labelsBetween(p, node) }
    val labelsOut = currentDT.childrenOf(node) flatMap { c => currentDT.labelsBetween(node, c) }
    labelsIn foreach { label => add(hmap, prefix + "-IN=" + label) }
    labelsOut foreach { label => add(hmap, prefix + "-OUT=" + label) }
    if (state.fragments.contains(node)) {
      add(hmap, prefix + "-FRG-HEAD")
      add(hmap, prefix + "-FRG-HEAD=" + state.fragments(node).size)
    } else if (state.fragments.values.toList.flatten.contains(node))
      add(hmap, prefix + "-FRG-PART")

    val lemma = dt.nodeLemmas.getOrElse(node, "")
    val concept = if (lemma == "##") "##" else currentDT.nodes.getOrElse(node, "")
    if (word.endsWith("er") || word.endsWith("est")) add(hmap, prefix + "-COMPARATOR")
    if (word.startsWith("un") || word.startsWith("in") || word.startsWith("il") || word.startsWith("anti")) add(hmap, prefix + "-NEGATION")
    if (currentDT.insertedNodes.contains(node)) add(hmap, prefix + "-INSERTED")

    val hyphenSplit = word.split("-")
    if (hyphenSplit.size > 1) {
      add(hmap, prefix + "-PREFIX=" + hyphenSplit(0))
      add(hmap, prefix + "-SUFFIX=" + hyphenSplit(1))
    }

    val POS = currentDT.nodePOS.getOrElse(node, "")
    val NER = currentDT.nodeNER.getOrElse(node, "")
    if (POS != "") add(hmap, prefix + "-POS=" + POS)
    if (lemma != "") add(hmap, prefix + "-LEM=" + lemma)
    if (concept != "" && concept != lemma && concept != word) add(hmap, prefix + "-CON=" + concept)
    if (word != lemma && includeWords) add(hmap, prefix + "-WRD=" + word)
    if (NER != "") add(hmap, prefix + "-NER=" + NER)
    val DL = currentDT.depLabels.getOrElse(node, "") // state.startingDT.edgesToParents(sigma) map state.startingDT.arcs
    add(hmap, prefix + "-DL=" + DL)
    hmap
  }
  def nodeFeatures(prefix: String, node1: Int, node2: Int, dt: DependencyTree, currentDT: DependencyTree, state: ClassicTransitionState): gnu.trove.map.hash.THashMap[Int, Float] = {
    val hmap = new gnu.trove.map.hash.THashMap[Int, Float]

    val POS1 = currentDT.nodePOS.getOrElse(node1, "")
    val NER1 = currentDT.nodeNER.getOrElse(node1, "")
    val DL1 = currentDT.depLabels.getOrElse(node1, "")
    val lemma1 = currentDT.nodeLemmas.getOrElse(node1, "")
    val concept1 = if (lemma1 == "##") "##" else currentDT.nodes.getOrElse(node1, "")
    val POS2 = currentDT.nodePOS.getOrElse(node2, "")
    val NER2 = currentDT.nodeNER.getOrElse(node2, "")
    val DL2 = currentDT.depLabels.getOrElse(node2, "")
    val lemma2 = currentDT.nodeLemmas.getOrElse(node2, "")
    val concept2 = if (lemma2 == "##") "##" else currentDT.nodes.getOrElse(node2, "")

    val token1 = currentDT.nodeSpans.getOrElse(node1, (0, 0))._1
    val token2 = currentDT.nodeSpans.getOrElse(node2, (0, 0))._1
    val distance = if (token1 != 0 && token2 != 0) math.abs(token1 - token2) else -1
    val DTNode1 = if (!currentDT.insertedNodes.contains(node1)) node1 else {
      (dt.nodeSpans filter { case (_, (position, _)) => position == token1 } map { case (node, (_, _)) => node }).toSeq(0)
    }
    val DTNode2 = if (!currentDT.insertedNodes.contains(node2)) node2 else {
      (dt.nodeSpans filter { case (_, (position, _)) => position == token2 } map { case (node, (_, _)) => node }).toSeq(0)
    }
    val path = dt.getPathBetween(DTNode1, DTNode2)

    if (distance >= 0) {
      add(hmap, prefix + "-DIST=" + distance)
      if (path != "") add(hmap, prefix + "-PATH=" + path)
    }
    if (concept1 != "" && DL2 != "") add(hmap, prefix + "-CON-DL=" + concept1 + "-" + DL2)
    if (concept1 != "" && POS2 != "") add(hmap, prefix + "-CON-POS=" + concept1 + "-" + POS2)
    if (concept2 != "" && DL1 != "") add(hmap, prefix + "-DL-LEM=" + DL1 + "-" + concept2)
    if (concept2 != "" && POS1 != "") add(hmap, prefix + "-POS-CON=" + POS1 + "-" + concept2)
    if (NER1 != "" && NER2 != "") add(hmap, prefix + "-NER=" + NER1 + "-" + NER2)
    if (concept1 != "" && concept2 != "") add(hmap, prefix + "-CON=" + concept1 + "-" + concept2)
    if (dt.arcs.contains((DTNode1, DTNode2))) add(hmap, prefix + "-DTARC")
    if (dt.arcs.contains((DTNode2, DTNode1))) add(hmap, prefix + "-REVDTARC")
    if (currentDT.arcs.contains((node1, node2))) add(hmap, prefix + "-ARC")
    if (currentDT.arcs.contains((node2, node1))) add(hmap, prefix + "-REVARC")
    val fragmentNode1 = state.fragments.getOrElse(node1, List())
    if (fragmentNode1 contains node2) add(hmap, prefix + "-FRGLINK")
    hmap
  }
  def sentenceFeatures(prefix: String, dt: DependencyTree, currentDT: DependencyTree, state: ClassicTransitionState): gnu.trove.map.hash.THashMap[Int, Float] = {
    val hmap = new gnu.trove.map.hash.THashMap[Int, Float]
    for ((fh, fString) <- state.fragmentHeads) {
      add(hmap, prefix + "-FRG=" + fString)
    }
    val lemmaCounts = dt.nodeLemmas.values.groupBy(identity) map { case (key, iter) => (key -> iter.size) }
    lemmaCounts foreach { lc => add(hmap, prefix + "-LEM=" + lc._1, lc._2) }
    hmap
  }

}