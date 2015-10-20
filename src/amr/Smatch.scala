package amr
import scala.util.Random
import scala.util.control._

object Smatch {

  val debug = false
  var useImprovedMapping = false
  var useSatisficing = true
  val quote = """"""".r
  val numbers = "[0-9.,]".r
  val op = "^op[0-9]+$".r
  val ARGof = "-of$".r

  def fScore(AMR1: AMRGraph, AMR2: AMRGraph, attempts: Int = 4, movesToConsider: Int = 500): (Double, Double, Double, Double, Int, Int, Int) = {
    val reduced1 = reduceAMR(AMR1)
    val reduced2 = reduceAMR(AMR2)
    val smallGraph = if (reduced1.nodes.size < reduced2.nodes.size) reduced1 else reduced2
    val largeGraph = if (reduced1.nodes.size >= reduced2.nodes.size) reduced1 else reduced2
    val allScores = 1 to attempts map (_ => oneIteration(largeGraph, smallGraph, movesToConsider))
    val maxScore = allScores.maxBy(_._1)
    val precision = if (reduced1.nodes.size < reduced2.nodes.size) maxScore._2 else maxScore._3
    val recall = if (reduced1.nodes.size < reduced2.nodes.size) maxScore._3 else maxScore._2
    val triples1 = if (reduced1.nodes.size < reduced2.nodes.size) maxScore._6 else maxScore._7
    val triples2 = if (reduced1.nodes.size < reduced2.nodes.size) maxScore._7 else maxScore._6
    (maxScore._1, precision, recall, maxScore._4, maxScore._5, triples1, triples2)
    // Returned tuple is (F-Score, Precision, Recall, errors, matches, triples1, triples2)
    // We may have swapped the input graphs based on relative size, so we need to switch back precision/ recall in this case
  }

  def oneIteration(AMR1: AMRGraph, AMR2: AMRGraph, movesToConsider: Int): (Double, Double, Double, Double, Int, Int, Int) = {
    var currentBestMap = if (useImprovedMapping) initialMap2(AMR1, AMR2) else initialMap(AMR1, AMR2)
    var improvement = true
    var lastBestScore = (0.0, 0.0, 0.0, 0.0, 0, 0, 0)
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
 //           println
 //     amr1 foreach println
 //     println
 //     amr2 foreach println
 //     println
 //     amr2raw foreach println
//      println  */
      currentBestMap foreach { case (i, v) => println(s"$i [${AMR2.nodes(i)}] -> $v [${AMR1.nodes(v)}] ") }
      println()
      // mappings are keyed on 2
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
        // TODO: Add some factor in for common attributes, now that these are not actually concepts 
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

    val remainingNodes1 = Random.shuffle(AMR1.nodes.keySet -- bestMapping.values)
    val remainingNodes2 = AMR2.nodes.keySet -- bestMapping.keys

    // then the remainder get allocated entirely at random
    val remainder = (remainingNodes2 zip remainingNodes1).toMap

    bestMapping.toMap ++ remainder
  }

  def initialMap(AMR1: AMRGraph, AMR2: AMRGraph): Map[String, String] = {
    // returns a mapping from AMR2 to AMR1
    // first we map the exact matches on node concepts

    // TODO: Take account of attributes

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

    val remainingNodes1 = Random.shuffle((AMR1.nodes.keySet -- coincidentMappings.values).toSeq)
    val remainingNodes2 = Random.shuffle((AMR2.nodes.keySet -- coincidentMappings.keys).toSeq)
    if (debug) print(s"AMR1 remaining: ${remainingNodes1.size}, AMR2: ${remainingNodes2.size}\n")

    // then the remainder get allocated entirely at random
    val remainder = (remainingNodes2 zip remainingNodes1).toMap

    coincidentMappings ++ remainder
  }

