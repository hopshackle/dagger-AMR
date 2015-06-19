package amr

object WXExpertTest {


  val testData = AMRGraph.importFile("C:\\AMR\\AMR2.txt")
                                                  //> testData  : IndexedSeq[(String, String)] = Vector((2008-05-14,"(d / date-enti
                                                  //| ty :month 5 :day 14 :year 2008) "), (Estonia (EE); Latvia (LV); Lithuania (LT
                                                  //| ); Germany (DE): Italy (IT); Spain (ES); Slovakia (SK); United States (US),"(
                                                  //| a / and :op1 (c / country :name (n / name :op1 "Estonia")) :op2 (c2 / country
                                                  //|  :name (n3 / name :op1 "Latvia")) :op3 (c3 / country :name (n5 / name :op1 "L
                                                  //| ithuania")) :op4 (c4 / country :name (n7 / name :op1 "Germany")) :op5 (c5 / c
                                                  //| ountry :name (n9 / name :op1 "Italy")) :op6 (c6 / country :name (n10 / name :
                                                  //| op1 "Spain")) :op7 (c7 / country :name (n12 / name :op1 "Slovakia")) :op8 (c8
                                                  //|  / country :name (n14 / name :op1 "United" :op2 "States"))) "), (Internationa
                                                  //| l; Government; Telecom; technology; science,"(a / and :op1 (i / international
                                                  //| ) :op2 (g / government-organization :ARG0-of (g2 / govern-01)) :op3 (t / tele
                                                  //| communication) :op4 (t2 / technology) :op5 (s / science)) "), (NATO CONSIDERS
                                                  //|  cyber attacks a threat to military and civilian computer networks after the 
                                                  //| Estonian Government was struck by cyber attacks in 2007.,"(c / consider-02 :A
                                                  //| RG0 (m2 / military :name (n / name :op1 "NATO")) :ARG1 (a / attack-01 :mod (c
                                                  //| 2 / cyber)) :ARG2 (t / thing :ARG1-of (t2 / threaten-01 :ARG2 (a5 / and :op1 
                                                  //| (n2 / network :mod (m / military)) :op2 (n4 / network :mod (c3 / civilian)) :
                                                  //| mod (c4 / computer)))) :time (a3 / after :op1 (s / strike-01 :ARG0 (a4 / atta
                                                  //| ck :mod (c6 / cyber)) :ARG1 (g / government-organization :ARG0-of (g2 / gover
                                                  //| n-01 :ARG1 (c5 / country :name (n3 / name :op1 "Estonia")))) :time (d / date-
                                                  //| entity :year 2007)))) "), (NATO allies have signed an agreement to fund a res
                                                  //| earch center to bolster defenses against cyber attacks.,(s / sign-01 :ARG0 (p
                                                  //|  / person :ARG0-of (a / ally-01) :mod (m / military :name (n / name :op1 "NAT
                                                  //| O"))) :ARG1 (a2 / agree-01 :ARG0 p :ARG1 (f / fund-01 :ARG0 p :ARG1 (c / cent
                                                  //| er :mod (r / research-01)) :purpose (b / bolster-01 :
                                                  //| Output exceeds cutoff limit.
  val s1 = Sentence(testData(0)._1, testData(0)._2)
                                                  //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.1 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [3.7 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [3.1 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [2.6 sec].
                                                  //| s1  : amr.Sentence = Sentence(2008-05-14,
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> 2008-05-14)
                                                  //| SpanMap:	Map(1 -> (1,2))
                                                  //| Edges:	Map((0,1) -> root),Some(AMRGraph(Map(0.1 -> 14, 0.0 -> 5, 0 -> d
                                                  //| ate-entity, 0.2 -> 2008, ROOT -> ROOT),Map(0 -> (1,2), 0.2 -> (1,2)),Map((0,
                                                  //| 0.0) -> month, (0,0.1) -> day, (0,0.2) -> year, (0,ROOT) -> ROOT))),Map(1 ->
                                                  //|  0.2))
                                                  
  s1.positionToAMR                                //> res0: Map[Int,String] = Map(1 -> 0.2)
                                                  
 val s2 = Sentence(testData(3)._1, testData(3)._2)//> WARNING: Found duplicate match for concept attack-01
                                                  //| WARNING: Found duplicate match for concept cyber
                                                  //| s2  : amr.Sentence = Sentence(NATO CONSIDERS cyber attacks a threat to milit
                                                  //| ary and civilian computer networks after the Estonian Government was struck 
                                                  //| by cyber attacks in 2007.,
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> NATO, 2 -> CONSIDERS, 3 -> cyber, 4 -> attac
                                                  //| ks, 5 -> a, 6 -> threat, 7 -> to, 8 -> military, 9 -> and, 10 -> civilian, 1
                                                  //| 1 -> computer, 12 -> networks, 13 -> after, 14 -> the, 15 -> Estonian, 16 ->
                                                  //|  Government, 17 -> was, 18 -> struck, 19 -> by, 20 -> cyber, 21 -> attacks, 
                                                  //| 22 -> in, 23 -> 2007)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,12
                                                  //| ), 12 -> (12,13), 13 -> (13,14), 14 -> (14,15), 15 -> (15,16), 16 -> (16,17)
                                                  //| , 17 -> (17,18), 18 -> (18,19), 19 -> (19,20), 20 -> (20,21), 21 -> (21,22),
                                                  //|  22 -> (22,23), 23 -> (23,24))
                                                  //| Edges:	Map((0,4) -> root, (3,1) -> nn, (3,2) -> nn, (4,3) -> nsubj, (4,
                                                  //| 6) -> dobj, (4,7) -> prep, (4,18) -> advcl, (6,5) -> det, (7,12) -> pobj, (8
                                                  //| ,9) -> cc, (8,10) -> conj, (12,8) -> amod, (12,11) -> nn, (16,14) -> det, (1
                                                  //| 6,15) -> amod, (18,13) -> mark, (18,16) -> nsubjpass, (18,17) -> auxpass, (1
                                                  //| 8,19) -> prep, (19,21) -> pobj, (21,20) -> nn, (21,22) -> prep, (22,23) -> p
                                                  //| obj),Some(AMRGraph(Map(0.1 -> attack-01, 0.0.0 -> name, 0.3.0.1.0.0 -> count
                                                  //| ry, 0.2.0.0.0 -> network, 0.1.0 -> cyber, 0.2.0.0.1.0 -> civilian, 0.3.0.2.0
                                                  //|  -> 2007, 0.0 -> military, 0.2.0.0.1 -> network, 0.3.0.1.0 -> govern-01, 0.0
                                                  //| .0.0 -> "NATO", 0.2.0 -> threaten-01, 0.3.0.0 -> attack, 0.3.0 -> strike-01,
                                                  //|  0.3 -> after, 0.2.0.0.0.0 -> military, 0.2.0.0.2 -> computer, 0 -> consider
                                                  //| -02, 0.3.0.1 -> government-organization, 0.2 -> thing, 0.3.0.2 -> date-entit
                                                  //| y, 0.3.0.1.0.0.0.0 -> "Estonia", ROOT -> ROOT, 0.2.0.0 -> and, 0.3.0.1.0.0.0
                                                  //|  -> name, 0.3.0.0.0 -
                                                  //| Output exceeds cutoff limit.
  s2.positionToAMR                                //> res1: Map[Int,String] = Map(10 -> 0.2.0.0.1.0, 20 -> 0.3.0.0.0, 1 -> 0.0.0.0
                                                  //| , 6 -> 0.2.0, 21 -> 0.3.0.0, 9 -> 0.2.0.0, 13 -> 0.3, 2 -> 0, 12 -> 0.2.0.0.
                                                  //| 0, 3 -> 0.1.0, 18 -> 0.3.0, 16 -> 0.3.0.1, 11 -> 0.2.0.0.2, 23 -> 0.3.0.2.0,
                                                  //|  8 -> 0.0, 4 -> 0.1, 15 -> 0.3.0.1.0.0.0.0)
  val expert = new WangXueExpertBasic             //> expert  : amr.WangXueExpertBasic = amr.WangXueExpertBasic@740cae06
  val expertSystem = new WangXueTransitionSystem  //> WARNING: Found duplicate match for concept attack-01
                                                  //| WARNING: Found duplicate match for concept cyber
                                                  //| WARNING: Found duplicate match for concept attack-01
                                                  //| WARNING: Found duplicate match for concept cyber
                                                  //| expertSystem  : amr.WangXueTransitionSystem = amr.WangXueTransitionSystem@17
                                                  //| 3ed316
  val startState = expertSystem.init(s2)          //> startState  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(23, 10, 9, 22, 20, 14, 21, 15, 8, 11, 12, 19, 13, 1, 17, 5,
                                                  //|  2, 16, 7, 6, 18, 3, 4, 0)
                                                  //| Children:	List()
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> NATO, 2 -> CONSIDERS, 3 -> cyber, 4 -> attac
                                                  //| ks, 5 -> a, 6 -> threat, 7 -> to, 8 -> military, 9 -> and, 10 -> civilian, 1
                                                  //| 1 -> computer, 12 -> networks, 13 -> after, 14 -> the, 15 -> Estonian, 16 ->
                                                  //|  Government, 17 -> was, 18 -> struck, 19 -> by, 20 -> cyber, 21 -> attacks, 
                                                  //| 22 -> in, 23 -> 2007)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,12
                                                  //| ), 12 -> (12,13), 13 -> (13,14), 14 -> (14,15), 15 -> (15,16), 16 -> (16,17)
                                                  //| , 17 -> (17,18), 18 -> (18,19), 19 -> (19,20), 20 -> (20,21), 21 -> (21,22),
                                                  //|  22 -> (22,23), 23 -> (23,24))
                                                  //| Edges:	Map((0,4) -> root, (3,1) -> nn, (3,2) -> nn, (4,3) -> nsubj, (4,
                                                  //| 6) -> dobj, (4,7) -> prep, (4,18) -> advcl, (6,5) -> det, (7,12) -> pobj, (8
                                                  //| ,9) -> cc, (8,10) -> conj, (12,8) -> amod, (12,11) -> nn, (16,14) -> det, (1
                                                  //| 6,15) -> amod, (18,13) -> mark, (18,16) -> nsubjpass, (18,17) -> auxpass, (1
                                                  //| 8,19) -> prep, (19,21) -> pobj, (21,20) -> nn, (21,22) -> prep, (22,23) -> p
                                                  //| obj)
  s2.amr                                          //> res2: Option[amr.AMRGraph] = Some(AMRGraph(Map(0.1 -> attack-01, 0.0.0 -> na
                                                  //| me, 0.3.0.1.0.0 -> country, 0.2.0.0.0 -> network, 0.1.0 -> cyber, 0.2.0.0.1.
                                                  //| 0 -> civilian, 0.3.0.2.0 -> 2007, 0.0 -> military, 0.2.0.0.1 -> network, 0.3
                                                  //| .0.1.0 -> govern-01, 0.0.0.0 -> "NATO", 0.2.0 -> threaten-01, 0.3.0.0 -> att
                                                  //| ack, 0.3.0 -> strike-01, 0.3 -> after, 0.2.0.0.0.0 -> military, 0.2.0.0.2 ->
                                                  //|  computer, 0 -> consider-02, 0.3.0.1 -> government-organization, 0.2 -> thin
                                                  //| g, 0.3.0.2 -> date-entity, 0.3.0.1.0.0.0.0 -> "Estonia", ROOT -> ROOT, 0.2.0
                                                  //| .0 -> and, 0.3.0.1.0.0.0 -> name, 0.3.0.0.0 -> cyber),Map(0.1 -> (4,5), 0.0.
                                                  //| 0 -> (1,2), 0.3.0.1.0.0 -> (15,16), 0.2.0.0.0 -> (12,13), 0.1.0 -> (3,4), 0.
                                                  //| 2.0.0.1.0 -> (10,11), 0.3.0.2.0 -> (23,24), 0.0 -> (8,9), 0.0.0.0 -> (1,2), 
                                                  //| 0.2.0 -> (6,7), 0.3.0.0 -> (21,22), 0.3.0 -> (18,19), 0.3 -> (13,14), 0.2.0.
                                                  //| 0.2 -> (11,12), 0 -> (2,3), 0.3.0.1 -> (16,17), 0.2 -> (6,7), 0.3.0.2 -> (23
                                                  //| ,24), 0.3.0.1.0.0.0.0 -> (15,16), 0.2.0.0 -> (9,10), 0.3.0.1.0.0.0 -> (15,16
                                                  //| ), 0.3.0.0.0 -> (20,21)),Map((0.0,0.0.0) -> name, (0.3.0,0.3.0.0) -> ARG0, (
                                                  //| 0,0.3) -> time, (0.0.0,0.0.0.0) -> opN, (0.2.0.0.1,0.2.0.0.1.0) -> mod, (0.1
                                                  //| ,0.1.0) -> mod, (0.3.0,0.3.0.1) -> ARG1, (0.3.0,0.3.0.2) -> time, (0.3.0.0,0
                                                  //| .3.0.0.0) -> mod, (0,0.2) -> ARG2, (0,0.1) -> ARG1, (0.2.0.0.0,0.2.0.0.0.0) 
                                                  //| -> mod, (0.2,0.2.0) -> ARG1-of, (0,ROOT) -> ROOT, (0.3,0.3.0) -> opN, (0.3.0
                                                  //| .1.0,0.3.0.1.0.0) -> ARG1, (0.2.0.0,0.2.0.0.1) -> opN, (0.2.0.0,0.2.0.0.0) -
                                                  //| > opN, (0.3.0.1.0.0.0,0.3.0.1.0.0.0.0) -> opN, (0.3.0.1,0.3.0.1.0) -> ARG0-o
                                                  //| f, (0,0.0) -> ARG0, (0.3.0.1.0.0,0.3.0.1.0.0.0) -> name, (0.2.0,0.2.0.0) -> 
                                                  //| ARG2, (0.3.0.2,0.3.0.2.0) -> year, (0.2.0.0,0.2.0.0.2) -> mod)))
  var nextAction = expert.chooseTransition(s2, startState)
                                                  //> Considering current node: 23 with child Nil
                                                  //| Position: 23
                                                  //| InGoldSpan: true
                                                  //| Concept: 0.3.0.2.0 -> 2007
                                                  //| Index: 0
                                                  //| Action chosen: NextNode: 0 -> UNKNOWN
                                                  //| nextAction  : amr.WangXueAction = NextNode: 0 -> UNKNOWN
  var nextState = nextAction(startState)          //> nextState  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(10, 9, 22, 20, 14, 21, 15, 8, 11, 12, 19, 13, 1, 17, 5, 2, 
                                                  //| 16, 7, 6, 18, 3, 4, 0)
                                                  //| Children:	List()
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> NATO, 2 -> CONSIDERS, 3 -> cyber, 4 -> attac
                                                  //| ks, 5 -> a, 6 -> threat, 7 -> to, 8 -> military, 9 -> and, 10 -> civilian, 1
                                                  //| 1 -> computer, 12 -> networks, 13 -> after, 14 -> the, 15 -> Estonian, 16 ->
                                                  //|  Government, 17 -> was, 18 -> struck, 19 -> by, 20 -> cyber, 21 -> attacks, 
                                                  //| 22 -> in, 23 -> 2007)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,12
                                                  //| ), 12 -> (12,13), 13 -> (13,14), 14 -> (14,15), 15 -> (15,16), 16 -> (16,17)
                                                  //| , 17 -> (17,18), 18 -> (18,19), 19 -> (19,20), 20 -> (20,21), 21 -> (21,22),
                                                  //|  22 -> (22,23), 23 -> (23,24))
                                                  //| Edges:	Map((0,4) -> root, (3,1) -> nn, (3,2) -> nn, (4,3) -> nsubj, (4,
                                                  //| 6) -> dobj, (4,7) -> prep, (4,18) -> advcl, (6,5) -> det, (7,12) -> pobj, (8
                                                  //| ,9) -> cc, (8,10) -> conj, (12,8) -> amod, (12,11) -> nn, (16,14) -> det, (1
                                                  //| 6,15) -> amod, (18,13) -> mark, (18,16) -> nsubjpass, (18,17) -> auxpass, (1
                                                  //| 8,19) -> prep, (19,21) -> pobj, (21,20) -> nn, (21,22) -> prep, (22,23) -> p
                                                  //| obj)
