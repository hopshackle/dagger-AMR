package amr
import edu.cmu.lti.nlp.amr._

object AlignmentImprovements {

// val (rawSentence, rawAMR) = AMRGraph.importFile("C:\\AMR\\Riyadh-based.txt")(0)
//  val (rawSentence, rawAMR) = AMRGraph.importFile("C:\\AMR\\CountryList.txt")(0)
//  val (rawSentence, rawAMR) = AMRGraph.importFile("C:\\AMR\\Lavrov.txt")(0)
  val (rawSentence, rawAMR) = AMRGraph.importFile("C:\\AMR\\TargetIAEA.txt")(0)
                                                  //> rawSentence  : String = IAEA accepted North Korea's proposal last month but 
                                                  //| cautioned that the proposal is not a breakthrough.
                                                  //| rawAMR  : String = (c / contrast-01 :ARG1 (a / accept-01 :ARG0 (o / organiza
                                                  //| tion :name (n / name :op1 "IAEA")) :ARG1 (t / thing :ARG1-of (p / propose-01
                                                  //|  :ARG0 (c2 / country :name (n2 / name :op1 "North" :op2 "Korea")))) :time (m
                                                  //|  / month :mod (l / last))) :ARG2 (c3 / caution-01 :ARG0 o :ARG1 (b / breakth
                                                  //| rough-01 :domain t :polarity -)))
// val (rawSentence, rawAMR) = AMRGraph.importFile("C:\\AMR\\simpleDate.txt")(0)
// val (rawSentence, rawAMR) = AMRGraph.importFile("C:\\AMR\\lobbying.txt")(0)