  def getBestMove(AMR1: AMRGraph, AMR2: AMRGraph, currentMap: Map[String, String], movesToConsider: Int): Map[String, String] = {
    // We know that AMR1 is the larger of the two graphs (defined by number of nodes)
    val startScore = fScoreWithMap(AMR1, AMR2, currentMap)

    val allVacantMoves = for {
      (i, name) <- AMR2.nodes
      j <- (AMR1.nodes.keySet -- currentMap.values)
    } yield Map(i -> j)
    
  //  if (debug) allVacantMoves foreach println

    val allSwapMoves = for {
      (i, iPointer) <- currentMap
      (j, jPointer) <- currentMap
      if i != j
    } yield Map((i -> jPointer), (j -> iPointer))

    val allMoves = Random.shuffle(allVacantMoves ++ allSwapMoves).take(movesToConsider)
    var bestMove = Map[String, String]()

    val loop = new Breaks;
    var bestScore = startScore._1
    loop.breakable {
      for (move <- allMoves) {
        val score = fScoreWithMap(AMR1, AMR2, currentMap ++ move)
   //     if (debug) println(f"$move gives score of ${score._1}%.3f")
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
    if (debug) println("Best Move: " + bestMove)
    currentMap ++ bestMove
  }

  def fScoreWithMap(AMR1: AMRGraph, AMR2: AMRGraph, nodeMap: Map[String, String]): (Double, Double, Double, Double, Int, Int, Int) = {
    // nodeMap is keyed on AMR2 node names, and links them to node names in AMR1
    // i.e. AMR1 (larger) is our fixed reference point around which we permute AMR2 (smaller)
    // Rather than get fiddly - we convert each graph to a Seq of String representations of the triples
    // (taking into account the mapping of node names between AMR1 and AMR2) and then match directly on these strings
    val amr1 = stringSeq(AMR1)
    val amr2 = stringSeq(AMR2, nodeMap)

    val matches: Double = if (amr1.size > amr2.size) (amr2 map (a => if (amr1 contains a) 1 else 0)).sum else (amr1 map (a => if (amr2 contains a) 1 else 0)).sum
    val precision = if (amr2.size == 0) 0.0 else matches / amr2.size
    val recall = if (amr1.size == 0) 0.0 else matches / amr1.size
    val incorrect = amr2.size - matches + amr1.size - matches
    val fScore = if (precision + recall > 0.00)
      (2 * precision * recall) / (precision + recall)
    else 0.00
    (fScore, precision, recall, incorrect, matches.toInt, amr1.size, amr2.size)
  }

  def naiveFScore(AMR1: AMRGraph, AMR2: AMRGraph, attempts: Int = 4, movesToConsider: Int = 500): (Double, Double, Double, Double, Int, Int, Int) = {
    val triples1 = naiveStringSeq(AMR1)
    val triples2 = naiveStringSeq(AMR2)
    val oneNotTwo = triples1 diff triples2 size
    val twoNotOne = triples2 diff triples1 size
    val matches = Math.min(triples1.size - oneNotTwo, triples2.size - twoNotOne)
    val precision = if (triples2.size == 0) 0.00 else matches.toDouble / triples2.size
    val recall = if (triples1.size == 0) 0.00 else matches.toDouble / triples1.size
    val incorrect = triples2.size - matches + triples1.size - matches
    val fScore = if (precision + recall > 0.00)
      (2 * precision * recall) / (precision + recall)
    else 0.00
    (fScore, precision, recall, incorrect, matches, triples1.size, triples2.size)
  }

  def stringSeq(AMR: AMRGraph): Seq[String] = {
    val nodes = AMR.nodes map (node => node._1 + ":" + node._2)
    val arcs = for {
      ((s, d), relation) <- AMR.arcs
      val (source, dest, arcName) = ARGof.findFirstIn(relation) match {
        case None => (s, d, relation)
        case Some(_) => (d, s, relation.substring(0, relation.size - 3))
      }
    } yield (source + ":" + arcName + ":" + dest)
 /*   
    val arcs = AMR.arcs map {
      case ((source, dest), arcName) => source + ":" + arcName + ":" + dest
    }
 */   
    val attributes = AMR.attributes map {
      case (node, attrType, attrValue) => node + ":" + attrType + ":" + attrValue
    }
    (nodes ++ arcs ++ attributes).toSeq
  }

  def stringSeq(AMR: AMRGraph, nodeMap: Map[String, String]): Seq[String] = {
    val nodes = AMR.nodes map (node => alias(node._1, nodeMap) + ":" + node._2)
    
    val arcs = for {
      ((s, d), relation) <- AMR.arcs
      val (source, dest, arcName) = ARGof.findFirstIn(relation) match {
        case None => (alias(s, nodeMap), alias(d, nodeMap), relation)
        case Some(_) => (alias(d, nodeMap), alias(s, nodeMap), relation.substring(0, relation.size - 3))
      }
    } yield (source + ":" + arcName + ":" + dest)
    
    val attributes = AMR.attributes map {
      case (node, attrType, attrValue) => alias(node, nodeMap) + ":" + attrType + ":" + attrValue
    }
    (nodes ++ arcs ++ attributes).toSeq
  }

  def naiveStringSeq(AMR: AMRGraph): Seq[String] = {
    val nodes = AMR.nodes map (_._2)
    val arcs = for {
      ((s, d), relation) <- AMR.arcs
      val (source, dest, arcName) = ARGof.findFirstIn(relation) match {
        case None => (s, d, relation)
        case Some(_) => (d, s, relation.substring(0, relation.size - 3))
      }
    } yield (source + ":" + arcName + ":" + dest)
    
    val attributes = AMR.attributes map {
      case (node, attrType, attrValue) => AMR.nodes(node) + ":" + attrType + ":" + attrValue
    }
    (nodes ++ arcs ++ attributes).toSeq
  }

  // We simply replace all nodes in AMR2 with the name of the node to which they are mapped in AMR1
  // In the event of no mapping, we prepend an underscore to the name, so that we don't get random matches due
  // to coincident names
  def alias(node: String, nodeMap: Map[String, String]): String = {
    nodeMap.getOrElse(node, "_" + node)
  }

  def reduceAMR(input: AMRGraph): AMRGraph = {
    // For Smatch calculation, any node that is:
    //  - numeric
    //  - -
    //  - in quotes
    // is not treated as a node in its own right, but as an attribute of the node it is linked to
    // (if it's not a leaf node, then we can't do this, and have to leave it as an incorrect node)

    // So what we do here, is remove any leaf nodes from the graph, and store their values in an attribute list for the node
    
    val newNodes = for {
      (n, v) <- input.nodes
      if !(input.isLeafNode(n) && (numbers.replaceAllIn(v, "") == "" || quote.findFirstIn(v) != None || v == "-"))
    } yield (n, quote.replaceAllIn(v, ""))

    val arcsToUse = if (input.originalArcs.isEmpty) input.arcs else input.originalArcs
    
    val arcsWithOpN = arcsToUse map {
      case ((source, dest), relation) if relation != "opN" => ((source, dest), relation)
      case ((source, dest), relation) =>
        val opEdges = input.edgesToChildren(source).filter(e => input.arcs(e) == "opN").
          sortWith(AMROutput.sortByPositionInSentence(input)).zipWithIndex.toMap
        ((source, dest), "op" + (opEdges((source, dest)) + 1))
    }

    val attributeNodes = input.nodes filter {
      case (node, value) => !newNodes.contains(node)
    }
    val newArcs = arcsWithOpN filter {
      case ((source, dest), relation) => !(attributeNodes.contains(dest) || attributeNodes.contains(source))
    }

    val attributes = (attributeNodes map {
      case (node, value) =>
          val allP = input.parentsOf(node)
          if (allP.nonEmpty) {
            val p = allP.head
          (p, arcsWithOpN((p, node)), value) 
          } else {
            ("R", "R", value)
          }
    }).toList ++ (input.getRoots map {r => (r, "ROOT", input.nodes(r))})
    
    input.copy(nodes = newNodes, arcs = newArcs, attributes = attributes)
  }
}