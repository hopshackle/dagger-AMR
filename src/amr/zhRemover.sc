package amr

object zhRemover {

  import scala.io._
  import java.io._
  val fileToProcess = "C://AMR//amr-lpp-struct-v1.5-training.txt"
                                                  //> fileToProcess  : String = C://AMR//amr-lpp-struct-v1.5-training.txt
  val s = Source.fromFile(fileToProcess)("UTF-8") //> s  : scala.io.BufferedSource = non-empty iterator
  val newLines = s.getLines filterNot { _.startsWith("# ::zh") } toList
                                                  //> newLines  : List[String] = List(# AMR release; corpus: lpp; section: trainin
                                                  //| g; number of AMRs: 1274 (generated on Fri Sep 11, 2015 at 17:11:29), "", # :
                                                  //| :id lpp_1943.289 ::date 2012-11-18T16:26:36 ::annotator ISI-AMR-05 ::preferr
                                                  //| ed, # ::snt Chapter 7 ., # ::save-date Tue Apr 16, 2013 ::file lpp_1943_289.
                                                  //| txt, (c / chapter :mod 7), "", # ::id lpp_1943.290 ::date 2012-11-18T16:26:5
                                                  //| 7 ::annotator ISI-AMR-05 ::preferred, # ::snt On the fifth day -- again , as
                                                  //|  always , it was thanks to the sheep -- the secret of the little prince 's l
                                                  //| ife was revealed to me ., # ::save-date Thu Apr 18, 2013 ::file lpp_1943_290
                                                  //| .txt, (r / reveal-01, "      :ARG0 (s / sheep", "            :ARG1-of (t / t
                                                  //| hank-01))", "      :ARG1 (s2 / secret", "            :poss (l / life", "    
                                                  //|               :poss (p / prince", "                        :mod (l2 / little
                                                  //| ))))", "      :ARG2 (i / i)", "      :time (d / day", "            :ord (o /
                                                  //|  ordinal-entity :value 5))", "      :mod (a / again)", "      :time (a2 / al
                                                  //| ways))", "", # ::id lpp_1943.291 ::date 2012-11-18T16:34:31 ::annotator ISI-
                                                  //| AMR-05 ::preferred, # ::snt Abruptly , without anything to lead up to it , a
                                                  //| nd as if the question had been born of long and silent meditation on his pro
                                                  //| blem , he demanded : " A sheep -- if it eats little bushes , does it eat flo
                                                  //| wers , too ? ", # ::save-date Thu Jun 4, 2015 ::file lpp_1943_291.txt, (d / 
                                                  //| demand-01, "      :ARG0 (h / he)", "      :ARG1 (e / eat-01", "            :
                                                  //| ARG0 (s / sheep)", "            :ARG1 (f / flower", "                  :mod 
                                                  //| (t / too))", "            :condition (e2 / eat-01", "                  :ARG0
                                                  //|  s", "                  :ARG1 (b / bush", "                        :mod (l /
                                                  //|  little))))", "      :manner (a / abrupt)", "      :conj-as-if (b2 / bear-02
                                                  //| ", "            :ARG0 (m / meditate-01", "                  :ARG1 (p / probl
                                                  //| em", "                        :poss h)", "                  :ARG1-of (l3 / l
                                                  //| ong-03)", "                  :manner (s2 / silent))", "            :ARG1 (t2
                                                  //|  / thing", "                  :ARG1-of (q / question-01)))", "      :ARG2-of
                                                  //|  (l2 / lead-up-05 :polarity -", "            :ARG1 (a2 / anything)))", "", #
                                                  //|  ::id lpp_1943.292 ::date 2012-11-18T16:49:43 ::annotator ISI-AMR-05 ::prefe
                                                  //| rred, # ::snt " A sheep , ", # ::save-date Sun Nov 18, 2012 ::file lpp_1943_
                                                  //| 292.txt, (s / sheep), "", # ::id lpp_1943.293 ::date 2012-11-18T16
                                                  //| Output exceeds cutoff limit.
  val output = new FileWriter(fileToProcess)      //> output  : java.io.FileWriter = java.io.FileWriter@12843fce
  newLines foreach (x => output.write(x + "\n"))
  output.close

}