val output = RunDagger.sampleTrajectory(s2, "", new WangXueExpertBasic)
                                                  //> Considering current node: 23 with child Nil
                                                  //| Position: 23
                                                  //| InGoldSpan: true
                                                  //| Concept: 0.3.0.2.0 -> 2007
                                                  //| Index: 0
                                                  //| Action chosen: NextNode: 0 -> UNKNOWN
                                                  //| Considering current node: 10 with child Nil
                                                  //| Position: 10
                                                  //| InGoldSpan: true
                                                  //| Concept: 0.2.0.0.1.0 -> civilian
                                                  //| Index: 21
                                                  //| Action chosen: NextNode: 21 -> civilian
                                                  //| Considering current node: 9 with child Nil
                                                  //| Position: 9
                                                  //| InGoldSpan: true
                                                  //| Concept: 0.2.0.0 -> and
                                                  //| Index: 41
                                                  //| Action chosen: NextNode: 41 -> and
                                                  //| Considering current node: 22 with child 23
                                                  //| Edge: NONE	0.3.0.2.0	NONE
                                                  //| Index: 0
                                                  //| Action chosen: NextEdge: 0 -> UNKNOWN
                                                  //| Considering current node: 22 with child Nil
                                                  //| Position: 22
                                                  //| InGoldSpan: false
                                                  //| Concept: NONE -> NONE
                                                  //| Index: 0
                                                  //| Action chosen: NextNode: 0 -> UNKNOWN
                                                  //| Considering current node: 20 with child Nil
                                                  //| Position: 20
                                                  //| InGoldSpan: true
                                                  //| Concept: 0.3.0.0.0 -> cyber
                                                  //| Index: 29
                                                  //| Action chosen: NextNode: 29 -> cyber
                                                  //| Considering current node: 14 with child Nil
                                                  //| Position: 14
                                                  //| InGoldSpan: false
                                                  //| Action chosen: DeleteNode
                                                  //| Considering current node: 21 with child 22
                                                  //| Edge: 0.3.0.0	NONE	NONE
                                                  //| Index: 0
                                                  //| Action chosen: NextEdge: 0 -> UNKNOWN
                                                  //| Considering current node: 21 with child 20
                                                  //| Edge: 0.3.0.0	0.3.0.0.0	mod
                                                  //| Index: 4
                                                  //| Action chosen: NextEdge: 4 -> mod
                                                  //| Considering current node: 21 with child Nil
                                                  //| Position: 21
                                                  //| InGoldSpan: true
                                                  //| Concept: 0.3.0.0 -> attack
                                                  //| Index: 33
                                                  //| Action chosen: NextNode: 33 -> attack
                                                  //| Considering current node: 15 with child Nil
                                                  //| Position: 15
                                                  //| InGoldSpan: true
                                                  //| Concept: 0.3.0.1.0.0.0.0 -> "Estonia"
                                                  //| Index: 12
                                                  //| Action chosen: NextNode: 12 -> "Estonia"
                                                  //| Considering current node: 8 with child 10
                                                  //| Edge: 0.0	0.2.0.0.1.0	NONE
                                                  //| Index: 0
                                                  //| Action chosen: NextEdge: 0 -> UNKNOWN
                                                  //| Considering current node: 8 with child 9
                                                  //| Edge: 0.0	0.2.0.0	NONE
                                                  //| 
                                                  //| Output exceeds cutoff limit.
