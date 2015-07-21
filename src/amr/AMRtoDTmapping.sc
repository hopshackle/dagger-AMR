package amr

object AMRtoDTmapping {
  val dt = DependencyTree("The Riyadh-based Naif Arab Academy for Security Sciences said in a statement that it was running a two-week workshop for 50 anti-terrorism experts.")
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
                                                  //| tsim.crf.ser.gz ... done [4.3 sec].
                                                  //| dt  : amr.DependencyTree = 
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> The, 2 -> Riyadh-based, 3 -> Naif, 4 -> Arab
                                                  //| , 5 -> Academy, 6 -> for, 7 -> Security, 8 -> Sciences, 9 -> said, 10 -> in,
                                                  //|  11 -> a, 12 -> statement, 13 -> that, 14 -> it, 15 -> was, 16 -> running, 1
                                                  //| 7 -> a, 18 -> two-week, 19 -> workshop, 20 -> for, 21 -> 50, 22 -> anti-terr
                                                  //| orism, 23 -> experts)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,12
                                                  //| ), 12 -> (12,13), 13 -> (13,14), 14 -> (14,15), 15 -> (15,16), 16 -> (16,17)
                                                  //| , 17 -> (17,18), 18 -> (18,19), 19 -> (19,20), 20 -> (20,21), 21 -> (21,22),
                                                  //|  22 -> (22,23), 23 -> (23,24))
                                                  //| Edges:	Map((0,9) -> root, (5,1) -> det, (5,2) -> amod, (5,3) -> nn, (5,
                                                  //| 4) -> nn, (5,6) -> prep, (6,8) -> pobj, (8,7) -> nn, (9,5) -> nsubj, (9,10) 
                                                  //| -> prep, (9,16) -> ccomp, (10,12) -> pobj, (12,11) -> det, (16,13) -> mark, 
                                                  //| (16,14) -> nsubj, (16,15) -> aux, (16,19) -> dobj, (19,17) -> det, (19,18) -
                                                  //| > amod, (19,20) -> prep, (20,23) -> pobj, (23,21) -> num, (23,22) -> amod)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	List()
  dt.nodeLemmas                                   //> res0: Map[Int,String] = Map(5 -> Academy, 10 -> in, 14 -> it, 20 -> for, 1 -
                                                  //| > the, 6 -> for, 21 -> 50, 9 -> say, 13 -> that, 2 -> riyadh-based, 17 -> a,
                                                  //|  22 -> anti-terrorism, 12 -> statement, 7 -> Security, 3 -> Naif, 18 -> two-
                                                  //| week, 16 -> run, 11 -> a, 23 -> expert, 8 -> Sciences, 19 -> workshop, 4 -> 
                                                  //| Arab, 15 -> be)
dt.nodePOS                                        //> res1: Map[Int,String] = Map(5 -> NNP, 10 -> IN, 14 -> PRP, 20 -> IN, 1 -> DT
                                                  //| , 6 -> IN, 21 -> CD, 9 -> VBD, 13 -> IN, 2 -> JJ, 17 -> DT, 22 -> JJ, 12 -> 
                                                  //| NN, 7 -> NNP, 3 -> NNP, 18 -> JJ, 16 -> VBG, 11 -> DT, 23 -> NNS, 8 -> NNPS,
                                                  //|  19 -> NN, 4 -> NNP, 15 -> VBD)
dt.nodeNER                                        //> res2: Map[Int,String] = Map(5 -> ORGANIZATION, 10 -> O, 14 -> O, 20 -> O, 1 
                                                  //| -> O, 6 -> ORGANIZATION, 21 -> NUMBER, 9 -> O, 13 -> O, 2 -> MISC, 17 -> O, 
                                                  //| 22 -> O, 12 -> O, 7 -> ORGANIZATION, 3 -> ORGANIZATION, 18 -> O, 16 -> O, 11
                                                  //|  -> O, 23 -> O, 8 -> ORGANIZATION, 19 -> O, 4 -> ORGANIZATION, 15 -> O)
  dt.nodes foreach (_ match { case (node, word) => println(s"$node -> $word has parents ${dt.edgesToParents(node)}") })
                                                  //> 0 -> ROOT has parents List()
                                                  //| 5 -> Academy has parents List((9,5))
                                                  //| 10 -> in has parents List((9,10))
                                                  //| 14 -> it has parents List((16,14))
                                                  //| 20 -> for has parents List((19,20))
                                                  //| 1 -> The has parents List((5,1))
                                                  //| 6 -> for has parents List((5,6))
                                                  //| 21 -> 50 has parents List((23,21))
                                                  //| 9 -> said has parents List((0,9))
                                                  //| 13 -> that has parents List((16,13))
                                                  //| 2 -> Riyadh-based has parents List((5,2))
                                                  //| 17 -> a has parents List((19,17))
                                                  //| 22 -> anti-terrorism has parents List((23,22))
                                                  //| 12 -> statement has parents List((10,12))
                                                  //| 7 -> Security has parents List((8,7))
                                                  //| 3 -> Naif has parents List((5,3))
                                                  //| 18 -> two-week has parents List((19,18))
                                                  //| 16 -> running has parents List((9,16))
                                                  //| 11 -> a has parents List((12,11))
                                                  //| 23 -> experts has parents List((20,23))
                                                  //| 8 -> Sciences has parents List((6,8))
                                                  //| 19 -> workshop has parents List((16,19))
                                                  //| 4 -> Arab has parents List((5,4))
                                                  //| 15 -> was has parents List((16,15))
  dt.nodes foreach (_ match { case (node, word) => println(s"$node -> $word has depth ${dt.depth(node)}") })
                                                  //> 0 -> ROOT has depth 0
                                                  //| 5 -> Academy has depth 2
                                                  //| 10 -> in has depth 2
                                                  //| 14 -> it has depth 3
                                                  //| 20 -> for has depth 4
                                                  //| 1 -> The has depth 3
                                                  //| 6 -> for has depth 3
                                                  //| 21 -> 50 has depth 6
                                                  //| 9 -> said has depth 1
                                                  //| 13 -> that has depth 3
                                                  //| 2 -> Riyadh-based has depth 3
                                                  //| 17 -> a has depth 4
                                                  //| 22 -> anti-terrorism has depth 6
                                                  //| 12 -> statement has depth 3
                                                  //| 7 -> Security has depth 5
                                                  //| 3 -> Naif has depth 3
                                                  //| 18 -> two-week has depth 4
                                                  //| 16 -> running has depth 2
                                                  //| 11 -> a has depth 4
                                                  //| 23 -> experts has depth 5
                                                  //| 8 -> Sciences has depth 4
                                                  //| 19 -> workshop has depth 3
                                                  //| 4 -> Arab has depth 3
                                                  //| 15 -> was has depth 3
  dt.getNodesBetween(4, 9)                        //> res3: List[Int] = List(9, 5, 4)
  dt.getPathBetween(4, 9)                         //> res4: String = NNP-nn-NNP-nsubj-VBD
  dt.getNodesBetween(9, 4)                        //> res5: List[Int] = List(4, 5, 9)
  dt.getPathBetween(9, 4)                         //> res6: String = VBD-nsubj-NNP-nn-NNP
  dt.getNodesBetween(0, 3)                        //> res7: List[Int] = List(3, 5, 9, 0)
  dt.getPathBetween(0, 3)                         //> res8: String = XX-root-VBD-nsubj-NNP-nn-NNP
  dt.getNodesBetween(2, 8)                        //> res9: List[Int] = List(8, 6, 5, 2)
  dt.getPathBetween(2, 8)                         //> res10: String = JJ-amod-NNP-prep-IN-pobj-NNPS
  dt.getPathBetween(4, 4)                         //> res11: String = NNP-ERR
  
