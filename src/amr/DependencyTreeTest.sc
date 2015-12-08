package amr

object DependencyTreeTest {
  val tree1 = Sentence("""I came. I saw. I conquered.""")
                                                  //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/english
                                                  //| PCFG.ser.gz ... done [1.6 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.distsi
                                                  //| m.crf.ser.gz ... done [4.9 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.distsi
                                                  //| m.crf.ser.gz ... done [4.1 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dist
                                                  //| sim.crf.ser.gz ... done [7.4 sec].
                                                  //| tree1  : amr.Sentence = Sentence(I came. I saw. I conquered.,
                                                  //| NodeMap:	Map(1 -> I, 2 -> came, 4 -> multi-sentence, 101 -> I, 102 -> saw
                                                  //| , 201 -> I, 202 -> conquered)
                                                  //| SpanMap:	Map(1 -> (1,2), 2 -> (2,3), 4 -> (7,8), 101 -> (3,4), 102 -> (4,
                                                  //| 5), 201 -> (5,6), 202 -> (6,7))
                                                  //| Edges:	Map((2,1) -> nsubj, (4,2) -> snt1, (4,102) -> snt2, (4,202) -> s
                                                  //| nt3, (102,101) -> nsubj, (202,201) -> nsubj)
                                                  //| InsertedNodes:	Map()
                                                  //| MergedNodes:	Map()
                                                  //| SwappedArcs:	Set()
                                                  //| DeletedNodes:	Map()
                                                  //| PartsOfSpeech:	Map(101 -> PRP, 202 -> VBD, 1 -> PRP, 201 -> PRP, 102 ->
                                                  //|  VBD, 2 -> VBD, 4 -> )
                                                  //| DependencyLabels:	Map(101 -> nsubj, 202 -> snt3, 1 -> nsubj, 201 -> nsubj,
                                                  //|  102 -> snt2, 2 -> snt1),None,Map())
}