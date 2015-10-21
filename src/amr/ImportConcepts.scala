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

  lazy val relationStrings = loadRelations
  lazy val relationMaster = (for {
    (relation, index) <- relationStrings zipWithIndex
  } yield ((index + 1) -> relation)).toMap + (0 -> "UNKNOWN")
  lazy val relationStringToIndex = relationMaster map (_ match { case (index, text) => (text -> index) })
  lazy val expertResults = {
    println("Now calculating expert results")
    val expert = new WangXueExpert
    val output = for {
      ((sentence, _), amr) <- allSentencesAndAMR zip allAMR
      val s = Sentence(sentence, Some(amr))
    } yield (s, RunDagger.sampleTrajectory(s, "", expert))
    output
  }
  lazy val allSentencesAndAMR = importFile(amrFile)
  lazy val allAMR = allSentencesAndAMR map { case (sentence, amr) => AMRGraph(amr, sentence) }
  lazy val conceptStrings = loadConcepts
  lazy val conceptMaster = (for {
    (concept, index) <- conceptStrings zipWithIndex
  } yield ((index + 1) -> concept)).toMap + (0 -> "WORD") + (-1 -> "LEMMA") + (-2 -> "VERB-FORM")
  lazy val conceptStringToIndex = conceptMaster map (_ match { case (index, text) => (text -> index) })

  lazy val conceptsPerLemma = loadConceptsPerLemmaReduced
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
      concept <- filterOutNumbersAndStripQuotes(graph.nodes.values.toSeq)
    } yield concept).toSet
  }

  def filterOutNumbersAndNames(input: Seq[String]): Seq[String] = {
    input filter (x => (quote findFirstIn x) == None) filter (x => numbers.replaceAllIn(x, "") != "")
  }
  def filterOutNumbersAndStripQuotes(input: Seq[String]): Seq[String] = {
    input filter (x => numbers.replaceAllIn(x, "") != "") map (quote.replaceAllIn(_, ""))
  }

  private def loadRelations: Set[String] = {
    println("Loading Relations")
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

      val interimConcepts = for {
        ((_, s), a) <- (expertResults zip allAMR)
        (node, amr) <- s.dependencyTree.insertedNodes.toList
        name = a.nodes(amr)
        lemma <- (s.dependencyTree.childrenOf(node) map (s.dependencyTree.nodeLemmas.getOrElse(_, ""))) filter (_ != "")
      } yield (lemma, name)

      val grouped = interimConcepts.groupBy(_._1)
      val cleaned = grouped.mapValues(_.map(_._2))
      val test = cleaned map { case (key, listOfSets) => (key -> listOfSets.groupBy(identity).mapValues(_.size)) }
      val insertableConcepts = test map { case (key, m) => (key -> (m.toSeq.sortWith(_._2 > _._2).map(_._1).toSet - "-" - "name")) }

      val lemmasToConcepts = (for {
        (original, processed) <- expertResults
        val l = original.positionToAMR.toList filter (_._1 != 0) map { case (k, v) => (original.dependencyTree.nodeLemmas(k), original.amr.get.nodes(v)) }
      } yield l).flatten.groupBy(_._1).mapValues(_.map(_._2))

      val filteredLtoC = lemmasToConcepts map { case (k, v) => (k, (filterOutNumbersAndStripQuotes(v) toSet)) }

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
        ((original, processed), a) <- (expertResults zip allAMR)
        (node, lemma) <- processed.dependencyTree.nodeLemmas
        if lemma != ""
        ignorableEdges = Set("opN", "polarity", "UNKNOWN") // ++ original.dependencyTree.arcs.values
        relationsIn = processed.dependencyTree.arcs filter (x => x._1._2 == node) map ((_._2)) filter (!ignorableEdges.contains(_))
        relationsOut = processed.dependencyTree.arcs filter (x => x._1._1 == node) map ((_._2)) filter (!ignorableEdges.contains(_))
      } yield (lemma, relationsIn, relationsOut)).toList
      val grouped = initial.groupBy(_._1)
      val cleanedIn = grouped.mapValues(_.map(_._2)) map { case (k, v) => (k + "-IN" -> v) }
      val cleanedOut = grouped.mapValues(_.map(_._3)) map { case (k, v) => (k + "-OUT" -> v) }
      val arcsByName = (cleanedIn ++ cleanedOut) map { case (key, listOfSets) => (key -> listOfSets.flatten.groupBy(identity).mapValues(_.size)) } map
        { case (key, m) => (key -> m.toSeq.sortWith(_._2 > _._2).map(_._1).toSet) }
      val le = new FileWriter(amrFile + "_le")
      arcsByName filter (_._2.nonEmpty) foreach (x => le.write(x._1 + ":" + x._2.mkString(":") + "\n"))
      le.close
      arcsByName map { case (k, v) => (k, v map relationIndex) }
    } else {
      val input = Source.fromFile(amrFile + "_le").getLines
      val lineSplit = input map (_.split(":").toList)
      (lineSplit map (x => (x.head, x.tail map relationIndex toSet))).toMap
    }
  }
}