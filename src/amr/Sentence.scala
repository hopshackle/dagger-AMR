
package amr

import edu.cmu.lti.nlp.amr._
import scala.collection.SortedMap
import amr.ImportConcepts.{ concept, relation }

// TODO: Currently the only difference between AMRGraph and DependencyTree is the use of String or Int as keys
// This derives from use of String in JAMR code I'm using. Might be better to merge these two case classes.
case class AMRGraph(nodes: Map[String, String], nodeSpans: Map[String, (Int, Int)], arcs: Map[(String, String), String]) {
  def toOutputFormat: String = {
    val nodeOutput = nodes.keys.toList map (x => s"# ::node\t${x}\t${nodes(x)}\n")
    val arcOutput = arcs map (x => s"# ::edge\t${x._1._1}\t${x._1._2}\t${x._2}\n")

    "# ::AMRGraph\n" + nodeOutput.mkString + arcOutput.mkString
  }
}

case class DependencyTree(nodes: Map[Int, String], nodeSpans: Map[Int, (Int, Int)], arcs: Map[(Int, Int), String]) {

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
    val newLabel = if (label == "UNKNOWN") oldValue else label
    val newNodes = nodes + (node -> newLabel)
    this.copy(nodes = newNodes)
  }

  def removeNode(node: Int): DependencyTree = {
    // only valid for a leaf node with no children
    assert(isLeafNode(node))
    val edgesToRemove = edgesToParents(node)
    DependencyTree(nodes - node, nodeSpans - node, arcs -- edgesToRemove)
  }

  def insertNodeAbove(node: Int, conceptIndex: Int): (Int, DependencyTree) = {
    // So we to add a new node as a parent, remove the edge from the current node to its current parent, and insert two new edges
    // ...parent to new, and new to current
    // we assign a nodeSpan to the new node to match its child
    val newNode = nodes.keys.max + 1
    val childSpan = nodeSpans.getOrElse(node, (0, 0))
    val newEdgesFromParent = edgesToParents(node) map { case (from, to) => ((from, newNode), arcs((from, to))) }
    val newEdgeFromNode = ((newNode, node), concept(conceptIndex) + "#") // dependency label made up for use as feature
    (newNode, DependencyTree(nodes + (newNode -> concept(conceptIndex)), nodeSpans + (newNode -> childSpan),
      arcs -- edgesToParents(node) ++ newEdgesFromParent + newEdgeFromNode))
  }

  def edgesToParents(node: Int): List[(Int, Int)] = (arcs filter (x => x match { case ((p, c), l) => c == node })).keys.toList
  def edgesToChildren(node: Int): List[(Int, Int)] = (arcs filter (x => x match { case ((p, c), l) => p == node })).keys.toList

  override def toString: String = {
    val nodeSort = nodes.foldLeft(SortedMap[Int, String]()) { case (start, (a, b)) => start + (a -> b) }
    val spanSort = nodeSpans.foldLeft(SortedMap[Int, (Int, Int)]()) { case (start, (a, b)) => start + (a -> b) }
    val edgeSort = arcs.foldLeft(SortedMap[(Int, Int), String]()) { case (start, (a, b)) => start + (a -> b) }
    "\nNodeMap:\t" + nodeSort.toString +
      "\nSpanMap:\t" + spanSort.toString +
      "\nEdges:\t" + edgeSort.toString
  }

  def toAMR: AMRGraph = {
    val amrNodes = nodes map { case (key: Int, value: Any) => (key.toString -> value) }
    val amrNodeSpan = nodeSpans map { case (key: Int, value: Any) => (key.toString -> value) }
    val amrArcs = arcs map { case (key: (Int, Int), value: Any) => ((key._1.toString, key._2.toString) -> value) }
    AMRGraph(amrNodes, amrNodeSpan, amrArcs)
  }

  def isLeafNode(node: Int): Boolean = { !(arcs.keys exists (_ match { case (f, t) => f == node })) }

}

