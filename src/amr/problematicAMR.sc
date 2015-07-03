package amr

object problematicAMR {
 // val testData = AMRGraph.importFile("C:\\AMR\\problematicAMR.txt") - Kuwolsan that created loop
 
 val testData = AMRGraph.importFile("C:\\AMR\\AMRInstance4.txt") // Nato Considers....
                                                  //> testData  : IndexedSeq[(String, String)] = Vector((NATO CONSIDERS cyber atta
                                                  //| cks a threat to military and civilian computer networks after the Estonian G
                                                  //| overnment was struck by cyber attacks in 2007.,(c / consider-02 :ARG0 (m2 / 
                                                  //| military :name (n / name :op1 "NATO")) :ARG1 (a / attack-01 :mod (c2 / cyber
                                                  //| )) :ARG2 (t / thing :ARG1-of (t2 / threaten-01 :ARG2 (a5 / and :op1 (n2 / ne
                                                  //| twork :mod (m / military)) :op2 (n4 / network :mod (c3 / civilian)) :mod (c4
                                                  //|  / computer)))) :time (a3 / after :op1 (s / strike-01 :ARG0 (a4 / attack :mo
                                                  //| d (c6 / cyber)) :ARG1 (g / government-organization :ARG0-of (g2 / govern-01 
                                                  //| :ARG1 (c5 / country :name (n3 / name :op1 "Estonia")))) :time (d / date-enti
                                                  //| ty :year 2007))))))
  val expert = new WangXueExpert                  //> expert  : amr.WangXueExpert = amr.WangXueExpert@5419f379
  val expertSystem = new WangXueTransitionSystem  //> expertSystem  : amr.WangXueTransitionSystem = amr.WangXueTransitionSystem@7d
                                                  //| c7cbad
  val s1 = Sentence(testData(0)._1, testData(0)._2)
                                                  //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [3.0 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [5.3 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [4.2 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [6.6 sec].
                                                  //| WARNING: Found duplicate match for concept attack-01
                                                  //| WARNING: Found duplicate match for concept cyber
                                                  //| s1  : amr.Sentence = Sentence(NATO CONSIDERS cyber attacks a threat to milit
                                                  //| ary and civilian computer networks after the Estonian Government was struck 
                                                  //| by cyber attacks in 2007.,
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> NATO, 2 -> CONSIDERS, 3 -> cyber, 4 -> attac
                                                  //| ks, 5 -> a, 6 -> threat, 7 -> to, 8 -> military, 9 -> and, 10 -> civilian, 1
                                                  //| 1 -> computer, 12 -> networks, 13 -> after, 14 -> the, 15 -> Estonian, 16 ->
                                                  //|  Government, 17 -> was, 18 -> struck, 19 -> by, 20 -> cyber, 21 -> attacks, 
                                                  //| 22 -> in, 23 -> 2007)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,12
                                                  //| ), 12 -> (12,13), 13 -> (13,14), 14 -> (14,15), 15 -> (15,16), 16 -> (16,17)
                                                  //| , 17 -> (17,18), 18 -> (18,19), 19 -> (19,20), 20 -> (20,21), 21 -> (21,22),
                                                  //|  22 -> (22,23), 23 -> (23,24))
                                                  //| Edges:	Map((0,4) -> root, (3,1) -> nn, (3,2) -> nn, (4,3) -> nsubj, (4,
                                                  //| 6) -> dobj, (4,7) -> prep, (4,18) -> advcl, (6,5) -> det, (7,12) -> pobj, (8
                                                  //| ,9) -> cc, (8,10) -> conj, (12,8) -> amod, (12,11) -> nn, (16,14) -> det, (1
                                                  //| 6,15) -> amod, (18,13) -> mark, (18,16) -> nsubjpass, (18,17) -> auxpass, (1
                                                  //| 8,19) -> prep, (19,21) -> pobj, (21,20) -> nn, (21,22) -> prep, (22,23) -> p
                                                  //| obj)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set(),Some(AMRGraph(Map(0.1 -> attack-01, 0.0.0 -> name,
                                                  //|  0.3.0.1.0.0 -> country, 0.2.0.0.0 -> network, 0.1.0 -> cyber, 0.2.0.0.1.0 -
                                                  //| > civilian, 0.3.0.2.0 -> 2007, 0.0 -> military, 0.2.0.0.1 -> network, 0.3.0.
                                                  //| 1.0 -> govern-01, 0.0.0.0 -> "NATO", 0.2.0 -> threaten-01, 0.3.0.0 -> attack
                                                  //| , 0.3.0 -> strike-01, 0.3 -> after, 0.2.0.0.0.0 -> military, 0.2.0.0.2 -> co
                                                  //| mputer, 0 
                                                  //| Output exceeds cutoff limit.
  var state = expertSystem.init(s1)               //> state  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(23, 22, 20, 10, 9, 14, 21, 11, 8, 15, 5, 1, 13, 2, 17, 12, 
                                                  //| 16, 19, 7, 6, 18, 3, 4, 0)
                                                  //| Children:	List()
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> NATO, 2 -> CONSIDERS, 3 -> cyber, 4 -> attac
                                                  //| ks, 5 -> a, 6 -> threat, 7 -> to, 8 -> military, 9 -> and, 10 -> civilian, 1
                                                  //| 1 -> computer, 12 -> networks, 13 -> after, 14 -> the, 15 -> Estonian, 16 ->
                                                  //|  Government, 17 -> was, 18 -> struck, 19 -> by, 20 -> cyber, 21 -> attacks, 
                                                  //| 22 -> in, 23 -> 2007)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,12
                                                  //| ), 12 -> (12,13), 13 -> (13,14), 14 -> (14,15), 15 -> (15,16), 16 -> (16,17)
                                                  //| , 17 -> (17,18), 18 -> (18,19), 19 -> (19,20), 20 -> (20,21), 21 -> (21,22),
                                                  //|  22 -> (22,23), 23 -> (23,24))
                                                  //| Edges:	Map((0,4) -> root, (3,1) -> nn, (3,2) -> nn, (4,3) -> nsubj, (4,
                                                  //| 6) -> dobj, (4,7) -> prep, (4,18) -> advcl, (6,5) -> det, (7,12) -> pobj, (8
                                                  //| ,9) -> cc, (8,10) -> conj, (12,8) -> amod, (12,11) -> nn, (16,14) -> det, (1
                                                  //| 6,15) -> amod, (18,13) -> mark, (18,16) -> nsubjpass, (18,17) -> auxpass, (1
                                                  //| 8,19) -> prep, (19,21) -> pobj, (21,20) -> nn, (21,22) -> prep, (22,23) -> p
                                                  //| obj)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| Mappings:	0 -> ROOT
                                                  //| 10 -> 0.2.0.0.1.0
                                                  //| 20 -> 0.3.0.0.0
                                                  //| 1 -> 0.0.0.0
                                                  //| 6 -> 0.2.0
                                                  //| 21 -> 0.3.0.0
                                                  //| 9 -> 0.2.0.0
                                                  //| 13 -> 0.3
                                                  //| 2 -> 0
                                                  //| 12 -> 0.2.0.0.0
                                                  //| 3 -> 0.1.0
                                                  //| 18 -> 0.3.0
                                                  //| 16 -> 0.3.0.1
                                                  //| 11 -> 0.2.0.0.2
                                                  //| 23 -> 0.3.0.2.0
                                                  //| 8 -> 0.0
                                                  //| 4 -> 0.1
                                                  //| 15 -> 0.3.0.1.0.0.0.0
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
  s1.positionToAMR                                //> res0: Map[Int,String] = Map(0 -> ROOT, 10 -> 0.2.0.0.1.0, 20 -> 0.3.0.0.0, 1
                                                  //|  -> 0.0.0.0, 6 -> 0.2.0, 21 -> 0.3.0.0, 9 -> 0.2.0.0, 13 -> 0.3, 2 -> 0, 12 
                                                  //| -> 0.2.0.0.0, 3 -> 0.1.0, 18 -> 0.3.0, 16 -> 0.3.0.1, 11 -> 0.2.0.0.2, 23 ->
                                                  //|  0.3.0.2.0, 8 -> 0.0, 4 -> 0.1, 15 -> 0.3.0.1.0.0.0.0)
  s1.amr.get.nodes                                //> res1: Map[String,String] = Map(0.1 -> attack-01, 0.0.0 -> name, 0.3.0.1.0.0 
                                                  //| -> country, 0.2.0.0.0 -> network, 0.1.0 -> cyber, 0.2.0.0.1.0 -> civilian, 0
                                                  //| .3.0.2.0 -> 2007, 0.0 -> military, 0.2.0.0.1 -> network, 0.3.0.1.0 -> govern
                                                  //| -01, 0.0.0.0 -> "NATO", 0.2.0 -> threaten-01, 0.3.0.0 -> attack, 0.3.0 -> st
                                                  //| rike-01, 0.3 -> after, 0.2.0.0.0.0 -> military, 0.2.0.0.2 -> computer, 0 -> 
                                                  //| consider-02, 0.3.0.1 -> government-organization, 0.2 -> thing, 0.3.0.2 -> da
                                                  //| te-entity, 0.3.0.1.0.0.0.0 -> "Estonia", ROOT -> ROOT, 0.2.0.0 -> and, 0.3.0
                                                  //| .1.0.0.0 -> name, 0.3.0.0.0 -> cyber)
  s1.amr.get.arcs                                 //> res2: Map[(String, String),String] = Map((0.0,0.0.0) -> name, (0.3.0,0.3.0.0
                                                  //| ) -> ARG0, (ROOT,0) -> ROOT, (0,0.3) -> time, (0.0.0,0.0.0.0) -> opN, (0.2.0
                                                  //| .0.1,0.2.0.0.1.0) -> mod, (0.1,0.1.0) -> mod, (0.3.0,0.3.0.1) -> ARG1, (0.3.
                                                  //| 0,0.3.0.2) -> time, (0.3.0.0,0.3.0.0.0) -> mod, (0,0.2) -> ARG2, (0,0.1) -> 
                                                  //| ARG1, (0.2.0.0.0,0.2.0.0.0.0) -> mod, (0.2,0.2.0) -> ARG1-of, (0.3,0.3.0) ->
                                                  //|  opN, (0.3.0.1.0,0.3.0.1.0.0) -> ARG1, (0.2.0.0,0.2.0.0.1) -> opN, (0.2.0.0,
                                                  //| 0.2.0.0.0) -> opN, (0.3.0.1.0.0.0,0.3.0.1.0.0.0.0) -> opN, (0.3.0.1,0.3.0.1.
                                                  //| 0) -> ARG0-of, (0,0.0) -> ARG0, (0.3.0.1.0.0,0.3.0.1.0.0.0) -> name, (0.2.0,
                                                  //| 0.2.0.0) -> ARG2, (0.3.0.2,0.3.0.2.0) -> year, (0.2.0.0,0.2.0.0.2) -> mod)
  import java.io._
  val file = new FileWriter("C://AMR//i4_output.txt")
                                                  //> file  : java.io.FileWriter = java.io.FileWriter@449a4f23
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
                                                  //> outputAdv  : amr.Sentence = Sentence(NATO CONSIDERS cyber attacks a threat 
                                                  //| to military and civilian computer networks after the Estonian Government wa
                                                  //| s struck by cyber attacks in 2007.,
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> "NATO", 2 -> consider-02, 3 -> cyber, 4 -> a
                                                  //| ttack-01, 6 -> threaten-01, 8 -> military, 9 -> and, 10 -> civilian, 11 -> 
                                                  //| computer, 12 -> network, 13 -> after, 15 -> "Estonia", 16 -> government-org
                                                  //| anization, 18 -> strike-01, 20 -> cyber, 21 -> attack, 23 -> 2007, 24 -> da
                                                  //| te-entity, 25 -> network, 26 -> name, 27 -> country, 28 -> govern-01, 29 ->
                                                  //|  name, 30 -> thing)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 6 -> (6,7), 
                                                  //| 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,12), 12 -> (7,12), 13 -> 
                                                  //| (13,14), 15 -> (15,16), 16 -> (16,17), 18 -> (18,19), 20 -> (20,21), 21 -> 
                                                  //| (19,21), 23 -> (23,24), 24 -> (22,23), 25 -> (10,11), 26 -> (15,16), 27 -> 
                                                  //| (15,16), 28 -> (15,16), 29 -> (1,2), 30 -> (6,7))
                                                  //| Edges:	Map((0,2) -> ROOT, (2,4) -> ARG1, (2,8) -> ARG0, (2,13) -> time,
                                                  //|  (2,30) -> ARG2, (4,3) -> mod, (6,9) -> ARG2, (8,29) -> name, (9,11) -> mod
                                                  //| , (9,12) -> opN, (9,25) -> opN, (13,18) -> opN, (16,28) -> ARG0-of, (18,16)
                                                  //|  -> ARG1, (18,21) -> ARG0, (18,24) -> time, (21,20) -> mod, (24,23) -> year
                                                  //| , (25,10) -> mod, (26,15) -> opN, (27,26) -> name, (28,27) -> ARG1, (29,1) 
                                                  //| -> opN, (30,6) -> ARG1-of)
                                                  //| InsertedNodes:	Map(24 -> 0.3.0.2, 25 -> 0.2.0.0.1, 29 -> 0.0.0, 28 -> 0
                                                  //| .3.0.1.0, 27 -> 0.3.0.1.0.0, 26 -> 0.3.0.1.0.0.0, 30 -> 0.2)
                                                  //| MergedNodes:	Map(24 -> List((22,in)), 21 -> List((19,by)), 12 -> List
                                                  //| ((7,to)))
                                                  //| SwappedArcs:	Set((18,13)),Some(AMRGraph(Map(12 -> network, 8 -> milit
                                                  //| ary, 23 -> 2007, 4 -> attack-01, 15 -> "Estonia", 11 -> computer, 9 -> and,
                                                  //|  26 -> name, 13 -> after, 24 -> date-entity, 16 -> government-organization,
                                                  //|  10 -> civilian, 21 -> attack, 6 -> threaten-01, 1 -> "NATO", 25 -> network
                                                  //| , 0 -> ROOT, 20 -> cyber, 27 -> country, 2 -> consider-02, 18 -> strike-01,
                                                  //|  30 -> thing, 29 -> name, 3 -> cyber, 28 -> govern-01),Map(12 -> (7,12), 8 
                                                  //| -> (8,9), 23 -> (23,24), 4 -> (4,5), 15 -> (15,16), 11 -> (11,12), 9 -> (9,
                                                  //| 10), 26 -> (15,16), 13 -> (13,14), 24 -> (22,23), 16 -> (16,17), 10 -> (10,
                                                  //| 11), 21 -> (19,21), 6 -> (6,7), 1 -> (1,2), 25 -> (10,11), 20 -> (20,21), 2
                                                  //| 7 -> (15,16), 2 -> (2,3), 18 -> (18,19), 30 -> (6,7), 29 -> (1,2), 3 -> (3,
                                                  //| 4), 28 -> (15,16)
                                                  //| Output exceeds cutoff limit.
  outputAdv                                       //> res3: amr.Sentence = Sentence(NATO CONSIDERS cyber attacks a threat to mili
                                                  //| tary and civilian computer networks after the Estonian Government was struc
                                                  //| k by cyber attacks in 2007.,
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> "NATO", 2 -> consider-02, 3 -> cyber, 4 -> a
                                                  //| ttack-01, 6 -> threaten-01, 8 -> military, 9 -> and, 10 -> civilian, 11 -> 
                                                  //| computer, 12 -> network, 13 -> after, 15 -> "Estonia", 16 -> government-org
                                                  //| anization, 18 -> strike-01, 20 -> cyber, 21 -> attack, 23 -> 2007, 24 -> da
                                                  //| te-entity, 25 -> network, 26 -> name, 27 -> country, 28 -> govern-01, 29 ->
                                                  //|  name, 30 -> thing)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 6 -> (6,7), 
                                                  //| 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,12), 12 -> (7,12), 13 -> 
                                                  //| (13,14), 15 -> (15,16), 16 -> (16,17), 18 -> (18,19), 20 -> (20,21), 21 -> 
                                                  //| (19,21), 23 -> (23,24), 24 -> (22,23), 25 -> (10,11), 26 -> (15,16), 27 -> 
                                                  //| (15,16), 28 -> (15,16), 29 -> (1,2), 30 -> (6,7))
                                                  //| Edges:	Map((0,2) -> ROOT, (2,4) -> ARG1, (2,8) -> ARG0, (2,13) -> time,
                                                  //|  (2,30) -> ARG2, (4,3) -> mod, (6,9) -> ARG2, (8,29) -> name, (9,11) -> mod
                                                  //| , (9,12) -> opN, (9,25) -> opN, (13,18) -> opN, (16,28) -> ARG0-of, (18,16)
                                                  //|  -> ARG1, (18,21) -> ARG0, (18,24) -> time, (21,20) -> mod, (24,23) -> year
                                                  //| , (25,10) -> mod, (26,15) -> opN, (27,26) -> name, (28,27) -> ARG1, (29,1) 
                                                  //| -> opN, (30,6) -> ARG1-of)
                                                  //| InsertedNodes:	Map(24 -> 0.3.0.2, 25 -> 0.2.0.0.1, 29 -> 0.0.0, 28 -> 0
                                                  //| .3.0.1.0, 27 -> 0.3.0.1.0.0, 26 -> 0.3.0.1.0.0.0, 30 -> 0.2)
                                                  //| MergedNodes:	Map(24 -> List((22,in)), 21 -> List((19,by)), 12 -> List
                                                  //| ((7,to)))
                                                  //| SwappedArcs:	Set((18,13)),Some(AMRGraph(Map(12 -> network, 8 -> milit
                                                  //| ary, 23 -> 2007, 4 -> attack-01, 15 -> "Estonia", 11 -> computer, 9 -> and,
                                                  //|  26 -> name, 13 -> after, 24 -> date-entity, 16 -> government-organization,
                                                  //|  10 -> civilian, 21 -> attack, 6 -> threaten-01, 1 -> "NATO", 25 -> network
                                                  //| , 0 -> ROOT, 20 -> cyber, 27 -> country, 2 -> consider-02, 18 -> strike-01,
                                                  //|  30 -> thing, 29 -> name, 3 -> cyber, 28 -> govern-01),Map(12 -> (7,12), 8 
                                                  //| -> (8,9), 23 -> (23,24), 4 -> (4,5), 15 -> (15,16), 11 -> (11,12), 9 -> (9,
                                                  //| 10), 26 -> (15,16), 13 -> (13,14), 24 -> (22,23), 16 -> (16,17), 10 -> (10,
                                                  //| 11), 21 -> (19,21), 6 -> (6,7), 1 -> (1,2), 25 -> (10,11), 20 -> (20,21), 2
                                                  //| 7 -> (15,16), 2 -> (2,3), 18 -> (18,19), 30 -> (6,7), 29 -> (1,2), 3 -> (3,
                                                  //| 4), 28 -> (15,16)),Map((
                                                  //| Output exceeds cutoff limit.
}