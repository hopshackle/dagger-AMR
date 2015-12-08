package amr
import edu.cmu.lti.nlp.amr._

object AlignmentImprovements {

// val (rawSentence, rawAMR) = AMRGraph.importFile("C:\\AMR\\Riyadh-based.txt")(0)
//  val (rawSentence, rawAMR) = AMRGraph.imprtFile("C:\\AMR\\CountryList.txt")(0)
//  val (rawSentence, rawAMR) = AMRGraphimportFile("C:\\AMR\\Lavrov.txt")(0)
  val (rawSentence, rawAMR) = AMRGraph.importFile("C:\\AMR\\VeniceTarget.txt")(0)
                                                  //> rawSentence  : String = Venice High School history teacher Craig Brandau
                                                  //| rawAMR  : String = (p / person :wiki - :name (n / name :op1 "Craig" :op2 "Br
                                                  //| andau") :ARG0-of (h2 / have-org-role-91 :ARG1 (s / school :wiki "Venice_High
                                                  //| _School_(Los_Angeles)" :name (n2 / name :op1 "Venice" :op2 "High" :op3 "Scho
                                                  //| ol")) :ARG3 (t / teach-01 :ARG0 p :ARG1 (h / history))))
// val (rawSentence, rawAMR) = AMRGraph.importFile("C:\\AMR\\simpleDate.txt")(0)
// val (rawSentence, rawAMR) = AMRGraph.importFile("C:\\AMR\\lobbying.tt")(0)

  //    val tokenisedSentence = rawSentence.split(" ")
  val amr = Graph.parse(rawAMR)                   //> amr  : edu.cmu.lti.nlp.amr.Graph = Graph((person :wiki - :name (name :op1 "C
                                                  //| raig" :op2 "Brandau") :ARG0-of (have-org-role-91 :ARG1 (school :wiki "Venice
                                                  //| _High_School_(Los_Angeles)" :name (name :op1 "Venice" :op2 "High" :op3 "Scho
                                                  //| ol")) :ARG3 (teach-01 :ARG1 history :ARG0 person))),ArrayBuffer(),Map(0.2.0.
                                                  //| 1.0 -> "Venice", 0.1 -> (name :op1 "Craig" :op2 "Brandau"), 0.1.1 -> "Branda
                                                  //| u", 0.2.0 -> (school :wiki "Venice_High_School_(Los_Angeles)" :name (name :o
                                                  //| p1 "Venice" :op2 "High" :op3 "School")), 0.2.0.1 -> (name :op1 "Venice" :op2
                                                  //|  "High" :op3 "School"), 0.2.1.0 -> history, 0.2.0.1.2 -> "School", 0.2.0.1.1
                                                  //|  -> "High", 0.0 -> -, 0.1.0 -> "Craig", 0.2.0.0 -> "Venice_High_School_(Los_
                                                  //| Angeles)", 0 -> (person :wiki - :name (name :op1 "Craig" :op2 "Brandau") :AR
                                                  //| G0-of (have-org-role-91 :ARG1 (school :wiki "Venice_High_School_(Los_Angeles
                                                  //| )" :name (name :op1 "Venice" :op2 "High" :op3 "School")) :ARG3 (teach-01 :AR
                                                  //| G1 history :ARG0 person))), 0.2 -> (have-org-role-91 :ARG1 (school :wiki "Ve
                                                  //| nice_High_School_(Los_Angeles)" :name (name :op1 "Venice" :op2 "High" :op3 "
                                                  //| School")) :ARG3 (teach-01 :ARG1 history :ARG0 person)), 0.2.1 -> (teach-01 :
                                                  //| ARG1 history :ARG0 person)),Map(n -> (name :op1 "Craig" :op2 "Brandau"), h -
                                                  //| > history, t -> (teach-01 :ARG1 history :ARG0 person), h2 -> (have-org-role-
                                                  //| 91 :ARG1 (school :wiki "Venice_High_School_(Los_Angeles)" :name (name :op1 "
                                                  //| Venice" :op2 "High" :op3 "School")) :ARG3 (teach-01 :ARG1 history :ARG0 pers
                                                  //| on)), s -> (school :wiki "Venice_High_School_(Los_Angeles)" :name (name :op1
                                                  //|  "Venice" :op2 "High" :op3 "School")), p -> (person :wiki - :name (name :op1
                                                  //|  "Craig" :op2 "Brandau") :ARG0-of (have-org-role-91 :ARG1 (school :wiki "Ven
                                                  //| ice_High_School_(Los_Angeles)" :name (name :op1 "Venice" :op2 "High" :op3 "S
                                                  //| chool")) :ARG3 (teach-01 :ARG1 history :ARG0 person))), n2 -> (name :op1 "Ve
                                                  //| nice" :op2 "High" :op3 "School")))
                                                  // ee-01 :ARG0 (person :ARG0-of (have-org-role-91 :ARG1 (organization :name (na

