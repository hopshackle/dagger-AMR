package amr

object WangXueStatetest {

val testSentence = Sentence("This is a test.")    //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/english
                                                  //| PCFG.ser.gz ... done [1.1 sec].
                                                  //| testSentence  : amr.Sentence = Sentence(This is a test.,DependencyTree(Map(0 
                                                  //| -> ROOT, 1 -> This, 2 -> is, 3 -> a, 4 -> test),Map(1 -> (1,2), 2 -> (2,3), 3
                                                  //|  -> (3,4), 4 -> (4,5)),List((4,1,nsubj), (4,2,cop), (4,3,det), (0,4,root))),N
                                                  //| one)
val WXSystem = new WangXueTransitionSystem        //> WXSystem  : amr.WangXueTransitionSystem = amr.WangXueTransitionSystem@3f197a
                                                  //| 46

val initialState = WXSystem.init(testSentence)    //> initialState  : amr.WangXueTransitionState = WangXueTransitionState(List(1, 
                                                  //| 2, 3, 4, 0),List(),DependencyTree(Map(0 -> ROOT, 1 -> This, 2 -> is, 3 -> a,
                                                  //|  4 -> test),Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5)),List((4,1,ns
                                                  //| ubj), (4,2,cop), (4,3,det), (0,4,root))))
val testSentence2 = Sentence("The police want to arrest Michael Karras in London.")
                                                  //> testSentence2  : amr.Sentence = Sentence(The police want to arrest Michael K
                                                  //| arras in London.,DependencyTree(Map(0 -> ROOT, 5 -> arrest, 1 -> The, 6 -> M
                                                  //| ichael, 9 -> London, 2 -> police, 7 -> Karras, 3 -> want, 8 -> in, 4 -> to),
                                                  //| Map(5 -> (5,6), 1 -> (1,2), 6 -> (6,7), 9 -> (9,10), 2 -> (2,3), 7 -> (7,8),
                                                  //|  3 -> (3,4), 8 -> (8,9), 4 -> (4,5)),List((2,1,det), (3,2,nsubj), (0,3,root)
                                                  //| , (5,4,aux), (3,5,xcomp), (7,6,nn), (5,7,dobj), (7,8,prep), (8,9,pobj))),Non
                                                  //| e)

val initialState2 = WXSystem.init(testSentence2)  //> initialState2  : amr.WangXueTransitionState = WangXueTransitionState(List(9,
                                                  //|  6, 8, 1, 4, 7, 2, 5, 3, 0),List(),DependencyTree(Map(0 -> ROOT, 5 -> arrest
                                                  //| , 1 -> The, 6 -> Michael, 9 -> London, 2 -> police, 7 -> Karras, 3 -> want, 
                                                  //| 8 -> in, 4 -> to),Map(5 -> (5,6), 1 -> (1,2), 6 -> (6,7), 9 -> (9,10), 2 -> 
                                                  //| (2,3), 7 -> (7,8), 3 -> (3,4), 8 -> (8,9), 4 -> (4,5)),List((2,1,det), (3,2,
                                                  //| nsubj), (0,3,root), (5,4,aux), (3,5,xcomp), (7,6,nn), (5,7,dobj), (7,8,prep)
                                                  //| , (8,9,pobj))))
}