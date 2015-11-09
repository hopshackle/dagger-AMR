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

  override def features(sentence: Sentence, state: ClassicTransitionState, action: ClassicAction): gnu.trove.map.hash.THashMap[Int, Float] = {

    val parameterisedAction = action.isInstanceOf[hasNodeAsParameter]
    if (cachedState != null && cachedState.eq(state) && !parameterisedAction) {
      cachedFeatures
    } else {
      // also; even if we do have a parameterNode, we can still share the core features
      val hmap = new gnu.trove.map.hash.THashMap[Int, Float]
      add(hmap, "BIAS")
      val dt = state.originalInput.get.dependencyTree
      val sigmaFeatures = nodeFeatures("S", state.nodesToProcess.head, dt)
      val parents = dt.parentsOf(state.nodesToProcess.head)
      val parentFeatures = if (parents.nonEmpty) nodeFeatures("P", parents.head, dt) else new THashMap[Int, Float]
      val grandParents = if (parents.nonEmpty) dt.parentsOf(parents.head) else Nil
      val gpFeatures = if (grandParents.nonEmpty) nodeFeatures("GP", grandParents.head, dt) else new THashMap[Int, Float]
      hmap.putAll(sigmaFeatures)
      hmap.putAll(parentFeatures)
      hmap.putAll(gpFeatures)
      hmap
    }
  }

  def nodeFeatures(prefix: String, node: Int, dt: DependencyTree): gnu.trove.map.hash.THashMap[Int, Float] = {
    val hmap = new gnu.trove.map.hash.THashMap[Int, Float]

    val word = dt.nodes.getOrElse(node, "!!??")

    val lemma = dt.nodeLemmas.getOrElse(node, "")
    if (word.endsWith("er") || word.endsWith("est")) add(hmap, prefix + "-COMPARATOR")
    if (word.startsWith("un") || word.startsWith("in") || word.startsWith("il") || word.startsWith("anti")) add(hmap, prefix + "-NEGATION")

    val hyphenSplit = word.split("-")
    if (hyphenSplit.size > 1) {
      add(hmap, prefix + "-PREFIX=" + hyphenSplit(0))
      add(hmap, prefix + "-SUFFIX=" + hyphenSplit(1))
    }

    val POS = dt.nodePOS.getOrElse(node, "")
    val NER = dt.nodeNER.getOrElse(node, "")
    if (POS != "") add(hmap, prefix + "-POS=" + POS)
    if (lemma != "") add(hmap, prefix + "-LEM=" + lemma)
    if (word != lemma && includeWords) add(hmap, prefix + "-WRD=" + word)
    if (NER != "") add(hmap, prefix + "-NER=" + NER)
    val DL = dt.depLabels.getOrElse(node, "") // state.startingDT.edgesToParents(sigma) map state.startingDT.arcs
    add(hmap, prefix + "-DL=" + DL)
    hmap

  }

}