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
initialise("C:\\AMR\\Medvedev.txt")

conceptStrings                                    //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.3 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [4.8 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [3.2 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [2.7 sec].
                                                  //| res4: Set[String] = Set(program, Federation, name, Medvedev, Russian, presid
                                                  //| ent, fund-01, date-entity, have-org-role-91, missile, provide-01, country, D
                                                  //| mitry, promise-01, person, nucleus, ROOT)
conceptStrings                                    //> res5: Set[String] = Set(program, Federation, name, Medvedev, Russian, presid
                                                  //| ent, fund-01, date-entity, have-org-role-91, missile, provide-01, country, D
                                                  //| mitry, promise-01, person, nucleus, ROOT)
relationStrings                                   //> Loading Relations
                                                  //| res6: Set[String] = Set(name, ARG0-of, mod, ARG1, year, ARG2, opN, poss, tim
                                                  //| e, ROOT, ARG0, day, month)
relationMaster                                    //> res7: scala.collection.immutable.Map[Int,String] = Map(0 -> UNKNOWN, 5 -> ye
                                                  //| ar, 10 -> ROOT, 1 -> name, 6 -> ARG2, 9 -> time, 13 -> month, 2 -> ARG0-of, 
                                                  //| 12 -> day, 7 -> opN, 3 -> mod, 11 -> ARG0, 8 -> poss, 4 -> ARG1)
insertableConcepts                                //> Now calculating expert results
                                                  //| res8: Map[String,Set[String]] = Map(name -> Set(country, person), president 
                                                  //| -> Set(have-org-role-91), medvedev -> Set(), have-org-role-91 -> Set(person)
                                                  //| , dmitry -> Set(), russian -> Set(), federation -> Set(), may -> Set(date-en
                                                  //| tity), ## -> Set(date-entity))
conceptsPerLemma                                  //> res9: Map[String,Set[Int]] = Map(program -> Set(1), president -> Set(6), med
                                                  //| vedev -> Set(4), dmitry -> Set(13), missile -> Set(10), nuclear -> Set(16), 
                                                  //| provide -> Set(11), russian -> Set(5), funding -> Set(7), federation -> Set(
                                                  //| 2), promise -> Set(14))
edgesPerLemma                                     //> res10: Map[String,Set[Int]] = Map(##-OUT -> Set(), president-IN -> Set(6), d
                                                  //| mitry-IN -> Set(), provide-IN -> Set(4), medvedev-OUT -> Set(), promise-OUT 
                                                  //| -> Set(4, 9), provide-OUT -> Set(11, 4), promise-IN -> Set(), to-OUT -> Set(
                                                  //| ), federation-OUT -> Set(), dmitry-OUT -> Set(), date-entity-OUT -> Set(5, 1
                                                  //| 3, 12), missile-IN -> Set(3), country-IN -> Set(8), ##-IN -> Set(5, 12), hav
                                                  //| e-org-role-91-OUT -> Set(6), on-IN -> Set(), 's-IN -> Set(), person-IN -> Se
                                                  //| t(11), program-IN -> Set(4), medvedev-IN -> Set(), name-IN -> Set(1), fundin
                                                  //| g-OUT -> Set(4), name-OUT -> Set(), russian-OUT -> Set(), nuclear-OUT -> Set
                                                  //| (), date-entity-IN -> Set(9), may-IN -> Set(13), to-IN -> Set(), 's-OUT -> S
                                                  //| et(), president-OUT -> Set(), for-IN -> Set(), on-OUT -> Set(), federation-I
                                                  //| N -> Set(), person-OUT -> Set(2, 1), funding-IN -> Set(4), for-OUT -> Set(),
                                                  //|  may-OUT -> Set(), have-org-role-91-IN -> Set(2), country-OUT -> Set(1), nuc
                                                  //| lear-IN -> Set(3), russian-IN -> Set(), missile-OUT -> Set(3), russia-IN -> 
                                                  //| Set(), program-OUT -> Set(8, 3), russia-OUT -> Set())
}