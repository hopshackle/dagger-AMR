package amr

object testDT {

  val dt = DependencyTree("The police want to arrest Michael Karras in Singapore.")
                                                  //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.2 sec].
                                                  //| dt  : amr.DependencyTree = 
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> The, 2 -> police, 3 -> want, 4 -> to, 5 -> a
                                                  //| rrest, 6 -> Michael, 7 -> Karras, 8 -> in, 9 -> Singapore)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10))
                                                  //| Edges:	Map((0,3) -> root, (2,1) -> det, (3,2) -> nsubj, (3,5) -> xcomp,
                                                  //|  (5,4) -> aux, (5,7) -> dobj, (7,6) -> nn, (7,8) -> prep, (8,9) -> pobj)
  val testData = AMRGraph.importFile("C:\\AMR\\AMR2.txt")
                                                  //> testData  : IndexedSeq[(String, String)] = Vector((Estonia (EE); Latvia (LV)
                                                  //| ; Lithuania (LT); Germany (DE): Italy (IT); Spain (ES); Slovakia (SK); Unite
                                                  //| d States (US),"(a / and :op1 (c / country :name (n / name :op1 "Estonia")) :
                                                  //| op2 (c2 / country :name (n3 / name :op1 "Latvia")) :op3 (c3 / country :name 
                                                  //| (n5 / name :op1 "Lithuania")) :op4 (c4 / country :name (n7 / name :op1 "Germ
                                                  //| any")) :op5 (c5 / country :name (n9 / name :op1 "Italy")) :op6 (c6 / country
                                                  //|  :name (n10 / name :op1 "Spain")) :op7 (c7 / country :name (n12 / name :op1 
                                                  //| "Slovakia")) :op8 (c8 / country :name (n14 / name :op1 "United" :op2 "States
                                                  //| "))) "), (International; Government; Telecom; technology; science,"(a / and 
                                                  //| :op1 (i / international) :op2 (g / government-organization :ARG0-of (g2 / go
                                                  //| vern-01)) :op3 (t / telecommunication) :op4 (t2 / technology) :op5 (s / scie
                                                  //| nce)) "), (NATO CONSIDERS cyber attacks a threat to military and civilian co
                                                  //| mputer networks after th
                                                  //| Output exceeds cutoff limit.
  val s1 = Sentence(testData(2)._1, testData(2)._2)
                                                  //> WARNING: Found duplicate match for concept attack-01
                                                  //| WARNING: Found duplicate match for concept cyber
                                                  //| s1  : amr.Sentence = Sentence(NATO CONSIDERS cyber attacks a threat to milit
                                                  //| ary and civilian computer networks after the Estonian Government was struck 
                                                  //| by cyber attacks in 2007.,
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> NATO, 2 -> CONSIDERS, 3 -> cyber, 4 -> attac
                                                  //| ks, 5 -> a, 6 -> threat, 7 -> to, 8 -> military, 9 -> and, 10 -> civilian, 1
                                                  //| 1 -> computer, 12 -> networks, 13 -> after, 14 -> the, 15 -> Estonian, 16 ->
                                                  //|  Government, 17 -> was, 18 -> struck, 19 -> by, 20 -> cyber, 21 -> attacks, 
                                                  //| 22 -> in, 23 -> 2007)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,12
                                                  //| ), 12 -> (12,13), 13 -> (13,14), 14 -> (14,15), 15 -> (15,16), 16 -> (16,17)
                                                  //| , 17 -> (17,18), 18 -> (18,19), 19 -> (19,20), 20 -> (20,21), 21 -> (21,22),
                                                  //|  22 -> (22,23), 23 -> (
                                                  //| Output exceeds cutoff limit.
  s1.dependencyTree.nodes foreach println         //> (0,ROOT)
                                                  //| (5,a)
                                                  //| (10,civilian)
                                                  //| (14,the)
                                                  //| (20,cyber)
                                                  //| (1,NATO)
                                                  //| (6,threat)
                                                  //| (21,attacks)
                                                  //| (9,and)
                                                  //| (13,after)
                                                  //| (2,CONSIDERS)
                                                  //| (17,was)
                                                  //| (22,in)
                                                  //| (12,networks)
                                                  //| (7,to)
                                                  //| (3,cyber)
                                                  //| (18,struck)
                                                  //| (16,Government)
                                                  //| (11,computer)
                                                  //| (23,2007)
                                                  //| (8,military)
                                                  //| (19,by)
                                                  //| (4,attacks)
                                                  //| (15,Estonian)
  s1.dependencyTree.nodeSpans foreach println     //> (5,(5,6))
                                                  //| (10,(10,11))
                                                  //| (14,(14,15))
                                                  //| (20,(20,21))
                                                  //| (1,(1,2))
                                                  //| (6,(6,7))
                                                  //| (21,(21,22))
                                                  //| (9,(9,10))
                                                  //| (13,(13,14))
                                                  //| (2,(2,3))
                                                  //| (17,(17,18))
                                                  //| (22,(22,23))
                                                  //| (12,(12,13))
                                                  //| (7,(7,8))
                                                  //| (3,(3,4))
                                                  //| (18,(18,19))
                                                  //| (16,(16,17))
                                                  //| (11,(11,12))
                                                  //| (23,(23,24))
                                                  //| (8,(8,9))
                                                  //| (19,(19,20))
                                                  //| (4,(4,5))
                                                  //| (15,(15,16))
  s1.dependencyTree.arcs foreach println          //> ((7,12),pobj)
                                                  //| ((8,10),conj)
                                                  //| ((8,9),cc)
                                                  //| ((18,19),prep)
                                                  //| ((16,14),det)
                                                  //| ((18,13),mark)
                                                  //| ((22,23),pobj)
                                                  //| ((4,7),prep)
                                                  //| ((19,21),pobj)
                                                  //| ((3,1),nn)
                                                  //| ((18,17),auxpass)
                                                  //| ((6,5),det)
                                                  //| ((4,6),dobj)
                                                  //| ((16,15),amod)
                                                  //| ((4,18),advcl)
                                                  //| ((12,8),amod)
                                                  //| ((0,4),root)
                                                  //| ((12,11),nn)
                                                  //| ((3,2),nn)
                                                  //| ((21,22),prep)
                                                  //| ((21,20),nn)
                                                  //| ((4,3),nsubj)
                                                  //| ((18,16),nsubjpass)
  val AMR = s1.amr match {
    case None => null
    case Some(amr) => amr
  }                                               //> AMR  : amr.AMRGraph = AMRGraph(Map(0.1 -> attack-01, 0.0.0 -> name, 0.3.0.1.
                                                  //| 0.0 -> country, 0.2.0.0.0 -> network, 0.1.0 -> cyber, 0.2.0.0.1.0 -> civilia
                                                  //| n, 0.3.0.2.0 -> 2007, 0.0 -> military, 0.2.0.0.1 -> network, 0.3.0.1.0 -> go
                                                  //| vern-01, 0.0.0.0 -> "NATO", 0.2.0 -> threaten-01, 0.3.0.0 -> attack, 0.3.0 -
                                                  //| > strike-01, 0.3 -> after, 0.2.0.0.0.0 -> military, 0.2.0.0.2 -> computer, 0
                                                  //|  -> consider-02, 0.3.0.1 -> government-organization, 0.2 -> thing, 0.3.0.2 -
                                                  //| > date-entity, 0.3.0.1.0.0.0.0 -> "Estonia", ROOT -> ROOT, 0.2.0.0 -> and, 0
                                                  //| .3.0.1.0.0.0 -> name, 0.3.0.0.0 -> cyber),Map(0.1 -> (4,5), 0.0.0 -> (1,2), 
                                                  //| 0.3.0.1.0.0 -> (15,16), 0.2.0.0.0 -> (12,13), 0.1.0 -> (3,4), 0.2.0.0.1.0 ->
                                                  //|  (10,11), 0.3.0.2.0 -> (23,24), 0.0 -> (8,9), 0.0.0.0 -> (1,2), 0.2.0 -> (6,
                                                  //| 7), 0.3.0.0 -> (21,22), 0.3.0 -> (18,19), 0.3 -> (13,14), 0.2.0.0.2 -> (11,1
                                                  //| 2), 0 -> (2,3), 0.3.0.1 -> (16,17), 0.2 -> (6,7), 0.3.0.2 -> (23,24), 0.3.0.
                                                  //| 1.0.0.0.0 -> (15,16), 0.
                                                  //| Output exceeds cutoff limit.
  AMR.nodes foreach println                       //> (0.1,attack-01)
                                                  //| (0.0.0,name)
                                                  //| (0.3.0.1.0.0,country)
                                                  //| (0.2.0.0.0,network)
                                                  //| (0.1.0,cyber)
                                                  //| (0.2.0.0.1.0,civilian)
                                                  //| (0.3.0.2.0,2007)
                                                  //| (0.0,military)
                                                  //| (0.2.0.0.1,network)
                                                  //| (0.3.0.1.0,govern-01)
                                                  //| (0.0.0.0,"NATO")
                                                  //| (0.2.0,threaten-01)
                                                  //| (0.3.0.0,attack)
                                                  //| (0.3.0,strike-01)
                                                  //| (0.3,after)
                                                  //| (0.2.0.0.0.0,military)
                                                  //| (0.2.0.0.2,computer)
                                                  //| (0,consider-02)
                                                  //| (0.3.0.1,government-organization)
                                                  //| (0.2,thing)
                                                  //| (0.3.0.2,date-entity)
                                                  //| (0.3.0.1.0.0.0.0,"Estonia")
                                                  //| (ROOT,ROOT)
                                                  //| (0.2.0.0,and)
                                                  //| (0.3.0.1.0.0.0,name)
                                                  //| (0.3.0.0.0,cyber)
  AMR.nodeSpans foreach println                   //> (0.1,(4,5))
                                                  //| (0.0.0,(1,2))
                                                  //| (0.3.0.1.0.0,(15,16))
                                                  //| (0.2.0.0.0,(12,13))
                                                  //| (0.1.0,(3,4))
                                                  //| (0.2.0.0.1.0,(10,11))
                                                  //| (0.3.0.2.0,(23,24))
                                                  //| (0.0,(8,9))
                                                  //| (0.0.0.0,(1,2))
                                                  //| (0.2.0,(6,7))
                                                  //| (0.3.0.0,(21,22))
                                                  //| (0.3.0,(18,19))
                                                  //| (0.3,(13,14))
                                                  //| (0.2.0.0.2,(11,12))
                                                  //| (0,(2,3))
                                                  //| (0.3.0.1,(16,17))
                                                  //| (0.2,(6,7))
                                                  //| (0.3.0.2,(23,24))
                                                  //| (0.3.0.1.0.0.0.0,(15,16))
                                                  //| (0.2.0.0,(9,10))
                                                  //| (0.3.0.1.0.0.0,(15,16))
                                                  //| (0.3.0.0.0,(20,21))
  AMR.arcs foreach println                        //> ((0.0,0.0.0),name)
                                                  //| ((0.3.0,0.3.0.0),ARG0)
                                                  //| ((0,0.3),time)
                                                  //| ((0.0.0,0.0.0.0),op1)
                                                  //| ((0.2.0.0.1,0.2.0.0.1.0),mod)
                                                  //| ((0.1,0.1.0),mod)
                                                  //| ((0.3.0,0.3.0.1),ARG1)
                                                  //| ((0.3.0,0.3.0.2),time)
                                                  //| ((0.3.0.0,0.3.0.0.0),mod)
                                                  //| ((0,0.2),ARG2)
                                                  //| ((0,0.1),ARG1)
                                                  //| ((0.2.0.0.0,0.2.0.0.0.0),mod)
                                                  //| ((0.2,0.2.0),ARG1-of)
                                                  //| ((0,ROOT),ROOT)
                                                  //| ((0.3,0.3.0),op1)
                                                  //| ((0.3.0.1.0,0.3.0.1.0.0),ARG1)
                                                  //| ((0.2.0.0,0.2.0.0.1),op2)
                                                  //| ((0.2.0.0,0.2.0.0.0),op1)
                                                  //| ((0.3.0.1.0.0.0,0.3.0.1.0.0.0.0),op1)
                                                  //| ((0.3.0.1,0.3.0.1.0),ARG0-of)
                                                  //| ((0,0.0),ARG0)
                                                  //| ((0.3.0.1.0.0,0.3.0.1.0.0.0),name)
                                                  //| ((0.2.0,0.2.0.0),ARG2)
                                                  //| ((0.3.0.2,0.3.0.2.0),year)
                                                  //| ((0.2.0.0,0.2.0.0.2),mod)
}