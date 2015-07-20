package amr
object AlignTest {

  def main(args: Array[String]): Unit = {

    val sample = AMRGraph.importFile("C://AMR//Borderless.txt")
    sample foreach {
      case s @ (sentence, amr) =>
        println(sentence)
        println
        println(amr)
        println
        val out = AMRGraph(amr, sentence)
        println(out)

    }
  }
}