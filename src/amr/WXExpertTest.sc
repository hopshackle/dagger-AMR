package amr

object WXExpertTest {


  val testData = AMRGraph.importFile("C:\\AMR\\initialTrainingSet.txt")
                                                  //> testData  : IndexedSeq[(String, String)] = Vector((2008-05-14,"(d / date-ent
                                                  //| ity :month 5 :day 14 :year 2008) "), (Estonia (EE); Latvia (LV); Lithuania (
                                                  //| LT); Germany (DE): Italy (IT); Spain (ES); Slovakia (SK); United States (US)
                                                  //| ,"(a / and :op1 (c / country :name (n / name :op1 "Estonia")) :op2 (c2 / cou
                                                  //| ntry :name (n3 / name :op1 "Latvia")) :op3 (c3 / country :name (n5 / name :o
                                                  //| p1 "Lithuania")) :op4 (c4 / country :name (n7 / name :op1 "Germany")) :op5 (
                                                  //| c5 / country :name (n9 / name :op1 "Italy")) :op6 (c6 / country :name (n10 /
                                                  //|  name :op1 "Spain")) :op7 (c7 / country :name (n12 / name :op1 "Slovakia")) 
                                                  //| :op8 (c8 / country :name (n14 / name :op1 "United" :op2 "States"))) "), (Int
                                                  //| ernational; Government; Telecom; technology; science,"(a / and :op1 (i / int
                                                  //| ernational) :op2 (g / government-organization :ARG0-of (g2 / govern-01)) :op
                                                  //| 3 (t / telecommunication) :op4 (t2 / technology) :op5 (s / science)) "), (NA
                                                  //| TO CONSIDERS cyber attacks a threat to military and civilian computer networ
                                                  //| ks after the Estonian Government was struck by cyber attacks in 2007.,"(c / 
                                                  //| consider-02 :ARG0 (m2 / military :name (n / name :op1 "NATO")) :ARG1 (a / at
                                                  //| tack-01 :mod (c2 / cyber)) :ARG2 (t / thing :ARG1-of (t2 / threaten-01 :ARG2
                                                  //|  (a5 / and :op1 (n2 / network :mod (m / military)) :op2 (n4 / network :mod (
                                                  //| c3 / civilian)) :mod (c4 / computer)))) :time (a3 / after :op1 (s / strike-0
                                                  //| 1 :ARG0 (a4 / attack :mod (c6 / cyber)) :ARG1 (g / government-organization :
                                                  //| ARG0-of (g2 / govern-01 :ARG1 (c5 / country :name (n3 / name :op1 "Estonia")
                                                  //| ))) :time (d / date-entity :year 2007)))) "), (NATO allies have signed an ag
                                                  //| reement to fund a research center to bolster defenses against cyber attacks.
                                                  //| ,"(s / sign-01 :ARG0 (p / person :ARG0-of (a / ally-01) :mod (m / military :
                                                  //| name (n / name :op1 "NATO"))) :ARG1 (a2 / agree-01 :ARG0 p :ARG1 (f / fund-0
                                                  //| 1 :ARG0 p :ARG1 (c / center :mod (r / research-01)) 
                                                  //| Output exceeds cutoff limit.
  val s1 = Sentence(testData(1)._1, testData(1)._2)
                                                  //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.3 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [4.4 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [3.6 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [6.3 sec].
                                                  //| s1  : amr.Sentence = Sentence(Estonia (EE); Latvia (LV); Lithuania (LT); Ger
                                                  //| many (DE): Italy (IT); Spain (ES); Slovakia (SK); United States (US),
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> Estonia, 3 -> EE, 6 -> Latvia, 8 -> LV, 11 -
                                                  //| > Lithuania, 13 -> LT, 16 -> Germany, 18 -> DE, 21 -> Italy, 23 -> IT, 26 ->
                                                  //|  Spain, 28 -> ES, 31 -> Slovakia, 33 -> SK, 36 -> United, 37 -> States, 39 -
                                                  //| > US)
                                                  //| SpanMap:	Map(1 -> (1,2), 3 -> (2,3), 6 -> (3,4), 8 -> (4,5), 11 -> (5,6),
                                                  //|  13 -> (6,7), 16 -> (7,8), 18 -> (8,9), 21 -> (9,10), 23 -> (10,11), 26 -> (
                                                  //| 11,12), 28 -> (12,13), 31 -> (13,14), 33 -> (14,15), 36 -> (15,16), 37 -> (1
                                                  //| 6,17), 39 -> (17,18))
                                                  //| Edges:	Map((0,3) -> root, (3,1) -> nn, (3,6) -> dep, (6,8) -> appos, (6
                                                  //| ,11) -> dep, (6,16) -> dep, (6,21) -> dep, (6,26) -> dep, (6,31) -> dep, (6,
                                                  //| 37) -> dep, (11,13) -> appos, (16,18) -> appos, (21,23) -> dep, (26,28) -> a
                                                  //| ppos, (31,33) -> appos, (37,36) -> nn, (37,39) -> appos),Some(AMRGraph(Map(0
                                                  //| .1 -> country, 0.0.0 -> name, 0.6.0.0 -> "Slovakia", 0.6.0 -> name, 0.1.0 ->
                                                  //|  name, 0.7.0 -> name, 0.5 -> country, 0.7.0.0 -> "United", 0.0 -> country, 0
                                                  //| .4 -> country, 0.0.0.0 -> "Estonia", 0.5.0 -> name, 0.2.0 -> name, 0.3.0.0 -
                                                  //| > "Germany", 0.3.0 -> name, 0.1.0.0 -> "Latvia", 0.4.0.0 -> "Italy", 0.5.0.0
                                                  //|  -> "Spain", 0.3 -> country, 0.4.0 -> name, 0 -> and, 0.6 -
                                                  //| Output exceeds cutoff limit.
                                                  
