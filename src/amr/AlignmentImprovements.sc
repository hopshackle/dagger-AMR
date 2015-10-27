package amr
import edu.cmu.lti.nlp.amr._

object AlignmentImprovements {

// val (rawSentence, rawAMR) = AMRGraph.importFile("C:\\AMR\\Riyadh-based.txt")(0)
//  val (rawSentence, rawAMR) = AMRGraph.importFile("C:\\AMR\\CountryList.txt")(0)
//  val (rawSentence, rawAMR) = AMRGraphimportFile("C:\\AMR\\Lavrov.txt")(0)
  val (rawSentence, rawAMR) = AMRGraph.importFile("C:\\AMR\\TargetKazakh.txt")(0)
                                                  //> rawSentence  : String = Kazakhstan also became a key U.S. partner in the Afg
                                                  //| han war opening its airspace to military overflights.
                                                  //| rawAMR  : String = (b / become-01 :ARG1 (c / country :name (n / name :op1 "K
                                                  //| azakhstan") :ARG0-of (o / open-01 :ARG1 (a / airspace :poss c) :ARG3 (f / fl
                                                  //| y-01 :ARG0 (m / military) :path (o2 / over :op1 c)))) :ARG2 (p / partner-01 
                                                  //| :ARG0 c :ARG1 (c2 / country :name (n2 / name :op1 "U.S.")) :mod (k / key) :p
                                                  //| urpose (w / war-01 :ARG1 (c3 / country :name (n3 / name :op1 "Afghanistan"))
                                                  //| )) :mod (a2 / also))
  val dt = DependencyTree(rawSentence)            //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [3.2 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [5.1 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [4.3 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [3.7 sec].
                                                  //| dt  : amr.DependencyTree = 
                                                  //| NodeMap:	Map(1 -> Kazakhstan, 2 -> also, 3 -> became, 4 -> a, 5 -> key, 6
                                                  //|  -> U.S., 7 -> partner, 8 -> in, 9 -> the, 10 -> Afghan, 11 -> war, 12 -> op
                                                  //| ening, 13 -> its, 14 -> airspace, 15 -> to, 16 -> military, 17 -> overflight
                                                  //| s)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,12
                                                  //| ), 12 -> (12,13), 13 -> (13,14), 14 -> (14,15), 15 -> (15,16), 16 -> (16,17)
                                                  //| , 17 -> (17,18))
                                                  //| Edges:	Map((7,1) -> nsubj, (7,2) -> advmod, (7,3) -> cop, (7,4) -> det,
                                                  //|  (7,5) -> amod, (7,6) -> nn, (7,8) -> prep, (7,12) -> xcomp, (8,11) -> pobj,
                                                  //|  (11,9) -> det, (11,10) -> amod, (12,14) -> dobj, (12,15) -> prep, (14,13) -
                                                  //| > poss, (15,17) -> pobj, (17,16) -> amod)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map()
                                                  //| PartsOfSpeech:	Map(5 -> JJ, 10 -> JJ, 14 -> NN, 1 -> NNP, 6 -> NNP, 9 -
                                                  //| > DT, 13 -> PRP$, 2 -> RB, 17 -> NNS, 12 -> VBG, 7 -> NN, 3 -> VBD, 16 -> JJ
                                                  //| , 11 -> NN, 8 -> IN, 4 -> DT, 15 -> TO)
                                                  //| DependencyLabels:	Map(5 -> amod, 10 -> amod, 14 -> dobj, 1 -> nsubj, 6 -> 
                                                  //| nn, 9 -> det, 13 -> poss, 2 -> advmod, 17 -> pobj, 12 -> xcomp, 3 -> cop, 16
                                                  //|  -> amod, 11 -> pobj, 8 -> prep, 4 -> det, 15 -> prep)
// val (rawSentence, rawAMR) = AMRGraph.importFile("C:\\AMR\\simpleDate.txt")(0)
// val (rawSentence, rawAMR) = AMRGraph.importFile("C:\\AMR\\lobbying.tt")(0)

