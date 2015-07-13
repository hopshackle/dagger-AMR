package amr

object HammingLoss {
  val expert = new WangXueExpert                  //> expert  : amr.WangXueExpert = amr.WangXueExpert@504bae78
  val expertSystem = new WangXueTransitionSystem  //> expertSystem  : amr.WangXueTransitionSystem = amr.WangXueTransitionSystem@59
                                                  //| a6e353
  val testData = AMRGraph.importFile("C:\\AMR\\initialTrainingSetAmended.txt")
                                                  //> testData  : IndexedSeq[(String, String)] = Vector((2008-05-14,"(d / date-ent
                                                  //| ity :month 5 :day 14 :year 2008) "), (Estonia (EE); Latvia (LV); Lithuania (
                                                  //| LT); United States (US),"(a / and :op1 (c / country :name (n / name :op1 "Es
                                                  //| tonia")) :op2 (c2 / country :name (n3 / name :op1 "Latvia")) :op3 (c3 / coun
                                                  //| try :name (n5 / name :op1 "Lithuania")) :op4 (c8 / country :name (n14 / name
                                                  //|  :op1 "United" :op2 "States"))) "), (International; Government; Telecom; tec
                                                  //| hnology; science,"(a / and :op1 (i / international) :op2 (g / government-org
                                                  //| anization :ARG0-of (g2 / govern-01)) :op3 (t / telecommunication) :op4 (t2 /
                                                  //|  technology) :op5 (s / science)) "), (NATO CONSIDERS cyber attacks a threat 
                                                  //| to military and civilian computer networks after the Estonian Government was
                                                  //|  struck by cyber attacks in 2007.,"(c / consider-02 :ARG0 (m2 / military :na
                                                  //| me (n / name :op1 "NATO")) :ARG1 (a / attack-01 :mod (c2 / cyber)) :ARG2 (t 
                                                  //| / thing :ARG1-of (t2 / threaten-01 :ARG2 (a5 / and :op1 (n2 / network :mod (
                                                  //| m / military)) :op2 (n4 / network :mod (c3 / civilian)) :mod (c4 / computer)
                                                  //| ))) :time (a3 / after :op1 (s / strike-01 :ARG0 (a4 / attack :mod (c6 / cybe
                                                  //| r)) :ARG1 (g / government-organization :ARG0-of (g2 / govern-01 :ARG1 (c5 / 
                                                  //| country :name (n3 / name :op1 "Estonia")))) :time (d / date-entity :year 200
                                                  //| 7)))) "), (NATO allies have signed an agreement to fund a research center to
                                                  //|  bolster defenses against cyber attacks.,"(s / sign-01 :ARG0 (p / person :AR
                                                  //| G0-of (a / ally-01) :mod (m / military :name (n / name :op1 "NATO"))) :ARG1 
                                                  //| (a2 / agree-01 :ARG0 p :ARG1 (f / fund-01 :ARG0 p :ARG1 (c / center :mod (r 
                                                  //| / research-01)) :purpose (b / bolster-01 :ARG0 p :ARG1 (d / defend-01 :prep-
                                                  //| against (a3 / attack :mod (c2 / cyber))))))) "), (On 14 May 2008 7 NATO alli
                                                  //| es signed an agreement to fund a research center.,"(s / sign-01 :ARG0 (p / p
                                                  //| erson :quant 7 :ARG0-of (a / ally-01) :mod (m / military :name (n / name :op
                                                  //| 1 "NATO"))) :ARG1 (a2 / agree-01 :ARG1 (f / fund-01 :ARG0 p :ARG1 (c / cente
                                                  //| r :mod (r / research-01)))) :time (d / date-entity :month 5 :day 14 :year 20
                                                  //| 08)) "), (The center will bolster NATO's defenses against cyber attacks.,"(b
                                                  //|  / bolster-01 :ARG0 (c / center) :ARG1 (d / defend-01 :ARG0 (m / military :n
                                                  //| ame (n / name :op1 "NATO")) :ARG1 m :prep-against (a / attack-01 :mod (c2 / 
                                                  //| cyber)))) "), (The center will be operational in August 2008.,"(o 
                                                  //| Output exceeds cutoff limit.
  val s0 = Sentence(testData(0)._1, testData(0)._2)
                                                  //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.2 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [4.5 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [3.3 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [2.9 sec].
                                                  //| s0  : amr.Sentence = Sentence(2008-05-14,
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> 2008, 2 -> 5, 3 -> 14)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4))
                                                  //| Edges:	Map((0,1) -> root, (1,3) -> dep, (3,2) -> num)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set(),Some(AMRGraph(Map(0.1 -> 14, 0.0 -> 5, 0 -> date-e
                                                  //| ntity, 0.2 -> 2008, ROOT -> ROOT),Map(0 -> (1,4), 0.0 -> (1,4), 0.1 -> (1,4)
                                                  //| , 0.2 -> (1,4)),Map((0,0.0) -> month, (0,0.1) -> day, (0,0.2) -> year, (ROOT
                                                  //| ,0) -> ROOT))),Map(3 -> 0.1, 2 -> 0.0, 1 -> 0.2, 0 -> ROOT))

  var action = new Array[WangXueAction](26)       //> action  : Array[amr.WangXueAction] = Array(null, null, null, null, null, nul
                                                  //| l, null, null, null, null, null, null, null, null, null, null, null, null, n
                                                  //| ull, null, null, null, null, null, null, null)
  val stateHistory = new Array[WangXueTransitionState](26)
                                                  //> stateHistory  : Array[amr.WangXueTransitionState] = Array(null, null, null, 
                                                  //| null, null, null, null, null, null, null, null, null, null, null, null, null
                                                  //| , null, null, null, null, null, null, null, null, null, null)
  var state = expertSystem.init(s0)               //> state  : amr.WangXueTransitionState = 
                                                  //| NodesToGo:	List(2, 3, 1, 0)
                                                  //| Children:	List()
                                                  //| PartialGraph:	
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> 2008, 2 -> 5, 3 -> 14)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4))
                                                  //| Edges:	Map((0,1) -> root, (1,3) -> dep, (3,2) -> num)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| Mappings:	3 -> 0.1
                                                  //| 2 -> 0.0
                                                  //| 1 -> 0.2
                                                  //| 0 -> ROOT
                                                  //| 
  for (i <- 0 to 25) {
    if (!expertSystem.isTerminal(state)) {
      stateHistory(i) = state
      action(i) = expert.chooseTransition(s0, state)
      state = action(i)(state)
    }                                             //> WARNING: Found duplicate match for concept attack-01
                                                  //| WARNING: Found duplicate match for concept cyber
                                                  //| WARNING: Found duplicate match for concept attack-01
                                                  //| WARNING: Found duplicate match for concept cyber
  }

