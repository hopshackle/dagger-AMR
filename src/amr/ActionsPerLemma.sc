package amr

object ActionsPerLemma {

ImportConcepts.initialise("C:\\AMR\\militaryNetwork.txt")
ImportConcepts.conceptStrings                     //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.1 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [5.0 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [3.0 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [5.9 sec].
                                                  //| WARNING: Found duplicate match for concept attack-01
                                                  //| WARNING: Found duplicate match for concept cyber
                                                  //| res0: scala.collection.immutable.Set[String] = Set(network, name, date-entit
                                                  //| y, threaten-01, thing, strike-01, country, computer, -, civilian, after, con
                                                  //| sider-02, cyber, attack-01, attack, ROOT, military, and, govern-01, governme
                                                  //| nt-organization)
ImportConcepts.conceptStrings                     //> res1: scala.collection.immutable.Set[String] = Set(network, name, date-entit
                                                  //| y, threaten-01, thing, strike-01, country, computer, -, civilian, after, con
                                                  //| sider-02, cyber, attack-01, attack, ROOT, military, and, govern-01, governme
                                                  //| nt-organization)
ImportConcepts.conceptsPerLemma.getOrElse("university", List()) foreach (x => println(ImportConcepts.concept(x)))
                                                  //> Map(24 -> 0.3.0.2, 25 -> 0.2.0.0.1, 29 -> 0.0.0, 28 -> 0.3.0.1.0, 27 -> 0.3.
                                                  //| 0.1.0.0, 26 -> 0.3.0.1.0.0.0, 30 -> 0.2)
                                                  //| Map(0.1 -> attack-01, 0.0.0 -> name, 0.3.0.1.0.0 -> country, 0.2.0.0.0 -> ne
                                                  //| twork, 0.1.0 -> cyber, 0.2.0.0.1.0 -> civilian, 0.3.0.2.0 -> 2007, 0.0 -> mi
                                                  //| litary, 0.2.0.0.1 -> network, 0.3.0.1.0 -> govern-01, 0.0.0.0 -> "NATO", 0.2
                                                  //| .0 -> threaten-01, 0.3.0.0 -> attack, 0.3.0 -> strike-01, 0.3 -> after, 0.2.
                                                  //| 0.0.0.0 -> military, 0.2.0.0.2 -> computer, 0 -> consider-02, 0.3.0.1 -> gov
                                                  //| ernment-organization, 0.2 -> thing, 0.3.0.2 -> date-entity, 0.3.0.1.0.0.0.0 
                                                  //| -> "Estonia", ROOT -> ROOT, 0.2.0.0 -> and, 0.3.0.1.0.0.0 -> name, 0.3.0.0.0
                                                  //|  -> cyber)
                                                  //| Vector((a,name), (civilian,name), (date-entity,name), (network,name), (the,n
                                                  //| ame), (cyber,name), (name,name), (nato,name), (threat,name), (govern-01,name
                                                  //| ), (attack,name), (and,name), (after,name), (considers,name), (be,name), (in
                                                  //| ,name), (country,name), (network,name), (to,name), (cyber,name), (strike,nam
                                                  //| e), (government,name), (computer,name), (name,name), (##,name), (military,na
                                                  //| me), (thing,name), (by,name), (attack,name), (estonian,name))
                                                  //| Map(network -> Vector((network,name), (network,name)), name -> Vector((name,
                                                  //| name), (name,name)), in -> Vector((in,name)), nato -> Vector((nato,name)), d
                                                  //| ate-entity -> Vector((date-entity,name)), considers -> Vector((considers,nam
                                                  //| e)), strike -> Vector((strike,name)), estonian -> Vector((estonian,name)), a
                                                  //|  -> Vector((a,name)), thing -> Vector((thing,name)), country -> Vector((coun
                                                  //| try,name)), computer -> Vector((computer,name)), to -> Vector((to,name)), ci
                                                  //| vilian -> Vector((civilian,name)), government -> Vector((government,name)), 
                                                  //| after -> Vector((after,name)), cyber -> Vector((cyber,name), (cyber,name)), 
                                                  //| by -> Vector((by,name)), be -> Vector((be,name)), threat -> Vector((threat,n
                                                  //| ame)), attack -> Vector((attack,name), (attack,name)), military -> Vector((m
                                                  //| ilitary,name)), and -> Vector((and,name)), govern-01 -> Vector((govern-01,na
                                                  //| me)), ## -> Vector((##,name)), the -> Vector((the,name)))
                                                  //| Map(network -> Vector(name, name), name -> Vector(name, name), in -> Vector(
                                                  //| name), nato -> Vector(name), date-entity -> Vector(name), considers -> Vecto
                                                  //| r(name), strike -> Vector(name), estonian -> Vector(name), a -> Vector(name)
                                                  //| , thing -> Vector(name), country -> Vector(name), computer -> Vector(name), 
                                                  //| to -> Vector(name), civilian -> Vect
                                                  //| Output exceeds cutoff limit.
(ImportConcepts.conceptsPerLemma map { case (lemma, set) => (lemma, set.size)}).toList.sortWith((a, b) => a._2 > b._2) foreach println
                                                  //> (attack,2)
                                                  //| (network,1)
                                                  //| (considers,1)
                                                  //| (strike,1)
                                                  //| (computer,1)
                                                  //| (civilian,1)
                                                  //| (government,1)
                                                  //| (after,1)
                                                  //| (cyber,1)
                                                  //| (threat,1)
                                                  //| (military,1)
                                                  //| (and,1)
ImportConcepts.edgesPerLemma foreach println      //> (network,Set(5, 10, 6, 9, 2, 12, 7, 3, 8, 4))
                                                  //| (nato,Set(5, 10, 6, 9, 2, 12, 7, 3, 8, 4))
                                                  //| (considers,Set(5, 10, 6, 9, 2, 12, 7, 3, 8, 4))
                                                  //| (strike,Set(5, 10, 6, 9, 2, 12, 7, 3, 8, 4))
                                                  //| (estonian,Set(5, 10, 6, 9, 2, 12, 7, 3, 8, 4))
                                                  //| (computer,Set(5, 10, 6, 9, 2, 12, 7, 3, 8, 4))
                                                  //| (civilian,Set(5, 10, 6, 9, 2, 12, 7, 3, 8, 4))
                                                  //| (government,Set(5, 10, 6, 9, 2, 12, 7, 3, 8, 4))
                                                  //| (after,Set(5, 10, 6, 9, 2, 12, 7, 3, 8, 4))
                                                  //| (cyber,Set(5, 10, 6, 9, 2, 12, 7, 3, 8, 4))
                                                  //| (by,Set(5, 10, 6, 9, 2, 12, 7, 3, 8, 4))
                                                  //| (be,Set(5, 10, 6, 9, 2, 12, 7, 3, 8, 4))
                                                  //| (threat,Set(5, 10, 6, 9, 2, 12, 7, 3, 8, 4))
                                                  //| (attack,Set(5, 10, 6, 9, 2, 12, 7, 3, 8, 4))
                                                  //| (military,Set(5, 10, 6, 9, 2, 12, 7, 3, 8, 4))
                                                  //| (##,Set(5, 10, 6, 9, 2, 12, 7, 3, 8, 4))
ImportConcepts.relationStrings                    //> res2: scala.collection.immutable.Set[String] = Set(polarity, name, ARG0-of, 
                                                  //| ARG1-of, mod, ARG1, year, ARG2, opN, time, ROOT, ARG0)
(ImportConcepts.edgesPerLemma map { case (lemma, set) => (lemma, set.size)}).toList.sortWith((a, b) => a._2 > b._2) foreach println
                                                  //> (network,10)
                                                  //| (nato,10)
                                                  //| (considers,10)
                                                  //| (strike,10)
                                                  //| (estonian,10)
                                                  //| (computer,10)
                                                  //| (civilian,10)
                                                  //| (government,10)
                                                  //| (after,10)
                                                  //| (cyber,10)
                                                  //| (by,10)
                                                  //| (be,10)
                                                  //| (threat,10)
                                                  //| (attack,10)
                                                  //| (military,10)
                                                  //| (##,10)
}