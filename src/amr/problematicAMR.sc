package amr

object problematicAMR {

val testData = AMRGraph.importFile("C:\\AMR\\simpleDate.txt")
                                                  //> testData  : IndexedSeq[(String, String)] = Vector((2008-05-14,(d / date-enti
                                                  //| ty :month 5 :day 14 :year 2008)))
  val expert = new WangXueExpert                  //> expert  : amr.WangXueExpert = amr.WangXueExpert@675d3402
  val expertSystem = WangXueTransitionSystem      //> expertSystem  : amr.WangXueTransitionSystem.type = amr.WangXueTransitionSyst
                                                  //| em$@d8355a8
  expertSystem.reentrance = true
  ImportConcepts.initialise("C:\\AMR\\simpleDate.txt")
// ImportConcepts.relationString
  val s1 = Sentence(testData(0)._1, testData(0)._2)
                                                  //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.3 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [3.8 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [3.2 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [2.7 sec].
                                                  //| s1  : amr.Sentence = Sentence(2008-05-14,
                                                  //| NodeMap:	Map(1 -> 2008, 2 -> 5, 3 -> 14)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4))
                                                  //| Edges:	Map((1,3) -> dep, (3,2) -> num)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map(),Some(AMRGraph(Map(0.1 -> 14, 0.0 -> 5, 0 -> date-e
                                                  //| ntity, 0.2 -> 2008),Map(0 -> (1,4), 0.0 -> (1,4), 0.1 -> (1,4), 0.2 -> (1,4)
                                                  //| ),Map((0,0.0) -> month, (0,0.1) -> day, (0,0.2) -> year),List())),Map(2 -> 0
                                                  //| .0, 1 -> 0.2, 3 -> 0.1))
  var state = expertSystem.init(s1)               //> state  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(2, 3, 1)
                                                  //| Children:	List()
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(1 -> 2008, 2 -> 5, 3 -> 14)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4))
                                                  //| Edges:	Map((1,3) -> dep, (3,2) -> num)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map()
                                                  //| Mappings:	2 -> 0.0
                                                  //| 1 -> 0.2
                                                  //| 3 -> 0.1
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
  s1.positionToAMR                                //> res0: Map[Int,String] = Map(2 -> 0.0, 1 -> 0.2, 3 -> 0.1)
  s1.amr.get.nodes                                //> res1: Map[String,String] = Map(0.1 -> 14, 0.0 -> 5, 0 -> date-entity, 0.2 ->
                                                  //|  2008)
  s1.amr.get.arcs                                 //> res2: Map[(String, String),String] = Map((0,0.0) -> month, (0,0.1) -> day, (
                                                  //| 0,0.2) -> year)
  import java.io._
  val file = new FileWriter("C://AMR//i4_output.txt")
                                                  //> file  : java.io.FileWriter = java.io.FileWriter@169e6180
  for (i <- 0 to 300) {
    if (!expertSystem.isTerminal(state)) {
      stateHistory(i) = state
      action(i) = expert.chooseTransition(s1, state)
      file.write(state.toString + "\n")
      file.write(action(i) + " : " + (state.nodesToProcess match { case Nil => ""; case _ => state.nodesToProcess.head }) + " -> " + (
        state.childrenToProcess match { case Nil => ""; case _ => state.childrenToProcess.head }) + "\n")

			file.write(s1.AMRToPosition.toString)
      file.flush
      state = action(i)(state)
    }                                             //> Loading Relations
  }
  file.close
  val outputAdv = RunDagger.sampleTrajectory(s1, "C:\\AMR\\WangXueExpert_output.txt", new WangXueExpert)
                                                  //> outputAdv  : amr.Sentence = Sentence(2008-05-14,
                                                  //| NodeMap:	Map(1 -> 2008, 2 -> 5, 3 -> 14, 4 -> date-entity)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (2,3))
                                                  //| Edges:	Map((4,1) -> year, (4,2) -> month, (4,3) -> day)
                                                  //| InsertedNodes:	Map(4 -> 0)
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set((3,4), (1,4))
                                                  //| DeletedNodes:	Map(),Some(AMRGraph(Map(1 -> 2008, 2 -> 5, 3 -> 14, 4 ->
                                                  //|  date-entity),Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (2,3)),Map((4,2)
                                                  //|  -> month, (4,3) -> day, (4,1) -> year),List())),Map(1 -> 1, 2 -> 2, 4 -> 4
                                                  //| , 3 -> 3))
     
}