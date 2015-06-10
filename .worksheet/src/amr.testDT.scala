package amr

object testDT {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(115); 

  val dt = DependencyTree("The police want to arrest Michael Karras in Singapore.");System.out.println("""dt  : amr.DependencyTree = """ + $show(dt ));$skip(58); 
  val testData = AMRGraph.importFile("C:\\AMR\\AMR2.txt");System.out.println("""testData  : IndexedSeq[(String, String)] = """ + $show(testData ));$skip(52); 
  val s1 = Sentence(testData(0)._1, testData(0)._2);System.out.println("""s1  : amr.Sentence = """ + $show(s1 ));$skip(42); 
  s1.dependencyTree.nodes foreach println;$skip(46); 
  s1.dependencyTree.nodeSpans foreach println;$skip(41); 
  s1.dependencyTree.arcs foreach println;$skip(79); 
  val AMR = s1.amr match {
    case None => null
    case Some(amr) => amr
  };System.out.println("""AMR  : amr.AMRGraph = """ + $show(AMR ));$skip(28); 
  AMR.nodes foreach println;$skip(32); 
  AMR.nodeSpans foreach println;$skip(27); 
  AMR.arcs foreach println}
}
