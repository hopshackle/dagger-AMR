package amr

object WXExpertTest {
  val testData = AMRGraph.importFile("C:\\AMR\\AMR2.txt")
                                                  //> testData  : IndexedSeq[(String, String)] = Vector((Estonia (EE); Latvia (LV);
                                                  //|  Lithuania (LT); Germany (DE): Italy (IT); Spain (ES); Slovakia (SK); United 
                                                  //| States (US),"(a / and :op1 (c / country :name (n / name :op1 "Estonia")) :op2
                                                  //|  (c2 / country :name (n3 / name :op1 "Latvia")) :op3 (c3 / country :name (n5 
                                                  //| / name :op1 "Lithuania")) :op4 (c4 / country :name (n7 / name :op1 "Germany")
                                                  //| ) :op5 (c5 / country :name (n9 / name :op1 "Italy")) :op6 (c6 / country :name
                                                  //|  (n10 / name :op1 "Spain")) :op7 (c7 / country :name (n12 / name :op1 "Slovak
                                                  //| ia")) :op8 (c8 / country :name (n14 / name :op1 "United" :op2 "States"))) "),
                                                  //|  (International; Government; Telecom; technology; science,"(a / and :op1 (i /
                                                  //|  international) :op2 (g / government-organization :ARG0-of (g2 / govern-01)) 
                                                  //| :op3 (t / telecommunication) :op4 (t2 / technology) :op5 (s / science)) "), (
                                                  //| NATO CONSIDERS cyber attacks a threat to military and civilian computer netwo
                                                  //| rks after the Estonian Government was struck by cyber attacks in 2007.,"(c / 
                                                  //| consider-02 :ARG0 (m2 / military :name (n / name :op1 "NATO")) :ARG1 (a / att
                                                  //| ack-01 :mod (c2 / cyber)) :ARG2 (t / thing :ARG1-of (t2 / threa
                                                  //| Output exceeds cutoff limit.
  val s1 = Sentence(testData(0)._1, testData(0)._2)
                                                  //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.2 sec].
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
                                                  
