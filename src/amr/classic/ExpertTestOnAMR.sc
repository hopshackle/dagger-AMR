package amr.classic

object ExpertTestOnAMR {

import amr.{AMRGraph, ImportConcepts, Sentence, RunDagger, AMROutput}

val testData = AMRGraph.importFile("C:\\AMR\\CountryList.txt")
                                                  //> testData  : IndexedSeq[(String, String)] = Vector((Myanmar (MM); Thailand (T
                                                  //| H); Lao People's Democratic Republic (LA),(a / and :op1 (c3 / country :name 
                                                  //| (n7 / name :op1 "Myanmar")) :op2 (c4 / country :name (n8 / name :op1 "Thaila
                                                  //| nd")) :op3 (c5 / country :name (n9 / name :op1 "Lao" :op2 "People's" :op3 "D
                                                  //| emocratic" :op4 "Republic")))))
  val expert = new ClassicExpert                  //> expert  : amr.classic.ClassicExpert = amr.classic.ClassicExpert@68be2bc2
  val expertSystem = ClassicTransitionSystem      //> expertSystem  : amr.classic.ClassicTransitionSystem.type = amr.classic.Class
                                                  //| icTransitionSystem$@4c70fda8
  AMRGraph.setAligner("JAMR")
  ImportConcepts.initialise("C:\\AMR\\CountryList.txt")
// ImportConcepts.relationStrin
  val s1 = Sentence(testData(0)._1, testData(0)._2)
                                                  //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.4 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [3.9 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [3.4 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [2.5 sec].
                                                  //| s1  : amr.Sentence = Sentence(Myanmar (MM); Thailand (TH); Lao People's Demo
                                                  //| cratic Republic (LA),
                                                  //| NodeMap:	Map(1 -> Myanmar, 3 -> MM, 6 -> Thailand, 8 -> TH, 11 -> Lao, 12
                                                  //|  -> People, 13 -> 's, 14 -> Democratic, 15 -> Republic, 17 -> LA)
                                                  //| SpanMap:	Map(1 -> (1,2), 3 -> (2,3), 6 -> (3,4), 8 -> (4,5), 11 -> (5,6),
                                                  //|  12 -> (6,7), 13 -> (7,8), 14 -> (8,9), 15 -> (9,10), 17 -> (10,11))
                                                  //| Edges:	Map((3,1) -> nn, (3,6) -> dep, (6,8) -> appos, (6,15) -> dep, (1
                                                  //| 2,11) -> nn, (12,13) -> possessive, (15,12) -> poss, (15,14) -> amod, (15,17
                                                  //| ) -> appos)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map()
                                                  //| PartsOfSpeech:	Map(14 -> JJ, 1 -> NNP, 6 -> NNP, 13 -> POS, 17 -> NNP, 
                                                  //| 12 -> NNP, 3 -> NN, 11 -> NNP, 8 -> NNP, 15 -> NN)
                                                  //| DependencyLabels:	Map(14 -> amod, 1 -> nn, 6 -> dep, 13 -> possessive, 17 
                                                  //| -> appos, 12 -> poss, 11 -> nn, 8 -> appos, 15 -> dep),Some(AMRGraph(Map(0.1
                                                  //|  -> country, 0.0.0 -> name, 0.1.0 -> name, 0.0 -> country, 0.0.0.0 -> "Myanm
                                                  //| ar", 0.2.0.1 -> "People's", 0.2.0 -> name, 0.1.0.0 -> "Thailand", 0 -> and, 
                                                  //| 0.2.0.3 -> "Republic", 0.2 -> country, 0.2.0.2 -> "Democratic", 0.2.0.0 -> "
                                                  //| Lao"),Map(0.1 -> (3,4), 0.0.0 -> (1,2), 0.1.0 -> (3,4), 0.0 -> (1,2), 0.0.0.
                                                  //| 0 -> (1,2), 0.2.0.1 -> (5,10), 0.2.0 -> (5,10), 0.1.0.0 -> (3,4), 0.2.0.3 ->
                                                  //|  (5,10), 0.2 -> (5,10), 0.2.0.2 -> (5,10), 0.2.0.0 -> (5,10)),Map((0.0,0.0.0
                                                  //| ) -> name, (0.2.0,0.2.0.3) -> opN, (0.0.0,0.0.0.0) -> opN, (0.1,0.1.0) -> na
                                                  //| me, (0.2.0,0.2.0.2) -> opN, (0.1.0,0.1.0.0) -> opN, (0,0.2) -> opN, (0,0.1) 
                                                  //| -> opN, (0.2,0.2.0) -> name, (0,0.0) -> opN, (0.2.0,0.2.0.0) -> opN, (0.2.0,
                                                  //| 0.2.0.1) -> opN),Map((0.0,0.0.0) -> name, (0.2.0,0.2.0.3) -> op4, (0.0.0,0.0
                                                  //| .0.0) -> op1, (0.1,0.1.0) -> name, (0.2.0,0.2.0.2) -> op3, (0.1.0,0.1.0.0) -
                                                  //| > op1, (0,0.2) ->
                                                  //| Output exceeds cutoff limit.
  var state = expertSystem.init(s1)               //> state  : amr.classic.ClassicTransitionState = NodesToProcess: 17, 15, 14, 13
                                                  //| , 12, 11, 8, 6, 3, 1
                                                  //| Current Graph: 
                                                  //| NodeMap:	Map(1 -> Myanmar, 3 -> MM, 6 -> Thailand, 8 -> TH, 11 -> Lao, 12
                                                  //|  -> People, 13 -> 's, 14 -> Democratic, 15 -> Republic, 17 -> LA)
                                                  //| SpanMap:	Map(1 -> (1,2), 3 -> (2,3), 6 -> (3,4), 8 -> (4,5), 11 -> (5,6),
                                                  //|  12 -> (6,7), 13 -> (7,8), 14 -> (8,9), 15 -> (9,10), 17 -> (10,11))
                                                  //| Edges:	Map()
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map()
                                                  //| PartsOfSpeech:	Map(14 -> JJ, 1 -> NNP, 6 -> NNP, 13 -> POS, 17 -> NNP, 
                                                  //| 12 -> NNP, 3 -> NN, 11 -> NNP, 8 -> NNP, 15 -> NN)
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
  s1.positionToAMR                                //> res0: Map[Int,String] = Map(14 -> 0.2.0.2, 1 -> 0.0.0.0, 6 -> 0.1.0.0, 13 ->
                                                  //|  0.2.0, 12 -> 0.2.0.1, 11 -> 0.2.0.0, 15 -> 0.2.0.3)
  s1.amr.get.nodes                                //> res1: Map[String,String] = Map(0.1 -> country, 0.0.0 -> name, 0.1.0 -> name,
                                                  //|  0.0 -> country, 0.0.0.0 -> "Myanmar", 0.2.0.1 -> "People's", 0.2.0 -> name,
                                                  //|  0.1.0.0 -> "Thailand", 0 -> and, 0.2.0.3 -> "Republic", 0.2 -> country, 0.2
                                                  //| .0.2 -> "Democratic", 0.2.0.0 -> "Lao")
  s1.amr.get.arcs                                 //> res2: Map[(String, String),String] = Map((0.0,0.0.0) -> name, (0.2.0,0.2.0.3
                                                  //| ) -> opN, (0.0.0,0.0.0.0) -> opN, (0.1,0.1.0) -> name, (0.2.0,0.2.0.2) -> op
                                                  //| N, (0.1.0,0.1.0.0) -> opN, (0,0.2) -> opN, (0,0.1) -> opN, (0.2,0.2.0) -> na
                                                  //| me, (0,0.0) -> opN, (0.2.0,0.2.0.0) -> opN, (0.2.0,0.2.0.1) -> opN)
  import java.io._
  val file = new FileWriter("C://AMR//CountryList_exp.txt")
                                                  //> file  : java.io.FileWriter = java.io.FileWriter@53fb3dab
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
  val outputAdv = RunDagger.sampleTrajectory(s1, "C:\\AMR\\WangXueExpert_output.txt", expert, expertSystem = expertSystem)
                                                  //> outputAdv  : amr.Sentence = Sentence(Myanmar (MM); Thailand (TH); Lao Peopl
                                                  //| e's Democratic Republic (LA),
                                                  //| NodeMap:	Map(1 -> Myanmar, 6 -> Thailand, 11 -> Lao, 12 -> People's, 13 -
                                                  //| > name, 14 -> Democratic, 15 -> Republic, 18 -> and, 19 -> country, 20 -> c
                                                  //| ountry)
                                                  //| SpanMap:	Map(1 -> (1,2), 6 -> (3,4), 11 -> (5,6), 12 -> (6,7), 13 -> (7,8
                                                  //| ), 14 -> (8,9), 15 -> (9,10), 18 -> (7,8), 19 -> (3,4), 20 -> (1,2))
                                                  //| Edges:	Map((13,11) -> opN, (13,12) -> opN, (13,14) -> opN, (13,15) -> o
                                                  //| pN, (18,13) -> name, (18,19) -> opN, (18,20) -> opN, (19,6) -> opN, (20,1) 
                                                  //| -> opN)
                                                  //| InsertedNodes:	Map(18 -> , 19 -> , 20 -> )
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map(-1 -> List((3,MM), (8,TH), (17,LA)))
                                                  //| PartsOfSpeech:	Map(14 -> JJ, 20 -> NNP, 1 -> NNP, 6 -> NNP, 13 -> POS, 
                                                  //| 17 -> NNP, 12 -> NNP, 3 -> NN, 18 -> POS, 11 -> NNP, 8 -> NNP, 19 -> NNP, 1
                                                  //| 5 -> NN)
                                                  //| DependencyLabels:	Map(18 -> DUMMY, 19 -> DUMMY, 20 -> DUMMY),Some(AMRGraph
                                                  //| (Map(12 -> "People's", 501 -> country, 19 -> country, 15 -> "Republic", 11 
                                                  //| -> "Lao", 13 -> name, 505 -> name, 6 -> Thailand, 1 -> Myanmar, 14 -> "Demo
                                                  //| cratic", 503 -> name, 20 -> country, 18 -> and),Map(12 -> (6,7), 19 -> (3,4
                                                  //| ), 15 -> (9,10), 11 -> (5,6), 13 -> (7,8), 6 -> (3,4), 1 -> (1,2), 14 -> (8
                                                  //| ,9), 20 -> (1,2), 18 -> (7,8)),Map((20,1) -> opN, (19,6) -> opN, (13,11) ->
                                                  //|  opN, (20,505) -> name, (18,501) -> opN, (18,19) -> opN, (19,503) -> name, 
                                                  //| (13,15) -> opN, (18,13) -> name, (18,20) -> opN, (13,12) -> opN, (13,14) ->
                                                  //|  opN),Map(),List())),Map(14 -> 14, 20 -> 20, 1 -> 1, 6 -> 6, 13 -> 13, 12 -
                                                  //| > 12, 18 -> 18, 11 -> 11, 19 -> 19, 15 -> 15))
     
}