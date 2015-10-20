package amr

object problematicAMR {

val testData = AMRGraph.importFile("C:\\AMR\\daggerTest\\Target1.txt")
                                                  //> testData  : IndexedSeq[(String, String)] = Vector((The agreement will provid
                                                  //| e staff and funding for the research center.,(p / provide-01 :ARG0 (a / agre
                                                  //| e-01) :ARG1 (a2 / and :op1 (s / staff :prep-for (c / center :mod (r / resear
                                                  //| ch-01))) :op2 (f / fund-01 :prep-for c)))))
  val expert = new WangXueExpert                  //> expert  : amr.WangXueExpert = amr.WangXueExpert@675d3402
  val expertSystem = WangXueTransitionSystem      //> expertSystem  : amr.WangXueTransitionSystem.type = amr.WangXueTransitionSyst
                                                  //| em$@d8355a8
  expertSystem.reentrance = true
  ImportConcepts.initialise("C:\\AMR\\daggerTest\\Target1.txt")
// ImportConcepts.relationString
  val s1 = Sentence(testData(0)._1, testData(0)._2)
                                                  //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.1 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [3.9 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [3.3 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [2.8 sec].
                                                  //| s1  : amr.Sentence = Sentence(The agreement will provide staff and funding f
                                                  //| or the research center.,
                                                  //| NodeMap:	Map(1 -> The, 2 -> agreement, 3 -> will, 4 -> provide, 5 -> staf
                                                  //| f, 6 -> and, 7 -> funding, 8 -> for, 9 -> the, 10 -> research, 11 -> center)
                                                  //| 
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,12
                                                  //| ))
                                                  //| Edges:	Map((2,1) -> det, (4,2) -> nsubj, (4,3) -> aux, (4,5) -> dobj, (
                                                  //| 4,8) -> prep, (5,6) -> cc, (5,7) -> conj, (8,11) -> pobj, (11,9) -> det, (11
                                                  //| ,10) -> nn)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map(),Some(AMRGraph(Map(0.1 -> and, 0.1.0 -> staff, 0.1.
                                                  //| 0.0.0 -> research-01, 0.0 -> agree-01, 0.1.0.0 -> center, 0 -> provide-01, 0
                                                  //| .1.1 -> fund-01),Map(0.1 -> (6,7), 0.1.0 -> (5,6), 0.1.0.0.0 -> (10,11), 0.0
                                                  //|  -> (2,3), 0.1.0.0 -> (11,12), 0 -> (4,5), 0.1.1 -> (7,8)),Map((0.1,0.1.0) -
                                                  //| > opN, (0.1.1,0.1.0.0) -> prep-for, (0.1.0,0.1.0.0) -> prep-for, (0,0.1) -> 
                                                  //| ARG1, (0.1.0.0,0.1.0.0.0) -> mod, (0,0.0) -> ARG0, (0.1,0.1.1) -> opN),Map((
                                                  //| 0.1,0.1.0) -> op1, (0.1.1,0.1.0.0) -> prep-for, (0.1.0,0.1.0.0) -> prep-for,
                                                  //|  (0,0.1) -> ARG1, (0.1.0.0,0.1.0.0.0) -> mod, (0,0.0) -> ARG0, (0.1,0.1.1) -
                                                  //| > op2),List())),Map(5 -> 0.1.0, 10 -> 0.1.0.0.0, 6 -> 0.1, 2 -> 0.0, 7 -> 0.
                                                  //| 1.1, 11 -> 0.1.0.0, 4 -> 0))
  var state = expertSystem.init(s1)               //> state  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(10, 9, 7, 6, 11, 1, 5, 8, 2, 3, 4)
                                                  //| Children:	List()
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(1 -> The, 2 -> agreement, 3 -> will, 4 -> provide, 5 -> staf
                                                  //| f, 6 -> and, 7 -> funding, 8 -> for, 9 -> the, 10 -> research, 11 -> center)
                                                  //| 
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,12
                                                  //| ))
                                                  //| Edges:	Map((2,1) -> det, (4,2) -> nsubj, (4,3) -> aux, (4,5) -> dobj, (
                                                  //| 4,8) -> prep, (5,6) -> cc, (5,7) -> conj, (8,11) -> pobj, (11,9) -> det, (11
                                                  //| ,10) -> nn)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map()
                                                  //| Mappings:	5 -> 0.1.0
                                                  //| 10 -> 0.1.0.0.0
                                                  //| 6 -> 0.1
                                                  //| 2 -> 0.0
                                                  //| 7 -> 0.1.1
                                                  //| 11 -> 0.1.0.0
                                                  //| 4 -> 0
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
  s1.positionToAMR                                //> res0: Map[Int,String] = Map(5 -> 0.1.0, 10 -> 0.1.0.0.0, 6 -> 0.1, 2 -> 0.0,
                                                  //|  7 -> 0.1.1, 11 -> 0.1.0.0, 4 -> 0)
  s1.amr.get.nodes                                //> res1: Map[String,String] = Map(0.1 -> and, 0.1.0 -> staff, 0.1.0.0.0 -> rese
                                                  //| arch-01, 0.0 -> agree-01, 0.1.0.0 -> center, 0 -> provide-01, 0.1.1 -> fund-
                                                  //| 01)
  s1.amr.get.arcs                                 //> res2: Map[(String, String),String] = Map((0.1,0.1.0) -> opN, (0.1.1,0.1.0.0)
                                                  //|  -> prep-for, (0.1.0,0.1.0.0) -> prep-for, (0,0.1) -> ARG1, (0.1.0.0,0.1.0.0
                                                  //| .0) -> mod, (0,0.0) -> ARG0, (0.1,0.1.1) -> opN)
  import java.io._
  val file = new FileWriter("C://AMR//i4_output.txt")
                                                  //> file  : java.io.FileWriter = java.io.FileWriter@74d1dc36
  for (i <- 0 to 300) {
    if (!expertSystem.isTerminal(state)) {
      stateHistory(i) = state
      action(i) = expert.chooseTransition(s1, state)
      file.write("\n" + state.toString + "\n")
      file.write(action(i) + " : " + (state.nodesToProcess match { case Nil => ""; case _ => state.nodesToProcess.head }) + " -> " + (
        state.childrenToProcess match { case Nil => ""; case _ => state.childrenToProcess.head }) + "\n")

			file.write(s1.AMRToPosition.toString)
      file.flush
      state = action(i)(state)
    }                                             //> Loading Relations
  }
  file.close
  val outputAdv = RunDagger.sampleTrajectory(s1, "C:\\AMR\\WangXueExpert_output.txt", new WangXueExpert)
                                                  //> outputAdv  : amr.Sentence = Sentence(The agreement will provide staff and f
                                                  //| unding for the research center.,
                                                  //| NodeMap:	Map(2 -> agree-01, 4 -> provide-01, 5 -> staff, 6 -> and, 7 -> f
                                                  //| und-01, 10 -> research-01, 11 -> center)
                                                  //| SpanMap:	Map(2 -> (2,3), 4 -> (4,5), 5 -> (5,6), 6 -> (6,7), 7 -> (7,8), 
                                                  //| 10 -> (10,11), 11 -> (8,11))
                                                  //| Edges:	Map((4,2) -> ARG0, (4,6) -> ARG1, (5,11) -> prep-for, (6,5) -> o
                                                  //| pN, (6,7) -> opN, (7,11) -> prep-for, (11,10) -> mod)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map(11 -> List((8,for)))
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map(11 -> List((9,the)), 2 -> List((1,The)), 4 -> List((
                                                  //| 3,will))),Some(AMRGraph(Map(4 -> provide-01, 11 -> center, 5 -> staff, 10 -
                                                  //| > research-01, 6 -> and, 2 -> agree-01, 7 -> fund-01),Map(4 -> (4,5), 11 ->
                                                  //|  (8,11), 5 -> (5,6), 10 -> (10,11), 6 -> (6,7), 2 -> (2,3), 7 -> (7,8)),Map
                                                  //| ((11,10) -> mod, (4,6) -> ARG1, (6,5) -> opN, (6,7) -> opN, (4,2) -> ARG0, 
                                                  //| (7,11) -> prep-for, (5,11) -> prep-for),Map(),List())),Map(5 -> 5, 10 -> 10
                                                  //| , 6 -> 6, 2 -> 2, 7 -> 7, 11 -> 11, 4 -> 4))
     
}