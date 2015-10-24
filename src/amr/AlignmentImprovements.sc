package amr
import edu.cmu.lti.nlp.amr._

object AlignmentImprovements {

// val (rawSentence, rawAMR) = AMRGraph.importFile("C:\\AMR\\Riyadh-based.txt")(0)
//  val (rawSentence, rawAMR) = AMRGraph.importFile("C:\\AMR\\CountryList.txt")(0)
//  val (rawSentence, rawAMR) = AMRGraphimportFile("C:\\AMR\\Lavrov.txt")(0)
  val (rawSentence, rawAMR) = AMRGraph.importFile("C:\\AMR\\TargetASEAN.txt")(0)
                                                  //> rawSentence  : String = ASEAN officials agreed to develop regional training 
                                                  //| programs.
                                                  //| rawAMR  : String = (a / agree-01 :ARG0 (p2 / person :ARG0-of (h / have-org-r
                                                  //| ole-91 :ARG1 (o2 / organization :name (n / name :op1 "ASEAN")) :ARG2 (o / of
                                                  //| ficial))) :ARG1 (d / develop-02 :ARG0 p2 :ARG1 (p / program :purpose (t / tr
                                                  //| ain-01) :location (r / regional))))
  val dt = DependencyTree(rawSentence)            //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.2 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [5.1 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [2.3 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [5.1 sec].
                                                  //| dt  : amr.DependencyTree = 
                                                  //| NodeMap:	Map(1 -> ASEAN, 2 -> officials, 3 -> agreed, 4 -> to, 5 -> devel
                                                  //| op, 6 -> regional, 7 -> training, 8 -> programs)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9))
                                                  //| Edges:	Map((2,1) -> nn, (3,2) -> nsubj, (3,5) -> xcomp, (5,4) -> aux, (
                                                  //| 5,8) -> dobj, (8,6) -> amod, (8,7) -> nn)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map()
// val (rawSentence, rawAMR) = AMRGraph.importFile("C:\\AMR\\simpleDate.txt")(0)
// val (rawSentence, rawAMR) = AMRGraph.importFile("C:\\AMR\\lobbying.txt")(0)

