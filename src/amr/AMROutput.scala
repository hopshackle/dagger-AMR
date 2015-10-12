package amr
import java.io._
import dagger.core._

object AMROutput {
    val numbers = "[0-9]".r
          val quote = """""""
  
  def AMROutputFunction(options: DAGGEROptions, text: String, i: Integer, prediction: Sentence): Unit = {
    val outputFile = options.DAGGER_OUTPUT_PATH + "AMR_" + text + ".txt"
    val asString = prediction.amr match {
      case Some(t) => convertToString(t)
      case None => ""
    }
  val file = new FileWriter(outputFile, true)
  file.write(asString + "\n\n")
  file.close()
  }
  
  def convertToString(sample: AMRGraph): String = {
    val processedNodes: Seq[String] = Seq.empty[String]
    val root = sample.getRoots.head
    val output = sample.childrenOf(root) map {c => printNode(c, sample, processedNodes, 0)._1}
    output.mkString("\n")
  }

  def printNode(node: String, sample: AMRGraph, processedNodes: Seq[String], tab: Integer): (StringBuffer, Seq[String]) = {
    val isNumeric = numbers.replaceAllIn(sample.nodes(node), "") == ""
    val isQuoted = sample.nodes(node).contains(quote)
    val isLeaf = sample.isLeafNode(node)
    if (isLeaf && (isNumeric || isQuoted))
      return (new StringBuffer(sample.nodes(node)), processedNodes)

    val output = new StringBuffer("( " + node)
    var newlyProcessedNodes = Seq(node)
    if (processedNodes contains node)
      output.append(")")
    else {
      output.append(" / " + sample.nodes(node))
      var opCount = 0
      for ((_, child) <- sample.edgesToChildren(node).sortWith(sortByPositionInSentence(sample))) {
        val relation = if (sample.arcs((node, child)) != "opN")
          sample.arcs((node, child))
        else {
          opCount += 1
          "op" + opCount
        }
        output.append("\n")
        for (i <- 0 to tab) output.append("\t")
        val (prettyStuff, nodesUsed) = printNode(child, sample, processedNodes :+ node, tab + 1)
        output.append(":" + relation + " " + prettyStuff.toString)
        newlyProcessedNodes = newlyProcessedNodes ++ nodesUsed
      }
      output.append(")")
    }
    (output, processedNodes ++ newlyProcessedNodes)
  }
  
  def sortByPositionInSentence(amr: AMRGraph)(a: (String, String), b: (String, String)): Boolean = {
    val posA = amr.nodeSpans(a._2)._1
    val posB = amr.nodeSpans(b._2)._1
    posA < posB
  }
}