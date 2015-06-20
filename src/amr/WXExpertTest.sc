package amr

object WXExpertTest {


  val testData = AMRGraph.importFile("C:\\AMR\\AMR2.txt")
                                                  //> testData  : IndexedSeq[(String, String)] = Vector((2008-05-14,"(d / date-enti
                                                  //| ty :month 5 :day 14 :year 2008) "), (Estonia (EE); Latvia (LV); Lithuania (LT
                                                  //| ),"(a / and :op1 (c / country :name (n / name :op1 "Estonia")) :op2 (c2 / cou
                                                  //| ntry :name (n3 / name :op1 "Latvia")) :op3 (c3 / country :name (n5 / name :op
                                                  //| 1 "Lithuania"))) "), (International; Government; Telecom; technology; science
                                                  //| ,"(a / and :op1 (i / international) :op2 (g / government-organization :ARG0-o
                                                  //| f (g2 / govern-01)) :op3 (t / telecommunication) :op4 (t2 / technology) :op5 
                                                  //| (s / science)) "), (NATO CONSIDERS cyber attacks a threat to military and civ
                                                  //| ilian computer networks after the Estonian Government was struck by cyber att
                                                  //| acks in 2007.,"(c / consider-02 :ARG0 (m2 / military :name (n / name :op1 "NA
                                                  //| TO")) :ARG1 (a / attack-01 :mod (c2 / cyber)) :ARG2 (t / thing :ARG1-of (t2 /
                                                  //|  threaten-01 :ARG2 (a5 / and :op1 (n2 / network :mod (m / military)) :op2 (n4
                                                  //|  / network :mod (c3 / civilian)) :mod (c4 / computer)))) :time (a3 / after :o
                                                  //| p1 (s / strike-01 :ARG0 (a4 / attack :mod (c6 / cyber)) :ARG1 (g / government
                                                  //| -organization :ARG0-of (g2 / govern-01 :ARG1 (c5 / country :name (n3 / name :
                                                  //| op1 "Estonia")))) :time (d / date-entity :year 2007)))) "), (NATO allies have
                                                  //|  signed an agreement to fund a research center to bolster defenses against cy
                                                  //| ber attacks.,(s / sign-01 :ARG0 (p / person :ARG0-of (a / ally-01) :mod (m / 
                                                  //| military :name (n / name :op1 "NATO"))) :ARG1 (a2 / agree-01 :ARG0 p :ARG1 (f
                                                  //|  / fund-01 :ARG0 p :ARG1 (c / center :mod (r / research-01)) :purpose (b / bo
                                                  //| lster-01 :ARG0 p :ARG1 (d / defend-01 :prep-against (a3 / attack :mod (c2 / c
                                                  //| yber)))))))))
  val s1 = Sentence(testData(1)._1, testData(1)._2)
                                                  //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.0 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [4.2 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [3.4 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [2.4 sec].
                                                  //| s1  : amr.Sentence = Sentence(Estonia (EE); Latvia (LV); Lithuania (LT),
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> Estonia, 3 -> EE, 6 -> Latvia, 8 -> LV, 11 -
                                                  //| > Lithuania, 13 -> LT)
                                                  //| SpanMap:	Map(1 -> (1,2), 3 -> (2,3), 6 -> (3,4), 8 -> (4,5), 11 -> (5,6),
                                                  //|  13 -> (6,7))
                                                  //| Edges:	Map((0,3) -> root, (3,1) -> nn, (3,6) -> dep, (6,8) -> appos, (6
                                                  //| ,11) -> dep, (11,13) -> appos),Some(AMRGraph(Map(0.1 -> country, 0.0.0 -> na
                                                  //| me, 0.1.0 -> name, 0.0 -> country, 0.0.0.0 -> "Estonia", 0.2.0 -> name, 0.1.
                                                  //| 0.0 -> "Latvia", 0 -> and, 0.2 -> country, ROOT -> ROOT, 0.2.0.0 -> "Lithuan
                                                  //| ia"),Map(0.1 -> (3,4), 0.0.0 -> (1,2), 0.1.0 -> (3,4), 0.0 -> (1,2), 0.0.0.0
                                                  //|  -> (1,2), 0.2.0 -> (5,6), 0.1.0.0 -> (3,4), 0.2 -> (5,6), 0.2.0.0 -> (5,6))
                                                  //| ,Map((0.0,0.0.0) -> name, (ROOT,0) -> ROOT, (0.0.0,0.0.0.0) -> opN, (0.1,0.1
                                                  //| .0) -> name, (0.1.0,0.1.0.0) -> opN, (0,0.2) -> opN, (0,0.1) -> opN, (0.2,0.
                                                  //| 2.0) -> name, (0,0.0) -> opN, (0.2.0,0.2.0.0) -> opN))),Map(11 -> 0.2.0.0, 1
                                                  //|  -> 0.0.0.0, 6 -> 0.1.0.0, 0 -> ROOT))
                                                  