case class Sentence(rawText: String, dependencyTree: DependencyTree, amr: Option[AMRGraph]) {

  val positionToAMR = amr match {
    case None => Map[Int, String]()
    case Some(amrGraph) => for {
      //TODO: Problem is that JAMR labels all of the nodes in the AMR - including sub-nodes.
      // This means we have duplicates for any given position - and we really just want the top-most one
      // This uses the useful fact that the initial sentence parse assigns the keys to each word based
      // purely on their position in the sentence after stripping out punctuation...so the first word is 1 etc. 
      (amrKey, (start, end)) <- amrGraph.nodeSpans
      i <- start until end
    } yield (i -> amrKey)
  }
  // Having mapped purely on the basis of nodeSpans - we can now be a little cleverer 
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
      val i = start until end
    } yield (allAMRWithSameSpan(amrKey), i)
  }

  def allAMRWithSameSpan(amrKey: String): Seq[String] = {
    val amrNodes = amr.get.nodes.keys
    val amrNodeSpans = amr.get.nodeSpans
    val sameSpans = amrNodes filter (amrNodeSpans.getOrElse(_, (0, 0)) == amrNodeSpans(amrKey))
    sameSpans.toSeq
  }

  val nextBit = (amrToWordIndices map {
    case (amrKeys, wordIndices) => mapAMRtoDTNodes(amrKeys, wordIndices)
  }).flatten.toMap

  def mapAMRtoDTNodes(amrKeys: Seq[String], wordIndices: Seq[Int]): Map[Int, String] = {
    // TODO: Use rockymadden/stringmetrics here!
    val amrNodes = amr.get.nodes
    val t = for {
      amrKey <- amrKeys
      val amrConcept = amrNodes(amrKey).toLowerCase.replaceAll("""[^0-9a-zA-Z\-' ]""", "")
      word <- wordIndices
      val dtWord = dependencyTree.nodes(word).toLowerCase.replaceAll("""[^0-9a-zA-Z\-' ]""", "")
      if (dtWord == amrConcept)
    } yield 0
    Map[Int, String]()
  }
  val mapFromDTtoAMR = {
    for {
      (dtSpan, (position, _)) <- dependencyTree.nodeSpans
    } yield (dtSpan -> positionToAMR.getOrElse(position, "NONE"))
  }
}

object Sentence {
  def apply(sentence: String): Sentence = {
    Sentence(sentence, DependencyTree(sentence), None)
  }
  def apply(sentence: String, rawAMR: String): Sentence = {
    Sentence(sentence, DependencyTree(sentence), Some(AMRGraph(rawAMR, sentence)))
  }
}

object DependencyTree {

  val processor = new StanfordProcessor

  def apply(sentence: String): DependencyTree = {

    val parseTree = processor.parse(sentence).head

    val nodes = (for {
      ConllToken(Some(index), Some(form), lemma, pos, cpos, feats, Some(parentIndex), Some(deprel), phead, pdeprel) <- parseTree
      if deprel != "punct"
    } yield (index -> form)).toMap + (0 -> "ROOT")

    val arcs = (for {
      ConllToken(Some(index), Some(form), lemma, pos, cpos, feats, Some(parentIndex), deprel, phead, pdeprel) <- parseTree
      if deprel.getOrElse("") != "punct"
    } yield ((parentIndex, index) -> deprel.getOrElse("UNK"))).toMap

    val nodeSpans = (for {
      (ConllToken(Some(index), Some(form), lemma, pos, cpos, feats, Some(parentIndex), deprel, phead, pdeprel), wordCount) <- (parseTree.filter(x => x.deprel.getOrElse("") != "punct").zipWithIndex)
    } yield (index -> (wordCount + 1, wordCount + 2))).toMap

    DependencyTree(nodes, nodeSpans, arcs)
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
    } yield ((node1.id, node2.id) -> relation2)).toMap + ((amr.root.id, "ROOT") -> "ROOT")

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