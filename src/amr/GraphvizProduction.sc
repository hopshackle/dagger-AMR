package amr

object GraphvizProduction {

ImportConcepts.initialise("C:\\AMR\\Naif.txt")

  ImportConcepts.allSentencesAndAMR.zipWithIndex map {
    case (x, i) =>
      val s = Sentence(x._1, x._2)
      val gv = new GraphViz("C://AMR//GV_val_amr_" + (i + 1) + ".dot", s.amr.get)

      val expert = RunDagger.sampleTrajectory(s, "", new WangXueExpert)
      val gvExpert = new GraphViz("C://AMR//GV_val_expert_" + (i + 1) + ".dot", expert.amr.get)
  }                                               //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.4 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [3.8 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [4.0 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [3.1 sec].
                                                  //| WARNING: Found duplicate match for concept "Arab"
                                                  //| WARNING: Found duplicate match for concept "Arab"
                                                  //| Loading Relations
                                                  //| res0: IndexedSeq[Unit] = Vector(())
  ImportConcepts.allSentencesAndAMR.zipWithIndex map {
    case (x, i) =>
      val s = Sentence(x._1, x._2)
      val gv = new GraphViz("C://AMR//GV_val_dt_" + (i + 1) + ".dot", s.dependencyTree.toAMR)
  }                                               //> WARNING: Found duplicate match for concept "Arab"
                                                  //| res1: IndexedSeq[Unit] = Vector(())
  }
