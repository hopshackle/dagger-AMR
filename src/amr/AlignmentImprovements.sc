package amr
import edu.cmu.lti.nlp.amr._

object AlignmentImprovements {

// val (rawSentence, rawAMR) = AMRGraph.importFile("C:\\AMR\\Riyadh-based.txt")(0)
//  val (rawSentence, rawAMR) = AMRGraph.importFile("C:\\AMR\\CountryList.txt")(0)
//  val (rawSentence, rawAMR) = AMRGraphimportFile("C:\\AMR\\Lavrov.txt")(0)
  val (rawSentence, rawAMR) = AMRGraph.importFile("C:\\AMR\\TargetHyphen.txt")(0)
                                                  //> rawSentence  : String = On 5 January 2002 pan-Arab anti-terror academy Naif 
                                                  //| Arab Academy for Security Sciences reopened for the first time since the Sep
                                                  //| tember 2001 terror attacks on the US.
                                                  //| rawAMR  : String = (r / reopen-01 :ARG1 (u / university :name (n / name :op1
                                                  //|  "Naif" :op2 "Arab" :op3 "Academy" :op4 "for" :op5 "Security" :op6 "Sciences
                                                  //| ") :purpose (o / oppose-01 :ARG1 (t / terror)) :mod (e / ethnic-group :name 
                                                  //| (n3 / name :op1 "Arab") :mod (p / pan))) :time (d / date-entity :year 2002 :
                                                  //| month 1 :day 5) :frequency (f / first :time (s / since :op1 (a3 / attack-01 
                                                  //| :ARG1 (c / country :name (n2 / name :op1 "US")) :time (d2 / date-entity :yea
                                                  //| r 2001 :month 9)))))
// val (rawSentence, rawAMR) = AMRGraph.importFile("C:\\AMR\\simpleDate.txt")(0)
// val (rawSentence, rawAMR) = AMRGraph.importFile("C:\\AMR\\lobbying.tt")(0)

