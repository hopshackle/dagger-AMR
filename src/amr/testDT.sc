package amr

object testDT {

  val dt = DependencyTree("The police want to arrest Michael Karras in Singapore.")
                                                  //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.1 sec].
                                                  //| dt  : amr.DependencyTree = DependencyTree(Map(0 -> ROOT, 5 -> arrest, 1 -> T
                                                  //| he, 6 -> Michael, 9 -> Singapore, 2 -> police, 7 -> Karras, 3 -> want, 8 -> 
                                                  //| in, 4 -> to),Map(0 -> (0,1), 5 -> (5,6), 1 -> (1,2), 6 -> (6,7), 9 -> (9,10)
                                                  //| , 2 -> (2,3), 7 -> (7,8), 3 -> (3,4), 8 -> (8,9), 4 -> (4,5)),List((2,1,det)
                                                  //| , (3,2,nsubj), (0,3,root), (5,4,aux), (3,5,xcomp), (7,6,nn), (5,7,dobj), (7,
                                                  //| 8,prep), (8,9,pobj)))
  val testData = AMRGraph.importFile("C:\\AMR\\AMR2.txt")
                                                  //> testData  : IndexedSeq[(String, String)] = Vector((NATO CONSIDERS cyber atta
                                                  //| cks a threat to military and civilian computer networks after the Estonian G
                                                  //| overnment was struck by cyber attacks in 2007.,"(c / consider-02 :ARG0 (m2 /
                                                  //|  military :name (n / name :op1 "NATO")) :ARG1 (a / attack-01 :mod (c2 / cybe
                                                  //| r)) :ARG2 (t / thing :ARG1-of (t2 / threaten-01 :ARG2 (a5 / and :op1 (n2 / n
                                                  //| etwork :mod (m / military)) :op2 (n4 / network :mod (c3 / civilian)) :mod (c
                                                  //| 4 / computer)))) :time (a3 / after :op1 (s / strike-01 :ARG0 (a4 / attack :m
                                                  //| od (c6 / cyber)) :ARG1 (g / government-organization :ARG0-of (g2 / govern-01
                                                  //|  :ARG1 (c5 / country :name (n3 / name :op1 "Estonia")))) :time (d / date-ent
                                                  //| ity :year 2007)))) "), (NATO allies have signed an agreement to fund a resea
                                                  //| rch center to bolster defenses against cyber attacks.,(s / sign-01 :ARG0 (p 
                                                  //| / person :ARG0-of (a / ally-01) :mod (m / military :name (n / name :op1 "NAT
                                                  //| O"))) :ARG1 (a2 / agree-01 :ARG0 p :ARG1 (f / fund-01 :ARG0 p :ARG1 (c / cen
                                                  //| ter :mod (r / research-01)) :purpose (b / bolster-01 :ARG0 p :ARG1 (d / defe
                                                  //| nd-01 :prep-against (a3 / attack :mod (c2 / cyber)))))))))
  val s1 = Sentence(testData(0)._1, testData(0)._2)
                                                  //> WARNING: Found duplicate match for concept attack-01
                                                  //| WARNING: Found duplicate match for concept cyber
                                                  //| s1  : amr.Sentence = Sentence(NATO CONSIDERS cyber attacks a threat to milit
                                                  //| ary and civilian computer networks after the Estonian Government was struck 
                                                  //| by cyber attacks in 2007.,DependencyTree(Map(0 -> ROOT, 5 -> a, 10 -> civili
                                                  //| an, 14 -> the, 20 -> cyber, 1 -> NATO, 6 -> threat, 21 -> attacks, 9 -> and,
                                                  //|  13 -> after, 2 -> CONSIDERS, 17 -> was, 22 -> in, 12 -> networks, 7 -> to, 
                                                  //| 3 -> cyber, 18 -> struck, 16 -> Government, 11 -> computer, 23 -> 2007, 8 ->
                                                  //|  military, 19 -> by, 4 -> attacks, 15 -> Estonian),Map(0 -> (0,1), 5 -> (5,6
                                                  //| ), 10 -> (10,11), 14 -> (14,15), 20 -> (20,21), 1 -> (1,2), 6 -> (6,7), 21 -
                                                  //| > (21,22), 9 -> (9,10), 13 -> (13,14), 2 -> (2,3), 17 -> (17,18), 22 -> (22,
                                                  //| 23), 12 -> (12,13), 7 -> (7,8), 3 -> (3,4), 18 -> (18,19), 16 -> (16,17), 11
                                                  //|  -> (11,12), 23 -> (23,24), 8 -> (8,9), 19 -> (19,20), 4 -> (4,5), 15 -> (15
                                                  //| ,16)),List((3,1,nn), (3,2,nn), (4,3,nsubj), (0,4,root), (6,5,det), (4,6,dobj
                                                  //| ), (4,7,prep), (12,8,amod), (8,9,cc), (8,10,conj), (12,11,nn), (7,12,pobj), 
                                                  //| (18,13,mark), (16,14,det), (16,15,amod), (18,16,nsubjpass), (18,17,auxpass),
                                                  //|  (4,18,advcl), (18,19,prep), (21,20,nn), (19,21,pobj), (21,22,prep), (22,23,
                                                  //| pobj))),Some(AMRGraph(Map(0.1 -> attack-01, 0.0.0 -> name, 0.3.0.1.0.0 -> co
                                                  //| untry, 0.2.0.0.0 -> network, 0.1.0 -> cyber, 0.2.0.0.1.0 -> civilian, 0.3.0.
                                                  //| 2.0 -> 2007, 0.0 -> military, 0.2.0.0.1 -> network, 0.3.0.1.0 -> govern-01, 
                                                  //| 0.0.0.0 -> "NATO", 0.2.0 -> threaten-01, 0.3.0.0 -> attack, 0.3.0 -> strike-
                                                  //| 01, 0.3 -> after, 0.2.0.0.0.0 -> military, 0.2.0.0.2 -> computer, 0 -> consi
                                                  //| der-02, 0.3.0.1 -> government-organization, 0.2 -> thing, 0.3.0.2 -> date-en
                                                  //| tity, 0.3.0.1.0.0.0.0 -> "Estonia", ROOT -> ROOT, 0.2.0.0 -> and, 0.3.0.1.0.
                                                  //| 0.0 -> name, 0.3.0.0.0 -> cyber),Map(0.1 -> (3,4), 0.0.0 -> (0,1), 0.3.0.1.0
                                                  //| .0 -> (14,15), 0.2.0.
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
  s1.dependencyTree.nodeSpans foreach println     //> (0,(0,1))
                                                  //| (5,(5,6))
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
  s1.dependencyTree.arcs foreach println          //> (3,1,nn)
                                                  //| (3,2,nn)
                                                  //| (4,3,nsubj)
                                                  //| (0,4,root)
                                                  //| (6,5,det)
                                                  //| (4,6,dobj)
                                                  //| (4,7,prep)
                                                  //| (12,8,amod)
                                                  //| (8,9,cc)
                                                  //| (8,10,conj)
                                                  //| (12,11,nn)
                                                  //| (7,12,pobj)
                                                  //| (18,13,mark)
                                                  //| (16,14,det)
                                                  //| (16,15,amod)
                                                  //| (18,16,nsubjpass)
                                                  //| (18,17,auxpass)
                                                  //| (4,18,advcl)
                                                  //| (18,19,prep)
                                                  //| (21,20,nn)
                                                  //| (19,21,pobj)
                                                  //| (21,22,prep)
                                                  //| (22,23,pobj)
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
                                                  //| .3.0.1.0.0.0 -> name, 0.3.0.0.0 -> cyber),Map(0.1 -> (3,4), 0.0.0 -> (0,1), 
                                                  //| 0.3.0.1.0.0 -> (14,15), 0.2.0.0.0 -> (11,12), 0.1.0 -> (2,3), 0.2.0.0.1.0 ->
                                                  //|  (9,10), 0.3.0.2.0 -> (22,23), 0.0 -> (7,8), 0.0.0.0 -> (0,1), 0.2.0 -> (5,6
                                                  //| ), 0.3.0.0 -> (20,21), 0.3.0 -> (17,18), 0.3 -> (12,13), 0.2.0.0.2 -> (10,11
                                                  //| ), 0 -> (1,2), 0.3.0.1 -> (15,16), 0.2 -> (5,6), 0.3.0.2 -> (22,23), 0.3.0.1
                                                  //| .0.0.0.0 -> (14,15), 0.2.0.0 -> (8,9), 0.3.0.1.0.0.0 -> (14,15), 0.3.0.0.0 -
                                                  //| > (19,20)),List((0,ROOT,ROOT), (0.3.0.0,0.3.0.0.0,mod), (0.2.0.0.1,0.2.0.0.1
                                                  //| .0,mod), (0.0.0,0.0.0.0,op1), (0.3.0.1.0.0.0,0.3.0.1.0.0.0.0,op1), (0.3.0.2,
                                                  //| 0.3.0.2.0,year), (0.2.0,0.2.0.0,ARG2), (0.1,0.1.0,mod), (0.2.0.0.0,0.2.0.0.0
                                                  //| .0,mod), (0.3.0.1,0.3.0.1.0,ARG0-of), (0.3.0.1.0.0,0.3.0.1.0.0.0,name), (0.0
                                                  //| ,0.0.0,name), (0.2.0.0,0.2.0.0.0,op1), (0.2.0.0,0.2.0.0.1,op2), (0.2.0.0,0.2
                                                  //| .0.0.2,mod), (0.3,0.3.0,op1), (0,0.0,ARG0), (0,0.1,ARG1), (0,0.2,ARG2), (0,0
                                                  //| .3,time), (0.3.0.1.0,0.3.0.1.0.0,ARG1), (0.3.0,0.3.0.0,ARG0), (0.3.0,0.3.0.1
                                                  //| ,ARG1), (0.3.0,0.3.0.2,time), (0.2,0.2.0,ARG1-of)))
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
  AMR.nodeSpans foreach println                   //> (0.1,(3,4))
                                                  //| (0.0.0,(0,1))
                                                  //| (0.3.0.1.0.0,(14,15))
                                                  //| (0.2.0.0.0,(11,12))
                                                  //| (0.1.0,(2,3))
                                                  //| (0.2.0.0.1.0,(9,10))
                                                  //| (0.3.0.2.0,(22,23))
                                                  //| (0.0,(7,8))
                                                  //| (0.0.0.0,(0,1))
                                                  //| (0.2.0,(5,6))
                                                  //| (0.3.0.0,(20,21))
                                                  //| (0.3.0,(17,18))
                                                  //| (0.3,(12,13))
                                                  //| (0.2.0.0.2,(10,11))
                                                  //| (0,(1,2))
                                                  //| (0.3.0.1,(15,16))
                                                  //| (0.2,(5,6))
                                                  //| (0.3.0.2,(22,23))
                                                  //| (0.3.0.1.0.0.0.0,(14,15))
                                                  //| (0.2.0.0,(8,9))
                                                  //| (0.3.0.1.0.0.0,(14,15))
                                                  //| (0.3.0.0.0,(19,20))
  AMR.arcs foreach println                        //> (0,ROOT,ROOT)
                                                  //| (0.3.0.0,0.3.0.0.0,mod)
                                                  //| (0.2.0.0.1,0.2.0.0.1.0,mod)
                                                  //| (0.0.0,0.0.0.0,op1)
                                                  //| (0.3.0.1.0.0.0,0.3.0.1.0.0.0.0,op1)
                                                  //| (0.3.0.2,0.3.0.2.0,year)
                                                  //| (0.2.0,0.2.0.0,ARG2)
                                                  //| (0.1,0.1.0,mod)
                                                  //| (0.2.0.0.0,0.2.0.0.0.0,mod)
                                                  //| (0.3.0.1,0.3.0.1.0,ARG0-of)
                                                  //| (0.3.0.1.0.0,0.3.0.1.0.0.0,name)
                                                  //| (0.0,0.0.0,name)
                                                  //| (0.2.0.0,0.2.0.0.0,op1)
                                                  //| (0.2.0.0,0.2.0.0.1,op2)
                                                  //| (0.2.0.0,0.2.0.0.2,mod)
                                                  //| (0.3,0.3.0,op1)
                                                  //| (0,0.0,ARG0)
                                                  //| (0,0.1,ARG1)
                                                  //| (0,0.2,ARG2)
                                                  //| (0,0.3,time)
                                                  //| (0.3.0.1.0,0.3.0.1.0.0,ARG1)
                                                  //| (0.3.0,0.3.0.0,ARG0)
                                                  //| (0.3.0,0.3.0.1,ARG1)
                                                  //| (0.3.0,0.3.0.2,time)
                                                  //| (0.2,0.2.0,ARG1-of)
}