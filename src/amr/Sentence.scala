
package amr

import edu.cmu.lti.nlp.amr._
import scala.collection.SortedMap
import amr.ImportConcepts.{ concept, relation, conceptIndex }

abstract class Graph[K] {
  def nodes: Map[K, String]
  def nodeSpans: Map[K, (Int, Int)]
  def arcs: Map[(K, K), String]
  def isRoot(node: K): Boolean = edgesToParents(node).isEmpty
  val debug = false
  def depth(node: K): Int = {
    def depthHelper(nodes: Seq[K], accum: Int): Int = {
      if (accum > 50) { println("Depth of tree exceeds 50: " + (if (debug) "\n" + this.toString)); accum } else {
        val nextNodes = (nodes map edgesToParents flatten) map { case (parent, child) => parent }
        if (nextNodes exists isRoot) accum else depthHelper(nextNodes, accum + 1)
      }
    }
    if (isRoot(node)) 0 else depthHelper(Seq(node), 1)
  }
  def edgesToParents(node: K): Seq[(K, K)] = (arcs filter (x => x match { case ((p, c), l) => c == node })).keys.toSeq filter (_ != node)
  def parentsOf(node: K): Seq[K] = edgesToParents(node) map { case (p, c) => p }
  def parentLabels(node: K): Seq[String] = (parentsOf(node) map (x => nodes(x)))
  def getNeighbourhood(node: K, range: Int): Set[K] = {
    range match {
      case 1 => (parentsOf(node) ++ childrenOf(node)).toSet
      case n => {
        val immediate = getNeighbourhood(node, 1)
        (immediate flatMap (getNeighbourhood(_, n - 1))) ++ immediate - node
      }
    }
  }
  def childrenOf(node: K): Seq[K] = edgesToChildren(node) map { case (p, c) => c }
  def labelsBetween(parent: K, child: K): Seq[String] = (arcs filter { case ((p, c), l) => p == parent && c == child } map { case ((p, c), l) => l }).toSeq
  def edgesToChildren(node: K): Seq[(K, K)] = (arcs filter (x => x match { case ((p, c), l) => p == node })).keys.toSeq filter (_ != node)
  def isLeafNode(node: K): Boolean = { !(arcs.keys exists (_ match { case (f, t) => f == node })) }
  def getRoots: Set[K] = nodes.keySet filter isRoot
  def getNodesBetween(node1: K, node2: K): Seq[K] = {
    def findPathTo(currentPaths: Seq[Seq[K]], target: K, acc: Int): Seq[K] = {
      if (acc > 30) {
        println("Path search has exceeded 30 nodes between " + node1 + " and " + node2 + ". Giving up." + (if (debug) "\n" + this.toString));
        Seq()
      } else {
        val o = for {
          currentPath <- currentPaths
          val successorNodes = (parentsOf(currentPath.head) ++ childrenOf(currentPath.head)) diff currentPath
        } yield successorNodes map (_ +: currentPath)
        val updatedPaths = o.flatten
        if (updatedPaths exists (_.head == target)) {
          updatedPaths.find { x => x.head == target }.get
        } else {
          findPathTo(updatedPaths, target, acc + 1)
        }
      }
    }
    val start = Seq(Seq(node1))
    if (node1 == node2) {
      Seq(node1)
    } else
      findPathTo(start, node2, 0)
  }

  def getDistanceBetween(node1: K, node2: K): Int = {
    val path = getNodesBetween(node2, node1)
    if (path.isEmpty) 20 else Math.max(path.size - 1, 0)
  }

  private def subGraph(node: K, acc: Int): Set[K] = {
    if (acc > 50) { println("Depth of subgraph exceeds 50 from " + node + (if (debug) " : \n" + this.toString)); Set(node) } else {
      if (isLeafNode(node)) Set(node)
      else (childrenOf(node) flatMap (subGraph(_, acc + 1))).toSet + node
    }
  }
  def subGraph(node: K): Set[K] = {
    subGraph(node, 0)
  }
  def getDescendants(parentList: Seq[K]): Seq[K] = {
    val allChildren = (parentList flatMap childrenOf) ++ parentList
    if (allChildren.distinct.size == parentList.distinct.size)
      parentList // we're done
    else
      getDescendants(allChildren.distinct)
  }
  def getAncestors(list: Seq[K]): Seq[K] = {
    val allParents = list ++ (list flatMap parentsOf)
    if (allParents.distinct.size == list.distinct.size)
      list // we're done
    else
      getAncestors(allParents.distinct)
  }

}

