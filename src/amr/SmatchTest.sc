package amr

object SmatchTest {
  val saudiArabia = AMRGraph.importFile("C://AMR//SaudiArabia.txt")
                                                  //> java.io.FileNotFoundException: C:\AMR\SaudiArabia.txt (The system cannot fin
                                                  //| d the file specified)
                                                  //| 	at java.io.FileInputStream.open(Native Method)
                                                  //| 	at java.io.FileInputStream.<init>(Unknown Source)
                                                  //| 	at scala.io.Source$.fromFile(Source.scala:91)
                                                  //| 	at scala.io.Source$.fromFile(Source.scala:76)
                                                  //| 	at scala.io.Source$.fromFile(Source.scala:54)
                                                  //| 	at amr.AMRGraph$.importFile(Sentence.scala:544)
                                                  //| 	at amr.SmatchTest$$anonfun$main$1.apply$mcV$sp(amr.SmatchTest.scala:4)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$$anonfun$$exe
                                                  //| cute$1.apply$mcV$sp(WorksheetSupport.scala:76)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.redirected(W
                                                  //| orksheetSupport.scala:65)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.$execute(Wor
                                                  //| ksheetSupport.scala:75)
                                                  //| 	at amr.SmatchTest$.main(amr.SmatchTest.scala:3)
                                                  //| 	at amr.SmatchTest.main(amr.SmatchTest.scala)
    val saudi = AMRGraph.importFile("C://AMR//Saudi.txt")
  val sa = Sentence(saudiArabia(0)._1, saudiArabia(0)._2)
  val sa_amr = sa.amr.get
  val s = Sentence(saudi(0)._1, saudi(0)._2)
  val s_amr = s.amr.get
  Smatch.fScore(s_amr, sa_amr, 1, 1000)
  Smatch.fScore(sa_amr, s_amr, 1, 1000)
 Smatch.naiveFScore(s_amr, sa_amr)
}