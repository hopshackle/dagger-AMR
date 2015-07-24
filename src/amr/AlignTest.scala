package amr
import edu.cmu.lti.nlp.amr._
object AlignTest {

  def main(args: Array[String]): Unit = {

    val sample = AMRGraph.importFile("C://AMR//Borderless.txt")
    sample foreach {
      case s @ (sentence, amr) =>
        println(sentence)
        println
        println(amr)
        println
        val out = AMRGraph(amr, sentence)
        println(out)

    }
  }

  private val ConceptExtractor = """^"?(.+?)-?[0-9]*"?$""".r // works except for numbers
  type Graph = edu.cmu.lti.nlp.amr.Graph

  def alignWords(sentence: Array[String], graph: Graph): Array[Option[Node]] = {
    val size = sentence.size
    val Relation = """:?(.*)""".r
    val wordAlignments = new Array[Option[Node]](size)
    val stemmedSentence = new Array[List[String]](size)
    for (i <- Range(0, size)) {
      stemmedSentence(i) = AlignWords.stemmer(sentence(i))
      wordAlignments(i) = None
    }
    val dt = DependencyTree(sentence.mkString(" "))
    logger(2, "Stemmed sentence " + stemmedSentence.toList.toString)
    alignWords(stemmedSentence, graph, wordAlignments, dt)
    AlignWords.fuzzyAligner(stemmedSentence, graph.root, wordAlignments)
    return wordAlignments
  }

  def alignWords(stemmedSentence: Array[List[String]], graph: Graph, alignments: Array[Option[Node]], dt: DependencyTree) {
    def topologicalDissimilarity(wordIndex: Int, amrId: String, dt: DependencyTree, amr: AMRGraph, mapping: scala.collection.mutable.Map[Int, String]): Double = {
      // We take all the neighbours of the node in the DependencyTree, and, where these map to something in the AMR Graph, we calculate
      // the mean distance in the AMR Graph. Hence high values are BAD.
      val distanceToParents = (dt.parentsOf(wordIndex) filter (mapping contains _) map mapping map (amr.getDistanceBetween(_, amrId))).sum
      val distanceToChildren = (dt.childrenOf(wordIndex) filter (mapping contains _) map mapping map (amr.getDistanceBetween(_, amrId))).sum
      val totalNeighbours = ((dt.parentsOf(wordIndex) ++ dt.childrenOf(wordIndex)) filter (mapping contains _) size)

      10.0
      //     if (totalNeighbours > 0) (distanceToParents + distanceToChildren).toDouble / totalNeighbours.toDouble
      //     else 10.0
    }
    val amrGraph = AMRGraph(graph)
    var possibleMatches = scala.collection.mutable.Map[Int, List[String]]()
    // Key is word position, Value is list of AMR node key matches
    var amrLemmas = scala.collection.mutable.Map[String, String]()
    for (node <- graph.nodes) {
      var ConceptExtractor(concept) = node.concept
      if (node.concept.matches("""^[0-9.]*$""")) {
        concept = node.concept
      }
      amrLemmas.put(node.id, concept)
    }
    for (node <- graph.nodes) {
      val concept = amrLemmas(node.id)
      logger(3, "alignWords: node.concept = " + node.concept)
      for (i <- Range(0, stemmedSentence.size)) {
        for (word <- stemmedSentence(i)) {
          if (word == concept && alignments(i) == None) {
            logger(3, "concept: " + node.concept + " word: " + word + " at " + i + "; AMR: " + node.id)
            possibleMatches.put(i+1, node.id :: possibleMatches.getOrElse(i+1, List()))
          }
        }
      }
    }
    val reverseMatches = possibleMatches.toList.flatMap { case (k, v) => for { s <- v } yield (s, k) }.groupBy(_._1).mapValues(_.map(_._2))
    // We now have a list of possible matches for each word (in both directions)
    possibleMatches foreach (x => println(x + " " + dt.nodes(x._1)))
    reverseMatches foreach (x => println(x + " " + amrGraph.nodes(x._1)))
    val iterations = 4
    var bestMapping = scala.collection.mutable.Map[Int, String]()
    for (i <- 1 to iterations) {
      val dtNodes = dt.nodes
      val amrNodes = amrGraph.nodes
      val similarities = (for {
        wordIndex <- dtNodes.keys
        if possibleMatches contains wordIndex
        val word = dtNodes(wordIndex)
        amrId <- amrNodes.keys
        if reverseMatches contains amrId
        val amrConcept = amrLemmas(amrId)
        val conceptCount: Double = if (amrConcept == word) (amrNodes.values count (_ == amrConcept)) +
          (dtNodes.values count (_ == amrConcept))
        else 0.0
        val similarity = (10.0 / topologicalDissimilarity(wordIndex, amrId, dt, amrGraph, bestMapping)) *
          (1.0 + (if (amrConcept == word) 5.0 * Math.pow(0.5, conceptCount - 1) else 0.0))
        if similarity > 0.001
      } yield (wordIndex, amrId, similarity)).toSeq.sortWith((x, y) => x._3 > y._3)
      // We now have a list of all non-zero similarities, sorted in descending order of similarity

      var mappings = scala.collection.mutable.Map[Int, String]()
      var dtMapped = scala.collection.mutable.Set[Int]()
      var amrMapped = scala.collection.mutable.Set[String]()

      for ((wordIndex, amrId, similarity) <- similarities) {
        if (!(dtMapped contains wordIndex) && !(amrMapped contains amrId)) {
          dtMapped.add(wordIndex)
          amrMapped.add(amrId)
          mappings.put(wordIndex, amrId)
        }
      }
      bestMapping = mappings
    }

    for (wordPos <- bestMapping.keys) {
      val nodeId = bestMapping(wordPos)
      val node = graph.getNodeById(nodeId)
      alignments(wordPos) = Some(node)
      node.alignment = Some(wordPos)
    }

  }
}