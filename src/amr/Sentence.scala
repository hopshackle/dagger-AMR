
package amr

import edu.cmu.lti.nlp.amr._
import scala.collection.SortedMap
import amr.ImportConcepts.{ concept, relation }

abstract class Graph[K] {
  def nodes: Map[K, String]
  def nodeSpans: Map[K, (Int, Int)]
  def arcs: Map[(K, K), String]
  def isRoot(node: K): Boolean = edgesToParents(node).isEmpty

  def depth(node: K): Int = {
    def depthHelper(nodes: List[K], accum: Int): Int = {
      val nextNodes = (nodes map edgesToParents flatten) map { case (parent, child) => parent }
      if (nextNodes exists isRoot) accum else depthHelper(nextNodes, accum + 1)
    }
    if (isRoot(node)) 0 else depthHelper(List(node), 1)
  }
  def edgesToParents(node: K): List[(K, K)] = (arcs filter (x => x match { case ((p, c), l) => c == node })).keys.toList filter (_ != node)
  def parentsOf(node: K): List[K] = edgesToParents(node) map { case (p, c) => p }
  def parentLabels(node: K): List[String] = (parentsOf(node) map (x => nodes(x))).toList
  def childrenOf(node: K): List[K] = edgesToChildren(node) map { case (p, c) => c }
  def labelsBetween(parent: K, child: K): List[String] = (arcs filter { case ((p, c), l) => p == parent && c == child } map { case ((p, c), l) => l }).toList
  def edgesToChildren(node: K): List[(K, K)] = (arcs filter (x => x match { case ((p, c), l) => p == node })).keys.toList filter (_ != node)
  def isLeafNode(node: K): Boolean = { !(arcs.keys exists (_ match { case (f, t) => f == node })) }

}

case class AMRGraph(nodes: Map[String, String], nodeSpans: Map[String, (Int, Int)], arcs: Map[(String, String), String]) extends Graph[String] {
  def toOutputFormat: String = {
    val nodeOutput = nodes.keys.toList map (x => s"# ::node\t${x}\t${nodes(x)}\n")
    val arcOutput = arcs map (x => s"# ::edge\t${x._1._1}\t${x._1._2}\t${x._2}\n")

    "# ::AMRGraph\n" + nodeOutput.mkString + arcOutput.mkString
  }
}

