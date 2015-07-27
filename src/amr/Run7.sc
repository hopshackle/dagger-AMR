package amr
object Run7 {

  val testData = AMRGraph.importFile("C:\\AMR\\CountryList.txt")
                                                  //> testData  : IndexedSeq[(String, String)] = Vector((Myanmar (MM); Thailand (TH
                                                  //| ); Lao People's Democratic Republic (LA),(a / and :op1 (c3 / country :name (n
                                                  //| 7 / name :op1 "Myanmar")) :op2 (c4 / country :name (n8 / name :op1 "Thailand"
                                                  //| )) :op3 (c5 / country :name (n9 / name :op1 "Lao" :op2 "People's" :op3 "Democ
                                                  //| ratic" :op4 "Republic")))))

  val s0 = Sentence(testData(0)._1, testData(0)._2)
                                                  //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.1 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [4.2 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [2.1 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [3.9 sec].
                                                  //| s0  : amr.Sentence = Sentence(Myanmar (MM); Thailand (TH); Lao People's Demo
                                                  //| cratic Republic (LA),
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> Myanmar, 3 -> MM, 6 -> Thailand, 8 -> TH, 11
                                                  //|  -> Lao, 12 -> People, 13 -> 's, 14 -> Democratic, 15 -> Republic, 17 -> LA)
                                                  //| 
                                                  //| SpanMap:	Map(1 -> (1,2), 3 -> (2,3), 6 -> (3,4), 8 -> (4,5), 11 -> (5,6),
                                                  //|  12 -> (6,7), 13 -> (7,8), 14 -> (8,9), 15 -> (9,10), 17 -> (10,11))
                                                  //| Edges:	Map((0,3) -> root, (3,1) -> nn, (3,6) -> dep, (6,8) -> appos, (6
                                                  //| ,15) -> dep, (12,11) -> nn, (12,13) -> possessive, (15,12) -> poss, (15,14) 
                                                  //| -> amod, (15,17) -> appos)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	List(),Some(AMRGraph(Map(0.1 -> country, 0.0.0 -> name, 
                                                  //| 0.1.0 -> name, 0.0 -> country, 0.0.0.0 -> "Myanmar", 0.2.0.1 -> "People's", 
                                                  //| 0.2.0 -> name, 0.1.0.0 -> "Thailand", 0 -> and, 0.2.0.3 -> "Republic", 0.2 -
                                                  //| > country, 0.2.0.2 -> "Democratic", ROOT -> ROOT, 0.2.0.0 -> "Lao"),Map(0.1 
                                                  //| -> (3,4), 0.0.0 -> (1,2), 0.1.0 -> (3,4), 0.0 -> (1,2), 0.0.0.0 -> (1,2), 0.
                                                  //| 2.0.1 -> (5,10), 0.2.0 -> (5,10), 0.1.0.0 -> (3,4), 0.2.0.3 -> (5,10), 0.2 -
                                                  //| > (5,10), 0.2.0.2 -> (5,10), 0.2.0.0 -> (5,10)),Map((0.0,0.0.0) -> name, (0.
                                                  //| 2.0,0.2.0.3) -> opN, (ROOT,0) -> ROOT, (0.0.0,0.0.0.0) -> opN, (0.1,0.1.0) -
                                                  //| > name, (0.2.0,0.2.0.2) -> opN, (0.1.0,0.1.0.0) -> opN, (0,0.2) -> opN, (0,0
                                                  //| .1) -> opN, (0.2,0.2.0) -> name, (0,0.0) -> opN, (0.2.0,0.2.0.0) -> opN, (0.
                                                  //| 2.0,0.2.0.1) -> opN))),Map(0 -> ROOT, 14 -> 0.2.0.2, 1 -> 0.0.0.0, 6 -> 0.1.
                                                  //| 0.0, 13 -> 0.2.0, 12 -> 0.2.0.1, 11 -> 0.2.0.0, 15 -> 0.2.0.3))

