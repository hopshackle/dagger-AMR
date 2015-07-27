package amr
import edu.cmu.lti.nlp.amr._

object AlignmentImprovements {

// val (rawSentence, rawAMR) = AMRGraph.importFile("C:\\AMR\\Riyadh-based.txt")(0)
 val (rawSentence, rawAMR) = AMRGraph.importFile("C:\\AMR\\CountryList.txt")(0)
                                                  //> rawSentence  : String = Myanmar (MM); Thailand (TH); Lao People's Democratic
                                                  //|  Republic (LA)
                                                  //| rawAMR  : String = (a / and :op1 (c3 / country :name (n7 / name :op1 "Myanma
                                                  //| r")) :op2 (c4 / country :name (n8 / name :op1 "Thailand")) :op3 (c5 / countr
                                                  //| y :name (n9 / name :op1 "Lao" :op2 "People's" :op3 "Democratic" :op4 "Republ
                                                  //| ic")))
// val (rawSentence, rawAMR) = AMRGraph.importFile("C:\\AMR\\simpleDate.txt")(0)
// val (rawSentence, rawAMR) = AMRGraph.importFile("C:\\AMR\\lobbying.txt")(0)

  //    val tokenisedSentence = rawSentence.split(" ")
  val amr = Graph.parse(rawAMR)                   //> amr  : edu.cmu.lti.nlp.amr.Graph = Graph((and :op1 (country :name (name :op1
                                                  //|  "Myanmar")) :op2 (country :name (name :op1 "Thailand")) :op3 (country :name
                                                  //|  (name :op1 "Lao" :op2 "People's" :op3 "Democratic" :op4 "Republic"))),Array
                                                  //| Buffer(),Map(0.0.0 -> (name :op1 "Myanmar"), 0.2.0.2 -> "Democratic", 0.0.0.
                                                  //| 0 -> "Myanmar", 0.1 -> (country :name (name :op1 "Thailand")), 0.2.0 -> (nam
                                                  //| e :op1 "Lao" :op2 "People's" :op3 "Democratic" :op4 "Republic"), 0.2.0.1 -> 
                                                  //| "People's", 0.1.0.0 -> "Thailand", 0.0 -> (country :name (name :op1 "Myanmar
                                                  //| ")), 0.1.0 -> (name :op1 "Thailand"), 0.2.0.0 -> "Lao", 0.2.0.3 -> "Republic
                                                  //| ", 0 -> (and :op1 (country :name (name :op1 "Myanmar")) :op2 (country :name 
                                                  //| (name :op1 "Thailand")) :op3 (country :name (name :op1 "Lao" :op2 "People's"
                                                  //|  :op3 "Democratic" :op4 "Republic"))), 0.2 -> (country :name (name :op1 "Lao
                                                  //| " :op2 "People's" :op3 "Democratic" :op4 "Republic"))),Map(n7 -> (name :op1 
                                                  //| "Myanmar"), c4 -> (country :name (name :op1 "Thailand")), c3 -> (country :na
                                                  //| me (name :op1 "Myanmar")), a -> (and :op1 (country :name (name :op1 "Myanmar
                                                  //| ")) :op2 (country :name (name :op1 "Thailand")) :op3 (country :name (name :o
                                                  //| p1 "Lao" :op2 "People's" :op3 "Democratic" :op4 "Republic"))), n9 -> (name :
                                                  //| op1 "Lao" :op2 "People's" :op3 "Democratic" :op4 "Republic"), n8 -> (name :o
                                                  //| p1 "Thailand"), c5 -> (country :name (name :op1 "Lao" :op2 "People's" :op3 "
                                                  //| Democratic" :op4 "Republic"))))
  val tokenisedSentence = DependencyTree.preProcess(rawSentence)
                                                  //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.1 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [3.9 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [3.2 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [5.4 sec].
                                                  //| tokenisedSentence  : List[String] = List(Myanmar, MM, Thailand, TH, Lao, Peo
                                                  //| ple, 's, Democratic, Republic, LA)
val wordAlignments = AlignTest.alignWords(tokenisedSentence.toArray, amr)
                                                  //> wordAlignments  : Array[Option[edu.cmu.lti.nlp.amr.Node]] = Array(Some("Myan
                                                  //| mar"), None, Some("Thailand"), None, Some("Lao"), Some("People's"), None, So
                                                  //| me("Democratic"), Some("Republic"), None)
wordAlignments foreach println                    //> Some("Myanmar")
                                                  //| None
                                                  //| Some("Thailand")
                                                  //| None
                                                  //| Some("Lao")
                                                  //| Some("People's")
                                                  //| None
                                                  //| Some("Democratic")
                                                  //| Some("Republic")
                                                  //| None
AlignSpans.alignSpans(tokenisedSentence.toArray, amr, wordAlignments) foreach println
                                                  //> Some(0)
                                                  //| None
                                                  //| Some(1)
                                                  //| None
                                                  //| Some(2)
                                                  //| Some(2)
                                                  //| None
                                                  //| Some(2)
                                                  //| Some(2)
                                                  //| None
 val amrG =  AMRGraph(amr)                        //> amrG  : amr.AMRGraph = AMRGraph(Map(0.1 -> country, 0.0.0 -> name, 0.1.0 -> 
                                                  //| name, 0.0 -> country, 0.0.0.0 -> "Myanmar", 0.2.0.1 -> "People's", 0.2.0 -> 
                                                  //| name, 0.1.0.0 -> "Thailand", 0 -> and, 0.2.0.3 -> "Republic", 0.2 -> country
                                                  //| , 0.2.0.2 -> "Democratic", ROOT -> ROOT, 0.2.0.0 -> "Lao"),Map(0.1 -> (3,4),
                                                  //|  0.0.0 -> (1,2), 0.1.0 -> (3,4), 0.0 -> (1,2), 0.0.0.0 -> (1,2), 0.2.0.1 -> 
                                                  //| (5,10), 0.2.0 -> (5,10), 0.1.0.0 -> (3,4), 0.2.0.3 -> (5,10), 0.2 -> (5,10),
                                                  //|  0.2.0.2 -> (5,10), 0.2.0.0 -> (5,10)),Map((0.0,0.0.0) -> name, (0.2.0,0.2.0
                                                  //| .3) -> opN, (ROOT,0) -> ROOT, (0.0.0,0.0.0.0) -> opN, (0.1,0.1.0) -> name, (
                                                  //| 0.2.0,0.2.0.2) -> opN, (0.1.0,0.1.0.0) -> opN, (0,0.2) -> opN, (0,0.1) -> op
                                                  //| N, (0.2,0.2.0) -> name, (0,0.0) -> opN, (0.2.0,0.2.0.0) -> opN, (0.2.0,0.2.0
                                                  //| .1) -> opN))
 amrG.nodes.keys.toList.sorted foreach (x => println(s"${x}\t\t${amrG.nodes(x)}\t\t${amrG.nodeSpans.getOrElse(x, "No Mapping")}"))
                                                  //> 0		and		No Mapping
                                                  //| 0.0		country		(1,2)
                                                  //| 0.0.0		name		(1,2)
                                                  //| 0.0.0.0		"Myanmar"		(1,2)
                                                  //| 0.1		country		(3,4)
                                                  //| 0.1.0		name		(3,4)
                                                  //| 0.1.0.0		"Thailand"		(3,4)
                                                  //| 0.2		country		(5,10)
                                                  //| 0.2.0		name		(5,10)
                                                  //| 0.2.0.0		"Lao"		(5,10)
                                                  //| 0.2.0.1		"People's"		(5,10)
                                                  //| 0.2.0.2		"Democratic"		(5,10)
                                                  //| 0.2.0.3		"Republic"		(5,10)
                                                  //| ROOT		ROOT		No Mapping

}