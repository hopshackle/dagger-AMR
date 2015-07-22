package amr
import edu.cmu.lti.nlp.amr._

object AlignmentImprovements {


  val (rawSentence, rawAMR) = AMRGraph.importFile("C:\\AMR\\IRNA.txt")(0)
                                                  //> rawSentence  : String = IRNA stated that in the large-scale army operation a
                                                  //| long the eastern borders with Afghanistan and Pakistan 21 drug traffickers w
                                                  //| ere killed, 1500 arrested and approximately 5 tons of drugs seized.
                                                  //| rawAMR  : String = (s / state-01 :ARG0 (p3 / publication :name (n / name :op
                                                  //| 1 "IRNA")) :ARG1 (a / and :op1 (k / kill-01 :ARG1 (p / person :quant 21 :ARG
                                                  //| 0-of (t / traffic-00 :ARG1 (d / drug)))) :op2 (a2 / arrest-01 :ARG1 (p2 / pe
                                                  //| rson :quant 1500)) :op3 (s2 / seize-01 :ARG1 (m / mass-quantity :unit (t2 / 
                                                  //| ton) :quant (a3 / approximate :op1 5) :consist-of (d2 / drug))) :instrument 
                                                  //| (o / operation :mod (a4 / army) :mod (l / large-scale) :location (a5 / along
                                                  //|  :op1 (b / border :location (a6 / and :op1 (c2 / country :name (n2 / name :o
                                                  //| p1 "Afghanistan")) :op2 (c3 / country :name (n3 / name :op1 "Pakistan"))) :d
                                                  //| irection (e / east))))))
// val (rawSentence, rawAMR) = AMRGraph.importFile("C:\\AMR\\CountryList.txt")(0)

