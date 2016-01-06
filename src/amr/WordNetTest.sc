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

Wordnet.getAncestors("crack", "NN", 10)           //> crack noun
                                                  //| res8: Seq[String] = List(entity, abstract_entity, abstraction, attribute, sh
                                                  //| ape, amorphous_shape, space, opening)
Wordnet.getAncestors("crack", "VB", 10)           //> crack verb
                                                  //| res9: Seq[String] = List(change)
Wordnet.getAncestors("crackly", "RB", 10)         //> crackly adverb
                                                  //| res10: Seq[String] = List()
Wordnet.getAncestors("crack", "DT", 10)           //> res11: Seq[String] = List()
Wordnet.getAncestors("crack", "", 10)             //> res12: Seq[String] = List()
Wordnet.getAncestors("crippling", "JJ", 10)       //> crippling adjective
                                                  //| res13: Seq[String] = List()
Wordnet.getAncestors("crippled", "JJ", 10)        //> crippled adjective
                                                  //| res14: Seq[String] = List()
Wordnet.getAncestors("fun", "JJ", 10)             //> fun adjective
                                                  //| res15: Seq[String] = List()

Wordnet.getAncestors("observe", "VB", 10)         //> observe verb
                                                  //| res16: Seq[String] = List(perceive, sight)
Wordnet.getAncestors("attack", "VB", 10)          //> attack verb
                                                  //| res17: Seq[String] = List(contend)
Wordnet.getAncestors("assault", "VB", 10)         //> assault verb
                                                  //| res18: Seq[String] = List()


}