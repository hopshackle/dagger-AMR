package amr

import scala.collection.mutable.HashMap
import scala.io._

class BrownCluster(file: String) {

  val rawData = Source.fromFile(file).getLines()
  val clusterData = (for {
    r <- rawData
    val entry = r.split("\t")
  } yield (entry.tail.head -> entry.head)).toMap

  def getClusterFor(word: String): String = {
    clusterData.getOrElse(word, "0")
  }

  def getBrownClusters(wordForms: List[String]): List[String] = {
    val matches = wordForms map getClusterFor filterNot (_ == "0")
    if (matches.size > 0) brownFragments(matches.head) else Nil
  }

  def brownFragments(brownClusterName: String): List[String] = {
    brownClusterName.size match {
      case x if x <= 4 => List(brownClusterName)
      case y if y <= 6 => List(brownClusterName, brownClusterName.take(4))
      case z if z <= 10 => List(brownClusterName, brownClusterName.take(6), brownClusterName.take(4))
      case a if a <= 20 => List(brownClusterName, brownClusterName.take(10), brownClusterName.take(6), brownClusterName.take(4))
      case _ => List(brownClusterName, brownClusterName.take(20), brownClusterName.take(10), brownClusterName.take(6), brownClusterName.take(4))
    }
  }
}