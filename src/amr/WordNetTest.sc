package amr


object WordNetTest {
Wordnet.stemmer("cause")                          //> res0: List[String] = List(cause)
Wordnet.synonyms("cause")                         //> res1: List[String] = List(campaign, case, causa, cause, crusade, do, drive, e
                                                  //| ffort, get, grounds, have, induce, lawsuit, make, movement, reason, stimulate
                                                  //| , suit)
                                                  
Wordnet.stemmer("possible")                       //> res2: List[String] = List(possible)
Wordnet.synonyms("possible")                      //> res3: List[String] = List(conceivable, imaginable, possible, potential)
Wordnet.stemmer("have")                           //> res4: List[String] = List(have)
Wordnet.synonyms("have")                          //> res5: List[String] = List(accept, bear, birth, cause, consume, deliver, expe
                                                  //| rience, feature, get, give, have, hold, induce, ingest, let, make, own, poss
                                                  //| ess, receive, stimulate, suffer, sustain, take, throw, undergo)
Wordnet.stemmer("conceding")                      //> res6: List[String] = List(concede, conceding)
Wordnet.synonyms("conceding")                     //> res7: List[String] = List(conceding, concession, yielding)
}