package amr

object ImportConceptsTest {
AMRGraph.setAligner("Pourdamghani")
"Sd|fr".split("[|]")                              //> res0: Array[String] = Array(Sd, fr)
ImportConcepts.initialise("C://AMR//deft-p2-amr-training-misc.txt")
ImportConcepts.loadFromFile = true
ImportConcepts.conceptsPerLemma.head              //> res1: (String, Set[Int]) = (jaleo,Set(3630))
ImportConcepts.insertableConcepts.head            //> res2: (String, Set[String]) = (serious,Set(doubt-01))
ImportConcepts.edgesPerLemma.head                 //> res3: (String, Set[Int]) = (internal-IN,Set(13, 24))
ImportConcepts.subInsertableConcepts.head         //> res4: (String, Set[String]) = (serious,Set(serious-02))
ImportConcepts.wikifications.head                 //> res5: (String, String) = (work-of-art:Le_Fantome_de_la_Liberte,The_Phantom_o
                                                  //| f_Liberty)
ImportConcepts.concept(60)                        //> res6: String = sunday
}