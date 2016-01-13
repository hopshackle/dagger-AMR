package amr
import dagger.util._
import dagger.ml._
import dagger.core._
import scala.collection.immutable._
import java.io._

object WangXueAnalyser {

  def main(args: Array[String]): Unit = {
    val options = new DAGGEROptions(args)
    analyserRun(options)
  }

  def analyserRun(options: DAGGEROptions): Unit = {

    FeatureAnalyser.initialise(options)
    val instances = new FileInstances[WangXueAction](List(options.getString("--instanceFile")),
      (y => WangXueAction.construct(y)), (x => x.name), 100, false)

    val errorCount = InstanceAnalyser.basicErrorMetrics(instances.iterator)
    val sortedErrorCount = TreeMap(errorCount.toArray: _*)
    val outputFile = new FileWriter(options.DAGGER_OUTPUT_PATH + "ErrorCountSummary.txt")
    sortedErrorCount foreach { case (k, v) => outputFile.write(s"$k\t$v\n") }
    outputFile.close

    val errorDetail = InstanceAnalyser.errorsByActionType[WangXueAction](instances.iterator, (action => action.name))
    val outputFile2 = new FileWriter(options.DAGGER_OUTPUT_PATH + "ErrorDetail.txt")
    errorDetail foreach {
      case (k, v) =>
        outputFile2.write(s"$k\t${v._1}\t${v._2.mkString("\t")}\n")
    }
    outputFile2.close

    val featureIndex = new MapIndex
    featureIndex.initialiseFromFile(options.getString("--featureIndex"))

    InstanceAnalyser.featureDescription(instances.iterator, options.getInt("--errorAnalysisThreshold", 10), (i => featureIndex.elem(i)), options.DAGGER_OUTPUT_PATH + "ErrorFeatureDetail.txt")
  }
}