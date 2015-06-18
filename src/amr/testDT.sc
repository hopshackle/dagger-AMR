package amr

object testDT {

  val dt = DependencyTree("The police want to arrest Michael Karras in Singapore.")
                                                  //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.0 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [4.3 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [2.0 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [3.8 sec].
                                                  //| dt  : amr.DependencyTree = 
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> The, 2 -> police, 3 -> want, 4 -> to, 5 -> a
                                                  //| rrest, 6 -> Michael, 7 -> Karras, 8 -> in, 9 -> Singapore)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10))
                                                  //| Edges:	Map((0,3) -> root, (2,1) -> det, (3,2) -> nsubj, (3,5) -> xcomp,
                                                  //|  (5,4) -> aux, (5,7) -> dobj, (7,6) -> nn, (7,8) -> prep, (8,9) -> pobj)
  val testData = AMRGraph.importFile("C:\\AMR\\AMR2.txt")
                                                  //> testData  : IndexedSeq[(String, String)] = Vector((2008-05-14,"(d / date-ent
                                                  //| ity :month 5 :day 14 :year 2008) "), (Estonia (EE); Latvia (LV); Lithuania (
                                                  //| LT); Germany (DE): Italy (IT); Spain (ES); Slovakia (SK); United States (US)
                                                  //| ,"(a / and :op1 (c / country :name (n / name :op1 "Estonia")) :op2 (c2 / cou
                                                  //| ntry :name (n3 / name :op1 "Latvia")) :op3 (c3 / country :name (n5 / name :o
                                                  //| p1 "Lithuania")) :op4 (c4 / country :name (n7 / name :op1 "Germany")) :op5 (
                                                  //| c5 / country :name (n9 / name :op1 "Italy")) :op6 (c6 / country :name (n10 /
                                                  //|  name :op1 "Spain")) :op7 (c7 / country :name (n12 / name :op1 "Slovakia")) 
                                                  //| :op8 (c8 / country :name (n14 / name :op1 "United" :op2 "States"))) "), (Int
                                                  //| ernational; Government; Telecom; technology; science,"(a / and :op1 (i / int
                                                  //| ernational) :op2 (g / government-organization :ARG0-of (g2 / govern-01)) :op
                                                  //| 3 (t / telecommunication) :op4 (t2 / technology) :op5 (s / science)) "), (NA
                                                  //| TO CONSIDERS cyber attacks a threat to military and civilian computer networ
                                                  //| ks after the Estonian Government was struck by cyber attacks in 2007.,"(c / 
                                                  //| consider-02 :ARG0 (m2 / military :name (n / name :op1 "NATO")) :ARG1 (a / at
                                                  //| tack-01 :mod (c2 / cyber)) :ARG2 (t / thing :ARG1-of (t2 / threaten-01 :ARG2
                                                  //|  (a5 / and :op1 (n2 / network :mod (m / military)) :op2 (n4 / network :mod (
                                                  //| c3 / civilian)) :mod (c4 / computer)))) :time (a3 / after :op1 (s / strike-0
                                                  //| 1 :ARG0 (a4 / attack :mod (c6 / cyber)) :ARG1 (g / government-organization :
                                                  //| ARG0-of (g2 / govern-01 :ARG1 (c5 / country :name (n3 / name :op1 "Estonia")
                                                  //| ))) :time (d / date-entity :year 2007)))) "), (NATO allies have signed an ag
                                                  //| reement to fund a research center to bolster defenses against cyber attacks.
                                                  //| ,(s / sign-01 :ARG0 (p / person :ARG0-of (a / ally-01) :mod (m / military :n
                                                  //| ame (n / name :op1 "NATO"))) :ARG1 (a2 / agree-01 :ARG0 p :ARG1 (f / fund-01
                                                  //|  :ARG0 p :ARG1 (c / center :mod (r / research-01)) :
                                                  //| Output exceeds cutoff limit.
  val s1 = Sentence(testData(2)._1, testData(2)._2)
                                                  //> s1  : amr.Sentence = Sentence(International; Government; Telecom; technology
                                                  //| ; science,
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> International, 3 -> Government, 5 -> Telecom
                                                  //| , 7 -> technology, 9 -> science)
                                                  //| SpanMap:	Map(1 -> (1,2), 3 -> (2,3), 5 -> (3,4), 7 -> (4,5), 9 -> (5,6))
                                                  //| Edges:	Map((0,1) -> root, (1,3) -> dep, (3,5) -> dep, (3,7) -> dep, (7,
                                                  //| 9) -> dep),Some(AMRGraph(Map(0.1 -> government-organization, 0.1.0 -> govern
                                                  //| -01, 0.0 -> international, 0.4 -> science, 0.3 -> technology, 0 -> and, 0.2 
                                                  //| -> telecommunication, ROOT -> ROOT),Map(0.1 -> (2,3), 0.0 -> (1,2), 0.4 -> (
                                                  //| 5,6), 0.3 -> (4,5), 0.2 -> (3,4)),Map((0,0.3) -> opN, (0.1,0.1.0) -> ARG0-of
                                                  //| , (0,0.2) -> opN, (0,0.1) -> opN, (0,ROOT) -> ROOT, (0,0.4) -> opN, (0,0.0) 
                                                  //| -> opN))),Map(5 -> 0.2, 1 -> 0.0, 9 -> 0.4, 7 -> 0.3, 3 -> 0.1))
  s1.dependencyTree.nodes foreach println         //> (0,ROOT)
                                                  //| (5,Telecom)
                                                  //| (1,International)
                                                  //| (9,science)
                                                  //| (7,technology)
                                                  //| (3,Government)
  s1.dependencyTree.nodeSpans foreach println     //> (5,(3,4))
                                                  //| (1,(1,2))
                                                  //| (9,(5,6))
                                                  //| (7,(4,5))
                                                  //| (3,(2,3))
  s1.dependencyTree.arcs foreach println          //> ((7,9),dep)
                                                  //| ((3,5),dep)
                                                  //| ((1,3),dep)
                                                  //| ((3,7),dep)
                                                  //| ((0,1),root)
    s1.dependencyTree.parentsOf(7)                //> res0: List[Int] = List(3)
  val AMR = s1.amr match {
    case None => null
    case Some(amr) => amr
  }                                               //> AMR  : amr.AMRGraph = AMRGraph(Map(0.1 -> government-organization, 0.1.0 -> 
                                                  //| govern-01, 0.0 -> international, 0.4 -> science, 0.3 -> technology, 0 -> and
                                                  //| , 0.2 -> telecommunication, ROOT -> ROOT),Map(0.1 -> (2,3), 0.0 -> (1,2), 0.
                                                  //| 4 -> (5,6), 0.3 -> (4,5), 0.2 -> (3,4)),Map((0,0.3) -> opN, (0.1,0.1.0) -> A
                                                  //| RG0-of, (0,0.2) -> opN, (0,0.1) -> opN, (0,ROOT) -> ROOT, (0,0.4) -> opN, (0
                                                  //| ,0.0) -> opN))
  AMR.nodes foreach println                       //> (0.1,government-organization)
                                                  //| (0.1.0,govern-01)
                                                  //| (0.0,international)
                                                  //| (0.4,science)
                                                  //| (0.3,technology)
                                                  //| (0,and)
                                                  //| (0.2,telecommunication)
                                                  //| (ROOT,ROOT)
  AMR.nodeSpans foreach println                   //> (0.1,(2,3))
                                                  //| (0.0,(1,2))
                                                  //| (0.4,(5,6))
                                                  //| (0.3,(4,5))
                                                  //| (0.2,(3,4))
  AMR.arcs foreach println                        //> ((0,0.3),opN)
                                                  //| ((0.1,0.1.0),ARG0-of)
                                                  //| ((0,0.2),opN)
                                                  //| ((0,0.1),opN)
                                                  //| ((0,ROOT),ROOT)
                                                  //| ((0,0.4),opN)
                                                  //| ((0,0.0),opN)
}