case class AMRGraph(nodes: Map[String, String], nodeSpans: Map[String, (Int, Int)], arcs: Map[(String, String), String],
  originalArcs: Map[(String, String), String] = Map(),
  attributes: List[(String, String, String)] = List()) extends Graph[String] {
  def toOutputFormat: String = {
    val nodeOutput = nodes.keys.toList map (x => s"# ::node\t${x}\t${nodes(x)}\n")
    val arcOutput = arcs map (x => s"# ::edge\t${x._1._1}\t${x._1._2}\t${x._2}\n")

    "# ::AMRGraph\n" + nodeOutput.mkString + arcOutput.mkString
  }
}

case class DependencyTree(nodes: Map[Int, String], nodeLemmas: Map[Int, String], nodePOS: Map[Int, String], nodeNER: Map[Int, String],
  nodeSpans: Map[Int, (Int, Int)], arcs: Map[(Int, Int), String], depLabels: Map[Int, String], reattachedNodes: List[Int],
  insertedNodes: Map[Int, String], mergedNodes: Map[Int, List[(Int, String)]], swappedArcs: Set[(Int, Int)], deletedNodes: Map[Int, List[(Int, String)]]) extends Graph[Int] {
  val numbers = "[0-9.,]".r

  def toOutputFormat: String = {
    val nodeOutput = nodes.keys.toList map (x => s"# ::node\t${x}\t${nodes(x)}\t${nodeSpans(x)._1}\t${nodeSpans(x)._2}\n")
    val arcOutput = arcs map (x => s"# ::edge\t${x._1._1}\t${x._1._2}\t${x._2}\n")

    "# ::SpanGraph\n" + nodeOutput.mkString + arcOutput.mkString
  }

  def labelArc(parent: Int, child: Int, label: String): DependencyTree = {
    this.copy(arcs = arcs + ((parent, child) -> label))
  }

  def labelNode(node: Int, label: String): DependencyTree = {
    val newNodes = nodes + (node -> label)
    this.copy(nodes = newNodes)
  }

  def removeNode(node: Int): DependencyTree = {
    // only valid for a leaf node with no children
    assert(isLeafNode(node))
    val edgesToRemove = edgesToParents(node)
    val parent = parentsOf(node).getOrElse(0, -1)
    val alreadyDeletedNodes = this.deletedNodes.get(parent) match { case None => List(); case Some(deletedNodes) => deletedNodes }
    val newDeletedNodes = (node, this.nodes(node)) :: alreadyDeletedNodes
    this.copy(nodes = this.nodes - node, nodeSpans = this.nodeSpans - node, arcs = this.arcs -- edgesToRemove,
      deletedNodes = this.deletedNodes + (parent -> newDeletedNodes))
  }

  def insertNodeAbove(node: Int, conceptIndex: Int, otherRef: String): (Int, DependencyTree) = {
    // So we to add a new node as a parent, remove the edge from the current node to its current parent, and insert two new edges
    // ...parent to new, and new to current
    // we assign a nodeSpan to the new node to match its child
    // we also take the DL and POS labelling and from old parent to child to be between old parent and new node
    val newNode = nodes.keys.max + 1
    val childSpan = nodeSpans.getOrElse(node, (0, 0))
    val newEdgesFromParent = edgesToParents(node) map { case (from, to) => ((from, newNode), arcs((from, to))) }
    val newInsertedNodes = this.insertedNodes + (newNode -> otherRef)
    val newEdgeFromNode = ((newNode, node), concept(conceptIndex) + "#") // dependency label made up for use as feature
    (newNode, this.copy(nodes = this.nodes + (newNode -> concept(conceptIndex)), nodeLemmas = this.nodeLemmas + (newNode -> concept(conceptIndex)),
      nodeSpans = this.nodeSpans + (newNode -> childSpan), arcs = this.arcs -- edgesToParents(node) ++ newEdgesFromParent + newEdgeFromNode,
      nodePOS = this.nodePOS + (newNode -> this.nodePOS(node)), depLabels = this.depLabels + (newNode -> this.depLabels(node)), 
      insertedNodes = newInsertedNodes))
  }
  def insertNodeBelow(node: Int, conceptIndex: Int, otherRef: String, label: String = ""): (Int, DependencyTree) = {
    val newNode = nodes.keys.max + 1
    val parentSpan = nodeSpans.getOrElse(node, (0, 0))
    val newInsertedNodes = this.insertedNodes + (newNode -> otherRef)
    val labelToUse = if (label != "") label else concept(conceptIndex) + "#" // label made up for use as feature
    val newEdgeToNode = ((node, newNode), labelToUse)
    (newNode, this.copy(nodes = this.nodes + (newNode -> concept(conceptIndex)), nodeLemmas = this.nodeLemmas + (newNode -> concept(conceptIndex)),
      nodeSpans = this.nodeSpans + (newNode -> parentSpan), arcs = this.arcs + newEdgeToNode,
      insertedNodes = newInsertedNodes))
  }

  def mergeNodes(nodeToRemove: Int, nodeToKeep: Int): DependencyTree = {
    val currentEdges = edgesToChildren(nodeToKeep) map { case (from, to) => ((from, to), arcs((from, to))) }
    val newEdgesIn = edgesToParents(nodeToRemove) map { case (from, to) => ((from, nodeToKeep), arcs((from, to))) }
    val newEdgesOut = edgesToChildren(nodeToRemove) map { case (from, to) => ((nodeToKeep, to), arcs((from, to))) }
    val oldEdges = edgesToParents(nodeToRemove) ++ edgesToChildren(nodeToRemove)
    // We also need to avoid over-writing any edges already processed
    val newSpans = this.nodeSpans - nodeToRemove + (nodeToKeep -> mergedSpan(nodeToRemove, nodeToKeep))
    val alreadyMergedNodes = this.mergedNodes.get(nodeToKeep) match { case None => List(); case Some(mergedNodes) => mergedNodes }
    val newMergedNodes = (nodeToRemove, this.nodes(nodeToRemove)) :: alreadyMergedNodes
    this.copy(nodes = this.nodes - nodeToRemove, nodeSpans = newSpans, arcs = this.arcs -- oldEdges ++ newEdgesIn ++ newEdgesOut ++ currentEdges - ((nodeToKeep, nodeToKeep)),
      mergedNodes = this.mergedNodes + (nodeToKeep -> newMergedNodes))
  }

  def swapArc(currentParent: Int, currentChild: Int): DependencyTree = {
    val currentLabel = labelsBetween(currentParent, currentChild) match {
      case Nil =>
        assert(false, "No such arc to swap in DependencyTree.swapArc " + currentParent + " -> " + currentChild + "\n" + this); "NONE"
      case head :: tail => head
    }
    val otherEdgestoChild = edgesToParents(currentChild) diff Seq((currentParent, currentChild)) map { case arc=> (arc, arcs(arc)) }
    val newEdgesFromParent = edgesToParents(currentParent) map { case (from, to) => ((from, currentChild), arcs((from, to))) }
    this.copy(arcs = this.arcs -- edgesToParents(currentParent) ++ newEdgesFromParent ++ otherEdgestoChild - ((currentParent, currentChild)) + ((currentChild, currentParent) -> currentLabel),
      swappedArcs = this.swappedArcs + ((currentParent, currentChild)))
  }

  def mergedSpan(node1: Int, node2: Int): (Int, Int) = {
    val (s1, e1) = nodeSpans.getOrElse(node1, (0, 0))
    val (s2, e2) = nodeSpans.getOrElse(node2, (0, 0))
    val s = Math.min(if (s1 == 0) 999 else s1, if (s2 == 0) 999 else s2)
    val e = Math.max(if (s1 == 0) 999 else s1, if (s2 == 0) 999 else s2)
    (if (s == 999) 0 else s, if (e == 999) 0 else e)
  }

  def getPathBetween(node1: Int, node2: Int): String = {
    // starting with node1, we're just conducting a search until we hit node2
    val path = getNodesBetween(node2, node1)
    val slidingPath = path.sliding(2)
    val pathString = nodePOS.getOrElse(path.head, "XX") + "-" +
      (for {
        a <- slidingPath
        val b = a match {
          case first :: second :: tail => {
            val label = if (labelsBetween(first, second).isEmpty) labelsBetween(second, first).head else labelsBetween(first, second).head
            val pos = nodePOS.getOrElse(second, "XX")
            label + "-" + pos
          }
          case _ => "ERR"
        }
      } yield b).mkString("-")
    pathString
  }

  override def toString: String = {
    val nodeSort = nodes.foldLeft(SortedMap[Int, String]()) { case (start, (a, b)) => start + (a -> b) }
    val spanSort = nodeSpans.foldLeft(SortedMap[Int, (Int, Int)]()) { case (start, (a, b)) => start + (a -> b) }
    val edgeSort = arcs.foldLeft(SortedMap[(Int, Int), String]()) { case (start, (a, b)) => start + (a -> b) }
    "\nNodeMap:\t" + nodeSort.toString +
      "\nSpanMap:\t" + spanSort.toString +
      "\nEdges:\t" + edgeSort.toString +
      "\nInsertedNodes:\t" + insertedNodes.toString +
      "\nMergedNodes:\t" + mergedNodes.toString +
      "\nSwappedArcs:\t" + swappedArcs.toString +
      "\nDeletedNodes:\t" + deletedNodes.toString
  }

  def toAMR: AMRGraph = {

    def extractConcept(node: Int): String = {
      val quote = """""""
      val oldValue = nodes(node)
      val parentNodeIsName = (parentsOf(node) map nodes contains "name") && !(oldValue contains quote)
      if (parentNodeIsName) quote + oldValue + quote else oldValue
    }

    val amrNodes = nodes map { case (key: Int, value: Any) => (key.toString -> extractConcept(key)) }
    val amrNodeSpan = nodeSpans map { case (key: Int, value: Any) => (key.toString -> value) }
    val amrArcs = arcs map { case (key: (Int, Int), value: Any) => ((key._1.toString, key._2.toString) -> value) }
    AMRGraph(amrNodes, amrNodeSpan, amrArcs)
  }
}

