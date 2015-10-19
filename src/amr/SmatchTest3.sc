package amr

object SmatchTest3 {

  val target = AMRGraph.importFile("C://AMR//daggerTest//ResultSov.txt")
                                                  //> target  : IndexedSeq[(String, String)] = Vector((It is difficult to defend t
                                                  //| he sovereignty of land and sea borders and air space.,( c / difficult :op1 (
                                                  //|  j / and :op1 ( e / defend) :mod ( i / land) :op2 ( m / and :op1 ( o / space
                                                  //| ))))))
  val s0 = Sentence(target(0)._1, target(0)._2)   //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.1 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [3.8 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [3.1 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [2.5 sec].
                                                  //| WARNING: Found duplicate match for concept and
                                                  //| s0  : amr.Sentence = Sentence(It is difficult to defend the sovereignty of l
                                                  //| and and sea borders and air space.,
                                                  //| NodeMap:	Map(1 -> It, 2 -> is, 3 -> difficult, 4 -> to, 5 -> defend, 6 ->
                                                  //|  the, 7 -> sovereignty, 8 -> of, 9 -> land, 10 -> and, 11 -> sea, 12 -> bord
                                                  //| ers, 13 -> and, 14 -> air, 15 -> space)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,12
                                                  //| ), 12 -> (12,13), 13 -> (13,14), 14 -> (14,15), 15 -> (15,16))
                                                  //| Edges:	Map((3,1) -> nsubj, (3,2) -> cop, (3,5) -> xcomp, (5,4) -> aux, 
                                                  //| (5,7) -> dobj, (7,6) -> det, (7,8) -> prep, (8,12) -> pobj, (9,10) -> cc, (9
                                                  //| ,11) -> conj, (12,9) -> nn, (12,13) -> cc, (12,15) -> conj, (15,14) -> nn)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map(),Some(AMRGraph(Map(0.0.0 -> defend, 0.0 -> and, 0.0
                                                  //| .2 -> and, 0.0.1 -> land, 0 -> difficult, 0.0.2.0 -> space),Map(0.0.0 -> (5,
                                                  //| 6), 0.0 -> (10,11), 0.0.2 -> (13,14), 0.0.1 -> (9,10), 0 -> (3,4), 0.0.2.0 -
                                                  //| > (15,16)),Map((0.0,0.0.0) -> opN, (0.0.2,0.0.2.0) -> opN, (0.0,0.0.2) -> op
                                                  //| N, (0,0.0) -> opN, (0.0,0.0.1) -> mod),Map((0.0,0.0.0) -> op1, (0.0.2,0.0.2.
                                                  //| 0) -> op1, (0.0,0.0.2) -> op2, (0,0.0) -> op1, (0.0,0.0.1) -> mod),List())),
                                                  //| Map(5 -> 0.0.0, 10 -> 0.0, 9 -> 0.0.1, 13 -> 0.0.2, 3 -> 0, 15 -> 0.0.2.0))
                                                  //| 
  val amr0 = s0.amr.get                           //> amr0  : amr.AMRGraph = AMRGraph(Map(0.0.0 -> defend, 0.0 -> and, 0.0.2 -> an
                                                  //| d, 0.0.1 -> land, 0 -> difficult, 0.0.2.0 -> space),Map(0.0.0 -> (5,6), 0.0 
                                                  //| -> (10,11), 0.0.2 -> (13,14), 0.0.1 -> (9,10), 0 -> (3,4), 0.0.2.0 -> (15,16
                                                  //| )),Map((0.0,0.0.0) -> opN, (0.0.2,0.0.2.0) -> opN, (0.0,0.0.2) -> opN, (0,0.
                                                  //| 0) -> opN, (0.0,0.0.1) -> mod),Map((0.0,0.0.0) -> op1, (0.0.2,0.0.2.0) -> op
                                                  //| 1, (0.0,0.0.2) -> op2, (0,0.0) -> op1, (0.0,0.0.1) -> mod),List())