  s1.positionToAMR                                //> res0: Map[Int,String] = Map(0 -> ROOT, 37 -> 0.7.0.1, 1 -> 0.0.0.0, 6 -> 0.1
                                                  //| .0.0, 21 -> 0.4.0.0, 16 -> 0.3.0.0, 31 -> 0.6.0.0, 11 -> 0.2.0.0, 26 -> 0.5.
                                                  //| 0.0, 36 -> 0.7.0.0)
                                                  
 val s2 = Sentence(testData(2)._1, testData(2)._2)//> s2  : amr.Sentence = Sentence(International; Government; Telecom; technology
                                                  //| ; science,
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> International, 3 -> Government, 5 -> Telecom
                                                  //| , 7 -> technology, 9 -> science)
                                                  //| SpanMap:	Map(1 -> (1,2), 3 -> (2,3), 5 -> (3,4), 7 -> (4,5), 9 -> (5,6))
                                                  //| Edges:	Map((0,1) -> root, (1,3) -> dep, (3,5) -> dep, (3,7) -> dep, (7,
                                                  //| 9) -> dep),Some(AMRGraph(Map(0.1 -> government-organization, 0.1.0 -> govern
                                                  //| -01, 0.0 -> international, 0.4 -> science, 0.3 -> technology, 0 -> and, 0.2 
                                                  //| -> telecommunication, ROOT -> ROOT),Map(0.1 -> (2,3), 0.0 -> (1,2), 0.4 -> (
                                                  //| 5,6), 0.3 -> (4,5), 0.2 -> (3,4)),Map((ROOT,0) -> ROOT, (0,0.3) -> opN, (0.1
                                                  //| ,0.1.0) -> ARG0-of, (0,0.2) -> opN, (0,0.1) -> opN, (0,0.4) -> opN, (0,0.0) 
                                                  //| -> opN))),Map(0 -> ROOT, 5 -> 0.2, 1 -> 0.0, 9 -> 0.4, 7 -> 0.3, 3 -> 0.1))
                                                  //| 
  s2.positionToAMR                                //> res1: Map[Int,String] = Map(0 -> ROOT, 5 -> 0.2, 1 -> 0.0, 9 -> 0.4, 7 -> 0.
                                                  //| 3, 3 -> 0.1)
  val expert = new WangXueExpertBasic             //> expert  : amr.WangXueExpertBasic = amr.WangXueExpertBasic@563f38c4
  val expertSystem = new WangXueTransitionSystem  //> WARNING: Found duplicate match for concept attack-01
                                                  //| WARNING: Found duplicate match for concept cyber
                                                  //| WARNING: Found duplicate match for concept attack-01
                                                  //| WARNING: Found duplicate match for concept cyber
                                                  //| expertSystem  : amr.WangXueTransitionSystem = amr.WangXueTransitionSystem@75
                                                  //| 437611
  val startState = expertSystem.init(s2)          //> startState  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(9, 5, 7, 3, 1, 0)
                                                  //| Children:	List()
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> International, 3 -> Government, 5 -> Telecom
                                                  //| , 7 -> technology, 9 -> science)
                                                  //| SpanMap:	Map(1 -> (1,2), 3 -> (2,3), 5 -> (3,4), 7 -> (4,5), 9 -> (5,6))
                                                  //| Edges:	Map((0,1) -> root, (1,3) -> dep, (3,5) -> dep, (3,7) -> dep, (7,
                                                  //| 9) -> dep)
                                                  //| Mappings:	0 -> ROOT
                                                  //| 5 -> 0.2
                                                  //| 1 -> 0.0
                                                  //| 9 -> 0.4
                                                  //| 7 -> 0.3
                                                  //| 3 -> 0.1
                                                  //| 
  s2.amr                                          //> res2: Option[amr.AMRGraph] = Some(AMRGraph(Map(0.1 -> government-organizatio
                                                  //| n, 0.1.0 -> govern-01, 0.0 -> international, 0.4 -> science, 0.3 -> technolo
                                                  //| gy, 0 -> and, 0.2 -> telecommunication, ROOT -> ROOT),Map(0.1 -> (2,3), 0.0 
                                                  //| -> (1,2), 0.4 -> (5,6), 0.3 -> (4,5), 0.2 -> (3,4)),Map((ROOT,0) -> ROOT, (0
                                                  //| ,0.3) -> opN, (0.1,0.1.0) -> ARG0-of, (0,0.2) -> opN, (0,0.1) -> opN, (0,0.4
                                                  //| ) -> opN, (0,0.0) -> opN)))
  var nextAction = expert.chooseTransition(s2, startState)
                                                  //> nextAction  : amr.WangXueAction = NextNode: 16 -> science
  var nextState = nextAction(startState)          //> nextState  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(5, 7, 3, 1, 0)
                                                  //| Children:	List()
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> International, 3 -> Government, 5 -> Telecom
                                                  //| , 7 -> technology, 9 -> science)
                                                  //| SpanMap:	Map(1 -> (1,2), 3 -> (2,3), 5 -> (3,4), 7 -> (4,5), 9 -> (5,6))
                                                  //| Edges:	Map((0,1) -> root, (1,3) -> dep, (3,5) -> dep, (3,7) -> dep, (7,
                                                  //| 9) -> dep)
                                                  //| Mappings:	0 -> ROOT
                                                  //| 5 -> 0.2
                                                  //| 1 -> 0.0
                                                  //| 9 -> 0.4
                                                  //| 7 -> 0.3
                                                  //| 3 -> 0.1
                                                  //| 
val output = RunDagger.sampleTrajectory(s2, "", new WangXueExpertBasic)
                                                  //> output  : amr.Sentence = Sentence(International; Government; Telecom; techno
                                                  //| logy; science,
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> international, 3 -> government-organization,
                                                  //|  5 -> telecommunication, 7 -> technology, 9 -> science)
                                                  //| SpanMap:	Map(1 -> (1,2), 3 -> (2,3), 5 -> (3,4), 7 -> (4,5), 9 -> (5,6))
                                                  //| Edges:	Map((0,1) -> UNKNOWN, (1,3) -> UNKNOWN, (3,5) -> UNKNOWN, (3,7) 
                                                  //| -> UNKNOWN, (7,9) -> UNKNOWN),Some(AMRGraph(Map(9 -> science, 5 -> telecommu
                                                  //| nication, 1 -> international, 0 -> ROOT, 7 -> technology, 3 -> government-or
                                                  //| ganization),Map(9 -> (5,6), 5 -> (3,4), 1 -> (1,2), 7 -> (4,5), 3 -> (2,3)),
                                                  //| Map((3,5) -> UNKNOWN, (1,3) -> UNKNOWN, (3,7) -> UNKNOWN, (0,1) -> UNKNOWN, 
                                                  //| (7,9) -> UNKNOWN))),Map(0 -> ROOT, 5 -> 5, 1 -> 1, 9 -> 9, 7 -> 7, 3 -> 3))
                                                  //| 
val loss = new WangXueLossFunction                //> loss  : amr.WangXueLossFunction = amr.WangXueLossFunction@350aac89
loss(s2, s2, null)                                //> res3: Double = 0.0
loss(s2, output, null)                            //> res4: Double = 0.5384615384615385
println(output.amr.get)                           //> AMRGraph(Map(9 -> science, 5 -> telecommunication, 1 -> international, 0 -> 
                                                  //| ROOT, 7 -> technology, 3 -> government-organization),Map(9 -> (5,6), 5 -> (3
                                                  //| ,4), 1 -> (1,2), 7 -> (4,5), 3 -> (2,3)),Map((3,5) -> UNKNOWN, (1,3) -> UNKN
                                                  //| OWN, (3,7) -> UNKNOWN, (0,1) -> UNKNOWN, (7,9) -> UNKNOWN))
s2.amr.get.toOutputFormat                         //> res5: String = "# ::AMRGraph
                                                  //| # ::node	0.1	government-organization
                                                  //| # ::node	0.1.0	govern-01
                                                  //| # ::node	0.0	international
                                                  //| # ::node	0.4	science
                                                  //| # ::node	0.3	technology
                                                  //| # ::node	0	and
                                                  //| # ::node	0.2	telecommunication
                                                  //| # ::node	ROOT	ROOT
                                                  //| # ::edge	ROOT	0	ROOT
                                                  //| # ::edge	0	0.3	opN
                                                  //| # ::edge	0.1	0.1.0	ARG0-of
                                                  //| # ::edge	0	0.2	opN
                                                  //| # ::edge	0	0.1	opN
                                                  //| # ::edge	0	0.4	opN
                                                  //| # ::edge	0	0.0	opN
                                                  //| "
 val outputAdv = RunDagger.sampleTrajectory(s2, "C:\\AMR\\WangXueExpert_output.txt", new WangXueExpert)
                                                  //> outputAdv  : amr.Sentence = Sentence(International; Government; Telecom; tec
                                                  //| hnology; science,
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> international, 3 -> government-organization,
                                                  //|  5 -> telecommunication, 7 -> technology, 9 -> science, 10 -> and)
                                                  //| SpanMap:	Map(1 -> (1,2), 3 -> (2,3), 5 -> (3,4), 7 -> (4,5), 9 -> (5,6), 
                                                  //| 10 -> (5,6))
                                                  //| Edges:	Map((0,1) -> UNKNOWN, (0,10) -> ROOT, (1,3) -> UNKNOWN, (3,5) ->
                                                  //|  UNKNOWN, (3,7) -> UNKNOWN, (10,9) -> opN),Some(AMRGraph(Map(9 -> science, 5
                                                  //|  -> telecommunication, 10 -> and, 1 -> international, 0 -> ROOT, 7 -> techno
                                                  //| logy, 3 -> government-organization),Map(9 -> (5,6), 5 -> (3,4), 10 -> (5,6),
                                                  //|  1 -> (1,2), 7 -> (4,5), 3 -> (2,3)),Map((3,5) -> UNKNOWN, (1,3) -> UNKNOWN,
                                                  //|  (3,7) -> UNKNOWN, (0,1) -> UNKNOWN, (10,9) -> opN, (0,10) -> ROOT))),Map(0 
                                                  //| -> ROOT, 5 -> 5, 10 -> 10, 1 -> 1, 9 -> 9, 7 -> 7, 3 -> 3))
outputAdv                                         //> res6: amr.Sentence = Sentence(International; Government; Telecom; technology
                                                  //| ; science,
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> international, 3 -> government-organization,
                                                  //|  5 -> telecommunication, 7 -> technology, 9 -> science, 10 -> and)
                                                  //| SpanMap:	Map(1 -> (1,2), 3 -> (2,3), 5 -> (3,4), 7 -> (4,5), 9 -> (5,6), 
                                                  //| 10 -> (5,6))
                                                  //| Edges:	Map((0,1) -> UNKNOWN, (0,10) -> ROOT, (1,3) -> UNKNOWN, (3,5) ->
                                                  //|  UNKNOWN, (3,7) -> UNKNOWN, (10,9) -> opN),Some(AMRGraph(Map(9 -> science, 5
                                                  //|  -> telecommunication, 10 -> and, 1 -> international, 0 -> ROOT, 7 -> techno
                                                  //| logy, 3 -> government-organization),Map(9 -> (5,6), 5 -> (3,4), 10 -> (5,6),
                                                  //|  1 -> (1,2), 7 -> (4,5), 3 -> (2,3)),Map((3,5) -> UNKNOWN, (1,3) -> UNKNOWN,
                                                  //|  (3,7) -> UNKNOWN, (0,1) -> UNKNOWN, (10,9) -> opN, (0,10) -> ROOT))),Map(0 
                                                  //| -> ROOT, 5 -> 5, 10 -> 10, 1 -> 1, 9 -> 9, 7 -> 7, 3 -> 3))
}