  //    val tokenisedSentence = rawSentence.split(" ")
  val amr = Graph.parse(rawAMR)                   //> amr  : edu.cmu.lti.nlp.amr.Graph = Graph((reopen-01 :ARG1 (university :name 
                                                  //| (name :op1 "Naif" :op2 "Arab" :op3 "Academy" :op4 "for" :op5 "Security" :op6
                                                  //|  "Sciences") :purpose (oppose-01 :ARG1 terror) :mod (ethnic-group :name (nam
                                                  //| e :op1 "Arab") :mod pan)) :time (date-entity :year 2002 :month 1 :day 5) :fr
                                                  //| equency (first :time (since :op1 (attack-01 :ARG1 (country :name (name :op1 
                                                  //| "US")) :time (date-entity :year 2001 :month 9))))),ArrayBuffer(),Map(0.2.0.0
                                                  //| .1 -> (date-entity :year 2001 :month 9), 0.2.0.0.1.1 -> 9, 0.0.0 -> (name :o
                                                  //| p1 "Naif" :op2 "Arab" :op3 "Academy" :op4 "for" :op5 "Security" :op6 "Scienc
                                                  //| es"), 0.0.2.1 -> pan, 0.0.0.3 -> "for", 0.0.0.0 -> "Naif", 0.0.2 -> (ethnic-
                                                  //| group :name (name :op1 "Arab") :mod pan), 0.1 -> (date-entity :year 2002 :mo
                                                  //| nth 1 :day 5), 0.1.1 -> 1, 0.2.0 -> (since :op1 (attack-01 :ARG1 (country :n
                                                  //| ame (name :op1 "US")) :time (date-entity :year 2001 :month 9))), 0.2.0.0.0 -
                                                  //| > (country :name (name :op1 "US")), 0.2.0.0.0.0.0 -> "US", 0.2.0.0.1.0 -> 20
                                                  //| 01, 0.0.2.0 -> (name :op1 "Arab"), 0.0.0.2 -> "Academy", 0.0.0.5 -> "Science
                                                  //| s", 0.0.2.0.0 -> "Arab", 0.0 -> (university :name (name :op1 "Naif" :op2 "Ar
                                                  //| ab" :op3 "Academy" :op4 "for" :op5 "Security" :op6 "Sciences") :purpose (opp
                                                  //| ose-01 :ARG1 terror) :mod (ethnic-group :name (name :op1 "Arab") :mod pan)),
                                                  //|  0.1.0 -> 2002, 0.0.1 -> (oppose-01 :ARG1 terror), 0.2.0.0 -> (attack-01 :AR
                                                  //| G1 (country :name (name :op1 "US")) :time (date-entity :year 2001 :month 9))
                                                  //| , 0.0.0.4 -> "Security", 0 -> (reopen-01 :ARG1 (university :name (name :op1 
                                                  //| "Naif" :op2 "Arab" :op3 "Academy" :op4 "for" :op5 "Security" :op6 "Sciences"
                                                  //| ) :purpose (oppose-01 :ARG1 terror) :mod (ethnic-group :name (name :op1 "Ara
                                                  //| b") :mod pan)) :time (date-entity :year 2002 :month 1 :day 5) :frequency (fi
                                                  //| rst :time (since :op1 (attack-01 :ARG1 (country :name (name :op1 "US")) :tim
                                                  //| e (date-entity :year 2001 :month 9))))), 0.2.0.0.0.0 -> (name :op1 "US"), 0.
                                                  //| 0.1.0 -> terror, 0.0.0.1 -> "Arab", 0.1.2 -> 5, 0.2 -> (first :time (since :
                                                  //| op1 (attack-01 :ARG1 (country :name (name :op1 "US")) :time (date-entity :ye
                                                  //| ar 2001 :month 9))))),Map(e -> (ethnic-group :name (name :op1 "Arab") :mod p
                                                  //| an), n -> (name :op1 "Naif" :op2 "Arab" :op3 "Academy" :op4 "for" :op5 "Secu
                                                  //| rity" :op6 "Sciences"), t -> terror, a3 -> (attack-01 :ARG1 (country :name (
                                                  //| name :op1 "US")) :time (date-entity :year 2001 :month 9)), s -> (s
                                                  //| Output exceeds cutoff limit.
                                                  // ee-01 :ARG0 (person :ARG0-of (have-org-role-91 :ARG1 (organization :name (na

