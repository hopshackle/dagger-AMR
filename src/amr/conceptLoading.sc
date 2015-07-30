package amr

object conceptLoading {

import ImportConcepts._
val numbers = "[0-9]".r                           //> numbers  : scala.util.matching.Regex = [0-9]
val alpha = "[a-zA-Z]".r                          //> alpha  : scala.util.matching.Regex = [a-zA-Z]
val quote = """"""".r                             //> quote  : scala.util.matching.Regex = "
numbers replaceAllIn("006294fg230=_", "")         //> res0: String = fg=_
alpha replaceAllIn("006294fg230=_", "")           //> res1: String = 006294230=_
quote findFirstIn(""" "NATO" """)                 //> res2: Option[String] = Some(")
quote findFirstIn("006294fg230=_")                //> res3: Option[String] = None
initialise("C:\\AMR\\CountryList.txt")

conceptStrings                                    //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.5 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [4.8 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [4.4 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [3.5 sec].
                                                  //| res4: Set[String] = Set(People's, name, Democratic, Thailand, Myanmar, count
                                                  //| ry, Lao, Republic, ROOT, and)
conceptStrings                                    //> res5: Set[String] = Set(People's, name, Democratic, Thailand, Myanmar, count
                                                  //| ry, Lao, Republic, ROOT, and)
relationStrings                                   //> res6: Set[String] = Set(name, opN, ROOT)
relationMaster                                    //> res7: scala.collection.immutable.Map[Int,String] = Map(1 -> name, 2 -> opN, 
                                                  //| 3 -> ROOT, 0 -> UNKNOWN)
insertableConcepts                                //> res8: Map[String,Set[String]] = Map(name -> Set(country), country -> Set(and
                                                  //| ), 's -> Set(country), myanmar -> Set(), thailand -> Set())
conceptsPerLemma                                  //> res9: Map[String,Set[Int]] = Map(people -> Set(1), lao -> Set(7), democratic
                                                  //|  -> Set(3), 's -> Set(2), myanmar -> Set(5), thailand -> Set(4), republic ->
                                                  //|  Set(8))
edgesPerLemma                                     //> res10: Map[String,Set[Int]] = Map(people -> Set(0), name -> Set(1), lao -> S
                                                  //| et(), democratic -> Set(0), la -> Set(), country -> Set(1), 's -> Set(1), th
                                                  //|  -> Set(), myanmar -> Set(), thailand -> Set(0), mm -> Set(), republic -> Se
                                                  //| t(0), and -> Set(3))
}