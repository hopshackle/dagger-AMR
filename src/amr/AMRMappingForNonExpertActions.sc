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
                                                  //| hPCFG.ser.gz ... done [1.5 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [4.1 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [2.3 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [5.3 sec].
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
val a1 = DeleteNode                               //> a1  : amr.DeleteNode.type = DeleteNode
val a2 = DeleteNode                               //> a2  : amr.DeleteNode.type = DeleteNode
val a3 = Insert(conceptIndex("name"))             //> WARNING: Found duplicate match for concept attack-01
                                                  //| WARNING: Found duplicate match for concept cyber
                                                  //| WARNING: Found duplicate match for concept attack-01
                                                  //| WARNING: Found duplicate match for concept cyber
                                                  //| a3  : amr.Insert = InsertNode: name (Ref: )
val a4 = NextNode(conceptIndex(""""Lithuania""""))//> a4  : amr.NextNode = NextNode: 20 -> "Lithuania"
val a5 = Insert(conceptIndex("country"))          //> a5  : amr.Insert = InsertNode: country (Ref: )
// These being the first five actions undertaken by the Expert policy
// We now check two things. Firstly that the state picks up the correct AMR ref labels (0.2.0 and 0.2 respectively)
// and secondly that If we now call the Expert policy, it uses this information, and does not
// try and insert a node
val trans = new WangXueTransitionSystem           //> trans  : amr.WangXueTransitionSystem = amr.WangXueTransitionSystem@52af26ee
                                                  //| 
val startState = trans.init(s1)                   //> startState  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(13, 8, 11, 1, 6, 3, 0)
                                                  //| Children:	List()
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> Estonia, 3 -> EE, 6 -> Latvia, 8 -> LV, 11 -
                                                  //| > Lithuania, 13 -> LT)
                                                  //| SpanMap:	Map(1 -> (1,2), 3 -> (2,3), 6 -> (3,4), 8 -> (4,5), 11 -> (5,6),
                                                  //|  13 -> (6,7))
                                                  //| Edges:	Map((0,3) -> root, (3,1) -> nn, (3,6) -> dep, (6,8) -> appos, (6
                                                  //| ,11) -> dep, (11,13) -> appos)
                                                  //| Mappings:	11 -> 0.2.0.0
                                                  //| 1 -> 0.0.0.0
                                                  //| 6 -> 0.1.0.0
                                                  //| 0 -> ROOT
                                                  //| 
val state1 = a1(startState)                       //> state1  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(8, 11, 1, 6, 3, 0)
                                                  //| Children:	List()
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> Estonia, 3 -> EE, 6 -> Latvia, 8 -> LV, 11 -
                                                  //| > Lithuania)
                                                  //| SpanMap:	Map(1 -> (1,2), 3 -> (2,3), 6 -> (3,4), 8 -> (4,5), 11 -> (5,6))
                                                  //| 
                                                  //| Edges:	Map((0,3) -> root, (3,1) -> nn, (3,6) -> dep, (6,8) -> appos, (6
                                                  //| ,11) -> dep)
                                                  //| Mappings:	11 -> 0.2.0.0
                                                  //| 1 -> 0.0.0.0
                                                  //| 6 -> 0.1.0.0
                                                  //| 0 -> ROOT
                                                  //| 
val state2 = a2(state1)                           //> state2  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(11, 1, 6, 3, 0)
                                                  //| Children:	List()
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> Estonia, 3 -> EE, 6 -> Latvia, 11 -> Lithuan
                                                  //| ia)
                                                  //| SpanMap:	Map(1 -> (1,2), 3 -> (2,3), 6 -> (3,4), 11 -> (5,6))
                                                  //| Edges:	Map((0,3) -> root, (3,1) -> nn, (3,6) -> dep, (6,11) -> dep)
                                                  //| Mappings:	11 -> 0.2.0.0
                                                  //| 1 -> 0.0.0.0
                                                  //| 6 -> 0.1.0.0
                                                  //| 0 -> ROOT
                                                  //| 
val state3 = a3(state2)                           //> state3  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(11, 12, 1, 6, 3, 0)
                                                  //| Children:	List()
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> Estonia, 3 -> EE, 6 -> Latvia, 11 -> Lithuan
                                                  //| ia, 12 -> name)
                                                  //| SpanMap:	Map(1 -> (1,2), 3 -> (2,3), 6 -> (3,4), 11 -> (5,6), 12 -> (5,6)
                                                  //| )
                                                  //| Edges:	Map((0,3) -> root, (3,1) -> nn, (3,6) -> dep, (6,12) -> dep, (12
                                                  //| ,11) -> name#)
                                                  //| Mappings:	0 -> ROOT
                                                  //| 1 -> 0.0.0.0
                                                  //| 6 -> 0.1.0.0
                                                  //| 12 -> 0.2.0
                                                  //| 11 -> 0.2.0.0
                                                  //| 
val state4 = a4(state3)                           //> state4  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(12, 1, 6, 3, 0)
                                                  //| Children:	List(11)
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> Estonia, 3 -> EE, 6 -> Latvia, 11 -> "Lithua
                                                  //| nia", 12 -> name)
                                                  //| SpanMap:	Map(1 -> (1,2), 3 -> (2,3), 6 -> (3,4), 11 -> (5,6), 12 -> (5,6)
                                                  //| )
                                                  //| Edges:	Map((0,3) -> root, (3,1) -> nn, (3,6) -> dep, (6,12) -> dep, (12
                                                  //| ,11) -> name#)
                                                  //| Mappings:	0 -> ROOT
                                                  //| 1 -> 0.0.0.0
                                                  //| 6 -> 0.1.0.0
                                                  //| 12 -> 0.2.0
                                                  //| 11 -> 0.2.0.0
                                                  //| 
val state5 = a5(state4)                           //> state5  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(12, 13, 1, 6, 3, 0)
                                                  //| Children:	List(11)
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> Estonia, 3 -> EE, 6 -> Latvia, 11 -> "Lithua
                                                  //| nia", 12 -> name, 13 -> country)
                                                  //| SpanMap:	Map(1 -> (1,2), 3 -> (2,3), 6 -> (3,4), 11 -> (5,6), 12 -> (5,6)
                                                  //| , 13 -> (5,6))
                                                  //| Edges:	Map((0,3) -> root, (3,1) -> nn, (3,6) -> dep, (6,13) -> dep, (12
                                                  //| ,11) -> name#, (13,12) -> country#)
                                                  //| Mappings:	0 -> ROOT
                                                  //| 1 -> 0.0.0.0
                                                  //| 6 -> 0.1.0.0
                                                  //| 13 -> 0.2
                                                  //| 12 -> 0.2.0
                                                  //| 11 -> 0.2.0.0
                                                  //| 
val expert = new WangXueExpert                    //> expert  : amr.WangXueExpert = amr.WangXueExpert@6392827e
val expertAction = expert.chooseTransition(s1, state5)
                                                  //> expertAction  : amr.WangXueAction = NextEdge: 9 -> opN
val state6 = expertAction(state5)                 //> state6  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(12, 13, 1, 6, 3, 0)
                                                  //| Children:	List()
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> Estonia, 3 -> EE, 6 -> Latvia, 11 -> "Lithua
                                                  //| nia", 12 -> name, 13 -> country)
                                                  //| SpanMap:	Map(1 -> (1,2), 3 -> (2,3), 6 -> (3,4), 11 -> (5,6), 12 -> (5,6)
                                                  //| , 13 -> (5,6))
                                                  //| Edges:	Map((0,3) -> root, (3,1) -> nn, (3,6) -> dep, (6,13) -> dep, (12
                                                  //| ,11) -> opN, (13,12) -> country#)
                                                  //| Mappings:	0 -> ROOT
                                                  //| 1 -> 0.0.0.0
                                                  //| 6 -> 0.1.0.0
                                                  //| 13 -> 0.2
                                                  //| 12 -> 0.2.0
                                                  //| 11 -> 0.2.0.0
                                                  //| 
}