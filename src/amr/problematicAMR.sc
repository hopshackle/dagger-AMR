package amr

object problematicAMR {

val testData = AMRGraph.importFile("C:\\AMR\\TargetIAEA.txt")
                                                  //> testData  : IndexedSeq[(String, String)] = Vector((IAEA accepted North Korea
                                                  //| 's proposal last month but cautioned that the proposal is not a breakthrough
                                                  //| .,(c / contrast-01 :ARG1 (a / accept-01 :ARG0 (o / organization :name (n / n
                                                  //| ame :op1 "IAEA")) :ARG1 (t / thing :ARG1-of (p / propose-01 :ARG0 (c2 / coun
                                                  //| try :name (n2 / name :op1 "North" :op2 "Korea")))) :time (m / month :mod (l 
                                                  //| / last))) :ARG2 (c3 / caution-01 :ARG0 o :ARG1 (b / breakthrough-01 :domain 
                                                  //| t :polarity -)))))
  val expert = new WangXueExpert                  //> expert  : amr.WangXueExpert = amr.WangXueExpert@675d3402
  val expertSystem = WangXueTransitionSystem      //> expertSystem  : amr.WangXueTransitionSystem.type = amr.WangXueTransitionSyst
                                                  //| em$@d8355a8
  AMRGraph.setAligner("improved")
  expertSystem.reentrance = true
  ImportConcepts.initialise("C:\\AMR\\TargetIAEA.txt")
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
                                                  //| im.crf.ser.gz ... done [4.3 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [2.2 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [4.0 sec].
                                                  //| s1  : amr.Sentence = Sentence(IAEA accepted North Korea's proposal last mont
                                                  //| h but cautioned that the proposal is not a breakthrough.,
                                                  //| NodeMap:	Map(1 -> IAEA, 2 -> accepted, 3 -> North, 4 -> Korea, 5 -> 's, 6
                                                  //|  -> proposal, 7 -> last, 8 -> month, 9 -> but, 10 -> cautioned, 11 -> that, 
                                                  //| 12 -> the, 13 -> proposal, 14 -> is, 15 -> not, 16 -> a, 17 -> breakthrough)
                                                  //| 
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,12
                                                  //| ), 12 -> (12,13), 13 -> (13,14), 14 -> (14,15), 15 -> (15,16), 16 -> (16,17)
                                                  //| , 17 -> (17,18))
                                                  //| Edges:	Map((2,1) -> nsubj, (2,6) -> dobj, (2,8) -> tmod, (2,9) -> cc, (
                                                  //| 2,10) -> conj, (4,3) -> nn, (4,5) -> possessive, (6,4) -> poss, (8,7) -> amo
                                                  //| d, (10,17) -> ccomp, (13,12) -> det, (17,11) -> mark, (17,13) -> nsubj, (17,
                                                  //| 14) -> cop, (17,15) -> neg, (17,16) -> det)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map(),Some(AMRGraph(Map(0.1 -> caution-01, 0.0.1.0.0.0 -
                                                  //| > name, 0.0.0 -> organization, 0.1.0 -> breakthrough-01, 0.0.1.0 -> propose-
                                                  //| 01, 0.0.1.0.0.0.1 -> "Korea", 0.0 -> accept-01, 0.0.1.0.0.0.0 -> "North", 0.
                                                  //| 0.2 -> month, 0.0.0.0 -> name, 0.0.1 -> thing, 0.1.0.0 -> -, 0.0.1.0.0 -> co
                                                  //| untry, 0 -> contrast-01, 0.0.0.0.0 -> "IAEA", 0.0.2.0 -> last),Map(0.1 -> (1
                                                  //| 0,11), 0.0.1.0.0.0 -> (3,5), 0.0.0 -> (1,2), 0.1.0 -> (17,18), 0.0.1.0 -> (1
                                                  //| 3,14), 0.0.1.0.0.0.1 -> (3,5), 0.0 -> (2,3), 0.0.1.0.0.0.0 -> (3,5), 0.0.2 -
                                                  //| > (8,9), 0.0.0.0 -> (1,2), 0.0.1 -> (13,14), 0.0.1.0.0 -> (3,5), 0 -> (9,10)
                                                  //| , 0.0.0.0.0 -> (1,2), 0.0.2.0 -> (7,8)),Map((0.0.1.0.0.0,0.0.1.0.0.0.1) -> o
                                                  //| pN, (0.0,0.0.0) -> ARG0, (0.0.1.0.0,0.0.1.0.0.0) -> name, (0.0.0.0,0.0.0.0.0
                                                  //| ) -> opN, (0.0.0,0.0.0.0) -> name, (0.1,0.1.0) -> ARG1, (0.1.0,
                                                  //| Output exceeds cutoff limit.
  var state = expertSystem.init(s1)               //> state  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(12, 13, 14, 15, 11, 16, 5, 3, 17, 7, 4, 10, 8, 6, 9, 1, 2)
                                                  //| Children:	List()
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(1 -> IAEA, 2 -> accepted, 3 -> North, 4 -> Korea, 5 -> 's, 6
                                                  //|  -> proposal, 7 -> last, 8 -> month, 9 -> but, 10 -> cautioned, 11 -> that, 
                                                  //| 12 -> the, 13 -> proposal, 14 -> is, 15 -> not, 16 -> a, 17 -> breakthrough)
                                                  //| 
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,12
                                                  //| ), 12 -> (12,13), 13 -> (13,14), 14 -> (14,15), 15 -> (15,16), 16 -> (16,17)
                                                  //| , 17 -> (17,18))
                                                  //| Edges:	Map((2,1) -> nsubj, (2,6) -> dobj, (2,8) -> tmod, (2,9) -> cc, (
                                                  //| 2,10) -> conj, (4,3) -> nn, (4,5) -> possessive, (6,4) -> poss, (8,7) -> amo
                                                  //| d, (10,17) -> ccomp, (13,12) -> det, (17,11) -> mark, (17,13) -> nsubj, (17,
                                                  //| 14) -> cop, (17,15) -> neg, (17,16) -> det)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map()
                                                  //| Mappings:	10 -> 0.1
                                                  //| 1 -> 0.0.0.0.0
                                                  //| 9 -> 0
                                                  //| 13 -> 0.0.1.0
                                                  //| 2 -> 0.0
                                                  //| 17 -> 0.1.0
                                                  //| 7 -> 0.0.2.0
                                                  //| 3 -> 0.0.1.0.0.0.0
                                                  //| 8 -> 0.0.2
                                                  //| 4 -> 0.0.1.0.0.0.1
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
  s1.positionToAMR                                //> res0: Map[Int,String] = Map(10 -> 0.1, 1 -> 0.0.0.0.0, 9 -> 0, 13 -> 0.0.1.0
                                                  //| , 2 -> 0.0, 17 -> 0.1.0, 7 -> 0.0.2.0, 3 -> 0.0.1.0.0.0.0, 8 -> 0.0.2, 4 -> 
                                                  //| 0.0.1.0.0.0.1)
  s1.amr.get.nodes                                //> res1: Map[String,String] = Map(0.1 -> caution-01, 0.0.1.0.0.0 -> name, 0.0.0
                                                  //|  -> organization, 0.1.0 -> breakthrough-01, 0.0.1.0 -> propose-01, 0.0.1.0.0
                                                  //| .0.1 -> "Korea", 0.0 -> accept-01, 0.0.1.0.0.0.0 -> "North", 0.0.2 -> month,
                                                  //|  0.0.0.0 -> name, 0.0.1 -> thing, 0.1.0.0 -> -, 0.0.1.0.0 -> country, 0 -> c
                                                  //| ontrast-01, 0.0.0.0.0 -> "IAEA", 0.0.2.0 -> last)
  s1.amr.get.arcs                                 //> res2: Map[(String, String),String] = Map((0.0.1.0.0.0,0.0.1.0.0.0.1) -> opN,
                                                  //|  (0.0,0.0.0) -> ARG0, (0.0.1.0.0,0.0.1.0.0.0) -> name, (0.0.0.0,0.0.0.0.0) -
                                                  //| > opN, (0.0.0,0.0.0.0) -> name, (0.1,0.1.0) -> ARG1, (0.1.0,0.1.0.0) -> pola
                                                  //| rity, (0.0.2,0.0.2.0) -> mod, (0,0.1) -> ARG2, (0.0,0.0.2) -> time, (0.1.0,0
                                                  //| .0.1) -> domain, (0.1,0.0.0) -> ARG0, (0,0.0) -> ARG1, (0.0.1,0.0.1.0) -> AR
                                                  //| G1-of, (0.0.1.0,0.0.1.0.0) -> ARG0, (0.0,0.0.1) -> ARG1, (0.0.1.0.0.0,0.0.1.
                                                  //| 0.0.0.0) -> opN)
  import java.io._
  val file = new FileWriter("C://AMR//i4_output.txt")
                                                  //> file  : java.io.FileWriter = java.io.FileWriter@222a59e6
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
                                                  //> outputAdv  : amr.Sentence = Sentence(IAEA accepted North Korea's proposal l
                                                  //| ast month but cautioned that the proposal is not a breakthrough.,
                                                  //| NodeMap:	Map(1 -> IAEA, 2 -> accept-01, 3 -> North, 4 -> Korea, 7 -> last
                                                  //| , 8 -> month, 9 -> contrast-01, 10 -> caution-01, 13 -> propose-01, 17 -> b
                                                  //| reakthrough-01, 18 -> thing, 19 -> name, 20 -> country, 21 -> -, 22 -> name
                                                  //| , 23 -> organization)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 7 -> (7,8), 
                                                  //| 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 13 -> (13,14), 17 -> (17,18), 18 ->
                                                  //|  (13,14), 19 -> (3,4), 20 -> (3,6), 21 -> (17,18), 22 -> (1,2), 23 -> (1,2)
                                                  //| )
                                                  //| Edges:	Map((2,8) -> time, (2,18) -> ARG1, (2,23) -> ARG0, (8,7) -> mod,
                                                  //|  (9,2) -> ARG1, (9,10) -> ARG2, (10,17) -> ARG1, (13,20) -> ARG0, (17,18) -
                                                  //| > domain, (17,21) -> polarity, (18,13) -> ARG1-of, (19,3) -> opN, (19,4) ->
                                                  //|  opN, (20,19) -> name, (22,1) -> opN, (23,22) -> name)
                                                  //| InsertedNodes:	Map(20 -> 0.0.1.0.0, 21 -> 0.1.0.0, 22 -> 0.0.0.0, 18 ->
                                                  //|  0.0.1, 23 -> 0.0.0, 19 -> 0.0.1.0.0.0)
                                                  //| MergedNodes:	Map(20 -> List((6,proposal)))
                                                  //| SwappedArcs:	Set((4,20), (2,9))
                                                  //| DeletedNodes:	Map(13 -> List((12,the)), 17 -> List((16,a), (11,that), 
                                                  //| (15,not), (14,is)), 4 -> List((5,'s))),Some(AMRGraph(Map(8 -> month, 19 -> 
                                                  //| name, 23 -> organization, 4 -> "Korea", 9 -> contrast-01, 22 -> name, 13 ->
                                                  //|  propose-01, 10 -> caution-01, 21 -> -, 1 -> "IAEA", 17 -> breakthrough-01,
                                                  //|  20 -> country, 2 -> accept-01, 18 -> thing, 7 -> last, 3 -> "North"),Map(8
                                                  //|  -> (8,9), 19 -> (3,4), 23 -> (1,2), 4 -> (4,5), 9 -> (9,10), 22 -> (1,2), 
                                                  //| 13 -> (13,14), 10 -> (10,11), 21 -> (17,18), 1 -> (1,2), 17 -> (17,18), 20 
                                                  //| -> (3,6), 2 -> (2,3), 18 -> (13,14), 7 -> (7,8), 3 -> (3,4)),Map((2,8) -> t
                                                  //| ime, (19,3) -> opN, (9,10) -> ARG2, (23,22) -> name, (2,23) -> ARG0, (10,17
                                                  //| ) -> ARG1, (19,4) -> opN, (13,20) -> ARG0, (9,2) -> ARG1, (17,21) -> polari
                                                  //| ty, (2,18) -> ARG1, (20,19) -> name, (22,1) -> opN, (18,13) -> ARG1-of, (17
                                                  //| ,18) -> domain, (8,7) -> mod),Map(),List())),Map(10 -> 10, 20 -> 20, 1 -> 1
                                                  //| , 21 -> 21, 9 -> 9, 13 -> 13, 2 -> 2, 17 -> 17, 22 -> 22, 7 -> 7, 3 -> 3, 1
                                                  //| 8 -> 18, 23 -> 23, 8 -> 8, 19 -> 19, 4 -> 4))
     
}