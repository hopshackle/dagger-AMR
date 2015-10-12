package amr
import java.io._
import dagger.core._

object AMROutput {
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
    val firstNode = sample.getRoots.head
    (printNode(firstNode, sample, processedNodes, 0)._1) toString
  }
  
  def printNode(node: String, sample: AMRGraph, processedNodes: Seq[String], tab: Integer): (StringBuffer, Seq[String]) = {
    val output = new StringBuffer("( " + node )
    var newlyProcessedNodes = Seq(node)
    if (processedNodes contains node) 
      output.append(")")
    else {
      output.append(" / " + sample.nodes(node))
      for ((_, child) <- sample.edgesToChildren(node)) {
        val relation = sample.arcs((node, child))
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
}