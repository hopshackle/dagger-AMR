package amr

object problematicAMR {

val testData = AMRGraph.importFile("C:\\AMR\\TargetBell.txt")
                                                  //> testData  : IndexedSeq[(String, String)] = Vector((Bell Canada spokesman And
                                                  //| rew Cole stated that Bell Canada is prepared and remains prepared for variou
                                                  //| s potential situations and scenarios.,(s / state-01 :ARG0 (p3 / person :name
                                                  //|  (n / name :op1 "Andrew" :op2 "Cole") :ARG0-of (h / have-org-role-91 :ARG1 (
                                                  //| c / company :name (n2 / name :op1 "Bell" :op2 "Canada")) :ARG2 (s2 / spokesm
                                                  //| an))) :ARG1 (a / and :op1 (p / prepare-02 :ARG1 c :ARG2 (a2 / and :op1 (s3 /
                                                  //|  situation) :op2 (s4 / scenario) :mod (p2 / potential) :quant (v / various))
                                                  //| ) :op2 (r / remain-01 :ARG1 c :ARG3 p)))))
  val expert = new WangXueExpert                  //> expert  : amr.WangXueExpert = amr.WangXueExpert@2a5ca609
  val expertSystem = WangXueTransitionSystem      //> expertSystem  : amr.WangXueTransitionSystem.type = amr.WangXueTransitionSyst
                                                  //| em$@4c70fda8
  AMRGraph.setAligner("improved")
  expertSystem.reentrance = true
  ImportConcepts.initialise("C:\\AMR\\TargetBell.txt")
// ImportConcepts.relationString
  val s1 = Sentence(testData(0)._1, testData(0)._2)
                                                  //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.2 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [4.3 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [3.7 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [2.7 sec].
                                                  //| s1  : amr.Sentence = Sentence(Bell Canada spokesman Andrew Cole stated that 
                                                  //| Bell Canada is prepared and remains prepared for various potential situation
                                                  //| s and scenarios.,
                                                  //| NodeMap:	Map(1 -> Bell, 2 -> Canada, 3 -> spokesman, 4 -> Andrew, 5 -> Co
                                                  //| le, 6 -> stated, 7 -> that, 8 -> Bell, 9 -> Canada, 10 -> is, 11 -> prepared
                                                  //| , 12 -> and, 13 -> remains, 14 -> prepared, 15 -> for, 16 -> various, 17 -> 
                                                  //| potential, 18 -> situations, 19 -> and, 20 -> scenarios)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,12
                                                  //| ), 12 -> (12,13), 13 -> (13,14), 14 -> (14,15), 15 -> (15,16), 16 -> (16,17)
                                                  //| , 17 -> (17,18), 18 -> (18,19), 19 -> (19,20), 20 -> (20,21))
                                                  //| Edges:	Map((5,1) -> nn, (5,2) -> nn, (5,3) -> nn, (5,4) -> nn, (6,5) ->
                                                  //|  nsubj, (6,11) -> ccomp, (6,12) -> cc, (6,14) -> conj, (9,8) -> nn, (11,7) -
                                                  //| > mark, (11,9) -> nsubj, (11,10) -> cop, (14,13) -> cop, (14,15) -> prep, (1
                                                  //| 5,18) -> pobj, (18,16) -> amod, (18,17) -> amod, (18,19) -> cc, (18,20) -> c
                                                  //| onj)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map(),Some(AMRGraph(Map(0.1 -> and, 0.0.1.0.0.0 -> "Bell
                                                  //| ", 0.0.0 -> name, 0.1.0.0.3 -> various, 0.1.0 -> prepare-02, 0.0.1.0 -> comp
                                                  //| any, 0.1.0.0.0 -> situation, 0.0 -> person, 0.0.0.0 -> "Andrew", 0.1.0.0.1 -
                                                  //| > scenario, 0.0.1.1 -> spokesman, 0.0.1 -> have-org-role-91, 0.1.0.0 -> and,
                                                  //|  0.0.1.0.0 -> name, 0.1.0.0.2 -> potential, 0 -> state-01, 0.0.0.1 -> "Cole"
                                                  //| , 0.0.1.0.0.1 -> "Canada", 0.1.1 -> remain-01),Map(0.1 -> (12,13), 0.0.1.0.0
                                                  //| .0 -> (8,10), 0.0.0 -> (4,6), 0.1.0.0.3 -> (16,17), 0.1.0 -> (14,15), 0.0.1.
                                                  //| 0 -> (8,10), 0.1.0.0.0 -> (18,19), 0.0 -> (4,6), 0.0.0.0 -> (4,6), 0.1.0.0.1
                                                  //|  -> (20,21), 0.0.1.1 -> (3,4), 0.1.0.0 ->
                                                  //| Output exceeds cutoff limit.
  var state = expertSystem.init(s1)               //> state  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(19, 17, 20, 16, 18, 8, 13, 15, 2, 1, 4, 3, 7, 10, 9, 14, 5,
                                                  //|  11, 12, 6)
                                                  //| Children:	List()
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(1 -> Bell, 2 -> Canada, 3 -> spokesman, 4 -> Andrew, 5 -> Co
                                                  //| le, 6 -> stated, 7 -> that, 8 -> Bell, 9 -> Canada, 10 -> is, 11 -> prepared
                                                  //| , 12 -> and, 13 -> remains, 14 -> prepared, 15 -> for, 16 -> various, 17 -> 
                                                  //| potential, 18 -> situations, 19 -> and, 20 -> scenarios)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,12
                                                  //| ), 12 -> (12,13), 13 -> (13,14), 14 -> (14,15), 15 -> (15,16), 16 -> (16,17)
                                                  //| , 17 -> (17,18), 18 -> (18,19), 19 -> (19,20), 20 -> (20,21))
                                                  //| Edges:	Map((5,1) -> nn, (5,2) -> nn, (5,3) -> nn, (5,4) -> nn, (6,5) ->
                                                  //|  nsubj, (6,11) -> ccomp, (6,12) -> cc, (6,14) -> conj, (9,8) -> nn, (11,7) -
                                                  //| > mark, (11,9) -> nsubj, (11,10) -> cop, (14,13) -> cop, (14,15) -> prep, (1
                                                  //| 5,18) -> pobj, (18,16) -> amod, (18,17) -> amod, (18,19) -> cc, (18,20) -> c
                                                  //| onj)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map()
                                                  //| Mappings:	5 -> 0.0.0.1
                                                  //| 14 -> 0.1.0
                                                  //| 20 -> 0.1.0.0.1
                                                  //| 6 -> 0
                                                  //| 9 -> 0.0.1.0.0.1
                                                  //| 13 -> 0.1.1
                                                  //| 17 -> 0.1.0.0.2
                                                  //| 12 -> 0.1
                                                  //| 3 -> 0.0.1.1
                                                  //| 18 -> 0.1.0.0.0
                                                  //| 16 -> 0.1.0.0.3
                                                  //| 8 -> 0.0.1.0.0.0
                                                  //| 19 -> 0.1.0.0
                                                  //| 4 -> 0.0.0.0
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
  s1.positionToAMR                                //> res0: Map[Int,String] = Map(5 -> 0.0.0.1, 14 -> 0.1.0, 20 -> 0.1.0.0.1, 6 ->
                                                  //|  0, 9 -> 0.0.1.0.0.1, 13 -> 0.1.1, 17 -> 0.1.0.0.2, 12 -> 0.1, 3 -> 0.0.1.1,
                                                  //|  18 -> 0.1.0.0.0, 16 -> 0.1.0.0.3, 8 -> 0.0.1.0.0.0, 19 -> 0.1.0.0, 4 -> 0.0
                                                  //| .0.0)
  s1.amr.get.nodes                                //> res1: Map[String,String] = Map(0.1 -> and, 0.0.1.0.0.0 -> "Bell", 0.0.0 -> n
                                                  //| ame, 0.1.0.0.3 -> various, 0.1.0 -> prepare-02, 0.0.1.0 -> company, 0.1.0.0.
                                                  //| 0 -> situation, 0.0 -> person, 0.0.0.0 -> "Andrew", 0.1.0.0.1 -> scenario, 0
                                                  //| .0.1.1 -> spokesman, 0.0.1 -> have-org-role-91, 0.1.0.0 -> and, 0.0.1.0.0 ->
                                                  //|  name, 0.1.0.0.2 -> potential, 0 -> state-01, 0.0.0.1 -> "Cole", 0.0.1.0.0.1
                                                  //|  -> "Canada", 0.1.1 -> remain-01)
  s1.amr.get.arcs                                 //> res2: Map[(String, String),String] = Map((0.0,0.0.0) -> name, (0.0.1.0.0,0.0
                                                  //| .1.0.0.0) -> opN, (0.0.0,0.0.0.0) -> opN, (0.1,0.1.0) -> opN, (0.0.1.0.0,0.0
                                                  //| .1.0.0.1) -> opN, (0.1.0.0,0.1.0.0.2) -> mod, (0.1.0.0,0.1.0.0.3) -> quant, 
                                                  //| (0.1.0,0.1.0.0) -> ARG2, (0.1.0,0.0.1.0) -> ARG1, (0,0.1) -> ARG1, (0.0.0,0.
                                                  //| 0.0.1) -> opN, (0.0.1,0.0.1.1) -> ARG2, (0.1.0.0,0.1.0.0.0) -> opN, (0.1.1,0
                                                  //| .0.1.0) -> ARG1, (0.1.0.0,0.1.0.0.1) -> opN, (0,0.0) -> ARG0, (0.0.1,0.0.1.0
                                                  //| ) -> ARG1, (0.0.1.0,0.0.1.0.0) -> name, (0.1.1,0.1.0) -> ARG3, (0.0,0.0.1) -
                                                  //| > ARG0-of, (0.1,0.1.1) -> opN)
  import java.io._
  val file = new FileWriter("C://AMR//Bell_improved.txt")
                                                  //> file  : java.io.FileWriter = java.io.FileWriter@23202fce
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
                                                  //> outputAdv  : amr.Sentence = Sentence(Bell Canada spokesman Andrew Cole stat
                                                  //| ed that Bell Canada is prepared and remains prepared for various potential 
                                                  //| situations and scenarios.,
                                                  //| NodeMap:	Map(3 -> spokesman, 4 -> Andrew, 5 -> Cole, 6 -> state-01, 8 -> 
                                                  //| Bell, 9 -> Canada, 12 -> and, 13 -> remain-01, 14 -> prepare-02, 16 -> vari
                                                  //| ous, 17 -> potential, 18 -> situation, 19 -> and, 20 -> scenario, 21 -> nam
                                                  //| e, 22 -> company, 23 -> have-org-role-91, 24 -> person, 25 -> name)
                                                  //| SpanMap:	Map(3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 6 -> (6,7), 8 -> (8,9), 
                                                  //| 9 -> (9,10), 12 -> (12,13), 13 -> (13,14), 14 -> (14,15), 16 -> (16,17), 17
                                                  //|  -> (17,18), 18 -> (18,19), 19 -> (15,19), 20 -> (20,21), 21 -> (8,9), 22 -
                                                  //| > (8,9), 23 -> (8,9), 24 -> (8,11), 25 -> (4,5))
                                                  //| Edges:	Map((6,12) -> ARG1, (6,24) -> ARG0, (12,13) -> opN, (12,14) -> o
                                                  //| pN, (13,14) -> ARG3, (14,19) -> ARG2, (19,16) -> quant, (19,17) -> mod, (19
                                                  //| ,18) -> opN, (19,20) -> opN, (21,8) -> opN, (21,9) -> opN, (22,21) -> name,
                                                  //|  (23,3) -> ARG2, (23,22) -> ARG1, (24,23) -> ARG0-of, (24,25) -> name, (25,
                                                  //| 4) -> opN, (25,5) -> opN)
                                                  //| InsertedNodes:	Map(24 -> 0.0, 25 -> 0.0.0, 21 -> 0.0.1.0.0, 22 -> 0.0.1
                                                  //| .0, 23 -> 0.0.1)
                                                  //| MergedNodes:	Map(19 -> List((15,for)), 24 -> List((11,prepared)))
                                                  //| SwappedArcs:	Set((18,19), (9,24), (14,13), (5,25))
                                                  //| DeletedNodes:	Map(5 -> List((1,Bell), (2,Canada)), 11 -> List((10,is),
                                                  //|  (7,that))),Some(AMRGraph(Map(12 -> and, 8 -> "Bell", 19 -> and, 23 -> have
                                                  //| -org-role-91, 4 -> "Andrew", 9 -> "Canada", 22 -> company, 13 -> remain-01,
                                                  //|  24 -> person, 16 -> various, 5 -> "Cole", 21 -> name, 6 -> state-01, 17 ->
                                                  //|  potential, 25 -> name, 14 -> prepare-02, 20 -> scenario, 18 -> situation, 
                                                  //| 3 -> spokesman),Map(12 -> (12,13), 8 -> (8,9), 19 -> (15,19), 23 -> (8,9), 
                                                  //| 4 -> (4,5), 9 -> (9,10), 22 -> (8,9), 13 -> (13,14), 24 -> (8,11), 16 -> (1
                                                  //| 6,17), 5 -> (5,6), 21 -> (8,9), 6 -> (6,7), 17 -> (17,18), 25 -> (4,5), 14 
                                                  //| -> (14,15), 20 -> (20,21), 18 -> (18,19), 3 -> (3,4)),Map((6,12) -> ARG1, (
                                                  //| 25,4) -> opN, (23,22) -> ARG1, (12,13) -> opN, (6,24) -> ARG0, (19,17) -> m
                                                  //| od, (24,23) -> ARG0-of, (19,18) -> opN, (19,20) -> opN, (19,16) -> quant, (
                                                  //| 14,19) -> ARG2, (21,8) -> opN, (21,9) -> opN, (25,5) -> opN, (23,3) -> ARG2
                                                  //| , (13,14) -> ARG3, (22,21) -> name, (24,25) -> name, (12,14) -> opN),Map(),
                                                  //| List())),Map(5 -> 5, 24 -> 24, 25 -> 25, 14 -> 14, 20 -> 20, 6 -> 6
                                                  //| Output exceeds cutoff limit.
     
}