case class DependencyTree(nodes: Map[Int, String], nodeLemmas: Map[Int, String], nodePOS: Map[Int, String], nodeNER: Map[Int, String], nodeSpans: Map[Int, (Int, Int)], arcs: Map[(Int, Int), String],
  insertedNodes: Map[Int, String], mergedNodes: Map[Int, List[(Int, String)]], swappedArcs: Set[(Int, Int)]) extends Graph[Int] {
  val numbers = "[0-9.,]".r

  def toOutputFormat: String = {
    val nodeOutput = nodes.keys.toList map (x => s"# ::node\t${x}\t${nodes(x)}\t${nodeSpans(x)._1}\t${nodeSpans(x)._2}\n")
    val arcOutput = arcs map (x => s"# ::edge\t${x._1._1}\t${x._1._2}\t${x._2}\n")

    "# ::SpanGraph\n" + nodeOutput.mkString + arcOutput.mkString
  }

  def labelArc(parent: Int, child: Int, label: String): DependencyTree = {
    this.copy(arcs = arcs + ((parent, child) -> label))
  }

  def labelNode(node: Int, label: String): DependencyTree = {
    // We account for the fact that if we have no idea of the concept, then using the actual word might just work
    val oldValue = nodes.getOrElse(node, "UNKNOWN")
    val newLabel = if (label == "UNKNOWN") {
      //      if (numbers.replaceAllIn(oldValue, "") == "") oldValue // purely numeric
      //     else """"""" + oldValue + """"""" // add quotes as we assume this is a name
      oldValue
    } else label
    val newNodes = nodes + (node -> newLabel)
    this.copy(nodes = newNodes)
  }

  def removeNode(node: Int): DependencyTree = {
    // only valid for a leaf node with no children
    assert(isLeafNode(node))
    val edgesToRemove = edgesToParents(node)
    this.copy(nodes = this.nodes - node, nodeSpans = this.nodeSpans - node, arcs = this.arcs -- edgesToRemove)
  }

  def insertNodeAbove(node: Int, conceptIndex: Int, otherRef: String): (Int, DependencyTree) = {
    // So we to add a new node as a parent, remove the edge from the current node to its current parent, and insert two new edges
    // ...parent to new, and new to current
    // we assign a nodeSpan to the new node to match its child
    val newNode = nodes.keys.max + 1
    val childSpan = nodeSpans.getOrElse(node, (0, 0))
    val newEdgesFromParent = edgesToParents(node) map { case (from, to) => ((from, newNode), arcs((from, to))) }
    val newInsertedNodes = this.insertedNodes + (newNode -> otherRef)
    val newEdgeFromNode = ((newNode, node), concept(conceptIndex) + "#") // dependency label made up for use as feature
    (newNode, this.copy(nodes = this.nodes + (newNode -> concept(conceptIndex)), nodeSpans = this.nodeSpans + (newNode -> childSpan),
      arcs = this.arcs -- edgesToParents(node) ++ newEdgesFromParent + newEdgeFromNode, insertedNodes = newInsertedNodes))
  }

  def mergeNodes(nodeToRemove: Int, nodeToKeep: Int): DependencyTree = {
    val newEdgesIn = edgesToParents(nodeToRemove) map { case (from, to) => ((from, nodeToKeep), arcs((from, to))) }
    val newEdgesOut = edgesToChildren(nodeToRemove) map { case (from, to) => ((nodeToKeep, to), arcs((from, to))) }
    val oldEdges = edgesToParents(nodeToRemove) ++ edgesToChildren(nodeToRemove)
    val newSpans = this.nodeSpans - nodeToRemove + (nodeToKeep -> mergedSpan(nodeToRemove, nodeToKeep))
    val alreadyMergedNodes = this.mergedNodes.get(nodeToKeep) match { case None => List(); case Some(mergedNodes) => mergedNodes }
    val newMergedNodes = (nodeToRemove, this.nodes(nodeToRemove)) :: alreadyMergedNodes
    this.copy(nodes = this.nodes - nodeToRemove, nodeSpans = newSpans, arcs = this.arcs -- oldEdges ++ newEdgesIn ++ newEdgesOut - ((nodeToKeep, nodeToKeep)),
      mergedNodes = this.mergedNodes + (nodeToKeep -> newMergedNodes))
  }

  def swapArc(currentParent: Int, currentChild: Int): DependencyTree = {
    val currentLabel = labelsBetween(currentParent, currentChild) match {
      case Nil => assert(false, "No such arc to swap in DependencyTree.swapArc " + currentParent + " -> " + currentChild); "NONE"
      case head :: tail => head
    }
    this.copy(arcs = this.arcs - ((currentParent, currentChild)) + ((currentChild, currentParent) -> currentLabel), 
        swappedArcs = this.swappedArcs + ((currentParent, currentChild)))
  }

  def mergedSpan(node1: Int, node2: Int): (Int, Int) = {
    val (s1, e1) = nodeSpans.getOrElse(node1, (0, 0))
    val (s2, e2) = nodeSpans.getOrElse(node2, (0, 0))
    val s = Math.min(if (s1 == 0) 999 else s1, if (s2 == 0) 999 else s2)
    val e = Math.max(if (s1 == 0) 999 else s1, if (s2 == 0) 999 else s2)
    (if (s == 999) 0 else s, if (e == 999) 0 else e)
  }

  override def toString: String = {
    val nodeSort = nodes.foldLeft(SortedMap[Int, String]()) { case (start, (a, b)) => start + (a -> b) }
    val spanSort = nodeSpans.foldLeft(SortedMap[Int, (Int, Int)]()) { case (start, (a, b)) => start + (a -> b) }
    val edgeSort = arcs.foldLeft(SortedMap[(Int, Int), String]()) { case (start, (a, b)) => start + (a -> b) }
    "\nNodeMap:\t" + nodeSort.toString +
      "\nSpanMap:\t" + spanSort.toString +
      "\nEdges:\t" + edgeSort.toString +
      "\nInsertedNodes:\t" + insertedNodes.toString +
      "\nMergedNodes:\t" + mergedNodes.toString +
      "\nSwappedArcs:\t" + swappedArcs.toString
  }

  def toAMR: AMRGraph = {
    //      val amrNodes: Map[String, String] = (nodes map {
    //   case (key, value) => if (parentLabels(key) contains "name") { (key.toString -> """"""" + value + """"""") }
    //   else { (key.toString -> value) }.toMap
    //  }
    val amrNodes = nodes map { case (key: Int, value: Any) => (key.toString -> value) }
    val amrNodeSpan = nodeSpans map { case (key: Int, value: Any) => (key.toString -> value) }
    val amrArcs = arcs map { case (key: (Int, Int), value: Any) => ((key._1.toString, key._2.toString) -> value) }
    AMRGraph(amrNodes, amrNodeSpan, amrArcs)
  }
}

