package amr

object ReduceAMR {
val testData = AMRGraph.importFile("C:\\AMR\\initialTrainingSetAmended.txt")
                                                  //> testData  : IndexedSeq[(String, String)] = Vector((2008-05-14,"(d / date-ent
                                                  //| ity :month 5 :day 14 :year 2008) "), (Estonia (EE); Latvia (LV); Lithuania (
                                                  //| LT); United States (US),"(a / and :op1 (c / country :name (n / name :op1 "Es
                                                  //| tonia")) :op2 (c2 / country :name (n3 / name :op1 "Latvia")) :op3 (c3 / coun
                                                  //| try :name (n5 / name :op1 "Lithuania")) :op4 (c8 / country :name (n14 / name
                                                  //|  :op1 "United" :op2 "States"))) "), (International; Government; Telecom; tec
                                                  //| hnology; science,"(a / and :op1 (i / international) :op2 (g / government-org
                                                  //| anization :ARG0-of (g2 / govern-01)) :op3 (t / telecommunication) :op4 (t2 /
                                                  //|  technology) :op5 (s / science)) "), (NATO CONSIDERS cyber attacks a threat 
                                                  //| to military and civilian computer networks after the Estonian Government was
                                                  //|  struck by cyber attacks in 2007.,"(c / consider-02 :ARG0 (m2 / military :na
                                                  //| me (n / name :op1 "NATO")) :ARG1 (a / attack-01 :mod (c2 / cyber)) :ARG2 (t 
                                                  //| / thing :ARG1-of (t2 / threaten-01 :ARG2 (a5 / and :op1 (n2 / network :mod (
                                                  //| m / military)) :op2 (n4 / network :mod (c3 / civilian)) :mod (c4 / computer)
                                                  //| ))) :time (a3 / after :op1 (s / strike-01 :ARG0 (a4 / attack :mod (c6 / cybe
                                                  //| r)) :ARG1 (g / government-organization :ARG0-of (g2 / govern-01 :ARG1 (c5 / 
                                                  //| country :name (n3 / name :op1 "Estonia")))) :time (d / date-entity :year 200
                                                  //| 7)))) "), (NATO allies have signed an agreement to fund a research center to
                                                  //|  bolster defenses against cyber attacks.,"(s / sign-01 :ARG0 (p / person :AR
                                                  //| G0-of (a / ally-01) :mod (m / military :name (n / name :op1 "NATO"))) :ARG1 
                                                  //| (a2 / agree-01 :ARG0 p :ARG1 (f / fund-01 :ARG0 p :ARG1 (c / center :mod (r 
                                                  //| / research-01)) :purpose (b / bolster-01 :ARG0 p :ARG1 (d / defend-01 :prep-
                                                  //| against (a3 / attack :mod (c2 / cyber))))))) "), (On 14 May 2008 7 NATO alli
                                                  //| es signed an agreement to fund a research center.,"(s / sign-01 :ARG0 (p / p
                                                  //| erson :quant 7 :ARG0-of (a / ally-01) :mod (m / military :name (n / name :op
                                                  //| 1 "NATO"))) :ARG1 (a2 / agree-01 :ARG1 (f / fund-01 :ARG0 p :ARG1 (c / cente
                                                  //| r :mod (r / research-01)))) :time (d / date-entity :month 5 :day 14 :year 20
                                                  //| 08)) "), (The center will bolster NATO's defenses against cyber attacks.,"(b
                                                  //|  / bolster-01 :ARG0 (c / center) :ARG1 (d / defend-01 :ARG0 (m / military :n
                                                  //| ame (n / name :op1 "NATO")) :ARG1 m :prep-against (a / attack-01 :mod (c2 / 
                                                  //| cyber)))) "), (The center will be operational in August 2008.,"(o 
                                                  //| Output exceeds cutoff limit.

