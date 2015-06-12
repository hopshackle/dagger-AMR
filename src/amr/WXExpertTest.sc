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
                                                  //|  / country :name (n14 / name :op1 "United" :op2 "States"))) "), (NATO CONSIDE
                                                  //| RS cyber attacks a threat to military and civilian computer networks after th
                                                  //| e Estonian Government was struck by cyber attacks in 2007.,"(c / consider-02 
                                                  //| :ARG0 (m2 / military :name (n / name :op1 "NATO")) :ARG1 (a / attack-01 :mod 
                                                  //| (c2 / cyber)) :ARG2 (t / thing :ARG1-of (t2 / threaten-01 :ARG2 (a5 / and :op
                                                  //| 1 (n2 / network :mod (m / military)) :op2 (n4 / network :mod (c3 / civilian))
                                                  //|  :mod (c4 / computer)))) :time (a3 / after :op1 (s / strike-01 :ARG0 (a4 / at
                                                  //| tack :mod (c6 / cyber)) :ARG1 (g / government-organization :ARG0-of (g2 / gov
                                                  //| ern-01 :ARG1 (c5 / country :name (n3 / name :op1 "Estonia")))) :time (d / dat
                                                  //| e-entity :year 2007)))) "), (NATO allies have signed an agreement to fund a r
                                                  //| esearch center to bolster defenses against cyber attacks.,(s / sign-01 :ARG0 
                                                  //| (p / person :ARG0-of (a / ally-01) :mod (m / military :name (n / name :op1 "N
                                                  //| ATO"))) :ARG1 (a2 / agree-01 :ARG0 p :ARG1 (f / fund-01 :ARG0 p :ARG1 (c / ce
                                                  //| nter :mod (r / research-01)) :purpose (b / bolster-01 :ARG0 p :ARG1 (d / defe
                                                  //| nd-01 :prep-against (a3 / attack :mod (c2 / cyber)))))))))
  val s1 = Sentence(testData(0)._1, testData(0)._2)
                                                  //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.4 sec].
                                                  //| s1  : amr.Sentence = Sentence(2008-05-14,
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> 2008-05-14)
                                                  //| SpanMap:	Map(1 -> (1,2))
                                                  //| Edges:	Map((0,1) -> root),Some(AMRGraph(Map(0.1 -> 14, 0.0 -> 5, 0 -> d
                                                  //| ate-entity, 0.2 -> 2008, ROOT -> ROOT),Map(0 -> (1,2), 0.2 -> (1,2)),Map((0,
                                                  //| 0.0) -> month, (0,0.1) -> day, (0,0.2) -> year, (0,ROOT) -> ROOT))))
                                                  
  s1.mapFromDTtoAMR                               //> res0: scala.collection.immutable.Map[Int,String] = Map(1 -> 0.2)
  s1.positionToAMR                                //> res1: scala.collection.immutable.Map[Int,String] = Map(1 -> 0.2)
                                                  
 val s2 = Sentence(testData(2)._1, testData(2)._2)//> WARNING: Found duplicate match for concept attack-01
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
  s2.mapFromDTtoAMR                               //> res2: scala.collection.immutable.Map[Int,String] = Map(5 -> NONE, 10 -> 0.2.
                                                  //| 0.0.1.0, 14 -> NONE, 20 -> 0.3.0.0.0, 1 -> 0.0.0.0, 6 -> 0.2, 21 -> 0.3.0.0,
                                                  //|  9 -> 0.2.0.0, 13 -> 0.3, 2 -> 0, 17 -> NONE, 22 -> NONE, 12 -> 0.2.0.0.0, 7
                                                  //|  -> NONE, 3 -> 0.1.0, 18 -> 0.3.0, 16 -> 0.3.0.1, 11 -> 0.2.0.0.2, 23 -> 0.3
                                                  //| .0.2, 8 -> 0.0, 19 -> NONE, 4 -> 0.1, 15 -> 0.3.0.1.0.0.0)
  s2.positionToAMR                                //> res3: scala.collection.immutable.Map[Int,String] = Map(10 -> 0.2.0.0.1.0, 20
                                                  //|  -> 0.3.0.0.0, 1 -> 0.0.0.0, 6 -> 0.2, 21 -> 0.3.0.0, 9 -> 0.2.0.0, 13 -> 0.
                                                  //| 3, 2 -> 0, 12 -> 0.2.0.0.0, 3 -> 0.1.0, 18 -> 0.3.0, 16 -> 0.3.0.1, 11 -> 0.
                                                  //| 2.0.0.2, 23 -> 0.3.0.2, 8 -> 0.0, 4 -> 0.1, 15 -> 0.3.0.1.0.0.0)
  val expert = new WangXueExpert                  //> expert  : amr.WangXueExpert = amr.WangXueExpert@fba92d3
  val expertSystem = new WangXueTransitionSystem  //> expertSystem  : amr.WangXueTransitionSystem = amr.WangXueTransitionSystem@71
                                                  //| 238fc2
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
  s2.amr                                          //> res4: Option[amr.AMRGraph] = Some(AMRGraph(Map(0.1 -> attack-01, 0.0.0 -> na
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
                                                  //> WARNING: Found duplicate match for concept attack-01
                                                  //| WARNING: Found duplicate match for concept cyber
                                                  //| nextAction  : amr.WangXueAction = NextNode: 5 -> date-entity
  var nextState = nextAction(startState)          //> nextState  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(10, 9, 22, 20, 14, 21, 15, 8, 11, 12, 19, 13, 1, 17, 5, 2, 
                                                  //| 16, 7, 6, 18, 3, 4, 0)
                                                  //| Children:	List()
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> NATO, 2 -> CONSIDERS, 3 -> cyber, 4 -> attac
                                                  //| ks, 5 -> a, 6 -> threat, 7 -> to, 8 -> military, 9 -> and, 10 -> civilian, 1
                                                  //| 1 -> computer, 12 -> networks, 13 -> after, 14 -> the, 15 -> Estonian, 16 ->
                                                  //|  Government, 17 -> was, 18 -> struck, 19 -> by, 20 -> cyber, 21 -> attacks, 
                                                  //| 22 -> in, 23 -> date-entity)
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
val output = SampleExpertTrajectory.sampleTrajectory(s2, "")
                                                  //> 23	NextNode: 5 -> date-entity
                                                  //| 10	NextNode: 20 -> civilian
                                                  //| 9	NextNode: 40 -> and
                                                  //| WARNING: Found duplicate match for concept attack-01
                                                  //| WARNING: Found duplicate match for concept cyber
                                                  //| 22	NextEdge: 0 -> UNKNOWN
                                                  //| 22	NextNode: 0 -> UNKNOWN
                                                  //| 20	NextNode: 28 -> cyber
                                                  //| 14	DeleteNode
                                                  //| 21	NextEdge: 0 -> UNKNOWN
                                                  //| 21	NextEdge: 4 -> mod
                                                  //| 21	NextNode: 32 -> attack
                                                  //| 15	NextNode: 3 -> name
                                                  //| 8	NextEdge: 0 -> UNKNOWN
                                                  //| 8	NextEdge: 0 -> UNKNOWN
                                                  //| 8	NextNode: 39 -> military
                                                  //| 11	NextNode: 18 -> computer
                                                  //| 12	NextEdge: 0 -> UNKNOWN
                                                  //| 12	NextEdge: 0 -> UNKNOWN
                                                  //| 12	NextNode: 2 -> network
                                                  //| 19	NextEdge: 0 -> UNKNOWN
                                                  //| 19	NextNode: 0 -> UNKNOWN
                                                  //| 13	NextNode: 25 -> after
                                                  //| 1	NextNode: 33 -> "NATO"
                                                  //| 17	DeleteNode
                                                  //| 5	DeleteNode
                                                  //| 2	NextNode: 27 -> consider-02
                                                  //| 16	NextEdge: 0 -> UNKNOWN
                                                  //| 16	NextNode: 42 -> government-organization
                                                  //| 7	NextEdge: 0 -> UNKNOWN
                                                  //| 7	NextNode: 0 -> UNKNOWN
                                                  //| 6	NextNode: 12 -> thing
                                                  //| 18	NextEdge: 0 -> UNKNOWN
                                                  //| 18	NextEdge: 0 -> UNKNOWN
                                                  //| 18	NextEdge: 6 -> ARG1
                                                  //| 18	NextNode: 15 -> strike-01
                                                  //| 3	NextEdge: 0 -> UNKNOWN
                                                  //| 3	NextEdge: 0 -> UNKNOWN
                                                  //| 3	NextNode: 28 -> cyber
                                                  //| 4	NextEdge: 0 -> UNKNOWN
                                                  //| 4	NextEdge: 0 -> UNKNOWN
                                                  //| 4	NextEdge: 0 -> UNKNOWN
                                                  //| 4	NextEdge: 4 -> mod
                                                  //| 4	NextNode: 30 -> attack-01
                                                  //| 0	NextEdge: 0 -> UNKNOWN
                                                  //| 0	NextNode: 38 -> ROOT
                                                  //| output  : amr.Sentence = Sentence(NATO CONSIDERS cyber attacks a threat to m
                                                  //| ilitary and civilian computer networks after the Estonian Government was str
                                                  //| uck by cyber attacks in 2007.,
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> "NATO", 2 -> consider-02, 3 -> cyber, 4 -> a
                                                  //| ttack-01, 6 -> thing, 7 -> UNKNOWN, 8 -> military, 9 -> and, 10 -> civilian,
                                                  //|  11 -> computer, 12 -> network, 13 -> after, 15 -> name, 16 -> government-or
                                                  //| ganization, 18 -> strike-01, 19 -> UNKNOWN, 20 -> cyber, 21 -> attack, 22 ->
                                                  //|  UNKNOWN, 23 -> date-entity)
                                                  //| SpanMap:	M
                                                  //| Output exceeds cutoff limit.
