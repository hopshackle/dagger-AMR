package amr
import edu.cmu.lti.nlp.amr._

object AlignmentImprovements {

// val (rawSentence, rawAMR) = AMRGraph.importFile("C:\\AMR\\Riyadh-based.txt")(0)
//  val (rawSentence, rawAMR) = AMRGraph.importFile("C:\\AMR\\CountryList.txt")(0)
//  val (rawSentence, rawAMR) = AMRGraph.importFile("C:\\AMR\\Lavrov.txt")(0)
  val (rawSentence, rawAMR) = AMRGraph.importFile("C:\\AMR\\Naif.txt")(0)
                                                  //> rawSentence  : String = Naif Arab Academy for Security Sciences is run by an
                                                  //|  Arab Interior Ministers' Council.
                                                  //| rawAMR  : String = (r / run-01 :ARG0 (o / organization :name (n / name :op1 
                                                  //| "Arab" :op2 "Interior" :op3 "Ministers'" :op4 "Council")) :ARG1 (u / univers
                                                  //| ity :name (n2 / name :op1 "Naif" :op2 "Arab" :op3 "Academy" :op4 "for" :op5 
                                                  //| "Security" :op6 "Sciences")))
// val (rawSentence, rawAMR) = AMRGraph.importFile("C:\\AMR\\simpleDate.txt")(0)
// val (rawSentence, rawAMR) = AMRGraph.importFile("C:\\AMR\\lobbying.txt")(0)

