package amr.classic

object ExpertTestOnAMR {

import amr.{AMRGraph, ImportConcepts, Sentence, RunDagger, AMROutput}

val testData = AMRGraph.importFile("C:\\AMR\\Initial4.txt")
                                                  //> testData  : IndexedSeq[(String, String)] = Vector((NATO CONSIDERS cyber atta
                                                  //| cks a threat to military and civilian computer networks after the Estonian G
                                                  //| overnment was struck by cyber attacks in 2007.,(c / consider-02 :ARG0 (m2 / 
                                                  //| military :name (n / name :op1 "NATO")) :ARG1 (a / attack-01 :mod (c2 / cyber
                                                  //| )) :ARG2 (t / thing :ARG1-of (t2 / threaten-01 :ARG2 (a5 / and :op1 (n2 / ne
                                                  //| twork :mod (m / military)) :op2 (n4 / network :mod (c3 / civilian)) :mod (c4
                                                  //|  / computer)))) :time (a3 / after :op1 (s / strike-01 :ARG0 (a4 / attack :mo
                                                  //| d (c6 / cyber)) :ARG1 (g / government-organization :ARG0-of (g2 / govern-01 
                                                  //| :ARG1 (c5 / country :name (n3 / name :op1 "Estonia")))) :time (d / date-enti
                                                  //| ty :year 2007))))))
  val expert = new ClassicExpert                  //> expert  : amr.classic.ClassicExpert = amr.classic.ClassicExpert@482f8f11
  val expertSystem = ClassicTransitionSystem      //> expertSystem  : amr.classic.ClassicTransitionSystem.type = amr.classic.Class
                                                  //| icTransitionSystem$@d8355a8
  AMRGraph.setAligner("JAMR")
  ImportConcepts.initialise("C:\\AMR\\Initial4.txt")
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
                                                  //| im.crf.ser.gz ... done [4.6 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [2.2 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [4.0 sec].
                                                  //| WARNING: Found duplicate match for concept attack-01
                                                  //| WARNING: Found duplicate match for concept cyber
                                                  //| s1  : amr.Sentence = Sentence(NATO CONSIDERS cyber attacks a threat to milit
                                                  //| ary and civilian computer networks after the Estonian Government was struck 
                                                  //| by cyber attacks in 2007.,
                                                  //| NodeMap:	Map(1 -> NATO, 2 -> CONSIDERS, 3 -> cyber, 4 -> attacks, 5 -> a,
                                                  //|  6 -> threat, 7 -> to, 8 -> military, 9 -> and, 10 -> civilian, 11 -> comput
                                                  //| er, 12 -> networks, 13 -> after, 14 -> the, 15 -> Estonian, 16 -> Government
                                                  //| , 17 -> was, 18 -> struck, 19 -> by, 20 -> cyber, 21 -> attacks, 22 -> in, 2
                                                  //| 3 -> 2007)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,12
                                                  //| ), 12 -> (12,13), 13 -> (13,14), 14 -> (14,15), 15 -> (15,16), 16 -> (16,17)
                                                  //| , 17 -> (17,18), 18 -> (18,19), 19 -> (19,20), 20 -> (20,21), 21 -> (21,22),
                                                  //|  22 -> (22,23), 23 -> (23,24))
                                                  //| Edges:	Map((3,1) -> nn, (3,2) -> nn, (4,3) -> nsubj, (4,6) -> dobj, (4,
                                                  //| 7) -> prep, (4,18) -> advcl, (6,5) -> det, (7,12) -> pobj, (8,9) -> cc, (8,1
                                                  //| 0) -> conj, (12,8) -> amod, (12,11) -> nn, (16,14) -> det, (16,15) -> amod, 
                                                  //| (18,13) -> mark, (18,16) -> nsubjpass, (18,17) -> auxpass, (18,19) -> prep, 
                                                  //| (19,21) -> pobj, (21,20) -> nn, (21,22) -> prep, (22,23) -> pobj)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map()
                                                  //| PartsOfSpeech:	Map(5 -> DT, 10 -> JJ, 14 -> DT, 20 -> NN, 1 -> NNP, 6 -
                                                  //| > NN, 21 -> NNS, 9 -> CC, 13 -> IN, 2 -> NNP, 17 -> VBD, 22 -> IN, 12 -> NNS
                                                  //| , 7 -> TO, 3 -> NN, 18 -> VBN, 16 -> NN, 11 -> NN, 23 -> CD, 8 -> JJ, 19 -> 
                                                  //| IN, 4 -> VBZ, 15 -> JJ)
                                                  //| DependencyLabels:	Map(5 -> det, 10 -> conj, 14 -> det, 20 -> nn, 1 -> nn, 
                                                  //| 6 -> dobj, 21 -> pobj, 9 -> cc, 13 -> mark, 2 -
                                                  //| Output exceeds cutoff limit.
  var state = expertSystem.init(s1)               //> state  : amr.classic.ClassicTransitionState = NodesToProcess: 23, 22, 21, 20
                                                  //| , 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1
                                                  //| Current Graph: 
                                                  //| NodeMap:	Map(1 -> NATO, 2 -> CONSIDERS, 3 -> cyber, 4 -> attacks, 5 -> a,
                                                  //|  6 -> threat, 7 -> to, 8 -> military, 9 -> and, 10 -> civilian, 11 -> comput
                                                  //| er, 12 -> networks, 13 -> after, 14 -> the, 15 -> Estonian, 16 -> Government
                                                  //| , 17 -> was, 18 -> struck, 19 -> by, 20 -> cyber, 21 -> attacks, 22 -> in, 2
                                                  //| 3 -> 2007)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,12
                                                  //| ), 12 -> (12,13), 13 -> (13,14), 14 -> (14,15), 15 -> (15,16), 16 -> (16,17)
                                                  //| , 17 -> (17,18), 18 -> (18,19), 19 -> (19,20), 20 -> (20,21), 21 -> (21,22),
                                                  //|  22 -> (22,23), 23 -> (23,24))
                                                  //| Edges:	Map()
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map()
                                                  //| PartsOfSpeech:	Map(5 -> DT, 10 -> JJ, 14 -> DT, 20 -> NN, 1 -> NNP, 6 -
                                                  //| > NN, 21 -> NNS, 9 -> CC, 13 -> IN, 2 -> NNP, 17 -> VBD, 22 -> IN, 12 -> NNS
                                                  //| , 7 -> TO, 3 -> NN, 18 -> VBN, 16 -> NN, 11 -> NN, 23 -> CD, 8 -> JJ, 19 -> 
                                                  //| IN, 4 -> VBZ, 15 -> JJ)
                                                  //| DependencyLabels:	Map()
                                                  //| Fragments: Map()
                                                  //| FragmentHeads: Map()
                                                  //| Additional AMR Map: Map()
                                                  //| NodePair: (0,0)
                                                  //| 
  val action = new Array[ClassicAction](1001)     //> action  : Array[amr.classic.ClassicAction] = Array(null, null, null, null, n
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
                                                  //| l, null, null, null, null, null, null, null, null, null, null, null, null, n
                                                  //| ull, null, null, null, null, null, null, null, null, null, null, null, null,
                                                  //|  null, null, null, null, null, null, null, null, null, null, null, null, nul
                                                  //| l, null, null, null, null, null, null, null, null, null, null, null, null, n
                                                  //| ull, null, null, null, null, null, null, null, null, null, null, null, null,
                                                  //|  null, null, null, null, null, null, null, null, null, null, null, null, nul
                                                  //| l, null, null, null, null, null, null, null, null, null, null, nul
                                                  //| Output exceeds cutoff limit.
  val stateHistory = new Array[ClassicTransitionState](1001)
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
                                                  //| null, null, null, null, null, null, null, null, null, null, null, null, null
                                                  //| , null, null, null, null, null, null, null, null, null, null, null, null, nu
                                                  //| ll, null, null, null, null, null, null, null, null, null, null, null, null, 
                                                  //| null, null, null, null, null, null, null, null, null, null, null, null, null
                                                  //| , null, null, null, null, null, null, null, null, null, null, null, null, nu
                                                  //| ll, null, null, null, null, null, null, null, null, null, null, null, null, 
                                                  //| null, null, null, null, null, null, null, null, null, null, null, 
                                                  //| Output exceeds cutoff limit.
  s1.positionToAMR                                //> res0: Map[Int,String] = Map(10 -> 0.2.0.0.1.0, 20 -> 0.3.0.0.0, 1 -> 0.0.0.0
                                                  //| , 6 -> 0.2.0, 21 -> 0.3.0.0, 9 -> 0.2.0.0, 13 -> 0.3, 2 -> 0, 12 -> 0.2.0.0.
                                                  //| 0, 3 -> 0.1.0, 18 -> 0.3.0, 16 -> 0.3.0.1, 11 -> 0.2.0.0.2, 23 -> 0.3.0.2.0,
                                                  //|  8 -> 0.0, 4 -> 0.1, 15 -> 0.3.0.1.0.0.0.0)
  s1.amr.get.nodes                                //> res1: Map[String,String] = Map(0.1 -> attack-01, 0.0.0 -> name, 0.3.0.1.0.0 
                                                  //| -> country, 0.2.0.0.0 -> network, 0.1.0 -> cyber, 0.2.0.0.1.0 -> civilian, 0
                                                  //| .3.0.2.0 -> 2007, 0.0 -> military, 0.2.0.0.1 -> network, 0.3.0.1.0 -> govern
                                                  //| -01, 0.0.0.0 -> "NATO", 0.2.0 -> threaten-01, 0.3.0.0 -> attack, 0.3.0 -> st
                                                  //| rike-01, 0.3 -> after, 0.2.0.0.0.0 -> military, 0.2.0.0.2 -> computer, 0 -> 
                                                  //| consider-02, 0.3.0.1 -> government-organization, 0.2 -> thing, 0.3.0.2 -> da
                                                  //| te-entity, 0.3.0.1.0.0.0.0 -> "Estonia", 0.2.0.0 -> and, 0.3.0.1.0.0.0 -> na
                                                  //| me, 0.3.0.0.0 -> cyber)
  s1.amr.get.arcs                                 //> res2: Map[(String, String),String] = Map((0.0,0.0.0) -> name, (0.3.0,0.3.0.0
                                                  //| ) -> ARG0, (0,0.3) -> time, (0.0.0,0.0.0.0) -> opN, (0.2.0.0.1,0.2.0.0.1.0) 
                                                  //| -> mod, (0.1,0.1.0) -> mod, (0.3.0,0.3.0.1) -> ARG1, (0.3.0,0.3.0.2) -> time
                                                  //| , (0.3.0.0,0.3.0.0.0) -> mod, (0,0.2) -> ARG2, (0,0.1) -> ARG1, (0.2.0.0.0,0
                                                  //| .2.0.0.0.0) -> mod, (0.2,0.2.0) -> ARG1-of, (0.3,0.3.0) -> opN, (0.3.0.1.0,0
                                                  //| .3.0.1.0.0) -> ARG1, (0.2.0.0,0.2.0.0.1) -> opN, (0.2.0.0,0.2.0.0.0) -> opN,
                                                  //|  (0.3.0.1.0.0.0,0.3.0.1.0.0.0.0) -> opN, (0.3.0.1,0.3.0.1.0) -> ARG0-of, (0,
                                                  //| 0.0) -> ARG0, (0.3.0.1.0.0,0.3.0.1.0.0.0) -> name, (0.2.0,0.2.0.0) -> ARG2, 
                                                  //| (0.3.0.2,0.3.0.2.0) -> year, (0.2.0.0,0.2.0.0.2) -> mod)
  import java.io._
  val file = new FileWriter("C://AMR//Initial4_exp.txt")
                                                  //> file  : java.io.FileWriter = java.io.FileWriter@700fb871
  for (i <- 0 to 1000) {
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
    }                                             //> WARNING: Found duplicate match for concept attack-01
                                                  //| WARNING: Found duplicate match for concept cyber
  }
  file.close
  val outputAdv = RunDagger.sampleTrajectory(s1, "C:\\AMR\\ClassicExpert_output.txt", expert, expertSystem = expertSystem)
                                                  //> outputAdv  : amr.Sentence = Sentence(NATO CONSIDERS cyber attacks a threat 
                                                  //| to military and civilian computer networks after the Estonian Government wa
                                                  //| s struck by cyber attacks in 2007.,
                                                  //| NodeMap:	Map(1 -> NATO, 2 -> consider-02, 3 -> cyber, 4 -> attack-01, 6 -
                                                  //| > threaten-01, 8 -> military, 9 -> and, 10 -> civilian, 11 -> computer, 12 
                                                  //| -> network, 13 -> after, 15 -> Estonia, 16 -> government-organization, 18 -
                                                  //| > strike-01, 20 -> cyber, 21 -> attack, 23 -> 2007, 24 -> date-entity, 25 -
                                                  //| > name, 26 -> country, 27 -> govern-01, 28 -> network, 29 -> thing, 30 -> n
                                                  //| ame)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 6 -> (6,7), 
                                                  //| 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,12), 12 -> (12,13), 13 ->
                                                  //|  (13,14), 15 -> (15,16), 16 -> (16,17), 18 -> (18,19), 20 -> (20,21), 21 ->
                                                  //|  (21,22), 23 -> (23,24), 24 -> (23,24), 25 -> (15,16), 26 -> (15,16), 27 ->
                                                  //|  (15,16), 28 -> (10,11), 29 -> (6,7), 30 -> (1,2))
                                                  //| Edges:	Map((2,4) -> ARG1, (2,8) -> ARG0, (2,13) -> time, (2,29) -> ARG2
                                                  //| , (4,3) -> mod, (6,9) -> ARG2, (8,30) -> name, (9,11) -> mod, (9,12) -> opN
                                                  //| , (9,28) -> opN, (13,18) -> opN, (16,27) -> ARG0-of, (18,16) -> ARG1, (18,2
                                                  //| 1) -> ARG0, (18,24) -> time, (21,20) -> mod, (24,23) -> year, (25,15) -> op
                                                  //| N, (26,25) -> name, (27,26) -> ARG1, (28,10) -> mod, (29,6) -> ARG1-of, (30
                                                  //| ,1) -> opN)
                                                  //| InsertedNodes:	Map(24 -> 0.3.0.2, 25 -> 0.3.0.1.0.0.0, 29 -> 0.2, 28 ->
                                                  //|  0.2.0.0.1, 27 -> 0.3.0.1.0, 26 -> 0.3.0.1.0.0, 30 -> 0.0.0)
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map(-1 -> List((5,a), (7,to), (14,the), (17,was), (19,by
                                                  //| ), (22,in)))
                                                  //| PartsOfSpeech:	Map(5 -> DT, 10 -> JJ, 24 -> CD, 25 -> JJ, 14 -> DT, 20 
                                                  //| -> NN, 29 -> NN, 1 -> NNP, 6 -> NN, 28 -> JJ, 21 -> NNS, 9 -> CC, 13 -> IN,
                                                  //|  2 -> NNP, 17 -> VBD, 22 -> IN, 27 -> JJ, 12 -> NNS, 7 -> TO, 3 -> NN, 18 -
                                                  //| > VBN, 16 -> NN, 11 -> NN, 26 -> JJ, 23 -> CD, 8 -> JJ, 30 -> NNP, 19 -> IN
                                                  //| , 4 -> VBZ, 15 -> JJ)
                                                  //| DependencyLabels:	Map(24 -> DUMMY, 25 -> DUMMY, 29 -> DUMMY, 28 -> DUMMY, 
                                                  //| 27 -> DUMMY, 26 -> DUMMY, 30 -> DUMMY),Some(AMRGraph(Map(12 -> network, 8 -
                                                  //| > military, 23 -> 2007, 4 -> attack-01, 15 -> "Estonia", 11 -> computer, 9 
                                                  //| -> and, 26 -> country, 13 -> after, 24 -> date-entity, 16 -> government-org
                                                  //| anization, 10 -> civilian, 21 -> attack, 6 -> threaten-01, 1 -> "NATO", 25 
                                                  //| -> name, 20 -> cyber, 27 -> govern-01, 2 -> con
                                                  //| Output exceeds cutoff limit.
     
}