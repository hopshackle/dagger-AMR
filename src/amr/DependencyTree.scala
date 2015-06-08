
package amr

import edu.cmu.lti.nlp.amr._

case class DependencyTree(nodes: Map[Int, String], nodeSpans: Map[Int, (Int, Int)], arcs: List[(Int, Int, String)]) {

  def toOutputFormat: String = {
    val nodeOutput = nodes.keys.toList map (x => s"# ::node\t${x}\t${nodes(x)}\t${nodeSpans(x)._1}\t${nodeSpans(x)._2}\n")
    val arcOutput = arcs map (x => s"# ::edge\t${x._1}\t${x._2}\t${x._3}\n")

    "# ::SpanGraph\n" + nodeOutput.mkString + arcOutput.mkString
  }
  
}
object DependencyTree {

  val processor = new StanfordProcessor

  def parse(sentence: String): DependencyTree = {

    val parseTree = processor.parse(sentence).head

    val nodes = (for {
      ConllToken(Some(index), Some(form), lemma, pos, cpos, feats, Some(parentIndex), Some(deprel), phead, pdeprel) <- parseTree
      if deprel != "punct"
    } yield (index -> form)).toMap + (0 -> "ROOT")
    
    val arcs = for {
      ConllToken(Some(index), Some(form), lemma, pos, cpos, feats, Some(parentIndex), deprel, phead, pdeprel) <- parseTree
      if deprel.getOrElse("") != "punct"
    } yield (parentIndex, index, deprel.getOrElse("UNK"))
   
    val nodeSpans = (for {
      (ConllToken(Some(index), Some(form), lemma, pos, cpos, feats, Some(parentIndex), deprel, phead, pdeprel), wordCount) <- 
        (parseTree.filter(x => x.deprel.getOrElse("") != "punct").zipWithIndex)
    } yield (index -> (wordCount+1, wordCount+2))).toMap + (0 -> (0, 1))
    
    DependencyTree(nodes, nodeSpans, arcs)
  }

}