package amr

object problematicAMR {

val testData = AMRGraph.importFile("C:\\AMR\\ClergyTarget.txt")
                                                  //> testData  : IndexedSeq[(String, String)] = Vector((but their clergymen could
                                                  //| n't manage it, and were unable to pass it on to the next generation. What co
                                                  //| uld they do?,(m / multi-sentence :snt1 (c / contrast-01 :ARG2 (a / and :op1 
                                                  //| (c3 / capable-01 :polarity - :ARG1 p5 :ARG2 (m2 / manage-01 :ARG0 (p5 / pers
                                                  //| on :ARG0-of (h / have-org-role-91 :ARG1 (t2 / they) :ARG2 (c2 / clergyman)))
                                                  //|  :ARG1 (i / it))) :op2 (c4 / capable-01 :polarity - :ARG1 p5 :ARG2 (p4 / pas
                                                  //| s-on-09 :ARG0 p5 :ARG1 i :ARG2 (g / generation :mod (n / next)))))) :snt2 (p
                                                  //| 3 / possible-01 :ARG1 (d / do-02 :ARG0 (t / they) :ARG1 (a2 / amr-unknown)))
                                                  //| )))
  val expert = new WangXueExpert                  //> expert  : amr.WangXueExpert = amr.WangXueExpert@2a5ca609
  val expertSystem = WangXueTransitionSystem      //> expertSystem  : amr.WangXueTransitionSystem.type = amr.WangXueTransitionSyst
                                                  //| em$@1d057a39
  AMRGraph.setAligner("JAMR")
  expertSystem.reentrance = true
  expertSystem.useCompositeNodes = false
  ImportConcepts.initialise("C:\\AMR\\ClergyTarget.txt")
// ImportConcepts.relationStrin
  val s1 = Sentence(testData(0)._1, testData(0)._2)
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
                                                  //| im.crf.ser.gz ... done [4.7 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [3.0 sec].
                                                  //| WARNING: Found duplicate match for concept it
                                                  //| s1  : amr.Sentence = Sentence(but their clergymen couldn't manage it, and we
                                                  //| re unable to pass it on to the next generation. What could they do?,
                                                  //| NodeMap:	Map(1 -> but, 2 -> their, 3 -> clergymen, 4 -> could, 5 -> n't, 
                                                  //| 6 -> manage, 7 -> it, 8 -> and, 9 -> were, 10 -> unable, 11 -> to, 12 -> pas
                                                  //| s, 13 -> it, 14 -> on, 15 -> to, 16 -> the, 17 -> next, 18 -> generation, 10
                                                  //| 1 -> What, 102 -> could, 103 -> they, 104 -> do)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,12
                                                  //| ), 12 -> (12,13), 13 -> (13,14), 14 -> (14,15), 15 -> (15,16), 16 -> (16,17)
                                                  //| , 17 -> (17,18), 18 -> (18,19), 101 -> (19,20), 102 -> (20,21), 103 -> (21,2
                                                  //| 2), 104 -> (22,23))
                                                  //| Edges:	Map((3,2) -> poss, (6,1) -> cc, (6,3) -> nsubj, (6,4) -> aux, (6
                                                  //| ,5) -> neg, (6,7) -> dobj, (6,8) -> cc, (6,10) -> conj, (10,9) -> cop, (10,1
                                                  //| 2) -> xcomp, (12,11) -> aux, (12,13) -> dobj, (12,14) -> prep, (14,15) -> pc
                                                  //| omp, (15,18) -> pobj, (18,16) -> det, (18,17) -> amod, (104,101) -> dobj, (1
                                                  //| 04,102) -> aux, (104,103) -> nsubj)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map()
                                                  //| PartsOfSpeech:	Map(101 -> WP, 5 -> RB, 10 -> JJ, 14 -> IN, 1 -> CC, 6 -
                                                  //| > VB, 102 -> MD, 9 -> VBD, 13 -> PRP, 2 -> PRP$, 17 -> JJ, 12 -> VB, 7 -> PR
                                                  //| P, 103 -> PRP, 3 -> NN, 18 -> NN, 16 -> DT, 11 -> TO, 104 -> VB, 8 -> CC, 4 
                                                  //| -> MD, 15 -> TO)
                                                  //| DependencyLabels:	Map(101 -> dobj, 5 -> neg, 10 -> conj, 14 -> prep, 1 -> 
                                                  //| cc, 102 -> aux, 9 -> cop, 13 -> dobj, 2 -> poss, 17 -> amod, 12 -> xcomp, 7 
                                                  //| -> dobj, 103 -> nsubj, 3 -> nsubj, 18 -> pobj, 16 -> det, 11 -> aux, 8 -> cc
                                                  //| , 4 -> aux, 15 -> pcomp),Some(AMRGraph(Map(0.0.0.0.1.0.0.1 -> clergyman, 0.1
                                                  //| Output exceeds cutoff limit.
  var state = expertSystem.init(s1)               //> state  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(17, 16, 18, 15, 11, 13, 14, 12, 9, 2, 7, 10, 4, 1, 5, 8, 3,
                                                  //|  102, 103, 101, 6, 104)
                                                  //| Children:	List()
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(1 -> but, 2 -> their, 3 -> clergymen, 4 -> could, 5 -> n't, 
                                                  //| 6 -> manage, 7 -> it, 8 -> and, 9 -> were, 10 -> unable, 11 -> to, 12 -> pas
                                                  //| s, 13 -> it, 14 -> on, 15 -> to, 16 -> the, 17 -> next, 18 -> generation, 10
                                                  //| 1 -> What, 102 -> could, 103 -> they, 104 -> do)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,12
                                                  //| ), 12 -> (12,13), 13 -> (13,14), 14 -> (14,15), 15 -> (15,16), 16 -> (16,17)
                                                  //| , 17 -> (17,18), 18 -> (18,19), 101 -> (19,20), 102 -> (20,21), 103 -> (21,2
                                                  //| 2), 104 -> (22,23))
                                                  //| Edges:	Map((3,2) -> poss, (6,1) -> cc, (6,3) -> nsubj, (6,4) -> aux, (6
                                                  //| ,5) -> neg, (6,7) -> dobj, (6,8) -> cc, (6,10) -> conj, (10,9) -> cop, (10,1
                                                  //| 2) -> xcomp, (12,11) -> aux, (12,13) -> dobj, (12,14) -> prep, (14,15) -> pc
                                                  //| omp, (15,18) -> pobj, (18,16) -> det, (18,17) -> amod, (104,101) -> dobj, (1
                                                  //| 04,102) -> aux, (104,103) -> nsubj)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map()
                                                  //| PartsOfSpeech:	Map(101 -> WP, 5 -> RB, 10 -> JJ, 14 -> IN, 1 -> CC, 6 -
                                                  //| > VB, 102 -> MD, 9 -> VBD, 13 -> PRP, 2 -> PRP$, 17 -> JJ, 12 -> VB, 7 -> PR
                                                  //| P, 103 -> PRP, 3 -> NN, 18 -> NN, 16 -> DT, 11 -> TO, 104 -> VB, 8 -> CC, 4 
                                                  //| -> MD, 15 -> TO)
                                                  //| DependencyLabels:	Map(101 -> dobj, 5 -> neg, 10 -> conj, 14 -> prep, 1 -> 
                                                  //| cc, 102 -> aux, 9 -> cop, 13 -> dobj, 2 -> poss, 17 -> amod, 12 -> xcomp, 7 
                                                  //| -> dobj, 103 -> nsubj, 3 -> nsubj, 18 -> pobj, 16 -> det, 11 -> aux, 8 -> cc
                                                  //| , 4 -> aux, 15 -> pcomp)
                                                  //| Mappings:	1 -> 0.0
                                                  //| 6 -> 0.0.0.0.1
                                                  //| 17 -> 0.0.0.1.1.0.0
                                                  //| 12 -> 0.0.0.1.1
                                                  //| 7 -> 0.0.0.0.1.1
                                                  //| 103 -> 0.0.0.0.1.0.0.0
                                                  //| 3 -> 0.0.0.0.1.0.0.1
                                                  //| 18 -> 0.0.0.1.1.0
                                                  //| 104 -> 0.1.0
                                                  //| 8 -> 0.0.0
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
  s1.positionToAMR                                //> res0: Map[Int,String] = Map(1 -> 0.0, 6 -> 0.0.0.0.1, 17 -> 0.0.0.1.1.0.0, 1
                                                  //| 2 -> 0.0.0.1.1, 7 -> 0.0.0.0.1.1, 103 -> 0.0.0.0.1.0.0.0, 3 -> 0.0.0.0.1.0.0
                                                  //| .1, 18 -> 0.0.0.1.1.0, 104 -> 0.1.0, 8 -> 0.0.0)
  s1.amr.get.nodes                                //> res1: Map[String,String] = Map(0.0.0.0.1.0.0.1 -> clergyman, 0.1 -> possible
                                                  //| -01, 0.0.0 -> and, 0.0.0.1.1.0 -> generation, 0.0.0.1.0 -> -, 0.1.0 -> do-02
                                                  //| , 0.1.0.1 -> amr-unknown, 0.0.0.1.1.0.0 -> next, 0.0 -> contrast-01, 0.0.0.0
                                                  //| .1.0 -> person, 0.0.0.0.1 -> manage-01, 0.0.0.0 -> capable-01, 0.0.0.0.1.0.0
                                                  //|  -> have-org-role-91, 0.1.0.0 -> they, 0 -> multi-sentence, 0.0.0.1 -> capab
                                                  //| le-01, 0.0.0.0.1.0.0.0 -> they, 0.0.0.0.0 -> -, 0.0.0.1.1 -> pass-on-09, 0.0
                                                  //| .0.0.1.1 -> it)
  s1.amr.get.arcs                                 //> res2: Map[(String, String),String] = Map((0.0,0.0.0) -> ARG2, (0.0.0.1.1,0.0
                                                  //| .0.0.1.0) -> ARG0, (0.0.0.1,0.0.0.0.1.0) -> ARG1, (0.0.0.0,0.0.0.0.0) -> pol
                                                  //| arity, (0.0.0,0.0.0.0) -> opN, (0.1,0.1.0) -> ARG1, (0.1.0,0.1.0.0) -> ARG0,
                                                  //|  (0.0.0.1.1.0,0.0.0.1.1.0.0) -> mod, (0.0.0.0.1.0.0,0.0.0.0.1.0.0.0) -> ARG1
                                                  //| , (0.0.0.1.1,0.0.0.1.1.0) -> ARG2, (0.0.0.0.1.0.0,0.0.0.0.1.0.0.1) -> ARG2, 
                                                  //| (0,0.1) -> snt2, (0.0.0.1.1,0.0.0.0.1.1) -> ARG1, (0.0.0.1,0.0.0.1.1) -> ARG
                                                  //| 2, (0.0.0.0,0.0.0.0.1) -> ARG2, (0.1.0,0.1.0.1) -> ARG1, (0.0.0,0.0.0.1) -> 
                                                  //| opN, (0.0.0.1,0.0.0.1.0) -> polarity, (0.0.0.0.1,0.0.0.0.1.1) -> ARG1, (0,0.
                                                  //| 0) -> snt1, (0.0.0.0.1.0,0.0.0.0.1.0.0) -> ARG0-of, (0.0.0.0,0.0.0.0.1.0) ->
                                                  //|  ARG1, (0.0.0.0.1,0.0.0.0.1.0) -> ARG0)
  import java.io._
  val file = new FileWriter("C://AMR//ClergyTarget_exp.txt")
                                                  //> file  : java.io.FileWriter = java.io.FileWriter@37313c65
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
    }                                             //> WARNING: Found duplicate match for concept it
  }
  file.close

