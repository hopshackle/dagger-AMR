
package amr
object Word2VecTest {



    val w2vDict = Word2VecReader.load("C:\\AMR\\daggerTest\\..\\glove.6B.50d.txt")
                                                  //> w2vDict  : amr.Word2Vec = amr.Word2Vec@6be46e8f
   
    w2vDict.size                                  //> res0: Int = 400000
    w2vDict.euclidean("the", "and")               //> res1: Double = 2.802192346511321
     w2vDict.euclidean("the", "visitors")         //> res2: Double = 4.879349468322334
  ImportConcepts.initialise("C:\\AMR\\MediumTrainingSet.txt")
  val trainData = (ImportConcepts.allAMR zip ImportConcepts.allSentencesAndAMR) map (all => Sentence(all._2._1, Some(all._1)))
                                                  //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.1 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [2.6 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [4.2 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [1.9 sec].
                                                  //| WARNING: Found duplicate match for concept "for"
                                                  //| WARNING: Found duplicate match for concept "for"
                                                  //| WARNING: Found duplicate match for concept "Arab"
                                                  //| WARNING: Found duplicate match for concept include-91
                                                  //| WARNING: Found duplicate match for concept and
                                                  //| WARNING: Found duplicate match for concept factor
                                                  //| WARNING: Fund duplicate match for concept and
                                                  //| WARNING: Found duplicate match for concept internet
                                                  //| WARNING: Found duplicate match for concept include-91/
  val devData = AMRGraph.importFile("C:\\AMR\\testSet126.txt") map { case (english, amr) => Sentence(english, amr) }
 
  val correctedDevData = RunDagger.replaceLemmasWordnet(devData)
 
  correctedDevData foreach (s => {
    println(s.rawText)
    s.dependencyTree.nodeLemmas foreach println
  })

}