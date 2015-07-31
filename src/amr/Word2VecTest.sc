
package amr
object Word2VecTest {



    val w2vDict = Word2VecReader.load("C:\\AMR\\glove.6B.50d.txt")
                                                  //> w2vDict  : amr.Word2Vec = amr.Word2Vec@6be46e8f
   
    w2vDict.size                                  //> res0: Int = 400000
    w2vDict.euclidean("the", "and")               //> res1: Double = 2.802192346511321
     w2vDict.euclidean("the", "visitors")         //> res2: Double = 4.879349468322334
  ImportConcepts.initialise("C:\\AMR\\MediumTrainingSet.txt")
  val trainData = (ImportConcepts.allAMR zip ImportConcepts.allSentencesAndAMR) map (all => Sentence(all._2._1, Some(all._1)))
                                                  //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.2 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [2.8 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [7.7 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [1.8 sec].
                                                  //| WARNING: Found duplicate match for concept "for"
                                                  //| WARNING: Found duplicate match for concept "for"
                                                  //| WARNING: Found duplicate match for concept "Arab"
                                                  //| WARNING: Found duplicate match for concept include-91
                                                  //| WARNING: Found duplicate match for concept and
                                                  //| WARNING: Found duplicate match for concept factor
                                                  //| WARNING: Found duplicate match for concept and
                                                  //| WARNING: Found duplicate match for concept internet
                                                  //| WARNING: Found duplicate match for concept include-91
                                                  //| WARNING: Found duplicate match for concept website
                                                  //| WARNING: Found duplicate match for concept "South"
                                                  //| WARNING: Found duplicate match for concept and
                                                  //| WARNING: Found duplicate match for concept "South"
                                                  //| WARNING: Found duplicate match for concept "FIFA"
                                                  //| WARNING: Found duplicate match for concept and
                                                  //| WARNING: Found duplicate match for concept and
                                                  //| WARNING: Found duplicate match for concept dog
                                                  //| WARNING: Found duplicate match for concept and
                                                  //| WARNING: Found duplicate match for concept include-91
                                                  //| WARNING: Found duplicate match for concept include-91
                                                  //| WARNING: Found duplicate match for concept group
                                                  //| WARNING: Found duplicate match for concept group
                                                  //| WARNING: Found duplicate match for concept 5
                                                  //| WARNING: Found duplicate match for concept ban-01
                                                  //| WARNING: Found duplicate match for concept ban-01
                                                  //| WARNING: Found duplicate match for concept "Pakistan"
                                                  //| WARNING: Found duplicate match for concept ban-01
                                                  //| WARNING: Found duplicate match for concept and
                                                  //| WARNING: Found duplicate match for concept include-01
                                                  //| WARNING: Found duplicate match for concept include-01
                                                  //| WARNING: Found duplicate match for concept and
                                                  //| WARNING: Found duplicate match for concept and
                                                  //| WARNING: Found duplicate match for concept and
                                                  //| WARNING: Found duplicate match for concept and
                                                  //| WARNING: Found 
                                                  //| Output exceeds cutoff limit.
  val devData = AMRGraph.importFile("C:\\AMR\\testSet126.txt") map { case (english, amr) => Sentence(english, amr) }
                                                  //> WARNING: Found duplicate match for concept launch-01
                                                  //| WARNING: Found duplicate match for concept missile
                                                  //| WARNING: Found duplicate match for concept state-01
                                                  //| WARNING: Found duplicate match for concept missile
                                                  //| WARNING: Found duplicate match for concept image
                                                  //| WARNING: Found duplicate match for concept missile
                                                  //| WARNING: Found duplicate match for concept missile
                                                  //| WARNING: Found duplicate match for concept missile
                                                  //| WARNING: Found duplicate match for concept image
                                                  //| WARNING: Found duplicate match for concept include-01
                                                  //| WARNING: Found duplicate match for concept include-01
                                                  //| WARNING: Found duplicate match for concept and
                                                  //| WARNING: Found duplicate match for concept include-01
                                                  //| WARNING: Found duplicate match for concept include-01
                                                  //| WARNING: Found duplicate match for concept state-01
                                                  //| WARNING: Found duplicate match for concept "Union"
                                                  //| WARNING: Found duplicate match for concept "for"
                                                  //| WARNING: Found duplicate match for concept "the"
                                                  //| WARNING: Found duplicate match for concept "the"
                                                  //| WARNING: Found duplicate match for concept "the"
                                                  //| WARNING: Found duplicate match for concept "the"
                                                  //| WARNING: Found duplicate match for concept "Mediterranean"
                                                  //| WARNING: Found duplicate match for concept state-01
                                                  //| WARNING: Found duplicate match for concept "the"
                                                  //| WARNING: Found duplicate match for concept "the"
                                                  //| WARNING: Found duplicate match for concept include-01
                                                  //| WARNING: Found duplicate match for concept weapon
                                                  //| WARNING: Found duplicate match for concept weapon
                                                  //| WARNING: Found duplicate match for concept weapon
                                                  //| WARNING: Found duplicate match for concept state-01
                                                  //| WARNING: Found duplicate match for concept state-01
                                                  //| WARNING: Found duplicate match for concept "Union"
                                                  //| WARNING: Found duplicate match for concept "for"
                                                  //| WARNING: Found duplicate match for concept "the"
                                                  //| WARNING: Found duplicate match for concept "the"
                                                  //| WARNING: Found duplicate match for concept "the"
                                                  //| WARNING: Found duplicate match for concept "the"
                                                  //| WARNING: Found duplicate match for concept "Mediterranean"
                                                  //| WARNING: Found duplicate match for concept and
                                                  //| WARNING: Found duplicate match for concept "the"
                                                  //| WARNING: Found duplicate match for concept "the"
                                                  //| WARNING: Found duplicate match for concept "the"
                                                  //| WARNING: Found duplicate match for concept "Israel"
                                                  //| WARNING: Found duplicate match for concept "United"
                                                  //| WARNING: Found duplicate match for co
                                                  //| Output exceeds cutoff limit.
 
