package amr

object WXExpertTest {
  val testData = AMRGraph.importFile("C:\\AMR\\AMR2.txt")
                                                  //> testData  : IndexedSeq[(String, String)] = Vector((2008-05-14,"(d / date-enti
                                                  //| ty :month 5 :day 14 :year 2008) "), (Estonia (EE); Latvia (LV); Lithuania (LT
                                                  //| ); Germany (DE): Italy (IT); Spain (ES); Slovakia (SK); United States (US),"(
                                                  //| a / and :op1 (c / country :name (n / name :op1 "Estonia")) :op2 (c2 / country
                                                  //|  :name (n3 / name :op1 "Latvia")) :op3 (c3 / country :name (n5 / name :op1 "L
                                                  //| ithuania")) :op4 (c4 / country :name (n7 / name :op1 "Germany")) :op5 (c5 / c
                                                  //| ountry :name (n9 / name :op1 "Italy")) :op6 (c6 / country :name (n10 / name :
                                                  //| op1 "Spain")) :op7 (c7 / country :name (n12 / name :op1 "Slovakia")) :op8 (c8
                                                  //|  / country :name (n14 / name :op1 "United" :op2 "States"))) "), (NATO CONSIDE
                                                  //| RS cyber attacks a threat to military and civilian computer networks after th
                                                  //| e Estonian Government was struck by cyber attacks in 2007.,"(c / consider-02 
                                                  //| :ARG0 (m2 / military :name (n / name :op1 "NATO")) :ARG1 (a / attack-01 :mod 
                                                  //| (c2 / cyber)) :ARG2 (t / thing :ARG1-of (t2 / threaten-01 :ARG2 (a5 / and :op
                                                  //| 1 (n2 / network :mod (m / military)) :op2 (n4 / network :mod (c3 / civilian))
                                                  //|  :mod (c4 / computer)))) :time (a3 / after :op1 (s / strike-01 :ARG0 (a4 / at
                                                  //| tack :mod (c6 / cyber)) :ARG1 (g / government-organization :ARG0-of (g2 / gov
                                                  //| ern-01 :ARG1 (c5 / country :name (n3 / name :op1 "Estonia")))) :time (d / dat
                                                  //| e-entity :year 2007)))) "), (NATO allies have signed an agreement to fund a r
                                                  //| esearch center to bolster defenses against cyber attacks.,(s / sign-01 :ARG0 
                                                  //| (p / person :ARG0-of (a / ally-01) :mod (m / military :name (n / name :op1 "N
                                                  //| ATO"))) :ARG1 (a2 / agree-01 :ARG0 p :ARG1 (f / fund-01 :ARG0 p :ARG1 (c / ce
                                                  //| nter :mod (r / research-01)) :purpose (b / bolster-01 :ARG0 p :ARG1 (d / defe
                                                  //| nd-01 :prep-against (a3 / attack :mod (c2 / cyber)))))))))
  val s1 = Sentence(testData(0)._1, testData(0)._2)
                                                  //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.2 sec].
                                                  //| s1  : amr.Sentence = Sentence(2008-05-14,DependencyTree(Map(1 -> 2008-05-14,
                                                  //|  0 -> ROOT),Map(1 -> (1,2)),Map((0,1) -> root)),Some(AMRGraph(Map(0.1 -> 14,
                                                  //|  0.0 -> 5, 0 -> date-entity, 0.2 -> 2008, ROOT -> ROOT),Map(0 -> (1,2), 0.2 
                                                  //| -> (1,2)),Map((0,0.0) -> month, (0,0.1) -> day, (0,0.2) -> year, (0,ROOT) ->
                                                  //|  ROOT))))
                                                  
  s1.mapFromDTtoAMR                               //> res0: scala.collection.immutable.Map[Int,String] = Map(1 -> 0.2)
  s1.positionToAMR                                //> res1: scala.collection.immutable.Map[Int,String] = Map(1 -> 0.2)
                                                  
