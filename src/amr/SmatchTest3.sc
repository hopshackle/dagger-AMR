package amr

object SmatchTest3 {

  val target = AMRGraph.importFile("C://AMR//daggerTest//Target4.txt")
                                                  //> target  : IndexedSeq[(String, String)] = Vector((NATO CONSIDERS cyber attack
                                                  //| s a threat to military and civilian computer networks after the Estonian Gov
                                                  //| ernment was struck by cyber attacks in 2007.,(c / consider-02 :ARG0 (m2 / mi
                                                  //| litary :name (n / name :op1 "NATO")) :ARG1 (a / attack-01 :mod (c2 / cyber))
                                                  //|  :ARG2 (t / thing :ARG1-of (t2 / threaten-01 :ARG2 (a5 / and :op1 (n2 / netw
                                                  //| ork :mod (m / military)) :op2 (n4 / network :mod (c3 / civilian)) :mod (c4 /
                                                  //|  computer)))))))
  val s0 = Sentence(target(0)._1, target(0)._2)   //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.1 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [4.1 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [3.1 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [2.5 sec].
                                                  //| WARNING: Found duplicate match for concept attack-01
                                                  //| WARNING: Found duplicate match for concept cyber
                                                  //| s0  : amr.Sentence = Sentence(NATO CONSIDERS cyber attacks a threat to milit
                                                  //| ary and civilian computer networks after the Estonian Government was struck 
                                                  //| by cyber attacks in 2007.,
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> NATO, 2 -> CONSIDERS, 3 -> cyber, 4 -> attac
                                                  //| ks, 5 -> a, 6 -> threat, 7 -> to, 8 -> military, 9 -> and, 10 -> civilian, 1
                                                  //| 1 -> computer, 12 -> networks, 13 -> after, 14 -> the, 15 -> Estonian, 16 ->
                                                  //|  Government, 17 -> was, 18 -> struck, 19 -> by, 20 -> cyber, 21 -> attacks, 
                                                  //| 22 -> in, 23 -> 2007, 24 -> .)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,12
                                                  //| ), 12 -> (12,13), 13 -> (13,14), 14 -> (14,15), 15 -> (15,16), 16 -> (16,17)
                                                  //| , 17 -> (17,18), 18 -> (18,19), 19 -> (19,20), 20 -> (20,21), 21 -> (21,22),
                                                  //|  22 -> (22,23), 23 -> (23,24), 24 -> (24,25))
                                                  //| Edges:	Map((0,4) -> root, (3,1) -> nn, (3,2) -> nn, (4,3) -> nsubj, (4,
                                                  //| 6) -> dobj, (4,7) -> prep, (4,18) -> advcl, (4,24) -> punct, (6,5) -> det, (
                                                  //| 7,12) -> pobj, (8,9) -> cc, (8,10) -> conj, (12,8) -> amod, (12,11) -> nn, (
                                                  //| 16,14) -> det, (16,15) -> amod, (18,13) -> mark, (18,16) -> nsubjpass, (18,1
                                                  //| 7) -> auxpass, (18,19) -> prep, (19,21) -> pobj, (21,20) -> nn, (21,22) -> p
                                                  //| rep, (22,23) -> pobj)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map(),Some(AMRGraph(Map(0.1 -> attack-01, 0.0.0 -> name,
                                                  //|  0.2.0.0.0 -> network, 0.1.0 -> cyber, 0.2.0.0.1.0 -> civilian, 0.0 -> milit
                                                  //| ary, 0.2.0.0.1 -> network, 0.0.0.0 -> "NATO", 0.2.0 -> threaten-01, 0.2.0.0.
                                                  //| 0.0 -> military, 0.2.0.0.2 -> computer, 0 -> consider-02, 0.2 -> thing, ROOT
                                                  //|  -> ROOT, 0.2.0.0 -> and),
                                                  //| Output exceeds cutoff limit.
  val amr0 = s0.amr.get                           //> amr0  : amr.AMRGraph = AMRGraph(Map(0.1 -> attack-01, 0.0.0 -> name, 0.2.0.0
                                                  //| .0 -> network, 0.1.0 -> cyber, 0.2.0.0.1.0 -> civilian, 0.0 -> military, 0.2
                                                  //| .0.0.1 -> network, 0.0.0.0 -> "NATO", 0.2.0 -> threaten-01, 0.2.0.0.0.0 -> m
                                                  //| ilitary, 0.2.0.0.2 -> computer, 0 -> consider-02, 0.2 -> thing, ROOT -> ROOT
                                                  //| , 0.2.0.0 -> and),Map(0.1 -> (4,5), 0.0.0 -> (1,2), 0.2.0.0.0 -> (12,13), 0.
                                                  //| 1.0 -> (3,4), 0.2.0.0.1.0 -> (10,11), 0.0 -> (8,9), 0.0.0.0 -> (1,2), 0.2.0 
                                                  //| -> (6,7), 0.2.0.0.2 -> (11,12), 0 -> (2,3), 0.2 -> (6,7), 0.2.0.0 -> (9,10))
                                                  //| ,Map((0.0,0.0.0) -> name, (ROOT,0) -> ROOT, (0.0.0,0.0.0.0) -> opN, (0.2.0.0
                                                  //| .1,0.2.0.0.1.0) -> mod, (0.1,0.1.0) -> mod, (0,0.2) -> ARG2, (0,0.1) -> ARG1
                                                  //| , (0.2.0.0.0,0.2.0.0.0.0) -> mod, (0.2,0.2.0) -> ARG1-of, (0.2.0.0,0.2.0.0.1
                                                  //| ) -> opN, (0.2.0.0,0.2.0.0.0) -> opN, (0,0.0) -> ARG0, (0.2.0,0.2.0.0) -> AR
                                                  //| G2, (0.2.0.0,0.2.0.0.2) -> mod),List())

