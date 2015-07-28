package amr
object Run7 {

  val testData = AMRGraph.importFile("C:\\AMR\\allies.txt")
                                                  //> testData  : IndexedSeq[(String, String)] = Vector((NATO allies have signed an
                                                  //|  agreement to fund a research center to bolster defenses against cyber attack
                                                  //| s.,(s / sign-01 :ARG0 (p / person :ARG0-of (a / ally-01) :mod (m / military :
                                                  //| name (n / name :op1 "NATO"))) :ARG1 (a2 / agree-01 :ARG0 p :ARG1 (f / fund-01
                                                  //|  :ARG0 p :ARG1 (c / center :mod (r / research-01)) :purpose (b / bolster-01 :
                                                  //| ARG0 p :ARG1 (d / defend-01 :prep-against (a3 / attack :mod (c2 / cyber))))))
                                                  //| )))

  val s0 = Sentence(testData(0)._1, testData(0)._2)
                                                  //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.7 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [5.0 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [3.4 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [2.6 sec].
                                                  //| s0  : amr.Sentence = Sentence(NATO allies have signed an agreement to fund a
                                                  //|  research center to bolster defenses against cyber attacks.,
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> NATO, 2 -> allies, 3 -> have, 4 -> signed, 5
                                                  //|  -> an, 6 -> agreement, 7 -> to, 8 -> fund, 9 -> a, 10 -> research, 11 -> ce
                                                  //| nter, 12 -> to, 13 -> bolster, 14 -> defenses, 15 -> against, 16 -> cyber, 1
                                                  //| 7 -> attacks)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,12
                                                  //| ), 12 -> (12,13), 13 -> (13,14), 14 -> (14,15), 15 -> (15,16), 16 -> (16,17)
                                                  //| , 17 -> (17,18))
                                                  //| Edges:	Map((0,4) -> root, (2,1) -> nn, (4,2) -> nsubj, (4,3) -> aux, (4
                                                  //| ,6) -> dobj, (6,5) -> det, (6,8) -> vmod, (8,7) -> aux, (8,11) -> dobj, (8,1
                                                  //| 3) -> xcomp, (11,9) -> det, (11,10) -> nn, (13,12) -> aux, (13,14) -> dobj, 
                                                  //| (13,15) -> prep, (15,17) -> pobj, (17,16) -> nn)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	List(),Some(AMRGraph(Map(0.1 -> agree-01, 0.0.0 -> ally-
                                                  //| 01, 0.1.0.1.0.0 -> attack, 0.1.0 -> fund-01, 0.0.1.0 -> name, 0.1.0.0.0 -> r
                                                  //| esearch-01, 0.1.0.1 -> bolster-01, 0.0 -> person, 0.0.1 -> military, 0.1.0.0
                                                  //|  -> center, 0.0.1.0.0 -> "NATO", 0.1.0.1.0.0.0 -> cyber, 0 -> sign-01, 0.1.0
                                                  //| .1.0 -> defend-01, ROOT -> ROOT),Map(0.1 -> (6,7), 0.0.0 -> (2,3), 0.1.0.1.0
                                                  //| .0 -> (17,18), 0.1.0 -> (8,9), 0.0.1.0 -> (1,2), 0.1.0.0.0 -> (10,11), 0.1.0
                                                  //| .1 -> (13,14), 0.0 -> (2,3), 0.1.0.0 -> (11,12), 0.0.1.0.0 -> (1,2), 0.1.0.1
                                                  //| .0.0.0 -> (16,17), 0 -> (4,5), 0.1.0.1.0 -> (14,15)),Map((0.0,0.0.0) -> ARG0
                                                  //| -of, (ROOT,0) -> ROOT, (0.1.0.1.0.0,0.1.0.1.0.0.0) -> mod, (0.1,0.1.0) -> AR
                                                  //| G1, (0.1.0,0.1.0.0) -> ARG1, (0,0.1) -> ARG1, (0.1.0.1.0,0.1.0.1.0.0) -> pre
                                                  //| p-against, (0.1.0.1,0.0) -> ARG0, (0.1.0,0
                                                  //| Output exceeds cutoff limit.