 val s2 = Sentence(testData(1)._1, testData(1)._2)//> s2  : amr.Sentence = Sentence(Estonia (EE); Latvia (LV); Lithuania (LT); Ger
                                                  //| many (DE): Italy (IT); Spain (ES); Slovakia (SK); United States (US),Depende
                                                  //| ncyTree(Map(0 -> ROOT, 37 -> States, 1 -> Estonia, 6 -> Latvia, 28 -> ES, 21
                                                  //|  -> Italy, 33 -> SK, 13 -> LT, 39 -> US, 3 -> EE, 18 -> DE, 16 -> Germany, 3
                                                  //| 1 -> Slovakia, 11 -> Lithuania, 26 -> Spain, 23 -> IT, 8 -> LV, 36 -> United
                                                  //| ),Map(37 -> (16,17), 1 -> (1,2), 6 -> (3,4), 28 -> (12,13), 21 -> (9,10), 33
                                                  //|  -> (14,15), 13 -> (6,7), 39 -> (17,18), 3 -> (2,3), 18 -> (8,9), 16 -> (7,8
                                                  //| ), 31 -> (13,14), 11 -> (5,6), 26 -> (11,12), 23 -> (10,11), 8 -> (4,5), 36 
                                                  //| -> (15,16)),Map((21,23) -> dep, (26,28) -> appos, (3,1) -> nn, (0,3) -> root
                                                  //| , (16,18) -> appos, (37,39) -> appos, (3,6) -> dep, (6,8) -> appos, (6,11) -
                                                  //| > dep, (6,16) -> dep, (37,36) -> nn, (6,21) -> dep, (11,13) -> appos, (6,37)
                                                  //|  -> dep, (6,26) -> dep, (31,33) -> appos, (6,31) -> dep)),Some(AMRGraph(Map(
                                                  //| 0.1 -> country, 0.0.0 -> name, 0.6.0.0 -> "Slovakia", 0.6.0 -> name, 0.1.0 -
                                                  //| > name, 0.7.0 -> name, 0.5 -> country, 0.7.0.0 -> "United", 0.0 -> country, 
                                                  //| 0.4 -> country, 0.0.0.0 -> "Estonia", 0.5.0 -> name, 0.2.0 -> name, 0.3.0.0 
                                                  //| -> "Germany", 0.3.0 -> name, 0.1.0.0 -> "Latvia", 0.4.0.0 -> "Italy", 0.5.0.
                                                  //| 0 -> "Spain", 0.3 -> country, 0.4.0 -> name, 0 -> and, 0.6 -> country, 0.2 -
                                                  //| > country, ROOT -> ROOT, 0.7 -> country, 0.2.0.0 -> "Lithuania", 0.7.0.1 -> 
                                                  //| "States"),Map(0.1 -> (3,4), 0.0.0 -> (1,2), 0.6.0.0 -> (13,14), 0.6.0 -> (13
                                                  //| ,14), 0.1.0 -> (3,4), 0.7.0 -> (15,17), 0.5 -> (11,12), 0.7.0.0 -> (15,17), 
                                                  //| 0.0 -> (1,2), 0.4 -> (9,10), 0.0.0.0 -> (1,2), 0.5.0 -> (11,12), 0.2.0 -> (5
                                                  //| ,6), 0.3.0.0 -> (7,8), 0.3.0 -> (7,8), 0.1.0.0 -> (3,4), 0.4.0.0 -> (9,10), 
                                                  //| 0.5.0.0 -> (11,12), 0.3 -> (7,8), 0.4.0 -> (9,10), 0.6 -> (13,14), 0.2 -> (5
                                                  //| ,6), 0.7 -> (15,17), 0.2.0.0 -> (5,6), 0.7.0.1 -> (15,17)),Map((0.0,0.0.0) -
                                                  //| > name, (0.3.0,0.3.0.0) -> op1, (0,0.7) -> op8, (0,0
                                                  //| Output exceeds cutoff limit.
  s2.mapFromDTtoAMR                               //> res2: scala.collection.immutable.Map[Int,String] = Map(37 -> 0.7.0.1, 1 -> 0
                                                  //| .0.0.0, 6 -> 0.1.0.0, 28 -> NONE, 21 -> 0.4.0, 33 -> NONE, 13 -> NONE, 39 ->
                                                  //|  NONE, 3 -> NONE, 18 -> NONE, 16 -> 0.3, 31 -> 0.6, 11 -> 0.2.0.0, 26 -> 0.5
                                                  //| .0.0, 23 -> NONE, 8 -> NONE, 36 -> 0.7.0.1)
  s2.positionToAMR                                //> res3: scala.collection.immutable.Map[Int,String] = Map(5 -> 0.2.0.0, 1 -> 0.
                                                  //| 0.0.0, 9 -> 0.4.0, 13 -> 0.6, 7 -> 0.3, 3 -> 0.1.0.0, 16 -> 0.7.0.1, 11 -> 0
                                                  //| .5.0.0, 15 -> 0.7.0.1)
}