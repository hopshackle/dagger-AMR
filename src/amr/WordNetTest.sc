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

Wordnet.getUsefulStuff("crack")                   //> POS: noun, WordID: WID-09126018-N-??-crack, Lemma: crack, Word: W-09126018-N
                                                  //| -1-crack
                                                  //| Synset: SYNSET{SID-09126018-N : Words[W-09126018-N-1-crack, W-09126018-N-2-c
                                                  //| left, W-09126018-N-3-crevice, W-09126018-N-4-fissure, W-09126018-N-5-scissur
                                                  //| e]}
                                                  //| Hypernyms: 
                                                  //| opening : space : amorphous_shape : shape : attribute : abstraction : abstra
                                                  //| ct_entity : entity : 
                                                  //| 
                                                  //| POS: noun, WordID: WID-03379810-N-??-crack, Lemma: crack, Word: W-03379810-N
                                                  //| -2-crack
                                                  //| Synset: SYNSET{SID-03379810-N : Words[W-03379810-N-1-gap, W-03379810-N-2-cra
                                                  //| ck]}
                                                  //| Hypernyms: 
                                                  //| opening : artifact : whole : object : physical_entity : entity : 
                                                  //| 
                                                  //| POS: noun, WordID: WID-13722072-N-??-crack, Lemma: crack, Word: W-13722072-N
                                                  //| -3-crack
                                                  //| Synset: SYNSET{SID-13722072-N : Words[W-13722072-N-1-crevice, W-13722072-N-2
                                                  //| -cranny, W-13722072-N-3-crack, W-13722072-N-4-fissure, W-13722072-N-5-chap]}
                                                  //| 
                                                  //| Hypernyms: 
                                                  //| depression : concave_shape : solid : shape : attribute : abstraction : abstr
                                                  //| act_entity : entity : 
                                                  //| 
                                                  //| POS: noun, WordID: WID-07280474-N-??-crack, Lemma: crack, Word: W-07280474-N
                                                  //| -1-crack
                                                  //| Synset: SYNSET{SID-07280474-N : Words[W-07280474-N-1-crack, W-07280474-N-2-c
                                                  //| racking, W-07280474-N-3-snap]}
                                                  //| Hypernyms: 
                                                  //| noise : sound : happening : event : psychological_feature : abstraction : ab
                                                  //| stract_entity : entity : 
                                                  //| 
                                                  //| POS: noun, WordID: WID-14293025-N-??-crack, Lemma: crack, Word: W-14293025-N
                                                  //| -2-crack
                                                  //| Synset: SYNSET{SID-14293025-N : Words[W-14293025-N-1-shot, W-14293025-N-2-cr
                                                  //| ack]}
                                                  //| Hypernyms: 
                                                  //| opportunity : possibility : being : state : attribute : abstraction : abstra
                                                  //| ct_entity : entity : 
                                                  //| 
                                                  //| POS: noun, WordID: WID-06677002-N-??-crack, Lemma: crack, Word: W-06677002-N
                                                  //| -2-crack
                                                  //| Synset: SYNSET{SID-06677002-N : Words[W-06677002-N-1-wisecrack, W-06677002-N
                                                  //| -2-crack, W-06677002-N-3-sally, W-06677002-N-4-quip]}
                                                  //| Hypernyms: 
                                                  //| remark : statement : message : communication : abstraction : abstract_entity
                                                  //|  : entity : 
                                                  //| 
                                                  //| POS: noun, WordID: WID-04637591-N-??-crack, Lemma: crack, Word: W-04637591-N
                                                  //| -1-crack
                                                  //| Synset: SYNSET{SID-04637591-N : Words[W-04637591-N-1-crack]}
                                                  //| Hypernyms: 
                                                  //| blemish : appearance : quality : attribute : abstraction : abstract_entity :
                                                  //|  entity : 
                                                  //| 
                                                  //| POS: noun, WordID: WID-03093085-N-??-crack, Lemma: crack, Word: W-03093085-N
                                                  //| -1-crack
                                                  //| Synset: SYNSET{SID-0309308
                                                  //| Output exceeds cutoff limit.
}