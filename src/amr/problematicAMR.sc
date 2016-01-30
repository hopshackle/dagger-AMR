package amr

object problematicAMR {

  AMRGraph.textEncoding = "ISO8859-1"
  val testData = AMRGraph.importFile("C:\\AMR\\Asperger.txt")
                                                  //> testData  : IndexedSeq[(String, String, String)] = Vector((My brother's auti
                                                  //| stic but I haven't noticed this with him either, but the spectrum is so broa
                                                  //| d and I myself ?probably have mild asperger?s because I have problems with c
                                                  //| ommunication and don't pick up on social reactions/body language?.,(c / cont
                                                  //| rast-01 :ARG1 (c2 / contrast-01 :ARG1 (a / autistic :domain (p4 / person :AR
                                                  //| G0-of (h3 / have-rel-role-91 :ARG1 (i / i) :ARG2 (b / brother)))) :ARG2 (n /
                                                  //|  notice-01 :polarity - :ARG0 i :ARG1 a :mod (e / either))) :ARG2 (a2 / and :
                                                  //| op1 (b2 / broad-02 :ARG1 (s2 / spectrum) :degree (s / so)) :op2 (p / probabl
                                                  //| e :domain (h2 / have-03 :ARG0 i :ARG1 (d / disease :wiki "Asperger_syndrome"
                                                  //|  :name (n2 / name :op1 "Asperger's") :mod (m2 / mild)) :ARG0-of (c3 / cause-
                                                  //| 01 :ARG1 (a5 / and :op1 (h / have-03 :ARG0 i :ARG1 (p2 / problem :topic (c4 
                                                  //| / communicate-01))) :op2 (p3 / pick-up-11 :polarity - :ARG0 i :ARG1 (s4 / sl
                                                  //| ash :op1 (r / react-01 :mod (s3 / society)) :op2 (l / language :mod (b3 / bo
                                                  //| dy)))))))))),# ::id DF-200-192400-625_7200.11))
  DependencyTree.preProcess(testData(0)._1)       //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.8 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [5.6 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [6.8 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [7.8 sec].
                                                  //| res0: List[String] = List(My, brother, 's, autistic, but, I, have, n't, noti
                                                  //| ced, this, with, him, either, but, the, spectrum, is, so, broad, and, I, mys
                                                  //| elf, probably, have, mild, asperger, ?s, because, I, have, problems, with, c
                                                  //| ommunication, and, do, n't, pick, up, on, social, reactions, /, body, langua
                                                  //| ge)
  val expert = new WangXueExpert                  //> expert  : amr.WangXueExpert = amr.WangXueExpert@732c2a62
  val expertSystem = WangXueTransitionSystem      //> expertSystem  : amr.WangXueTransitionSystem.type = amr.WangXueTransitionSyst
                                                  //| em$@2dde1bff
  AMRGraph.setAligner("Pourdamghani")
  expertSystem.reentrance = true
  expertSystem.useCompositeNodes = false
  expertSystem.insertBelow = true
  ImportConcepts.initialise("C:\\AMR\\Asperger.txt")
  // ImportConcepts.relationStrin
  val s1 = Sentence(testData(0)._1, testData(0)._2, testData(0)._3)
                                                  //> s1  : amr.Sentence = # ::id DF-200-192400-625_7200.11
                                                  //| My brother's autistic but I haven't noticed this with him either, but the sp
                                                  //| ectrum is so broad and I myself ?probably have mild asperger?s because I hav
                                                  //| e problems with communication and don't pick up on social reactions/body lan
                                                  //| guage?.
                                                  //| 
                                                  //| NodeMap:	Map(1 -> My, 2 -> brother, 3 -> 's, 4 -> autistic, 5 -> but, 6 -
                                                  //| > I, 7 -> have, 8 -> n't, 9 -> noticed, 10 -> this, 11 -> with, 12 -> him, 1
                                                  //| 3 -> either, 14 -> but, 15 -> the, 16 -> spectrum, 17 -> is, 18 -> so, 19 ->
                                                  //|  broad, 20 -> and, 21 -> I, 22 -> myself, 24 -> probably, 25 -> have, 26 -> 
                                                  //| mild, 27 -> asperger, 28 -> ?s, 29 -> because, 30 -> I, 31 -> have, 32 -> pr
                                                  //| oblems, 33 -> with, 34 -> communication, 35 -> and, 36 -> do, 37 -> n't, 38 
                                                  //| -> pick, 39 -> up, 40 -> on, 41 -> social, 42 -> reactions, 43 -> /, 44 -> b
                                                  //| ody, 45 -> language)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,12
                                                  //| ), 12 -> (12,13), 13 -> (13,14), 14 -> (14,15), 15 -> (15,16), 16 -> (16,17)
                                                  //| , 17 -> (17,18), 18 -> (18,19), 19 -> (19,20), 20 -> (20,21), 21 -> (21,22),
                                                  //|  22 -> (22,23), 24 -> (23,24), 25 -> (24,25), 26 -> (25,26), 27 -> (26,27), 
                                                  //| 28 -> (27,28), 29 -> (28,29), 30 -> (29,30), 31 -> (30,31), 32 -> (31,32), 3
                                                  //| 3 -> (32,33), 34 -> (33,34), 35 -> (34,35), 36 -> (35,36), 37 -> (36,37), 38
                                                  //|  -> (37,38), 39 -> (38,39), 40 -> (39,40), 41 -> (40,41), 42 -> (41,42), 43 
                                                  //| -> (42,43), 44 -> (43,44), 45 -> (44,45))
                                                  //| Edges:	Map((2,1) -> poss, (4,2) -> nsubj, (4,3) -> cop, (4,9) -> ccomp,
                                                  //|  (4,14) -> cc, (4,19) -> conj, (4,20) -> cc, (4,28) -> conj, (9,5) -> cc, (9
                                                  //| ,6) -> nsubj, (9,7) -> aux, (9,8) -> neg, (9,10) -> dobj, (9,13) -> advmod, 
                                                  //| (10,11) -> prep, (11,12) -> pobj, (16,15) -> det, (19,16) -> nsubj, (19,17) 
                                                  //| -> cop, (19,18) -> advmod, (21,25) -> rcmod, (25,22) -> nsubj, (25,24) -> ad
                                                  //| vmod, (25,27) -> dobj, (27,26) -> amod, (28,21) -> nsubj, (28,31) -> advcl, 
                                                  //| (31,29) -> mark, (31,30) -> nsubj, (31,32) -> dobj, (31,35) -> cc, (31,38) -
                                                  //| > conj, (32,33) -> prep, (33,34) -> pobj, (38,36) -> aux, (38,37) -> neg, (3
                                                  //| 8,39) -> prt, (38,40) -> prep, (38,45) -> dobj, (40,42) -> pobj, (42,41) -> 
                                                  //| amod, (45,43) -> amod, (45,44) -> nn)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map()
                                                  //| PartsOfSpeech:	Map(5 -> CC, 10 -> DT,
                                                  //| Output exceeds cutoff limit.
  var state = expertSystem.init(s1)               //> state  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(41, 26, 42, 44, 43, 34, 24, 27, 22, 40, 39, 45, 36, 37, 33,
                                                  //|  12, 25, 38, 30, 35, 32, 29, 11, 15, 21, 31, 10, 13, 8, 7, 6, 5, 1, 17, 16, 
                                                  //| 18, 28, 20, 9, 2, 14, 3, 19, 4)
                                                  //| Children:	List()
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(1 -> My, 2 -> brother, 3 -> 's, 4 -> autistic, 5 -> but, 6 -
                                                  //| > I, 7 -> have, 8 -> n't, 9 -> noticed, 10 -> this, 11 -> with, 12 -> him, 1
                                                  //| 3 -> either, 14 -> but, 15 -> the, 16 -> spectrum, 17 -> is, 18 -> so, 19 ->
                                                  //|  broad, 20 -> and, 21 -> I, 22 -> myself, 24 -> probably, 25 -> have, 26 -> 
                                                  //| mild, 27 -> asperger, 28 -> ?s, 29 -> because, 30 -> I, 31 -> have, 32 -> pr
                                                  //| oblems, 33 -> with, 34 -> communication, 35 -> and, 36 -> do, 37 -> n't, 38 
                                                  //| -> pick, 39 -> up, 40 -> on, 41 -> social, 42 -> reactions, 43 -> /, 44 -> b
                                                  //| ody, 45 -> language)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,12
                                                  //| ), 12 -> (12,13), 13 -> (13,14), 14 -> (14,15), 15 -> (15,16), 16 -> (16,17)
                                                  //| , 17 -> (17,18), 18 -> (18,19), 19 -> (19,20), 20 -> (20,21), 21 -> (21,22),
                                                  //|  22 -> (22,23), 24 -> (23,24), 25 -> (24,25), 26 -> (25,26), 27 -> (26,27), 
                                                  //| 28 -> (27,28), 29 -> (28,29), 30 -> (29,30), 31 -> (30,31), 32 -> (31,32), 3
                                                  //| 3 -> (32,33), 34 -> (33,34), 35 -> (34,35), 36 -> (35,36), 37 -> (36,37), 38
                                                  //|  -> (37,38), 39 -> (38,39), 40 -> (39,40), 41 -> (40,41), 42 -> (41,42), 43 
                                                  //| -> (42,43), 44 -> (43,44), 45 -> (44,45))
                                                  //| Edges:	Map((2,1) -> poss, (4,2) -> nsubj, (4,3) -> cop, (4,9) -> ccomp,
                                                  //|  (4,14) -> cc, (4,19) -> conj, (4,20) -> cc, (4,28) -> conj, (9,5) -> cc, (9
                                                  //| ,6) -> nsubj, (9,7) -> aux, (9,8) -> neg, (9,10) -> dobj, (9,13) -> advmod, 
                                                  //| (10,11) -> prep, (11,12) -> pobj, (16,15) -> det, (19,16) -> nsubj, (19,17) 
                                                  //| -> cop, (19,18) -> advmod, (21,25) -> rcmod, (25,22) -> nsubj, (25,24) -> ad
                                                  //| vmod, (25,27) -> dobj, (27,26) -> amod, (28,21) -> nsubj, (28,31) -> advcl, 
                                                  //| (31,29) -> mark, (31,30) -> nsubj, (31,32) -> dobj, (31,35) -> cc, (31,38) -
                                                  //| > conj, (32,33) -> prep, (33,34) -> pobj, (38,36) -> aux, (38,37) -> neg, (3
                                                  //| 8,39) -> prt, (38,40) -> prep, (38,45) -> dobj, (40,42) -> pobj, (42,41) -> 
                                                  //| amod, (45,43) -> amod, (45,44) -> nn)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map()
                                                  //| PartsOfSpeech:	Map(5 -> CC, 10 -> DT, 42 -> NNS, 24 -> RB, 37 -> RB, 25
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
  s1.positionToAMR                                //> res1: Map[Int,String] = Map(5 -> 0.0, 42 -> 0.1.1.0.2.0.1.2.0, 24 -> 0.1.1, 
                                                  //| 37 -> 0.1.1.0.2.0.1.0, 25 -> 0.1.1.0, 14 -> 0, 20 -> 0.1, 29 -> 0.1.1.0.2, 1
                                                  //|  -> 0.0.0.0.0.0, 38 -> 0.1.1.0.2.0.1, 9 -> 0.0.1, 13 -> 0.0.1.3, 41 -> 0.1.1
                                                  //| .0.2.0.1.2.0.0, 2 -> 0.0.0.0.0.1, 32 -> 0.1.1.0.2.0.0.1, 34 -> 0.1.1.0.2.0.0
                                                  //| .1.0, 45 -> 0.1.1.0.2.0.1.2.1, 44 -> 0.1.1.0.2.0.1.2.1.0, 3 -> 0.0.0.0.0, 35
                                                  //|  -> 0.1.1.0.2.0, 18 -> 0.1.0.1, 16 -> 0.1.0.0, 31 -> 0.1.1.0.2.0.0, 26 -> 0.
                                                  //| 1.1.0.1.2, 8 -> 0.0.1.0, 19 -> 0.1.0, 4 -> 0.0.0)
  s1.amr.get.nodes                                //> res2: Map[String,String] = Map(0.1 -> and, 0.1.1.0.2.0.1.2.0.0 -> society, 0
                                                  //| .0.0 -> autistic, 0.0.0.0.0.1 -> brother, 0.1.1.0.2 -> cause-01, 0.1.1.0.2.0
                                                  //| .0 -> have-03, 0.1.0 -> broad-02, 0.1.1.0.1.1.0 -> "Asperger's", 0.1.1.0.1.0
                                                  //|  -> "Asperger_syndrome", 0.0.1.0 -> -, 0.1.1.0.1 -> disease, 0.1.1.0.2.0.1.2
                                                  //|  -> slash, 0.1.0.1 -> so, 0.0 -> contrast-01, 0.1.1.0.2.0.1.2.1.0 -> body, 0
                                                  //| .1.1.0.2.0.1 -> pick-up-11, 0.0.0.0 -> person, 0.1.1.0.2.0.1.2.1 -> language
                                                  //| , 0.1.1.0.2.0 -> and, 0.1.1.0.1.1 -> name, 0.1.1.0.2.0.1.0 -> -, 0.0.1 -> no
                                                  //| tice-01, 0.0.1.3 -> either, 0.1.0.0 -> spectrum, 0.1.1.0.1.2 -> mild, 0 -> c
                                                  //| ontrast-01, 0.1.1.0.2.0.1.2.0 -> react-01, 0.1.1.0.2.0.0.1 -> problem, 0.0.0
                                                  //| .0.0.0 -> i, 0.0.0.0.0 -> have-rel-role-91, 0.1.1.0 -> have-03, 0.1.1 -> pro
                                                  //| bable, 0.1.1.0.2.0.0.1.0 -> communicate-01)
  s1.amr.get.arcs                                 //> res3: Map[(String, String),String] = Map((0.0,0.0.0) -> ARG1, (0.1.1.0,0.1.1
                                                  //| .0.1) -> ARG1, (0.1.1.0.2.0,0.1.1.0.2.0.1) -> opN, (0.1.1.0.2.0,0.1.1.0.2.0.
                                                  //| 0) -> opN, (0.0.0.0,0.0.0.0.0) -> ARG0-of, (0.0.0,0.0.0.0) -> domain, (0.1,0
                                                  //| .1.0) -> opN, (0.0.1,0.0.0) -> ARG1, (0.0.1,0.0.1.3) -> mod, (0.1.1.0.2.0.1.
                                                  //| 2.1,0.1.1.0.2.0.1.2.1.0) -> mod, (0.1.1.0.2.0.0,0.1.1.0.2.0.0.1) -> ARG1, (0
                                                  //| .1.1.0.1,0.1.1.0.1.2) -> mod, (0.1.0,0.1.0.0) -> ARG1, (0.1.1.0.2.0.0,0.0.0.
                                                  //| 0.0.0) -> ARG0, (0.1.1.0.1.1,0.1.1.0.1.1.0) -> opN, (0.1.1.0,0.1.1.0.2) -> A
                                                  //| RG0-of, (0,0.1) -> ARG2, (0.1.1.0.1,0.1.1.0.1.0) -> wiki, (0.0.0.0.0,0.0.0.0
                                                  //| .0.0) -> ARG1, (0.1.1.0.2.0.0.1,0.1.1.0.2.0.0.1.0) -> topic, (0.1.0,0.1.0.1)
                                                  //|  -> degree, (0.1.1.0.2.0.1.2.0,0.1.1.0.2.0.1.2.0.0) -> mod, (0.1.1.0.1,0.1.1
                                                  //| .0.1.1) -> name, (0.1.1.0.2.0.1,0.1.1.0.2.0.1.2) -> ARG1, (0.1.1.0,0.0.0.0.0
                                                  //| .0) -> ARG0, (0.1.1.0.2,0.1.1.0.2.0) -> ARG1, (0.1.1,0.1.1.0) -> domain, (0.
                                                  //| 1.1.0.2.0.1,0.1.1.0.2.0.1.0) -> polarity, (0.0.1,0.0.0.0.0.0) -> ARG0, (0,0.
                                                  //| 0) -> ARG1, (0.1.1.0.2.0.1.2,0.1.1.0.2.0.1.2.0) -> opN, (0.1.1.0.2.0.1,0.0.0
                                                  //| .0.0.0) -> ARG0, (0.0.1,0.0.1.0) -> polarity, (0.0.0.0.0,0.0.0.0.0.1) -> ARG
                                                  //| 2, (0.1.1.0.2.0.1.2,0.1.1.0.2.0.1.2.1) -> opN, (0.0,0.0.1) -> ARG2, (0.1,0.1
                                                  //| .1) -> opN)
  s1.dependencyTree.nodeLemmas                    //> res4: Map[Int,String] = Map(5 -> but, 10 -> this, 42 -> reaction, 24 -> prob
                                                  //| ably, 37 -> not, 25 -> have, 14 -> but, 20 -> and, 29 -> because, 1 -> my, 6
                                                  //|  -> i, 28 -> be, 38 -> pick, 21 -> i, 33 -> with, 9 -> notice, 13 -> either,
                                                  //|  41 -> social, 2 -> brother, 32 -> problem, 34 -> communication, 45 -> langu
                                                  //| age, 17 -> be, 22 -> myself, 44 -> body, 27 -> asperger, 12 -> he, 7 -> have
                                                  //| , 39 -> up, 3 -> be, 35 -> and, 18 -> so, 16 -> spectrum, 31 -> have, 11 -> 
                                                  //| with, 43 -> /, 40 -> on, 26 -> mild, 8 -> not, 36 -> do, 30 -> i, 19 -> broa
                                                  //| d, 4 -> autistic, 15 -> the)
  ImportConcepts.conceptStrings foreach println   //> contrast-01
                                                  //| Asperger_syndrome
                                                  //| body
                                                  //| name
                                                  //| society
                                                  //| cause-01
                                                  //| problem
                                                  //| slash
                                                  //| so
                                                  //| Asperger's
                                                  //| autistic
                                                  //| probable
                                                  //| mild
                                                  //| i
                                                  //| -
                                                  //| spectrum
                                                  //| brother
                                                  //| broad-02
                                                  //| language
                                                  //| pick-up-11
                                                  //| have-03
                                                  //| notice-01
                                                  //| disease
                                                  //| have-rel-role-91
                                                  //| either
                                                  //| person
                                                  //| react-01
                                                  //| and
                                                  //| communicate-01
  ImportConcepts.conceptsPerLemma foreach println //> (body,Set(3))
                                                  //| (have,Set(21))
                                                  //| (communication,Set(29))
                                                  //| (but,Set(1))
                                                  //| (broad,Set(18))
                                                  //| (problem,Set(7))
                                                  //| (so,Set(9))
                                                  //| (autistic,Set(11))
                                                  //| (because,Set(6))
                                                  //| (reaction,Set(27))
                                                  //| (social,Set(5))
                                                  //| (mild,Set(13))
                                                  //| (probably,Set(12))
                                                  //| (spectrum,Set(16))
                                                  //| (brother,Set(17))
                                                  //| (notice,Set(22))
                                                  //| (my,Set(14))
                                                  //| (language,Set(19))
                                                  //| (either,Set(25))
                                                  //| (pick,Set(20))
                                                  //| (not,Set(15))
                                                  //| (be,Set(24))
                                                  //| (and,Set(28))
  import java.io._
  val file = new FileWriter("C://AMR//Expert_output.txt")
                                                  //> file  : java.io.FileWriter = java.io.FileWriter@b91d8c4
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
                                                  //> res5: String = ( n / contrast-01 
                                                  //| 	:ARG1 ( e / contrast-01 
                                                  //| 		:ARG1 ( d / autistic 
                                                  //| 			:domain ( v2 / person 
                                                  //| 				:ARG0-of ( c / have-rel-role-91 
                                                  //| 					:ARG1 ( a / i )
                                                  //| 					:ARG2 ( b / brother ))))
                                                  //| 		:ARG2 ( i / notice-01 :polarity -
                                                  //| 			:ARG0  a 
                                                  //| 			:ARG1  d 
                                                  //| 			:mod ( m / either )))
                                                  //| 	:ARG2 ( t / and 
                                                  //| 		:op1 ( s / broad-02 
                                                  //| 			:ARG1 ( p / spectrum )
                                                  //| 			:degree ( r / so ))
                                                  //| 		:op2 ( x / probable 
                                                  //| 			:domain ( y / have-03 
                                                  //| 				:ARG0  a 
                                                  //| 				:ARG1 ( t2 / disease :wiki "-"
                                                  //| 					:mod ( z / mild ))
                                                  //| 				:ARG0-of ( c2 / cause-01 
                                                  //| 					:ARG1 ( i2 / and 
                                                  //| 						:op1 ( e2 / have-03 
                                                  //| 							:ARG0  a 
                                                  //| 							:ARG1 ( f2 / problem 
                                                  //| 								:topic ( h2 / co
                                                  //| mmunicate-01 )))
                                                  //| 						:op2 ( l2 / pick-up-11 :polarity
                                                  //|  -
                                                  //| 							:ARG0  a 
                                                  //| 							:ARG1 ( u2 / slash 
                                                  //| 								:op1 ( p2 / reac
                                                  //| t-01 
                                                  //| 									:mod ( o
                                                  //| 2 / society ))
                                                  //| 								:op2 ( s2 / lang
                                                  //| uage 
                                                  //| 									:mod ( r
                                                  //| 2 / body ))))))))))
  AMROutput.convertToString(Smatch.reduceAMR(state.currentGraph.toAMR))
                                                  //> res6: String = ( n / contrast-01 
                                                  //| 	:ARG1 ( e / contrast-01 
                                                  //| 		:ARG1 ( d / autistic 
                                                  //| 			:domain ( v2 / person 
                                                  //| 				:ARG0-of ( c / have-rel-role-91 
                                                  //| 					:ARG1 ( a / i )
                                                  //| 					:ARG2 ( b / brother ))))
                                                  //| 		:ARG2 ( i / notice-01 
                                                  //| 			:ARG0  a 
                                                  //| 			:ARG1  d 
                                                  //| 			:mod ( m / either )))
                                                  //| 	:ARG2 ( t / and 
                                                  //| 		:op1 ( s / broad-02 
                                                  //| 			:ARG1 ( p / spectrum )
                                                  //| 			:degree ( r / so ))
                                                  //| 		:op2 ( x / probable 
                                                  //| 			:domain ( y / have-03 
                                                  //| 				:ARG0  a 
                                                  //| 				:ARG1 ( t2 / disease 
                                                  //| 					:mod ( z / mild ))
                                                  //| 				:ARG0-of ( c2 / cause-01 
                                                  //| 					:ARG1 ( i2 / and 
                                                  //| 						:op1 ( e2 / have-03 
                                                  //| 							:ARG0  a 
                                                  //| 							:ARG1 ( f2 / problem 
                                                  //| 								:topic ( h2 / co
                                                  //| mmunicate-01 )))
                                                  //| 						:op2 ( l2 / pick-up-11 
                                                  //| 							:ARG0  a 
                                                  //| 							:ARG1 ( u2 / slash 
                                                  //| 								:op1 ( p2 / reac
                                                  //| t-01 
                                                  //| 									:mod ( o
                                                  //| 2 / society ))
                                                  //| 								:op2 ( s2 / lang
                                                  //| uage 
                                                  //| 									:mod ( r
                                                  //| 2 / body ))))))))))
}