  //    val tokenisedSentence = rawSentence.split(" ")
  val amr = Graph.parse(rawAMR)                   //> amr  : edu.cmu.lti.nlp.amr.Graph = Graph((run-01 :ARG0 (organization :name (
                                                  //| name :op1 "Arab" :op2 "Interior" :op3 "Ministers'" :op4 "Council")) :ARG1 (u
                                                  //| niversity :name (name :op1 "Naif" :op2 "Arab" :op3 "Academy" :op4 "for" :op5
                                                  //|  "Security" :op6 "Sciences"))),ArrayBuffer(),Map(0.1.0.4 -> "Security", 0.0.
                                                  //| 0 -> (name :op1 "Arab" :op2 "Interior" :op3 "Ministers'" :op4 "Council"), 0.
                                                  //| 0.0.3 -> "Council", 0.1.0.1 -> "Arab", 0.0.0.0 -> "Arab", 0.1 -> (university
                                                  //|  :name (name :op1 "Naif" :op2 "Arab" :op3 "Academy" :op4 "for" :op5 "Securit
                                                  //| y" :op6 "Sciences")), 0.1.0.0 -> "Naif", 0.1.0.3 -> "for", 0.0.0.2 -> "Minis
                                                  //| ters'", 0.1.0.5 -> "Sciences", 0.0 -> (organization :name (name :op1 "Arab" 
                                                  //| :op2 "Interior" :op3 "Ministers'" :op4 "Council")), 0.1.0 -> (name :op1 "Nai
                                                  //| f" :op2 "Arab" :op3 "Academy" :op4 "for" :op5 "Security" :op6 "Sciences"), 0
                                                  //|  -> (run-01 :ARG0 (organization :name (name :op1 "Arab" :op2 "Interior" :op3
                                                  //|  "Ministers'" :op4 "Council")) :ARG1 (university :name (name :op1 "Naif" :op
                                                  //| 2 "Arab" :op3 "Academy" :op4 "for" :op5 "Security" :op6 "Sciences"))), 0.1.0
                                                  //| .2 -> "Academy", 0.0.0.1 -> "Interior"),Map(n -> (name :op1 "Arab" :op2 "Int
                                                  //| erior" :op3 "Ministers'" :op4 "Council"), r -> (run-01 :ARG0 (organization :
                                                  //| name (name :op1 "Arab" :op2 "Interior" :op3 "Ministers'" :op4 "Council")) :A
                                                  //| RG1 (university :name (name :op1 "Naif" :op2 "Arab" :op3 "Academy" :op4 "for
                                                  //| " :op5 "Security" :op6 "Sciences"))), u -> (university :name (name :op1 "Nai
                                                  //| f" :op2 "Arab" :op3 "Academy" :op4 "for" :op5 "Security" :op6 "Sciences")), 
                                                  //| o -> (organization :name (name :op1 "Arab" :op2 "Interior" :op3 "Ministers'"
                                                  //|  :op4 "Council")), n2 -> (name :op1 "Naif" :op2 "Arab" :op3 "Academy" :op4 "
                                                  //| for" :op5 "Security" :op6 "Sciences")))
  val tokenisedSentence = DependencyTree.preProcess(rawSentence)
                                                  //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.3 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [5.4 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [2.1 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [5.2 sec].
                                                  //| tokenisedSentence  : List[String] = List(Naif, Arab, Academy, for, Security,
                                                  //|  Sciences, is, run, by, an, Arab, Interior, Ministers, ', Council)
val wordAlignments = AlignWords.alignWords(tokenisedSentence.toArray, amr)
                                                  //> WARNING: Found duplicate match for concept "Arab"
                                                  //| wordAlignments  : Array[Option[edu.cmu.lti.nlp.amr.Node]] = Array(Some("Naif
                                                  //| "), Some("Arab"), Some("Academy"), Some("for"), Some("Security"), Some("Scie
                                                  //| nces"), None, Some((run-01 :ARG0 (organization :name (name :op1 "Arab" :op2 
                                                  //| "Interior" :op3 "Ministers'" :op4 "Council")) :ARG1 (university :name (name 
                                                  //| :op1 "Naif" :op2 "Arab" :op3 "Academy" :op4 "for" :op5 "Security" :op6 "Scie
                                                  //| nces")))), None, None, Some("Arab"), Some("Interior"), Some("Ministers'"), N
                                                  //| one, Some("Council"))
wordAlignments foreach println                    //> Some("Naif")
                                                  //| Some("Arab")
                                                  //| Some("Academy")
                                                  //| Some("for")
                                                  //| Some("Security")
                                                  //| Some("Sciences")
                                                  //| None
                                                  //| Some((run-01 :ARG0 (organization :name (name :op1 "Arab" :op2 "Interior" :op
                                                  //| 3 "Ministers'" :op4 "Council")) :ARG1 (university :name (name :op1 "Naif" :o
                                                  //| p2 "Arab" :op3 "Academy" :op4 "for" :op5 "Security" :op6 "Sciences"))))
                                                  //| None
                                                  //| None
                                                  //| Some("Arab")
                                                  //| Some("Interior")
                                                  //| Some("Ministers'")
                                                  //| None
                                                  //| Some("Council")
AlignSpans.alignSpans(tokenisedSentence.toArray, amr, wordAlignments) foreach println
                                                  //> Some(2)
                                                  //| Some(1)
                                                  //| Some(2)
                                                  //| Some(2)
                                                  //| Some(2)
                                                  //| Some(2)
                                                  //| None
                                                  //| Some(0)
                                                  //| None
                                                  //| None
                                                  //| Some(2)
                                                  //| Some(1)
                                                  //| Some(1)
                                                  //| None
                                                  //| Some(1)
 val amrG =  AMRGraph(amr)                        //> amrG  : amr.AMRGraph = AMRGraph(Map(0.1 -> university, 0.0.0 -> name, 0.1.0 
                                                  //| -> name, 0.1.0.5 -> "Sciences", 0.1.0.1 -> "Arab", 0.0.0.3 -> "Council", 0.0
                                                  //|  -> organization, 0.0.0.0 -> "Arab", 0.1.0.2 -> "Academy", 0.1.0.0 -> "Naif"
                                                  //| , 0 -> run-01, 0.0.0.1 -> "Interior", 0.1.0.3 -> "for", 0.0.0.2 -> "Minister
                                                  //| s'", 0.1.0.4 -> "Security"),Map(0 -> (8,9), 0.0 -> (16,1), 0.1 -> (16,1)),Ma
                                                  //| p((0.0,0.0.0) -> name, (0.0.0,0.0.0.0) -> opN, (0.1,0.1.0) -> name, (0.1.0,0
                                                  //| .1.0.0) -> opN, (0,0.1) -> ARG1, (0.1.0,0.1.0.1) -> opN, (0.0.0,0.0.0.1) -> 
                                                  //| opN, (0.1.0,0.1.0.2) -> opN, (0.1.0,0.1.0.5) -> opN, (0.0.0,0.0.0.2) -> opN,
                                                  //|  (0.0.0,0.0.0.3) -> opN, (0.1.0,0.1.0.4) -> opN, (0.1.0,0.1.0.3) -> opN, (0,
                                                  //| 0.0) -> ARG0),List())
 amrG.nodes.keys.toList.sorted foreach (x => println(s"${x}\t\t${amrG.nodes(x)}\t\t${amrG.nodeSpans.getOrElse(x, "No Mapping")}"))
                                                  //> 0		run-01		(8,9)
                                                  //| 0.0		organization		(16,1)
                                                  //| 0.0.0		name		No Mapping
                                                  //| 0.0.0.0		"Arab"		No Mapping
                                                  //| 0.0.0.1		"Interior"		No Mapping
                                                  //| 0.0.0.2		"Ministers'"		No Mapping
                                                  //| 0.0.0.3		"Council"		No Mapping
                                                  //| 0.1		university		(16,1)
                                                  //| 0.1.0		name		No Mapping
                                                  //| 0.1.0.0		"Naif"		No Mapping
                                                  //| 0.1.0.1		"Arab"		No Mapping
                                                  //| 0.1.0.2		"Academy"		No Mapping
                                                  //| 0.1.0.3		"for"		No Mapping
                                                  //| 0.1.0.4		"Security"		No Mapping
                                                  //| 0.1.0.5		"Sciences"		No Mapping

}