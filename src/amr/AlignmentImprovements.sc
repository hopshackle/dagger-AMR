package amr
import edu.cmu.lti.nlp.amr._

object AlignmentImprovements {

// val (rawSentence, rawAMR) = AMRGraph.importFile("C:\\AMR\\Riyadh-based.txt")(0)
//  val (rawSentence, rawAMR) = AMRGraph.imprtFile("C:\\AMR\\CountryList.txt")(0)
//  val (rawSentence, rawAMR) = AMRGraphimportFile("C:\\AMR\\Lavrov.txt")(0)
  val (rawSentence, rawAMR) = AMRGraph.importFile("C:\\AMR\\HammerTarget.txt")(0)
                                                  //> rawSentence  : String = He looked at me there , with my hammer in my hand , 
                                                  //| my fingers black with engine - grease , bending down over an object which se
                                                  //| emed to him extremely ugly ...
                                                  //| rawAMR  : String = (l / look-01 :ARG0 (h / he) :ARG1 (i / i :ARG0-of (b2 / b
                                                  //| end-01 :destination (d / down) :location (o / object :mod (u / ugly :mod (s 
                                                  //| / seem-01 :ARG2 h) :degree (e2 / extreme)))) :ARG0-of (h4 / have-03 :ARG1 (h
                                                  //| 2 / hammer :poss i) :location (h3 / hand :part-of i)) :ARG0-of (h5 / have-03
                                                  //|  :ARG1 (f / finger :part-of i :ARG1-of (b / black-04 :ARG0 (g / grease :mod 
                                                  //| (e / engine)))))) :location (t / there))
// val (rawSentence, rawAMR) = AMRGraph.importFile("C:\\AMR\\simpleDate.txt")(0)
// val (rawSentence, rawAMR) = AMRGraph.importFile("C:\\AMR\\lobbying.tt")(0)

  //    val tokenisedSentence = rawSentence.split(" ")
  val amr = Graph.parse(rawAMR)                   //> amr  : edu.cmu.lti.nlp.amr.Graph = Graph((look-01 :ARG0 he :ARG1 (i :ARG0-of
                                                  //|  (bend-01 :destination down :location (object :mod (ugly :mod (seem-01 :ARG2
                                                  //|  he) :degree extreme))) :ARG0-of (have-03 :ARG1 (hammer :poss i) :location (
                                                  //| hand :part-of i)) :ARG0-of (have-03 :ARG1 (finger :ARG1-of (black-04 :ARG0 (
                                                  //| grease :mod engine)) :part-of i))) :location there),ArrayBuffer(),Map(0.1.0.
                                                  //| 1.0.1 -> extreme, 0.1.0.1 -> (object :mod (ugly :mod (seem-01 :ARG2 he) :deg
                                                  //| ree extreme)), 0.1.1.0 -> (hammer :poss i), 0.1.2.0.0 -> (black-04 :ARG0 (gr
                                                  //| ease :mod engine)), 0.1 -> (i :ARG0-of (bend-01 :destination down :location 
                                                  //| (object :mod (ugly :mod (seem-01 :ARG2 he) :degree extreme))) :ARG0-of (have
                                                  //| -03 :ARG1 (hammer :poss i) :location (hand :part-of i)) :ARG0-of (have-03 :A
                                                  //| RG1 (finger :ARG1-of (black-04 :ARG0 (grease :mod engine)) :part-of i))), 0.
                                                  //| 1.1 -> (have-03 :ARG1 (hammer :poss i) :location (hand :part-of i)), 0.1.0.0
                                                  //|  -> down, 0.1.0.1.0 -> (ugly :mod (seem-01 :ARG2 he) :degree extreme), 0.1.0
                                                  //| .1.0.0 -> (seem-01 :ARG2 he), 0.1.2.0.0.0.0 -> engine, 0.1.2.0.0.0 -> (greas
                                                  //| e :mod engine), 0.0 -> he, 0.1.0 -> (bend-01 :destination down :location (ob
                                                  //| ject :mod (ugly :mod (seem-01 :ARG2 he) :degree extreme))), 0.1.2.0 -> (fing
                                                  //| er :ARG1-of (black-04 :ARG0 (grease :mod engine)) :part-of i), 0 -> (look-01
                                                  //|  :ARG0 he :ARG1 (i :ARG0-of (bend-01 :destination down :location (object :mo
                                                  //| d (ugly :mod (seem-01 :ARG2 he) :degree extreme))) :ARG0-of (have-03 :ARG1 (
                                                  //| hammer :poss i) :location (hand :part-of i)) :ARG0-of (have-03 :ARG1 (finger
                                                  //|  :ARG1-of (black-04 :ARG0 (grease :mod engine)) :part-of i))) :location ther
                                                  //| e), 0.1.1.1 -> (hand :part-of i), 0.1.2 -> (have-03 :ARG1 (finger :ARG1-of (
                                                  //| black-04 :ARG0 (grease :mod engine)) :part-of i)), 0.2 -> there),Map(e -> en
                                                  //| gine, h5 -> (have-03 :ARG1 (finger :ARG1-of (black-04 :ARG0 (grease :mod eng
                                                  //| ine)) :part-of i)), h -> he, h2 -> (hammer :poss i), t -> there, b -> (black
                                                  //| -04 :ARG0 (grease :mod engine)), b2 -> (bend-01 :destination down :location 
                                                  //| (object :mod (ugly :mod (seem-01 :ARG2 he) :degree extreme))), e2 -> extreme
                                                  //| , s -> (seem-01 :ARG2 he), h4 -> (have-03 :ARG1 (hammer :poss i) :location (
                                                  //| hand :part-of i)), d -> down, g -> (grease :mod engine), i -> (i :ARG0-of (b
                                                  //| end-01 :destination down :location (object :mod (ugly :mod (seem-01 :ARG2 he
                                                  //| ) :degree extreme))) :ARG0-of (have-03 :ARG1 (hammer :poss i) :loc
                                                  //| Output exceeds cutoff limit.
                                                  // ee-01 :ARG0 (person :ARG0-of (have-org-role-91 :ARG1 (organization :name (na

