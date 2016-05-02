package amr

object AMRtoDTmapping {
  val dt = DependencyTree("Russian Federation President Dmitry/Medvedev promised on May 15, 2008 to provide 1/3 funding for Russia's nuclear missile program.")
                                                  //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.8 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [4.8 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [3.6 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [2.3 sec].
                                                  //| dt  : amr.DependencyTree = 
                                                  //| NodeMap:	Map(1 -> Russian, 2 -> Federation, 3 -> President, 4 -> Dmitry, 
                                                  //| 6 -> Medvedev, 7 -> promised, 8 -> on, 9 -> 5, 10 -> 15, 11 -> 2008, 12 -> t
                                                  //| o, 13 -> provide, 14 -> 1, 15 -> /, 16 -> 3, 17 -> funding, 18 -> for, 19 ->
                                                  //|  Russia, 20 -> 's, 21 -> nuclear, 22 -> missile, 23 -> program)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 6 -> (5,6), 
                                                  //| 7 -> (6,7), 8 -> (7,8), 9 -> (8,9), 10 -> (9,10), 11 -> (10,11), 12 -> (11,1
                                                  //| 2), 13 -> (12,13), 14 -> (13,14), 15 -> (14,15), 16 -> (15,16), 17 -> (16,17
                                                  //| ), 18 -> (17,18), 19 -> (18,19), 20 -> (19,20), 21 -> (20,21), 22 -> (21,22)
                                                  //| , 23 -> (22,23))
                                                  //| Edges:	Map((4,1) -> amod, (4,2) -> nn, (4,3) -> nn, (4,7) -> dep, (7,6)
                                                  //|  -> nsubj, (7,8) -> prep, (7,11) -> tmod, (7,13) -> xcomp, (8,9) -> pobj, (9
                                                  //| ,10) -> num, (13,12) -> aux, (13,15) -> iobj, (13,17) -> dobj, (15,14) -> nu
                                                  //| m, (17,16) -> num, (17,18) -> prep, (18,23) -> pobj, (19,20) -> possessive, 
                                                  //| (23,19) -> poss, (23,21) -> amod, (23,22) -> nn)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map()
                                                  //| PartsOfSpeech:	Map(10 -> CD, 14 -> CD, 20 -> POS, 1 -> JJ, 6 -> NNP, 21
                                                  //|  -> JJ, 9 -> NNP, 13 -> VB, 2 -> NNP, 17 -> NN, 22 -> NN, 12 -> TO, 7 -> VBD
                                                  //| , 3 -> NNP, 18 -> IN, 16 -> CD, 11 -> CD, 23 -> NN, 8 -> IN, 19 -> NNP, 4 ->
                                                  //|  NNP, 15 -> NNS)
                                                  //| DependencyLabels:	Map(10 -> num, 14 -> num, 20 -> possessive, 1 -> amod, 6
                                                  //|  -> nsubj, 21 -> amod, 9 -> pobj, 13 -> xcomp, 2 -> nn, 17 -> dobj, 22 -> nn
                                                  //| , 12 -> aux, 7 -> dep, 3 -> nn, 18 -> prep, 16 -> num, 11 -> tmod, 23 -> pob
                                                  //| j, 8 -> prep, 19 -> poss, 15 -> iobj)
  dt.nodeLemmas                                   //> res0: Map[Int,String] = Map(10 -> ##, 14 -> ##, 20 -> 's, 1 -> russian, 6 ->
                                                  //|  medvedev, 21 -> nuclear, 9 -> may, 13 -> provide, 2 -> federation, 17 -> fu
                                                  //| nding, 22 -> missile, 12 -> to, 7 -> promise, 3 -> president, 18 -> for, 16 
                                                  //| -> ##, 11 -> ##, 23 -> program, 8 -> on, 19 -> russia, 4 -> dmitry, 15 -> /)
                                                  //| 
  dt.nodePOS                                      //> res1: Map[Int,String] = Map(10 -> CD, 14 -> CD, 20 -> POS, 1 -> JJ, 6 -> NNP
                                                  //| , 21 -> JJ, 9 -> NNP, 13 -> VB, 2 -> NNP, 17 -> NN, 22 -> NN, 12 -> TO, 7 ->
                                                  //|  VBD, 3 -> NNP, 18 -> IN, 16 -> CD, 11 -> CD, 23 -> NN, 8 -> IN, 19 -> NNP, 
                                                  //| 4 -> NNP, 15 -> NNS)
  dt.nodeNER                                      //> res2: Map[Int,String] = Map(10 -> DATE, 14 -> NUMBER, 20 -> O, 1 -> O, 6 -> 
                                                  //| PERSON, 21 -> O, 9 -> DATE, 13 -> O, 2 -> O, 17 -> O, 22 -> O, 12 -> O, 7 ->
                                                  //|  O, 3 -> O, 18 -> O, 16 -> NUMBER, 11 -> DATE, 23 -> O, 8 -> O, 19 -> LOCATI
                                                  //| ON, 4 -> O, 15 -> O)
  dt.nodes foreach (_ match { case (node, word) => println(s"$node -> $word has parents ${dt.edgesToParents(node)}") })
                                                  //> 10 -> 15 has parents ArrayBuffer((9,10))
                                                  //| 14 -> 1 has parents ArrayBuffer((15,14))
                                                  //| 20 -> 's has parents ArrayBuffer((19,20))
                                                  //| 1 -> Russian has parents ArrayBuffer((4,1))
                                                  //| 6 -> Medvedev has parents ArrayBuffer((7,6))
                                                  //| 21 -> nuclear has parents ArrayBuffer((23,21))
                                                  //| 9 -> 5 has parents ArrayBuffer((8,9))
                                                  //| 13 -> provide has parents ArrayBuffer((7,13))
                                                  //| 2 -> Federation has parents ArrayBuffer((4,2))
                                                  //| 17 -> funding has parents ArrayBuffer((13,17))
                                                  //| 22 -> missile has parents ArrayBuffer((23,22))
                                                  //| 12 -> to has parents ArrayBuffer((13,12))
                                                  //| 7 -> promised has parents ArrayBuffer((4,7))
                                                  //| 3 -> President has parents ArrayBuffer((4,3))
                                                  //| 18 -> for has parents ArrayBuffer((17,18))
                                                  //| 16 -> 3 has parents ArrayBuffer((17,16))
                                                  //| 11 -> 2008 has parents ArrayBuffer((7,11))
                                                  //| 23 -> program has parents ArrayBuffer((18,23))
                                                  //| 8 -> on has parents ArrayBuffer((7,8))
                                                  //| 19 -> Russia has parents ArrayBuffer((23,19))
                                                  //| 4 -> Dmitry has parents ArrayBuffer()
                                                  //| 15 -> / has parents ArrayBuffer((13,15))
  dt.nodes foreach (_ match { case (node, word) => println(s"$node -> $word has depth ${dt.depth(node)}") })
                                                  //> 10 -> 15 has depth 4
                                                  //| 14 -> 1 has depth 4
                                                  //| 20 -> 's has depth 7
                                                  //| 1 -> Russian has depth 1
                                                  //| 6 -> Medvedev has depth 2
                                                  //| 21 -> nuclear has depth 6
                                                  //| 9 -> 5 has depth 3
                                                  //| 13 -> provide has depth 2
                                                  //| 2 -> Federation has depth 1
                                                  //| 17 -> funding has depth 3
                                                  //| 22 -> missile has depth 6
                                                  //| 12 -> to has depth 3
                                                  //| 7 -> promised has depth 1
                                                  //| 3 -> President has depth 1
                                                  //| 18 -> for has depth 4
                                                  //| 16 -> 3 has depth 4
                                                  //| 11 -> 2008 has depth 2
                                                  //| 23 -> program has depth 5
                                                  //| 8 -> on has depth 2
                                                  //| 19 -> Russia has depth 6
                                                  //| 4 -> Dmitry has depth 0
                                                  //| 15 -> / has depth 3
  dt.getNodesBetween(4, 9)                        //> res3: Seq[Int] = List(9, 8, 7, 4)
  dt.getPathBetween(4, 9)                         //> res4: String = NNP-dep-VBD-prep-IN-pobj-NNP
  dt.getNodesBetween(9, 4)                        //> res5: Seq[Int] = List(4, 7, 8, 9)
  dt.getPathBetween(9, 4)                         //> res6: String = NNP-pobj-IN-prep-VBD-dep-NNP
  dt.getNodesBetween(0, 3)                        //> res7: Seq[Int] = List()
  dt.getPathBetween(0, 3)                         //> res8: String = ""
  dt.getNodesBetween(2, 8)                        //> res9: Seq[Int] = List(8, 7, 4, 2)
  dt.getPathBetween(2, 8)                         //> res10: String = NNP-nn-NNP-dep-VBD-prep-IN
  dt.getPathBetween(4, 4)                         //> res11: String = NNP-ERR