  dt.subGraph(4)                                  //> res12: Set[Int] = Set(4)
  dt.subGraph(9)                                  //> res13: Set[Int] = Set(5, 10, 14, 20, 1, 6, 21, 9, 13, 2, 17, 22, 12, 7, 3, 1
                                                  //| 8, 16, 11, 23, 8, 19, 4, 15)
  dt.subGraph(0)                                  //> res14: Set[Int] = Set(0, 5, 10, 14, 20, 1, 6, 21, 9, 13, 2, 17, 22, 12, 7, 3
                                                  //| , 18, 16, 11, 23, 8, 19, 4, 15)
  dt.subGraph(3)                                  //> res15: Set[Int] = Set(3)
  
  dt.getDistanceBetween(4, 9)                     //> res16: Int = 2
  dt.getDistanceBetween(9, 4)                     //> res17: Int = 2
  dt.getDistanceBetween(0, 3)                     //> res18: Int = 3
  dt.getDistanceBetween(2, 8)                     //> res19: Int = 3
  dt.getDistanceBetween(4, 4)                     //> res20: Int = 0
  val testData = AMRGraph.importFile("C:\\AMR\\AMR2.txt")
                                                  //> testData  : IndexedSeq[(String, String)] = Vector((2008-05-14,"(d / date-en
                                                  //| tity :month 5 :day 14 :year 2008) "), (Estonia (EE); Latvia (LV); Lithuania
                                                  //|  (LT),"(a / and :op1 (c / country :name (n / name :op1 "Estonia")) :op2 (c2
                                                  //|  / country :name (n3 / name :op1 "Latvia")) :op3 (c3 / country :name (n5 / 
                                                  //| name :op1 "Lithuania"))) "), (International; Government; Telecom; technolog
                                                  //| y; science,"(a / and :op1 (i / international) :op2 (g / government-organiza
                                                  //| tion :ARG0-of (g2 / govern-01)) :op3 (t / telecommunication) :op4 (t2 / tec
                                                  //| hnology) :op5 (s / science)) "), (NATO CONSIDERS cyber attacks a threat to 
                                                  //| military and civilian computer networks after the Estonian Government was s
                                                  //| truck by cyber attacks in 2007.,"(c / consider-02 :ARG0 (m2 / military :nam
                                                  //| e (n / name :op1 "NATO")) :ARG1 (a / attack-01 :mod (c2 / cyber)) :ARG2 (t 
                                                  //| / thing :ARG1-of (t2 / threaten-01 :ARG2 (a5 / and :op1 (n2 / network :mod 
                                                  //| (m / military)) :op2 (n4 / network :mod (c3 / civilian)) :mod (c4 / compute
                                                  //| r)))) :time (a3 / after :op1 (s / strike-01 :ARG0 (a4 / attack :mod (c6 / c
                                                  //| yber)) :ARG1 (g / government-organization :ARG0-of (g2 / govern-01 :ARG1 (c
                                                  //| 5 / country :name (n3 / name :op1 "Estonia")))) :time (d / date-entity :yea
                                                  //| r 2007)))) "), (NATO allies have signed an agreement to fund a research cen
                                                  //| ter to bolster defenses against cyber attacks.,(s / sign-01 :ARG0 (p / pers
                                                  //| on :ARG0-of (a / ally-01) :mod (m / military :name (n / name :op1 "NATO")))
                                                  //|  :ARG1 (a2 / agree-01 :ARG0 p :ARG1 (f / fund-01 :ARG0 p :ARG1 (c / center 
                                                  //| :mod (r / research-01)) :purpose (b / bolster-01 :ARG0 p :ARG1 (d / defend-
                                                  //| 01 :prep-against (a3 / attack :mod (c2 / cyber)))))))))
  DependencyTree("Estonia (EE); Latvia (LV); Lithuania (LT); Germany (DE): Italy (IT); Spain (ES); Slovakia (SK); United States (US)")
                                                  //> res21: amr.DependencyTree = 
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> Estonia, 3 -> EE, 6 -> Latvia, 8 -> LV, 11 -
                                                  //| > Lithuania, 13 -> LT, 16 -> Germany, 18 -> DE, 21 -> Italy, 23 -> IT, 26 -
                                                  //| > Spain, 28 -> ES, 31 -> Slovakia, 33 -> SK, 36 -> United, 37 -> States, 39
                                                  //|  -> US)
                                                  //| SpanMap:	Map(1 -> (1,2), 3 -> (2,3), 6 -> (3,4), 8 -> (4,5), 11 -> (5,6),
                                                  //|  13 -> (6,7), 16 -> (7,8), 18 -> (8,9), 21 -> (9,10), 23 -> (10,11), 26 -> 
                                                  //| (11,12), 28 -> (12,13), 31 -> (13,14), 33 -> (14,15), 36 -> (15,16), 37 -> 
                                                  //| (16,17), 39 -> (17,18))
                                                  //| Edges:	Map((0,3) -> root, (3,1) -> nn, (3,6) -> dep, (6,8) -> appos, (6
                                                  //| ,11) -> dep, (6,16) -> dep, (6,21) -> dep, (6,26) -> dep, (6,31) -> dep, (6
                                                  //| ,37) -> dep, (11,13) -> appos, (16,18) -> appos, (21,23) -> dep, (26,28) ->
                                                  //|  appos, (31,33) -> appos, (37,36) -> nn, (37,39) -> appos)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	List()
  val s1 = Sentence(testData(1)._1, testData(1)._2)
                                                  //> s1  : amr.Sentence = Sentence(Estonia (EE); Latvia (LV); Lithuania (LT),
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> Estonia, 3 -> EE, 6 -> Latvia, 8 -> LV, 11 -
                                                  //| > Lithuania, 13 -> LT)
                                                  //| SpanMap:	Map(1 -> (1,2), 3 -> (2,3), 6 -> (3,4), 8 -> (4,5), 11 -> (5,6),
                                                  //|  13 -> (6,7))
                                                  //| Edges:	Map((0,3) -> root, (3,1) -> nn, (3,6) -> dep, (6,8) -> appos, (6
                                                  //| ,11) -> dep, (11,13) -> appos)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	List(),Some(AMRGraph(Map(0.1 -> country, 0.0.0 -> name, 
                                                  //| 0.1.0 -> name, 0.0 -> country, 0.0.0.0 -> "Estonia", 0.2.0 -> name, 0.1.0.0
                                                  //|  -> "Latvia", 0 -> and, 0.2 -> country, ROOT -> ROOT, 0.2.0.0 -> "Lithuania
                                                  //| "),Map(0.1 -> (3,4), 0.0.0 -> (1,2), 0.1.0 -> (3,4), 0.0 -> (1,2), 0.0.0.0 
                                                  //| -> (1,2), 0.2.0 -> (5,6), 0.1.0.0 -> (3,4), 0.2 -> (5,6), 0.2.0.0 -> (5,6))
                                                  //| ,Map((0.0,0.0.0) -> name, (ROOT,0) -> ROOT, (0.0.0,0.0.0.0) -> opN, (0.1,0.
                                                  //| 1.0) -> name, (0.1.0,0.1.0.0) -> opN, (0,0.2) -> opN, (0,0.1) -> opN, (0.2,
                                                  //| 0.2.0) -> name, (0,0.0) -> opN, (0.2.0,0.2.0.0) -> opN))),Map(11 -> 0.2.0.0
                                                  //| , 1 -> 0.0.0.0, 6 -> 0.1.0.0, 0 -> ROOT))
  s1.positionToAMR                                //> res22: Map[Int,String] = Map(11 -> 0.2.0.0, 1 -> 0.0.0.0, 6 -> 0.1.0.0, 0 -
                                                  //| > ROOT)
  val s2 = Sentence(testData(3)._1, testData(3)._2)
                                                  //> WARNING: Found duplicate match for concept attack-01
                                                  //| WARNING: Found duplicate match for concept cyber
                                                  //| s2  : amr.Sentence = Sentence(NATO CONSIDERS cyber attacks a threat to mili
                                                  //| tary and civilian computer networks after the Estonian Government was struc
                                                  //| k by cyber attacks in 2007.,
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> NATO, 2 -> CONSIDERS, 3 -> cyber, 4 -> attac
                                                  //| ks, 5 -> a, 6 -> threat, 7 -> to, 8 -> military, 9 -> and, 10 -> civilian, 
                                                  //| 11 -> computer, 12 -> networks, 13 -> after, 14 -> the, 15 -> Estonian, 16 
                                                  //| -> Government, 17 -> was, 18 -> struck, 19 -> by, 20 -> cyber, 21 -> attack
                                                  //| s, 22 -> in, 23 -> 2007)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,1
                                                  //| 2), 12 -> (12,13), 13 -> (13,14), 14 -> (14,15), 15 -> (15,16), 16 -> (16,1
                                                  //| 7), 17 -> (17,18), 18 -> (18,19), 19 -> (19,20), 20 -> (20,21), 21 -> (21,2
                                                  //| 2), 22 -> (22,23), 23 -> (23,24))
                                                  //| Edges:	Map((0,4) -> root, (3,1) -> nn, (3,2) -> nn, (4,3) -> nsubj, (4,
                                                  //| 6) -> dobj, (4,7) -> prep, (4,18) -> advcl, (6,5) -> det, (7,12) -> pobj, (
                                                  //| 8,9) -> cc, (8,10) -> conj, (12,8) -> amod, (12,11) -> nn, (16,14) -> det, 
                                                  //| (16,15) -> amod, (18,13) -> mark, (18,16) -> nsubjpass, (18,17) -> auxpass,
                                                  //|  (18,19) -> prep, (19,21) -> pobj, (21,20) -> nn, (21,22) -> prep, (22,23) 
                                                  //| -> pobj)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	List(),Some(AMRGraph(Map(0.1 -> attack-01, 0.0.0 -> name
                                                  //| , 0.3.0.1.0.0 -> country, 0.2.0.0.0 -> network, 0.1.0 -> cyber, 0.2.0.0.1.0
                                                  //|  -> civilian, 0.3.0.2.0 -> 2007, 0.0 -> military, 0.2.0.0.1 -> network, 0.3
                                                  //| .0.1.0 -> govern-01, 0.0.0.0 -> "NATO", 0.2.0 -> threaten-01, 0.3.0.0 -> at
                                                  //| tack, 0.3.0 -> strike-01, 0.3 -> after, 0.2.0.0.0.0 -> military, 0.2.0.0.2 
                                                  //| -> computer, 0 -> consider-02, 0.3.0.1 -> government-organization, 0.2 -> t
                                                  //| hing, 0.3.0.2 -> date-entity, 0.3.0.1.0.0.0.0 -> "Estonia", ROOT -> ROOT, 0
                                                  //| .2.0.0 -> and, 0.3.0.1.0.0.0 -> name, 0.3.0.0.0 -> cyber),Map(0.1 -> (4,5),
                                                  //|  0.0.0 -> (1,2), 0.3.0.1.0.0 -> (15,16), 0.2.0.0.0 -> (12,13), 0.1.0 -> (3,
                                                  //| 4), 0.2.0.0.1.0 -> (10,11), 0.3.0.2.0 -> (23,24), 0.0 -> (8,9), 0.0.0.0 -> 
                                                  //| (1,2), 0.2.0 -> (6,7), 0.3.0.0 -> (21,22), 0.3.0 -> (18,19), 0.3 -> (13,14)
                                                  //| , 0.2.0.0.2 -> (11,12), 0 -> (2,3), 0.3.0.1 -> (16,17), 0.2 -> (6,7), 0.3.0
                                                  //| .2 -> (23,24), 0
                                                  //| Output exceeds cutoff limit.
  s2.positionToAMR                                //> res23: Map[Int,String] = Map(0 -> ROOT, 10 -> 0.2.0.0.1.0, 20 -> 0.3.0.0.0,
                                                  //|  1 -> 0.0.0.0, 6 -> 0.2.0, 21 -> 0.3.0.0, 9 -> 0.2.0.0, 13 -> 0.3, 2 -> 0, 
                                                  //| 12 -> 0.2.0.0.0, 3 -> 0.1.0, 18 -> 0.3.0, 16 -> 0.3.0.1, 11 -> 0.2.0.0.2, 2
                                                  //| 3 -> 0.3.0.2.0, 8 -> 0.0, 4 -> 0.1, 15 -> 0.3.0.1.0.0.0.0)