  s0.amr.get                                      //> res0: amr.AMRGraph = AMRGraph(Map(0.1 -> country, 0.0.0 -> name, 0.1.0 -> na
                                                  //| me, 0.0 -> country, 0.0.0.0 -> "Myanmar", 0.2.0.1 -> "People's", 0.2.0 -> na
                                                  //| me, 0.1.0.0 -> "Thailand", 0 -> and, 0.2.0.3 -> "Republic", 0.2 -> country, 
                                                  //| 0.2.0.2 -> "Democratic", ROOT -> ROOT, 0.2.0.0 -> "Lao"),Map(0.1 -> (3,4), 0
                                                  //| .0.0 -> (1,2), 0.1.0 -> (3,4), 0.0 -> (1,2), 0.0.0.0 -> (1,2), 0.2.0.1 -> (5
                                                  //| ,10), 0.2.0 -> (5,10), 0.1.0.0 -> (3,4), 0.2.0.3 -> (5,10), 0.2 -> (5,10), 0
                                                  //| .2.0.2 -> (5,10), 0.2.0.0 -> (5,10)),Map((0.0,0.0.0) -> name, (0.2.0,0.2.0.3
                                                  //| ) -> opN, (ROOT,0) -> ROOT, (0.0.0,0.0.0.0) -> opN, (0.1,0.1.0) -> name, (0.
                                                  //| 2.0,0.2.0.2) -> opN, (0.1.0,0.1.0.0) -> opN, (0,0.2) -> opN, (0,0.1) -> opN,
                                                  //|  (0.2,0.2.0) -> name, (0,0.0) -> opN, (0.2.0,0.2.0.0) -> opN, (0.2.0,0.2.0.1
                                                  //| ) -> opN))
  s0.amr.get.nodeSpans                            //> res1: Map[String,(Int, Int)] = Map(0.1 -> (3,4), 0.0.0 -> (1,2), 0.1.0 -> (3
                                                  //| ,4), 0.0 -> (1,2), 0.0.0.0 -> (1,2), 0.2.0.1 -> (5,10), 0.2.0 -> (5,10), 0.1
                                                  //| .0.0 -> (3,4), 0.2.0.3 -> (5,10), 0.2 -> (5,10), 0.2.0.2 -> (5,10), 0.2.0.0 
                                                  //| -> (5,10))
  s0.positionToAMR                                //> res2: Map[Int,String] = Map(0 -> ROOT, 14 -> 0.2.0.2, 1 -> 0.0.0.0, 6 -> 0.1
                                                  //| .0.0, 13 -> 0.2.0, 12 -> 0.2.0.1, 11 -> 0.2.0.0, 15 -> 0.2.0.3)
  val expert = new WangXueExpert                  //> expert  : amr.WangXueExpert = amr.WangXueExpert@74d1dc36
  val expertSystem = new WangXueTransitionSystem  //> expertSystem  : amr.WangXueTransitionSystem = amr.WangXueTransitionSystem@71
                                                  //| 61d8d1
  var state = expertSystem.init(s0)               //> state  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(11, 13, 14, 12, 17, 8, 15, 1, 6, 3, 0)
                                                  //| Children:	List()
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> Myanmar, 3 -> MM, 6 -> Thailand, 8 -> TH, 11
                                                  //|  -> Lao, 12 -> People, 13 -> 's, 14 -> Democratic, 15 -> Republic, 17 -> LA)
                                                  //| 
                                                  //| SpanMap:	Map(1 -> (1,2), 3 -> (2,3), 6 -> (3,4), 8 -> (4,5), 11 -> (5,6),
                                                  //|  12 -> (6,7), 13 -> (7,8), 14 -> (8,9), 15 -> (9,10), 17 -> (10,11))
                                                  //| Edges:	Map((0,3) -> root, (3,1) -> nn, (3,6) -> dep, (6,8) -> appos, (6
                                                  //| ,15) -> dep, (12,11) -> nn, (12,13) -> possessive, (15,12) -> poss, (15,14) 
                                                  //| -> amod, (15,17) -> appos)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	List()
                                                  //| Mappings:	0 -> ROOT
                                                  //| 14 -> 0.2.0.2
                                                  //| 1 -> 0.0.0.0
                                                  //| 6 -> 0.1.0.0
                                                  //| 13 -> 0.2.0
                                                  //| 12 -> 0.2.0.1
                                                  //| 11 -> 0.2.0.0
                                                  //| 15 -> 0.2.0.3
                                                  //| 
  val permissibleActions = expertSystem.permissibleActions(state)
                                                  //> WARNING: Found duplicate match for concept attack-01
                                                  //| WARNING: Found duplicate match for concept cyber
                                                  //| permissibleActions  : Array[amr.WangXueAction] = Array(NextNode: 0 -> UNKNOW
                                                  //| N, DeleteNode, ReversePolarity)
  var expertNextAction = expert.chooseTransition(s0, state)
                                                  //> expertNextAction  : amr.WangXueAction = NextNode: 0 -> UNKNOWN
  var expertNextState = expertNextAction(state)   //> expertNextState  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(13, 14, 12, 17, 8, 15, 1, 6, 3, 0)
                                                  //| Children:	List()
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> Myanmar, 3 -> MM, 6 -> Thailand, 8 -> TH, 11
                                                  //|  -> Lao, 12 -> People, 13 -> 's, 14 -> Democratic, 15 -> Republic, 17 -> LA)
                                                  //| 
                                                  //| SpanMap:	Map(1 -> (1,2), 3 -> (2,3), 6 -> (3,4), 8 -> (4,5), 11 -> (5,6),
                                                  //|  12 -> (6,7), 13 -> (7,8), 14 -> (8,9), 15 -> (9,10), 17 -> (10,11))
                                                  //| Edges:	Map((0,3) -> root, (3,1) -> nn, (3,6) -> dep, (6,8) -> appos, (6
                                                  //| ,15) -> dep, (12,11) -> nn, (12,13) -> possessive, (15,12) -> poss, (15,14) 
                                                  //| -> amod, (15,17) -> appos)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	List()
                                                  //| Mappings:	0 -> ROOT
                                                  //| 14 -> 0.2.0.2
                                                  //| 1 -> 0.0.0.0
                                                  //| 6 -> 0.1.0.0
                                                  //| 13 -> 0.2.0
                                                  //| 12 -> 0.2.0.1
                                                  //| 11 -> 0.2.0.0
                                                  //| 15 -> 0.2.0.3
                                                  //| NextNode: 0 -> UNKNOWN
                                                  //| 
  import java.io._
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
  val file = new FileWriter("C://AMR//Laos.txt")  //> file  : java.io.FileWriter = java.io.FileWriter@5be49b60
  for (i <- 0 to 300) {
    if (!expertSystem.isTerminal(state)) {
      stateHistory(i) = state
      action(i) = expert.chooseTransition(s0, state)
      file.write(state.toString + "\n")
      file.write(action(i) + " : " + (state.nodesToProcess match { case Nil => ""; case _ => state.nodesToProcess.head }) + " -> " + (
        state.childrenToProcess match { case Nil => ""; case _ => state.childrenToProcess.head }) + "\n")

      file.flush
      state = action(i)(state)
    }
  }
  file.close
  println("Here")                                 //> Here
  val outputAdv = RunDagger.sampleTrajectory(s0, "C:\\AMR\\WangXueExpert_output.txt", new WangXueExpert)
                                                  //> outputAdv  : amr.Sentence = Sentence(Myanmar (MM); Thailand (TH); Lao Peopl
                                                  //| e's Democratic Republic (LA),
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> Myanmar, 6 -> Thailand, 11 -> Lao, 12 -> Peo
                                                  //| ple, 13 -> name, 14 -> Democratic, 15 -> Republic, 18 -> country, 19 -> and
                                                  //| , 20 -> name, 21 -> name, 22 -> country, 23 -> country)
                                                  //| SpanMap:	Map(1 -> (1,2), 6 -> (3,4), 11 -> (5,6), 12 -> (6,7), 13 -> (7,8
                                                  //| ), 14 -> (8,9), 15 -> (9,10), 18 -> (7,8), 19 -> (7,8), 20 -> (1,2), 21 -> 
                                                  //| (3,4), 22 -> (2,3), 23 -> (1,2))
                                                  //| Edges:	Map((0,19) -> ROOT, (6,15) -> dep, (13,11) -> opN, (15,12) -> po
                                                  //| ss, (15,14) -> amod, (18,13) -> name, (19,18) -> opN, (19,22) -> opN, (19,2
                                                  //| 3) -> opN, (20,1) -> opN, (21,6) -> opN, (22,21) -> name, (23,20) -> name)
                                                  //| InsertedNodes:	Map(20 -> 0.0.0, 21 -> 0.1.0, 22 -> 0.1, 18 -> 0.2, 23 -
                                                  //| > 0.0, 19 -> 0)
                                                  //| MergedNodes:	Map(22 -> List((3,MM)))
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	List(8, 17),Some(AMRGraph(Map(12 -> People, 19 -> and, 2
                                                  //| 3 -> country, 15 -> Republic, 11 -> "Lao", 22 -> country, 13 -> name, 21 ->
                                                  //|  name, 6 -> "Thailand", 1 -> "Myanmar", 14 -> Democratic, 0 -> ROOT, 20 -> 
                                                  //| name, 18 -> country),Map(12 -> (6,7), 19 -> (7,8), 23 -> (1,2), 15 -> (9,10
                                                  //| ), 11 -> (5,6), 22 -> (2,3), 13 -> (7,8), 21 -> (3,4), 6 -> (3,4), 1 -> (1,
                                                  //| 2), 14 -> (8,9), 20 -> (1,2), 18 -> (7,8)),Map((20,1) -> opN, (19,22) -> op
                                                  //| N, (15,12) -> poss, (21,6) -> opN, (13,11) -> opN, (23,20) -> name, (0,19) 
                                                  //| -> ROOT, (19,18) -> opN, (18,13) -> name, (15,14) -> amod, (22,21) -> name,
                                                  //|  (6,15) -> dep, (19,23) -> opN))),Map(0 -> ROOT, 14 -> 14, 20 -> 20, 1 -> 1
                                                  //| , 6 -> 6, 21 -> 21, 13 -> 13, 22 -> 22, 12 -> 12, 18 -> 18, 11 -> 11, 23 ->
                                                  //|  23, 19 -> 19, 15 -> 15))
  outputAdv                                       //> res3: amr.Sentence = Sentence(Myanmar (MM); Thailand (TH); Lao People's Dem
                                                  //| ocratic Republic (LA),
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> Myanmar, 6 -> Thailand, 11 -> Lao, 12 -> Peo
                                                  //| ple, 13 -> name, 14 -> Democratic, 15 -> Republic, 18 -> country, 19 -> and
                                                  //| , 20 -> name, 21 -> name, 22 -> country, 23 -> country)
                                                  //| SpanMap:	Map(1 -> (1,2), 6 -> (3,4), 11 -> (5,6), 12 -> (6,7), 13 -> (7,8
                                                  //| ), 14 -> (8,9), 15 -> (9,10), 18 -> (7,8), 19 -> (7,8), 20 -> (1,2), 21 -> 
                                                  //| (3,4), 22 -> (2,3), 23 -> (1,2))
                                                  //| Edges:	Map((0,19) -> ROOT, (6,15) -> dep, (13,11) -> opN, (15,12) -> po
                                                  //| ss, (15,14) -> amod, (18,13) -> name, (19,18) -> opN, (19,22) -> opN, (19,2
                                                  //| 3) -> opN, (20,1) -> opN, (21,6) -> opN, (22,21) -> name, (23,20) -> name)
                                                  //| InsertedNodes:	Map(20 -> 0.0.0, 21 -> 0.1.0, 22 -> 0.1, 18 -> 0.2, 23 -
                                                  //| > 0.0, 19 -> 0)
                                                  //| MergedNodes:	Map(22 -> List((3,MM)))
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	List(8, 17),Some(AMRGraph(Map(12 -> People, 19 -> and, 2
                                                  //| 3 -> country, 15 -> Republic, 11 -> "Lao", 22 -> country, 13 -> name, 21 ->
                                                  //|  name, 6 -> "Thailand", 1 -> "Myanmar", 14 -> Democratic, 0 -> ROOT, 20 -> 
                                                  //| name, 18 -> country),Map(12 -> (6,7), 19 -> (7,8), 23 -> (1,2), 15 -> (9,10
                                                  //| ), 11 -> (5,6), 22 -> (2,3), 13 -> (7,8), 21 -> (3,4), 6 -> (3,4), 1 -> (1,
                                                  //| 2), 14 -> (8,9), 20 -> (1,2), 18 -> (7,8)),Map((20,1) -> opN, (19,22) -> op
                                                  //| N, (15,12) -> poss, (21,6) -> opN, (13,11) -> opN, (23,20) -> name, (0,19) 
                                                  //| -> ROOT, (19,18) -> opN, (18,13) -> name, (15,14) -> amod, (22,21) -> name,
                                                  //|  (6,15) -> dep, (19,23) -> opN))),Map(0 -> ROOT, 14 -> 14, 20 -> 20, 1 -> 1
                                                  //| , 6 -> 6, 21 -> 21, 13 -> 13, 22 -> 22, 12 -> 12, 18 -> 18, 11 -> 11, 23 ->
                                                  //|  23, 19 -> 19, 15 -> 15))

}