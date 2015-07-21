package amr
import edu.cmu.lti.nlp.amr._

object AlignmentImprovements {


  val (rawSentence, rawAMR) = AMRGraph.importFile("C:\\AMR\\militaryNetwork.txt")(0)
                                                  //> rawSentence  : String = NATO CONSIDERS cyber attacks a threat to military an
                                                  //| d civilian computer networks after the Estonian Government was struck by cyb
                                                  //| er attacks in 2007.
                                                  //| rawAMR  : String = (c / consider-02 :ARG0 (m2 / military :name (n / name :op
                                                  //| 1 "NATO")) :ARG1 (a / attack-01 :mod (c2 / cyber)) :ARG2 (t / thing :ARG1-of
                                                  //|  (t2 / threaten-01 :ARG2 (a5 / and :op1 (n2 / network :mod (m / military)) :
                                                  //| op2 (n4 / network :mod (c3 / civilian)) :mod (c4 / computer)))) :time (a3 / 
                                                  //| after :op1 (s / strike-01 :ARG0 (a4 / attack :mod (c6 / cyber)) :ARG1 (g / g
                                                  //| overnment-organization :ARG0-of (g2 / govern-01 :ARG1 (c5 / country :name (n
                                                  //| 3 / name :op1 "Estonia")))) :time (d / date-entity :year 2007))))
// val (rawSentence, rawAMR) = AMRGraph.importFile("C:\\AMR\\CountryList.txt")(0)

