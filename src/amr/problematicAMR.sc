package amr

object problematicAMR {

  val testData = AMRGraph.importFile("C:\\AMR\\Cancer.txt")
                                                  //> testData  : IndexedSeq[(String, String, String)] = Vector((10% of smokers die
                                                  //|  of lung cancer.,(i / include-91 :ARG1 (p / person :ARG1-of (d / die-01 :ARG1
                                                  //| -of (c2 / cause-01 :ARG0 (c / cancer :mod (l / lung))))) :ARG2 (p2 / person :
                                                  //| ARG0-of (s2 / smoke-02)) :ARG3 (p3 / percentage-entity :value 10)),# ::id isi
                                                  //| _0002.17))
  DependencyTree.preProcess(testData(0)._1)       //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.4 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [3.8 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [3.3 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [2.7 sec].
                                                  //| res0: List[String] = List(10, %, of, smokers, die, of, lung, cancer)
  val expert = new WangXueExpert                  //> expert  : amr.WangXueExpert = amr.WangXueExpert@7cc0cdad
  val expertSystem = WangXueTransitionSystem      //> expertSystem  : amr.WangXueTransitionSystem.type = amr.WangXueTransitionSyst
                                                  //| em$@78691363
  AMRGraph.setAligner("Pourdamghani")
  expertSystem.reentrance = true
  expertSystem.useCompositeNodes = false
  expertSystem.insertBelow = true
  ImportConcepts.initialise("C:\\AMR\\Cancer.txt")
  // ImportConcepts.relationStrin
  val s1 = Sentence(testData(0)._1, testData(0)._2, testData(0)._3)
                                                  //> s1  : amr.Sentence = # ::id isi_0002.17
                                                  //| 10% of smokers die of lung cancer.
                                                  //| 
                                                  //| NodeMap:	Map(1 -> 10, 2 -> %, 3 -> of, 4 -> smokers, 5 -> die, 6 -> of, 7
                                                  //|  -> lung, 8 -> cancer)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9))
                                                  //| Edges:	Map((2,1) -> num, (2,3) -> prep, (3,4) -> pobj, (5,2) -> nsubj, 
                                                  //| (5,6) -> prep, (6,8) -> pobj, (8,7) -> nn)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map()
                                                  //| PartsOfSpeech:	Map(5 -> VBP, 1 -> CD, 6 -> IN, 2 -> NN, 7 -> NN, 3 -> I
                                                  //| N, 8 -> NN, 4 -> NNS)
                                                  //| DependencyLabels:	Map(1 -> num, 6 -> prep, 2 -> nsubj, 7 -> nn, 3 -> prep,
                                                  //|  8 -> pobj, 4 -> pobj)
                                                  //| AMRGraph(Map(0.1 -> person, 0.0.0 -> die-01, 0.1.0 -> smoke-02, 0.0 -> perso
                                                  //| n, 0.0.0.0 -> cause-01, 0.2.0 -> 10, 0 -> include-91, 0.2 -> percentage-enti
                                                  //| ty, 0.0.0.0.0.0 -> lung, 0.0.0.0.0 -> cancer),Map(0.1 -> (4,5), 0.0 -> (5,6)
                                                  //| , 0.2.0 -> (1,2), 0.2 -> (2,3), 0.0.0.0.0.0 -> (7,8), 0.0.0.0.0 -> (8,9)),Ma
                                                  //| p((0.0,0.0.0) -> ARG1-of, (0.0.0.0,0.0.0.0.0) -> ARG0, (0.0.0,0.0.0.0) -> AR
                                                  //| G1-of, (0.1,0.1.0) -> ARG0-of, (0,0.2) -> ARG3, (0,0.1) -> ARG2, (0.2,0.2.0)
                                                  //|  -> value, (0.0.0.0.0,0.0.0.0.0.0) -> mod, (0,0.0) -> ARG1),Map((0.0,0.0.0) 
                                                  //| -> ARG1-of, (0.0.0.0,0.0.0.0.0) -> ARG0, (0.0.0,0.0.0.0) -> ARG1-of, (0.1,0.
                                                  //| 1.0) -> ARG0-of, (0,0.2) -> ARG3, (0,0.1) -> ARG2, (0.2,0.2.0) -> value, (0.
                                                  //| 0.0.0.0,0.0.0.0.0.0) -> mod, (0,0.0) -> ARG1),List())
                                                  //| Map(5 -> 0.0, 1 -> 0.2.0, 2 -> 0.2, 7 -> 0.0.0.0.0.0, 8 -> 0.0.0.0.0, 4 -> 0
                                                  //| .1)
                                                  //| 
  var state = expertSystem.init(s1)               //> state  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(4, 7, 3, 1, 8, 2, 6, 5)
                                                  //| Children:	List()
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(1 -> 10, 2 -> %, 3 -> of, 4 -> smokers, 5 -> die, 6 -> of, 7
                                                  //|  -> lung, 8 -> cancer)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9))
                                                  //| Edges:	Map((2,1) -> num, (2,3) -> prep, (3,4) -> pobj, (5,2) -> nsubj, 
                                                  //| (5,6) -> prep, (6,8) -> pobj, (8,7) -> nn)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map()
                                                  //| PartsOfSpeech:	Map(5 -> VBP, 1 -> CD, 6 -> IN, 2 -> NN, 7 -> NN, 3 -> I
                                                  //| N, 8 -> NN, 4 -> NNS)
                                                  //| DependencyLabels:	Map(1 -> num, 6 -> prep, 2 -> nsubj, 7 -> nn, 3 -> prep,
                                                  //|  8 -> pobj, 4 -> pobj)
                                                  //| Mappings:	5 -> 0.0
                                                  //| 1 -> 0.2.0
                                                  //| 2 -> 0.2
                                                  //| 7 -> 0.0.0.0.0.0
                                                  //| 8 -> 0.0.0.0.0
                                                  //| 4 -> 0.1
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
  s1.positionToAMR                                //> res1: Map[Int,String] = Map(5 -> 0.0, 1 -> 0.2.0, 2 -> 0.2, 7 -> 0.0.0.0.0.0
                                                  //| , 8 -> 0.0.0.0.0, 4 -> 0.1)
  s1.amr.get.nodes                                //> res2: Map[String,String] = Map(0.1 -> person, 0.0.0 -> die-01, 0.1.0 -> smok
                                                  //| e-02, 0.0 -> person, 0.0.0.0 -> cause-01, 0.2.0 -> 10, 0 -> include-91, 0.2 
                                                  //| -> percentage-entity, 0.0.0.0.0.0 -> lung, 0.0.0.0.0 -> cancer)
  s1.amr.get.arcs                                 //> res3: Map[(String, String),String] = Map((0.0,0.0.0) -> ARG1-of, (0.0.0.0,0.
                                                  //| 0.0.0.0) -> ARG0, (0.0.0,0.0.0.0) -> ARG1-of, (0.1,0.1.0) -> ARG0-of, (0,0.2
                                                  //| ) -> ARG3, (0,0.1) -> ARG2, (0.2,0.2.0) -> value, (0.0.0.0.0,0.0.0.0.0.0) ->
                                                  //|  mod, (0,0.0) -> ARG1)
  s1.dependencyTree.nodeLemmas                    //> res4: Map[Int,String] = Map(5 -> die, 1 -> ##, 6 -> of, 2 -> %, 7 -> lung, 3
                                                  //|  -> of, 8 -> cancer, 4 -> smoker)
 ImportConcepts.conceptStrings foreach println    //> percentage-entity
                                                  //| include-91
                                                  //| cause-01
                                                  //| die-01
                                                  //| 10
                                                  //| lung
                                                  //| cancer
                                                  //| person
                                                  //| smoke-02
 ImportConcepts.conceptsPerLemma foreach println  //> (%,Set(1))
                                                  //| (lung,Set(6))
                                                  //| (die,Set(8))
                                                  //| (cancer,Set(7))
                                                  //| (smoker,Set(8))
                                                  //| (##,Set())
  import java.io._
  val file = new FileWriter("C://AMR//Cancer_exp.txt")
                                                  //> file  : java.io.FileWriter = java.io.FileWriter@4d14b6c2
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
    }
  }
  file.close

  AMROutput.convertToString(state.currentGraph.toAMR)
                                                  //> res5: String = ( i / include-91 
                                                  //| 	:ARG3 ( b / percentage-entity :value 10)
                                                  //| 	:ARG2 ( d / person 
                                                  //| 		:ARG0-of ( j / smoke-02 ))
                                                  //| 	:ARG1 ( e / person 
                                                  //| 		:ARG1-of ( l / die-01 
                                                  //| 			:ARG1-of ( k / cause-01 
                                                  //| 				:ARG0 ( h / cancer 
                                                  //| 					:mod ( g / lung ))))))
  AMROutput.convertToString(Smatch.reduceAMR(state.currentGraph.toAMR))
                                                  //> res6: String = ( i / include-91 
                                                  //| 	:ARG3 ( b / percentage-entity )
                                                  //| 	:ARG2 ( d / person 
                                                  //| 		:ARG0-of ( j / smoke-02 ))
                                                  //| 	:ARG1 ( e / person 
                                                  //| 		:ARG1-of ( l / die-01 
                                                  //| 			:ARG1-of ( k / cause-01 
                                                  //| 				:ARG0 ( h / cancer 
                                                  //| 					:mod ( g / lung ))))))
}