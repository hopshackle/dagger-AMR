package amr

object SmatchTest {
  val saudiArabia = AMRGraph.importFile("C://AMR//SaudiArabia.txt")
                                                  //> saudiArabia  : IndexedSeq[(String, String)] = Vector((Saudi Arabia (SA),(c /
                                                  //|  country :name (n / name :op1 "Saudi" :op2 "Arabia"))))
    val saudi = AMRGraph.importFile("C://AMR//Saudi.txt")
                                                  //> saudi  : IndexedSeq[(String, String)] = Vector((Saudi Arabia (SA),(s / "Saud
                                                  //| i" )))
  val sa = Sentence(saudiArabia(0)._1, saudiArabia(0)._2)
                                                  //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.6 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [5.4 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [2.2 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [5.3 sec].
                                                  //| sa  : amr.Sentence = Sentence(Saudi Arabia (SA),
                                                  //| NodeMap:	Map(1 -> Saudi, 2 -> Arabia, 4 -> SA)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 4 -> (3,4))
                                                  //| Edges:	Map((2,1) -> nn, (2,4) -> appos)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map(),Some(AMRGraph(Map(0.0.0 -> "Saudi", 0.0.1 -> "Arab
                                                  //| ia", 0.0 -> name, 0 -> country),Map(0 -> (1,3), 0.0 -> (1,3), 0.0.0 -> (1,3)
                                                  //| , 0.0.1 -> (1,3)),Map((0.0,0.0.0) -> opN, (0.0,0.0.1) -> opN, (0,0.0) -> nam
                                                  //| e),Map((0.0,0.0.0) -> op1, (0.0,0.0.1) -> op2, (0,0.0) -> name),List())),Map
                                                  //| (2 -> 0.0.1, 1 -> 0.0.0))
  val sa_amr = sa.amr.get                         //> sa_amr  : amr.AMRGraph = AMRGraph(Map(0.0.0 -> "Saudi", 0.0.1 -> "Arabia", 0
                                                  //| .0 -> name, 0 -> country),Map(0 -> (1,3), 0.0 -> (1,3), 0.0.0 -> (1,3), 0.0.
                                                  //| 1 -> (1,3)),Map((0.0,0.0.0) -> opN, (0.0,0.0.1) -> opN, (0,0.0) -> name),Map
                                                  //| ((0.0,0.0.0) -> op1, (0.0,0.0.1) -> op2, (0,0.0) -> name),List())
  val s = Sentence(saudi(0)._1, saudi(0)._2)      //> s  : amr.Sentence = Sentence(Saudi Arabia (SA),
                                                  //| NodeMap:	Map(1 -> Saudi, 2 -> Arabia, 4 -> SA)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 4 -> (3,4))
                                                  //| Edges:	Map((2,1) -> nn, (2,4) -> appos)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map(),Some(AMRGraph(Map(0 -> "Saudi"),Map(0 -> (1,2)),Ma
                                                  //| p(),Map(),List())),Map(1 -> 0))
  val s_amr = s.amr.get                           //> s_amr  : amr.AMRGraph = AMRGraph(Map(0 -> "Saudi"),Map(0 -> (1,2)),Map(),Map
                                                  //| (),List())
  Smatch.fScore(s_amr, sa_amr, 1, 1000)           //> AMR1 remaining: 2, AMR2: 0
                                                  //| Best Move: Map()
                                                  //| 
                                                  //| 0.0:name
                                                  //| 0:country
                                                  //| 0:name:0.0
                                                  //| 0.0:op1:"Saudi"
                                                  //| 0.0:op2:"Arabia"
                                                  //| 0:ROOT:country
                                                  //| 
                                                  //| _R:R:"Saudi"
                                                  //| _0:ROOT:"Saudi"
                                                  //| 
                                                  //| R:R:"Saudi"
                                                  //| 0:ROOT:"Saudi"
                                                  //| 
                                                  //| 
                                                  //| res0: (Double, Double, Double, Double, Int, Int, Int) = (0.0,0.0,0.0,8.0,0,6
                                                  //| ,2)
  Smatch.fScore(sa_amr, s_amr, 1, 1000)           //> AMR1 remaining: 2, AMR2: 0
                                                  //| Best Move: Map()
                                                  //| 
                                                  //| 0.0:name
                                                  //| 0:country
                                                  //| 0:name:0.0
                                                  //| 0.0:op1:"Saudi"
                                                  //| 0.0:op2:"Arabia"
                                                  //| 0:ROOT:country
                                                  //| 
                                                  //| _R:R:"Saudi"
                                                  //| _0:ROOT:"Saudi"
                                                  //| 
                                                  //| R:R:"Saudi"
                                                  //| 0:ROOT:"Saudi"
                                                  //| 
                                                  //| 
                                                  //| res1: (Double, Double, Double, Double, Int, Int, Int) = (0.0,0.0,0.0,8.0,0,2
                                                  //| ,6)
 Smatch.naiveFScore(s_amr, sa_amr)                //> res2: (Double, Double, Double, Double, Int, Int, Int) = (0.25,0.142857142857
                                                  //| 14285,1.0,6.0,1,1,7)
}