case class Sentence(rawText: String, dependencyTree: DependencyTree, amr: Option[AMRGraph], positionToAMR: Map[Int, String]) {
  val AMRToPosition: Map[String, Int] = positionToAMR map { case (i, s) => (s -> i) }
  def unmatchedAMRNodesByConcept: Map[String, String] = amr match {
    case None => Map()
    case Some(graph) =>
      val filterForInitialMatches = graph.nodes filter {
        case (key, concept) =>
          !(AMRToPosition contains key)
      }
      val filterForInsertedMatches = filterForInitialMatches filter {
        case (key, concept) =>
          !(dependencyTree.insertedNodes.values.toList contains key)
      }
      val finalResult = filterForInsertedMatches map { case (key, concept) => (concept -> key) }
      finalResult
  }
}

object Sentence {
  val debug = false
  def apply(sentence: String): Sentence = {
    Sentence(sentence, DependencyTree(sentence), None, Map[Int, String]())
  }
  def apply(sentence: String, rawAMR: String): Sentence = {
    Sentence(sentence, DependencyTree(sentence), Some(AMRGraph(rawAMR, sentence)))
  }
  def apply(sentence: String, amr: Option[AMRGraph]): Sentence = {
    Sentence(sentence, DependencyTree(sentence), amr)
  }
  def apply(sentence: String, dt: DependencyTree, amr: Option[AMRGraph]): Sentence = {
    Sentence(sentence, dt, amr, positionToAMR(amr, dt))
  }

