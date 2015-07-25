package amr
import edu.cmu.lti.nlp.amr._

object AlignmentImprovements {

// val (rawSentence, rawAMR) = AMRGraph.importFile("C:\\AMR\\Riyadh-based.txt")(0)
// val (rawSentence, rawAMR) = AMRGraph.importFile("C:\\AMR\\CountryList.txt")(0)
val (rawSentence, rawAMR) = AMRGraph.importFile("C:\\AMR\\simpleDate.txt")(0)
                                                  //> rawSentence  : String = 2002-01-05
                                                  //| rawAMR  : String = (d / date-entity :year 2002 :month 1 :day 5)

  //    val tokenisedSentence = rawSentence.split(" ")
  val amr = Graph.parse(rawAMR)                   //> amr  : edu.cmu.lti.nlp.amr.Graph = Graph((date-entity :year 2002 :month 1 :d
                                                  //| ay 5),ArrayBuffer(),Map(0.1 -> 1, 0.0 -> 2002, 0 -> (date-entity :year 2002 
                                                  //| :month 1 :day 5), 0.2 -> 5),Map(d -> (date-entity :year 2002 :month 1 :day 5
                                                  //| )))
  val tokenisedSentence = DependencyTree.preProcess(rawSentence)
                                                  //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.2 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [3.9 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [3.5 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [3.0 sec].
                                                  //| tokenisedSentence  : List[String] = List(2002, 1, 5)
val wordAlignments = AlignWords.alignWords(tokenisedSentence.toArray, amr)
                                                  //> wordAlignments  : Array[Option[edu.cmu.lti.nlp.amr.Node]] = Array(Some(2002)
                                                  //| , Some(1), Some(5))
wordAlignments foreach println                    //> Some(2002)
                                                  //| Some(1)
                                                  //| Some(5)
AlignSpans.alignSpans(tokenisedSentence.toArray, amr, wordAlignments) foreach println
                                                  //> Some(0)
                                                  //| Some(0)
                                                  //| Some(0)
 val amrG =  AMRGraph(amr)                        //> amrG  : amr.AMRGraph = AMRGraph(Map(0.1 -> 1, 0.0 -> 2002, 0 -> date-entity,
                                                  //|  0.2 -> 5, ROOT -> ROOT),Map(0 -> (1,4), 0.0 -> (1,4), 0.1 -> (1,4), 0.2 -> 
                                                  //| (1,4)),Map((0,0.0) -> year, (0,0.1) -> month, (0,0.2) -> day, (ROOT,0) -> RO
                                                  //| OT))
 amrG.nodes.keys.toList.sorted foreach (x => println(s"${x}\t\t${amrG.nodes(x)}\t\t${amrG.nodeSpans.getOrElse(x, "No Mapping")}"))
                                                  //> 0		date-entity		(1,4)
                                                  //| 0.0		2002		(1,4)
                                                  //| 0.1		1		(1,4)
                                                  //| 0.2		5		(1,4)
                                                  //| ROOT		ROOT		No Mapping

}