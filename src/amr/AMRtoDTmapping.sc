package amr

object AMRtoDTmapping {
  val dt = DependencyTree("Russian Federation President Dmitry Medvedev promised on May 15, 2008 to provide funding for Russia's nuclear missile program.")
                                                  //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.4 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [4.4 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [2.1 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [4.2 sec].
                                                  //| dt  : amr.DependencyTree = 
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> Russian, 2 -> Federation, 3 -> President, 4 
                                                  //| -> Dmitry, 5 -> Medvedev, 6 -> promised, 7 -> on, 8 -> 5, 9 -> 15, 10 -> ,, 
                                                  //| 11 -> 2008, 12 -> to, 13 -> provide, 14 -> funding, 15 -> for, 16 -> Russia,
                                                  //|  17 -> 's, 18 -> nuclear, 19 -> missile, 20 -> program, 21 -> .)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,12
                                                  //| ), 12 -> (12,13), 13 -> (13,14), 14 -> (14,15), 15 -> (15,16), 16 -> (16,17)
                                                  //| , 17 -> (17,18), 18 -> (18,19), 19 -> (19,20), 20 -> (20,21), 21 -> (21,22))
                                                  //| 
                                                  //| Edges:	Map((0,6) -> root, (5,1) -> nn, (5,2) -> nn, (5,3) -> nn, (5,4) 
                                                  //| -> nn, (6,5) -> nsubj, (6,7) -> prep, (6,13) -> xcomp, (6,21) -> punct, (7,8
                                                  //| ) -> pobj, (8,9) -> num, (8,10) -> punct, (8,11) -> num, (13,12) -> aux, (13
                                                  //| ,14) -> dobj, (14,15) -> prep, (15,20) -> pobj, (16,17) -> possessive, (20,1
                                                  //| 6) -> poss, (20,18) -> amod, (20,19) -> nn)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	List()
  dt.nodeLemmas                                   //> res0: Map[Int,String] = Map(5 -> medvedev, 10 -> ,, 14 -> funding, 20 -> pro
                                                  //| gram, 1 -> russian, 6 -> promise, 21 -> ., 9 -> ##, 13 -> provide, 2 -> fede
                                                  //| ration, 17 -> 's, 12 -> to, 7 -> on, 3 -> president, 18 -> nuclear, 16 -> ru
                                                  //| ssia, 11 -> ##, 8 -> may, 19 -> missile, 4 -> dmitry, 15 -> for)
  dt.nodePOS                                      //> res1: Map[Int,String] = Map(5 -> NNP, 10 -> ,, 14 -> NN, 20 -> NN, 1 -> NNP,
                                                  //|  6 -> VBD, 21 -> ., 9 -> CD, 13 -> VB, 2 -> NNP, 17 -> POS, 12 -> TO, 7 -> I
                                                  //| N, 3 -> NNP, 18 -> JJ, 16 -> NNP, 11 -> CD, 8 -> NNP, 19 -> NN, 4 -> NNP, 15
                                                  //|  -> IN)
  dt.nodeNER                                      //> res2: Map[Int,String] = Map(5 -> PERSON, 10 -> DATE, 14 -> O, 20 -> O, 1 -> 
                                                  //| MISC, 6 -> O, 21 -> O, 9 -> DATE, 13 -> O, 2 -> O, 17 -> O, 12 -> O, 7 -> O,
                                                  //|  3 -> O, 18 -> O, 16 -> LOCATION, 11 -> DATE, 8 -> DATE, 19 -> O, 4 -> PERSO
                                                  //| N, 15 -> O)
  dt.nodes foreach (_ match { case (node, word) => println(s"$node -> $word has parents ${dt.edgesToParents(node)}") })
                                                  //> 0 -> ROOT has parents ArrayBuffer()
                                                  //| 5 -> Medvedev has parents ArrayBuffer((6,5))
                                                  //| 10 -> , has parents ArrayBuffer((8,10))
                                                  //| 14 -> funding has parents ArrayBuffer((13,14))
                                                  //| 20 -> program has parents ArrayBuffer((15,20))
                                                  //| 1 -> Russian has parents ArrayBuffer((5,1))
                                                  //| 6 -> promised has parents ArrayBuffer((0,6))
                                                  //| 21 -> . has parents ArrayBuffer((6,21))
                                                  //| 9 -> 15 has parents ArrayBuffer((8,9))
                                                  //| 13 -> provide has parents ArrayBuffer((6,13))
                                                  //| 2 -> Federation has parents ArrayBuffer((5,2))
                                                  //| 17 -> 's has parents ArrayBuffer((16,17))
                                                  //| 12 -> to has parents ArrayBuffer((13,12))
                                                  //| 7 -> on has parents ArrayBuffer((6,7))
                                                  //| 3 -> President has parents ArrayBuffer((5,3))
                                                  //| 18 -> nuclear has parents ArrayBuffer((20,18))
                                                  //| 16 -> Russia has parents ArrayBuffer((20,16))
                                                  //| 11 -> 2008 has parents ArrayBuffer((8,11))
                                                  //| 8 -> 5 has parents ArrayBuffer((7,8))
                                                  //| 19 -> missile has parents ArrayBuffer((20,19))
                                                  //| 4 -> Dmitry has parents ArrayBuffer((5,4))
                                                  //| 15 -> for has parents ArrayBuffer((14,15))
  dt.nodes foreach (_ match { case (node, word) => println(s"$node -> $word has depth ${dt.depth(node)}") })
                                                  //> 0 -> ROOT has depth 0
                                                  //| 5 -> Medvedev has depth 2
                                                  //| 10 -> , has depth 4
                                                  //| 14 -> funding has depth 3
                                                  //| 20 -> program has depth 5
                                                  //| 1 -> Russian has depth 3
                                                  //| 6 -> promised has depth 1
                                                  //| 21 -> . has depth 2
                                                  //| 9 -> 15 has depth 4
                                                  //| 13 -> provide has depth 2
                                                  //| 2 -> Federation has depth 3
                                                  //| 17 -> 's has depth 7
                                                  //| 12 -> to has depth 3
                                                  //| 7 -> on has depth 2
                                                  //| 3 -> President has depth 3
                                                  //| 18 -> nuclear has depth 6
                                                  //| 16 -> Russia has depth 6
                                                  //| 11 -> 2008 has depth 4
                                                  //| 8 -> 5 has depth 3
                                                  //| 19 -> missile has depth 6
                                                  //| 4 -> Dmitry has depth 3
                                                  //| 15 -> for has depth 4
  dt.getNodesBetween(4, 9)                        //> res3: Seq[Int] = List(9, 8, 7, 6, 5, 4)
  dt.getPathBetween(4, 9)                         //> res4: String = NNP-nn-NNP-nsubj-VBD-prep-IN-pobj-NNP-num-CD
  dt.getNodesBetween(9, 4)                        //> res5: Seq[Int] = List(4, 5, 6, 7, 8, 9)
  dt.getPathBetween(9, 4)                         //> res6: String = CD-num-NNP-pobj-IN-prep-VBD-nsubj-NNP-nn-NNP
  dt.getNodesBetween(0, 3)                        //> res7: Seq[Int] = List(3, 5, 6, 0)
  dt.getPathBetween(0, 3)                         //> res8: String = XX-root-VBD-nsubj-NNP-nn-NNP
  dt.getNodesBetween(2, 8)                        //> res9: Seq[Int] = List(8, 7, 6, 5, 2)
  dt.getPathBetween(2, 8)                         //> res10: String = NNP-nn-NNP-nsubj-VBD-prep-IN-pobj-NNP
  dt.getPathBetween(4, 4)                         //> res11: String = NNP-ERR