   val amrGraph = AlignTest.alignWords(rawSentence, amr, false)
                                                  //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.3 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [5.2 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [2.2 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [4.7 sec].
                                                  //| amrGraph  : amr.AMRGraph = AMRGraph(Map(0.1 -> name, 0.1.0 -> "Craig", 0.2.0
                                                  //| .1.2 -> "School", 0.2.1 -> teach-01, 0.0 -> -, 0.2.0.1.1 -> "High", 0.2.0.1 
                                                  //| -> name, 0.2.0 -> school, 0.2.0.1.0 -> "Venice", 0 -> person, 0.2 -> have-or
                                                  //| g-role-91, 0.1.1 -> "Brandau", 0.2.0.0 -> "Venice_High_School_(Los_Angeles)"
                                                  //| , 0.2.1.0 -> history),Map(0.1.0 -> (6,7), 0.2.1 -> (5,6), 0.2.0.1.1 -> (2,3)
                                                  //| , 0.2.0 -> (3,4), 0.2.0.1.0 -> (1,2), 0.1.1 -> (7,8), 0.2.1.0 -> (4,5)),Map(
                                                  //| (0.2.0.1,0.2.0.1.1) -> opN, (0.1,0.1.0) -> opN, (0.2.1,0) -> ARG0, (0,0.2) -
                                                  //| > ARG0-of, (0,0.1) -> name, (0.2,0.2.0) -> ARG1, (0.2.0.1,0.2.0.1.0) -> opN,
                                                  //|  (0.2.1,0.2.1.0) -> ARG1, (0.2,0.2.1) -> ARG3, (0,0.0) -> wiki, (0.2.0.1,0.2
                                                  //| .0.1.2) -> opN, (0.2.0,0.2.0.0) -> wiki, (0.2.0,0.2.0.1) -> name, (0.1,0.1.1
                                                  //| ) -> opN),Map((0.2.0.1,0.2.0.1.1) -> op2, (0.1,0.1.0) -> op1, (0.2.1,0) -> A
                                                  //| RG0, (0,0.2) -> ARG0-of, (0,0.1) -> name, (0.2,0.2.0) -> ARG1, (0.2.0.1,0.2.
                                                  //| 0.1.0) -> op1, (0.2.1,0.2.1.0) -> ARG1, (0.2,0.2.1) -> ARG3, (0,0.0) -> wiki
                                                  //| , (0.2.0.1,0.2.0.1.2) -> op3, (0.2.0,0.2.0.0) -> wiki, (0.2.0,0.2.0.1) -> na
                                                  //| me, (0.1,0.1.1) -> op2),List())
    val tokenisedSentence = DependencyTree.preProcess(rawSentence)
                                                  //> tokenisedSentence  : List[String] = List(Venice, High, School, history, teac
                                                  //| her, Craig, Brandau)
  
  val wordAlignments = AlignWords.alignWords(tokenisedSentence.toArray, amr)
                                                  //> wordAlignments  : Array[Option[edu.cmu.lti.nlp.amr.Node]] = Array(Some("Veni
                                                  //| ce"), Some("High"), Some((school :wiki "Venice_High_School_(Los_Angeles)" :n
                                                  //| ame (name :op1 "Venice" :op2 "High" :op3 "School"))), Some(history), Some((t
                                                  //| each-01 :ARG1 history :ARG0 person)), Some("Craig"), Some("Brandau"))
 amrGraph.nodes foreach {case (k, v) => println(k + ":" + v + " -> " + amrGraph.nodeSpans.getOrElse(k, (-1, -1)._1))}
                                                  //> 0.1:name -> -1
                                                  //| 0.1.0:"Craig" -> (6,7)
                                                  //| 0.2.0.1.2:"School" -> -1
                                                  //| 0.2.1:teach-01 -> (5,6)
                                                  //| 0.0:- -> -1
                                                  //| 0.2.0.1.1:"High" -> (2,3)
                                                  //| 0.2.0.1:name -> -1
                                                  //| 0.2.0:school -> (3,4)
                                                  //| 0.2.0.1.0:"Venice" -> (1,2)
                                                  //| 0:person -> -1
                                                  //| 0.2:have-org-role-91 -> -1
                                                  //| 0.1.1:"Brandau" -> (7,8)
                                                  //| 0.2.0.0:"Venice_High_School_(Los_Angeles)" -> -1
                                                  //| 0.2.1.0:history -> (4,5)
                                               
                                            
 wordAlignments foreach println                   //> Some("Venice")
                                                  //| Some("High")
                                                  //| Some((school :wiki "Venice_High_School_(Los_Angeles)" :name (name :op1 "Ven
                                                  //| ice" :op2 "High" :op3 "School")))
                                                  //| Some(history)
                                                  //| Some((teach-01 :ARG1 history :ARG0 person))
                                                  //| Some("Craig")
                                                  //| Some("Brandau")
                                            
