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
    fileP.close
    val fileT = new FileWriter(outputFileT, true)
    fileT.write(asStringT + "\n\n")
    fileT.close
    val smatchFile = new FileWriter(summaryFile, true)
    smatchFile.write(f"${smatchScore._1}%.3f \t ${smatchScore._2}%.3f \t ${smatchScore._3}%.3f \t ${target.rawText}\n")
    smatchFile.close
  }

  def convertToString(inputGraph: AMRGraph): String = {
    val sample = Smatch.reduceAMR(inputGraph)
    val processedNodes: Set[String] = Set.empty[String]
    val output = sample.getRoots map { c => printNode(c, sample, processedNodes, 0)._1 }
    output.mkString("\n")
  }

  def printNode(node: String, amr: AMRGraph, processedNodes: Set[String], tab: Integer): (StringBuffer, Set[String]) = {
    (processedNodes contains node) match {
      case true => (new StringBuffer(" " + nodeCode(node) + " "), processedNodes)
      case false =>
        var newlyProcessedNodes = Seq(node)
        val outString = {
          val attributes = amr.attributes filter { case (attributeNode, label, value) => attributeNode == node && label != "ROOT"} sortBy (_._2)
          val attributeString = attributes map { a => ":" + a._2 + " " + a._3 } mkString (" ")
          val output = new StringBuffer("( " + nodeCode(node))
          output.append(" / " + amr.nodes(node) + " " + attributeString)
          for ((_, child) <- amr.edgesToChildren(node).sortWith(sortByPositionInSentence(amr))) {
            val relation = amr.arcs((node, child))
            output.append("\n")
            for (i <- 0 to tab) output.append("\t")
            val (prettyStuff, nodesUsed) = printNode(child, amr, processedNodes ++ newlyProcessedNodes, tab + 1)
            output.append(":" + relation + " " + prettyStuff.toString)
            newlyProcessedNodes = newlyProcessedNodes ++ nodesUsed
          }
          output.append(")")
        }
        (outString, processedNodes ++ newlyProcessedNodes)
    }

    //  val arcsToUse = if (amr.originalArcs.isEmpty) amr.arcs else amr.originalArcs 
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