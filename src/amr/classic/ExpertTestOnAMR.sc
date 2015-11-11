package amr.classic

object ExpertTestOnAMR {

import amr.{AMRGraph, ImportConcepts, Sentence, RunDagger, AMROutput}

val testData = AMRGraph.importFile("C:\\AMR\\SaudiArabia.txt")
                                                  //> testData  : IndexedSeq[(String, String)] = Vector((Saudi Arabia (SA),(c / co
                                                  //| untry :name (n / name :op1 "Saudi" :op2 "Arabia"))))
  val expert = new ClassicExpert                  //> expert  : amr.classic.ClassicExpert = amr.classic.ClassicExpert@68be2bc2
  val expertSystem = ClassicTransitionSystem      //> expertSystem  : amr.classic.ClassicTransitionSystem.type = amr.classic.Class
                                                  //| icTransitionSystem$@28feb3fa
  AMRGraph.setAligner("JAMR")
  ImportConcepts.initialise("C:\\AMR\\SaudiArabia.txt")
// ImportConcepts.relationStrin
  val s1 = Sentence(testData(0)._1, testData(0)._2)
                                                  //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [2.6 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [6.9 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [2.2 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [5.1 sec].
                                                  //| s1  : amr.Sentence = Sentence(Saudi Arabia (SA),
                                                  //| NodeMap:	Map(1 -> Saudi, 2 -> Arabia, 4 -> SA)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 4 -> (3,4))
                                                  //| Edges:	Map((2,1) -> nn, (2,4) -> appos)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map()
                                                  //| PartsOfSpeech:	Map(1 -> NNP, 2 -> NNP, 4 -> NNP)
                                                  //| DependencyLabels:	Map(1 -> nn, 4 -> appos),Some(AMRGraph(Map(0.0.0 -> "Sau
                                                  //| di", 0.0.1 -> "Arabia", 0.0 -> name, 0 -> country),Map(0 -> (1,3), 0.0 -> (1
                                                  //| ,3), 0.0.0 -> (1,3), 0.0.1 -> (1,3)),Map((0.0,0.0.0) -> opN, (0.0,0.0.1) -> 
                                                  //| opN, (0,0.0) -> name),Map((0.0,0.0.0) -> op1, (0.0,0.0.1) -> op2, (0,0.0) ->
                                                  //|  name),List())),Map(2 -> 0.0.1, 1 -> 0.0.0))
  var state = expertSystem.init(s1)               //> state  : amr.classic.ClassicTransitionState = NodesToProcess: 4, 2, 1
                                                  //| Current Graph: 
                                                  //| NodeMap:	Map(1 -> Saudi, 2 -> Arabia, 4 -> SA)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 4 -> (3,4))
                                                  //| Edges:	Map()
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map()
                                                  //| PartsOfSpeech:	Map(1 -> NNP, 2 -> NNP, 4 -> NNP)
                                                  //| DependencyLabels:	Map()
                                                  //| Fragments: Map()
                                                  //| FragmentHeads: Map()
                                                  //| 
  val action = new Array[ClassicAction](301)      //> action  : Array[amr.classic.ClassicAction] = Array(null, null, null, null, n
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
                                                  //|  null, null, null, null, null, null, null, null, null, null, null, null, nul
                                                  //| l, null, null, null, null, null)
  val stateHistory = new Array[ClassicTransitionState](301)
                                                  //> stateHistory  : Array[amr.classic.ClassicTransitionState] = Array(null, null
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
                                                  //| ll, null, null, null, null, null, null, null, null, null, null, null, null, 
                                                  //| null, null, null, null, null, null, null, null)
  s1.positionToAMR                                //> res0: Map[Int,String] = Map(2 -> 0.0.1, 1 -> 0.0.0)
  s1.amr.get.nodes                                //> res1: Map[String,String] = Map(0.0.0 -> "Saudi", 0.0.1 -> "Arabia", 0.0 -> n
                                                  //| ame, 0 -> country)
  s1.amr.get.arcs                                 //> res2: Map[(String, String),String] = Map((0.0,0.0.0) -> opN, (0.0,0.0.1) -> 
                                                  //| opN, (0,0.0) -> name)
  import java.io._
  val file = new FileWriter("C://AMR//Saudi_improved.txt")
                                                  //> file  : java.io.FileWriter = java.io.FileWriter@6fd83fc1
  for (i <- 0 to 300) {
    if (!expertSystem.isTerminal(state)) {
      stateHistory(i) = state
      action(i) = expert.chooseTransition(s1, state)
      file.write("\n" + state.toString + "\n")
      file.write("\n" + action(i) + " : " + (state.nodesToProcess match { case Nil => ""; case _ => state.nodesToProcess.head }) + "\n")

			file.write((s1.AMRToPosition map {case (amrKey, dtKey) => (dtKey, s1.amr.get.nodes(amrKey))}).toString)
      file.flush
      state = action(i)(state)
      if (expertSystem.isTerminal(state)){
      val finalAMR = expertSystem.construct(state, s1).amr.get
      file.write("\n\n" + finalAMR + "\n\n" + AMROutput.convertToString(finalAMR))
      }
    }
  }
  file.close
  val outputAdv = RunDagger.sampleTrajectory(s1, "C:\\AMR\\WangXueExpert_output.txt", expert, expertSystem = expertSystem)
                                                  //> outputAdv  : amr.Sentence = Sentence(Saudi Arabia (SA),
                                                  //| NodeMap:	Map(5 -> country)
                                                  //| SpanMap:	Map(5 -> (2,3))
                                                  //| Edges:	Map()
                                                  //| InsertedNodes:	Map(5 -> )
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map(-1 -> List((1,Saudi), (4,SA)), 5 -> List((2,Arabia))
                                                  //| )
                                                  //| PartsOfSpeech:	Map(1 -> NNP, 2 -> NNP, 4 -> NNP, 5 -> NNP)
                                                  //| DependencyLabels:	Map(5 -> DUMMY),Some(AMRGraph(Map(5 -> country, 501 -> n
                                                  //| ame, 1 -> "Saudi", 2 -> "Arabia"),Map(5 -> (2,3)),Map((5,501) -> name, (501
                                                  //| ,1) -> opN, (501,2) -> opN),Map(),List())),Map(5 -> 5))
     
}