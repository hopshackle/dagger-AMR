package amr

object SmatchTest2 {

  val testData = AMRGraph.importFile("C://AMR//narcotic.txt")
                                                  //> testData  : IndexedSeq[(String, String)] = Vector((Crime; narcotics,(a / and 
                                                  //| :op1 (c / crime) :op2 (n / narcotic))))
  val expert = new WangXueExpert                  //> expert  : amr.WangXueExpert = amr.WangXueExpert@482f8f11
  val s0 = Sentence(testData(0)._1, testData(0)._2)
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
                                                  //| im.crf.ser.gz ... done [3.2 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [2.6 sec].
                                                  //| s0  : amr.Sentence = Sentence(Crime; narcotics,
                                                  //| NodeMap:	Map(1 -> Crime, 3 -> narcotics)
                                                  //| SpanMap:	Map(1 -> (1,2), 3 -> (2,3))
                                                  //| Edges:	Map((1,3) -> dep)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map()
                                                  //| PartsOfSpeech:	Map(1 -> NN, 3 -> NNS)
                                                  //| DependencyLabels:	Map(3 -> dep),Some(AMRGraph(Map(0.1 -> narcotic, 0.0 -> 
                                                  //| crime, 0 -> and),Map(0.0 -> (1,2), 0.1 -> (2,3)),Map((0,0.0) -> opN, (0,0.1)
                                                  //|  -> opN),Map((0,0.0) -> op1, (0,0.1) -> op2),List())),Map(1 -> 0.0, 3 -> 0.1
                                                  //| ))
  val amr0 = s0.amr.get                           //> amr0  : amr.AMRGraph = AMRGraph(Map(0.1 -> narcotic, 0.0 -> crime, 0 -> and)
                                                  //| ,Map(0.0 -> (1,2), 0.1 -> (2,3)),Map((0,0.0) -> opN, (0,0.1) -> opN),Map((0,
                                                  //| 0.0) -> op1, (0,0.1) -> op2),List())
  Smatch.initialMap(amr0, amr0)                   //> res0: Map[String,String] = Map(0.1 -> 0.1, 0.0 -> 0.0, 0 -> 0)
  Smatch.initialMap2(amr0, amr0)                  //> res1: Map[String,String] = Map(0.1 -> 0.1, 0.0 -> 0.0, 0 -> 0)

  Smatch.fScore(amr0, amr0, 1, 1000)              //> res2: (Double, Double, Double, Double, Int, Int, Int) = (1.0,1.0,1.0,0.0,6,6
                                                  //| ,6)
  val nodes = Map("b" -> "and", "c" -> "crime", "d" -> "narcotics")
                                                  //> nodes  : scala.collection.immutable.Map[String,String] = Map(b -> and, c -> 
                                                  //| crime, d -> narcotics)
  val edges = Map(("b" -> "c") -> "opN", ("b" -> "d") -> "opN")
                                                  //> edges  : scala.collection.immutable.Map[(String, String),String] = Map((b,c)
                                                  //|  -> opN, (b,d) -> opN)
  val amrGraph = new AMRGraph(nodes, Map(), edges)//> amrGraph  : amr.AMRGraph = AMRGraph(Map(b -> and, c -> crime, d -> narcotics
                                                  //| ),Map(),Map((b,c) -> opN, (b,d) -> opN),Map(),List())
  amr0.nodes                                      //> res3: Map[String,String] = Map(0.1 -> narcotic, 0.0 -> crime, 0 -> and)
  amrGraph.nodes                                  //> res4: Map[String,String] = Map(b -> and, c -> crime, d -> narcotics)
  Smatch.initialMap(amrGraph, amr0)               //> res5: Map[String,String] = Map(0.0 -> c, 0 -> b, 0.1 -> d)
  Smatch.initialMap2(amrGraph, amr0)              //> res6: Map[String,String] = Map(0.1 -> d, 0.0 -> c, 0 -> b)
  val fScore = Smatch.fScore(amrGraph, amr0, 1, 500)
                                                  //> fScore  : (Double, Double, Double, Double, Int, Int, Int) = (0.8333333333333
                                                  //| 334,0.8333333333333334,0.8333333333333334,2.0,5,6,6)
  Smatch.naiveFScore(amrGraph, amr0)              //> and
                                                  //| crime
                                                  //| narcotics
                                                  //| and:op1:crime
                                                  //| and:op2:narcotics
                                                  //| and:ROOT:and
                                                  //| ------
                                                  //| narcotic
                                                  //| crime
                                                  //| and
                                                  //| and:op1:crime
                                                  //| and:op2:narcotic
                                                  //| and:ROOT:and
                                                  //| res7: (Double, Double, Double, Double, Int, Int, Int) = (0.6666666666666666,
                                                  //| 0.6666666666666666,0.6666666666666666,4.0,4,6,6)

}