  dt.subGraph(4)                                  //> res12: Set[Int] = Set(10, 14, 20, 1, 6, 21, 9, 13, 2, 17, 22, 12, 7, 3, 18, 
                                                  //| 16, 11, 23, 8, 19, 4, 15)
  dt.subGraph(9)                                  //> res13: Set[Int] = Set(10, 9)
  dt.subGraph(0)                                  //> res14: Set[Int] = Set(0)
  dt.subGraph(3)                                  //> res15: Set[Int] = Set(3)

  dt.getNeighbourhood(3, 1)                       //> res16: Set[Int] = Set(4)
  dt.getNeighbourhood(3, 2)                       //> res17: Set[Int] = Set(1, 2, 7, 4)
  dt.getNeighbourhood(3, 3)                       //> res18: Set[Int] = Set(1, 6, 13, 2, 7, 11, 8, 4)
  dt.getNeighbourhood(3, 4)                       //> res19: Set[Int] = Set(1, 6, 9, 13, 2, 17, 12, 7, 11, 8, 4, 15)
  dt.getNeighbourhood(5, 1)                       //> res20: Set[Int] = Set()
  dt.getNeighbourhood(5, 2)                       //> res21: Set[Int] = Set()
  dt.getNeighbourhood(5, 3)                       //> res22: Set[Int] = Set()
  dt.getNeighbourhood(5, 4)                       //> res23: Set[Int] = Set()
  
  dt.getDistanceBetween(4, 9)                     //> res24: Int = 3
  dt.getDistanceBetween(9, 4)                     //> res25: Int = 3
  dt.getDistanceBetween(0, 3)                     //> res26: Int = 30
  dt.getDistanceBetween(2, 8)                     //> res27: Int = 3
  dt.getDistanceBetween(4, 4)                     //> res28: Int = 0

}