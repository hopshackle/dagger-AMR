package amr

import amr.ImportConcepts.{concept, conceptIndex}
object SimpleDateExpertTest {

val testTuple = Seq((1.0, 1.0, 1.0, 0.0), (1.0, 2.0, 0.0, 5.6))
                                                  //> testTuple  : Seq[(Double, Double, Double, Double)] = List((1.0,1.0,1.0,0.0),
                                                  //|  (1.0,2.0,0.0,5.6))
ImportConcepts.initialise("C:\\AMR\\initialTrainingSet.txt")
 val testData = ImportConcepts.allSentencesAndAMR //> testData  : IndexedSeq[(String, String)] = Vector((2008-05-14,"(d / date-ent
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
                                                  //| 1 :ARG0 p :ARG1 (c / center :mod (r / research-01)) :purpose (b / bolster-01
                                                  //|  :ARG0 p :ARG1 (d / defend-01 :prep-against (a3 / attack :mod (c2 / cyber)))
                                                  //| )))) "), (On 14 May 2008 7 NATO allies signed an agreement to fund a researc
                                                  //| h center.,"(s / sign-01 :ARG0 (p / person :quant 7 :ARG0-of (a / ally-01) :m
                                                  //| od (m / military :name (n / name :op1 "NATO"))) :ARG1 (a2 / agree-01 :ARG1 (
                                                  //| f / fund-01 :ARG0 p :ARG1 (c / center :mod (r / research-01)))) :time (d / d
                                                  //| ate-entity :month 5 :day 14 :year 2008)) "), (The center will bols
                                                  //| Output exceeds cutoff limit.
  val s0 = Sentence(testData(0)._1, testData(0)._2)
                                                  //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.0 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [6.1 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [3.8 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [2.2 sec].
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
  val s0_copy = Sentence(testData(0)._1, testData(0)._2)
                                                  //> s0_copy  : amr.Sentence = Sentence(2008-05-14,
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
 // We then want to apply the Exprt to one, and a non-exprt actionto the other
 
s0.amr                                            //> res0: Option[amr.AMRGraph] = Some(AMRGraph(Map(0.1 -> 14, 0.0 -> 5, 0 -> dat
                                                  //| e-entity, 0.2 -> 2008, ROOT -> ROOT),Map(0 -> (1,4), 0.0 -> (1,4), 0.1 -> (1
                                                  //| ,4), 0.2 -> (1,4)),Map((0,0.0) -> month, (0,0.1) -> day, (0,0.2) -> year, (R
                                                  //| OOT,0) -> ROOT)))
  val expert = new WangXueExpert                  //> expert  : amr.WangXueExpert = amr.WangXueExpert@f0da945
  val expertSystem = WangXueTransitionSystem      //> expertSystem  : amr.WangXueTransitionSystem.type = amr.WangXueTransitionSyst
                                                  //| em$@4803b726
  val startState = expertSystem.init(s0)          //> startState  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(2, 3, 1, 0)
                                                  //| Children:	List()
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> 2008, 2 -> 5, 3 -> 14)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4))
                                                  //| Edges:	Map((0,1) -> root, (1,3) -> dep, (3,2) -> num)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	List()
                                                  //| Mappings:	2 -> 0.0
                                                  //| 1 -> 0.2
                                                  //| 3 -> 0.1
                                                  //| 0 -> ROOT
                                                  //| 
  // val permissibleActions = expertSystem.permissibleActions(startState)
  var expertNextAction = expert.chooseTransition(s0, startState)
                                                  //> WARNING: Found duplicate match for concept attack-01
                                                  //| WARNING: Found duplicate match for concept cyber
                                                  //| expertNextAction  : amr.WangXueAction = InsertNode: date-entity (Ref: 0)
  var expertNextState = expertNextAction(startState)
                                                  //> expertNextState  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(2, 4, 3, 1, 0)
                                                  //| Children:	List()
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> 2008, 2 -> 5, 3 -> 14, 4 -> date-entity)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (2,3))
                                                  //| Edges:	Map((0,1) -> root, (1,3) -> dep, (3,4) -> num, (4,2) -> date-ent
                                                  //| ity#)
                                                  //| InsertedNodes:	Map(4 -> 0)
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	List()
                                                  //| Mappings:	0 -> ROOT
                                                  //| 1 -> 0.2
                                                  //| 2 -> 0.0
                                                  //| 3 -> 0.1
                                                  //| 4 -> 0
                                                  //| InsertNode: date-entity (Ref: 0)
                                                  //| 
  // This should correctly add mapping 2 -> 0 even though specific AMR nod ref was not supplied
  val nonExpertNextAction = Insert(conceptIndex("date-entity"))
                                                  //> nonExpertNextAction  : amr.Insert = InsertNode: date-entity (Ref: )
  val nonExpertNextState = nonExpertNextAction(startState)
                                                  //> nonExpertNextState  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(2, 4, 3, 1, 0)
                                                  //| Children:	List()
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> 2008, 2 -> 5, 3 -> 14, 4 -> date-entity)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (2,3))
                                                  //| Edges:	Map((0,1) -> root, (1,3) -> dep, (3,4) -> num, (4,2) -> date-ent
                                                  //| ity#)
                                                  //| InsertedNodes:	Map(4 -> 0)
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	List()
                                                  //| Mappings:	0 -> ROOT
                                                  //| 1 -> 0.2
                                                  //| 2 -> 0.0
                                                  //| 3 -> 0.1
                                                  //| 4 -> 0
                                                  //| InsertNode: date-entity (Ref: )
                                                  //| 
  val s6 = Sentence(testData(5)._1, testData(5)._2)
                                                  //> s6  : amr.Sentence = Sentence(On 14 May 2008 7 NATO allies signed an agreem
                                                  //| ent to fund a research center.,
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> On, 2 -> 14, 3 -> 5, 4 -> 2008, 5 -> 7, 6 ->
                                                  //|  NATO, 7 -> allies, 8 -> signed, 9 -> an, 10 -> agreement, 11 -> to, 12 -> 
                                                  //| fund, 13 -> a, 14 -> research, 15 -> center)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,1
                                                  //| 2), 12 -> (12,13), 13 -> (13,14), 14 -> (14,15), 15 -> (15,16))
                                                  //| Edges:	Map((0,8) -> root, (1,2) -> pobj, (2,3) -> tmod, (3,4) -> num, (
                                                  //| 7,5) -> num, (7,6) -> nn, (8,1) -> prep, (8,7) -> nsubj, (8,10) -> dobj, (1
                                                  //| 0,9) -> det, (10,12) -> vmod, (12,11) -> aux, (12,15) -> dobj, (15,13) -> d
                                                  //| et, (15,14) -> nn)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	List(),Some(AMRGraph(Map(0.1 -> agree-01, 0.0.0 -> 7, 0.
                                                  //| 0.2.0.0 -> "NATO", 0.1.0 -> fund-01, 0.2.1 -> 14, 0.1.0.0.0 -> research-01,
                                                  //|  0.0 -> person, 0.0.2 -> military, 0.2.0 -> 5, 0.0.1 -> ally-01, 0.1.0.0 ->
                                                  //|  center, 0 -> sign-01, 0.2 -> date-entity, 0.2.2 -> 2008, ROOT -> ROOT, 0.0
                                                  //| .2.0 -> name),Map(0.1 -> (10,11), 0.0.0 -> (5,8), 0.0.2.0.0 -> (6,7), 0.1.0
                                                  //|  -> (12,13), 0.2.1 -> (2,5), 0.1.0.0.0 -> (14,15), 0.0 -> (5,8), 0.2.0 -> (
                                                  //| 2,5), 0.0.1 -> (5,8), 0.1.0.0 -> (15,16), 0 -> (8,9), 0.2 -> (2,5), 0.2.2 -
                                                  //| > (2,5), 0.0.2.0 -> (6,7)),Map((0.0,0.0.0) -> quant, (ROOT,0) -> ROOT, (0.1
                                                  //| ,0.1.0) -> ARG1, (0.1.0,0.1.0.0) -> ARG1, (0.0.2,0.0.2.0) -> name, (0,0.2) 
                                                  //| -> time, (0,0.1) -> ARG1, (0.0.2.0,0.0.2.0.0) -> opN, (0.2,0.2.0) -> month,
                                                  //|  (0.0,0.0.2) -> mod, (0.1.0.0,0.1.0.0.0) -> mod, (0.2,0.2.1) -> day, (0,0.0
                                                  //| ) -> ARG0, (0.1.0,0.0) -> ARG0, (0.0,0.0.1) -> ARG0-of, (0.2,0.2.2) -> year
                                                  //| ))),Map(0 -> ROOT, 5 -> 0.0.0, 10 -> 0.1, 14 -> 0.1.0.0.0, 6 -> 0.0, 2 -> 0
                                                  //| .2.1, 12 -> 0.1.0, 7 -> 0.0.1, 3 -> 0.2.0, 8 -> 0, 4 -> 0.2.2, 15 -> 0.1.0.
                                                  //| 0))
  expertSystem.init(s6)                           //> res1: amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(4, 14, 13, 3, 15, 11, 5, 6, 9, 2, 12, 10, 1, 7, 8, 0)
                                                  //| Children:	List()
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> On, 2 -> 14, 3 -> 5, 4 -> 2008, 5 -> 7, 6 ->
                                                  //|  NATO, 7 -> allies, 8 -> signed, 9 -> an, 10 -> agreement, 11 -> to, 12 -> 
                                                  //| fund, 13 -> a, 14 -> research, 15 -> center)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,1
                                                  //| 2), 12 -> (12,13), 13 -> (13,14), 14 -> (14,15), 15 -> (15,16))
                                                  //| Edges:	Map((0,8) -> root, (1,2) -> pobj, (2,3) -> tmod, (3,4) -> num, (
                                                  //| 7,5) -> num, (7,6) -> nn, (8,1) -> prep, (8,7) -> nsubj, (8,10) -> dobj, (1
                                                  //| 0,9) -> det, (10,12) -> vmod, (12,11) -> aux, (12,15) -> dobj, (15,13) -> d
                                                  //| et, (15,14) -> nn)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	List()
                                                  //| Mappings:	0 -> ROOT
                                                  //| 5 -> 0.0.0
                                                  //| 10 -> 0.1
                                                  //| 14 -> 0.1.0.0.0
                                                  //| 6 -> 0.0
                                                  //| 2 -> 0.2.1
                                                  //| 12 -> 0.1.0
                                                  //| 7 -> 0.0.1
                                                  //| 3 -> 0.2.0
                                                  //| 8 -> 0
                                                  //| 4 -> 0.2.2
                                                  //| 15 -> 0.1.0.0
                                                  //| 
 DeleteNode                                       //> res2: amr.DeleteNode.type = DeleteNode
 NextNode(23).toString                            //> res3: String = NextNode: 23 -> computer
 NextNode(23).name                                //> res4: String = NextNodecomputer
 NextNode(23).getClass                            //> res5: Class[?0] = class amr.NextNode
}