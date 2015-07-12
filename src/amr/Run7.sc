package amr
object Run7 {

  val testData = AMRGraph.importFile("C:\\AMR\\Large_7.txt")
                                                  //> testData  : IndexedSeq[(String, String)] = Vector((Naif Arab Academy for Secu
                                                  //| rity Sciences is run by an Arab Interior Ministers' Council.,(r / run-01 :ARG
                                                  //| 0 (o / organization :name (n / name :op1 "Arab" :op2 "Interior" :op3 "Ministe
                                                  //| rs'" :op4 "Council")) :ARG1 (u / university :name (n2 / name :op1 "Naif" :op2
                                                  //|  "Arab" :op3 "Academy" :op4 "for" :op5 "Security" :op6 "Sciences")))))

  val s0 = Sentence(testData(0)._1, testData(0)._2)
                                                  //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.1 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [4.6 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [2.2 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [3.8 sec].
                                                  //| WARNING: Found duplicate match for concept "Arab"
                                                  //| Some("Naif")
                                                  //| Some("Arab")
                                                  //| Some("Academy")
                                                  //| Some("for")
                                                  //| Some("Security")
                                                  //| Some("Sciences")
                                                  //| None
                                                  //| Some((run-01 :ARG0 (organization :name (name :op1 "Arab" :op2 "Interior" :op
                                                  //| 3 "Ministers'" :op4 "Council")) :ARG1 (university :name (name :op1 "Naif" :o
                                                  //| p2 "Arab" :op3 "Academy" :op4 "for" :op5 "Security" :op6 "Sciences"))))
                                                  //| None
                                                  //| None
                                                  //| Some("Arab")
                                                  //| Some("Interior")
                                                  //| Some("Ministers'")
                                                  //| None
                                                  //| Some("Council")
                                                  //| Some(2)
                                                  //| Some(1)
                                                  //| Some(2)
                                                  //| Some(2)
                                                  //| Some(2)
                                                  //| Some(2)
                                                  //| None
                                                  //| Some(0)
                                                  //| None
                                                  //| None
                                                  //| Some(2)
                                                  //| Some(1)
                                                  //| Some(1)
                                                  //| None
                                                  //| Some(1)
                                                  //| s0  : amr.Sentence = Sentence(Naif Arab Academy for Security Sciences is run
                                                  //|  by an Arab Interior Ministers' Council.,
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> Naif, 2 -> Arab, 3 -> Academy, 4 -> for, 5 -
                                                  //| > Security, 6 -> Sciences, 7 -> is, 8 -> run, 9 -> by, 10 -> an, 11 -> Arab,
                                                  //|  12 -> Interior, 13 -> Ministers, 14 -> ', 15 -> Council)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,12
                                                  //| ), 12 -> (12,13), 13 -> (13,14), 14 -> (14,15), 15 -> (15,16))
                                                  //| Edges:	Map((0,8) -> root, (3,1) -> nn, (3,2) -> nn, (3,4) -> prep, (4,6
                                                  //| ) -> pobj, (6,5) -> nn, (8,3) -> nsubjpass, (8,7) -> auxpass, (8,9) -> prep,
                                                  //|  (9,15) -> pobj, (13,10) -> det, (13,11) -> nn, (13,12) -> nn, (13,14) -> po
                                                  //| ssessive, (15,13) -> poss)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set(),Some(AMRGraph(Map(0.1 -> university, 0.0.0 -> name
                                                  //| , 0.1.0 -> name, 0.1.0.5 -> "Sciences", 0.1.0.1 -> "Arab", 0.0.0.3 -> "Counc
                                                  //| il", 0.0 -> organization, 0.0.0.0 -> "Arab", 0.1.0.2 -> "Academy", 0.1.0.0 -
                                                  //| > "Naif", 0 -> run-01, 0.0
                                                  //| Output exceeds cutoff limit.

