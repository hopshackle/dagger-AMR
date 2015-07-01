package amr

import amr.ImportConcepts.{concept, conceptIndex}
object SimpleDateExpertTest {

val testTuple = Seq((1.0, 1.0, 1.0, 0.0), (1.0, 2.0, 0.0, 5.6))
                                                  //> testTuple  : Seq[(Double, Double, Double, Double)] = List((1.0,1.0,1.0,0.0),
                                                  //|  (1.0,2.0,0.0,5.6))

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
  val s0 = Sentence(testData(0)._1, testData(0)._2)
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
                                                  //| im.crf.ser.gz ... done [2.1 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [3.6 sec].
                                                  //| s0  : amr.Sentence = Sentence(2008-05-14,
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> 2008-05-14)
                                                  //| SpanMap:	Map(1 -> (1,2))
                                                  //| Edges:	Map((0,1) -> root)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set(),Some(AMRGraph(Map(0.1 -> 14, 0.0 -> 5, 0 -> date-e
                                                  //| ntity, 0.2 -> 2008, ROOT -> ROOT),Map(0 -> (1,2), 0.2 -> (1,2)),Map((0,0.0) 
                                                  //| -> month, (0,0.1) -> day, (0,0.2) -> year, (ROOT,0) -> ROOT))),Map(1 -> 0.2,
                                                  //|  0 -> ROOT))
  val s0_copy = Sentence(testData(0)._1, testData(0)._2)
                                                  //> s0_copy  : amr.Sentence = Sentence(2008-05-14,
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> 2008-05-14)
                                                  //| SpanMap:	Map(1 -> (1,2))
                                                  //| Edges:	Map((0,1) -> root)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set(),Some(AMRGraph(Map(0.1 -> 14, 0.0 -> 5, 0 -> date-e
                                                  //| ntity, 0.2 -> 2008, ROOT -> ROOT),Map(0 -> (1,2), 0.2 -> (1,2)),Map((0,0.0) 
                                                  //| -> month, (0,0.1) -> day, (0,0.2) -> year, (ROOT,0) -> ROOT))),Map(1 -> 0.2,
                                                  //|  0 -> ROOT))
 // We then want to apply the Exprt to one, and a non-exprt action to the other
 
s0.amr                                            //> res0: Option[amr.AMRGraph] = Some(AMRGraph(Map(0.1 -> 14, 0.0 -> 5, 0 -> dat
                                                  //| e-entity, 0.2 -> 2008, ROOT -> ROOT),Map(0 -> (1,2), 0.2 -> (1,2)),Map((0,0.
                                                  //| 0) -> month, (0,0.1) -> day, (0,0.2) -> year, (ROOT,0) -> ROOT)))
  val expert = new WangXueExpert                  //> expert  : amr.WangXueExpert = amr.WangXueExpert@1eb5174b
  val expertSystem = new WangXueTransitionSystem  //> expertSystem  : amr.WangXueTransitionSystem = amr.WangXueTransitionSystem@67
                                                  //| 080771
  val startState = expertSystem.init(s0)          //> startState  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(1, 0)
                                                  //| Children:	List()
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> 2008-05-14)
                                                  //| SpanMap:	Map(1 -> (1,2))
                                                  //| Edges:	Map((0,1) -> root)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| Mappings:	1 -> 0.2
                                                  //| 0 -> ROOT
                                                  //| 
  val permissibleActions = expertSystem.permissibleActions(startState)
                                                  //> WARNING: Found duplicate match for concept attack-01
                                                  //| WARNING: Found duplicate match for concept cyber
                                                  //| WARNING: Found duplicate match for concept attack-01
                                                  //| WARNING: Found duplicate match for concept cyber
                                                  //| permissibleActions  : Array[amr.WangXueAction] = Array(NextNode: 14 -> compu
                                                  //| ter, NextNode: 10 -> "Estonia", NextNode: 17 -> civilian, NextNode: 0 -> UNK
                                                  //| NOWN, NextNode: 4 -> fund-01, NextNode: 24 -> cyber, NextNode: 35 -> govern-
                                                  //| 01, NextNode: 6 -> threaten-01, NextNode: 20 -> sign-01, NextNode: 32 -> mil
                                                  //| itary, NextNode: 7 -> center, NextNode: 1 -> defend-01, NextNode: 27 -> atta
                                                  //| ck, NextNode: 13 -> country, NextNode: 11 -> strike-01, NextNode: 12 -> agre
                                                  //| e-01, NextNode: 3 -> name, NextNode: 5 -> date-entity, NextNode: 33 -> and, 
                                                  //| NextNode: 19 -> research-01, NextNode: 23 -> consider-02, NextNode: 15 -> sc
                                                  //| ience, NextNode: 8 -> bolster-01, NextNode: 9 -> thing, NextNode: 21 -> afte
                                                  //| r, NextNode: 36 -> government-organization, NextNode: 28 -> "NATO", NextNode
                                                  //| : 16 -> technology, NextNode: 18 -> "Lithuania", NextNode: 26 -> person, Nex
                                                  //| tNode: 31 -> ROOT, NextNode: 2 -> network, NextNode: 34 -> telecommunication
                                                  //| , NextNode: 29 -> ally-01, NextNode: 25 -> attack-01, NextNode: 22 -> intern
                                                  //| ational, NextNode: 30 -> "Latvia", DeleteNode, InsertNode: date-entity (Ref:
                                                  //|  ), InsertNode: thing (Ref: ), InsertNode: person (Ref: ), InsertNode: milit
                                                  //| ary (Ref: ), InsertNode: country (Ref: ), InsertNode: govern-01 (Ref: ), Ins
                                                  //| ertNode: network (Ref: ), InsertNode: name (Ref: ), InsertNode: and (Ref: ))
                                                  //| 
  var expertNextAction = expert.chooseTransition(s0, startState)
                                                  //> expertNextAction  : amr.WangXueAction = InsertNode: date-entity (Ref: 0)
  var expertNextState = expertNextAction(startState)
                                                  //> expertNextState  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(1, 2, 0)
                                                  //| Children:	List()
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> 2008-05-14, 2 -> date-entity)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (1,2))
                                                  //| Edges:	Map((0,2) -> root, (2,1) -> date-entity#)
                                                  //| InsertedNodes:	Map(2 -> 0)
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| Mappings:	1 -> 0.2
                                                  //| 0 -> ROOT
                                                  //| 2 -> 0
                                                  //| 
  // This should correctly add mapping 2 -> 0 even though specific AMR node ref was not supplied
  val nonExpertNextAction = Insert(conceptIndex("date-entity"))
                                                  //> nonExpertNextAction  : amr.Insert = InsertNode: date-entity (Ref: )
  val nonExpertNextState = nonExpertNextAction(startState)
                                                  //> nonExpertNextState  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(1, 2, 0)
                                                  //| Children:	List()
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> 2008-05-14, 2 -> date-entity)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (1,2))
                                                  //| Edges:	Map((0,2) -> root, (2,1) -> date-entity#)
                                                  //| InsertedNodes:	Map(2 -> 0)
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| Mappings:	1 -> 0.2
                                                  //| 0 -> ROOT
                                                  //| 2 -> 0
                                                  //| 
}