  s0.amr.get                                      //> res0: amr.AMRGraph = AMRGraph(Map(0.1 -> agree-01, 0.0.0 -> ally-01, 0.1.0.1
                                                  //| .0.0 -> attack, 0.1.0 -> fund-01, 0.0.1.0 -> name, 0.1.0.0.0 -> research-01,
                                                  //|  0.1.0.1 -> bolster-01, 0.0 -> person, 0.0.1 -> military, 0.1.0.0 -> center,
                                                  //|  0.0.1.0.0 -> "NATO", 0.1.0.1.0.0.0 -> cyber, 0 -> sign-01, 0.1.0.1.0 -> def
                                                  //| end-01, ROOT -> ROOT),Map(0.1 -> (6,7), 0.0.0 -> (2,3), 0.1.0.1.0.0 -> (17,1
                                                  //| 8), 0.1.0 -> (8,9), 0.0.1.0 -> (1,2), 0.1.0.0.0 -> (10,11), 0.1.0.1 -> (13,1
                                                  //| 4), 0.0 -> (2,3), 0.1.0.0 -> (11,12), 0.0.1.0.0 -> (1,2), 0.1.0.1.0.0.0 -> (
                                                  //| 16,17), 0 -> (4,5), 0.1.0.1.0 -> (14,15)),Map((0.0,0.0.0) -> ARG0-of, (ROOT,
                                                  //| 0) -> ROOT, (0.1.0.1.0.0,0.1.0.1.0.0.0) -> mod, (0.1,0.1.0) -> ARG1, (0.1.0,
                                                  //| 0.1.0.0) -> ARG1, (0,0.1) -> ARG1, (0.1.0.1.0,0.1.0.1.0.0) -> prep-against, 
                                                  //| (0.1.0.1,0.0) -> ARG0, (0.1.0,0.1.0.1) -> purpose, (0.1.0.0,0.1.0.0.0) -> mo
                                                  //| d, (0.1,0.0) -> ARG0, (0,0.0) -> ARG0, (0.1.0.1,0.1.0.1.0) -> ARG1, (0.0.1,0
                                                  //| .0.1.0) -> name, (0.1.0,0.0) -> ARG0, (0.0.1.0,0.0.1.0.0) -> opN, (0.0,0.0.1
                                                  //| ) -> mod))
  s0.amr.get.nodeSpans                            //> res1: Map[String,(Int, Int)] = Map(0.1 -> (6,7), 0.0.0 -> (2,3), 0.1.0.1.0.0
                                                  //|  -> (17,18), 0.1.0 -> (8,9), 0.0.1.0 -> (1,2), 0.1.0.0.0 -> (10,11), 0.1.0.1
                                                  //|  -> (13,14), 0.0 -> (2,3), 0.1.0.0 -> (11,12), 0.0.1.0.0 -> (1,2), 0.1.0.1.0
                                                  //| .0.0 -> (16,17), 0 -> (4,5), 0.1.0.1.0 -> (14,15))
  s0.positionToAMR                                //> res2: Map[Int,String] = Map(0 -> ROOT, 10 -> 0.1.0.0.0, 14 -> 0.1.0.1.0, 1 -
                                                  //| > 0.0.1.0.0, 6 -> 0.1, 13 -> 0.1.0.1, 2 -> 0.0.0, 17 -> 0.1.0.1.0.0, 16 -> 0
                                                  //| .1.0.1.0.0.0, 11 -> 0.1.0.0, 8 -> 0.1.0, 4 -> 0)
  val expert = new WangXueExpert                  //> expert  : amr.WangXueExpert = amr.WangXueExpert@5a59ca5e
  val expertSystem = new WangXueTransitionSystem  //> expertSystem  : amr.WangXueTransitionSystem = amr.WangXueTransitionSystem@4b
                                                  //| deaabb
  var state = expertSystem.init(s0)               //> state  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(16, 17, 10, 9, 12, 15, 14, 11, 13, 7, 5, 8, 1, 6, 2, 3, 4, 
                                                  //| 0)
                                                  //| Children:	List()
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> NATO, 2 -> allies, 3 -> have, 4 -> signed, 5
                                                  //|  -> an, 6 -> agreement, 7 -> to, 8 -> fund, 9 -> a, 10 -> research, 11 -> ce
                                                  //| nter, 12 -> to, 13 -> bolster, 14 -> defenses, 15 -> against, 16 -> cyber, 1
                                                  //| 7 -> attacks)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,12
                                                  //| ), 12 -> (12,13), 13 -> (13,14), 14 -> (14,15), 15 -> (15,16), 16 -> (16,17)
                                                  //| , 17 -> (17,18))
                                                  //| Edges:	Map((0,4) -> root, (2,1) -> nn, (4,2) -> nsubj, (4,3) -> aux, (4
                                                  //| ,6) -> dobj, (6,5) -> det, (6,8) -> vmod, (8,7) -> aux, (8,11) -> dobj, (8,1
                                                  //| 3) -> xcomp, (11,9) -> det, (11,10) -> nn, (13,12) -> aux, (13,14) -> dobj, 
                                                  //| (13,15) -> prep, (15,17) -> pobj, (17,16) -> nn)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	List()
                                                  //| Mappings:	0 -> ROOT
                                                  //| 10 -> 0.1.0.0.0
                                                  //| 14 -> 0.1.0.1.0
                                                  //| 1 -> 0.0.1.0.0
                                                  //| 6 -> 0.1
                                                  //| 13 -> 0.1.0.1
                                                  //| 2 -> 0.0.0
                                                  //| 17 -> 0.1.0.1.0.0
                                                  //| 16 -> 0.1.0.1.0.0.0
                                                  //| 11 -> 0.1.0.0
                                                  //| 8 -> 0.1.0
                                                  //| 4 -> 0
                                                  //| 
  val permissibleActions = expertSystem.permissibleActions(state)
                                                  //> WARNING: Found duplicate match for concept attack-01
                                                  //| WARNING: Found duplicate match for concept cyber
                                                  //| permissibleActions  : Array[amr.WangXueAction] = Array(NextNode: 23 -> cyber
                                                  //| , NextNode: 0 -> UNKNOWN, InsertNode: name (Ref: ), DeleteNode, ReversePolar
                                                  //| ity)
  var expertNextAction = expert.chooseTransition(s0, state)
                                                  //> expertNextAction  : amr.WangXueAction = NextNode: 23 -> cyber
  var expertNextState = expertNextAction(state)   //> expertNextState  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(17, 10, 9, 12, 15, 14, 11, 13, 7, 5, 8, 1, 6, 2, 3, 4, 0)
                                                  //| Children:	List(16)
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> NATO, 2 -> allies, 3 -> have, 4 -> signed, 5
                                                  //|  -> an, 6 -> agreement, 7 -> to, 8 -> fund, 9 -> a, 10 -> research, 11 -> ce
                                                  //| nter, 12 -> to, 13 -> bolster, 14 -> defenses, 15 -> against, 16 -> cyber, 1
                                                  //| 7 -> attacks)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,12
                                                  //| ), 12 -> (12,13), 13 -> (13,14), 14 -> (14,15), 15 -> (15,16), 16 -> (16,17)
                                                  //| , 17 -> (17,18))
                                                  //| Edges:	Map((0,4) -> root, (2,1) -> nn, (4,2) -> nsubj, (4,3) -> aux, (4
                                                  //| ,6) -> dobj, (6,5) -> det, (6,8) -> vmod, (8,7) -> aux, (8,11) -> dobj, (8,1
                                                  //| 3) -> xcomp, (11,9) -> det, (11,10) -> nn, (13,12) -> aux, (13,14) -> dobj, 
                                                  //| (13,15) -> prep, (15,17) -> pobj, (17,16) -> nn)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	List()
                                                  //| Mappings:	0 -> ROOT
                                                  //| 10 -> 0.1.0.0.0
                                                  //| 14 -> 0.1.0.1.0
                                                  //| 1 -> 0.0.1.0.0
                                                  //| 6 -> 0.1
                                                  //| 13 -> 0.1.0.1
                                                  //| 2 -> 0.0.0
                                                  //| 17 -> 0.1.0.1.0.0
                                                  //| 16 -> 0.1.0.1.0.0.0
                                                  //| 11 -> 0.1.0.0
                                                  //| 8 -> 0.1.0
                                                  //| 4 -> 0
                                                  //| NextNode: 23 -> cyber
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
  val file = new FileWriter("C://AMR//Laos.txt")  //> file  : java.io.FileWriter = java.io.FileWriter@3688eb5b
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
                                                  //> outputAdv  : amr.Sentence = Sentence(NATO allies have signed an agreement t
                                                  //| o fund a research center to bolster defenses against cyber attacks.,
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> NATO, 2 -> ally-01, 4 -> sign-01, 6 -> agree
                                                  //| -01, 8 -> fund-01, 10 -> research-01, 11 -> center, 13 -> bolster-01, 14 ->
                                                  //|  defend-01, 16 -> cyber, 17 -> attack, 18 -> name, 19 -> military, 20 -> pe
                                                  //| rson)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 4 -> (4,5), 6 -> (6,7), 8 -> (8,9), 
                                                  //| 10 -> (10,11), 11 -> (11,12), 13 -> (13,14), 14 -> (14,15), 16 -> (16,17), 
                                                  //| 17 -> (15,17), 18 -> (1,2), 19 -> (1,2), 20 -> (1,2))
                                                  //| Edges:	Map((0,4) -> ROOT, (4,6) -> ARG1, (4,20) -> ARG0, (6,8) -> ARG1,
                                                  //|  (6,20) -> ARG0, (8,11) -> ARG1, (8,13) -> purpose, (8,20) -> ARG0, (11,10)
                                                  //|  -> mod, (13,14) -> ARG1, (13,20) -> ARG0, (14,17) -> prep-against, (17,16)
                                                  //|  -> mod, (18,1) -> opN, (19,18) -> name, (20,2) -> ARG0-of, (20,19) -> mod)
                                                  //| 
                                                  //| InsertedNodes:	Map(18 -> 0.0.1.0, 19 -> 0.0.1, 20 -> 0.0)
                                                  //| MergedNodes:	Map(17 -> List((15,against)))
                                                  //| SwappedArcs:	Set((2,20))
                                                  //| DeletedNodes:	List(3, 5, 7, 12, 9),Some(AMRGraph(Map(8 -> fund-01, 19 
                                                  //| -> military, 4 -> sign-01, 11 -> center, 13 -> bolster-01, 16 -> cyber, 10 
                                                  //| -> research-01, 6 -> agree-01, 1 -> "NATO", 17 -> attack, 14 -> defend-01, 
                                                  //| 0 -> ROOT, 20 -> person, 2 -> ally-01, 18 -> name),Map(8 -> (8,9), 19 -> (1
                                                  //| ,2), 4 -> (4,5), 11 -> (11,12), 13 -> (13,14), 16 -> (16,17), 10 -> (10,11)
                                                  //| , 6 -> (6,7), 1 -> (1,2), 17 -> (15,17), 14 -> (14,15), 20 -> (1,2), 2 -> (
                                                  //| 2,3), 18 -> (1,2)),Map((11,10) -> mod, (20,2) -> ARG0-of, (18,1) -> opN, (1
                                                  //| 7,16) -> mod, (8,20) -> ARG0, (4,6) -> ARG1, (6,8) -> ARG1, (13,20) -> ARG0
                                                  //| , (19,18) -> name, (8,11) -> ARG1, (4,20) -> ARG0, (0,4) -> ROOT, (14,17) -
                                                  //| > prep-against, (6,20) -> ARG0, (20,19) -> mod, (13,14) -> ARG1, (8,13) -> 
                                                  //| purpose))),Map(0 -> ROOT, 10 -> 10, 14 -> 14, 20 -> 20, 1 -> 1, 6 -> 6, 13 
                                                  //| -> 13, 2 -> 2, 17 -> 17, 18 -> 18, 16 -> 16, 11 -> 11, 8 -> 8, 19 -> 19, 4 
                                                  //| -> 4))
  outputAdv                                       //> res3: amr.Sentence = Sentence(NATO allies have signed an agreement to fund 
                                                  //| a research center to bolster defenses against cyber attacks.,
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> NATO, 2 -> ally-01, 4 -> sign-01, 6 -> agree
                                                  //| -01, 8 -> fund-01, 10 -> research-01, 11 -> center, 13 -> bolster-01, 14 ->
                                                  //|  defend-01, 16 -> cyber, 17 -> attack, 18 -> name, 19 -> military, 20 -> pe
                                                  //| rson)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 4 -> (4,5), 6 -> (6,7), 8 -> (8,9), 
                                                  //| 10 -> (10,11), 11 -> (11,12), 13 -> (13,14), 14 -> (14,15), 16 -> (16,17), 
                                                  //| 17 -> (15,17), 18 -> (1,2), 19 -> (1,2), 20 -> (1,2))
                                                  //| Edges:	Map((0,4) -> ROOT, (4,6) -> ARG1, (4,20) -> ARG0, (6,8) -> ARG1,
                                                  //|  (6,20) -> ARG0, (8,11) -> ARG1, (8,13) -> purpose, (8,20) -> ARG0, (11,10)
                                                  //|  -> mod, (13,14) -> ARG1, (13,20) -> ARG0, (14,17) -> prep-against, (17,16)
                                                  //|  -> mod, (18,1) -> opN, (19,18) -> name, (20,2) -> ARG0-of, (20,19) -> mod)
                                                  //| 
                                                  //| InsertedNodes:	Map(18 -> 0.0.1.0, 19 -> 0.0.1, 20 -> 0.0)
                                                  //| MergedNodes:	Map(17 -> List((15,against)))
                                                  //| SwappedArcs:	Set((2,20))
                                                  //| DeletedNodes:	List(3, 5, 7, 12, 9),Some(AMRGraph(Map(8 -> fund-01, 19 
                                                  //| -> military, 4 -> sign-01, 11 -> center, 13 -> bolster-01, 16 -> cyber, 10 
                                                  //| -> research-01, 6 -> agree-01, 1 -> "NATO", 17 -> attack, 14 -> defend-01, 
                                                  //| 0 -> ROOT, 20 -> person, 2 -> ally-01, 18 -> name),Map(8 -> (8,9), 19 -> (1
                                                  //| ,2), 4 -> (4,5), 11 -> (11,12), 13 -> (13,14), 16 -> (16,17), 10 -> (10,11)
                                                  //| , 6 -> (6,7), 1 -> (1,2), 17 -> (15,17), 14 -> (14,15), 20 -> (1,2), 2 -> (
                                                  //| 2,3), 18 -> (1,2)),Map((11,10) -> mod, (20,2) -> ARG0-of, (18,1) -> opN, (1
                                                  //| 7,16) -> mod, (8,20) -> ARG0, (4,6) -> ARG1, (6,8) -> ARG1, (13,20) -> ARG0
                                                  //| , (19,18) -> name, (8,11) -> ARG1, (4,20) -> ARG0, (0,4) -> ROOT, (14,17) -
                                                  //| > prep-against, (6,20) -> ARG0, (20,19) -> mod, (13,14) -> ARG1, (8,13) -> 
                                                  //| purpose))),Map(0 -> ROOT, 10 -> 10, 14 -> 14, 20 -> 20, 1 -> 1, 6 -> 6, 13 
                                                  //| -> 13, 2 -> 2, 17 -> 17, 18 -> 18, 16 -> 16, 11 -> 11, 8 -> 8, 19 -> 19, 4 
                                                  //| -> 4))

}