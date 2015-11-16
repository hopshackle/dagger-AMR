package amr.classic

object ExpertTestOnAMR {

import amr.{AMRGraph, ImportConcepts, Sentence, RunDagger, AMROutput}

val testData = AMRGraph.importFile("C:\\AMR\\CountryList.txt")
                                                  //> testData  : IndexedSeq[(String, String)] = Vector((Estonia (EE); Germany (DE
                                                  //| ): Italy (IT); Spain (ES); United States (US),(a / and :op1 (c / country :na
                                                  //| me (n / name :op1 "Estonia")) :op2 (c4 / country :name (n7 / name :op1 "Germ
                                                  //| any")) :op3 (c5 / country :name (n9 / name :op1 "Italy")) :op4 (c6 / country
                                                  //|  :name (n10 / name :op1 "Spain")) :op5 (c8 / country :name (n14 / name :op1 
                                                  //| "United" :op2 "States")))))
  val expert = new ClassicExpert                  //> expert  : amr.classic.ClassicExpert = amr.classic.ClassicExpert@482f8f11
  val expertSystem = ClassicTransitionSystem      //> expertSystem  : amr.classic.ClassicTransitionSystem.type = amr.classic.Class
                                                  //| icTransitionSystem$@d8355a8
  AMRGraph.setAligner("JAMR")
  ImportConcepts.initialise("C:\\AMR\\CountryList.txt")
// ImportConcepts.relationStrin
  val s1 = Sentence(testData(0)._1, testData(0)._2)
                                                  //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.1 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [4.4 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [2.2 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [4.3 sec].
                                                  //| s1  : amr.Sentence = Sentence(Estonia (EE); Germany (DE): Italy (IT); Spain 
                                                  //| (ES); United States (US),
                                                  //| NodeMap:	Map(1 -> Estonia, 3 -> EE, 6 -> Germany, 8 -> DE, 11 -> Italy, 1
                                                  //| 3 -> IT, 16 -> Spain, 18 -> ES, 21 -> United, 22 -> States, 24 -> US)
                                                  //| SpanMap:	Map(1 -> (1,2), 3 -> (2,3), 6 -> (3,4), 8 -> (4,5), 11 -> (5,6),
                                                  //|  13 -> (6,7), 16 -> (7,8), 18 -> (8,9), 21 -> (9,10), 22 -> (10,11), 24 -> (
                                                  //| 11,12))
                                                  //| Edges:	Map((3,1) -> nn, (3,6) -> dep, (6,8) -> appos, (6,11) -> dep, (6
                                                  //| ,16) -> dep, (6,22) -> dep, (11,13) -> dep, (16,18) -> appos, (22,21) -> nn,
                                                  //|  (22,24) -> appos)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map()
                                                  //| PartsOfSpeech:	Map(24 -> NNP, 1 -> NNP, 6 -> NNP, 21 -> NNP, 13 -> PRP,
                                                  //|  22 -> NNPS, 3 -> NN, 18 -> NNP, 16 -> NNP, 11 -> NNP, 8 -> NNP)
                                                  //| DependencyLabels:	Map(24 -> appos, 1 -> nn, 6 -> dep, 21 -> nn, 13 -> dep,
                                                  //|  22 -> dep, 18 -> appos, 16 -> dep, 11 -> dep, 8 -> appos),Some(AMRGraph(Map
                                                  //| (0.1 -> country, 0.0.0 -> name, 0.1.0 -> name, 0.0 -> country, 0.4 -> countr
                                                  //| y, 0.0.0.0 -> "Estonia", 0.2.0 -> name, 0.3.0.0 -> "Spain", 0.3.0 -> name, 0
                                                  //| .1.0.0 -> "Germany", 0.4.0.0 -> "United", 0.3 -> country, 0.4.0 -> name, 0 -
                                                  //| > and, 0.2 -> country, 0.4.0.1 -> "States", 0.2.0.0 -> "Italy"),Map(0.1 -> (
                                                  //| 3,4), 0.0.0 -> (1,2), 0.1.0 -> (3,4), 0.0 -> (1,2), 0.4 -> (9,11), 0.0.0.0 -
                                                  //| > (1,2), 0.2.0 -> (5,6), 0.3.0.0 -> (7,8), 0.3.0 -> (7,8), 0.1.0.0 -> (3,4),
                                                  //|  0.4.0.0 -> (9,11), 0.3 -> (7,8), 0.4.0 -> (9,11), 0.2 -> (5,6), 0.4.0.1 -> 
                                                  //| (9,11), 0.2.0.0 -> (5,6)),Map((0.0,0.0.0) -> name, (0.3.0,0.3.0.0) -> opN, (
                                                  //| 0.4.0,0.4.0.1) -> opN, (0,0.3) -> opN, (0.4.0,0.4.0.0) -> opN, (0.0.0,0.0.0.
                                                  //| 0) -> opN, (0.1,0.1.0) -> name, (0.1.0,0.1.0.0) -> opN, (0,0.2) -> opN, (0,0
                                                  //| .1) -> opN, (0.2,0.2.0) -> name, (0.3,0.3.0) -> n
                                                  //| Output exceeds cutoff limit.
  var state = expertSystem.init(s1)               //> state  : amr.classic.ClassicTransitionState = NodesToProcess: 24, 22, 21, 18
                                                  //| , 16, 13, 11, 8, 6, 3, 1
                                                  //| Current Graph: 
                                                  //| NodeMap:	Map(1 -> Estonia, 3 -> EE, 6 -> Germany, 8 -> DE, 11 -> Italy, 1
                                                  //| 3 -> IT, 16 -> Spain, 18 -> ES, 21 -> United, 22 -> States, 24 -> US)
                                                  //| SpanMap:	Map(1 -> (1,2), 3 -> (2,3), 6 -> (3,4), 8 -> (4,5), 11 -> (5,6),
                                                  //|  13 -> (6,7), 16 -> (7,8), 18 -> (8,9), 21 -> (9,10), 22 -> (10,11), 24 -> (
                                                  //| 11,12))
                                                  //| Edges:	Map()
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map()
                                                  //| PartsOfSpeech:	Map(24 -> NNP, 1 -> NNP, 6 -> NNP, 21 -> NNP, 13 -> PRP,
                                                  //|  22 -> NNPS, 3 -> NN, 18 -> NNP, 16 -> NNP, 11 -> NNP, 8 -> NNP)
                                                  //| DependencyLabels:	Map()
                                                  //| Fragments: Map()
                                                  //| FragmentHeads: Map()
                                                  //| Additional AMR Map: Map()
                                                  //| NodePair: (0,0)
                                                  //| 
  val action = new Array[ClassicAction](301)      //> action  : Array[amr.classic.ClassicAction] = Array(null, null, null, null, n
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
                                                  //|  null, null, null, null, null, null, null, null, null, null, null, null, nul
                                                  //| l, null, null, null, null, null)
  val stateHistory = new Array[ClassicTransitionState](301)
                                                  //> stateHistory  : Array[amr.classic.ClassicTransitionState] = Array(null, null
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
                                                  //| ll, null, null, null, null, null, null, null, null, null, null, null, null, 
                                                  //| null, null, null, null, null, null, null, null)
  s1.positionToAMR                                //> res0: Map[Int,String] = Map(1 -> 0.0.0.0, 6 -> 0.1.0.0, 21 -> 0.4.0.0, 22 ->
                                                  //|  0.4.0.1, 16 -> 0.3.0.0, 11 -> 0.2.0.0)
  s1.amr.get.nodes                                //> res1: Map[String,String] = Map(0.1 -> country, 0.0.0 -> name, 0.1.0 -> name,
                                                  //|  0.0 -> country, 0.4 -> country, 0.0.0.0 -> "Estonia", 0.2.0 -> name, 0.3.0.
                                                  //| 0 -> "Spain", 0.3.0 -> name, 0.1.0.0 -> "Germany", 0.4.0.0 -> "United", 0.3 
                                                  //| -> country, 0.4.0 -> name, 0 -> and, 0.2 -> country, 0.4.0.1 -> "States", 0.
                                                  //| 2.0.0 -> "Italy")
  s1.amr.get.arcs                                 //> res2: Map[(String, String),String] = Map((0.0,0.0.0) -> name, (0.3.0,0.3.0.0
                                                  //| ) -> opN, (0.4.0,0.4.0.1) -> opN, (0,0.3) -> opN, (0.4.0,0.4.0.0) -> opN, (0
                                                  //| .0.0,0.0.0.0) -> opN, (0.1,0.1.0) -> name, (0.1.0,0.1.0.0) -> opN, (0,0.2) -
                                                  //| > opN, (0,0.1) -> opN, (0.2,0.2.0) -> name, (0.3,0.3.0) -> name, (0.4,0.4.0)
                                                  //|  -> name, (0,0.4) -> opN, (0,0.0) -> opN, (0.2.0,0.2.0.0) -> opN)
  import java.io._
  val file = new FileWriter("C://AMR//CountryList_exp.txt")
                                                  //> file  : java.io.FileWriter = java.io.FileWriter@231f98ef
  for (i <- 0 to 300) {
    if (!expertSystem.isTerminal(state)) {
      stateHistory(i) = state
      action(i) = expert.chooseTransition(s1, state)
      file.write("\n" + state.toString + "\n")
      file.write("\n" + action(i) + " : " + (state.nodesToProcess match { case Nil => ""; case _ => state.nodesToProcess.head }) + "\n")

			file.write((s1.AMRToPosition map {case (amrKey, dtKey) => (dtKey, s1.amr.get.nodes(amrKey))}).toString)
      file.flush
      state = action(i)(state)
      if (expertSystem.isTerminal(state)){
      val finalAMR = expertSystem.construct(state, s1).amr.get
      file.write("\n\n" + finalAMR + "\n\n" + AMROutput.convertToString(finalAMR))
      }
    }
  }
  file.close
  val outputAdv = RunDagger.sampleTrajectory(s1, "C:\\AMR\\ClassicExpert_output.txt", expert, expertSystem = expertSystem)
                                                  //> outputAdv  : <error> = Sentence(Estonia (EE); Germany (DE): Italy (IT); Spa
                                                  //| in (ES); United States (US),
                                                  //| NodeMap:	Map(1 -> Estonia, 6 -> Germany, 11 -> Italy, 16 -> Spain, 21 -> 
                                                  //| United, 22 -> States, 25 -> name, 26 -> country, 27 -> and, 28 -> name, 29 
                                                  //| -> country, 30 -> name, 31 -> country, 32 -> name, 33 -> country, 34 -> nam
                                                  //| e, 35 -> country)
                                                  //| SpanMap:	Map(1 -> (1,2), 6 -> (3,4), 11 -> (5,6), 16 -> (7,8), 21 -> (9,1
                                                  //| 0), 22 -> (10,11), 25 -> (10,11), 26 -> (10,11), 27 -> (10,11), 28 -> (7,8)
                                                  //| , 29 -> (7,8), 30 -> (5,6), 31 -> (5,6), 32 -> (3,4), 33 -> (3,4), 34 -> (1
                                                  //| ,2), 35 -> (1,2))
                                                  //| Edges:	Map((25,21) -> opN, (25,22) -> opN, (26,25) -> name, (27,26) -> 
                                                  //| opN, (27,29) -> opN, (27,31) -> opN, (27,33) -> opN, (27,35) -> opN, (28,16
                                                  //| ) -> opN, (29,28) -> name, (30,11) -> opN, (31,30) -> name, (32,6) -> opN, 
                                                  //| (33,32) -> name, (34,1) -> opN, (35,34) -> name)
                                                  //| InsertedNodes:	Map(25 -> 0.4.0, 29 -> 0.3, 28 -> 0.3.0, 33 -> 0.1, 32 -
                                                  //| > 0.1.0, 34 -> 0.0.0, 27 -> 0, 35 -> 0.0, 31 -> 0.2, 26 -> 0.4, 30 -> 0.2.0
                                                  //| )
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map(-1 -> List((3,EE), (8,DE), (13,IT), (18,ES), (24,US)
                                                  //| ))
                                                  //| PartsOfSpeech:	Map(24 -> NNP, 25 -> NNPS, 29 -> NNP, 1 -> NNP, 6 -> NNP
                                                  //| , 28 -> NNP, 21 -> NNP, 33 -> NNP, 13 -> PRP, 32 -> NNP, 34 -> NNP, 22 -> N
                                                  //| NPS, 27 -> NNPS, 3 -> NN, 35 -> NNP, 18 -> NNP, 16 -> NNP, 31 -> NNP, 11 ->
                                                  //|  NNP, 26 -> NNPS, 8 -> NNP, 30 -> NNP)
                                                  //| DependencyLabels:	Map(25 -> DUMMY, 29 -> DUMMY, 28 -> DUMMY, 33 -> DUMMY, 
                                                  //| 32 -> DUMMY, 34 -> DUMMY, 27 -> DUMMY, 35 -> DUMMY, 31 -> DUMMY, 26 -> DUMM
                                                  //| Y, 30 -> DUMMY),Some(AMRGraph(Map(34 -> name, 11 -> "Italy", 33 -> country,
                                                  //|  22 -> "States", 26 -> country, 35 -> country, 16 -> "Spain", 21 -> "United
                                                  //| ", 32 -> name, 6 -> "Germany", 1 -> "Estonia", 25 -> name, 31 -> country, 2
                                                  //| 7 -> and, 30 -> name, 29 -> country, 28 -> name),Map(34 -> (1,2), 11 -> (5,
                                                  //| 6), 33 -> (3,4), 22 -> (10,11), 26 -> (10,11), 35 -> (1,2), 16 -> (7,8), 21
                                                  //|  -> (9,10), 32 -> (3,4), 6 -> (3,4), 1 -> (1,2), 25 -> (10,11), 31 -> (5,6)
                                                  //| , 27 -> (10,11), 30 -> (5,6), 29 -> (7,8), 28 -> (7,8)),Map((35,34) -> name
                                                  //| , (31,30) -> name, (34,1) -> opN, (32,6) -> opN, (28,16) -> opN, (27,31) ->
                                                  //|  opN, (25,22) -> opN, (27,26) -> opN, (30,11) -> opN, (27,35) -> opN, (33,3
                                                  //| 2) -> name, (26,25) -> name, (29,28) -> name, (25,21) -> opN, (27,33) -> op
                                                  //| N, (27,2
                                                  //| Output exceeds cutoff limit.
     
}