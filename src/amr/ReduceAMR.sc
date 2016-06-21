package amr

object ReduceAMR {
val testData = AMRGraph.importFile("C:\\AMR\\Estonian.txt")
                                                  //> testData  : IndexedSeq[(String, String, String)] = Vector((NATO CONSIDERS cyb
                                                  //| er attacks a threat to military and civilian computer networks after the Esto
                                                  //| nian Government was struck by cyber attacks in 2007.,(c / consider-02 :ARG0 (
                                                  //| m2 / military :wiki "NATO" :name (n / name :op1 "NATO")) :ARG1 (a / attack-01
                                                  //|  :mod (c2 / cyber)) :ARG2 (t / thing :ARG1-of (t2 / threaten-01 :ARG2 (a5 / a
                                                  //| nd :op1 (n2 / network :mod (m / military)) :op2 (n4 / network :mod (c3 / civi
                                                  //| lian)) :mod (c4 / computer)))) :time (a3 / after :op1 (s / strike-01 :ARG1 (g
                                                  //|  / government-organization :ARG0-of (g2 / govern-01 :ARG1 (c5 / country :wiki
                                                  //|  "Estonia" :name (n3 / name :op1 "Estonia")))) :ARG2 (a4 / attack :mod (c6 / 
                                                  //| cyber)) :time (d / date-entity :year 2007)))),# ::id PROXY_APW_ENG_20080514_1
                                                  //| 125.4))

 val s = testData map {d => Sentence(d._1, d._2, "id")}
                                                  //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.3 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [5.7 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [2.5 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [5.6 sec].
                                                  //| WARNING: Found duplicate match for concept attack-01
                                                  //| WARNING: Found duplicate match for concept cyber
                                                  //| s  : IndexedSeq[amr.Sentence] = Vector(id
                                                  //| NATO CONSIDERS cyber attacks a threat to military and civilian computer netw
                                                  //| orks after the Estonian Government was struck by cyber attacks in 2007.
                                                  //| 
                                                  //| NodeMap:	Map(1 -> NATO, 2 -> CONSIDERS, 3 -> cyber, 4 -> attacks, 5 -> a,
                                                  //|  6 -> threat, 7 -> to, 8 -> military, 9 -> and, 10 -> civilian, 11 -> comput
                                                  //| er, 12 -> networks, 13 -> after, 14 -> the, 15 -> Estonian, 16 -> Government
                                                  //| , 17 -> was, 18 -> struck, 19 -> by, 20 -> cyber, 21 -> attacks, 22 -> in, 2
                                                  //| 3 -> 2007)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,12
                                                  //| ), 12 -> (12,13), 13 -> (13,14), 14 -> (14,15), 15 -> (15,16), 16 -> (16,17)
                                                  //| , 17 -> (17,18), 18 -> (18,19), 19 -> (19,20), 20 -> (20,21), 21 -> (21,22),
                                                  //|  22 -> (22,23), 23 -> (23,24))
                                                  //| Edges:	Map((3,1) -> nn, (3,2) -> nn, (4,3) -> nsubj, (4,6) -> dobj, (4,
                                                  //| 7) -> prep, (4,18) -> advcl, (6,5) -> det, (7,12) -> pobj, (8,9) -> cc, (8,1
                                                  //| 0) -> conj, (12,8) -> amod, (12,11) -> nn, (16,14) -> det, (16,15) -> amod, 
                                                  //| (18,13) -> mark, (18,16) -> nsubjpass, (18,17) -> auxpass, (18,19) -> prep, 
                                                  //| (19,21) -> pobj, (21,20) -> nn, (21,22) -> prep, (22,23) -> pobj)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map()
                                                  //| PartsOfSpeech:	Map(5 -> DT, 10 -> JJ, 14 -> DT, 20 -> NN, 1 -> NNP, 6 -
                                                  //| > NN, 21 -> NNS, 9 -> CC, 13 -> IN, 2 -> NNP, 17 -> VBD, 22 -> IN, 12 -> NNS
                                                  //| , 7 -> TO, 3 -> NN, 18 -> VBN, 16 -> NN, 11 -> NN, 23 -> CD, 8 -> JJ, 19 -> 
                                                  //| IN, 4 -> VBZ, 15 -> JJ)
                                                  //| DependencyLabels:	Map(5 -> det, 10 -> conj, 14 -> det, 20 -> nn, 1 -> nn, 
                                                  //| 6 -> dobj, 21 -> pobj, 9 -> cc, 
                                                  //| Output exceeds cutoff limit.
 
