
package amr

import edu.cmu.lti.nlp.amr._

// TODO: Currently the only difference between AMRGraph and DependencyTree is the use of String or Int as node keys
// This derives from use of String in the raw AMR input files. Might be better to merge these two case classes.
case class AMRGraph(nodes: Map[String, String], nodeSpans: Map[String, (Int, Int)], arcs: List[(String, String, String)]) {}

case class DependencyTree(nodes: Map[Int, String], nodeSpans: Map[Int, (Int, Int)], arcs: List[(Int, Int, String)]) {

  def toOutputFormat: String = {
    val nodeOutput = nodes.keys.toList map (x => s"# ::node\t${x}\t${nodes(x)}\t${nodeSpans(x)._1}\t${nodeSpans(x)._2}\n")
    val arcOutput = arcs map (x => s"# ::edge\t${x._1}\t${x._2}\t${x._3}\n")

    "# ::SpanGraph\n" + nodeOutput.mkString + arcOutput.mkString
  }
}

case class Sentence(rawText: String, dependencyTree: DependencyTree, amr: Option[AMRGraph]) {}

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

    val arcs = for {
      ConllToken(Some(index), Some(form), lemma, pos, cpos, feats, Some(parentIndex), deprel, phead, pdeprel) <- parseTree
      if deprel.getOrElse("") != "punct"
    } yield (parentIndex, index, deprel.getOrElse("UNK"))

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
    } yield (nodeId -> (span.start, span.end))).toMap
    
    val Relation = """:?(.*)""".r
    val arcs = (amr.root.id, "ROOT", "ROOT") :: (for {
      node1 <- amr.nodes
      (label, node2) <- node1.relations
      Relation(relation) = label // label includes the ":"
    } yield (node1.id, node2.id, relation)).toList

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