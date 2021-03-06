package amr

object generalPlay {
  val minPerDay: Double = 24.0 * 60.0 / 1000.0    //> minPerDay  : Double = 1.44
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
  testTuple.max                                   //> res9: (Double, Double, Double, Double) = (1.0,2.0,0.0,5.6)
  testTuple maxBy (_._1)                          //> res10: (Double, Double, Double, Double) = (1.0,1.0,1.0,0.0)
  Math.abs(2.34)                                  //> res11: Double = 2.34
  Math.abs(-2.33)                                 //> res12: Double = 2.33
  Math.max(3, 78)                                 //> res13: Int = 78

  val date1 = """((19|20)\d\d)[- /.]((0[1-9]|1[012]))[- /.]((0[1-9]|[12][0-9]|3[01]))""".r
                                                  //> date1  : scala.util.matching.Regex = ((19|20)\d\d)[- /.]((0[1-9]|1[012]))[- 
                                                  //| /.]((0[1-9]|[12][0-9]|3[01]))
  val redDate = """\b((?:19|20)\d\d)-(\d\d)-(\d\d)\b""".r
                                                  //> redDate  : scala.util.matching.Regex = \b((?:19|20)\d\d)-(\d\d)-(\d\d)\b
  val redDate2 = """(\d\d\d\d)(\d\d)(\d\d)""".r   //> redDate2  : scala.util.matching.Regex = (\d\d\d\d)(\d\d)(\d\d)
  val testDate = "The Date was 2001-12-01."       //> testDate  : String = The Date was 2001-12-01.
  val (year, month, day) = testDate match {
    case redDate.unanchored(year, month, day) => (year, month, day)
  }                                               //> year  : String = 2001
                                                  //| month  : String = 12
                                                  //| day  : String = 01
  redDate replaceAllIn (testDate, m => (m group 1) + " " + (m group 2) + " " + (m group 3))
                                                  //> res14: String = The Date was 2001 12 01.
  redDate.replaceAllIn("20011201", m => (m group 1) + " " + (m group 2) + " " + (m group 3))
                                                  //> res15: String = 20011201
  redDate2.replaceAllIn("20011201", m => (m group 1) + " " + (m group 2) + " " + (m group 3))
                                                  //> res16: String = 2001 12 01

  """[.-]""".r.replaceAllIn("He-llo.0.0", "0")    //> res17: String = He0llo0000
  "Hell'o".toLowerCase.replaceAll("""[^0-9a-zA-Z\- ]""", "")
                                                  //> res18: String = hello
  val b1 = List((1, "one"), (2, "two"), (3, "three"), (1, "oneA"))
                                                  //> b1  : List[(Int, String)] = List((1,one), (2,two), (3,three), (1,oneA))
  b1.sortWith((a, b) => a._1 > b._1)              //> res19: List[(Int, String)] = List((3,three), (2,two), (1,one), (1,oneA))
  val tallinn = """"Tallinn""""                   //> tallinn  : String = "Tallinn"
  val test = " [label = " + tallinn + "]"         //> test  : String = " [label = "Tallinn"]"

  val t2 = "one billion 2 million once, one, 6.02 billions onet One "
                                                  //> t2  : String = "one billion 2 million once, one, 6.02 billions onet One "
  val one = """(?i)(^one | one | one[,.?!;:])""".r//> one  : scala.util.matching.Regex = (?i)(^one | one | one[,.?!;:])
  one.replaceAllIn(t2, " 1 ")                     //> res20: String = " 1 billion 2 million once, 1  6.02 billions onet 1 "
  val old = """((?:[0-9]+\.[0-9]*)|(?:[0-9]*\.[0-9]+)|(?:[0-9]+))"""
                                                  //> old  : String = ((?:[0-9]+\.[0-9]*)|(?:[0-9]*\.[0-9]+)|(?:[0-9]+))
  val realNumbers = """((?:[0-9]+\.[0-9]*)|(?:[0-9]*\.[0-9]+)|(?:[0-9]+)) (thousand|million|billion)""".r
                                                  //> realNumbers  : scala.util.matching.Regex = ((?:[0-9]+\.[0-9]*)|(?:[0-9]*\.[
                                                  //| 0-9]+)|(?:[0-9]+)) (thousand|million|billion)
  val matches = (realNumbers findAllIn t2).toList //> matches  : List[String] = List(2 million, 6.02 billion)
  // matches foreach println
  val text = matches map (x => x.split(" "))      //> text  : List[Array[String]] = List(Array(2, million), Array(6.02, billion))
                                                  //| 
  // DependencyTree.extractNumbers("A two-week extension until the 30-strong group arrive to test their 3-billion-dollar theory.")
  // val replacements = text map { x => x(0).toDouble * (x(1) match { case "thousand" => 1000; case "million" => 1000000; case "billion" => 1000000000 }) } map (x => f"${x}%.0f")
  // replacements foreach println
  realNumbers.replaceAllIn(t2, " rep ")           //> res21: String = "one billion  rep  once, one,  rep s onet One "
  t2 match { case realNumbers(number) => "f"; case other => other }
                                                  //> res22: String = "one billion 2 million once, one, 6.02 billions onet One "
                                                  //| 
  val a2 = realNumbers findAllIn t2               //> a2  : scala.util.matching.Regex.MatchIterator = non-empty iterator
  val reformatted = realNumbers replaceAllIn (t2, _ match {
    case realNumbers(number, multiple) =>
      val replacement = number.toDouble * (multiple match { case "thousand" => 1000; case "million" => 1000000; case "billion" => 1000000000 });
      f"$replacement%.0f "
    case other => ""
  })                                              //> reformatted  : String = "one billion 2000000  once, one, 6020000000 s onet 
                                                  //| One "

