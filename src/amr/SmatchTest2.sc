package amr

object SmatchTest2 {

  val testData = AMRGraph.importFile("C://AMR//narcotic.txt")
                                                  //> testData  : IndexedSeq[(String, String)] = Vector((Crime; narcotics,(a / and 
                                                  //| :op1 (c / crime) :op2 (n / narcotic))))
  val expert = new WangXueExpert                  //> expert  : amr.WangXueExpert = amr.WangXueExpert@675d3402
  val s0 = Sentence(testData(0)._1, testData(0)._2)
                                                  //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.7 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [5.2 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [4.5 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [4.7 sec].
                                                  //| s0  : amr.Sentence = Sentence(Crime; narcotics,
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> Crime, 2 -> ;, 3 -> narcotics)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4))
                                                  //| Edges:	Map((0,1) -> root, (1,2) -> punct, (1,3) -> dep)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map(),Some(AMRGraph(Map(0.1 -> narcotic, 0.0 -> crime, 0
                                                  //|  -> and, ROOT -> ROOT),Map(0 -> (2,3), 0.0 -> (1,2), 0.1 -> (3,4)),Map((0,0.
                                                  //| 0) -> opN, (0,0.1) -> opN, (ROOT,0) -> ROOT))),Map(2 -> 0, 1 -> 0.0, 3 -> 0.
                                                  //| 1, 0 -> ROOT))
  val amr0 = s0.amr.get                           //> amr0  : amr.AMRGraph = AMRGraph(Map(0.1 -> narcotic, 0.0 -> crime, 0 -> and,
                                                  //|  ROOT -> ROOT),Map(0 -> (2,3), 0.0 -> (1,2), 0.1 -> (3,4)),Map((0,0.0) -> op
                                                  //| N, (0,0.1) -> opN, (ROOT,0) -> ROOT))
  Smatch.initialMap(amr0, amr0)                   //> res0: Map[String,String] = Map(ROOT -> ROOT, 0.1 -> 0.1, 0.0 -> 0.0, 0 -> 0)
                                                  //| 
  Smatch.initialMap2(amr0, amr0)                  //> res1: Map[String,String] = Map(0.1 -> 0.1, ROOT -> ROOT, 0.0 -> 0.0, 0 -> 0)
                                                  //| 

  Smatch.fScore(amr0, amr0, 1, 1000)              //> 
                                                  //| 0.1:narcotic
                                                  //| 0.0:crime
                                                  //| 0:and
                                                  //| ROOT:ROOT
                                                  //| 0:opN:0.0
                                                  //| 0:opN:0.1
                                                  //| ROOT:ROOT:0
                                                  //| 
                                                  //| 0.1:narcotic
                                                  //| 0.0:crime
                                                  //| 0:and
                                                  //| ROOT:ROOT
                                                  //| 0:opN:0.0
                                                  //| 0:opN:0.1
                                                  //| ROOT:ROOT:0
                                                  //| 
                                                  //| 0.1:narcotic
                                                  //| 0.0:crime
                                                  //| 0:and
                                                  //| ROOT:ROOT
                                                  //| 0:opN:0.0
                                                  //| 0:opN:0.1
                                                  //| ROOT:ROOT:0
                                                  //| 
                                                  //| 0.1 -> 0.1
                                                  //| ROOT -> ROOT
                                                  //| 0.0 -> 0.0
                                                  //| 0 -> 0
                                                  //| res2: (Double, Double, Double, Double, Double) = (1.0,1.0,1.0,0.0,7.0)
  val nodes = Map("a" -> "ROOT", "b" -> "and", "c" -> "crime", "d" -> "narcotics")
                                                  //> nodes  : scala.collection.immutable.Map[String,String] = Map(a -> ROOT, b ->
                                                  //|  and, c -> crime, d -> narcotics)
  val edges = Map(("a", "b") -> "ROOT", ("b" -> "c") -> "opN", ("b" -> "d") -> "opN")
                                                  //> edges  : scala.collection.immutable.Map[(String, String),String] = Map((a,b)
                                                  //|  -> ROOT, (b,c) -> opN, (b,d) -> opN)
  val amrGraph = new AMRGraph(nodes, Map(), edges)//> amrGraph  : amr.AMRGraph = AMRGraph(Map(a -> ROOT, b -> and, c -> crime, d -
                                                  //| > narcotics),Map(),Map((a,b) -> ROOT, (b,c) -> opN, (b,d) -> opN))
  amr0.nodes                                      //> res3: Map[String,String] = Map(0.1 -> narcotic, 0.0 -> crime, 0 -> and, ROOT
                                                  //|  -> ROOT)
  amrGraph.nodes                                  //> res4: Map[String,String] = Map(a -> ROOT, b -> and, c -> crime, d -> narcoti
                                                  //| cs)
  Smatch.initialMap(amrGraph, amr0)               //> res5: Map[String,String] = Map(ROOT -> a, 0 -> b, 0.0 -> c, 0.1 -> d)
  Smatch.initialMap2(amrGraph, amr0)              //> res6: Map[String,String] = Map(0.1 -> d, ROOT -> a, 0.0 -> c, 0 -> b)
  val fScore = Smatch.fScore(amrGraph, amr0, 1, 500)
                                                  //> 
                                                  //| a:ROOT
                                                  //| b:and
                                                  //| c:crime
                                                  //| d:narcotics
                                                  //| a:ROOT:b
                                                  //| b:opN:c
                                                  //| b:opN:d
                                                  //| 
                                                  //| d:narcotic
                                                  //| c:crime
                                                  //| b:and
                                                  //| a:ROOT
                                                  //| b:opN:c
                                                  //| b:opN:d
                                                  //| a:ROOT:b
                                                  //| 
                                                  //| 0.1:narcotic
                                                  //| 0.0:crime
                                                  //| 0:and
                                                  //| ROOT:ROOT
                                                  //| 0:opN:0.0
                                                  //| 0:opN:0.1
                                                  //| ROOT:ROOT:0
                                                  //| 
                                                  //| 0.1 -> d
                                                  //| ROOT -> a
                                                  //| 0.0 -> c
                                                  //| 0 -> b
                                                  //| fScore  : (Double, Double, Double, Double, Double) = (0.8571428571428571,0.8
                                                  //| 571428571428571,0.8571428571428571,2.0,6.0)
  Smatch.naiveFScore(amrGraph, amr0)              //> res7: (Double, Double, Double, Double, Double) = (0.7142857142857143,0.71428
                                                  //| 57142857143,0.7142857142857143,4.0,5.0)

}