val loss = new WangXueLossFunction                //> loss  : amr.WangXueLossFunction = amr.WangXueLossFunction@17f62e33
loss(s2, s2, null)                                //> res3: Double = 0.0
loss(s2, output, null)                            //> res4: Double = 0.5434782608695652
println(output.amr.get)                           //> AMRGraph(Map(12 -> network, 8 -> military, 19 -> by, 23 -> 2007, 4 -> attack
                                                  //| -01, 15 -> "Estonia", 11 -> computer, 9 -> and, 22 -> in, 13 -> after, 16 ->
                                                  //|  government-organization, 10 -> civilian, 21 -> attack, 6 -> threaten-01, 1 
                                                  //| -> "NATO", 0 -> ROOT, 20 -> cyber, 2 -> consider-02, 18 -> strike-01, 7 -> t
                                                  //| o, 3 -> cyber),Map(12 -> (12,13), 8 -> (8,9), 19 -> (19,20), 23 -> (23,24), 
                                                  //| 4 -> (4,5), 15 -> (15,16), 11 -> (11,12), 9 -> (9,10), 22 -> (22,23), 13 -> 
                                                  //| (13,14), 16 -> (16,17), 10 -> (10,11), 21 -> (21,22), 6 -> (6,7), 1 -> (1,2)
                                                  //| , 20 -> (20,21), 2 -> (2,3), 18 -> (18,19), 7 -> (7,8), 3 -> (3,4)),Map((7,1
                                                  //| 2) -> UNKNOWN, (12,11) -> UNKNOWN, (18,16) -> ARG1, (3,2) -> UNKNOWN, (16,15
                                                  //| ) -> UNKNOWN, (12,8) -> UNKNOWN, (4,6) -> UNKNOWN, (4,18) -> UNKNOWN, (21,20
                                                  //| ) -> mod, (19,21) -> UNKNOWN, (18,19) -> UNKNOWN, (22,23) -> UNKNOWN, (0,4) 
                                                  //| -> UNKNOWN, (3,1) -> UNKNOWN, (4,3) -> mod, (18,13) -> UNKNOWN, (8,9) -> UNK
                                                  //| NOWN, (21,22) -> UNKNOWN, (8,10) -> UNKNOWN, (4,7) -> UNKNOWN))