val loss = new WangXueLossFunction                //> loss  : amr.WangXueLossFunction = amr.WangXueLossFunction@66d3eec0
loss(s2, s2, null)                                //> res5: Double = 0.0
loss(s2, output, null)                            //> res6: Double = 0.5434782608695652
println(output.amr.get)                           //> AMRGraph(Map(12 -> network, 8 -> military, 19 -> UNKNOWN, 23 -> date-entity,
                                                  //|  4 -> attack-01, 15 -> name, 11 -> computer, 9 -> and, 22 -> UNKNOWN, 13 -> 
                                                  //| after, 16 -> government-organization, 10 -> civilian, 21 -> attack, 6 -> thi
                                                  //| ng, 1 -> "NATO", 0 -> ROOT, 20 -> cyber, 2 -> consider-02, 18 -> strike-01, 
                                                  //| 7 -> UNKNOWN, 3 -> cyber),Map(12 -> (12,13), 8 -> (8,9), 19 -> (19,20), 23 -
                                                  //| > (23,24), 4 -> (4,5), 15 -> (15,16), 11 -> (11,12), 9 -> (9,10), 22 -> (22,
                                                  //| 23), 13 -> (13,14), 16 -> (16,17), 10 -> (10,11), 21 -> (21,22), 6 -> (6,7),
                                                  //|  1 -> (1,2), 20 -> (20,21), 2 -> (2,3), 18 -> (18,19), 7 -> (7,8), 3 -> (3,4
                                                  //| )),Map((7,12) -> UNKNOWN, (12,11) -> UNKNOWN, (18,16) -> ARG1, (3,2) -> UNKN
                                                  //| OWN, (16,15) -> UNKNOWN, (12,8) -> UNKNOWN, (4,6) -> UNKNOWN, (4,18) -> UNKN
                                                  //| OWN, (21,20) -> mod, (19,21) -> UNKNOWN, (18,19) -> UNKNOWN, (22,23) -> UNKN
                                                  //| OWN, (0,4) -> UNKNOWN, (3,1) -> UNKNOWN, (4,3) -> mod, (18,13) -> UNKNOWN, (
                                                  //| 8,9) -> UNKNOWN, (21,22) -> UNKNOWN, (8,10) -> UNKNOWN, (4,7) -> UNKNOWN))
s2.amr.get.toOutputFormat                         //> res7: String = "# ::AMRGraph
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
}