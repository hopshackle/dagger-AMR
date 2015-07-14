package amr

object ProduceDTAMRStandards {
  val testDataFull = AMRGraph.importFile("C:\\AMR\\trainingSet150.txt")
                                                  //> testDataFull  : IndexedSeq[(String, String)] = Vector((2008-05-14,"(d / date
                                                  //| -entity :month 5 :day 14 :year 2008) "), (Estonia (EE); Latvia (LV); Lithuan
                                                  //| ia (LT); Germany (DE): Italy (IT); Spain (ES); Slovakia (SK); United States 
                                                  //| (US),"(a / and :op1 (c / country :name (n / name :op1 "Estonia")) :op2 (c2 /
                                                  //|  country :name (n3 / name :op1 "Latvia")) :op3 (c3 / country :name (n5 / nam
                                                  //| e :op1 "Lithuania")) :op4 (c4 / country :name (n7 / name :op1 "Germany")) :o
                                                  //| p5 (c5 / country :name (n9 / name :op1 "Italy")) :op6 (c6 / country :name (n
                                                  //| 10 / name :op1 "Spain")) :op7 (c7 / country :name (n12 / name :op1 "Slovakia
                                                  //| ")) :op8 (c8 / country :name (n14 / name :op1 "United" :op2 "States"))) "), 
                                                  //| (International; Government; Telecom; technology; science,"(a / and :op1 (i /
                                                  //|  international) :op2 (g / government-organization :ARG0-of (g2 / govern-01))
                                                  //|  :op3 (t / telecommunication) :op4 (t2 / technology) :op5 (s / science)) "),
                                                  //|  (NATO CONSIDERS cyber attacks a threat to military and civilian computer ne
                                                  //| tworks after the Estonian Government was struck by cyber attacks in 2007.,"(
                                                  //| c / consider-02 :ARG0 (m2 / military :name (n / name :op1 "NATO")) :ARG1 (a 
                                                  //| / attack-01 :mod (c2 / cyber)) :ARG2 (t / thing :ARG1-of (t2 / threaten-01 :
                                                  //| ARG2 (a5 / and :op1 (n2 / network :mod (m / military)) :op2 (n4 / network :m
                                                  //| od (c3 / civilian)) :mod (c4 / computer)))) :time (a3 / after :op1 (s / stri
                                                  //| ke-01 :ARG0 (a4 / attack :mod (c6 / cyber)) :ARG1 (g / government-organizati
                                                  //| on :ARG0-of (g2 / govern-01 :ARG1 (c5 / country :name (n3 / name :op1 "Eston
                                                  //| ia")))) :time (d / date-entity :year 2007)))) "), (NATO allies have signed a
                                                  //| n agreement to fund a research center to bolster defenses against cyber atta
                                                  //| cks.,"(s / sign-01 :ARG0 (p / person :ARG0-of (a / ally-01) :mod (m / milita
                                                  //| ry :name (n / name :op1 "NATO"))) :ARG1 (a2 / agree-01 :ARG0 p :ARG1 (f / fu
                                                  //| nd-01 :ARG0 p :ARG1 (c / center :mod (r / research-01)) :purpose (b / bolste
                                                  //| r-01 :ARG0 p :ARG1 (d / defend-01 :prep-against (a3 / attack :mod (c2 / cybe
                                                  //| r))))))) "), (On 14 May 2008 7 NATO allies signed an agreement to fund a res
                                                  //| earch center.,"(s / sign-01 :ARG0 (p / person :quant 7 :ARG0-of (a / ally-01
                                                  //| ) :mod (m / military :name (n / name :op1 "NATO"))) :ARG1 (a2 / agree-01 :AR
                                                  //| G1 (f / fund-01 :ARG0 p :ARG1 (c / center :mod (r / research-01)))) :time (d
                                                  //|  / date-entity :month 5 :day 14 :year 2008)) "), (The center will 
                                                  //| Output exceeds cutoff limit.
  testDataFull.zipWithIndex map {
    case (x, i) =>
      val s = Sentence(x._1, x._2)
      val gv = new GraphViz("C://AMR//GV_initial_amr_" + (i + 1) + ".dot", s.amr.get)
  }                                               //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.1 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [4.5 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [4.4 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [3.4 sec].
                                                  //| WARNING: Found duplicate match for concept attack-01
                                                  //| WARNING: Found duplicate match for concept cyber
                                                  //| WARNING: Found duplicate match for concept together
                                                  //| WARNING: Found duplicate match for concept -
                                                  //| WARNING: Found duplicate match for concept "NATO"
                                                  //| WARNING: Found duplicate match for concept and
                                                  //| WARNING: Found duplicate match for concept minister
                                                  //| WARNING: Found duplicate match for concept foreign
                                                  //| WARNING: Found duplicate match for concept and
                                                  //| WARNING: Found duplicate match for concept border-01
                                                  //| WARNING: Found duplicate match for concept "Afghanistan"
                                                  //| WARNING: Found duplicate match for concept "Tajikistan"
                                                  //| WARNING: Found duplicate match for concept "Russian"
                                                  //| WARNING: Found duplicate match for concept "Federation"
                                                  //| WARNING: Found duplicate match for concept force
                                                  //| WARNING: Found duplicate match for concept and
                                                  //| WARNING: Found duplicate match for concept progress-01
                                                  //| WARNING: Found duplicate match for concept and
                                                  //| WARNING: Found duplicate match for concept and
                                                  //| WARNING: Found duplicate match for concept and
                                                  //| WARNING: Found duplicate match for concept plan-01
                                                  //| WARNING: Found duplicate match for concept and
                                                  //| WARNING: Found duplicate match for concept missile
                                                  //| WARNING: Found duplicate match for concept missile
                                                  //| WARNING: Found duplicate match for concept design-01
                                                  //| WARNING: Found duplicate match for concept missile
                                                  //| WARNING: Found duplicate match for concept early
                                                  //| WARNING: Found duplicate match for concept submarine
                                                  //| res0: IndexedSeq[Unit] = Vector((), (), (), (), (), (), (), (), (), (), (), 
                                                  //| (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), 
                                                  //| (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), 
                                                  //| (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (),
                                                  //| Output exceeds cutoff limit.
    testDataFull.zipWithIndex map {
    case (x, i) =>
      val s = Sentence(x._1, x._2)
      val gv = new GraphViz("C://AMR//GV_initial_dt_" + (i + 1) + ".dot", s.dependencyTree.toAMR)
  }                                               //> WARNING: Found duplicate match for concept attack-01
                                                  //| WARNING: Found duplicate match for concept cyber
                                                  //| WARNING: Found duplicate match for concept together
                                                  //| WARNING: Found duplicate match for concept -
                                                  //| WARNING: Found duplicate match for concept "NATO"
                                                  //| WARNING: Found duplicate match for concept and
                                                  //| WARNING: Found duplicate match for concept minister
                                                  //| WARNING: Found duplicate match for concept foreign
                                                  //| WARNING: Found duplicate match for concept and
                                                  //| WARNING: Found duplicate match for concept border-01
                                                  //| WARNING: Found duplicate match for concept "Afghanistan"
}