package amr
import dagger.core._
import dagger.ml._
object RunClassifier {

  def main(args: Array[String]): Unit = {
    val options = new DAGGEROptions(args)
    analyserRun(options)
  }

  def analyserRun(options: DAGGEROptions): Unit = {
    FeatureAnalyser.initialise(options)
    val devFiles = options.getString("--validation.data", "").split(";")

    val featureIndex = new MapIndex
    val classifierToUse = options.getString("--classifier", options.DAGGER_OUTPUT_PATH + "../StartingClassifier.txt")
    val featuresToUse = options.getString("--featureIndex", options.DAGGER_OUTPUT_PATH + "../FeatureIndex.txt")
    val startingClassifier = AROWClassifier.fromFile(classifierToUse, y => WangXueAction.construct(y))
    featureIndex.initialiseFromFile(featuresToUse)

    val trainedPolicy = new ProbabilisticClassifierPolicy[Sentence, WangXueAction, WangXueTransitionState](startingClassifier)

    for ((devFile, i) <- devFiles.zipWithIndex) {
      val devData = if (devFile == "") Iterable.empty else AMRGraph.importFile(devFile) map { case (english, amr, id) => Sentence(english, amr, id) }
      val baseResult = FeatureAnalyser.runPolicy(devData, options, featureIndex, trainedPolicy, "base_" + (i + 1))

      println(f"Baseline F-Score $baseResult%.3f for file $devFile")
    }
  }
}