  AlignSpans.alignSpans(tokenisedSentence.toArray, amr, wordAlignments) zip wordAlignments foreach println
                                                  //> (Some(2),Some("Venice"))
                                                  //| (Some(2),Some("High"))
                                                  //| (Some(1),Some((school :wiki "Venice_High_School_(Los_Angeles)" :name (name 
                                                  //| :op1 "Venice" :op2 "High" :op3 "School"))))
                                                  //| (Some(4),Some(history))
                                                  //| (Some(3),Some((teach-01 :ARG1 history :ARG0 person)))
                                                  //| (Some(0),Some("Craig"))
                                                  //| (Some(0),Some("Brandau"))
 val amrG =  AMRGraph(amr)                        //> amrG  : amr.AMRGraph = AMRGraph(Map(0.1 -> name, 0.1.0 -> "Craig", 0.2.0.1.
                                                  //| 2 -> "School", 0.2.1 -> teach-01, 0.0 -> -, 0.2.0.1.1 -> "High", 0.2.0.1 ->
                                                  //|  name, 0.2.0 -> school, 0.2.0.1.0 -> "Venice", 0 -> person, 0.2 -> have-org
                                                  //| -role-91, 0.1.1 -> "Brandau", 0.2.0.0 -> "Venice_High_School_(Los_Angeles)"
                                                  //| , 0.2.1.0 -> history),Map(0.1 -> (6,8), 0.1.0 -> (6,8), 0.2.1 -> (5,6), 0.2
                                                  //| .0.1.1 -> (1,3), 0.2.0.1 -> (1,3), 0.2.0 -> (3,4), 0.2.0.1.0 -> (1,3), 0 ->
                                                  //|  (6,8), 0.1.1 -> (6,8), 0.2.1.0 -> (4,5)),Map((0.2.0.1,0.2.0.1.1) -> opN, (
                                                  //| 0.1,0.1.0) -> opN, (0.2.1,0) -> ARG0, (0,0.2) -> ARG0-of, (0,0.1) -> name, 
                                                  //| (0.2,0.2.0) -> ARG1, (0.2.0.1,0.2.0.1.0) -> opN, (0.2.1,0.2.1.0) -> ARG1, (
                                                  //| 0.2,0.2.1) -> ARG3, (0,0.0) -> wiki, (0.2.0.1,0.2.0.1.2) -> opN, (0.2.0,0.2
                                                  //| .0.0) -> wiki, (0.2.0,0.2.0.1) -> name, (0.1,0.1.1) -> opN),Map((0.2.0.1,0.
                                                  //| 2.0.1.1) -> op2, (0.1,0.1.0) -> op1, (0.2.1,0) -> ARG0, (0,0.2) -> ARG0-of,
                                                  //|  (0,0.1) -> name, (0.2,0.2.0) -> ARG1, (0.2.0.1,0.2.0.1.0) -> op1, (0.2.1,0
                                                  //| .2.1.0) -> ARG1, (0.2,0.2.1) -> ARG3, (0,0.0) -> wiki, (0.2.0.1,0.2.0.1.2) 
                                                  //| -> op3, (0.2.0,0.2.0.0) -> wiki, (0.2.0,0.2.0.1) -> name, (0.1,0.1.1) -> op
                                                  //| 2),List())
 amrG.nodes.keys.toList.sorted foreach (x => println(s"${x}\t\t${amrG.nodes(x)}\t\t${amrG.nodeSpans.getOrElse(x, "No Mapping")}"))
                                                  //> 0		person		(6,8)
                                                  //| 0.0		-		No Mapping
                                                  //| 0.1		name		(6,8)
                                                  //| 0.1.0		"Craig"		(6,8)
                                                  //| 0.1.1		"Brandau"		(6,8)
                                                  //| 0.2		have-org-role-91		No Mapping
                                                  //| 0.2.0		school		(3,4)
                                                  //| 0.2.0.0		"Venice_High_School_(Los_Angeles)"		No Mappi
                                                  //| ng
                                                  //| 0.2.0.1		name		(1,3)
                                                  //| 0.2.0.1.0		"Venice"		(1,3)
                                                  //| 0.2.0.1.1		"High"		(1,3)
                                                  //| 0.2.0.1.2		"School"		No Mapping
                                                  //| 0.2.1		teach-01		(5,6)
                                                  //| 0.2.1.0		history		(4,5)
                                                                                               
                                                  
                                                  
}