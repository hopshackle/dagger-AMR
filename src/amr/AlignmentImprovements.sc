package amr
import edu.cmu.lti.nlp.amr._

object AlignmentImprovements {

  val (rawSentence, rawAMR) = AMRGraph.importFile("C:\\AMR\\Riyadh-based.txt")(0)
                                                  //> rawSentence  : String = The Riyadh-based Naif Arab Academy for Security Scie
                                                  //| nces said in a statement that it was running a two-week workshop for 50 anti
                                                  //| -terrorism experts.
                                                  //| rawAMR  : String = (s / say-01 :ARG0 (u / university :name (n / name :op1 "N
                                                  //| aif" :op2 "Arab" :op3 "Academy" :op4 "for" :op5 "Security" :op6 "Sciences") 
                                                  //| :ARG1-of (b / base-01 :location (c / city :name (n2 / name :op1 "Riyadh"))))
                                                  //|  :ARG1 (r / run-01 :ARG0 u :ARG1 (w / workshop :beneficiary (p / person :qua
                                                  //| nt 50 :ARG1-of (e / expert-41 :ARG2 (c2 / counter-01 :ARG1 (t2 / terrorism))
                                                  //| ))) :duration (t / temporal-quantity :quant 2 :unit (w2 / week))) :medium (s
                                                  //| 2 / statement))
// val (rawSentence, rawAMR) = AMRGraph.importFile("C:\\AMR\\CountryList.txt")(0)