  def positionToAMR(amr: Option[AMRGraph], dt: DependencyTree): Map[Int, String] = {
    // We run through each set of AMR nodes - i.e. that share a NodeSpan
    // We then get all the DT nodes for those positions
    // If 1:1 then we're done
    // If 1:0 (i.e. AMR has nodeSpan of 0, 0) then we're also done [no entry in Map]
    // If m:n then:
    //   Find good text matches between AMR concept and word or lemma in DT node and assign greedily.
    //   For those left over: 
    //      m > n : unassigned AMR nodes mapped to random top-most DT nodes. Surplus DT nodes not entered into map
    //      n > m : unassigned DT nodes mapped to random bottom-most AMR nodes. Surplus AMR nodes not entered into map
    // Problem - when NodeSpans overlap (usually because one is a subset of another)
    // Solution - Remove these subsets from consideration (this works as long as one is a strict subset of another
 //   println(amr)
    
    val uniqueSpans = (amr match {
      case Some(amrGraph) => for {
        (key, span) <- amrGraph.nodeSpans.toSeq
      } yield span
      case None => Nil
    }).distinct

    val removeOverlaps = uniqueSpans filter {s1 =>
      var start = s1._1
      var end = s1._2
      var remove = false
      for (s2 <- uniqueSpans diff Seq(s1)) {
        if (start >= s2._1 && end <= s2._2) {
          // s1 is subsumed within s2 and must be removed
          remove = true
        }
      }
      !remove
    }
    
    val amrToWordIndices= for {
        (start, end) <- removeOverlaps
        val allDTIndices = dt.nodeSpans filter { case (_, (wordPos, _)) => (start until end) contains wordPos } map { case (index, (wp, _)) => index }
      } yield (allAMRWithSameSpan(start, end, amr), allDTIndices.toSeq)
    
    (amrToWordIndices map { case (amrKeys, wordIndices) => mapAMRtoDTNodes(amrKeys, wordIndices, amr, dt) }).flatten.toMap
  }