  val result = AMRGraph.importFile("C://AMR//daggerTest//Result4.txt")
                                                  //> result  : IndexedSeq[(String, String)] = Vector((NATO CONSIDERS cyber attack
                                                  //| s a threat to military and civilian computer networks after the Estonian Gov
                                                  //| ernment was struck by cyber attacks in 2007.,( b / consider-02 :ARG1 ( d / a
                                                  //| ttack-01 :mod ( c / cyber)) :ARG2 ( d2 / thing :ARG1-of ( f / threaten-01 :A
                                                  //| RG2 ( i / and :op1 ( l / network) :op2 ( y / network :mod ( j / civilian)) :
                                                  //| mod ( k / computer)))) :ARG0 ( h / military :name ( c2 / name :op1 "NATO")))
                                                  //| ))
  val s1 = Sentence(result(0)._1, result(0)._2)   //> WARNING: Found duplicate match for concept attack-01
                                                  //| WARNING: Found duplicate match for concept cyber
                                                  //| s1  : amr.Sentence = Sentence(NATO CONSIDERS cyber attacks a threat to milit
                                                  //| ary and civilian computer networks after the Estonian Government was struck 
                                                  //| by cyber attacks in 2007.,
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> NATO, 2 -> CONSIDERS, 3 -> cyber, 4 -> attac
                                                  //| ks, 5 -> a, 6 -> threat, 7 -> to, 8 -> military, 9 -> and, 10 -> civilian, 1
                                                  //| 1 -> computer, 12 -> networks, 13 -> after, 14 -> the, 15 -> Estonian, 16 ->
                                                  //|  Government, 17 -> was, 18 -> struck, 19 -> by, 20 -> cyber, 21 -> attacks, 
                                                  //| 22 -> in, 23 -> 2007, 24 -> .)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,12
                                                  //| ), 12 -> (12,13), 13 -> (13,14), 14 -> (14,15), 15 -> (15,16), 16 -> (16,17)
                                                  //| , 17 -> (17,18), 18 -> (18,19), 19 -> (19,20), 20 -> (20,21), 21 -> (21,22),
                                                  //|  22 -> (22,23), 23 -> (23,24), 24 -> (24,25))
                                                  //| Edges:	Map((0,4) -> root, (3,1) -> nn, (3,2) -> nn, (4,3) -> nsubj, (4,
                                                  //| 6) -> dobj, (4,7) -> prep, (4,18) -> advcl, (4,24) -> punct, (6,5) -> det, (
                                                  //| 7,12) -> pobj, (8,9) -> cc, (8,10) -> conj, (12,8) -> amod, (12,11) -> nn, (
                                                  //| 16,14) -> det, (16,15) -> amod, (18,13) -> mark, (18,16) -> nsubjpass, (18,1
                                                  //| 7) -> auxpass, (18,19) -> prep, (19,21) -> pobj, (21,20) -> nn, (21,22) -> p
                                                  //| rep, (22,23) -> pobj)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map(),Some(AMRGraph(Map(0.1 -> thing, 0.0.0 -> cyber, 0.
                                                  //| 1.0 -> threaten-01, 0.1.0.0.1.0 -> civilian, 0.1.0.0.0 -> network, 0.0 -> at
                                                  //| tack-01, 0.2.0 -> name, 0.1.0.0.1 -> network, 0.1.0.0 -> and, 0.1.0.0.2 -> c
                                                  //| omputer, 0 -> consider-02, 0.2 -> military, ROOT -> ROOT, 0.2.0.0 -> "NATO")
                                                  //| ,Map(0.1 -> (6,7), 0.0.0 -> (3,4), 0.1.0 -> (6,7), 0.1.0.0.1.0 -> (10,11), 0
                                                  //| .1.0.0.0 -> (12,13), 0.0 -> (4,5), 0.2.0 -> (1,2), 0.1.0.0 -> (9,10), 0.1.0.
                                                  //| 0.2 -> (11,12), 0 -> (2,3), 0.2 -> (8,9), 0.2.0.0 -> (1,2)),Map((0.0,0.0.0) 
                                                  //| -> mod, (ROOT,0) -> ROOT, (0.1,0.1.0) -> ARG1-of, (0.1.0.0,0.1.0.0.2) -> mod
                                                  //| , (0.1.0,0.1.0.0) -> ARG2, (0,0.2) -> ARG0, (0,0.1) -> ARG2, (0.2,0.2.0) -> 
                                                  //| name, (0.1.0.0.1,0.1.0.0.1.0) -> mod, (0.1.0.0,0.1.0.0.0) -> opN, (0.1.0.0,0
                                                  //| .1.0.0.1) -> opN, (0,0.0) -> ARG1, (0.2.0,0.2.0.0) -> opN),List())),Map(0 ->
                                                  //|  ROOT, 10 -> 0.1.0.0.1.0, 1 -> 0.2.0.0, 6 -> 0.1.0, 9 -> 0.1.0.0, 2 -> 0, 12
                                                  //|  
                                                  //| Output exceeds cutoff limit.
  val amr1 = s1.amr.get                           //> amr1  : amr.AMRGraph = AMRGraph(Map(0.1 -> thing, 0.0.0 -> cyber, 0.1.0 -> t
                                                  //| hreaten-01, 0.1.0.0.1.0 -> civilian, 0.1.0.0.0 -> network, 0.0 -> attack-01,
                                                  //|  0.2.0 -> name, 0.1.0.0.1 -> network, 0.1.0.0 -> and, 0.1.0.0.2 -> computer,
                                                  //|  0 -> consider-02, 0.2 -> military, ROOT -> ROOT, 0.2.0.0 -> "NATO"),Map(0.1
                                                  //|  -> (6,7), 0.0.0 -> (3,4), 0.1.0 -> (6,7), 0.1.0.0.1.0 -> (10,11), 0.1.0.0.0
                                                  //|  -> (12,13), 0.0 -> (4,5), 0.2.0 -> (1,2), 0.1.0.0 -> (9,10), 0.1.0.0.2 -> (
                                                  //| 11,12), 0 -> (2,3), 0.2 -> (8,9), 0.2.0.0 -> (1,2)),Map((0.0,0.0.0) -> mod, 
                                                  //| (ROOT,0) -> ROOT, (0.1,0.1.0) -> ARG1-of, (0.1.0.0,0.1.0.0.2) -> mod, (0.1.0
                                                  //| ,0.1.0.0) -> ARG2, (0,0.2) -> ARG0, (0,0.1) -> ARG2, (0.2,0.2.0) -> name, (0
                                                  //| .1.0.0.1,0.1.0.0.1.0) -> mod, (0.1.0.0,0.1.0.0.0) -> opN, (0.1.0.0,0.1.0.0.1
                                                  //| ) -> opN, (0,0.0) -> ARG1, (0.2.0,0.2.0.0) -> opN),List())
  Smatch.reduceAMR(amr0)                          //> res0: amr.AMRGraph = AMRGraph(Map(0.1 -> attack-01, 0.0.0 -> name, 0.2.0.0.0
                                                  //|  -> network, 0.1.0 -> cyber, 0.2.0.0.1.0 -> civilian, 0.0 -> military, 0.2.0
                                                  //| .0.1 -> network, 0.2.0 -> threaten-01, 0.2.0.0.0.0 -> military, 0.2.0.0.2 ->
                                                  //|  computer, 0 -> consider-02, 0.2 -> thing, 0.2.0.0 -> and),Map(0.1 -> (4,5),
                                                  //|  0.0.0 -> (1,2), 0.2.0.0.0 -> (12,13), 0.1.0 -> (3,4), 0.2.0.0.1.0 -> (10,11
                                                  //| ), 0.0 -> (8,9), 0.0.0.0 -> (1,2), 0.2.0 -> (6,7), 0.2.0.0.2 -> (11,12), 0 -
                                                  //| > (2,3), 0.2 -> (6,7), 0.2.0.0 -> (9,10)),Map((0.0,0.0.0) -> name, (0.2.0.0.
                                                  //| 1,0.2.0.0.1.0) -> mod, (0.1,0.1.0) -> mod, (0,0.2) -> ARG2, (0,0.1) -> ARG1,
                                                  //|  (0.2.0.0.0,0.2.0.0.0.0) -> mod, (0.2,0.2.0) -> ARG1-of, (0.2.0.0,0.2.0.0.1)
                                                  //|  -> op1, (0.2.0.0,0.2.0.0.0) -> op2, (0,0.0) -> ARG0, (0.2.0,0.2.0.0) -> ARG
                                                  //| 2, (0.2.0.0,0.2.0.0.2) -> mod),List((0.0.0,op1,"NATO"), (0,ROOT,consider-02)
                                                  //| ))
  Smatch.reduceAMR(amr1)                          //> res1: amr.AMRGraph = AMRGraph(Map(0.1 -> thing, 0.0.0 -> cyber, 0.1.0 -> thr
                                                  //| eaten-01, 0.1.0.0.1.0 -> civilian, 0.1.0.0.0 -> network, 0.0 -> attack-01, 0
                                                  //| .2.0 -> name, 0.1.0.0.1 -> network, 0.1.0.0 -> and, 0.1.0.0.2 -> computer, 0
                                                  //|  -> consider-02, 0.2 -> military),Map(0.1 -> (6,7), 0.0.0 -> (3,4), 0.1.0 ->
                                                  //|  (6,7), 0.1.0.0.1.0 -> (10,11), 0.1.0.0.0 -> (12,13), 0.0 -> (4,5), 0.2.0 ->
                                                  //|  (1,2), 0.1.0.0 -> (9,10), 0.1.0.0.2 -> (11,12), 0 -> (2,3), 0.2 -> (8,9), 0
                                                  //| .2.0.0 -> (1,2)),Map((0.0,0.0.0) -> mod, (0.1,0.1.0) -> ARG1-of, (0.1.0.0,0.
                                                  //| 1.0.0.2) -> mod, (0.1.0,0.1.0.0) -> ARG2, (0,0.2) -> ARG0, (0,0.1) -> ARG2, 
                                                  //| (0.2,0.2.0) -> name, (0.1.0.0.1,0.1.0.0.1.0) -> mod, (0.1.0.0,0.1.0.0.0) -> 
                                                  //| op2, (0.1.0.0,0.1.0.0.1) -> op1, (0,0.0) -> ARG1),List((0,ROOT,consider-02),
                                                  //|  (0.2.0,op1,"NATO")))
                                         
