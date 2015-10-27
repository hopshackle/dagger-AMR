package amr

object problematicAMR {

val testData = AMRGraph.importFile("C:\\AMR\\TargetKazakh.txt")
                                                  //> testData  : IndexedSeq[(String, String)] = Vector((Kazakhstan also became a 
                                                  //| key U.S. partner in the Afghan war opening its airspace to military overflig
                                                  //| hts.,(b / become-01 :ARG1 (c / country :name (n / name :op1 "Kazakhstan") :A
                                                  //| RG0-of (o / open-01 :ARG1 (a / airspace :poss c) :ARG3 (f / fly-01 :ARG0 (m 
                                                  //| / military) :path (o2 / over :op1 c)))) :ARG2 (p / partner-01 :ARG0 c :ARG1 
                                                  //| (c2 / country :name (n2 / name :op1 "U.S.")) :mod (k / key) :purpose (w / wa
                                                  //| r-01 :ARG1 (c3 / country :name (n3 / name :op1 "Afghanistan")))) :mod (a2 / 
                                                  //| also))))
  val expert = new WangXueExpert                  //> expert  : amr.WangXueExpert = amr.WangXueExpert@2a5ca609
  val expertSystem = WangXueTransitionSystem      //> expertSystem  : amr.WangXueTransitionSystem.type = amr.WangXueTransitionSyst
                                                  //| em$@4c70fda8
  AMRGraph.setAligner("improved")
  expertSystem.reentrance = true
  ImportConcepts.initialise("C:\\AMR\\TargetKazakh.txt")
// ImportConcepts.relationStrin
  val s1 = Sentence(testData(0)._1, testData(0)._2)
                                                  //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.7 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [5.4 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [4.1 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [6.2 sec].
                                                  //| s1  : amr.Sentence = Sentence(Kazakhstan also became a key U.S. partner in t
                                                  //| he Afghan war opening its airspace to military overflights.,
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
                                                  //|  -> amod, 11 -> pobj, 8 -> prep, 4 -> det, 15 -> prep),Some(AMRGraph(Map(0.1
                                                  //|  -> partner-01, 0.0.0 -> name, 0.0.1.1.0 -> military, 0.1.2.0.0 -> name, 0.1
                                                  //| .0 -> country, 0.0.1.0 -> airspace, 0.1.0.0.0 -> "U.S.", 0.0 -> country, 0.0
                                                  //| .0.0 -> "Kazakhstan", 0.1.2.0.0.0 -> "Afghanistan", 0.0.1.1 -> fly-01, 0.0.1
                                                  //|  -> open-01, 0.1.0.0 -> name, 0.1.2.0 -> country, 0 -> become-01, 0.1.2 -> w
                                                  //| ar-01, 0.0.1.1.1 -> over, 0.2 -> also, 0.1.1 -> key),Map(0.1 -> (7,8), 0.0
                                                  //| Output exceeds cutoff limit.
  var state = expertSystem.init(s1)               //> state  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(16, 17, 13, 10, 9, 15, 14, 11, 1, 5, 6, 12, 4, 8, 3, 2, 7)
                                                  //| Children:	List()
                                                  //| PartialGraph:	
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
                                                  //| Mappings:	5 -> 0.1.1
                                                  //| 10 -> 0.1.2.0.0.0
                                                  //| 14 -> 0.0.1.0
                                                  //| 1 -> 0.0.0.0
                                                  //| 6 -> 0.1.0.0.0
                                                  //| 9 -> 0.1.0.0
                                                  //| 2 -> 0.2
                                                  //| 17 -> 0.0.1.1.1
                                                  //| 12 -> 0.0.1
                                                  //| 7 -> 0.1
                                                  //| 3 -> 0
                                                  //| 16 -> 0.0.1.1.0
                                                  //| 11 -> 0.1.2
                                                  //| 8 -> 0.1.2.0.0
                                                  //| 4 -> 0.0.0
                                                  //| 
  val action = new Array[WangXueAction](301)      //> action  : Array[amr.WangXueAction] = Array(null, null, null, null, null, nul
                                                  //| l, null, null, null, null, null, null, null, null, null, null, null, null, n
                                                  //| ull, null, null, null, null, null, null, null, null, null, null, null, null,
                                                  //|  null, null, null, null, null, null, null, null, null, null, null, null, nul
                                                  //| l, null, null, null, null, null, null, null, null, null, null, null, null, n
                                                  //| ull, null, null, null, null, null, null, null, null, null, null, null, null,
                                                  //|  null, null, null, null, null, null, null, null, null, null, null, null, nul
                                                  //| l, null, null, null, null, null, null, null, null, null, null, null, null, n
                                                  //| ull, null, null, null, null, null, null, null, null, null, null, null, null,
                                                  //|  null, null, null, null, null, null, null, null, null, null, null, null, nul
                                                  //| l, null, null, null, null, null, null, null, null, null, null, null, null, n
                                                  //| ull, null, null, null, null, null, null, null, null, null, null, null, null,
                                                  //|  null, null, null, null, null, null, null, null, null, null, null, null, nul
                                                  //| l, null, null, null, null, null, null, null, null, null, null, null, null, n
                                                  //| ull, null, null, null, null, null, null, null, null, null, null, null, null,
                                                  //|  null, null, null, null, null, null, null, null, null, null, null, null, nul
                                                  //| l, null, null, null, null, null, null, null, null, null, null, null, null, n
                                                  //| ull, null, null, null, null, null, null, null, null, null, null, null, null,
                                                  //|  null, null, null, null, null, null, null, null, null, null, null, null, nul
                                                  //| l, null, null, null, null, null, null, null, null, null, null, null, null, n
                                                  //| ull, null, null, null, null, null, null, null, null, null, null, null, null,
                                                  //|  null, null, null, null, null, null, null, null, null, null, null, null, nul
                                                  //| l, null, null, null, null, null, null, null, null, null, null, null, null, n
                                                  //| ull, null, null, null, null, null, null, null, null, null, null, null, null,
                                                  //|  null, null, null, null)
  val stateHistory = new Array[WangXueTransitionState](301)
                                                  //> stateHistory  : Array[amr.WangXueTransitionState] = Array(null, null, null, 
                                                  //| null, null, null, null, null, null, null, null, null, null, null, null, null
                                                  //| , null, null, null, null, null, null, null, null, null, null, null, null, nu
                                                  //| ll, null, null, null, null, null, null, null, null, null, null, null, null, 
                                                  //| null, null, null, null, null, null, null, null, null, null, null, null, null
                                                  //| , null, null, null, null, null, null, null, null, null, null, null, null, nu
                                                  //| ll, null, null, null, null, null, null, null, null, null, null, null, null, 
                                                  //| null, null, null, null, null, null, null, null, null, null, null, null, null
                                                  //| , null, null, null, null, null, null, null, null, null, null, null, null, nu
                                                  //| ll, null, null, null, null, null, null, null, null, null, null, null, null, 
                                                  //| null, null, null, null, null, null, null, null, null, null, null, null, null
                                                  //| , null, null, null, null, null, null, null, null, null, null, null, null, nu
                                                  //| ll, null, null, null, null, null, null, null, null, null, null, null, null, 
                                                  //| null, null, null, null, null, null, null, null, null, null, null, null, null
                                                  //| , null, null, null, null, null, null, null, null, null, null, null, null, nu
                                                  //| ll, null, null, null, null, null, null, null, null, null, null, null, null, 
                                                  //| null, null, null, null, null, null, null, null, null, null, null, null, null
                                                  //| , null, null, null, null, null, null, null, null, null, null, null, null, nu
                                                  //| ll, null, null, null, null, null, null, null, null, null, null, null, null, 
                                                  //| null, null, null, null, null, null, null, null, null, null, null, null, null
                                                  //| , null, null, null, null, null, null, null, null, null, null, null, null, nu
                                                  //| ll, null, null, null, null, null, null, null, null, null, null, null, null, 
                                                  //| null, null, null, null, null, null, null, null, null, null, null, null, null
                                                  //| , null, null, null, null, null, null, null, null, null, null, null, null, nu
                                                  //| ll, null, null, null, null, null, null)
  s1.positionToAMR                                //> res0: Map[Int,String] = Map(5 -> 0.1.1, 10 -> 0.1.2.0.0.0, 14 -> 0.0.1.0, 1 
                                                  //| -> 0.0.0.0, 6 -> 0.1.0.0.0, 9 -> 0.1.0.0, 2 -> 0.2, 17 -> 0.0.1.1.1, 12 -> 0
                                                  //| .0.1, 7 -> 0.1, 3 -> 0, 16 -> 0.0.1.1.0, 11 -> 0.1.2, 8 -> 0.1.2.0.0, 4 -> 0
                                                  //| .0.0)
  s1.amr.get.nodes                                //> res1: Map[String,String] = Map(0.1 -> partner-01, 0.0.0 -> name, 0.0.1.1.0 -
                                                  //| > military, 0.1.2.0.0 -> name, 0.1.0 -> country, 0.0.1.0 -> airspace, 0.1.0.
                                                  //| 0.0 -> "U.S.", 0.0 -> country, 0.0.0.0 -> "Kazakhstan", 0.1.2.0.0.0 -> "Afgh
                                                  //| anistan", 0.0.1.1 -> fly-01, 0.0.1 -> open-01, 0.1.0.0 -> name, 0.1.2.0 -> c
                                                  //| ountry, 0 -> become-01, 0.1.2 -> war-01, 0.0.1.1.1 -> over, 0.2 -> also, 0.1
                                                  //| .1 -> key)
  s1.amr.get.arcs                                 //> res2: Map[(String, String),String] = Map((0.0,0.0.0) -> name, (0.0.1.1.1,0.0
                                                  //| ) -> opN, (0.0.0,0.0.0.0) -> opN, (0.1,0.1.0) -> ARG1, (0.1,0.1.2) -> purpos
                                                  //| e, (0.1.2.0,0.1.2.0.0) -> name, (0.0.1.0,0.0) -> poss, (0.1.0,0.1.0.0) -> na
                                                  //| me, (0,0.2) -> mod, (0,0.1) -> ARG2, (0.0.1,0.0.1.1) -> ARG3, (0.1.0.0,0.1.0
                                                  //| .0.0) -> opN, (0.1,0.0) -> ARG0, (0.0.1.1,0.0.1.1.0) -> ARG0, (0,0.0) -> ARG
                                                  //| 1, (0.0.1,0.0.1.0) -> ARG1, (0.1.2,0.1.2.0) -> ARG1, (0.0.1.1,0.0.1.1.1) -> 
                                                  //| path, (0.1.2.0.0,0.1.2.0.0.0) -> opN, (0.0,0.0.1) -> ARG0-of, (0.1,0.1.1) ->
                                                  //|  mod)
  import java.io._
  val file = new FileWriter("C://AMR//Kazakh_improved.txt")
                                                  //> file  : java.io.FileWriter = java.io.FileWriter@4ef74c30
  for (i <- 0 to 300) {
    if (!expertSystem.isTerminal(state)) {
      stateHistory(i) = state
      action(i) = expert.chooseTransition(s1, state)
      file.write("\n" + state.toString + "\n")
      file.write(action(i) + " : " + (state.nodesToProcess match { case Nil => ""; case _ => state.nodesToProcess.head }) + " -> " + (
        state.childrenToProcess match { case Nil => ""; case _ => state.childrenToProcess.head }) + "\n")

			file.write(s1.AMRToPosition.toString)
      file.flush
      state = action(i)(state)
    }                                             //> Loading Relations
  }
  file.close
  val outputAdv = RunDagger.sampleTrajectory(s1, "C:\\AMR\\WangXueExpert_output.txt", new WangXueExpert)
                                                  //> outputAdv  : amr.Sentence = Sentence(Kazakhstan also became a key U.S. part
                                                  //| ner in the Afghan war opening its airspace to military overflights.,
                                                  //| NodeMap:	Map(1 -> Kazakhstan, 2 -> also, 3 -> become-01, 4 -> name, 5 -> 
                                                  //| key, 6 -> U.S., 7 -> partner-01, 8 -> name, 9 -> name, 10 -> Afghanistan, 1
                                                  //| 1 -> war-01, 12 -> open-01, 14 -> airspace, 16 -> military, 17 -> over, 18 
                                                  //| -> fly-01, 19 -> country, 20 -> country, 21 -> country)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,1
                                                  //| 2), 12 -> (12,13), 14 -> (14,15), 16 -> (16,17), 17 -> (17,18), 18 -> (15,1
                                                  //| 6), 19 -> (9,10), 20 -> (12,13), 21 -> (8,9))
                                                  //| Edges:	Map((3,2) -> mod, (3,7) -> ARG2, (3,20) -> ARG1, (4,1) -> opN, (
                                                  //| 7,5) -> mod, (7,11) -> purpose, (7,14) -> UNKNOWN, (7,19) -> ARG1, (7,20) -
                                                  //| > ARG0, (8,10) -> opN, (9,6) -> opN, (11,21) -> ARG1, (14,12) -> UNKNOWN, (
                                                  //| 14,18) -> UNKNOWN, (18,12) -> UNKNOWN, (18,16) -> ARG0, (18,17) -> UNKNOWN,
                                                  //|  (18,20) -> UNKNOWN, (19,9) -> name, (20,4) -> name, (21,8) -> name)
                                                  //| InsertedNodes:	Map(18 -> 0.0.1.1, 19 -> 0.1.0, 20 -> 0.0, 21 -> 0.1.2.0
                                                  //| )
                                                  //| MergedNodes:	Map(18 -> List((15,to)))
                                                  //| SwappedArcs:	Set((21,11), (20,18), (20,14), (17,20), (8,11), (12,18),
                                                  //|  (7,3), (18,14), (17,18), (20,12), (12,14))
                                                  //| DeletedNodes:	Map(14 -> List((13,its)))
                                                  //| PartsOfSpeech:	Map(5 -> JJ, 10 -> JJ, 14 -> NN, 20 -> VBG, 1 -> NNP, 6 
                                                  //| -> NNP, 21 -> IN, 9 -> DT, 13 -> PRP$, 2 -> RB, 17 -> NNS, 12 -> VBG, 7 -> 
                                                  //| NN, 3 -> VBD, 18 -> JJ, 16 -> JJ, 11 -> NN, 8 -> IN, 19 -> DT, 4 -> DT, 15 
                                                  //| -> TO)
                                                  //| DependencyLabels:	Map(5 -> amod, 10 -> amod, 14 -> dobj, 20 -> xcomp, 1 ->
                                                  //|  nsubj, 6 -> nn, 21 -> prep, 9 -> det, 13 -> poss, 2 -> advmod, 17 -> pobj,
                                                  //|  12 -> xcomp, 3 -> cop, 18 -> amod, 16 -> amod, 11 -> pobj, 8 -> prep, 19 -
                                                  //| > det, 4 -> det, 15 -> prep),Some(AMRGraph(Map(12 -> open-01, 8 -> name, 19
                                                  //|  -> country, 4 -> name, 11 -> war-01, 9 -> name, 16 -> military, 5 -> key, 
                                                  //| 10 -> "Afghanistan", 21 -> country, 6 -> "U.S.", 1 -> "Kazakhstan", 17 -> o
                                                  //| ver, 14 -> airspace, 20 -> country, 2 -> also, 18 -> fly-01, 7 -> partner-0
                                                  //| 1, 3 -> become-01),Map(12 -> (12,13), 8 -> (8,9), 19 -> (9,10), 4 -> (4,5),
                                                  //|  11 -> (11,12), 9 -> (9,10), 16 -> (16,17), 5 -> (5,6), 10 -> (10,11), 21 -
                                                  //| > (8,9), 6 -> (6,7), 1 -> (1,2), 17 -> (17,18), 14 -> (14,15), 20 -> (12,13
     
}