  def allAMRWithSameSpan(start: Int, end: Int, amr: Option[AMRGraph]): Seq[String] = {
    val amrNodes = amr.get.nodes.keys
    val amrNodeSpans = amr.get.nodeSpans
    val sameSpans = amrNodes filter (amrNodeSpans.getOrElse(_, (0, 0)) match {
      case (spanStart, spanEnd) => if (spanStart >= start && spanEnd <= end) true else false
    })
    sameSpans.toSeq
  }

  def mapAMRtoDTNodes(amrKeys: Seq[String], wordIndices: Seq[Int], amr: Option[AMRGraph], dependencyTree: DependencyTree): Map[Int, String] = {
    import com.rockymadden.stringmetric.similarity.JaroMetric
    val amrNodes = amr.get.nodes
    if (debug) println(amrKeys)
    if (debug) println(wordIndices)
    val similarities = (for {
      amrKey <- amrKeys
      val amrConcept = amrNodes(amrKey).toLowerCase.replaceAll("""[^0-9a-zA-Z\-' ]""", "")
      word <- wordIndices
      val dtWord = dependencyTree.nodes(word).toLowerCase.replaceAll("""[^0-9a-zA-Z\-' ]""", "")
      val similarity = JaroMetric.compare(dtWord, amrConcept).getOrElse(0.0)
      if similarity > 0.001
    } yield (word, amrKey, similarity)) sortWith ((x, y) => x._3 > y._3)
    // We now have a list of all non-zero similarities, sorted in descending order of similarity

    var mappings = scala.collection.mutable.Map[Int, String]()
    var wordsMapped = scala.collection.mutable.Set[Int]()
    var conceptsMapped = scala.collection.mutable.Set[String]()
    for ((word, concept, similarity) <- similarities) {
      if (!(wordsMapped contains word) && !(conceptsMapped contains concept)) {
        mappings.put(word, concept)
        wordsMapped.add(word)
        conceptsMapped.add(concept)
      }
    }

    if (debug) println("Jaro mapping: " + mappings)
    // At this stage we have mapped any DT to AMR nodes with a non-zero similarity
    // We then select the topmost DT nodes that are required to match the remaining unmapped AMR nodes
    // (up to all of them, with 'topmost' defined as being closest to root of DT)
    val unmappedAMRInDecreasingDepthOrder = amrKeys diff mappings.values.toSeq sortWith (amr.get.depth(_) > amr.get.depth(_))
    if (debug && unmappedAMRInDecreasingDepthOrder.size > 0) println("AMR Dec: " + unmappedAMRInDecreasingDepthOrder)
    val unmappedDTInIncreasingDepthOrder = wordIndices diff mappings.keys.toSeq sortWith (dependencyTree.depth(_) < dependencyTree.depth(_))
    if (debug && unmappedDTInIncreasingDepthOrder.size > 0) println("DT Inc: " + unmappedDTInIncreasingDepthOrder)
    val randomMatch = (unmappedDTInIncreasingDepthOrder zip unmappedAMRInDecreasingDepthOrder).toMap
    // we then concatenate the greedy and random matches, plus a hard-coded mapping of the Root nodes
    if (debug) println("Random mapping: " + randomMatch)
    mappings.toMap ++ randomMatch
  }
}

object DependencyTree {

  var excludePunctuation = true
  val processor = new StanfordProcessor
  val numbers = "[0-9.,]".r

