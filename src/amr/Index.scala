package amr

import gnu.trove.map.hash.THashMap

import scala.math._
import scala.collection.mutable.ArrayBuffer

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



class HashIndex(n: Int=10000000, base: Int=0) extends Index {

  def index(s: String): Int = {
    val h = abs(s.hashCode)+1
    base + (if (h < n) h else (h % n)+1)
  }

  def indexOf(s: String) = index(s)

  def setRange(low: Int, high: Int): HashIndex = {
    new HashIndex(high-low, low)
  }

  def elem(i: Int) = null
}


class MapIndex extends Index {
  val map = new gnu.trove.map.hash.THashMap[String, Int]()
  val array = new ArrayBuffer[String]()
  var size = 0

  def index(s: String): Int = {
    if (map.contains(s)) {
      map.get(s)
    }
    else {
      array += s
      size += 1
      map.put(s, size)
      size
    }
  }

  def elem(i: Int): String = array(i-1)

}


class TroveIndex extends Index {
  private val map = new THashMap[String, Int]
  val array = new ArrayBuffer[String]()
  var size = 0

  def index(s: String): Int = {
    if (map.contains(s)) {
      map.get(s)
    }
    else {
      array += s
      size += 1
      map.put(s, size)
      size
    }
  }

  def elem(i: Int): String = array(i-1)

}