  // val amrGraph = AlignTest.alignWords(rawSentence, amr, false)
    val tokenisedSentence = DependencyTree.preProcess(rawSentence)
                                                  //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.3 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [4.5 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [2.3 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [4.2 sec].
                                                  //| tokenisedSentence  : List[String] = List(He, looked, at, me, there, with, my
                                                  //| , hammer, in, my, hand, my, fingers, black, with, engine-grease, bending, do
                                                  //| wn, over, an, object, which, seemed, to, him, extremely, ugly)
  
  val wordAlignments = AlignWords.alignWords(tokenisedSentence.toArray, amr)
                                                  //> wordAlignments  : Array[Option[edu.cmu.lti.nlp.amr.Node]] = Array(Some(he), 
                                                  //| Some((look-01 :ARG0 he :ARG1 (i :ARG0-of (bend-01 :destination down :locatio
                                                  //| n (object :mod (ugly :mod (seem-01 :ARG2 he) :degree extreme))) :ARG0-of (ha
                                                  //| ve-03 :ARG1 (hammer :poss i) :location (hand :part-of i)) :ARG0-of (have-03 
                                                  //| :ARG1 (finger :ARG1-of (black-04 :ARG0 (grease :mod engine)) :part-of i))) :
                                                  //| location there)), None, None, Some(there), None, None, Some((hammer :poss i)
                                                  //| ), None, None, Some((hand :part-of i)), None, Some((finger :ARG1-of (black-0
                                                  //| 4 :ARG0 (grease :mod engine)) :part-of i)), Some((black-04 :ARG0 (grease :mo
                                                  //| d engine))), None, Some(engine), Some((bend-01 :destination down :location (
                                                  //| object :mod (ugly :mod (seem-01 :ARG2 he) :degree extreme)))), Some(down), N
                                                  //| one, None, Some((object :mod (ugly :mod (seem-01 :ARG2 he) :degree extreme))
                                                  //| ), None, Some((seem-01 :ARG2 he)), None, None, Some(extreme), Some((ugly :mo
                                                  //| d (seem-01 :ARG2 he) :degree extreme)))
 // amrGraph.nodes foreach {case (k, v) => println(k + ":" + v + " -> " + amrGraph.nodeSpans.getOrElse(k, (-1, -1)._1))}
                                               
                                            
 wordAlignments foreach println                   //> Some(he)
                                                  //| Some((look-01 :ARG0 he :ARG1 (i :ARG0-of (bend-01 :destination down :locati
                                                  //| on (object :mod (ugly :mod (seem-01 :ARG2 he) :degree extreme))) :ARG0-of (
                                                  //| have-03 :ARG1 (hammer :poss i) :location (hand :part-of i)) :ARG0-of (have-
                                                  //| 03 :ARG1 (finger :ARG1-of (black-04 :ARG0 (grease :mod engine)) :part-of i)
                                                  //| )) :location there))
                                                  //| None
                                                  //| None
                                                  //| Some(there)
                                                  //| None
                                                  //| None
                                                  //| Some((hammer :poss i))
                                                  //| None
                                                  //| None
                                                  //| Some((hand :part-of i))
                                                  //| None
                                                  //| Some((finger :ARG1-of (black-04 :ARG0 (grease :mod engine)) :part-of i))
                                                  //| Some((black-04 :ARG0 (grease :mod engine)))
                                                  //| None
                                                  //| Some(engine)
                                                  //| Some((bend-01 :destination down :location (object :mod (ugly :mod (seem-01 
                                                  //| :ARG2 he) :degree extreme))))
                                                  //| Some(down)
                                                  //| None
                                                  //| None
                                                  //| Some((object :mod (ugly :mod (seem-01 :ARG2 he) :degree extreme)))
                                                  //| None
                                                  //| Some((seem-01 :ARG2 he))
                                                  //| None
                                                  //| None
                                                  //| Some(extreme)
                                                  //| Some((ugly :mod (seem-01 :ARG2 he) :degree extreme))
                                            