case class Sentence(rawText: String, dependencyTree: DependencyTree, amr: Option[AMRGraph], positionToAMR: Map[Int, String]) {
  val AMRToPosition: Map[String, Int] = positionToAMR map { case (i, s) => (s -> i) }
  def unmatchedAMRNodesByConcept: Map[String, String] = amr match {
    case None => Map()
    case Some(graph) =>
      val filterForInitialMatches = graph.nodes filter {
        case (key, concept) =>
          !(AMRToPosition contains key)
      }
      val filterForInsertedMatches = filterForInitialMatches filter {
        case (key, concept) =>
          !(dependencyTree.insertedNodes.values.toList contains key)
      }
      val finalResult = filterForInsertedMatches map { case (key, concept) => (concept -> key) }
      finalResult
  }
}

object Sentence {
  val debug = false
  def apply(sentence: String): Sentence = {
    Sentence(sentence, DependencyTree(sentence), None, Map[Int, String]())
  }
  def apply(sentence: String, rawAMR: String): Sentence = {
    Sentence(sentence, DependencyTree(sentence), Some(AMRGraph(rawAMR, sentence)))
  }
  def apply(sentence: String, dt: DependencyTree, amr: Option[AMRGraph]): Sentence = {
    Sentence(sentence, dt, amr, positionToAMR(amr, dt))
  }

  def positionToAMR(amr: Option[AMRGraph], dt: DependencyTree): Map[Int, String] = {
    // We run through each set of AMR nodes - i.e. that share a NodeSpan
    // We then get all the DT nodes for those positions
    // If 1:1 then we're done
    // If 1:0 (i.e. AMR has nodeSpan of 0, 0) then we're also done [no entry in Map]
    // If m:n then:
    //   Find good text matches between AMR concept and word or lemma in DT node and assign greedily.
    //   For those left over: 
    //      m > n : unassigned AMR nodes mapped to random top-most DT nodes. Surplus DT nodes not entered into map
    //      n > m : unassigned DT nodes mapped to random bottom-most AMR nodes. Surplus AMR nodes not entered into map

    val amrToWordIndices: Map[Seq[String], Seq[Int]] = amr match {
      case None => Map[Seq[String], Seq[Int]]()
      case Some(amrGraph) => for {
        (amrKey, (start, end)) <- amrGraph.nodeSpans
        val allDTIndices = dt.nodeSpans filter { case (_, (wordPos, _)) => (start until end) contains wordPos } map { case (index, (wp, _)) => index }
      } yield (allAMRWithSameSpan(amrKey, amr), allDTIndices.toSeq)
    }
    (amrToWordIndices map { case (amrKeys, wordIndices) => mapAMRtoDTNodes(amrKeys, wordIndices, amr, dt) }).flatten.toMap + (0 -> "ROOT")
  }

  def allAMRWithSameSpan(amrKey: String, amr: Option[AMRGraph]): Seq[String] = {
    val amrNodes = amr.get.nodes.keys
    val amrNodeSpans = amr.get.nodeSpans
    val sameSpans = amrNodes filter (amrNodeSpans.getOrElse(_, (0, 0)) == amrNodeSpans(amrKey))
    sameSpans.toSeq
  }

  def mapAMRtoDTNodes(amrKeys: Seq[String], wordIndices: Seq[Int], amr: Option[AMRGraph], dependencyTree: DependencyTree): Map[Int, String] = {
    // TODO: Use rockymadden/stringmetrics here!
    val amrNodes = amr.get.nodes
    if (debug) println(amrKeys)
    if (debug) println(wordIndices)
    val greedyMatch = (for {
      amrKey <- amrKeys
      val amrConcept = amrNodes(amrKey).toLowerCase.replaceAll("""[^0-9a-zA-Z\-' ]""", "")
      word <- wordIndices
      val dtWord = dependencyTree.nodes(word).toLowerCase.replaceAll("""[^0-9a-zA-Z\-' ]""", "")
      if (dtWord == amrConcept)
    } yield (word -> amrKey)).toMap
    if (debug) println("Greedy mapping: " + greedyMatch)
    // At this stage we have mapped any DT to AMR nodes with an exact String match
    // We then select the topmost DT nodes that are required to match the remaining unmapped AMR nodes
    // (up to all of them, with 'topmost' defined as being closest to root of DT)
    val unmappedAMRInDecreasingDepthOrder = amrKeys diff greedyMatch.values.toSeq sortWith (amr.get.depth(_) > amr.get.depth(_))
    if (debug && unmappedAMRInDecreasingDepthOrder.size > 0) println("AMR Dec: " + unmappedAMRInDecreasingDepthOrder)
    val unmappedDTInIncreasingDepthOrder = wordIndices diff greedyMatch.keys.toSeq sortWith (dependencyTree.depth(_) < dependencyTree.depth(_))
    if (debug && unmappedDTInIncreasingDepthOrder.size > 0) println("DT Inc: " + unmappedDTInIncreasingDepthOrder)
    val randomMatch = (unmappedDTInIncreasingDepthOrder zip unmappedAMRInDecreasingDepthOrder).toMap
    // we then concatenate the greedy and random matches, plus a hard-coded mapping of the Root nodes
    if (debug) println("Random mapping: " + randomMatch)
    greedyMatch ++ randomMatch
  }
}

