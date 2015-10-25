package amr

import gnu.trove.map.hash.THashMap

import scala.math._
import scala.collection.mutable.ArrayBuffer
import java.io._

/**
 * Created with IntelliJ IDEA.
 * User: narad
 * Date: 5/21/14
 * Time: 1:50 PM
 */

abstract class Index() {

  def index(s: String): Int

  def elem(i: Int): String

}

class HashIndex(n: Int = 10000000, base: Int = 0) extends Index {

  def index(s: String): Int = {
    val h = abs(s.hashCode) + 1
    base + (if (h < n) h else (h % n) + 1)
  }

  def indexOf(s: String) = index(s)

  def setRange(low: Int, high: Int): HashIndex = {
    new HashIndex(high - low, low)
  }

  def elem(i: Int) = null
}

class MapIndex extends Index {
  val map = new gnu.trove.map.hash.THashMap[String, Int]()
  val array = new ArrayBuffer[String]()
  var size = 0

  def index(s: String): Int = {
    this.synchronized {
      if (map.contains(s)) {
        map.get(s)
      } else {
        array += s
        size += 1
        map.put(s, size)
        size
      }
    }
  }

  def elem(i: Int): String = this.synchronized { array(i - 1) }

  def writeToFile(file: String) = {
    import scala.collection.JavaConversions._
    val outputFile = new FileWriter(file)
    for (i <- (1 to array.size)) {
      outputFile.write(i + "\t" + array(i - 1) + "\n")
    }
    outputFile.close()
  }

  def initialiseFromFile(file: String) = {
    import scala.collection.JavaConversions._
    size = 0
    map.clear
    array.clear
    val inputFile = new BufferedReader(new FileReader(file))
    var eof = false
    while (!eof) {
      val nextLine = inputFile.readLine
      if (nextLine == null) {
        eof = true
      } else {
        val elements = nextLine.split("\t")
        array += elements(1)
        size += 1
        assert(size == elements(0).toInt)
        map.put(elements(1), size)
      }
    }
  }
}

class TroveIndex extends Index {
  private val map = new THashMap[String, Int]
  val array = new ArrayBuffer[String]()
  var size = 0

  def index(s: String): Int = {
    if (map.contains(s)) {
      map.get(s)
    } else {
      array += s
      size += 1
      map.put(s, size)
      size
    }
  }

  def elem(i: Int): String = array(i - 1)

}