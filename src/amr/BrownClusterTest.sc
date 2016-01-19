package amr

object BrownClusterTest {
val brown100 = new BrownCluster("C:\\AMR\\Brown100.txt")
                                                  //> brown100  : amr.BrownCluster = amr.BrownCluster@cb5822
brown100.getClusterFor("we")                      //> res0: String = 111001110
brown100.getClusterFor("We")                      //> res1: String = 111000110
brown100.getClusterFor("completelyRandomWIBBLE")  //> res2: String = 0

brown100.getBrownClusters(List("we"))             //> res3: List[String] = List(111001110, 111001, 1110)
brown100.getBrownClusters(List("completelyRandomWibble"))
                                                  //> res4: List[String] = List()
brown100.getBrownClusters(List("completelyRandomWibble", "we"))
                                                  //> res5: List[String] = List(111001110, 111001, 1110)
brown100.getBrownClusters(List("we", "completelyRandomWibble"))
                                                  //> res6: List[String] = List(111001110, 111001, 1110)
brown100.getBrownClusters(List("We", "we"))       //> res7: List[String] = List(111000110, 111000, 1110)

}