  val result = AMRGraph.importFile("C://AMR//daggerTest//TargetSov.txt")
                                                  //> result  : IndexedSeq[(String, String)] = Vector((It is difficult to defend t
                                                  //| he sovereignty of land and sea borders and air space.,( ` / difficult :domai
                                                  //| n ( 0.0 / defend-01 :ARG1 ( 0.0.0 / sovereignty :poss ( 0.0.0.0 / and :op2 (
                                                  //|  0.0.0.0.1 / border :mod ( 0.0.0.0.1.0 / sea)) :op1 ( 0.0.0.0.0 / border :mo
                                                  //| d ( 0.0.0.0.0.0 / land)) :op3 ( 0.0.0.0.2 / space :mod ( 0.0.0.0.2.0 / air))
                                                  //| ))))))
  val s1 = Sentence(result(0)._1, result(0)._2)   //> WARNING: Found duplicate match for concept and
                                                  //| s1  : amr.Sentence = Sentence(It is difficult to defend the sovereignty of l
                                                  //| and and sea borders and air space.,
                                                  //| NodeMap:	Map(1 -> It, 2 -> is, 3 -> difficult, 4 -> to, 5 -> defend, 6 ->
                                                  //|  the, 7 -> sovereignty, 8 -> of, 9 -> land, 10 -> and, 11 -> sea, 12 -> bord
                                                  //| ers, 13 -> and, 14 -> air, 15 -> space)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,12
                                                  //| ), 12 -> (12,13), 13 -> (13,14), 14 -> (14,15), 15 -> (15,16))
                                                  //| Edges:	Map((3,1) -> nsubj, (3,2) -> cop, (3,5) -> xcomp, (5,4) -> aux, 
                                                  //| (5,7) -> dobj, (7,6) -> det, (7,8) -> prep, (8,12) -> pobj, (9,10) -> cc, (9
                                                  //| ,11) -> conj, (12,9) -> nn, (12,13) -> cc, (12,15) -> conj, (15,14) -> nn)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map(),Some(AMRGraph(Map(0.0.0 -> sovereignty, 0.0.0.0.2 
                                                  //| -> space, 0.0.0.0.2.0 -> air, 0.0 -> defend-01, 0.0.0.0.1.0 -> land, 0.0.0.0
                                                  //| .1 -> border, 0.0.0.0 -> and, 0 -> difficult, 0.0.0.0.0.0 -> sea, 0.0.0.0.0 
                                                  //| -> border),Map(0.0.0 -> (7,8), 0.0.0.0.2 -> (15,16), 0.0.0.0.2.0 -> (14,15),
                                                  //|  0.0 -> (5,6), 0.0.0.0.1.0 -> (9,10), 0.0.0.0 -> (10,11), 0 -> (3,4), 0.0.0.
                                                  //| 0.0.0 -> (11,12), 0.0.0.0.0 -> (12,13)),Map((0.0,0.0.0) -> ARG1, (0.0.0.0,0.
                                                  //| 0.0.0.2) -> opN, (0.0.0.0,0.0.0.0.0) -> opN, (0.0.0.0.2,0.0.0.0.2.0) -> mod,
                                                  //|  (0.0.0,0.0.0.0) -> poss, (0.0.0.0,0.0.0.0.1) -> opN, (0.0.0.0.0,0.0.0.0.0.0
                                                  //| ) -> mod, (0,0.0) -> domain, (0.0.0.0.1,0.0.0.0.1.0) -> mod),Map((0.0,0.0.0)
                                                  //|  -> ARG1, (0.0.0.0,0.0.0.0.2) -> op3, (0.0.0.0,0.0.0.0.0) -> op2, (0.0.0.0.2
                                                  //| ,0.0.0.0.2.0) -> mod, (0.0.0,0.0.0.0) -> poss, (0.0.0.0,0.0.0.0.1) -> op1, (
                                                  //| 0.0.0.0.0,0.0.0.0.0.0) -> mod, (0,0.0) -> domain, (0.0.0.0.1,0.0.0.0.1.0) ->
                                                  //|  mod),List())),Map(5 -> 0.0, 10 -> 0.0.0.0, 14 -> 0.0.0.0.2.0, 9 -> 0.0.0.0.
                                                  //| 1.0, 12 -> 0.0.0.0.0, 7 -> 0.0.0, 3 -> 0, 11 -> 0.0.0.0.0.0, 15 -> 0.0.0.0.2
                                                  //| ))
  val amr1 = s1.amr.get                           //> amr1  : amr.AMRGraph = AMRGraph(Map(0.0.0 -> sovereignty, 0.0.0.0.2 -> space
                                                  //| , 0.0.0.0.2.0 -> air, 0.0 -> defend-01, 0.0.0.0.1.0 -> land, 0.0.0.0.1 -> bo
                                                  //| rder, 0.0.0.0 -> and, 0 -> difficult, 0.0.0.0.0.0 -> sea, 0.0.0.0.0 -> borde
                                                  //| r),Map(0.0.0 -> (7,8), 0.0.0.0.2 -> (15,16), 0.0.0.0.2.0 -> (14,15), 0.0 -> 
                                                  //| (5,6), 0.0.0.0.1.0 -> (9,10), 0.0.0.0 -> (10,11), 0 -> (3,4), 0.0.0.0.0.0 ->
                                                  //|  (11,12), 0.0.0.0.0 -> (12,13)),Map((0.0,0.0.0) -> ARG1, (0.0.0.0,0.0.0.0.2)
                                                  //|  -> opN, (0.0.0.0,0.0.0.0.0) -> opN, (0.0.0.0.2,0.0.0.0.2.0) -> mod, (0.0.0,
                                                  //| 0.0.0.0) -> poss, (0.0.0.0,0.0.0.0.1) -> opN, (0.0.0.0.0,0.0.0.0.0.0) -> mod
                                                  //| , (0,0.0) -> domain, (0.0.0.0.1,0.0.0.0.1.0) -> mod),Map((0.0,0.0.0) -> ARG1
                                                  //| , (0.0.0.0,0.0.0.0.2) -> op3, (0.0.0.0,0.0.0.0.0) -> op2, (0.0.0.0.2,0.0.0.0
                                                  //| .2.0) -> mod, (0.0.0,0.0.0.0) -> poss, (0.0.0.0,0.0.0.0.1) -> op1, (0.0.0.0.
                                                  //| 0,0.0.0.0.0.0) -> mod, (0,0.0) -> domain, (0.0.0.0.1,0.0.0.0.1.0) -> mod),Li
                                                  //| st())
  Smatch.reduceAMR(amr0)                          //> res0: amr.AMRGraph = AMRGraph(Map(0.0.0 -> defend, 0.0 -> and, 0.0.2 -> and,
                                                  //|  0.0.1 -> land, 0 -> difficult, 0.0.2.0 -> space),Map(0.0.0 -> (5,6), 0.0 ->
                                                  //|  (10,11), 0.0.2 -> (13,14), 0.0.1 -> (9,10), 0 -> (3,4), 0.0.2.0 -> (15,16))
                                                  //| ,Map((0.0,0.0.0) -> op1, (0.0.2,0.0.2.0) -> op1, (0.0,0.0.2) -> op2, (0,0.0)
                                                  //|  -> op1, (0.0,0.0.1) -> mod),Map((0.0,0.0.0) -> op1, (0.0.2,0.0.2.0) -> op1,
                                                  //|  (0.0,0.0.2) -> op2, (0,0.0) -> op1, (0.0,0.0.1) -> mod),List((0,ROOT,diffic
                                                  //| ult)))
  Smatch.reduceAMR(amr1)                          //> res1: amr.AMRGraph = AMRGraph(Map(0.0.0 -> sovereignty, 0.0.0.0.2 -> space, 
                                                  //| 0.0.0.0.2.0 -> air, 0.0 -> defend-01, 0.0.0.0.1.0 -> land, 0.0.0.0.1 -> bord
                                                  //| er, 0.0.0.0 -> and, 0 -> difficult, 0.0.0.0.0.0 -> sea, 0.0.0.0.0 -> border)
                                                  //| ,Map(0.0.0 -> (7,8), 0.0.0.0.2 -> (15,16), 0.0.0.0.2.0 -> (14,15), 0.0 -> (5
                                                  //| ,6), 0.0.0.0.1.0 -> (9,10), 0.0.0.0 -> (10,11), 0 -> (3,4), 0.0.0.0.0.0 -> (
                                                  //| 11,12), 0.0.0.0.0 -> (12,13)),Map((0.0,0.0.0) -> ARG1, (0.0.0.0,0.0.0.0.2) -
                                                  //| > op3, (0.0.0.0,0.0.0.0.0) -> op2, (0.0.0.0.2,0.0.0.0.2.0) -> mod, (0.0.0,0.
                                                  //| 0.0.0) -> poss, (0.0.0.0,0.0.0.0.1) -> op1, (0.0.0.0.0,0.0.0.0.0.0) -> mod, 
                                                  //| (0,0.0) -> domain, (0.0.0.0.1,0.0.0.0.1.0) -> mod),Map((0.0,0.0.0) -> ARG1, 
                                                  //| (0.0.0.0,0.0.0.0.2) -> op3, (0.0.0.0,0.0.0.0.0) -> op2, (0.0.0.0.2,0.0.0.0.2
                                                  //| .0) -> mod, (0.0.0,0.0.0.0) -> poss, (0.0.0.0,0.0.0.0.1) -> op1, (0.0.0.0.0,
                                                  //| 0.0.0.0.0.0) -> mod, (0,0.0) -> domain, (0.0.0.0.1,0.0.0.0.1.0) -> mod),List
                                                  //| ((0,ROOT,difficult)))
                                         
  Smatch.initialMap(amr0, amr1)                   //> res2: Map[String,String] = Map(0.0.0.0.2 -> 0.0.2.0, 0.0.0.0.2.0 -> 0.0.2, 0
                                                  //| .0.0.0.1.0 -> 0.0.1, 0.0.0.0.1 -> 0.0.0, 0.0.0.0 -> 0.0, 0 -> 0)
  val fScore = Smatch.fScore(amr0, amr1, 4, 10000)//> fScore  : (Double, Double, Double, Double, Int, Int, Int) = (0.4375,0.583333
                                                  //| 3333333334,0.35,18.0,7,20,12)
                                                 
  
  
  fScore                                          //> res3: (Double, Double, Double, Double, Int, Int, Int) = (0.4375,0.5833333333
                                                  //| 333334,0.35,18.0,7,20,12)

}