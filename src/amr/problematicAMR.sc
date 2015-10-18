package amr

object problematicAMR {

val testData = AMRGraph.importFile("C:\\AMR\\CountryList.txt")
                                                  //> testData  : IndexedSeq[(String, String)] = Vector((Estonia (EE); Germany (DE
                                                  //| ): Italy (IT); Spain (ES); United States (US),(a / and :op1 (c / country :na
                                                  //| me (n / name :op1 "Estonia")) :op2 (c4 / country :name (n7 / name :op1 "Germ
                                                  //| any")) :op3 (c5 / country :name (n9 / name :op1 "Italy")) :op4 (c6 / country
                                                  //|  :name (n10 / name :op1 "Spain")) :op5 (c8 / country :name (n14 / name :op1 
                                                  //| "United" :op2 "States")))))
  val expert = new WangXueExpert                  //> expert  : amr.WangXueExpert = amr.WangXueExpert@675d3402
  val expertSystem = WangXueTransitionSystem      //> expertSystem  : amr.WangXueTransitionSystem.type = amr.WangXueTransitionSyst
                                                  //| em$@d8355a8
  expertSystem.reentrance = true
  ImportConcepts.initialise("C:\\AMR\\CountryList.txt")
// ImportConcepts.relationString
  val s1 = Sentence(testData(0)._1, testData(0)._2)
                                                  //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.1 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [4.5 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [2.2 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [4.3 sec].
                                                  //| s1  : amr.Sentence = Sentence(Estonia (EE); Germany (DE): Italy (IT); Spain 
                                                  //| (ES); United States (US),
                                                  //| NodeMap:	Map(1 -> Estonia, 3 -> EE, 6 -> Germany, 8 -> DE, 11 -> Italy, 1
                                                  //| 3 -> IT, 16 -> Spain, 18 -> ES, 21 -> United, 22 -> States, 24 -> US)
                                                  //| SpanMap:	Map(1 -> (1,2), 3 -> (2,3), 6 -> (3,4), 8 -> (4,5), 11 -> (5,6),
                                                  //|  13 -> (6,7), 16 -> (7,8), 18 -> (8,9), 21 -> (9,10), 22 -> (10,11), 24 -> (
                                                  //| 11,12))
                                                  //| Edges:	Map((3,1) -> nn, (3,6) -> dep, (6,8) -> appos, (6,11) -> dep, (6
                                                  //| ,16) -> dep, (6,22) -> dep, (11,13) -> dep, (16,18) -> appos, (22,21) -> nn,
                                                  //|  (22,24) -> appos)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map(),Some(AMRGraph(Map(0.1 -> country, 0.0.0 -> name, 0
                                                  //| .1.0 -> name, 0.0 -> country, 0.4 -> country, 0.0.0.0 -> "Estonia", 0.2.0 ->
                                                  //|  name, 0.3.0.0 -> "Spain", 0.3.0 -> name, 0.1.0.0 -> "Germany", 0.4.0.0 -> "
                                                  //| United", 0.3 -> country, 0.4.0 -> name, 0 -> and, 0.2 -> country, 0.4.0.1 ->
                                                  //|  "States", 0.2.0.0 -> "Italy"),Map(0.1 -> (3,4), 0.0.0 -> (1,2), 0.1.0 -> (3
                                                  //| ,4), 0.0 -> (1,2), 0.4 -> (9,11), 0.0.0.0 -> (1,2), 0.2.0 -> (5,6), 0.3.0.0 
                                                  //| -> (7,8), 0.3.0 -> (7,8), 0.1.0.0 -> (3,4), 0.4.0.0 -> (9,11), 0.3 -> (7,8),
                                                  //|  0.4.0 -> (9,11), 0.2 -> (5,6), 0.4.0.1 -> (9,11), 0.2.0.0 -> (5,6)),Map((0.
                                                  //| 0,0.0.0) -> name, (0.3.0,0.3.0.0) -> opN, (0.4.0,0.4.0.1) -> opN, (0,0.3) ->
                                                  //|  opN, (0.4.0,0.4.0.0) -> opN, (0.0.0,0.0.0.0) -> opN, (0.1,0.1.0) -> name, (
                                                  //| 0.1.0,0.1.0.0) -> opN, (0,0.2) -> opN, (0,0.1) -> opN, (0.2,0.2.0) -> name, 
                                                  //| (0.3,0.3.0) -> name, (0.4,0.4.0) -> name, (0,0.4) -> opN, (0,0.0) -> opN, (0
                                                  //| .2.0,0.2.0.0) -> opN),List())),Map(1 -> 0.0.0.0, 6 -> 0.1.0.0, 21 -> 0.4.0.0
                                                  //| , 22 -> 0.4.0.1, 16 -> 0.3.0.0, 11 -> 0.2.0.0))
  var state = expertSystem.init(s1)               //> state  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(21, 24, 13, 18, 22, 8, 11, 16, 1, 6, 3)
                                                  //| Children:	List()
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(1 -> Estonia, 3 -> EE, 6 -> Germany, 8 -> DE, 11 -> Italy, 1
                                                  //| 3 -> IT, 16 -> Spain, 18 -> ES, 21 -> United, 22 -> States, 24 -> US)
                                                  //| SpanMap:	Map(1 -> (1,2), 3 -> (2,3), 6 -> (3,4), 8 -> (4,5), 11 -> (5,6),
                                                  //|  13 -> (6,7), 16 -> (7,8), 18 -> (8,9), 21 -> (9,10), 22 -> (10,11), 24 -> (
                                                  //| 11,12))
                                                  //| Edges:	Map((3,1) -> nn, (3,6) -> dep, (6,8) -> appos, (6,11) -> dep, (6
                                                  //| ,16) -> dep, (6,22) -> dep, (11,13) -> dep, (16,18) -> appos, (22,21) -> nn,
                                                  //|  (22,24) -> appos)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map()
                                                  //| Mappings:	1 -> 0.0.0.0
                                                  //| 6 -> 0.1.0.0
                                                  //| 21 -> 0.4.0.0
                                                  //| 22 -> 0.4.0.1
                                                  //| 16 -> 0.3.0.0
                                                  //| 11 -> 0.2.0.0
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
  s1.positionToAMR                                //> res0: Map[Int,String] = Map(1 -> 0.0.0.0, 6 -> 0.1.0.0, 21 -> 0.4.0.0, 22 ->
                                                  //|  0.4.0.1, 16 -> 0.3.0.0, 11 -> 0.2.0.0)
  s1.amr.get.nodes                                //> res1: Map[String,String] = Map(0.1 -> country, 0.0.0 -> name, 0.1.0 -> name,
                                                  //|  0.0 -> country, 0.4 -> country, 0.0.0.0 -> "Estonia", 0.2.0 -> name, 0.3.0.
                                                  //| 0 -> "Spain", 0.3.0 -> name, 0.1.0.0 -> "Germany", 0.4.0.0 -> "United", 0.3 
                                                  //| -> country, 0.4.0 -> name, 0 -> and, 0.2 -> country, 0.4.0.1 -> "States", 0.
                                                  //| 2.0.0 -> "Italy")
  s1.amr.get.arcs                                 //> res2: Map[(String, String),String] = Map((0.0,0.0.0) -> name, (0.3.0,0.3.0.0
                                                  //| ) -> opN, (0.4.0,0.4.0.1) -> opN, (0,0.3) -> opN, (0.4.0,0.4.0.0) -> opN, (0
                                                  //| .0.0,0.0.0.0) -> opN, (0.1,0.1.0) -> name, (0.1.0,0.1.0.0) -> opN, (0,0.2) -
                                                  //| > opN, (0,0.1) -> opN, (0.2,0.2.0) -> name, (0.3,0.3.0) -> name, (0.4,0.4.0)
                                                  //|  -> name, (0,0.4) -> opN, (0,0.0) -> opN, (0.2.0,0.2.0.0) -> opN)
  import java.io._
  val file = new FileWriter("C://AMR//i4_output.txt")
                                                  //> file  : java.io.FileWriter = java.io.FileWriter@183ec003
  for (i <- 0 to 300) {
    if (!expertSystem.isTerminal(state)) {
      stateHistory(i) = state
      action(i) = expert.chooseTransition(s1, state)
      file.write(state.toString + "\n")
      file.write(action(i) + " : " + (state.nodesToProcess match { case Nil => ""; case _ => state.nodesToProcess.head }) + " -> " + (
        state.childrenToProcess match { case Nil => ""; case _ => state.childrenToProcess.head }) + "\n")

			file.write(s1.AMRToPosition.toString)
      file.flush
      state = action(i)(state)
    }                                             //> Considering current node: 21 with child Nil
                                                  //| SIGMAAMR = Some(0.4.0.0)
                                                  //| Set(0, 0.4, 0.4.0, 0.4.0.0)
                                                  //| sigmaAMRParents = ArrayBuffer(0.4.0)
                                                  //| betaAMR = None
                                                  //| Action chosen: InsertNode: name (Ref: 0.4.0)
                                                  //| Considering current node: 21 with child Nil
                                                  //| SIGMAAMR = Some(0.4.0.0)
                                                  //| Set(0, 0.4, 0.4.0, 0.4.0.0)
                                                  //| sigmaAMRParents = ArrayBuffer(0.4.0)
                                                  //| betaAMR = None
                                                  //| Action chosen: NextNode: 6 -> United
                                                  //| Considering current node: 24 with child Nil
                                                  //| SIGMAAMR = None
                                                  //| Set()
                                                  //| sigmaAMRParents = List()
                                                  //| betaAMR = None
                                                  //| Action chosen: DeleteNode
                                                  //| Considering current node: 25 with child 21
                                                  //| SIGMAAMR = Some(0.4.0)
                                                  //| Set(0, 0.4, 0.4.0)
                                                  //| sigmaAMRParents = ArrayBuffer(0.4)
                                                  //| betaAMR = Some(0.4.0.0)
                                                  //| Action chosen: InsertNode: country (Ref: 0.4)
                                                  //| Considering current node: 25 with child 21
                                                  //| SIGMAAMR = Some(0.4.0)
                                                  //| Set(0, 0.4, 0.4.0)
                                                  //| sigmaAMRParents = ArrayBuffer(0.4)
                                                  //| betaAMR = Some(0.4.0.0)
                                                  //| Loading Relations
                                                  //| Action chosen: NextEdge: 2 -> opN
                                                  //| Considering current node: 26 with child 25
                                                  //| SIGMAAMR = Some(0.4)
                                                  //| Set(0, 0.4)
                                                  //| sigmaAMRParents = ArrayBuffer(0)
                                                  //| betaAMR = Some(0.4.0)
                                                  //| Action chosen: InsertNode: and (Ref: 0)
                                                  //| Considering current node: 26 with child 25
                                                  //| SIGMAAMR = Some(0.4)
                                                  //| Set(0, 0.4)
                                                  //| sigmaAMRParents = ArrayBuffer(0)
                                                  //| betaAMR = Some(0.4.0)
                                                  //| Action chosen: NextEdge: 1 -> name
                                                  //| Considering current node: 27 with child 26
                                                  //| SIGMAAMR = Some(0)
                                                  //| Set(0)
                                                  //| sigmaAMRParents = ArrayBuffer()
                                                  //| betaAMR = Some(0.4)
                                                  //| Action chosen: NextEdge: 2 -> opN
                                                  //| Considering current node: 13 with child Nil
                                                  //| SIGMAAMR = None
                                                  //| Set()
                                                  //| sigmaAMRParents = List()
                                                  //| betaAMR = None
                                                  //| Action chosen: DeleteNode
                                                  //| Considering current node: 18 with child Nil
                                                  //| SIGMAAMR = None
                                                  //| Set()
                                                  //| sigmaAMRParents = List()
                                                  //| betaAMR = None
                                                  //| Action chosen: DeleteNode
                                                  //| Considering current node: 22 with child 27
                                                  //| SIGMAAMR = Some(0.4.0.1)
                                                  //| Set(0, 0.4, 0.4.0, 0.4.0.1)
                                                  //| sigmaAMRParents = ArrayBuffer(0.4.0)
                                                  //| betaAMR = Some(0)
                                                  //| Action chosen: Swap
                                                  //| Considering current node: 22 with child Nil
                                                  //| SIGMAAMR = Some(0.4.0.1)
                                                  //| Set(0, 0.4, 0.4.0, 0.4.0.1)
                                                  //| sigmaAMRParents = ArrayBuffer(0.4.0)
                                                  //| betaAMR = None
                                                  //| Action chosen: NextNode: 7 -> States
                                                  //| Considering current node: 27 with child 22
  }
  file.close
  val outputAdv = RunDagger.sampleTrajectory(s1, "C:\\AMR\\WangXueExpert_output.txt", new WangXueExpert)
                                                  //> Considering current node: 21 with child Nil
                                                  //| SIGMAAMR = Some(0.4.0.0)
                                                  //| Set(0, 0.4, 0.4.0, 0.4.0.0)
                                                  //| sigmaAMRParents = ArrayBuffer(0.4.0)
                                                  //| betaAMR = None
                                                  //| Action chosen: InsertNode: name (Ref: 0.4.0)
                                                  //| Considering current node: 21 with child Nil
                                                  //| SIGMAAMR = Some(0.4.0.0)
                                                  //| Set(0, 0.4, 0.4.0, 0.4.0.0)
                                                  //| sigmaAMRParents = ArrayBuffer(0.4.0)
                                                  //| betaAMR = None
                                                  //| Action chosen: NextNode: 6 -> United
                                                  //| Considering current node: 24 with child Nil
                                                  //| SIGMAAMR = None
                                                  //| Set()
                                                  //| sigmaAMRParents = List()
                                                  //| betaAMR = None
                                                  //| Action chosen: DeleteNode
                                                  //| Considering current node: 25 with child 21
                                                  //| SIGMAAMR = Some(0.4.0)
                                                  //| Set(0, 0.4, 0.4.0)
                                                  //| sigmaAMRParents = ArrayBuffer(0.4)
                                                  //| betaAMR = Some(0.4.0.0)
                                                  //| Action chosen: InsertNode: country (Ref: 0.4)
                                                  //| Considering current node: 25 with child 21
                                                  //| SIGMAAMR = Some(0.4.0)
                                                  //| Set(0, 0.4, 0.4.0)
                                                  //| sigmaAMRParents = ArrayBuffer(0.4)
                                                  //| betaAMR = Some(0.4.0.0)
                                                  //| Action chosen: NextEdge: 2 -> opN
                                                  //| Considering current node: 26 with child 25
                                                  //| SIGMAAMR = Some(0.4)
                                                  //| Set(0, 0.4)
                                                  //| sigmaAMRParents = ArrayBuffer(0)
                                                  //| betaAMR = Some(0.4.0)
                                                  //| Action chosen: InsertNode: and (Ref: 0)
                                                  //| Considering current node: 26 with child 25
                                                  //| SIGMAAMR = Some(0.4)
                                                  //| Set(0, 0.4)
                                                  //| sigmaAMRParents = ArrayBuffer(0)
                                                  //| betaAMR = Some(0.4.0)
                                                  //| Action chosen: NextEdge: 1 -> name
                                                  //| Considering current node: 27 with child 26
                                                  //| SIGMAAMR = Some(0)
                                                  //| Set(0)
                                                  //| sigmaAMRParents = ArrayBuffer()
                                                  //| betaAMR = Some(0.4)
                                                  //| Action chosen: NextEdge: 2 -> opN
                                                  //| Considering current node: 13 with child Nil
                                                  //| SIGMAAMR = None
                                                  //| Set()
                                                  //| sigmaAMRParents = List()
                                                  //| betaAMR = None
                                                  //| Action chosen: DeleteNode
                                                  //| Considering current node: 18 with child Nil
                                                  //| SIGMAAMR = None
                                                  //| Set()
                                                  //| sigmaAMRParents = List()
                                                  //| betaAMR = None
                                                  //| Action chosen: DeleteNode
                                                  //| Considering current node: 22 with child 27
                                                  //| SIGMAAMR = Some(0.4.0.1)
                                                  //| Set(0, 0.4, 0.4.0, 0.4.0.1)
                                                  //| sigmaAMRParents = ArrayBuffer(0.4.0)
                                                  //| betaAMR = Some(0)
                                                  //| Action chosen: Swap
                                                  //| Considering current node: 22 with child Nil
                                                  //| SIGMAAMR = Some(0.4.0.1)
                                                  //| Set(0, 0.4, 0.4.0, 0.4.0.1)
                                                  //| sigmaAMRParents = ArrayBuffer(0.4.0)
                                                  //| betaAMR = None
                                                  //| Action chosen: NextNode: 7 -> States
                                                  //| Considering current node: 27 with child 22
                                                  //| SIGMAAMR = Some(0)
     
}