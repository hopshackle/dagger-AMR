package amr

object AMRtoDTmapping {
  val dt = DependencyTree("Russian Federation President Dmitry Medvedev promised on May 15, 2008 to provide funding for Russia's nuclear missile program.")
                                                  //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.3 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [5.3 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [2.4 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [4.9 sec].
                                                  //| dt  : amr.DependencyTree = 
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> Russian, 2 -> Federation, 3 -> President, 4 
                                                  //| -> Dmitry, 5 -> Medvedev, 6 -> promised, 7 -> on, 8 -> 5, 9 -> 15, 11 -> 200
                                                  //| 8, 12 -> to, 13 -> provide, 14 -> funding, 15 -> for, 16 -> Russia, 17 -> 's
                                                  //| , 18 -> nuclear, 19 -> missile, 20 -> program)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 11 -> (10,11), 12 -> (11,12
                                                  //| ), 13 -> (12,13), 14 -> (13,14), 15 -> (14,15), 16 -> (15,16), 17 -> (16,17)
                                                  //| , 18 -> (17,18), 19 -> (18,19), 20 -> (19,20))
                                                  //| Edges:	Map((0,6) -> root, (5,1) -> nn, (5,2) -> nn, (5,3) -> nn, (5,4) 
                                                  //| -> nn, (6,5) -> nsubj, (6,7) -> prep, (6,13) -> xcomp, (7,8) -> pobj, (8,9) 
                                                  //| -> num, (8,11) -> num, (13,12) -> aux, (13,14) -> dobj, (14,15) -> prep, (15
                                                  //| ,20) -> pobj, (16,17) -> possessive, (20,16) -> poss, (20,18) -> amod, (20,1
                                                  //| 9) -> nn)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	List()
  dt.nodeLemmas                                   //> res0: Map[Int,String] = Map(5 -> medvedev, 14 -> funding, 20 -> program, 1 -
                                                  //| > russian, 6 -> promise, 9 -> ##, 13 -> provide, 2 -> federation, 17 -> 's, 
                                                  //| 12 -> to, 7 -> on, 3 -> president, 18 -> nuclear, 16 -> russia, 11 -> ##, 8 
                                                  //| -> may, 19 -> missile, 4 -> dmitry, 15 -> for)
  dt.nodePOS                                      //> res1: Map[Int,String] = Map(5 -> NNP, 14 -> NN, 20 -> NN, 1 -> NNP, 6 -> VBD
                                                  //| , 9 -> CD, 13 -> VB, 2 -> NNP, 17 -> POS, 12 -> TO, 7 -> IN, 3 -> NNP, 18 ->
                                                  //|  JJ, 16 -> NNP, 11 -> CD, 8 -> NNP, 19 -> NN, 4 -> NNP, 15 -> IN)
  dt.nodeNER                                      //> res2: Map[Int,String] = Map(5 -> PERSON, 14 -> O, 20 -> O, 1 -> MISC, 6 -> O
                                                  //| , 9 -> DATE, 13 -> O, 2 -> O, 17 -> O, 12 -> O, 7 -> O, 3 -> O, 18 -> O, 16 
                                                  //| -> LOCATION, 11 -> DATE, 8 -> DATE, 19 -> O, 4 -> PERSON, 15 -> O)
  dt.nodes foreach (_ match { case (node, word) => println(s"$node -> $word has parents ${dt.edgesToParents(node)}") })
                                                  //> 0 -> ROOT has parents ArrayBuffer()
                                                  //| 5 -> Medvedev has parents ArrayBuffer((6,5))
                                                  //| 14 -> funding has parents ArrayBuffer((13,14))
                                                  //| 20 -> program has parents ArrayBuffer((15,20))
                                                  //| 1 -> Russian has parents ArrayBuffer((5,1))
                                                  //| 6 -> promised has parents ArrayBuffer((0,6))
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
                                                  //| 14 -> funding has depth 3
                                                  //| 20 -> program has depth 5
                                                  //| 1 -> Russian has depth 3
                                                  //| 6 -> promised has depth 1
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
  dt.subGraph(0)                                  //> res14: Set[Int] = Set(0, 5, 14, 20, 1, 6, 9, 13, 2, 17, 12, 7, 3, 18, 16, 11
                                                  //| , 8, 19, 4, 15)
  dt.subGraph(3)                                  //> res15: Set[Int] = Set(3)

