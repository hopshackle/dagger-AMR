package amr

object AMRtoDTmapping {
  val dt = DependencyTree("The Riyadh-based Naif Arab Academy for Security Sciences said in a statement that it was running a two-week workshop for 50 anti-terrorism experts.")
                                                  //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.5 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [4.9 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [4.1 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [3.7 sec].
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
  dt.nodeLemmas                                   //> res0: Map[Int,String] = Map(5 -> academy, 10 -> in, 14 -> it, 20 -> for, 1 -
                                                  //| > the, 6 -> for, 21 -> ##, 9 -> say, 13 -> that, 2 -> riyadh-based, 17 -> a,
                                                  //|  22 -> anti-terrorism, 12 -> statement, 7 -> security, 3 -> naif, 18 -> two-
                                                  //| week, 16 -> run, 11 -> a, 23 -> expert, 8 -> sciences, 19 -> workshop, 4 -> 
                                                  //| arab, 15 -> be)
  dt.nodePOS                                      //> res1: Map[Int,String] = Map(5 -> NNP, 10 -> IN, 14 -> PRP, 20 -> IN, 1 -> DT
                                                  //| , 6 -> IN, 21 -> CD, 9 -> VBD, 13 -> IN, 2 -> JJ, 17 -> DT, 22 -> JJ, 12 -> 
                                                  //| NN, 7 -> NNP, 3 -> NNP, 18 -> JJ, 16 -> VBG, 11 -> DT, 23 -> NNS, 8 -> NNPS,
                                                  //|  19 -> NN, 4 -> NNP, 15 -> VBD)
  dt.nodeNER                                      //> res2: Map[Int,String] = Map(5 -> ORGANIZATION, 10 -> O, 14 -> O, 20 -> O, 1 
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

  dt.getNeighbourhood(3, 1)                       //> res16: Set[Int] = Set(5)
  dt.getNeighbourhood(3, 2)                       //> res17: Set[Int] = Set(5, 1, 6, 9, 2, 4)
  dt.getNeighbourhood(3, 3)                       //> res18: Set[Int] = Set(0, 5, 10, 1, 6, 9, 2, 16, 8, 4)
  dt.getNeighbourhood(3, 4)                       //> res19: Set[Int] = Set(0, 5, 10, 14, 1, 6, 9, 13, 2, 12, 7, 16, 8, 19, 4, 15)
                                                  //| 
  dt.getNeighbourhood(5, 1)                       //> res20: Set[Int] = Set(1, 6, 9, 2, 3, 4)
  dt.getNeighbourhood(5, 2)                       //> res21: Set[Int] = Set(0, 10, 1, 6, 9, 2, 3, 16, 8, 4)
  dt.getNeighbourhood(5, 3)                       //> res22: Set[Int] = Set(0, 10, 14, 1, 6, 9, 13, 2, 12, 7, 3, 16, 8, 19, 4, 15)
                                                  //| 
  dt.getNeighbourhood(5, 4)                       //> res23: Set[Int] = Set(0, 10, 14, 20, 1, 6, 9, 13, 2, 17, 12, 7, 3, 18, 16, 
                                                  //| 11, 8, 19, 4, 15)
  
  dt.getDistanceBetween(4, 9)                     //> res24: Int = 2
  dt.getDistanceBetween(9, 4)                     //> res25: Int = 2
  dt.getDistanceBetween(0, 3)                     //> res26: Int = 3
  dt.getDistanceBetween(2, 8)                     //> res27: Int = 3
  dt.getDistanceBetween(4, 4)                     //> res28: Int = 0
  val testData = AMRGraph.importFile("C:\\AMR\\IRNA.txt")
                                                  //> java.io.FileNotFoundException: C:\AMR\IRNA.txt (The system cannot find the 
                                                  //| file specified)
                                                  //| 	at java.io.FileInputStream.open(Native Method)
                                                  //| 	at java.io.FileInputStream.<init>(Unknown Source)
                                                  //| 	at scala.io.Source$.fromFile(Source.scala:91)
                                                  //| 	at scala.io.Source$.fromFile(Source.scala:76)
                                                  //| 	at scala.io.Source$.fromFile(Source.scala:54)
                                                  //| 	at amr.AMRGraph$.importFile(Sentence.scala:470)
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