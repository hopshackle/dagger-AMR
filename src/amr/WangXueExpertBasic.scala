package amr
import dagger.core._
import amr.ImportConcepts.{concept, relation, conceptIndex, relationIndex}

class WangXueExpertBasic extends HeuristicPolicy[Sentence, WangXueAction, WangXueTransitionState] {

  val debug = false

  override def chooseTransition(data: Sentence, state: WangXueTransitionState): WangXueAction = {

    if (debug) println("Considering current node: " + state.nodesToProcess.head +
      " with child " + (if (state.childrenToProcess isEmpty) "Nil" else state.childrenToProcess.head))
    val chosenAction = state.childrenToProcess match {
      case Nil =>
        if (currentNodeIsNotIncludedInAGoldSpan(data, state) && state.currentGraph.isLeafNode(state.nodesToProcess.head)) DeleteNode
        else NextNode(conceptForCurrentNode(data, state))
      case head :: tail => 
        NextEdge(relationBetweenSigmaAndBeta(data, state))
    }
    if (debug) println("Action chosen: " + chosenAction)
    chosenAction
  }

  def currentNodeIsNotIncludedInAGoldSpan(data: Sentence, state: WangXueTransitionState): Boolean = {
    val span @ (position, _) = data.dependencyTree.nodeSpans.getOrElse(state.nodesToProcess.head, (0, 0))
    if (debug) {
      println("Position: " + position)
      println("InGoldSpan: " + (data.positionToAMR contains position))
    }
    !(data.positionToAMR contains position)
  }

  def conceptForCurrentNode(data: Sentence, state: WangXueTransitionState): Int = {
    if (state.nodesToProcess.head == 0) return conceptIndex("ROOT") // special hard-code for the ROOT node  
    val conceptKey = data.positionToAMR.getOrElse(state.nodesToProcess.head, "NONE")
    val conceptString = data.amr.get.nodes.getOrElse(conceptKey, "NONE")
    if (debug) {
      println("Concept: " + conceptKey + " -> " + conceptString)
      println("Index: " + conceptIndex(conceptString))
    }
    conceptIndex(conceptString)
  }

  def relationBetweenSigmaAndBeta(data: Sentence, state: WangXueTransitionState): Int = {
    val currentNode = data.positionToAMR.getOrElse(state.nodesToProcess.head, "NONE")
    val childNode = data.positionToAMR.getOrElse(state.childrenToProcess.head, "NONE")
    val relationString = data.amr.get.arcs.getOrElse((currentNode, childNode), "NONE")
    if (debug) {
      println("Edge: " + currentNode + "\t" + childNode + "\t" + relationString)
      println("Index: " + relationIndex(relationString))
    }
    relationIndex(relationString)
  }

}

object WangXueExpertCheck {

  def main(args: Array[String]): Unit = {
    val parsedArgs = new dagger.util.ArgParser(args)
    val fileName = parsedArgs.getString("-i", "C:\\AMR\\AMR2.txt")
    ImportConcepts.initialise(fileName)
    val sentences = AMRGraph.importFile(fileName) map { case (english, amr) => Sentence(english, amr) }
    println("Extracted AMR...now calculating scores...")
    val fScore = sentences map { s => Smatch.fScore(RunDagger.sampleTrajectory(s).amr.get, s.amr.get)}
    fScore zip sentences foreach { case (score, sentence) => println(f"$score%.2f" + "\t" + sentence.rawText)}
    val meanScore = (fScore reduce (_ + _)) / fScore.size
    println(f"Average f-Score of $meanScore%.2f")
  }
}