  //    val tokenisedSentence = rawSentence.split(" ")
  val amr = Graph.parse(rawAMR)                   //> amr  : edu.cmu.lti.nlp.amr.Graph = Graph((become-01 :ARG1 (country :name (na
                                                  //| me :op1 "Kazakhstan") :ARG0-of (open-01 :ARG1 (airspace :poss country) :ARG3
                                                  //|  (fly-01 :ARG0 military :path (over :op1 country)))) :ARG2 (partner-01 :ARG1
                                                  //|  (country :name (name :op1 "U.S.")) :mod key :purpose (war-01 :ARG1 (country
                                                  //|  :name (name :op1 "Afghanistan"))) :ARG0 country) :mod also),ArrayBuffer(),M
                                                  //| ap(0.0.0 -> (name :op1 "Kazakhstan"), 0.1.2.0.0 -> (name :op1 "Afghanistan")
                                                  //| , 0.0.0.0 -> "Kazakhstan", 0.1.1 -> key, 0.1 -> (partner-01 :ARG1 (country :
                                                  //| name (name :op1 "U.S.")) :mod key :purpose (war-01 :ARG1 (country :name (nam
                                                  //| e :op1 "Afghanistan"))) :ARG0 country), 0.1.0.0 -> (name :op1 "U.S."), 0.0.1
                                                  //| .1.1 -> (over :op1 country), 0.0.1.1 -> (fly-01 :ARG0 military :path (over :
                                                  //| op1 country)), 0.1.2.0.0.0 -> "Afghanistan", 0.0 -> (country :name (name :op
                                                  //| 1 "Kazakhstan") :ARG0-of (open-01 :ARG1 (airspace :poss country) :ARG3 (fly-
                                                  //| 01 :ARG0 military :path (over :op1 country)))), 0.1.0 -> (country :name (nam
                                                  //| e :op1 "U.S.")), 0.0.1 -> (open-01 :ARG1 (airspace :poss country) :ARG3 (fly
                                                  //| -01 :ARG0 military :path (over :op1 country))), 0.1.0.0.0 -> "U.S.", 0.1.2.0
                                                  //|  -> (country :name (name :op1 "Afghanistan")), 0 -> (become-01 :ARG1 (countr
                                                  //| y :name (name :op1 "Kazakhstan") :ARG0-of (open-01 :ARG1 (airspace :poss cou
                                                  //| ntry) :ARG3 (fly-01 :ARG0 military :path (over :op1 country)))) :ARG2 (partn
                                                  //| er-01 :ARG1 (country :name (name :op1 "U.S.")) :mod key :purpose (war-01 :AR
                                                  //| G1 (country :name (name :op1 "Afghanistan"))) :ARG0 country) :mod also), 0.0
                                                  //| .1.0 -> (airspace :poss country), 0.0.1.1.0 -> military, 0.1.2 -> (war-01 :A
                                                  //| RG1 (country :name (name :op1 "Afghanistan"))), 0.2 -> also),Map(n -> (name 
                                                  //| :op1 "Kazakhstan"), w -> (war-01 :ARG1 (country :name (name :op1 "Afghanista
                                                  //| n"))), b -> (become-01 :ARG1 (country :name (name :op1 "Kazakhstan") :ARG0-o
                                                  //| f (open-01 :ARG1 (airspace :poss country) :ARG3 (fly-01 :ARG0 military :path
                                                  //|  (over :op1 country)))) :ARG2 (partner-01 :ARG1 (country :name (name :op1 "U
                                                  //| .S.")) :mod key :purpose (war-01 :ARG1 (country :name (name :op1 "Afghanista
                                                  //| n"))) :ARG0 country) :mod also), k -> key, m -> military, p -> (partner-01 :
                                                  //| ARG1 (country :name (name :op1 "U.S.")) :mod key :purpose (war-01 :ARG1 (cou
                                                  //| ntry :name (name :op1 "Afghanistan"))) :ARG0 country), n3 -> (name :op1 "Afg
                                                  //| hanistan"), c3 -> (country :name (name :op1 "Afghanistan")), a -> 
                                                  //| Output exceeds cutoff limit.
                                                  // ee-01 :ARG0 (person :ARG0-of (have-org-role-91 :ARG1 (organization :name (na
  val tokenisedSentence = DependencyTree.preProcess(rawSentence)
                                                  //> tokenisedSentence  : List[String] = List(Kazakhstan, also, became, a, key, U
                                                  //| .S., partner, in, the, Afghan, war, opening, its, airspace, to, military, ov
                                                  //| erflights)
 val wordAlignments = AlignTest.alignWords(rawSentence, tokenisedSentence.toArray, amr)
                                                  //> wordAlignments  : Array[Option[edu.cmu.lti.nlp.amr.Node]] = Array(Some("Kaza
                                                  //| khstan"), Some(also), Some((become-01 :ARG1 (country :name (name :op1 "Kazak
                                                  //| hstan") :ARG0-of (open-01 :ARG1 (airspace :poss country) :ARG3 (fly-01 :ARG0
                                                  //|  military :path (over :op1 country)))) :ARG2 (partner-01 :ARG1 (country :nam
                                                  //| e (name :op1 "U.S.")) :mod key :purpose (war-01 :ARG1 (country :name (name :
                                                  //| op1 "Afghanistan"))) :ARG0 country) :mod also)), None, Some(key), Some("U.S.
                                                  //| "), Some((partner-01 :ARG1 (country :name (name :op1 "U.S.")) :mod key :purp
                                                  //| ose (war-01 :ARG1 (country :name (name :op1 "Afghanistan"))) :ARG0 country))
                                                  //| , None, None, Some("Afghanistan"), Some((war-01 :ARG1 (country :name (name :
                                                  //| op1 "Afghanistan")))), Some((open-01 :ARG1 (airspace :poss country) :ARG3 (f
                                                  //| ly-01 :ARG0 military :path (over :op1 country)))), None, Some((airspace :pos
                                                  //| s country)), None, Some(military), Some((over :op1 country)))
//val wordAlignments = AlignWords.alignWords(tokenisedSentence.toArray, amr)

                                               
                                            
wordAlignments foreach println                    //> Some("Kazakhstan")
                                                  //| Some(also)
                                                  //| Some((become-01 :ARG1 (country :name (name :op1 "Kazakhstan") :ARG0-of (ope
                                                  //| n-01 :ARG1 (airspace :poss country) :ARG3 (fly-01 :ARG0 military :path (ove
                                                  //| r :op1 country)))) :ARG2 (partner-01 :ARG1 (country :name (name :op1 "U.S."
                                                  //| )) :mod key :purpose (war-01 :ARG1 (country :name (name :op1 "Afghanistan")
                                                  //| )) :ARG0 country) :mod also))
                                                  //| None
                                                  //| Some(key)
                                                  //| Some("U.S.")
                                                  //| Some((partner-01 :ARG1 (country :name (name :op1 "U.S.")) :mod key :purpose
                                                  //|  (war-01 :ARG1 (country :name (name :op1 "Afghanistan"))) :ARG0 country))
                                                  //| None
                                                  //| None
                                                  //| Some("Afghanistan")
                                                  //| Some((war-01 :ARG1 (country :name (name :op1 "Afghanistan"))))
                                                  //| Some((open-01 :ARG1 (airspace :poss country) :ARG3 (fly-01 :ARG0 military :
                                                  //| path (over :op1 country))))
                                                  //| None
                                                  //| Some((airspace :poss country))
                                                  //| None
                                                  //| Some(military)
                                                  //| Some((over :op1 country))
                                            
AlignSpans.alignSpans(tokenisedSentence.toArray, amr, wordAlignments) zip wordAlignments foreach println
                                                  //> (Some(1),Some("Kazakhstan"))
                                                  //| (Some(10),Some(also))
                                                  //| (Some(0),Some((become-01 :ARG1 (country :name (name :op1 "Kazakhstan") :ARG
                                                  //| 0-of (open-01 :ARG1 (airspace :poss country) :ARG3 (fly-01 :ARG0 military :
                                                  //| path (over :op1 country)))) :ARG2 (partner-01 :ARG1 (country :name (name :o
                                                  //| p1 "U.S.")) :mod key :purpose (war-01 :ARG1 (country :name (name :op1 "Afgh
                                                  //| anistan"))) :ARG0 country) :mod also)))
                                                  //| (None,None)
                                                  //| (Some(7),Some(key))
                                                  //| (Some(6),Some("U.S."))
                                                  //| (Some(5),Some((partner-01 :ARG1 (country :name (name :op1 "U.S.")) :mod key
                                                  //|  :purpose (war-01 :ARG1 (country :name (name :op1 "Afghanistan"))) :ARG0 co
                                                  //| untry)))
                                                  //| (None,None)
                                                  //| (None,None)
                                                  //| (Some(9),Some("Afghanistan"))
                                                  //| (Some(8),Some((war-01 :ARG1 (country :name (name :op1 "Afghanistan")))))
                                                  //| (Some(1),Some((open-01 :ARG1 (airspace :poss country) :ARG3 (fly-01 :ARG0 m
                                                  //| ilitary :path (over :op1 country)))))
                                                  //| (None,None)
                                                  //| (Some(2),Some((airspace :poss country)))
                                                  //| (None,None)
                                                  //| (Some(3),Some(military))
                                                  //| (Some(4),Some((over :op1 country)))
 val amrG =  AMRGraph(amr)                        //> amrG  : amr.AMRGraph = AMRGraph(Map(0.1 -> partner-01, 0.0.0 -> name, 0.0.1
                                                  //| .1.0 -> military, 0.1.2.0.0 -> name, 0.1.0 -> country, 0.0.1.0 -> airspace,
                                                  //|  0.1.0.0.0 -> "U.S.", 0.0 -> country, 0.0.0.0 -> "Kazakhstan", 0.1.2.0.0.0 
                                                  //| -> "Afghanistan", 0.0.1.1 -> fly-01, 0.0.1 -> open-01, 0.1.0.0 -> name, 0.1
                                                  //| .2.0 -> country, 0 -> become-01, 0.1.2 -> war-01, 0.0.1.1.1 -> over, 0.2 ->
                                                  //|  also, 0.1.1 -> key),Map(0.1 -> (7,8), 0.0.0 -> (1,13), 0.0.1.1.0 -> (16,17
                                                  //| ), 0.1.2.0.0 -> (10,11), 0.1.0 -> (6,7), 0.0.1.0 -> (14,15), 0.1.0.0.0 -> (
                                                  //| 6,7), 0.0 -> (1,13), 0.0.0.0 -> (1,13), 0.1.2.0.0.0 -> (10,11), 0.0.1 -> (1
                                                  //| ,13), 0.1.0.0 -> (6,7), 0.1.2.0 -> (10,11), 0 -> (3,4), 0.1.2 -> (11,12), 0
                                                  //| .0.1.1.1 -> (17,18), 0.2 -> (2,3), 0.1.1 -> (5,6)),Map((0.0,0.0.0) -> name,
                                                  //|  (0.0.1.1.1,0.0) -> opN, (0.0.0,0.0.0.0) -> opN, (0.1,0.1.0) -> ARG1, (0.1,
                                                  //| 0.1.2) -> purpose, (0.1.2.0,0.1.2.0.0) -> name, (0.0.1.0,0.0) -> poss, (0.1
                                                  //| .0,0.1.0.0) -> name, (0,0.2) -> mod, (0,0.1) -> ARG2, (0.0.1,0.0.1.1) -> AR
                                                  //| G3, (0.1.0.0,0.1.0.0.0) -> opN, (0.1,0.0) -> ARG0, (0.0.1.1,0.0.1.1.0) -> A
                                                  //| RG0, (0,0.0) -> ARG1, (0.0.1,0.0.1.0) -> ARG1, (0.1.2,0.1.2.0) -> ARG1, (0.
                                                  //| 0.1.1,0.0.1.1.1) -> path, (0.1.2.0.0,0.1.2.0.0.0) -> opN, (0.0,0.0.1) -> AR
                                                  //| G0-of, (0.1,0.1.1) -> mod),Map((0.0,0.0.0) -> name, (0.0.1.1.1,0.0) -> op1,
                                                  //|  (0.0.0,0.0.0.0) -> op1, (0.1,0.1.0) -> ARG1, (0.1,0.1.2) -> purpose, (0.1.
                                                  //| 2.0,0.1.2.0.0) -> name, (0.0.1.0,0.0) -> poss, (0.1.0,0.1.0.0) -> name, (0,
                                                  //| 0.2) -> mod, (0,0.1) -> ARG2, (0.0.1,0.0.1.1) -> ARG3, (0.1.0.0,0.1.0.0.0) 
                                                  //| -> op1, (0.1,0.0) -> ARG0, (0.0.1.1,0.0.1.1.0) -> ARG0, (0,0.0) -> ARG1, (0
                                                  //| .0.1,0.0.1.0) -> ARG1, (0.1.2,0.1.2.0) -> ARG1, (0.0.1.1,0.0.1.1.1) -> path
                                                  //| , (0.1.2.0.0,0.1.2.0.0.0) -> op1, (0.0,0.0.1) -> ARG0-of, (0.1,0.1.1) -> mo
                                                  //| d),List())
 amrG.nodes.keys.toList.sorted foreach (x => println(s"${x}\t\t${amrG.nodes(x)}\t\t${amrG.nodeSpans.getOrElse(x, "No Mapping")}"))
                                                  //> 0		become-01		(3,4)
                                                  //| 0.0		country		(1,13)
                                                  //| 0.0.0		name		(1,13)
                                                  //| 0.0.0.0		"Kazakhstan"		(1,13)
                                                  //| 0.0.1		open-01		(1,13)
                                                  //| 0.0.1.0		airspace		(14,15)
                                                  //| 0.0.1.1		fly-01		No Mapping
                                                  //| 0.0.1.1.0		military		(16,17)
                                                  //| 0.0.1.1.1		over		(17,18)
                                                  //| 0.1		partner-01		(7,8)
                                                  //| 0.1.0		country		(6,7)
                                                  //| 0.1.0.0		name		(6,7)
                                                  //| 0.1.0.0.0		"U.S."		(6,7)
                                                  //| 0.1.1		key		(5,6)
                                                  //| 0.1.2		war-01		(11,12)
                                                  //| 0.1.2.0		country		(10,11)
                                                  //| 0.1.2.0.0		name		(10,11)
                                                  //| 0.1.2.0.0.0		"Afghanistan"		(10,11)
                                                  //| 0.2		also		(2,3)
                                                                                               
                                                  
                                                  
}