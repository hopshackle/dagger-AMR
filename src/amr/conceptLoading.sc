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
initialise("C:\\AMR\\InitialTrainingSet.txt")

conceptStrings                                    //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.5 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [5.7 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [4.3 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [3.1 sec].
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
insertableConcepts                                //> res8: Set[String] = Set(network, city, name, date-entity, have-org-role-91, 
                                                  //| thing, country, person, military, and, govern-01)
conceptsPerLemma                                  //> res9: Map[String,Set[Int]] = Map(chief -> Set(2), network -> Set(3), defense
                                                  //|  -> Set(4), nato -> Set(36), research -> Set(25), defens -> Set(1), agreemen
                                                  //| t -> Set(18), center -> Set(11), considers -> Set(30), specialist -> Set(16)
                                                  //| , strike -> Set(15), operational -> Set(35), formally -> Set(42), training -
                                                  //| > Set(33), computer -> Set(20), provide -> Set(14), open -> Set(29), science
                                                  //|  -> Set(21), base -> Set(40), technology -> Set(23), sign -> Set(26), civili
                                                  //| an -> Set(24), government -> Set(48), after -> Set(27), international -> Set
                                                  //| (28), grow -> Set(38), warfare -> Set(31), cyber -> Set(32), conduct -> Set(
                                                  //| 17), telecom -> Set(46), staff -> Set(37), threat -> Set(10), attack -> Set(
                                                  //| 39, 34), funding -> Set(7), fund -> Set(7), military -> Set(44), ally -> Set
                                                  //| (41), and -> Set(45), bolster -> Set(12))
edgesPerLemma                                     //> res10: Map[String,Set[Int]] = Map(chief -> Set(14, 9, 13, 3, 11), for -> Set
                                                  //| (14, 9, 7, 11, 19), network -> Set(14, 6, 9, 13, 3), defense -> Set(14, 9, 1
                                                  //| 3, 3, 11), in -> Set(14, 9, 12, 16, 11), nato -> Set(14, 9, 3, 11, 19), have
                                                  //|  -> Set(10, 14, 9, 3, 11), lv -> Set(14, 3), germany -> Set(14, 9, 13, 3, 11
                                                  //| ), research -> Set(14, 9, 3, 11, 19), defens -> Set(10, 14, 9, 3, 11), talli
                                                  //| nn -> Set(14, 9, 3, 11, 8), agreement -> Set(14, 9, 3, 11, 19), center -> Se
                                                  //| t(14, 9, 3, 11, 19), considers -> Set(14, 6, 9, 13, 3), specialist -> Set(14
                                                  //| , 1, 9, 13, 17), strike -> Set(14, 9, 12, 3, 16), estonian -> Set(14, 9, 12,
                                                  //|  3, 16), us -> Set(14, 3), it -> Set(14, 3), united -> Set(14, 3), a -> Set(
                                                  //| 14, 9, 3, 11, 19), operational -> Set(16, 19, 21, 12), formally -> Set(11, 5
                                                  //| , 16, 12), training -> Set(14, 1, 9, 13, 17), computer -> Set(14, 6, 9, 13, 
                                                  //| 3), 's -> Set(10, 14, 9, 3, 11), provide -> Set(14, 9, 7, 11, 19), italy -> 
                                                  //| Set(14, 9, 13, 3, 11), de -> Set(14, 3), open -> Set(11, 5, 16, 12), to -> S
                                                  //| et(14, 9, 3, 11, 19), science -> Set(14, 4), base -> Set(14, 9, 3, 11, 8), t
                                                  //| echnology -> Set(14, 4), sign -> Set(14, 9, 3, 11, 19), civilian -> Set(14, 
                                                  //| 6, 9, 13, 3), on -> Set(14, 1, 9, 11, 19), government -> Set(14, 9, 12, 3, 4
                                                  //| ), after -> Set(14, 6, 9, 13, 12), international -> Set(14, 4), grow -> Set(
                                                  //| 14, 6, 9, 13, 3), warfare -> Set(14, 1, 9, 13, 17), cyber -> Set(14, 9, 3, 1
                                                  //| 1, 19), by -> Set(14, 9, 12, 3, 16), will -> Set(14, 9, 12, 11, 19), spain -
                                                  //| > Set(14, 9, 13, 3, 11), conduct -> Set(14, 1, 9, 13, 17), sk -> Set(14, 3),
                                                  //|  from -> Set(14, 9, 13, 3, 11), estonia -> Set(14, 9, 13, 3, 11), august -> 
                                                  //| Set(16, 19, 21, 12), es -> Set(14, 3), telecom -> Set(14, 4), staff -> Set(1
                                                  //| 4, 1, 9, 11, 19), latvia -> Set(14, 9, 13, 3, 11), be -> Set(14, 9, 12, 3, 1
                                                  //| 6), threat -> Set(14, 6, 9, 13, 3), attack -> Set(14, 9, 3, 11, 19), funding
                                                  //|  -> Set(14, 9, 7, 11, 19), fund -> Set(14, 9, 3, 11, 19), ee -> Set(14, 3), 
                                                  //| against -> Set(10, 14, 9, 3, 11), lt -> Set(14, 3), military -> Set(14, 6, 9
                                                  //| , 13, 3), lithuania -> Set(14, 9, 13, 3, 11), slovakia -> Set(14, 9, 13, 3, 
                                                  //| 11), ally -> Set(14, 9, 3, 11, 19), may -> Set(14, 20, 1, 21, 9), of -> Set(
                                                  //| 14, 1, 9, 13, 17), states -> Set(14, 3), and -> Set(14, 9, 13, 11, 19), bols
                                                  //| ter -> Set(10, 14, 9, 3, 11), ## -> Set(21, 12, 16, 11, 19), the -> Set(14, 
                                                  //| 9, 3, 11, 19))
}