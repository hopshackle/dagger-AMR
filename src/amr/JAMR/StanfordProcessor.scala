package amr.JAMR

import java.util.Properties
import edu.stanford.nlp.pipeline.StanfordCoreNLP
import edu.stanford.nlp.trees._
import edu.stanford.nlp.util.Filters
import scala.collection.JavaConversions._
import edu.stanford.nlp.ling.CoreAnnotations._
import edu.stanford.nlp.trees.TreeCoreAnnotations.TreeAnnotation

case class ConllToken(index: Option[Int],
  form: Option[String],
  lemma: Option[String],
  pos: Option[String],
  cpos: Option[String],
  feats: Option[String],
  gov: Option[Int],
  deprel: Option[String],
  phead: Option[Int],
  pdeprel: Option[String]) extends Iterable[Option[_]] {

  override def toString() = {
    map(_.getOrElse("_")).mkString("\t")
  }

  override def iterator = productIterator.asInstanceOf[Iterator[Option[_]]]
}

/**
 * Thin wrapper around edu.stanford.nlp.pipeline.StanfordCoreNLP
 *
 * @author sthomson@cs.cmu.edu
 */
class StanfordProcessor {
  private val processor = {
    val props = new Properties()
    props.setProperty("annotators", "tokenize,ssplit,parse,lemma,ner")
    new StanfordCoreNLP(props)
  }

  // don't filter out punctuation dependencies
  private val grammaticalStructureFactory = new EnglishGrammaticalStructureFactory(Filters.acceptFilter())

  /**
   * Parse to basic dependencies in conllx format.
   * Undoes any changes Stanford makes to the word forms.
   */
  def parse(input: String): List[ConllToken] = {
    val annotation = processor.process(input)
    val sentences = annotation.get(classOf[SentencesAnnotation]).toList
    val allSentences = for (sentence <- sentences) yield {
      val tree = sentence.get(classOf[TreeAnnotation])
      val gs = grammaticalStructureFactory.newGrammaticalStructure(tree)
      val deps = gs.typedDependencies().toList sortBy (_.dep.index)
      val tokens = sentence.get(classOf[TokensAnnotation]).toList
      for ((token, dep) <- tokens zip deps) yield {
        val start = token.get(classOf[CharacterOffsetBeginAnnotation])
        val end = token.get(classOf[CharacterOffsetEndAnnotation])
        val pos = token.get(classOf[PartOfSpeechAnnotation])
        val ner = token.get(classOf[NamedEntityTagAnnotation])
        val lemma = token.get(classOf[LemmaAnnotation])
        ConllToken(
          Some(dep.dep.index),
          Some(input.substring(start, end)),
          None,
          Some(pos),
          Some(pos),
          Some(lemma),
          Some(dep.gov.index),
          Some(dep.reln.getShortName),
          None,
          Some(ner))
      }
    }
    val t = allSentences.zipWithIndex
    val maxIndex = (allSentences.flatten maxBy { x => x.index.getOrElse(0) }).index.get
    val multiSentence = t.size > 1
    val t2 = t map {
      case (listCONLL, index) =>
        listCONLL map { x =>
          x.copy(
            index = Some(x.index.get + (index * 100)),
            gov = x.gov match {
              case None if !multiSentence => None
              case None if multiSentence => Some(maxIndex + 1)
              case Some(parentIndex) if parentIndex != 0 => Some(parentIndex + index * 100)
              case _ if !multiSentence => Some(0)
              case _ if multiSentence => Some(maxIndex + 1)
            },
            deprel = x.deprel match {
              case None => None
              case Some(label) => Option(if (multiSentence && label == "root") "sntN" else label)
            })
        }
    }

    val multiSentenceTokens = if (multiSentence) {
      List(ConllToken(Some(maxIndex + 1), Some("multi-sentence"), None, Some(""), Some(""), Some("multi-sentence"), Some(0), Some(""), None, Some("")))
    } else List.empty[ConllToken]
    t2.flatten ++ multiSentenceTokens
  }

  def parseToConll(input: String) = parse(input).map(_.mkString("\n")).mkString("\n\n")
}
