package amr

object WXExpertTest {

  val expert = new WangXueExpert                  //> expert  : amr.WangXueExpert = amr.WangXueExpert@ba8a1dc
  val expertSystem = new WangXueTransitionSystem  //> expertSystem  : amr.WangXueTransitionSystem = amr.WangXueTransitionSystem@7e
                                                  //| 0ea639
  val testData = AMRGraph.importFile("C:\\AMR\\initialTrainingSetAmended.txt")
                                                  //> testData  : IndexedSeq[(String, String)] = Vector((2008-05-14,"(d / date-ent
                                                  //| ity :month 5 :day 14 :year 2008) "), (Estonia (EE); Latvia (LV); Lithuania (
                                                  //| LT); United States (US),"(a / and :op1 (c / country :name (n / name :op1 "Es
                                                  //| tonia")) :op2 (c2 / country :name (n3 / name :op1 "Latvia")) :op3 (c3 / coun
                                                  //| try :name (n5 / name :op1 "Lithuania")) :op4 (c8 / country :name (n14 / name
                                                  //|  :op1 "United" :op2 "States"))) "), (International; Government; Telecom; tec
                                                  //| hnology; science,"(a / and :op1 (i / international) :op2 (g / government-org
                                                  //| anization :ARG0-of (g2 / govern-01)) :op3 (t / telecommunication) :op4 (t2 /
                                                  //|  technology) :op5 (s / science)) "), (NATO CONSIDERS cyber attacks a threat 
                                                  //| to military and civilian computer networks after the Estonian Government was
                                                  //|  struck by cyber attacks in 2007.,"(c / consider-02 :ARG0 (m2 / military :na
                                                  //| me (n / name :op1 "NATO")) :ARG1 (a / attack-01 :mod (c2 / cyber)) :ARG2 (t 
                                                  //| / thing :ARG1-of (t2 / threaten-01 :ARG2 (a5 / and :op1 (n2 / network :mod (
                                                  //| m / military)) :op2 (n4 / network :mod (c3 / civilian)) :mod (c4 / computer)
                                                  //| ))) :time (a3 / after :op1 (s / strike-01 :ARG0 (a4 / attack :mod (c6 / cybe
                                                  //| r)) :ARG1 (g / government-organization :ARG0-of (g2 / govern-01 :ARG1 (c5 / 
                                                  //| country :name (n3 / name :op1 "Estonia")))) :time (d / date-entity :year 200
                                                  //| 7)))) "), (NATO allies have signed an agreement to fund a research center to
                                                  //|  bolster defenses against cyber attacks.,"(s / sign-01 :ARG0 (p / person :AR
                                                  //| G0-of (a / ally-01) :mod (m / military :name (n / name :op1 "NATO"))) :ARG1 
                                                  //| (a2 / agree-01 :ARG0 p :ARG1 (f / fund-01 :ARG0 p :ARG1 (c / center :mod (r 
                                                  //| / research-01)) :purpose (b / bolster-01 :ARG0 p :ARG1 (d / defend-01 :prep-
                                                  //| against (a3 / attack :mod (c2 / cyber))))))) "), (On 14 May 2008 7 NATO alli
                                                  //| es signed an agreement to fund a research center.,"(s / sign-01 :ARG0 (p / p
                                                  //| erson :quant 7 :ARG0-of (a / ally-01) :mod (m / military :name (n / name :op
                                                  //| 1 "NATO"))) :ARG1 (a2 / agree-01 :ARG1 (f / fund-01 :ARG0 p :ARG1 (c / cente
                                                  //| r :mod (r / research-01)))) :time (d / date-entity :month 5 :day 14 :year 20
                                                  //| 08)) "), (The center will bolster NATO's defenses against cyber attacks.,"(b
                                                  //|  / bolster-01 :ARG0 (c / center) :ARG1 (d / defend-01 :ARG0 (m / military :n
                                                  //| ame (n / name :op1 "NATO")) :ARG1 m :prep-against (a / attack-01 :mod (c2 / 
                                                  //| cyber)))) "), (The center will be operational in August 2008.,"(o 
                                                  //| Output exceeds cutoff limit.
  val s1 = Sentence(testData(1)._1, testData(1)._2)
                                                  //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.2 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [5.4 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [2.0 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [4.7 sec].
                                                  //| s1  : amr.Sentence = Sentence(Estonia (EE); Latvia (LV); Lithuania (LT); Uni
                                                  //| ted States (US),
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> Estonia, 3 -> EE, 6 -> Latvia, 8 -> LV, 11 -
                                                  //| > Lithuania, 13 -> LT, 16 -> United, 17 -> States, 19 -> US)
                                                  //| SpanMap:	Map(1 -> (1,2), 3 -> (2,3), 6 -> (3,4), 8 -> (4,5), 11 -> (5,6),
                                                  //|  13 -> (6,7), 16 -> (7,8), 17 -> (8,9), 19 -> (9,10))
                                                  //| Edges:	Map((0,1) -> root, (1,3) -> appos, (1,6) -> dep, (1,11) -> dep, 
                                                  //| (6,8) -> appos, (11,13) -> appos, (11,17) -> dep, (17,16) -> nn, (17,19) -> 
                                                  //| appos)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set(),Some(AMRGraph(Map(0.1 -> country, 0.0.0 -> name, 0
                                                  //| .1.0 -> name, 0.0 -> country, 0.0.0.0 -> "Estonia", 0.2.0 -> name, 0.3.0.0 -
                                                  //| > "United", 0.3.0 -> name, 0.1.0.0 -> "Latvia", 0.3 -> country, 0 -> and, 0.
                                                  //| 3.0.1 -> "States", 0.2 -> country, ROOT -> ROOT, 0.2.0.0 -> "Lithuania"),Map
                                                  //| (0.1 -> (3,4), 0.0.0 -> (1,2), 0.1.0 -> (3,4), 0.0 -> (1,2), 0.0.0.0 -> (1,2
                                                  //| ), 0.2.0 -> (5,6), 0.3.0.0 -> (7,9), 0.3.0 -> (7,9), 0.1.0.0 -> (3,4), 0.3 -
                                                  //| > (7,9), 0.3.0.1 -> (7,9), 0.2 -> (5,6), 0.2.0.0 -> (5,6)),Map((0.0,0.0.0) -
                                                  //| > name, (0.3.0,0.3.0.0) -> opN, (ROOT,0) -> ROOT, (0,0.3) -> opN, (0.0.0,0.0
                                                  //| .0.0) -> opN, (0.1,0.1.0) -> name, (0.3.0,0.3.0.1) -> opN, (0.1.0,0.1.0.0) -
                                                  //| > opN, (0,0.2) -> opN, (0,0.1) -> opN, (0.2,0.2.0) -> name, (0.3,0.3.0) -> n
                                                  //| ame, (0,0.0) -> opN, (0.2.0,0.2.0.0) -> opN))),Map(0 -> ROOT, 1 -> 0.0.0.0, 
                                                  //| 6 -> 0.1.0.0, 17 -> 0.3.0.1, 16 -> 0.3.0.0, 11 -> 0.2.0.0))
  var state = expertSystem.init(s1)               //> state  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(19, 16, 8, 17, 13, 6, 11, 3, 1, 0)
                                                  //| Children:	List()
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> Estonia, 3 -> EE, 6 -> Latvia, 8 -> LV, 11 -
                                                  //| > Lithuania, 13 -> LT, 16 -> United, 17 -> States, 19 -> US)
                                                  //| SpanMap:	Map(1 -> (1,2), 3 -> (2,3), 6 -> (3,4), 8 -> (4,5), 11 -> (5,6),
                                                  //|  13 -> (6,7), 16 -> (7,8), 17 -> (8,9), 19 -> (9,10))
                                                  //| Edges:	Map((0,1) -> root, (1,3) -> appos, (1,6) -> dep, (1,11) -> dep, 
                                                  //| (6,8) -> appos, (11,13) -> appos, (11,17) -> dep, (17,16) -> nn, (17,19) -> 
                                                  //| appos)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| Mappings:	0 -> ROOT
                                                  //| 1 -> 0.0.0.0
                                                  //| 6 -> 0.1.0.0
                                                  //| 17 -> 0.3.0.1
                                                  //| 16 -> 0.3.0.0
                                                  //| 11 -> 0.2.0.0
                                                  //| 

