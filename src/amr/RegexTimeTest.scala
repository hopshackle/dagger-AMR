package amr

object RegexTimeTest extends App {

  val hyphen = "-".r
  val negation = "^(un.*|in.*|il.*|anti.*)".r

  test("REGEX", "antilegal", 10000000)
  test("STRING", "antilegal", 10000000)

  def test(method: String, target: String, rounds: Int) = {
    val start = System.currentTimeMillis()
    for (i <- 1 to rounds) {
      if (method == "REGEX") {
            hyphen.split(target)
    //    negation.findFirstIn(target)
      } else {
            target.split("-")
    //    target.startsWith("il") || target.startsWith("un") || target.startsWith("in") || target.startsWith("anti")
      }
    }
    val end = System.currentTimeMillis()
    println("Test of method " + method + " concluded in " + (end - start))
  }

}