package amr
import edu.cmu.lti.nlp.amr._

object AlignmentImprovements {

// val (rawSentence, rawAMR) = AMRGraph.importFile("C:\\AMR\\Riyadh-based.txt")(0)
 // val (rawSentence, rawAMR) = AMRGraph.importFile("C:\\AMR\\CountryList.txt")(0)
//  val (rawSentence, rawAMR) = AMRGraph.importFile("C:\\AMR\\Lavrov.txt")(0)
  val (rawSentence, rawAMR) = AMRGraph.importFile("C:\\AMR\\Medvedev.txt")(0)
                                                  //> rawSentence  : String = Russian Federation President Dmitry Medvedev promise
                                                  //| d on May 15, 2008 to provide funding for Russia's nuclear missile program.
                                                  //| rawAMR  : String = (p2 / promise-01 :ARG0 (p / person :name (n2 / name :op1 
                                                  //| "Dmitry" :op2 "Medvedev") :ARG0-of (h / have-org-role-91 :ARG1 (c / country 
                                                  //| :name (n / name :op1 "Russian" :op2 "Federation")) :ARG2 (p3 / president))) 
                                                  //| :ARG1 (p4 / provide-01 :ARG0 p :ARG1 (f / fund-01 :ARG0 p :ARG1 (p5 / progra
                                                  //| m :mod (m / missile :mod (n3 / nucleus)) :poss c))) :time (d / date-entity :
                                                  //| month 5 :day 15 :year 2008))
// val (rawSentence, rawAMR) = AMRGraph.importFile("C:\\AMR\\simpleDate.txt")(0)
// val (rawSentence, rawAMR) = AMRGraph.importFile("C:\\AMR\\lobbying.txt")(0)

