
package amr
object testDT {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(45); 

  val dt1 = 57;System.out.println("""dt1  : Int = """ + $show(dt1 ))}

	}
 // val dt2 = DependencyTree("Rockström said that when rich countries increase their consumption , they also accelerate the exploitation of the world 's national resources , with the result that they emit more greenhouse gases . ")
/*  val testData = AMRGraph.importFile("C:\\AMR\\AMR2.txt")
  val s1 = Sentence(testData(2)._1, testData(2)._2)
  s1.dependencyTree.nodes foreach println
  s1.dependencyTree.nodeSpans foreach println
  s1.dependencyTree.arcs foreach println
    s1.dependencyTree.parentsOf(7)
  val AMR = s1.amr match {
    case None => null
    case Some(amr) => amr
  }
  AMR.nodes foreach println
  AMR.nodeSpans foreach println
  AMR.arcs foreach println
  */
