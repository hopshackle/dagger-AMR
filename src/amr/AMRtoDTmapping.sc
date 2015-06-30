package amr

object AMRtoDTmapping {
  val dt = DependencyTree("The police want to arrest Michael Karras in Singapore.")
                                                  //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.1 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [4.0 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [2.0 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [3.7 sec].
                                                  //| dt  : amr.DependencyTree = 
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> The, 2 -> police, 3 -> want, 4 -> to, 5 -> a
                                                  //| rrest, 6 -> Michael, 7 -> Karras, 8 -> in, 9 -> Singapore)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10))
                                                  //| Edges:	Map((0,3) -> root, (2,1) -> det, (3,2) -> nsubj, (3,5) -> xcomp,
                                                  //|  (5,4) -> aux, (5,7) -> dobj, (7,6) -> nn, (7,8) -> prep, (8,9) -> pobj)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()

  dt.nodes foreach (_ match { case (node, word) => println(s"$node -> $word has parents ${dt.edgesToParents(node)}") })
                                                  //> 0 -> ROOT has parents List()
                                                  //| 5 -> arrest has parents List((3,5))
                                                  //| 1 -> The has parents List((2,1))
                                                  //| 6 -> Michael has parents List((7,6))
                                                  //| 9 -> Singapore has parents List((8,9))
                                                  //| 2 -> police has parents List((3,2))
                                                  //| 7 -> Karras has parents List((5,7))
                                                  //| 3 -> want has parents List((0,3))
                                                  //| 8 -> in has parents List((7,8))
                                                  //| 4 -> to has parents List((5,4))
  dt.nodes foreach (_ match { case (node, word) => println(s"$node -> $word has depth ${dt.depth(node)}") })
                                                  //> 0 -> ROOT has depth 0
                                                  //| 5 -> arrest has depth 2
                                                  //| 1 -> The has depth 3
                                                  //| 6 -> Michael has depth 4
                                                  //| 9 -> Singapore has depth 5
                                                  //| 2 -> police has depth 2
                                                  //| 7 -> Karras has depth 3
                                                  //| 3 -> want has depth 1
                                                  //| 8 -> in has depth 4
                                                  //| 4 -> to has depth 3
  dt.getNodesBetween(4, 9)                        //> res0: List[Int] = List(9, 8, 7, 5, 4)
  dt.getPathBetween(4, 9)                         //> res1: String = TO-aux-VB-dobj-NNP-prep-IN-pobj-NNP
  dt.getNodesBetween(9, 4)                        //> res2: List[Int] = List(4, 5, 7, 8, 9)
  dt.getPathBetween(9, 4)                         //> res3: String = NNP-pobj-IN-prep-NNP-dobj-VB-aux-TO
  dt.getNodesBetween(0, 3)                        //> res4: List[Int] = List(3, 0)
  dt.getPathBetween(0, 3)                         //> res5: String = XX-root-VBP
  dt.getNodesBetween(2, 8)                        //> res6: List[Int] = List(8, 7, 5, 3, 2)
  dt.getPathBetween(2, 8)                         //> res7: String = NN-nsubj-VBP-xcomp-VB-dobj-NNP-prep-IN
  dt.getPathBetween(4, 4)                         //> res8: String = TO-aux-VB-aux-TO
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
  DependencyTree("Estonia (EE); Latvia (LV); Lithuania (LT); Germany (DE): Italy (IT); Spain (ES); Slovakia (SK); United States (US)")
                                                  //> res9: amr.DependencyTree = 
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
                                                  //| ppos, (31,33) -> appos, (37,36) -> nn, (37,39) -> appos)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
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
                                                  //| SwappedArcs:	Set(),Some(AMRGraph(Map(0.1 -> country, 0.0.0 -> name, 0
                                                  //| .1.0 -> name, 0.0 -> country, 0.0.0.0 -> "Estonia", 0.2.0 -> name, 0.1.0.0 -
                                                  //| > "Latvia", 0 -> and, 0.2 -> country, ROOT -> ROOT, 0.2.0.0 -> "Lithuania"),
                                                  //| Map(0.1 -> (3,4), 0.0.0 -> (1,2), 0.1.0 -> (3,4), 0.0 -> (1,2), 0.0.0.0 -> (
                                                  //| 1,2), 0.2.0 -> (5,6), 0.1.0.0 -> (3,4), 0.2 -> (5,6), 0.2.0.0 -> (5,6)),Map(
                                                  //| (0.0,0.0.0) -> name, (ROOT,0) -> ROOT, (0.0.0,0.0.0.0) -> opN, (0.1,0.1.0) -
                                                  //| > name, (0.1.0,0.1.0.0) -> opN, (0,0.2) -> opN, (0,0.1) -> opN, (0.2,0.2.0) 
                                                  //| -> name, (0,0.0) -> opN, (0.2.0,0.2.0.0) -> opN))),Map(11 -> 0.2.0.0, 1 -> 0
                                                  //| .0.0.0, 6 -> 0.1.0.0, 0 -> ROOT))
  s1.positionToAMR                                //> res10: Map[Int,String] = Map(11 -> 0.2.0.0, 1 -> 0.0.0.0, 6 -> 0.1.0.0, 0 ->
                                                  //|  ROOT)
  val s2 = Sentence(testData(3)._1, testData(3)._2)
                                                  //> WARNING: Found duplicate match for concept attack-01
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
                                                  //| obj)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set(),Some(AMRGraph(Map(0.1 -> attack-01, 0.0.0 -> name,
                                                  //|  0.3.0.1.0.0 -> country, 0.2.0.0.0 -> network, 0.1.0 -> cyber, 0.2.0.0.1.0 -
                                                  //| > civilian, 0.3.0.2.0 -> 2007, 0.0 -> military, 0.2.0.0.1 -> network, 0.3.0.
                                                  //| 1.0 -> govern-01, 0.0.0.0 -> "NATO", 0.2.0 -> threaten-01, 0.3.0.0 -> attack
                                                  //| , 0.3.0 -> strike-01, 0.3 -> after, 0.2.0.0.0.0 -> military, 0.2.0.0.2 -> co
                                                  //| mputer, 0 -> consider-02, 0.3.0.1 -> government-organization, 0.2 -> thing, 
                                                  //| 0.3.0.2 -> date-entity, 0.3.0.1.0.0.0.0 -> "Estonia"
                                                  //| Output exceeds cutoff limit.
  s2.positionToAMR                                //> res11: Map[Int,String] = Map(0 -> ROOT, 10 -> 0.2.0.0.1.0, 20 -> 0.3.0.0.0, 
                                                  //| 1 -> 0.0.0.0, 6 -> 0.2.0, 21 -> 0.3.0.0, 9 -> 0.2.0.0, 13 -> 0.3, 2 -> 0, 12
                                                  //|  -> 0.2.0.0.0, 3 -> 0.1.0, 18 -> 0.3.0, 16 -> 0.3.0.1, 11 -> 0.2.0.0.2, 23 -
                                                  //| > 0.3.0.2.0, 8 -> 0.0, 4 -> 0.1, 15 -> 0.3.0.1.0.0.0.0)