  dt.subGraph(4)                                  //> res12: Set[Int] = Set(4)
  dt.subGraph(9)                                  //> res13: Set[Int] = Set(9)
  dt.subGraph(0)                                  //> res14: Set[Int] = Set(0, 5, 10, 14, 20, 1, 6, 21, 9, 13, 2, 17, 12, 7, 3, 18
                                                  //| , 16, 11, 8, 19, 4, 15)
  dt.subGraph(3)                                  //> res15: Set[Int] = Set(3)

  dt.getNeighbourhood(3, 1)                       //> res16: Set[Int] = Set(5)
  dt.getNeighbourhood(3, 2)                       //> res17: Set[Int] = Set(5, 1, 6, 2, 4)
  dt.getNeighbourhood(3, 3)                       //> res18: Set[Int] = Set(0, 5, 1, 6, 21, 13, 2, 7, 4)
  dt.getNeighbourhood(3, 4)                       //> res19: Set[Int] = Set(0, 5, 14, 1, 6, 21, 13, 2, 12, 7, 8, 4)
  dt.getNeighbourhood(5, 1)                       //> res20: Set[Int] = Set(1, 6, 2, 3, 4)
  dt.getNeighbourhood(5, 2)                       //> res21: Set[Int] = Set(0, 1, 6, 21, 13, 2, 7, 3, 4)
  dt.getNeighbourhood(5, 3)                       //> res22: Set[Int] = Set(0, 14, 1, 6, 21, 13, 2, 12, 7, 3, 8, 4)
  dt.getNeighbourhood(5, 4)                       //> res23: Set[Int] = Set(0, 10, 14, 1, 6, 21, 9, 13, 2, 12, 7, 3, 11, 8, 4, 15)
                                                  //| 
  
