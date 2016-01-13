package amr

object problematicAMR {

  val testData = AMRGraph.importFile("C:\\AMR\\1990s.txt")
                                                  //> testData  : IndexedSeq[(String, String, String)] = Vector((The Iraqis are in 
                                                  //| line behind their compatriots who have been waiting in Jordan since the late 
                                                  //| 1990s .,(l / line-up-02 :ARG1 (p / person :mod (c2 / country :wiki "Iraq" :na
                                                  //| me (n2 / name :op1 "Iraq")) :location (b / behind :op1 (c3 / compatriot :ARG1
                                                  //| -of (w / wait-01 :location (c4 / country :wiki "Jordan" :name (n3 / name :op1
                                                  //|  "Jordan")) :time (s / since :op1 (d2 / date-entity :decade 1990 :mod (l2 / l
                                                  //| ate)))))))),# ::id wb.eng_0015.161))
  DependencyTree.preProcess(testData(0)._1)       //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.8 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [4.2 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [3.9 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [4.8 sec].
                                                  //| res0: List[String] = List(The, Iraqis, are, in, line, behind, their, compatr
                                                  //| iots, who, have, been, waiting, in, Jordan, since, the, late, 1990, s)
  val testDT = DependencyTree(testData(0)._1)     //> the
                                                  //| Iraqis
                                                  //| be
                                                  //| in
                                                  //| line
                                                  //| behind
                                                  //| they
                                                  //| compatriot
                                                  //| who
                                                  //| have
                                                  //| be
                                                  //| wait
                                                  //| in
                                                  //| Jordan
                                                  //| since
                                                  //| the
                                                  //| late
                                                  //| 1990
                                                  //| s
                                                  //| testDT  : amr.DependencyTree = 
                                                  //| NodeMap:	Map(1 -> The, 2 -> Iraqis, 3 -> are, 4 -> in, 5 -> line, 6 -> be
                                                  //| hind, 7 -> their, 8 -> compatriots, 9 -> who, 10 -> have, 11 -> been, 12 -> 
                                                  //| waiting, 13 -> in, 14 -> Jordan, 15 -> since, 16 -> the, 17 -> late, 18 -> 1
                                                  //| 990, 19 -> s)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,12
                                                  //| ), 12 -> (12,13), 13 -> (13,14), 14 -> (14,15), 15 -> (15,16), 16 -> (16,17)
                                                  //| , 17 -> (17,18), 18 -> (18,19), 19 -> (19,20))
                                                  //| Edges:	Map((2,1) -> det, (3,2) -> nsubj, (3,4) -> prep, (4,5) -> pobj, 
                                                  //| (5,6) -> prep, (5,12) -> rcmod, (6,8) -> pobj, (8,7) -> poss, (12,9) -> nsub
                                                  //| j, (12,10) -> aux, (12,11) -> aux, (12,13) -> prep, (12,15) -> prep, (13,14)
                                                  //|  -> pobj, (15,19) -> pobj, (19,16) -> det, (19,17) -> amod, (19,18) -> num)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map()
                                                  //| PartsOfSpeech:	Map(5 -> NN, 10 -> VBP, 14 -> NNP, 1 -> DT, 6 -> IN, 9 -
                                                  //| > WP, 13 -> IN, 2 -> NNPS, 17 -> JJ, 12 -> VBG, 7 -> PRP$, 3 -> VBP, 18 -> C
                                                  //| D, 16 -> DT, 11 -> VBN, 8 -> NNS, 19 -> NNS, 4 -> IN, 15 -> IN)
                                                  //| DependencyLabels:	Map(5 -> pobj, 10 -> aux, 14 -> pobj, 1 -> det, 6 -> pre
                                                  //| p, 9 -> nsubj, 13 -> prep, 2 -> nsubj, 17 -> amod, 12 -> rcmod, 7 -> poss, 1
                                                  //| 8 -> num, 16 -> det, 11 -> aux, 8 -> pobj, 19 -> pobj, 4 -> prep, 15 -> prep
                                                  //| )
  testDT.nodeLemmas foreach println               //> (5,line)
                                                  //| (10,have)
                                                  //| (14,jordan)
                                                  //| (1,the)
                                                  //| (6,behind)
                                                  //| (9,who)
                                                  //| (13,in)
                                                  //| (2,iraqis)
                                                  //| (17,late)
                                                  //| (12,wait)
                                                  //| (7,they)
                                                  //| (3,be)
                                                  //| (18,##)
                                                  //| (16,the)
                                                  //| (11,be)
                                                  //| (8,compatriot)
                                                  //| (19,s)
                                                  //| (4,in)
                                                  //| (15,since)
  val expert = new WangXueExpert                  //> expert  : amr.WangXueExpert = amr.WangXueExpert@2a54a73f
  val expertSystem = WangXueTransitionSystem      //> expertSystem  : amr.WangXueTransitionSystem.type = amr.WangXueTransitionSyst
                                                  //| em$@3bf9ce3e
  AMRGraph.setAligner("Pourdamghani")
  expertSystem.reentrance = true
  expertSystem.useCompositeNodes = false
  expertSystem.insertBelow = true
  ImportConcepts.initialise("C:\\AMR\\1990s.txt")
  // ImportConcepts.relationStrin
  val s1 = Sentence(testData(0)._1, testData(0)._2, testData(0)._3)
                                                  //> the
                                                  //| Iraqis
                                                  //| be
                                                  //| in
                                                  //| line
                                                  //| behind
                                                  //| they
                                                  //| compatriot
                                                  //| who
                                                  //| have
                                                  //| be
                                                  //| wait
                                                  //| in
                                                  //| Jordan
                                                  //| since
                                                  //| the
                                                  //| late
                                                  //| 1990
                                                  //| s
                                                  //| s1  : amr.Sentence = # ::id wb.eng_0015.161
                                                  //| The Iraqis are in line behind their compatriots who have been waiting in Jor
                                                  //| dan since the late 1990s .
                                                  //| 
                                                  //| NodeMap:	Map(1 -> The, 2 -> Iraqis, 3 -> are, 4 -> in, 5 -> line, 6 -> be
                                                  //| hind, 7 -> their, 8 -> compatriots, 9 -> who, 10 -> have, 11 -> been, 12 -> 
                                                  //| waiting, 13 -> in, 14 -> Jordan, 15 -> since, 16 -> the, 17 -> late, 18 -> 1
                                                  //| 990, 19 -> s)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,12
                                                  //| ), 12 -> (12,13), 13 -> (13,14), 14 -> (14,15), 15 -> (15,16), 16 -> (16,17)
                                                  //| , 17 -> (17,18), 18 -> (18,19), 19 -> (19,20))
                                                  //| Edges:	Map((2,1) -> det, (3,2) -> nsubj, (3,4) -> prep, (4,5) -> pobj, 
                                                  //| (5,6) -> prep, (5,12) -> rcmod, (6,8) -> pobj, (8,7) -> poss, (12,9) -> nsub
                                                  //| j, (12,10) -> aux, (12,11) -> aux, (12,13) -> prep, (12,15) -> prep, (13,14)
                                                  //|  -> pobj, (15,19) -> pobj, (19,16) -> det, (19,17) -> amod, (19,18) -> num)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map()
                                                  //| PartsOfSpeech:	Map(5 -> NN, 10 -> VBP, 14 -> NNP, 1 -> DT, 6 -> IN, 9 -
                                                  //| > WP, 13 -> IN, 2 -> NNPS, 17 -> JJ, 12 -> VBG, 7 -> PRP$, 3 -> VBP, 18 -> C
                                                  //| D, 16 -> DT, 11 -> VBN, 8 -> NNS, 19 -> NNS, 4 -> IN, 15 -> IN)
                                                  //| DependencyLabels:	Map(5 -> pobj, 10 -> aux, 14 -> pobj, 1 -> det, 6 -> pre
                                                  //| p, 9 -> nsubj, 13 -> prep, 2 -> nsubj, 17 -> amod, 12 -> rcmod, 7 -> poss, 1
                                                  //| 8 -> num, 16 -> det, 11 -> aux, 8 -> pobj, 19 -> pobj, 4 -> prep, 15 -> prep
                                                  //| )
                                                  //| AMRGraph(Map(0.0.1.0.0.0.1.0 -> "Jordan", 0.0.1.0.0.0 -> country, 0.0.0 -> c
                                                  //| ountry, 0.0.0.1.0 -> "Iraq", 0.0.1.0.0.1.0.0 -> 1990, 0.0.1.0.0.1.0 -> date-
                                                  //| entity, 0.0.1.0 -> compatriot, 0.0.1.0.0.0.1 -> name, 0.0 -> person, 0.0.1.0
                                                  //| .0.0.0 -> "Jordan", 0.0.0.0 -> "Iraq", 0.0.1.0.0.1.0.1 -> late, 0.0.1 -> beh
                                                  //| ind, 0.0.1.0.0 -> wait-01, 0 -> line-up-02, 0.0.0.1 -> name, 0.0.1.0.0.1 -> 
                                                  //| since),Map(0.0.1.0.0.0.1.0 -> (14,15), 0.0.0.1.0 -> (2,3), 0.0.1.0.0.1.0.0 -
                                                  //| > (18,19), 0.0.1.0 -> (8,9), 0.0.1.0.0.1.0.1 -> (17,18), 0.0.1 -> (6,7), 0.0
                                                  //| .1.0.0 -> (12,13), 0 -> (5,6), 0.0.1.0.0.1 -> (15,16)),Map((0.0.1.0.0.0,0.0.
                                                  //| 1.0.0.0.1) -> name, (0.0,0.0.0) -> mod, (0.0.1.0.0.1,0.0.1.0
                                                  //| Output exceeds cutoff limit.
  var state = expertSystem.init(s1)               //> state  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(17, 16, 18, 19, 14, 7, 10, 9, 15, 11, 13, 8, 12, 6, 5, 1, 4
                                                  //| , 2, 3)
                                                  //| Children:	List()
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(1 -> The, 2 -> Iraqis, 3 -> are, 4 -> in, 5 -> line, 6 -> be
                                                  //| hind, 7 -> their, 8 -> compatriots, 9 -> who, 10 -> have, 11 -> been, 12 -> 
                                                  //| waiting, 13 -> in, 14 -> Jordan, 15 -> since, 16 -> the, 17 -> late, 18 -> 1
                                                  //| 990, 19 -> s)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,12
                                                  //| ), 12 -> (12,13), 13 -> (13,14), 14 -> (14,15), 15 -> (15,16), 16 -> (16,17)
                                                  //| , 17 -> (17,18), 18 -> (18,19), 19 -> (19,20))
                                                  //| Edges:	Map((2,1) -> det, (3,2) -> nsubj, (3,4) -> prep, (4,5) -> pobj, 
                                                  //| (5,6) -> prep, (5,12) -> rcmod, (6,8) -> pobj, (8,7) -> poss, (12,9) -> nsub
                                                  //| j, (12,10) -> aux, (12,11) -> aux, (12,13) -> prep, (12,15) -> prep, (13,14)
                                                  //|  -> pobj, (15,19) -> pobj, (19,16) -> det, (19,17) -> amod, (19,18) -> num)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map()
                                                  //| PartsOfSpeech:	Map(5 -> NN, 10 -> VBP, 14 -> NNP, 1 -> DT, 6 -> IN, 9 -
                                                  //| > WP, 13 -> IN, 2 -> NNPS, 17 -> JJ, 12 -> VBG, 7 -> PRP$, 3 -> VBP, 18 -> C
                                                  //| D, 16 -> DT, 11 -> VBN, 8 -> NNS, 19 -> NNS, 4 -> IN, 15 -> IN)
                                                  //| DependencyLabels:	Map(5 -> pobj, 10 -> aux, 14 -> pobj, 1 -> det, 6 -> pre
                                                  //| p, 9 -> nsubj, 13 -> prep, 2 -> nsubj, 17 -> amod, 12 -> rcmod, 7 -> poss, 1
                                                  //| 8 -> num, 16 -> det, 11 -> aux, 8 -> pobj, 19 -> pobj, 4 -> prep, 15 -> prep
                                                  //| )
                                                  //| Mappings:	5 -> 0
                                                  //| 14 -> 0.0.1.0.0.0.1.0
                                                  //| 6 -> 0.0.1
                                                  //| 2 -> 0.0.0.1.0
                                                  //| 17 -> 0.0.1.0.0.1.0.1
                                                  //| 12 -> 0.0.1.0.0
                                                  //| 18 -> 0.0.1.0.0.1.0.0
                                                  //| 8 -> 0.0.1.0
                                                  //| 15 -> 0.0.1.0.0.1
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
  s1.positionToAMR                                //> res1: Map[Int,String] = Map(5 -> 0, 14 -> 0.0.1.0.0.0.1.0, 6 -> 0.0.1, 2 -> 
                                                  //| 0.0.0.1.0, 17 -> 0.0.1.0.0.1.0.1, 12 -> 0.0.1.0.0, 18 -> 0.0.1.0.0.1.0.0, 8 
                                                  //| -> 0.0.1.0, 15 -> 0.0.1.0.0.1)
  s1.amr.get.nodes                                //> res2: Map[String,String] = Map(0.0.1.0.0.0.1.0 -> "Jordan", 0.0.1.0.0.0 -> c
                                                  //| ountry, 0.0.0 -> country, 0.0.0.1.0 -> "Iraq", 0.0.1.0.0.1.0.0 -> 1990, 0.0.
                                                  //| 1.0.0.1.0 -> date-entity, 0.0.1.0 -> compatriot, 0.0.1.0.0.0.1 -> name, 0.0 
                                                  //| -> person, 0.0.1.0.0.0.0 -> "Jordan", 0.0.0.0 -> "Iraq", 0.0.1.0.0.1.0.1 -> 
                                                  //| late, 0.0.1 -> behind, 0.0.1.0.0 -> wait-01, 0 -> line-up-02, 0.0.0.1 -> nam
                                                  //| e, 0.0.1.0.0.1 -> since)
  s1.amr.get.arcs                                 //> res3: Map[(String, String),String] = Map((0.0.1.0.0.0,0.0.1.0.0.0.1) -> name
                                                  //| , (0.0,0.0.0) -> mod, (0.0.1.0.0.1,0.0.1.0.0.1.0) -> opN, (0.0.1.0.0,0.0.1.0
                                                  //| .0.0) -> location, (0.0.1.0.0.1.0,0.0.1.0.0.1.0.1) -> mod, (0.0.0,0.0.0.0) -
                                                  //| > wiki, (0.0.1.0.0,0.0.1.0.0.1) -> time, (0.0.1.0.0.0.1,0.0.1.0.0.0.1.0) -> 
                                                  //| opN, (0.0.1.0.0.1.0,0.0.1.0.0.1.0.0) -> decade, (0.0.0,0.0.0.1) -> name, (0.
                                                  //| 0.0.1,0.0.0.1.0) -> opN, (0,0.0) -> ARG1, (0.0.1,0.0.1.0) -> opN, (0.0.1.0,0
                                                  //| .0.1.0.0) -> ARG1-of, (0.0,0.0.1) -> location, (0.0.1.0.0.0,0.0.1.0.0.0.0) -
                                                  //| > wiki)
  s1.dependencyTree.nodeLemmas                    //> res4: Map[Int,String] = Map(5 -> line, 10 -> have, 14 -> jordan, 1 -> the, 6
                                                  //|  -> behind, 9 -> who, 13 -> in, 2 -> iraqis, 17 -> late, 12 -> wait, 7 -> th
                                                  //| ey, 3 -> be, 18 -> ##, 16 -> the, 11 -> be, 8 -> compatriot, 19 -> s, 4 -> i
                                                  //| n, 15 -> since)
 ImportConcepts.conceptStrings foreach println    //> behind
                                                  //| Iraq
                                                  //| name
                                                  //| Jordan
                                                  //| compatriot
                                                  //| date-entity
                                                  //| late
                                                  //| country
                                                  //| wait-01
                                                  //| person
                                                  //| line-up-02
                                                  //| since
                                                  //| 1990
 ImportConcepts.conceptsPerLemma foreach println  //> Loading expert results
                                                  //| the
                                                  //| Iraqis
                                                  //| be
                                                  //| in
                                                  //| line
                                                  //| behind
                                                  //| they
                                                  //| compatriot
                                                  //| who
                                                  //| have
                                                  //| be
                                                  //| wait
                                                  //| in
                                                  //| Jordan
                                                  //| since
                                                  //| the
                                                  //| late
                                                  //| 1990
                                                  //| s
                                                  //| (behind,Set(1))
                                                  //| (wait,Set(9))
                                                  //| (compatriot,Set(5))
                                                  //| (late,Set(7))
                                                  //| (line,Set(11))
                                                  //| (iraqis,Set(2))
                                                  //| (jordan,Set(4))
                                                  //| (since,Set(12))
                                                  //| (##,Set())
  import java.io._
  val file = new FileWriter("C://AMR//1990s_exp.txt")
                                                  //> file  : java.io.FileWriter = java.io.FileWriter@6bffbc6d
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
                                                  //> res5: String = ( e / line-up-02 
                                                  //| 	:ARG1 ( w / person 
                                                  //| 		:mod ( y / country :wiki "Iraq"
                                                  //| 			:name ( x / name :op1 "Iraq"))
                                                  //| 		:location ( f / behind 
                                                  //| 			:op1 ( h / compatriot 
                                                  //| 				:ARG1-of ( l / wait-01 
                                                  //| 					:location ( v / country :wiki "Jordan"
                                                  //| 						:name ( u / name :op1 "Jordan"))
                                                  //| 
                                                  //| 					:time ( o / since 
                                                  //| 						:op1 ( t / date-entity :decade 1
                                                  //| 990
                                                  //| 							:mod ( q / late ))))))))
                                                  //| 
  AMROutput.convertToString(Smatch.reduceAMR(state.currentGraph.toAMR))
                                                  //> res6: String = ( e / line-up-02 
                                                  //| 	:ARG1 ( w / person 
                                                  //| 		:mod ( y / country 
                                                  //| 			:name ( x / name ))
                                                  //| 		:location ( f / behind 
                                                  //| 			:op1 ( h / compatriot 
                                                  //| 				:ARG1-of ( l / wait-01 
                                                  //| 					:location ( v / country 
                                                  //| 						:name ( u / name ))
                                                  //| 					:time ( o / since 
                                                  //| 						:op1 ( t / date-entity 
                                                  //| 							:mod ( q / late ))))))))
                                                  //| 
}