  val gv1 = new GraphViz("C://AMR//testGraphViz1.txt", s1.amr.get)
                                                  //> gv1  : amr.GraphViz = GraphViz(C://AMR//testGraphViz1.txt,AMRGraph(Map(0.1 -
                                                  //| > country, 0.0.0 -> name, 0.1.0 -> name, 0.0 -> country, 0.0.0.0 -> "Estonia
                                                  //| ", 0.2.0 -> name, 0.1.0.0 -> "Latvia", 0 -> and, 0.2 -> country, ROOT -> ROO
                                                  //| T, 0.2.0.0 -> "Lithuania"),Map(0.1 -> (3,4), 0.0.0 -> (1,2), 0.1.0 -> (3,4),
                                                  //|  0.0 -> (1,2), 0.0.0.0 -> (1,2), 0.2.0 -> (5,6), 0.1.0.0 -> (3,4), 0.2 -> (5
                                                  //| ,6), 0.2.0.0 -> (5,6)),Map((0.0,0.0.0) -> name, (ROOT,0) -> ROOT, (0.0.0,0.0
                                                  //| .0.0) -> opN, (0.1,0.1.0) -> name, (0.1.0,0.1.0.0) -> opN, (0,0.2) -> opN, (
                                                  //| 0,0.1) -> opN, (0.2,0.2.0) -> name, (0,0.0) -> opN, (0.2.0,0.2.0.0) -> opN))
                                                  //| )
  val gv2 = new GraphViz("C://AMR//testGraphViz2.txt", s2.amr.get)
                                                  //> gv2  : amr.GraphViz = GraphViz(C://AMR//testGraphViz2.txt,AMRGraph(Map(0.1 
                                                  //| -> attack-01, 0.0.0 -> name, 0.3.0.1.0.0 -> country, 0.2.0.0.0 -> network, 
                                                  //| 0.1.0 -> cyber, 0.2.0.0.1.0 -> civilian, 0.3.0.2.0 -> 2007, 0.0 -> military
                                                  //| , 0.2.0.0.1 -> network, 0.3.0.1.0 -> govern-01, 0.0.0.0 -> "NATO", 0.2.0 ->
                                                  //|  threaten-01, 0.3.0.0 -> attack, 0.3.0 -> strike-01, 0.3 -> after, 0.2.0.0.
                                                  //| 0.0 -> military, 0.2.0.0.2 -> computer, 0 -> consider-02, 0.3.0.1 -> govern
                                                  //| ment-organization, 0.2 -> thing, 0.3.0.2 -> date-entity, 0.3.0.1.0.0.0.0 ->
                                                  //|  "Estonia", ROOT -> ROOT, 0.2.0.0 -> and, 0.3.0.1.0.0.0 -> name, 0.3.0.0.0 
                                                  //| -> cyber),Map(0.1 -> (4,5), 0.0.0 -> (1,2), 0.3.0.1.0.0 -> (15,16), 0.2.0.0
                                                  //| .0 -> (12,13), 0.1.0 -> (3,4), 0.2.0.0.1.0 -> (10,11), 0.3.0.2.0 -> (23,24)
                                                  //| , 0.0 -> (8,9), 0.0.0.0 -> (1,2), 0.2.0 -> (6,7), 0.3.0.0 -> (21,22), 0.3.0
                                                  //|  -> (18,19), 0.3 -> (13,14), 0.2.0.0.2 -> (11,12), 0 -> (2,3), 0.3.0.1 -> (
                                                  //| 16,17), 0.2 -> (6,7), 0.3.0.2 -> (23,24), 0.3.0.1.0.0.0.0 -> (15,16), 0.2.0
                                                  //| .0 -> (9,10), 0.3.0.1.0.0.0 -> (15,16), 0.3.0.0.0 -> (20,21)),Map((0.0,0.0.
                                                  //| 0) -> name, (0.3.0,0.3.0.0) -> ARG0, (ROOT,0) -> ROOT, (0,0.3) -> time, (0.
                                                  //| 0.0,0.0.0.0) -> opN, (0.2.0.0.1,0.2.0.0.1.0) -> mod, (0.1,0.1.0) -> mod, (0
                                                  //| .3.0,0.3.0.1) -> ARG1, (0.3.0,0.3.0.2) -> time, (0.3.0.0,0.3.0.0.0) -> mod,
                                                  //|  (0,0.2) -> ARG2, (0,0.1) -> ARG1, (0.2.0.0.0,0.2.0.0.0.0) -> mod, (0.2,0.2
                                                  //| .0) -> ARG1-of, (0.3,0.3.0) -> opN, (0.3.0.1.0,0.3.0.1.0.0) -> ARG1, (0.2.0
                                                  //| .0,0.2.0.0.1) -> opN, (0.2.0.0,0.2.0.0.0) -> opN, (0.3.0.1.0.0.0,0.3.0.1.0.
                                                  //| 0.0.0) -> opN, (0.3.0.1,0.3.0.1.0) -> ARG0-of, (0,0.0) -> ARG0, (0.3.0.1.0.
                                                  //| 0,0.3.0.1.0.0.0) -> name, (0.2.0,0.2.0.0) -> ARG2, (0.3.0.2,0.3.0.2.0) -> y
                                                  //| ear, (0.2.0.0,0.2.0.0.2) -> mod)))

