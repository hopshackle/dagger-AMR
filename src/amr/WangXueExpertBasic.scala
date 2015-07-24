package amr
import dagger.core._
import dagger.util.Timer
import amr.ImportConcepts.{ concept, relation, conceptIndex, relationIndex }

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
    val iterations = 1
    val movesToConsider = 1000
    val parsedArgs = new dagger.util.ArgParser(args)
    val fileName = parsedArgs.getString("-i", "C:\\AMR\\AMR2.txt")
    ImportConcepts.initialise(fileName)
 //   AMRGraph.setAligner("improved")
    val rawData = AMRGraph.importFile(fileName)
    val sentences = rawData map (d => Sentence(d._1, d._2))
    val timer = new Timer()
    var timerStarted = false
    var allScores = List[Double]()
    val allAMR = sentences map { sentence =>
      val amrGraph = RunDagger.sampleTrajectory(sentence).amr.get
      val fScore = Smatch.naiveFScore(amrGraph, sentence.amr.get)
      if (!timerStarted) { timerStarted = true; timer.start; println("Starting Timer: " + timer) }
      allScores = fScore._1 :: allScores
      println(f"${fScore._1}%.2f" + "\t" + sentence.rawText)
      (sentence.amr.get, amrGraph)
    }

    val meanScore = (allScores reduce (_ + _)) / allScores.size
    println(f"Average f-Score of $meanScore%.3f in $timer")
    val scores = RunDagger.corpusSmatchScoreAMR(allAMR, iterations, movesToConsider)
    println(f"Corpus level f-Score of ${scores(0)._2}%.3f in $timer")
    println(f"Corpus level precision of ${scores(1)._2}%.3f in $timer")
    println(f"Corpus level recall of ${scores(2)._2}%.3f in $timer")
  }
}