  s1.positionToAMR                                //> res0: Map[Int,String] = Map(11 -> 0.2.0.0, 1 -> 0.0.0.0, 6 -> 0.1.0.0, 0 -> 
                                                  //| ROOT)
                                                  
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
  s2.positionToAMR                                //> res1: Map[Int,String] = Map(0 -> ROOT, 10 -> 0.2.0.0.1.0, 20 -> 0.3.0.0.0, 1
                                                  //|  -> 0.0.0.0, 6 -> 0.2.0, 21 -> 0.3.0.0, 9 -> 0.2.0.0, 13 -> 0.3, 2 -> 0, 12 
                                                  //| -> 0.2.0.0.0, 3 -> 0.1.0, 18 -> 0.3.0, 16 -> 0.3.0.1, 11 -> 0.2.0.0.2, 23 ->
                                                  //|  0.3.0.2.0, 8 -> 0.0, 4 -> 0.1, 15 -> 0.3.0.1.0.0.0.0)
  val expert = new WangXueExpertBasic             //> expert  : amr.WangXueExpertBasic = amr.WangXueExpertBasic@3f191845
  val expertSystem = new WangXueTransitionSystem  //> WARNING: Found duplicate match for concept attack-01
                                                  //| WARNING: Found duplicate match for concept cyber
                                                  //| WARNING: Found duplicate match for concept attack-01
                                                  //| WARNING: Found duplicate match for concept cyber
                                                  //| expertSystem  : amr.WangXueTransitionSystem = amr.WangXueTransitionSystem@40
                                                  //| 2c4085
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
                                                  //| ROOT,0) -> ROOT, (0,0.3) -> time, (0.0.0,0.0.0.0) -> opN, (0.2.0.0.1,0.2.0.0
                                                  //| .1.0) -> mod, (0.1,0.1.0) -> mod, (0.3.0,0.3.0.1) -> ARG1, (0.3.0,0.3.0.2) -
                                                  //| > time, (0.3.0.0,0.3.0.0.0) -> mod, (0,0.2) -> ARG2, (0,0.1) -> ARG1, (0.2.0
                                                  //| .0.0,0.2.0.0.0.0) -> mod, (0.2,0.2.0) -> ARG1-of, (0.3,0.3.0) -> opN, (0.3.0
                                                  //| .1.0,0.3.0.1.0.0) -> ARG1, (0.2.0.0,0.2.0.0.1) -> opN, (0.2.0.0,0.2.0.0.0) -
                                                  //| > opN, (0.3.0.1.0.0.0,0.3.0.1.0.0.0.0) -> opN, (0.3.0.1,0.3.0.1.0) -> ARG0-o
                                                  //| f, (0,0.0) -> ARG0, (0.3.0.1.0.0,0.3.0.1.0.0.0) -> name, (0.2.0,0.2.0.0) -> 
                                                  //| ARG2, (0.3.0.2,0.3.0.2.0) -> year, (0.2.0.0,0.2.0.0.2) -> mod)))
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
                                                  //> output  : amr.Sentence = Sentence(NATO CONSIDERS cyber attacks a threat to m
                                                  //| ilitary and civilian computer networks after the Estonian Government was str
                                                  //| uck by cyber attacks in 2007.,
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> "NATO", 2 -> consider-02, 3 -> cyber, 4 -> a
                                                  //| ttack-01, 6 -> threaten-01, 7 -> to, 8 -> military, 9 -> and, 10 -> civilian
                                                  //| , 11 -> computer, 12 -> network, 13 -> after, 15 -> "Estonia", 16 -> governm
                                                  //| ent-organization, 18 -> strike-01, 19 -> by, 20 -> cyber, 21 -> attack, 22 -
                                                  //| > in, 23 -> 2007)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 6 -> (6,7), 
                                                  //| 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,12), 12 -> (12
                                                  //| ,13), 13 -> (13,14), 15 -> (15,16), 16 -> (16,17), 18 -> (18,19), 19 -> (19,
                                                  //| 20), 20 -> (20,21), 21 -> (21,22), 22 -> (22,23), 23 -> (23,24))
                                                  //| Edges:	Map((0,4) -> UNKNOWN, (3,1) -> UNKNOWN, (3,2) -> UNKNOWN, (4,3) 
                                                  //| -> mod, (4,6) -> UNKNOWN, (4,7) -> UNKNOWN, (4,18) -> UNKNOWN, (7,12) -> UNK
                                                  //| NOWN, (8,9) -> UNKNOWN, (8,10) -> UNKNOWN, (12,8) -> UNKNOWN, (12,11) -> UNK
                                                  //| NOWN, (16,15) -> UNKNOWN, (18,13) -> UNKNOWN, (18,16) -> ARG1, (18,19) -> UN
                                                  //| KNOWN, (19,21) -> UNKNOWN, (21,20) -> mod, (21,22) -> UNKNOWN, (22,23) -> UN
                                                  //| KNOWN),Some(AMRGraph(Map(12 -> network, 8 -> military, 19 -> by, 23 -> 2007,
                                                  //|  4 -> attack-01, 15 -> "Estonia", 11 -> computer, 9 -> and, 22 -> in, 13 -> 
                                                  //| after, 16 -> government-organization, 10 -> civilian, 21 -> attack, 6 -> thr
                                                  //| eaten-01, 1 -> "NATO", 0 -> ROOT, 20 -> cyber, 2 -> consider-02, 18 -> strik
                                                  //| e-01, 7 -> to, 3 -> cyber),Map(12 -> (12,13), 8 -> (8,9), 19 -> (19,20), 23 
                                                  //| -> (23,24), 4 -> (4,5), 15 -> (15,16), 11 -> (11,12), 9 -> (9,10), 22 -> (22
                                                  //| ,23), 13 -> (13,14), 16 -> (16,17), 10 -> (10,11), 21 -> (21,22), 6 -> (6,7)
                                                  //| , 1 -> (1,2), 20 -> (20,21), 2 -> (2,3), 18 -> (18,19), 7 -> (7,8), 3 -> (3,
                                                  //| 4)),Map((7,12) -> UNKNOWN, (12,11) -> UNKNOWN, (18,16) -> ARG1, (3,2) -> UNK
                                                  //| NOWN, (16,15) -> U
                                                  //| Output exceeds cutoff limit.
val loss = new WangXueLossFunction                //> loss  : amr.WangXueLossFunction = amr.WangXueLossFunction@710c2b53
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
                                                  //| # ::edge	ROOT	0	ROOT
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
 val outputAdv = RunDagger.sampleTrajectory(s1, "C:\\AMR\\WangXueExpert_output.txt", new WangXueExpert)
                                                  //> outputAdv  : amr.Sentence = Sentence(Estonia (EE); Latvia (LV); Lithuania (L
                                                  //| T),
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> "Estonia", 3 -> EE, 6 -> "Latvia", 11 -> "Li
                                                  //| thuania", 12 -> name, 13 -> country, 14 -> and, 15 -> name, 16 -> name, 17 -
                                                  //| > country, 18 -> country)
                                                  //| SpanMap:	Map(1 -> (1,2), 3 -> (2,3), 6 -> (3,4), 11 -> (5,6), 12 -> (5,6)
                                                  //| , 13 -> (5,6), 14 -> (5,6), 15 -> (1,2), 16 -> (3,4), 17 -> (1,2), 18 -> (3,
                                                  //| 4))
                                                  //| Edges:	Map((0,3) -> UNKNOWN, (3,14) -> UNKNOWN, (3,17) -> UNKNOWN, (3,1
                                                  //| 8) -> UNKNOWN, (12,11) -> opN, (13,12) -> name, (14,13) -> opN, (15,1) -> op
                                                  //| N, (16,6) -> opN, (17,15) -> name, (18,16) -> name),Some(AMRGraph(Map(12 -> 
                                                  //| name, 15 -> name, 11 -> "Lithuania", 13 -> country, 16 -> name, 6 -> "Latvia
                                                  //| ", 1 -> "Estonia", 17 -> country, 14 -> and, 0 -> ROOT, 18 -> country, 3 -> 
                                                  //| EE),Map(12 -> (5,6), 15 -> (1,2), 11 -> (5,6), 13 -> (5,6), 16 -> (3,4), 6 -
                                                  //| > (3,4), 1 -> (1,2), 17 -> (1,2), 14 -> (5,6), 18 -> (3,4), 3 -> (2,3)),Map(
                                                  //| (12,11) -> opN, (3,17) -> UNKNOWN, (18,16) -> name, (15,1) -> opN, (3,18) ->
                                                  //|  UNKNOWN, (0,3) -> UNKNOWN, (16,6) -> opN, (3,14) -> UNKNOWN, (14,13) -> opN
                                                  //| , (17,15) -> name, (13,12) -> name))),Map(0 -> ROOT, 14 -> 14, 1 -> 1, 6 -> 
                                                  //| 6, 13 -> 13, 17 -> 17, 12 -> 12, 3 -> 3, 18 -> 18, 16 -> 16, 11 -> 11, 15 ->
                                                  //|  15))
outputAdv                                         //> res6: amr.Sentence = Sentence(Estonia (EE); Latvia (LV); Lithuania (LT),
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> "Estonia", 3 -> EE, 6 -> "Latvia", 11 -> "Li
                                                  //| thuania", 12 -> name, 13 -> country, 14 -> and, 15 -> name, 16 -> name, 17 -
                                                  //| > country, 18 -> country)
                                                  //| SpanMap:	Map(1 -> (1,2), 3 -> (2,3), 6 -> (3,4), 11 -> (5,6), 12 -> (5,6)
                                                  //| , 13 -> (5,6), 14 -> (5,6), 15 -> (1,2), 16 -> (3,4), 17 -> (1,2), 18 -> (3,
                                                  //| 4))
                                                  //| Edges:	Map((0,3) -> UNKNOWN, (3,14) -> UNKNOWN, (3,17) -> UNKNOWN, (3,1
                                                  //| 8) -> UNKNOWN, (12,11) -> opN, (13,12) -> name, (14,13) -> opN, (15,1) -> op
                                                  //| N, (16,6) -> opN, (17,15) -> name, (18,16) -> name),Some(AMRGraph(Map(12 -> 
                                                  //| name, 15 -> name, 11 -> "Lithuania", 13 -> country, 16 -> name, 6 -> "Latvia
                                                  //| ", 1 -> "Estonia", 17 -> country, 14 -> and, 0 -> ROOT, 18 -> country, 3 -> 
                                                  //| EE),Map(12 -> (5,6), 15 -> (1,2), 11 -> (5,6), 13 -> (5,6), 16 -> (3,4), 6 -
                                                  //| > (3,4), 1 -> (1,2), 17 -> (1,2), 14 -> (5,6), 18 -> (3,4), 3 -> (2,3)),Map(
                                                  //| (12,11) -> opN, (3,17) -> UNKNOWN, (18,16) -> name, (15,1) -> opN, (3,18) ->
                                                  //|  UNKNOWN, (0,3) -> UNKNOWN, (16,6) -> opN, (3,14) -> UNKNOWN, (14,13) -> opN
                                                  //| , (17,15) -> name, (13,12) -> name))),Map(0 -> ROOT, 14 -> 14, 1 -> 1, 6 -> 
                                                  //| 6, 13 -> 13, 17 -> 17, 12 -> 12, 3 -> 3, 18 -> 18, 16 -> 16, 11 -> 11, 15 ->
                                                  //|  15))
}