  val testDataFull = AMRGraph.importFile("C:\\AMR\\trainingSet150.txt")
                                                  //> testDataFull  : IndexedSeq[(String, String)] = Vector((2008-05-14,"(d / dat
                                                  //| e-entity :month 5 :day 14 :year 2008) "), (Estonia (EE); Latvia (LV); Lithu
                                                  //| ania (LT); Germany (DE): Italy (IT); Spain (ES); Slovakia (SK); United Stat
                                                  //| es (US),"(a / and :op1 (c / country :name (n / name :op1 "Estonia")) :op2 (
                                                  //| c2 / country :name (n3 / name :op1 "Latvia")) :op3 (c3 / country :name (n5 
                                                  //| / name :op1 "Lithuania")) :op4 (c4 / country :name (n7 / name :op1 "Germany
                                                  //| ")) :op5 (c5 / country :name (n9 / name :op1 "Italy")) :op6 (c6 / country :
                                                  //| name (n10 / name :op1 "Spain")) :op7 (c7 / country :name (n12 / name :op1 "
                                                  //| Slovakia")) :op8 (c8 / country :name (n14 / name :op1 "United" :op2 "States
                                                  //| "))) "), (International; Government; Telecom; technology; science,"(a / and
                                                  //|  :op1 (i / international) :op2 (g / government-organization :ARG0-of (g2 / 
                                                  //| govern-01)) :op3 (t / telecommunication) :op4 (t2 / technology) :op5 (s / s
                                                  //| cience)) "), (NATO CONSIDERS cyber attacks a threat to military and civilia
                                                  //| n computer networks after the Estonian Government was struck by cyber attac
                                                  //| ks in 2007.,"(c / consider-02 :ARG0 (m2 / military :name (n / name :op1 "NA
                                                  //| TO")) :ARG1 (a / attack-01 :mod (c2 / cyber)) :ARG2 (t / thing :ARG1-of (t2
                                                  //|  / threaten-01 :ARG2 (a5 / and :op1 (n2 / network :mod (m / military)) :op2
                                                  //|  (n4 / network :mod (c3 / civilian)) :mod (c4 / computer)))) :time (a3 / af
                                                  //| ter :op1 (s / strike-01 :ARG0 (a4 / attack :mod (c6 / cyber)) :ARG1 (g / go
                                                  //| vernment-organization :ARG0-of (g2 / govern-01 :ARG1 (c5 / country :name (n
                                                  //| 3 / name :op1 "Estonia")))) :time (d / date-entity :year 2007)))) "), (NATO
                                                  //|  allies have signed an agreement to fund a research center to bolster defen
                                                  //| ses against cyber attacks.,"(s / sign-01 :ARG0 (p / person :ARG0-of (a / al
                                                  //| ly-01) :mod (m / military :name (n / name :op1 "NATO"))) :ARG1 (a2 / agree-
                                                  //| 01 :ARG0 p :ARG1 (f / fund-01 :ARG0 p :ARG1 (c / center :mod (r / research-
                                                  //| 01)) :purpose (b / bolster-01 :ARG0 p :ARG1 (d / defend-01 :prep-against (a
                                                  //| 3 / attack :mod (c2 / cyber))))))) "), (On 14 May 2008 7 NATO allies signed
                                                  //|  an agreement to fund a research center.,"(s / sign-01 :ARG0 (p / person :q
                                                  //| uant 7 :ARG0-of (a / ally-01) :mod (m / military :name (n / name :op1 "NATO
                                                  //| "))) :ARG1 (a2 / agree-01 :ARG1 (f / fund-01 :ARG0 p :ARG1 (c / center :mod
                                                  //|  (r / research-01)))) :time (d / date-entity :month 5 :day 14 :ye
                                                  //| Output exceeds cutoff limit.
 }