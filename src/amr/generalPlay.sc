package amr

object generalPlay {
  val a = List[String]()                          //> a  : List[String] = List()
  val b = List("one", "two", "three")             //> b  : List[String] = List(one, two, three)
  a contains "6"                                  //> res0: Boolean = false
  val c = List("four", "five", "one")             //> c  : List[String] = List(four, five, one)
  b contains c                                    //> res1: Boolean = false
  b contains c(2)                                 //> res2: Boolean = true
}