AMROutput.convertToString(state.currentGraph.toAMR)
                                                  //> res3: String = ( c5 / multi-sentence
                                                  //| 	:snt1 ( a / contrast-01
                                                  //| 		:ARG2 ( h / and
                                                  //| 			:op1 ( f5 / capable-01
                                                  //| 				:ARG1 ( e5 / person
                                                  //| 					:ARG0-of ( d5 / have-org-role-91
                                                  //| 						:ARG2 ( c / clergyman)))
                                                  //| 				:polarity -
                                                  //| 				:ARG2 ( f / manage-01
                                                  //| 					:ARG0  e5 
                                                  //| 					:ARG1 ( g / it)))
                                                  //| 			:op2 ( a5 / capable-01
                                                  //| 				:ARG1  e5 
                                                  //| 				:polarity -
                                                  //| 				:ARG2 ( l / pass-on-09
                                                  //| 					:ARG0  e5 
                                                  //| 					:ARG1  g 
                                                  //| 					:ARG2 ( r / generation
                                                  //| 						:mod ( q / next)))))))
                                                  //| ( h5 / possible-01
                                                  //| 	:ARG1 ( z4 / do-02
                                                  //| 		:UNKNOWN ( y4 / they)
                                                  //| 		:ARG1 ( i5 / amr-unknown)))
}