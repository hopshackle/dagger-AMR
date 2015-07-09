package amr

object generalPlay {
val minPerDay: Double = 24.0 * 60.0 / 1000.0      //> minPerDay  : Double = 1.44
  val a = List[String]()                          //> a  : List[String] = List()
  val b = List("one", "two", "three")             //> b  : List[String] = List(one, two, three)
  a contains "6"                                  //> res0: Boolean = false
  val c = List("four", "five", "one")             //> c  : List[String] = List(four, five, one)
  b contains c                                    //> res1: Boolean = false
  b contains c(2)                                 //> res2: Boolean = true
  b diff c                                        //> res3: List[String] = List(two, three)
  a diff c                                        //> res4: List[String] = List()
  a.size                                          //> res5: Int = 0
  a.isEmpty                                       //> res6: Boolean = true
  a == Nil                                        //> res7: Boolean = true
  val value = "text"                              //> value  : String = text
  val newValue = """"""" + value + """""""        //> newValue  : String = "text"

  val testMap: Map[Int, Double] = Map((2 -> 1.0), (3 -> 67.0), (56 -> 4.56), (1 -> 99.0), (40 -> 0.013))
                                                  //> testMap  : Map[Int,Double] = Map(56 -> 4.56, 1 -> 99.0, 2 -> 1.0, 3 -> 67.0,
                                                  //|  40 -> 0.013)
  testMap + (4 -> 9)                              //> res8: scala.collection.immutable.Map[Int,AnyVal] = Map(56 -> 4.56, 1 -> 99.0
                                                  //| , 2 -> 1.0, 3 -> 67.0, 40 -> 0.013, 4 -> 9)
  val keys = testMap.keys.toList.sorted           //> keys  : List[Int] = List(1, 2, 3, 40, 56)
  val combo = for {
    f1 <- keys
    f2 <- keys.dropWhile { _ <= f1 }
  } yield (f1 + "-" + f2 -> testMap(f1) * testMap(f2))
                                                  //> combo  : List[(String, Double)] = List((1-2,99.0), (1-3,6633.0), (1-40,1.287
                                                  //| ), (1-56,451.43999999999994), (2-3,67.0), (2-40,0.013), (2-56,4.56), (3-40,0
                                                  //| .871), (3-56,305.52), (40-56,0.05927999999999999))
   val testTuple = Seq((1.0, 1.0, 1.0, 0.0), (1.0, 2.0, 0.0, 5.6))
                                                  //> testTuple  : Seq[(Double, Double, Double, Double)] = List((1.0,1.0,1.0,0.0),
                                                  //|  (1.0,2.0,0.0,5.6))
testTuple.max                                     //> res9: (Double, Double, Double, Double) = (1.0,2.0,0.0,5.6)
testTuple maxBy (_._1)                            //> res10: (Double, Double, Double, Double) = (1.0,1.0,1.0,0.0)
Math.abs(2.34)                                    //> res11: Double = 2.34
Math.abs(-2.33)                                   //> res12: Double = 2.33
Math.max(3, 78)                                   //> res13: Int = 78
}