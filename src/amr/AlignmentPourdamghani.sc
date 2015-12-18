package amr
import edu.cmu.lti.nlp.amr._
object AlignmentPourdamghani {
  val (rawSentence, rawAMR, _) = AMRGraph.importFile("C:\\AMR\\VeniceTarget.txt")(0)
                                                  //> rawSentence  : String = Venice High School history teacher Craig Brandau
                                                  //| rawAMR  : String = (p / person :wiki - :name (n / name :op1 "Craig" :op2 "Br
                                                  //| andau") :ARG0-of (h2 / have-org-role-91 :ARG1 (s / school :wiki "Venice_High
                                                  //| _School_(Los_Angeles)" :name (n2 / name :op1 "Venice" :op2 "High" :op3 "Scho
                                                  //| ol")) :ARG3 (t / teach-01 :ARG0 p :ARG1 (h / history))))
  val amr =AMRGraph(Graph.parse(rawAMR))          //> amr  : amr.AMRGraph = AMRGraph(Map(0.1 -> name, 0.1.0 -> "Craig", 0.2.0.1.2 
                                                  //| -> "School", 0.2.1 -> teach-01, 0.0 -> -, 0.2.0.1.1 -> "High", 0.2.0.1 -> na
                                                  //| me, 0.2.0 -> school, 0.2.0.1.0 -> "Venice", 0 -> person, 0.2 -> have-org-rol
                                                  //| e-91, 0.1.1 -> "Brandau", 0.2.0.0 -> "Venice_High_School_(Los_Angeles)", 0.2
                                                  //| .1.0 -> history),Map(),Map((0.2.0.1,0.2.0.1.1) -> opN, (0.1,0.1.0) -> opN, (
                                                  //| 0.2.1,0) -> ARG0, (0,0.2) -> ARG0-of, (0,0.1) -> name, (0.2,0.2.0) -> ARG1, 
                                                  //| (0.2.0.1,0.2.0.1.0) -> opN, (0.2.1,0.2.1.0) -> ARG1, (0.2,0.2.1) -> ARG3, (0
                                                  //| ,0.0) -> wiki, (0.2.0.1,0.2.0.1.2) -> opN, (0.2.0,0.2.0.0) -> wiki, (0.2.0,0
                                                  //| .2.0.1) -> name, (0.1,0.1.1) -> opN),Map((0.2.0.1,0.2.0.1.1) -> op2, (0.1,0.
                                                  //| 1.0) -> op1, (0.2.1,0) -> ARG0, (0,0.2) -> ARG0-of, (0,0.1) -> name, (0.2,0.
                                                  //| 2.0) -> ARG1, (0.2.0.1,0.2.0.1.0) -> op1, (0.2.1,0.2.1.0) -> ARG1, (0.2,0.2.
                                                  //| 1) -> ARG3, (0,0.0) -> wiki, (0.2.0.1,0.2.0.1.2) -> op3, (0.2.0,0.2.0.0) -> 
                                                  //| wiki, (0.2.0,0.2.0.1) -> name, (0.1,0.1.1) -> op2),List())
  amr.nodes foreach println                       //> (0.1,name)
                                                  //| (0.1.0,"Craig")
                                                  //| (0.2.0.1.2,"School")
                                                  //| (0.2.1,teach-01)
                                                  //| (0.0,-)
                                                  //| (0.2.0.1.1,"High")
                                                  //| (0.2.0.1,name)
                                                  //| (0.2.0,school)
                                                  //| (0.2.0.1.0,"Venice")
                                                  //| (0,person)
                                                  //| (0.2,have-org-role-91)
                                                  //| (0.1.1,"Brandau")
                                                  //| (0.2.0.0,"Venice_High_School_(Los_Angeles)")
                                                  //| (0.2.1.0,history)
  amr.arcs foreach println                        //> ((0.2.0.1,0.2.0.1.1),opN)
                                                  //| ((0.1,0.1.0),opN)
                                                  //| ((0.2.1,0),ARG0)
                                                  //| ((0,0.2),ARG0-of)
                                                  //| ((0,0.1),name)
                                                  //| ((0.2,0.2.0),ARG1)
                                                  //| ((0.2.0.1,0.2.0.1.0),opN)
                                                  //| ((0.2.1,0.2.1.0),ARG1)
                                                  //| ((0.2,0.2.1),ARG3)
                                                  //| ((0,0.0),wiki)
                                                  //| ((0.2.0.1,0.2.0.1.2),opN)
                                                  //| ((0.2.0,0.2.0.0),wiki)
                                                  //| ((0.2.0,0.2.0.1),name)
                                                  //| ((0.1,0.1.1),opN)

