package amr

object SentenceTest {
  AMRGraph.textEncoding = "windows-1252"
 val testData = AMRGraph.importFile("C:\\AMR\\UTFQuote.txt")
                                                  //> testData  : IndexedSeq[(String, String, String)] = Vector((The Dream Defende
                                                  //| rs’ occupation of the Capitol began July 16, three days after George Zimmerm
                                                  //| an was acquitted in the shooting death of Trayvon Martin, an unarmed teenage
                                                  //| r from Miami-Dade.,"",""))
	val s = Sentence(testData(0)._1)          //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.4 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [4.0 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [3.2 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [2.6 sec].
                                                  //| s  : amr.Sentence = 
                                                  //| The Dream Defenders’ occupation of the Capitol began July 16, three days aft
                                                  //| er George Zimmerman was acquitted in the shooting death of Trayvon Martin, a
                                                  //| n unarmed teenager from Miami-Dade.
                                                  //| 
                                                  //| NodeMap:	Map(1 -> The, 2 -> Dream, 3 -> Defenders, 4 -> ’, 5 -> occupatio
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
                                                  //| 20 -> DT, 29 -> IN, 1 -> DT, 6 -> IN, 28 -> NN, 21 -> JJ, 9 
                                                  //| Output exceeds cutoff limit.

}