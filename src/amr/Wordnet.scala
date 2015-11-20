package amr

import scala.util.matching.Regex
import scala.collection.mutable.{ Map, Set, ArrayBuffer }
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._ // see http://stackoverflow.com/questions/16162090/how-to-convert-java-util-list-to-scala-list

import java.net.URL
import java.io.File

import edu.mit.jwi.RAMDictionary
import edu.mit.jwi.IRAMDictionary
import edu.mit.jwi.data.ILoadPolicy
import edu.mit.jwi.item._
import edu.mit.jwi.morph.WordnetStemmer

object Wordnet {

  private val wnhome: String = System.getenv("WNHOME")
  private val path: String = wnhome + File.separator + "dict"
  private val url: URL = new URL("file", null, path)
  private val dict: IRAMDictionary = new RAMDictionary(url, ILoadPolicy.NO_LOAD)
  dict.open
  private val wordnetStemmer: WordnetStemmer = new WordnetStemmer(dict)

  def stemmer(word: String): List[String] = {
    var stems = List[String]()
    for (pos <- POS.values) {
      try { stems ++= wordnetStemmer.findStems(word, pos) }
      catch { case e: Throwable => Unit }
    }
    return stems.distinct.sorted
  }

  def stemmer(word: String, pos: POS): List[String] = {
    try { wordnetStemmer.findStems(word, pos).asScala.toList }
    catch { case e: Throwable => List() }
  }

  def synonyms(word: String): List[String] = {
    var synonyms = List[String]()
    for (pos <- POS.values) {
      try { synonyms ++= ((dict.getIndexWord(word, pos).getWordIDs map dict.getWord flatMap getRelatedWordsExcAntonyms)) }
      catch { case e: Throwable => Unit }
    }
    return synonyms.distinct.sorted
  }

  def getRelatedWordsExcAntonyms(w: IWord): List[String] = {
    val s = w.getSynset.getWords
    //   s.getRelatedSynsets(Pointer.SIMILAR_TO)
    (s map (_.getLemma)).toList filter (a => !(a.contains("_")))
  }

  def getUsefulStuff(word: String): Unit = {
    for (pos <- POS.values) {
      val iw = dict.getIndexWord(word, pos)
      if (iw != null) {
        for (x <- iw.getWordIDs) {
          println(s"POS: $pos, WordID: ${x}, Lemma: ${x.getLemma}, Word: ${dict.getWord(x)}")
          val ss = dict.getSynset(x.getSynsetID)
          println("Synset: " + ss)
          println("Hypernyms: ")
          println(printHypernyms(ss, 1))
          println
        }
      }
    }
  }

  def printHypernyms(ss: ISynset, level: Int): String = {
    val hypernyms = ss.getRelatedSynsets(Pointer.HYPERNYM) map dict.getSynset
    if (hypernyms.nonEmpty) {
      val h = hypernyms.head
      h.getWord(1).getLemma + " : " + printHypernyms(h, level + 1)
    } else ""
  }
}

