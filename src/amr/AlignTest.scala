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

  def alignWords(sentence: Array[String], graph: Graph, wordnet: Boolean = false): Array[Option[Node]] = {
    val size = sentence.size
    val Relation = """:?(.*)""".r
    val wordAlignments = new Array[Option[Node]](size)
    val stemmedSentence = new Array[List[String]](size)
    for (i <- Range(0, size)) {
      stemmedSentence(i) = (AlignWords.stemmer(sentence(i)) ++ (if (wordnet) Wordnet.synonyms(sentence(i)) else List[String]()))
      wordAlignments(i) = None
    }
    //    println(sentence.mkString(" "))
    val dt = DependencyTree(sentence.mkString(" "))
    logger(2, "Stemmed sentence " + stemmedSentence.toList.toString)
    alignWords(stemmedSentence, graph, wordAlignments, dt)
    //    AlignWords.fuzzyAligner(stemmedSentence, graph.root, wordAlignments)
    return wordAlignments
  }

  def alignWords(stemmedSentence: Array[List[String]], graph: Graph, alignments: Array[Option[Node]], dt: DependencyTree) {
    import com.rockymadden.stringmetric.similarity._
    def topologicalDissimilarity(wordIndex: Int, amrId: String, dt: DependencyTree, amr: AMRGraph, mapping: scala.collection.mutable.Map[Int, String]): Double = {
      // We take all the neighbours of the node in the DependencyTree, and, where these map to something in the AMR Graph, we calculate
      // the mean distance in the AMR Graph. Hence high values are BAD.
      val reverseMapping = mapping map { case (k, v) => (v, k) }
      val distanceToParents = (dt.parentsOf(wordIndex) filter (mapping contains _) map mapping map (amr.getDistanceBetween(_, amrId)) map (x => if (x == 0) 10 else x)).sum
      val distanceToChildren = (dt.childrenOf(wordIndex) filter (mapping contains _) map mapping map (amr.getDistanceBetween(_, amrId)) map (x => if (x == 0) 10 else x)).sum
      val totalNeighbours = ((dt.parentsOf(wordIndex) ++ dt.childrenOf(wordIndex)) filter (mapping contains _) size)
      //      if (totalNeighbours > 0) println(s"Parents of ${dt.nodes(wordIndex)} have distance to ${amr.nodes(amrId)} of $distanceToParents; Children have $distanceToChildren; giving final Score of ${(distanceToParents + distanceToChildren).toDouble / totalNeighbours.toDouble}")

      // we then repeat from the other perspective of AMR to DT
      val AMRdistanceToParents = (amr.parentsOf(amrId) filter (reverseMapping contains _) map reverseMapping map (dt.getDistanceBetween(_, wordIndex)) map (x => if (x == 0) 10 else x)).sum
      val AMRdistanceToChildren = (amr.childrenOf(amrId) filter (reverseMapping contains _) map reverseMapping map (dt.getDistanceBetween(_, wordIndex)) map (x => if (x == 0) 10 else x)).sum
      val AMRtotalNeighbours = ((amr.parentsOf(amrId) ++ amr.childrenOf(amrId)) filter (reverseMapping contains _) size)

      val output = if (totalNeighbours + AMRtotalNeighbours > 0) (AMRdistanceToChildren + AMRdistanceToChildren + distanceToParents + distanceToChildren).toDouble / (totalNeighbours + AMRtotalNeighbours).toDouble
      else 10.0
      if (output == 0.0) 10.0 else output
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
    // stemmedSentence is in word order, and should have the same number of entries as the sentence
    //   println(s"Sentence: ${stemmedSentence.size}, DT Node: ${dt.nodes.keys.size}")
    assert(stemmedSentence.size == dt.nodes.keys.size, "Unequal sentence lengths for " + dt.nodes)
    //    val dtKeysInOrder = dt.nodes.keys.toSeq.sorted
    // Now, for wordIndex of i, we find the dt node key using dtKeysInOrder(i)
    for (node <- graph.nodes) {
      val concept = amrLemmas(node.id).toLowerCase
      logger(3, "alignWords: node.concept = " + node.concept)
      for (i <- Range(0, stemmedSentence.size)) {
        for (word <- stemmedSentence(i)) {
          val jaro = JaroMetric.compare(word.toLowerCase, concept).getOrElse(0.0)
          val wordLength = word.size.toDouble
          //  math.max(0.7, 1.10 - math.min(wordLength, concept.size) / 10.0)
          if (jaro >= 0.7 && alignments(i) == None && word != "-" && word != "a") {
            logger(3, "concept: " + node.concept + " word: " + word + " at " + i + "; AMR: " + node.id)
            possibleMatches.put(i + 1, node.id :: possibleMatches.getOrElse(i + 1, List()))
          }
        }
      }
    }
    val reverseMatches = possibleMatches.toList.flatMap { case (k, v) => for { s <- v } yield (s, k) }.groupBy(_._1).mapValues(_.map(_._2))
    // We now have a list of possible matches for each word (in both directions)
    //  possibleMatches foreach (x => println(x + " " + stemmedSentence(x._1 - 1)))
    //     reverseMatches foreach (x => println(x + " " + amrGraph.nodes(x._1)))
    val iterations = dt.nodes.size
    var bestMapping = scala.collection.mutable.Map[Int, String]()
    var solutionFound = false
    for (i <- 1 to iterations) {
      if (!solutionFound) {
        val dtNodes = dt.nodeLemmas
        val amrNodes = amrGraph.nodes
        val similarities = (for {
          wordIndex <- dtNodes.keys
          amrId <- possibleMatches.getOrElse(wordIndex, Nil)
          word <- stemmedSentence(wordIndex - 1) ++ Array(dtNodes(wordIndex).toLowerCase)
          val amrConcept = amrLemmas(amrId).toLowerCase
          val conceptCount: Double = if (amrConcept == word) (amrNodes.values count (_ == amrConcept)) +
            (dtNodes.values count (_ == amrConcept))
          else 1.0
          val jaro = JaroMetric.compare(word.toLowerCase, amrConcept).getOrElse(0.0)
   //         val d = println(f"Jaro for ${word} and ${amrConcept} is ${jaro}%.2f")
          val s1 = 1.0 + 10.0 / topologicalDissimilarity(wordIndex, amrId, dt, amrGraph, bestMapping)
          val s2 = 1.0 + 10.0 * (if (jaro == 1.0) 2.0 else 1.0) * Math.pow(jaro, 5)// * Math.pow(0.5, conceptCount - 1)
          //     val p = if (word.toLowerCase == "bell" && amrConcept == "bell") println(f"$word at $wordIndex, $amrConcept at $amrId, $s1%.2f $s2%.2f")
          val similarity = s1 * s2
          if similarity > 0.001
        } yield (wordIndex, amrId, similarity, s1, s2)).toSeq.sortWith((x, y) => x._3 > y._3)
        // We now have a list of all non-zero similarities, sorted in descending order of similarity

        var mappings = scala.collection.mutable.Map[Int, String]()
        var wordMapped = scala.collection.mutable.Set[Int]()
        var amrMapped = scala.collection.mutable.Set[String]()

        for ((wordIndex, amrId, similarity, s1, s2) <- similarities) {
        //              println(f"Similarity between ${amrGraph.nodes(amrId)} and ${dt.nodes(wordIndex)} is $similarity%.2f (topological = $s1%.2f; jaro = $s2%.2f")
          if (!(wordMapped contains wordIndex) && !(amrMapped contains amrId)) {
       //     println(f"Similarity between ${amrGraph.nodes(amrId)} and ${dt.nodes(wordIndex)} is $similarity%.2f (topological = $s1%.2f; jaro = $s2%.2f")
            wordMapped.add(wordIndex)
            amrMapped.add(amrId)
            mappings.put(wordIndex, amrId)
          }
        }
        if (i == 1)
          bestMapping = mappings
        else {
          // Find one mapping which has changed...make only this change, and then iterate. Slower, but avoids the swapping to and fro
          // of concepts
          val newMove = mappings.find(p => p._2 != bestMapping.getOrElse(p._1, ""))
          //       println(newMove)
          newMove match {
            case None => solutionFound = true
            case Some((k, v)) =>
              var cycleFinished = false
              var newKey = k
              var newValue = v
              while (!cycleFinished) {
                newKey = updateBestMappingAndReturnDisplacedKey(newKey, newValue)
                // the key being removed
                if (newKey != -1) {
                  newValue = mappings.getOrElse(newKey, "")
                  if (newValue != "") {
                    //         val p = println(" Leads to: " + (newKey, newValue))
                  } else { cycleFinished = true }
                } else {
                  cycleFinished = true
                }
              }
          }

          def updateBestMappingAndReturnDisplacedKey(key: Int, value: String): Int = {
            val oldKey = bestMapping.find(p => p._2 == value).getOrElse((-1, ""))._1
            bestMapping.put(key, value)
            if (oldKey != -1) bestMapping.remove(oldKey)
            oldKey
          }
        }
      }
      //   bestMapping foreach (x => println(x._1 + " " + dt.nodes(x._1) + " -> " + x._2 + " " + amrGraph.nodes(x._2)))
      //          bestMapping foreach (x => println(stemmedSentence(x._1 - 1) + " -> " + amrGraph.nodes(x._2)))

    }

    // bestMapping uses wordPos starting at 1 (the DependencyTree convention)
    // The Node convention is indexing starting at 0
    for (wordPos <- bestMapping.keys) {
      val nodeId = bestMapping(wordPos)
      val node = graph.getNodeById(nodeId)
      alignments(wordPos - 1) = Some(node)
      node.alignment = Some(wordPos - 1)
    }

  }
}