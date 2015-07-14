package amr

object polarityAMR {
val testData = AMRGraph.importFile("C:\\AMR\\borderless.txt") // Nato Considers....
                                                  //> testData  : IndexedSeq[(String, String)] = Vector((It is even more complicat
                                                  //| ed in borderless cyber space where there is no smoking gun, fingerprints or 
                                                  //| footprints.,(c / complicate :degree (m / more) :mod (e / even) :location (s 
                                                  //| / space :mod (c2 / cyber) :mod (b / border :polarity -) :location-of (a / an
                                                  //| d :op1 (g / gun :polarity - :ARG0-of (s2 / smoke-01)) :op2 (f / fingerprint 
                                                  //| :polarity -) :op3 (f2 / footprint :polarity -))))))
  val expert = new WangXueExpert                  //> expert  : amr.WangXueExpert = amr.WangXueExpert@7c16905e
  val expertSystem = new WangXueTransitionSystem  //> expertSystem  : amr.WangXueTransitionSystem = amr.WangXueTransitionSystem@2a
                                                  //| 2d45ba
  val s1 = Sentence(testData(0)._1, testData(0)._2)
                                                  //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.7 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [5.7 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [3.1 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [4.5 sec].
                                                  //| s1  : amr.Sentence = Sentence(It is even more complicated in borderless cybe
                                                  //| r space where there is no smoking gun, fingerprints or footprints.,
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> It, 2 -> is, 3 -> even, 4 -> more, 5 -> comp
                                                  //| licated, 6 -> in, 7 -> borderless, 8 -> cyber, 9 -> space, 10 -> where, 11 -
                                                  //| > there, 12 -> is, 13 -> no, 14 -> smoking, 15 -> gun, 17 -> fingerprints, 1
                                                  //| 8 -> or, 19 -> footprints)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,12
                                                  //| ), 12 -> (12,13), 13 -> (13,14), 14 -> (14,15), 15 -> (15,16), 17 -> (16,17)
                                                  //| , 18 -> (17,18), 19 -> (18,19))
                                                  //| Edges:	Map((0,5) -> root, (5,1) -> nsubj, (5,2) -> cop, (5,3) -> advmod
                                                  //| , (5,4) -> advmod, (5,6) -> prep, (5,12) -> advcl, (6,9) -> pobj, (9,7) -> a
                                                  //| mod, (9,8) -> nn, (12,10) -> advmod, (12,11) -> expl, (12,15) -> nsubj, (15,
                                                  //| 13) -> det, (15,14) -> nn, (15,17) -> conj, (15,18) -> cc, (15,19) -> conj)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set(),Some(AMRGraph(Map(0.1 -> even, 0.2.2.1.0 -> -, 0.2
                                                  //| .2.0 -> gun, 0.2.2.2.0 -> -, 0.2.1 -> border, 0.0 -> more, 0.2.0 -> cyber, 0
                                                  //| .2.2.0.1 -> smoke-01, 0.2.2.2 -> footprint, 0 -> complicate, 0.2.2.1 -> fing
                                                  //| erprint, 0.2 -> space, 0.2.2 -> and, ROOT -> ROOT, 0.2.2.0.0 -> -, 0.2.1.0 -
                                                  //| > -),Map(0.1 -> (3,4), 0.2.2.0 -> (15,16), 0.2.1 -> (7,8), 0.0 -> (4,5), 0.2
                                                  //| .0 -> (8,9), 0.2.2.0.1 -> (14,15), 0.2.2.2 -> (18,19), 0 -> (5,6), 0.2.2.1 -
                                                  //| > (16,17), 0.2 -> (9,10), 0.2.1.0 -> (13,14)),Map((0.2.2.0,0.2.2.0.1) -> ARG
                                                  //| 0-of, (ROOT,0) -> ROOT, (0.2.2.1,0.2.2.1.0) -> polarity, (0.2.2,0.2.2.1) -> 
                                                  //| opN, (0.2.2,0.2.2.2) -> opN, (0,0.2) -> location, (0,0.1) -> mod, (0.2,0.2.0
                                                  //| ) -> mod, (0.2.2.2,0.2.2.2.0) -> polarity, (0.2.2,0.2.2.0) -> opN, (0.2.1,0.
                                                  //| 2.1.
                                                  //| Output exceeds cutoff limit.
  var state = expertSystem.init(s1)               //> state  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(14, 13, 17, 7, 18, 8, 19, 9, 10, 15, 11, 1, 6, 2, 12, 3, 4,
                                                  //|  5, 0)
                                                  //| Children:	List()
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> It, 2 -> is, 3 -> even, 4 -> more, 5 -> comp
                                                  //| licated, 6 -> in, 7 -> borderless, 8 -> cyber, 9 -> space, 10 -> where, 11 -
                                                  //| > there, 12 -> is, 13 -> no, 14 -> smoking, 15 -> gun, 17 -> fingerprints, 1
                                                  //| 8 -> or, 19 -> footprints)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,12
                                                  //| ), 12 -> (12,13), 13 -> (13,14), 14 -> (14,15), 15 -> (15,16), 17 -> (16,17)
                                                  //| , 18 -> (17,18), 19 -> (18,19))
                                                  //| Edges:	Map((0,5) -> root, (5,1) -> nsubj, (5,2) -> cop, (5,3) -> advmod
                                                  //| , (5,4) -> advmod, (5,6) -> prep, (5,12) -> advcl, (6,9) -> pobj, (9,7) -> a
                                                  //| mod, (9,8) -> nn, (12,10) -> advmod, (12,11) -> expl, (12,15) -> nsubj, (15,
                                                  //| 13) -> det, (15,14) -> nn, (15,17) -> conj, (15,18) -> cc, (15,19) -> conj)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| Mappings:	0 -> ROOT
                                                  //| 5 -> 0
                                                  //| 14 -> 0.2.2.0.1
                                                  //| 9 -> 0.2
                                                  //| 13 -> 0.2.1.0
                                                  //| 17 -> 0.2.2.1
                                                  //| 7 -> 0.2.1
                                                  //| 3 -> 0.1
                                                  //| 8 -> 0.2.0
                                                  //| 19 -> 0.2.2.2
                                                  //| 4 -> 0.0
                                                  //| 15 -> 0.2.2.0
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
  s1.positionToAMR                                //> res0: Map[Int,String] = Map(0 -> ROOT, 5 -> 0, 14 -> 0.2.2.0.1, 9 -> 0.2, 13
                                                  //|  -> 0.2.1.0, 17 -> 0.2.2.1, 7 -> 0.2.1, 3 -> 0.1, 8 -> 0.2.0, 19 -> 0.2.2.2,
                                                  //|  4 -> 0.0, 15 -> 0.2.2.0)
  s1.amr.get.nodes                                //> res1: Map[String,String] = Map(0.1 -> even, 0.2.2.1.0 -> -, 0.2.2.0 -> gun, 
                                                  //| 0.2.2.2.0 -> -, 0.2.1 -> border, 0.0 -> more, 0.2.0 -> cyber, 0.2.2.0.1 -> s
                                                  //| moke-01, 0.2.2.2 -> footprint, 0 -> complicate, 0.2.2.1 -> fingerprint, 0.2 
                                                  //| -> space, 0.2.2 -> and, ROOT -> ROOT, 0.2.2.0.0 -> -, 0.2.1.0 -> -)
  s1.amr.get.arcs                                 //> res2: Map[(String, String),String] = Map((0.2.2.0,0.2.2.0.1) -> ARG0-of, (RO
                                                  //| OT,0) -> ROOT, (0.2.2.1,0.2.2.1.0) -> polarity, (0.2.2,0.2.2.1) -> opN, (0.2
                                                  //| .2,0.2.2.2) -> opN, (0,0.2) -> location, (0,0.1) -> mod, (0.2,0.2.0) -> mod,
                                                  //|  (0.2.2.2,0.2.2.2.0) -> polarity, (0.2.2,0.2.2.0) -> opN, (0.2.1,0.2.1.0) ->
                                                  //|  polarity, (0.2,0.2.1) -> mod, (0.2.2.0,0.2.2.0.0) -> polarity, (0,0.0) -> d
                                                  //| egree, (0.2,0.2.2) -> location-of)
  import java.io._
  val file = new FileWriter("C://AMR//borderless_output.txt")
                                                  //> file  : java.io.FileWriter = java.io.FileWriter@2de56eb2
  for (i <- 0 to 300) {
    if (!expertSystem.isTerminal(state)) {
      stateHistory(i) = state
      if (i > 4) println(state)
      action(i) = expert.chooseTransition(s1, state)
      println(action(i))
      file.write(state.toString + "\n")
      file.write(action(i) + " : " + (state.nodesToProcess match { case Nil => ""; case _ => state.nodesToProcess.head }) + " -> " + (
        state.childrenToProcess match { case Nil => ""; case _ => state.childrenToProcess.head }) + "\n")

      file.flush
      state = action(i)(state)
    }                                             //> WARNING: Found duplicate match for concept attack-01
                                                  //| WARNING: Found duplicate match for concept cyber
                                                  //| NextNode: 0 -> UNKNOWN
                                                  //| NextNode: 16 -> -
                                                  //| InsertNode: and (Ref: 0.2.2)
                                                  //| ReversePolarity
                                                  //| WARNING: Found duplicate match for concept attack-01
                                                  //| WARNING: Found duplicate match for concept cyber
                                                  //| NextEdge: 1 -> polarity
                                                  //| 
                                                  //| NodesToGo:	List(17, 21, 20, 7, 18, 8, 19, 9, 10, 15, 11, 1, 6, 2, 12, 3, 4,
                                                  //|  5, 0)
                                                  //| Children:	List()
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> It, 2 -> is, 3 -> even, 4 -> more, 5 -> comp
                                                  //| licated, 6 -> in, 7 -> borderless, 8 -> cyber, 9 -> space, 10 -> where, 11 
                                                  //| -> there, 12 -> is, 13 -> -, 14 -> smoking, 15 -> gun, 17 -> fingerprints, 
                                                  //| 18 -> or, 19 -> footprints, 20 -> and, 21 -> -)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,1
                                                  //| 2), 12 -> (12,13), 13 -> (13,14), 14 -> (14,15), 15 -> (15,16), 17 -> (16,1
                                                  //| 7), 18 -> (17,18), 19 -> (18,19), 20 -> (16,17), 21 -> (16,17))
                                                  //| Edges:	Map((0,5) -> root, (5,1) -> nsubj, (5,2) -> cop, (5,3) -> advmod
                                                  //| , (5,4) -> advmod, (5,6) -> prep, (5,12) -> advcl, (6,9) -> pobj, (9,7) -> 
                                                  //| amod, (9,8) -> nn, (12,10) -> advmod, (12,11) -> expl, (12,15) -> nsubj, (1
                                                  //| 5,13) -> det, (15,14) -> nn, (15,18) -> cc, (15,19) -> conj, (15,20) -> con
                                                  //| j, (17,21) -> polarity, (20,17) -> and#)
                                                  //| InsertedNodes:	Map(20 -> 0.2.2, 21 -> 0.2.2.1.0)
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| Mappings:	0 -> ROOT
                                                  //| 5 -> 0
                                                  //| 14 -> 0.2.2.0.1
                                                  //| 20 -> 0.2.2
                                                  //| 21 -> 0.2.2.1.0
                                                  //| 9 -> 0.2
                                                  //| 13 -> 0.2.1.0
                                                  //| 17 -> 0.2.2.1
                                                  //| 7 -> 0.2.1
                                                  //| 3 -> 0.1
                                                  //| 8 -> 0.2.0
                                                  //| 19 -> 0.2.2.2
                                                  //| 4 -> 0.0
                                                  //| 15 -> 0.2.2.0
                                                  //| NextEdge: 1 -> polarity
                                                  //| ReversePolarity
                                                  //| InsertNode: and (Ref: 0.2.2)
                                                  //| 
                                                  //| NextNode: 0 -> UNKNOWN
                                                  //| 
                                                  //| NodesToGo:	List(21, 20, 7, 18, 8, 19, 9, 10, 15, 11, 1, 6, 2, 12, 3, 4, 5, 
                                                  //| 0)
                                                  //| Children:	List()
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> It, 2 -> is, 3 -> even, 4 -> more, 5 -> comp
                                                  //| licated, 6 -> in, 7 -> borderless, 8 -> cyber, 9 -> space, 10 -> where, 11 
                                                  //| -> there, 12 -> is, 13 -> -, 14 -> smoking, 15 -> gun, 17 -> fingerprints, 
                                                  //| 18 -> or, 19 -> footprints, 20 -> and, 21 -> -)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 
                                                  //| Output exceeds cutoff limit.
  }
  file.close
  println("Here")                                 //> Here
  val outputAdv = RunDagger.sampleTrajectory(s1, "C:\\AMR\\WangXueExpert_output.txt", new WangXueExpert)
                                                  //> outputAdv  : amr.Sentence = Sentence(It is even more complicated in borderl
                                                  //| ess cyber space where there is no smoking gun, fingerprints or footprints.,
                                                  //| 
                                                  //| NodeMap:	Map(0 -> ROOT, 3 -> even, 4 -> more, 5 -> complicated, 7 -> bord
                                                  //| erless, 8 -> cyber, 9 -> space, 13 -> -, 14 -> smoking, 15 -> gun, 17 -> fi
                                                  //| ngerprints, 19 -> footprints, 20 -> and, 21 -> -, 22 -> -, 23 -> -)
                                                  //| SpanMap:	Map(3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 7 -> (7,8), 8 -> (8,9), 
                                                  //| 9 -> (6,9), 13 -> (13,14), 14 -> (14,15), 15 -> (15,16), 17 -> (16,17), 19 
                                                  //| -> (18,19), 20 -> (12,16), 21 -> (16,17), 22 -> (18,19), 23 -> (15,16))
                                                  //| Edges:	Map((0,5) -> ROOT, (5,3) -> mod, (5,4) -> UNKNOWN, (5,9) -> UNKN
                                                  //| OWN, (5,20) -> UNKNOWN, (9,7) -> mod, (9,8) -> mod, (15,13) -> UNKNOWN, (15
                                                  //| ,14) -> ARG0-of, (15,19) -> UNKNOWN, (15,23) -> polarity, (17,21) -> polari
                                                  //| ty, (19,22) -> polarity, (20,15) -> opN, (20,17) -> opN)
                                                  //| InsertedNodes:	Map(20 -> 0.2.2, 21 -> 0.2.2.1.0, 22 -> 0.2.2.2.0, 23 ->
                                                  //|  0.2.2.0.0)
                                                  //| MergedNodes:	Map(9 -> List((6,in)), 20 -> List((12,is)))
                                                  //| SwappedArcs:	Set((15,20)),Some(AMRGraph(Map(8 -> cyber, 19 -> footpri
                                                  //| nts, 23 -> -, 4 -> more, 15 -> gun, 9 -> space, 22 -> -, 13 -> -, 5 -> comp
                                                  //| licated, 21 -> -, 17 -> fingerprints, 14 -> smoking, 0 -> ROOT, 20 -> and, 
                                                  //| 7 -> borderless, 3 -> even),Map(8 -> (8,9), 19 -> (18,19), 23 -> (15,16), 4
                                                  //|  -> (4,5), 15 -> (15,16), 9 -> (6,9), 22 -> (18,19), 13 -> (13,14), 5 -> (5
                                                  //| ,6), 21 -> (16,17), 17 -> (16,17), 14 -> (14,15), 20 -> (12,16), 7 -> (7,8)
                                                  //| , 3 -> (3,4)),Map((5,3) -> mod, (19,22) -> polarity, (15,23) -> polarity, (
                                                  //| 0,5) -> ROOT, (5,20) -> UNKNOWN, (15,19) -> UNKNOWN, (5,4) -> UNKNOWN, (9,8
                                                  //| ) -> mod, (9,7) -> mod, (15,13) -> UNKNOWN, (20,17) -> opN, (5,9) -> UNKNOW
                                                  //| N, (20,15) -> opN, (17,21) -> polarity, (15,14) -> ARG0-of))),Map(0 -> ROOT
                                                  //| , 5 -> 5, 14 -> 14, 20 -> 20, 21 -> 21, 9 -> 9, 13 -> 13, 17 -> 17, 22 -> 2
                                                  //| 2, 7 -> 7, 3 -> 3, 23 -> 23, 8 -> 8, 19 -> 19, 4 -> 4, 15 -> 15))
  outputAdv                                       //> res3: amr.Sentence = Sentence(It is even more complicated in borderless cyb
                                                  //| er space where there is no smoking gun, fingerprints or footprints.,
                                                  //| NodeMap:	Map(0 -> ROOT, 3 -> even, 4 -> more, 5 -> complicated, 7 -> bord
                                                  //| erless, 8 -> cyber, 9 -> space, 13 -> -, 14 -> smoking, 15 -> gun, 17 -> fi
                                                  //| ngerprints, 19 -> footprints, 20 -> and, 21 -> -, 22 -> -, 23 -> -)
                                                  //| SpanMap:	Map(3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 7 -> (7,8), 8 -> (8,9), 
                                                  //| 9 -> (6,9), 13 -> (13,14), 14 -> (14,15), 15 -> (15,16), 17 -> (16,17), 19 
                                                  //| -> (18,19), 20 -> (12,16), 21 -> (16,17), 22 -> (18,19), 23 -> (15,16))
                                                  //| Edges:	Map((0,5) -> ROOT, (5,3) -> mod, (5,4) -> UNKNOWN, (5,9) -> UNKN
                                                  //| OWN, (5,20) -> UNKNOWN, (9,7) -> mod, (9,8) -> mod, (15,13) -> UNKNOWN, (15
                                                  //| ,14) -> ARG0-of, (15,19) -> UNKNOWN, (15,23) -> polarity, (17,21) -> polari
                                                  //| ty, (19,22) -> polarity, (20,15) -> opN, (20,17) -> opN)
                                                  //| InsertedNodes:	Map(20 -> 0.2.2, 21 -> 0.2.2.1.0, 22 -> 0.2.2.2.0, 23 ->
                                                  //|  0.2.2.0.0)
                                                  //| MergedNodes:	Map(9 -> List((6,in)), 20 -> List((12,is)))
                                                  //| SwappedArcs:	Set((15,20)),Some(AMRGraph(Map(8 -> cyber, 19 -> footpri
                                                  //| nts, 23 -> -, 4 -> more, 15 -> gun, 9 -> space, 22 -> -, 13 -> -, 5 -> comp
                                                  //| licated, 21 -> -, 17 -> fingerprints, 14 -> smoking, 0 -> ROOT, 20 -> and, 
                                                  //| 7 -> borderless, 3 -> even),Map(8 -> (8,9), 19 -> (18,19), 23 -> (15,16), 4
                                                  //|  -> (4,5), 15 -> (15,16), 9 -> (6,9), 22 -> (18,19), 13 -> (13,14), 5 -> (5
                                                  //| ,6), 21 -> (16,17), 17 -> (16,17), 14 -> (14,15), 20 -> (12,16), 7 -> (7,8)
                                                  //| , 3 -> (3,4)),Map((5,3) -> mod, (19,22) -> polarity, (15,23) -> polarity, (
                                                  //| 0,5) -> ROOT, (5,20) -> UNKNOWN, (15,19) -> UNKNOWN, (5,4) -> UNKNOWN, (9,8
                                                  //| ) -> mod, (9,7) -> mod, (15,13) -> UNKNOWN, (20,17) -> opN, (5,9) -> UNKNOW
                                                  //| N, (20,15) -> opN, (17,21) -> polarity, (15,14) -> ARG0-of))),Map(0 -> ROOT
                                                  //| , 5 -> 5, 14 -> 14, 20 -> 20, 21 -> 21, 9 -> 9, 13 -> 13, 17 -> 17, 22 -> 2
                                                  //| 2, 7 -> 7, 3 -> 3, 23 -> 23, 8 -> 8, 19 -> 19, 4 -> 4, 15 -> 15))-
}