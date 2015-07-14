package amr

object ActionsPerLemma {

ImportConcepts.initialise("C:\\AMR\\initialTrainingSetAmended.txt")

ImportConcepts.conceptStrings                     //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.2 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [4.4 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [3.6 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [6.3 sec].
                                                  //| WARNING: Found duplicate match for concept attack-01
                                                  //| WARNING: Found duplicate match for concept cyber
                                                  //| res0: scala.collection.immutable.Set[String] = Set(defend-01, chief, network
                                                  //| , defense, city, name, fund-01, date-entity, have-org-role-91, "United", thr
                                                  //| eaten-01, center, bolster-01, "Italy", thing, "Estonia", provide-01, strike-
                                                  //| 01, specialize-01, conduct-01, agree-01, country, computer, science, -, tech
                                                  //| nology, civilian, "Lithuania", research-01, sign-01, after, "Spain", interna
                                                  //| tional, open-01, consider-02, warfare, cyber, train-01, "Slovakia", attack-0
                                                  //| 1, operate-01, "Tallinn", person, staff, grow-01, attack, "NATO", base-01, "
                                                  //| States", ally-01, "Germany", "Latvia", formal, ROOT, military, and, telecomm
                                                  //| unication, govern-01, government-organization)
ImportConcepts.conceptsPerLemma foreach println   //> (chief,Set(56, 52, 6, 28, 21, 9, 2, 22, 54, 39, 43, 30, 4))
                                                  //| (for,Set(56, 29, 21, 17, 44, 12, 54, 7))
                                                  //| (network,Set(56, 37, 6, 45, 27, 54, 3, 35, 11, 40, 55, 23, 47, 15))
                                                  //| (defense,Set(56, 52, 6, 28, 21, 9, 2, 22, 54, 39, 43, 30, 4))
                                                  //| (in,Set(37, 6, 22, 59, 54, 18, 16, 40, 8, 58))
                                                  //| (have,Set(37, 46, 29, 1, 6, 21, 13, 12, 54, 7, 50, 43, 55, 30, 47))
                                                  //| (research,Set(56, 29, 21, 17, 44, 12, 54, 7))
                                                  //| (August,Set(8, 12, 41, 54))
                                                  //| (defens,Set(37, 1, 6, 13, 12, 54, 40, 55, 47))
                                                  //| (agreement,Set(56, 29, 21, 17, 44, 12, 54, 7))
                                                  //| (ES,Set(10, 56, 14, 6, 32, 22, 54, 49, 16, 51))
                                                  //| (center,Set(56, 29, 21, 17, 44, 12, 54, 7))
                                                  //| (Spain,Set(10, 56, 14, 6, 32, 22, 54, 49, 16, 51))
                                                  //| (specialist,Set(56, 37, 20, 29, 38, 9, 44, 54, 43, 36, 19))
                                                  //| (US,Set(10, 56, 14, 6, 32, 22, 54, 49, 16, 51))
                                                  //| (strike,Set(37, 6, 22, 59, 54, 18, 16, 40, 8, 58))
                                                  //| (estonian,Set(37, 6, 22, 59, 54, 18, 16, 40, 8, 58))
                                                  //| (International,Set(56, 24, 57, 33, 59, 54, 26, 58))
                                                  //| (Estonia,Set(5, 42, 29, 6, 22, 12, 54, 48, 16))
                                                  //| (it,Set(10, 56, 14, 6, 32, 22, 54, 49, 16, 51))
                                                  //| (NATO,Set(56, 37, 6, 45, 27, 54, 3, 35, 11, 40, 55, 23, 47, 15))
                                                  //| (a,Set(56, 37, 6, 45, 27, 54, 3, 35, 11, 40, 55, 23, 47, 15))
                                                  //| (operational,Set(8, 12, 41, 54))
                                                  //| (formally,Set(53, 34, 12, 54, 8))
                                                  //| (training,Set(56, 37, 20, 29, 38, 9, 44, 54, 43, 36, 19))
                                                  //| (computer,Set(56, 37, 6, 45, 27, 54, 3, 35, 11, 40, 55, 23, 47, 15))
                                                  //| ('s,Set(37, 1, 6, 13, 12, 54, 40, 55, 47))
                                                  //| (provide,Set(56, 29, 21, 17, 44, 12, 54, 7))
                                                  //| (open,Set(53, 34, 12, 54, 8))
                                                  //| (Slovakia,Set(56, 52, 6, 28, 21, 9, 2, 22, 54, 39, 43, 30, 4))
                                                  //| (to,Set(56, 37, 6, 45, 27, 54, 3, 35, 11, 40, 55, 23, 47, 15))
                                                  //| (science,Set(56, 24, 57, 33, 59, 54, 26, 58))
                                                  //| (base,Set(5, 42, 29, 6, 22, 12, 54, 48, 16))
                                                  //| (technology,Set(56, 24, 57, 33, 59, 54, 26, 58))
                                                  //| (Tallinn,Set(5, 42, 29, 6, 22, 12, 54, 48, 16))
                                                  //| (sign,Set(56, 52, 6, 28, 21, 9, 2, 22, 54, 39, 43, 30, 4))
                                                  //| (civilian,Set(56, 37, 6, 45, 27, 54, 3, 35, 11, 40, 55, 23, 47, 15))
                                                  //| (Lithuania,Set(56, 52, 6, 28, 21, 9, 2, 22, 54, 39, 43, 30, 4))
                                                  //| (May,Set(29, 6, 21, 12, 54, 7, 50, 43, 55, 8, 30, 47))
                                                  //| (on,Set(56, 37, 20, 29, 38, 9, 44, 54, 43, 36, 19))
                                                  //| (government,Set(37, 6, 22, 59, 54, 18, 16, 40, 8, 58))
                                                  //| (after,Set(56, 37, 46, 6, 22, 59, 27, 54, 3, 35, 18, 16, 31, 11, 40, 55, 23,
                                                  //|  8, 58, 47, 15))
                                                  //| (grow,Set(56, 37, 6, 45, 27, 54, 3, 35, 11, 40, 55, 23, 47, 15))
                                                  //| Output exceeds cutoff limit.
val t = (ImportConcepts.conceptsPerLemma map { case (lemma, set) => (lemma, set.size)}).toList.sortWith((a, b) => a._2 > b._2)
                                                  //> t  : List[(String, Int)] = List((after,21), (have,15), (network,14), (NATO,1
                                                  //| 4), (a,14), (computer,14), (to,14), (civilian,14), (grow,14), (threat,14), (
                                                  //| CONSIDERS,14), (military,14), (chief,13), (defense,13), (Slovakia,13), (sign
                                                  //| ,13), (Lithuania,13), (Latvia,13), (from,13), (May,12), (fund,12), (ally,12)
                                                  //| , (specialist,11), (training,11), (on,11), (warfare,11), (conduct,11), (of,1
                                                  //| 1), (in,10), (ES,10), (Spain,10), (US,10), (strike,10), (estonian,10), (it,1
                                                  //| 0), (government,10), (cyber,10), (by,10), (Italy,10), (be,10), (attack,10), 
                                                  //| (United,10), (ee,10), (States,10), (Germany,10), (the,10), (DE,10), (defens,
                                                  //| 9), (Estonia,9), ('s,9), (base,9), (Tallinn,9), (against,9), (bolster,9), (f
                                                  //| or,8), (research,8), (agreement,8), (center,8), (International,8), (provide,
                                                  //| 8), (science,8), (technology,8), (will,8), (staff,8), (funding,8), (Telecom,
                                                  //| 8), (and,8), (Government,8), (formally,5), (open,5), (August,4), (operationa
                                                  //| l,4))
ImportConcepts.edgesPerLemma foreach println      //> (chief,Set(6, 0, 54))
                                                  //| (for,Set(0, 54))
                                                  //| (network,Set(6, 0, 54))
                                                  //| (defense,Set(6, 0, 54))
                                                  //| (in,Set(6, 0, 54))
                                                  //| (have,Set(6, 0, 54))
                                                  //| (research,Set(0, 54))
                                                  //| (August,Set(0, 54))
                                                  //| (defens,Set(6, 0, 54))
                                                  //| (agreement,Set(0, 54))
                                                  //| (ES,Set(6, 0, 54))
                                                  //| (center,Set(0, 54))
                                                  //| (Spain,Set(6, 0, 54))
                                                  //| (specialist,Set(0, 54))
                                                  //| (US,Set(6, 0, 54))
                                                  //| (strike,Set(6, 0, 54))
                                                  //| (estonian,Set(6, 0, 54))
                                                  //| (International,Set(54, 0))
                                                  //| (Estonia,Set(6, 0, 54))
                                                  //| (it,Set(6, 0, 54))
                                                  //| (NATO,Set(6, 0, 54))
                                                  //| (a,Set(6, 0, 54))
                                                  //| (operational,Set(0, 54))
                                                  //| (formally,Set(0, 54))
                                                  //| (training,Set(0, 54))
                                                  //| (computer,Set(6, 0, 54))
                                                  //| ('s,Set(6, 0, 54))
                                                  //| (provide,Set(0, 54))
                                                  //| (open,Set(0, 54))
                                                  //| (Slovakia,Set(6, 0, 54))
                                                  //| (to,Set(6, 0, 54))
                                                  //| (science,Set(54, 0))
                                                  //| (base,Set(6, 0, 54))
                                                  //| (technology,Set(54, 0))
                                                  //| (Tallinn,Set(6, 0, 54))
                                                  //| (sign,Set(6, 0, 54))
                                                  //| (civilian,Set(6, 0, 54))
                                                  //| (Lithuania,Set(6, 0, 54))
                                                  //| (May,Set(0, 6, 54))
                                                  //| (on,Set(0, 54))
                                                  //| (government,Set(6, 0, 54))
                                                  //| (after,Set(6, 0, 54))
                                                  //| (grow,Set(6, 0, 54))
                                                  //| (Latvia,Set(6, 0, 54))
                                                  //| (warfare,Set(0, 54))
                                                  //| (cyber,Set(6, 0, 54))
                                                  //| (by,Set(6, 0, 54))
                                                  //| (will,Set(0, 54))
                                                  //| (conduct,Set(0, 54))
                                                  //| (from,Set(6, 0, 54))
                                                  //| (Italy,Set(6, 0, 54))
                                                  //| (staff,Set(0, 54))
                                                  //| (be,Set(6, 0, 54))
                                                  //| (threat,Set(6, 0, 54))
                                                  //| (attack,Set(6, 0, 54))
                                                  //| (funding,Set(0, 54))
                                                  //| (United,Set(6, 0, 54))
                                                  //| (fund,Set(0, 6, 54))
                                                  //| (CONSIDERS,Set(6, 0, 54))
                                                  //| (Telecom,Set(54, 0))
                                                  //| (ee,Set(6, 0, 54))
                                                  //| (against,Set(6, 0, 54))
                                                  //| (military,Set(6, 0, 54))
                                                  //| (ally,Set(0, 6, 54))
                                                  //| (States,Set(6, 0, 54))
                                                  //| (of,Set(0, 54))
                                                  //| (Germany,Set(6, 0, 54))
                                                  //| (and,Set(0, 54))
                                                  //| (bolster,Set(6, 0, 54))
                                                  //| (Government,Set(54, 0))
                                                  //| (the,Set(6, 0, 54))
                                                  //| (DE,Set(6, 0, 54))
ImportConcepts.relationStrings                    //> res1: scala.collection.immutable.Set[String] = Set(quant, polarity, name, AR
                                                  //| G0-of, manner, ARG1-of, prep-for, location, mod, prep-against, ARG1, year, A
                                                  //| RG2, opN, purpose, time, ARG3, ROOT, ARG0, day, month)
}