package amr
import scala.io.Source

object PourdamghaniAligner {
  lazy val tokenizationFile = ImportConcepts.amrFile + "_tok"
  lazy val tokenizations = importTokenization
  var useHeadMapping = true
  val idFinder = """(# ::id [^:]*) ::.*""".r

  def importTokenization: Map[String, (String, String)] = {
    val source = Source.fromFile(tokenizationFile)("ISO8859-1").getLines()
    val sentenceIndices = (for {
      (line, i) <- source.map(_.trim).zipWithIndex
      if line.matches("^# ::tok .*")
    } yield i).toList
    val input = Source.fromFile(tokenizationFile)("ISO8859-1").getLines().toList.map(_.trim)

    (for {
      i <- sentenceIndices
      val id = input(i - 1) match {
        case idFinder(ref) => ref
        case _ => ""
      }
      val tok = input(i)
      val align = input(i + 1)
    } yield (id, (tok, align))).toMap
  }
}

case class PourdamghaniAligner(tokenisedSentence: List[String], amr: AMRGraph, id: String) {
  val debug = false
  import PourdamghaniAligner.useHeadMapping

  def mapToAMR: AMRGraph = {
    val mapping = map(tokenization, tokenisedSentence.toArray, Map(), 0)
    amr.copy(nodeSpans = mapping map { case (k, v) => (v -> (k + 1, k + 2)) })
  }

  /*
   * (tokenIndex, token, amrRef) purely using the tokenised file
   */
  def tokenization: Seq[(Int, String, String)] = {
    val (rawTokenisedSentence, rawAlignments) = PourdamghaniAligner.tokenizations.getOrElse(id, ("", ""))
    if (rawAlignments.size < 15) {
      Seq()
    } else {
      val tokenisedSentence = rawTokenisedSentence.substring(8).split(' ')
      val alignments = rawAlignments.substring(15).split(' ') filterNot (_.endsWith(".r"))
      val correctedAlignments = alignments map unwrapAlignment
      correctedAlignments map (ca => (ca._1, tokenisedSentence(ca._1), ca._2))
    }
  }

  def unwrapAlignment(rawAlignment: String): (Int, String) = {
    val initialSplit = rawAlignment.split('-')
    val tokenIndex = initialSplit(0).toInt
    val amrText = initialSplit(1)
    val amrGraphKey = amrText split ('.') map (_.toInt - 1) mkString (".")
    (tokenIndex, amrGraphKey)
  }

  /* this is where we map the tokenised data to the sentence
   * 
   */
  def map(tokens: Seq[(Int, String, String)], currentSentence: Array[String], currentMap: Map[Int, String], offset: Int): Map[Int, String] = {
    if (tokens.isEmpty || currentSentence.isEmpty) {
      currentMap
    } else {
      val m = map(tokens.head, currentSentence)
      val allTokensForHead = tokens takeWhile (_._1 == tokens.head._1)
      val tailMapping: Map[Int, String] = if (m == -1) Map() else Map((m + offset -> allTokensForHead.last._3))
      val headMapping: Map[Int, String] = if (m == -1) Map() else Map((m + offset -> tokens.head._3))
      if (debug) println(headMapping)
      if (debug) println(tailMapping)
      val truncatedSentence = currentSentence.drop(m + 1)
      val availableTokens = tokens.tail filterNot { case (index, _, amrRef) => m != -1 && (amrRef == tokens.head._3 || index == tokens.head._1) }
      map(availableTokens, truncatedSentence, currentMap ++ (if (useHeadMapping) headMapping else tailMapping), offset + m + 1)
    }
  }
  def map(token: (Int, String, String), currentSentence: Array[String]): Int = {
    val wordToFind = DependencyTree.preProcess(token._2).head
    if (debug) println(token + " : " + currentSentence.mkString("@") + " : " + wordToFind)
    val found = currentSentence.take(5).find { word => word.size >= wordToFind.size && wordToFind == word.substring(0, wordToFind.size) }
    found match {
      case None => -1
      case Some(string) => currentSentence.indexOf(string)
    }
  }
}