  val dollar = """\$""".r                         //> dollar  : scala.util.matching.Regex = \$
  dollar.replaceAllIn("$56", "dollars ")          //> res23: String = dollars 56
  // DependencyTree.extractNumbers(t2)
  val t3 = List(2.34, 6, -56, 9.0073, 0, 1, -1)   //> t3  : List[Double] = List(2.34, 6.0, -56.0, 9.0073, 0.0, 1.0, -1.0)
  val (highScore, index) = (t3 zipWithIndex).max  //> highScore  : Double = 9.0073
                                                  //| index  : Int = 3
  val costs = List(0.686, 0.749, 0.744, 0.639, 0.386, 0.619, 0.674, 0.574, 0.785, 0.513, 0.412, 0.513, 0.321, 0.417)
                                                  //> costs  : List[Double] = List(0.686, 0.749, 0.744, 0.639, 0.386, 0.619, 0.67
                                                  //| 4, 0.574, 0.785, 0.513, 0.412, 0.513, 0.321, 0.417)
  val min = costs.minBy(_ * 1.0)                  //> min  : Double = 0.321
  val tempNormCosts = costs.map(x => (x - min))   //> tempNormCosts  : List[Double] = List(0.36500000000000005, 0.428, 0.423, 0.3
                                                  //| 18, 0.065, 0.298, 0.35300000000000004, 0.25299999999999995, 0.464, 0.192, 0
                                                  //| .09099999999999997, 0.192, 0.0, 0.09599999999999997)
  val normedCosts = if (tempNormCosts contains 0.0) tempNormCosts.toArray else (tempNormCosts map (x => 0.0)).toArray
                                                  //> normedCosts  : Array[Double] = Array(0.36500000000000005, 0.428, 0.423, 0.3
                                                  //| 18, 0.065, 0.298, 0.35300000000000004, 0.25299999999999995, 0.464, 0.192, 0
                                                  //| .09099999999999997, 0.192, 0.0, 0.09599999999999997)
  normedCosts map (_.toFloat)                     //> res24: Array[Float] = Array(0.365, 0.428, 0.423, 0.318, 0.065, 0.298, 0.353
                                                  //| , 0.253, 0.464, 0.192, 0.091, 0.192, 0.0, 0.096)
  var classifier = null.asInstanceOf[dagger.ml.MultiClassClassifier[String]]
                                                  //> classifier  : dagger.ml.MultiClassClassifier[String] = null
  classifier == null                              //> res25: Boolean = true
  val clean2 = """[.#'-]""".r                     //> clean2  : scala.util.matching.Regex = [.#'-]
  clean2.replaceAllIn("ba.se-01", "")             //> res26: String = base01
  val numbers = "([0-9*][.,][0-9])".r             //> numbers  : scala.util.matching.Regex = ([0-9*][.,][0-9])
  numbers.replaceAllIn("100", "")                 //> res27: String = 100
  numbers.replaceAllIn(".", "")                   //> res28: String = .
  numbers.replaceAllIn("100.00", "")              //> res29: String = 100

  val arrayTest = Array(0, 1, 2, 3, -7, 89)       //> arrayTest  : Array[Int] = Array(0, 1, 2, 3, -7, 89)
  arrayTest.toList                                //> res30: List[Int] = List(0, 1, 2, 3, -7, 89)
  "ARG0-of".substring(0, 4)                       //> res31: String = ARG0

