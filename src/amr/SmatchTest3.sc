package amr

object SmatchTest3 {

  val target = AMRGraph.importFile("C://AMR//daggerTest//Result3.txt")
                                                  //> target  : IndexedSeq[(String, String)] = Vector((Brazilian embassy officials
                                                  //|  stated that on 8 April 2002 Brazilian Defense Minister Geraldo da Cruz Quin
                                                  //| tao began a week-long series of meetings in Moscow with Russian officials du
                                                  //| ring which he is to meet Russian Defense Minister Sergei Ivanov.,( d / state
                                                  //| -01 :ARG0 ( c3 / person :ARG0-of ( a3 / have-org-role-91 :ARG1 ( b / embassy
                                                  //|  :poss ( b3 / country :name ( z2 / name :op1 "Brazil"))) :ARG2 ( c / officia
                                                  //| l))) :ARG1 ( q / begin-01 :time ( y2 / date-entity :day 8 :month 4 :year 200
                                                  //| 2) :ARG0 ( w2 / person :ARG0-of ( x2 / have-org-role-91 :ARG2 ( l / minister
                                                  //|  :topic ( k / defense))) :name ( v2 / name :op1 "Geraldo" :op2 "da" :op3 "Cr
                                                  //| uz" :op4 "Quintao")) :ARG1 ( t / series :duration ( s / week-long) :time-of 
                                                  //| ( v / meet-03 :UNKNOWN ( q2 / city :name ( p2 / name :op1 "Moscow"))) :consi
                                                  //| st-of ( g2 / meet-03 :ARG1 ( u2 / person :ARG0-of ( t2 / have-org-role-91 :A
                                                  //| RG2 ( a2 / official) :ARG1 ( s2 / country :name ( r2 / name :op1 "Russia")))
                                                  //| ) :UNKNOWN ( l2 / Ivanov :UNKNOWN ( n2 / person :ARG0-of ( m2 / have-org-rol
                                                  //| e-91 :ARG2 ( j2 / minister :topic ( i2 / defense))) :name ( o2 / name :op1 "
                                                  //| Sergei")))))))))
  val s0 = Sentence(target(0)._1, target(0)._2)   //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.5 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [4.4 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [3.6 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [3.1 sec].
                                                  //| WARNING: Found duplicate match for concept official
                                                  //| WARNING: Found duplicate match for concept minister
                                                  //| WARNING: Found duplicate match for concept defense
                                                  //| s0  : amr.Sentence = Sentence(Brazilian embassy officials stated that on 8 A
                                                  //| pril 2002 Brazilian Defense Minister Geraldo da Cruz Quintao began a week-lo
                                                  //| ng series of meetings in Moscow with Russian officials during which he is to
                                                  //|  meet Russian Defense Minister Sergei Ivanov.,
                                                  //| NodeMap:	Map(1 -> Brazilian, 2 -> embassy, 3 -> officials, 4 -> stated, 5
                                                  //|  -> that, 6 -> on, 7 -> 8, 8 -> 4, 9 -> 2002, 10 -> Brazilian, 11 -> Defense
                                                  //| , 12 -> Minister, 13 -> Geraldo, 14 -> da, 15 -> Cruz, 16 -> Quintao, 17 -> 
                                                  //| began, 18 -> a, 19 -> week-long, 20 -> series, 21 -> of, 22 -> meetings, 23 
                                                  //| -> in, 24 -> Moscow, 25 -> with, 26 -> Russian, 27 -> officials, 28 -> durin
                                                  //| g, 29 -> which, 30 -> he, 31 -> is, 32 -> to, 33 -> meet, 34 -> Russian, 35 
                                                  //| -> Defense, 36 -> Minister, 37 -> Sergei, 38 -> Ivanov)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,12
                                                  //| ), 12 -> (12,13), 13 -> (13,14), 14 -> (14,15), 15 -> (15,16), 16 -> (16,17)
                                                  //| , 17 -> (17,18), 18 -> (18,19), 19 -> (19,20), 20 -> (20,21), 21 -> (21,22),
                                                  //|  22 -> (22,23), 23 -> (23,24), 24 -> (24,25), 25 -> (25,26), 26 -> (26,27), 
                                                  //| 27 -> (27,28), 28 -> (28,29), 29 -> (29,30), 30 -> (30,31), 31 -> (31,32), 3
                                                  //| 2 -> (32,33), 33 -> (33,34), 34 -> (34,35), 35 -> (35,36), 36 -> (36,37), 37
                                                  //|  -> (37,38), 38 -> (38,39))
                                                  //| Edges:	Map((3,1) -> amod, (3,2) -> nn, (4,3) -> nsubj, (4,5) -> dobj, (
                                                  //| 4,6) -> prep, (4,8) -> tmod, (4,17) -> dep, (6,7) -> pobj, (8,9) -> num, (16
                                                  //| ,10) -> amod, (16,11) -> nn, (16,12) -> nn, (16,13) -> nn, (16,14) -> nn, (1
                                                  //| 6,15) -> nn, (17,16) -> nsubj, (
                                                  //| Output exceeds cutoff limit.
  val amr0 = s0.amr.get                           //> amr0  : amr.AMRGraph = AMRGraph(Map(0.1.2.2 -> meet-03, 0.1 -> begin-01, 0.0
                                                  //| .0 -> have-org-role-91, 0.1.2.2.0.0.1 -> country, 0.1.2.2.1.0.0.0.0 -> defen
                                                  //| se, 0.1.1.1.3 -> "Quintao", 0.1.1.1.2 -> "Cruz", 0.1.2.2.0.0.1.0.0 -> "Russi
                                                  //| a", 0.1.0 -> date-entity, 0.1.2.1.0.0 -> name, 0.1.0.1 -> 4, 0.0 -> person, 
                                                  //| 0.1.2.2.0 -> person, 0.1.2.2.1.0.0 -> have-org-role-91, 0.1.2.1 -> meet-03, 
                                                  //| 0.0.0.0 -> embassy, 0.1.1.1.1 -> "da", 0.1.1.0.0 -> minister, 0.1.0.2 -> 200
                                                  //| 2, 0.1.2.2.0.0.1.0 -> name, 0.1.2.2.1.0.0.0 -> minister, 0.1.2.2.1.0 -> pers
                                                  //| on, 0.0.0.0.0.0.0 -> "Brazil", 0.1.2.2.0.0 -> have-org-role-91, 0.1.0.0 -> 8
                                                  //| , 0.1.2.2.1 -> Ivanov, 0.1.2.0 -> week-long, 0.1.2.2.1.0.1 -> name, 0.1.1.1.
                                                  //| 0 -> "Geraldo", 0 -> state-01, 0.1.2 -> series, 0.1.2.1.0.0.0 -> "Moscow", 0
                                                  //| .0.0.1 -> official, 0.1.2.2.1.0.1.0 -> "Sergei", 0.1.1.1 -> name, 0.0.0.0.0.
                                                  //| 0 -> name, 0.0.0.0.0 -> country, 0.1.1.0 -> have-org-role-91, 0.1.2.2.0.0.0 
                                                  //| -> official, 0.1.2.1.0 -> city, 0.1.1 -> person, 0.1.1.0.0.0 -> defense),Map
                                                  //| (0.1.2.2 -> (22,23), 0.1 -> (17,18), 0.1.2.2.0.0.1 -> (26,27), 0.1.2.2.1.0.0
                                                  //| .0.0 -> (35,36), 0.1.1.1.3 -> (13,17), 0.1.1.1.2 -> (13,17), 0.1.2.2.0.0.1.0
                                                  //| .0 -> (26,27), 0.1.0 -> (7,10), 0.1.2.1.0.0 -> (24,25), 0.1.0.1 -> (7,10), 0
                                                  //| .0 -> (39,1), 0.1.2.2.0 -> (39,1), 0.1.2.1 -> (33,34), 0.0.0.0 -> (2,3), 0.1
                                                  //| .1.1.1 -> (13,17), 0.1.1.0.0 -> (12,13), 0.1.0.2 -> (7,10), 0.1.2.2.0.0.1.0 
                                                  //| -> (26,27), 0.1.2.2.1.0.0.0 -> (36,37), 0.1.2.2.1.0 -> (37,38), 0.0.0.0.0.0.
                                                  //| 0 -> (1,2), 0.1.0.0 -> (7,10), 0.1.2.2.1 -> (38,39), 0.1.2.0 -> (19,20), 0.1
                                                  //| .2.2.1.0.1 -> (37,38), 0.1.1.1.0 -> (13,17), 0 -> (4,5), 0.1.2 -> (20,21), 0
                                                  //| .1.2.1.0.0.0 -> (24,25), 0.0.0.1 -> (3,4), 0.1.2.2.1.0.1.0 -> (37,38), 0.1.1
                                                  //| .1 -> (13,17), 0.0.0.0.0.0 -> (1,2), 0.0.0.0.0 -> (1,2), 0.1.2.2.0.0.0 -> (2
                                                  //| 7,28), 0.1.2.1.0 -> (24,25), 0.1.1 -> (13,17), 0.1.1.0.0.0 -> (11,12)),Map((
                                                  //| 0.0,0.0.0) -> ARG0-of, (0.1.1.1,0.1.1.1.0) -> opN, (0.1.1.1,0.1.1.1.3) -> op
                                                  //| N, (0.1.1.0,0.1.1.0.0) -> ARG2, (0.1.2.2.0,0.1.2.2.0.0) -> ARG0-of, (0.0.0.0
                                                  //| ,0.0.0.0.0) -> poss, (0.1.2.2.0.0.1,0.1.2.2.0.0.1.0) -> name, (0.1.2.2.0.0,0
                                                  //| .1.2.2.0.0.0) -> ARG2, (0.1.2.2.1.0.1,0.1.2.2.1.0.1.0) -> opN, (0.0.0,0.0.0.
                                                  //| 0) -> ARG1, (0.1,0.1.0) -> time, (0.1,0.1.2) -> ARG1, (0.1.1.0.0,0.1.1.0.0.0
                                                  //| ) -> topic, (0.1.2.2,0.1.2.2.1) -> UNKNOWN, (0.1.0,0.1.0.0) -> day, (0.1.2.2
                                                  //| .1,0.1.2.2.1.0) -> UNKNOWN, (0,0.1) -> ARG1, (0.1.2.1,0.1.2.1.0) -
                                                  //| Output exceeds cutoff limit.

