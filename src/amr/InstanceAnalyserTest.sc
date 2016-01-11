package amr
import dagger.ml._
import dagger.util._

object InstanceAnalyserTest {
  AMRGraph.setAligner("improved")
  WangXueTransitionSystem.preferKnown = false
  ImportConcepts.initialise("C:\\AMR\\initialTrainingSetAmended.txt")
  val instances = new FileInstances[WangXueAction](List("C:\\AMR\\daggerTest\\InstanceGather\\InstanceTest.txt"),
    (y => WangXueAction.construct(y)), (x => x.name), 100, false)
                                                  //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.6 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [3.9 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [2.0 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [4.6 sec].
                                                  //| Unknown Concept industry
                                                  //| Unknown Concept industry
                                                  //| Unknown Edge sntN
                                                  //| Unknown Edge prep-in
                                                  //| Unknown Edge sntN
                                                  //| Unknown Edge prep-in
                                                  //| Unknown Concept innovate-01
                                                  //| Unknown Concept innovate-01
                                                  //| instances  : dagger.ml.FileInstances[amr.WangXueAction] = FileInstances(Inst
                                                  //| ance:Vector(13:1.0,   [+]NextNode: 0 -> WORD:	0.00000012:1.0,   [+]Nex
                                                  //| tNode: 0 -> WORD:	0.00000011:1.0,   [+]NextNode: 0 -> WORD:	0.000000
                                                  //| 10:1.0,   [+]NextNode: 0 -> WORD:	0.0000009:1.0,   [+]NextNode: 0 -> WORD:
                                                  //| 	0.0000008:1.0,   [+]NextNode: 0 -> WORD:	0.0000007:1.0,   [+]Next
                                                  //| Node: 0 -> WORD:	0.0000006:1.0,   [+]NextNode: 0 -> WORD:	0.000000
                                                  //| 5:1.0,   [+]NextNode: 0 -> WORD:	0.0000004:1.0,   [+]NextNode: 0 -> WORD:
                                                  //| 	0.0000003:1.0,   [+]NextNode: 0 -> WORD:	0.0000002:1.0,   [+]Next
                                                  //| Node: 0 -> WORD:	0.0000001:1.0, 13:1.0,   [+]NextNode: 0 -> WORD:	
                                                  //| 1.00000012:1.0,   [+]NextNode: 0 -> WORD:	1.00000011:1.0,   [+]NextNode: 0
                                                  //|  -> WORD:	1.00000010:1.0,   [+]NextNode: 0 -> WORD:	1.0000009:1.0,  
                                                  //|  [+]NextNode: 0 -> WORD:	1.0000008:1.0,   [+]NextNode: 0 -> WORD:	
                                                  //| 1.0000007:1.0,   [+]NextNode: 0 -> WORD:	1.0000006:1.0,   [+]NextNode: 0 
                                                  //| -> WORD:	1.0000005:1.0,   [+]NextNode: 0 -> WORD:	1.0000004:1.0,  
                                                  //|  [+]NextNode: 0 -> WORD:	1.0000003:1.0,   [+]NextNode: 0 -> WORD:	
                                                  //| 1.0000002:1.0,   [+]NextNode: 0 -> WORD:	1.0000001:1.0, 13:1.0,   [ ]Next
                                                  //| Node: -1 -> LEMMA:	1.00000012:1.0,   [ ]NextNode: -1 -> LEMMA:	1.000000
                                                  //| 11:1.0,   [ ]NextNode: -1 -> LEMMA:	1.00000010:1.0,   [ ]NextNode: -1 -> LEM
                                                  //| MA:	1.0000009:1.0,   [ ]NextNode: -1 -> LEMMA:	1.0000008:1.0,   [ ]Next
                                                  //| Node: -1 -> LEMMA:	1.0000007:1.0,   [ ]NextNode: -1 -> LEMMA:	1.000000
                                                  //| 6:1.0,   [ ]NextNode: -1 -> LEMMA:	1.0000005:1.0,   [ ]NextNode: -1 -> LEMM
                                                  //| A:	1.0000004:1.0,   [ ]NextNode: -1 -> LEMMA:	1.0000003:1.0,   [ ]Next
                                                  //| Output exceeds cutoff limit.

val errorCount = InstanceAnalyser.basicErrorMetrics(instances.iterator)
                                                  //> Unknown Concept industry
                                                  //| Unknown Concept industry
                                                  //| Unknown Edge sntN
                                                  //| Unknown Edge prep-in
                                                  //| Unknown Edge sntN
                                                  //| Unknown Edge prep-in
                                                  //| Unknown Concept innovate-01
                                                  //| Unknown Concept innovate-01
                                                  //| errorCount  : Map[Int,Int] = Map(1 -> 3)
  errorCount                                      //> res0: Map[Int,Int] = Map(1 -> 3)
  val errorDetail = InstanceAnalyser.errorsByActionType[WangXueAction](instances.iterator, (x => x.name))
                                                  //> Unknown Concept industry
                                                  //| Unknown Concept industry
                                                  //| Unknown Edge sntN
                                                  //| Unknown Edge prep-in
                                                  //| Unknown Edge sntN
                                                  //| Unknown Edge prep-in
                                                  //| Unknown Concept innovate-01
                                                  //| Unknown Concept innovate-01
                                                  //| errorDetail  : Map[String,(Int, Seq[Int])] = Map(NextNodeWORD -> (2,WrappedA
                                                  //| rray(0, 2)), NextEdgeARG1 -> (1,WrappedArray(0, 1)))
  errorDetail                                     //> res1: Map[String,(Int, Seq[Int])] = Map(NextNodeWORD -> (2,WrappedArray(0, 2
                                                  //| )), NextEdgeARG1 -> (1,WrappedArray(0, 1)))

  val featureIndex = new MapIndex                 //> featureIndex  : amr.MapIndex = amr.MapIndex@771158fb
  featureIndex.initialiseFromFile("C:\\AMR\\daggerTest\\InstanceGather\\FeatureIndex.txt")

  InstanceAnalyser.featureDescription(instances.iterator, 0, (i => featureIndex.elem(i)), "C://AMR//daggerTest//ia_test.txt")
                                                  //> Unknown Concept industry
                                                  //| Unknown Concept industry
                                                  //| Unknown Edge sntN
                                                  //| Unknown Edge prep-in
                                                  //| Unknown Edge sntN
                                                  //| Unknown Edge prep-in
                                                  //| Unknown Concept innovate-01
                                                  //| Unknown Concept innovate-01
}