package amr

object InsertAction {
val a = List(2, 7, 34, 0, 1)                      //> a  : List[Int] = List(2, 7, 34, 0, 1)
val b = List(7, 2, 67, 41, 102, 0)                //> b  : List[Int] = List(7, 2, 67, 41, 102, 0)
a intersect b                                     //> res0: List[Int] = List(2, 7, 0)
b intersect a                                     //> res1: List[Int] = List(7, 2, 0)
val testSentence = Sentence("This is a test.")    //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.1 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [4.1 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [2.1 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [3.8 sec].
                                                  //| testSentence  : amr.Sentence = Sentence(This is a test.,
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> This, 2 -> is, 3 -> a, 4 -> test)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5))
                                                  //| Edges:	Map((0,4) -> root, (4,1) -> nsubj, (4,2) -> cop, (4,3) -> det),N
                                                  //| one)
val WXSystem = new WangXueTransitionSystem        //> WXSystem  : amr.WangXueTransitionSystem = amr.WangXueTransitionSystem@4135c3
                                                  //| b

val initialState = WXSystem.init(testSentence)    //> initialState  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(1, 2, 3, 4, 0)
                                                  //| Children:	List()
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> This, 2 -> is, 3 -> a, 4 -> test)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5))
                                                  //| Edges:	Map((0,4) -> root, (4,1) -> nsubj, (4,2) -> cop, (4,3) -> det)
val nextState = Insert(3)(initialState)           //> WARNING: Found duplicate match for concept attack-01
                                                  //| WARNING: Found duplicate match for concept cyber
                                                  //| nextState  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(1, 5, 2, 3, 4, 0)
                                                  //| Children:	List()
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> This, 2 -> is, 3 -> a, 4 -> test, 5 -> name)
                                                  //| 
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (1,2))
                                                  //| Edges:	Map((0,4) -> root, (4,2) -> cop, (4,3) -> det, (4,5) -> nsubj, (
                                                  //| 5,1) -> name#)
}