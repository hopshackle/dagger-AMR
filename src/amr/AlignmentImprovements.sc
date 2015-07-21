package amr
import edu.cmu.lti.nlp.amr._

object AlignmentImprovements {
ImportConcepts.initialise("C:\\AMR\\polarityExample.txt")
  val (rawSentence, rawAMR) = AMRGraph.importFile("C:\\AMR\\polarityExample.txt")(0)
                                                  //> rawSentence  : String = The cyber attacks were unprecedented.
                                                  //| rawAMR  : String = (p / precedent :polarity - :domain (a / attack-01 :mod (c
                                                  //|  / cyber)))
  val tokenisedSentence = DependencyTree.preProcess(rawSentence)
                                                  //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.2 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [3.7 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [3.3 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [4.3 sec].
                                                  //| tokenisedSentence  : List[String] = List(The, cyber, attacks, were, unpreced
                                                  //| ented)
  //    val tokenisedSentence = rawSentence.split(" ")
  val amr = Graph.parse(rawAMR)                   //> amr  : edu.cmu.lti.nlp.amr.Graph = Graph((precedent :polarity - :domain (att
                                                  //| ack-01 :mod cyber)),ArrayBuffer(),Map(0.1 -> (attack-01 :mod cyber), 0.1.0 -
                                                  //| > cyber, 0.0 -> -, 0 -> (precedent :polarity - :domain (attack-01 :mod cyber
                                                  //| ))),Map(p -> (precedent :polarity - :domain (attack-01 :mod cyber)), a -> (a
                                                  //| ttack-01 :mod cyber), c -> cyber))
  val wordAlignments = AlignTest.alignWords(tokenisedSentence.toArray, amr)
                                                  //> java.util.NoSuchElementException: key not found: attack-01
                                                  //| 	at scala.collection.MapLike$class.default(MapLike.scala:228)
                                                  //| 	at scala.collection.AbstractMap.default(Map.scala:59)
                                                  //| 	at scala.collection.mutable.HashMap.apply(HashMap.scala:65)
                                                  //| 	at amr.AlignTest$$anonfun$alignWords$2.apply$mcVI$sp(AlignTest.scala:63)
                                                  //| 
                                                  //| 	at amr.AlignTest$$anonfun$alignWords$2.apply(AlignTest.scala:60)
                                                  //| 	at amr.AlignTest$$anonfun$alignWords$2.apply(AlignTest.scala:60)
                                                  //| 	at scala.collection.mutable.HashMap$$anon$1$$anonfun$foreach$2.apply(Has
                                                  //| hMap.scala:103)
                                                  //| 	at scala.collection.mutable.HashMap$$anon$1$$anonfun$foreach$2.apply(Has
                                                  //| hMap.scala:103)
                                                  //| 	at scala.collection.mutable.HashTable$class.foreachEntry(HashTable.scala
                                                  //| :230)
                                                  //| 	at scala.collection.mutable.HashMap.foreachEntry(HashMap.scala:40)
                                                  //| 	at scala.collection.mutable.HashMap$$anon$1.foreach(HashMap.scala:103)
                                                  //| 	at amr.AlignTest$.alignWords(AlignTest.scala:60)
                                                  //| 	at amr.AlignTest$.alignWords(AlignTest.scala:34)
                                                  //| 	at amr.AlignmentImprovements$$anonfun$main$1.apply$mcV$sp(amr.AlignmentI
                                                  //| mprovements.scala:10)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$$anonfun$$exe
                                                  //| cute$1.apply$mcV$sp(WorksheetSupport.scala:76)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.redirected(W
                                                  //| orksheetSupport.scala:65)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.$execute(Wor
                                                  //| ksheetSupport.scala:75)
                                                  //| 	at amr.AlignmentImprovements$.main(amr.AlignmentImprovements.scala:4)
                                                  //| 	at amr.AlignmentImprovements.main(amr.AlignmentImprovements.scala)
  //    wordAlignments foreach println
  val spanAlignments = AlignSpans.alignSpans(tokenisedSentence.toArray, amr, wordAlignments)
  //    spanAlignments foreach println
  AMRGraph(amr)

}