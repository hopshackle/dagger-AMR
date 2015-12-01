package amr

object problematicAMR {

val testData = AMRGraph.importFile("C:\\AMR\\wikifiedSaudiArabia.txt")
                                                  //> testData  : IndexedSeq[(String, String)] = Vector((Saudi Arabia (SA),(c / co
                                                  //| untry :wiki "Saudi_Arabia" :name (n / name :op1 "Saudi" :op2 "Arabia"))))
  val expert = new WangXueExpert                  //> expert  : amr.WangXueExpert = amr.WangXueExpert@68be2bc2
  val expertSystem = WangXueTransitionSystem      //> expertSystem  : amr.WangXueTransitionSystem.type = amr.WangXueTransitionSyst
                                                  //| em$@4c70fda8
  AMRGraph.setAligner("JAMR")
  expertSystem.reentrance = true
  expertSystem.useCompositeNodes = false
  ImportConcepts.initialise("C:\\AMR\\wikifiedSaudiArabia.txt")
// ImportConcepts.relationStrin
  val s1 = Sentence(testData(0)._1, testData(0)._2)
                                                  //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.9 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [7.5 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [7.3 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [8.2 sec].
                                                  //| s1  : amr.Sentence = Sentence(Saudi Arabia (SA),
                                                  //| NodeMap:	Map(1 -> Saudi, 2 -> Arabia, 4 -> SA)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 4 -> (3,4))
                                                  //| Edges:	Map((2,1) -> nn, (2,4) -> appos)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map()
                                                  //| PartsOfSpeech:	Map(1 -> NNP, 2 -> NNP, 4 -> NNP)
                                                  //| DependencyLabels:	Map(1 -> nn, 4 -> appos),Some(AMRGraph(Map(0.1 -> name, 
                                                  //| 0.1.0 -> "Saudi", 0.0 -> "Saudi_Arabia", 0 -> country, 0.1.1 -> "Arabia"),Ma
                                                  //| p(0 -> (1,3), 0.1 -> (1,3), 0.1.0 -> (1,3), 0.1.1 -> (1,3)),Map((0.1,0.1.0) 
                                                  //| -> opN, (0.1,0.1.1) -> opN, (0,0.0) -> wiki, (0,0.1) -> name),Map((0.1,0.1.0
                                                  //| ) -> op1, (0.1,0.1.1) -> op2, (0,0.0) -> wiki, (0,0.1) -> name),List())),Map
                                                  //| (2 -> 0.1.1, 1 -> 0.1.0))
  var state = expertSystem.init(s1)               //> state  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(1, 4, 2)
                                                  //| Children:	List()
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(1 -> Saudi, 2 -> Arabia, 4 -> SA)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 4 -> (3,4))
                                                  //| Edges:	Map((2,1) -> nn, (2,4) -> appos)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map()
                                                  //| PartsOfSpeech:	Map(1 -> NNP, 2 -> NNP, 4 -> NNP)
                                                  //| DependencyLabels:	Map(1 -> nn, 4 -> appos)
                                                  //| Mappings:	2 -> 0.1.1
                                                  //| 1 -> 0.1.0
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
  s1.positionToAMR                                //> res0: Map[Int,String] = Map(2 -> 0.1.1, 1 -> 0.1.0)
  s1.amr.get.nodes                                //> res1: Map[String,String] = Map(0.1 -> name, 0.1.0 -> "Saudi", 0.0 -> "Saudi_
                                                  //| Arabia", 0 -> country, 0.1.1 -> "Arabia")
  s1.amr.get.arcs                                 //> res2: Map[(String, String),String] = Map((0.1,0.1.0) -> opN, (0.1,0.1.1) -> 
                                                  //| opN, (0,0.0) -> wiki, (0,0.1) -> name)
  import java.io._
  val file = new FileWriter("C://AMR//wikifiedSaudiArabia_exp.txt")
                                                  //> file  : java.io.FileWriter = java.io.FileWriter@6404f418
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
    }                                             //> Full wikiString = 
                                                  //| ForwardConcat = ""
                                                  //| Full wikiString = 
                                                  //| ForwardConcat = ""
                                                  //| Full wikiString = 
                                                  //| ForwardConcat = ""
                                                  //| Full wikiString = 
                                                  //| ForwardConcat = ""
                                                  //| Full wikiString = 
                                                  //| ForwardConcat = ""
                                                  //| Full wikiString = "Saudi_Arabia"
                                                  //| ForwardConcat = "Saudi_Arabia"
                                                  //| Full wikiString = "Saudi_Arabia"
                                                  //| ForwardConcat = "Saudi_Arabia"
                                                  //| Full wikiString = 
                                                  //| ForwardConcat = ""
                                                  //| Full wikiString = 
                                                  //| ForwardConcat = ""
                                                  //| Full wikiString = "Saudi_Arabia"
                                                  //| ForwardConcat = "Saudi_Arabia"
                                                  //| Full wikiString = 
                                                  //| ForwardConcat = ""
                                                  //| Full wikiString = 
                                                  //| ForwardConcat = ""
                                                  //| Full wikiString = 
                                                  //| ForwardConcat = ""
                                                  //| Full wikiString = 
                                                  //| ForwardConcat = ""
                                                  //| Full wikiString = "Saudi_Arabia"
                                                  //| ForwardConcat = "Saudi_Arabia"
                                                  //| Full wikiString = "Saudi_Arabia"
                                                  //| ForwardConcat = "Saudi_Arabia"
  }
  file.close

AMROutput.convertToString(state.currentGraph.toAMR)
                                                  //> res3: String = ( f / country
                                                  //| 	:name ( e / name
                                                  //| 		:op1 "Saudi"
                                                  //| 		:op2 "Arabia")
                                                  //| 	:wiki "Saudi_Arabia")
}