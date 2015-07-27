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
                                                  //| im.crf.ser.gz ... done [4.2 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [2.0 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [3.7 sec].
                                                  //| res0: scala.collection.immutable.Set[String] = Set(name, country, -, ROOT, a
                                                  //| nd)
ImportConcepts.conceptStrings                     //> res1: scala.collection.immutable.Set[String] = Set(name, country, -, ROOT, a
                                                  //| nd)
ImportConcepts.conceptsPerLemma.getOrElse("university", List()) foreach (x => println(ImportConcepts.concept(x)))
                                                  //> Map(20 -> 0.0.0, 21 -> 0.1.0, 22 -> 0.1, 18 -> 0.2, 23 -> 0.0, 19 -> 0)
                                                  //| Map(0.1 -> country, 0.0.0 -> name, 0.1.0 -> name, 0.0 -> country, 0.0.0.0 ->
                                                  //|  "Myanmar", 0.2.0.1 -> "People's", 0.2.0 -> name, 0.1.0.0 -> "Thailand", 0 -
                                                  //| > and, 0.2.0.3 -> "Republic", 0.2 -> country, 0.2.0.2 -> "Democratic", ROOT 
                                                  //| -> ROOT, 0.2.0.0 -> "Lao")
                                                  //| Vector((democratic,name), (democratic,name), (democratic,country), (democrat
                                                  //| ic,country), (democratic,country), (democratic,and), (name,name), (name,name
                                                  //| ), (name,country), (name,country), (name,country), (name,and), (myanmar,name
                                                  //| ), (myanmar,name), (myanmar,country), (myanmar,country), (myanmar,country), 
                                                  //| (myanmar,and), (thailand,name), (thailand,name), (thailand,country), (thaila
                                                  //| nd,country), (thailand,country), (thailand,and), (name,name), (name,name), (
                                                  //| name,country), (name,country), (name,country), (name,and), ('s,name), ('s,na
                                                  //| me), ('s,country), ('s,country), ('s,country), ('s,and), (la,name), (la,name
                                                  //| ), (la,country), (la,country), (la,country), (la,and), (country,name), (coun
                                                  //| try,name), (country,country), (country,country), (country,country), (country
                                                  //| ,and), (people,name), (people,name), (people,country), (people,country), (pe
                                                  //| ople,country), (people,and), (mm,name), (mm,name), (mm,country), (mm,country
                                                  //| ), (mm,country), (mm,and), (country,name), (country,name), (country,country)
                                                  //| , (country,country), (country,country), (country,and), (lao,name), (lao,name
                                                  //| ), (lao,country), (lao,country), (lao,country), (lao,and), (country,name), (
                                                  //| country,name), (country,country), (country,country), (country,country), (cou
                                                  //| ntry,and), (th,name), (th,name), (th,country), (th,country), (th,country), (
                                                  //| th,and), (and,name), (and,name), (and,country), (and,country), (and,country)
                                                  //| , (and,and), (republic,name), (republic,name), (republic,country), (republic
                                                  //| ,country), (republic,country), (republic,and))
                                                  //| Map(people -> Vector((people,name), (people,name), (people,country), (people
                                                  //| ,country), (people,country), (people,and)), name -> Vector((name,name), (nam
                                                  //| e,name), (name,country), (name,country), (name,country), (name,and), (name,n
                                                  //| ame), (name,name), (name,country), (name,country), (name,country), (name,and
                                                  //| )), lao -> Vector((lao,name), (lao,name), (lao,country), (lao,country), (lao
                                                  //| ,country), (lao,and)), democratic -> Vector((democratic,name), (democratic,n
                                                  //| ame), (democratic,country), (democratic,country), (democratic,count
                                                  //| Output exceeds cutoff limit.
(ImportConcepts.conceptsPerLemma map { case (lemma, set) => (lemma, set.size)}).toList.sortWith((a, b) => a._2 > b._2) foreach println
                                                  //> ('s,1)
ImportConcepts.edgesPerLemma foreach println      //> (people,Set(2, 1))
                                                  //| (lao,Set(2, 1))
                                                  //| (democratic,Set(2, 1))
                                                  //| (la,Set(2, 1))
                                                  //| ('s,Set(2, 1))
                                                  //| (th,Set(2, 1))
                                                  //| (myanmar,Set(2, 1))
                                                  //| (thailand,Set(2, 1))
                                                  //| (mm,Set(2, 1))
                                                  //| (republic,Set(2, 1))
ImportConcepts.relationStrings                    //> res2: scala.collection.immutable.Set[String] = Set(name, opN, ROOT, polarity
                                                  //| )
(ImportConcepts.edgesPerLemma map { case (lemma, set) => (lemma, set.size)}).toList.sortWith((a, b) => a._2 > b._2) foreach println
                                                  //> (people,2)
                                                  //| (lao,2)
                                                  //| (democratic,2)
                                                  //| (la,2)
                                                  //| ('s,2)
                                                  //| (th,2)
                                                  //| (myanmar,2)
                                                  //| (thailand,2)
                                                  //| (mm,2)
                                                  //| (republic,2)
}