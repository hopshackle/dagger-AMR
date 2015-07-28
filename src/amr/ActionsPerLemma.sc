package amr

object ActionsPerLemma {

ImportConcepts.initialise("C:\\AMR\\allies.txt")
ImportConcepts.conceptStrings                     //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.7 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [5.0 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [4.2 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [3.8 sec].
                                                  //| res0: scala.collection.immutable.Set[String] = Set(defend-01, name, fund-01,
                                                  //|  center, bolster-01, agree-01, -, research-01, sign-01, cyber, person, attac
                                                  //| k, ally-01, ROOT, military)
ImportConcepts.conceptStrings                     //> res1: scala.collection.immutable.Set[String] = Set(defend-01, name, fund-01,
                                                  //|  center, bolster-01, agree-01, -, research-01, sign-01, cyber, person, attac
                                                  //| k, ally-01, ROOT, military)
ImportConcepts.conceptsPerLemma.getOrElse("university", List()) foreach (x => println(ImportConcepts.concept(x)))
(ImportConcepts.conceptsPerLemma map { case (lemma, set) => (lemma, set.size)}).toList.sortWith((a, b) => a._2 > b._2) foreach println
                                                  //> (research,1)
                                                  //| (defens,1)
                                                  //| (agreement,1)
                                                  //| (center,1)
                                                  //| (sign,1)
                                                  //| (cyber,1)
                                                  //| (attack,1)
                                                  //| (fund,1)
                                                  //| (ally,1)
                                                  //| (bolster,1)
ImportConcepts.edgesPerLemma foreach println      //> (name,Set(7, 2))
                                                  //| (nato,Set(7))
                                                  //| (have,Set())
                                                  //| (research,Set(4))
                                                  //| (defens,Set(5, 6))
                                                  //| (agreement,Set(6))
                                                  //| (center,Set(6, 4))
                                                  //| (a,Set())
                                                  //| (to,Set())
                                                  //| (sign,Set(10, 9, 6))
                                                  //| (cyber,Set(4))
                                                  //| (person,Set(10, 3, 4))
                                                  //| (attack,Set(5, 4))
                                                  //| (fund,Set(6, 8))
                                                  //| (against,Set())
                                                  //| (military,Set(2, 4))
                                                  //| (ally,Set(3))
                                                  //| (bolster,Set(6, 8))
ImportConcepts.relationStrings                    //> res2: scala.collection.immutable.Set[String] = Set(polarity, name, ARG0-of, 
                                                  //| mod, prep-against, ARG1, opN, purpose, ROOT, ARG0)
(ImportConcepts.edgesPerLemma map { case (lemma, set) => (lemma, set.size)}).toList.sortWith((a, b) => a._2 > b._2) foreach println
                                                  //> (sign,3)
                                                  //| (person,3)
                                                  //| (name,2)
                                                  //| (defens,2)
                                                  //| (center,2)
                                                  //| (attack,2)
                                                  //| (fund,2)
                                                  //| (military,2)
                                                  //| (bolster,2)
                                                  //| (nato,1)
                                                  //| (research,1)
                                                  //| (agreement,1)
                                                  //| (cyber,1)
                                                  //| (ally,1)
                                                  //| (have,0)
                                                  //| (a,0)
                                                  //| (to,0)
                                                  //| (against,0)
}