  val testDataFull = AMRGraph.importFile("C:\\AMR\\initialTrainingSetAmended.txt")
                                                  //> testDataFull  : IndexedSeq[(String, String)] = Vector((2008-05-14,"(d / dat
                                                  //| e-entity :month 5 :day 14 :year 2008) "), (Estonia (EE); Germany (DE): Ital
                                                  //| y (IT); Spain (ES); United States (US),"(a / and :op1 (c / country :name (n
                                                  //|  / name :op1 "Estonia")) :op2 (c4 / country :name (n7 / name :op1 "Germany"
                                                  //| )) :op3 (c5 / country :name (n9 / name :op1 "Italy")) :op4 (c6 / country :n
                                                  //| ame (n10 / name :op1 "Spain")) :op5 (c8 / country :name (n14 / name :op1 "U
                                                  //| nited" :op2 "States"))) "), (International; Government; Telecom; technology
                                                  //| ; science,"(a / and :op1 (i / international) :op2 (g / government-organizat
                                                  //| ion :ARG0-of (g2 / govern-01)) :op3 (t / telecommunication) :op4 (t2 / tech
                                                  //| nology) :op5 (s / science)) "), (NATO CONSIDERS cyber attacks a threat to m
                                                  //| ilitary and civilian computer networks after the Estonian Government was st
                                                  //| ruck by cyber attacks in 2007.,"(c / consider-02 :ARG0 (m2 / military :name
                                                  //|  (n / name :op1 "NATO")) :ARG1 (a / attack-01 :mod (c2 / cyber)) :ARG2 (t /
                                                  //|  thing :ARG1-of (t2 / threaten-01 :ARG2 (a5 / and :op1 (n2 / network :mod (
                                                  //| m / military)) :op2 (n4 / network :mod (c3 / civilian)) :mod (c4 / computer
                                                  //| )))) :time (a3 / after :op1 (s / strike-01 :ARG0 (a4 / attack :mod (c6 / cy
                                                  //| ber)) :ARG1 (g / government-organization :ARG0-of (g2 / govern-01 :ARG1 (c5
                                                  //|  / country :name (n3 / name :op1 "Estonia")))) :time (d / date-entity :year
                                                  //|  2007)))) "), (NATO allies have signed an agreement to fund a research cent
                                                  //| er to bolster defenses against cyber attacks.,"(s / sign-01 :ARG0 (p / pers
                                                  //| on :ARG0-of (a / ally-01) :mod (m / military :name (n / name :op1 "NATO")))
                                                  //|  :ARG1 (a2 / agree-01 :ARG0 p :ARG1 (f / fund-01 :ARG0 p :ARG1 (c / center 
                                                  //| :mod (r / research-01)) :purpose (b / bolster-01 :ARG0 p :ARG1 (d / defend-
                                                  //| 01 :prep-against (a3 / attack :mod (c2 / cyber))))))) "), (On 14 May 2008 7
                                                  //|  NATO allies signed an agreement to fund a research
                                                  //| Output exceeds cutoff limit.
  testDataFull.zipWithIndex map {
    case (x, i) =>
      val s = Sentence(x._1, x._2)
      val gv = new GraphViz("C://AMR//GV_initial_master_" + (i + 1) + ".dot", s.amr.get)
  }                                               //> WARNING: Found duplicate match for concept attack-01
                                                  //| WARNING: Found duplicate match for concept cyber
                                                  //| res12: IndexedSeq[Unit] = Vector((), (), (), (), (), (), (), (), (), (), ()
                                                  //| , (), (), (), ())

}