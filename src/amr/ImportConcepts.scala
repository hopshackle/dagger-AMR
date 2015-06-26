package amr

import AMRGraph.importFile
import scala.io.Source
import java.io._
/**
 * @author James
 */
object ImportConcepts {
  // Bit of a hack - we relay on initialise(fileName) being called as part of set up so that when we start the run
  // the lazy evaluations of the core concepts doesn't use the default amrFile setting

  var amrFile: String = "C:\\AMR\\AMR2.txt"
  val quote = """"""".r
  val numbers = "[0-9.,]".r

  lazy val relationStrings = loadRelations(amrFile)
  lazy val relationMaster = (for {
    (relation, index) <- relationStrings zipWithIndex
  } yield ((index + 1) -> relation)).toMap + (0 -> "UNKNOWN")
  lazy val relationStringToIndex = relationMaster map (_ match { case (index, text) => (text -> index) })

  lazy val conceptStrings = loadConcepts(amrFile)
  lazy val conceptMaster = (for {
    (concept, index) <- conceptStrings zipWithIndex
  } yield ((index + 1) -> concept)).toMap + (0 -> "UNKNOWN")
  lazy val conceptStringToIndex = conceptMaster map (_ match { case (index, text) => (text -> index) })

  lazy val insertableConcepts = loadInsertableConcepts(amrFile)

  def initialise(fileName: String): Unit = {
    amrFile = fileName
  }

  def concept(index: Int): String = conceptMaster.getOrElse(index, "UNKNOWN")
  def relation(index: Int): String = relationMaster.getOrElse(index, "UNKNOWN")
  def conceptIndex(value: String) = conceptStringToIndex.getOrElse(value, 0)
  def relationIndex(value: String) = relationStringToIndex.getOrElse(value, 0)

  private def loadConcepts(amrFile: String): Set[String] = {
    val allAMR = importFile(amrFile)
    (for {
      (sentence, amrString) <- allAMR
      concept <- AMRGraph(amrString, sentence).nodes.values
      //      if (quote findFirstIn concept) == None  // ignore anything with quotes
      if numbers.replaceAllIn(concept, "") != "" // and anything that is purely numeric
    } yield concept).toSet

  }

  private def loadRelations(amrFile: String): Set[String] = {
    val allAMR = importFile(amrFile)
    (for {
      (sentence, amrString) <- allAMR
      relation <- AMRGraph(amrString, sentence).arcs.values
    } yield relation).toSet

  }

  private def loadInsertableConcepts(amrFile: String): Set[String] = {
    val conceptFileExists = {
      try {
        val fr = new FileReader(amrFile + "_ic")
        true
      } catch {
        case e: FileNotFoundException => false
      }
    }

    if (!conceptFileExists) {
      val expert = new WangXueExpert
      val allAMR = importFile(amrFile)
      val insertableConcepts = (for {
        (sentence, amrString) <- allAMR
        val s = Sentence(sentence, amrString)
        val processedSentence = RunDagger.sampleTrajectory(s, "", expert)
        (dt, amr) <- processedSentence.dependencyTree.insertedNodes
        val name = s.amr.get.nodes(amr)
      } yield name).toSet
      val fw = new FileWriter(amrFile + "_ic")
      insertableConcepts foreach (x => fw.write(x + "\n"))
      fw.close
      insertableConcepts
    } else {
      Source.fromFile(amrFile + "_ic").getLines().toSet
    }
  }
}