  dt.getNeighbourhood(3, 1)                       //> res16: Set[Int] = Set(5)
  dt.getNeighbourhood(3, 2)                       //> res17: Set[Int] = Set(5, 1, 6, 2, 4)
  dt.getNeighbourhood(3, 3)                       //> res18: Set[Int] = Set(0, 5, 1, 6, 13, 2, 7, 4)
  dt.getNeighbourhood(3, 4)                       //> res19: Set[Int] = Set(0, 5, 14, 1, 6, 13, 2, 12, 7, 8, 4)
  dt.getNeighbourhood(5, 1)                       //> res20: Set[Int] = Set(1, 6, 2, 3, 4)
  dt.getNeighbourhood(5, 2)                       //> res21: Set[Int] = Set(0, 1, 6, 13, 2, 7, 3, 4)
  dt.getNeighbourhood(5, 3)                       //> res22: Set[Int] = Set(0, 14, 1, 6, 13, 2, 12, 7, 3, 8, 4)
  dt.getNeighbourhood(5, 4)                       //> res23: Set[Int] = Set(0, 14, 1, 6, 9, 13, 2, 12, 7, 3, 11, 8, 4, 15)
  
  dt.getDistanceBetween(4, 9)                     //> res24: Int = 5
  dt.getDistanceBetween(9, 4)                     //> res25: Int = 5
  dt.getDistanceBetween(0, 3)                     //> res26: Int = 3
  dt.getDistanceBetween(2, 8)                     //> res27: Int = 4
  dt.getDistanceBetween(4, 4)                     //> res28: Int = 0
  val testData = AMRGraph.importFile("C:\\AMR\\IRNA.txt")
                                                  //> java.io.FileNotFoundException: C:\AMR\IRNA.txt (The system cannot find the 
                                                  //| file specified)
                                                  //| 	at java.io.FileInputStream.open(Native Method)
                                                  //| 	at java.io.FileInputStream.<init>(Unknown Source)
                                                  //| 	at scala.io.Source$.fromFile(Source.scala:91)
                                                  //| 	at scala.io.Source$.fromFile(Source.scala:76)
                                                  //| 	at scala.io.Source$.fromFile(Source.scala:54)
                                                  //| 	at amr.AMRGraph$.importFile(Sentence.scala:471)
                                                  //| 	at amr.AMRtoDTmapping$$anonfun$main$1.apply$mcV$sp(amr.AMRtoDTmapping.sc
                                                  //| ala:39)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$$anonfun$$exe
                                                  //| cute$1.apply$mcV$sp(WorksheetSupport.scala:76)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.redirected(W
                                                  //| orksheetSupport.scala:65)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.$execute(Wor
                                                  //| ksheetSupport.scala:75)
                                                  //| 	at amr.AMRtoDTmapping$.main(amr.AMRtoDTmapping.scala:3)
                                                  //| 	at amr.AMRtoDTmapping.main(amr.AMRtoDTmapping.scala)
  DependencyTree("Estonia (EE); Latvia (LV); Lithuania (LT); Germany (DE): Italy (IT); Spain (ES); Slovakia (SK); United States (US)")
  val s1 = Sentence(testData(0)._1, testData(0)._2)
  s1.positionToAMR map { case (k, v) => s1.dependencyTree.nodes(k) + " -> " + s1.amr.get.nodes(v) } foreach println

  // val s2 = Sentence(testData(3)._1, testData(3)._2)
  // s2.positionToAMR

  val testDataFull = AMRGraph.importFile("C:\\AMR\\trainingSet150.txt")
}