  val hyphen = "-".r                              //> hyphen  : scala.util.matching.Regex = -
  hyphen.split("anti-terrorism")                  //> res32: Array[String] = Array(anti, terrorism)
  "anti-terrorism".split("-")                     //> res33: Array[String] = Array(anti, terrorism)
  hyphen.split("--")                              //> res34: Array[String] = Array()
  val negation = "^(un.*|in.*|il.*|anti.*)".r     //> negation  : scala.util.matching.Regex = ^(un.*|in.*|il.*|anti.*)
  negation.findFirstIn("illegal")                 //> res35: Option[String] = Some(illegal)
  negation.findFirstIn("Illegal")                 //> res36: Option[String] = None
  negation.findFirstIn("toil")                    //> res37: Option[String] = None
  val comparator = "(er|est)$".r                  //> comparator  : scala.util.matching.Regex = (er|est)$
  comparator.findFirstIn("best")                  //> res38: Option[String] = Some(est)
  comparator.findFirstIn("nester")                //> res39: Option[String] = Some(er)
  comparator.findFirstIn("estevan")               //> res40: Option[String] = None
  val idFinder = """(# ::id [^:]*) ::.*""".r      //> idFinder  : scala.util.matching.Regex = (# ::id [^:]*) ::.*
  idFinder.findFirstIn("# ::id wb.c2e_0008.1 ::date 2012-11-15T12:53:07 ::annotator SDL-AMR-09 ::preferred")
                                                  //> res41: Option[String] = Some(# ::id wb.c2e_0008.1 ::date 2012-11-15T12:53:0
                                                  //| 7 ::annotator SDL-AMR-09 ::preferred)
  "# ::id wb.c2e_0008.1 ::date 2012-11-15T12:53:07 ::annotator SDL-AMR-09 ::preferred" match {
    case idFinder(id) => id
  }                                               //> res42: String = # ::id wb.c2e_0008.1

  val mutaSeq = new Array[Int](5)                 //> mutaSeq  : Array[Int] = Array(0, 0, 0, 0, 0)
  mutaSeq(2) = 6

  val numericBoundary = """\b([0-9]+)([a-zA-Z]+)\b""".r
                                                  //> numericBoundary  : scala.util.matching.Regex = \b([0-9]+)([a-zA-Z]+)\b
  numericBoundary.replaceAllIn("23 rd", "$1 $2")  //> res43: String = 23 rd
  numericBoundary.replaceAllIn("23rd", "$1 $2")   //> res44: String = 23 rd
  numericBoundary.replaceAllIn("d20", "$1 $2")    //> res45: String = d20
  numericBoundary.replaceAllIn("During the 1990s Sweden was hot.", "$1 $2")
                                                  //> res46: String = During the 1990 s Sweden was hot.

  val tupleToSort: List[(String, Double)] = List(("a", 0.3), ("b", 67), ("c", -0.5), ("d", 0.0), ("e", 20))
                                                  //> tupleToSort  : List[(String, Double)] = List((a,0.3), (b,67.0), (c,-0.5), (
                                                  //| d,0.0), (e,20.0))
  tupleToSort.sortBy(-_._2)                       //> res47: List[(String, Double)] = List((b,67.0), (e,20.0), (a,0.3), (d,0.0), 
                                                  //| (c,-0.5))
  tupleToSort.sortBy(_._2)                        //> res48: List[(String, Double)] = List((c,-0.5), (d,0.0), (a,0.3), (e,20.0), 
                                                  //| (b,67.0))
  tupleToSort.sortBy(-_._2).take(2)               //> res49: List[(String, Double)] = List((b,67.0), (e,20.0))
  100.toString                                    //> res50: String = 100
  List(1, 2, 3).mkString("n")                     //> res51: String = 1n2n3
  (List(0.1, 0.2, 0.35, 0.05, 0.30).scanLeft(0.0)(_ + _)).tail
                                                  //> res52: List[Double] = List(0.1, 0.30000000000000004, 0.65, 0.70000000000000
                                                  //| 01, 1.0)
  val beta = 0.3                                  //> beta  : Double = 0.3
  val n = 5                                       //> n  : Int = 5
  val tempProb = (1 to n) map (x => beta * math.pow((1 - beta), x - 1))
                                                  //> tempProb  : scala.collection.immutable.IndexedSeq[Double] = Vector(0.3, 0.2
                                                  //| 1, 0.14699999999999996, 0.10289999999999998, 0.07202999999999998)
  val probabilities = (tempProb map (_ / tempProb.sum)).scanLeft(0.0)(_ + _).tail
                                                  //> probabilities  : scala.collection.immutable.IndexedSeq[Double] = Vector(0.3
                                                  //| 606072626302693, 0.6130323464714579, 0.7897299051602898, 0.9134181962424722
                                                  //| , 0.9999999999999999)
  List(0.0, 0.4, 0.6).indexWhere(_ > 0.8)         //> res53: Int = -1
  val random = new util.Random(1)                 //> random  : scala.util.Random = scala.util.Random@694e1548
  val diceRoll = (1 to 10) map (_ => random.nextDouble())
                                                  //> diceRoll  : scala.collection.immutable.IndexedSeq[Double] = Vector(0.730878
                                                  //| 1907032909, 0.41008081149220166, 0.20771484130971707, 0.3327170559595112, 0
                                                  //| .9677559094241207, 0.006117182265761301, 0.9637047970232077, 0.939865388781
                                                  //| 9098, 0.9471949176631939, 0.9370821488959696)
  val i = diceRoll map (d => probabilities.indexWhere(d <= _))
                                                  //> i  : scala.collection.immutable.IndexedSeq[Int] = Vector(2, 1, 0, 0, 4, 0, 
                                                  //| 4, 4, 4, 4)
}