  s0.amr.get                                      //> res0: amr.AMRGraph = AMRGraph(Map(0.1 -> university, 0.0.0 -> name, 0.1.0 ->
                                                  //|  name, 0.1.0.5 -> "Sciences", 0.1.0.1 -> "Arab", 0.0.0.3 -> "Council", 0.0 -
                                                  //| > organization, 0.0.0.0 -> "Arab", 0.1.0.2 -> "Academy", 0.1.0.0 -> "Naif", 
                                                  //| 0 -> run-01, 0.0.0.1 -> "Interior", 0.1.0.3 -> "for", ROOT -> ROOT, 0.0.0.2 
                                                  //| -> "Ministers'", 0.1.0.4 -> "Security"),Map(0 -> (8,9), 0.0 -> (16,1), 0.1 -
                                                  //| > (16,1)),Map((0.0,0.0.0) -> name, (ROOT,0) -> ROOT, (0.0.0,0.0.0.0) -> opN,
                                                  //|  (0.1,0.1.0) -> name, (0.1.0,0.1.0.0) -> opN, (0,0.1) -> ARG1, (0.1.0,0.1.0.
                                                  //| 1) -> opN, (0.0.0,0.0.0.1) -> opN, (0.1.0,0.1.0.2) -> opN, (0.1.0,0.1.0.5) -
                                                  //| > opN, (0.0.0,0.0.0.2) -> opN, (0.0.0,0.0.0.3) -> opN, (0.1.0,0.1.0.4) -> op
                                                  //| N, (0.1.0,0.1.0.3) -> opN, (0,0.0) -> ARG0))
  s0.amr.get.nodeSpans                            //> res1: Map[String,(Int, Int)] = Map(0 -> (8,9), 0.0 -> (16,1), 0.1 -> (16,1))
                                                  //| 
  val expert = new WangXueExpert                  //> expert  : amr.WangXueExpert = amr.WangXueExpert@53045c6c
  val expertSystem = new WangXueTransitionSystem  //> expertSystem  : amr.WangXueTransitionSystem = amr.WangXueTransitionSystem@51
                                                  //| 49d738
  var state = expertSystem.init(s0)               //> state  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(5, 10, 14, 12, 11, 13, 6, 15, 4, 1, 2, 9, 3, 7, 8, 0)
                                                  //| Children:	List()
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> Naif, 2 -> Arab, 3 -> Academy, 4 -> for, 5 -
                                                  //| > Security, 6 -> Sciences, 7 -> is, 8 -> run, 9 -> by, 10 -> an, 11 -> Arab,
                                                  //|  12 -> Interior, 13 -> Ministers, 14 -> ', 15 -> Council)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,12
                                                  //| ), 12 -> (12,13), 13 -> (13,14), 14 -> (14,15), 15 -> (15,16))
                                                  //| Edges:	Map((0,8) -> root, (3,1) -> nn, (3,2) -> nn, (3,4) -> prep, (4,6
                                                  //| ) -> pobj, (6,5) -> nn, (8,3) -> nsubjpass, (8,7) -> auxpass, (8,9) -> prep,
                                                  //|  (9,15) -> pobj, (13,10) -> det, (13,11) -> nn, (13,12) -> nn, (13,14) -> po
                                                  //| ssessive, (15,13) -> poss)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| Mappings:	8 -> 0
                                                  //| 0 -> ROOT
                                                  //| 
  val permissibleActions = expertSystem.permissibleActions(state)
                                                  //> Some(2008)
                                                  //| Some(5)
                                                  //| Some(14)
                                                  //| Some(0)
                                                  //| Some(0)
                                                  //| Some(0)
                                                  //| Some("Estonia")
                                                  //| None
                                                  //| Some("Latvia")
                                                  //| None
                                                  //| Some("Lithuania")
                                                  //| None
                                                  //| Some(0)
                                                  //| None
                                                  //| Some(1)
                                                  //| None
                                                  //| Some(2)
                                                  //| None
                                                  //| Some(international)
                                                  //| Some((government-organization :ARG0-of govern-01))
                                                  //| Some(telecommunication)
                                                  //| Some(technology)
                                                  //| Some(science)
                                                  //| Some(0)
                                                  //| Some(1)
                                                  //| Some(2)
                                                  //| Some(3)
                                                  //| Some(4)
                                                  //| WARNING: Found duplicate match for concept attack-01
                                                  //| WARNING: Found duplicate match for concept cyber
                                                  //| Some("NATO")
                                                  //| Some((consider-02 :ARG0 (military :name (name :op1 "NATO")) :ARG1 (attack-01
                                                  //|  :mod cyber) :ARG2 (thing :ARG1-of (threaten-01 :ARG2 (and :op1 (network :mo
                                                  //| d military) :op2 (network :mod civilian) :mod computer))) :time (after :op1 
                                                  //| (strike-01 :ARG0 (attack :mod cyber) :ARG1 (government-organization :ARG0-of
                                                  //|  (govern-01 :ARG1 (country :name (name :op1 "Estonia")))) :time (date-entity
                                                  //|  :year 2007)))))
                                                  //| Some(cyber)
                                                  //| Some((attack-01 :mod cyber))
                                                  //| None
                                                  //| Some((threaten-01 :ARG2 (and :op1 (network :mod military) :op2 (network :mod
                                                  //|  civilian) :mod computer)))
                                                  //| None
                                                  //| Some((military :name (name :op1 "NATO")))
                                                  //| Some((and :op1 (network :mod military) :op2 (network :mod civilian) :mod com
                                                  //| puter))
                                                  //| Some(civilian)
                                                  //| Some(computer)
                                                  //| Some((network :mod military))
                                                  //| Some((after :op1 (strike-01 :ARG0 (attack :mod cyber) :ARG1 (government-orga
                                                  //| nization :ARG0-of (govern-01 :ARG1 (country :name (name :op1 "Estonia")))) :
                                                  //| time (date-entity :year 2007))))
                                                  //| None
                                                  //| Some("Estonia")
                                                  //| Some((government-organization :ARG0-of (govern-01 :ARG1 (country :name (name
                                                  //|  :op1 "Estonia")))))
                                                  //| None
                                                  //| Some((strike-01 :ARG0 (attack :mod cyber) :ARG1 (government-organization :AR
                                                  //| G0-of (govern-01 :ARG1 (country :name (name :op1 "Estonia")))) :time (date-e
                                                  //| ntity :year 2007)))
                                                  //| None
                                                  //| Some(cyber)
                                                  //| Some((attack :mod cyber))
                                                  //| None
                                                  //| Some(2007)
                                                  //| Some(2)
                                                  //| Some(0)
                                                  //| Some(4)
                                                  //| Some(3)
                                                  //| None
                                                  //| Some(5)
                                                  //| None
                                                  //| Some(1)
                                                  //| Some(6)
                                                  //| Some(8)
                                                  //| Some(9)
                                                  //| Some(7)
                                                  //| Some(10)
                                                  //| None
                                                  //| Some(15)
                                                  //| Some(14)
                                                  //| None
                                                  //| Some(11)
                                                  //| None
                                                  //| Some(13)
                                                  //| Some(12)
                                                  //| None
                                                  //| Some(16)
                                                  //| Some("NATO")
                                                  //| Some(ally-01)
                                                  //| None
                                                  //| Some((sign-01 :ARG0 (person :ARG0-of ally-01 :mod (military :name (name :op1
                                                  //|  "NATO"))) :ARG1 (agree-01 :ARG1 (fund-01 
                                                  //| Output exceeds cutoff limit.
  var expertNextAction = expert.chooseTransition(s0, state)
                                                  //> expertNextAction  : amr.WangXueAction = DeleteNode
  var expertNextState = expertNextAction(state)   //> expertNextState  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(10, 14, 12, 11, 13, 6, 15, 4, 1, 2, 9, 3, 7, 8, 0)
                                                  //| Children:	List()
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> Naif, 2 -> Arab, 3 -> Academy, 4 -> for, 6 -
                                                  //| > Sciences, 7 -> is, 8 -> run, 9 -> by, 10 -> an, 11 -> Arab, 12 -> Interior
                                                  //| , 13 -> Ministers, 14 -> ', 15 -> Council)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 6 -> (6,7), 
                                                  //| 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,12), 12 -> (12
                                                  //| ,13), 13 -> (13,14), 14 -> (14,15), 15 -> (15,16))
                                                  //| Edges:	Map((0,8) -> root, (3,1) -> nn, (3,2) -> nn, (3,4) -> prep, (4,6
                                                  //| ) -> pobj, (8,3) -> nsubjpass, (8,7) -> auxpass, (8,9) -> prep, (9,15) -> po
                                                  //| bj, (13,10) -> det, (13,11) -> nn, (13,12) -> nn, (13,14) -> possessive, (15
                                                  //| ,13) -> poss)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| Mappings:	8 -> 0
                                                  //| 0 -> ROOT
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
  val file = new FileWriter("C://AMR//i7_output.txt")
                                                  //> file  : java.io.FileWriter = java.io.FileWriter@7a1a3478
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
                                                  //> outputAdv  : amr.Sentence = Sentence(Naif Arab Academy for Security Science
                                                  //| s is run by an Arab Interior Ministers' Council.,
                                                  //| NodeMap:	Map(0 -> ROOT, 8 -> run)
                                                  //| SpanMap:	Map(8 -> (8,9))
                                                  //| Edges:	Map((0,8) -> ROOT)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set(),Some(AMRGraph(Map(0 -> ROOT, 8 -> run),Map(8 -> (8
                                                  //| ,9)),Map((0,8) -> ROOT))),Map(8 -> 8, 0 -> ROOT))
  outputAdv                                       //> res2: amr.Sentence = Sentence(Naif Arab Academy for Security Sciences is ru
                                                  //| n by an Arab Interior Ministers' Council.,
                                                  //| NodeMap:	Map(0 -> ROOT, 8 -> run)
                                                  //| SpanMap:	Map(8 -> (8,9))
                                                  //| Edges:	Map((0,8) -> ROOT)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set(),Some(AMRGraph(Map(0 -> ROOT, 8 -> run),Map(8 -> (8
                                                  //| ,9)),Map((0,8) -> ROOT))),Map(8 -> 8, 0 -> ROOT))

}