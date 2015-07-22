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
//  val commonLemmas = List("the", "and", "a", "in", "of", "to")
  val commonLemmas: List[String] = List()

  lazy val relationStrings = loadRelations + "polarity"
  lazy val relationMaster = (for {
    (relation, index) <- relationStrings zipWithIndex
  } yield ((index + 1) -> relation)).toMap + (0 -> "UNKNOWN")
  lazy val relationStringToIndex = relationMaster map (_ match { case (index, text) => (text -> index) })

  lazy val allSentencesAndAMR = importFile(amrFile)
  lazy val allAMR = allSentencesAndAMR map { case (sentence, amr) => AMRGraph(amr, sentence) }
  lazy val conceptStrings = loadConcepts + "-"
  lazy val conceptMaster = (for {
    (concept, index) <- conceptStrings zipWithIndex
  } yield ((index + 1) -> concept)).toMap + (0 -> "UNKNOWN")
  lazy val conceptStringToIndex = conceptMaster map (_ match { case (index, text) => (text -> index) })

  lazy val conceptsPerLemma = loadConceptsPerLemma
  lazy val universalConcepts = Set("and", "date-entity", "UNKNOWN", "ROOT", "name", "monetary-quantity") map conceptIndex
  lazy val universalRelations = Set("year", "month", "day", "UNKNOWN", "ROOT", "quant", "poss") map relationIndex
  lazy val edgesPerLemma = loadEdgesPerLemma

  lazy val insertableConcepts = loadInsertableConcepts

  def initialise(fileName: String): Unit = {
    amrFile = fileName
  }

  def concept(index: Int): String = conceptMaster.getOrElse(index, "UNKNOWN")
  def relation(index: Int): String = relationMaster.getOrElse(index, "UNKNOWN")
  def conceptIndex(value: String) = conceptStringToIndex.getOrElse(value, 0)
  def relationIndex(value: String) = relationStringToIndex.getOrElse(value, 0)

  private def loadConcepts: Set[String] = {
    (for {
      graph <- allAMR
      concept <- graph.nodes.values
      if (quote findFirstIn concept) == None // ignore anything with quotes
      if numbers.replaceAllIn(concept, "") != "" // and anything that is purely numeric
    } yield concept).toSet

  }

  private def loadRelations: Set[String] = {
    (for {
      graph <- allAMR
      relation <- graph.arcs.values
    } yield relation).toSet
  }

  private def loadInsertableConcepts: Set[String] = {
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
      val insertableConcepts = (for {
        ((sentence, _), amr) <- allSentencesAndAMR zip allAMR
        val s = Sentence(sentence, Some(amr))
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

  private def loadConceptsPerLemma: Map[String, Set[Int]] = {
    val initial = (for {
      (graph, (sentence, _)) <- allAMR zip allSentencesAndAMR
      concepts = graph.nodes.values.toSet filter (numbers.replaceAllIn(_, "") != "") map conceptIndex
      val dt = DependencyTree(sentence)
      val eligibleNodes = dt.nodes filter {case (i, v) => dt.nodeNER.getOrElse(i, "O") != "PERSON" && numbers.replaceAllIn(v, "") != ""} map (_._1)
      n <- eligibleNodes
      lemma = dt.nodeLemmas.getOrElse(n, "a")
      if !(commonLemmas contains lemma)
    } yield (lemma, concepts)).toList

    val grouped = initial.groupBy(_._1)
    val cleaned = grouped.mapValues(_.map(_._2))
    cleaned map { case (key, listOfSets) => (key -> listOfSets.flatten.toSet) }
  }

  private def loadEdgesPerLemma: Map[String, Set[Int]] = {
    val initial = (for {
      (graph, (sentence, _)) <- allAMR zip allSentencesAndAMR
      relations = graph.arcs.values.toSet filter (numbers.replaceAllIn(_, "") != "") map relationIndex
      lemma <- DependencyTree(sentence).nodeLemmas.values filter (numbers.replaceAllIn(_, "") != "")
      if !(commonLemmas contains lemma)
    } yield (lemma -> relations)).toList
    val grouped = initial.groupBy(_._1)
    val cleaned = grouped.mapValues(_.map(_._2))
    cleaned map { case (key, listOfSets) => (key -> listOfSets.flatten.toSet) }
  }
}