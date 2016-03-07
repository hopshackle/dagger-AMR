package amr

object problematicAMR {
WangXueTransitionSystem.wikification = false
  AMRGraph.usePourdamghaniAligner = true
  ImportConcepts.initialise("C:\\AMR\\Estonian.txt")
  val testData = AMRGraph.importFile("C:\\AMR\\Estonian.txt")
                                                  //> testData  : IndexedSeq[(String, String, String)] = Vector((NATO CONSIDERS cy
                                                  //| ber attacks a threat to military and civilian computer networks after the Es
                                                  //| tonian Government was struck by cyber attacks in 2007.,(c / consider-02 :ARG
                                                  //| 0 (m2 / military :name (n / name :op1 "NATO")) :ARG1 (a / attack-01 :mod (c2
                                                  //|  / cyber)) :ARG2 (t / thing :ARG1-of (t2 / threaten-01 :ARG2 (a5 / and :op1 
                                                  //| (n2 / network :mod (m / military)) :op2 (n4 / network :mod (c3 / civilian)) 
                                                  //| :mod (c4 / computer)))) :time (a3 / after :op1 (s / strike-01 :ARG0 (a4 / at
                                                  //| tack :mod (c6 / cyber)) :ARG1 (g / government-organization :ARG0-of (g2 / go
                                                  //| vern-01 :ARG1 (c5 / country :name (n3 / name :op1 "Estonia")))) :time (d / d
                                                  //| ate-entity :year 2007)))),# ::id PROXY_APW_ENG_20080514_1125.4))
  DependencyTree.preProcess(testData(0)._1)       //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.4 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [4.0 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [3.4 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [4.3 sec].
                                                  //| res0: List[String] = List(NATO, CONSIDERS, cyber, attacks, a, threat, to, mi
                                                  //| litary, and, civilian, computer, networks, after, the, Estonian, Government,
                                                  //|  was, struck, by, cyber, attacks, in, 2007)
  val expert = new WangXueExpert                  //> expert  : amr.WangXueExpert = amr.WangXueExpert@609cd4d8
  val expertSystem = WangXueTransitionSystem      //> expertSystem  : amr.WangXueTransitionSystem.type = amr.WangXueTransitionSyst
                                                  //| em$@17f7cd29
  AMRGraph.setAligner("Pourdamghani")
  expertSystem.reentrance = true
  expertSystem.useCompositeNodes = false
  expertSystem.insertBelow = true
  val s1 = Sentence(testData(0)._1, testData(0)._2, testData(0)._3)
                                                  //> s1  : amr.Sentence = # ::id PROXY_APW_ENG_20080514_1125.4
                                                  //| NATO CONSIDERS cyber attacks a threat to military and civilian computer netw
                                                  //| orks after the Estonian Government was struck by cyber attacks in 2007.
                                                  //| 
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
                                                  //| 6 -> dobj, 21 -> pobj, 9 -> cc, 13 -> mark, 2 -> nn, 17 -> auxpass, 22 -> pr
                                                  //| ep, 12 -> pobj, 7 -> prep, 3 -> nsubj, 18 -> advcl, 16 -> nsubjpass, 11 -> n
                                                  //| n, 23 -> pobj, 8 -> amod, 19 -> prep, 15 -> amod)
                                                  //| AMRGraph(Map(0.1 -> attack-01, 0.0.0 -> name, 0.3.0.1.0.0 -> country, 0.2.0.
                                                  //| 0.0 -> network, 0.1.0 -> cyber, 0.2.0.0.1.0 -> civilian, 0.3.0.2.0 -> 2007, 
                                                  //| 0.0 -> military, 0.2.0.0.1 -> network, 0.3.0.1.0 -> govern-01, 0.0.0.0 -> "N
                                                  //| ATO", 0.2.0 -> threaten-01, 0.3.0.0 -> attack, 0.3.0 -> strike-01, 0.3 -> af
                                                  //| ter, 0.2.0.0.0.0 -> military, 0.2.0.0.2 -> computer, 0 -> consider-02, 0.3.0
                                                  //| .1 -> government-organization, 0.2 -> thing, 0.3.0.2 -> date-entity, 0.3.0.1
                                                  //| .0.0.0.0 -> "Estonia", 0.2.0.0 -> and, 0.3.0.1.0.
                                                  //| Output exceeds cutoff limit.
  var state = expertSystem.init(s1)               //> state  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(23, 10, 9, 22, 20, 8, 11, 21, 14, 15, 12, 5, 19, 13, 17, 16
                                                  //| , 1, 2, 7, 6, 18, 3, 4)
                                                  //| Children:	List()
                                                  //| PartialGraph:	
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
                                                  //| 6 -> dobj, 21 -> pobj, 9 -> cc, 13 -> mark, 2 -> nn, 17 -> auxpass, 22 -> pr
                                                  //| ep, 12 -> pobj, 7 -> prep, 3 -> nsubj, 18 -> advcl, 16 -> nsubjpass, 11 -> n
                                                  //| n, 23 -> pobj, 8 -> amod, 19 -> prep, 15 -> amod)
                                                  //| Mappings:	10 -> 0.2.0.0.1.0
                                                  //| 20 -> 0.3.0.1.0
                                                  //| 1 -> 0.0.0.0
                                                  //| 6 -> 0.2
                                                  //| 21 -> 0.3.0.1
                                                  //| 9 -> 0.2.0.0
                                                  //| 13 -> 0.3
                                                  //| 2 -> 0
                                                  //| 12 -> 0.2.0.0.0
                                                  //| 3 -> 0.1.0
                                                  //| 18 -> 0.3.0
                                                  //| 16 -> 0.3.0.0
                                                  //| 11 -> 0.2.0.0.2
                                                  //| 23 -> 0.3.0.2.0
                                                  //| 8 -> 0.2.0.0.0.0
                                                  //| 4 -> 0.1
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
  s1.positionToAMR                                //> res1: Map[Int,String] = Map(10 -> 0.2.0.0.1.0, 20 -> 0.3.0.1.0, 1 -> 0.0.0.0
                                                  //| , 6 -> 0.2, 21 -> 0.3.0.1, 9 -> 0.2.0.0, 13 -> 0.3, 2 -> 0, 12 -> 0.2.0.0.0,
                                                  //|  3 -> 0.1.0, 18 -> 0.3.0, 16 -> 0.3.0.0, 11 -> 0.2.0.0.2, 23 -> 0.3.0.2.0, 8
                                                  //|  -> 0.2.0.0.0.0, 4 -> 0.1)
  s1.amr.get.nodes                                //> res2: Map[String,String] = Map(0.1 -> attack-01, 0.0.0 -> name, 0.3.0.1.0.0 
                                                  //| -> country, 0.2.0.0.0 -> network, 0.1.0 -> cyber, 0.2.0.0.1.0 -> civilian, 0
                                                  //| .3.0.2.0 -> 2007, 0.0 -> military, 0.2.0.0.1 -> network, 0.3.0.1.0 -> govern
                                                  //| -01, 0.0.0.0 -> "NATO", 0.2.0 -> threaten-01, 0.3.0.0 -> attack, 0.3.0 -> st
                                                  //| rike-01, 0.3 -> after, 0.2.0.0.0.0 -> military, 0.2.0.0.2 -> computer, 0 -> 
                                                  //| consider-02, 0.3.0.1 -> government-organization, 0.2 -> thing, 0.3.0.2 -> da
                                                  //| te-entity, 0.3.0.1.0.0.0.0 -> "Estonia", 0.2.0.0 -> and, 0.3.0.1.0.0.0 -> na
                                                  //| me, 0.3.0.0.0 -> cyber)
  s1.amr.get.arcs                                 //> res3: Map[(String, String),String] = Map((0.0,0.0.0) -> name, (0.3.0,0.3.0.0
                                                  //| ) -> ARG0, (0,0.3) -> time, (0.0.0,0.0.0.0) -> opN, (0.2.0.0.1,0.2.0.0.1.0) 
                                                  //| -> mod, (0.1,0.1.0) -> mod, (0.3.0,0.3.0.1) -> ARG1, (0.3.0,0.3.0.2) -> time
                                                  //| , (0.3.0.0,0.3.0.0.0) -> mod, (0,0.2) -> ARG2, (0,0.1) -> ARG1, (0.2.0.0.0,0
                                                  //| .2.0.0.0.0) -> mod, (0.2,0.2.0) -> ARG1-of, (0.3,0.3.0) -> opN, (0.3.0.1.0,0
                                                  //| .3.0.1.0.0) -> ARG1, (0.2.0.0,0.2.0.0.1) -> opN, (0.2.0.0,0.2.0.0.0) -> opN,
                                                  //|  (0.3.0.1.0.0.0,0.3.0.1.0.0.0.0) -> opN, (0.3.0.1,0.3.0.1.0) -> ARG0-of, (0,
                                                  //| 0.0) -> ARG0, (0.3.0.1.0.0,0.3.0.1.0.0.0) -> name, (0.2.0,0.2.0.0) -> ARG2, 
                                                  //| (0.3.0.2,0.3.0.2.0) -> year, (0.2.0.0,0.2.0.0.2) -> mod)
  s1.dependencyTree.nodeLemmas                    //> res4: Map[Int,String] = Map(5 -> a, 10 -> civilian, 14 -> the, 20 -> cyber, 
                                                  //| 1 -> nato, 6 -> threat, 21 -> attack, 9 -> and, 13 -> after, 2 -> considers,
                                                  //|  17 -> be, 22 -> in, 12 -> network, 7 -> to, 3 -> cyber, 18 -> strike, 16 ->
                                                  //|  government, 11 -> computer, 23 -> ##, 8 -> military, 19 -> by, 4 -> attack,
                                                  //|  15 -> estonian)
  ImportConcepts.conceptStrings foreach println   //> network
                                                  //| name
                                                  //| date-entity
                                                  //| threaten-01
                                                  //| 2007
                                                  //| Estonia
                                                  //| NATO
                                                  //| thing
                                                  //| strike-01
                                                  //| country
                                                  //| computer
                                                  //| civilian
                                                  //| after
                                                  //| consider-02
                                                  //| cyber
                                                  //| attack-01
                                                  //| attack
                                                  //| military
                                                  //| and
                                                  //| govern-01
                                                  //| government-organization
  ImportConcepts.conceptsPerLemma foreach println //> (network,Set(1))
                                                  //| (nato,Set(7))
                                                  //| (considers,Set(14))
                                                  //| (strike,Set(9))
                                                  //| (computer,Set(11))
                                                  //| (civilian,Set(12))
                                                  //| (government,Set(17))
                                                  //| (after,Set(13))
                                                  //| (cyber,Set(20, 15))
                                                  //| (threat,Set(8))
                                                  //| (attack,Set(21, 16))
                                                  //| (military,Set(18))
                                                  //| (and,Set(19))
                                                  //| (##,Set())
  import java.io._
  val file = new FileWriter("C://AMR//Expert_output.txt")
                                                  //> file  : java.io.FileWriter = java.io.FileWriter@10cf09e8
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
                                                  //> res5: String = ( b / consider-02 
                                                  //| 	:ARG0 ( c2 / military 
                                                  //| 		:name ( b2 / name :op1 "NATO"))
                                                  //| 	:ARG1 ( d / attack-01 
                                                  //| 		:mod ( c / cyber ))
                                                  //| 	:ARG2 ( f / thing 
                                                  //| 		:ARG1-of ( z / threaten-01 
                                                  //| 			:ARG2 ( i / and 
                                                  //| 				:op1 ( y / network 
                                                  //| 					:mod ( j / civilian ))
                                                  //| 				:mod ( k / computer )
                                                  //| 				:op2 ( l / network 
                                                  //| 					:mod ( h / military )))))
                                                  //| 	:time ( m / after 
                                                  //| 		:op1 ( r / strike-01 
                                                  //| 			:ARG0 ( p / attack 
                                                  //| 				:mod ( a2 / cyber ))
                                                  //| 			:ARG1 ( u / government-organization 
                                                  //| 				:ARG0-of ( t / govern-01 ))
                                                  //| 			:time ( x / date-entity :year 2007))))
  AMROutput.convertToString(Smatch.reduceAMR(state.currentGraph.toAMR))
                                                  //> res6: String = ( b / consider-02 
                                                  //| 	:ARG0 ( c2 / military 
                                                  //| 		:name ( b2 / name ))
                                                  //| 	:ARG1 ( d / attack-01 
                                                  //| 		:mod ( c / cyber ))
                                                  //| 	:ARG2 ( f / thing 
                                                  //| 		:ARG1-of ( z / threaten-01 
                                                  //| 			:ARG2 ( i / and 
                                                  //| 				:op1 ( y / network 
                                                  //| 					:mod ( j / civilian ))
                                                  //| 				:mod ( k / computer )
                                                  //| 				:op2 ( l / network 
                                                  //| 					:mod ( h / military )))))
                                                  //| 	:time ( m / after 
                                                  //| 		:op1 ( r / strike-01 
                                                  //| 			:ARG0 ( p / attack 
                                                  //| 				:mod ( a2 / cyber ))
                                                  //| 			:ARG1 ( u / government-organization 
                                                  //| 				:ARG0-of ( t / govern-01 ))
                                                  //| 			:time ( x / date-entity ))))
}