  AlignSpans.alignSpans(tokenisedSentence.toArray, amr, wordAlignments) zip wordAlignments foreach println
                                                  //> (Some(1),Some(he))
                                                  //| (Some(0),Some((look-01 :ARG0 he :ARG1 (i :ARG0-of (bend-01 :destination dow
                                                  //| n :location (object :mod (ugly :mod (seem-01 :ARG2 he) :degree extreme))) :
                                                  //| ARG0-of (have-03 :ARG1 (hammer :poss i) :location (hand :part-of i)) :ARG0-
                                                  //| of (have-03 :ARG1 (finger :ARG1-of (black-04 :ARG0 (grease :mod engine)) :p
                                                  //| art-of i))) :location there)))
                                                  //| (None,None)
                                                  //| (None,None)
                                                  //| (Some(13),Some(there))
                                                  //| (None,None)
                                                  //| (None,None)
                                                  //| (Some(8),Some((hammer :poss i)))
                                                  //| (None,None)
                                                  //| (None,None)
                                                  //| (Some(9),Some((hand :part-of i)))
                                                  //| (None,None)
                                                  //| (Some(10),Some((finger :ARG1-of (black-04 :ARG0 (grease :mod engine)) :part
                                                  //| -of i)))
                                                  //| (Some(11),Some((black-04 :ARG0 (grease :mod engine))))
                                                  //| (None,None)
                                                  //| (Some(12),Some(engine))
                                                  //| (Some(2),Some((bend-01 :destination down :location (object :mod (ugly :mod 
                                                  //| (seem-01 :ARG2 he) :degree extreme)))))
                                                  //| (Some(3),Some(down))
                                                  //| (None,None)
                                                  //| (None,None)
                                                  //| (Some(4),Some((object :mod (ugly :mod (seem-01 :ARG2 he) :degree extreme)))
                                                  //| )
                                                  //| (None,None)
                                                  //| (Some(6),Some((seem-01 :ARG2 he)))
                                                  //| (None,None)
                                                  //| (None,None)
                                                  //| (Some(7),Some(extreme))
                                                  //| (Some(5),Some((ugly :mod (seem-01 :ARG2 he) :degree extreme)))
 val amrG =  AMRGraph(amr)                        //> amrG  : amr.AMRGraph = AMRGraph(Map(0.1 -> i, 0.1.0.1.0.0 -> seem-01, 0.1.2
                                                  //| .0.0.0.0 -> engine, 0.1.2.0.0 -> black-04, 0.1.0 -> bend-01, 0.1.0.1 -> obj
                                                  //| ect, 0.0 -> he, 0.1.2.0.0.0 -> grease, 0.1.0.0 -> down, 0.1.2.0 -> finger, 
                                                  //| 0 -> look-01, 0.1.2 -> have-03, 0.2 -> there, 0.1.1.1 -> hand, 0.1.1.0 -> h
                                                  //| ammer, 0.1.0.1.0 -> ugly, 0.1.1 -> have-03, 0.1.0.1.0.1 -> extreme),Map(0.1
                                                  //| .0.1.0.0 -> (23,24), 0.1.2.0.0.0.0 -> (16,17), 0.1.2.0.0 -> (14,15), 0.1.0 
                                                  //| -> (17,18), 0.1.0.1 -> (21,22), 0.0 -> (1,2), 0.1.0.0 -> (18,19), 0.1.2.0 -
                                                  //| > (13,14), 0 -> (2,3), 0.2 -> (5,6), 0.1.1.1 -> (11,12), 0.1.1.0 -> (8,9), 
                                                  //| 0.1.0.1.0 -> (27,28), 0.1.0.1.0.1 -> (26,27)),Map((0.1.0.1.0.0,0.0) -> ARG2
                                                  //| , (0.1,0.1.0) -> ARG0-of, (0.1,0.1.2) -> ARG0-of, (0.1.2.0,0.1.2.0.0) -> AR
                                                  //| G1-of, (0.1.0,0.1.0.0) -> destination, (0.1.2.0.0.0,0.1.2.0.0.0.0) -> mod, 
                                                  //| (0,0.2) -> location, (0,0.1) -> ARG1, (0.1.0.1.0,0.1.0.1.0.0) -> mod, (0.1.
                                                  //| 1,0.1.1.1) -> location, (0.1.0.1.0,0.1.0.1.0.1) -> degree, (0.1.0,0.1.0.1) 
                                                  //| -> location, (0.1.2.0,0.1) -> part-of, (0.1.1,0.1.1.0) -> ARG1, (0,0.0) -> 
                                                  //| ARG0, (0.1.0.1,0.1.0.1.0) -> mod, (0.1.1.1,0.1) -> part-of, (0.1.2,0.1.2.0)
                                                  //|  -> ARG1, (0.1.1.0,0.1) -> poss, (0.1.2.0.0,0.1.2.0.0.0) -> ARG0, (0.1,0.1.
                                                  //| 1) -> ARG0-of),Map((0.1.0.1.0.0,0.0) -> ARG2, (0.1,0.1.0) -> ARG0-of, (0.1,
                                                  //| 0.1.2) -> ARG0-of, (0.1.2.0,0.1.2.0.0) -> ARG1-of, (0.1.0,0.1.0.0) -> desti
                                                  //| nation, (0.1.2.0.0.0,0.1.2.0.0.0.0) -> mod, (0,0.2) -> location, (0,0.1) ->
                                                  //|  ARG1, (0.1.0.1.0,0.1.0.1.0.0) -> mod, (0.1.1,0.1.1.1) -> location, (0.1.0.
                                                  //| 1.0,0.1.0.1.0.1) -> degree, (0.1.0,0.1.0.1) -> location, (0.1.2.0,0.1) -> p
                                                  //| art-of, (0.1.1,0.1.1.0) -> ARG1, (0,0.0) -> ARG0, (0.1.0.1,0.1.0.1.0) -> mo
                                                  //| d, (0.1.1.1,0.1) -> part-of, (0.1.2,0.1.2.0) -> ARG1, (0.1.1.0,0.1) -> poss
                                                  //| , (0.1.2.0.0,0.1.2.0.0.0) -> ARG0, (0.1,0.1.1) -> ARG0-of),List())
 amrG.nodes.keys.toList.sorted foreach (x => println(s"${x}\t\t${amrG.nodes(x)}\t\t${amrG.nodeSpans.getOrElse(x, "No Mapping")}"))
                                                  //> 0		look-01		(2,3)
                                                  //| 0.0		he		(1,2)
                                                  //| 0.1		i		No Mapping
                                                  //| 0.1.0		bend-01		(17,18)
                                                  //| 0.1.0.0		down		(18,19)
                                                  //| 0.1.0.1		object		(21,22)
                                                  //| 0.1.0.1.0		ugly		(27,28)
                                                  //| 0.1.0.1.0.0		seem-01		(23,24)
                                                  //| 0.1.0.1.0.1		extreme		(26,27)
                                                  //| 0.1.1		have-03		No Mapping
                                                  //| 0.1.1.0		hammer		(8,9)
                                                  //| 0.1.1.1		hand		(11,12)
                                                  //| 0.1.2		have-03		No Mapping
                                                  //| 0.1.2.0		finger		(13,14)
                                                  //| 0.1.2.0.0		black-04		(14,15)
                                                  //| 0.1.2.0.0.0		grease		No Mapping
                                                  //| 0.1.2.0.0.0.0		engine		(16,17)
                                                  //| 0.2		there		(5,6)
                                                                                               
                                                  
                                                  
}