  val action = new Array[WangXueAction](26)       //> action  : Array[amr.WangXueAction] = Array(null, null, null, null, null, nul
                                                  //| l, null, null, null, null, null, null, null, null, null, null, null, null, n
                                                  //| ull, null, null, null, null, null, null, null)
  val stateHistory = new Array[WangXueTransitionState](26)
                                                  //> stateHistory  : Array[amr.WangXueTransitionState] = Array(null, null, null, 
                                                  //| null, null, null, null, null, null, null, null, null, null, null, null, null
                                                  //| , null, null, null, null, null, null, null, null, null, null)
  s1.positionToAMR                                //> res0: Map[Int,String] = Map(0 -> ROOT, 1 -> 0.0.0.0, 6 -> 0.1.0.0, 17 -> 0.3
                                                  //| .0.1, 16 -> 0.3.0.0, 11 -> 0.2.0.0)
  s1.amr.get.arcs                                 //> res1: Map[(String, String),String] = Map((0.0,0.0.0) -> name, (0.3.0,0.3.0.0
                                                  //| ) -> opN, (ROOT,0) -> ROOT, (0,0.3) -> opN, (0.0.0,0.0.0.0) -> opN, (0.1,0.1
                                                  //| .0) -> name, (0.3.0,0.3.0.1) -> opN, (0.1.0,0.1.0.0) -> opN, (0,0.2) -> opN,
                                                  //|  (0,0.1) -> opN, (0.2,0.2.0) -> name, (0.3,0.3.0) -> name, (0,0.0) -> opN, (
                                                  //| 0.2.0,0.2.0.0) -> opN)
  for (i <- 0 to 25) {
    stateHistory(i) = state
    action(i) = expert.chooseTransition(s1, state)
    state = action(i)(state)
  }                                               //> WARNING: Found duplicate match for concept attack-01
                                                  //| WARNING: Found duplicate match for concept cyber
                                                  //| WARNING: Found duplicate match for concept attack-01
                                                  //| WARNING: Found duplicate match for concept cyber
  (action zip stateHistory) foreach {
    case (a, s) =>
      println(s.nodesToProcess.head + " -> " + (
        s.childrenToProcess match { case Nil => ""; case _ => s.childrenToProcess.head })); println(a)
  }                                               //> 19 -> 
                                                  //| DeleteNode
                                                  //| 16 -> 
                                                  //| InsertNode: name (Ref: 0.3.0)
                                                  //| 16 -> 
                                                  //| NextNode: 0 -> UNKNOWN
                                                  //| 18 -> 16
                                                  //| ParentFlip
                                                  //| 18 -> 17
                                                  //| InsertNode: country (Ref: 0.3)
                                                  //| 18 -> 17
                                                  //| NextEdge: 9 -> opN
                                                  //| 18 -> 16
                                                  //| NextEdge: 9 -> opN
                                                  //| 18 -> 
                                                  //| NextNode: 3 -> name
                                                  //| 17 -> 
                                                  //| NextNode: 0 -> UNKNOWN
                                                  //| 8 -> 
                                                  //| DeleteNode
                                                  //| 19 -> 18
                                                  //| InsertNode: and (Ref: 0)
                                                  //| 19 -> 18
                                                  //| NextEdge: 1 -> name
                                                  //| 19 -> 
                                                  //| NextNode: 13 -> country
                                                  //| 13 -> 
                                                  //| DeleteNode
                                                  //| 20 -> 19
                                                  //| NextEdge: 9 -> opN
                                                  //| 20 -> 
                                                  //| NextNode: 33 -> and
                                                  //| 6 -> 
                                                  //| InsertNode: name (Ref: 0.1.0)
                                                  //| 6 -> 
                                                  //| NextNode: 30 -> "Latvia"
                                                  //| 21 -> 6
                                                  //| InsertNode: country (Ref: 0.1)
                                                  //| 21 -> 6
                                                  //| NextEdge: 9 -> opN
                                                  //| 21 -> 
                                                  //| NextNode: 3 -> name
                                                  //| 11 -> 20
                                                  //| InsertNode: name (Ref: 0.2.0)
                                                  //| 11 -> 20
                                                  //| Reattach(0)
                                                  //| 11 -> 
                                                  //| NextNode: 18 -> "Lithuania"
                                                  //| 3 -> 
                                                  //| DeleteNode
                                                  //| 22 -> 21
                                                  //| NextEdge: 1 -> name

