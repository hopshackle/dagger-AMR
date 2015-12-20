package amr
import dagger.ml._
object FeatureRemovalTimeTest extends App {

  val start = System.currentTimeMillis

  ImportConcepts.initialise("C://AMR//trainingSet150.txt")
  WangXueTransitionSystem.reentrance = true
  val instances = new FileInstances[WangXueAction](List("C://AMR//daggerTest//Instances_1.txt"), y => WangXueAction.construct(y), x => x.name, 100)
  val toRemove = AROW.removeRareFeatures(instances, 1)

  val middle = System.currentTimeMillis

  Instance.setRareFeatures(toRemove)
  instances.iterator foreach (x => x.feats foreach Instance.pruneRareFeatures)

  val endI = System.currentTimeMillis

  instances.iterator foreach (_.removeFeaturesII(toRemove))

  val endII = System.currentTimeMillis

  println("Initialisation took " + (middle - start))
  println("Remove I took " + (endI - middle))
  println("Remove II took " + (endII - endI))
}