  //    val tokenisedSentence = rawSentence.split(" ")
  val amr = Graph.parse(rawAMR)                   //> amr  : edu.cmu.lti.nlp.amr.Graph = Graph((state-01 :ARG0 (publication :name 
                                                  //| (name :op1 "IRNA")) :ARG1 (and :op1 (kill-01 :ARG1 (person :quant 21 :ARG0-o
                                                  //| f (traffic-00 :ARG1 drug))) :op2 (arrest-01 :ARG1 (person :quant 1500)) :op3
                                                  //|  (seize-01 :ARG1 (mass-quantity :unit ton :quant (approximate :op1 5) :consi
                                                  //| st-of drug)) :instrument (operation :mod army :mod large-scale :location (al
                                                  //| ong :op1 (border :location (and :op1 (country :name (name :op1 "Afghanistan"
                                                  //| )) :op2 (country :name (name :op1 "Pakistan"))) :direction east))))),ArrayBu
                                                  //| ffer(),Map(0.1.3.2.0.0.1.0 -> (name :op1 "Pakistan"), 0.0.0 -> (name :op1 "I
                                                  //| RNA"), 0.1.3.1 -> large-scale, 0.1.1.0 -> (person :quant 1500), 0.1.2.0.1.0 
                                                  //| -> 5, 0.1.2.0.0 -> ton, 0.0.0.0 -> "IRNA", 0.1.3.2.0.0.1 -> (country :name (
                                                  //| name :op1 "Pakistan")), 0.1.1 -> (arrest-01 :ARG1 (person :quant 1500)), 0.1
                                                  //|  -> (and :op1 (kill-01 :ARG1 (person :quant 21 :ARG0-of (traffic-00 :ARG1 dr
                                                  //| ug))) :op2 (arrest-01 :ARG1 (person :quant 1500)) :op3 (seize-01 :ARG1 (mass
                                                  //| -quantity :unit ton :quant (approximate :op1 5) :consist-of drug)) :instrume
                                                  //| nt (operation :mod army :mod large-scale :location (along :op1 (border :loca
                                                  //| tion (and :op1 (country :name (name :op1 "Afghanistan")) :op2 (country :name
                                                  //|  (name :op1 "Pakistan"))) :direction east)))), 0.1.3.2.0.0.0.0.0 -> "Afghani
                                                  //| stan", 0.1.3.2.0.0.0.0 -> (name :op1 "Afghanistan"), 0.1.0.0.1 -> (traffic-0
                                                  //| 0 :ARG1 drug), 0.1.0.0 -> (person :quant 21 :ARG0-of (traffic-00 :ARG1 drug)
                                                  //| ), 0.1.3.0 -> army, 0.1.3.2.0.1 -> east, 0.1.3.2.0.0.0 -> (country :name (na
                                                  //| me :op1 "Afghanistan")), 0.1.2.0.2 -> drug, 0.1.3.2.0.0.1.0.0 -> "Pakistan",
                                                  //|  0.1.0 -> (kill-01 :ARG1 (person :quant 21 :ARG0-of (traffic-00 :ARG1 drug))
                                                  //| ), 0.0 -> (publication :name (name :op1 "IRNA")), 0.1.3.2 -> (along :op1 (bo
                                                  //| rder :location (and :op1 (country :name (name :op1 "Afghanistan")) :op2 (cou
                                                  //| ntry :name (name :op1 "Pakistan"))) :direction east)), 0.1.0.0.1.0 -> drug, 
                                                  //| 0.1.0.0.0 -> 21, 0.1.3.2.0 -> (border :location (and :op1 (country :name (na
                                                  //| me :op1 "Afghanistan")) :op2 (country :name (name :op1 "Pakistan"))) :direct
                                                  //| ion east), 0.1.3 -> (operation :mod army :mod large-scale :location (along :
                                                  //| op1 (border :location (and :op1 (country :name (name :op1 "Afghanistan")) :o
                                                  //| p2 (country :name (name :op1 "Pakistan"))) :direction east))), 0 -> (state-0
                                                  //| 1 :ARG0 (publication :name (name :op1 "IRNA")) :ARG1 (and :op1 (ki
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
                                                  //| im.crf.ser.gz ... done [3.9 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [3.5 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [2.9 sec].
                                                  //| tokenisedSentence  : List[String] = List(IRNA, stated, that, in, the, large-
                                                  //| scale, army, operation, along, the, eastern, borders, with, Afghanistan, and
                                                  //| , Pakistan, 21, drug, traffickers, were, killed, 1500, arrested, and, approx
                                                  //| imately, 5, tons, of, drugs, seized)
val wordAlignments = AlignWords.alignWords(tokenisedSentence.toArray, amr)
                                                  //> WARNING: Found duplicate match for concept and
                                                  //| WARNING: Found duplicate match for concept drug
                                                  //| wordAlignments  : Array[Option[edu.cmu.lti.nlp.amr.Node]] = Array(Some("IRNA
                                                  //| "), Some((state-01 :ARG0 (publication :name (name :op1 "IRNA")) :ARG1 (and :
                                                  //| op1 (kill-01 :ARG1 (person :quant 21 :ARG0-of (traffic-00 :ARG1 drug))) :op2
                                                  //|  (arrest-01 :ARG1 (person :quant 1500)) :op3 (seize-01 :ARG1 (mass-quantity 
                                                  //| :unit ton :quant (approximate :op1 5) :consist-of drug)) :instrument (operat
                                                  //| ion :mod army :mod large-scale :location (along :op1 (border :location (and 
                                                  //| :op1 (country :name (name :op1 "Afghanistan")) :op2 (country :name (name :op
                                                  //| 1 "Pakistan"))) :direction east)))))), None, None, None, Some(large-scale), 
                                                  //| Some(army), Some((operation :mod army :mod large-scale :location (along :op1
                                                  //|  (border :location (and :op1 (country :name (name :op1 "Afghanistan")) :op2 
                                                  //| (country :name (name :op1 "Pakistan"))) :direction east)))), Some((along :op
                                                  //| 1 (border :location (and :op1 (country :name (name :op1 "Afghanistan")) :op2
                                                  //|  (country :name (name :op1 "Pakistan"))) :direction east))), None, Some(east
                                                  //| ), Some((border :location (and :op1 (country :name (name :op1 "Afghanistan")
                                                  //| ) :op2 (country :name (name :op1 "Pakistan"))) :direction east)), None, Some
                                                  //| ("Afghanistan"), Some((and :op1 (kill-01 :ARG1 (person :quant 21 :ARG0-of (t
                                                  //| raffic-00 :ARG1 drug))) :op2 (arrest-01 :ARG1 (person :quant 1500)) :op3 (se
                                                  //| ize-01 :ARG1 (mass-quantity :unit ton :quant (approximate :op1 5) :consist-o
                                                  //| f drug)) :instrument (operation :mod army :mod large-scale :location (along 
                                                  //| :op1 (border :location (and :op1 (country :name (name :op1 "Afghanistan")) :
                                                  //| op2 (country :name (name :op1 "Pakistan"))) :direction east))))), Some("Paki
                                                  //| stan"), Some(21), Some(drug), Some((traffic-00 :ARG1 drug)), None, Some((kil
                                                  //| l-01 :ARG1 (person :quant 21 :ARG0-of (traffic-00 :ARG1 drug)))), Some(1500)
                                                  //| , Some((arrest-01 :ARG1 (person :quant 1500))), Some((and :op1 (country :nam
                                                  //| e (name :op1 "Afghanistan")) :op2 (country :name (name :op1 "Pakistan")))), 
                                                  //| Some((approximate :op1 5)), Some(5), Some(ton), None, Some(drug), Some((seiz
                                                  //| e-01 :ARG1 (mass-quantity :unit ton :quant (approximate :op1 5) :consist-of 
                                                  //| drug))))
wordAlignments foreach println                    //> Some("IRNA")
                                                  //| Some((state-01 :ARG0 (publication :name (name :op1 "IRNA")) :ARG1 (and :op1 
                                                  //| (kill-01 :ARG1 (person :quant 21 :ARG0-of (traffic-00 :ARG1 drug))) :op2 (ar
                                                  //| rest-01 :ARG1 (person :quant 1500)) :op3 (seize-01 :ARG1 (mass-quantity :uni
                                                  //| t ton :quant (approximate :op1 5) :consist-of drug)) :instrument (operation 
                                                  //| :mod army :mod large-scale :location (along :op1 (border :location (and :op1
                                                  //|  (country :name (name :op1 "Afghanistan")) :op2 (country :name (name :op1 "P
                                                  //| akistan"))) :direction east))))))
                                                  //| None
                                                  //| None
                                                  //| None
                                                  //| Some(large-scale)
                                                  //| Some(army)
                                                  //| Some((operation :mod army :mod large-scale :location (along :op1 (border :lo
                                                  //| cation (and :op1 (country :name (name :op1 "Afghanistan")) :op2 (country :na
                                                  //| me (name :op1 "Pakistan"))) :direction east))))
                                                  //| Some((along :op1 (border :location (and :op1 (country :name (name :op1 "Afgh
                                                  //| anistan")) :op2 (country :name (name :op1 "Pakistan"))) :direction east)))
                                                  //| None
                                                  //| Some(east)
                                                  //| Some((border :location (and :op1 (country :name (name :op1 "Afghanistan")) :
                                                  //| op2 (country :name (name :op1 "Pakistan"))) :direction east))
                                                  //| None
                                                  //| Some("Afghanistan")
                                                  //| Some((and :op1 (kill-01 :ARG1 (person :quant 21 :ARG0-of (traffic-00 :ARG1 d
                                                  //| rug))) :op2 (arrest-01 :ARG1 (person :quant 1500)) :op3 (seize-01 :ARG1 (mas
                                                  //| s-quantity :unit ton :quant (approximate :op1 5) :consist-of drug)) :instrum
                                                  //| ent (operation :mod army :mod large-scale :location (along :op1 (border :loc
                                                  //| ation (and :op1 (country :name (name :op1 "Afghanistan")) :op2 (country :nam
                                                  //| e (name :op1 "Pakistan"))) :direction east)))))
                                                  //| Some("Pakistan")
                                                  //| Some(21)
                                                  //| Some(drug)
                                                  //| Some((traffic-00 :ARG1 drug))
                                                  //| None
                                                  //| Some((kill-01 :ARG1 (person :quant 21 :ARG0-of (traffic-00 :ARG1 drug))))
                                                  //| Some(1500)
                                                  //| Some((arrest-01 :ARG1 (person :quant 1500)))
                                                  //| Some((and :op1 (country :name (name :op1 "Afghanistan")) :op2 (country :name
                                                  //|  (name :op1 "Pakistan"))))
                                                  //| Some((approximate :op1 5))
                                                  //| Some(5)
                                                  //| Some(ton)
                                                  //| None
                                                  //| Some(drug)
                                                  //| Some((seize-01 :ARG1 (mass-quantity :unit ton :quant (approximate :op1 5) :c
                                                  //| onsist-of drug)))
AlignSpans.alignSpans(tokenisedSentence.toArray, amr, wordAlignments) foreach println
                                                  //> Some(1)
                                                  //| Some(0)
                                                  //| None
                                                  //| None
                                                  //| None
                                                  //| Some(13)
                                                  //| Some(12)
                                                  //| Some(11)
                                                  //| Some(14)
                                                  //| None
                                                  //| Some(19)
                                                  //| Some(15)
                                                  //| None
                                                  //| Some(17)
                                                  //| Some(2)
                                                  //| Some(18)
                                                  //| Some(4)
                                                  //| Some(5)
                                                  //| Some(4)
                                                  //| None
                                                  //| Some(3)
                                                  //| Some(7)
                                                  //| Some(6)
                                                  //| Some(16)
                                                  //| Some(9)
                                                  //| Some(10)
                                                  //| Some(9)
                                                  //| None
                                                  //| Some(9)
                                                  //| Some(8)
 val amrG =  AMRGraph(amr)                        //> amrG  : amr.AMRGraph = AMRGraph(Map(0.1 -> and, 0.0.0 -> name, 0.1.3.2.0.1 -
                                                  //| > east, 0.1.3.2.0.0.0.0.0 -> "Afghanistan", 0.1.3.2.0.0.0 -> country, 0.1.3.
                                                  //| 2.0.0.0.0 -> name, 0.1.2.0.0 -> ton, 0.1.0 -> kill-01, 0.1.3.2.0.0 -> and, 0
                                                  //| .1.0.0.1.0 -> drug, 0.1.0.0.0 -> 21, 0.0 -> publication, 0.1.3.2.0.0.1.0 -> 
                                                  //| name, 0.1.2.0.1 -> approximate, 0.1.3.2 -> along, 0.0.0.0 -> "IRNA", 0.1.3 -
                                                  //| > operation, 0.1.1.0.0 -> 1500, 0.1.0.0.1 -> traffic-00, 0.1.3.2.0.0.1.0.0 -
                                                  //| > "Pakistan", 0.1.0.0 -> person, 0.1.2.0 -> mass-quantity, 0.1.3.1 -> large-
                                                  //| scale, 0 -> state-01, 0.1.2 -> seize-01, 0.1.2.0.2 -> drug, 0.1.3.0 -> army,
                                                  //|  0.1.3.2.0 -> border, 0.1.1.0 -> person, ROOT -> ROOT, 0.1.3.2.0.0.1 -> coun
                                                  //| try, 0.1.1 -> arrest-01, 0.1.2.0.1.0 -> 5),Map(0.1 -> (15,16), 0.0.0 -> (1,2
                                                  //| ), 0.1.3.2.0.1 -> (11,12), 0.1.3.2.0.0.0.0.0 -> (14,15), 0.1.3.2.0.0.0 -> (1
                                                  //| 4,15), 0.1.3.2.0.0.0.0 -> (14,15), 0.1.2.0.0 -> (25,30), 0.1.0 -> (21,22), 0
                                                  //| .1.3.2.0.0 -> (24,25), 0.1.0.0.1.0 -> (18,19), 0.1.0.0.0 -> (17,20), 0.1.3.2
                                                  //| .0.0.1.0 -> (16,17), 0.1.2.0.1 -> (25,30), 0.1.3.2 -> (9,10), 0.0.0.0 -> (1,
                                                  //| 2), 0.1.3 -> (8,9), 0.1.1.0.0 -> (22,23), 0.1.0.0.1 -> (17,20), 0.1.3.2.0.0.
                                                  //| 1.0.0 -> (16,17), 0.1.0.0 -> (17,20), 0.1.2.0 -> (25,30), 0.1.3.1 -> (6,7), 
                                                  //| 0 -> (2,3), 0.1.2 -> (30,31), 0.1.2.0.2 -> (25,30), 0.1.3.0 -> (7,8), 0.1.3.
                                                  //| 2.0 -> (12,13), 0.1.1.0 -> (22,23), 0.1.3.2.0.0.1 -> (16,17), 0.1.1 -> (23,2
                                                  //| 4), 0.1.2.0.1.0 -> (26,27)),Map((0.0,0.0.0) -> name, (ROOT,0) -> ROOT, (0.1.
                                                  //| 1.0,0.1.1.0.0) -> quant, (0.1.3.2,0.1.3.2.0) -> opN, (0.1.2.0.1,0.1.2.0.1.0)
                                                  //|  -> opN, (0.1.3.2.0.0.1,0.1.3.2.0.0.1.0) -> name, (0.0.0,0.0.0.0) -> opN, (0
                                                  //| .1,0.1.0) -> opN, (0.1,0.1.2) -> opN, (0.1.3,0.1.3.2) -> location, (0.1.3.2.
                                                  //| 0.0,0.1.3.2.0.0.0) -> opN, (0.1.2.0,0.1.2.0.0) -> unit, (0.1.0,0.1.0.0) -> A
                                                  //| RG1, (0.1.2.0,0.1.2.0.2) -> consist-of, (0,0.1) -> ARG1, (0.1.0.0.1,0.1.0.0.
                                                  //| 1.0) -> ARG1, (0.1.3.2.0.0,0.1.3.2.0.0.1) -> opN, (0.1,0.1.3) -> instrument,
                                                  //|  (0.1.3.2.0,0.1.3.2.0.1) -> direction, (0.1.3,0.1.3.0) -> mod, (0.1.3.2.0.0.
                                                  //| 1.0,0.1.3.2.0.0.1.0.0) -> opN, (0.1.0.0,0.1.0.0.0) -> quant, (0.1.1,0.1.1.0)
                                                  //|  -> ARG1, (0.1.3.2.0,0.1.3.2.0.0) -> location, (0.1.2.0,0.1.2.0.1) -> quant,
                                                  //|  (0.1.0.0,0.1.0.0.1) -> ARG0-of, (0,0.0) -> ARG0, (0.1.2,0.1.2.0) -> ARG1, (
                                                  //| 0.1.3.2.0.0.0.0,0.1.3.2.0.0.0.0.0) -> opN, (0.1.3,0.1.3.1) -> mod, (0.1,0.1.
                                                  //| 1) -> opN, (0.1.3.2.0.0.0,0.1.3.2.0.0.0.0) -> name))
 amrG.nodes.keys.toList.sorted foreach (x => println(s"${x}\t\t${amrG.nodes(x)}\t\t${amrG.nodeSpans.getOrElse(x, "No Mapping")}"))
                                                  //> 0		state-01		(2,3)
                                                  //| 0.0		publication		No Mapping
                                                  //| 0.0.0		name		(1,2)
                                                  //| 0.0.0.0		"IRNA"		(1,2)
                                                  //| 0.1		and		(15,16)
                                                  //| 0.1.0		kill-01		(21,22)
                                                  //| 0.1.0.0		person		(17,20)
                                                  //| 0.1.0.0.0		21		(17,20)
                                                  //| 0.1.0.0.1		traffic-00		(17,20)
                                                  //| 0.1.0.0.1.0		drug		(18,19)
                                                  //| 0.1.1		arrest-01		(23,24)
                                                  //| 0.1.1.0		person		(22,23)
                                                  //| 0.1.1.0.0		1500		(22,23)
                                                  //| 0.1.2		seize-01		(30,31)
                                                  //| 0.1.2.0		mass-quantity		(25,30)
                                                  //| 0.1.2.0.0		ton		(25,30)
                                                  //| 0.1.2.0.1		approximate		(25,30)
                                                  //| 0.1.2.0.1.0		5		(26,27)
                                                  //| 0.1.2.0.2		drug		(25,30)
                                                  //| 0.1.3		operation		(8,9)
                                                  //| 0.1.3.0		army		(7,8)
                                                  //| 0.1.3.1		large-scale		(6,7)
                                                  //| 0.1.3.2		along		(9,10)
                                                  //| 0.1.3.2.0		border		(12,13)
                                                  //| 0.1.3.2.0.0		and		(24,25)
                                                  //| 0.1.3.2.0.0.0		country		(14,15)
                                                  //| 0.1.3.2.0.0.0.0		name		(14,15)
                                                  //| 0.1.3.2.0.0.0.0.0		"Afghanistan"		(14,15)
                                                  //| 0.1.3.2.0.0.1		country		(16,17)
                                                  //| 0.1.3.2.0.0.1.0		name		(16,17)
                                                  //| 0.1.3.2.0.0.1.0.0		"Pakistan"		(16,17)
                                                  //| 0.1.3.2.0.1		east		(11,12)
                                                  //| ROOT		ROOT		No Mapping

}