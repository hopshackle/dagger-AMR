package amr
import scala.util.Random
import scala.util.control._

object Smatch {

  val debug = true
  var useImprovedMapping = true
  var useSatisficing = true

  def fScore(AMR1: AMRGraph, AMR2: AMRGraph, attempts: Int = 4, movesToConsider: Int = 500): (Double, Double, Double, Double, Double) = {
    val smallGraph = if (AMR1.nodes.size < AMR2.nodes.size) AMR1 else AMR2
    val largeGraph = if (AMR1.nodes.size >= AMR2.nodes.size) AMR1 else AMR2
    val allScores = 1 to attempts map (_ => oneIteration(largeGraph, smallGraph, movesToConsider))
    val maxScore = allScores.maxBy(_._1)
    val precision = if (AMR1.nodes.size < AMR2.nodes.size) maxScore._2 else maxScore._3
    val recall = if (AMR1.nodes.size < AMR2.nodes.size) maxScore._3 else maxScore._2
    (maxScore._1, precision, recall, maxScore._4, maxScore._5)
    // Returned tuple is (F-Score, Precision, Recall, Absolute Errors)
    // We may have swapped the input graphs based on relative size, so we need to switch back precision/ recall in this case
  }

  def oneIteration(AMR1: AMRGraph, AMR2: AMRGraph, movesToConsider: Int): (Double, Double, Double, Double, Double) = {
    var currentBestMap = if (useImprovedMapping) initialMap2(AMR1, AMR2) else initialMap(AMR1, AMR2)
    var improvement = true
    var lastBestScore = (0.0, 0.0, 0.0, 0.0, 0.0)
    do {
      var lastBestMap = currentBestMap
      currentBestMap = getBestMove(AMR1, AMR2, currentBestMap, movesToConsider)
      var newScore = fScoreWithMap(AMR1, AMR2, currentBestMap)
      improvement = newScore._1 > lastBestScore._1
      if (improvement) lastBestScore = newScore
      if (!improvement) currentBestMap = lastBestMap
    } while (improvement)

    if (debug) {
      val amr1 = stringSeq(AMR1)
      val amr2 = stringSeq(AMR2, currentBestMap)
      val amr2raw = stringSeq(AMR2)
      println
      amr1 foreach println
      println
      amr2 foreach println
      println
      amr2raw foreach println
      println
      currentBestMap foreach { case (i, v) => println(i + " -> " + v) }
    }
    lastBestScore
  }

  def initialMap2(AMR1: AMRGraph, AMR2: AMRGraph): Map[String, String] = {
    def topologicalSimilarity(key1: String, key2: String, mapping: scala.collection.mutable.Map[String, String]): Double = {
      val parentsInCommon = (AMR2.parentsOf(key2) map { p => mapping.getOrElse(p, "NOT FOUND") } intersect AMR1.parentsOf(key1)).size
      val childrenInCommon = (AMR2.childrenOf(key2) map { c => mapping.getOrElse(c, "NOT FOUND") } intersect AMR1.childrenOf(key1)).size
      parentsInCommon + childrenInCommon
    }
    val iterations = 4
    var bestMapping = scala.collection.mutable.Map[String, String]()
    for (i <- 1 to iterations) {
      val amrNodes1 = AMR1.nodes
      val amrNodes2 = AMR2.nodes
      val similarities = (for {
        amrKey1 <- amrNodes1.keys
        val amrConcept1 = amrNodes1(amrKey1)
        amrKey2 <- amrNodes2.keys
        val amrConcept2 = amrNodes2(amrKey2)
        val conceptCount: Double = if (amrConcept1 == amrConcept2) (amrNodes1.values count (_ == amrConcept1)) +
          (amrNodes2.values count (_ == amrConcept1))
        else 0.0
        val similarity = (1.0 + topologicalSimilarity(amrKey1, amrKey2, bestMapping)) *
          (1.0 + (if (amrConcept1 == amrConcept2) 5.0 * Math.pow(0.5, conceptCount - 1) else 0.0))
        if similarity > 0.001
      } yield (amrKey1, amrKey2, similarity)).toSeq.sortWith((x, y) => x._3 > y._3)
      // We now have a list of all non-zero similarities, sorted in descending order of similarity
      var mappings = scala.collection.mutable.Map[String, String]()
      var amr1Mapped = scala.collection.mutable.Set[String]()
      var amr2Mapped = scala.collection.mutable.Set[String]()
      for ((amrKey1, amrKey2, similarity) <- similarities) {
        if (!(amr1Mapped contains amrKey1) && !(amr2Mapped contains amrKey2)) {
          amr1Mapped.add(amrKey1)
          amr2Mapped.add(amrKey2)
          mappings.put(amrKey2, amrKey1)
        }
      }
      bestMapping = mappings
    }

    val remainingNodes1 = AMR1.nodes.keySet -- bestMapping.values
    val remainingNodes2 = Random.shuffle(AMR2.nodes.keySet -- bestMapping.keys)

    // then the remainder get allocated entirely at random
    val remainder = (remainingNodes2 zip remainingNodes1).toMap

    bestMapping.toMap ++ remainder
  }