  //    val tokenisedSentence = rawSentence.split(" ")
  val amr = Graph.parse(rawAMR)                   //> amr  : edu.cmu.lti.nlp.amr.Graph = Graph((promise-01 :ARG0 (person :name (na
                                                  //| me :op1 "Dmitry" :op2 "Medvedev") :ARG0-of (have-org-role-91 :ARG1 (country 
                                                  //| :name (name :op1 "Russian" :op2 "Federation")) :ARG2 president)) :ARG1 (prov
                                                  //| ide-01 :ARG1 (fund-01 :ARG1 (program :mod (missile :mod nucleus) :poss count
                                                  //| ry) :ARG0 person) :ARG0 person) :time (date-entity :month 5 :day 15 :year 20
                                                  //| 08)),ArrayBuffer(),Map(0.0.0 -> (name :op1 "Dmitry" :op2 "Medvedev"), 0.1.0.
                                                  //| 0.0.0 -> nucleus, 0.0.0.0 -> "Dmitry", 0.0.1.0.0.0 -> "Russian", 0.0.1.0.0 -
                                                  //| > (name :op1 "Russian" :op2 "Federation"), 0.2.0 -> 5, 0.1 -> (provide-01 :A
                                                  //| RG1 (fund-01 :ARG1 (program :mod (missile :mod nucleus) :poss country) :ARG0
                                                  //|  person) :ARG0 person), 0.1.0.0 -> (program :mod (missile :mod nucleus) :pos
                                                  //| s country), 0.0.1.1 -> president, 0.0 -> (person :name (name :op1 "Dmitry" :
                                                  //| op2 "Medvedev") :ARG0-of (have-org-role-91 :ARG1 (country :name (name :op1 "
                                                  //| Russian" :op2 "Federation")) :ARG2 president)), 0.1.0 -> (fund-01 :ARG1 (pro
                                                  //| gram :mod (missile :mod nucleus) :poss country) :ARG0 person), 0.0.1 -> (hav
                                                  //| e-org-role-91 :ARG1 (country :name (name :op1 "Russian" :op2 "Federation")) 
                                                  //| :ARG2 president), 0.1.0.0.0 -> (missile :mod nucleus), 0.2.2 -> 2008, 0 -> (
                                                  //| promise-01 :ARG0 (person :name (name :op1 "Dmitry" :op2 "Medvedev") :ARG0-of
                                                  //|  (have-org-role-91 :ARG1 (country :name (name :op1 "Russian" :op2 "Federatio
                                                  //| n")) :ARG2 president)) :ARG1 (provide-01 :ARG1 (fund-01 :ARG1 (program :mod 
                                                  //| (missile :mod nucleus) :poss country) :ARG0 person) :ARG0 person) :time (dat
                                                  //| e-entity :month 5 :day 15 :year 2008)), 0.0.1.0 -> (country :name (name :op1
                                                  //|  "Russian" :op2 "Federation")), 0.0.0.1 -> "Medvedev", 0.0.1.0.0.1 -> "Feder
                                                  //| ation", 0.2.1 -> 15, 0.2 -> (date-entity :month 5 :day 15 :year 2008)),Map(n
                                                  //|  -> (name :op1 "Russian" :op2 "Federation"), h -> (have-org-role-91 :ARG1 (c
                                                  //| ountry :name (name :op1 "Russian" :op2 "Federation")) :ARG2 president), p5 -
                                                  //| > (program :mod (missile :mod nucleus) :poss country), p2 -> (promise-01 :AR
                                                  //| G0 (person :name (name :op1 "Dmitry" :op2 "Medvedev") :ARG0-of (have-org-rol
                                                  //| e-91 :ARG1 (country :name (name :op1 "Russian" :op2 "Federation")) :ARG2 pre
                                                  //| sident)) :ARG1 (provide-01 :ARG1 (fund-01 :ARG1 (program :mod (missile :mod 
                                                  //| nucleus) :poss country) :ARG0 person) :ARG0 person) :time (date-entity :mont
                                                  //| h 5 :day 15 :year 2008)), m -> (missile :mod nucleus), d -> (date-
                                                  //| Output exceeds cutoff limit.
  val tokenisedSentence = DependencyTree.preProcess(rawSentence)
                                                  //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.0 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [4.2 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [2.1 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [3.8 sec].
                                                  //| tokenisedSentence  : List[String] = List(Russian, Federation, President, Dmi
                                                  //| try, Medvedev, promised, on, May, 15, 2008, to, provide, funding, for, Russi
                                                  //| a, 's, nuclear, missile, program)
val wordAlignments = AlignTest.alignWords(tokenisedSentence.toArray, amr)
                                                  //> Similarity between fund-01 and funding is 202.00 (topological = 2.00; jaro =
                                                  //|  101.00
                                                  //| Similarity between "Federation" and Federation is 102.00 (topological = 2.00
                                                  //| ; jaro = 51.00
                                                  //| Similarity between 15 and 15 is 102.00 (topological = 2.00; jaro = 51.00
                                                  //| Similarity between 5 and 5 is 102.00 (topological = 2.00; jaro = 51.00
                                                  //| Similarity between "Medvedev" and Medvedev is 102.00 (topological = 2.00; ja
                                                  //| ro = 51.00
                                                  //| Similarity between 2008 and 2008 is 102.00 (topological = 2.00; jaro = 51.00
                                                  //| 
                                                  //| Similarity between provide-01 and provide is 102.00 (topological = 2.00; jar
                                                  //| o = 51.00
                                                  //| Similarity between promise-01 and promised is 102.00 (topological = 2.00; ja
                                                  //| ro = 51.00
                                                  //| Similarity between "Dmitry" and Dmitry is 102.00 (topological = 2.00; jaro =
                                                  //|  51.00
                                                  //| Similarity between "Russian" and Russian is 102.00 (topological = 2.00; jaro
                                                  //|  = 51.00
                                                  //| Similarity between program and program is 52.00 (topological = 2.00; jaro = 
                                                  //| 26.00
                                                  //| Similarity between president and President is 52.00 (topological = 2.00; jar
                                                  //| o = 26.00
                                                  //| Similarity between missile and missile is 52.00 (topological = 2.00; jaro = 
                                                  //| 26.00
                                                  //| Similarity between nucleus and nuclear is 12.61 (topological = 2.00; jaro = 
                                                  //| 6.31
                                                  //| Similarity between fund-01 and funding is 1111.00 (topological = 11.00; jaro
                                                  //|  = 101.00
                                                  //| Similarity between provide-01 and provide is 561.00 (topological = 11.00; ja
                                                  //| ro = 51.00
                                                  //| Similarity between 15 and 15 is 306.00 (topological = 6.00; jaro = 51.00
                                                  //| Similarity between "Dmitry" and Dmitry is 306.00 (topological = 6.00; jaro =
                                                  //|  51.00
                                                  //| Similarity between 5 and 5 is 306.00 (topological = 6.00; jaro = 51.00
                                                  //| Similarity between promise-01 and promised is 306.00 (topological = 6.00; ja
                                                  //| ro = 51.00
                                                  //| Similarity between 2008 and 2008 is 306.00 (topological = 6.00; jaro = 51.00
                                                  //| 
                                                  //| Similarity between missile and missile is 286.00 (topological = 11.00; jaro 
                                                  //| = 26.00
                                                  //| Similarity between program and program is 199.33 (topological = 7.67; jaro =
                                                  //|  26.00
                                                  //| Similarity between "Medvedev" and Medvedev is 172.43 (topological = 3.38; ja
                                                  //| ro = 51.00
                                                  //| Similarity between "Federation" and Federation is 136.00 (topological = 2.67
                                                  //| ; jaro = 51.00
                                                  //| Similarity between "Russian" and Russian is 136.00 (topological = 2.67; jaro
                                                  //|  = 51.00
                                                  //| Similarity between president and President is 91.00 (topological = 3.50; jar
                                                  //| o = 26.00
                                                  //| Similarity 
                                                  //| Output exceeds cutoff limit.
wordAlignments foreach println                    //> Some("Russian")
                                                  //| Some("Federation")
                                                  //| Some(president)
                                                  //| Some("Dmitry")
                                                  //| Some("Medvedev")
                                                  //| Some((promise-01 :ARG0 (person :name (name :op1 "Dmitry" :op2 "Medvedev") :A
                                                  //| RG0-of (have-org-role-91 :ARG1 (country :name (name :op1 "Russian" :op2 "Fed
                                                  //| eration")) :ARG2 president)) :ARG1 (provide-01 :ARG1 (fund-01 :ARG1 (program
                                                  //|  :mod (missile :mod nucleus) :poss country) :ARG0 person) :ARG0 person) :tim
                                                  //| e (date-entity :month 5 :day 15 :year 2008)))
                                                  //| None
                                                  //| Some(5)
                                                  //| Some(15)
                                                  //| Some(2008)
                                                  //| None
                                                  //| Some((provide-01 :ARG1 (fund-01 :ARG1 (program :mod (missile :mod nucleus) :
                                                  //| poss country) :ARG0 person) :ARG0 person))
                                                  //| Some((fund-01 :ARG1 (program :mod (missile :mod nucleus) :poss country) :ARG
                                                  //| 0 person))
                                                  //| None
                                                  //| None
                                                  //| None
                                                  //| Some(nucleus)
                                                  //| Some((missile :mod nucleus))
                                                  //| Some((program :mod (missile :mod nucleus) :poss country))
AlignSpans.alignSpans(tokenisedSentence.toArray, amr, wordAlignments) foreach println
                                                  //> Some(2)
                                                  //| Some(2)
                                                  //| Some(3)
                                                  //| Some(1)
                                                  //| Some(1)
                                                  //| Some(0)
                                                  //| None
                                                  //| Some(9)
                                                  //| Some(9)
                                                  //| Some(9)
                                                  //| None
                                                  //| Some(4)
                                                  //| Some(5)
                                                  //| None
                                                  //| None
                                                  //| None
                                                  //| Some(8)
                                                  //| Some(7)
                                                  //| Some(6)
 val amrG =  AMRGraph(amr)                        //> amrG  : amr.AMRGraph = AMRGraph(Map(0.1 -> provide-01, 0.0.1.0.0.0 -> "Russi
                                                  //| an", 0.0.0 -> name, 0.1.0 -> fund-01, 0.0.1.0 -> country, 0.2.1 -> 15, 0.1.0
                                                  //| .0.0 -> missile, 0.0 -> person, 0.0.0.0 -> "Dmitry", 0.1.0.0.0.0 -> nucleus,
                                                  //|  0.2.0 -> 5, 0.0.1.1 -> president, 0.0.1 -> have-org-role-91, 0.1.0.0 -> pro
                                                  //| gram, 0.0.1.0.0 -> name, 0 -> promise-01, 0.0.0.1 -> "Medvedev", 0.2 -> date
                                                  //| -entity, 0.0.1.0.0.1 -> "Federation", 0.2.2 -> 2008, ROOT -> ROOT),Map(0.1 -
                                                  //| > (12,13), 0.0.1.0.0.0 -> (1,3), 0.0.0 -> (4,6), 0.1.0 -> (13,14), 0.0.1.0 -
                                                  //| > (1,3), 0.2.1 -> (8,11), 0.1.0.0.0 -> (18,19), 0.0 -> (4,6), 0.0.0.0 -> (4,
                                                  //| 6), 0.1.0.0.0.0 -> (17,18), 0.2.0 -> (8,11), 0.0.1.1 -> (3,4), 0.1.0.0 -> (1
                                                  //| 9,20), 0.0.1.0.0 -> (1,3), 0 -> (6,7), 0.0.0.1 -> (4,6), 0.2 -> (8,11), 0.0.
                                                  //| 1.0.0.1 -> (1,3), 0.2.2 -> (8,11)),Map((0.0,0.0.0) -> name, (ROOT,0) -> ROOT
                                                  //| , (0.0.1.0.0,0.0.1.0.0.0) -> opN, (0.1.0.0,0.0.1.0) -> poss, (0.0.0,0.0.0.0)
                                                  //|  -> opN, (0.1,0.1.0) -> ARG1, (0.1.0.0.0,0.1.0.0.0.0) -> mod, (0.0.1.0.0,0.0
                                                  //| .1.0.0.1) -> opN, (0.1.0,0.1.0.0) -> ARG1, (0,0.2) -> time, (0,0.1) -> ARG1,
                                                  //|  (0.2,0.2.0) -> month, (0.0.0,0.0.0.1) -> opN, (0.0.1,0.0.1.1) -> ARG2, (0.1
                                                  //| .0.0,0.1.0.0.0) -> mod, (0.2,0.2.1) -> day, (0.1,0.0) -> ARG0, (0,0.0) -> AR
                                                  //| G0, (0.0.1,0.0.1.0) -> ARG1, (0.1.0,0.0) -> ARG0, (0.0.1.0,0.0.1.0.0) -> nam
                                                  //| e, (0.0,0.0.1) -> ARG0-of, (0.2,0.2.2) -> year))
 amrG.nodes.keys.toList.sorted foreach (x => println(s"${x}\t\t${amrG.nodes(x)}\t\t${amrG.nodeSpans.getOrElse(x, "No Mapping")}"))
                                                  //> 0		promise-01		(6,7)
                                                  //| 0.0		person		(4,6)
                                                  //| 0.0.0		name		(4,6)
                                                  //| 0.0.0.0		"Dmitry"		(4,6)
                                                  //| 0.0.0.1		"Medvedev"		(4,6)
                                                  //| 0.0.1		have-org-role-91		No Mapping
                                                  //| 0.0.1.0		country		(1,3)
                                                  //| 0.0.1.0.0		name		(1,3)
                                                  //| 0.0.1.0.0.0		"Russian"		(1,3)
                                                  //| 0.0.1.0.0.1		"Federation"		(1,3)
                                                  //| 0.0.1.1		president		(3,4)
                                                  //| 0.1		provide-01		(12,13)
                                                  //| 0.1.0		fund-01		(13,14)
                                                  //| 0.1.0.0		program		(19,20)
                                                  //| 0.1.0.0.0		missile		(18,19)
                                                  //| 0.1.0.0.0.0		nucleus		(17,18)
                                                  //| 0.2		date-entity		(8,11)
                                                  //| 0.2.0		5		(8,11)
                                                  //| 0.2.1		15		(8,11)
                                                  //| 0.2.2		2008		(8,11)
                                                  //| ROOT		ROOT		No Mapping

}