// action = action filter (_ != null)
  action foreach println                          //> InsertNode: date-entity (Ref: 0)
                                                  //| NextNode: 0 -> UNKNOWN
                                                  //| NextEdge: 16 -> month
                                                  //| NextNode: 5 -> date-entity
                                                  //| Swap
                                                  //| NextNode: 0 -> UNKNOWN
                                                  //| NextEdge: 16 -> month
                                                  //| NextEdge: 15 -> day
                                                  //| NextNode: 5 -> date-entity
                                                  //| Swap
                                                  //| NextNode: 0 -> UNKNOWN
                                                  //| NextEdge: 16 -> month
                                                  //| NextEdge: 15 -> day
                                                  //| NextEdge: 8 -> year
                                                  //| NextNode: 5 -> date-entity
                                                  //| NextEdge: 13 -> ROOT
                                                  //| NextNode: 32 -> ROOT
                                                  //| null
                                                  //| null
                                                  //| null
                                                  //| null
                                                  //| null
                                                  //| null
                                                  //| null
                                                  //| null
                                                  //| null
  val hamming = new WXHammingLoss                 //> hamming  : amr.WXHammingLoss = amr.WXHammingLoss@539d019
  action.size                                     //> res0: Int = 26
    var other = action                            //> other  : Array[amr.WangXueAction] = Array(InsertNode: date-entity (Ref: 0), 
                                                  //| NextNode: 0 -> UNKNOWN, NextEdge: 16 -> month, NextNode: 5 -> date-entity, S
                                                  //| wap, NextNode: 0 -> UNKNOWN, NextEdge: 16 -> month, NextEdge: 15 -> day, Nex
                                                  //| tNode: 5 -> date-entity, Swap, NextNode: 0 -> UNKNOWN, NextEdge: 16 -> month
                                                  //| , NextEdge: 15 -> day, NextEdge: 8 -> year, NextNode: 5 -> date-entity, Next
                                                  //| Edge: 13 -> ROOT, NextNode: 32 -> ROOT, null, null, null, null, null, null, 
                                                  //| null, null, null)
  hamming(null, null, action, other, NextNode(0), NextNode(0))
                                                  //> res1: Double = 0.0
  hamming(null, null, action, other, NextNode(0), NextEdge(0))
                                                  //> res2: Double = 2.0
  hamming(null, null, action, other, NextNode(3), NextNode(0))
                                                  //> res3: Double = 2.0
  hamming(null, null, action, Array(), NextNode(0), NextNode(0))
                                                  //> res4: Double = 17.0
  hamming(null, null, action, other.slice(0, 5), NextNode(0), NextNode(0))
                                                  //> res5: Double = 12.0
   hamming(null, null, action, other.slice(0, 5), Insert(2), Insert(2, "test"))
                                                  //> res6: Double = 12.0
}