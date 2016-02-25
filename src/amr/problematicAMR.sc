package amr

object problematicAMR {

  AMRGraph.textEncoding = "ISO8859-1"
  val testData = AMRGraph.importFile("C:\\AMR\\UTFQuote.txt")
                                                  //> testData  : IndexedSeq[(String, String, String)] = Vector((The Dream Defende
                                                  //| rs? occupation of the Capitol began July 16, three days after George Zimmerm
                                                  //| an was acquitted in the shooting death of Trayvon Martin, an unarmed teenage
                                                  //| r from Miami-Dade.,"",""))
  DependencyTree.preProcess(testData(0)._1)       //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [2.5 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [3.9 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [6.2 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [3.4 sec].
                                                  //| res0: List[String] = List(The, Dream, Defenders, ?, occupation, of, the, Cap
                                                  //| itol, began, July, 16, 3, days, after, George, Zimmerman, was, acquitted, in
                                                  //| , the, shooting, death, of, Trayvon, Martin, an, unarmed, teenager, from, Mi
                                                  //| ami-Dade)
  val expert = new WangXueExpert                  //> expert  : amr.WangXueExpert = amr.WangXueExpert@e4487af
  val expertSystem = WangXueTransitionSystem      //> expertSystem  : amr.WangXueTransitionSystem.type = amr.WangXueTransitionSyst
                                                  //| em$@7b02881e
  AMRGraph.setAligner("Pourdamghani")
  expertSystem.reentrance = true
  expertSystem.useCompositeNodes = false
  expertSystem.insertBelow = true
  ImportConcepts.initialise("C:\\AMR\\UTFQuote.txt")
  // ImportConcepts.relationStrin
  val s1 = Sentence(testData(0)._1, testData(0)._2, testData(0)._3)
                                                  //> s1  : amr.Sentence = 
                                                  //| The Dream Defenders? occupation of the Capitol began July 16, three days aft
                                                  //| er George Zimmerman was acquitted in the shooting death of Trayvon Martin, a
                                                  //| n unarmed teenager from Miami-Dade.
                                                  //| 
                                                  //| NodeMap:	Map(1 -> The, 2 -> Dream, 3 -> Defenders, 4 -> ?, 5 -> occupatio
                                                  //| n, 6 -> of, 7 -> the, 8 -> Capitol, 9 -> began, 10 -> 7, 11 -> 16, 12 -> 3, 
                                                  //| 13 -> days, 14 -> after, 15 -> George, 16 -> Zimmerman, 17 -> was, 18 -> acq
                                                  //| uitted, 19 -> in, 20 -> the, 21 -> shooting, 22 -> death, 23 -> of, 24 -> Tr
                                                  //| ayvon, 25 -> Martin, 26 -> an, 27 -> unarmed, 28 -> teenager, 29 -> from, 30
                                                  //|  -> Miami-Dade)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,12
                                                  //| ), 12 -> (12,13), 13 -> (13,14), 14 -> (14,15), 15 -> (15,16), 16 -> (16,17)
                                                  //| , 17 -> (17,18), 18 -> (18,19), 19 -> (19,20), 20 -> (20,21), 21 -> (21,22),
                                                  //|  22 -> (22,23), 23 -> (23,24), 24 -> (24,25), 25 -> (25,26), 26 -> (26,27), 
                                                  //| 27 -> (27,28), 28 -> (28,29), 29 -> (29,30), 30 -> (30,31))
                                                  //| Edges:	Map((3,1) -> det, (3,2) -> nn, (3,4) -> possessive, (5,3) -> pos
                                                  //| s, (5,6) -> prep, (6,8) -> pobj, (8,7) -> det, (9,5) -> nsubj, (9,10) -> tmo
                                                  //| d, (9,18) -> advcl, (10,11) -> num, (13,12) -> num, (16,15) -> nn, (18,13) -
                                                  //| > dep, (18,14) -> mark, (18,16) -> nsubjpass, (18,17) -> auxpass, (18,19) ->
                                                  //|  prep, (18,29) -> prep, (19,22) -> pobj, (22,20) -> det, (22,21) -> amod, (2
                                                  //| 2,23) -> prep, (23,25) -> pobj, (25,24) -> nn, (25,28) -> dep, (28,26) -> de
                                                  //| t, (28,27) -> amod, (29,30) -> pobj)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map()
                                                  //| PartsOfSpeech:	Map(5 -> NN, 10 -> NNP, 24 -> NNP, 25 -> NNP, 14 -> IN, 
                                                  //| 20 -> DT, 29 -> IN, 1 -> DT, 6 -> IN, 28 -> NN, 21 -> JJ, 9 -> VBD, 13 -> NN
                                                  //| S, 2 -> NN, 17 -> VBD, 22 -> NN, 27 -> JJ, 12 -> CD, 7 -> DT, 3 -> NNS, 18 -
                                                  //| > VBN, 16 -> NNP, 11 -> CD, 26 -> DT, 23 -> IN, 8 -> NNP, 30 -> NNP, 19 -> I
                                                  //| N, 4 -> POS, 15 -> NNP)
                                                  //| DependencyLabels:	Map(5 -> nsubj, 10 -> tmod, 24 -> nn, 25 -> pobj, 14 -> 
                                                  //| mark, 20 -> det, 29 -> prep, 1 -> det, 6 -> prep, 28 -> dep, 21 -> amod, 13 
                                                  //| -> dep, 2 -> nn, 17 -> auxpass, 22 -> pobj, 27 -> amod, 12 -> num, 7 -> det,
                                                  //|  3 -> poss, 18 -> advcl, 16 -> nsubjpass, 11 -> num, 26 -> det, 23 -> prep, 
                                                  //| 8 -> pobj, 30 -> pobj, 19 -> prep, 4 -> possessive, 15 -> nn)
                                                  //| No AMR
                                                  //| Map()
                                                  //| 
  var state = expertSystem.init(s1)               //> state  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(26, 27, 28, 24, 25, 21, 23, 20, 7, 22, 12, 30, 15, 4, 1, 2,
                                                  //|  8, 11, 19, 13, 17, 14, 29, 16, 3, 6, 10, 18, 5, 9)
                                                  //| Children:	List()
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(1 -> The, 2 -> Dream, 3 -> Defenders, 4 -> ?, 5 -> occupatio
                                                  //| n, 6 -> of, 7 -> the, 8 -> Capitol, 9 -> began, 10 -> 7, 11 -> 16, 12 -> 3, 
                                                  //| 13 -> days, 14 -> after, 15 -> George, 16 -> Zimmerman, 17 -> was, 18 -> acq
                                                  //| uitted, 19 -> in, 20 -> the, 21 -> shooting, 22 -> death, 23 -> of, 24 -> Tr
                                                  //| ayvon, 25 -> Martin, 26 -> an, 27 -> unarmed, 28 -> teenager, 29 -> from, 30
                                                  //|  -> Miami-Dade)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,12
                                                  //| ), 12 -> (12,13), 13 -> (13,14), 14 -> (14,15), 15 -> (15,16), 16 -> (16,17)
                                                  //| , 17 -> (17,18), 18 -> (18,19), 19 -> (19,20), 20 -> (20,21), 21 -> (21,22),
                                                  //|  22 -> (22,23), 23 -> (23,24), 24 -> (24,25), 25 -> (25,26), 26 -> (26,27), 
                                                  //| 27 -> (27,28), 28 -> (28,29), 29 -> (29,30), 30 -> (30,31))
                                                  //| Edges:	Map((3,1) -> det, (3,2) -> nn, (3,4) -> possessive, (5,3) -> pos
                                                  //| s, (5,6) -> prep, (6,8) -> pobj, (8,7) -> det, (9,5) -> nsubj, (9,10) -> tmo
                                                  //| d, (9,18) -> advcl, (10,11) -> num, (13,12) -> num, (16,15) -> nn, (18,13) -
                                                  //| > dep, (18,14) -> mark, (18,16) -> nsubjpass, (18,17) -> auxpass, (18,19) ->
                                                  //|  prep, (18,29) -> prep, (19,22) -> pobj, (22,20) -> det, (22,21) -> amod, (2
                                                  //| 2,23) -> prep, (23,25) -> pobj, (25,24) -> nn, (25,28) -> dep, (28,26) -> de
                                                  //| t, (28,27) -> amod, (29,30) -> pobj)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map()
                                                  //| PartsOfSpeech:	Map(5 -> NN, 10 -> NNP, 24 -> NNP, 25 -> NNP, 14 -> IN, 
                                                  //| 20 -> DT, 29 -> IN, 1 -> DT, 6 -> IN, 28 -> NN, 21 -> JJ, 9 -> VBD, 13 -> NN
                                                  //| S, 2 -> NN, 17 -> VBD, 22 -> NN, 27 -> JJ, 12 -> CD, 7 -> DT, 3 -> NNS, 18 -
                                                  //| > VBN, 16 -> NNP, 11 -> CD, 26 -> DT, 23 -> IN, 8 -> NNP, 30 -> NNP, 19 -> I
                                                  //| N, 4 -> POS, 15 -> NNP)
                                                  //| DependencyLabels:	Map(5 -> nsubj, 10 -> tmod, 24 -> nn, 25 -> pobj, 14 -> 
                                                  //| mark, 20 -> det, 29 -> prep, 1 -> det, 6 -> prep, 28 -> dep, 21 -> amod, 13 
                                                  //| -> dep, 2 -> nn, 17 -> auxpass, 22 -> pobj, 27 -> amod, 12 -> num, 7 -> det,
                                                  //|  3 -> poss, 18 -> advcl, 16 -> nsubjpass, 11 -> num, 26 -> det, 23 -> prep, 
                                                  //| 8 -> pobj, 30 -> pobj, 19 -> prep, 4 -> possessive, 15 -> nn)
                                                  //| Mappings:	
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
  s1.positionToAMR                                //> res1: Map[Int,String] = Map()
  s1.amr.get.nodes                                //> java.util.NoSuchElementException: None.get
                                                  //| 	at scala.None$.get(Option.scala:347)
                                                  //| 	at scala.None$.get(Option.scala:345)
                                                  //| 	at amr.problematicAMR$$anonfun$main$1.apply$mcV$sp(amr.problematicAMR.sc
                                                  //| ala:21)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$$anonfun$$exe
                                                  //| cute$1.apply$mcV$sp(WorksheetSupport.scala:76)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.redirected(W
                                                  //| orksheetSupport.scala:65)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.$execute(Wor
                                                  //| ksheetSupport.scala:75)
                                                  //| 	at amr.problematicAMR$.main(amr.problematicAMR.scala:3)
                                                  //| 	at amr.problematicAMR.main(amr.problematicAMR.scala)
  s1.amr.get.arcs
  s1.dependencyTree.nodeLemmas
  ImportConcepts.conceptStrings foreach println
  ImportConcepts.conceptsPerLemma foreach println
  import java.io._
  val file = new FileWriter("C://AMR//Expert_output.txt")
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
    }
  }
  file.close

  AMROutput.convertToString(state.currentGraph.toAMR)
  AMROutput.convertToString(Smatch.reduceAMR(state.currentGraph.toAMR))
}