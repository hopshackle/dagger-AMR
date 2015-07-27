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
  val commonLemmas = List("the", "and", "a", "in", "of", "to")
  // val commonLemmas: List[String] = List()

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

  lazy val conceptsPerLemma = loadConceptsPerLemmaReduced
  //  lazy val universalConcepts = Set("and", "date-entity", "UNKNOWN", "ROOT", "name", "monetary-quantity") map conceptIndex
  //  lazy val universalRelations = Set("year", "month", "day", "UNKNOWN", "ROOT", "quant", "poss") map relationIndex
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
      concept <- filterOutNumbersAndNames(graph.nodes.values.toSeq)
    } yield concept).toSet

  }

  private def filterOutNumbersAndNames(input: Seq[String]): Seq[String] = {
    input filter (x => (quote findFirstIn x) == None) filter (x => numbers.replaceAllIn(x, "") != "")
  }

  private def loadRelations: Set[String] = {
    (for {
      graph <- allAMR
      relation <- graph.arcs.values
    } yield relation).toSet
  }

  private def loadInsertableConcepts: Map[String, Set[String]] = {
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
      val expertResults = for {
        ((sentence, _), amr) <- allSentencesAndAMR zip allAMR
        val s = Sentence(sentence, Some(amr))
      } yield (s, RunDagger.sampleTrajectory(s, "", expert))

      val interimConcepts = (for {
        ((_, s), a) <- expertResults zip allAMR
        (dt, amr) <- s.dependencyTree.insertedNodes
        lemma <- s.dependencyTree.nodeLemmas.values filter (_ != "")
        if !(commonLemmas contains lemma)
        val name = a.nodes(amr)
      } yield (lemma -> name))

      val grouped = interimConcepts.groupBy(_._1)
      val cleaned = grouped.mapValues(_.map(_._2))
      val test = cleaned map { case (key, listOfSets) => (key -> listOfSets.groupBy(identity).mapValues(_.size)) }
      val insertableConcepts = test map { case (key, m) => (key -> (m.toSeq.sortWith(_._2 > _._2).take(20).map(_._1).toSet - "-")) }

      val lemmasToConcepts = (for {
        (original, processed) <- expertResults
        val l = original.positionToAMR.toList filter (_._1 != 0) map { case (k, v) => (original.dependencyTree.nodeLemmas(k), original.amr.get.nodes(v)) }
      } yield l).flatten.groupBy(_._1).mapValues(_.map(_._2))

      val filteredLtoC = lemmasToConcepts map { case (k, v) => (k, filterOutNumbersAndNames(v).toSet) }

      val ic = new FileWriter(amrFile + "_ic")
      insertableConcepts filter (_._2.nonEmpty) foreach (x => ic.write(x._1 + ":" + (x._2).mkString(":") + "\n"))
      ic.close

      val lc = new FileWriter(amrFile + "_lc")
      filteredLtoC filter (_._2.nonEmpty) foreach (x => lc.write(x._1 + ":" + x._2.mkString(":") + "\n"))
      lc.close

      insertableConcepts
    } else {
      val input = Source.fromFile(amrFile + "_ic").getLines
      val lineSplit = input map (_.split(":").toList)
      (lineSplit map (x => (x.head, x.tail.toSet))).toMap
    }
  }

  private def loadConceptsPerLemmaReduced: Map[String, Set[Int]] = {
    val conceptFileExists = {
      try {
        val fr = new FileReader(amrFile + "_lc")
        true
      } catch {
        case e: FileNotFoundException => false
      }
    }

    if (!conceptFileExists) insertableConcepts
    // to load data

    val input = Source.fromFile(amrFile + "_lc").getLines
    val lineSplit = input map (_.split(":").toList)
    (lineSplit map (x => (x.head, x.tail map conceptIndex toSet))).toMap
  }

  private def loadConceptsPerLemma: Map[String, Set[Int]] = {
    val initial = (for {
      (graph, (sentence, _)) <- allAMR zip allSentencesAndAMR
      concepts = graph.nodes.values.toSet filter (numbers.replaceAllIn(_, "") != "") map conceptIndex
      val dt = DependencyTree(sentence)
      val eligibleNodes = dt.nodes filter { case (i, v) => dt.nodeNER.getOrElse(i, "O") != "PERSON" && numbers.replaceAllIn(v, "") != "" } map (_._1)
      n <- eligibleNodes
      lemma = dt.nodeLemmas.getOrElse(n, "a")
      if !(commonLemmas contains lemma)
    } yield (lemma, concepts)).toList

    val grouped = initial.groupBy(_._1)
    val cleaned = grouped.mapValues(_.map(_._2))
    cleaned map { case (key, listOfSets) => (key -> listOfSets.flatten.toSet) }
  }

  private def loadEdgesPerLemma: Map[String, Set[Int]] = {
    val conceptFileExists = {
      try {
        val fr = new FileReader(amrFile + "_le")
        true
      } catch {
        case e: FileNotFoundException => false
      }
    }

    if (!conceptFileExists) {
      val initial = (for {
        (graph, (sentence, _)) <- allAMR zip allSentencesAndAMR
        relations = (graph.arcs.values.toSet - "ROOT") map relationIndex
        lemma <- DependencyTree(sentence).nodeLemmas.values map { x: String => numbers.replaceAllIn(x, "") match { case "" => "##"; case a => a } }
        if !(commonLemmas contains lemma)
      } yield (lemma -> relations)).toList
      val grouped = initial.groupBy(_._1)
      val cleaned = grouped.mapValues(_.map(_._2))
      //    val output = cleaned map { case (key, listOfSets) => (key -> listOfSets.flatten.toSet) }
      val test = cleaned map { case (key, listOfSets) => (key -> listOfSets.flatten.groupBy(identity).mapValues(_.size)) }
      val t2 = test map { case (key, m) => (key -> m.toSeq.sortWith(_._2 > _._2).take(30).map(_._1).toSet) }
      val le = new FileWriter(amrFile + "_le")
      t2 filter (_._2.nonEmpty) foreach (x => le.write(x._1 + ":" + (x._2 map relation).mkString(":") + "\n"))
      le.close
      t2
    } else {
      val input = Source.fromFile(amrFile + "_le").getLines
      val lineSplit = input map (_.split(":").toList)
      (lineSplit map (x => (x.head, x.tail map relationIndex toSet))).toMap
    }
  }
}