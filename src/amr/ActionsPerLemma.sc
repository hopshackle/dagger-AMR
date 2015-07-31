package amr

object ActionsPerLemma {

ImportConcepts.initialise("C:\\AMR\\Medvedev2.txt")
ImportConcepts.conceptStrings                     //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.3 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [3.7 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [3.0 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [5.4 sec].
                                                  //| res0: Set[String] = Set(program, Federation, name, Medvedev, Russian, presid
                                                  //| ent, fund-01, date-entity, have-org-role-91, missile, provide-01, country, D
                                                  //| mitry, promise-01, person, nucleus, ROOT)
ImportConcepts.conceptStrings                     //> res1: Set[String] = Set(program, Federation, name, Medvedev, Russian, presid
                                                  //| ent, fund-01, date-entity, have-org-role-91, missile, provide-01, country, D
                                                  //| mitry, promise-01, person, nucleus, ROOT)
(ImportConcepts.conceptsPerLemma map { case (lemma, set) => (lemma, set.size)}).toList.sortWith((a, b) => a._2 > b._2) foreach println
                                                  //> Now calculating expert results
                                                  //| 0.1.0.0 -> 0.1.0.0.0.0
                                                  //| 0.1.0.0 -> 0.1.0.0.0
                                                  //| relationText = mod
                                                  //| Loading Relations
                                                  //| relationIndex = 3
                                                  //| 0.1.0.0.0 -> 0.1.0.0.0.0
                                                  //| relationText = mod
                                                  //| relationIndex = 3
                                                  //| 0.2 -> 0.2.1
                                                  //| relationText = day
                                                  //| relationIndex = 12
                                                  //| 0.2.0 -> 0.2.2
                                                  //| 0.2.0 -> 0.2
                                                  //| 0.2 -> 0.2.2
                                                  //| relationText = year
                                                  //| relationIndex = 5
                                                  //| 0.2 -> 0.2.0
                                                  //| relationText = month
                                                  //| relationIndex = 13
                                                  //| 0.0.1.0.0 -> 0.0.1.0.0.1
                                                  //| relationText = opN
                                                  //| relationIndex = 7
                                                  //| 0.0.1.0 -> 0.0.1.0.0
                                                  //| relationText = name
                                                  //| relationIndex = 1
                                                  //| 0.0.0 -> 0.0.0.0
                                                  //| relationText = opN
                                                  //| relationIndex = 7
                                                  //| 0.0.1 -> 0.0.1.1
                                                  //| relationText = ARG2
                                                  //| relationIndex = 6
                                                  //| 0.0 -> 0.0.0
                                                  //| relationText = name
                                                  //| relationIndex = 1
                                                  //| 0.1.0 -> 0.1.0.0
                                                  //| relationText = ARG1
                                                  //| relationIndex = 4
                                                  //| 0.0.0.1 -> 0.0.1.0.0.0
                                                  //| 0.0.0.1 -> 0.0.1.0
                                                  //| 0.0.0.1 -> 0.0.1
                                                  //| 0.0.0.1 -> 0.0
                                                  //| 0.0.1.0.0 -> 0.0.1.0.0.0
                                                  //| relationText = opN
                                                  //| relationIndex = 7
                                                  //| 0.1.0.0 -> 0.0.1.0
                                                  //| relationText = poss
                                                  //| relationIndex = 8
                                                  //| 0.0 -> 0.0.1
                                                  //| relationText = ARG0-of
                                                  //| relationIndex = 2
                                                  //| 0.1 -> 0.0
                                                  //| relationText = ARG0
                                                  //| relationIndex = 11
                                                  //| 0.1 -> 0.1.0
                                                  //| relationText = ARG1
                                                  //| relationIndex = 4
                                                  //| 0 -> 0.0.0.1
                                                  //| 0 -> 0.1
                                                  //| relationText = ARG1
                                                  //| relationIndex = 4
                                                  //| 0 -> 0.2
                                                  //| relationText = time
                                                  //| relationIndex = 9
                                                  //| 0.0.0 -> 0.0.0.1
                                                  //| relationText = opN
                                                  //| relationIndex = 7
                                                  //| ROOT -> 0
                                                  //| relationText = ROOT
                                                  //| relationIndex = 10
                                                  //| (program,1)
                                                  //| (president,1)
                                                  //| (medvedev,1)
                                                  //| (dmitry,1)
                                                  //| (missile,1)
                                                  //| (nuclear,1)
                                                  //| (provide,1)
                                                  //| (russian,1)
                                                  //| (funding,1)
                                                  //| (federation,1)
                                                  //| (promise,1)
ImportConcepts.edgesPerLemma foreach println      //> (##-OUT,Set())
                                                  //| (president-IN,Set(6))
                                                  //| (dmitry-IN,Set())
                                                  //| (provide-IN,Set(4))
                                                  //| (medvedev-OUT,Set())
                                                  //| (promise-OUT,Set(4, 9))
                                                  //| (provide-OUT,Set(11, 4))
                                                  //| (promise-IN,Set())
                                                  //| (to-OUT,Set())
                                                  //| (federation-OUT,Set())
                                                  //| (dmitry-OUT,Set())
                                                  //| (date-entity-OUT,Set(5, 13, 12))
                                                  //| (missile-IN,Set(3))
                                                  //| (country-IN,Set(8))
                                                  //| (##-IN,Set(5, 12))
                                                  //| (have-org-role-91-OUT,Set(6))
                                                  //| (on-IN,Set())
                                                  //| ('s-IN,Set())
                                                  //| (person-IN,Set(11))
                                                  //| (program-IN,Set(4))
                                                  //| (medvedev-IN,Set())
                                                  //| (name-IN,Set(1))
                                                  //| (funding-OUT,Set(4))
                                                  //| (name-OUT,Set())
                                                  //| (russian-OUT,Set())
                                                  //| (nuclear-OUT,Set())
                                                  //| (date-entity-IN,Set(9))
                                                  //| (may-IN,Set(13))
                                                  //| (to-IN,Set())
                                                  //| ('s-OUT,Set())
                                                  //| (president-OUT,Set())
                                                  //| (for-IN,Set())
                                                  //| (on-OUT,Set())
                                                  //| (federation-IN,Set())
                                                  //| (person-OUT,Set(2, 1))
                                                  //| (funding-IN,Set(4))
                                                  //| (for-OUT,Set())
                                                  //| (may-OUT,Set())
                                                  //| (have-org-role-91-IN,Set(2))
                                                  //| (country-OUT,Set(1))
                                                  //| (nuclear-IN,Set(3))
                                                  //| (russian-IN,Set())
                                                  //| (missile-OUT,Set(3))
                                                  //| (russia-IN,Set())
                                                  //| (program-OUT,Set(8, 3))
                                                  //| (russia-OUT,Set())
ImportConcepts.relationStrings                    //> res2: Set[String] = Set(name, ARG0-of, mod, ARG1, year, ARG2, opN, poss, tim
                                                  //| e, ROOT, ARG0, day, month)
(ImportConcepts.edgesPerLemma map { case (lemma, set) => (lemma, set.size)}).toList.sortWith((a, b) => a._2 > b._2) foreach println
                                                  //> (date-entity-OUT,3)
                                                  //| (promise-OUT,2)
                                                  //| (provide-OUT,2)
                                                  //| (##-IN,2)
                                                  //| (person-OUT,2)
                                                  //| (program-OUT,2)
                                                  //| (president-IN,1)
                                                  //| (provide-IN,1)
                                                  //| (missile-IN,1)
                                                  //| (country-IN,1)
                                                  //| (have-org-role-91-OUT,1)
                                                  //| (person-IN,1)
                                                  //| (program-IN,1)
                                                  //| (name-IN,1)
                                                  //| (funding-OUT,1)
                                                  //| (date-entity-IN,1)
                                                  //| (may-IN,1)
                                                  //| (funding-IN,1)
                                                  //| (have-org-role-91-IN,1)
                                                  //| (country-OUT,1)
                                                  //| (nuclear-IN,1)
                                                  //| (missile-OUT,1)
                                                  //| (##-OUT,0)
                                                  //| (dmitry-IN,0)
                                                  //| (medvedev-OUT,0)
                                                  //| (promise-IN,0)
                                                  //| (to-OUT,0)
                                                  //| (federation-OUT,0)
                                                  //| (dmitry-OUT,0)
                                                  //| (on-IN,0)
                                                  //| ('s-IN,0)
                                                  //| (medvedev-IN,0)
                                                  //| (name-OUT,0)
                                                  //| (russian-OUT,0)
                                                  //| (nuclear-OUT,0)
                                                  //| (to-IN,0)
                                                  //| ('s-OUT,0)
                                                  //| (president-OUT,0)
                                                  //| (for-IN,0)
                                                  //| (on-OUT,0)
                                                  //| (federation-IN,0)
                                                  //| (for-OUT,0)
                                                  //| (may-OUT,0)
                                                  //| (russian-IN,0)
                                                  //| (russia-IN,0)
                                                  //| (russia-OUT,0)
}