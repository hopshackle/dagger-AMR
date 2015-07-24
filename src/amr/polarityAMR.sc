package amr

object polarityAMR {
val testData = AMRGraph.importFile("C:\\AMR\\polarityExample.txt") // Nato Considers....
                                                  //> testData  : IndexedSeq[(String, String)] = Vector((The cyber attacks were un
                                                  //| precedented.,(p / precedent :polarity - :domain (a / attack-01 :mod (c / cyb
                                                  //| er)))))
  val expert = new WangXueExpert                  //> expert  : amr.WangXueExpert = amr.WangXueExpert@548a9f61
  val expertSystem = new WangXueTransitionSystem  //> expertSystem  : amr.WangXueTransitionSystem = amr.WangXueTransitionSystem@17
                                                  //| 53acfe
  val s1 = Sentence(testData(0)._1, testData(0)._2)
                                                  //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [2.1 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [4.0 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [3.1 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [5.1 sec].
                                                  //| s1  : amr.Sentence = Sentence(The cyber attacks were unprecedented.,
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> The, 2 -> cyber, 3 -> attacks, 4 -> were, 5 
                                                  //| -> unprecedented)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6))
                                                  //| Edges:	Map((0,5) -> root, (3,1) -> det, (3,2) -> nn, (5,3) -> nsubj, (5
                                                  //| ,4) -> cop)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	List(),Some(AMRGraph(Map(0.1 -> attack-01, 0.1.0 -> cybe
                                                  //| r, 0.0 -> -, 0 -> precedent, ROOT -> ROOT),Map(0 -> (5,6), 0.1 -> (3,4), 0.1
                                                  //| .0 -> (2,3)),Map((0.1,0.1.0) -> mod, (0,0.0) -> polarity, (0,0.1) -> domain,
                                                  //|  (ROOT,0) -> ROOT))),Map(5 -> 0, 3 -> 0.1, 2 -> 0.1.0, 0 -> ROOT))
  var state = expertSystem.init(s1)               //> state  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(1, 2, 4, 3, 5, 0)
                                                  //| Children:	List()
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> The, 2 -> cyber, 3 -> attacks, 4 -> were, 5 
                                                  //| -> unprecedented)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6))
                                                  //| Edges:	Map((0,5) -> root, (3,1) -> det, (3,2) -> nn, (5,3) -> nsubj, (5
                                                  //| ,4) -> cop)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	List()
                                                  //| Mappings:	5 -> 0
                                                  //| 3 -> 0.1
                                                  //| 2 -> 0.1.0
                                                  //| 0 -> ROOT
                                                  //| 
  val action = new Array[WangXueAction](301)      //> action  : Array[amr.WangXueAction] = Array(null, null, null, null, null, nul
                                                  //| l, null, null, null, null, null, null, null, null, null, null, null, null, n
                                                  //| ull, null, null, null, null, null, null, null, null, null, null, null, null,
                                                  //|  null, null, null, null, null, null, null, null, null, null, null, null, nul
                                                  //| l, null, null, null, null, null, null, null, null, null, null, null, null, n
                                                  //| ull, null, null, null, null, null, null, null, null, null, null, null, null,
                                                  //|  null, null, null, null, null, null, null, null, null, null, null, null, nul
                                                  //| l, null, null, null, null, null, null, null, null, null, null, null, null, n
                                                  //| ull, null, null, null, null, null, null, null, null, null, null, null, null,
                                                  //|  null, null, null, null, null, null, null, null, null, null, null, null, nul
                                                  //| l, null, null, null, null, null, null, null, null, null, null, null, null, n
                                                  //| ull, null, null, null, null, null, null, null, null, null, null, null, null,
                                                  //|  null, null, null, null, null, null, null, null, null, null, null, null, nul
                                                  //| l, null, null, null, null, null, null, null, null, null, null, null, null, n
                                                  //| ull, null, null, null, null, null, null, null, null, null, null, null, null,
                                                  //|  null, null, null, null, null, null, null, null, null, null, null, null, nul
                                                  //| l, null, null, null, null, null, null, null, null, null, null, null, null, n
                                                  //| ull, null, null, null, null, null, null, null, null, null, null, null, null,
                                                  //|  null, null, null, null, null, null, null, null, null, null, null, null, nul
                                                  //| l, null, null, null, null, null, null, null, null, null, null, null, null, n
                                                  //| ull, null, null, null, null, null, null, null, null, null, null, null, null,
                                                  //|  null, null, null, null, null, null, null, null, null, null, null, null, nul
                                                  //| l, null, null, null, null, null, null, null, null, null, null, null, null, n
                                                  //| ull, null, null, null, null, null, null, null, null, null, null, null, null,
                                                  //|  null, null, null, null)
  val stateHistory = new Array[WangXueTransitionState](301)
                                                  //> stateHistory  : Array[amr.WangXueTransitionState] = Array(null, null, null, 
                                                  //| null, null, null, null, null, null, null, null, null, null, null, null, null
                                                  //| , null, null, null, null, null, null, null, null, null, null, null, null, nu
                                                  //| ll, null, null, null, null, null, null, null, null, null, null, null, null, 
                                                  //| null, null, null, null, null, null, null, null, null, null, null, null, null
                                                  //| , null, null, null, null, null, null, null, null, null, null, null, null, nu
                                                  //| ll, null, null, null, null, null, null, null, null, null, null, null, null, 
                                                  //| null, null, null, null, null, null, null, null, null, null, null, null, null
                                                  //| , null, null, null, null, null, null, null, null, null, null, null, null, nu
                                                  //| ll, null, null, null, null, null, null, null, null, null, null, null, null, 
                                                  //| null, null, null, null, null, null, null, null, null, null, null, null, null
                                                  //| , null, null, null, null, null, null, null, null, null, null, null, null, nu
                                                  //| ll, null, null, null, null, null, null, null, null, null, null, null, null, 
                                                  //| null, null, null, null, null, null, null, null, null, null, null, null, null
                                                  //| , null, null, null, null, null, null, null, null, null, null, null, null, nu
                                                  //| ll, null, null, null, null, null, null, null, null, null, null, null, null, 
                                                  //| null, null, null, null, null, null, null, null, null, null, null, null, null
                                                  //| , null, null, null, null, null, null, null, null, null, null, null, null, nu
                                                  //| ll, null, null, null, null, null, null, null, null, null, null, null, null, 
                                                  //| null, null, null, null, null, null, null, null, null, null, null, null, null
                                                  //| , null, null, null, null, null, null, null, null, null, null, null, null, nu
                                                  //| ll, null, null, null, null, null, null, null, null, null, null, null, null, 
                                                  //| null, null, null, null, null, null, null, null, null, null, null, null, null
                                                  //| , null, null, null, null, null, null, null, null, null, null, null, null, nu
                                                  //| ll, null, null, null, null, null, null)
  s1.positionToAMR                                //> res0: Map[Int,String] = Map(5 -> 0, 3 -> 0.1, 2 -> 0.1.0, 0 -> ROOT)
  s1.amr.get.nodes                                //> res1: Map[String,String] = Map(0.1 -> attack-01, 0.1.0 -> cyber, 0.0 -> -, 0
                                                  //|  -> precedent, ROOT -> ROOT)
  s1.amr.get.arcs                                 //> res2: Map[(String, String),String] = Map((0.1,0.1.0) -> mod, (0,0.0) -> pola
                                                  //| rity, (0,0.1) -> domain, (ROOT,0) -> ROOT)
  import java.io._
  val file = new FileWriter("C://AMR//polarity_output.txt")
                                                  //> file  : java.io.FileWriter = java.io.FileWriter@73e9cf30
  for (i <- 0 to 300) {
    if (!expertSystem.isTerminal(state)) {
      stateHistory(i) = state
      if (i > 4) println(state)
      action(i) = expert.chooseTransition(s1, state)
      println(action(i))
      file.write(state.toString + "\n")
      file.write(action(i) + " : " + (state.nodesToProcess match { case Nil => ""; case _ => state.nodesToProcess.head }) + " -> " + (
        state.childrenToProcess match { case Nil => ""; case _ => state.childrenToProcess.head }) + "\n")

      file.flush
      state = action(i)(state)
    }                                             //> DeleteNode
                                                  //| WARNING: Found duplicate match for concept attack-01
                                                  //| WARNING: Found duplicate match for concept cyber
                                                  //| NextNode: 0 -> UNKNOWN
                                                  //| DeleteNode
                                                  //| NextEdge: 5 -> mod
                                                  //| NextNode: 24 -> attack-01
                                                  //| 
                                                  //| NodesToGo:	List(5, 0)
                                                  //| Children:	List(3)
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(0 -> ROOT, 2 -> cyber, 3 -> attack-01, 5 -> unprecedented)
                                                  //| SpanMap:	Map(2 -> (2,3), 3 -> (3,4), 5 -> (5,6))
                                                  //| Edges:	Map((0,5) -> root, (3,2) -> mod, (5,3) -> nsubj)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	List(4, 1)
                                                  //| Mappings:	5 -> 0
                                                  //| 3 -> 0.1
                                                  //| 2 -> 0.1.0
                                                  //| 0 -> ROOT
                                                  //| NextNode: 24 -> attack-01
                                                  //| NextEdge: 5 -> mod
                                                  //| DeleteNode
                                                  //| 
                                                  //| ReversePolarity
                                                  //| 
                                                  //| NodesToGo:	List(5, 6, 0)
                                                  //| Children:	List(6, 3)
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(0 -> ROOT, 2 -> cyber, 3 -> attack-01, 5 -> unprecedented, 6
                                                  //|  -> -)
                                                  //| SpanMap:	Map(2 -> (2,3), 3 -> (3,4), 5 -> (5,6), 6 -> (5,6))
                                                  //| Edges:	Map((0,5) -> root, (3,2) -> mod, (5,3) -> nsubj, (5,6) -> -#)
                                                  //| InsertedNodes:	Map(6 -> 0.0)
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	List(4, 1)
                                                  //| Mappings:	0 -> ROOT
                                                  //| 5 -> 0
                                                  //| 6 -> 0.0
                                                  //| 2 -> 0.1.0
                                                  //| 3 -> 0.1
                                                  //| ReversePolarity
                                                  //| NextNode: 24 -> attack-01
                                                  //| NextEdge: 5 -> mod
                                                  //| 
                                                  //| NextEdge: 1 -> polarity
                                                  //| 
                                                  //| NodesToGo:	List(5, 6, 0)
                                                  //| Children:	List(3)
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(0 -> ROOT, 2 -> cyber, 3 -> attack-01, 5 -> unprecedented, 6
                                                  //|  -> -)
                                                  //| SpanMap:	Map(2 -> (2,3), 3 -> (3,4), 5 -> (5,6), 6 -> (5,6))
                                                  //| Edges:	Map((0,5) -> root, (3,2) -> mod, (5,3) -> nsubj, (5,6) -> polari
                                                  //| ty)
                                                  //| InsertedNodes:	Map(6 -> 0.0)
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	List(4, 1)
                                                  //| Mappings:	0 -> ROOT
                                                  //| 5 -> 0
                                                  //| 6 -> 0.0
                                                  //| 2 -> 0.1.0
                                                  //| 3 -> 0.1
                                                  //| NextEdge: 1 -> polarity
                                                  //| ReversePolarity
                                                  //| NextNode: 24 -> attack-01
                                                  //| 
                                                  //| NextEdge: 0 -> UNKNOWN
                                                  //| 
                                                  //| NodesToGo:	List(5, 6, 0)
                                                  //| Children:	List()
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(0 -> ROOT, 2 -> cyber, 3 -> attack-01, 5 -> unprecedented, 6
                                                  //|  -> -)
                                                  //| SpanMap:	Map(2 -> (2,3), 3 -> (3,4), 5 -> (5,6), 6 -> (5,6))
                                                  //| Edges:	Map((0,5) -> root, (3,2) -> mod, (5,3) -> UNKNOWN, (5,6) -> pola
                                                  //| rity)
                                                  //| InsertedNodes:	Map(6 -> 0.0)
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	List(4, 1)
                                                  //| Mappings:	0 -> ROOT
                                                  //| 5 -> 0
  }
  file.close
  println("Here")                                 //> Here
  val outputAdv = RunDagger.sampleTrajectory(s1, "C:\\AMR\\WangXueExpert_output.txt", new WangXueExpert)
                                                  //> outputAdv  : amr.Sentence = Sentence(The cyber attacks were unprecedented.,
                                                  //| 
                                                  //| NodeMap:	Map(0 -> ROOT, 2 -> cyber, 3 -> attack-01, 5 -> unprecedented, 6
                                                  //|  -> -)
                                                  //| SpanMap:	Map(2 -> (2,3), 3 -> (3,4), 5 -> (5,6), 6 -> (5,6))
                                                  //| Edges:	Map((0,5) -> ROOT, (3,2) -> mod, (5,3) -> UNKNOWN, (5,6) -> pola
                                                  //| rity)
                                                  //| InsertedNodes:	Map(6 -> 0.0)
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	List(4, 1),Some(AMRGraph(Map(5 -> unprecedented, 6 -> -,
                                                  //|  0 -> ROOT, 2 -> cyber, 3 -> attack-01),Map(5 -> (5,6), 6 -> (5,6), 2 -> (2
                                                  //| ,3), 3 -> (3,4)),Map((0,5) -> ROOT, (3,2) -> mod, (5,3) -> UNKNOWN, (5,6) -
                                                  //| > polarity))),Map(0 -> ROOT, 5 -> 5, 6 -> 6, 2 -> 2, 3 -> 3))
  outputAdv                                       //> res3: amr.Sentence = Sentence(The cyber attacks were unprecedented.,
                                                  //| NodeMap:	Map(0 -> ROOT, 2 -> cyber, 3 -> attack-01, 5 -> unprecedented, 6
                                                  //|  -> -)
                                                  //| SpanMap:	Map(2 -> (2,3), 3 -> (3,4), 5 -> (5,6), 6 -> (5,6))
                                                  //| Edges:	Map((0,5) -> ROOT, (3,2) -> mod, (5,3) -> UNKNOWN, (5,6) -> pola
                                                  //| rity)
                                                  //| InsertedNodes:	Map(6 -> 0.0)
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	List(4, 1),Some(AMRGraph(Map(5 -> unprecedented, 6 -> -,
                                                  //|  0 -> ROOT, 2 -> cyber, 3 -> attack-01),Map(5 -> (5,6), 6 -> (5,6), 2 -> (2
                                                  //| ,3), 3 -> (3,4)),Map((0,5) -> ROOT, (3,2) -> mod, (5,3) -> UNKNOWN, (5,6) -
                                                  //| > polarity))),Map(0 -> ROOT, 5 -> 5, 6 -> 6, 2 -> 2, 3 -> 3))
}