 val s = testData map {d => Sentence(d._1, d._2)} //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.8 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [5.8 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [4.5 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [7.0 sec].
                                                  //| WARNING: Found duplicate match for concept and
                                                  //| WARNING: Found duplicate match for concept and
                                                  //| WARNING: Found duplicate match for concept and
                                                  //| WARNING: Found duplicate match for concept and
                                                  //| WARNING: Found duplicate match for concept and
                                                  //| WARNING: Found duplicate match for concept attack-01
                                                  //| WARNING: Found duplicate match for concept cyber
                                                  //| s  : IndexedSeq[amr.Sentence] = Vector(Sentence(2008-05-14,
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> 2008, 2 -> 5, 3 -> 14)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4))
                                                  //| Edges:	Map((0,1) -> root, (1,3) -> dep, (3,2) -> num)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map(),Some(AMRGraph(Map(0.1 -> 14, 0.0 -> 5, 0 -> date-e
                                                  //| ntity, 0.2 -> 2008, ROOT -> ROOT),Map(0 -> (1,4), 0.0 -> (1,4), 0.1 -> (1,4)
                                                  //| , 0.2 -> (1,4)),Map((0,0.0) -> month, (0,0.1) -> day, (0,0.2) -> year, (ROOT
                                                  //| ,0) -> ROOT))),Map(2 -> 0.0, 1 -> 0.2, 3 -> 0.1, 0 -> ROOT)), Sentence(Eston
                                                  //| ia (EE); Latvia (LV); Lithuania (LT); United States (US),
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> Estonia, 2 -> (, 3 -> EE, 4 -> ), 5 -> ;, 6 
                                                  //| -> Latvia, 7 -> (, 8 -> LV, 9 -> ), 10 -> ;, 11 -> Lithuania, 12 -> (, 13 ->
                                                  //|  LT, 14 -> ), 15 -> ;, 16 -> United, 17 -> States, 18 -> (, 19 -> US, 20 -> 
                                                  //| ))
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,12
                                                  //| ), 12 -> (12,13), 13 -> (13,14), 14 -> (14,15), 15 -> (15,16), 16 -> (16,17)
                                                  //| , 17 -> (17,18), 18 -> (18,19), 19 -> (19,20), 20 -> (20,21))
                                                  //| Edges:	Map((0,1) -> root, (1,3) -> appos, (1,5) -> punct, (1,6) -> dep,
                                                  //|  (1,10) -> punct, (1,11) -> dep, (3,2) -> punct, (3,4) -> punct, (6,8) -> ap
                                                  //| pos, (8,7) -> punct, (8,9) -> punct, (11,13) -> appos, (11,15) -> punct, (11
                                                  //| ,17) -> dep, (13,12) -> punc
                                                  //| Output exceeds cutoff limit.
 
 s(0).amr.get                                     //> res0: amr.AMRGraph = AMRGraph(Map(0.1 -> 14, 0.0 -> 5, 0 -> date-entity, 0.2
                                                  //|  -> 2008, ROOT -> ROOT),Map(0 -> (1,4), 0.0 -> (1,4), 0.1 -> (1,4), 0.2 -> (
                                                  //| 1,4)),Map((0,0.0) -> month, (0,0.1) -> day, (0,0.2) -> year, (ROOT,0) -> ROO
                                                  //| T))
 Smatch.reduceAMR(s(0).amr.get)                   //> res1: (amr.AMRGraph, List[(String, String, String)]) = (AMRGraph(Map(0 -> da
                                                  //| te-entity),Map(0 -> (1,4), 0.0 -> (1,4), 0.1 -> (1,4), 0.2 -> (1,4)),Map()),
                                                  //| List((0,day,14), (0,month,5), (0,year,2008), (0,ROOT,ROOT)))
  s(1).amr.get                                    //> res2: amr.AMRGraph = AMRGraph(Map(0.1 -> country, 0.0.0 -> name, 0.1.0 -> na
                                                  //| me, 0.0 -> country, 0.0.0.0 -> "Estonia", 0.2.0 -> name, 0.3.0.0 -> "United"
                                                  //| , 0.3.0 -> name, 0.1.0.0 -> "Latvia", 0.3 -> country, 0 -> and, 0.3.0.1 -> "
                                                  //| States", 0.2 -> country, ROOT -> ROOT, 0.2.0.0 -> "Lithuania"),Map(0.1 -> (6
                                                  //| ,7), 0.0.0 -> (1,2), 0.1.0 -> (6,7), 0.0 -> (1,2), 0.0.0.0 -> (1,2), 0.2.0 -
                                                  //| > (11,12), 0.3.0.0 -> (16,18), 0.3.0 -> (16,18), 0.1.0.0 -> (6,7), 0.3 -> (1
                                                  //| 6,18), 0 -> (5,6), 0.3.0.1 -> (16,18), 0.2 -> (11,12), 0.2.0.0 -> (11,12)),M
                                                  //| ap((0.0,0.0.0) -> name, (0.3.0,0.3.0.0) -> opN, (ROOT,0) -> ROOT, (0,0.3) ->
                                                  //|  opN, (0.0.0,0.0.0.0) -> opN, (0.1,0.1.0) -> name, (0.3.0,0.3.0.1) -> opN, (
                                                  //| 0.1.0,0.1.0.0) -> opN, (0,0.2) -> opN, (0,0.1) -> opN, (0.2,0.2.0) -> name, 
                                                  //| (0.3,0.3.0) -> name, (0,0.0) -> opN, (0.2.0,0.2.0.0) -> opN))
 Smatch.reduceAMR(s(1).amr.get)                   //> res3: (amr.AMRGraph, List[(String, String, String)]) = (AMRGraph(Map(0.1 -> 
                                                  //| country, 0.0.0 -> name, 0.1.0 -> name, 0.0 -> country, 0.2.0 -> name, 0.3.0 
                                                  //| -> name, 0.3 -> country, 0 -> and, 0.2 -> country),Map(0.1 -> (6,7), 0.0.0 -
                                                  //| > (1,2), 0.1.0 -> (6,7), 0.0 -> (1,2), 0.0.0.0 -> (1,2), 0.2.0 -> (11,12), 0
                                                  //| .3.0.0 -> (16,18), 0.3.0 -> (16,18), 0.1.0.0 -> (6,7), 0.3 -> (16,18), 0 -> 
                                                  //| (5,6), 0.3.0.1 -> (16,18), 0.2 -> (11,12), 0.2.0.0 -> (11,12)),Map((0.0,0.0.
                                                  //| 0) -> name, (0,0.3) -> op4, (0.1,0.1.0) -> name, (0,0.2) -> op3, (0,0.1) -> 
                                                  //| op2, (0.2,0.2.0) -> name, (0.3,0.3.0) -> name, (0,0.0) -> op1)),List((0.0.0,
                                                  //| op1,"Estonia"), (0.3.0,op1,"United"), (0.1.0,op1,"Latvia"), (0.3.0,op2,"Stat
                                                  //| es"), (0,ROOT,ROOT), (0.2.0,op1,"Lithuania")))
  s(2).amr.get                                    //> res4: amr.AMRGraph = AMRGraph(Map(0.1 -> government-organization, 0.1.0 -> g
                                                  //| overn-01, 0.0 -> international, 0.4 -> science, 0.3 -> technology, 0 -> and,
                                                  //|  0.2 -> telecommunication, ROOT -> ROOT),Map(0.1 -> (3,4), 0.0 -> (1,2), 0.4
                                                  //|  -> (9,10), 0.3 -> (7,8), 0 -> (2,3), 0.2 -> (5,6)),Map((ROOT,0) -> ROOT, (0
                                                  //| ,0.3) -> opN, (0.1,0.1.0) -> ARG0-of, (0,0.2) -> opN, (0,0.1) -> opN, (0,0.4
                                                  //| ) -> opN, (0,0.0) -> opN))
 Smatch.reduceAMR(s(2).amr.get)                   //> res5: (amr.AMRGraph, List[(String, String, String)]) = (AMRGraph(Map(0.1 -> 
                                                  //| government-organization, 0.1.0 -> govern-01, 0.0 -> international, 0.4 -> sc
                                                  //| ience, 0.3 -> technology, 0 -> and, 0.2 -> telecommunication),Map(0.1 -> (3,
                                                  //| 4), 0.0 -> (1,2), 0.4 -> (9,10), 0.3 -> (7,8), 0 -> (2,3), 0.2 -> (5,6)),Map
                                                  //| ((0,0.3) -> op4, (0.1,0.1.0) -> ARG0-of, (0,0.2) -> op3, (0,0.1) -> op2, (0,
                                                  //| 0.4) -> op5, (0,0.0) -> op1)),List((0,ROOT,ROOT)))
  s(3).amr.get                                    //> res6: amr.AMRGraph = AMRGraph(Map(0.1 -> attack-01, 0.0.0 -> name, 0.3.0.1.0
                                                  //| .0 -> country, 0.2.0.0.0 -> network, 0.1.0 -> cyber, 0.2.0.0.1.0 -> civilian
                                                  //| , 0.3.0.2.0 -> 2007, 0.0 -> military, 0.2.0.0.1 -> network, 0.3.0.1.0 -> gov
                                                  //| ern-01, 0.0.0.0 -> "NATO", 0.2.0 -> threaten-01, 0.3.0.0 -> attack, 0.3.0 ->
                                                  //|  strike-01, 0.3 -> after, 0.2.0.0.0.0 -> military, 0.2.0.0.2 -> computer, 0 
                                                  //| -> consider-02, 0.3.0.1 -> government-organization, 0.2 -> thing, 0.3.0.2 ->
                                                  //|  date-entity, 0.3.0.1.0.0.0.0 -> "Estonia", ROOT -> ROOT, 0.2.0.0 -> and, 0.
                                                  //| 3.0.1.0.0.0 -> name, 0.3.0.0.0 -> cyber),Map(0.1 -> (4,5), 0.0.0 -> (1,2), 0
                                                  //| .3.0.1.0.0 -> (15,16), 0.2.0.0.0 -> (12,13), 0.1.0 -> (3,4), 0.2.0.0.1.0 -> 
                                                  //| (10,11), 0.3.0.2.0 -> (23,24), 0.0 -> (8,9), 0.0.0.0 -> (1,2), 0.2.0 -> (6,7
                                                  //| ), 0.3.0.0 -> (21,22), 0.3.0 -> (18,19), 0.3 -> (13,14), 0.2.0.0.2 -> (11,12
                                                  //| ), 0 -> (2,3), 0.3.0.1 -> (16,17), 0.2 -> (6,7), 0.3.0.2 -> (23,24), 0.3.0.1
                                                  //| .0.0.0.0 -> (15,16), 0.2.0.0 -> (9,10), 0.3.0.1.0.0.0 -> (15,16), 0.3.0.0.0 
                                                  //| -> (20,21)),Map((0.0,0.0.0) -> name, (0.3.0,0.3.0.0) -> ARG0, (ROOT,0) -> RO
                                                  //| OT, (0,0.3) -> time, (0.0.0,0.0.0.0) -> opN, (0.2.0.0.1,0.2.0.0.1.0) -> mod,
                                                  //|  (0.1,0.1.0) -> mod, (0.3.0,0.3.0.1) -> ARG1, (0.3.0,0.3.0.2) -> time, (0.3.
                                                  //| 0.0,0.3.0.0.0) -> mod, (0,0.2) -> ARG2, (0,0.1) -> ARG1, (0.2.0.0.0,0.2.0.0.
                                                  //| 0.0) -> mod, (0.2,0.2.0) -> ARG1-of, (0.3,0.3.0) -> opN, (0.3.0.1.0,0.3.0.1.
                                                  //| 0.0) -> ARG1, (0.2.0.0,0.2.0.0.1) -> opN, (0.2.0.0,0.2.0.0.0) -> opN, (0.3.0
                                                  //| .1.0.0.0,0.3.0.1.0.0.0.0) -> opN, (0.3.0.1,0.3.0.1.0) -> ARG0-of, (0,0.0) ->
                                                  //|  ARG0, (0.3.0.1.0.0,0.3.0.1.0.0.0) -> name, (0.2.0,0.2.0.0) -> ARG2, (0.3.0.
                                                  //| 2,0.3.0.2.0) -> year, (0.2.0.0,0.2.0.0.2) -> mod))
 Smatch.reduceAMR(s(3).amr.get)                   //> res7: (amr.AMRGraph, List[(String, String, String)]) = (AMRGraph(Map(0.1 -> 
                                                  //| attack-01, 0.0.0 -> name, 0.3.0.1.0.0 -> country, 0.2.0.0.0 -> network, 0.1.
                                                  //| 0 -> cyber, 0.2.0.0.1.0 -> civilian, 0.0 -> military, 0.2.0.0.1 -> network, 
                                                  //| 0.3.0.1.0 -> govern-01, 0.2.0 -> threaten-01, 0.3.0.0 -> attack, 0.3.0 -> st
                                                  //| rike-01, 0.3 -> after, 0.2.0.0.0.0 -> military, 0.2.0.0.2 -> computer, 0 -> 
                                                  //| consider-02, 0.3.0.1 -> government-organization, 0.2 -> thing, 0.3.0.2 -> da
                                                  //| te-entity, 0.2.0.0 -> and, 0.3.0.1.0.0.0 -> name, 0.3.0.0.0 -> cyber),Map(0.
                                                  //| 1 -> (4,5), 0.0.0 -> (1,2), 0.3.0.1.0.0 -> (15,16), 0.2.0.0.0 -> (12,13), 0.
                                                  //| 1.0 -> (3,4), 0.2.0.0.1.0 -> (10,11), 0.3.0.2.0 -> (23,24), 0.0 -> (8,9), 0.
                                                  //| 0.0.0 -> (1,2), 0.2.0 -> (6,7), 0.3.0.0 -> (21,22), 0.3.0 -> (18,19), 0.3 ->
                                                  //|  (13,14), 0.2.0.0.2 -> (11,12), 0 -> (2,3), 0.3.0.1 -> (16,17), 0.2 -> (6,7)
                                                  //| , 0.3.0.2 -> (23,24), 0.3.0.1.0.0.0.0 -> (15,16), 0.2.0.0 -> (9,10), 0.3.0.1
                                                  //| .0.0.0 -> (15,16), 0.3.0.0.0 -> (20,21)),Map((0.0,0.0.0) -> name, (0.3.0,0.3
                                                  //| .0.0) -> ARG0, (0,0.3) -> time, (0.2.0.0.1,0.2.0.0.1.0) -> mod, (0.1,0.1.0) 
                                                  //| -> mod, (0.3.0,0.3.0.1) -> ARG1, (0.3.0,0.3.0.2) -> time, (0.3.0.0,0.3.0.0.0
                                                  //| ) -> mod, (0,0.2) -> ARG2, (0,0.1) -> ARG1, (0.2.0.0.0,0.2.0.0.0.0) -> mod, 
                                                  //| (0.2,0.2.0) -> ARG1-of, (0.3,0.3.0) -> op1, (0.3.0.1.0,0.3.0.1.0.0) -> ARG1,
                                                  //|  (0.2.0.0,0.2.0.0.1) -> op1, (0.2.0.0,0.2.0.0.0) -> op2, (0.3.0.1,0.3.0.1.0)
                                                  //|  -> ARG0-of, (0,0.0) -> ARG0, (0.3.0.1.0.0,0.3.0.1.0.0.0) -> name, (0.2.0,0.
                                                  //| 2.0.0) -> ARG2, (0.2.0.0,0.2.0.0.2) -> mod)),List((0.3.0.2,year,2007), (0.0.
                                                  //| 0,op1,"NATO"), (0.3.0.1.0.0.0,op1,"Estonia"), (0,ROOT,ROOT)))
  s(4).amr.get                                    //> res8: amr.AMRGraph = AMRGraph(Map(0.1 -> agree-01, 0.0.0 -> ally-01, 0.1.0.1
                                                  //| .0.0 -> attack, 0.1.0 -> fund-01, 0.0.1.0 -> name, 0.1.0.0.0 -> research-01,
                                                  //|  0.1.0.1 -> bolster-01, 0.0 -> person, 0.0.1 -> military, 0.1.0.0 -> center,
                                                  //|  0.0.1.0.0 -> "NATO", 0.1.0.1.0.0.0 -> cyber, 0 -> sign-01, 0.1.0.1.0 -> def
                                                  //| end-01, ROOT -> ROOT),Map(0.1 -> (6,7), 0.0.0 -> (2,3), 0.1.0.1.0.0 -> (17,1
                                                  //| 8), 0.1.0 -> (8,9), 0.0.1.0 -> (1,2), 0.1.0.0.0 -> (10,11), 0.1.0.1 -> (13,1
                                                  //| 4), 0.0 -> (2,3), 0.1.0.0 -> (11,12), 0.0.1.0.0 -> (1,2), 0.1.0.1.0.0.0 -> (
                                                  //| 16,17), 0 -> (4,5), 0.1.0.1.0 -> (14,15)),Map((0.0,0.0.0) -> ARG0-of, (ROOT,
                                                  //| 0) -> ROOT, (0.1.0.1.0.0,0.1.0.1.0.0.0) -> mod, (0.1,0.1.0) -> ARG1, (0.1.0,
                                                  //| 0.1.0.0) -> ARG1, (0,0.1) -> ARG1, (0.1.0.1.0,0.1.0.1.0.0) -> prep-against, 
                                                  //| (0.1.0.1,0.0) -> ARG0, (0.1.0,0.1.0.1) -> purpose, (0.1.0.0,0.1.0.0.0) -> mo
                                                  //| d, (0.1,0.0) -> ARG0, (0,0.0) -> ARG0, (0.1.0.1,0.1.0.1.0) -> ARG1, (0.0.1,0
                                                  //| .0.1.0) -> name, (0.1.0,0.0) -> ARG0, (0.0.1.0,0.0.1.0.0) -> opN, (0.0,0.0.1
                                                  //| ) -> mod))
 Smatch.reduceAMR(s(4).amr.get)                   //> res9: (amr.AMRGraph, List[(String, String, String)]) = (AMRGraph(Map(0.1 -> 
                                                  //| agree-01, 0.0.0 -> ally-01, 0.1.0.1.0.0 -> attack, 0.1.0 -> fund-01, 0.0.1.0
                                                  //|  -> name, 0.1.0.0.0 -> research-01, 0.1.0.1 -> bolster-01, 0.0 -> person, 0.
                                                  //| 0.1 -> military, 0.1.0.0 -> center, 0.1.0.1.0.0.0 -> cyber, 0 -> sign-01, 0.
                                                  //| 1.0.1.0 -> defend-01),Map(0.1 -> (6,7), 0.0.0 -> (2,3), 0.1.0.1.0.0 -> (17,1
                                                  //| 8), 0.1.0 -> (8,9), 0.0.1.0 -> (1,2), 0.1.0.0.0 -> (10,11), 0.1.0.1 -> (13,1
                                                  //| 4), 0.0 -> (2,3), 0.1.0.0 -> (11,12), 0.0.1.0.0 -> (1,2), 0.1.0.1.0.0.0 -> (
                                                  //| 16,17), 0 -> (4,5), 0.1.0.1.0 -> (14,15)),Map((0.0,0.0.0) -> ARG0-of, (0.1.0
                                                  //| .1.0.0,0.1.0.1.0.0.0) -> mod, (0.1,0.1.0) -> ARG1, (0.1.0,0.1.0.0) -> ARG1, 
                                                  //| (0,0.1) -> ARG1, (0.1.0.1.0,0.1.0.1.0.0) -> prep-against, (0.1.0.1,0.0) -> A
                                                  //| RG0, (0.1.0,0.1.0.1) -> purpose, (0.1.0.0,0.1.0.0.0) -> mod, (0.1,0.0) -> AR
                                                  //| G0, (0,0.0) -> ARG0, (0.1.0.1,0.1.0.1.0) -> ARG1, (0.0.1,0.0.1.0) -> name, (
                                                  //| 0.1.0,0.0) -> ARG0, (0.0,0.0.1) -> mod)),List((0.0.1.0,op1,"NATO"), (0,ROOT,
                                                  //| ROOT)))
  s(5).amr.get                                    //> res10: amr.AMRGraph = AMRGraph(Map(0.1 -> agree-01, 0.0.0 -> 7, 0.0.2.0.0 ->
                                                  //|  "NATO", 0.1.0 -> fund-01, 0.2.1 -> 14, 0.1.0.0.0 -> research-01, 0.0 -> per
                                                  //| son, 0.0.2 -> military, 0.2.0 -> 5, 0.0.1 -> ally-01, 0.1.0.0 -> center, 0 -
                                                  //| > sign-01, 0.2 -> date-entity, 0.2.2 -> 2008, ROOT -> ROOT, 0.0.2.0 -> name)
                                                  //| ,Map(0.1 -> (10,11), 0.0.0 -> (5,8), 0.0.2.0.0 -> (6,7), 0.1.0 -> (12,13), 0
                                                  //| .2.1 -> (2,5), 0.1.0.0.0 -> (14,15), 0.0 -> (5,8), 0.2.0 -> (2,5), 0.0.1 -> 
                                                  //| (5,8), 0.1.0.0 -> (15,16), 0 -> (8,9), 0.2 -> (2,5), 0.2.2 -> (2,5), 0.0.2.0
                                                  //|  -> (6,7)),Map((0.0,0.0.0) -> quant, (ROOT,0) -> ROOT, (0.1,0.1.0) -> ARG1, 
                                                  //| (0.1.0,0.1.0.0) -> ARG1, (0.0.2,0.0.2.0) -> name, (0,0.2) -> time, (0,0.1) -
                                                  //| > ARG1, (0.0.2.0,0.0.2.0.0) -> opN, (0.2,0.2.0) -> month, (0.0,0.0.2) -> mod
                                                  //| , (0.1.0.0,0.1.0.0.0) -> mod, (0.2,0.2.1) -> day, (0,0.0) -> ARG0, (0.1.0,0.
                                                  //| 0) -> ARG0, (0.0,0.0.1) -> ARG0-of, (0.2,0.2.2) -> year))
 Smatch.reduceAMR(s(5).amr.get)                   //> res11: (amr.AMRGraph, List[(String, String, String)]) = (AMRGraph(Map(0.1 ->
                                                  //|  agree-01, 0.1.0 -> fund-01, 0.1.0.0.0 -> research-01, 0.0 -> person, 0.0.2 
                                                  //| -> military, 0.0.1 -> ally-01, 0.1.0.0 -> center, 0 -> sign-01, 0.2 -> date-
                                                  //| entity, 0.0.2.0 -> name),Map(0.1 -> (10,11), 0.0.0 -> (5,8), 0.0.2.0.0 -> (6
                                                  //| ,7), 0.1.0 -> (12,13), 0.2.1 -> (2,5), 0.1.0.0.0 -> (14,15), 0.0 -> (5,8), 0
                                                  //| .2.0 -> (2,5), 0.0.1 -> (5,8), 0.1.0.0 -> (15,16), 0 -> (8,9), 0.2 -> (2,5),
                                                  //|  0.2.2 -> (2,5), 0.0.2.0 -> (6,7)),Map((0.1,0.1.0) -> ARG1, (0.1.0,0.1.0.0) 
                                                  //| -> ARG1, (0.0.2,0.0.2.0) -> name, (0,0.2) -> time, (0,0.1) -> ARG1, (0.0,0.0
                                                  //| .2) -> mod, (0.1.0.0,0.1.0.0.0) -> mod, (0,0.0) -> ARG0, (0.1.0,0.0) -> ARG0
                                                  //| , (0.0,0.0.1) -> ARG0-of)),List((0.0,quant,7), (0.0.2.0,op1,"NATO"), (0.2,da
                                                  //| y,14), (0.2,month,5), (0.2,year,2008), (0,ROOT,ROOT)))
}