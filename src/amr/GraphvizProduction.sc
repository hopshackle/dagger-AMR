package amr

object GraphvizProduction {
  val testData = AMRGraph.importFile("C:\\AMR\\Naif.txt")
                                                  //> testData  : IndexedSeq[(String, String)] = Vector((Naif Arab Academy for Secu
                                                  //| rity Sciences is run by an Arab Interior Ministers' Council.,(r / run-01 :ARG
                                                  //| 0 (o / organization :name (n / name :op1 "Arab" :op2 "Interior" :op3 "Ministe
                                                  //| rs'" :op4 "Council")) :ARG1 (u / university :name (n2 / name :op1 "Naif" :op2
                                                  //|  "Arab" :op3 "Academy" :op4 "for" :op5 "Security" :op6 "Sciences")))))
    testData.zipWithIndex map {
    case (x, i) =>
      val s = Sentence(x._1, x._2)
      val gv = new GraphViz("C://AMR//GV_amr_" + (i + 1) + ".dot", s.amr.get)
  }                                               //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.2 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [4.6 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [4.6 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [2.5 sec].
                                                  //| WARNING: Found duplicate match for concept "Arab"
                                                  //| res0: IndexedSeq[Unit] = Vector(())
      testData.zipWithIndex map {
    case (x, i) =>
      val s = Sentence(x._1, x._2)
      val gv = new GraphViz("C://AMR//GV_dt_" + (i + 1) + ".dot", s.dependencyTree.toAMR)
  }                                               //> WARNING: Found duplicate match for concept "Arab"
                                                  //| res1: IndexedSeq[Unit] = Vector(())
}