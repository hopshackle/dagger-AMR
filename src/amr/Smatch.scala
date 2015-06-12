package amr
import scala.util.Random

object Smatch {

  def fScore(AMR1: AMRGraph, AMR2: AMRGraph): Double = {
    val smallGraph = if (AMR1.nodes.size < AMR2.nodes.size) AMR1 else AMR2
    val largeGraph = if (AMR1.nodes.size >= AMR2.nodes.size) AMR1 else AMR2
    val allScores = 1 to 5 map (_ => oneIteration(largeGraph, smallGraph))
    allScores.max
  }
  
  def oneIteration(AMR1: AMRGraph, AMR2: AMRGraph): Double = {
      var currentBestMap = initialMap(AMR1, AMR2)
      var improvement = true
      do {
        var lastBestMap = currentBestMap
        currentBestMap = getBestMove(AMR1, AMR2, currentBestMap)
        improvement = fScore(AMR1, AMR2, currentBestMap) > fScore(AMR1, AMR2, lastBestMap)
        if (!improvement) currentBestMap = lastBestMap
      } while (improvement)
      fScore(AMR1, AMR2, currentBestMap)
  }
  
  def initialMap(AMR1: AMRGraph, AMR2: AMRGraph): Map[String, String] = {
    // returns a mapping from AMR2 to AMR1
    // first we map the exact matches on node concepts
    val coincidentNodes = for {
      (name1, concept1) <- AMR1.nodes
      (name2, concept2) <- AMR2.nodes
      if concept1 == concept2
    } yield (name2, name1)
    // TODO:  this can have some missing...if we have two or more copies of one concept in both graphs...
    // this could be a problem with lots of 'and's for example
    val remainingNodes1 = AMR1.nodes.keySet -- coincidentNodes.values
    val remainingNodes2 = Random.shuffle(AMR2.nodes.keySet -- coincidentNodes.keys)
        
    // then the remainder get allocated entirely at random
    val remainder = (remainingNodes2 zip remainingNodes1).toMap

    coincidentNodes ++ remainder
  }
  
  def getBestMove(AMR1: AMRGraph, AMR2: AMRGraph, currentMap: Map[String, String]): Map[String, String] = {
    // We know that AMR1 is the larger of the two graphs (defined by number of nodes)
    val allVacantMoves = for {
      (i, name) <- AMR2.nodes
      j <- AMR1.nodes.keySet -- currentMap.values
    } yield currentMap - (i, name) + (i -> j)
    
    val allSwapMoves = for {
      (i, iPointer) <- currentMap
      (j, jPointer) <- currentMap
      if i != j
    } yield currentMap - (i, iPointer) - (j, jPointer) + (i -> jPointer) + (j -> iPointer)
    
    val allMoves = allVacantMoves ++ allSwapMoves
    
    val allScores = allMoves map (m => fScore(AMR1, AMR2, m))
    val maxScore = allScores.max
    (allScores zip allMoves) find (_._1 == maxScore) match {
      case None => Map.empty
      case Some((score, move)) => move
    }
  }
  
  def fScore(AMR1: AMRGraph, AMR2: AMRGraph, nodeMap: Map[String, String]) : Double = {
    // nodeMap is keyed on AMR2 node names, and links them to node names in AMR1
    // i.e. AMR1 is our fixed reference point around which we permute AMR2
    // Rather than get fiddly - we convert each graph to a Seq of String representations of the triples
    // (taking into account the mapping of node names between AMR1 and AMR2) and then match directly on these strings
    val amr1 = stringSeq(AMR1)
    val amr2 = stringSeq(AMR2, nodeMap)
    
    val matches: Double = (amr1 map (a => if (amr2 contains a) 1 else 0)).sum
    val precision = matches / amr2.size
    val recall = matches / amr1.size
    if (precision + recall > 0.00)
      (2 * precision * recall) / (precision + recall)
    else 0.00
  }
    
  def stringSeq(AMR: AMRGraph): Seq[String] = {
    val nodes = AMR.nodes map (node => node._1 + ":" + node._2)
    val arcs = AMR.arcs map ( input => input match {
      case ((source, dest), arcName) => source + ":" + arcName + ":" + dest
    })

    (nodes ++ arcs).toSeq
  }
  
  def stringSeq(AMR: AMRGraph, nodeMap: Map[String, String]): Seq[String] = {
    val nodes = AMR.nodes map (node => alias(node._1, nodeMap) + ":" + node._2)
    val arcs = AMR.arcs map ( input => input match {
      case ((source, dest), arcName) => alias(source, nodeMap) + ":" + arcName + ":" + alias(dest, nodeMap)
    })

    (nodes ++ arcs).toSeq
  }
  
  // We simply replace all nodes in AMR2 with the name of the node to which they are mapped in AMR1
  // In the event of no mapping, we prepend an underscore to the name, so that we don't get random matches due
  // to coincident names
  def alias(node: String, nodeMap: Map[String, String]): String = {
    val lookup = nodeMap.get(node)
    lookup match {
      case None => "_" + node
      case Some(output) => output
    }
  }
  
  
  
}