  def initialMap(AMR1: AMRGraph, AMR2: AMRGraph): Map[String, String] = {
    // returns a mapping from AMR2 to AMR1
    // first we map the exact matches on node concepts
    def findMatch(name: String, nodes: Map[String, String]): String = {
      // name is the value I need to match 
      val r = nodes.find(x => x._2 == name)
      r match {
        case None => ""
        case Some((name2, concept2)) => name2
      }
    }

    var unmappedNodes2 = AMR2.nodes
    var coincidentMappings = Map[String, String]()
    for ((name1, concept1) <- Random.shuffle(AMR1.nodes.toList)) {
      val matching = findMatch(concept1, unmappedNodes2)
      if (matching != "") {
        unmappedNodes2 = unmappedNodes2 - matching
        coincidentMappings = coincidentMappings + (matching -> name1)
      }
    }

    val remainingNodes1 = AMR1.nodes.keySet -- coincidentMappings.values
    val remainingNodes2 = Random.shuffle(AMR2.nodes.keySet -- coincidentMappings.keys)

    // then the remainder get allocated entirely at random
    val remainder = (remainingNodes2 zip remainingNodes1).toMap

    coincidentMappings ++ remainder
  }

  def getBestMove(AMR1: AMRGraph, AMR2: AMRGraph, currentMap: Map[String, String], movesToConsider: Int): Map[String, String] = {
    // We know that AMR1 is the larger of the two graphs (defined by number of nodes)
    val allVacantMoves = for {
      (i, name) <- AMR2.nodes
      j <- (AMR1.nodes.keySet -- currentMap.values)
    } yield currentMap + (i -> j)

    val allSwapMoves = for {
      (i, iPointer) <- currentMap
      (j, jPointer) <- currentMap
      if i != j
    } yield currentMap + (i -> jPointer) + (j -> iPointer)

    val allMoves = Random.shuffle(allVacantMoves ++ allSwapMoves).take(movesToConsider)
    val startScore = fScoreWithMap(AMR1, AMR2, currentMap)
    var bestMove = currentMap

    val loop = new Breaks;
    var bestScore = startScore._1
    loop.breakable {
      for (move <- allMoves) {
        val score = fScoreWithMap(AMR1, AMR2, move)
        if (useSatisficing && score._1 > startScore._1) {
          bestMove = move
          loop.break
        }
        if (!useSatisficing && score._1 > bestScore) {
          bestScore = score._1
          bestMove = move
        }
      }
    }
    bestMove
  }

  def fScoreWithMap(AMR1: AMRGraph, AMR2: AMRGraph, nodeMap: Map[String, String]): (Double, Double, Double, Double, Double) = {
    // nodeMap is keyed on AMR2 node names, and links them to node names in AMR1
    // i.e. AMR1 (larger) is our fixed reference point around which we permute AMR2 (smaller)
    // Rather than get fiddly - we convert each graph to a Seq of String representations of the triples
    // (taking into account the mapping of node names between AMR1 and AMR2) and then match directly on these strings
    val amr1 = stringSeq(AMR1)
    val amr2 = stringSeq(AMR2, nodeMap)

    val matches: Double = if (amr1.size > amr2.size) (amr2 map (a => if (amr1 contains a) 1 else 0)).sum else (amr1 map (a => if (amr2 contains a) 1 else 0)).sum
    val precision = matches / amr2.size
    val recall = matches / amr1.size
    val incorrect = amr2.size - matches + amr1.size - matches
    val fScore = if (precision + recall > 0.00)
      (2 * precision * recall) / (precision + recall)
    else 0.00
    (fScore, precision, recall, incorrect, matches)
  }

  def naiveFScore(AMR1: AMRGraph, AMR2: AMRGraph, attempts: Int = 4, movesToConsider: Int = 500): (Double, Double, Double, Double, Double) = {
    val triples1 = naiveStringSeq(AMR1)
    val triples2 = naiveStringSeq(AMR2)
    val oneNotTwo = triples1 diff triples2 size
    val twoNotOne = triples2 diff triples1 size
    val matches: Double = Math.min(triples1.size - oneNotTwo, triples2.size - twoNotOne)
    val precision = matches / triples2.size
    val recall = matches / triples1.size
    val incorrect = triples2.size - matches + triples1.size - matches
    val fScore = if (precision + recall > 0.00)
      (2 * precision * recall) / (precision + recall)
    else 0.00
    (fScore, precision, recall, incorrect, matches)
  }

  def stringSeq(AMR: AMRGraph): Seq[String] = {
    val nodes = AMR.nodes map (node => node._1 + ":" + node._2)
    val arcs = AMR.arcs map {
      case ((source, dest), arcName) => source + ":" + arcName + ":" + dest
    }
    (nodes ++ arcs).toSeq
  }

  def stringSeq(AMR: AMRGraph, nodeMap: Map[String, String]): Seq[String] = {
    val nodes = AMR.nodes map (node => alias(node._1, nodeMap) + ":" + node._2)
    val arcs = AMR.arcs map {
      case ((source, dest), arcName) => alias(source, nodeMap) + ":" + arcName + ":" + alias(dest, nodeMap)
    }
    (nodes ++ arcs).toSeq
  }

  def naiveStringSeq(AMR: AMRGraph): Seq[String] = {
    val nodes = AMR.nodes map (_._2)
    val arcs = AMR.arcs map {
      case ((source, dest), arcName) => AMR.nodes(source) + ":" + arcName + ":" + AMR.nodes(dest)
    }
    (nodes ++ arcs).toSeq
  }

  // We simply replace all nodes in AMR2 with the name of the node to which they are mapped in AMR1
  // In the event of no mapping, we prepend an underscore to the name, so that we don't get random matches due
  // to coincident names
  def alias(node: String, nodeMap: Map[String, String]): String = {
    nodeMap.getOrElse(node, "_" + node)
  }

}