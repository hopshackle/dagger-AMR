package amr

object SmatchTest {
  val testData = AMRGraph.importFile("C://AMR//initialTrainingSetAmended.txt")
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
  val expert = new WangXueExpert                  //> expert  : amr.WangXueExpert = amr.WangXueExpert@548a9f61
  val expertSystem = new WangXueTransitionSystem  //> expertSystem  : amr.WangXueTransitionSystem = amr.WangXueTransitionSystem@17
                                                  //| 53acfe
  val s0 = Sentence(testData(0)._1, testData(0)._2)
                                                  //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.3 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [3.9 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [4.0 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [2.6 sec].
                                                  //| s0  : amr.Sentence = Sentence(2008-05-14,
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> 2008, 2 -> 5, 3 -> 14)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4))
                                                  //| Edges:	Map((0,1) -> root, (1,3) -> dep, (3,2) -> num)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	List(),Some(AMRGraph(Map(0.1 -> 14, 0.0 -> 5, 0 -> date-
                                                  //| entity, 0.2 -> 2008, ROOT -> ROOT),Map(0 -> (1,4), 0.0 -> (1,4), 0.1 -> (1,4
                                                  //| ), 0.2 -> (1,4)),Map((0,0.0) -> month, (0,0.1) -> day, (0,0.2) -> year, (ROO
                                                  //| T,0) -> ROOT))),Map(2 -> 0.0, 1 -> 0.2, 3 -> 0.1, 0 -> ROOT))
  val amr0 = s0.amr.get                           //> amr0  : amr.AMRGraph = AMRGraph(Map(0.1 -> 14, 0.0 -> 5, 0 -> date-entity, 0
                                                  //| .2 -> 2008, ROOT -> ROOT),Map(0 -> (1,4), 0.0 -> (1,4), 0.1 -> (1,4), 0.2 ->
                                                  //|  (1,4)),Map((0,0.0) -> month, (0,0.1) -> day, (0,0.2) -> year, (ROOT,0) -> R
                                                  //| OOT))
  Smatch.initialMap(amr0, amr0)                   //> res0: Map[String,String] = Map(0.1 -> 0.1, 0.0 -> 0.0, 0 -> 0, 0.2 -> 0.2, R
                                                  //| OOT -> ROOT)
  Smatch.initialMap2(amr0, amr0)                  //> res1: Map[String,String] = Map(0.1 -> 0.1, 0.0 -> 0.0, 0 -> 0, 0.2 -> 0.2, R
                                                  //| OOT -> ROOT)
  val s1 = Sentence(testData(1)._1, testData(1)._2)
                                                  //> s1  : amr.Sentence = Sentence(Estonia (EE); Latvia (LV); Lithuania (LT); Uni
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
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	List(),Some(AMRGraph(Map(0.1 -> country, 0.0.0 -> name, 
                                                  //| 0.1.0 -> name, 0.0 -> country, 0.0.0.0 -> "Estonia", 0.2.0 -> name, 0.3.0.0 
                                                  //| -> "United", 0.3.0 -> name, 0.1.0.0 -> "Latvia", 0.3 -> country, 0 -> and, 0
                                                  //| .3.0.1 -> "States", 0.2 -> country, ROOT -> ROOT, 0.2.0.0 -> "Lithuania"),Ma
                                                  //| p(0.1 -> (3,4), 0.0.0 -> (1,2), 0.1.0 -> (3,4), 0.0 -> (1,2), 0.0.0.0 -> (1,
                                                  //| 2), 0.2.0 -> (5,6), 0.3.0.0 -> (7,9), 0.3.0 -> (7,9), 0.1.0.0 -> (3,4), 0.3 
                                                  //| -> (7,9), 0.3.0.1 -> (7,9), 0.2 -> (5,6), 0.2.0.0 -> (5,6)),Map((0.0,0.0.0) 
                                                  //| -> name, (0.3.0,0.3.0.0) -> opN, (ROOT,0) -> ROOT, (0,0.3) -> opN, (0.0.0,0.
                                                  //| 0.0.0) -> opN, (0.1,0.1.0) -> name, (0.3.0,0.3.0.1) -> opN, (0.1.0,0.1.0.0) 
                                                  //| -> opN, (0,0.2) -> opN, (0,0.1) -> opN, (0.2,0.2.0) -> name, (0.3,0.3.0) -> 
                                                  //| name, (0,0.0) -> opN, (0.2.0,0.2.0.0) -> opN))),Map(0 -> ROOT, 1 -> 0.0.0.0,
                                                  //|  6 -> 0.1.0.0, 17 -> 0.3.0.1, 16 -> 0.3.0.0, 11 -> 0.2.0.0))
  val amr1 = s1.amr.get                           //> amr1  : amr.AMRGraph = AMRGraph(Map(0.1 -> country, 0.0.0 -> name, 0.1.0 -> 
                                                  //| name, 0.0 -> country, 0.0.0.0 -> "Estonia", 0.2.0 -> name, 0.3.0.0 -> "Unite
                                                  //| d", 0.3.0 -> name, 0.1.0.0 -> "Latvia", 0.3 -> country, 0 -> and, 0.3.0.1 ->
                                                  //|  "States", 0.2 -> country, ROOT -> ROOT, 0.2.0.0 -> "Lithuania"),Map(0.1 -> 
                                                  //| (3,4), 0.0.0 -> (1,2), 0.1.0 -> (3,4), 0.0 -> (1,2), 0.0.0.0 -> (1,2), 0.2.0
                                                  //|  -> (5,6), 0.3.0.0 -> (7,9), 0.3.0 -> (7,9), 0.1.0.0 -> (3,4), 0.3 -> (7,9),
                                                  //|  0.3.0.1 -> (7,9), 0.2 -> (5,6), 0.2.0.0 -> (5,6)),Map((0.0,0.0.0) -> name, 
                                                  //| (0.3.0,0.3.0.0) -> opN, (ROOT,0) -> ROOT, (0,0.3) -> opN, (0.0.0,0.0.0.0) ->
                                                  //|  opN, (0.1,0.1.0) -> name, (0.3.0,0.3.0.1) -> opN, (0.1.0,0.1.0.0) -> opN, (
                                                  //| 0,0.2) -> opN, (0,0.1) -> opN, (0.2,0.2.0) -> name, (0.3,0.3.0) -> name, (0,
                                                  //| 0.0) -> opN, (0.2.0,0.2.0.0) -> opN))
  Smatch.initialMap(amr1, amr1)                   //> res2: Map[String,String] = Map(0.1 -> 0.1, 0.0.0 -> 0.0.0, 0.1.0 -> 0.1.0, 0
                                                  //| .0 -> 0.0, 0.0.0.0 -> 0.0.0.0, 0.2.0 -> 0.2.0, 0.3.0.0 -> 0.3.0.0, 0.3.0 -> 
                                                  //| 0.3.0, 0.1.0.0 -> 0.1.0.0, 0.3 -> 0.2, 0 -> 0, 0.3.0.1 -> 0.3.0.1, 0.2 -> 0.
                                                  //| 3, ROOT -> ROOT, 0.2.0.0 -> 0.2.0.0)
  Smatch.initialMap2(amr1, amr1)                  //> res3: Map[String,String] = Map(0.1 -> 0.1, 0.0.0 -> 0.2.0, 0.1.0 -> 0.1.0, 0
                                                  //| .0 -> 0.0, 0.0.0.0 -> 0.0.0.0, 0.2.0 -> 0.0.0, 0.3.0.0 -> 0.3.0.0, 0.3.0 -> 
                                                  //| 0.3.0, 0.1.0.0 -> 0.1.0.0, 0.3 -> 0.3, 0 -> 0, 0.3.0.1 -> 0.3.0.1, 0.2 -> 0.
                                                  //| 2, ROOT -> ROOT, 0.2.0.0 -> 0.2.0.0)
  Smatch.fScore(amr0, amr0, 1, 1000)              //> res4: (Double, Double, Double, Double, Double) = (1.0,1.0,1.0,0.0,9.0)
  Smatch.fScore(amr1, amr1, 1, 1000)              //> res5: (Double, Double, Double, Double, Double) = (1.0,1.0,1.0,0.0,29.0)
  val amrGraph = RunDagger.sampleTrajectory(s0).amr.get
                                                  //> WARNING: Found duplicate match for concept attack-01
                                                  //| WARNING: Found duplicate match for concept cyber
                                                  //| amrGraph  : amr.AMRGraph = AMRGraph(Map(4 -> date-entity, 1 -> 2008, 0 -> RO
                                                  //| OT, 2 -> 5, 3 -> 14),Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (2,3)),Map
                                                  //| ((4,2) -> month, (4,3) -> day, (0,4) -> ROOT, (4,1) -> year))
  amr0.nodes                                      //> res6: Map[String,String] = Map(0.1 -> 14, 0.0 -> 5, 0 -> date-entity, 0.2 ->
                                                  //|  2008, ROOT -> ROOT)
  amrGraph.nodes                                  //> res7: Map[String,String] = Map(4 -> date-entity, 1 -> 2008, 0 -> ROOT, 2 -> 
                                                  //| 5, 3 -> 14)
  Smatch.initialMap(amrGraph, amr0)               //> res8: Map[String,String] = Map(0.1 -> 3, 0.0 -> 2, 0 -> 4, 0.2 -> 1, ROOT ->
                                                  //|  0)
  Smatch.initialMap2(amrGraph, amr0)              //> res9: Map[String,String] = Map(0.1 -> 3, 0.0 -> 2, 0 -> 4, 0.2 -> 1, ROOT ->
                                                  //|  0)
  val fScore = Smatch.fScore(amrGraph, amr0, 1, 500)
                                                  //> fScore  : (Double, Double, Double, Double, Double) = (1.0,1.0,1.0,0.0,9.0)
 Smatch.naiveFScore(amrGraph, amr0)               //> res10: (Double, Double, Double, Double, Double) = (1.0,1.0,1.0,0.0,9.0)
 val amrGraph1 = RunDagger.sampleTrajectory(s1).amr.get
                                                  //> amrGraph1  : amr.AMRGraph = AMRGraph(Map(19 -> country, 23 -> name, 11 -> "L
                                                  //| ithuania", 22 -> country, 26 -> country, 24 -> country, 16 -> "United", 21 -
                                                  //| > name, 6 -> "Latvia", 1 -> "Estonia", 17 -> States, 25 -> name, 0 -> ROOT, 
                                                  //| 20 -> and, 18 -> name),Map(19 -> (7,8), 23 -> (5,6), 11 -> (5,6), 22 -> (3,4
                                                  //| ), 26 -> (1,2), 24 -> (5,6), 16 -> (7,8), 21 -> (3,4), 6 -> (3,4), 1 -> (1,2
                                                  //| ), 17 -> (8,9), 25 -> (1,2), 20 -> (7,8), 18 -> (7,8)),Map((20,22) -> opN, (
                                                  //| 18,16) -> opN, (21,6) -> opN, (20,26) -> opN, (25,1) -> opN, (24,23) -> name
                                                  //| , (19,18) -> name, (0,20) -> ROOT, (20,19) -> opN, (26,25) -> name, (23,11) 
                                                  //| -> opN, (22,21) -> name, (11,17) -> UNKNOWN, (20,24) -> opN))
  amr1.nodes                                      //> res11: Map[String,String] = Map(0.1 -> country, 0.0.0 -> name, 0.1.0 -> name
                                                  //| , 0.0 -> country, 0.0.0.0 -> "Estonia", 0.2.0 -> name, 0.3.0.0 -> "United", 
                                                  //| 0.3.0 -> name, 0.1.0.0 -> "Latvia", 0.3 -> country, 0 -> and, 0.3.0.1 -> "St
                                                  //| ates", 0.2 -> country, ROOT -> ROOT, 0.2.0.0 -> "Lithuania")
  amrGraph1.nodes                                 //> res12: Map[String,String] = Map(19 -> country, 23 -> name, 11 -> "Lithuania"
                                                  //| , 22 -> country, 26 -> country, 24 -> country, 16 -> "United", 21 -> name, 6
                                                  //|  -> "Latvia", 1 -> "Estonia", 17 -> States, 25 -> name, 0 -> ROOT, 20 -> and
                                                  //| , 18 -> name)
  Smatch.initialMap(amrGraph1, amr1)              //> res13: Map[String,String] = Map(0.1 -> 19, 0.0.0 -> 18, 0.1.0 -> 25, 0.0 -> 
                                                  //| 26, 0.0.0.0 -> 1, 0.2.0 -> 21, 0.3.0.0 -> 16, 0.3.0 -> 23, 0.1.0.0 -> 6, 0.3
                                                  //|  -> 24, 0 -> 20, 0.3.0.1 -> 17, 0.2 -> 22, ROOT -> 0, 0.2.0.0 -> 11)
  Smatch.initialMap2(amrGraph1, amr1)             //> res14: Map[String,String] = Map(0.1 -> 22, 0.0.0 -> 25, 0.1.0 -> 21, 0.0 -> 
                                                  //| 24, 0.0.0.0 -> 1, 0.2.0 -> 23, 0.3.0.0 -> 16, 0.3.0 -> 18, 0.1.0.0 -> 6, 0.3
                                                  //|  -> 26, 0 -> 20, 0.3.0.1 -> 17, 0.2 -> 19, ROOT -> 0, 0.2.0.0 -> 11)
  val fScore1 = Smatch.fScore(amrGraph1, amr1, 1, 500)
                                                  //> fScore1  : (Double, Double, Double, Double, Double) = (0.9310344827586207,0
                                                  //| .9310344827586207,0.9310344827586207,4.0,27.0)
 Smatch.naiveFScore(amrGraph1, amr1)              //> res15: (Double, Double, Double, Double, Double) = (0.9310344827586207,0.931
                                                  //| 0344827586207,0.9310344827586207,4.0,27.0)
}