package amr
import edu.cmu.lti.nlp.amr._
object AlignmentPourdamghani {
  AMRGraph.usePourdamghaniAligner = true
  ImportConcepts.initialise("C:\\AMR\\PIssue4.txt")
  val (rawSentence, rawAMR, id) = AMRGraph.importFile("C:\\AMR\\PIssue4.txt")(0)
                                                  //> rawSentence  : String = We took a taxi along the path of the highway that he
                                                  //| ads toward Disney , trying to experience this mysterious park from close by 
                                                  //| .
                                                  //| rawAMR  : String = (t / take-01 :ARG0 (w / we) :ARG1 (t2 / taxi) :purpose (t
                                                  //| 4 / try-01 :ARG0 w :ARG1 (e / experience-01 :ARG0 w :ARG1 (a / amusement-par
                                                  //| k :wiki "Hong_Kong_Disneyland" :name (n / name :op1 "Disney") :mod (m / myst
                                                  //| erious) :mod (t3 / this)) :location (c / close :op1 a))) :path (a2 / along :
                                                  //| op1 (p2 / path :mod (h / highway :ARG0-of (h2 / head-02 :ARG1 a)))))
                                                  //| id  : String = # ::id bc.cctv_0000.19
 val JAMRamr = Graph.parse(rawAMR)                //> JAMRamr  : edu.cmu.lti.nlp.amr.Graph = Graph((take-01 :ARG0 we :ARG1 taxi :p
                                                  //| urpose (try-01 :ARG1 (experience-01 :ARG1 (amusement-park :wiki "Hong_Kong_D
                                                  //| isneyland" :name (name :op1 "Disney") :mod mysterious :mod this) :location (
                                                  //| close :op1 amusement-park) :ARG0 we) :ARG0 we) :path (along :op1 (path :mod 
                                                  //| (highway :ARG0-of (head-02 :ARG1 amusement-park))))),ArrayBuffer(),Map(0.3.0
                                                  //| .0.0 -> (head-02 :ARG1 amusement-park), 0.3.0.0 -> (highway :ARG0-of (head-0
                                                  //| 2 :ARG1 amusement-park)), 0.2.1.1 -> (amusement-park :wiki "Hong_Kong_Disney
                                                  //| land" :name (name :op1 "Disney") :mod mysterious :mod this), 0.1 -> taxi, 0.
                                                  //| 2.1.1.0 -> "Hong_Kong_Disneyland", 0.2.1.1.1.0 -> "Disney", 0.2.1.1.3 -> thi
                                                  //| s, 0.2.1.1.2 -> mysterious, 0.0 -> we, 0.3 -> (along :op1 (path :mod (highwa
                                                  //| y :ARG0-of (head-02 :ARG1 amusement-park)))), 0.2.1.2 -> (close :op1 amuseme
                                                  //| nt-park), 0 -> (take-01 :ARG0 we :ARG1 taxi :purpose (try-01 :ARG1 (experien
                                                  //| ce-01 :ARG1 (amusement-park :wiki "Hong_Kong_Disneyland" :name (name :op1 "D
                                                  //| isney") :mod mysterious :mod this) :location (close :op1 amusement-park) :AR
                                                  //| G0 we) :ARG0 we) :path (along :op1 (path :mod (highway :ARG0-of (head-02 :AR
                                                  //| G1 amusement-park))))), 0.2.1.1.1 -> (name :op1 "Disney"), 0.3.0 -> (path :m
                                                  //| od (highway :ARG0-of (head-02 :ARG1 amusement-park))), 0.2 -> (try-01 :ARG1 
                                                  //| (experience-01 :ARG1 (amusement-park :wiki "Hong_Kong_Disneyland" :name (nam
                                                  //| e :op1 "Disney") :mod mysterious :mod this) :location (close :op1 amusement-
                                                  //| park) :ARG0 we) :ARG0 we), 0.2.1 -> (experience-01 :ARG1 (amusement-park :wi
                                                  //| ki "Hong_Kong_Disneyland" :name (name :op1 "Disney") :mod mysterious :mod th
                                                  //| is) :location (close :op1 amusement-park) :ARG0 we)),Map(e -> (experience-01
                                                  //|  :ARG1 (amusement-park :wiki "Hong_Kong_Disneyland" :name (name :op1 "Disney
                                                  //| ") :mod mysterious :mod this) :location (close :op1 amusement-park) :ARG0 we
                                                  //| ), w -> we, n -> (name :op1 "Disney"), t4 -> (try-01 :ARG1 (experience-01 :A
                                                  //| RG1 (amusement-park :wiki "Hong_Kong_Disneyland" :name (name :op1 "Disney") 
                                                  //| :mod mysterious :mod this) :location (close :op1 amusement-park) :ARG0 we) :
                                                  //| ARG0 we), h -> (highway :ARG0-of (head-02 :ARG1 amusement-park)), h2 -> (hea
                                                  //| d-02 :ARG1 amusement-park), t -> (take-01 :ARG0 we :ARG1 taxi :purpose (try-
                                                  //| 01 :ARG1 (experience-01 :ARG1 (amusement-park :wiki "Hong_Kong_Disneyland" :
                                                  //| name (name :op1 "Disney") :mod mysterious :mod this) :location (cl
                                                  //| Output exceeds cutoff limit.
 JAMRamr.nodes foreach (x => println(x.concept + " : " + x.relations))
                                                  //> head-02 : List((:ARG1,(amusement-park :wiki "Hong_Kong_Disneyland" :name (na
                                                  //| me :op1 "Disney") :mod mysterious :mod this)))
                                                  //| highway : List((:ARG0-of,(head-02 :ARG1 amusement-park)))
                                                  //| amusement-park : List((:wiki,"Hong_Kong_Disneyland"), (:name,(name :op1 "Dis
                                                  //| ney")), (:mod,mysterious), (:mod,this))
                                                  //| taxi : List()
                                                  //| "Hong_Kong_Disneyland" : List()
                                                  //| "Disney" : List()
                                                  //| this : List()
                                                  //| mysterious : List()
                                                  //| we : List()
                                                  //| along : List((:op1,(path :mod (highway :ARG0-of (head-02 :ARG1 amusement-par
                                                  //| k)))))
                                                  //| close : List((:op1,(amusement-park :wiki "Hong_Kong_Disneyland" :name (name 
                                                  //| :op1 "Disney") :mod mysterious :mod this)))
                                                  //| take-01 : List((:ARG0,we), (:ARG1,taxi), (:purpose,(try-01 :ARG1 (experience
                                                  //| -01 :ARG1 (amusement-park :wiki "Hong_Kong_Disneyland" :name (name :op1 "Dis
                                                  //| ney") :mod mysterious :mod this) :location (close :op1 amusement-park) :ARG0
                                                  //|  we) :ARG0 we)), (:path,(along :op1 (path :mod (highway :ARG0-of (head-02 :A
                                                  //| RG1 amusement-park))))))
                                                  //| name : List((:op1,"Disney"))
                                                  //| path : List((:mod,(highway :ARG0-of (head-02 :ARG1 amusement-park))))
                                                  //| try-01 : List((:ARG0,we), (:ARG1,(experience-01 :ARG1 (amusement-park :wiki 
                                                  //| "Hong_Kong_Disneyland" :name (name :op1 "Disney") :mod mysterious :mod this)
                                                  //|  :location (close :op1 amusement-park) :ARG0 we)))
                                                  //| experience-01 : List((:ARG0,we), (:ARG1,(amusement-park :wiki "Hong_Kong_Dis
                                                  //| neyland" :name (name :op1 "Disney") :mod mysterious :mod this)), (:location,
                                                  //| (close :op1 amusement-park)))
  JAMRamr.nodes foreach (x => println(x.concept + " : " + x.topologicalOrdering))
                                                  //> head-02 : List()
                                                  //| highway : List((:ARG0-of,(head-02 :ARG1 amusement-park)))
                                                  //| amusement-park : List((:wiki,"Hong_Kong_Disneyland"), (:name,(name :op1 "Dis
                                                  //| ney")), (:mod,mysterious), (:mod,this))
                                                  //| taxi : List()
                                                  //| "Hong_Kong_Disneyland" : List()
                                                  //| "Disney" : List()
                                                  //| this : List()
                                                  //| mysterious : List()
                                                  //| we : List()
                                                  //| along : List((:op1,(path :mod (highway :ARG0-of (head-02 :ARG1 amusement-par
                                                  //| k)))))
                                                  //| close : List()
                                                  //| take-01 : List((:ARG0,we), (:ARG1,taxi), (:purpose,(try-01 :ARG1 (experience
                                                  //| -01 :ARG1 (amusement-park :wiki "Hong_Kong_Disneyland" :name (name :op1 "Dis
                                                  //| ney") :mod mysterious :mod this) :location (close :op1 amusement-park) :ARG0
                                                  //|  we) :ARG0 we)), (:path,(along :op1 (path :mod (highway :ARG0-of (head-02 :A
                                                  //| RG1 amusement-park))))))
                                                  //| name : List((:op1,"Disney"))
                                                  //| path : List((:mod,(highway :ARG0-of (head-02 :ARG1 amusement-park))))
                                                  //| try-01 : List((:ARG1,(experience-01 :ARG1 (amusement-park :wiki "Hong_Kong_D
                                                  //| isneyland" :name (name :op1 "Disney") :mod mysterious :mod this) :location (
                                                  //| close :op1 amusement-park) :ARG0 we)))
                                                  //| experience-01 : List((:ARG1,(amusement-park :wiki "Hong_Kong_Disneyland" :na
                                                  //| me (name :op1 "Disney") :mod mysterious :mod this)), (:location,(close :op1 
                                                  //| amusement-park)))
  val amr = AMRGraph(JAMRamr)                     //> amr  : amr.AMRGraph = AMRGraph(Map(0.1 -> taxi, 0.2.1.1.0 -> "Hong_Kong_Disn
                                                  //| eyland", 0.2.1 -> experience-01, 0.0 -> we, 0.2.1.1.3 -> this, 0.2.1.2 -> cl
                                                  //| ose, 0.3.0.0 -> highway, 0.3.0 -> path, 0.2.1.1.2 -> mysterious, 0.3 -> alon
                                                  //| g, 0 -> take-01, 0.2.1.1 -> amusement-park, 0.2.1.1.1.0 -> "Disney", 0.2 -> 
                                                  //| try-01, 0.2.1.1.1 -> name, 0.3.0.0.0 -> head-02),Map(),Map((0.3.0,0.3.0.0) -
                                                  //| > mod, (0.2.1.1,0.2.1.1.1) -> name, (0,0.3) -> path, (0.2.1.1,0.2.1.1.2) -> 
                                                  //| mod, (0.2.1.1.1,0.2.1.1.1.0) -> opN, (0.2.1,0.2.1.1) -> ARG1, (0.3.0.0,0.3.0
                                                  //| .0.0) -> ARG0-of, (0,0.2) -> purpose, (0,0.1) -> ARG1, (0.3,0.3.0) -> opN, (
                                                  //| 0.2.1,0.0) -> ARG0, (0.2,0.2.1) -> ARG1, (0.2.1.1,0.2.1.1.3) -> mod, (0.2.1.
                                                  //| 2,0.2.1.1) -> opN, (0,0.0) -> ARG0, (0.2.1.1,0.2.1.1.0) -> wiki, (0.2,0.0) -
                                                  //| > ARG0, (0.3.0.0.0,0.2.1.1) -> ARG1, (0.2.1,0.2.1.2) -> location),Map((0.3.0
                                                  //| ,0.3.0.0) -> mod, (0.2.1.1,0.2.1.1.1) -> name, (0,0.3) -> path, (0.2.1.1,0.2
                                                  //| .1.1.2) -> mod, (0.2.1.1.1,0.2.1.1.1.0) -> op1, (0.2.1,0.2.1.1) -> ARG1, (0.
                                                  //| 3.0.0,0.3.0.0.0) -> ARG0-of, (0,0.2) -> purpose, (0,0.1) -> ARG1, (0.3,0.3.0
                                                  //| ) -> op1, (0.2.1,0.0) -> ARG0, (0.2,0.2.1) -> ARG1, (0.2.1.1,0.2.1.1.3) -> m
                                                  //| od, (0.2.1.2,0.2.1.1) -> op1, (0,0.0) -> ARG0, (0.2.1.1,0.2.1.1.0) -> wiki, 
                                                  //| (0.2,0.0) -> ARG0, (0.3.0.0.0,0.2.1.1) -> ARG1, (0.2.1,0.2.1.2) -> location)
                                                  //| ,List())
  PourdamghaniAligner.importTokenization          //> res0: Map[String,(String, String)] = Map(# ::id bc.cctv_0000.19 -> (# ::tok 
                                                  //| We took a taxi along the path of the highway that heads toward Disney , tryi
                                                  //| ng to experience this mysterious park from close by .,# ::alignments 0-1.1 1
                                                  //| -1 3-1.2 4-1.4 6-1.4.1 7-1.4.1.1.r 9-1.4.1.1 11-1.4.1.1.1 13-1.4.1.1.1.1 15-
                                                  //| 1.3 17-1.3.2 18-1.3.2.3.1 19-1.3.2.3.1 20-1.3.2.3.1 22-1.3.2.3))
   val pa = PourdamghaniAligner(DependencyTree.preProcess(rawSentence), amr, id)
                                                  //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.6 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [4.6 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [4.8 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [2.8 sec].
                                                  //| pa  : amr.PourdamghaniAligner = PourdamghaniAligner(List(We, took, a, taxi, 
                                                  //| along, the, path, of, the, highway, that, heads, toward, Disney, trying, to,
                                                  //|  experience, this, mysterious, park, from, close, by),AMRGraph(Map(0.1 -> ta
                                                  //| xi, 0.2.1.1.0 -> "Hong_Kong_Disneyland", 0.2.1 -> experience-01, 0.0 -> we, 
                                                  //| 0.2.1.1.3 -> this, 0.2.1.2 -> close, 0.3.0.0 -> highway, 0.3.0 -> path, 0.2.
                                                  //| 1.1.2 -> mysterious, 0.3 -> along, 0 -> take-01, 0.2.1.1 -> amusement-park, 
                                                  //| 0.2.1.1.1.0 -> "Disney", 0.2 -> try-01, 0.2.1.1.1 -> name, 0.3.0.0.0 -> head
                                                  //| -02),Map(),Map((0.3.0,0.3.0.0) -> mod, (0.2.1.1,0.2.1.1.1) -> name, (0,0.3) 
                                                  //| -> path, (0.2.1.1,0.2.1.1.2) -> mod, (0.2.1.1.1,0.2.1.1.1.0) -> opN, (0.2.1,
                                                  //| 0.2.1.1) -> ARG1, (0.3.0.0,0.3.0.0.0) -> ARG0-of, (0,0.2) -> purpose, (0,0.1
                                                  //| ) -> ARG1, (0.3,0.3.0) -> opN, (0.2.1,0.0) -> ARG0, (0.2,0.2.1) -> ARG1, (0.
                                                  //| 2.1.1,0.2.1.1.3) -> mod, (0.2.1.2,0.2.1.1) -> opN, (0,0.0) -> ARG0, (0.2.1.1
                                                  //| ,0.2.1.1.0) -> wiki, (0.2,0.0) -> ARG0, (0.3.0.0.0,0.2.1.1) -> ARG1, (0.2.1,
                                                  //| 0.2.1.2) -> location),Map((0.3.0,0.3.0.0) -> mod, (0.2.1.1,0.2.1.1.1) -> nam
                                                  //| e, (0,0.3) -> path, (0.2.1.1,0.2.1.1.2) -> mod, (0.2.1.1.1,0.2.1.1.1.0) -> o
                                                  //| p1, (0.2.1,0.2.1.1) -> ARG1, (0.3.0.0,0.3.0.0.0) -> ARG0-of, (0,0.2) -> purp
                                                  //| ose, (0,0.1) -> ARG1, (0.3,0.3.0) -> op1, (0.2.1,0.0) -> ARG0, (0.2,0.2.1) -
                                                  //| > ARG1, (0.2.1.1,0.2.1.1.3) -> mod, (0.2.1.2,0.2.1.1) -> op1, (0,0.0) -> ARG
                                                  //| 0, (0.2.1.1,0.2.1.1.0) -> wiki, (0.2,0.0) -> ARG0, (0.3.0.0.0,0.2.1.1) -> AR
                                                  //| G1, (0.2.1,0.2.1.2) -> location),List()),# ::id bc.cctv_0000.19)
  DependencyTree.preProcess(rawSentence)          //> res1: List[String] = List(We, took, a, taxi, along, the, path, of, the, high
                                                  //| way, that, heads, toward, Disney, trying, to, experience, this, mysterious, 
                                                  //| park, from, close, by)
  val mapping = pa.mapToAMR                       //> mapping  : amr.AMRGraph = AMRGraph(Map(0.1 -> taxi, 0.2.1.1.0 -> "Hong_Kong_
                                                  //| Disneyland", 0.2.1 -> experience-01, 0.0 -> we, 0.2.1.1.3 -> this, 0.2.1.2 -
                                                  //| > close, 0.3.0.0 -> highway, 0.3.0 -> path, 0.2.1.1.2 -> mysterious, 0.3 -> 
                                                  //| along, 0 -> take-01, 0.2.1.1 -> amusement-park, 0.2.1.1.1.0 -> "Disney", 0.2
                                                  //|  -> try-01, 0.2.1.1.1 -> name, 0.3.0.0.0 -> head-02),Map(0.1 -> (4,5), 0.3.0
                                                  //| .0.0.0 -> (14,15), 0.2.1 -> (17,18), 0.2.1.2.0 -> (18,19), 0.0 -> (1,2), 0.2
                                                  //| .1.2 -> (22,23), 0.3.0.0 -> (10,11), 0.3.0 -> (7,8), 0.3 -> (5,6), 0 -> (2,3
                                                  //| ), 0.2 -> (15,16), 0.3.0.0.0 -> (12,13)),Map((0.3.0,0.3.0.0) -> mod, (0.2.1.
                                                  //| 1,0.2.1.1.1) -> name, (0,0.3) -> path, (0.2.1.1,0.2.1.1.2) -> mod, (0.2.1.1.
                                                  //| 1,0.2.1.1.1.0) -> opN, (0.2.1,0.2.1.1) -> ARG1, (0.3.0.0,0.3.0.0.0) -> ARG0-
                                                  //| of, (0,0.2) -> purpose, (0,0.1) -> ARG1, (0.3,0.3.0) -> opN, (0.2.1,0.0) -> 
                                                  //| ARG0, (0.2,0.2.1) -> ARG1, (0.2.1.1,0.2.1.1.3) -> mod, (0.2.1.2,0.2.1.1) -> 
                                                  //| opN, (0,0.0) -> ARG0, (0.2.1.1,0.2.1.1.0) -> wiki, (0.2,0.0) -> ARG0, (0.3.0
                                                  //| .0.0,0.2.1.1) -> ARG1, (0.2.1,0.2.1.2) -> location),Map((0.3.0,0.3.0.0) -> m
                                                  //| od, (0.2.1.1,0.2.1.1.1) -> name, (0,0.3) -> path, (0.2.1.1,0.2.1.1.2) -> mod
                                                  //| , (0.2.1.1.1,0.2.1.1.1.0) -> op1, (0.2.1,0.2.1.1) -> ARG1, (0.3.0.0,0.3.0.0.
                                                  //| 0) -> ARG0-of, (0,0.2) -> purpose, (0,0.1) -> ARG1, (0.3,0.3.0) -> op1, (0.2
                                                  //| .1,0.0) -> ARG0, (0.2,0.2.1) -> ARG1, (0.2.1.1,0.2.1.1.3) -> mod, (0.2.1.2,0
                                                  //| .2.1.1) -> op1, (0,0.0) -> ARG0, (0.2.1.1,0.2.1.1.0) -> wiki, (0.2,0.0) -> A
                                                  //| RG0, (0.3.0.0.0,0.2.1.1) -> ARG1, (0.2.1,0.2.1.2) -> location),List())
  amr.nodes foreach (x => println(s"${x._1}\t${x._2}\t${mapping.nodeSpans.getOrElse(x._1, "No Mapping")}"))
                                                  //> 0.1	taxi	(4,5)
                                                  //| 0.2.1.1.0	"Hong_Kong_Disneyland"	No Mapping
                                                  //| 0.2.1	experience-01	(17,18)
                                                  //| 0.0	we	(1,2)
                                                  //| 0.2.1.1.3	this	No Mapping
                                                  //| 0.2.1.2	close	(22,23)
                                                  //| 0.3.0.0	highway	(10,11)
                                                  //| 0.3.0	path	(7,8)
                                                  //| 0.2.1.1.2	mysterious	No Mapping
                                                  //| 0.3	along	(5,6)
                                                  //| 0	take-01	(2,3)
                                                  //| 0.2.1.1	amusement-park	No Mapping
                                                  //| 0.2.1.1.1.0	"Disney"	No Mapping
                                                  //| 0.2	try-01	(15,16)
                                                  //| 0.2.1.1.1	name	No Mapping
                                                  //| 0.3.0.0.0	head-02	(12,13)
  Sentence(rawSentence, rawAMR, id)               //> res2: amr.Sentence = # ::id bc.cctv_0000.19
                                                  //| We took a taxi along the path of the highway that heads toward Disney , tryi
                                                  //| ng to experience this mysterious park from close by .
                                                  //| 
                                                  //| NodeMap:	Map(1 -> We, 2 -> took, 3 -> a, 4 -> taxi, 5 -> along, 6 -> the,
                                                  //|  7 -> path, 8 -> of, 9 -> the, 10 -> highway, 11 -> that, 12 -> heads, 13 ->
                                                  //|  toward, 14 -> Disney, 16 -> trying, 17 -> to, 18 -> experience, 19 -> this,
                                                  //|  20 -> mysterious, 21 -> park, 22 -> from, 23 -> close, 24 -> by)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,12
                                                  //| ), 12 -> (12,13), 13 -> (13,14), 14 -> (14,15), 16 -> (15,16), 17 -> (16,17)
                                                  //| , 18 -> (17,18), 19 -> (18,19), 20 -> (19,20), 21 -> (20,21), 22 -> (21,22),
                                                  //|  23 -> (22,23), 24 -> (23,24))
                                                  //| Edges:	Map((2,1) -> nsubj, (2,4) -> dobj, (4,3) -> det, (4,5) -> prep, 
                                                  //| (4,16) -> vmod, (5,7) -> pobj, (7,6) -> det, (7,8) -> prep, (8,10) -> pobj, 
                                                  //| (10,9) -> det, (10,12) -> rcmod, (12,11) -> nsubj, (12,13) -> prep, (13,14) 
                                                  //| -> pobj, (16,18) -> xcomp, (18,17) -> aux, (18,21) -> dobj, (18,22) -> prep,
                                                  //|  (18,24) -> prep, (21,19) -> det, (21,20) -> amod, (22,23) -> pobj)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map()
                                                  //| PartsOfSpeech:	Map(5 -> IN, 10 -> NN, 24 -> IN, 14 -> NNP, 20 -> JJ, 1 
                                                  //| -> PRP, 6 -> DT, 21 -> NN, 9 -> DT, 13 -> IN, 2 -> VBD, 17 -> TO, 22 -> IN, 
                                                  //| 12 -> VBZ, 7 -> NN, 3 -> DT, 18 -> VB, 16 -> VBG, 11 -> WDT, 23 -> NN, 8 -> 
                                                  //| IN, 19 -> DT, 4 -> NN)
                                                  //| DependencyLabels:	Map(5 -> prep, 10 -> pobj, 24 -> prep, 14 -> pobj, 20 ->
                                                  //|  amod, 1 -> nsubj, 6 -> det, 21 -> dobj, 9 -> det, 13 -> prep, 17 -> aux, 22
                                                  //|  -> prep, 12 -> rcmod, 7 -> pobj, 3 -> det, 18 -> xcomp, 16 -> vmod, 11 -> n
                                                  //| subj, 23 -> pobj, 8 -> prep, 19 -> det, 4 -> dobj)
                                                  //| AMRGraph(Map(0.1 -> taxi, 0.2.1.1.0 -> "Hong_Kong_Disneyland", 0.2.1 -> expe
                                                  //| rience-01, 0.0 -> we, 0.2.1.1.3 -> this, 0.2.1.2 -> close, 0.3.0.0 -> highwa
                                                  //| y, 0.3.0 -> path, 0.2.1.1.2 -> mysterious, 0.3 -> along, 0 -> take-01, 0.2.1
                                                  //| .1 -> amusement-park, 0.2.1.1.1.0 -> "Disney", 0.2 -> try-01, 0.2.1.1.1 -> n
                                                  //| ame, 0.3.0.0.0 -> head-02),Map(0.1 -> (4,5), 0.3.0.0.0.0 -> (14,15), 0.2.1 -
                                                  //| > (17,18), 0.2.1.2.0 -> (18,19), 0.0 -> (1,2), 0.2.1.2 -> (22,23), 0.3.0.0 -
                                                  //| > (10,11), 0.3.0 -> (7,8), 0.3 -> (5,6), 0 -> (2,3), 0.2 -> (15,16), 0.3.0.0
                                                  //| .0 -> (12,13)),Map((0.3.
                                                  //| Output exceeds cutoff limit.
  amr.arcs foreach println                        //> ((0.3.0,0.3.0.0),mod)
                                                  //| ((0.2.1.1,0.2.1.1.1),name)
                                                  //| ((0,0.3),path)
                                                  //| ((0.2.1.1,0.2.1.1.2),mod)
                                                  //| ((0.2.1.1.1,0.2.1.1.1.0),opN)
                                                  //| ((0.2.1,0.2.1.1),ARG1)
                                                  //| ((0.3.0.0,0.3.0.0.0),ARG0-of)
                                                  //| ((0,0.2),purpose)
                                                  //| ((0,0.1),ARG1)
                                                  //| ((0.3,0.3.0),opN)
                                                  //| ((0.2.1,0.0),ARG0)
                                                  //| ((0.2,0.2.1),ARG1)
                                                  //| ((0.2.1.1,0.2.1.1.3),mod)
                                                  //| ((0.2.1.2,0.2.1.1),opN)
                                                  //| ((0,0.0),ARG0)
                                                  //| ((0.2.1.1,0.2.1.1.0),wiki)
                                                  //| ((0.2,0.0),ARG0)
                                                  //| ((0.3.0.0.0,0.2.1.1),ARG1)
                                                  //| ((0.2.1,0.2.1.2),location)
  }