s2.amr.get.toOutputFormat                         //> res5: String = "# ::AMRGraph
                                                  //| # ::node	0.1	attack-01
                                                  //| # ::node	0.0.0	name
                                                  //| # ::node	0.3.0.1.0.0	country
                                                  //| # ::node	0.2.0.0.0	network
                                                  //| # ::node	0.1.0	cyber
                                                  //| # ::node	0.2.0.0.1.0	civilian
                                                  //| # ::node	0.3.0.2.0	2007
                                                  //| # ::node	0.0	military
                                                  //| # ::node	0.2.0.0.1	network
                                                  //| # ::node	0.3.0.1.0	govern-01
                                                  //| # ::node	0.0.0.0	"NATO"
                                                  //| # ::node	0.2.0	threaten-01
                                                  //| # ::node	0.3.0.0	attack
                                                  //| # ::node	0.3.0	strike-01
                                                  //| # ::node	0.3	after
                                                  //| # ::node	0.2.0.0.0.0	military
                                                  //| # ::node	0.2.0.0.2	computer
                                                  //| # ::node	0	consider-02
                                                  //| # ::node	0.3.0.1	government-organization
                                                  //| # ::node	0.2	thing
                                                  //| # ::node	0.3.0.2	date-entity
                                                  //| # ::node	0.3.0.1.0.0.0.0	"Estonia"
                                                  //| # ::node	ROOT	ROOT
                                                  //| # ::node	0.2.0.0	and
                                                  //| # ::node	0.3.0.1.0.0.0	name
                                                  //| # ::node	0.3.0.0.0	cyber
                                                  //| # ::edge	0.0	0.0.0	name
                                                  //| # ::edge	0.3.0	0.3.0.0	ARG0
                                                  //| # ::edge	0	0.3	time
                                                  //| # ::edge	0.0.0	0.0.0.0	opN
                                                  //| # ::edge	0.2.0.0.1	0.2.0.0.1.0	mod
                                                  //| # ::edge	0.1	0.1.0	mod
                                                  //| # ::edge	0.3.0	0.3.0.1	ARG1
                                                  //| # ::edge	0.3.0	0.3.0.2	time
                                                  //| # ::edge	0.3.0.0	0.3.0.0.0	mod
                                                  //| # ::edge	0	0.2	ARG2
                                                  //| # ::edge	0	0.1	ARG1
                                                  //| # ::edge	0.2.0.0.0	0.2.0.0.0.0	mod
                                                  //| # ::edge	0.2	0.2.0	ARG1-of
                                                  //| # ::edge	0	ROOT	ROOT
                                                  //| # ::edge	0.3	0.3.0	opN
                                                  //| # ::edge	0.3.0.1.0	0.3.0.1.0.0	ARG1
                                                  //| # ::edge	0.2.0.0	0.2.0.0.1	opN
                                                  //| # ::edge	0.2.0.0	0.2.0.0.0	opN
                                                  //| # ::edge	0.3.0.1.0.0.0	0.3.0.1.0.0.0.0	opN
                                                  //| # ::edge	0.3.0.1	0.3.0.1.0	ARG0-of
                                                  //| # ::edge	0	0.0	ARG0
                                                  //| # ::edge	0.3.0.1.0.0	0.3.0.1.0.0.0	name
                                                  //| # ::edge	0.2.0	0.2.0.0	ARG2
                                                  //| # ::edge	0.3.0.2	0.3.0.2.0	year
                                                  //| # ::edge	0.2.0.0	0.2.0.0.2	mod
                                                  //| "
 val outputAdv = RunDagger.sampleTrajectory(s1, "", new WangXueExpert)
                                                  //> Considering current node: 1 with child Nil
                                                  //| Matched AMR
                                                  //| No matching AMR
                                                  //| List(0)
                                                  //| List(0)
                                                  //| List(date-entity)
                                                  //| Action chosen: InsertNode: date-entity
                                                  //| Considering current node: 1 with child Nil
                                                  //| Matched AMR
                                                  //| No matching AMR
                                                  //| List(0)
                                                  //| List(0)
                                                  //| List()
                                                  //| Action chosen: NextNode: 0 -> UNKNOWN
                                                  //| Considering current node: 2 with child 1
                                                  //| No matching AMR
                                                  //| Matched AMR
                                                  //| List()
                                                  //| List()
                                                  //| List()
                                                  //| Action chosen: NextEdge: 0 -> UNKNOWN
                                                  //| Considering current node: 2 with child Nil
                                                  //| No matching AMR
                                                  //| No matching AMR
                                                  //| List()
                                                  //| List()
                                                  //| List()
                                                  //| Action chosen: NextNode: 0 -> UNKNOWN
                                                  //| Considering current node: 0 with child 2
                                                  //| No matching AMR
                                                  //| No matching AMR
                                                  //| List(0)
                                                  //| List(0)
                                                  //| List(date-entity)
                                                  //| Action chosen: InsertNode: date-entity
                                                  //| Considering current node: 0 with child 2
                                                  //| No matching AMR
                                                  //| No matching AMR
                                                  //| List(0)
                                                  //| List(0)
                                                  //| List(date-entity)
                                                  //| Action chosen: InsertNode: date-entity
                                                  //| Considering current node: 0 with child 2
                                                  //| No matching AMR
                                                  //| No matching AMR
                                                  //| List(0)
                                                  //| List(0)
                                                  //| List(date-entity)
                                                  //| Action chosen: InsertNode: date-entity
                                                  //| Considering current node: 0 with child 2
                                                  //| No matching AMR
                                                  //| No matching AMR
                                                  //| List(0)
                                                  //| List(0)
                                                  //| List(date-entity)
                                                  //| Action chosen: InsertNode: date-entity
                                                  //| Considering current node: 0 with child 2
                                                  //| No matching AMR
                                                  //| No matching AMR
                                                  //| List(0)
                                                  //| List(0)
                                                  //| List(date-entity)
                                                  //| Action chosen: InsertNode: date-entity
                                                  //| Considering current node: 0 with child 2
                                                  //| No matching AMR
                                                  //| No matching AMR
                                                  //| List(0)
                                                  //| List(0)
                                                  //| List(date-entity)
                                                  //| Action chosen: InsertNode: date-entity
                                                  //| Considering current node: 0 with child 2
                                                  //| No matching AMR
                                                  //| No matching AMR
                                                  //| List(0)
                                                  //| List(0)
                                                  //| List(date-entity)
                                                  //| Action chosen: InsertNode: date-entity
                                                  //| Considering current node: 0 with child 2
                                                  //| No ma
                                                  //| Output exceeds cutoff limit./
outputAdv
}