  val amrGraph = AlignTest.alignWords(rawSentence, amr, false)
                                                  //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [3.7 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [7.0 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [3.6 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [2.8 sec].
                                                  //| amrGraph  : amr.AMRGraph = AMRGraph(Map(0.1 -> date-entity, 0.0.0 -> name, 0
                                                  //| .0.2.0.0 -> "Arab", 0.2.0.0.0 -> country, 0.1.0 -> 2002, 0.2.0.0.1.0 -> 2001
                                                  //| , 0.0.1.0 -> terror, 0.0.0.3 -> "for", 0.0 -> university, 0.0.2.1 -> pan, 0.
                                                  //| 2.0.0.1 -> date-entity, 0.0.2 -> ethnic-group, 0.0.0.0 -> "Naif", 0.0.0.5 ->
                                                  //|  "Sciences", 0.2.0 -> since, 0.0.1 -> oppose-01, 0.2.0.0.1.1 -> 9, 0.2.0.0.0
                                                  //| .0 -> name, 0 -> reopen-01, 0.1.2 -> 5, 0.0.0.4 -> "Security", 0.0.0.1 -> "A
                                                  //| rab", 0.2.0.0.0.0.0 -> "US", 0.2 -> first, 0.1.1 -> 1, 0.2.0.0 -> attack-01,
                                                  //|  0.0.2.0 -> name, 0.0.0.2 -> "Academy"),Map(0.1.0 -> (4,5), 0.2.0.0.1.0 -> (
                                                  //| 22,23), 0.0.1.0 -> (23,24), 0.0.0.3 -> (11,12), 0.0.2.1 -> (5,6), 0.0.0.0 ->
                                                  //|  (8,9), 0.0.0.5 -> (13,14), 0.2.0 -> (19,20), 0.2.0.0.1.1 -> (21,22), 0 -> (
                                                  //| 14,15), 0.1.2 -> (2,3), 0.0.0.4 -> (12,13), 0.0.0.1 -> (9,10), 0.2.0.0.0.0.0
                                                  //|  -> (27,28), 0.2 -> (17,18), 0.1.1 -> (3,4), 0.2.0.0 -> (24,25), 0.0.0.2 -> 
                                                  //| (10,11)),Map((0.0,0.0.0) -> name, (0.0.0,0.0.0.0) -> opN, (0.2.0.0.1,0.2.0.0
                                                  //| .1.0) -> year, (0.1,0.1.0) -> year, (0.1,0.1.2) -> day, (0.0.0,0.0.0.5) -> o
                                                  //| pN, (0.0.2,0.0.2.0) -> name, (0,0.2) -> frequency, (0,0.1) -> time, (0.2.0.0
                                                  //| .0,0.2.0.0.0.0) -> name, (0.2.0.0.0.0,0.2.0.0.0.0.0) -> opN, (0.0.2.0,0.0.2.
                                                  //| 0.0) -> opN, (0.2,0.2.0) -> time, (0.0,0.0.2) -> mod, (0.2.0.0.1,0.2.0.0.1.1
                                                  //| ) -> month, (0.0.0,0.0.0.1) -> opN, (0.2.0.0,0.2.0.0.1) -> time, (0.0.0,0.0.
                                                  //| 0.4) -> opN, (0.2.0.0,0.2.0.0.0) -> ARG1, (0.0.0,0.0.0.2) -> opN, (0.0.0,0.0
                                                  //| .0.3) -> opN, (0.0.2,0.0.2.1) -> mod, (0,0.0) -> ARG1, (0.0.1,0.0.1.0) -> AR
                                                  //| G1, (0.2.0,0.2.0.0) -> opN, (0.0,0.0.1) -> purpose, (0.1,0.1.1) -> month),Ma
                                                  //| p((0.0,0.0.0) -> name, (0.0.0,0.0.0.0) -> op1, (0.2.0.0.1,0.2.0.0.1.0) -> ye
                                                  //| ar, (0.1,0.1.0) -> year, (0.1,0.1.2) -> day, (0.0.0,0.0.0.5) -> op6, (0.0.2,
                                                  //| 0.0.2.0) -
                                                  //| Output exceeds cutoff limit.
// val wordAlignments = AlignWords.alignWords(tokenisedSentence.toArray, amr)
  val tokenisedSentence = DependencyTree.preProcess(rawSentence)
                                                  //> tokenisedSentence  : List[String] = List(On, 5, January, 2002, pan-Arab, ant
                                                  //| i-terror, academy, Naif, Arab, Academy, for, Security, Sciences, reopened, f
                                                  //| or, the, first, time, since, the, September, 2001, terror, attacks, on, the,
                                                  //|  US)
amrGraph.nodes foreach {case (k, v) => println(k + ":" + v + " -> " + amrGraph.nodeSpans.getOrElse(k, (-1, -1)._1))}
                                                  //> 0.1:date-entity -> -1
                                                  //| 0.0.0:name -> -1
                                                  //| 0.0.2.0.0:"Arab" -> -1
                                                  //| 0.2.0.0.0:country -> -1
                                                  //| 0.1.0:2002 -> (4,5)
                                                  //| 0.2.0.0.1.0:2001 -> (22,23)
                                                  //| 0.0.1.0:terror -> (23,24)
                                                  //| 0.0.0.3:"for" -> (11,12)
                                                  //| 0.0:university -> -1
                                                  //| 0.0.2.1:pan -> (5,6)
                                                  //| 0.2.0.0.1:date-entity -> -1
                                                  //| 0.0.2:ethnic-group -> -1
                                                  //| 0.0.0.0:"Naif" -> (8,9)
                                                  //| 0.0.0.5:"Sciences" -> (13,14)
                                                  //| 0.2.0:since -> (19,20)
                                                  //| 0.0.1:oppose-01 -> -1
                                                  //| 0.2.0.0.1.1:9 -> (21,22)
                                                  //| 0.2.0.0.0.0:name -> -1
                                                  //| 0:reopen-01 -> (14,15)
                                                  //| 0.1.2:5 -> (2,3)
                                                  //| 0.0.0.4:"Security" -> (12,13)
                                                  //| 0.0.0.1:"Arab" -> (9,10)
                                                  //| 0.2.0.0.0.0.0:"US" -> (27,28)
                                                  //| 0.2:first -> (17,18)
                                                  //| 0.1.1:1 -> (3,4)
                                                  //| 0.2.0.0:attack-01 -> (24,25)
                                                  //| 0.0.2.0:name -> -1
                                                  //| 0.0.0.2:"Academy" -> (10,11)
                                               
                                            
// wordAlignments foreach println
                                            
