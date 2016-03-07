package amr
import edu.cmu.lti.nlp.amr._
object AlignmentPourdamghani {
WangXueTransitionSystem.wikification = false
  AMRGraph.usePourdamghaniAligner = true
  ImportConcepts.initialise("C:\\AMR\\Estonian.txt")
  val (rawSentence, rawAMR, id) = AMRGraph.importFile("C:\\AMR\\Estonian.txt")(0)
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
                                                  //| id  : String = # ::id PROXY_APW_ENG_20080514_1125.4
 val JAMRamr = Graph.parse(rawAMR)                //> JAMRamr  : edu.cmu.lti.nlp.amr.Graph = Graph((consider-02 :ARG0 (military :n
                                                  //| ame (name :op1 "NATO")) :ARG1 (attack-01 :mod cyber) :ARG2 (thing :ARG1-of (
                                                  //| threaten-01 :ARG2 (and :op1 (network :mod military) :op2 (network :mod civil
                                                  //| ian) :mod computer))) :time (after :op1 (strike-01 :ARG0 (attack :mod cyber)
                                                  //|  :ARG1 (government-organization :ARG0-of (govern-01 :ARG1 (country :name (na
                                                  //| me :op1 "Estonia")))) :time (date-entity :year 2007)))),ArrayBuffer(),Map(0.
                                                  //| 3.0.0 -> (attack :mod cyber), 0.3.0.0.0 -> cyber, 0.2.0.0.1 -> (network :mod
                                                  //|  civilian), 0.0.0 -> (name :op1 "NATO"), 0.3.0.1.0.0.0 -> (name :op1 "Estoni
                                                  //| a"), 0.0.0.0 -> "NATO", 0.3.0.2 -> (date-entity :year 2007), 0.2.0 -> (threa
                                                  //| ten-01 :ARG2 (and :op1 (network :mod military) :op2 (network :mod civilian) 
                                                  //| :mod computer)), 0.1 -> (attack-01 :mod cyber), 0.2.0.0.0 -> (network :mod m
                                                  //| ilitary), 0.3.0.2.0 -> 2007, 0.2.0.0.1.0 -> civilian, 0.3.0.1 -> (government
                                                  //| -organization :ARG0-of (govern-01 :ARG1 (country :name (name :op1 "Estonia")
                                                  //| ))), 0.3.0.1.0.0 -> (country :name (name :op1 "Estonia")), 0.1.0 -> cyber, 0
                                                  //| .0 -> (military :name (name :op1 "NATO")), 0.2.0.0 -> (and :op1 (network :mo
                                                  //| d military) :op2 (network :mod civilian) :mod computer), 0.3 -> (after :op1 
                                                  //| (strike-01 :ARG0 (attack :mod cyber) :ARG1 (government-organization :ARG0-of
                                                  //|  (govern-01 :ARG1 (country :name (name :op1 "Estonia")))) :time (date-entity
                                                  //|  :year 2007))), 0.2.0.0.0.0 -> military, 0 -> (consider-02 :ARG0 (military :
                                                  //| name (name :op1 "NATO")) :ARG1 (attack-01 :mod cyber) :ARG2 (thing :ARG1-of 
                                                  //| (threaten-01 :ARG2 (and :op1 (network :mod military) :op2 (network :mod civi
                                                  //| lian) :mod computer))) :time (after :op1 (strike-01 :ARG0 (attack :mod cyber
                                                  //| ) :ARG1 (government-organization :ARG0-of (govern-01 :ARG1 (country :name (n
                                                  //| ame :op1 "Estonia")))) :time (date-entity :year 2007)))), 0.3.0.1.0.0.0.0 ->
                                                  //|  "Estonia", 0.2.0.0.2 -> computer, 0.3.0.1.0 -> (govern-01 :ARG1 (country :n
                                                  //| ame (name :op1 "Estonia"))), 0.3.0 -> (strike-01 :ARG0 (attack :mod cyber) :
                                                  //| ARG1 (government-organization :ARG0-of (govern-01 :ARG1 (country :name (name
                                                  //|  :op1 "Estonia")))) :time (date-entity :year 2007)), 0.2 -> (thing :ARG1-of 
                                                  //| (threaten-01 :ARG2 (and :op1 (network :mod military) :op2 (network :mod civi
                                                  //| lian) :mod computer)))),Map(n -> (name :op1 "NATO"), t -> (thing :ARG1-of (t
                                                  //| hreaten-01 :ARG2 (and :op1 (network :mod military) :op2 (network :
                                                  //| Output exceeds cutoff limit.
 JAMRamr.nodes foreach (x => println(x.concept + " : " + x.relations))
                                                  //> attack : List((:mod,cyber))
                                                  //| cyber : List()
                                                  //| network : List((:mod,civilian))
                                                  //| name : List((:op1,"NATO"))
                                                  //| name : List((:op1,"Estonia"))
                                                  //| "NATO" : List()
                                                  //| date-entity : List((:year,2007))
                                                  //| threaten-01 : List((:ARG2,(and :op1 (network :mod military) :op2 (network :m
                                                  //| od civilian) :mod computer)))
                                                  //| attack-01 : List((:mod,cyber))
                                                  //| network : List((:mod,military))
                                                  //| 2007 : List()
                                                  //| civilian : List()
                                                  //| government-organization : List((:ARG0-of,(govern-01 :ARG1 (country :name (na
                                                  //| me :op1 "Estonia")))))
                                                  //| country : List((:name,(name :op1 "Estonia")))
                                                  //| cyber : List()
                                                  //| military : List((:name,(name :op1 "NATO")))
                                                  //| and : List((:op1,(network :mod military)), (:op2,(network :mod civilian)), (
                                                  //| :mod,computer))
                                                  //| after : List((:op1,(strike-01 :ARG0 (attack :mod cyber) :ARG1 (government-or
                                                  //| ganization :ARG0-of (govern-01 :ARG1 (country :name (name :op1 "Estonia"))))
                                                  //|  :time (date-entity :year 2007))))
                                                  //| military : List()
                                                  //| consider-02 : List((:ARG0,(military :name (name :op1 "NATO"))), (:ARG1,(atta
                                                  //| ck-01 :mod cyber)), (:ARG2,(thing :ARG1-of (threaten-01 :ARG2 (and :op1 (net
                                                  //| work :mod military) :op2 (network :mod civilian) :mod computer)))), (:time,(
                                                  //| after :op1 (strike-01 :ARG0 (attack :mod cyber) :ARG1 (government-organizati
                                                  //| on :ARG0-of (govern-01 :ARG1 (country :name (name :op1 "Estonia")))) :time (
                                                  //| date-entity :year 2007)))))
                                                  //| "Estonia" : List()
                                                  //| computer : List()
                                                  //| govern-01 : List((:ARG1,(country :name (name :op1 "Estonia"))))
                                                  //| strike-01 : List((:ARG0,(attack :mod cyber)), (:ARG1,(government-organizatio
                                                  //| n :ARG0-of (govern-01 :ARG1 (country :name (name :op1 "Estonia"))))), (:time
                                                  //| ,(date-entity :year 2007)))
                                                  //| thing : List((:ARG1-of,(threaten-01 :ARG2 (and :op1 (network :mod military) 
                                                  //| :op2 (network :mod civilian) :mod computer))))
  JAMRamr.nodes foreach (x => println(x.concept + " : " + x.topologicalOrdering))
                                                  //> attack : List((:mod,cyber))
                                                  //| cyber : List()
                                                  //| network : List((:mod,civilian))
                                                  //| name : List((:op1,"NATO"))
                                                  //| name : List((:op1,"Estonia"))
                                                  //| "NATO" : List()
                                                  //| date-entity : List((:year,2007))
                                                  //| threaten-01 : List((:ARG2,(and :op1 (network :mod military) :op2 (network :m
                                                  //| od civilian) :mod computer)))
                                                  //| attack-01 : List((:mod,cyber))
                                                  //| network : List((:mod,military))
                                                  //| 2007 : List()
                                                  //| civilian : List()
                                                  //| government-organization : List((:ARG0-of,(govern-01 :ARG1 (country :name (na
                                                  //| me :op1 "Estonia")))))
                                                  //| country : List((:name,(name :op1 "Estonia")))
                                                  //| cyber : List()
                                                  //| military : List((:name,(name :op1 "NATO")))
                                                  //| and : List((:op1,(network :mod military)), (:op2,(network :mod civilian)), (
                                                  //| :mod,computer))
                                                  //| after : List((:op1,(strike-01 :ARG0 (attack :mod cyber) :ARG1 (government-or
                                                  //| ganization :ARG0-of (govern-01 :ARG1 (country :name (name :op1 "Estonia"))))
                                                  //|  :time (date-entity :year 2007))))
                                                  //| military : List()
                                                  //| consider-02 : List((:ARG0,(military :name (name :op1 "NATO"))), (:ARG1,(atta
                                                  //| ck-01 :mod cyber)), (:ARG2,(thing :ARG1-of (threaten-01 :ARG2 (and :op1 (net
                                                  //| work :mod military) :op2 (network :mod civilian) :mod computer)))), (:time,(
                                                  //| after :op1 (strike-01 :ARG0 (attack :mod cyber) :ARG1 (government-organizati
                                                  //| on :ARG0-of (govern-01 :ARG1 (country :name (name :op1 "Estonia")))) :time (
                                                  //| date-entity :year 2007)))))
                                                  //| "Estonia" : List()
                                                  //| computer : List()
                                                  //| govern-01 : List((:ARG1,(country :name (name :op1 "Estonia"))))
                                                  //| strike-01 : List((:ARG0,(attack :mod cyber)), (:ARG1,(government-organizatio
                                                  //| n :ARG0-of (govern-01 :ARG1 (country :name (name :op1 "Estonia"))))), (:time
                                                  //| ,(date-entity :year 2007)))
                                                  //| thing : List((:ARG1-of,(threaten-01 :ARG2 (and :op1 (network :mod military) 
                                                  //| :op2 (network :mod civilian) :mod computer))))
  val amr = AMRGraph(JAMRamr)                     //> amr  : amr.AMRGraph = AMRGraph(Map(0.1 -> attack-01, 0.0.0 -> name, 0.3.0.1.
                                                  //| 0.0 -> country, 0.2.0.0.0 -> network, 0.1.0 -> cyber, 0.2.0.0.1.0 -> civilia
                                                  //| n, 0.3.0.2.0 -> 2007, 0.0 -> military, 0.2.0.0.1 -> network, 0.3.0.1.0 -> go
                                                  //| vern-01, 0.0.0.0 -> "NATO", 0.2.0 -> threaten-01, 0.3.0.0 -> attack, 0.3.0 -
                                                  //| > strike-01, 0.3 -> after, 0.2.0.0.0.0 -> military, 0.2.0.0.2 -> computer, 0
                                                  //|  -> consider-02, 0.3.0.1 -> government-organization, 0.2 -> thing, 0.3.0.2 -
                                                  //| > date-entity, 0.3.0.1.0.0.0.0 -> "Estonia", 0.2.0.0 -> and, 0.3.0.1.0.0.0 -
                                                  //| > name, 0.3.0.0.0 -> cyber),Map(),Map((0.0,0.0.0) -> name, (0.3.0,0.3.0.0) -
                                                  //| > ARG0, (0,0.3) -> time, (0.0.0,0.0.0.0) -> opN, (0.2.0.0.1,0.2.0.0.1.0) -> 
                                                  //| mod, (0.1,0.1.0) -> mod, (0.3.0,0.3.0.1) -> ARG1, (0.3.0,0.3.0.2) -> time, (
                                                  //| 0.3.0.0,0.3.0.0.0) -> mod, (0,0.2) -> ARG2, (0,0.1) -> ARG1, (0.2.0.0.0,0.2.
                                                  //| 0.0.0.0) -> mod, (0.2,0.2.0) -> ARG1-of, (0.3,0.3.0) -> opN, (0.3.0.1.0,0.3.
                                                  //| 0.1.0.0) -> ARG1, (0.2.0.0,0.2.0.0.1) -> opN, (0.2.0.0,0.2.0.0.0) -> opN, (0
                                                  //| .3.0.1.0.0.0,0.3.0.1.0.0.0.0) -> opN, (0.3.0.1,0.3.0.1.0) -> ARG0-of, (0,0.0
                                                  //| ) -> ARG0, (0.3.0.1.0.0,0.3.0.1.0.0.0) -> name, (0.2.0,0.2.0.0) -> ARG2, (0.
                                                  //| 3.0.2,0.3.0.2.0) -> year, (0.2.0.0,0.2.0.0.2) -> mod),Map((0.0,0.0.0) -> nam
                                                  //| e, (0.3.0,0.3.0.0) -> ARG0, (0,0.3) -> time, (0.0.0,0.0.0.0) -> op1, (0.2.0.
                                                  //| 0.1,0.2.0.0.1.0) -> mod, (0.1,0.1.0) -> mod, (0.3.0,0.3.0.1) -> ARG1, (0.3.0
                                                  //| ,0.3.0.2) -> time, (0.3.0.0,0.3.0.0.0) -> mod, (0,0.2) -> ARG2, (0,0.1) -> A
                                                  //| RG1, (0.2.0.0.0,0.2.0.0.0.0) -> mod, (0.2,0.2.0) -> ARG1-of, (0.3,0.3.0) -> 
                                                  //| op1, (0.3.0.1.0,0.3.0.1.0.0) -> ARG1, (0.2.0.0,0.2.0.0.1) -> op2, (0.2.0.0,0
                                                  //| .2.0.0.0) -> op1, (0.3.0.1.0.0.0,0.3.0.1.0.0.0.0) -> op1, (0.3.0.1,0.3.0.1.0
                                                  //| ) -> ARG0-of, (0,0.0) -> ARG0, (0.3.0.1.0.0,0.3.0.1.0.0.0) -> name, (0.2.0,0
                                                  //| .2.0.0) -> ARG2, (0.3.0.2,0.3.0.2.0) -> year, (0.2.0.0,0.2.0.0.2) -> mod),Li
                                                  //| st())
  PourdamghaniAligner.importTokenization          //> res0: Map[String,(String, String)] = Map(# ::id PROXY_APW_ENG_20080514_1125.
                                                  //| 4 -> (# ::tok NATO CONSIDERS cyber attacks a threat to military and civilian
                                                  //|  computer networks after the Estonian Government was struck by cyber attacks
                                                  //|  in 2007 .,# ::alignments 0-1.1.2.1 1-1 2-1.2.1 3-1.2 5-1.3 5-1.3.1 5-1.3.1.
                                                  //| r 6-1.3.1.1.r 7-1.3.1.1.1.1 8-1.3.1.1 9-1.3.1.1.2.1 10-1.3.1.1.3 11-1.3.1.1.
                                                  //| 1 11-1.3.1.1.2 12-1.4 14-1.4.1.1.1.1.2.1 15-1.4.1.1 15-1.4.1.1.1 15-1.4.1.1.
                                                  //| 1.r 17-1.4.1 18-1.4.1.2.r 19-1.4.1.2.1 20-1.4.1.2 21-1.4.1.3.r 22-1.4.1.3.1)
                                                  //| )
   val pa = PourdamghaniAligner(DependencyTree.preProcess(rawSentence), amr, id)
                                                  //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.3 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [5.3 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [2.3 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [5.1 sec].
                                                  //| FullName = 0.0
                                                  //| FullName = 0.3.0.1.0.0
                                                  //| pa  : amr.PourdamghaniAligner = PourdamghaniAligner(List(NATO, CONSIDERS, cy
                                                  //| ber, attacks, a, threat, to, military, and, civilian, computer, networks, af
                                                  //| ter, the, Estonian, Government, was, struck, by, cyber, attacks, in, 2007),A
                                                  //| MRGraph(Map(0.1 -> attack-01, 0.0.0 -> name, 0.3.0.1.0.0 -> country, 0.2.0.0
                                                  //| .0 -> network, 0.1.0 -> cyber, 0.2.0.0.1.0 -> civilian, 0.3.0.2.0 -> 2007, 0
                                                  //| .0 -> military, 0.2.0.0.1 -> network, 0.3.0.1.0 -> govern-01, 0.0.0.0 -> "NA
                                                  //| TO", 0.2.0 -> threaten-01, 0.3.0.0 -> attack, 0.3.0 -> strike-01, 0.3 -> aft
                                                  //| er, 0.2.0.0.0.0 -> military, 0.2.0.0.2 -> computer, 0 -> consider-02, 0.3.0.
                                                  //| 1 -> government-organization, 0.2 -> thing, 0.3.0.2 -> date-entity, 0.3.0.1.
                                                  //| 0.0.0.0 -> "Estonia", 0.2.0.0 -> and, 0.3.0.1.0.0.0 -> name, 0.3.0.0.0 -> cy
                                                  //| ber),Map(),Map((0.0,0.0.0) -> name, (0.3.0,0.3.0.0) -> ARG0, (0,0.3) -> time
                                                  //| , (0.0.0,0.0.0.0) -> opN, (0.2.0.0.1,0.2.0.0.1.0) -> mod, (0.1,0.1.0) -> mod
                                                  //| , (0.3.0,0.3.0.1) -> ARG1, (0.3.0,0.3.0.2) -> time, (0.3.0.0,0.3.0.0.0) -> m
                                                  //| od, (0,0.2) -> ARG2, (0,0.1) -> ARG1, (0.2.0.0.0,0.2.0.0.0.0) -> mod, (0.2,0
                                                  //| .2.0) -> ARG1-of, (0.3,0.3.0) -> opN, (0.3.0.1.0,0.3.0.1.0.0) -> ARG1, (0.2.
                                                  //| 0.0,0.2.0.0.1) -> opN, (0.2.0.0,0.2.0.0.0) -> opN, (0.3.0.1.0.0.0,0.3.0.1.0.
                                                  //| 0.0.0) -> opN, (0.3.0.1,0.3.0.1.0) -> ARG0-of, (0,0.0) -> ARG0, (0.3.0.1.0.0
                                                  //| ,0.3.0.1.0.0.0) -> name, (0.2.0,0.2.0.0) -> ARG2, (0.3.0.2,0.3.0.2.0) -> yea
                                                  //| r, (0.2.0.0,0.2.0.0.2) -> mod),Map((0.0,0.0.0) -> name, (0.3.0,0.3.0.0) -> A
                                                  //| RG0, (0,0.3) -> time, (0.0.0,0.0.0.0) -> op1, (0.2.0.0.1,0.2.0.0.1.0) -> mod
                                                  //| , (0.1,0.1.0) -> mod, (0.3.0,0.3.0.1) -> ARG1, (0.3.0,0.3.0.2) -> time, (0.3
                                                  //| .0.0,0.3.0.0.0) -> mod, (0,0.2) -> ARG2, (0,0.1) -> ARG1, (0.2.0.0.0,0.2.0.0
                                                  //| .0.0) -> mod, (0.2,0.2.0) -> ARG1-of, (0.3,
                                                  //| Output exceeds cutoff limit.
                                                  
