package amr

object testDT {

val dt = DependencyTree("The police want to arrest Michael Karras in Singapore.")
                                                  //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.1 sec].
                                                  //| dt  : amr.DependencyTree = DependencyTree(Map(0 -> ROOT, 5 -> arrest, 1 -> T
                                                  //| he, 6 -> Michael, 9 -> Singapore, 2 -> police, 7 -> Karras, 3 -> want, 8 -> 
                                                  //| in, 4 -> to),Map(0 -> (0,1), 5 -> (5,6), 1 -> (1,2), 6 -> (6,7), 9 -> (9,10)
                                                  //| , 2 -> (2,3), 7 -> (7,8), 3 -> (3,4), 8 -> (8,9), 4 -> (4,5)),List((2,1,det)
                                                  //| , (3,2,nsubj), (0,3,root), (5,4,aux), (3,5,xcomp), (7,6,nn), (5,7,dobj), (7,
                                                  //| 8,prep), (8,9,pobj)))

}