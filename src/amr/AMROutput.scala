package amr
import java.io._
import dagger.core._

object AMROutput {
  val numbers = "[0-9]".r
  val quote = """""""

  def AMROutputFunction(options: DAGGEROptions, text: String, i: Integer, prediction: Sentence, target: Sentence): Unit = {
    val outputFileP = options.DAGGER_OUTPUT_PATH + "AMR_prediction_" + text + ".txt"
    val outputFileT = options.DAGGER_OUTPUT_PATH + "AMR_target_" + text + ".txt"
    val summaryFile = options.DAGGER_OUTPUT_PATH + "SmatchScores_" + text + ".txt"
    val asStringP = prediction.amr match {
      case Some(p) => convertToString(p)
      case None => ""
    }
    val asStringT = target.amr match {
      case Some(t) => convertToString(t)
      case None => ""
    }
    val smatchScore = (prediction.amr, target.amr) match {
      case (Some(p), Some(t)) => Smatch.fScore(p, t, 4, 10000)
      case _ => (0.0, 0.0, 0.0, 0.0, 0, 0, 0)
    }
    val fileP = new FileWriter(outputFileP, true)
    fileP.write(asStringP + "\n\n")
    fileP.close()
    val fileT = new FileWriter(outputFileT, true)
    fileT.write(asStringT + "\n\n")
    fileT.close()
    val smatchFile = new FileWriter(summaryFile, true)
    smatchFile.write(f"${smatchScore._1}%.3f \t ${smatchScore._2}%.3f \t ${smatchScore._3}%.3f \t ${target.rawText}\n")
    smatchFile.close
  }

  def convertToString(sample: AMRGraph): String = {
    val processedNodes: Set[String] = Set.empty[String]
    val output = sample.getRoots map { c => printNode(c, sample, processedNodes, 0)._1 }
    output.mkString("\n")
  }

  def printNode(node: String, sample: AMRGraph, processedNodes: Set[String], tab: Integer): (StringBuffer, Set[String]) = {
    val amr = sample
    val isNumeric = numbers.replaceAllIn(amr.nodes(node), "") == ""
    val isQuoted = amr.nodes(node).contains(quote)
    val isLeaf = amr.isLeafNode(node)
    if (isLeaf && (isNumeric || isQuoted))
      return (new StringBuffer(amr.nodes(node)), processedNodes)

    val arcsToUse = if (sample.originalArcs.isEmpty) amr.arcs else amr.originalArcs
    val output = new StringBuffer("( " + nodeCode(node))
    var newlyProcessedNodes = Seq(node)
    if (processedNodes contains node)
      output.append(")")
    else {
      output.append(" / " + amr.nodes(node))
      var opCount = 0
      for ((_, child) <- amr.edgesToChildren(node).sortWith(sortByPositionInSentence(sample))) {
        val relation = if (arcsToUse((node, child)) != "opN")
          arcsToUse((node, child))
        else {
          opCount += 1
          "op" + opCount
        }
        output.append("\n")
        for (i <- 0 to tab) output.append("\t")
        val (prettyStuff, nodesUsed) = printNode(child, sample, processedNodes ++ newlyProcessedNodes, tab + 1)
        output.append(":" + relation + " " + prettyStuff.toString)
        newlyProcessedNodes = newlyProcessedNodes ++ nodesUsed
      }
      output.append(")")
    }
    (output, processedNodes ++ newlyProcessedNodes)
  }

  def sortByPositionInSentence(amr: AMRGraph)(a: (String, String), b: (String, String)): Boolean = {
    val posA = amr.nodeSpans.getOrElse(a._2, (0, 0))._1
    val posB = amr.nodeSpans.getOrElse(b._2, (0, 0))._1
    posA < posB
  }

  def nodeCode(original: String): String = {
    var suffix = 1
    try {
      var asInteger = original.toInt
      while (asInteger > 26) {
        asInteger -= 26
        suffix += 1
      }
      (96 + asInteger).toChar.toString + (if (suffix > 1) suffix.toString else "")
    } catch {
      case _ => original
    }
  }
}