 // AlignSpans.alignSpans(tokenisedSentence.toArray, amr, wordAlignments) zip wordAlignments foreach println
 val amrG =  AMRGraph(amr)                        //> amrG  : amr.AMRGraph = AMRGraph(Map(0.1 -> date-entity, 0.0.0 -> name, 0.0.
                                                  //| 2.0.0 -> "Arab", 0.2.0.0.0 -> country, 0.1.0 -> 2002, 0.2.0.0.1.0 -> 2001, 
                                                  //| 0.0.1.0 -> terror, 0.0.0.3 -> "for", 0.0 -> university, 0.0.2.1 -> pan, 0.2
                                                  //| .0.0.1 -> date-entity, 0.0.2 -> ethnic-group, 0.0.0.0 -> "Naif", 0.0.0.5 ->
                                                  //|  "Sciences", 0.2.0 -> since, 0.0.1 -> oppose-01, 0.2.0.0.1.1 -> 9, 0.2.0.0.
                                                  //| 0.0 -> name, 0 -> reopen-01, 0.1.2 -> 5, 0.0.0.4 -> "Security", 0.0.0.1 -> 
                                                  //| "Arab", 0.2.0.0.0.0.0 -> "US", 0.2 -> first, 0.1.1 -> 1, 0.2.0.0 -> attack-
                                                  //| 01, 0.0.2.0 -> name, 0.0.0.2 -> "Academy"),Map(),Map((0.0,0.0.0) -> name, (
                                                  //| 0.0.0,0.0.0.0) -> opN, (0.2.0.0.1,0.2.0.0.1.0) -> year, (0.1,0.1.0) -> year
                                                  //| , (0.1,0.1.2) -> day, (0.0.0,0.0.0.5) -> opN, (0.0.2,0.0.2.0) -> name, (0,0
                                                  //| .2) -> frequency, (0,0.1) -> time, (0.2.0.0.0,0.2.0.0.0.0) -> name, (0.2.0.
                                                  //| 0.0.0,0.2.0.0.0.0.0) -> opN, (0.0.2.0,0.0.2.0.0) -> opN, (0.2,0.2.0) -> tim
                                                  //| e, (0.0,0.0.2) -> mod, (0.2.0.0.1,0.2.0.0.1.1) -> month, (0.0.0,0.0.0.1) ->
                                                  //|  opN, (0.2.0.0,0.2.0.0.1) -> time, (0.0.0,0.0.0.4) -> opN, (0.2.0.0,0.2.0.0
                                                  //| .0) -> ARG1, (0.0.0,0.0.0.2) -> opN, (0.0.0,0.0.0.3) -> opN, (0.0.2,0.0.2.1
                                                  //| ) -> mod, (0,0.0) -> ARG1, (0.0.1,0.0.1.0) -> ARG1, (0.2.0,0.2.0.0) -> opN,
                                                  //|  (0.0,0.0.1) -> purpose, (0.1,0.1.1) -> month),Map((0.0,0.0.0) -> name, (0.
                                                  //| 0.0,0.0.0.0) -> op1, (0.2.0.0.1,0.2.0.0.1.0) -> year, (0.1,0.1.0) -> year, 
                                                  //| (0.1,0.1.2) -> day, (0.0.0,0.0.0.5) -> op6, (0.0.2,0.0.2.0) -> name, (0,0.2
                                                  //| ) -> frequency, (0,0.1) -> time, (0.2.0.0.0,0.2.0.0.0.0) -> name, (0.2.0.0.
                                                  //| 0.0,0.2.0.0.0.0.0) -> op1, (0.0.2.0,0.0.2.0.0) -> op1, (0.2,0.2.0) -> time,
                                                  //|  (0.0,0.0.2) -> mod, (0.2.0.0.1,0.2.0.0.1.1) -> month, (0.0.0,0.0.0.1) -> o
                                                  //| p2, (0.2.0.0,0.2.0.0.1) -> time, (0.0.0,0.0.0.4) -> op5, (0.2.0.0,0.2.0.0.0
                                                  //| ) -> ARG1, (0.0.0,0.0.0.2) -> op3, (0.0.0,0.0.0.3) -> op4, (0.0.2,0.0.2.1) 
                                                  //| -> mod, (0,0.0) -> ARG1, (0.0.1,0.0.1.0) -> ARG1, (0.2.0,0.2.0.0) -> op1, (
                                                  //| 0.0,0.0.1) -> purpose, (0.1,0.1.1) -> month),List())
 amrG.nodes.keys.toList.sorted foreach (x => println(s"${x}\t\t${amrG.nodes(x)}\t\t${amrG.nodeSpans.getOrElse(x, "No Mapping")}"))
                                                  //> 0		reopen-01		No Mapping
                                                  //| 0.0		university		No Mapping
                                                  //| 0.0.0		name		No Mapping
                                                  //| 0.0.0.0		"Naif"		No Mapping
                                                  //| 0.0.0.1		"Arab"		No Mapping
                                                  //| 0.0.0.2		"Academy"		No Mapping
                                                  //| 0.0.0.3		"for"		No Mapping
                                                  //| 0.0.0.4		"Security"		No Mapping
                                                  //| 0.0.0.5		"Sciences"		No Mapping
                                                  //| 0.0.1		oppose-01		No Mapping
                                                  //| 0.0.1.0		terror		No Mapping
                                                  //| 0.0.2		ethnic-group		No Mapping
                                                  //| 0.0.2.0		name		No Mapping
                                                  //| 0.0.2.0.0		"Arab"		No Mapping
                                                  //| 0.0.2.1		pan		No Mapping
                                                  //| 0.1		date-entity		No Mapping
                                                  //| 0.1.0		2002		No Mapping
                                                  //| 0.1.1		1		No Mapping
                                                  //| 0.1.2		5		No Mapping
                                                  //| 0.2		first		No Mapping
                                                  //| 0.2.0		since		No Mapping
                                                  //| 0.2.0.0		attack-01		No Mapping
                                                  //| 0.2.0.0.0		country		No Mapping
                                                  //| 0.2.0.0.0.0		name		No Mapping
                                                  //| 0.2.0.0.0.0.0		"US"		No Mapping
                                                  //| 0.2.0.0.1		date-entity		No Mapping
                                                  //| 0.2.0.0.1.0		2001		No Mapping
                                                  //| 0.2.0.0.1.1		9		No Mapping
                                                                                               
                                                  
                                                  
}