  val result = AMRGraph.importFile("C://AMR//daggerTest//Target3.txt")
                                                  //> result  : IndexedSeq[(String, String)] = Vector((Brazilian embassy officials
                                                  //|  stated that on 8 April 2002 Brazilian Defense Minister Geraldo da Cruz Quin
                                                  //| tao began a week-long series of meetings in Moscow with Russian officials du
                                                  //| ring which he is to meet Russian Defense Minister Sergei Ivanov.,(s / state-
                                                  //| 01 :ARG0 (p3 / person :ARG0-of (h / have-org-role-91 :ARG1 (e / embassy :pos
                                                  //| s (c / country :name (n / name :op1 "Brazil"))) :ARG2 (o / official))) :ARG1
                                                  //|  (b / begin-01 :ARG0 (p / person :name (n2 / name :op1 "Geraldo" :op2 "da" :
                                                  //| op3 "Cruz" :op4 "Quintao") :ARG0-of (h2 / have-org-role-91 :ARG1 c :ARG2 (m5
                                                  //|  / minister :topic (d2 / defense)))) :ARG1 (s2 / series :consist-of (m2 / me
                                                  //| et-03 :ARG0 p :ARG1 (p4 / person :ARG0-of (h3 / have-org-role-91 :ARG1 (c2 /
                                                  //|  country :name (n4 / name :op1 "Russia")) :ARG2 (o2 / official))) :location 
                                                  //| (c3 / city :name (n5 / name :op1 "Moscow"))) :duration (w / week-long) :time
                                                  //| -of (m3 / meet-03 :ARG0 p :ARG1 (p2 / person :name (n7 / name :op1 "Sergei" 
                                                  //| :op2 "Ivanov") :ARG0-of (h4 / have-org-role-91 :ARG1 c2 :ARG2 (m / minister 
                                                  //| :topic (d3 / defense)))))) :time (d / date-entity :year 2002 :month 4 :day 8
                                                  //| )))))
  val s1 = Sentence(result(0)._1, result(0)._2)   //> WARNING: Found duplicate match for concept official
                                                  //| WARNING: Found duplicate match for concept minister
                                                  //| WARNING: Found duplicate match for concept defense
                                                  //| s1  : amr.Sentence = Sentence(Brazilian embassy officials stated that on 8 A
                                                  //| pril 2002 Brazilian Defense Minister Geraldo da Cruz Quintao began a week-lo
                                                  //| ng series of meetings in Moscow with Russian officials during which he is to
                                                  //|  meet Russian Defense Minister Sergei Ivanov.,
                                                  //| NodeMap:	Map(1 -> Brazilian, 2 -> embassy, 3 -> officials, 4 -> stated, 5
                                                  //|  -> that, 6 -> on, 7 -> 8, 8 -> 4, 9 -> 2002, 10 -> Brazilian, 11 -> Defense
                                                  //| , 12 -> Minister, 13 -> Geraldo, 14 -> da, 15 -> Cruz, 16 -> Quintao, 17 -> 
                                                  //| began, 18 -> a, 19 -> week-long, 20 -> series, 21 -> of, 22 -> meetings, 23 
                                                  //| -> in, 24 -> Moscow, 25 -> with, 26 -> Russian, 27 -> officials, 28 -> durin
                                                  //| g, 29 -> which, 30 -> he, 31 -> is, 32 -> to, 33 -> meet, 34 -> Russian, 35 
                                                  //| -> Defense, 36 -> Minister, 37 -> Sergei, 38 -> Ivanov)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,12
                                                  //| ), 12 -> (12,13), 13 -> (13,14), 14 -> (14,15), 15 -> (15,16), 16 -> (16,17)
                                                  //| , 17 -> (17,18), 18 -> (18,19), 19 -> (19,20), 20 -> (20,21), 21 -> (21,22),
                                                  //|  22 -> (22,23), 23 -> (23,24), 24 -> (24,25), 25 -> (25,26), 26 -> (26,27), 
                                                  //| 27 -> (27,28), 28 -> (28,29), 29 -> (29,30), 30 -> (30,31), 31 -> (31,32), 3
                                                  //| 2 -> (32,33), 33 -> (33,34), 34 -> (34,35), 35 -> (35,36), 36 -> (36,37), 37
                                                  //|  -> (37,38), 38 -> (38,39))
                                                  //| Edges:	Map((3,1) -> amod, (3,2) -> nn, (4,3) -> nsubj, (4,5) -> dobj, (
                                                  //| 4,6) -> prep, (4,8) -> tmod, (4,17) -> dep, (6,7) -> pobj, (8,9) -> num, (16
                                                  //| ,10) -> amod, (16,11) -> nn, (16,12) -> nn, (16,13) -> nn, (16,14) -> nn, (1
                                                  //| 6,15) -> nn, (17,16) -> nsubj, (17,20) -> dobj, (17,25) -> prep, (20,18) -> 
                                                  //| det, (20,19) -> amod, (20,21) -> prep, (21,22) -> pobj, (22,23) -> prep, (23
                                                  //| ,24) -> pobj, (25,27) -> pobj, (27,26) -> amod, (27,31) -> rcmod, (28,29) ->
                                                  //|  pobj, (31,28) -> prep, (31,30) -> nsubj, (31,33) -> xcomp, (33,32) -> aux, 
                                                  //| (33,38) -> dobj, (38,34) -> amod, (38,35) -> nn, (38,36) -> nn, (38,37) -> n
                                                  //| n)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map(),Some(AMRGraph(Map(0.1.2.2 -> 8, 0.1 -> begin-01, 0
                                                  //| .1.1.0.0.0.0 -> country, 0.0.0 -> have-org-role-91, 0.1.0.1.0.0 -> defense, 
                                                  //| 0.1.0.0.3 -> "Qu
                                                  //| Output exceeds cutoff limit.
  val amr1 = s1.amr.get                           //> amr1  : amr.AMRGraph = AMRGraph(Map(0.1.2.2 -> 8, 0.1 -> begin-01, 0.1.1.0.0
                                                  //| .0.0 -> country, 0.0.0 -> have-org-role-91, 0.1.0.1.0.0 -> defense, 0.1.0.0.
                                                  //| 3 -> "Quintao", 0.1.1.2.0.0 -> name, 0.1.1.2 -> meet-03, 0.1.1.2.0.1.0.0 -> 
                                                  //| defense, 0.1.0 -> person, 0.1.1.0.1.0 -> name, 0.1.1.0.1 -> city, 0.1.0.0.0 
                                                  //| -> "Geraldo", 0.1.0.1 -> have-org-role-91, 0.0 -> person, 0.1.1.2.0.0.1 -> "
                                                  //| Ivanov", 0.1.2.1 -> 4, 0.0.0.0 -> embassy, 0.1.1.0.0 -> person, 0.1.1.0.0.0.
                                                  //| 0.0 -> name, 0.1.0.0.1 -> "da", 0.1.1.0.0.0.0.0.0 -> "Russia", 0.0.0.0.0.0.0
                                                  //|  -> "Brazil", 0.1.1.2.0.0.0 -> "Sergei", 0.1.0.0 -> name, 0.1.2.0 -> 2002, 0
                                                  //| .1.1.2.0 -> person, 0.1.0.0.2 -> "Cruz", 0.1.1.2.0.1 -> have-org-role-91, 0 
                                                  //| -> state-01, 0.1.2 -> date-entity, 0.1.1.0.0.0.1 -> official, 0.0.0.1 -> off
                                                  //| icial, 0.1.1.2.0.1.0 -> minister, 0.1.1.1 -> week-long, 0.0.0.0.0.0 -> name,
                                                  //|  0.0.0.0.0 -> country, 0.1.1.0.1.0.0 -> "Moscow", 0.1.1.0 -> meet-03, 0.1.0.
                                                  //| 1.0 -> minister, 0.1.1 -> series, 0.1.1.0.0.0 -> have-org-role-91),Map(0.1.2
                                                  //| .2 -> (7,10), 0.1 -> (17,18), 0.1.1.0.0.0.0 -> (26,27), 0.1.0.1.0.0 -> (11,1
                                                  //| 2), 0.1.0.0.3 -> (13,17), 0.1.1.2.0.0 -> (37,39), 0.1.1.2 -> (22,23), 0.1.1.
                                                  //| 2.0.1.0.0 -> (35,36), 0.1.0 -> (13,17), 0.1.1.0.1.0 -> (24,25), 0.1.1.0.1 ->
                                                  //|  (24,25), 0.1.0.0.0 -> (13,17), 0.0 -> (39,1), 0.1.1.2.0.0.1 -> (37,39), 0.1
                                                  //| .2.1 -> (7,10), 0.0.0.0 -> (2,3), 0.1.1.0.0 -> (39,1), 0.1.1.0.0.0.0.0 -> (2
                                                  //| 6,27), 0.1.0.0.1 -> (13,17), 0.1.1.0.0.0.0.0.0 -> (26,27), 0.0.0.0.0.0.0 -> 
                                                  //| (1,2), 0.1.1.2.0.0.0 -> (37,39), 0.1.0.0 -> (13,17), 0.1.2.0 -> (7,10), 0.1.
                                                  //| 1.2.0 -> (37,39), 0.1.0.0.2 -> (13,17), 0 -> (4,5), 0.1.2 -> (7,10), 0.1.1.0
                                                  //| .0.0.1 -> (27,28), 0.0.0.1 -> (3,4), 0.1.1.2.0.1.0 -> (36,37), 0.1.1.1 -> (1
                                                  //| 9,20), 0.0.0.0.0.0 -> (1,2), 0.0.0.0.0 -> (1,2), 0.1.1.0.1.0.0 -> (24,25), 0
                                                  //| .1.1.0 -> (33,34), 0.1.0.1.0 -> (12,13), 0.1.1 -> (20,21)),Map((0.0,0.0.0) -
                                                  //| > ARG0-of, (0.1.1.0,0.1.1.0.1) -> location, (0.1.1.2.0.1,0.1.1.2.0.1.0) -> A
                                                  //| RG2, (0.1.1.0,0.1.1.0.0) -> ARG1, (0.0.0.0,0.0.0.0.0) -> poss, (0.1.1.0.0.0,
                                                  //| 0.1.1.0.0.0.1) -> ARG2, (0.0.0,0.0.0.0) -> ARG1, (0.1.1.0.0.0,0.1.1.0.0.0.0)
                                                  //|  -> ARG1, (0.1,0.1.0) -> ARG0, (0.1,0.1.2) -> time, (0.1.1,0.1.1.2) -> time-
                                                  //| of, (0.1.1.2.0.1.0,0.1.1.2.0.1.0.0) -> topic, (0.1.1.0.0,0.1.1.0.0.0) -> ARG
                                                  //| 0-of, (0.1.0.0,0.1.0.0.2) -> opN, (0.1.0.0,0.1.0.0.3) -> opN, (0.1.0,0.1.0.0
                                                  //| ) -> name, (0.1.1.2.0.0,0.1.1.2.0.0.0) -> opN, (0.1.1.2.0.1,0.1.1.
                                                  //| Output exceeds cutoff limit.
  Smatch.reduceAMR(amr0)                          //> res0: amr.AMRGraph = AMRGraph(Map(0.1.2.2 -> meet-03, 0.1 -> begin-01, 0.0.0
                                                  //|  -> have-org-role-91, 0.1.2.2.0.0.1 -> country, 0.1.2.2.1.0.0.0.0 -> defense
                                                  //| , 0.1.0 -> date-entity, 0.1.2.1.0.0 -> name, 0.0 -> person, 0.1.2.2.0 -> per
                                                  //| son, 0.1.2.2.1.0.0 -> have-org-role-91, 0.1.2.1 -> meet-03, 0.0.0.0 -> embas
                                                  //| sy, 0.1.1.0.0 -> minister, 0.1.2.2.0.0.1.0 -> name, 0.1.2.2.1.0.0.0 -> minis
                                                  //| ter, 0.1.2.2.1.0 -> person, 0.1.2.2.0.0 -> have-org-role-91, 0.1.2.2.1 -> Iv
                                                  //| anov, 0.1.2.0 -> week-long, 0.1.2.2.1.0.1 -> name, 0 -> state-01, 0.1.2 -> s
                                                  //| eries, 0.0.0.1 -> official, 0.1.1.1 -> name, 0.0.0.0.0.0 -> name, 0.0.0.0.0 
                                                  //| -> country, 0.1.1.0 -> have-org-role-91, 0.1.2.2.0.0.0 -> official, 0.1.2.1.
                                                  //| 0 -> city, 0.1.1 -> person, 0.1.1.0.0.0 -> defense),Map(0.1.2.2 -> (22,23), 
                                                  //| 0.1 -> (17,18), 0.1.2.2.0.0.1 -> (26,27), 0.1.2.2.1.0.0.0.0 -> (35,36), 0.1.
                                                  //| 1.1.3 -> (13,17), 0.1.1.1.2 -> (13,17), 0.1.2.2.0.0.1.0.0 -> (26,27), 0.1.0 
                                                  //| -> (7,10), 0.1.2.1.0.0 -> (24,25), 0.1.0.1 -> (7,10), 0.0 -> (39,1), 0.1.2.2
                                                  //| .0 -> (39,1), 0.1.2.1 -> (33,34), 0.0.0.0 -> (2,3), 0.1.1.1.1 -> (13,17), 0.
                                                  //| 1.1.0.0 -> (12,13), 0.1.0.2 -> (7,10), 0.1.2.2.0.0.1.0 -> (26,27), 0.1.2.2.1
                                                  //| .0.0.0 -> (36,37), 0.1.2.2.1.0 -> (37,38), 0.0.0.0.0.0.0 -> (1,2), 0.1.0.0 -
                                                  //| > (7,10), 0.1.2.2.1 -> (38,39), 0.1.2.0 -> (19,20), 0.1.2.2.1.0.1 -> (37,38)
                                                  //| , 0.1.1.1.0 -> (13,17), 0 -> (4,5), 0.1.2 -> (20,21), 0.1.2.1.0.0.0 -> (24,2
                                                  //| 5), 0.0.0.1 -> (3,4), 0.1.2.2.1.0.1.0 -> (37,38), 0.1.1.1 -> (13,17), 0.0.0.
                                                  //| 0.0.0 -> (1,2), 0.0.0.0.0 -> (1,2), 0.1.2.2.0.0.0 -> (27,28), 0.1.2.1.0 -> (
                                                  //| 24,25), 0.1.1 -> (13,17), 0.1.1.0.0.0 -> (11,12)),Map((0.0,0.0.0) -> ARG0-of
                                                  //| , (0.1.1.0,0.1.1.0.0) -> ARG2, (0.1.2.2.0,0.1.2.2.0.0) -> ARG0-of, (0.0.0.0,
                                                  //| 0.0.0.0.0) -> poss, (0.1.2.2.0.0.1,0.1.2.2.0.0.1.0) -> name, (0.1.2.2.0.0,0.
                                                  //| 1.2.2.0.0.0) -> ARG2, (0.0.0,0.0.0.0) -> ARG1, (0.1,0.1.0) -> time, (0.1,0.1
                                                  //| .2) -> ARG1, (0.1.1.0.0,0.1.1.0.0.0) -> topic, (0.1.2.2,0.1.2.2.1) -> UNKNOW
                                                  //| N, (0.1.2.2.1,0.1.2.2.1.0) -> UNKNOWN, (0,0.1) -> ARG1, (0.1.2.1,0.1.2.1.0) 
                                                  //| -> UNKNOWN, (0.1.1,0.1.1.1) -> name, (0.1.2.2.1.0,0.1.2.2.1.0.1) -> name, (0
                                                  //| .0.0.0.0,0.0.0.0.0.0) -> name, (0.0.0,0.0.0.1) -> ARG2, (0.1.2.2.0.0,0.1.2.2
                                                  //| .0.0.1) -> ARG1, (0.1.2.2.1.0.0,0.1.2.2.1.0.0.0) -> ARG2, (0.1.2,0.1.2.2) ->
                                                  //|  consist-of, (0.1.1,0.1.1.0) -> ARG0-of, (0.1.2.2.1.0,0.1.2.2.1.0.0) -> ARG0
                                                  //| -of, (0,0.0) -> ARG0, (0.1.2.2.1.0.0.0,0.1.2.2.1.0.0.0.0) -> topic
                                                  //| Output exceeds cutoff limit.
  Smatch.reduceAMR(amr1)                          //> res1: amr.AMRGraph = AMRGraph(Map(0.1 -> begin-01, 0.1.1.0.0.0.0 -> country,
                                                  //|  0.0.0 -> have-org-role-91, 0.1.0.1.0.0 -> defense, 0.1.1.2.0.0 -> name, 0.1
                                                  //| .1.2 -> meet-03, 0.1.1.2.0.1.0.0 -> defense, 0.1.0 -> person, 0.1.1.0.1.0 ->
                                                  //|  name, 0.1.1.0.1 -> city, 0.1.0.1 -> have-org-role-91, 0.0 -> person, 0.0.0.
                                                  //| 0 -> embassy, 0.1.1.0.0 -> person, 0.1.1.0.0.0.0.0 -> name, 0.1.0.0 -> name,
                                                  //|  0.1.1.2.0 -> person, 0.1.1.2.0.1 -> have-org-role-91, 0 -> state-01, 0.1.2 
                                                  //| -> date-entity, 0.1.1.0.0.0.1 -> official, 0.0.0.1 -> official, 0.1.1.2.0.1.
                                                  //| 0 -> minister, 0.1.1.1 -> week-long, 0.0.0.0.0.0 -> name, 0.0.0.0.0 -> count
                                                  //| ry, 0.1.1.0 -> meet-03, 0.1.0.1.0 -> minister, 0.1.1 -> series, 0.1.1.0.0.0 
                                                  //| -> have-org-role-91),Map(0.1.2.2 -> (7,10), 0.1 -> (17,18), 0.1.1.0.0.0.0 ->
                                                  //|  (26,27), 0.1.0.1.0.0 -> (11,12), 0.1.0.0.3 -> (13,17), 0.1.1.2.0.0 -> (37,3
                                                  //| 9), 0.1.1.2 -> (22,23), 0.1.1.2.0.1.0.0 -> (35,36), 0.1.0 -> (13,17), 0.1.1.
                                                  //| 0.1.0 -> (24,25), 0.1.1.0.1 -> (24,25), 0.1.0.0.0 -> (13,17), 0.0 -> (39,1),
                                                  //|  0.1.1.2.0.0.1 -> (37,39), 0.1.2.1 -> (7,10), 0.0.0.0 -> (2,3), 0.1.1.0.0 ->
                                                  //|  (39,1), 0.1.1.0.0.0.0.0 -> (26,27), 0.1.0.0.1 -> (13,17), 0.1.1.0.0.0.0.0.0
                                                  //|  -> (26,27), 0.0.0.0.0.0.0 -> (1,2), 0.1.1.2.0.0.0 -> (37,39), 0.1.0.0 -> (1
                                                  //| 3,17), 0.1.2.0 -> (7,10), 0.1.1.2.0 -> (37,39), 0.1.0.0.2 -> (13,17), 0 -> (
                                                  //| 4,5), 0.1.2 -> (7,10), 0.1.1.0.0.0.1 -> (27,28), 0.0.0.1 -> (3,4), 0.1.1.2.0
                                                  //| .1.0 -> (36,37), 0.1.1.1 -> (19,20), 0.0.0.0.0.0 -> (1,2), 0.0.0.0.0 -> (1,2
                                                  //| ), 0.1.1.0.1.0.0 -> (24,25), 0.1.1.0 -> (33,34), 0.1.0.1.0 -> (12,13), 0.1.1
                                                  //|  -> (20,21)),Map((0.0,0.0.0) -> ARG0-of, (0.1.1.0,0.1.1.0.1) -> location, (0
                                                  //| .1.1.2.0.1,0.1.1.2.0.1.0) -> ARG2, (0.1.1.0,0.1.1.0.0) -> ARG1, (0.0.0.0,0.0
                                                  //| .0.0.0) -> poss, (0.1.1.0.0.0,0.1.1.0.0.0.1) -> ARG2, (0.0.0,0.0.0.0) -> ARG
                                                  //| 1, (0.1.1.0.0.0,0.1.1.0.0.0.0) -> ARG1, (0.1,0.1.0) -> ARG0, (0.1,0.1.2) -> 
                                                  //| time, (0.1.1,0.1.1.2) -> time-of, (0.1.1.2.0.1.0,0.1.1.2.0.1.0.0) -> topic, 
                                                  //| (0.1.1.0.0,0.1.1.0.0.0) -> ARG0-of, (0.1.0,0.1.0.0) -> name, (0.1.1.2.0.1,0.
                                                  //| 1.1.0.0.0.0) -> ARG1, (0,0.1) -> ARG1, (0.1.0.1.0,0.1.0.1.0.0) -> topic, (0.
                                                  //| 1.1.0,0.1.0) -> ARG0, (0.1.1,0.1.1.1) -> duration, (0.1.1.0.0.0.0,0.1.1.0.0.
                                                  //| 0.0.0) -> name, (0.1.1.0.1,0.1.1.0.1.0) -> name, (0.0.0.0.0,0.0.0.0.0.0) -> 
                                                  //| name, (0.1.0,0.1.0.1) -> ARG0-of, (0.0.0,0.0.0.1) -> ARG2, (0.1.1.2.0,0.1.1.
                                                  //| 2.0.1) -> ARG0-of, (0.1.1.2,0.1.0) -> ARG0, (0.1.1,0.1.1.0) -> con
                                                  //| Output exceeds cutoff limit.
                                         