  //    val tokenisedSentence = rawSentence.split(" ")
  val amr = Graph.parse(rawAMR)                   //> amr  : edu.cmu.lti.nlp.amr.Graph = Graph((contrast-01 :ARG1 (accept-01 :ARG0
                                                  //|  (organization :name (name :op1 "IAEA")) :ARG1 (thing :ARG1-of (propose-01 :
                                                  //| ARG0 (country :name (name :op1 "North" :op2 "Korea")))) :time (month :mod la
                                                  //| st)) :ARG2 (caution-01 :ARG1 (breakthrough-01 :polarity - :domain thing) :AR
                                                  //| G0 organization)),ArrayBuffer(),Map(0.0.0 -> (organization :name (name :op1 
                                                  //| "IAEA")), 0.0.0.0 -> (name :op1 "IAEA"), 0.0.1.0.0.0 -> (name :op1 "North" :
                                                  //| op2 "Korea"), 0.0.1.0.0 -> (country :name (name :op1 "North" :op2 "Korea")),
                                                  //|  0.1 -> (caution-01 :ARG1 (breakthrough-01 :polarity - :domain thing) :ARG0 
                                                  //| organization), 0.0.2 -> (month :mod last), 0.0.1.0.0.0.1 -> "Korea", 0.1.0.0
                                                  //|  -> -, 0.0.2.0 -> last, 0.1.0 -> (breakthrough-01 :polarity - :domain thing)
                                                  //| , 0.0 -> (accept-01 :ARG0 (organization :name (name :op1 "IAEA")) :ARG1 (thi
                                                  //| ng :ARG1-of (propose-01 :ARG0 (country :name (name :op1 "North" :op2 "Korea"
                                                  //| )))) :time (month :mod last)), 0.0.1 -> (thing :ARG1-of (propose-01 :ARG0 (c
                                                  //| ountry :name (name :op1 "North" :op2 "Korea")))), 0.0.1.0.0.0.0 -> "North", 
                                                  //| 0 -> (contrast-01 :ARG1 (accept-01 :ARG0 (organization :name (name :op1 "IAE
                                                  //| A")) :ARG1 (thing :ARG1-of (propose-01 :ARG0 (country :name (name :op1 "Nort
                                                  //| h" :op2 "Korea")))) :time (month :mod last)) :ARG2 (caution-01 :ARG1 (breakt
                                                  //| hrough-01 :polarity - :domain thing) :ARG0 organization)), 0.0.1.0 -> (propo
                                                  //| se-01 :ARG0 (country :name (name :op1 "North" :op2 "Korea"))), 0.0.0.0.0 -> 
                                                  //| "IAEA"),Map(n -> (name :op1 "IAEA"), b -> (breakthrough-01 :polarity - :doma
                                                  //| in thing), t -> (thing :ARG1-of (propose-01 :ARG0 (country :name (name :op1 
                                                  //| "North" :op2 "Korea")))), m -> (month :mod last), p -> (propose-01 :ARG0 (co
                                                  //| untry :name (name :op1 "North" :op2 "Korea"))), c3 -> (caution-01 :ARG1 (bre
                                                  //| akthrough-01 :polarity - :domain thing) :ARG0 organization), a -> (accept-01
                                                  //|  :ARG0 (organization :name (name :op1 "IAEA")) :ARG1 (thing :ARG1-of (propos
                                                  //| e-01 :ARG0 (country :name (name :op1 "North" :op2 "Korea")))) :time (month :
                                                  //| mod last)), l -> last, c -> (contrast-01 :ARG1 (accept-01 :ARG0 (organizatio
                                                  //| n :name (name :op1 "IAEA")) :ARG1 (thing :ARG1-of (propose-01 :ARG0 (country
                                                  //|  :name (name :op1 "North" :op2 "Korea")))) :time (month :mod last)) :ARG2 (c
                                                  //| aution-01 :ARG1 (breakthrough-01 :polarity - :domain thing) :ARG0 organizati
                                                  //| on)), o -> (organization :name (name :op1 "IAEA")), n2 -> (name :o
                                                  //| Output exceeds cutoff limit.
  val tokenisedSentence = DependencyTree.preProcess(rawSentence)
                                                  //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.4 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [4.7 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [3.4 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [5.4 sec].
                                                  //| tokenisedSentence  : List[String] = List(IAEA, accepted, North, Korea, 's, p
                                                  //| roposal, last, month, but, cautioned, that, the, proposal, is, not, a, break
                                                  //| through)
val wordAlignments = AlignTest.alignWords(tokenisedSentence.toArray, amr)
                                                  //> wordAlignments  : Array[Option[edu.cmu.lti.nlp.amr.Node]] = Array(Some("IAEA
                                                  //| "), Some((accept-01 :ARG0 (organization :name (name :op1 "IAEA")) :ARG1 (thi
                                                  //| ng :ARG1-of (propose-01 :ARG0 (country :name (name :op1 "North" :op2 "Korea"
                                                  //| )))) :time (month :mod last))), Some("North"), Some("Korea"), None, None, So
                                                  //| me(last), Some((month :mod last)), Some((contrast-01 :ARG1 (accept-01 :ARG0 
                                                  //| (organization :name (name :op1 "IAEA")) :ARG1 (thing :ARG1-of (propose-01 :A
                                                  //| RG0 (country :name (name :op1 "North" :op2 "Korea")))) :time (month :mod las
                                                  //| t)) :ARG2 (caution-01 :ARG1 (breakthrough-01 :polarity - :domain thing) :ARG
                                                  //| 0 organization))), Some((caution-01 :ARG1 (breakthrough-01 :polarity - :doma
                                                  //| in thing) :ARG0 organization)), None, None, Some((propose-01 :ARG0 (country 
                                                  //| :name (name :op1 "North" :op2 "Korea")))), None, None, None, Some((breakthro
                                                  //| ugh-01 :polarity - :domain thing)))
wordAlignments foreach println                    //> Some("IAEA")
                                                  //| Some((accept-01 :ARG0 (organization :name (name :op1 "IAEA")) :ARG1 (thing :
                                                  //| ARG1-of (propose-01 :ARG0 (country :name (name :op1 "North" :op2 "Korea"))))
                                                  //|  :time (month :mod last)))
                                                  //| Some("North")
                                                  //| Some("Korea")
                                                  //| None
                                                  //| None
                                                  //| Some(last)
                                                  //| Some((month :mod last))
                                                  //| Some((contrast-01 :ARG1 (accept-01 :ARG0 (organization :name (name :op1 "IAE
                                                  //| A")) :ARG1 (thing :ARG1-of (propose-01 :ARG0 (country :name (name :op1 "Nort
                                                  //| h" :op2 "Korea")))) :time (month :mod last)) :ARG2 (caution-01 :ARG1 (breakt
                                                  //| hrough-01 :polarity - :domain thing) :ARG0 organization)))
                                                  //| Some((caution-01 :ARG1 (breakthrough-01 :polarity - :domain thing) :ARG0 org
                                                  //| anization))
                                                  //| None
                                                  //| None
                                                  //| Some((propose-01 :ARG0 (country :name (name :op1 "North" :op2 "Korea"))))
                                                  //| None
                                                  //| None
                                                  //| None
                                                  //| Some((breakthrough-01 :polarity - :domain thing))
AlignSpans.alignSpans(tokenisedSentence.toArray, amr, wordAlignments) foreach println
                                                  //> Some(2)
                                                  //| Some(1)
                                                  //| Some(4)
                                                  //| Some(4)
                                                  //| None
                                                  //| None
                                                  //| Some(6)
                                                  //| Some(5)
                                                  //| Some(0)
                                                  //| Some(7)
                                                  //| None
                                                  //| None
                                                  //| Some(3)
                                                  //| None
                                                  //| None
                                                  //| None
                                                  //| Some(8)
 val amrG =  AMRGraph(amr)                        //> amrG  : amr.AMRGraph = AMRGraph(Map(0.1 -> caution-01, 0.0.1.0.0.0 -> name, 
                                                  //| 0.0.0 -> organization, 0.1.0 -> breakthrough-01, 0.0.1.0 -> propose-01, 0.0.
                                                  //| 1.0.0.0.1 -> "Korea", 0.0 -> accept-01, 0.0.1.0.0.0.0 -> "North", 0.0.2 -> m
                                                  //| onth, 0.0.0.0 -> name, 0.0.1 -> thing, 0.1.0.0 -> -, 0.0.1.0.0 -> country, 0
                                                  //|  -> contrast-01, 0.0.0.0.0 -> "IAEA", 0.0.2.0 -> last),Map(0.1 -> (10,11), 0
                                                  //| .0.1.0.0.0 -> (3,5), 0.0.0 -> (1,2), 0.1.0 -> (17,18), 0.0.1.0 -> (13,14), 0
                                                  //| .0.1.0.0.0.1 -> (3,5), 0.0 -> (2,3), 0.0.1.0.0.0.0 -> (3,5), 0.0.2 -> (8,9),
                                                  //|  0.0.0.0 -> (1,2), 0.0.1 -> (13,14), 0.0.1.0.0 -> (3,5), 0 -> (9,10), 0.0.0.
                                                  //| 0.0 -> (1,2), 0.0.2.0 -> (7,8)),Map((0.0.1.0.0.0,0.0.1.0.0.0.1) -> opN, (0.0
                                                  //| ,0.0.0) -> ARG0, (0.0.1.0.0,0.0.1.0.0.0) -> name, (0.0.0.0,0.0.0.0.0) -> opN
                                                  //| , (0.0.0,0.0.0.0) -> name, (0.1,0.1.0) -> ARG1, (0.1.0,0.1.0.0) -> polarity,
                                                  //|  (0.0.2,0.0.2.0) -> mod, (0,0.1) -> ARG2, (0.0,0.0.2) -> time, (0.1.0,0.0.1)
                                                  //|  -> domain, (0.1,0.0.0) -> ARG0, (0,0.0) -> ARG1, (0.0.1,0.0.1.0) -> ARG1-of
                                                  //| , (0.0.1.0,0.0.1.0.0) -> ARG0, (0.0,0.0.1) -> ARG1, (0.0.1.0.0.0,0.0.1.0.0.0
                                                  //| .0) -> opN),Map((0.0.1.0.0.0,0.0.1.0.0.0.1) -> op2, (0.0,0.0.0) -> ARG0, (0.
                                                  //| 0.1.0.0,0.0.1.0.0.0) -> name, (0.0.0.0,0.0.0.0.0) -> op1, (0.0.0,0.0.0.0) ->
                                                  //|  name, (0.1,0.1.0) -> ARG1, (0.1.0,0.1.0.0) -> polarity, (0.0.2,0.0.2.0) -> 
                                                  //| mod, (0,0.1) -> ARG2, (0.0,0.0.2) -> time, (0.1.0,0.0.1) -> domain, (0.1,0.0
                                                  //| .0) -> ARG0, (0,0.0) -> ARG1, (0.0.1,0.0.1.0) -> ARG1-of, (0.0.1.0,0.0.1.0.0
                                                  //| ) -> ARG0, (0.0,0.0.1) -> ARG1, (0.0.1.0.0.0,0.0.1.0.0.0.0) -> op1),List())
                                                  //| 
 amrG.nodes.keys.toList.sorted foreach (x => println(s"${x}\t\t${amrG.nodes(x)}\t\t${amrG.nodeSpans.getOrElse(x, "No Mapping")}"))
                                                  //> 0		contrast-01		(9,10)
                                                  //| 0.0		accept-01		(2,3)
                                                  //| 0.0.0		organization		(1,2)
                                                  //| 0.0.0.0		name		(1,2)
                                                  //| 0.0.0.0.0		"IAEA"		(1,2)
                                                  //| 0.0.1		thing		(13,14)
                                                  //| 0.0.1.0		propose-01		(13,14)
                                                  //| 0.0.1.0.0		country		(3,5)
                                                  //| 0.0.1.0.0.0		name		(3,5)
                                                  //| 0.0.1.0.0.0.0		"North"		(3,5)
                                                  //| 0.0.1.0.0.0.1		"Korea"		(3,5)
                                                  //| 0.0.2		month		(8,9)
                                                  //| 0.0.2.0		last		(7,8)
                                                  //| 0.1		caution-01		(10,11)
                                                  //| 0.1.0		breakthrough-01		(17,18)
                                                  //| 0.1.0.0		-		No Mapping

}