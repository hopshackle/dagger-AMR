package amr

import ImportConcepts.{conceptIndex}

object AMRMappingForNonExpertActions {
ImportConcepts.initialise("C:\\AMR\\AMR2.txt")
val testData = AMRGraph.importFile("C:\\AMR\\AMR2.txt")
                                                  //> testData  : IndexedSeq[(String, String)] = Vector((2008-05-14,"(d / date-ent
                                                  //| ity :month 5 :day 14 :year 2008) "), (Estonia (EE); Latvia (LV); Lithuania (
                                                  //| LT),"(a / and :op1 (c / country :name (n / name :op1 "Estonia")) :op2 (c2 / 
                                                  //| country :name (n3 / name :op1 "Latvia")) :op3 (c3 / country :name (n5 / name
                                                  //|  :op1 "Lithuania"))) "), (International; Government; Telecom; technology; sc
                                                  //| ience,"(a / and :op1 (i / international) :op2 (g / government-organization :
                                                  //| ARG0-of (g2 / govern-01)) :op3 (t / telecommunication) :op4 (t2 / technology
                                                  //| ) :op5 (s / science)) "), (NATO CONSIDERS cyber attacks a threat to military
                                                  //|  and civilian computer networks after the Estonian Government was struck by 
                                                  //| cyber attacks in 2007.,"(c / consider-02 :ARG0 (m2 / military :name (n / nam
                                                  //| e :op1 "NATO")) :ARG1 (a / attack-01 :mod (c2 / cyber)) :ARG2 (t / thing :AR
                                                  //| G1-of (t2 / threaten-01 :ARG2 (a5 / and :op1 (n2 / network :mod (m / militar
                                                  //| y)) :op2 (n4 / network :mod (c3 / civilian)) :mod (c4 / computer)))) :time (
                                                  //| a3 / after :op1 (s / strike-01 :ARG0 (a4 / attack :mod (c6 / cyber)) :ARG1 (
                                                  //| g / government-organization :ARG0-of (g2 / govern-01 :ARG1 (c5 / country :na
                                                  //| me (n3 / name :op1 "Estonia")))) :time (d / date-entity :year 2007)))) "), (
                                                  //| NATO allies have signed an agreement to fund a research center to bolster de
                                                  //| fenses against cyber attacks.,(s / sign-01 :ARG0 (p / person :ARG0-of (a / a
                                                  //| lly-01) :mod (m / military :name (n / name :op1 "NATO"))) :ARG1 (a2 / agree-
                                                  //| 01 :ARG0 p :ARG1 (f / fund-01 :ARG0 p :ARG1 (c / center :mod (r / research-0
                                                  //| 1)) :purpose (b / bolster-01 :ARG0 p :ARG1 (d / defend-01 :prep-against (a3 
                                                  //| / attack :mod (c2 / cyber)))))))))
val s1 = Sentence(testData(1)._1, testData(1)._2) //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [2.0 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [10.4 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [3.7 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [9.9 sec].
                                                  //| WARNING: Found duplicate match for concept and
                                                  //| s1  : amr.Sentence = Sentence(Estonia (EE); Latvia (LV); Lithuania (LT),
                                                  //| NodeMap:	Map(1 -> Estonia, 2 -> (, 3 -> EE, 4 -> ), 5 -> ;, 6 -> Latvia, 
                                                  //| 7 -> (, 8 -> LV, 9 -> ), 10 -> ;, 11 -> Lithuania, 12 -> (, 13 -> LT, 14 -> 
                                                  //| ))
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,12
                                                  //| ), 12 -> (12,13), 13 -> (13,14), 14 -> (14,15))
                                                  //| Edges:	Map((3,1) -> nn, (3,2) -> punct, (3,4) -> punct, (3,5) -> punct,
                                                  //|  (3,6) -> dep, (6,8) -> appos, (6,10) -> punct, (6,11) -> dep, (8,7) -> punc
                                                  //| t, (8,9) -> punct, (11,13) -> appos, (13,12) -> punct, (13,14) -> punct)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map(),Some(AMRGraph(Map(0.1 -> country, 0.0.0 -> name, 0
                                                  //| .1.0 -> name, 0.0 -> country, 0.0.0.0 -> "Estonia", 0.2.0 -> name, 0.1.0.0 -
                                                  //| > "Latvia", 0 -> and, 0.2 -> country, 0.2.0.0 -> "Lithuania"),Map(0.1 -> (6,
                                                  //| 7), 0.0.0 -> (1,2), 0.1.0 -> (6,7), 0.0 -> (1,2), 0.0.0.0 -> (1,2), 0.2.0 ->
                                                  //|  (11,12), 0.1.0.0 -> (6,7), 0 -> (5,6), 0.2 -> (11,12), 0.2.0.0 -> (11,12)),
                                                  //| Map((0.0,0.0.0) -> name, (0.0.0,0.0.0.0) -> opN, (0.1,0.1.0) -> name, (0.1.0
                                                  //| ,0.1.0.0) -> opN, (0,0.2) -> opN, (0,0.1) -> opN, (0.2,0.2.0) -> name, (0,0.
                                                  //| 0) -> opN, (0.2.0,0.2.0.0) -> opN),List())),Map(11 -> 0.2.0.0, 6 -> 0.1.0.0,
                                                  //|  1 -> 0.0.0.0, 5 -> 0))
                                                 
s1.amr.get                                        //> res0: amr.AMRGraph = AMRGraph(Map(0.1 -> country, 0.0.0 -> name, 0.1.0 -> na
                                                  //| me, 0.0 -> country, 0.0.0.0 -> "Estonia", 0.2.0 -> name, 0.1.0.0 -> "Latvia"
                                                  //| , 0 -> and, 0.2 -> country, 0.2.0.0 -> "Lithuania"),Map(0.1 -> (6,7), 0.0.0 
                                                  //| -> (1,2), 0.1.0 -> (6,7), 0.0 -> (1,2), 0.0.0.0 -> (1,2), 0.2.0 -> (11,12), 
                                                  //| 0.1.0.0 -> (6,7), 0 -> (5,6), 0.2 -> (11,12), 0.2.0.0 -> (11,12)),Map((0.0,0
                                                  //| .0.0) -> name, (0.0.0,0.0.0.0) -> opN, (0.1,0.1.0) -> name, (0.1.0,0.1.0.0) 
                                                  //| -> opN, (0,0.2) -> opN, (0,0.1) -> opN, (0.2,0.2.0) -> name, (0,0.0) -> opN,
                                                  //|  (0.2.0,0.2.0.0) -> opN),List())
s1.dependencyTree                                 //> res1: amr.DependencyTree = 
                                                  //| NodeMap:	Map(1 -> Estonia, 2 -> (, 3 -> EE, 4 -> ), 5 -> ;, 6 -> Latvia, 
                                                  //| 7 -> (, 8 -> LV, 9 -> ), 10 -> ;, 11 -> Lithuania, 12 -> (, 13 -> LT, 14 -> 
                                                  //| ))
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,12
                                                  //| ), 12 -> (12,13), 13 -> (13,14), 14 -> (14,15))
                                                  //| Edges:	Map((3,1) -> nn, (3,2) -> punct, (3,4) -> punct, (3,5) -> punct,
                                                  //|  (3,6) -> dep, (6,8) -> appos, (6,10) -> punct, (6,11) -> dep, (8,7) -> punc
                                                  //| t, (8,9) -> punct, (11,13) -> appos, (13,12) -> punct, (13,14) -> punct)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map()
                  
val a1 = DeleteNode                               //> a1  : amr.DeleteNode.type = DeleteNode
val a2 = DeleteNode                               //> a2  : amr.DeleteNode.type = DeleteNode
val a3 = Insert(conceptIndex("name"))             //> WARNING: Found duplicate match for concept and
                                                  //| WARNING: Found duplicate match for concept and
                                                  //| WARNING: Found duplicate match for concept and
                                                  //| WARNING: Found duplicate match for concept and
                                                  //| WARNING: Found duplicate match for concept attack-01
                                                  //| WARNING: Found duplicate match for concept cyber
                                                  //| a3  : amr.Insert = InsertNode: name (Ref: )
val a4 = NextNode(conceptIndex(""""Lithuania""""))//> a4  : amr.NextNode = NextNode: 0 -> UNKNOWN
val a5 = Insert(conceptIndex("country"))          //> a5  : amr.Insert = InsertNode: country (Ref: )
// These being the first five actions undertaken by the Expert policy
// We now check two things. Firstly that the state picks up the correct AMR ref labels (0.2.0 and 0.2 respectively)
// and secondly that If we now call the Expert policy, it uses this information, and does not
// try and insert a node
val trans = WangXueTransitionSystem               //> trans  : amr.WangXueTransitionSystem.type = amr.WangXueTransitionSystem$@275
                                                  //| 08c5d
s1.amr.get.getRoots                               //> res2: Set[String] = Set(0)
val startState = trans.init(s1)                   //> startState  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(12, 14, 9, 7, 13, 10, 8, 11, 4, 1, 6, 5, 2, 3)
                                                  //| Children:	List()
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(1 -> Estonia, 2 -> (, 3 -> EE, 4 -> ), 5 -> ;, 6 -> Latvia, 
                                                  //| 7 -> (, 8 -> LV, 9 -> ), 10 -> ;, 11 -> Lithuania, 12 -> (, 13 -> LT, 14 -> 
                                                  //| ))
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,12
                                                  //| ), 12 -> (12,13), 13 -> (13,14), 14 -> (14,15))
                                                  //| Edges:	Map((3,1) -> nn, (3,2) -> punct, (3,4) -> punct, (3,5) -> punct,
                                                  //|  (3,6) -> dep, (6,8) -> appos, (6,10) -> punct, (6,11) -> dep, (8,7) -> punc
                                                  //| t, (8,9) -> punct, (11,13) -> appos, (13,12) -> punct, (13,14) -> punct)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map()
                                                  //| Mappings:	11 -> 0.2.0.0
                                                  //| 6 -> 0.1.0.0
                                                  //| 1 -> 0.0.0.0
                                                  //| 5 -> 0
                                                  //| 
val state1 = a1(startState)                       //> state1  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(14, 9, 7, 13, 10, 8, 11, 4, 1, 6, 5, 2, 3)
                                                  //| Children:	List()
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(1 -> Estonia, 2 -> (, 3 -> EE, 4 -> ), 5 -> ;, 6 -> Latvia, 
                                                  //| 7 -> (, 8 -> LV, 9 -> ), 10 -> ;, 11 -> Lithuania, 13 -> LT, 14 -> ))
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,12
                                                  //| ), 13 -> (13,14), 14 -> (14,15))
                                                  //| Edges:	Map((3,1) -> nn, (3,2) -> punct, (3,4) -> punct, (3,5) -> punct,
                                                  //|  (3,6) -> dep, (6,8) -> appos, (6,10) -> punct, (6,11) -> dep, (8,7) -> punc
                                                  //| t, (8,9) -> punct, (11,13) -> appos, (13,14) -> punct)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map(13 -> List((12,()))
                                                  //| Mappings:	11 -> 0.2.0.0
                                                  //| 6 -> 0.1.0.0
                                                  //| 1 -> 0.0.0.0
                                                  //| 5 -> 0
                                                  //| DeleteNode
                                                  //| 
val state2 = a2(state1)                           //> state2  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(9, 7, 13, 10, 8, 11, 4, 1, 6, 5, 2, 3)
                                                  //| Children:	List()
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(1 -> Estonia, 2 -> (, 3 -> EE, 4 -> ), 5 -> ;, 6 -> Latvia, 
                                                  //| 7 -> (, 8 -> LV, 9 -> ), 10 -> ;, 11 -> Lithuania, 13 -> LT)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,12
                                                  //| ), 13 -> (13,14))
                                                  //| Edges:	Map((3,1) -> nn, (3,2) -> punct, (3,4) -> punct, (3,5) -> punct,
                                                  //|  (3,6) -> dep, (6,8) -> appos, (6,10) -> punct, (6,11) -> dep, (8,7) -> punc
                                                  //| t, (8,9) -> punct, (11,13) -> appos)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map(13 -> List((14,)), (12,()))
                                                  //| Mappings:	11 -> 0.2.0.0
                                                  //| 6 -> 0.1.0.0
                                                  //| 1 -> 0.0.0.0
                                                  //| 5 -> 0
                                                  //| DeleteNode
                                                  //| DeleteNode
                                                  //| 
val state3 = a3(state2)                           //> state3  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(9, 7, 14, 13, 10, 8, 11, 4, 1, 6, 5, 2, 3)
                                                  //| Children:	List()
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(1 -> Estonia, 2 -> (, 3 -> EE, 4 -> ), 5 -> ;, 6 -> Latvia, 
                                                  //| 7 -> (, 8 -> LV, 9 -> ), 10 -> ;, 11 -> Lithuania, 13 -> LT, 14 -> name)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,12
                                                  //| ), 13 -> (13,14), 14 -> (9,10))
                                                  //| Edges:	Map((3,1) -> nn, (3,2) -> punct, (3,4) -> punct, (3,5) -> punct,
                                                  //|  (3,6) -> dep, (6,8) -> appos, (6,10) -> punct, (6,11) -> dep, (8,7) -> punc
                                                  //| t, (8,14) -> punct, (11,13) -> appos, (14,9) -> name#)
                                                  //| InsertedNodes:	Map(14 -> )
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map(13 -> List((14,)), (12,()))
                                                  //| Mappings:	5 -> 0
                                                  //| 14 -> 
                                                  //| 1 -> 0.0.0.0
                                                  //| 6 -> 0.1.0.0
                                                  //| 11 -> 0.2.0.0
                                                  //| InsertNode: name (Ref: )
                                                  //| DeleteNode
                                                  //| DeleteNode
                                                  //| 
val state4 = a4(state3)                           //> state4  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(7, 14, 13, 10, 8, 11, 4, 1, 6, 5, 2, 3)
                                                  //| Children:	List()
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(1 -> Estonia, 2 -> (, 3 -> EE, 4 -> ), 5 -> ;, 6 -> Latvia, 
                                                  //| 7 -> (, 8 -> LV, 9 -> ), 10 -> ;, 11 -> Lithuania, 13 -> LT, 14 -> name)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,1
                                                  //| 2), 13 -> (13,14), 14 -> (9,10))
                                                  //| Edges:	Map((3,1) -> nn, (3,2) -> punct, (3,4) -> punct, (3,5) -> punct,
                                                  //|  (3,6) -> dep, (6,8) -> appos, (6,10) -> punct, (6,11) -> dep, (8,7) -> pun
                                                  //| ct, (8,14) -> punct, (11,13) -> appos, (14,9) -> name#)
                                                  //| InsertedNodes:	Map(14 -> )
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map(13 -> List((14,)), (12,()))
                                                  //| Mappings:	5 -> 0
                                                  //| 14 -> 
                                                  //| 1 -> 0.0.0.0
                                                  //| 6 -> 0.1.0.0
                                                  //| 11 -> 0.2.0.0
                                                  //| NextNode: 0 -> UNKNOWN
                                                  //| InsertNode: name (Ref: )
                                                  //| DeleteNode
                                                  //| 
val state5 = a5(state4)                           //> state5  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(7, 15, 14, 13, 10, 8, 11, 4, 1, 6, 5, 2, 3)
                                                  //| Children:	List()
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(1 -> Estonia, 2 -> (, 3 -> EE, 4 -> ), 5 -> ;, 6 -> Latvia, 
                                                  //| 7 -> (, 8 -> LV, 9 -> ), 10 -> ;, 11 -> Lithuania, 13 -> LT, 14 -> name, 15
                                                  //|  -> country)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,1
                                                  //| 2), 13 -> (13,14), 14 -> (9,10), 15 -> (7,8))
                                                  //| Edges:	Map((3,1) -> nn, (3,2) -> punct, (3,4) -> punct, (3,5) -> punct,
                                                  //|  (3,6) -> dep, (6,8) -> appos, (6,10) -> punct, (6,11) -> dep, (8,14) -> pu
                                                  //| nct, (8,15) -> punct, (11,13) -> appos, (14,9) -> name#, (15,7) -> country#
                                                  //| )
                                                  //| InsertedNodes:	Map(14 -> , 15 -> )
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map(13 -> List((14,)), (12,()))
                                                  //| Mappings:	5 -> 0
                                                  //| 14 -> 
                                                  //| 1 -> 0.0.0.0
                                                  //| 6 -> 0.1.0.0
                                                  //| 11 -> 0.2.0.0
                                                  //| 15 -> 
                                                  //| InsertNode: country (Ref: )
                                                  //| NextNode: 0 -> UNKNOWN
                                                  //| InsertNode: name (Ref: )
                                                  //| 
val expert = new WangXueExpert                    //> expert  : amr.WangXueExpert = amr.WangXueExpert@6e01f9b0
val expertAction = expert.chooseTransition(s1, state5)
                                                  //> expertAction  : amr.WangXueAction = DeleteNode
val state6 = expertAction(state5)                 //> state6  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(14, 13, 10, 8, 11, 4, 1, 6, 5, 2, 3)
                                                  //| Children:	List(9)
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(1 -> Estonia, 2 -> (, 3 -> EE, 4 -> ), 5 -> ;, 6 -> Latvia, 
                                                  //| 8 -> LV, 9 -> ), 10 -> ;, 11 -> Lithuania, 13 -> LT, 14 -> name, 15 -> coun
                                                  //| try)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,12), 13 -> (1
                                                  //| 3,14), 14 -> (9,10), 15 -> (7,8))
                                                  //| Edges:	Map((3,1) -> nn, (3,2) -> punct, (3,4) -> punct, (3,5) -> punct,
                                                  //|  (3,6) -> dep, (6,8) -> appos, (6,10) -> punct, (6,11) -> dep, (8,14) -> pu
                                                  //| nct, (8,15) -> punct, (11,13) -> appos, (14,9) -> name#)
                                                  //| InsertedNodes:	Map(14 -> , 15 -> )
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map(13 -> List((14,)), (12,()), 15 -> List((7,()))
                                                  //| Mappings:	5 -> 0
                                                  //| 14 -> 
                                                  //| 1 -> 0.0.0.0
                                                  //| 6 -> 0.1.0.0
                                                  //| 11 -> 0.2.0.0
                                                  //| 15 -> 
                                                  //| NextNode: 0 -> UNKNOWN
                                                  //| DeleteNode
                                                  //| InsertNode: country (Ref: )
                                                  //| 
}