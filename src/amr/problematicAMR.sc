package amr

object problematicAMR {
 // val testData = AMRGraph.importFile("C:\\AMR\\problemticAM.txt") - Kuwolsan that created loop
 
// val testData = AMRGraph.importFile("C:\\AMR\\AMRInstance4.txt") // Nato Considers...
// val testData = AMRGraph.importFile("C:\\AMR\\simpleDate.tx")
val testData = AMRGraph.importFile("C:\\AMR\\Medvedev2.txt")
                                                  //> testData  : IndexedSeq[(String, String)] = Vector((Russian Federation Presid
                                                  //| ent Dmitry Medvedev promised on May 15, 2008 to provide funding for Russia's
                                                  //|  nuclear missile program.,(p2 / promise-01 :ARG0 (p / person :name (n2 / nam
                                                  //| e :op1 "Dmitry" :op2 "Medvedev") :ARG0-of (h / have-org-role-91 :ARG1 (c / c
                                                  //| ountry :name (n / name :op1 "Russian" :op2 "Federation")) :ARG2 (p3 / presid
                                                  //| ent))) :ARG1 (p4 / provide-01 :ARG0 p :ARG1 (f / fund-01 :ARG0 p :ARG1 (p5 /
                                                  //|  program :mod (m / missile :mod (n3 / nucleus)) :poss c))) :time (d / date-e
                                                  //| ntity :month 5 :day 15 :year 2008))))
  val expert = new WangXueExpert                  //> expert  : amr.WangXueExpert = amr.WangXueExpert@675d3402
  val expertSystem = WangXueTransitionSystem      //> expertSystem  : amr.WangXueTransitionSystem.type = amr.WangXueTransitionSyst
                                                  //| em$@28d25987
  expertSystem.reentrance = true
  ImportConcepts.initialise("C:\\AMR\\Medvedev2.txt")
// ImportConcepts.relationStrings
  val s1 = Sentence(testData(0)._1, testData(0)._2)
                                                  //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.1 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [4.3 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [2.1 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [3.9 sec].
                                                  //| s1  : amr.Sentence = Sentence(Russian Federation President Dmitry Medvedev p
                                                  //| romised on May 15, 2008 to provide funding for Russia's nuclear missile prog
                                                  //| ram.,
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> Russian, 2 -> Federation, 3 -> President, 4 
                                                  //| -> Dmitry, 5 -> Medvedev, 6 -> promised, 7 -> on, 8 -> 5, 9 -> 15, 11 -> 200
                                                  //| 8, 12 -> to, 13 -> provide, 14 -> funding, 15 -> for, 16 -> Russia, 17 -> 's
                                                  //| , 18 -> nuclear, 19 -> missile, 20 -> program)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 11 -> (10,11), 12 -> (11,12
                                                  //| ), 13 -> (12,13), 14 -> (13,14), 15 -> (14,15), 16 -> (15,16), 17 -> (16,17)
                                                  //| , 18 -> (17,18), 19 -> (18,19), 20 -> (19,20))
                                                  //| Edges:	Map((0,6) -> root, (5,1) -> nn, (5,2) -> nn, (5,3) -> nn, (5,4) 
                                                  //| -> nn, (6,5) -> nsubj, (6,7) -> prep, (6,13) -> xcomp, (7,8) -> pobj, (8,9) 
                                                  //| -> num, (8,11) -> num, (13,12) -> aux, (13,14) -> dobj, (14,15) -> prep, (15
                                                  //| ,20) -> pobj, (16,17) -> possessive, (20,16) -> poss, (20,18) -> amod, (20,1
                                                  //| 9) -> nn)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	List(),Some(AMRGraph(Map(0.1 -> provide-01, 0.0.1.0.0.0 
                                                  //| -> "Russian", 0.0.0 -> name, 0.1.0 -> fund-01, 0.0.1.0 -> country, 0.2.1 -> 
                                                  //| 15, 0.1.0.0.0 -> missile, 0.0 -> person, 0.0.0.0 -> "Dmitry", 0.1.0.0.0.0 ->
                                                  //|  nucleus, 0.2.0 -> 5, 0.0.1.1 -> president, 0.0.1 -> have-org-role-91, 0.1.0
                                                  //| .0 -> program, 0.0.1.0.0 -> name, 0 -> promise-01, 0.0.0.1 -> "Medvedev", 0.
                                                  //| 2 -> date-entity, 0.0.1.0.0.1 -> "Federation", 0.2.2 -> 2008, ROOT -> ROOT),
                                                  //| Map(0.1 -> (12,13), 0.0.1.0.0.0 -> (1,3), 0.0.0 -> (4,6), 0.1.0 -> (13,14), 
                                                  //| 0.0.1.0 -> (1,3), 0.2.1 -> (8,11), 0.1.0.0.0 -> (18,19), 0.0 -> (4,6), 0.0.0
                                                  //| .0 -> (4,6), 0.1.0.0.0.0 -> (17,18), 0.2.0 -> (8,11), 0.0.1.1 -> (3,4), 0
                                                  //| Output exceeds cutoff limit.
  var state = expertSystem.init(s1)               //> state  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(17, 16, 18, 19, 20, 9, 11, 15, 8, 2, 1, 4, 3, 12, 14, 7, 5,
                                                  //|  13, 6, 0)
                                                  //| Children:	List()
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> Russian, 2 -> Federation, 3 -> President, 4 
                                                  //| -> Dmitry, 5 -> Medvedev, 6 -> promised, 7 -> on, 8 -> 5, 9 -> 15, 11 -> 200
                                                  //| 8, 12 -> to, 13 -> provide, 14 -> funding, 15 -> for, 16 -> Russia, 17 -> 's
                                                  //| , 18 -> nuclear, 19 -> missile, 20 -> program)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 11 -> (10,11), 12 -> (11,12
                                                  //| ), 13 -> (12,13), 14 -> (13,14), 15 -> (14,15), 16 -> (15,16), 17 -> (16,17)
                                                  //| , 18 -> (17,18), 19 -> (18,19), 20 -> (19,20))
                                                  //| Edges:	Map((0,6) -> root, (5,1) -> nn, (5,2) -> nn, (5,3) -> nn, (5,4) 
                                                  //| -> nn, (6,5) -> nsubj, (6,7) -> prep, (6,13) -> xcomp, (7,8) -> pobj, (8,9) 
                                                  //| -> num, (8,11) -> num, (13,12) -> aux, (13,14) -> dobj, (14,15) -> prep, (15
                                                  //| ,20) -> pobj, (16,17) -> possessive, (20,16) -> poss, (20,18) -> amod, (20,1
                                                  //| 9) -> nn)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	List()
                                                  //| Mappings:	0 -> ROOT
                                                  //| 5 -> 0.0.0.1
                                                  //| 14 -> 0.1.0
                                                  //| 20 -> 0.1.0.0
                                                  //| 1 -> 0.0.1.0.0.0
                                                  //| 6 -> 0
                                                  //| 9 -> 0.2.1
                                                  //| 13 -> 0.1
                                                  //| 2 -> 0.0.1.0.0.1
                                                  //| 3 -> 0.0.1.1
                                                  //| 18 -> 0.1.0.0.0.0
                                                  //| 11 -> 0.2.2
                                                  //| 8 -> 0.2.0
                                                  //| 19 -> 0.1.0.0.0
                                                  //| 4 -> 0.0.0.0
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
  s1.positionToAMR                                //> res0: Map[Int,String] = Map(0 -> ROOT, 5 -> 0.0.0.1, 14 -> 0.1.0, 20 -> 0.1.
                                                  //| 0.0, 1 -> 0.0.1.0.0.0, 6 -> 0, 9 -> 0.2.1, 13 -> 0.1, 2 -> 0.0.1.0.0.1, 3 ->
                                                  //|  0.0.1.1, 18 -> 0.1.0.0.0.0, 11 -> 0.2.2, 8 -> 0.2.0, 19 -> 0.1.0.0.0, 4 -> 
                                                  //| 0.0.0.0)
  s1.amr.get.nodes                                //> res1: Map[String,String] = Map(0.1 -> provide-01, 0.0.1.0.0.0 -> "Russian", 
                                                  //| 0.0.0 -> name, 0.1.0 -> fund-01, 0.0.1.0 -> country, 0.2.1 -> 15, 0.1.0.0.0 
                                                  //| -> missile, 0.0 -> person, 0.0.0.0 -> "Dmitry", 0.1.0.0.0.0 -> nucleus, 0.2.
                                                  //| 0 -> 5, 0.0.1.1 -> president, 0.0.1 -> have-org-role-91, 0.1.0.0 -> program,
                                                  //|  0.0.1.0.0 -> name, 0 -> promise-01, 0.0.0.1 -> "Medvedev", 0.2 -> date-enti
                                                  //| ty, 0.0.1.0.0.1 -> "Federation", 0.2.2 -> 2008, ROOT -> ROOT)
  s1.amr.get.arcs                                 //> res2: Map[(String, String),String] = Map((0.0,0.0.0) -> name, (ROOT,0) -> RO
                                                  //| OT, (0.0.1.0.0,0.0.1.0.0.0) -> opN, (0.1.0.0,0.0.1.0) -> poss, (0.0.0,0.0.0.
                                                  //| 0) -> opN, (0.1,0.1.0) -> ARG1, (0.1.0.0.0,0.1.0.0.0.0) -> mod, (0.0.1.0.0,0
                                                  //| .0.1.0.0.1) -> opN, (0.1.0,0.1.0.0) -> ARG1, (0,0.2) -> time, (0,0.1) -> ARG
                                                  //| 1, (0.2,0.2.0) -> month, (0.0.0,0.0.0.1) -> opN, (0.0.1,0.0.1.1) -> ARG2, (0
                                                  //| .1.0.0,0.1.0.0.0) -> mod, (0.2,0.2.1) -> day, (0.1,0.0) -> ARG0, (0,0.0) -> 
                                                  //| ARG0, (0.0.1,0.0.1.0) -> ARG1, (0.1.0,0.0) -> ARG0, (0.0.1.0,0.0.1.0.0) -> n
                                                  //| ame, (0.0,0.0.1) -> ARG0-of, (0.2,0.2.2) -> year)
  import java.io._
  val file = new FileWriter("C://AMR//i4_output.txt")
                                                  //> file  : java.io.FileWriter = java.io.FileWriter@16150369
  for (i <- 0 to 300) {
    if (!expertSystem.isTerminal(state)) {
      stateHistory(i) = state
      action(i) = expert.chooseTransition(s1, state)
      file.write(state.toString + "\n")
      file.write(action(i) + " : " + (state.nodesToProcess match { case Nil => ""; case _ => state.nodesToProcess.head }) + " -> " + (
        state.childrenToProcess match { case Nil => ""; case _ => state.childrenToProcess.head }) + "\n")

      file.flush
      state = action(i)(state)
    }                                             //> 0.1.0.0 -> 0.1.0.0.0.0
                                                  //| 0.1.0.0 -> 0.1.0.0.0
                                                  //| relationText = mod
                                                  //| Loading Relations
                                                  //| relationIndex = 3
                                                  //| 0.1.0.0.0 -> 0.1.0.0.0.0
                                                  //| relationText = mod
                                                  //| relationIndex = 3
                                                  //| 0.2 -> 0.2.1
                                                  //| relationText = day
                                                  //| relationIndex = 12
                                                  //| 0.2.0 -> 0.2.2
                                                  //| 0.2.0 -> 0.2
                                                  //| 0.2 -> 0.2.2
                                                  //| relationText = year
                                                  //| relationIndex = 5
                                                  //| 0.2 -> 0.2.0
                                                  //| relationText = month
                                                  //| relationIndex = 13
                                                  //| 0.0.1.0.0 -> 0.0.1.0.0.1
                                                  //| relationText = opN
                                                  //| relationIndex = 7
                                                  //| 0.0.1.0 -> 0.0.1.0.0
                                                  //| relationText = name
                                                  //| relationIndex = 1
                                                  //| 0.0.0 -> 0.0.0.0
                                                  //| relationText = opN
                                                  //| relationIndex = 7
                                                  //| 0.0.1 -> 0.0.1.1
                                                  //| relationText = ARG2
                                                  //| relationIndex = 6
                                                  //| 0.0 -> 0.0.0
                                                  //| relationText = name
                                                  //| relationIndex = 1
                                                  //| 0.1.0 -> 0.1.0.0
                                                  //| relationText = ARG1
                                                  //| relationIndex = 4
                                                  //| 0.0.0.1 -> 0.0.1.0.0.0
                                                  //| 0.0.0.1 -> 0.0.1.0
                                                  //| 0.0.0.1 -> 0.0.1
                                                  //| 0.0.0.1 -> 0.0
                                                  //| 0.0.1.0.0 -> 0.0.1.0.0.0
                                                  //| relationText = opN
                                                  //| relationIndex = 7
                                                  //| 0.1.0.0 -> 0.0.1.0
                                                  //| relationText = poss
                                                  //| relationIndex = 8
                                                  //| 0.0 -> 0.0.1
                                                  //| relationText = ARG0-of
                                                  //| relationIndex = 2
                                                  //| 0.1 -> 0.0
                                                  //| relationText = ARG0
                                                  //| relationIndex = 11
                                                  //| 0.1 -> 0.1.0
                                                  //| relationText = ARG1
                                                  //| relationIndex = 4
                                                  //| 0 -> 0.0.0.1
                                                  //| 0 -> 0.1
                                                  //| relationText = ARG1
                                                  //| relationIndex = 4
                                                  //| 0 -> 0.2
                                                  //| relationText = time
                                                  //| relationIndex = 9
                                                  //| 0.0.0 -> 0.0.0.1
                                                  //| relationText = opN
                                                  //| relationIndex = 7
                                                  //| ROOT -> 0
                                                  //| relationText = ROOT
                                                  //| relationIndex = 10
                                                  //| 0.1.0 -> 0.0
                                                  //| relationText = ARG0
                                                  //| relationIndex = 11
                                                  //| 0 -> 0.0
                                                  //| relationText = ARG0
                                                  //| relationIndex = 11
  }
  file.close
  val outputAdv = RunDagger.sampleTrajectory(s1, "C:\\AMR\\WangXueExpert_output.txt", new WangXueExpert)
                                                  //> 0.1.0.0 -> 0.1.0.0.0.0
                                                  //| 0.1.0.0 -> 0.1.0.0.0
                                                  //| relationText = mod
                                                  //| relationIndex = 3
                                                  //| 0.1.0.0.0 -> 0.1.0.0.0.0
                                                  //| relationText = mod
                                                  //| relationIndex = 3
                                                  //| 0.2 -> 0.2.1
                                                  //| relationText = day
                                                  //| relationIndex = 12
                                                  //| 0.2.0 -> 0.2.2
                                                  //| 0.2.0 -> 0.2
                                                  //| 0.2 -> 0.2.2
                                                  //| relationText = year
                                                  //| relationIndex = 5
                                                  //| 0.2 -> 0.2.0
                                                  //| relationText = month
                                                  //| relationIndex = 13
                                                  //| 0.0.1.0.0 -> 0.0.1.0.0.1
                                                  //| relationText = opN
                                                  //| relationIndex = 7
                                                  //| 0.0.1.0 -> 0.0.1.0.0
                                                  //| relationText = name
                                                  //| relationIndex = 1
                                                  //| 0.0.0 -> 0.0.0.0
                                                  //| relationText = opN
                                                  //| relationIndex = 7
                                                  //| 0.0.1 -> 0.0.1.1
                                                  //| relationText = ARG2
                                                  //| relationIndex = 6
                                                  //| 0.0 -> 0.0.0
                                                  //| relationText = name
                                                  //| relationIndex = 1
                                                  //| 0.1.0 -> 0.1.0.0
                                                  //| relationText = ARG1
                                                  //| relationIndex = 4
                                                  //| 0.0.0.1 -> 0.0.1.0.0.0
                                                  //| 0.0.0.1 -> 0.0.1.0
                                                  //| 0.0.0.1 -> 0.0.1
                                                  //| 0.0.0.1 -> 0.0
                                                  //| 0.0.1.0.0 -> 0.0.1.0.0.0
                                                  //| relationText = opN
                                                  //| relationIndex = 7
                                                  //| 0.1.0.0 -> 0.0.1.0
                                                  //| relationText = poss
                                                  //| relationIndex = 8
                                                  //| 0.0 -> 0.0.1
                                                  //| relationText = ARG0-of
                                                  //| relationIndex = 2
                                                  //| 0.1 -> 0.0
                                                  //| relationText = ARG0
                                                  //| relationIndex = 11
                                                  //| 0.1 -> 0.1.0
                                                  //| relationText = ARG1
                                                  //| relationIndex = 4
                                                  //| 0 -> 0.0.0.1
                                                  //| 0 -> 0.1
                                                  //| relationText = ARG1
                                                  //| relationIndex = 4
                                                  //| 0 -> 0.2
                                                  //| relationText = time
                                                  //| relationIndex = 9
                                                  //| 0.0.0 -> 0.0.0.1
                                                  //| relationText = opN
                                                  //| relationIndex = 7
                                                  //| ROOT -> 0
                                                  //| relationText = ROOT
                                                  //| relationIndex = 10
                                                  //| 0.1.0 -> 0.0
                                                  //| relationText = ARG0
                                                  //| relationIndex = 11
                                                  //| 0 -> 0.0
                                                  //| relationText = ARG0
                                                  //| relationIndex = 11
                                                  //| outputAdv  : amr.Sentence = Sentence(Russian Federation President Dmitry Me
                                                  //| dvedev promised on May 15, 2008 to provide funding for Russia's nuclear mis
                                                  //| sile program.,
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> Russian, 2 -> Federation, 3 -> president, 4 
                                                  //| -> Dmitry, 5 -> Medvedev, 6 -> promise-01, 8 -> 5, 9 -> 15, 11 -> 2008, 13 
                                                  //| -> provide-01, 14 -> fund-01, 18 -> nucleus, 19 -> missile, 20 -> program, 
                                                  //| 21 -> date-entity, 22 -> name, 23 -> name, 24 -> have-org-role-91, 25 -> co
                                                  //| untry, 26 -> person)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 8 -> (8,9), 9 -> (9,10), 11 -> (10,11), 13 -> (12,13), 14 -> (1
                                                  //| 3,14), 18 -> (17,18), 19 -> (18,19), 20 -
                                                  //| Output exceeds cutoff limit.
  outputAdv                                       //> res3: amr.Sentence = Sentence(Russian Federation President Dmitry Medvedev 
                                                  //| promised on May 15, 2008 to provide funding for Russia's nuclear missile pr
                                                  //| ogram.,
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> Russian, 2 -> Federation, 3 -> president, 4 
                                                  //| -> Dmitry, 5 -> Medvedev, 6 -> promise-01, 8 -> 5, 9 -> 15, 11 -> 2008, 13 
                                                  //| -> provide-01, 14 -> fund-01, 18 -> nucleus, 19 -> missile, 20 -> program, 
                                                  //| 21 -> date-entity, 22 -> name, 23 -> name, 24 -> have-org-role-91, 25 -> co
                                                  //| untry, 26 -> person)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 8 -> (8,9), 9 -> (9,10), 11 -> (10,11), 13 -> (12,13), 14 -> (1
                                                  //| 3,14), 18 -> (17,18), 19 -> (18,19), 20 -> (14,19), 21 -> (7,9), 22 -> (2,3
                                                  //| ), 23 -> (4,5), 24 -> (3,4), 25 -> (2,3), 26 -> (4,5))
                                                  //| Edges:	Map((0,6) -> ROOT, (6,13) -> ARG1, (6,21) -> time, (6,26) -> ARG
                                                  //| 0, (13,14) -> ARG1, (13,26) -> ARG0, (14,20) -> ARG1, (14,26) -> ARG0, (19,
                                                  //| 18) -> mod, (20,19) -> mod, (20,25) -> poss, (21,8) -> month, (21,9) -> day
                                                  //| , (21,11) -> year, (22,1) -> opN, (22,2) -> opN, (23,4) -> opN, (23,5) -> o
                                                  //| pN, (24,3) -> ARG2, (25,22) -> name, (26,23) -> name, (26,24) -> ARG0-of)
                                                  //| InsertedNodes:	Map(24 -> 0.0.1, 25 -> 0.0.1.0, 21 -> 0.2, 22 -> 0.0.1.0
                                                  //| .0, 26 -> 0.0, 23 -> 0.0.0)
                                                  //| MergedNodes:	Map(20 -> List((15,for)), 21 -> List((7,on)))
                                                  //| SwappedArcs:	Set((8,21))
                                                  //| DeletedNodes:	List(12, 16, 17),Some(AMRGraph(Map(8 -> 5, 19 -> missile
                                                  //| , 23 -> name, 4 -> "Dmitry", 11 -> 2008, 9 -> 15, 22 -> name, 26 -> person,
                                                  //|  13 -> provide-01, 24 -> have-org-role-91, 5 -> "Medvedev", 21 -> date-enti
                                                  //| ty, 6 -> promise-01, 1 -> "Russian", 25 -> country, 14 -> fund-01, 0 -> ROO
                                                  //| T, 20 -> program, 2 -> "Federation", 18 -> nucleus, 3 -> president),Map(8 -
                                                  //| > (8,9), 19 -> (18,19), 23 -> (4,5), 4 -> (4,5), 11 -> (10,11), 9 -> (9,10)
                                                  //| , 22 -> (2,3), 26 -> (4,5), 13 -> (12,13), 24 -> (3,4), 5 -> (5,6), 21 -> (
                                                  //| 7,9), 6 -> (6,7), 1 -> (1,2), 25 -> (2,3), 14 -> (13,14), 20 -> (14,19), 2 
                                                  //| -> (2,3), 18 -> (17,18), 3 -> (3,4)),Map((14,26) -> ARG0, (24,3) -> ARG2, (
                                                  //| 26,24) -> ARG0-of, (13,26) -> ARG0, (6,21) -> time, (6,13) -> ARG1, (26,23)
                                                  //|  -> name, (19,18) -> mod, (25,22) -> name, (23,5) -> opN, (22,2) -> opN, (6
                                                  //| ,26) -> ARG0, (14,20) -> ARG1, (21,8) -> month, (21,9) -> day, (20,25) -> p
                                                  //| oss, (20,19) -> mod, (22,1) -> opN, (0,6) -> ROOT, (13,14) -> ARG1, (23,4) 
                                                  //| -> opN, (21,11) -> y
                                                  //| Output exceeds cutoff limit.
}