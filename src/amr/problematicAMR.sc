package amr

object problematicAMR {
val testData = AMRGraph.importFile("C:\\AMR\\problematicAMR.txt")
                                                  //> testData  : IndexedSeq[(String, String)] = Vector((Israel is widely believed
                                                  //|  to have a stockpile of nuclear weapons.,(b / believe-01 :ARG1 (c / country 
                                                  //| :name (n / name :op1 "Israel") :ARG0-of (h / have-03 :ARG1 (s / stockpile-01
                                                  //|  :ARG0 c :ARG1 (w2 / weapon :mod (n2 / nucleus))))) :degree (w / wide))))
val s1 = Sentence(testData(0)._1, testData(0)._2) //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.3 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [4.6 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [2.0 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [4.8 sec].
                                                  //| ArrayBuffer(0.1)
                                                  //| List(3)
                                                  //| Greedy mapping: Map()
                                                  //| ArrayBuffer(0.1)
                                                  //| AMR Dec: ArrayBuffer(0.1)
                                                  //| DT Inc: List(3)
                                                  //| Random mapping: Map(3 -> 0.1)
                                                  //| ArrayBuffer(0)
                                                  //| List(4)
                                                  //| Greedy mapping: Map()
                                                  //| ArrayBuffer(0)
                                                  //| AMR Dec: ArrayBuffer(0)
                                                  //| DT Inc: List(4)
                                                  //| Random mapping: Map(4 -> 0)
                                                  //| ArrayBuffer(0.0.1.0.0.0)
                                                  //| List(10)
                                                  //| Greedy mapping: Map()
                                                  //| ArrayBuffer(0.0.1.0.0.0)
                                                  //| AMR Dec: ArrayBuffer(0.0.1.0.0.0)
                                                  //| DT Inc: List(10)
                                                  //| Random mapping: Map(10 -> 0.0.1.0.0.0)
                                                  //| ArrayBuffer(0.0.1.0)
                                                  //| List(8)
                                                  //| Greedy mapping: Map()
                                                  //| ArrayBuffer(0.0.1.0)
                                                  //| AMR Dec: ArrayBuffer(0.0.1.0)
                                                  //| DT Inc: List(8)
                                                  //| Random mapping: Map(8 -> 0.0.1.0)
                                                  //| ArrayBuffer(0.0.1.0.0)
                                                  //| List(11)
                                                  //| Greedy mapping: Map()
                                                  //| ArrayBuffer(0.0.1.0.0)
                                                  //| AMR Dec: ArrayBuffer(0.0.1.0.0)
                                                  //| DT Inc: List(11)
                                                  //| Random mapping: Map(11 -> 0.0.1.0.0)
                                                  //| ArrayBuffer(0.0.0, 0.0, 0.0.0.0, 0.0.1)
                                                  //| List(5, 1, 6, 2, 3, 4)
                                                  //| Greedy mapping: Map(1 -> 0.0.0.0)
                                                  //| ArrayBuffer(0.0.0, 0.0, 0.0.1)
                                                  //| AMR Dec: ArrayBuffer(0.0.0, 0.0.1, 0.0)
                                                  //| DT Inc: List(4, 6, 2, 3, 5)
                                                  //| Random mapping: Map(4 -> 0.0.0, 6 -> 0.0.1, 2 -> 0.0)
                                                  //| s1  : amr.Sentence = Sentence(Israel is widely believed to have a stockpile 
                                                  //| of nuclear weapons.,
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> Israel, 2 -> is, 3 -> widely, 4 -> believed,
                                                  //|  5 -> to, 6 -> have, 7 -> a, 8 -> stockpile, 9 -> of, 10 -> nuclear, 11 -> w
                                                  //| eapons)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 5 -> (5,6), 
                                                  //| 6 -> (6,7), 7 -> (7,8), 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,12
                                                  //| ))
                                                  //| Edges:	Map((0,4) -> root, (4,1) -> nsubjpass, (4,2) -> auxpass, (4,3) -
                                                  //| > advmod, (4,6) -> xcomp, (6,5) -> aux, (6,8) -> dobj, (8,7) -> det, (8,9) -
                                                  //| > prep, (9,11) -> pobj, (11,10) -> amod),Some(AMRGraph(Map(0.1 
                                                  //| Output exceeds cutoff limit.
                             
val outputAdv = RunDagger.sampleTrajectory(s1, "C:\\AMR\\WangXueExpert_output.txt", new WangXueExpert)
                                                  //> WARNING: Found duplicate match for concept attack-01
                                                  //| WARNING: Found duplicate match for concept cyber
                                                  //| WARNING: Found duplicate match for concept attack-01
                                                  //| WARNING: Found duplicate match for concept cyber
                                                  //| ArrayBuffer(6)
                                                  //| List(6)
                                                  //| Greedy mapping: Map(6 -> 6)
                                                  //| ArrayBuffer()
                                                  //| Random mapping: Map()
                                                  //| ArrayBuffer(12, 2)
                                                  //| List(2, 12)
                                                  //| Greedy mapping: Map(12 -> 12, 2 -> 2)
                                                  //| ArrayBuffer()
                                                  //| Random mapping: Map()
                                                  //| ArrayBuffer(1)
                                                  //| List(1)
                                                  //| Greedy mapping: Map(1 -> 1)
                                                  //| ArrayBuffer()
                                                  //| Random mapping: Map()
                                                  //| ArrayBuffer(11)
                                                  //| List(11)
                                                  //| Greedy mapping: Map(11 -> 11)
                                                  //| ArrayBuffer()
                                                  //| Random mapping: Map()
                                                  //| ArrayBuffer(3)
                                                  //| List(3)
                                                  //| Greedy mapping: Map(3 -> 3)
                                                  //| ArrayBuffer()
                                                  //| Random mapping: Map()
                                                  //| ArrayBuffer(9)
                                                  //| List(9)
                                                  //| Greedy mapping: Map(9 -> 9)
                                                  //| ArrayBuffer()
                                                  //| Random mapping: Map()
                                                  //| ArrayBuffer(4)
                                                  //| List(4)
                                                  //| Greedy mapping: Map(4 -> 4)
                                                  //| ArrayBuffer()
                                                  //| Random mapping: Map()
                                                  //| ArrayBuffer(8)
                                                  //| List(8)
                                                  //| Greedy mapping: Map(8 -> 8)
                                                  //| ArrayBuffer()
                                                  //| Random mapping: Map()
                                                  //| ArrayBuffer(10)
                                                  //| List(10)
                                                  //| Greedy mapping: Map(10 -> 10)
                                                  //| ArrayBuffer()
                                                  //| Random mapping: Map()
                                                  //| outputAdv  : amr.Sentence = Sentence(Israel is widely believed to have a sto
                                                  //| ckpile of nuclear weapons.,
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> Israel, 2 -> country, 3 -> widely, 4 -> name
                                                  //| , 6 -> have, 8 -> stockpile, 9 -> of, 10 -> nuclear, 11 -> weapons, 12 -> UN
                                                  //| KNOWN)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 6 -> (6,7), 
                                                  //| 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,12), 12 -> (2,3))
                                                  //| Edges:	Map((0,4) -> UNKNOWN, (0,12) -> ROOT, (4,1) -> opN, (4,3) -> UNK
                                                  //| NOWN, (4,6) -> UNKNOWN, (4,9) -> UNKNOWN, (6,8) -> ARG1, (9,11) -> UNKNOWN, 
                                                  //| (11,10) -> mod, (12,2) -> ARG1),Some(AMRGraph(Map(12 -> UNKNOWN, 8 -> stockp
                                                  //| ile, 4 -> name, 11 -> weapons, 9 -> of, 10 -> nuclear, 6 -> have, 1 -> Israe
                                                  //| l, 0 -> ROOT, 2 -> country, 3 -> widely),Map(12 -> (2,3), 8 -> (8,9), 4 -> (
                                                  //| 4,5), 11 -> (11,12), 9 -> (9,10), 10 -> (10,11), 6 -> (6,7), 1 -> (1,2), 2 -
                                                  //| > (2,3), 3 -> (3,4)),Map((4,1) -> opN, (11,10) -> mod, (9,11) -> UNKNOWN, (1
                                                  //| 2,2) -> ARG1, (0,12) -> ROOT, (4,6) -> UNKNOWN, (6,8) -> ARG1, (0,4) -> UNKN
                                                  //| OWN, (4,9) -> UNKNOWN, (4,3) -> UNKNOWN))),Map(0 -> ROOT, 10 -> 10, 1 -> 1, 
                                                  //| 6 -> 6, 9 -> 9, 2 -> 2, 12 -> 12, 3 -> 3, 11 -> 11, 8 -> 8, 4 -> 4))
outputAdv                                         //> res0: amr.Sentence = Sentence(Israel is widely believed to have a stockpile 
                                                  //| of nuclear weapons.,
                                                  //| NodeMap:	Map(0 -> ROOT, 1 -> Israel, 2 -> country, 3 -> widely, 4 -> name
                                                  //| , 6 -> have, 8 -> stockpile, 9 -> of, 10 -> nuclear, 11 -> weapons, 12 -> UN
                                                  //| KNOWN)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 3 -> (3,4), 4 -> (4,5), 6 -> (6,7), 
                                                  //| 8 -> (8,9), 9 -> (9,10), 10 -> (10,11), 11 -> (11,12), 12 -> (2,3))
                                                  //| Edges:	Map((0,4) -> UNKNOWN, (0,12) -> ROOT, (4,1) -> opN, (4,3) -> UNK
                                                  //| NOWN, (4,6) -> UNKNOWN, (4,9) -> UNKNOWN, (6,8) -> ARG1, (9,11) -> UNKNOWN, 
                                                  //| (11,10) -> mod, (12,2) -> ARG1),Some(AMRGraph(Map(12 -> UNKNOWN, 8 -> stockp
                                                  //| ile, 4 -> name, 11 -> weapons, 9 -> of, 10 -> nuclear, 6 -> have, 1 -> Israe
                                                  //| l, 0 -> ROOT, 2 -> country, 3 -> widely),Map(12 -> (2,3), 8 -> (8,9), 4 -> (
                                                  //| 4,5), 11 -> (11,12), 9 -> (9,10), 10 -> (10,11), 6 -> (6,7), 1 -> (1,2), 2 -
                                                  //| > (2,3), 3 -> (3,4)),Map((4,1) -> opN, (11,10) -> mod, (9,11) -> UNKNOWN, (1
                                                  //| 2,2) -> ARG1, (0,12) -> ROOT, (4,6) -> UNKNOWN, (6,8) -> ARG1, (0,4) -> UNKN
                                                  //| OWN, (4,9) -> UNKNOWN, (4,3) -> UNKNOWN))),Map(0 -> ROOT, 10 -> 10, 1 -> 1, 
                                                  //| 6 -> 6, 9 -> 9, 2 -> 2, 12 -> 12, 3 -> 3, 11 -> 11, 8 -> 8, 4 -> 4))
}