package amr

object problematicAMR {

  val testData = AMRGraph.importFile("C:\\AMR\\VeniceTarget.txt")
                                                  //> testData  : IndexedSeq[(String, String)] = Vector((Venice High School histor
                                                  //| y teacher Craig Brandau,(p / person :wiki - :name (n / name :op1 "Craig" :op
                                                  //| 2 "Brandau") :ARG0-of (h2 / have-org-role-91 :ARG1 (s / school :wiki "Venice
                                                  //| _High_School_(Los_Angeles)" :name (n2 / name :op1 "Venice" :op2 "High" :op3 
                                                  //| "School")) :ARG3 (t / teach-01 :ARG0 p :ARG1 (h / history))))))
  val expert = new WangXueExpert                  //> expert  : amr.WangXueExpert = amr.WangXueExpert@2a5ca609
  val expertSystem = WangXueTransitionSystem      //> expertSystem  : amr.WangXueTransitionSystem.type = amr.WangXueTransitionSyst
                                                  //| em$@4c70fda8
  AMRGraph.setAligner("JAMR")
  expertSystem.reentrance = true
  expertSystem.useCompositeNodes = false
  expertSystem.insertBelow = true                 //> res0: <error> = ()
  ImportConcepts.initialise("C:\\AMR\\VeniceTarget.txt")
  // ImportConcepts.relationStrin
  val s1 = Sentence(testData(0)._1, testData(0)._2)
                                                  //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [2.2 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [8.3 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [3.3 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [8.2 sec].
                                                  //| s1  : amr.Sentence = Sentence(Venice High School history teacher Craig Brand
                                                  //| au,
                                                  //| NodeMap:	Map(1 -> Venice, 2 -> High, 3 -> School, 4 -> history, 5 -> teac
                                                  //| her, 6 -> Craig, 7 -> Brandau)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8))
                                                  //| Edges:	Map((3,1) -> nn, (3,2) -> nn, (3,5) -> dep, (3,7) -> dep, (5,4) 
                                                  //| -> nn, (7,6) -> nn)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map()
                                                  //| PartsOfSpeech:	Map(5 -> NN, 1 -> NNP, 6 -> NNP, 2 -> NNP, 7 -> NNP, 3 -
                                                  //| > NNP, 4 -> NN)
                                                  //| DependencyLabels:	Map(5 -> dep, 1 -> nn, 6 -> nn, 2 -> nn, 7 -> dep, 4 -> 
                                                  //| nn),Some(AMRGraph(Map(0.1 -> name, 0.1.0 -> "Craig", 0.2.0.1.2 -> "School", 
                                                  //| 0.2.1 -> teach-01, 0.0 -> -, 0.2.0.1.1 -> "High", 0.2.0.1 -> name, 0.2.0 -> 
                                                  //| school, 0.2.0.1.0 -> "Venice", 0 -> person, 0.2 -> have-org-role-91, 0.1.1 -
                                                  //| > "Brandau", 0.2.0.0 -> "Venice_High_School_(Los_Angeles)", 0.2.1.0 -> histo
                                                  //| ry),Map(0.1 -> (6,8), 0.1.0 -> (6,8), 0.2.1 -> (5,6), 0.2.0.1.1 -> (1,3), 0.
                                                  //| 2.0.1 -> (1,3), 0.2.0 -> (3,4), 0.2.0.1.0 -> (1,3), 0 -> (6,8), 0.1.1 -> (6,
                                                  //| 8), 0.2.1.0 -> (4,5)),Map((0.2.0.1,0.2.0.1.1) -> opN, (0.1,0.1.0) -> opN, (0
                                                  //| .2.1,0) -> ARG0, (0,0.2) -> ARG0-of, (0,0.1) -> name, (0.2,0.2.0) -> ARG1, (
                                                  //| 0.2.0.1,0.2.0.1.0) -> opN, (0.2.1,0.2.1.0) -> ARG1, (0.2,0.2.1) -> ARG3, (0,
                                                  //| 0.0) -> wiki, (0.2.0.1,0.2.0.1.2) -> opN, (0.2.0,0.2.0.0) -> wiki, (0.2.0,0.
                                                  //| 2.0.1) -> name, (0.1,0.1.1) -> opN),Map((0.2.0.1,0.2.0.1.1) -> op2, (0.1,0.1
                                                  //| .0) -> op1, (0.2.1,0) -> ARG0, (0,0.2) -> ARG0-of, (0,0.1) -> name, (0.2,0.2
                                                  //| .0) -> ARG1, (0.2.0.1,0.2.0.1.0) -> op1, (0.2.1,0.2.1.0) -> ARG1, (0.2,0.2.1
                                                  //| ) -> ARG3, (0,0.0) -> wiki, (0.2.0.1,0.2.0.1.2) -> op3, (0.2.0,0.2.0.0) -> w
                                                  //| iki, (0.2.0,0.2.0.1) -> name, (0.1,0.1.1) -> op2),List())),Ma
                                                  //| Output exceeds cutoff limit.
  var state = expertSystem.init(s1)               //> state  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(4, 6, 1, 5, 2, 7, 3)
                                                  //| Children:	List()
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(1 -> Venice, 2 -> High, 3 -> School, 4 -> history, 5 -> teac
                                                  //| her, 6 -> Craig, 7 -> Brandau)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8))
                                                  //| Edges:	Map((3,1) -> nn, (3,2) -> nn, (3,5) -> dep, (3,7) -> dep, (5,4) 
                                                  //| -> nn, (7,6) -> nn)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map()
                                                  //| PartsOfSpeech:	Map(5 -> NN, 1 -> NNP, 6 -> NNP, 2 -> NNP, 7 -> NNP, 3 -
                                                  //| > NNP, 4 -> NN)
                                                  //| DependencyLabels:	Map(5 -> dep, 1 -> nn, 6 -> nn, 2 -> nn, 7 -> dep, 4 -> 
                                                  //| nn)
                                                  //| Mappings:	5 -> 0.2.1
                                                  //| 1 -> 0.2.0.1.0
                                                  //| 6 -> 0.1.0
                                                  //| 2 -> 0.2.0.1.1
                                                  //| 7 -> 0.1.1
                                                  //| 3 -> 0.2.0
                                                  //| 4 -> 0.2.1.0
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
  s1.positionToAMR                                //> res1: Map[Int,String] = Map(5 -> 0.2.1, 1 -> 0.2.0.1.0, 6 -> 0.1.0, 2 -> 0.2
                                                  //| .0.1.1, 7 -> 0.1.1, 3 -> 0.2.0, 4 -> 0.2.1.0)
  s1.amr.get.nodes                                //> res2: Map[String,String] = Map(0.1 -> name, 0.1.0 -> "Craig", 0.2.0.1.2 -> "
                                                  //| School", 0.2.1 -> teach-01, 0.0 -> -, 0.2.0.1.1 -> "High", 0.2.0.1 -> name, 
                                                  //| 0.2.0 -> school, 0.2.0.1.0 -> "Venice", 0 -> person, 0.2 -> have-org-role-91
                                                  //| , 0.1.1 -> "Brandau", 0.2.0.0 -> "Venice_High_School_(Los_Angeles)", 0.2.1.0
                                                  //|  -> history)
  s1.amr.get.arcs                                 //> res3: Map[(String, String),String] = Map((0.2.0.1,0.2.0.1.1) -> opN, (0.1,0.
                                                  //| 1.0) -> opN, (0.2.1,0) -> ARG0, (0,0.2) -> ARG0-of, (0,0.1) -> name, (0.2,0.
                                                  //| 2.0) -> ARG1, (0.2.0.1,0.2.0.1.0) -> opN, (0.2.1,0.2.1.0) -> ARG1, (0.2,0.2.
                                                  //| 1) -> ARG3, (0,0.0) -> wiki, (0.2.0.1,0.2.0.1.2) -> opN, (0.2.0,0.2.0.0) -> 
                                                  //| wiki, (0.2.0,0.2.0.1) -> name, (0.1,0.1.1) -> opN)
  import java.io._
  val file = new FileWriter("C://AMR//VeniceTarget_exp.txt")
                                                  //> file  : java.io.FileWriter = java.io.FileWriter@72e5a8e
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
                                                  //> res4: String = ( i / person  !  ? ARG0-of 
                                                  //| 	:ARG0-of ( l / have-org-role-91  !  ? ARG1 
                                                  //| 		:ARG1 ( c / school  !  ? name 
                                                  //| 			:name ( j / name  !  ? opN 
                                                  //| 				:opN ( a / "Venice"  ! ) ? opN 
                                                  //| 				:opN ( k / "WORD"  ! ) ? opN 
                                                  //| 				:opN ( b / "High"  ! )) ? wiki 
                                                  //| 			:wiki ( m / "Venice_High_School_(Los_Angeles)"  ! )) ? A
                                                  //| RG3 
                                                  //| 		:ARG3 ( e / teach-01  !  ? ARG1 
                                                  //| 			:ARG1 ( d / history  ! ))) ? wiki 
                                                  //| 	:wiki ( n / "-"  ! ) ? name 
                                                  //| 	:name ( h / name  !  ? opN 
                                                  //| 		:opN ( f / "Craig"  ! ) ? opN 
                                                  //| 		:opN ( g / "Brandau"  ! )))
  AMROutput.convertToString(Smatch.reduceAMR(state.currentGraph.toAMR))
                                                  //> res5: String = ( i / person :wiki "-" !  ? ARG0-of 
                                                  //| 	:ARG0-of ( l / have-org-role-91  !  ? ARG1 
                                                  //| 		:ARG1 ( c / school :wiki "Venice_High_School_(Los_Angeles)" !  ?
                                                  //|  name 
                                                  //| 			:name ( j / name :op1 "Venice" :op2 "WORD" :op3 "High" !
                                                  //|  )) ? ARG3 
                                                  //| 		:ARG3 ( e / teach-01  !  ? ARG1 
                                                  //| 			:ARG1 ( d / history  ! ))) ? name 
                                                  //| 	:name ( h / name :op1 "Craig" :op2 "Brandau" ! ))
}