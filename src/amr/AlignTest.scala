package amr
import amr.JAMR._
object AlignTest {

  val quote = """"""".r
  val frameEnding = "-0[1-9]$".r

  private val ConceptExtractor = """^"?(.+?)-?[0-9]*"?$""".r // works except for numbers
  type Graph = amr.JAMR.Graph

  def alignWords(rawSentence: String, amr: Graph, wordnet: Boolean = false): AMRGraph = {
    import com.rockymadden.stringmetric.similarity._
    def topologicalDissimilarity(dtNode: Int, amrId: String, dt: DependencyTree, amr: AMRGraph, mapping: scala.collection.mutable.Map[Int, String]): Double = {
      // We take all the neighbours of the node in the DependencyTree, and, where these map to something in the AMR Graph, we calculate
      // the mean distance in the AMR Graph. Hence high values are BAD.
      val reverseMapping = mapping map { case (k, v) => (v, k) }
      val distanceToParents = (dt.parentsOf(dtNode) filter (mapping contains _) map mapping map (amr.getDistanceBetween(_, amrId)) map (x => if (x == 0) 10 else x)).sum
      val distanceToChildren = (dt.childrenOf(dtNode) filter (mapping contains _) map mapping map (amr.getDistanceBetween(_, amrId)) map (x => if (x == 0) 10 else x)).sum
      val totalNeighbours = ((dt.parentsOf(dtNode) ++ dt.childrenOf(dtNode)) filter (mapping contains _) size)
      //      if (totalNeighbours > 0) println(s"Parents of ${dt.nodes(wordIndex)} have distance to ${amr.nodes(amrId)} of $distanceToParents; Children have $distanceToChildren; giving final Score of ${(distanceToParents + distanceToChildren).toDouble / totalNeighbours.toDouble}")

      // we then repeat from the other perspective of AMR to DT
      val AMRdistanceToParents = (amr.parentsOf(amrId) filter (reverseMapping contains _) map reverseMapping map (dt.getDistanceBetween(_, dtNode)) map (x => if (x == 0) 10 else x)).sum
      val AMRdistanceToChildren = (amr.childrenOf(amrId) filter (reverseMapping contains _) map reverseMapping map (dt.getDistanceBetween(_, dtNode)) map (x => if (x == 0) 10 else x)).sum
      val AMRtotalNeighbours = ((amr.parentsOf(amrId) ++ amr.childrenOf(amrId)) filter (reverseMapping contains _) size)

      val output = if (totalNeighbours + AMRtotalNeighbours > 0) (AMRdistanceToChildren + AMRdistanceToChildren + distanceToParents + distanceToChildren).toDouble / (totalNeighbours + AMRtotalNeighbours).toDouble
      else 10.0
      if (output == 0.0) 10.0 else output
    }
    val amrGraph = AMRGraph(amr)
    val dt = DependencyTree(rawSentence)
    // This populates nodes and arcs. We now need to update the NodeSpan component based on alignment to sentence 
    // (as encapsulated in the Dependency Tree)

    val dtNodeFromWordOrder = Seq(0) ++ dt.nodes.keys.toSeq.sorted
    val sentenceLength = dt.nodes.size
    // the Dependency Tree Nodes are always in ascending order moving left to right across the sentence
    // but will have missing numbers (for punctuation marks, which we usually remove)
    // Now, for wordIndex of i, we find the dt node key using dtNodeFromWordOrder(i)
    // We add Seq(0) at the front for the i = 0 case

    var possibleMatches = scala.collection.mutable.Map[Int, List[String]]()
    // Key is word position, Value is list of AMR node key matches
    // Why not just map DT node to AMR node directly?? All this is a relic of JAMR.
    // Hmm...because currently Sentence assumes it is fed a mapping of AMR concepts to sentence positions
    // ...so to keep up with that interface I need to do it this way (as I want to keep JAMR working)
    // even though Sentence will replicate part of this logic.
    val removedWikiConcepts = amrGraph.nodes filterNot { case (k, v) => amrGraph.edgesToParents(k) map amrGraph.arcs contains "wiki" }
    val modifiedConcepts = removedWikiConcepts map { case (k, v) => (k, quote.replaceAllIn(frameEnding.replaceAllIn(v, ""), "")) }

    for (node <- modifiedConcepts) {
      val concept = node._2.toLowerCase
      logger(3, "alignWords: node.concept = " + concept)
      for (i <- (1 to sentenceLength)) {
        val fullWord = dt.nodes(dtNodeFromWordOrder(i))
        for (word <- Array(fullWord) ++ fullWord.split("-")) {
          val jaro = JaroMetric.compare(word.toLowerCase, concept).getOrElse(0.0)
          val wordLength = word.size.toDouble
          //  math.max(0.7, 1.10 - math.min(wordLength, concept.size) / 10.0)
          if (jaro >= math.max(0.7, 1.10 - math.min(wordLength, concept.length) / 10.0) && word != "-" && word != "a") {
            logger(3, "concept: " + concept + " word: " + word + " at " + i + "; AMR: " + node._1)
            possibleMatches.put(i, node._1 :: possibleMatches.getOrElse(i, List()))
          }
        }
      }
    }
    // possibleMatches foreach println
    val reverseMatches = possibleMatches.toList.flatMap { case (k, v) => for { s <- v } yield (s, k) }.groupBy(_._1).mapValues(_.map(_._2))
    // We now have a list of possible matches for each word (in both directions)

    val iterations = dt.nodes.size
    var bestMapping = scala.collection.mutable.Map[Int, String]()
    var solutionFound = false
    for (i <- 1 to iterations) {
      if (!solutionFound) {
        val dtNodes = dt.nodes
        val similarities = (for {
          wordIndex <- (1 to sentenceLength)
          amrId <- possibleMatches.getOrElse(wordIndex, Nil)
          val word = dtNodes(dtNodeFromWordOrder(wordIndex)).toLowerCase
          val amrConcept = modifiedConcepts(amrId).toLowerCase
          val jaro = JaroMetric.compare(word, amrConcept).getOrElse(0.0)
          //    val d = println(f"Jaro for ${word} and ${amrConcept} is ${jaro}%.2f")
          val s1 = 1.0 + 10.0 / topologicalDissimilarity(dtNodeFromWordOrder(wordIndex), amrId, dt, amrGraph, bestMapping)
          val s2 = 1.0 + 10.0 * (if (jaro == 1.0) 2.0 else 1.0) * Math.pow(jaro, 5)
          val similarity = s1 * s2
          if similarity > 0.001
        } yield (wordIndex, amrId, similarity, s1, s2)).toSeq.sortWith((x, y) => x._3 > y._3)
        // We now have a list of all non-zero similarities, sorted in descending order of similarity

        var mappings = scala.collection.mutable.Map[Int, String]()
        var wordMapped = scala.collection.mutable.Set[Int]()
        var amrMapped = scala.collection.mutable.Set[String]()

        for ((wordIndex, amrId, similarity, s1, s2) <- similarities) {
          //                    println(f"Similarity between ${amrGraph.nodes(amrId)} and ${dt.nodes(wordIndex)} is $similarity%.2f (topological = $s1%.2f; jaro = $s2%.2f")
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

    // Now to populate the NodeSpans in the AMRGraph
    val nodeSpans = bestMapping map { x => (x._2, (x._1, x._1 + 1)) }

    AMRGraph(amrGraph.nodes, nodeSpans.toMap, amrGraph.arcs, amrGraph.originalArcs)

  }
}