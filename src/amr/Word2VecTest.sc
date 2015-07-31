
package amr
object Word2VecTest {

io.Source.fromFile("C:\\AMR\\glove.6B.50d.txt").getLines()
                                                  //> res0: Iterator[String] = non-empty iterator

"CPD" contains "C"                                //> res1: Boolean = true
    val w2vDict = Word2VecReader.load("C:\\AMR\\glove.6B.50d.txt")
                                                  //> w2vDict  : amr.Word2Vec = amr.Word2Vec@1936f0f5
   
    w2vDict.size                                  //> res2: Int = 400000
    w2vDict.euclidean("the", "and")               //> res3: Double = 2.802192346511321
     w2vDict.euclidean("the", "visitors")         //> res4: Double = 4.879349468322334
  ImportConcepts.initialise("C:\\AMR\\LargeTrainingSet.txt")
  val trainData = (ImportConcepts.allAMR zip ImportConcepts.allSentencesAndAMR) map (all => Sentence(all._2._1, Some(all._1)))
                                                  //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.2 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [5.7 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [1.8 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [5.4 sec].
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
 
  val correctedDevData = RunDagger.replaceLemmas(devData, "C://AMR//Glove.6B.50d.txt")
                                                  //> New Lemma ir replaced with combate
                                                  //| New Lemma doctor replaced with boy
                                                  //| New Lemma th replaced with marks
                                                  //| New Lemma photo replaced with picture
                                                  //| New Lemma launching replaced with launch
                                                  //| New Lemma add replaced with instead
                                                  //| New Lemma desert replaced with mountainous
                                                  //| New Lemma non-proliferation replaced with proliferation
                                                  //| New Lemma photograph replaced with picture
                                                  //| New Lemma institute replaced with sciences
                                                  //| New Lemma th replaced with marks
                                                  //| New Lemma studies replaced with study
                                                  //| New Lemma london-based replaced with montreal-based
                                                  //| New Lemma cluster replaced with larger
                                                  //| New Lemma alter replaced with define
                                                  //| New Lemma fitzpatrick replaced with jennings
                                                  //| New Lemma sepah replaced with multi-billion
                                                  //| New Lemma which replaced with the
                                                  //| New Lemma revolutionary replaced with struggle
                                                  //| New Lemma guards replaced with guard
                                                  //| New Lemma post replaced with office
                                                  //| New Lemma vehicle replaced with carry
                                                  //| New Lemma th replaced with marks
                                                  //| New Lemma grounded replaced with suddenly
                                                  //| New Lemma nearby replaced with near
                                                  //| New Lemma appear replaced with yet
                                                  //| New Lemma same replaced with only
                                                  //| New Lemma what replaced with think
                                                  //| New Lemma replace replaced with put
                                                  //| New Lemma previous replaced with earlier
                                                  //| New Lemma with replaced with and
                                                  //| New Lemma photo replaced with picture
                                                  //| New Lemma absent replaced with yet
                                                  //| New Lemma with replaced with and
                                                  //| New Lemma archive replaced with website
                                                  //| New Lemma sepah replaced with multi-billion
                                                  //| New Lemma page replaced with read
                                                  //| New Lemma photo replaced with picture
                                                  //| New Lemma obscure replaced with describe
                                                  //| New Lemma fitzpatrick replaced with jennings
                                                  //| New Lemma impact replaced with significant
                                                  //| New Lemma probably replaced with actually
                                                  //| New Lemma demonstration replaced with protest
                                                  //| New Lemma manipulate replaced with expose
                                                  //| New Lemma th replaced with marks
                                                  //| New Lemma photo replaced with picture
                                                  //| New Lemma maneuver replaced with task
                                                  //| New Lemma enhanced replaced with availability
                                                  //| New Lemma version replaced with original
                                                  //| New Lemma which replaced with the
                                                  //| New Lemma middle replaced with rest
                                                  //| New Lemma mile replaced with above
                                                  //| New Lemma enhanced replaced with availability
                                                  //| New Lemma could replaced with because
                                                  //| New Lemma thus replaced with means
                                                  //| New Lemma israel replaced with syria
                                                  //| New Lemma sy replaced with tar
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
                                                  //| (3,combate)
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
                                                  //| (9,boy)
                                                  //| (13,marks)
                                                  //| (2,state)
                                                  //| (12,fail)
                                                  //| (7,picture)
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
                                                  //| (42,instead)
                                                  //| (24,state)
                                                  //| (37,mountainous)
                                                  //| (25,that)
                                                  //| (14,proliferation)
                                                  //| (20,department)
                                                  //| (29,show)
                                                  //| (1,##)
                                                  //| (6,the)
                                                  //| (28,picture)
                                                  //| (38,range)
                                                  //| (21,official)
                                                  //| (33,missile)
                                                  //| (9,sciences)
                                                  //| (13,'s)
                                                  //| (41,to)
                                                  //| (2,##)
                                                  //| (32,of)
                                                  //| (34,launch)
                                                  //| (45,missile)
                                                  //| (17,former)
                                                  //| (22,mark)
                                                  //| (44,marks)
                                                  //| (27,iranian)
                                                  //| (12,study)
                                                  //| (7,montreal-based)
                                                  //| (39,be)
                                                  //| (3,##)
                                                  //| (35,from)
                                                  //| (18,us)
                                                  //| (16,and)
                                                  //| (31,larger)
                                                  //| (11,strategic)
                                                  //| (43,a)
                                                  //| (40,define)
                                                  //| (26,a)
                                                  //| (23,jennings)
                                                  //| (8,international)
                                                  //| (36,a)
                                                  //| (30,a)
                                                  //| (19,state)
                                                  //| (4,director)
                                                  //| (15,program)
                                                  //| The image was posted 20080709 on the Sepah news site, which is run by Iran's
                                                  //|  Revolutionary Guards.
                                                  //| (5,##)
                                                  //| (10,multi-billion)
                                                  //| (14,the)
                                                  //| (20,struggle)
                                                  //| (1,the)
                                                  //| (6,##)
                                                  //| (21,guard)
                                                  //| (9,the)
                                                  //| (2,image)
                                                  //| (17,by)
                                                  //| (12,site)
                                                  //| (7,##)
                                                  //| (3,be)
                                                  //| (18,iran)
                                                  //| (16,run)
                                                  //| (11,news)
                                                  //| (8,on)
                                                  //| (19,'s)
                                                  //| (4,office)
                                                  //| (15,be)
                                                  //| 20080710 the image was replaced with an image of the same 3 missiles in the 
                                                  //| previous photo but in place of the 4th missile is a grounded missile and wha
                                                  //| t appears to be a vehicle nearby.
                                                  //| (5,image)
                                                  //| (10,image)
                                                  //| (24,the)
                                                  //| (37,carry)
                                                  //| (25,marks)
                                                  //| (14,##)
                                                  //| (20,but)
                                                  //| (29,suddenly)
                                                  //| (1,##)
                                                  //| (6,be)
                                                  //| (28,a)
                                                  //| (38,near)
                                                  //| (21,in)
                                                  //| (33,yet)
                                                  //| (9,a)
                                                  //| (13,only)
                                                  //| (2,##)
                                                  //| (32,think)
                                                  //| (34,to)
                                                  //| (17,the)
                                                  //| (22,place)
                                                  //| (27,be)
                                                  //| (12,the)
                                                  //| (7,put)
                                                  //| (3,##)
                                                  //| (3
                                                  //| Output exceeds cutoff limit.

}