  def preProcess(sentence: String): List[String] = {
    val parsedForDates = extractNumbers(extractDates(sentence))
    val stanfordTree = (processor.parse(parsedForDates).head) filter (if (excludePunctuation) (x => x.deprel.getOrElse("") != "punct") else (_ => true))
    stanfordTree map {
      case ConllToken(_, form, _, _, _, _, _, _, _, _) => form.getOrElse("")
    }
  }

  def apply(sentence: String): DependencyTree = {

    val parsedForDates = extractNumbers(extractDates(sentence))
    val stanfordTree = (processor.parse(parsedForDates).head) filter (if (excludePunctuation) (x => x.deprel.getOrElse("") != "punct") else (_ => true))
    val monthFiddledTree = stanfordTree map {
      case full @ ConllToken(_, form, _, _, _, _, _, _, _, ner) if (ner.getOrElse("") == "DATE") => { full.copy(form = convertMonth(form)) }
      case full => full
    }

    val parseTree = monthFiddledTree zipWithIndex

    val nodes = (for {
      (ConllToken(Some(index), Some(form), _, pos, cpos, feats, Some(parentIndex), Some(deprel), phead, ner), wordCount) <- parseTree
    } yield (index -> form)).toMap

    val arcs = (for {
      (ConllToken(Some(index), _, _, pos, cpos, feats, Some(parentIndex), deprel, phead, ner), wordCount) <- parseTree
      if (parentIndex != 0)
    } yield ((parentIndex, index) -> deprel.getOrElse("UNK"))).toMap

    val nodeSpans = (for {
      (ConllToken(Some(index), _, _, pos, cpos, feats, _, deprel, phead, ner), wordCount) <- parseTree
    } yield (index -> (wordCount + 1, wordCount + 2))).toMap

    val nodeLemmas = (for {
      (ConllToken(Some(index), _, _, _, _, Some(lemma), _, _, _, _), wordCount) <- parseTree
    } yield (index -> (numbers.replaceAllIn(lemma, "") match { case "" if !(".,".contains(lemma)) => "##"; case "" if (".,".contains(lemma)) => lemma; case a => a.toLowerCase }))).toMap

    val nodePOS = (for {
      (ConllToken(Some(index), _, _, Some(pos), cpos, feats, _, deprel, phead, ner), wordCount) <- parseTree
    } yield (index -> pos)).toMap

    val nodeNER = (for {
      (ConllToken(Some(index), _, _, pos, cpos, feats, _, deprel, phead, Some(ner)), wordCount) <- parseTree
    } yield (index -> ner)).toMap

    val depLabels = arcs map { case ((from, to), label) => (to, label)}  // as we have a tree at this point
    DependencyTree(nodes, nodeLemmas, nodePOS, nodeNER, nodeSpans, arcs, depLabels, List(), Map(), Map(), Set(), Map())
  }

  def convertMonth(input: Option[String]): Option[String] = {
    input match {
      case Some(string) => Some(string match {
        case "January" | "Jan" => "1"
        case "February" | "Feb" => "2"
        case "March" | "Mar" => "3"
        case "April" | "Apr" => "4"
        case "May" => { "5" }
        case "June" | "Jun" => "6"
        case "July" | "Jul" => "7"
        case "August" | "Aug" => "8"
        case "September" | "Sep" => "9"
        case "October" | "Oct" => "10"
        case "November" | "Nov" => "11"
        case "December" | "Dec" => "12"
        case other => other
      })
      case None => None
    }
  }

  def extractDates(input: String): String = {
    val redDate = """\b(\d\d\d\d)-(\d\d)-(\d\d)\b""".r
    val redDate2 = """\b((?:19|20)\d\d)(\d\d)(\d\d)\b""".r
    val redDate3 = """\b(\d\d)(\d\d)(\d\d)\b""".r

    var output = redDate replaceAllIn (input, m => (m group 1).toInt + " " + (m group 2).toInt + " " + (m group 3).toInt)
    output = redDate2 replaceAllIn (output, m => (m group 1).toInt + " " + (m group 2).toInt + " " + (m group 3).toInt)
    redDate3 replaceAllIn (output, m => (m group 1).toInt + (if ((m group 1).toInt > 50) 1900 else 2000) + " " + (m group 2).toInt + " " + (m group 3).toInt)
  }