  //    val tokenisedSentence = rawSentence.split(" ")
  val amr = Graph.parse(rawAMR)                   //> amr  : edu.cmu.lti.nlp.amr.Graph = Graph((say-01 :ARG0 (university :name (na
                                                  //| me :op1 "Naif" :op2 "Arab" :op3 "Academy" :op4 "for" :op5 "Security" :op6 "S
                                                  //| ciences") :ARG1-of (base-01 :location (city :name (name :op1 "Riyadh")))) :A
                                                  //| RG1 (run-01 :ARG1 (workshop :beneficiary (person :quant 50 :ARG1-of (expert-
                                                  //| 41 :ARG2 (counter-01 :ARG1 terrorism)))) :duration (temporal-quantity :quant
                                                  //|  2 :unit week) :ARG0 university) :medium statement),ArrayBuffer(),Map(0.0.0 
                                                  //| -> (name :op1 "Naif" :op2 "Arab" :op3 "Academy" :op4 "for" :op5 "Security" :
                                                  //| op6 "Sciences"), 0.0.0.3 -> "for", 0.1.1.0 -> 2, 0.0.0.0 -> "Naif", 0.0.1.0.
                                                  //| 0.0 -> "Riyadh", 0.0.1.0.0 -> (name :op1 "Riyadh"), 0.1.1 -> (temporal-quant
                                                  //| ity :quant 2 :unit week), 0.1 -> (run-01 :ARG1 (workshop :beneficiary (perso
                                                  //| n :quant 50 :ARG1-of (expert-41 :ARG2 (counter-01 :ARG1 terrorism)))) :durat
                                                  //| ion (temporal-quantity :quant 2 :unit week) :ARG0 university), 0.1.0.0.1 -> 
                                                  //| (expert-41 :ARG2 (counter-01 :ARG1 terrorism)), 0.1.0.0 -> (person :quant 50
                                                  //|  :ARG1-of (expert-41 :ARG2 (counter-01 :ARG1 terrorism))), 0.1.0.0.1.0.0 -> 
                                                  //| terrorism, 0.0.0.2 -> "Academy", 0.0.0.5 -> "Sciences", 0.1.0 -> (workshop :
                                                  //| beneficiary (person :quant 50 :ARG1-of (expert-41 :ARG2 (counter-01 :ARG1 te
                                                  //| rrorism)))), 0.0 -> (university :name (name :op1 "Naif" :op2 "Arab" :op3 "Ac
                                                  //| ademy" :op4 "for" :op5 "Security" :op6 "Sciences") :ARG1-of (base-01 :locati
                                                  //| on (city :name (name :op1 "Riyadh")))), 0.0.1 -> (base-01 :location (city :n
                                                  //| ame (name :op1 "Riyadh"))), 0.1.0.0.1.0 -> (counter-01 :ARG1 terrorism), 0.1
                                                  //| .0.0.0 -> 50, 0.0.0.4 -> "Security", 0.1.1.1 -> week, 0 -> (say-01 :ARG0 (un
                                                  //| iversity :name (name :op1 "Naif" :op2 "Arab" :op3 "Academy" :op4 "for" :op5 
                                                  //| "Security" :op6 "Sciences") :ARG1-of (base-01 :location (city :name (name :o
                                                  //| p1 "Riyadh")))) :ARG1 (run-01 :ARG1 (workshop :beneficiary (person :quant 50
                                                  //|  :ARG1-of (expert-41 :ARG2 (counter-01 :ARG1 terrorism)))) :duration (tempor
                                                  //| al-quantity :quant 2 :unit week) :ARG0 university) :medium statement), 0.0.1
                                                  //| .0 -> (city :name (name :op1 "Riyadh")), 0.0.0.1 -> "Arab", 0.2 -> statement
                                                  //| ),Map(e -> (expert-41 :ARG2 (counter-01 :ARG1 terrorism)), n -> (name :op1 "
                                                  //| Naif" :op2 "Arab" :op3 "Academy" :op4 "for" :op5 "Security" :op6 "Sciences")
                                                  //| , w -> (workshop :beneficiary (person :quant 50 :ARG1-of (expert-41 :ARG2 (c
                                                  //| ounter-01 :ARG1 terrorism)))), s2 -> statement, b -> (base-01 :loc
                                                  //| Output exceeds cutoff limit.
  val tokenisedSentence = DependencyTree.preProcess(rawSentence)
                                                  //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.1 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [4.2 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [3.2 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [2.5 sec].
                                                  //| tokenisedSentence  : List[String] = List(The, Riyadh-based, Naif, Arab, Acad
                                                  //| emy, for, Security, Sciences, said, in, a, statement, that, it, was, running
                                                  //| , a, two-week, workshop, for, 50, anti-terrorism, experts)
val wordAlignments = AlignTest.alignWords(tokenisedSentence.toArray, amr)
                                                  //> (23,List(0.1.0.0.1)) experts
                                                  //| (8,List(0.0.0.5)) Sciences
                                                  //| (20,List(0.0.0.3)) for
                                                  //| (5,List(0.0.0.2)) Academy
                                                  //| (4,List(0.0.0.1)) Arab
                                                  //| (16,List(0.1)) running
                                                  //| (7,List(0.0.0.4)) Security
                                                  //| (19,List(0.1.0)) workshop
                                                  //| (9,List(0)) said
                                                  //| (3,List(0.0.0.0)) Naif
                                                  //| (21,List(0.1.0.0.0)) 50
                                                  //| (12,List(0.2)) statement
                                                  //| (6,List(0.0.0.3)) for
                                                  //| (0.1,List(16)) run-01
                                                  //| (0.1.0,List(19)) workshop
                                                  //| (0.1.0.0.0,List(21)) 50
                                                  //| (0.0.0.3,List(20, 6)) "for"
                                                  //| (0.0.0.0,List(3)) "Naif"
                                                  //| (0.0.0.5,List(8)) "Sciences"
                                                  //| (0.1.0.0.1,List(23)) expert-41
                                                  //| (0,List(9)) say-01
                                                  //| (0.0.0.4,List(7)) "Security"
                                                  //| (0.0.0.1,List(4)) "Arab"
                                                  //| (0.2,List(12)) statement
                                                  //| (0.0.0.2,List(5)) "Academy"
                                                  //| java.lang.ArrayIndexOutOfBoundsException: 23
                                                  //| 	at amr.AlignTest$$anonfun$alignWords$3.apply$mcVI$sp(AlignTest.scala:116
                                                  //| )
                                                  //| 	at amr.AlignTest$$anonfun$alignWords$3.apply(AlignTest.scala:113)
                                                  //| 	at amr.AlignTest$$anonfun$alignWords$3.apply(AlignTest.scala:113)
                                                  //| 	at scala.collection.mutable.HashMap$$anon$1$$anonfun$foreach$2.apply(Has
                                                  //| hMap.scala:103)
                                                  //| 	at scala.collection.mutable.HashMap$$anon$1$$anonfun$foreach$2.apply(Has
                                                  //| hMap.scala:103)
                                                  //| 	at scala.collection.mutable.HashTable$class.foreachEntry(HashTable.scala
                                                  //| :230)
                                                  //| 	at scala.collection.mutable.HashMap.foreachEntry(HashMap.scala:40)
                                                  //| 	at scala.collection.mutable.HashMap$$anon$1.foreach(HashMap.scala:103)
                                                  //| 	at amr.AlignTest$.alignWords(AlignTest.scala:113)
                                                  //| 	at amr.AlignTest$.alignWords(AlignTest.scala:34)
                                                  //| 	at amr.AlignmentImprovements$$anonfun$main$1.apply$mcV$sp(amr.AlignmentI
                                                  //| mprovements.scala:12)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$$anonfun$$exe
                                                  //| cute$1.apply$mcV$sp(WorksheetSupport.scala:76)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.redirected(W
                                                  //| orksheetSupport.scala:65)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.$execute(Wor
                                                  //| ksheetSupport.scala:75)
                                                  //| 	at amr.AlignmentImprovements$.main(amr.AlignmentImprovements.scala:4)
                                                  //| 	at amr.AlignmentImprovements.main(amr.AlignmentImprovements.scala)
wordAlignments foreach println
AlignSpans.alignSpans(tokenisedSentence.toArray, amr, wordAlignments) foreach println
 val amrG =  AMRGraph(amr)
 amrG.nodes.keys.toList.sorted foreach (x => println(s"${x}\t\t${amrG.nodes(x)}\t\t${amrG.nodeSpans.getOrElse(x, "No Mapping")}"))

}