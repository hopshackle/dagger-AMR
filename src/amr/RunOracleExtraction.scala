package amr
import dagger.core._

object RunOracleExtraction {

  def main(args: Array[String]): Unit = {
    val options = new DAGGEROptions(args)
    val extractor = new OracleExtractor[Sentence, WangXueAction, WangXueTransitionState](options)
    val alignerToUse = options.getString("--aligner", "")
    AMRGraph.setAligner(alignerToUse)
    ImportConcepts.initialise(options.getString("--train.data", "C:\\AMR\\AMR2.txt"))
    println("Loading training data")
    //  val trainData = AMRGraph.importFile(options.getString("--train.data", "C:\\AMR\\AMR2.txt")) map { case (english, amr) => Sentence(english, amr) }
    val trainData = (ImportConcepts.allAMR zip ImportConcepts.allSentencesAndAMR) map (all => Sentence(all._2._1, Some(all._1)))
    val devFile = options.getString("--validation.data", "")
    println("Loading validation data")
    val devData = if (devFile == "") Iterable.empty else AMRGraph.importFile(devFile) map { case (english, amr) => Sentence(english, amr) }
    val lossFunctionFactory = new WangXueLossFunctionFactory("Basic F-Score")
    val featureIndex = new MapIndex
    val WXFeatures = new WangXueFeatureFactory(options, featureIndex)
    val WXTransitionSystem = new WangXueTransitionSystem
    val score = (s: Iterable[(Sentence, Sentence)]) => 1.0
    println("Extracting instances...")
    val classifier = extractor.train(trainData, new WangXueExpert, WXFeatures, lossFunctionFactory, WXTransitionSystem, devData, RunDagger.corpusSmatchScore, options)
  }
}