  val correctedDevData = RunDagger.replaceLemmasWordnet(devData)
                                                  //> New Lemma launching replaced with launch
                                                  //| New Lemma add replaced with total
                                                  //| New Lemma institute replaced with bring
                                                  //| New Lemma former replaced with late
                                                  //| New Lemma mark replaced with sign
                                                  //| New Lemma revolutionary replaced with radical
                                                  //| New Lemma by replaced with past
                                                  //| New Lemma post replaced with situation
                                                  //| New Lemma appear replaced with look
                                                  //| New Lemma place replaced with point
                                                  //| New Lemma previous replaced with late
                                                  //| New Lemma absent replaced with remove
                                                  //| New Lemma main replaced with chief
                                                  //| New Lemma impact replaced with affect
                                                  //| New Lemma manipulate replaced with control
                                                  //| New Lemma immediately replaced with instantly
                                                  //| New Lemma do replaced with execute
                                                  //| New Lemma maneuver replaced with point
                                                  //| New Lemma test replaced with run
                                                  //| New Lemma middle replaced with center
                                                  //| New Lemma thus replaced with so
                                                  //| New Lemma test replaced with run
                                                  //| New Lemma immediately replaced with instantly
                                                  //| New Lemma destruction replaced with death
                                                  //| New Lemma middle replaced with center
                                                  //| New Lemma agree replaced with hold
                                                  //| New Lemma mass replaced with deal
                                                  //| New Lemma free replaced with release
                                                  //| New Lemma destruction replaced with death
                                                  //| New Lemma free replaced with release
                                                  //| New Lemma agree replaced with hold
                                                  //| New Lemma mass replaced with deal
                                                  //| New Lemma middle replaced with center
                                                  //| New Lemma pursue replaced with engage
                                                  //| New Lemma mass replaced with deal
                                                  //| New Lemma union replaced with join
                                                  //| New Lemma free replaced with release
                                                  //| New Lemma destruction replaced with death
                                                  //| New Lemma declaration replaced with resolve
                                                  //| New Lemma middle replaced with center
                                                  //| New Lemma union replaced with join
                                                  //| New Lemma union replaced with join
                                                  //| New Lemma destruction replaced with death
                                                  //| New Lemma mass replaced with deal
                                                  //| New Lemma delivery replaced with speech
                                                  //| New Lemma union replaced with join
                                                  //| New Lemma step replaced with measure
                                                  //| New Lemma destruction replaced with death
                                                  //| New Lemma mass replaced with deal
                                                  //| New Lemma union replaced with join
                                                  //| New Lemma consider replaced with take
                                                  //| New Lemma country replaced with state
                                                  //| New Lemma declaration replaced with resolve
                                                  //| New Lemma middle replaced with center
                                                  //| New Lemma prime replaced with ground
                                                  //| New Lemma union replaced with join
                                                  //| New Lemma country replaced with state
                                                  //| New Lemma stockpile replaced with reserve
                                                  //| New Lemma believe r
                                                  //| Output exceeds cutoff limit.
 
