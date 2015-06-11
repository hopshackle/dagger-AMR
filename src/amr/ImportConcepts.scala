package amr

import AMRGraph.importFile
/**
 * @author James
 */
object ImportConcepts {

  def loadConcepts(amrFile: String): Set[String] = {
    val allAMR = importFile(amrFile)
    (for {
      (sentence, amrString) <- allAMR
      concept <- AMRGraph(amrString, sentence).nodes.values
    } yield concept).toSet

  }

  def loadRelations(amrFile: String): Set[String] = {
    val allAMR = importFile(amrFile)
    (for {
      (sentence, amrString) <- allAMR
      relation <- AMRGraph(amrString, sentence).arcs.values
    } yield relation).toSet

  }
}