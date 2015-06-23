package amr

object WangXueStatetest {

val testSentence = Sentence("This is a test.")    //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/english
                                                  //| PCFG.ser.gz ... done [1.4 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.distsi
                                                  //| m.crf.ser.gz ... done [4.4 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.distsi
                                                  //| m.crf.ser.gz ... done [7.4 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dist
                                                  //| sim.crf.ser.gz ... done [2.5 sec].
                                                  //| testSentence  : amr.Sentence = Sentence(This is a test.,
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> This, 2 -> is, 3 -> a, 4 -> test)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5))
                                                  //| Edges:	Map((0,4) -> root, (4,1) -> nsubj, (4,2) -> cop, (4,3) -> det),N
                                                  //| one,Map())
val WXSystem = new WangXueTransitionSystem        //> WARNING: Found duplicate match for concept attack-01
                                                  //| WARNING: Found duplicate match for concept cyber
                                                  //| WARNING: Found duplicate match for concept attack-01
                                                  //| WARNING: Found duplicate match for concept cyber
                                                  //| WXSystem  : amr.WangXueTransitionSystem = amr.WangXueTransitionSystem@6d4e50
                                                  //| 11

val initialState = WXSystem.init(testSentence)    //> initialState  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(1, 2, 3, 4, 0)
                                                  //| Children:	List()
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> This, 2 -> is, 3 -> a, 4 -> test)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5))
                                                  //| Edges:	Map((0,4) -> root, (4,1) -> nsubj, (4,2) -> cop, (4,3) -> det)
                                                  //| Mappings:	
val testSentence2 = Sentence("The police want to arrest Michael Karras in London.")
                                                  //> testSentence2  : amr.Sentence = Sentence(The police want to arrest Michael K
                                                  //| arras in London.,
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> The, 2 -> police, 3 -> want, 4 -> to, 5 -> a
                                                  //| rrest, 6 -> Michael, 7 -> Karras, 8 -> in, 9 -> London)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10))
                                                  //| Edges:	Map((0,3) -> root, (2,1) -> det, (3,2) -> nsubj, (3,5) -> xcomp,
                                                  //|  (5,4) -> aux, (5,7) -> dobj, (7,6) -> nn, (7,8) -> prep, (8,9) -> pobj),Non
                                                  //| e,Map())

val initialState2 = WXSystem.init(testSentence2)  //> initialState2  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(9, 6, 8, 7, 4, 1, 5, 2, 3, 0)
                                                  //| Children:	List()
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> The, 2 -> police, 3 -> want, 4 -> to, 5 -> a
                                                  //| rrest, 6 -> Michael, 7 -> Karras, 8 -> in, 9 -> London)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10))
                                                  //| Edges:	Map((0,3) -> root, (2,1) -> det, (3,2) -> nsubj, (3,5) -> xcomp,
                                                  //|  (5,4) -> aux, (5,7) -> dobj, (7,6) -> nn, (7,8) -> prep, (8,9) -> pobj)
                                                  //| Mappings:	
}