  DependencyTree.preProcess(rawSentence)          //> res1: List[String] = List(NATO, CONSIDERS, cyber, attacks, a, threat, to, mi
                                                  //| litary, and, civilian, computer, networks, after, the, Estonian, Government,
                                                  //|  was, struck, by, cyber, attacks, in, 2007)
  val mapping = pa.mapToAMR                       //> AMR Key = 0.0.1.0
                                                  //| applyRule: 0.0.1.0 => 0.0.0.0
                                                  //| AMR Key = 0
                                                  //| AMR Key = 0.1.0
                                                  //| AMR Key = 0.1
                                                  //| AMR Key = 0.2
                                                  //| AMR Key = 0.2.0
                                                  //| AMR Key = 0.2.0.0.0.0
                                                  //| AMR Key = 0.2.0.0
                                                  //| AMR Key = 0.2.0.0.1.0
                                                  //| AMR Key = 0.2.0.0.2
                                                  //| AMR Key = 0.2.0.0.0
                                                  //| AMR Key = 0.2.0.0.1
                                                  //| AMR Key = 0.3
                                                  //| AMR Key = 0.3.0.0.0.0.1.0
                                                  //| AMR Key = 0.3.0.0
                                                  //| AMR Key = 0.3.0.0.0
                                                  //| AMR Key = 0.3.0
                                                  //| AMR Key = 0.3.0.1.0
                                                  //| AMR Key = 0.3.0.1
                                                  //| AMR Key = 0.3.0.2.0
                                                  //| mapping  : amr.AMRGraph = AMRGraph(Map(0.1 -> attack-01, 0.0.0 -> name, 0.3.
                                                  //| 0.1.0.0 -> country, 0.2.0.0.0 -> network, 0.1.0 -> cyber, 0.2.0.0.1.0 -> civ
                                                  //| ilian, 0.3.0.2.0 -> 2007, 0.0 -> military, 0.2.0.0.1 -> network, 0.3.0.1.0 -
                                                  //| > govern-01, 0.0.0.0 -> "NATO", 0.2.0 -> threaten-01, 0.3.0.0 -> attack, 0.3
                                                  //| .0 -> strike-01, 0.3 -> after, 0.2.0.0.0.0 -> military, 0.2.0.0.2 -> compute
                                                  //| r, 0 -> consider-02, 0.3.0.1 -> government-organization, 0.2 -> thing, 0.3.0
                                                  //| .2 -> date-entity, 0.3.0.1.0.0.0.0 -> "Estonia", 0.2.0.0 -> and, 0.3.0.1.0.0
                                                  //| .0 -> name, 0.3.0.0.0 -> cyber),Map(0.1 -> (4,5), 0.2.0.0.0 -> (12,13), 0.1.
                                                  //| 0 -> (3,4), 0.2.0.0.1.0 -> (10,11), 0.3.0.2.0 -> (23,24), 0.3.0.1.0 -> (20,2
                                                  //| 1), 0.0.0.0 -> (1,2), 0.3.0.0 -> (16,17), 0.3.0 -> (18,19), 0.3 -> (13,14), 
                                                  //| 0.2.0.0.0.0 -> (8,9), 0.2.0.0.2 -> (11,12), 0 -> (2,3), 0.3.0.1 -> (21,22), 
                                                  //| 0.2 -> (6,7), 0.2.0.0 -> (9,10), 0.3.0.0.0.0.1.0 -> (15,16)),Map((0.0,0.0.0)
                                                  //|  -> name, (0.3.0,0.3.0.0) -> ARG0, (0,0.3) -> time, (0.0.0,0.0.0.0) -> opN, 
                                                  //| (0.2.0.0.1,0.2.0.0.1.0) -> mod, (0.1,0.1.0) -> mod, (0.3.0,0.3.0.1) -> ARG1,
                                                  //|  (0.3.0,0.3.0.2) -> time, (0.3.0.0,0.3.0.0.0) -> mod, (0,0.2) -> ARG2, (0,0.
                                                  //| 1) -> ARG1, (0.2.0.0.0,0.2.0.0.0.0) -> mod, (0.2,0.2.0) -> ARG1-of, (0.3,0.3
                                                  //| .0) -> opN, (0.3.0.1.0,0.3.0.1.0.0) -> ARG1, (0.2.0.0,0.2.0.0.1) -> opN, (0.
                                                  //| 2.0.0,0.2.0.0.0) -> opN, (0.3.0.1.0.0.0,0.3.0.1.0.0.0.0) -> opN, (0.3.0.1,0.
                                                  //| 3.0.1.0) -> ARG0-of, (0,0.0) -> ARG0, (0.3.0.1.0.0,0.3.0.1.0.0.0) -> name, (
                                                  //| 0.2.0,0.2.0.0) -> ARG2, (0.3.0.2,0.3.0.2.0) -> year, (0.2.0.0,0.2.0.0.2) -> 
                                                  //| mod),Map((0.0,0.0.0) -> name, (0.3.0,0.3.0.0) -> ARG0, (0,0.3) -> time, (0.0
                                                  //| .0,0.0.0.0) -> op1, (0.2.0.0.1,0.2.0.0.1.0) -> mod, (0.1,0.1.0) -> mod, (0.3
                                                  //| .0,0.3.0.1) -> ARG1, (0.3.0,0.3.0.2) -> time, (0.3.0.0,0.3.0.0.0) -> mod, (0
                                                  //| ,0.2) -> ARG2, (0,0.1) -> ARG1, (0.2.0.0.0,0.2.0.0.0.0) -> mod, (0.2,0.2.0) 
                                                  //| -> ARG1-of, (0.3,0.3.0) -> op1, (0.3.0.1.0,0.3.0.1.0.
                                                  //| Output exceeds cutoff limit.
  amr.nodes foreach (x => println(s"${x._1}\t${x._2}\t${mapping.nodeSpans.getOrElse(x._1, "No Mapping")}"))
                                                  //> 0.1	attack-01	(4,5)
                                                  //| 0.0.0	name	No Mapping
                                                  //| 0.3.0.1.0.0	country	No Mapping
                                                  //| 0.2.0.0.0	network	(12,13)
                                                  //| 0.1.0	cyber	(3,4)
                                                  //| 0.2.0.0.1.0	civilian	(10,11)
                                                  //| 0.3.0.2.0	2007	(23,24)
                                                  //| 0.0	military	No Mapping
                                                  //| 0.2.0.0.1	network	No Mapping
                                                  //| 0.3.0.1.0	govern-01	(20,21)
                                                  //| 0.0.0.0	"NATO"	(1,2)
                                                  //| 0.2.0	threaten-01	No Mapping
                                                  //| 0.3.0.0	attack	(16,17)
                                                  //| 0.3.0	strike-01	(18,19)
                                                  //| 0.3	after	(13,14)
                                                  //| 0.2.0.0.0.0	military	(8,9)
                                                  //| 0.2.0.0.2	computer	(11,12)
                                                  //| 0	consider-02	(2,3)
                                                  //| 0.3.0.1	government-organization	(21,22)
                                                  //| 0.2	thing	(6,7)
                                                  //| 0.3.0.2	date-entity	No Mapping
                                                  //| 0.3.0.1.0.0.0.0	"Estonia"	No Mapping
                                                  //| 0.2.0.0	and	(9,10)
                                                  //| 0.3.0.1.0.0.0	name	No Mapping
                                                  //| 0.3.0.0.0	cyber	No Mapping
  Sentence(rawSentence, rawAMR, id)               //> FullName = 0.0
                                                  //| FullName = 0.3.0.1.0.0
                                                  //| AMR Key = 0.0.1.0
                                                  //| applyRule: 0.0.1.0 => 0.0.0.0
                                                  //| AMR Key = 0
                                                  //| AMR Key = 0.1.0
                                                  //| AMR Key = 0.1
                                                  //| AMR Key = 0.2
                                                  //| AMR Key = 0.2.0
                                                  //| AMR Key = 0.2.0.0.0.0
                                                  //| AMR Key = 0.2.0.0
                                                  //| AMR Key = 0.2.0.0.1.0
                                                  //| AMR Key = 0.2.0.0.2
                                                  //| AMR Key = 0.2.0.0.0
                                                  //| AMR Key = 0.2.0.0.1
                                                  //| AMR Key = 0.3
                                                  //| AMR Key = 0.3.0.0.0.0.1.0
                                                  //| AMR Key = 0.3.0.0
                                                  //| AMR Key = 0.3.0.0.0
                                                  //| AMR Key = 0.3.0
                                                  //| AMR Key = 0.3.0.1.0
                                                  //| AMR Key = 0.3.0.1
                                                  //| AMR Key = 0.3.0.2.0
                                                  //| res2: amr.Sentence = # ::id PROXY_APW_ENG_20080514_1125.4
                                                  //| NATO CONSIDERS cyber attacks a threat to military and civilian computer netw
                                                  //| orks after the Estonian Government was struck by cyber attacks in 2007.
                                                  //| 
                                                  //| NodeMap:	Map(1 -> NATO, 2 -> CONSIDERS, 3 -> cyber, 4 -> attacks, 5 -> a,
                                                  //|  6 -> threat, 7 -> to, 8 -> military, 9 -> and, 10 -> civilian, 11 -> comput
                                                  //| er, 12 -> networks, 13 -> after, 14 -> the, 15 -> Estonian, 16 -> Government
                                                  //| , 17 -> was, 18 -> struck, 19 -> by, 20 -> cyber, 21 -> attacks, 22 -> in, 2
                                                  //| 3 -> 2007)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,12
                                                  //| ), 12 -> (12,13), 13 -> (13,14), 14 -> (14,15), 15 -> (15,16), 16 -> (16,17)
                                                  //| , 17 -> (17,18), 18 -> (18,19), 19 -> (19,20), 20 -> (20,21), 21 -> (21,22),
                                                  //|  22 -> (22,23), 23 -> (23,24))
                                                  //| Edges:	Map((3,1) -> nn, (3,2) -> nn, (4,3) -> nsubj, (4,6) -> dobj, (4,
                                                  //| 7) -> prep, (4,18) -> advcl, (6,5) -> det, (7,12) -> pobj, (8,9) -> cc, (8,1
                                                  //| 0) -> conj, (12,8) -> amod, (12,11) -> nn, (16,14) -> det, (16,15) -> amod, 
                                                  //| (18,13) -> mark, (18,16) -> nsubjpass, (18,17) -> auxpass, (18,19) -> prep, 
                                                  //| (19,21) -> pobj, (21,20) -> nn, (21,22) -> prep, (22,23) -> pobj)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map()
                                                  //| PartsOfSpeech:	Map(5 -> DT, 10 -> JJ, 14 -> DT, 20 -> NN, 1 -> NNP, 6 -
                                                  //| > NN, 21 -> NNS, 9 -> CC, 13 -> IN, 2 -> NNP, 17 -> VBD, 22 -> IN, 12 -> NNS
                                                  //| , 7 -> TO, 3 -> NN, 18 -> VBN, 16 -> NN, 11 -> NN, 23 -> CD, 8 -> JJ, 19 -> 
                                                  //| IN, 4 -> VBZ, 15 -> JJ)
                                                  //| DependencyLabels:	Map(5 -> det, 10 -> conj, 14 -> det, 20 -> nn, 1 -> nn, 
                                                  //| 6 -> dobj, 21 -> pobj, 9 -> cc, 13 -> mark, 2 -> nn, 17 -> auxpass, 22 -> pr
                                                  //| ep, 12 -> pobj, 7 -> prep, 3 -> nsubj, 18 -> advcl, 16 -> nsubjpass, 11 -> n
                                                  //| n, 23 -> pobj, 8 -> amod, 19 -> prep, 15 -
                                                  //| Output exceeds cutoff limit.
  amr.arcs foreach println                        //> ((0.0,0.0.0),name)
                                                  //| ((0.3.0,0.3.0.0),ARG0)
                                                  //| ((0,0.3),time)
                                                  //| ((0.0.0,0.0.0.0),opN)
                                                  //| ((0.2.0.0.1,0.2.0.0.1.0),mod)
                                                  //| ((0.1,0.1.0),mod)
                                                  //| ((0.3.0,0.3.0.1),ARG1)
                                                  //| ((0.3.0,0.3.0.2),time)
                                                  //| ((0.3.0.0,0.3.0.0.0),mod)
                                                  //| ((0,0.2),ARG2)
                                                  //| ((0,0.1),ARG1)
                                                  //| ((0.2.0.0.0,0.2.0.0.0.0),mod)
                                                  //| ((0.2,0.2.0),ARG1-of)
                                                  //| ((0.3,0.3.0),opN)
                                                  //| ((0.3.0.1.0,0.3.0.1.0.0),ARG1)
                                                  //| ((0.2.0.0,0.2.0.0.1),opN)
                                                  //| ((0.2.0.0,0.2.0.0.0),opN)
                                                  //| ((0.3.0.1.0.0.0,0.3.0.1.0.0.0.0),opN)
                                                  //| ((0.3.0.1,0.3.0.1.0),ARG0-of)
                                                  //| ((0,0.0),ARG0)
                                                  //| ((0.3.0.1.0.0,0.3.0.1.0.0.0),name)
                                                  //| ((0.2.0,0.2.0.0),ARG2)
                                                  //| ((0.3.0.2,0.3.0.2.0),year)
                                                  //| ((0.2.0.0,0.2.0.0.2),mod)
  }