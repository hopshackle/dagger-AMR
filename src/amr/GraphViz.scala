package amr
import java.io._
import dagger.core._

case class blah(options: DAGGEROptions, iteration: Int, instance: Int, prediction: Sentence) {
  val outputFile = options.DAGGER_OUTPUT_PATH + "_AMRPrediction_" + iteration + "_" + instance + ".dot"
  prediction.amr match {
    case Some(t) => GraphViz(outputFile, t)
    case None => 
  }
}

case class GraphViz(outputFile: String, graph: AMRGraph) {
  
      val file = new FileWriter(outputFile, false)
      val string = new StringBuffer("digraph G {\n")
      string.append((graph.nodes map {case (ref, concept) => """"""" + ref + """"""" + """ [label = """" + concept.replaceAll(""""""", "") + """"]"""}).mkString("\n"))
      string.append("\n")
      string.append((graph.arcs map {case ((from, to), label) => """"""" + from + """"""" + " -> " + """"""" + to + """"""" + """ [label = """" + label + """"]"""}).mkString("\n"))
      string.append("\n}\n")
      file.write(string.toString)
      file.close()
  
}