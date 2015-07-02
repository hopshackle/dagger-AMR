package amr

object problematicAMR {
  val testData = AMRGraph.importFile("C:\\AMR\\problematicAMR.txt")
                                                  //> testData  : IndexedSeq[(String, String)] = Vector((On 1 July 1999 armed troo
                                                  //| ps and a group of government weapons experts aided customs officials and gai
                                                  //| ned forcible entry onto the kuwolsan.,(a / and :op1 (a2 / aid-01 :ARG0 (a3 /
                                                  //|  and :op1 (t / troop :ARG1-of (a4 / arm-01)) :op2 (g5 / group :consist-of (p
                                                  //|  / person :ARG0-of (h / have-org-role-91 :ARG1 (g2 / government-organization
                                                  //|  :ARG0-of (g3 / govern-01)) :ARG3 (e / expert-41 :ARG1 p :ARG2 (w / weapon))
                                                  //| )))) :ARG2 (p2 / person :ARG0-of (h2 / have-org-role-91 :ARG1 (c / customs) 
                                                  //| :ARG2 (o / official)))) :op2 (g / gain-02 :ARG0 a3 :ARG1 (e2 / enter-01 :ARG
                                                  //| 1 (s / ship :name (n / name :op1 "Kuwolsan"))) :instrument (f / force)) :tim
                                                  //| e (d / date-entity :year 1999 :month 7 :day 1))))
  val expert = new WangXueExpert                  //> expert  : amr.WangXueExpert = amr.WangXueExpert@7c16905e
  val expertSystem = new WangXueTransitionSystem  //> expertSystem  : amr.WangXueTransitionSystem = amr.WangXueTransitionSystem@2a
                                                  //| 2d45ba
  val s1 = Sentence(testData(0)._1, testData(0)._2)
                                                  //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.0 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [4.1 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [2.1 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [4.0 sec].
                                                  //| WARNING: Found duplicate match for concept and
                                                  //| s1  : amr.Sentence = Sentence(On 1 July 1999 armed troops and a group of gov
                                                  //| ernment weapons experts aided customs officials and gained forcible entry on
                                                  //| to the kuwolsan.,
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> On, 2 -> 1, 3 -> July, 4 -> 1999, 5 -> armed
                                                  //| , 6 -> troops, 7 -> and, 8 -> a, 9 -> group, 10 -> of, 11 -> government, 12 
                                                  //| -> weapons, 13 -> experts, 14 -> aided, 15 -> customs, 16 -> officials, 17 -
                                                  //| > and, 18 -> gained, 19 -> forcible, 20 -> entry, 21 -> onto, 22 -> the, 23 
                                                  //| -> kuwolsan)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,12
                                                  //| ), 12 -> (12,13), 13 -> (13,14), 14 -> (14,15), 15 -> (15,16), 16 -> (16,17)
                                                  //| , 17 -> (17,18), 18 -> (18,19), 19 -> (19,20), 20 -> (20,21), 21 -> (21,22),
                                                  //|  22 -> (22,23), 23 -> (23,24))
                                                  //| Edges:	Map((0,14) -> root, (1,2) -> pobj, (6,4) -> num, (6,5) -> amod, 
                                                  //| (6,7) -> cc, (6,9) -> conj, (9,8) -> det, (9,10) -> prep, (10,13) -> pobj, (
                                                  //| 13,11) -> nn, (13,12) -> nn, (14,1) -> prep, (14,3) -> tmod, (14,6) -> nsubj
                                                  //| , (14,16) -> dobj, (14,17) -> cc, (14,18) -> conj, (16,15) -> nn, (18,20) ->
                                                  //|  dobj, (18,21) -> prep, (20,19) -> amod, (21,23) -> pobj, (23,22) -> det)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set(),Some(AMRGraph(Map(0.1 -> gain-02, 0.0.0 -> and, 0.
                                                  //| 0.0.1.0.0.0.0 -> govern-01, 0.0.0.1.0.0 -> have-org-role-91, 0.0.0.1.0 -> pe
                                                  //| rson, 0.1.0 -> enter-01, 0.0.1.0 -> have-org-role-91, 0.2.1 -> 7, 0.1.0.0.0 
                                                  //| -> name, 0.0 -> aid-01, 0.0.0.1.0.0.1 -> expert-41, 0.0.0.0 -> troop, 0.1.0.
                                                  //| 0.0.0 -> "Kuwolsan", 0.2.0 -> 1999, 0.0.1 -> person, 0.1.0.0 -> ship, 0.0.1.
                                                  //| 0.0 -> customs, 0.0.0.1.0.0.0 -> government-organization, 0.0.0.1.0.0.1.0 ->
                                                  //|  weapon, 0 -> and, 
                                                  //| Output exceeds cutoff limit.
  var state = expertSystem.init(s1)               //> state  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(12, 11, 22, 13, 19, 23, 10, 8, 5, 20, 21, 9, 2, 7, 4, 15, 1
                                                  //| , 6, 17, 3, 18, 16, 14, 0)
                                                  //| Children:	List()
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> On, 2 -> 1, 3 -> July, 4 -> 1999, 5 -> armed
                                                  //| , 6 -> troops, 7 -> and, 8 -> a, 9 -> group, 10 -> of, 11 -> government, 12 
                                                  //| -> weapons, 13 -> experts, 14 -> aided, 15 -> customs, 16 -> officials, 17 -
                                                  //| > and, 18 -> gained, 19 -> forcible, 20 -> entry, 21 -> onto, 22 -> the, 23 
                                                  //| -> kuwolsan)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,12
                                                  //| ), 12 -> (12,13), 13 -> (13,14), 14 -> (14,15), 15 -> (15,16), 16 -> (16,17)
                                                  //| , 17 -> (17,18), 18 -> (18,19), 19 -> (19,20), 20 -> (20,21), 21 -> (21,22),
                                                  //|  22 -> (22,23), 23 -> (23,24))
                                                  //| Edges:	Map((0,14) -> root, (1,2) -> pobj, (6,4) -> num, (6,5) -> amod, 
                                                  //| (6,7) -> cc, (6,9) -> conj, (9,8) -> det, (9,10) -> prep, (10,13) -> pobj, (
                                                  //| 13,11) -> nn, (13,12) -> nn, (14,1) -> prep, (14,3) -> tmod, (14,6) -> nsubj
                                                  //| , (14,16) -> dobj, (14,17) -> cc, (14,18) -> conj, (16,15) -> nn, (18,20) ->
                                                  //|  dobj, (18,21) -> prep, (20,19) -> amod, (21,23) -> pobj, (23,22) -> det)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| Mappings:	0 -> ROOT
                                                  //| 5 -> 0.0.0.0.0
                                                  //| 14 -> 0.0
                                                  //| 6 -> 0.0.0.0
                                                  //| 9 -> 0.0.0.1
                                                  //| 13 -> 0.0.0.1.0.0.1
                                                  //| 2 -> 0.2.2
                                                  //| 17 -> 0.0.0
                                                  //| 12 -> 0.0.0.1.0.0.1.0
                                                  //| 7 -> 0
                                                  //| 3 -> 0.2.1
                                                  //| 18 -> 0.1
                                                  //| 16 -> 0.0.1.0.1
                                                  //| 11 -> 0.0.0.1.0.0.0
                                                  //| 19 -> 0.1.1
                                                  //| 4 -> 0.2.0
                                                  //| 15 -> 0.0.1.0.0
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
  s1.positionToAMR                                //> res0: Map[Int,String] = Map(0 -> ROOT, 5 -> 0.0.0.0.0, 14 -> 0.0, 6 -> 0.0.0
                                                  //| .0, 9 -> 0.0.0.1, 13 -> 0.0.0.1.0.0.1, 2 -> 0.2.2, 17 -> 0.0.0, 12 -> 0.0.0.
                                                  //| 1.0.0.1.0, 7 -> 0, 3 -> 0.2.1, 18 -> 0.1, 16 -> 0.0.1.0.1, 11 -> 0.0.0.1.0.0
                                                  //| .0, 19 -> 0.1.1, 4 -> 0.2.0, 15 -> 0.0.1.0.0)
  s1.amr.get.arcs                                 //> res1: Map[(String, String),String] = Map((0.0,0.0.0) -> ARG0, (ROOT,0) -> RO
                                                  //| OT, (0.0.0.1.0.0,0.0.0.1.0.0.0) -> ARG1, (0.0.0.1.0.0.1,0.0.0.1.0.0.1.0) -> 
                                                  //| ARG2, (0.0.0.0,0.0.0.0.0) -> ARG1-of, (0.0.0,0.0.0.0) -> opN, (0.1,0.1.0) ->
                                                  //|  ARG1, (0.1.0.0.0,0.1.0.0.0.0) -> opN, (0.0.0.1.0,0.0.0.1.0.0) -> ARG0-of, (
                                                  //| 0.1.0,0.1.0.0) -> ARG1, (0.0.0.1.0.0.0,0.0.0.1.0.0.0.0) -> ARG0-of, (0.0.0.1
                                                  //| .0.0.1,0.0.0.1.0) -> ARG1, (0,0.2) -> time, (0,0.1) -> opN, (0.2,0.2.0) -> y
                                                  //| ear, (0.0.0,0.0.0.1) -> opN, (0.0.0.1,0.0.0.1.0) -> consist-of, (0.1,0.0.0) 
                                                  //| -> ARG0, (0.0.1.0,0.0.1.0.1) -> ARG2, (0.1.0.0,0.1.0.0.0) -> name, (0.2,0.2.
                                                  //| 1) -> month, (0,0.0) -> opN, (0.0.1,0.0.1.0) -> ARG0-of, (0.0.1.0,0.0.1.0.0)
                                                  //|  -> ARG1, (0.0.0.1.0.0,0.0.0.1.0.0.1) -> ARG3, (0.0,0.0.1) -> ARG2, (0.2,0.2
                                                  //| .2) -> day, (0.1,0.1.1) -> instrument)
  import java.io._
  val file = new FileWriter("C://AMR//problematic_output.txt")
                                                  //> file  : java.io.FileWriter = java.io.FileWriter@5b1669c0
  for (i <- 0 to 300) {
    if (!expertSystem.isTerminal(state)) {
      stateHistory(i) = state
      action(i) = expert.chooseTransition(s1, state)
      file.write(state.toString + "\n")
      file.write(action(i) + " : " + (state.nodesToProcess match { case Nil => ""; case _ => state.nodesToProcess.head }) + " -> " + (
        state.childrenToProcess match { case Nil => ""; case _ => state.childrenToProcess.head }) + "\n")

      file.flush
      state = action(i)(state)
    }                                             //> WARNING: Found duplicate match for concept attack-01
                                                  //| WARNING: Found duplicate match for concept cyber
                                                  //| WARNING: Found duplicate match for concept attack-01
                                                  //| WARNING: Found duplicate match for concept cyber
  }
  file.close
  println("Here")                                 //> Here
  val outputAdv = RunDagger.sampleTrajectory(s1, "C:\\AMR\\WangXueExpert_output.txt", new WangXueExpert)
                                                  //> outputAdv  : amr.Sentence = Sentence(On 1 July 1999 armed troops and a grou
                                                  //| p of government weapons experts aided customs officials and gained forcible
                                                  //|  entry onto the kuwolsan.,
                                                  //| NodeMap:	Map(0 -> ROOT, 2 -> 1, 3 -> July, 4 -> 1999, 5 -> armed, 6 -> tr
                                                  //| oops, 7 -> and, 9 -> group, 11 -> government-organization, 12 -> weapons, 1
                                                  //| 3 -> experts, 14 -> aided, 15 -> customs, 16 -> officials, 17 -> and, 18 ->
                                                  //|  gained, 19 -> forcible, 24 -> UNKNOWN, 25 -> person, 26 -> date-entity, 27
                                                  //|  -> UNKNOWN, 28 -> person)
                                                  //| SpanMap:	Map(2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 6 -> (6,7), 
                                                  //| 7 -> (7,8), 9 -> (9,10), 11 -> (11,12), 12 -> (12,13), 13 -> (10,13), 14 ->
                                                  //|  (14,15), 15 -> (15,16), 16 -> (16,17), 17 -> (17,18), 18 -> (18,19), 19 ->
                                                  //|  (19,20), 24 -> (11,12), 25 -> (11,12), 26 -> (1,2), 27 -> (15,16), 28 -> (
                                                  //| 15,16))
                                                  //| Edges:	Map((0,7) -> ROOT, (6,5) -> ARG1-of, (7,14) -> opN, (7,18) -> op
                                                  //| N, (7,26) -> time, (9,13) -> UNKNOWN, (13,12) -> ARG2, (13,25) -> ARG1, (14
                                                  //| ,17) -> ARG0, (14,28) -> ARG2, (17,6) -> opN, (17,9) -> opN, (18,19) -> UNK
                                                  //| NOWN, (24,11) -> ARG1, (25,24) -> ARG0-of, (26,2) -> day, (26,3) -> month, 
                                                  //| (26,4) -> year, (27,15) -> ARG1, (27,16) -> ARG2, (28,27) -> ARG0-of)
                                                  //| InsertedNodes:	Map(24 -> 0.0.0.1.0.0, 25 -> 0.0.0.1.0, 28 -> 0.0.1, 27 
                                                  //| -> 0.0.1.0, 26 -> 0.2)
                                                  //| MergedNodes:	Map(13 -> List((10,of)), 19 -> List((20,entry)), 26 -> L
                                                  //| ist((1,On)))
                                                  //| SwappedArcs:	Set(),Some(AMRGraph(Map(12 -> weapons, 19 -> forcible, 4
                                                  //|  -> 1999, 15 -> customs, 11 -> government-organization, 9 -> group, 26 -> d
                                                  //| ate-entity, 13 -> experts, 24 -> UNKNOWN, 16 -> officials, 5 -> armed, 6 ->
                                                  //|  troops, 17 -> and, 25 -> person, 14 -> aided, 0 -> ROOT, 27 -> UNKNOWN, 2 
                                                  //| -> 1, 18 -> gained, 7 -> and, 3 -> July, 28 -> person),Map(12 -> (12,13), 1
                                                  //| 9 -> (19,20), 4 -> (4,5), 15 -> (15,16), 11 -> (11,12), 9 -> (9,10), 26 -> 
                                                  //| (1,2), 13 -> (10,13), 24 -> (11,12), 16 -> (16,17), 5 -> (5,6), 6 -> (6,7),
                                                  //|  17 -> (17,18), 25 -> (11,12), 14 -> (14,15), 27 -> (15,16), 2 -> (2,3), 18
                                                  //|  -> (18,19), 7 -> (7,8), 3 -> (3,4), 28 -> (15,16)),Map((26,4) -> year, (0,
                                                  //| 7) -> ROOT, (7,26) -> time, (28,27) -> ARG0-of, (17,9) -> opN, (25,24) -> A
                                                  //| RG0-of, (27,16) -> ARG2, (6,5) -> ARG1-of, (9,13) -> UNKNOWN, (14,28) -> AR
                                                  //| G2, (26,2) -> day, (18,19) -> UNKNOWN, (24,11) -> ARG1, (7,14) -> opN, (17,
                                                  //| 6) -> opN, (14,17) -> ARG0, (26,3) -> month, (13,12) 
                                                  //| Output exceeds cutoff limit.
  outputAdv                                       //> res2: amr.Sentence = Sentence(On 1 July 1999 armed troops and a group of go
                                                  //| vernment weapons experts aided customs officials and gained forcible entry 
                                                  //| onto the kuwolsan.,
                                                  //| NodeMap:	Map(0 -> ROOT, 2 -> 1, 3 -> July, 4 -> 1999, 5 -> armed, 6 -> tr
                                                  //| oops, 7 -> and, 9 -> group, 11 -> government-organization, 12 -> weapons, 1
                                                  //| 3 -> experts, 14 -> aided, 15 -> customs, 16 -> officials, 17 -> and, 18 ->
                                                  //|  gained, 19 -> forcible, 24 -> UNKNOWN, 25 -> person, 26 -> date-entity, 27
                                                  //|  -> UNKNOWN, 28 -> person)
                                                  //| SpanMap:	Map(2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 6 -> (6,7), 
                                                  //| 7 -> (7,8), 9 -> (9,10), 11 -> (11,12), 12 -> (12,13), 13 -> (10,13), 14 ->
                                                  //|  (14,15), 15 -> (15,16), 16 -> (16,17), 17 -> (17,18), 18 -> (18,19), 19 ->
                                                  //|  (19,20), 24 -> (11,12), 25 -> (11,12), 26 -> (1,2), 27 -> (15,16), 28 -> (
                                                  //| 15,16))
                                                  //| Edges:	Map((0,7) -> ROOT, (6,5) -> ARG1-of, (7,14) -> opN, (7,18) -> op
                                                  //| N, (7,26) -> time, (9,13) -> UNKNOWN, (13,12) -> ARG2, (13,25) -> ARG1, (14
                                                  //| ,17) -> ARG0, (14,28) -> ARG2, (17,6) -> opN, (17,9) -> opN, (18,19) -> UNK
                                                  //| NOWN, (24,11) -> ARG1, (25,24) -> ARG0-of, (26,2) -> day, (26,3) -> month, 
                                                  //| (26,4) -> year, (27,15) -> ARG1, (27,16) -> ARG2, (28,27) -> ARG0-of)
                                                  //| InsertedNodes:	Map(24 -> 0.0.0.1.0.0, 25 -> 0.0.0.1.0, 28 -> 0.0.1, 27 
                                                  //| -> 0.0.1.0, 26 -> 0.2)
                                                  //| MergedNodes:	Map(13 -> List((10,of)), 19 -> List((20,entry)), 26 -> L
                                                  //| ist((1,On)))
                                                  //| SwappedArcs:	Set(),Some(AMRGraph(Map(12 -> weapons, 19 -> forcible, 4
                                                  //|  -> 1999, 15 -> customs, 11 -> government-organization, 9 -> group, 26 -> d
                                                  //| ate-entity, 13 -> experts, 24 -> UNKNOWN, 16 -> officials, 5 -> armed, 6 ->
                                                  //|  troops, 17 -> and, 25 -> person, 14 -> aided, 0 -> ROOT, 27 -> UNKNOWN, 2 
                                                  //| -> 1, 18 -> gained, 7 -> and, 3 -> July, 28 -> person),Map(12 -> (12,13), 1
                                                  //| 9 -> (19,20), 4 -> (4,5), 15 -> (15,16), 11 -> (11,12), 9 -> (9,10), 26 -> 
                                                  //| (1,2), 13 -> (10,13), 24 -> (11,12), 16 -> (16,17), 5 -> (5,6), 6 -> (6,7),
                                                  //|  17 -> (17,18), 25 -> (11,12), 14 -> (14,15), 27 -> (15,16), 2 -> (2,3), 18
                                                  //|  -> (18,19), 7 -> (7,8), 3 -> (3,4), 28 -> (15,16)),Map((26,4) -> year, (0,
                                                  //| 7) -> ROOT, (7,26) -> time, (28,27) -> ARG0-of, (17,9) -> opN, (25,24) -> A
                                                  //| RG0-of, (27,16) -> ARG2, (6,5) -> ARG1-of, (9,13) -> UNKNOWN, (14,28) -> AR
                                                  //| G2, (26,2) -> day, (18,19) -> UNKNOWN, (24,11) -> ARG1, (7,14) -> opN, (17,
                                                  //| 6) -> opN, (14,17) -> ARG0, (26,3) -> month, (13,12) -> ARG2
                                                  //| Output exceeds cutoff limit.
}