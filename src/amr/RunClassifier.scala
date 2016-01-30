package amr
import dagger.core._
import dagger.ml._
object RunClassifier {

  def main(args: Array[String]): Unit = {
    val options = new DAGGEROptions(args)
    analyserRun(options)
  }

  def analyserRun(options: DAGGEROptions): Unit = {
    val init = System.currentTimeMillis()
    println("Initialising...")
    FeatureAnalyser.initialise(options)
    val devFiles = options.getString("--validation.data", "").split(";")
    val start = System.currentTimeMillis()

    println("Took " + ((start - init) / 1000) + " seconds")
    println("Loading Classifier details...")
    val featureIndex = new MapIndex
    val classifierToUse = options.getString("--classifier", options.DAGGER_OUTPUT_PATH + "../StartingClassifier.txt")
    val featuresToUse = options.getString("--featureIndex", options.DAGGER_OUTPUT_PATH + "../FeatureIndex.txt")
    val startingClassifier = AROWClassifier.fromFile(classifierToUse, y => WangXueAction.construct(y))
    featureIndex.initialiseFromFile(featuresToUse)

    val trainedPolicy = new ProbabilisticClassifierPolicy[Sentence, WangXueAction, WangXueTransitionState](startingClassifier)

    var mid = System.currentTimeMillis
    println("Took " + ((mid - start) / 1000) + " seconds")
    for ((devFile, i) <- devFiles.zipWithIndex) {
      println("Processing file " + devFile)
      val devData = if (devFile == "") Iterable.empty else {
        AMRGraph.importFile(devFile) map {
          case (english, amr, id) =>
            AMRGraph(amr, english, id, false) match {
              case null => Sentence(english, None, id)
              case amrGraph => Sentence(english, Some(amrGraph), id)
            }
        }
      }
      val baseResult = FeatureAnalyser.runPolicy(devData, options, featureIndex, trainedPolicy, "base_" + (i + 1))

      println(f"Baseline F-Score $baseResult%.3f in ${(System.currentTimeMillis - mid) / 1000}%.0f seconds for file $devFile")
      mid = System.currentTimeMillis
    }
  }
}