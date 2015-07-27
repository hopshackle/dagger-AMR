package amr
import java.io._
import dagger.core._


case class GraphViz(outputFile: String, graph: AMRGraph) {
  val quote = """""""
  val bigQ = """"""
  def clean = """[.#'-]""".r
  val file = new FileWriter(outputFile, false)
  val string = new StringBuffer("digraph G {\n")
  // We replace . with - due to a graphviz issue that incorrectly parses labels with more than one .
  string.append((graph.nodes map { case (ref, concept) => clean.replaceAllIn(ref, "") + " [label = " +  clean.replaceAllIn(concept, "") + "]" }).mkString("\n"))
  string.append("\n")
  string.append((graph.arcs map { case ((from, to), label) => clean.replaceAllIn(from, "") +  " -> " + clean.replaceAllIn(to, "") + " [label = " + clean.replaceAllIn(label, "") + "]" }).mkString("\n"))
  string.append("\n}\n")
  file.write(string.toString)
  file.close()
}

object GraphViz {
  def graphVizOutputFunction(options: DAGGEROptions, text: String, prediction: Sentence): Unit = {
    val outputFile = options.DAGGER_OUTPUT_PATH + "AMRPrediction_" + text + ".txt"
    prediction.amr match {
      case Some(t) => GraphViz(outputFile, t)
      case None =>
    }
  }
}