  def extractNumbers(input: String): String = {
    val regexStr = "(?i)(^$ | $ | $-| $[,.?!;:])"
    val numbers = List("one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen")
    var output = input
    for ((str, number) <- numbers.zipWithIndex) {
      val regexToUse = """\$""".r.replaceAllIn(regexStr, str).r
      output = regexToUse.replaceAllIn(output, " " + (number + 1) + " ")
    }
    
    val numbersWithComma = """\b((?:[0-9,]+\.[0-9]*)|(?:[0-9,]*\.[0-9]+)|(?:[0-9,]+))""".r
    output = numbersWithComma replaceAllIn (output, _ match {
      case numbersWithComma(number) => number.replaceAll(",", "")
      case other => ""
    })

    val numbersWithHyphen = """\b((?:[0-9]+\.[0-9]*)|(?:[0-9]*\.[0-9]+)|(?:[0-9]+))-""".r
    output = numbersWithHyphen replaceAllIn (output, _ match {
      case numbersWithHyphen(number) =>
        number + " "
      case other => ""
    })
    val realNumbers = """((?:[0-9]+\.[0-9]*)|(?:[0-9]*\.[0-9]+)|(?:[0-9]+)) (thousand|million|billion)[ -]""".r
    output = realNumbers replaceAllIn (output, _ match {
      case realNumbers(number, multiple) =>
        val replacement = number.toDouble * (multiple match { case "thousand" => 1000; case "million" => 1000000; case "billion" => 1000000000 });
        f"$replacement%.0f "
      case other => ""
    })

    val dollars = """\$""".r
    dollars.replaceAllIn(output, "dollars ")
  }
}

object AMRGraph {
  // We then use the JAMR functionality here
  val opN = "^op[0-9]+$".r
  var useImprovedAligner = false
  var useWordNet = false
  def setAligner(code: String): Unit = {
    if (code == "improved") useImprovedAligner = true
    if (code == "wordnet") { useImprovedAligner = true; useWordNet = true }
  }
  def apply(jamrGraph: edu.cmu.lti.nlp.amr.Graph): AMRGraph = {
    val nodes = jamrGraph.nodes.map(node => (node.id -> node.concept)).toMap
    val nodeSpans = (for {
      span <- jamrGraph.spans
      nodeId <- span.nodeIds
    } yield (nodeId -> (span.start + 1, span.end + 1))).toMap
    // Note our convention is that the first word in a sentence is at index 1

    val Relation = """:?(.*)""".r

    val originalArcs = (for {
      node1 <- jamrGraph.nodes
      (label, node2) <- node1.relations
      Relation(relation) = label // label includes the ":"
    } yield ((node1.id, node2.id) -> relation)).toMap

    val arcs = originalArcs map {
      case (k, v) => opN.findFirstIn(v) match {
        case None => (k, v)
        case Some(_) => (k, "opN")
      }
    }

    AMRGraph(nodes, nodeSpans, arcs, originalArcs)
  }

  def apply(rawAMR: String, rawSentence: String): AMRGraph = {
    val tokenisedSentence = DependencyTree.preProcess(rawSentence)
    val amr = Graph.parse(rawAMR)
    val wordAlignments = if (useImprovedAligner)
      AlignTest.alignWords(rawSentence, tokenisedSentence.toArray, amr, useWordNet)
    else
      AlignWords.alignWords(tokenisedSentence.toArray, amr)

    // wordAlignments from JAMR aligns to spans of words
    // We now wish to fine-tune this

    val spanAlignments = AlignSpans.alignSpans(tokenisedSentence.toArray, amr, wordAlignments)

    AMRGraph(amr)
  }

  def importFile(fileName: String): IndexedSeq[(String, String)] = {
    val input = Source.fromFile(fileName).getLines().toList.map(_.trim)
    val sentenceIndices = for {
      (line, i) <- input.zipWithIndex
      if line.matches("^# ::snt .*")
    } yield i
    val startIndices = sentenceIndices.map(_ + 2)
    val endIndices = sentenceIndices.map(_ - 2).tail :+ (input.length - 1)

    for {
      i <- 0 until sentenceIndices.size
      val english = input(sentenceIndices(i)).substring(8)
      val amr = (for (j <- startIndices(i) to endIndices(i)) yield input(j)).toList.mkString(" ")
    } yield (english, amr)
  }

}