  val (rawSentenceT, rawAMRT, _) = AMRGraph.importFile("C:\\AMR\\VeniceTok.txt")(0)
                                                  //> rawSentenceT  : String = Venice High School history teacher Craig Brandau
                                                  //| rawAMRT  : String = (p / person :wiki - :name (n / name :op1 "Craig"~e.5 :op
                                                  //| 2 "Brandau"~e.6) :ARG0-of (h2 / have-org-role-91 :ARG1 (s / school :wiki "Ve
                                                  //| nice_High_School_(Los_Angeles)" :name (n2 / name :op1 "Venice"~e.0 :op2 "Hig
                                                  //| h"~e.1 :op3 "School"~e.2)) :ARG3 (t / teach-01~e.4 :ARG0~e.4 p~e.4 :ARG1 (h 
                                                  //| / history~e.3))))
  val amrT = AMRGraph(Graph.parse(rawAMRT))       //> amrT  : amr.AMRGraph = AMRGraph(Map(0.1 -> name, 0.1.0 -> "Craig"~e.5, 0.2.0
                                                  //| .1.2 -> "School"~e.2, 0.2.1 -> teach-01~e.4, 0.0 -> -, 0.2.0.1.1 -> "High"~e
                                                  //| .1, 0.2.0.1 -> name, 0.2.0 -> school, 0.2.0.1.0 -> "Venice"~e.0, 0 -> person
                                                  //| , 0.2.1.1 -> history~e.3, 0.2 -> have-org-role-91, 0.1.1 -> "Brandau"~e.6, 0
                                                  //| .2.0.0 -> "Venice_High_School_(Los_Angeles)", 0.2.1.0 -> p~e.4),Map(),Map((0
                                                  //| .2.0.1,0.2.0.1.1) -> opN, (0.1,0.1.0) -> opN, (0.2.1,0.2.1.1) -> ARG1, (0,0.
                                                  //| 2) -> ARG0-of, (0,0.1) -> name, (0.2,0.2.0) -> ARG1, (0.2.0.1,0.2.0.1.0) -> 
                                                  //| opN, (0.2.1,0.2.1.0) -> ARG0~e.4, (0.2,0.2.1) -> ARG3, (0,0.0) -> wiki, (0.2
                                                  //| .0.1,0.2.0.1.2) -> opN, (0.2.0,0.2.0.0) -> wiki, (0.2.0,0.2.0.1) -> name, (0
                                                  //| .1,0.1.1) -> opN),Map((0.2.0.1,0.2.0.1.1) -> op2, (0.1,0.1.0) -> op1, (0.2.1
                                                  //| ,0.2.1.1) -> ARG1, (0,0.2) -> ARG0-of, (0,0.1) -> name, (0.2,0.2.0) -> ARG1,
                                                  //|  (0.2.0.1,0.2.0.1.0) -> op1, (0.2.1,0.2.1.0) -> ARG0~e.4, (0.2,0.2.1) -> ARG
                                                  //| 3, (0,0.0) -> wiki, (0.2.0.1,0.2.0.1.2) -> op3, (0.2.0,0.2.0.0) -> wiki, (0.
                                                  //| 2.0,0.2.0.1) -> name, (0.1,0.1.1) -> op2),List())
  amrT.nodes foreach println                      //> (0.1,name)
                                                  //| (0.1.0,"Craig"~e.5)
                                                  //| (0.2.0.1.2,"School"~e.2)
                                                  //| (0.2.1,teach-01~e.4)
                                                  //| (0.0,-)
                                                  //| (0.2.0.1.1,"High"~e.1)
                                                  //| (0.2.0.1,name)
                                                  //| (0.2.0,school)
                                                  //| (0.2.0.1.0,"Venice"~e.0)
                                                  //| (0,person)
                                                  //| (0.2.1.1,history~e.3)
                                                  //| (0.2,have-org-role-91)
                                                  //| (0.1.1,"Brandau"~e.6)
                                                  //| (0.2.0.0,"Venice_High_School_(Los_Angeles)")
                                                  //| (0.2.1.0,p~e.4)
  amrT.arcs foreach println                       //> ((0.2.0.1,0.2.0.1.1),opN)
                                                  //| ((0.1,0.1.0),opN)
                                                  //| ((0.2.1,0.2.1.1),ARG1)
                                                  //| ((0,0.2),ARG0-of)
                                                  //| ((0,0.1),name)
                                                  //| ((0.2,0.2.0),ARG1)
                                                  //| ((0.2.0.1,0.2.0.1.0),opN)
                                                  //| ((0.2.1,0.2.1.0),ARG0~e.4)
                                                  //| ((0.2,0.2.1),ARG3)
                                                  //| ((0,0.0),wiki)
                                                  //| ((0.2.0.1,0.2.0.1.2),opN)
                                                  //| ((0.2.0,0.2.0.0),wiki)
                                                  //| ((0.2.0,0.2.0.1),name)
                                                  //| ((0.1,0.1.1),opN)
  
}