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
                                                  //| hPCFG.ser.gz ... done [1.0 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [4.1 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [2.0 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [3.8 sec].
                                                  //| testSentence  : amr.Sentence = Sentence(This is a test.,
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> This, 2 -> is, 3 -> a, 4 -> test)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5))
                                                  //| Edges:	Map((0,4) -> root, (4,1) -> nsubj, (4,2) -> cop, (4,3) -> det)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNode:	Map(),None,Map())
val WXSystem = new WangXueTransitionSystem        //> WARNING: Found duplicate match for concept attack-01
                                                  //| WARNING: Found duplicate match for concept cyber
                                                  //| WARNING: Found duplicate match for concept attack-01
                                                  //| WARNING: Found duplicate match for concept cyber
                                                  //| WXSystem  : amr.WangXueTransitionSystem = amr.WangXueTransitionSystem@71238f
                                                  //| c2

val initialState = WXSystem.init(testSentence)    //> initialState  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(1, 2, 3, 4, 0)
                                                  //| Children:	List()
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> This, 2 -> is, 3 -> a, 4 -> test)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5))
                                                  //| Edges:	Map((0,4) -> root, (4,1) -> nsubj, (4,2) -> cop, (4,3) -> det)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNode:	Map()
                                                  //| Mappings:	
val nextState = Insert(3)(initialState)           //> nextState  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(1, 5, 2, 3, 4, 0)
                                                  //| Children:	List()
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> This, 2 -> is, 3 -> a, 4 -> test, 5 -> name)
                                                  //| 
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (1,2))
                                                  //| Edges:	Map((0,4) -> root, (4,2) -> cop, (4,3) -> det, (4,5) -> nsubj, (
                                                  //| 5,1) -> name#)
                                                  //| InsertedNodes:	Map(5 -> )
                                                  //| MergedNode:	Map()
                                                  //| Mappings:	5 -> 
                                                  //| 
val moveUp = NextNode(6)(nextState)               //> moveUp  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(5, 2, 3, 4, 0)
                                                  //| Children:	List(1)
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> threaten-01, 2 -> is, 3 -> a, 4 -> test, 5 -
                                                  //| > name)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (1,2))
                                                  //| Edges:	Map((0,4) -> root, (4,2) -> cop, (4,3) -> det, (4,5) -> nsubj, (
                                                  //| 5,1) -> name#)
                                                  //| InsertedNodes:	Map(5 -> )
                                                  //| MergedNode:	Map()
                                                  //| Mappings:	5 -> 
                                                  //| 
val reattach = Reattach(2)(moveUp)                //> reattach  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(5, 2, 3, 4, 0)
                                                  //| Children:	List()
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> threaten-01, 2 -> is, 3 -> a, 4 -> test, 5 -
                                                  //| > name)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (1,2))
                                                  //| Edges:	Map((0,4) -> root, (2,1) -> name#, (4,2) -> cop, (4,3) -> det, (
                                                  //| 4,5) -> nsubj)
                                                  //| InsertedNodes:	Map(5 -> )
                                                  //| MergedNode:	Map()
                                                  //| Mappings:	5 -> 
                                                  //| 
val next2 = NextNode(6)(reattach)                 //> next2  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(2, 3, 4, 0)
                                                  //| Children:	List(1)
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> threaten-01, 2 -> is, 3 -> a, 4 -> test, 5 -
                                                  //| > threaten-01)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (1,2))
                                                  //| Edges:	Map((0,4) -> root, (2,1) -> name#, (4,2) -> cop, (4,3) -> det, (
                                                  //| 4,5) -> nsubj)
                                                  //| InsertedNodes:	Map(5 -> )
                                                  //| MergedNode:	Map()
                                                  //| Mappings:	5 -> 
                                                  //| 
val merge = ReplaceHead(next2)                    //> merge  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(1, 3, 4, 0)
                                                  //| Children:	List()
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> threaten-01, 3 -> a, 4 -> test, 5 -> threate
                                                  //| n-01)
                                                  //| SpanMap:	Map(1 -> (1,2), 3 -> (3,4), 4 -> (4,5), 5 -> (1,2))
                                                  //| Edges:	Map((0,4) -> root, (4,1) -> cop, (4,3) -> det, (4,5) -> nsubj)
                                                  //| InsertedNodes:	Map(5 -> )
                                                  //| MergedNode:	Map(1 -> List((2,is)))
                                                  //| Mappings:	5 -> 
                                                  //| 
// check that we do not have a self-loop
}