  dt.getDistanceBetween(4, 9)                     //> res24: Int = 5
  dt.getDistanceBetween(9, 4)                     //> res25: Int = 5
  dt.getDistanceBetween(0, 3)                     //> res26: Int = 3
  dt.getDistanceBetween(2, 8)                     //> res27: Int = 4
  dt.getDistanceBetween(4, 4)                     //> res28: Int = 0
  val testData = AMRGraph.importFile("C:\\AMR\\IRNA.txt")
                                                  //> testData  : IndexedSeq[(String, String)] = Vector((IRNA stated that in the 
                                                  //| large-scale army operation along the eastern borders with Afghanistan and P
                                                  //| akistan 21 drug traffickers were killed, 1500 arrested and approximately 5 
                                                  //| tons of drugs seized.,(s / state-01 :ARG0 (p3 / publication :name (n / name
                                                  //|  :op1 "IRNA")) :ARG1 (a / and :op1 (k / kill-01 :ARG1 (p / person :quant 21
                                                  //|  :ARG0-of (t / traffic-00 :ARG1 (d / drug)))) :op2 (a2 / arrest-01 :ARG1 (p
                                                  //| 2 / person :quant 1500)) :op3 (s2 / seize-01 :ARG1 (m / mass-quantity :unit
                                                  //|  (t2 / ton) :quant (a3 / approximate :op1 5) :consist-of (d2 / drug))) :ins
                                                  //| trument (o / operation :mod (a4 / army) :mod (l / large-scale) :location (a
                                                  //| 5 / along :op1 (b / border :location (a6 / and :op1 (c2 / country :name (n2
                                                  //|  / name :op1 "Afghanistan")) :op2 (c3 / country :name (n3 / name :op1 "Paki
                                                  //| stan"))) :direction (e / east))))))))
  DependencyTree("Estonia (EE); Latvia (LV); Lithuania (LT); Germany (DE): Italy (IT); Spain (ES); Slovakia (SK); United States (US)")
                                                  //> res29: amr.DependencyTree = 
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> Estonia, 2 -> (, 3 -> EE, 4 -> ), 5 -> ;, 6 
                                                  //| -> Latvia, 7 -> (, 8 -> LV, 9 -> ), 10 -> ;, 11 -> Lithuania, 12 -> (, 13 -
                                                  //| > LT, 14 -> ), 15 -> ;, 16 -> Germany, 17 -> (, 18 -> DE, 19 -> ), 20 -> :,
                                                  //|  21 -> Italy, 22 -> (, 23 -> IT, 24 -> ), 25 -> ;, 26 -> Spain, 27 -> (, 28
                                                  //|  -> ES, 29 -> ), 30 -> ;, 31 -> Slovakia, 32 -> (, 33 -> SK, 34 -> ), 35 ->
                                                  //|  ;, 36 -> United, 37 -> States, 38 -> (, 39 -> US, 40 -> ))
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,1
                                                  //| 2), 12 -> (12,13), 13 -> (13,14), 14 -> (14,15), 15 -> (15,16), 16 -> (16,1
                                                  //| 7), 17 -> (17,18), 18 -> (18,19), 19 -> (19,20), 20 -> (20,21), 21 -> (21,2
                                                  //| 2), 22 -> (22,23), 23 -> (23,24), 24 -> (24,25), 25 -> (25,26), 26 -> (26,2
                                                  //| 7), 27 -> (27,28), 28 -> (28,29), 29 -> (29,30), 30 -> (30,31), 31 -> (31,3
                                                  //| 2), 32 -> (32,33), 33 -> (33,34), 34 -> (34,35), 35 -> (35,36), 36 -> (36,3
                                                  //| 7), 37 -> (37,38), 38 -> (38,39), 39 -> (39,40), 40 -> (40,41))
                                                  //| Edges:	Map((0,3) -> root, (3,1) -> nn, (3,2) -> punct, (3,4) -> punct, 
                                                  //| (3,5) -> punct, (3,6) -> dep, (6,8) -> appos, (6,10) -> punct, (6,11) -> de
                                                  //| p, (6,15) -> punct, (6,16) -> dep, (6,20) -> punct, (6,21) -> dep, (6,25) -
                                                  //| > punct, (6,26) -> dep, (6,30) -> punct, (6,31) -> dep, (6,35) -> punct, (6
                                                  //| ,37) -> dep, (8,7) -> punct, (8,9) -> punct, (11,13) -> appos, (13,12) -> p
                                                  //| unct, (13,14) -> punct, (16,18) -> appos, (18,17) -> punct, (18,19) -> punc
                                                  //| t, (21,23) -> dep, (23,22) -> punct, (23,24) -> punct, (26,28) -> appos, (2
                                                  //| 8,27) -> punct, (28,29) -> punct, (31,33) -> appos, (33,32) -> punct, (33,3
                                                  //| 4) -> punct, (37,36) -> nn, (37,39) -> appos, (39,38) -> punct, (39,40) -> 
                                                  //| punct)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	List()
  val s1 = Sentence(testData(0)._1, testData(0)._2)
                                                  //> WARNING: Found duplicate match for concept and
                                                  //| WARNING: Found duplicate match for concept drug
                                                  //| s1  : amr.Sentence = Sentence(IRNA stated that in the large-scale army oper
                                                  //| ation along the eastern borders with Afghanistan and Pakistan 21 drug traff
                                                  //| ickers were killed, 1500 arrested and approximately 5 tons of drugs seized.
                                                  //| ,
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> IRNA, 2 -> stated, 3 -> that, 4 -> in, 5 -> 
                                                  //| the, 6 -> large-scale, 7 -> army, 8 -> operation, 9 -> along, 10 -> the, 11
                                                  //|  -> eastern, 12 -> borders, 13 -> with, 14 -> Afghanistan, 15 -> and, 16 ->
                                                  //|  Pakistan, 17 -> 21, 18 -> drug, 19 -> traffickers, 20 -> were, 21 -> kille
                                                  //| d, 22 -> ,, 23 -> 1500, 24 -> arrested, 25 -> and, 26 -> approximately, 27 
                                                  //| -> 5, 28 -> tons, 29 -> of, 30 -> drugs, 31 -> seized, 32 -> .)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,1
                                                  //| 2), 12 -> (12,13), 13 -> (13,14), 14 -> (14,15), 15 -> (15,16), 16 -> (16,1
                                                  //| 7), 17 -> (17,18), 18 -> (18,19), 19 -> (19,20), 20 -> (20,21), 21 -> (21,2
                                                  //| 2), 22 -> (22,23), 23 -> (23,24), 24 -> (24,25), 25 -> (25,26), 26 -> (26,2
                                                  //| 7), 27 -> (27,28), 28 -> (28,29), 29 -> (29,30), 30 -> (30,31), 31 -> (31,3
                                                  //| 2), 32 -> (32,33))
                                                  //| Edges:	Map((0,2) -> root, (2,1) -> nsubj, (2,21) -> ccomp, (2,32) -> pu
                                                  //| nct, (4,8) -> pobj, (8,5) -> det, (8,6) -> amod, (8,7) -> nn, (8,9) -> prep
                                                  //| , (9,12) -> pobj, (12,10) -> det, (12,11) -> amod, (12,13) -> prep, (13,14)
                                                  //|  -> pobj, (14,15) -> cc, (14,16) -> conj, (19,17) -> num, (19,18) -> nn, (2
                                                  //| 1,3) -> mark, (21,4) -> prep, (21,19) -> nsubjpass, (21,20) -> auxpass, (21
                                                  //| ,22) -> punct, (21,23) -> dobj, (23,24) -> amod, (23,25) -> cc, (23,28) -> 
                                                  //| conj, (27,26) -> quantmod, (28,27) -> num, (28,29) -> prep, (29,30) -> pobj
                                                  //| , (30,31) -> vmod)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	List(),Some(AMRGraph(Map(0.1 -> and, 0.0.0 -> name, 0.1.
                                                  //| 3.2.0.1 -> east, 0.1.3.2.0.0.0.0.0 -> "Afghanistan", 0.1.3.2.0.0.0 -> count
                                                  //| ry, 0.1.3.2.0.0.0.0 -> name, 0.1.2.0.0 -> ton, 0.1.0 -> kill-01, 0.1.3.2.0.
                                                  //| 0 -> and, 0.1.0.0.1.0 -> drug, 0.1.0.0.0 -> 21, 0.0 -> publication, 0.1.3.2
                                                  //| .0.0.1.0 -> name, 0.1.2.0.1 -> approximate, 0.1.3.2 -> along, 0.0.0.0 -> "I
                                                  //| RNA", 0.1.3 -> operation, 0.1.1.0.0 -> 1500, 0.1.0.0.1 -> traffic-00, 0.1.3
                                                  //| .2.0.0.1.0.0 -> 
                                                  //| Output exceeds cutoff limit.
  s1.positionToAMR map { case (k, v) => s1.dependencyTree.nodes(k) + " -> " + s1.amr.get.nodes(v) } foreach println
                                                  //> ROOT -> ROOT
                                                  //| arrested -> arrest-01
                                                  //| and -> and
                                                  //| Afghanistan -> "Afghanistan"
                                                  //| of -> mass-quantity
                                                  //| IRNA -> "IRNA"
                                                  //| large-scale -> large-scale
                                                  //| tons -> ton
                                                  //| killed -> kill-01
                                                  //| along -> along
                                                  //| stated -> state-01
                                                  //| 21 -> 21
                                                  //| 5 -> 5
                                                  //| borders -> border
                                                  //| army -> army
                                                  //| drug -> drug
                                                  //| Pakistan -> "Pakistan"
                                                  //| seized -> seize-01
                                                  //| eastern -> east
                                                  //| approximately -> approximate
                                                  //| 1500 -> 1500
                                                  //| operation -> operation
                                                  //| drugs -> drug
                                                  //| traffickers -> traffic-00
                                                  //| and -> and

  // val s2 = Sentence(testData(3)._1, testData(3)._2)
  // s2.positionToAMR

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