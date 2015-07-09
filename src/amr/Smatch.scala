package amr
import scala.util.Random

object Smatch {

  val debug = false

  def fScore(AMR1: AMRGraph, AMR2: AMRGraph, attempts: Int = 4): (Double, Double, Double, Double) = {
    val smallGraph = if (AMR1.nodes.size < AMR2.nodes.size) AMR1 else AMR2
    val largeGraph = if (AMR1.nodes.size >= AMR2.nodes.size) AMR1 else AMR2
    val allScores = 1 to attempts map (_ => oneIteration(largeGraph, smallGraph))
    allScores.maxBy(_._1)
    // Returned tuple is (F-Score, Precision, Recall, Absolute Errors)
  }

  def oneIteration(AMR1: AMRGraph, AMR2: AMRGraph): (Double, Double, Double, Double) = {
    var currentBestMap = initialMap(AMR1, AMR2)
    var improvement = true
    var lastBestScore = (0.0, 0.0, 0.0, 0.0)
    do {
      var lastBestMap = currentBestMap
      currentBestMap = getBestMove(AMR1, AMR2, currentBestMap)
      var newScore = fScore(AMR1, AMR2, currentBestMap)
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

  def getBestMove(AMR1: AMRGraph, AMR2: AMRGraph, currentMap: Map[String, String]): Map[String, String] = {
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

    val allMoves = allVacantMoves ++ allSwapMoves

    val allScores = allMoves map (m => fScore(AMR1, AMR2, m))
    val maxScore = if (allScores.isEmpty) 0 else allScores.max
    (allScores zip allMoves) find (_._1 == maxScore) match {
      case None => Map.empty
      case Some((score, move)) => move
    }
  }

  def fScore(AMR1: AMRGraph, AMR2: AMRGraph, nodeMap: Map[String, String]): (Double, Double, Double, Double) = {
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
    (fScore, precision, recall, incorrect)
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

  // We simply replace all nodes in AMR2 with the name of the node to which they are mapped in AMR1
  // In the event of no mapping, we prepend an underscore to the name, so that we don't get random matches due
  // to coincident names
  def alias(node: String, nodeMap: Map[String, String]): String = {
    nodeMap.getOrElse(node, "_" + node)
  }

}