  //    val tokenisedSentence = rawSentence.split(" ")
  val amr = Graph.parse(rawAMR)                   //> amr  : edu.cmu.lti.nlp.amr.Graph = Graph((agree-01 :ARG0 (person :ARG0-of (h
                                                  //| ave-org-role-91 :ARG1 (organization :name (name :op1 "ASEAN")) :ARG2 officia
                                                  //| l)) :ARG1 (develop-02 :ARG1 (program :purpose train-01 :location regional) :
                                                  //| ARG0 person)),ArrayBuffer(),Map(0.0.0 -> (have-org-role-91 :ARG1 (organizati
                                                  //| on :name (name :op1 "ASEAN")) :ARG2 official), 0.1.0.1 -> regional, 0.0.0.0 
                                                  //| -> (organization :name (name :op1 "ASEAN")), 0.1 -> (develop-02 :ARG1 (progr
                                                  //| am :purpose train-01 :location regional) :ARG0 person), 0.1.0.0 -> train-01,
                                                  //|  0.0.0.0.0.0 -> "ASEAN", 0.1.0 -> (program :purpose train-01 :location regio
                                                  //| nal), 0.0 -> (person :ARG0-of (have-org-role-91 :ARG1 (organization :name (n
                                                  //| ame :op1 "ASEAN")) :ARG2 official)), 0 -> (agree-01 :ARG0 (person :ARG0-of (
                                                  //| have-org-role-91 :ARG1 (organization :name (name :op1 "ASEAN")) :ARG2 offici
                                                  //| al)) :ARG1 (develop-02 :ARG1 (program :purpose train-01 :location regional) 
                                                  //| :ARG0 person)), 0.0.0.1 -> official, 0.0.0.0.0 -> (name :op1 "ASEAN")),Map(n
                                                  //|  -> (name :op1 "ASEAN"), h -> (have-org-role-91 :ARG1 (organization :name (n
                                                  //| ame :op1 "ASEAN")) :ARG2 official), t -> train-01, p2 -> (person :ARG0-of (h
                                                  //| ave-org-role-91 :ARG1 (organization :name (name :op1 "ASEAN")) :ARG2 officia
                                                  //| l)), d -> (develop-02 :ARG1 (program :purpose train-01 :location regional) :
                                                  //| ARG0 person), p -> (program :purpose train-01 :location regional), a -> (agr
                                                  //| ee-01 :ARG0 (person :ARG0-of (have-org-role-91 :ARG1 (organization :name (na
                                                  //| me :op1 "ASEAN")) :ARG2 official)) :ARG1 (develop-02 :ARG1 (program :purpose
                                                  //|  train-01 :location regional) :ARG0 person)), o2 -> (organization :name (nam
                                                  //| e :op1 "ASEAN")), r -> regional, o -> official))
                                                  // ee-01 :ARG0 (person :ARG0-of (have-org-role-91 :ARG1 (organization :name (na
  val tokenisedSentence = DependencyTree.preProcess(rawSentence)
                                                  //> tokenisedSentence  : List[String] = List(ASEAN, officials, agreed, to, devel
                                                  //| op, regional, training, programs)
val wordAlignments = AlignTest.alignWords(tokenisedSentence.toArray, amr)
                                                  //> wordAlignments  : Array[Option[edu.cmu.lti.nlp.amr.Node]] = Array(Some("ASEA
                                                  //| N"), Some(official), Some((agree-01 :ARG0 (person :ARG0-of (have-org-role-91
                                                  //|  :ARG1 (organization :name (name :op1 "ASEAN")) :ARG2 official)) :ARG1 (deve
                                                  //| lop-02 :ARG1 (program :purpose train-01 :location regional) :ARG0 person))),
                                                  //|  None, Some((develop-02 :ARG1 (program :purpose train-01 :location regional)
                                                  //|  :ARG0 person)), Some(regional), Some(train-01), Some((program :purpose trai
                                                  //| n-01 :location regional)))
                                               
                                            
wordAlignments foreach println                    //> Some("ASEAN")
                                                  //| Some(official)
                                                  //| Some((agree-01 :ARG0 (person :ARG0-of (have-org-role-91 :ARG1 (organization
                                                  //|  :name (name :op1 "ASEAN")) :ARG2 official)) :ARG1 (develop-02 :ARG1 (progr
                                                  //| am :purpose train-01 :location regional) :ARG0 person)))
                                                  //| None
                                                  //| Some((develop-02 :ARG1 (program :purpose train-01 :location regional) :ARG0
                                                  //|  person))
                                                  //| Some(regional)
                                                  //| Some(train-01)
                                                  //| Some((program :purpose train-01 :location regional))
                                            
AlignSpans.alignSpans(tokenisedSentence.toArray, amr, wordAlignments) zip wordAlignments foreach println
                                                  //> (Some(2),Some("ASEAN"))
                                                  //| (Some(3),Some(official))
                                                  //| (Some(0),Some((agree-01 :ARG0 (person :ARG0-of (have-org-role-91 :ARG1 (org
                                                  //| anization :name (name :op1 "ASEAN")) :ARG2 official)) :ARG1 (develop-02 :AR
                                                  //| G1 (program :purpose train-01 :location regional) :ARG0 person))))
                                                  //| (None,None)
                                                  //| (Some(4),Some((develop-02 :ARG1 (program :purpose train-01 :location region
                                                  //| al) :ARG0 person)))
                                                  //| (Some(7),Some(regional))
                                                  //| (Some(6),Some(train-01))
                                                  //| (Some(5),Some((program :purpose train-01 :location regional)))
 val amrG =  AMRGraph(amr)                        //> amrG  : amr.AMRGraph = AMRGraph(Map(0.1 -> develop-02, 0.0.0 -> have-org-ro
                                                  //| le-91, 0.1.0 -> program, 0.1.0.1 -> regional, 0.0 -> person, 0.0.0.0 -> org
                                                  //| anization, 0.1.0.0 -> train-01, 0 -> agree-01, 0.0.0.1 -> official, 0.0.0.0
                                                  //| .0.0 -> "ASEAN", 0.0.0.0.0 -> name),Map(0.1 -> (5,6), 0.1.0 -> (8,9), 0.1.0
                                                  //| .1 -> (6,7), 0.0 -> (9,1), 0.0.0.0 -> (1,2), 0.1.0.0 -> (7,8), 0 -> (3,4), 
                                                  //| 0.0.0.1 -> (2,3), 0.0.0.0.0.0 -> (1,2), 0.0.0.0.0 -> (1,2)),Map((0.0,0.0.0)
                                                  //|  -> ARG0-of, (0.0.0.0,0.0.0.0.0) -> name, (0.0.0,0.0.0.0) -> ARG1, (0.1,0.1
                                                  //| .0) -> ARG1, (0.1.0,0.1.0.0) -> purpose, (0,0.1) -> ARG1, (0.0.0.0.0,0.0.0.
                                                  //| 0.0.0) -> opN, (0.1.0,0.1.0.1) -> location, (0.0.0,0.0.0.1) -> ARG2, (0.1,0
                                                  //| .0) -> ARG0, (0,0.0) -> ARG0),Map((0.0,0.0.0) -> ARG0-of, (0.0.0.0,0.0.0.0.
                                                  //| 0) -> name, (0.0.0,0.0.0.0) -> ARG1, (0.1,0.1.0) -> ARG1, (0.1.0,0.1.0.0) -
                                                  //| > purpose, (0,0.1) -> ARG1, (0.0.0.0.0,0.0.0.0.0.0) -> op1, (0.1.0,0.1.0.1)
                                                  //|  -> location, (0.0.0,0.0.0.1) -> ARG2, (0.1,0.0) -> ARG0, (0,0.0) -> ARG0),
                                                  //| List())
 amrG.nodes.keys.toList.sorted foreach (x => println(s"${x}\t\t${amrG.nodes(x)}\t\t${amrG.nodeSpans.getOrElse(x, "No Mapping")}"))
                                                  //> 0		agree-01		(3,4)
                                                  //| 0.0		person		(9,1)
                                                  //| 0.0.0		have-org-role-91		No Mapping
                                                  //| 0.0.0.0		organization		(1,2)
                                                  //| 0.0.0.0.0		name		(1,2)
                                                  //| 0.0.0.0.0.0		"ASEAN"		(1,2)
                                                  //| 0.0.0.1		official		(2,3)
                                                  //| 0.1		develop-02		(5,6)
                                                  //| 0.1.0		program		(8,9)
                                                  //| 0.1.0.0		train-01		(7,8)
                                                  //| 0.1.0.1		regional		(6,7)
                                                                                               
                                                  
                                                  
}