 val amrGraph = s(0).amr.get                      //> amrGraph  : amr.AMRGraph = AMRGraph(Map(0.1 -> attack-01, 0.3.0.0.0.0.0 -> "
                                                  //| Estonia", 0.0.0 -> "NATO", 0.3.0.0.0.0 -> country, 0.2.0.0.0 -> network, 0.1
                                                  //| .0 -> cyber, 0.2.0.0.1.0 -> civilian, 0.0.1.0 -> "NATO", 0.3.0.2.0 -> 2007, 
                                                  //| 0.0 -> military, 0.2.0.0.1 -> network, 0.3.0.1.0 -> cyber, 0.3.0.0.0.0.1 -> 
                                                  //| name, 0.2.0 -> threaten-01, 0.0.1 -> name, 0.3.0.0 -> government-organizatio
                                                  //| n, 0.3.0 -> strike-01, 0.3 -> after, 0.2.0.0.0.0 -> military, 0.2.0.0.2 -> c
                                                  //| omputer, 0 -> consider-02, 0.3.0.1 -> attack, 0.2 -> thing, 0.3.0.2 -> date-
                                                  //| entity, 0.2.0.0 -> and, 0.3.0.0.0.0.1.0 -> "Estonia", 0.3.0.0.0 -> govern-01
                                                  //| ),Map(0.1 -> (4,5), 0.3.0.0.0.0 -> (15,16), 0.2.0.0.0 -> (12,13), 0.1.0 -> (
                                                  //| 3,4), 0.2.0.0.1.0 -> (10,11), 0.0.1.0 -> (1,2), 0.3.0.2.0 -> (23,24), 0.0 ->
                                                  //|  (8,9), 0.3.0.1.0 -> (20,21), 0.3.0.0.0.0.1 -> (15,16), 0.2.0 -> (6,7), 0.0.
                                                  //| 1 -> (1,2), 0.3.0.0 -> (16,17), 0.3.0 -> (18,19), 0.3 -> (13,14), 0.2.0.0.2 
                                                  //| -> (11,12), 0 -> (2,3), 0.3.0.1 -> (21,22), 0.2 -> (6,7), 0.3.0.2 -> (23,24)
                                                  //| , 0.2.0.0 -> (9,10), 0.3.0.0.0.0.1.0 -> (15,16)),Map((0.3.0.0.0.0,0.3.0.0.0.
                                                  //| 0.1) -> name, (0.0,0.0.0) -> wiki, (0.3.0,0.3.0.0) -> ARG1, (0,0.3) -> time,
                                                  //|  (0.2.0.0.1,0.2.0.0.1.0) -> mod, (0.1,0.1.0) -> mod, (0.3.0,0.3.0.1) -> ARG2
                                                  //| , (0.3.0,0.3.0.2) -> time, (0.3.0.0,0.3.0.0.0) -> ARG0-of, (0,0.2) -> ARG2, 
                                                  //| (0,0.1) -> ARG1, (0.2.0.0.0,0.2.0.0.0.0) -> mod, (0.2,0.2.0) -> ARG1-of, (0.
                                                  //| 3.0.0.0.0.1,0.3.0.0.0.0.1.0) -> opN, (0.3,0.3.0) -> opN, (0.2.0.0,0.2.0.0.1)
                                                  //|  -> opN, (0.2.0.0,0.2.0.0.0) -> opN, (0.3.0.1,0.3.0.1.0) -> mod, (0,0.0) -> 
                                                  //| ARG0, (0.0.1,0.0.1.0) -> opN, (0.2.0,0.2.0.0) -> ARG2, (0.0,0.0.1) -> name, 
                                                  //| (0.3.0.0.0,0.3.0.0.0.0) -> ARG1, (0.3.0.2,0.3.0.2.0) -> year, (0.3.0.0.0.0,0
                                                  //| .3.0.0.0.0.0) -> wiki, (0.2.0.0,0.2.0.0.2) -> mod),Map((0.3.0.0.0.0,0.3.0.0.
                                                  //| 0.0.1) -> name, (0.0,0.0.0) -> wiki, (0.3.0,0.3.0.0) -> ARG1, (0,0.3) -> tim
                                                  //| e, (0.2.0.0.1,0.2.0.0.1.0) -> mod, (0.1,0.1.0) -> mod, (0.3.0,0.3.0.1) -> AR
                                                  //| G2, (0.3.0,0.3.0.2) -> time, (0.3.0.0,0.3.0.0.0) -> ARG0-of, (0,0.2) -> ARG2
                                                  //| , (0,0.1) -> ARG1, (0.2.0.0.0,0.2.0.0.0.0) -> mod, (0.2,0.2.0) -> ARG1-of, (
                                                  //| 0.3.0.0.0.0.1,0.3.0.0.0.0.1.0) -> op1, (0.3,0.3.0) -> op1, (0.2.0.0,0.2.0.0.
                                                  //| 1) -> op2, (0.2.0.0,0.2.0.0.0) -> op1, (0.3.0.1,0.3.0.1.0) -> mod, (0,0.0) -
                                                  //| > ARG0, (0.0.1,0.0.1.0) -> op1, (0.2.0,0.2.0.0) -> ARG2, (0.0,0.0.1) -> name
                                                  //| , (0.3.0.0.0,0.3.0.0.0.0) -> ARG1, (0.3.0.2,0.3.0.2.0) -> year, (0
                                                  //| Output exceeds cutoff limit.
 val newNodes = amrGraph.nodes map {
 case a if a._1 == "0.0" => ("0.0" -> "mil:itary")
 case a if a._1 == "0.0.1.0" => ("0.0.1.0" -> """"NA:TO"""")
 case b => b
 }                                                //> newNodes  : scala.collection.immutable.Map[String,String] = Map(0.1 -> attac
                                                  //| k-01, 0.3.0.0.0.0.0 -> "Estonia", 0.0.0 -> "NATO", 0.3.0.0.0.0 -> country, 0
                                                  //| .2.0.0.0 -> network, 0.1.0 -> cyber, 0.2.0.0.1.0 -> civilian, 0.0.1.0 -> "NA
                                                  //| :TO", 0.3.0.2.0 -> 2007, 0.0 -> mil:itary, 0.2.0.0.1 -> network, 0.3.0.1.0 -
                                                  //| > cyber, 0.3.0.0.0.0.1 -> name, 0.2.0 -> threaten-01, 0.0.1 -> name, 0.3.0.0
                                                  //|  -> government-organization, 0.3.0 -> strike-01, 0.3 -> after, 0.2.0.0.0.0 -
                                                  //| > military, 0.2.0.0.2 -> computer, 0 -> consider-02, 0.3.0.1 -> attack, 0.2 
                                                  //| -> thing, 0.3.0.2 -> date-entity, 0.2.0.0 -> and, 0.3.0.0.0.0.1.0 -> "Estoni
                                                  //| a", 0.3.0.0.0 -> govern-01)
 val amendedGraph = amrGraph.copy(nodes =  newNodes)
                                                  //> amendedGraph  : amr.AMRGraph = AMRGraph(Map(0.1 -> attack-01, 0.3.0.0.0.0.0 
                                                  //| -> "Estonia", 0.0.0 -> "NATO", 0.3.0.0.0.0 -> country, 0.2.0.0.0 -> network,
                                                  //|  0.1.0 -> cyber, 0.2.0.0.1.0 -> civilian, 0.0.1.0 -> "NA:TO", 0.3.0.2.0 -> 2
                                                  //| 007, 0.0 -> mil:itary, 0.2.0.0.1 -> network, 0.3.0.1.0 -> cyber, 0.3.0.0.0.0
                                                  //| .1 -> name, 0.2.0 -> threaten-01, 0.0.1 -> name, 0.3.0.0 -> government-organ
                                                  //| ization, 0.3.0 -> strike-01, 0.3 -> after, 0.2.0.0.0.0 -> military, 0.2.0.0.
                                                  //| 2 -> computer, 0 -> consider-02, 0.3.0.1 -> attack, 0.2 -> thing, 0.3.0.2 ->
                                                  //|  date-entity, 0.2.0.0 -> and, 0.3.0.0.0.0.1.0 -> "Estonia", 0.3.0.0.0 -> gov
                                                  //| ern-01),Map(0.1 -> (4,5), 0.3.0.0.0.0 -> (15,16), 0.2.0.0.0 -> (12,13), 0.1.
                                                  //| 0 -> (3,4), 0.2.0.0.1.0 -> (10,11), 0.0.1.0 -> (1,2), 0.3.0.2.0 -> (23,24), 
                                                  //| 0.0 -> (8,9), 0.3.0.1.0 -> (20,21), 0.3.0.0.0.0.1 -> (15,16), 0.2.0 -> (6,7)
                                                  //| , 0.0.1 -> (1,2), 0.3.0.0 -> (16,17), 0.3.0 -> (18,19), 0.3 -> (13,14), 0.2.
                                                  //| 0.0.2 -> (11,12), 0 -> (2,3), 0.3.0.1 -> (21,22), 0.2 -> (6,7), 0.3.0.2 -> (
                                                  //| 23,24), 0.2.0.0 -> (9,10), 0.3.0.0.0.0.1.0 -> (15,16)),Map((0.3.0.0.0.0,0.3.
                                                  //| 0.0.0.0.1) -> name, (0.0,0.0.0) -> wiki, (0.3.0,0.3.0.0) -> ARG1, (0,0.3) ->
                                                  //|  time, (0.2.0.0.1,0.2.0.0.1.0) -> mod, (0.1,0.1.0) -> mod, (0.3.0,0.3.0.1) -
                                                  //| > ARG2, (0.3.0,0.3.0.2) -> time, (0.3.0.0,0.3.0.0.0) -> ARG0-of, (0,0.2) -> 
                                                  //| ARG2, (0,0.1) -> ARG1, (0.2.0.0.0,0.2.0.0.0.0) -> mod, (0.2,0.2.0) -> ARG1-o
                                                  //| f, (0.3.0.0.0.0.1,0.3.0.0.0.0.1.0) -> opN, (0.3,0.3.0) -> opN, (0.2.0.0,0.2.
                                                  //| 0.0.1) -> opN, (0.2.0.0,0.2.0.0.0) -> opN, (0.3.0.1,0.3.0.1.0) -> mod, (0,0.
                                                  //| 0) -> ARG0, (0.0.1,0.0.1.0) -> opN, (0.2.0,0.2.0.0) -> ARG2, (0.0,0.0.1) -> 
                                                  //| name, (0.3.0.0.0,0.3.0.0.0.0) -> ARG1, (0.3.0.2,0.3.0.2.0) -> year, (0.3.0.0
                                                  //| .0.0,0.3.0.0.0.0.0) -> wiki, (0.2.0.0,0.2.0.0.2) -> mod),Map((0.3.0.0.0.0,0.
                                                  //| 3.0.0.0.0.1) -> name, (0.0,0.0.0) -> wiki, (0.3.0,0.3.0.0) -> ARG1, (0,0.3) 
                                                  //| -> time, (0.2.0.0.1,0.2.0.0.1.0) -> mod, (0.1,0.1.0) -> mod, (0.3.0,0.3.0.1)
                                                  //|  -> ARG2, (0.3.0,0.3.0.2) -> time, (0.3.0.0,0.3.0.0.0) -> ARG0-of, (0,0.2) -
                                                  //| > ARG2, (0,0.1) -> ARG1, (0.2.0.0.0,0.2.0.0.0.0) -> mod, (0.2,0.2.0) -> ARG1
                                                  //| -of, (0.3.0.0.0.0.1,0.3.0.0.0.0.1.0) -> op1, (0.3,0.3.0) -> op1, (0.2.0.0,0.
                                                  //| 2.0.0.1) -> op2, (0.2.0.0,0.2.0.0.0) -> op1, (0.3.0.1,0.3.0.1.0) -> mod, (0,
                                                  //| 0.0) -> ARG0, (0.0.1,0.0.1.0) -> op1, (0.2.0,0.2.0.0) -> ARG2, (0.0,0.0.1) -
                                                  //| > name, (0.3.0.0.0,0.3.0.0.0.0) -> ARG1, (0.3.0.2,0.3.0.2.0) -> ye
                                                  //| Output exceeds cutoff limit.
 Smatch.reduceAMR(amendedGraph)                   //> res0: amr.AMRGraph = AMRGraph(Map(0.1 -> attack-01, 0.3.0.0.0.0 -> country, 
                                                  //| 0.2.0.0.0 -> network, 0.1.0 -> cyber, 0.2.0.0.1.0 -> civilian, 0.0 -> mil:it
                                                  //| ary, 0.2.0.0.1 -> network, 0.3.0.1.0 -> cyber, 0.3.0.0.0.0.1 -> name, 0.2.0 
                                                  //| -> threaten-01, 0.0.1 -> name, 0.3.0.0 -> government-organization, 0.3.0 -> 
                                                  //| strike-01, 0.3 -> after, 0.2.0.0.0.0 -> military, 0.2.0.0.2 -> computer, 0 -
                                                  //| > consider-02, 0.3.0.1 -> attack, 0.2 -> thing, 0.3.0.2 -> date-entity, 0.2.
                                                  //| 0.0 -> and, 0.3.0.0.0 -> govern-01),Map(0.1 -> (4,5), 0.3.0.0.0.0 -> (15,16)
                                                  //| , 0.2.0.0.0 -> (12,13), 0.1.0 -> (3,4), 0.2.0.0.1.0 -> (10,11), 0.0.1.0 -> (
                                                  //| 1,2), 0.3.0.2.0 -> (23,24), 0.0 -> (8,9), 0.3.0.1.0 -> (20,21), 0.3.0.0.0.0.
                                                  //| 1 -> (15,16), 0.2.0 -> (6,7), 0.0.1 -> (1,2), 0.3.0.0 -> (16,17), 0.3.0 -> (
                                                  //| 18,19), 0.3 -> (13,14), 0.2.0.0.2 -> (11,12), 0 -> (2,3), 0.3.0.1 -> (21,22)
                                                  //| , 0.2 -> (6,7), 0.3.0.2 -> (23,24), 0.2.0.0 -> (9,10), 0.3.0.0.0.0.1.0 -> (1
                                                  //| 5,16)),Map((0.3.0.0.0.0,0.3.0.0.0.0.1) -> name, (0.3.0,0.3.0.0) -> ARG1, (0,
                                                  //| 0.3) -> time, (0.2.0.0.1,0.2.0.0.1.0) -> mod, (0.1,0.1.0) -> mod, (0.3.0,0.3
                                                  //| .0.1) -> ARG2, (0.3.0,0.3.0.2) -> time, (0.3.0.0,0.3.0.0.0) -> ARG0-of, (0,0
                                                  //| .2) -> ARG2, (0,0.1) -> ARG1, (0.2.0.0.0,0.2.0.0.0.0) -> mod, (0.2,0.2.0) ->
                                                  //|  ARG1-of, (0.3,0.3.0) -> op1, (0.2.0.0,0.2.0.0.1) -> op2, (0.2.0.0,0.2.0.0.0
                                                  //| ) -> op1, (0.3.0.1,0.3.0.1.0) -> mod, (0,0.0) -> ARG0, (0.2.0,0.2.0.0) -> AR
                                                  //| G2, (0.0,0.0.1) -> name, (0.3.0.0.0,0.3.0.0.0.0) -> ARG1, (0.2.0.0,0.2.0.0.2
                                                  //| ) -> mod),Map((0.3.0.0.0.0,0.3.0.0.0.0.1) -> name, (0.0,0.0.0) -> wiki, (0.3
                                                  //| .0,0.3.0.0) -> ARG1, (0,0.3) -> time, (0.2.0.0.1,0.2.0.0.1.0) -> mod, (0.1,0
                                                  //| .1.0) -> mod, (0.3.0,0.3.0.1) -> ARG2, (0.3.0,0.3.0.2) -> time, (0.3.0.0,0.3
                                                  //| .0.0.0) -> ARG0-of, (0,0.2) -> ARG2, (0,0.1) -> ARG1, (0.2.0.0.0,0.2.0.0.0.0
                                                  //| ) -> mod, (0.2,0.2.0) -> ARG1-of, (0.3.0.0.0.0.1,0.3.0.0.0.0.1.0) -> op1, (0
                                                  //| .3,0.3.0) -> op1, (0.2.0.0,0.2.0.0.1) -> op2, (0.2.0.0,0.2.0.0.0) -> op1, (0
                                                  //| .3.0.1,0.3.0.1.0) -> mod, (0,0.0) -> ARG0, (0.0.1,0.0.1.0) -> op1, (0.2.0,0.
                                                  //| 2.0.0) -> ARG2, (0.0,0.0.1) -> name, (0.3.0.0.0,0.3.0.0.0.0) -> ARG1, (0.3.0
                                                  //| .2,0.3.0.2.0) -> year, (0.3.0.0.0.0,0.3.0.0.0.0.0) -> wiki, (0.2.0.0,0.2.0.0
                                                  //| .2) -> mod),List((0.3.0.0.0.0,wiki,"Estonia"), (0.0,wiki,"NATO"), (0.0.1,op1
                                                  //| ,"NA:TO"), (0.3.0.2,year,2007), (0.3.0.0.0.0.1,op1,"Estonia"), (0,ROOT,consi
                                                  //| der-02)))
 
}