  Smatch.initialMap(amr0, amr1)                   //> res2: Map[String,String] = Map(0.1 -> 0.2, 0.0.0 -> 0.1.0, 0.1.0 -> 0.2.0, 0
                                                  //| .1.0.0.1.0 -> 0.2.0.0.1.0, 0.1.0.0.0 -> 0.2.0.0.0, 0.0 -> 0.1, 0.2.0 -> 0.0.
                                                  //| 0, 0.1.0.0.1 -> 0.2.0.0.1, 0.1.0.0 -> 0.2.0.0, 0.1.0.0.2 -> 0.2.0.0.2, 0 -> 
                                                  //| 0, 0.2 -> 0.0, ROOT -> ROOT, 0.2.0.0 -> 0.0.0.0)
  val fScore = Smatch.fScore(amr0, amr1, 4, 10000)//> Best Move: Map(0.1.0.0.0 -> 0.2.0.0.0, 0.1.0.0.1 -> 0.2.0.0.1)
                                                  //| Best Move: Map()
                                                  //| 0.1 [thing] -> 0.2 [thing] 
                                                  //| 0.0.0 [cyber] -> 0.1.0 [cyber] 
                                                  //| 0.1.0 [threaten-01] -> 0.2.0 [threaten-01] 
                                                  //| 0.1.0.0.1.0 [civilian] -> 0.2.0.0.1.0 [civilian] 
                                                  //| 0.1.0.0.0 [network] -> 0.2.0.0.0 [network] 
                                                  //| 0.0 [attack-01] -> 0.1 [attack-01] 
                                                  //| 0.2.0 [name] -> 0.0.0 [name] 
                                                  //| 0.1.0.0.1 [network] -> 0.2.0.0.1 [network] 
                                                  //| 0.1.0.0 [and] -> 0.2.0.0 [and] 
                                                  //| 0.1.0.0.2 [computer] -> 0.2.0.0.2 [computer] 
                                                  //| 0 [consider-02] -> 0 [consider-02] 
                                                  //| 0.2 [military] -> 0.0 [military] 
                                                  //| 
                                                  //| Best Move: Map(0.2 -> 0.0)
                                                  //| Best Move: Map(0.1.0.0.0 -> 0.2.0.0.0, 0.1.0.0.1 -> 0.2.0.0.1)
                                                  //| Best Move: Map()
                                                  //| 0.1 [thing] -> 0.2 [thing] 
                                                  //| 0.0.0 [cyber] -> 0.1.0 [cyber] 
                                                  //| 0.1.0 [threaten-01] -> 0.2.0 [threaten-01] 
                                                  //| 0.1.0.0.1.0 [civilian] -> 0.2.0.0.1.0 [civilian] 
                                                  //| 0.1.0.0.0 [network] -> 0.2.0.0.0 [network] 
                                                  //| 0.0 [attack-01] -> 0.1 [attack-01] 
                                                  //| 0.2.0 [name] -> 0.0.0 [name] 
                                                  //| 0.1.0.0.1 [network] -> 0.2.0.0.1 [network] 
                                                  //| 0.1.0.0 [and] -> 0.2.0.0 [and] 
                                                  //| 0.1.0.0.2 [computer] -> 0.2.0.0.2 [computer] 
                                                  //| 0 [consider-02] -> 0 [consider-02] 
                                                  //| 0.2 [military] -> 0.0 [military] 
                                                  //| 
                                                  //| Best Move: Map()
                                                  //| Best Move: Map()
                                                  //| 0.1 [thing] -> 0.2 [thing] 
                                                  //| 0.0.0 [cyber] -> 0.1.0 [cyber] 
                                                  //| 0.1.0 [threaten-01] -> 0.2.0 [threaten-01] 
                                                  //| 0.1.0.0.1.0 [civilian] -> 0.2.0.0.1.0 [civilian] 
                                                  //| 0.1.0.0.0 [network] -> 0.2.0.0.0 [network] 
                                                  //| 0.0 [attack-01] -> 0.1 [attack-01] 
                                                  //| 0.2.0 [name] -> 0.0.0 [name] 
                                                  //| 0.1.0.0.1 [network] -> 0.2.0.0.1 [network] 
                                                  //| 0.1.0.0 [and] -> 0.2.0.0 [and] 
                                                  //| 0.1.0.0.2 [computer] -> 0.2.0.0.2 [computer] 
                                                  //| 0 [consider-02] -> 0 [consider-02] 
                                                  //| 0.2 [military] -> 0.0 [military] 
                                                  //| 
                                                  //| Best Move: Map(0.1.0.0.1 -> 0.2.0.0.1, 0.1.0.0.0 -> 0.2.0.0.0)
                                                  //| Best Move: Map(0.2 -> 0.0)
                                                  //| Best Move: Map()
                                                  //| 0.1 [thing] -> 0.2 [thing] 
                                                  //| 0.0.0 [cyber] -> 0.1.0 [cyber] 
                                                  //| 0.1.0 [threaten-01] -> 0.2.0 [threaten-01] 
                                                  //| 0.1.0.0.1.0 [civilian] -> 0.2.0.0.1.0 [civilian] 
                                                  //| 0.1.0.0.0 [network] -> 0.2.0.0.0 [network] 
                                                  //| 0.0 [attack-01] -> 0.1 [attack-01] 
                                                  //| 0.2.0 [name] -> 0.0.0 [name] 
                                                  //| 0.1.0.0.1 [network] -> 0.2.0.0.1 [network] 
                                                  //| 0.1.0.0 [and] -> 0.2.0.0 [and] 
                                                  //| 0.1.0.0.2 [computer] -> 0.2.0.0.2 [computer] 
                                                  //| 0 [consider-02] -> 0 [consider-02] 
                                                  //| 0.2 [military] -> 0.0 [military] 
                                                  //| 
                                                  //| fScore  : (Double, Double, Dou
                                                  //| Output exceeds cutoff limit.
  
  
  fScore                                          //> res3: (Double, Double, Double, Double, Int, Int, Int) = (0.9615384615384615,
                                                  //| 0.9259259259259259,1.0,2.0,25,25,27)

}