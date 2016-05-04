package amr.JAMR

import scala.collection.mutable.Map
import scala.collection.mutable.Set
import scala.collection.mutable.ArrayBuffer
import scala.collection.immutable.Queue
import scala.collection.immutable
import scala.util.parsing.combinator._

case class Graph(var root: Node, spans: ArrayBuffer[Span], getNodeById: Map[String, Node], getNodeByName: Map[String, Node]) {

    def duplicate : Graph = {
        // Makes a copy of the graph
        // Assumes that getNodeById exists and is properly set up (for 'nodes' call)
        val getNodeById2 : Map[String, Node] = Map()
        for (node <- nodes) {
            val Node(id, name, concept, relations, topologicalOrdering, variableRelations, alignment, spans) = node
            getNodeById2(id) = Node(id, name, concept, List(), List(), List(), alignment, spans)
        }
        for (node <- nodes) {
            val node2 = getNodeById2(node.id)
            node2.relations = node.relations.map(x => (x._1, getNodeById2(x._2.id)))
            node2.topologicalOrdering = node.topologicalOrdering.map(x => (x._1, getNodeById2(x._2.id)))
            node2.variableRelations = node.variableRelations.map(x => (x._1, Var(getNodeById2(x._2.node.id), x._2.name)))
        }
        val getNodeByName2 = getNodeByName.map(x => (x._1, getNodeById2(x._2.id)))
        logger(1, "getNodeById = " + getNodeById)
        logger(1, "getNodeById2 = " + getNodeById2)
        val root2 = if (getNodeById2.contains(root.id)) {
            getNodeById2(root.id)
        } else {
            Graph.Null().root  // sometimes the root is not in the spans (if it is from automatically aligned spans, and the root is not in the spans).  So we create a dummy root which will get re-assigned later
        }
        return Graph(root2, spans.clone, getNodeById2, getNodeByName2)
    }

    def clearUnalignedNodes() {
        // Removes all the unaligned nodes from the graph (useful for oracle experiments)
        // WARNING: this can break the topological ordering
        for (node <- nodes) {
            if (node.spans.size == 0) { // Unaligned
                logger(1, "clearUnalignedNodes():  removing unaligned node: "+node.name+" / "+node.concept)
                getNodeById -= node.id
                if (node.name != None) {
                    getNodeByName -= node.name.get
                }
            }
        }
        if (nodes.filter(x => !getNodeById.contains(x.id)).size  > 0) {
            logger(1, "clearUnalignedNodes() deleting relations to the following nodes: "+nodes.filter(x => !getNodeById.contains(x.id)).toList)
        }
        for (node <- nodes) {
            node.topologicalOrdering = node.topologicalOrdering.filter(x => getNodeById.contains(x._2.id)) // Warning this may break the topological ordering
            node.relations = node.relations.filter(x => getNodeById.contains(x._2.id))
            node.variableRelations = node.variableRelations.filter(x => getNodeById.contains(x._2.node.id))
        }
    }

    def clearEdges() : Graph = {
        // Initializes the graph from the spans (effectively clearing the edges)
        // Sets relations, but leaves topologicalOrdering and variableRelations blank
        // As a side effect, it re-assigns ids to all the nodes in the graph.  TODO: why did I do it this way???  Couldn't I just keep the ids the same???  Maybe it was so I could simulate what it is like to have ids from stage 1
        // TODO: CHANGE THIS SO IT DOESN'T RE-ASSIGN IDS

        var currentId = 0
        getNodeById.clear
        getNodeByName.clear

        def addRec(node: Node) : (Node, List[String]) = {
            var ids = List(currentId.toString)
            //val node2 = Node(node.id, node.name, node.concept, List(), List(), List(), node.alignment, node.spans)
            val node2 = Node(currentId.toString, node.name, node.concept, List(), List(), List(), node.alignment, node.spans)   // TODO: is this bugfix correct???
            var relations = List[(String, Node)]()
            getNodeById(currentId.toString) = node2
            node.name match {
                case Some(name) => { getNodeByName(name) = node2 }
                case None => Unit
            }
            currentId += 1
            for ((relation, child) <- node.topologicalOrdering) {
                val result = addRec(child)
                ids = ids ::: result._2
                relations = (relation, result._1) :: relations
            }
            node2.relations = relations.reverse
            return (node2, ids)
        }

        // Add all the non-coref spans
        for (span <- spans if !span.coRef) {
            span.nodeIds = addRec(span.amr)._2
        }

        // Set the span ids for the coref spans correctly
        for (span <- spans if span.coRef) {
            span.nodeIds = List()
        }

        for ((id, node) <- getNodeById) {
            for { spanIndex <- node.spans
                  span = spans(spanIndex)
                  if span.coRef
                } {
                span.nodeIds = span.nodeIds ::: List(id)
            }
        }
        return this
    }

    def printRoot : String = {
        return "# ::root\t" + root.id + "\t" + root.concept
    }

    def printNodes : List[String] = {
        nodes.map(node =>
            if (node.spans.size > 0) {
                //node.id + "\t" + node.nameStr + "\t" + node.concept + "\t" + spans(node.spans(0)).start + "-" + spans(node.spans(0)).end
                node.id + "\t" + node.concept + "\t" + spans(node.spans(0)).start + "-" + spans(node.spans(0)).end
            } else {
                //node.id + "\t" + node.nameStr + "\t" + node.concept + "\t"
                node.id + "\t" + node.concept + "\t"
            }
        ).toList.sorted
    }

    def printEdges : List[String] = {
        var edges : List[String] = List()
        val Relation = """:?(.*)""".r

        for { node1 <- nodes
              (label, node2) <- node1.relations
              Relation(relation) = label    // label includes the ":"
            } {
                //edges = node1.id + "\t" + node1.concept + "\t" + relation + "\t" + node2.id + "\t" + node2.concept :: edges
                edges = node1.concept + "\t" + relation + "\t" + node2.concept + "\t" + node1.id + "\t" +  node2.id + "\t" :: edges
        }
        return edges.sorted
    }

    def printTriples(detail: Int = 1,
                     extra: (Node, Node, String) => String = (n1, n2, r) => "",   // Optional string to print after each relation
                     sorted: Boolean = true
                     ) : String = {
        def name(node: Node) : String = {
            node.name match {
                case None => ""
                case Some(n) => n + " / "
            }
        }

        var triples : List[(String, String)] = List()
        val Relation = """:?(.*)""".r

        for { node1 <- nodes
              (label, node2) <- node1.relations
              Relation(relation) = label    // label includes the ":" (passed to extra)
            } {
            detail match {
                case 0 => triples = ("(" + node1.concept + ", " + relation + ", " + node2.concept + ")", extra(node1,node2,label)) :: triples
                case 1 => triples = ( "(" + name(node1) + node1.concept + ", " + relation + ", " + name(node2) + node2.concept + ")", extra(node1,node2,label)) :: triples
                case 2 => triples = (relation + "(" + node1.concept + ", " + node2.concept + ")", extra(node1,node2,label)) :: triples
                case 3 => triples = (relation + "(" + name(node1) + node1.concept + ", " + name(node2) + node2.concept + ")", extra(node1,node2,label)) :: triples
                case _ => triples = ("(" + name(node1) + node1.concept + ", " + name(node2) + node2.concept + ", " + relation + ")", extra(node1,node2,label)) :: triples
            }
        }
        if (sorted) { triples = triples.sorted } else { triples = triples.reverse }
        return triples.map(x => x._1+x._2).mkString("\n")
    }

    def loadSpans(spanStr: String, sentence: Array[String]) = {
        //assert(spans.size == 0, "This code does not support re-loading the spans")
        if (spans.size != 0) {
            logger(-1, "WARNING: Reloading the spans")   // used to be assert
            spans.clear
            for (node <- nodes) {
                node.spans.clear
            }
        }
        val SpanRegex = """([*]?)([0-9]+)-([0-9]+)\|(.*)""".r   // TODO: move to Span
        for (spanStr <- spanStr.split(" ")) {
            try {
                val SpanRegex(corefStr, start, end, nodeStr) = spanStr
                val nodeIds = nodeStr.split("[+]").toList.sorted
                val words = SpanLoader.getWords(start.toInt, end.toInt, sentence)   // TODO: use addSpan function
                val amr = SpanLoader.getAmr(nodeIds, this)
                val coref = corefStr match {
                    case "*" => true
                    case "" => false
                }
                spans += Span(start.toInt, end.toInt, nodeIds, words, amr, coref)
                for (id <- nodeIds) {
                    getNodeById(id).addSpan(spans.size-1, coref)
                }
            } catch {
                // TODO: catch malformed input (Regex match error, or toInt err)
                case e : Throwable => logger(1, "****************** MALFORMED SPAN: "+spanStr)
            }
        }
    }

    def addSpan(sentence: Array[String], start: Int, end: Int, amrStr: String) { // This function is used by the ConceptInvoker
        // This code sets up relations for each node but not topologicalOrdering or variableRelations
        val graphFrag = Graph.parse(amrStr)
        val graphFrag2 = Graph.parse(amrStr)
        var currentId = getNodeById.size
        var nodeIds : List[String] = List()
        var nodes : List[Node] = List()
        doRecursive(x => nodes = x :: nodes, graphFrag.root)    // in order traversal
        var nodes2 : List[Node] = List() // copy of the nodes in the span (goes into span.amr)
        doRecursive(x => nodes2 = x :: nodes2, graphFrag2.root)    // in order traversal
        for ((node, node2) <- nodes.zip(nodes2).reverse) {
            node.id = currentId.toString
            node2.id = node.id
            nodeIds = node.id :: nodeIds
            getNodeById(node.id) = node
            if (node.concept(0) != '"' && node.concept(0) != '-') {   // concepts always have size > 0 (enforced by GraphParser)
                val varName = getNextVariableName(node.concept.toLowerCase()(0))
                getNodeByName(varName) = node
                node.name = Some(varName)
                node2.name = Some(varName)
            }
            node.alignment = None // alignment is only used in AlignSpans and AlignSpans2 (TODO: remove?)
            node.spans = ArrayBuffer(spans.size)
            node.topologicalOrdering = List()
            node.variableRelations = List()
            node2.alignment = None // alignment is only used in AlignSpans and AlignSpans2 (TODO: remove?)
            node2.spans = ArrayBuffer(spans.size)
            currentId += 1
        }
        logger(2, "nodeIds = "+nodeIds.reverse)
        logger(2, "concepts = "+nodeIds.reverse.map(x => getNodeById(x).concept))
        spans += Span(start, end, nodeIds, sentence.slice(start, end).mkString(" "), nodes2.reverse.apply(0), coRef = false)
    }

    def addSpan(start: Int, end: Int, nodeIds: List[String], coRef: Boolean, sentence: Array[String]) {
        val span = Span(start, end, nodeIds, sentence.slice(start, end).mkString(" "), SpanLoader.getAmr(nodeIds, this), coRef)
        addSpan(span)
    }

    def addSpan(span: Span) {
        spans += span
        for (id <- span.nodeIds) {
            getNodeById(id).addSpan(spans.size-1, span.coRef)
        }
    }

    def updateSpan(spanIndex: Int, start: Int, end: Int, nodeIds: List[String], coRef: Boolean, sentence: Array[String]) {
        //println("new nodes = "+nodeIds.toString)
        for (id <- spans(spanIndex).nodeIds) {
            //println(getNodeById(id).spans)
            getNodeById(id).spans -= spanIndex
        }
        val span = Span(start, end, nodeIds, sentence.slice(start, end).mkString(" "), SpanLoader.getAmr(nodeIds, this), coRef)
        spans(spanIndex) = span
       for (id <- nodeIds) {
            getNodeById(id).addSpan(spanIndex, coRef)
            //println(getNodeById(id).spans)
        }
    }

    def updateSpan(spanIndex: Int, start: Int, end: Int, sentence: Array[String]) {
        // Update start, end
        val span = spans(spanIndex)
        updateSpan(spanIndex, start, end, span.nodeIds, span.coRef, sentence)
    }

    def updateSpan(spanIndex: Int, nodeIds: List[String], sentence: Array[String]) {
        // Update nodeIds
        val span = spans(spanIndex)
        updateSpan(spanIndex, span.start, span.end, nodeIds, span.coRef, sentence)
    }

    def updateSpan(spanIndex: Int, coRef: Boolean, sentence: Array[String]) {
        // Update coRef indicator
        val span = spans(spanIndex)
        updateSpan(spanIndex, span.start, span.end, span.nodeIds, coRef, sentence)
    }

    def nodes : Iterator[Node] = {
        return getNodeById.valuesIterator
    }

    def nodesByName : Iterator[Node] = {
        return getNodeByName.valuesIterator
    }

    def doRecursive(f: (Node) => Unit, node: Node = root) {
        f(node)
        for ((_,child) <- node.topologicalOrdering) {
            doRecursive(f, child)
        }
    }

/* Very cool recursive function TODO: use this one instead
    def doRecursive(parentMessage: T, node: Node, f: (Node, T) => T, g: List[T] => R ) : R = {
        val message = f(parentMessage, node)
        return g(node.topologicalOrdering.map(x => doRecursive(message, x.2, f, g)).toList)
    }
*/

    def sortRelations() {
        // Sorts the nodes in topological ordering by the label name
        // WARNING: This should be called only after assignOpN
        doRecursive(x => {x.topologicalOrdering = x.topologicalOrdering.sortBy(_._1)}, root)
    }

    def makeIds() {
        // Sets the node.id field for every node in the graph according to the topologicalOrdering
        // For example "0" is the root and "0.1" is the 2nd child of the root
        // Assumes that a topological ordering already exists (node.topologicalOrdering is non-empty)
        getNodeById.clear
        val oldToNew = makeIds(root)
        for (span <- spans) {
            span.nodeIds = span.nodeIds.map(x => oldToNew(x))
            doRecursive(x => x.id = oldToNew.getOrElse(x.id, { logger(0, "WARNING: makeIds can't find span Id: "+x.id); x.id }), span.amr)
        }
    }

    def makeIds(node: Node, id: List[Int] = List(0)) : immutable.Map[String, String] = {
        // Sets the node.id field for every node in the graph according to the topologicalOrdering
        // For example "0" is the root and "0.1" is the 2nd child of the root
        // Assumes that a topological ordering already exists (node.topologicalOrdering is non-empty)
        val newId = id.mkString(".")
        var oldIdToNewId : immutable.Map[String, String] = immutable.Map(node.id -> newId)
        node.id = newId
        getNodeById += (node.id -> node)
        val relationIndex = node.relations.zipWithIndex
        val topologicalOrderingWithRelationalIndex = relationIndex filter (x => node.topologicalOrdering.contains(x._1))
        for (((label,child), i) <- topologicalOrderingWithRelationalIndex) {
            oldIdToNewId ++= makeIds(child, id ::: List(i))
        }
        return oldIdToNewId
    }

    private def makeVariables(node: Node = root) {
    // TODO: this can be simplified using getNodeById and 'nodes'
        // Populate the getNodeByName map
        // Assumes a topologicalOrdering exists and node.name is set
        if (node == root) {
            getNodeByName.clear
        }
        if (node.name != None) {
            var Some(name) = node.name
            if (getNodeByName.contains(name)) {
                logger(-2, "WARNING: Duplicate variable name in annotation: " + name)
                name = getNextVariableName(name(0))
                logger(-2, "Changing name to: " + name)
                node.name = Some(name)
            }
            getNodeByName += (name -> node)
            for ((_,child) <- node.topologicalOrdering) {
                makeVariables(child)
            }
        }
    }

/*    private def makeVariables() {
        // Populate the getNodeByName map
        // Assumes getNodeById exists and node.name is set correctly (can be None)
        
    } */

    def normalizeInverseRelations() {
        // For all nodes, converts all inverse relations in node.relations into forward relations for the corresponding nodes
        for (node1 <- nodes ) {
            for { (rel, node2) <- node1.relations
                  if rel.endsWith("-of")
                  relation = rel.slice(0,rel.size-3) } {
                // Add non-inverse relation to node2
                node2.relations = node2.relations ::: List((relation, node1))
            }
            // Remove inverse relations from node1
            node1.relations = node1.relations.filter(x => !x._1.endsWith("-of"))
        }
    }

    def inverseRelations : Map[String, List[(String, String)]] = {
        // Returns all the inverse relations as a map: node_id -> List((inverse_relation, node_id))
        // Used by makeTopologicalOrdering to follow inverse relations as well
        val inverse = Map.empty[String, List[(String, String)]]
        for { node1 <- nodes
              (rel, node2) <- node1.relations } {
            val relation = if (rel.endsWith("-of")) { rel.slice(0,rel.size-3) } else { rel+"-of" }
            inverse(node2.id) = (relation, node1.id) :: inverse.getOrElse(node2.id, List())
        }
        return inverse
    }

    private def unifyVariables(node: Node = root) {
        // Postcondition: Unify variables, remove variables from node.topologicalOrdering,
        // and populate node.relations and node.variableRelations attributes for each node
        // Precondition: topologicalOrdering was filled in by the graph parser
        // and that makeVariables has already been called
        val relations = node.topologicalOrdering
        node.relations = List[(String, Node)]()
        node.topologicalOrdering = List[(String, Node)]()
        node.variableRelations = List[(String, Var)]()
        for ((relation, child) <- relations) {
            // figure out if child is a node, or a variable
            if (child.name == None && getNodeByName.contains(child.concept) && child.topologicalOrdering.size == 0) { // variables have concepts, but no names
                // child is a variable
                val varName = child.concept
                val actualNode = getNodeByName(varName)
                node.relations = node.relations ::: List((relation, actualNode))
                node.variableRelations = node.variableRelations ::: List((relation, Var(actualNode, varName)))
            } else {
                // child is a legit node (not a variable)
                node.relations = node.relations ::: List((relation, child))
                node.topologicalOrdering = node.topologicalOrdering ::: List((relation, child))
                unifyVariables(child)
            }
        }
    }

    def makeTopologicalOrdering() {
        // This function is called after the graph decoder has added edges to the graph and
        // the root has been chosen. (The edges were added to each node's relations list and
        // root has been correctly set).
        // Preconditions:
        //   root is set
        //   node.relations is correct for each node
        //   getNodeByName is setup correctly
        //   getNodeById is defined for all nodes
        // Postcondition: This function populates node.variableRelations and node.topologicalOrdering by 
        // breadth-first-search from the root.
        var queue = Queue[Node](root)
        val visited = Set.empty[String]
        val inverse = inverseRelations
        do {
            val (node, dequeue) = queue.dequeue
            logger(2, "Node = "+node.id)
            queue = dequeue
            visited += node.id
            node.topologicalOrdering = List()
            node.variableRelations = List()
            logger(3, "inverse.getOrElse(node.id, List()) = "+inverse.getOrElse(node.id, List()))
            val relations = node.relations ::: inverse.getOrElse(node.id, List()).map(x => (x._1, getNodeById(x._2)))
            for ((relation, child) <- relations.sortBy(_._1).reverse) {
                logger(3, "(relation, child) = "+(relation,child.id))
                if (queue.contains(child)) {
                    // this is a variable relation
                    logger(3, "Adding "+child.id+" as a variable of "+node.id)
                    //assert(child.name != None, "Attempting to create a variable relation to a node without a variable name")
                    if (child.name != None) {
                        val Some(name) = child.name
                        assert(getNodeByName.contains(name), "Variable name not in getNodeByName")
                        node.variableRelations = (relation, Var(child, name)) :: node.variableRelations
                    } else {
                        logger(0, "WARNGING: Creating a variable relation to a node without a variable name - ignoring this relation in the topological ordering")
                        assert(false, "WARNGING: Attempted to create a variable relation to a node without a variable name")
                    }
                } else if (!visited.contains(child.id)) {
                    // this node goes into the topological ordering
                    logger(3, "Adding "+child.id+" as a child of "+node.id)
                    visited += child.id
                    queue = queue.enqueue(child)
                    node.topologicalOrdering = (relation, child) :: node.topologicalOrdering
                }
            }
        } while (queue.size != 0)
        logger(2, "visited = "+visited)
        assert(visited.size == nodes.size, "The graph does not span the nodes")
    }

    private def getNextVariableName(c: Char) : String = {
        if (!getNodeByName.contains(c.toString)) {
            c.toString
        } else {
            var i = 2
            while (getNodeByName.contains(c.toString+i.toString)) {
                i += 1
            }
            c.toString+i.toString
        }
    }

    def addVariableToSpans() {
        for (span <- spans) {
            if (span.nodeIds.map(x => getNodeById(x)).filter(x => x.name != None).size == 0) {
                logger(1, "WARNING: Adding a variable name to a span")
                val node = getNodeById(span.nodeIds(0))
                var c : Char = node.concept(0)
                if (c == '"') {
                    c = node.concept(1)
                }
                assert(c != '"', "Concept starts with \"")
                val varName = getNextVariableName(c)
                getNodeByName(varName) = node
                node.name = Some(varName)
            }
        }
    }

    def prettyString(detail: Int, pretty: Boolean) : String = {
        val vars = Set.empty[String]        // set of variable names to be sure to keep in output 
        if (root.name != None) {
            vars += root.name.get
        }
        doRecursive(node => vars ++= node.variableRelations.map(_._2.name))
        return root.prettyString(detail, pretty, vars)
    }

    def assignOpN() {
        def numberOps(relations: List[(String, Node)]) : List[(String, Node)] = {
            val ops = relations.filter(x => x._1 == ":op")
            val opNs = ops.sortBy(x => spans(x._2.spans(0)).start).zipWithIndex.map(x => (x._1._1 + (x._2+1).toString, x._1._2))
            val notOps = relations.filter(x => x._1 != ":op")
            return opNs ::: notOps
        }
        def numberOpsVar(relations: List[(String, Var)]) : List[(String, Var)] = {
            val ops = relations.filter(x => x._1 == ":op")
            val opNs = ops.sortBy(x => spans(x._2.node.spans(0)).start).zipWithIndex.map(x => (x._1._1 + (x._2+1).toString, x._1._2))
            val notOps = relations.filter(x => x._1 != ":op")
            return opNs ::: notOps
        }
        doRecursive(node => {
            node.relations = numberOps(node.relations)
            node.topologicalOrdering = numberOps(node.topologicalOrdering)
            node.variableRelations = numberOpsVar(node.variableRelations)
        })
    }
}

