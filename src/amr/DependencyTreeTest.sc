package amr

object DependencyTreeTest {
  val tree1 = Sentence("""Hum ! " replied the king .""")
                                                  //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/english
                                                  //| PCFG.ser.gz ... done [1.2 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.distsi
                                                  //| m.crf.ser.gz ... done [4.4 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.distsi
                                                  //| m.crf.ser.gz ... done [2.2 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dist
                                                  //| sim.crf.ser.gz ... done [4.0 sec].
                                                  //| tree1  : amr.Sentence = Sentence(Hum ! " replied the king .,
                                                  //| NodeMap:	Map(1 -> Hum, 101 -> replied, 102 -> the, 103 -> king)
                                                  //| SpanMap:	Map(1 -> (1,2), 101 -> (2,3), 102 -> (3,4), 103 -> (4,5))
                                                  //| Edges:	Map((101,103) -> dobj, (103,102) -> det)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map()
                                                  //| PartsOfSpeech:	Map(1 -> VB, 101 -> VBD, 102 -> DT, 103 -> NN)
                                                  //| DependencyLabels:	Map(102 -> det, 103 -> dobj),None,Map())
}