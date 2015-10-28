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
                                                  //| hPCFG.ser.gz ... done [1.3 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [3.9 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [3.5 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [2.6 sec].
                                                  //| (17,List(0.0.1.1.1))
                                                  //| (2,List(0.2))
                                                  //| (11,List(0.1.2))
                                                  //| (5,List(0.1.1))
                                                  //| (14,List(0.0.1.0))
                                                  //| (16,List(0.0.1.1.0))
                                                  //| (7,List(0.1))
                                                  //| (10,List(0.1.2.0.0.0))
                                                  //| (1,List(0.0.0.0))
                                                  //| (3,List(0))
                                                  //| (12,List(0.0.1))
                                                  //| (6,List(0.1.0.0.0))
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
                                                  //| .0 -> country, 0.0.1.0 -> airspace, 0.1.0.0.0
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
                                                  //| 2 -> 0.2
                                                  //| 17 -> 0.0.1.1.1
                                                  //| 12 -> 0.0.1
                                                  //| 7 -> 0.1
                                                  //| 3 -> 0
                                                  //| 16 -> 0.0.1.1.0
                                                  //| 11 -> 0.1.2
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
                                                  //| -> 0.0.0.0, 6 -> 0.1.0.0.0, 2 -> 0.2, 17 -> 0.0.1.1.1, 12 -> 0.0.1, 7 -> 0.1
                                                  //| , 3 -> 0, 16 -> 0.0.1.1.0, 11 -> 0.1.2)
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
                                                  //> file  : java.io.FileWriter = java.io.FileWriter@8f2ef19
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
    }                                             //> (17,List(0.0.1.1.1))
                                                  //| (2,List(0.2))
                                                  //| (11,List(0.1.2))
                                                  //| (5,List(0.1.1))
                                                  //| (14,List(0.0.1.0))
                                                  //| (16,List(0.0.1.1.0))
                                                  //| (7,List(0.1))
                                                  //| (10,List(0.1.2.0.0.0))
                                                  //| (1,List(0.0.0.0))
                                                  //| (3,List(0))
                                                  //| (12,List(0.0.1))
                                                  //| (6,List(0.1.0.0.0))
                                                  //| Loading Relations
  }
  file.close
  val outputAdv = RunDagger.sampleTrajectory(s1, "C:\\AMR\\WangXueExpert_output.txt", new WangXueExpert)
                                                  //> outputAdv  : amr.Sentence = Sentence(Kazakhstan also became a key U.S. part
                                                  //| ner in the Afghan war opening its airspace to military overflights.,
                                                  //| NodeMap:	Map(1 -> Kazakhstan, 2 -> also, 3 -> become-01, 5 -> key, 6 -> U
                                                  //| .S., 7 -> partner-01, 10 -> Afghanistan, 11 -> war-01, 12 -> open-01, 14 ->
                                                  //|  airspace, 16 -> military, 17 -> over, 18 -> fly-01, 19 -> name, 20 -> coun
                                                  //| try, 21 -> name, 22 -> country, 23 -> name, 24 -> country)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 5 -> (5,6), 6 -> (6,7), 
                                                  //| 7 -> (7,8), 10 -> (10,11), 11 -> (8,11), 12 -> (12,13), 14 -> (14,15), 16 -
                                                  //| > (16,17), 17 -> (17,18), 18 -> (15,16), 19 -> (10,11), 20 -> (10,11), 21 -
                                                  //| > (1,2), 22 -> (1,2), 23 -> (6,7), 24 -> (6,7))
                                                  //| Edges:	Map((3,2) -> mod, (3,7) -> ARG2, (3,22) -> ARG1, (7,5) -> mod, (
                                                  //| 7,11) -> purpose, (7,18) -> UNKNOWN, (7,22) -> ARG0, (7,24) -> ARG1, (11,20
                                                  //| ) -> ARG1, (14,12) -> UNKNOWN, (18,14) -> UNKNOWN, (18,16) -> ARG0, (18,17)
                                                  //|  -> UNKNOWN, (18,22) -> UNKNOWN, (19,10) -> opN, (20,19) -> name, (21,1) ->
                                                  //|  opN, (22,21) -> name, (23,6) -> opN, (24,23) -> name)
                                                  //| InsertedNodes:	Map(24 -> 0.1.0, 20 -> 0.1.2.0, 21 -> 0.0.0, 22 -> 0.0, 
                                                  //| 18 -> 0.0.1.1, 23 -> 0.1.0.0, 19 -> 0.1.2.0.0)
                                                  //| MergedNodes:	Map(18 -> List((15,to)), 11 -> List((8,in)))
                                                  //| SwappedArcs:	Set((12,18), (7,3), (22,12), (17,22), (18,14), (22,18), 
                                                  //| (17,18), (12,14))
                                                  //| DeletedNodes:	Map(14 -> List((13,its)), 11 -> List((9,the)), 7 -> List
                                                  //| ((4,a)))
                                                  //| PartsOfSpeech:	Map(5 -> JJ, 10 -> JJ, 24 -> NNP, 14 -> NN, 20 -> JJ, 1 
                                                  //| -> NNP, 6 -> NNP, 21 -> NNP, 9 -> DT, 13 -> PRP$, 2 -> RB, 17 -> NNS, 22 ->
                                                  //|  NNP, 12 -> VBG, 7 -> NN, 3 -> VBD, 18 -> JJ, 16 -> JJ, 11 -> NN, 23 -> NNP
                                                  //| , 8 -> IN, 19 -> JJ, 4 -> DT, 15 -> TO)
                                                  //| DependencyLabels:	Map(5 -> amod, 10 -> amod, 24 -> nn, 14 -> dobj, 20 -> a
                                                  //| mod, 1 -> nsubj, 6 -> nn, 21 -> nsubj, 9 -> det, 13 -> poss, 2 -> advmod, 1
                                                  //| 7 -> pobj, 22 -> nsubj, 12 -> xcomp, 3 -> cop, 18 -> amod, 16 -> amod, 11 -
                                                  //| > pobj, 23 -> nn, 8 -> prep, 19 -> amod, 4 -> det, 15 -> prep),Some(AMRGrap
                                                  //| h(Map(12 -> open-01, 19 -> name, 23 -> name, 11 -> war-01, 22 -> country, 2
                                                  //| 4 -> country, 16 -> military, 5 -> key, 10 -> "Afghanistan", 21 -> name, 6 
                                                  //| -> "U.S.", 1 -> "Kazakhstan", 17 -> over, 14 -> airspace, 20 -> country, 2 
                                                  //| -> also, 18 -> fly-01, 7 -> partner-01, 3 -> become-01),Map(12 -> (12,13), 
                                                  //| 19 -> (10,11), 23 -> (6,7), 11 -> (8,11), 22 -> (1
                                                  //| Output exceeds cutoff limit.
     
}