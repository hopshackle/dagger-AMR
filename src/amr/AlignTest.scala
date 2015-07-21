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
    return wordAlignments // Todo: Return spanAlignments
  }

  def alignWords(stemmedSentence: Array[List[String]], graph: Graph, alignments: Array[Option[Node]], dt: DependencyTree) {
    val amrGraph = AMRGraph(graph)
    var possibleMatches = scala.collection.mutable.Map[Int, List[String]]()
    // Key is word position, Value is list of AMR node key matches
    for (node <- graph.nodes) {
      logger(3, "alignWords: node.concept = " + node.concept)
      var ConceptExtractor(concept) = node.concept
      if (node.concept.matches("""^[0-9.]*$""")) {
        concept = node.concept
      }
      for (i <- Range(0, stemmedSentence.size)) {
        for (word <- stemmedSentence(i)) {
          if (word == concept && alignments(i) == None) {
            logger(3, "concept: " + node.concept + " word: " + word + " AMR: " + node.id)
            possibleMatches.put(i, node.id :: possibleMatches.getOrElse(i, List()))
          }
        }
      }
    }
    // We now have a list of possible matches for each word
    // Our first pass is to pick out unique matches
    for (wordPos <- possibleMatches.keys) {
      if (possibleMatches(wordPos).size == 1) {
        val nodeId = possibleMatches(wordPos)(0)
        val node = graph.getNodeById(nodeId)
        alignments(wordPos) = Some(node)
        node.alignment = Some(wordPos)
      }
    }

    // Now we go through possible duplicates, and determine if we have a clear preference
    val amrToDT = (graph.nodes map { n => n.alignment match { case None => (n.id -> -1); case Some(pos) => (n.id -> pos) } }).toMap
    for (wordPos <- possibleMatches.keys) {
      if (possibleMatches(wordPos).size > 1) {
        val scores = for {
          nodeId <- possibleMatches(wordPos)
          val neighbours = amrGraph.parentsOf(nodeId) ++ amrGraph.childrenOf(nodeId)
          val s = for {
            n <- neighbours
            val nPos = amrToDT.getOrElse(n, -1)
            val distance = if (nPos > -1) dt.getDistanceBetween(wordPos, nPos) else 10
          } yield distance
        } yield s.sum.toDouble / s.size.toDouble
        val (highScore, index) = (scores zipWithIndex).max
        val nodeId = possibleMatches(wordPos)(index)
        val node = graph.getNodeById(nodeId)
        alignments(wordPos) = Some(node)
        node.alignment = Some(wordPos)
      }
    }
  }
}