package amr

object problematicAMR {
 // val testData = AMRGraph.importFile("C:\\AMR\\problemticAMR.txt") - Kuwolsan that created loop
 
// val testData = AMRGraph.importFile("C:\\AMR\\AMRInstance4.txt") // Nato Considers...
 val testData = AMRGraph.importFile("C:\\AMR\\simpleDate.txt")
                                                  //> testData  : IndexedSeq[(String, String)] = Vector((2002-01-05,(d / date-enti
                                                  //| ty :year 2002 :month 1 :day 5)))
  val expert = new WangXueExpert                  //> expert  : amr.WangXueExpert = amr.WangXueExpert@548a9f61
  val expertSystem = new WangXueTransitionSystem  //> expertSystem  : amr.WangXueTransitionSystem = amr.WangXueTransitionSystem@17
                                                  //| 53acfe
  val s1 = Sentence(testData(0)._1, testData(0)._2)
                                                  //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.2 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [4.5 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [3.5 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [2.7 sec].
                                                  //| s1  : amr.Sentence = Sentence(2002-01-05,
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> 2002, 2 -> 1, 3 -> 5)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4))
                                                  //| Edges:	Map((0,1) -> root, (1,3) -> dep, (3,2) -> num)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	List(),Some(AMRGraph(Map(0.1 -> 1, 0.0 -> 2002, 0 -> dat
                                                  //| e-entity, 0.2 -> 5, ROOT -> ROOT),Map(0 -> (1,4), 0.0 -> (1,4), 0.1 -> (1,4)
                                                  //| , 0.2 -> (1,4)),Map((0,0.0) -> year, (0,0.1) -> month, (0,0.2) -> day, (ROOT
                                                  //| ,0) -> ROOT))),Map(2 -> 0.1, 1 -> 0.0, 3 -> 0.2, 0 -> ROOT))
  var state = expertSystem.init(s1)               //> state  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(2, 3, 1, 0)
                                                  //| Children:	List()
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> 2002, 2 -> 1, 3 -> 5)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4))
                                                  //| Edges:	Map((0,1) -> root, (1,3) -> dep, (3,2) -> num)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	List()
                                                  //| Mappings:	2 -> 0.1
                                                  //| 1 -> 0.0
                                                  //| 3 -> 0.2
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
  s1.positionToAMR                                //> res0: Map[Int,String] = Map(2 -> 0.1, 1 -> 0.0, 3 -> 0.2, 0 -> ROOT)
  s1.amr.get.nodes                                //> res1: Map[String,String] = Map(0.1 -> 1, 0.0 -> 2002, 0 -> date-entity, 0.2 
                                                  //| -> 5, ROOT -> ROOT)
  s1.amr.get.arcs                                 //> res2: Map[(String, String),String] = Map((0,0.0) -> year, (0,0.1) -> month, 
                                                  //| (0,0.2) -> day, (ROOT,0) -> ROOT)
  import java.io._
  val file = new FileWriter("C://AMR//i4_output.txt")
                                                  //> file  : java.io.FileWriter = java.io.FileWriter@18d87d80
  for (i <- 0 to 300) {
    if (!expertSystem.isTerminal(state)) {
      stateHistory(i) = state
      action(i) = expert.chooseTransition(s1, state)
      file.write(state.toString + "\n")
      file.write(action(i) + " : " + (state.nodesToProcess match { case Nil => ""; case _ => state.nodesToProcess.head }) + " -> " + (
        state.childrenToProcess match { case Nil => ""; case _ => state.childrenToProcess.head }) + "\n")

      file.flush
      state = action(i)(state)
    }                                             //> WARNING: Found duplicate match for concept attack-01
                                                  //| WARNING: Found duplicate match for concept cyber
  }
  file.close
  println("Here")                                 //> Here
  val outputAdv = RunDagger.sampleTrajectory(s1, "C:\\AMR\\WangXueExpert_output.txt", new WangXueExpert)
                                                  //> outputAdv  : amr.Sentence = Sentence(2002-01-05,
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> 2002, 2 -> 1, 3 -> 5, 4 -> date-entity)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (2,3))
                                                  //| Edges:	Map((0,4) -> ROOT, (4,1) -> year, (4,2) -> month, (4,3) -> UNKNO
                                                  //| WN)
                                                  //| InsertedNodes:	Map(4 -> 0)
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set((3,4), (1,4))
                                                  //| DeletedNodes:	List(),Some(AMRGraph(Map(4 -> date-entity, 1 -> 2002, 0 
                                                  //| -> ROOT, 2 -> 1, 3 -> 5),Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (2,3)
                                                  //| ),Map((4,2) -> month, (4,3) -> UNKNOWN, (0,4) -> ROOT, (4,1) -> year))),Map
                                                  //| (0 -> ROOT, 1 -> 1, 2 -> 2, 3 -> 3, 4 -> 4))
  outputAdv                                       //> res3: amr.Sentence = Sentence(2002-01-05,
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> 2002, 2 -> 1, 3 -> 5, 4 -> date-entity)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (2,3))
                                                  //| Edges:	Map((0,4) -> ROOT, (4,1) -> year, (4,2) -> month, (4,3) -> UNKNO
                                                  //| WN)
                                                  //| InsertedNodes:	Map(4 -> 0)
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set((3,4), (1,4))
                                                  //| DeletedNodes:	List(),Some(AMRGraph(Map(4 -> date-entity, 1 -> 2002, 0 
                                                  //| -> ROOT, 2 -> 1, 3 -> 5),Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (2,3)
                                                  //| ),Map((4,2) -> month, (4,3) -> UNKNOWN, (0,4) -> ROOT, (4,1) -> year))),Map
                                                  //| (0 -> ROOT, 1 -> 1, 2 -> 2, 3 -> 3, 4 -> 4))
}