  s1.mapFromDTtoAMR                               //> res0: scala.collection.immutable.Map[Int,String] = Map(37 -> 0.7.0.1, 1 -> 0
                                                  //| .0.0.0, 6 -> 0.1.0.0, 28 -> NONE, 21 -> 0.4.0, 33 -> NONE, 13 -> NONE, 39 ->
                                                  //|  NONE, 3 -> NONE, 18 -> NONE, 16 -> 0.3, 31 -> 0.6, 11 -> 0.2.0.0, 26 -> 0.5
                                                  //| .0.0, 23 -> NONE, 8 -> NONE, 36 -> 0.7.0.1)
  s1.positionToAMR                                //> res1: scala.collection.immutable.Map[Int,String] = Map(5 -> 0.2.0.0, 1 -> 0.
                                                  //| 0.0.0, 9 -> 0.4.0, 13 -> 0.6, 7 -> 0.3, 3 -> 0.1.0.0, 16 -> 0.7.0.1, 11 -> 0
                                                  //| .5.0.0, 15 -> 0.7.0.1)
                                                  
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
                                                  //| ,9) -> cc, (8,10) -> conj, (12,
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
  val expert = new WangXueExpert                  //> expert  : amr.WangXueExpert = amr.WangXueExpert@6d4d66d2
  val expertSystem = new WangXueTransitionSystem  //> expertSystem  : amr.WangXueTransitionSystem = amr.WangXueTransitionSystem@2a
                                                  //| 265ea9
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
                                                  //| 6,15) -> amod, (18,13) -> mark, (18,16) -> nsubjpass, (1
                                                  //| Output exceeds cutoff limit.
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
                                                  //| 0,0.3) -> time, (0.0.0,0.0.0.0) -> op1, (0.2.0.0.1,0.2.0.0.1.0
                                                  //| Output exceeds cutoff limit.
  var nextAction = expert.chooseTransition(s2, startState)
                                                  //> nextAction  : amr.WangXueAction = NextNode: 0 -> UNKNOWN
  var nextState = nextAction(startState)          //> nextState  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(10, 9, 22, 20, 14, 21, 15, 8, 11, 12, 19, 13, 1, 17, 5, 2, 
                                                  //| 16, 7, 6, 18, 3, 4, 0)
                                                  //| Children:	List()
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> NATO, 2 -> CONSIDERS, 3 -> cyber, 4 -> attac
                                                  //| ks, 5 -> a, 6 -> threat, 7 -> to, 8 -> military, 9 -> and, 10 -> civilian, 1
                                                  //| 1 -> computer, 12 -> networks, 13 -> after, 14 -> the, 15 -> Estonian, 16 ->
                                                  //|  Government, 17 -> was, 18 -> struck, 19 -> by, 20 -> cyber, 21 -> attacks, 
                                                  //| 22 -> in, 23 -> UNKNOWN)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,12
                                                  //| ), 12 -> (12,13), 13 -> (13,14), 14 -> (14,15), 15 -> (15,16), 16 -> (16,17)
                                                  //| , 17 -> (17,18), 18 -> (18,19), 19 -> (19,20), 20 -> (20,21), 21 -> (21,22),
                                                  //|  22 -> (22,23), 23 -> (23,24))
                                                  //| Edges:	Map((0,4) -> root, (3,1) -> nn, (3,2) -> nn, (4,3) -> nsubj, (4,
                                                  //| 6) -> dobj, (4,7) -> prep, (4,18) -> advcl, (6,5) -> det, (7,12) -> pobj, (8
                                                  //| ,9) -> cc, (8,10) -> conj, (12,8) -> amod, (12,11) -> nn, (16,14) -> det, (1
                                                  //| 6,15) -> amod, (18,13) -> mark, (18,16) -> nsubjpass, (18,
                                                  //| Output exceeds cutoff limit.
val amrOutput = SampleExpertTrajectory.sampleTrajectory(s2, "")
                                                  //> 23	NextNode: 0 -> UNKNOWN
                                                  //| 10	NextNode: 0 -> UNKNOWN
                                                  //| 9	NextNode: 0 -> UNKNOWN
                                                  //| 22	NextNode: 0 -> UNKNOWN
                                                  //| 20	NextNode: 0 -> UNKNOWN
                                                  //| 14	DeleteNode
                                                  //| 21	NextNode: 0 -> UNKNOWN
                                                  //| 15	NextNode: 0 -> UNKNOWN
                                                  //| 8	NextNode: 5 -> military
                                                  //| 11	NextNode: 4 -> computer
                                                  //| 12	NextNode: 7 -> network
                                                  //| 19	NextNode: 0 -> UNKNOWN
                                                  //| 13	NextNode: 0 -> UNKNOWN
                                                  //| 1	NextNode: 0 -> UNKNOWN
                                                  //| 17	DeleteNode
                                                  //| 5	DeleteNode
                                                  //| 2	NextNode: 0 -> UNKNOWN
                                                  //| 16	NextNode: 0 -> UNKNOWN
                                                  //| 7	NextNode: 0 -> UNKNOWN
                                                  //| 6	NextNode: 8 -> thing
                                                  //| 18	NextNode: 0 -> UNKNOWN
                                                  //| 3	NextNode: 0 -> UNKNOWN
                                                  //| 4	NextNode: 1 -> attack-01
                                                  //| 0	NextNode: 0 -> UNKNOWN
                                                  //| amrOutput  : amr.AMRGraph = AMRGraph(Map(12 -> network, 8 -> military, 19 ->
                                                  //|  UNKNOWN, 23 -> UNKNOWN, 4 -> attack-01, 15 -> UNKNOWN, 11 -> computer, 9 ->
                                                  //|  UNKNOWN, 22 -> UNKNOWN, 13 -> UNKNOWN, 16 -> UNKNOWN, 10 -> UNKNOWN, 21 -> 
                                                  //| UNKNOWN, 6 -> thing, 1 -> UNKNOWN, 0 -> UNKNOWN, 20 -> UNKNOWN, 2 -> UNKNOWN
                                                  //| , 18 -> UNKNOWN, 7 -> UNKNOWN, 3 -> UNKNOWN),Map(12 -> (12,13), 8 -> (8,9), 
                                                  //| 19 -> (19,20), 23 -> (23,24), 4 -> (4,5), 15 -> (15,16), 11 -> (11,12), 9 ->
                                                  //|  (9,10), 2
                                                  //| Output exceeds cutoff limit.
println(amrOutput)                                //> AMRGraph(Map(12 -> network, 8 -> military, 19 -> UNKNOWN, 23 -> UNKNOWN, 4 -
                                                  //| > attack-01, 15 -> UNKNOWN, 11 -> computer, 9 -> UNKNOWN, 22 -> UNKNOWN, 13 
                                                  //| -> UNKNOWN, 16 -> UNKNOWN, 10 -> UNKNOWN, 21 -> UNKNOWN, 6 -> thing, 1 -> UN
                                                  //| KNOWN, 0 -> UNKNOWN, 20 -> UNKNOWN, 2 -> UNKNOWN, 18 -> UNKNOWN, 7 -> UNKNOW
                                                  //| N, 3 -> UNKNOWN),Map(12 -> (12,13), 8 -> (8,9), 19 -> (19,20), 23 -> (23,24)
                                                  //| , 4 -> (4,5), 15 -> (15,16), 11 -> (11,12), 9 -> (9,10), 22 -> (22,23), 13 -
                                                  //| > (13,14), 16 -> (16,17), 5 -> (5,6), 10 -> (10,11), 21 -> (21,22), 6 -> (6,
                                                  //| 7), 1 -> (1,2), 17 -> (17,18), 14 -> (14,15), 20 -> (20,21), 2 -> (2,3), 18 
                                                  //| -> (18,19), 7 -> (7,8), 3 -> (3,4)),Map((7,12) -> pobj, (12,11) -> nn, (18,1
                                                  //| 6) -> nsubjpass, (3,2) -> nn, (16,15) -> amod, (12,8) -> amod, (4,6) -> dobj
                                                  //| , (6,5) -> det, (4,18) -> advcl, (21,20) -> nn, (19,21) -> pobj, (18,19) -> 
                                                  //| prep, (22,23) -> pobj, (18,17) -> auxpass, (0,4) -> root, (3,1) -> nn, (4,3)
                                                  //|  -> nsubj, (18,13) -> mark, (8,9) -> cc, (21,22) -> prep, (8,10) -> conj, (4
                                                  //| ,7) -> prep, (16,14) -> det))
}