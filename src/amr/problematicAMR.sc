package amr

object problematicAMR {

val testData = AMRGraph.importFile("C:\\AMR\\lobbying.txt")
                                                  //> testData  : IndexedSeq[(String, String)] = Vector((The statement was made to 
                                                  //| gain supporters to join the lobbying.,(s / state-01 :purpose (g / gain-02 :AR
                                                  //| G1 (p / person :ARG0-of (s2 / support-01)) :purpose (j / join-02 :ARG0 p :ARG
                                                  //| 1 (l / lobby-01))))))
  val expert = new WangXueExpert                  //> expert  : amr.WangXueExpert = amr.WangXueExpert@482f8f11
  val expertSystem = WangXueTransitionSystem      //> expertSystem  : amr.WangXueTransitionSystem.type = amr.WangXueTransitionSyst
                                                  //| em$@d8355a8
  AMRGraph.setAligner("improved")
  expertSystem.reentrance = true
  ImportConcepts.initialise("C:\\AMR\\lobbying.txt")
// ImportConcepts.relationStrin
  val s1 = Sentence(testData(0)._1, testData(0)._2)
                                                  //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.2 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [4.6 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [2.2 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [3.8 sec].
                                                  //| s1  : amr.Sentence = Sentence(The statement was made to gain supporters to j
                                                  //| oin the lobbying.,
                                                  //| NodeMap:	Map(1 -> The, 2 -> statement, 3 -> was, 4 -> made, 5 -> to, 6 ->
                                                  //|  gain, 7 -> supporters, 8 -> to, 9 -> join, 10 -> the, 11 -> lobbying)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,12
                                                  //| ))
                                                  //| Edges:	Map((2,1) -> det, (4,2) -> nsubjpass, (4,3) -> auxpass, (4,6) ->
                                                  //|  xcomp, (6,5) -> aux, (6,9) -> xcomp, (9,7) -> nsubj, (9,8) -> aux, (9,11) -
                                                  //| > dobj, (11,10) -> det)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map()
                                                  //| PartsOfSpeech:	Map(5 -> TO, 10 -> DT, 1 -> DT, 6 -> VB, 9 -> VB, 2 -> N
                                                  //| N, 7 -> NNS, 3 -> VBD, 11 -> NN, 8 -> TO, 4 -> VBN)
                                                  //| DependencyLabels:	Map(5 -> aux, 10 -> det, 1 -> det, 6 -> xcomp, 9 -> xcom
                                                  //| p, 2 -> nsubjpass, 7 -> nsubj, 3 -> auxpass, 11 -> dobj, 8 -> aux),Some(AMRG
                                                  //| raph(Map(0.0.0 -> person, 0.0.1.0 -> lobby-01, 0.0 -> gain-02, 0.0.0.0 -> su
                                                  //| pport-01, 0.0.1 -> join-02, 0 -> state-01),Map(0.0.1.0 -> (11,12), 0.0 -> (6
                                                  //| ,7), 0.0.0.0 -> (7,8), 0.0.1 -> (9,10), 0 -> (2,3)),Map((0.0,0.0.0) -> ARG1,
                                                  //|  (0.0.0,0.0.0.0) -> ARG0-of, (0.0.1,0.0.0) -> ARG0, (0,0.0) -> purpose, (0.0
                                                  //| .1,0.0.1.0) -> ARG1, (0.0,0.0.1) -> purpose),Map((0.0,0.0.0) -> ARG1, (0.0.0
                                                  //| ,0.0.0.0) -> ARG0-of, (0.0.1,0.0.0) -> ARG0, (0,0.0) -> purpose, (0.0.1,0.0.
                                                  //| 1.0) -> ARG1, (0.0,0.0.1) -> purpose),List())),Map(6 -> 0.0, 9 -> 0.0.1, 2 -
                                                  //| > 0, 7 -> 0.0.0.0, 11 -> 0.0.1.0))
  var state = expertSystem.init(s1)               //> state  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(10, 8, 7, 11, 9, 5, 1, 6, 2, 3, 4)
                                                  //| Children:	List()
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(1 -> The, 2 -> statement, 3 -> was, 4 -> made, 5 -> to, 6 ->
                                                  //|  gain, 7 -> supporters, 8 -> to, 9 -> join, 10 -> the, 11 -> lobbying)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,12
                                                  //| ))
                                                  //| Edges:	Map((2,1) -> det, (4,2) -> nsubjpass, (4,3) -> auxpass, (4,6) ->
                                                  //|  xcomp, (6,5) -> aux, (6,9) -> xcomp, (9,7) -> nsubj, (9,8) -> aux, (9,11) -
                                                  //| > dobj, (11,10) -> det)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map()
                                                  //| PartsOfSpeech:	Map(5 -> TO, 10 -> DT, 1 -> DT, 6 -> VB, 9 -> VB, 2 -> N
                                                  //| N, 7 -> NNS, 3 -> VBD, 11 -> NN, 8 -> TO, 4 -> VBN)
                                                  //| DependencyLabels:	Map(5 -> aux, 10 -> det, 1 -> det, 6 -> xcomp, 9 -> xcom
                                                  //| p, 2 -> nsubjpass, 7 -> nsubj, 3 -> auxpass, 11 -> dobj, 8 -> aux)
                                                  //| Mappings:	6 -> 0.0
                                                  //| 9 -> 0.0.1
                                                  //| 2 -> 0
                                                  //| 7 -> 0.0.0.0
                                                  //| 11 -> 0.0.1.0
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
  s1.positionToAMR                                //> res0: Map[Int,String] = Map(6 -> 0.0, 9 -> 0.0.1, 2 -> 0, 7 -> 0.0.0.0, 11 -
                                                  //| > 0.0.1.0)
  s1.amr.get.nodes                                //> res1: Map[String,String] = Map(0.0.0 -> person, 0.0.1.0 -> lobby-01, 0.0 -> 
                                                  //| gain-02, 0.0.0.0 -> support-01, 0.0.1 -> join-02, 0 -> state-01)
  s1.amr.get.arcs                                 //> res2: Map[(String, String),String] = Map((0.0,0.0.0) -> ARG1, (0.0.0,0.0.0.0
                                                  //| ) -> ARG0-of, (0.0.1,0.0.0) -> ARG0, (0,0.0) -> purpose, (0.0.1,0.0.1.0) -> 
                                                  //| ARG1, (0.0,0.0.1) -> purpose)
  import java.io._
  val file = new FileWriter("C://AMR//militaryNetwork_exp.txt")
                                                  //> file  : java.io.FileWriter = java.io.FileWriter@4310d43
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
    }
  }
  file.close

}