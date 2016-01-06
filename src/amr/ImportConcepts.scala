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
    val expert = new WangXueExpert
    val output = for {
      ((sentence, _, id), amr) <- allSentencesAndAMR zip allAMR
      val s = Sentence(sentence, Some(amr), id)
      val result = RunDagger.sampleTrajectory(s, "", expert, WangXueTransitionSystem)
    } yield (s, result)
    output
  }
  lazy val allSentencesAndAMR = importFile(amrFile)
  lazy val allAMR = allSentencesAndAMR map { case (sentence, amr, id) => AMRGraph(amr, sentence, id) }
  lazy val conceptStrings = loadConcepts
  lazy val defaultOptions = if (WangXueTransitionSystem.preferKnown) Map(0 -> "WORD") else Map((0 -> "WORD"), (-1 -> "LEMMA"), (-2 -> "VERB-FORM"))
  lazy val conceptMaster = (for {
    (concept, index) <- conceptStrings zipWithIndex
  } yield ((index + 1) -> concept)).toMap ++ defaultOptions
  lazy val conceptStringToIndex = conceptMaster map (_ match { case (index, text) => (text -> index) })

  lazy val conceptsPerLemma = loadConceptsPerLemmaReduced
  lazy val edgesPerLemma = loadEdgesPerLemma
  lazy val edgesPerConcept = loadEdgesPerConcept
  private var loadCompleted = false

  lazy val insertableConcepts = loadInsertableConcepts
  lazy val subInsertableConcepts = loadSubInsertions

  lazy val compositeNodes = loadCompositeNodes
  lazy val wikifications = loadWikifications

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
      concept <- stripQuotes(graph.nodes.values.toSeq)
    } yield concept).toSet
  }

  def filterOutNumbersAndNames(input: Seq[String]): Seq[String] = {
    input filter (x => (quote findFirstIn x) == None) filter (x => numbers.replaceAllIn(x, "") != "")
  }
  def filterOutNumbersAndStripQuotes(input: Seq[String]): Seq[String] = {
    stripQuotes(input filter (x => numbers.replaceAllIn(x, "") != ""))
  }
  def stripQuotes(input: Seq[String]): Seq[String] = {
    input map (quote.replaceAllIn(_, ""))
  }

  private def loadRelations: Set[String] = {
    (for {
      graph <- allAMR
      relation <- graph.arcs.values
    } yield relation).toSet
  }

  private def loadCompositeNodes: Map[String, Set[String]] = {
    val stage1 = (for {
      (original, _) <- expertResults
      allLemmas = original.dependencyTree.nodeLemmas.values.toSet
      allUnmappedAMRKeys = original.amr.get.nodes.keys filterNot original.AMRToPosition.contains
      allComposites = (allUnmappedAMRKeys map { key => allCompositesFrom(key, original) }).flatten.toSet
    } yield (allLemmas, allComposites)).toSeq
    // I also now want to keep track of the lemmas in each sentence, so that I can track which lemmas give which composite nodes
    // obviously keyed from lemma to list of nodes

    // excluding any lemma that appears more than n times (rather than hard-coding common words)
    // or which is linked to more than n of the composite nodes
    // OK - both limits now implemented independently
    val sentenceTotal = stage1.size
    val commonLemmaProportion = 0.10
    val lemmaCountsBySentence = (stage1 flatMap (_._1.toList) groupBy identity mapValues (_.size)) - "##"

    val limitOfCompositesNodesPerLemma = 1000
    val output = (for {
      (lemmas, composites) <- stage1
      lemma <- lemmas
      if lemmaCountsBySentence.getOrElse(lemma, 0) <= sentenceTotal * commonLemmaProportion
    } yield (lemma, composites)).groupBy(_._1).mapValues { all => all flatMap (_._2) toSet } filter (i => i._2.size <= limitOfCompositesNodesPerLemma)

    val cn = new FileWriter(amrFile + "_cn")
    output filter (i => i._2.nonEmpty) foreach (x => cn.write(x._1 + " " + x._2.toList.mkString(" ") + "\n"))
    cn.close
    output
  }

  private def loadWikifications: Map[String, String] = {
    import Wikify.{ isConcatenationOfNameArgs, forwardConcatenationOfNameArgs }
    val output = (for {
      amr <- allAMR
      conceptsToWikiAttributes = amr.arcs.toSeq filter
        { case (_, edgeType) => edgeType == "wiki" } map
        { case ((from, to), _) => (from, amr.nodes(to)) } filterNot
        { case (node, wikification) => wikification == "-" || isConcatenationOfNameArgs(amr, node, wikification) } map
        {
          case (from, wikiString) =>
            (amr.nodes(from) + ":" + forwardConcatenationOfNameArgs(amr, from), wikiString.replaceAll(Wikify.quoteString, ""))
        }

    } yield conceptsToWikiAttributes).flatten.toMap //.groupBy(_._1).mapValues(seq => (seq map (_._2) distinct).toList)
    val lr = new FileWriter(amrFile + "_wiki")
    output foreach (x => lr.write(x._1 + "\t:\t" + x._2 + "\n"))
    lr.close

    output
  }

  private def loadSubInsertions: Map[String, Set[String]] = {
    val interimConcepts = for {
      ((_, s), a) <- (expertResults zip allAMR)
      (node, (_, amr)) <- s.dependencyTree.insertedNodes.toList
      if amr != ""
      if s.dependencyTree.isLeafNode(node)
      name = a.nodes(amr)
      if numbers.replaceAllIn(name, "") != ""
      lemma <- (s.dependencyTree.parentsOf(node) map (s.dependencyTree.nodeLemmas.getOrElse(_, ""))) filter (_ != "")
    } yield (lemma, name)

    val grouped = interimConcepts.groupBy(_._1)
    val cleaned = grouped.mapValues(_.map(_._2))
    val test = cleaned map { case (key, listOfSets) => (key -> listOfSets.groupBy(identity).mapValues(_.size)) }
    val insertableConcepts = test map { case (key, m) => (key -> (m.toSeq.sortWith(_._2 > _._2).map(_._1).toSet - "-")) }
    val ic = new FileWriter(amrFile + "_isubc")
    insertableConcepts filter (_._2.nonEmpty) foreach (x => ic.write(x._1 + ":" + (x._2).mkString(":") + "\n"))
    ic.close

    insertableConcepts
  }

  private def allCompositesFrom(amrKey: String, sentence: Sentence): List[String] = {
    def oneUp(amrKey: String, sentence: Sentence, acc: List[String]): List[String] = {
      val unmappedParents = sentence.amr.get.parentsOf(amrKey) filterNot sentence.AMRToPosition.contains
      if (unmappedParents.isEmpty)
        acc
      else {
        (for {
          p <- unmappedParents.toList
          val concept = sentence.amr.get.nodes(p)
          val relation = sentence.amr.get.arcs((p, amrKey))
          val newAcc = acc map { concept + ":" + relation + ":" + _ }
        } yield oneUp(p, sentence, acc ++ newAcc)).flatten
      }
    }
    oneUp(amrKey, sentence, List(sentence.amr.get.nodes(amrKey)))
  }

  private def loadInsertableConcepts: Map[String, Set[String]] = {
    val interimConcepts = for {
      ((_, s), a) <- (expertResults zip allAMR)
      (node, (_, amr)) <- s.dependencyTree.insertedNodes.toList
      if amr != ""
      name = a.nodes(amr)
      lemma <- (s.dependencyTree.childrenOf(node) filterNot(s.dependencyTree.arcs(node, _) == "wiki") map (s.dependencyTree.nodeLemmas.getOrElse(_, ""))) filter (_ != "")
    } yield (lemma, name)

    val grouped = interimConcepts.groupBy(_._1)
    val cleaned = grouped.mapValues(_.map(_._2))
    val test = cleaned map { case (key, listOfSets) => (key -> listOfSets.groupBy(identity).mapValues(_.size)) }
    val insertableConcepts = test map { case (key, m) => (key -> (m.toSeq.sortWith(_._2 > _._2).map(_._1).toSet - "-" - "name")) }

    val ic = new FileWriter(amrFile + "_ic")
    insertableConcepts filter (_._2.nonEmpty) foreach (x => ic.write(x._1 + ":" + (x._2).mkString(":") + "\n"))
    ic.close

    insertableConcepts
  }

  private def loadConceptsPerLemmaReduced: Map[String, Set[Int]] = {

    val lemmasToConcepts = (for {
      (original, processed) <- expertResults
      val l = original.positionToAMR.toList filter (_._1 != 0) map { case (k, v) => (original.dependencyTree.nodeLemmas(k), original.amr.get.nodes(v)) }
    } yield l).flatten.groupBy(_._1).mapValues(_.map(_._2))

    val filteredLtoC = lemmasToConcepts map {
      case (k, v) if k == "##" => (k, (filterOutNumbersAndStripQuotes(v) toSet))
      case (k, v) => (k, (stripQuotes(v) toSet))
    }

    val lc = new FileWriter(amrFile + "_lc")
    filteredLtoC filter (_._2.nonEmpty) foreach (x => lc.write(x._1 + ":" + x._2.mkString(":") + "\n"))
    lc.close

    filteredLtoC map { case (k, stringVals) => (k -> (stringVals map conceptIndex)) }
  }

  private def loadEdgesPerLemma: Map[String, Set[Int]] = {
    val initial = (for {
      ((original, processed), a) <- (expertResults zip allAMR)
      (node, lemma) <- processed.dependencyTree.nodeLemmas
      if lemma != ""
      ignorableEdges = Set("opN", "polarity", "UNKNOWN", "sntN", "wiki") // ++ original.dependencyTree.arcs.values
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
  }
  private def loadEdgesPerConcept: Map[String, Set[Int]] = {
    val edges = (for {
      (original, _) <- expertResults
      amr = original.amr.get
      ((from, to), relation) <- amr.arcs filterNot (_._2 == "wiki")
      conceptFrom = if (numbers.replaceAllIn(amr.nodes(from), "") == "") "##" else amr.nodes(from)
      conceptTo = if (numbers.replaceAllIn(amr.nodes(to), "") == "") "##" else amr.nodes(to)
    } yield List((conceptFrom + "-OUT" -> relation), (conceptTo + "-IN" -> relation))).flatten.toSeq

    val stuff = edges.groupBy(_._1).mapValues(seq => (seq map { i => relationIndex(i._2) }).toSet)
    val ce = new FileWriter(amrFile + "_ce")
    stuff filter (_._2.nonEmpty) foreach (x => ce.write(x._1 + ":" + (x._2 map relation).mkString(":") + "\n"))
    ce.close
    stuff
  }
}
