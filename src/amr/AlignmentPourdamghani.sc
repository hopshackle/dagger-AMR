package amr
import edu.cmu.lti.nlp.amr._
object AlignmentPourdamghani {
  AMRGraph.usePourdamghaniAligner = true
  ImportConcepts.initialise("C:\\AMR\\PIssue4.txt")
  val (rawSentence, rawAMR, id) = AMRGraph.importFile("C:\\AMR\\PIssue4.txt")(0)
                                                  //> java.io.FileNotFoundException: C:\AMR\PIssue4.txt (The system cannot find th
                                                  //| e file specified)
                                                  //| 	at java.io.FileInputStream.open0(Native Method)
                                                  //| 	at java.io.FileInputStream.open(Unknown Source)
                                                  //| 	at java.io.FileInputStream.<init>(Unknown Source)
                                                  //| 	at scala.io.Source$.fromFile(Source.scala:91)
                                                  //| 	at scala.io.Source$.fromFile(Source.scala:76)
                                                  //| 	at scala.io.Source$.fromFile(Source.scala:54)
                                                  //| 	at amr.AMRGraph$.importFile(Sentence.scala:617)
                                                  //| 	at amr.AlignmentPourdamghani$$anonfun$main$1.apply$mcV$sp(amr.AlignmentP
                                                  //| ourdamghani.scala:6)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$$anonfun$$exe
                                                  //| cute$1.apply$mcV$sp(WorksheetSupport.scala:76)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.redirected(W
                                                  //| orksheetSupport.scala:65)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.$execute(Wor
                                                  //| ksheetSupport.scala:75)
                                                  //| 	at amr.AlignmentPourdamghani$.main(amr.AlignmentPourdamghani.scala:3)
                                                  //| 	at amr.AlignmentPourdamghani.main(amr.AlignmentPourdamghani.scala)
 val JAMRamr = Graph.parse(rawAMR)
 JAMRamr.nodes foreach (x => println(x.concept + " : " + x.relations))
  JAMRamr.nodes foreach (x => println(x.concept + " : " + x.topologicalOrdering))
  val amr = AMRGraph(JAMRamr)
  PourdamghaniAligner.importTokenization
   val pa = PourdamghaniAligner(DependencyTree.preProcess(rawSentence), amr, id)
  DependencyTree.preProcess(rawSentence)
  val mapping = pa.mapToAMR
  amr.nodes foreach (x => println(s"${x._1}\t${x._2}\t${mapping.nodeSpans.getOrElse(x._1, "No Mapping")}"))
  Sentence(rawSentence, rawAMR, id)
  amr.arcs foreach println
  }