  correctedDevData foreach (s => {
    println(s.rawText)
    s.dependencyTree.nodeLemmas foreach println
  })                                              //> 2008-07-10
                                                  //| (1,##)
                                                  //| (2,##)
                                                  //| (3,##)
                                                  //| Iran (IR)
                                                  //| (1,iran)
                                                  //| (3,ir)
                                                  //| International; weapons
                                                  //| (1,international)
                                                  //| (3,weapon)
                                                  //| Expert states that Iranian missile launch photo was doctored to show failed 
                                                  //| 4th missile launching.
                                                  //| (5,missile)
                                                  //| (10,to)
                                                  //| (14,missile)
                                                  //| (1,expert)
                                                  //| (6,launch)
                                                  //| (9,doctor)
                                                  //| (13,th)
                                                  //| (2,state)
                                                  //| (12,fail)
                                                  //| (7,photo)
                                                  //| (3,that)
                                                  //| (11,show)
                                                  //| (8,be)
                                                  //| (4,iranian)
                                                  //| (15,launch)
                                                  //| 20080710 director of the London-based International Institute for Strategic 
                                                  //| Studies's non-proliferation program and former US State Department official 
                                                  //| Mark Fitzpatrick stated that an Iranian photograph showing a cluster of miss
                                                  //| ile launches from a desert range was altered to add a 4th missile.
                                                  //| (5,of)
                                                  //| (10,for)
                                                  //| (42,total)
                                                  //| (24,state)
                                                  //| (37,desert)
                                                  //| (25,that)
                                                  //| (14,non-proliferation)
                                                  //| (20,department)
                                                  //| (29,show)
                                                  //| (1,##)
                                                  //| (6,the)
                                                  //| (28,photograph)
                                                  //| (38,range)
                                                  //| (21,official)
                                                  //| (33,missile)
                                                  //| (9,bring)
                                                  //| (13,'s)
                                                  //| (41,to)
                                                  //| (2,##)
                                                  //| (32,of)
                                                  //| (34,launch)
                                                  //| (45,missile)
                                                  //| (17,late)
                                                  //| (22,sign)
                                                  //| (44,th)
                                                  //| (27,iranian)
                                                  //| (12,studies)
                                                  //| (7,london-based)
                                                  //| (39,be)
                                                  //| (3,##)
                                                  //| (35,from)
                                                  //| (18,us)
                                                  //| (16,and)
                                                  //| (31,cluster)
                                                  //| (11,strategic)
                                                  //| (43,a)
                                                  //| (40,alter)
                                                  //| (26,a)
                                                  //| (23,fitzpatrick)
                                                  //| (8,international)
                                                  //| (36,a)
                                                  //| (30,a)
                                                  //| (19,state)
                                                  //| (4,director)
                                                  //| (15,program)
                                                  //| The image was posted 20080709 on the Sepah news site, which is run by Iran's
                                                  //|  Revolutionary Guards.
                                                  //| (5,##)
                                                  //| (10,sepah)
                                                  //| (14,which)
                                                  //| (20,radical)
                                                  //| (1,the)
                                                  //| (6,##)
                                                  //| (21,guards)
                                                  //| (9,the)
                                                  //| (2,image)
                                                  //| (17,past)
                                                  //| (12,site)
                                                  //| (7,##)
                                                  //| (3,be)
                                                  //| (18,iran)
                                                  //| (16,run)
                                                  //| (11,news)
                                                  //| (8,on)
                                                  //| (19,'s)
                                                  //| (4,situation)
                                                  //| (15,be)
                                                  //| 20080710 the image was replaced with an image of the same 3 missiles in the 
                                                  //| previous photo but in place of the 4th missile is a grounded missile and wha
                                                  //| t appears to be a vehicle nearby.
                                                  //| (5,image)
                                                  //| (10,image)
                                                  //| (24,the)
                                                  //| (37,vehicle)
                                                  //| (25,th)
                                                  //| (14,##)
                                                  //| (20,but)
                                                  //| (29,grounded)
                                                  //| (1,##)
                                                  //| (6,be)
                                                  //| (28,a)
                                                  //| (38,nearby)
                                                  //| (21,in)
                                                  //| (33,look)
                                                  //| (9,a)
                                                  //| (13,same)
                                                  //| (2,##)
                                                  //| (32,what)
                                                  //| (34,to)
                                                  //| (17,the)
                                                  //| (22,point)
                                                  //| (27,be)
                                                  //| (12,the)
                                                  //| (7,replace)
                                                  //| (3,##)
                                                  //| (35,be)

}