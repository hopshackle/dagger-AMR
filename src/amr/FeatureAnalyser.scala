package amr
import dagger.core._
import dagger.ml._
import collection.mutable.HashMap
import java.io._

object FeatureAnalyser {

  def main(args: Array[String]): Unit = {
    val options = new DAGGEROptions(args)
    analyserRun(options)
  }

  def analyserRun(options: DAGGEROptions, devData: Iterable[Sentence], startingClassifier: AROWClassifier[WangXueAction], featureIndex: MapIndex): Unit = {
    val trainedPolicy = new ProbabilisticClassifierPolicy[Sentence, WangXueAction, WangXueTransitionState](startingClassifier)
    val baseResult = runPolicy(devData, options, featureIndex, trainedPolicy, "base")

    println(f"Baseline F-Score $baseResult%.3f")

    import scala.collection.JavaConversions._
    val sortedByName: scala.collection.immutable.List[String] = featureIndex.map.keySet.toList.sorted
    val byPrefix = sortedByName groupBy (s => {
      val eqIndex = s.indexOf("=")
      val sub = if (eqIndex == -1) s else s.substring(0, eqIndex)
      sub
    })
    val byCount = (byPrefix mapValues { l => l.size } toList).sortBy(-_._2)
    byCount foreach {
      i => println(s"Prefix: ${i._1}, count: ${i._2}")
    }
    val countMap = byCount.toMap
    val outputFile = new FileWriter(options.DAGGER_OUTPUT_PATH + "FeatureAnalysis.txt", true)
    outputFile.write(f"Baseline $baseResult%.3f + \n")
    for ((currentPrefix, count) <- byCount) {
      def weightBelongsToCurrentPrefix(k: Int): Boolean = {
        featureIndex.elem(k).startsWith(currentPrefix + "=") || featureIndex.elem(k) == currentPrefix
      }
      var allNewWeights = new HashMap[WangXueAction, HashMap[Int, Float]]()
      var totalNumberRemoved = 0
      for ((action, actionWeights) <- startingClassifier.weights) {
        val newWeights = actionWeights filterNot { case (k, v) => weightBelongsToCurrentPrefix(k) }
        val removedWeights = actionWeights filter { case (k, v) => weightBelongsToCurrentPrefix(k) }
        val sumWeights = (removedWeights.values map math.abs).sum
        totalNumberRemoved += removedWeights.size
        allNewWeights.put(action, newWeights)
      }

      val newClassifier = AROWClassifier[WangXueAction](allNewWeights)
      val newPolicy = ProbabilisticClassifierPolicy[Sentence, WangXueAction, WangXueTransitionState](newClassifier)
      val newResult = runPolicy(devData, options, featureIndex, newPolicy, "")
      println(f"Removing $currentPrefix features gives decrease of ${baseResult - newResult}%.3f with the removal of $totalNumberRemoved weights")
      outputFile.write(f"$currentPrefix\t${countMap(currentPrefix)}\t${baseResult - newResult}%.3f\t$totalNumberRemoved\n")
    }
    outputFile.close
  }

  def analyserRun(options: DAGGEROptions): Unit = {
    RunDagger.preInitialise(options)
    RunDagger.postInitialise(options)
    val devFile = options.getString("--validation.data", "")
    val devData = if (devFile == "") Iterable.empty else AMRGraph.importFile(devFile) map { case (english, amr, id) => Sentence(english, amr, id) }

    val featureIndex = new MapIndex
    val classifierToUse = options.getString("--classifier", options.DAGGER_OUTPUT_PATH + "../StartingClassifier.txt")
    val featuresToUse = options.getString("--featureIndex", options.DAGGER_OUTPUT_PATH + "../FeatureIndex.txt")
    val startingClassifier = AROWClassifier.fromFile(classifierToUse, y => WangXueAction.construct(y))
    featureIndex.initialiseFromFile(featuresToUse)
    analyserRun(options, devData, startingClassifier, featureIndex)
  }

  def runPolicy(devData: Iterable[Sentence], options: DAGGEROptions, featureIndex: MapIndex,
    policy: ProbabilisticClassifierPolicy[Sentence, WangXueAction, WangXueTransitionState], text: String = ""): Double = {
    val WXFeatures = new WangXueFeatureFactory(options, featureIndex).newFeatureFunction
    val dagger = new DAGGER[Sentence, WangXueAction, WangXueTransitionState](options)
    val debug = if (options.DEBUG && text != "") new FileWriter(options.DAGGER_OUTPUT_PATH + "FA_debug_" + text + ".txt", true) else null

    val results = for {
      data <- devData
      val (result, actions, _) = dagger.unroll(data, null, policy, WangXueTransitionSystem.init(data), WangXueTransitionSystem,
        WXFeatures, 0.0, true, debug)
      val t = if (options.DEBUG && text != "") {
        debug.write("Target = " + data + "\n")
        debug.write("Prediction = " + result + "\n")
        debug.write("Actions Taken: \n")
        for (a <- actions) debug.write(a.toString + "\n")
      }

    } yield result.getOrElse(Sentence(""))

    val testFunction = RunDagger.corpusSmatchScore(options) _
    val allData = devData zip results
    if (text != "")
      allData foreach { case (target, prediction) => AMROutput.AMROutputFunction(options, text, 0, prediction, target) }
    val finalResult = testFunction(allData).toMap getOrElse ("F-Score", 0.0)
    finalResult
  }

  // for each feature type
  // Compile list of the feature numbers
  // create a new classifier, based on the original, that has all these weights removed
  // it might be interesting to also store:
  // - the number of weights removed in this way
  // - the total (absolute) value of the removed weights

  // Now process the test set using this amended classifier and store the final results
  // against the removed feature type

  // 
}