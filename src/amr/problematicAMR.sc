package amr

object problematicAMR {

val testData = AMRGraph.importFile("C:\\AMR\\CountryList.txt")
                                                  //> testData  : IndexedSeq[(String, String)] = Vector((Estonia (EE); Germany (DE
                                                  //| ): Italy (IT); Spain (ES); United States (US),(a / and :op1 (c / country :na
                                                  //| me (n / name :op1 "Estonia")) :op2 (c4 / country :name (n7 / name :op1 "Germ
                                                  //| any")) :op3 (c5 / country :name (n9 / name :op1 "Italy")) :op4 (c6 / country
                                                  //|  :name (n10 / name :op1 "Spain")) :op5 (c8 / country :name (n14 / name :op1 
                                                  //| "United" :op2 "States")))))
  val expert = new WangXueExpert                  //> expert  : amr.WangXueExpert = amr.WangXueExpert@482f8f11
  val expertSystem = WangXueTransitionSystem      //> expertSystem  : amr.WangXueTransitionSystem.type = amr.WangXueTransitionSyst
                                                  //| em$@d8355a8
  AMRGraph.setAligner("improved")
  expertSystem.reentrance = true
  expertSystem.useCompositeNodes = true
  ImportConcepts.initialise("C:\\AMR\\CountryList.txt")
// ImportConcepts.relationStrin
  val s1 = Sentence(testData(0)._1, testData(0)._2)
                                                  //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.3 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [4.6 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [2.1 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [3.9 sec].
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
                                                  //| > and, 0.2 -> country, 0.4.0.1 -> "States", 0.2.0.0 -> "Italy"),Map(0.0.0.0 
                                                  //| -> (1,2), 0.3.0.0 -> (7,8), 0.1.0.0 -> (3,4), 0.4.0.0 -> (9,10), 0.4.0.1 -> 
                                                  //| (10,11), 0.2.0.0 -> (5,6)),Map((0.0,0.0.0) -> name, (0.3.0,0.3.0.0) -> opN, 
                                                  //| (0.4.0,0.4.0.1) -> opN, (0,0.3) -> opN, (0.4.0,0.4.0.0) -> opN, (0.0.0,0.0.0
                                                  //| .0) -> opN, (0.1,0.1.0) -> name, (0.1.0,0.1.0.0) -> opN, (0,0.2) -> opN, (0,
                                                  //| 0.1) -> opN, (0.2,0.2.0) -> name, (0.3,0.3.0) -> name, (0.4,0.4.0) -> name, 
                                                  //| (0,0.4) -> opN, (0,0.0) -> opN, (0.2.0,0.2.0.0) -> opN),Map((0.0,0.0.0) -> n
                                                  //| ame, (0.3.0,0.3.0.0) -> op1, (0.4.0,0.4.0.1) -> o
                                                  //| Output exceeds cutoff limit.
  var state = expertSystem.init(s1)               //> state  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(21, 24, 13, 18, 22, 8, 11, 16, 1, 6, 3)
                                                  //| Children:	List()
                                                  //| PartialGraph:	
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
                                                  //|  22 -> dep, 18 -> appos, 16 -> dep, 11 -> dep, 8 -> appos)
                                                  //| Mappings:	1 -> 0.0.0.0
                                                  //| 6 -> 0.1.0.0
                                                  //| 21 -> 0.4.0.0
                                                  //| 22 -> 0.4.0.1
                                                  //| 16 -> 0.3.0.0
                                                  //| 11 -> 0.2.0.0
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
                                                  //> file  : java.io.FileWriter = java.io.FileWriter@2e6a8155
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

AMROutput.convertToString(state.currentGraph.toAMR)
                                                  //> res3: String = ( a2 / and
                                                  //| 	:op1 ( g2 / country
                                                  //| 		:name ( f2 / name
                                                  //| 			:op1 "Estonia"))
                                                  //| 	:op2 ( i2 / country
                                                  //| 		:name ( h2 / name
                                                  //| 			:op1 "Germany"))
                                                  //| 	:op3 ( c2 / country
                                                  //| 		:name ( b2 / name
                                                  //| 			:op1 "Italy"))
                                                  //| 	:op4 ( e2 / country
                                                  //| 		:name ( d2 / name
                                                  //| 			:op1 "Spain"))
                                                  //| 	:op5 ( z / country
                                                  //| 		:name ( y / name
                                                  //| 			:op1 "United"
                                                  //| 			:op2 "States")))
}