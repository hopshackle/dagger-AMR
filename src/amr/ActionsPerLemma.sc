package amr

object ActionsPerLemma {

ImportConcepts.initialise("C:\\AMR\\CountryList.txt")
ImportConcepts.conceptStrings                     //> Adding annotator tokenize
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
                                                  //| tsim.crf.ser.gz ... done [2.5 sec].
                                                  //| res0: scala.collection.immutable.Set[String] = Set(name, country, -, ROOT, a
                                                  //| nd)
ImportConcepts.conceptStrings                     //> res1: scala.collection.immutable.Set[String] = Set(name, country, -, ROOT, a
                                                  //| nd)
ImportConcepts.conceptsPerLemma.getOrElse("university", List()) foreach (x => println(ImportConcepts.concept(x)))
(ImportConcepts.conceptsPerLemma map { case (lemma, set) => (lemma, set.size)}).toList.sortWith((a, b) => a._2 > b._2) foreach println
                                                  //> ('s,1)
ImportConcepts.edgesPerLemma foreach println      //> (people,Set(2, 1))
                                                  //| (name,Set(2, 1))
                                                  //| (lao,Set(2, 1))
                                                  //| (democratic,Set(2, 1))
                                                  //| (la,Set(2, 1))
                                                  //| (country,Set(2, 1))
                                                  //| ('s,Set(2, 1))
                                                  //| (th,Set(2, 1))
                                                  //| (myanmar,Set(2, 1))
                                                  //| (thailand,Set(2, 1))
                                                  //| (mm,Set(2, 1))
                                                  //| (republic,Set(2, 1))
                                                  //| (and,Set(2, 1))
ImportConcepts.relationStrings                    //> res2: scala.collection.immutable.Set[String] = Set(name, opN, ROOT, polarity
                                                  //| )
(ImportConcepts.edgesPerLemma map { case (lemma, set) => (lemma, set.size)}).toList.sortWith((a, b) => a._2 > b._2) foreach println
                                                  //> (people,2)
                                                  //| (name,2)
                                                  //| (lao,2)
                                                  //| (democratic,2)
                                                  //| (la,2)
                                                  //| (country,2)
                                                  //| ('s,2)
                                                  //| (th,2)
                                                  //| (myanmar,2)
                                                  //| (thailand,2)
                                                  //| (mm,2)
                                                  //| (republic,2)
                                                  //| (and,2)
}