  //    val tokenisedSentence = rawSentence.split(" ")
  val amr = Graph.parse(rawAMR)                   //> amr  : edu.cmu.lti.nlp.amr.Graph = Graph((consider-02 :ARG0 (military :name 
                                                  //| (name :op1 "NATO")) :ARG1 (attack-01 :mod cyber) :ARG2 (thing :ARG1-of (thre
                                                  //| aten-01 :ARG2 (and :op1 (network :mod military) :op2 (network :mod civilian)
                                                  //|  :mod computer))) :time (after :op1 (strike-01 :ARG0 (attack :mod cyber) :AR
                                                  //| G1 (government-organization :ARG0-of (govern-01 :ARG1 (country :name (name :
                                                  //| op1 "Estonia")))) :time (date-entity :year 2007)))),ArrayBuffer(),Map(0.3.0.
                                                  //| 0 -> (attack :mod cyber), 0.3.0.0.0 -> cyber, 0.2.0.0.1 -> (network :mod civ
                                                  //| ilian), 0.0.0 -> (name :op1 "NATO"), 0.3.0.1.0.0.0 -> (name :op1 "Estonia"),
                                                  //|  0.0.0.0 -> "NATO", 0.3.0.2 -> (date-entity :year 2007), 0.2.0 -> (threaten-
                                                  //| 01 :ARG2 (and :op1 (network :mod military) :op2 (network :mod civilian) :mod
                                                  //|  computer)), 0.1 -> (attack-01 :mod cyber), 0.2.0.0.0 -> (network :mod milit
                                                  //| ary), 0.3.0.2.0 -> 2007, 0.2.0.0.1.0 -> civilian, 0.3.0.1 -> (government-org
                                                  //| anization :ARG0-of (govern-01 :ARG1 (country :name (name :op1 "Estonia")))),
                                                  //|  0.3.0.1.0.0 -> (country :name (name :op1 "Estonia")), 0.1.0 -> cyber, 0.0 -
                                                  //| > (military :name (name :op1 "NATO")), 0.2.0.0 -> (and :op1 (network :mod mi
                                                  //| litary) :op2 (network :mod civilian) :mod computer), 0.3 -> (after :op1 (str
                                                  //| ike-01 :ARG0 (attack :mod cyber) :ARG1 (government-organization :ARG0-of (go
                                                  //| vern-01 :ARG1 (country :name (name :op1 "Estonia")))) :time (date-entity :ye
                                                  //| ar 2007))), 0.2.0.0.0.0 -> military, 0 -> (consider-02 :ARG0 (military :name
                                                  //|  (name :op1 "NATO")) :ARG1 (attack-01 :mod cyber) :ARG2 (thing :ARG1-of (thr
                                                  //| eaten-01 :ARG2 (and :op1 (network :mod military) :op2 (network :mod civilian
                                                  //| ) :mod computer))) :time (after :op1 (strike-01 :ARG0 (attack :mod cyber) :A
                                                  //| RG1 (government-organization :ARG0-of (govern-01 :ARG1 (country :name (name 
                                                  //| :op1 "Estonia")))) :time (date-entity :year 2007)))), 0.3.0.1.0.0.0.0 -> "Es
                                                  //| tonia", 0.2.0.0.2 -> computer, 0.3.0.1.0 -> (govern-01 :ARG1 (country :name 
                                                  //| (name :op1 "Estonia"))), 0.3.0 -> (strike-01 :ARG0 (attack :mod cyber) :ARG1
                                                  //|  (government-organization :ARG0-of (govern-01 :ARG1 (country :name (name :op
                                                  //| 1 "Estonia")))) :time (date-entity :year 2007)), 0.2 -> (thing :ARG1-of (thr
                                                  //| eaten-01 :ARG2 (and :op1 (network :mod military) :op2 (network :mod civilian
                                                  //| ) :mod computer)))),Map(n -> (name :op1 "NATO"), t -> (thing :ARG1-of (threa
                                                  //| ten-01 :ARG2 (and :op1 (network :mod military) :op2 (network :mod 
                                                  //| Output exceeds cutoff limit.
  val tokenisedSentence = DependencyTree.preProcess(rawSentence)
                                                  //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.2 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [4.2 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [2.1 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [3.9 sec].
                                                  //| tokenisedSentence  : List[String] = List(NATO, CONSIDERS, cyber, attacks, a,
                                                  //|  threat, to, military, and, civilian, computer, networks, after, the, Estoni
                                                  //| an, Government, was, struck, by, cyber, attacks, in, 2007)
val wordAlignments = AlignTest.alignWords(tokenisedSentence.toArray, amr)
                                                  //> wordAlignments  : Array[Option[edu.cmu.lti.nlp.amr.Node]] = Array(Some("NATO
                                                  //| "), Some((consider-02 :ARG0 (military :name (name :op1 "NATO")) :ARG1 (attac
                                                  //| k-01 :mod cyber) :ARG2 (thing :ARG1-of (threaten-01 :ARG2 (and :op1 (network
                                                  //|  :mod military) :op2 (network :mod civilian) :mod computer))) :time (after :
                                                  //| op1 (strike-01 :ARG0 (attack :mod cyber) :ARG1 (government-organization :ARG
                                                  //| 0-of (govern-01 :ARG1 (country :name (name :op1 "Estonia")))) :time (date-en
                                                  //| tity :year 2007))))), Some(cyber), Some((attack :mod cyber)), None, Some((th
                                                  //| reaten-01 :ARG2 (and :op1 (network :mod military) :op2 (network :mod civilia
                                                  //| n) :mod computer))), None, Some(military), Some((and :op1 (network :mod mili
                                                  //| tary) :op2 (network :mod civilian) :mod computer)), Some(civilian), Some(com
                                                  //| puter), Some((network :mod military)), Some((after :op1 (strike-01 :ARG0 (at
                                                  //| tack :mod cyber) :ARG1 (government-organization :ARG0-of (govern-01 :ARG1 (c
                                                  //| ountry :name (name :op1 "Estonia")))) :time (date-entity :year 2007)))), Non
                                                  //| e, Some("Estonia"), Some((government-organization :ARG0-of (govern-01 :ARG1 
                                                  //| (country :name (name :op1 "Estonia"))))), None, Some((strike-01 :ARG0 (attac
                                                  //| k :mod cyber) :ARG1 (government-organization :ARG0-of (govern-01 :ARG1 (coun
                                                  //| try :name (name :op1 "Estonia")))) :time (date-entity :year 2007))), None, S
                                                  //| ome(cyber), Some((attack-01 :mod cyber)), None, Some(2007))
wordAlignments foreach println                    //> Some("NATO")
                                                  //| Some((consider-02 :ARG0 (military :name (name :op1 "NATO")) :ARG1 (attack-01
                                                  //|  :mod cyber) :ARG2 (thing :ARG1-of (threaten-01 :ARG2 (and :op1 (network :mo
                                                  //| d military) :op2 (network :mod civilian) :mod computer))) :time (after :op1 
                                                  //| (strike-01 :ARG0 (attack :mod cyber) :ARG1 (government-organization :ARG0-of
                                                  //|  (govern-01 :ARG1 (country :name (name :op1 "Estonia")))) :time (date-entity
                                                  //|  :year 2007)))))
                                                  //| Some(cyber)
                                                  //| Some((attack :mod cyber))
                                                  //| None
                                                  //| Some((threaten-01 :ARG2 (and :op1 (network :mod military) :op2 (network :mod
                                                  //|  civilian) :mod computer)))
                                                  //| None
                                                  //| Some(military)
                                                  //| Some((and :op1 (network :mod military) :op2 (network :mod civilian) :mod com
                                                  //| puter))
                                                  //| Some(civilian)
                                                  //| Some(computer)
                                                  //| Some((network :mod military))
                                                  //| Some((after :op1 (strike-01 :ARG0 (attack :mod cyber) :ARG1 (government-orga
                                                  //| nization :ARG0-of (govern-01 :ARG1 (country :name (name :op1 "Estonia")))) :
                                                  //| time (date-entity :year 2007))))
                                                  //| None
                                                  //| Some("Estonia")
                                                  //| Some((government-organization :ARG0-of (govern-01 :ARG1 (country :name (name
                                                  //|  :op1 "Estonia")))))
                                                  //| None
                                                  //| Some((strike-01 :ARG0 (attack :mod cyber) :ARG1 (government-organization :AR
                                                  //| G0-of (govern-01 :ARG1 (country :name (name :op1 "Estonia")))) :time (date-e
                                                  //| ntity :year 2007)))
                                                  //| None
                                                  //| Some(cyber)
                                                  //| Some((attack-01 :mod cyber))
                                                  //| None
                                                  //| Some(2007)
AlignSpans.alignSpans(tokenisedSentence.toArray, amr, wordAlignments) foreach println
                                                  //> Some(1)
                                                  //| Some(0)
                                                  //| None
                                                  //| Some(11)
                                                  //| None
                                                  //| Some(3)
                                                  //| None
                                                  //| Some(6)
                                                  //| Some(4)
                                                  //| Some(7)
                                                  //| Some(8)
                                                  //| Some(5)
                                                  //| Some(9)
                                                  //| None
                                                  //| Some(14)
                                                  //| Some(13)
                                                  //| None
                                                  //| Some(10)
                                                  //| None
                                                  //| Some(12)
                                                  //| Some(2)
                                                  //| None
                                                  //| Some(15)
 val amrG =  AMRGraph(amr)                        //> amrG  : amr.AMRGraph = AMRGraph(Map(0.1 -> attack-01, 0.0.0 -> name, 0.3.0.1
                                                  //| .0.0 -> country, 0.2.0.0.0 -> network, 0.1.0 -> cyber, 0.2.0.0.1.0 -> civili
                                                  //| an, 0.3.0.2.0 -> 2007, 0.0 -> military, 0.2.0.0.1 -> network, 0.3.0.1.0 -> g
                                                  //| overn-01, 0.0.0.0 -> "NATO", 0.2.0 -> threaten-01, 0.3.0.0 -> attack, 0.3.0 
                                                  //| -> strike-01, 0.3 -> after, 0.2.0.0.0.0 -> military, 0.2.0.0.2 -> computer, 
                                                  //| 0 -> consider-02, 0.3.0.1 -> government-organization, 0.2 -> thing, 0.3.0.2 
                                                  //| -> date-entity, 0.3.0.1.0.0.0.0 -> "Estonia", ROOT -> ROOT, 0.2.0.0 -> and, 
                                                  //| 0.3.0.1.0.0.0 -> name, 0.3.0.0.0 -> cyber),Map(0.1 -> (21,22), 0.0.0 -> (1,2
                                                  //| ), 0.3.0.1.0.0 -> (15,16), 0.2.0.0.0 -> (12,13), 0.2.0.0.1.0 -> (10,11), 0.3
                                                  //| .0.2.0 -> (23,24), 0.0.0.0 -> (1,2), 0.2.0 -> (6,7), 0.3.0.0 -> (4,5), 0.3.0
                                                  //|  -> (18,19), 0.3 -> (13,14), 0.2.0.0.0.0 -> (8,9), 0.2.0.0.2 -> (11,12), 0 -
                                                  //| > (2,3), 0.3.0.1 -> (16,17), 0.2 -> (6,7), 0.3.0.2 -> (23,24), 0.3.0.1.0.0.0
                                                  //| .0 -> (15,16), 0.2.0.0 -> (9,10), 0.3.0.1.0.0.0 -> (15,16), 0.3.0.0.0 -> (20
                                                  //| ,21)),Map((0.0,0.0.0) -> name, (0.3.0,0.3.0.0) -> ARG0, (ROOT,0) -> ROOT, (0
                                                  //| ,0.3) -> time, (0.0.0,0.0.0.0) -> opN, (0.2.0.0.1,0.2.0.0.1.0) -> mod, (0.1,
                                                  //| 0.1.0) -> mod, (0.3.0,0.3.0.1) -> ARG1, (0.3.0,0.3.0.2) -> time, (0.3.0.0,0.
                                                  //| 3.0.0.0) -> mod, (0,0.2) -> ARG2, (0,0.1) -> ARG1, (0.2.0.0.0,0.2.0.0.0.0) -
                                                  //| > mod, (0.2,0.2.0) -> ARG1-of, (0.3,0.3.0) -> opN, (0.3.0.1.0,0.3.0.1.0.0) -
                                                  //| > ARG1, (0.2.0.0,0.2.0.0.1) -> opN, (0.2.0.0,0.2.0.0.0) -> opN, (0.3.0.1.0.0
                                                  //| .0,0.3.0.1.0.0.0.0) -> opN, (0.3.0.1,0.3.0.1.0) -> ARG0-of, (0,0.0) -> ARG0,
                                                  //|  (0.3.0.1.0.0,0.3.0.1.0.0.0) -> name, (0.2.0,0.2.0.0) -> ARG2, (0.3.0.2,0.3.
                                                  //| 0.2.0) -> year, (0.2.0.0,0.2.0.0.2) -> mod))
 amrG.nodes.keys.toList.sorted foreach (x => println(s"${x}\t\t${amrG.nodes(x)}\t\t${amrG.nodeSpans.getOrElse(x, "No Mapping")}"))
                                                  //> 0		consider-02		(2,3)
                                                  //| 0.0		military		No Mapping
                                                  //| 0.0.0		name		(1,2)
                                                  //| 0.0.0.0		"NATO"		(1,2)
                                                  //| 0.1		attack-01		(21,22)
                                                  //| 0.1.0		cyber		No Mapping
                                                  //| 0.2		thing		(6,7)
                                                  //| 0.2.0		threaten-01		(6,7)
                                                  //| 0.2.0.0		and		(9,10)
                                                  //| 0.2.0.0.0		network		(12,13)
                                                  //| 0.2.0.0.0.0		military		(8,9)
                                                  //| 0.2.0.0.1		network		No Mapping
                                                  //| 0.2.0.0.1.0		civilian		(10,11)
                                                  //| 0.2.0.0.2		computer		(11,12)
                                                  //| 0.3		after		(13,14)
                                                  //| 0.3.0		strike-01		(18,19)
                                                  //| 0.3.0.0		attack		(4,5)
                                                  //| 0.3.0.0.0		cyber		(20,21)
                                                  //| 0.3.0.1		government-organization		(16,17)
                                                  //| 0.3.0.1.0		govern-01		No Mapping
                                                  //| 0.3.0.1.0.0		country		(15,16)
                                                  //| 0.3.0.1.0.0.0		name		(15,16)
                                                  //| 0.3.0.1.0.0.0.0		"Estonia"		(15,16)
                                                  //| 0.3.0.2		date-entity		(23,24)
                                                  //| 0.3.0.2.0		2007		(23,24)
                                                  //| ROOT		ROOT		No Mapping

}