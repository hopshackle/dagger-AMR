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
  val expert = new WangXueExpert                  //> expert  : amr.WangXueExpert = amr.WangXueExpert@5419f379
  val expertSystem = new WangXueTransitionSystem  //> expertSystem  : amr.WangXueTransitionSystem = amr.WangXueTransitionSystem@7d
                                                  //| c7cbad
  val s1 = Sentence(testData(0)._1, testData(0)._2)
                                                  //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.3 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [4.7 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [3.4 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [2.8 sec].
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
  val action = new Array[WangXueAction](500)      //> action  : Array[amr.WangXueAction] = Array(null, null, null, null, null, nul
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
                                                  //|  null, null, null, null, null, null, null, null, null, null, null, null, nul
                                                  //| l, null, null, null, null, null, null, null, null, null, null, null, null, n
                                                  //| ull, null, null, null, null, null, null, null, null, null, null, null, null,
                                                  //|  null, null, null, null, null, null, null, null, null, null, null, null, nul
                                                  //| l, null, null, null, null, null, null, null, null, null, null, null, null, n
                                                  //| ull, null, null, null, null, null, null, null, null, null, null, null, null,
                                                  //|  null, null, null, null, null, null, null, null, null, null, null,
                                                  //| Output exceeds cutoff limit.
  val stateHistory = new Array[WangXueTransitionState](500)
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
                                                  //| ll, null, null, null, null, null, null, null, null, null, null, null, null, 
                                                  //| null, null, null, null, null, null, null, null, null, null, null, null, null
                                                  //| , null, null, null, null, null, null, null, null, null, null, null, null, nu
                                                  //| ll, null, null, null, null, null, null, null, null, null, null, null, null, 
                                                  //| null, null, null, null, null, null, null, null, null, null, null, null, null
                                                  //| , null, null, null, null, null, null, null, null, null, null, null, null, nu
                                                  //| ll, null, null, null, null, null, null, null, null, null, null, nu
                                                  //| Output exceeds cutoff limit.
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
                                                  //> file  : java.io.FileWriter = java.io.FileWriter@6d4d66d2
  for (i <- 0 to 450) {
    stateHistory(i) = state
    action(i) = expert.chooseTransition(s1, state)
    file.write(action(i) + " : " + (state.nodesToProcess match {case Nil => ""; case _ => state.nodesToProcess.head}) + " -> " + (
      state.childrenToProcess match { case Nil => ""; case _ => state.childrenToProcess.head }) + "\n")
    file.flush
    state = action(i)(state)
  }                                               //> WARNING: Found duplicate match for concept attack-01
                                                  //| WARNING: Found duplicate match for concept cyber
                                                  //| WARNING: Found duplicate match for concept attack-01
                                                  //| WARNING: Found duplicate match for concept cyber
                                                  //| java.util.NoSuchElementException: head of empty list
                                                  //| 	at scala.collection.immutable.Nil$.head(List.scala:420)
                                                  //| 	at scala.collection.immutable.Nil$.head(List.scala:417)
                                                  //| 	at amr.WangXueExpert.chooseTransition(WangXueExpert.scala:26)
                                                  //| 	at amr.problematicAMR$$anonfun$main$1$$anonfun$apply$mcV$sp$1.apply$mcVI
                                                  //| $sp(amr.problematicAMR.scala:17)
                                                  //| 	at scala.collection.immutable.Range.foreach$mVc$sp(Range.scala:166)
                                                  //| 	at amr.problematicAMR$$anonfun$main$1.apply$mcV$sp(amr.problematicAMR.sc
                                                  //| ala:15)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$$anonfun$$exe
                                                  //| cute$1.apply$mcV$sp(WorksheetSupport.scala:76)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.redirected(W
                                                  //| orksheetSupport.scala:65)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.$execute(Wor
                                                  //| ksheetSupport.scala:75)
                                                  //| 	at amr.problematicAMR$.main(amr.problematicAMR.scala:3)
                                                  //| 	at amr.problematicAMR.main(amr.problematicAMR.scala)
  file.close

  val outputAdv = RunDagger.sampleTrajectory(s1, "C:\\AMR\\WangXueExpert_output.txt", new WangXueExpert)
  outputAdv
}