object Graph {
    private class GraphParser extends JavaTokenParsers {
        // Parser implementation for parsing AMR graphs
        def variable : Parser[String] = """[^ \t\n()":]+(\~e.[0-9]+)?""".r
        def concept : Parser[String] = """(([^ \~\t\n()":]+)|("[^\~"]+"))(\~e.[0-9]+)?""".r
        def relationStr : Parser[String] = """:[^ \~\t\n()":]+(\~e.[0-9]+)?""".r
        // The expressions below work correctly, but are more strict
        //def variable : Parser[String] = """[a-zA-Z0-9]+""".r
        //def concept : Parser[String] = """([a-zA-Z0-9.-]+)|("[^"]+")""".r
        //def relationStr : Parser[String] = """:[a-zA-Z0-9-]+""".r
        def relation : Parser[(String, Node)] = relationStr~node ^^ {
            case relationStr~node => (relationStr, node)
        }
        def relations : Parser[List[(String, Node)]] = rep(relation)
        def internalNode : Parser[Node] = "("~>variable~"/"~concept~relations<~")" ^^ {
            case variable~"/"~concept~relations => Node("", Some(variable), concept, List[(String, Node)](), relations, List[(String, Var)](), None, ArrayBuffer[Int]())
        }
        def unnamedInternalNode : Parser[Node] = "("~>concept~relations<~")" ^^ {
            case concept~relations => Node("", None, concept, List[(String, Node)](), relations, List[(String, Var)](), None, ArrayBuffer[Int]())
        }
        def terminalNode : Parser[Node] = concept ^^ { 
            case concept => Node("", None, concept, List[(String, Node)](), List[(String, Node)](), List[(String, Var)](), None, ArrayBuffer[Int]())
        }
        def node : Parser[Node] = terminalNode | internalNode | unnamedInternalNode
    }

    private val parser = new GraphParser

    def load(iterator: Iterator[String]) : Iterator[Graph] = {
        for (line <- iterator) yield {
            parse(line)
        }
    }

    def parse(amr: String) : Graph = {
        val graph = parser.parseAll(parser.node, amr) match {
            case parser.Success(e, _) => Graph(e, new ArrayBuffer[Span](), Map[String, Node](), Map[String, Node]())
            case _ => { assert(false, "Could not parse AMR: "+amr); Graph.AMREmpty }
        }
        graph.makeVariables()
        graph.unifyVariables()
        graph.makeIds()
        return graph
    }

    //def empty() : Graph = { val g = parse("(n / none)"); g.getNodeById.clear; g.getNodeByName.clear; return g }
    //def null() : Graph = { parse("(n / null)") }
    def AMREmpty() : Graph = { val g = parse("(a / amr-empty)"); g.loadSpans("0-1|0", Array()); return g }
    def Null() : Graph = { val g = parse("(n / null)"); g.getNodeById.clear; g.getNodeByName.clear; return g }
    def empty() : Graph = { val g = parse("(n / null)"); g.getNodeById.clear; g.getNodeByName.clear; return g }
}

