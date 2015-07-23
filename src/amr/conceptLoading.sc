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
initialise("C:\\AMR\\initialTrainingSetAmended.txt")

conceptStrings                                    //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.5 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [3.7 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [3.2 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [4.7 sec].
                                                  //| WARNING: Found duplicate match for concept attack-01
                                                  //| WARNING: Found duplicate match for concept cyber
                                                  //| res4: scala.collection.immutable.Set[String] = Set(defend-01, chief, network
                                                  //| , defense, city, name, fund-01, date-entity, have-org-role-91, threaten-01, 
                                                  //| center, bolster-01, thing, provide-01, strike-01, specialize-01, conduct-01,
                                                  //|  agree-01, country, computer, science, -, technology, civilian, research-01,
                                                  //|  sign-01, after, international, open-01, consider-02, warfare, cyber, train-
                                                  //| 01, attack-01, operate-01, person, staff, grow-01, attack, base-01, ally-01,
                                                  //|  formal, ROOT, military, and, telecommunication, govern-01, government-organ
                                                  //| ization)
conceptStrings                                    //> res5: scala.collection.immutable.Set[String] = Set(defend-01, chief, network
                                                  //| , defense, city, name, fund-01, date-entity, have-org-role-91, threaten-01, 
                                                  //| center, bolster-01, thing, provide-01, strike-01, specialize-01, conduct-01,
                                                  //|  agree-01, country, computer, science, -, technology, civilian, research-01,
                                                  //|  sign-01, after, international, open-01, consider-02, warfare, cyber, train-
                                                  //| 01, attack-01, operate-01, person, staff, grow-01, attack, base-01, ally-01,
                                                  //|  formal, ROOT, military, and, telecommunication, govern-01, government-organ
                                                  //| ization)
relationStrings                                   //> res6: scala.collection.immutable.Set[String] = Set(quant, polarity, name, AR
                                                  //| G0-of, manner, ARG1-of, prep-for, location, mod, prep-against, ARG1, year, A
                                                  //| RG2, opN, purpose, time, ARG3, ROOT, ARG0, day, month)
relationMaster                                    //> res7: scala.collection.immutable.Map[Int,String] = Map(0 -> UNKNOWN, 5 -> ma
                                                  //| nner, 10 -> prep-against, 14 -> opN, 20 -> day, 1 -> quant, 6 -> ARG1-of, 21
                                                  //|  -> month, 9 -> mod, 13 -> ARG2, 2 -> polarity, 17 -> ARG3, 12 -> year, 7 ->
                                                  //|  prep-for, 3 -> name, 18 -> ROOT, 16 -> time, 11 -> ARG1, 8 -> location, 19 
                                                  //| -> ARG0, 4 -> ARG0-of, 15 -> purpose)

}