object DependencyTree {

  val processor = new StanfordProcessor

  def apply(sentence: String): DependencyTree = {

    val parseTree = (processor.parse(sentence).head) filter (x => x.deprel.getOrElse("") != "punct") zipWithIndex

    val nodes = (for {
      (ConllToken(Some(index), Some(form), lemma, pos, cpos, feats, Some(parentIndex), Some(deprel), phead, ner), wordCount) <- parseTree
      if deprel != "punct"
    } yield (index -> form)).toMap + (0 -> "ROOT")

    val arcs = (for {
      (ConllToken(Some(index), _, lemma, pos, cpos, feats, Some(parentIndex), deprel, phead, ner), wordCount) <- parseTree
      if deprel.getOrElse("") != "punct"
    } yield ((parentIndex, index) -> deprel.getOrElse("UNK"))).toMap

    val nodeSpans = (for {
      (ConllToken(Some(index), _, lemma, pos, cpos, feats, _, deprel, phead, ner), wordCount) <- parseTree
    } yield (index -> (wordCount + 1, wordCount + 2))).toMap

    val nodeLemmas = (for {
      (ConllToken(Some(index), Some(form), Some(lemma), pos, cpos, feats, Some(parentIndex), deprel, phead, ner), wordCount) <- parseTree
    } yield (index -> lemma)).toMap

    val nodePOS = (for {
      (ConllToken(Some(index), _, _, Some(pos), cpos, feats, _, deprel, phead, ner), wordCount) <- parseTree
    } yield (index -> pos)).toMap

    val nodeNER = (for {
      (ConllToken(Some(index), _, _, pos, cpos, feats, _, deprel, phead, Some(ner)), wordCount) <- parseTree
    } yield (index -> ner)).toMap

    DependencyTree(nodes, nodeLemmas, nodePOS, nodeNER, nodeSpans, arcs, Map(), Map(), Set())
  }
}

object AMRGraph {
  // We then use the JAMR functionality here
  // 
  def apply(rawAMR: String, sentence: String): AMRGraph = {
    val amr = Graph.parse(rawAMR)
    val tokenized = sentence.split(" ")
    val wordAlignments = AlignWords.alignWords(tokenized, amr)
    val spanAlignments = AlignSpans.alignSpans(tokenized, amr, wordAlignments)

    val nodes = amr.nodes.map(node => (node.id -> node.concept)).toMap + ("ROOT" -> "ROOT")

    val nodeSpans = (for {
      span <- amr.spans
      nodeId <- span.nodeIds
    } yield (nodeId -> (span.start + 1, span.end + 1))).toMap
    // Note our convention is that the first word in a sentence is at index 1

    val Relation = """:?(.*)""".r
    val arcs = (for {
      node1 <- amr.nodes
      (label, node2) <- node1.relations
      Relation(relation) = label // label includes the ":"
      relation2 = if (relation.size == 3 && relation.startsWith("op")) "opN" else relation
    } yield ((node1.id, node2.id) -> relation2)).toMap + (("ROOT", amr.root.id) -> "ROOT")

    AMRGraph(nodes, nodeSpans, arcs)
  }

  def importFile(fileName: String): IndexedSeq[(String, String)] = {
    val input = Source.fromFile(fileName).getLines().toList.map(_.trim)
    val sentenceIndices = for {
      (line, i) <- input.zipWithIndex
      if line.matches("^# ::snt .*")
    } yield i
    val startIndices = sentenceIndices.map(_ + 2)
    val endIndices = sentenceIndices.map(_ - 2).tail :+ (input.length - 1)

    for {
      i <- 0 until sentenceIndices.size
      val english = input(sentenceIndices(i)).substring(8)
      val amr = (for (j <- startIndices(i) to endIndices(i)) yield input(j)).toList.mkString(" ")
    } yield (english, amr)
  }

}