  Smatch.initialMap(amr0, amr1)                   //> res2: Map[String,String] = Map(0.1.2.2 -> 0.1.0.0, 0.1 -> 0.1, 0.1.1.0.0.0.0
                                                  //|  -> 0.1.2.2.0.0.1, 0.0.0 -> 0.1.2.2.1.0.0, 0.1.0.1.0.0 -> 0.1.1.0.0.0, 0.1.0
                                                  //| .0.3 -> 0.1.1.1.3, 0.1.1.2.0.0 -> 0.1.1.1, 0.1.1.2 -> 0.1.2.2, 0.1.1.2.0.1.0
                                                  //| .0 -> 0.1.2.2.1.0.0.0.0, 0.1.0 -> 0.1.2.2.1.0, 0.1.1.0.1.0 -> 0.1.2.2.0.0.1.
                                                  //| 0, 0.1.1.0.1 -> 0.1.2.1.0, 0.1.0.0.0 -> 0.1.1.1.0, 0.1.0.1 -> 0.1.1.0, 0.0 -
                                                  //| > 0.0, 0.1.1.2.0.0.1 -> 0.1.2.2.1, 0.1.2.1 -> 0.1.0.1, 0.0.0.0 -> 0.0.0.0, 0
                                                  //| .1.1.0.0 -> 0.1.2.2.0, 0.1.1.0.0.0.0.0 -> 0.1.2.1.0.0, 0.1.0.0.1 -> 0.1.1.1.
                                                  //| 1, 0.1.1.0.0.0.0.0.0 -> 0.1.2.2.0.0.1.0.0, 0.0.0.0.0.0.0 -> 0.0.0.0.0.0.0, 0
                                                  //| .1.1.2.0.0.0 -> 0.1.2.2.1.0.1.0, 0.1.0.0 -> 0.1.2.2.1.0.1, 0.1.2.0 -> 0.1.0.
                                                  //| 2, 0.1.1.2.0 -> 0.1.1, 0.1.0.0.2 -> 0.1.1.1.2, 0.1.1.2.0.1 -> 0.1.2.2.0.0, 0
                                                  //|  -> 0, 0.1.2 -> 0.1.0, 0.1.1.0.0.0.1 -> 0.0.0.1, 0.0.0.1 -> 0.1.2.2.0.0.0, 0
                                                  //| .1.1.2.0.1.0 -> 0.1.2.2.1.0.0.0, 0.1.1.1 -> 0.1.2.0, 0.0.0.0.0.0 -> 0.0.0.0.
                                                  //| 0.0, 0.0.0.0.0 -> 0.0.0.0.0, 0.1.1.0.1.0.0 -> 0.1.2.1.0.0.0, 0.1.1.0 -> 0.1.
                                                  //| 2.1, 0.1.0.1.0 -> 0.1.1.0.0, 0.1.1 -> 0.1.2, 0.1.1.0.0.0 -> 0.0.0)
  val fScore = Smatch.fScore(amr0, amr1, 4, 10000)//> fScore  : (Double, Double, Double, Double, Int, Int, Int) = (0.9261744966442
                                                  //| 953,0.9452054794520548,0.9078947368421053,11.0,69,76,73)
                                                 
  
  
  fScore                                          //> res3: (Double, Double, Double, Double, Int, Int, Int) = (0.9261744966442953,
                                                  //| 0.9452054794520548,0.9078947368421053,11.0,69,76,73)

}