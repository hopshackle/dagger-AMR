
package amr

import edu.cmu.lti.nlp.amr._

// TODO: Currently the only difference between AMRGraph and DependencyTree is the use of String or Int as keys
// This derives from use of String in JAMR code I'm using. Might be better to merge these two case classes.
case class AMRGraph(nodes: Map[String, String], nodeSpans: Map[String, (Int, Int)], arcs: Map[(String, String), String]) {}

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
    val newNodes = nodes + (node -> label)
    this.copy(nodes = newNodes)
  }

  def removeNode(node: Int): DependencyTree = {
    // only valid for a leaf node with no children
    assert(!(arcs.keys exists (_ match { case (f, t) => f == node })))
    this.copy(nodes = nodes - node)
  }

}

case class Sentence(rawText: String, dependencyTree: DependencyTree, amr: Option[AMRGraph]) {

  val positionToAMR = amr match {
    case None => Map[Int, String]()
    case Some(amrGraph) => for {
      //TODO: Problem is that JAMR labels all of the nodes in the AMR - including sub-nodes.
      // This means we have duplicates for any given position - and we really just want the top-most one
      (amrKey, (start, end)) <- amrGraph.nodeSpans
      i <- start until end
    } yield (i -> amrKey)
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
    } yield (nodeId -> (span.start+1, span.end+1))).toMap
    // Note our convention is that the first word in a sentence is at index 1

    val Relation = """:?(.*)""".r
    val arcs = (for {
      node1 <- amr.nodes
      (label, node2) <- node1.relations
      Relation(relation) = label // label includes the ":"
    } yield ((node1.id, node2.id) -> relation)).toMap + ((amr.root.id, "ROOT") -> "ROOT")

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