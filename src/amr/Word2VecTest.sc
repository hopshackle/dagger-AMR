
package amr
object Word2VecTest {



    val w2vDict = Word2VecReader.load("C:\\AMR\\daggerTest\\..\\glove.6B.50d.txt")
                                                  //> w2vDict  : amr.Word2Vec = amr.Word2Vec@614c5515
   
    w2vDict.size                                  //> res0: Int = 400000
    w2vDict.euclidean("the", "and")               //> res1: Double = 2.802192346511321
     w2vDict.euclidean("the", "visitors")         //> res2: Double = 4.879349468322334
  ImportConcepts.initialise("C:\\AMR\\MediumTrainingSet.txt")
  val trainData = (ImportConcepts.allAMR zip ImportConcepts.allSentencesAndAMR) map (all => Sentence(all._2._1, Some(all._1)))
                                                  //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [2.1 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [8.7 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [3.8 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [9.3 sec].
                                                  //| WARNING: Found duplicate match for concept "for"
                                                  //| WARNING: Found duplicate match for concept "for"
                                                  //| WARNING: Found duplicate match for concept "Arab"
                                                  //| WARNING: Found duplicate match for concept include-91
                                                  //| WARNING: Found duplicate match for concept and
                                                  //| WARNING: Found duplicate match for concept factor
                                                  //| WARNING: Found duplicate match for concept and
                                                  //| WARNING: Found duplicate match for concept internet
                                                  //| WARNING: Found duplicate match for concept include-91
                                                  //| WARNING: Found duplicate match for concept website
                                                  //| WARNING: Found duplicate match for concept "South"
                                                  //| WARNING: Found duplicate match for concept and
                                                  //| WARNING: Found duplicate match for concept "South"
                                                  //| WARNING: Found duplicate match for concept "FIFA"
                                                  //| WARNING: Found duplicate match for concept and
                                                  //| WARNING: Found duplicate match for concept and
                                                  //| WARNING: Found duplicate match for concept dog
                                                  //| WARNING: Found duplicate match for concept and
                                                  //| WARNING: Found duplicate match for concept include-91
                                                  //| WARNING: Found duplicate match for concept include-91
                                                  //| WARNING: Found duplicate match for concept group
                                                  //| WARNING: Found duplicate match for concept group
                                                  //| WARNING: Found duplicate match for concept 5
                                                  //| WARNING: Found duplicate match for concept ban-01
                                                  //| WARNING: Found duplicate match for concept ban-01
  val devData = AMRGraph.importFile("C:\\AMR\\testSet126.txt") map { case (english, amr) => Sentence(english, amr) }
 
  val correctedDevData = RunDagger.replaceLemmasWordnet(devData)
 
  correctedDevData foreach (s => {
    println(s.rawText)
    s.dependencyTree.nodeLemmas foreach println
  })

}