  s1.positionToAMR                                //> res2: Map[Int,String] = Map(0 -> ROOT, 1 -> 0.0.0.0, 6 -> 0.1.0.0, 17 -> 0.3
                                                  //| .0.1, 16 -> 0.3.0.0, 11 -> 0.2.0.0)

  val s2 = Sentence(testData(2)._1, testData(2)._2)
                                                  //> s2  : amr.Sentence = Sentence(International; Government; Telecom; technology
                                                  //| ; science,
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> International, 3 -> Government, 5 -> Telecom
                                                  //| , 7 -> technology, 9 -> science)
                                                  //| SpanMap:	Map(1 -> (1,2), 3 -> (2,3), 5 -> (3,4), 7 -> (4,5), 9 -> (5,6))
                                                  //| Edges:	Map((0,1) -> root, (1,3) -> dep, (3,5) -> dep, (3,7) -> dep, (7,
                                                  //| 9) -> dep)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set(),Some(AMRGraph(Map(0.1 -> government-organization, 
                                                  //| 0.1.0 -> govern-01, 0.0 -> international, 0.4 -> science, 0.3 -> technology,
                                                  //|  0 -> and, 0.2 -> telecommunication, ROOT -> ROOT),Map(0.1 -> (2,3), 0.0 -> 
                                                  //| (1,2), 0.4 -> (5,6), 0.3 -> (4,5), 0.2 -> (3,4)),Map((ROOT,0) -> ROOT, (0,0.
                                                  //| 3) -> opN, (0.1,0.1.0) -> ARG0-of, (0,0.2) -> opN, (0,0.1) -> opN, (0,0.4) -
                                                  //| > opN, (0,0.0) -> opN))),Map(0 -> ROOT, 5 -> 0.2, 1 -> 0.0, 9 -> 0.4, 7 -> 0
                                                  //| .3, 3 -> 0.1))
  s2.positionToAMR                                //> res3: Map[Int,String] = Map(0 -> ROOT, 5 -> 0.2, 1 -> 0.0, 9 -> 0.4, 7 -> 0.
                                                  //| 3, 3 -> 0.1)
  val startState = expertSystem.init(s2)          //> startState  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(9, 5, 7, 3, 1, 0)
                                                  //| Children:	List()
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> International, 3 -> Government, 5 -> Telecom
                                                  //| , 7 -> technology, 9 -> science)
                                                  //| SpanMap:	Map(1 -> (1,2), 3 -> (2,3), 5 -> (3,4), 7 -> (4,5), 9 -> (5,6))
                                                  //| Edges:	Map((0,1) -> root, (1,3) -> dep, (3,5) -> dep, (3,7) -> dep, (7,
                                                  //| 9) -> dep)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| Mappings:	0 -> ROOT
                                                  //| 5 -> 0.2
                                                  //| 1 -> 0.0
                                                  //| 9 -> 0.4
                                                  //| 7 -> 0.3
                                                  //| 3 -> 0.1
                                                  //| 
  s2.amr                                          //> res4: Option[amr.AMRGraph] = Some(AMRGraph(Map(0.1 -> government-organizatio
                                                  //| n, 0.1.0 -> govern-01, 0.0 -> international, 0.4 -> science, 0.3 -> technolo
                                                  //| gy, 0 -> and, 0.2 -> telecommunication, ROOT -> ROOT),Map(0.1 -> (2,3), 0.0 
                                                  //| -> (1,2), 0.4 -> (5,6), 0.3 -> (4,5), 0.2 -> (3,4)),Map((ROOT,0) -> ROOT, (0
                                                  //| ,0.3) -> opN, (0.1,0.1.0) -> ARG0-of, (0,0.2) -> opN, (0,0.1) -> opN, (0,0.4
                                                  //| ) -> opN, (0,0.0) -> opN)))
  var nextAction = expert.chooseTransition(s2, startState)
                                                  //> nextAction  : amr.WangXueAction = InsertNode: and (Ref: 0)
  var nextState = nextAction(startState)          //> nextState  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(9, 10, 5, 7, 3, 1, 0)
                                                  //| Children:	List()
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> International, 3 -> Government, 5 -> Telecom
                                                  //| , 7 -> technology, 9 -> science, 10 -> and)
                                                  //| SpanMap:	Map(1 -> (1,2), 3 -> (2,3), 5 -> (3,4), 7 -> (4,5), 9 -> (5,6), 
                                                  //| 10 -> (5,6))
                                                  //| Edges:	Map((0,1) -> root, (1,3) -> dep, (3,5) -> dep, (3,7) -> dep, (7,
                                                  //| 10) -> dep, (10,9) -> and#)
                                                  //| InsertedNodes:	Map(10 -> 0)
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| Mappings:	0 -> ROOT
                                                  //| 5 -> 0.2
                                                  //| 10 -> 0
                                                  //| 1 -> 0.0
                                                  //| 9 -> 0.4
                                                  //| 7 -> 0.3
                                                  //| 3 -> 0.1
                                                  //| 
  val output = RunDagger.sampleTrajectory(s2, "", new WangXueExpertBasic)
                                                  //> output  : amr.Sentence = Sentence(International; Government; Telecom; techn
                                                  //| ology; science,
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> international, 3 -> government-organization,
                                                  //|  5 -> telecommunication, 7 -> technology, 9 -> science)
                                                  //| SpanMap:	Map(1 -> (1,2), 3 -> (2,3), 5 -> (3,4), 7 -> (4,5), 9 -> (5,6))
                                                  //| Edges:	Map((0,1) -> UNKNOWN, (1,3) -> UNKNOWN, (3,5) -> UNKNOWN, (3,7) 
                                                  //| -> UNKNOWN, (7,9) -> UNKNOWN)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set(),Some(AMRGraph(Map(9 -> science, 5 -> telecommunica
                                                  //| tion, 1 -> international, 0 -> ROOT, 7 -> technology, 3 -> government-organ
                                                  //| ization),Map(9 -> (5,6), 5 -> (3,4), 1 -> (1,2), 7 -> (4,5), 3 -> (2,3)),Ma
                                                  //| p((3,5) -> UNKNOWN, (1,3) -> UNKNOWN, (3,7) -> UNKNOWN, (0,1) -> UNKNOWN, (
                                                  //| 7,9) -> UNKNOWN))),Map(0 -> ROOT, 5 -> 5, 1 -> 1, 9 -> 9, 7 -> 7, 3 -> 3))
                                                  //| 
  val loss = new WangXueLossFunction              //> loss  : amr.WangXueLossFunction = amr.WangXueLossFunction@7b36aa0c
  loss(s2, s2, null)                              //> res5: Double = 0.0
  loss(s2, output, null)                          //> res6: Double = 0.5384615384615385
  println(output.amr.get)                         //> AMRGraph(Map(9 -> science, 5 -> telecommunication, 1 -> international, 0 ->
                                                  //|  ROOT, 7 -> technology, 3 -> government-organization),Map(9 -> (5,6), 5 -> 
                                                  //| (3,4), 1 -> (1,2), 7 -> (4,5), 3 -> (2,3)),Map((3,5) -> UNKNOWN, (1,3) -> U
                                                  //| NKNOWN, (3,7) -> UNKNOWN, (0,1) -> UNKNOWN, (7,9) -> UNKNOWN))
  s2.amr.get.toOutputFormat                       //> res7: String = "# ::AMRGraph
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
  val outputAdv2 = RunDagger.sampleTrajectory(s2, "C:\\AMR\\WangXueExpert_output.txt", new WangXueExpert)
                                                  //> outputAdv2  : amr.Sentence = Sentence(International; Government; Telecom; t
                                                  //| echnology; science,
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> international, 3 -> government-organization,
                                                  //|  5 -> telecommunication, 7 -> technology, 9 -> science, 10 -> and)
                                                  //| SpanMap:	Map(1 -> (1,2), 3 -> (2,3), 5 -> (3,4), 7 -> (4,5), 9 -> (5,6), 
                                                  //| 10 -> (5,6))
                                                  //| Edges:	Map((0,10) -> ROOT, (3,5) -> UNKNOWN, (10,1) -> opN, (10,3) -> o
                                                  //| pN, (10,7) -> opN, (10,9) -> opN)
                                                  //| InsertedNodes:	Map(10 -> 0)
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set((7,10), (3,10), (1,10)),Some(AMRGraph(Map(9 -> scien
                                                  //| ce, 5 -> telecommunication, 10 -> and, 1 -> international, 0 -> ROOT, 7 -> 
                                                  //| technology, 3 -> government-organization),Map(9 -> (5,6), 5 -> (3,4), 10 ->
                                                  //|  (5,6), 1 -> (1,2), 7 -> (4,5), 3 -> (2,3)),Map((3,5) -> UNKNOWN, (10,9) ->
                                                  //|  opN, (10,7) -> opN, (10,3) -> opN, (10,1) -> opN, (0,10) -> ROOT))),Map(0 
                                                  //| -> ROOT, 5 -> 5, 10 -> 10, 1 -> 1, 9 -> 9, 7 -> 7, 3 -> 3))
  outputAdv2                                      //> res8: amr.Sentence = Sentence(International; Government; Telecom; technolog
                                                  //| y; science,
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> international, 3 -> government-organization,
                                                  //|  5 -> telecommunication, 7 -> technology, 9 -> science, 10 -> and)
                                                  //| SpanMap:	Map(1 -> (1,2), 3 -> (2,3), 5 -> (3,4), 7 -> (4,5), 9 -> (5,6), 
                                                  //| 10 -> (5,6))
                                                  //| Edges:	Map((0,10) -> ROOT, (3,5) -> UNKNOWN, (10,1) -> opN, (10,3) -> o
                                                  //| pN, (10,7) -> opN, (10,9) -> opN)
                                                  //| InsertedNodes:	Map(10 -> 0)
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set((7,10), (3,10), (1,10)),Some(AMRGraph(Map(9 -> scien
                                                  //| ce, 5 -> telecommunication, 10 -> and, 1 -> international, 0 -> ROOT, 7 -> 
                                                  //| technology, 3 -> government-organization),Map(9 -> (5,6), 5 -> (3,4), 10 ->
                                                  //|  (5,6), 1 -> (1,2), 7 -> (4,5), 3 -> (2,3)),Map((3,5) -> UNKNOWN, (10,9) ->
                                                  //|  opN, (10,7) -> opN, (10,3) -> opN, (10,1) -> opN, (0,10) -> ROOT))),Map(0 
                                                  //| -> ROOT, 5 -> 5, 10 -> 10, 1 -> 1, 9 -> 9, 7 -> 7, 3 -> 3))
  val outputAdv1 = RunDagger.sampleTrajectory(s1, "C:\\AMR\\WangXueExpert_output.txt", new WangXueExpert)
                                                  //> outputAdv1  : amr.Sentence = Sentence(Estonia (EE); Latvia (LV); Lithuania 
                                                  //| (LT); United States (US),
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> "Estonia", 6 -> "Latvia", 11 -> "Lithuania",
                                                  //|  16 -> United, 17 -> States, 18 -> name, 19 -> country, 20 -> and, 21 -> na
                                                  //| me, 22 -> country, 23 -> name, 24 -> country, 25 -> name, 26 -> country)
                                                  //| SpanMap:	Map(1 -> (1,2), 6 -> (3,4), 11 -> (5,6), 16 -> (7,8), 17 -> (8,9
                                                  //| ), 18 -> (7,8), 19 -> (7,8), 20 -> (7,8), 21 -> (3,4), 22 -> (3,4), 23 -> (
                                                  //| 5,6), 24 -> (5,6), 25 -> (1,2), 26 -> (1,2))
                                                  //| Edges:	Map((0,20) -> ROOT, (0,26) -> UNKNOWN, (1,22) -> UNKNOWN, (1,24)
                                                  //|  -> UNKNOWN, (18,16) -> opN, (18,17) -> opN, (19,18) -> name, (20,19) -> op
                                                  //| N, (21,6) -> opN, (22,21) -> name, (23,11) -> opN, (24,23) -> name, (25,1) 
                                                  //| -> opN, (26,25) -> name)
                                                  //| InsertedNodes:	Map(24 -> 0.2, 25 -> 0.0.0, 20 -> 0, 21 -> 0.1.0, 22 -> 
                                                  //| 0.1, 18 -> 0.3.0, 26 -> 0.0, 23 -> 0.2.0, 19 -> 0.3)
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set((17,18)),Some(AMRGraph(Map(19 -> country, 23 -> name
                                                  //| , 11 -> "Lithuania", 22 -> country, 26 -> country, 24 -> country, 16 -> Uni
                                                  //| ted, 21 -> name, 6 -> "Latvia", 1 -> "Estonia", 17 -> States, 25 -> name, 0
                                                  //|  -> ROOT, 20 -> and, 18 -> name),Map(19 -> (7,8), 23 -> (5,6), 11 -> (5,6),
                                                  //|  22 -> (3,4), 26 -> (1,2), 24 -> (5,6), 16 -> (7,8), 21 -> (3,4), 6 -> (3,4
                                                  //| ), 1 -> (1,2), 17 -> (8,9), 25 -> (1,2), 20 -> (7,8), 18 -> (7,8)),Map((18,
                                                  //| 16) -> opN, (21,6) -> opN, (25,1) -> opN, (1,22) -> UNKNOWN, (24,23) -> nam
                                                  //| e, (19,18) -> name, (18,17) -> opN, (1,24) -> UNKNOWN, (0,20) -> ROOT, (20,
                                                  //| 19) -> opN, (26,25) -> name, (23,11) -> opN, (22,21) -> name, (0,26) -> UNK
                                                  //| NOWN))),Map(0 -> ROOT, 24 -> 24, 25 -> 25, 20 -> 20, 1 -> 1, 6 -> 6, 21 -> 
                                                  //| 21, 17 -> 17, 22 -> 22, 18 -> 18, 16 -> 16, 11 -> 11, 26 -> 26, 23 -> 23, 1
                                                  //| 9 -> 19))
  outputAdv1                                      //> res9: amr.Sentence = Sentence(Estonia (EE); Latvia (LV); Lithuania (LT); Un
                                                  //| ited States (US),
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> "Estonia", 6 -> "Latvia", 11 -> "Lithuania",
                                                  //|  16 -> United, 17 -> States, 18 -> name, 19 -> country, 20 -> and, 21 -> na
                                                  //| me, 22 -> country, 23 -> name, 24 -> country, 25 -> name, 26 -> country)
                                                  //| SpanMap:	Map(1 -> (1,2), 6 -> (3,4), 11 -> (5,6), 16 -> (7,8), 17 -> (8,9
                                                  //| ), 18 -> (7,8), 19 -> (7,8), 20 -> (7,8), 21 -> (3,4), 22 -> (3,4), 23 -> (
                                                  //| 5,6), 24 -> (5,6), 25 -> (1,2), 26 -> (1,2))
                                                  //| Edges:	Map((0,20) -> ROOT, (0,26) -> UNKNOWN, (1,22) -> UNKNOWN, (1,24)
                                                  //|  -> UNKNOWN, (18,16) -> opN, (18,17) -> opN, (19,18) -> name, (20,19) -> op
                                                  //| N, (21,6) -> opN, (22,21) -> name, (23,11) -> opN, (24,23) -> name, (25,1) 
                                                  //| -> opN, (26,25) -> name)
                                                  //| InsertedNodes:	Map(24 -> 0.2, 25 -> 0.0.0, 20 -> 0, 21 -> 0.1.0, 22 -> 
                                                  //| 0.1, 18 -> 0.3.0, 26 -> 0.0, 23 -> 0.2.0, 19 -> 0.3)
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set((17,18)),Some(AMRGraph(Map(19 -> country, 23 -> name
                                                  //| , 11 -> "Lithuania", 22 -> country, 26 -> country, 24 -> country, 16 -> Uni
                                                  //| ted, 21 -> name, 6 -> "Latvia", 1 -> "Estonia", 17 -> States, 25 -> name, 0
                                                  //|  -> ROOT, 20 -> and, 18 -> name),Map(19 -> (7,8), 23 -> (5,6), 11 -> (5,6),
                                                  //|  22 -> (3,4), 26 -> (1,2), 24 -> (5,6), 16 -> (7,8), 21 -> (3,4), 6 -> (3,4
                                                  //| ), 1 -> (1,2), 17 -> (8,9), 25 -> (1,2), 20 -> (7,8), 18 -> (7,8)),Map((18,
                                                  //| 16) -> opN, (21,6) -> opN, (25,1) -> opN, (1,22) -> UNKNOWN, (24,23) -> nam
                                                  //| e, (19,18) -> name, (18,17) -> opN, (1,24) -> UNKNOWN, (0,20) -> ROOT, (20,
                                                  //| 19) -> opN, (26,25) -> name, (23,11) -> opN, (22,21) -> name, (0,26) -> UNK
                                                  //| NOWN))),Map(0 -> ROOT, 24 -> 24, 25 -> 25, 20 -> 20, 1 -> 1, 6 -> 6, 21 -> 
                                                  //| 21, 17 -> 17, 22 -> 22, 18 -> 18, 16 -> 16, 11 -> 11, 26 -> 26, 23 -> 23, 1
                                                  //| 9 -> 19))
}