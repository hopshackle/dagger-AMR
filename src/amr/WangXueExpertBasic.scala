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
    val iterations = 4
    val movesToConsider = 1000
    val parsedArgs = new dagger.util.ArgParser(args)
    val fileName = parsedArgs.getString("-i", "C:\\AMR\\AMR2.txt")
    val options = new DAGGEROptions(Array("--dagger.output.path", "C:\\AMR\\"))
    AMRGraph.setAligner("improved")
    WangXueTransitionSystem.prohibition = false
    WangXueTransitionSystem.reentrance = true
    //    Reattach.REATTACH_RANGE = 20
    ImportConcepts.initialise(fileName)
    val rawData = ImportConcepts.expertResults
    val timer = new Timer()
    timer.start
    var allScores = List[Double]()
    val allAMR = rawData map {
      case (original, processed) =>
        val fScore = Smatch.naiveFScore(original.amr.get, processed.amr.get)
        allScores = fScore._1 :: allScores
        println(f"${fScore._1}%.2f" + "\t" + original.rawText)
        (original.amr.get, processed.amr.get)
    }

    val meanScore = (allScores reduce (_ + _)) / allScores.size
    println(f"Average f-Score of $meanScore%.3f in $timer")
    timer.stop
    val timer2 = new Timer()
    timer2.start
    // All improvements
    var scores = RunDagger.corpusSmatchScoreAMR(options)(allAMR, iterations, movesToConsider)
    println(f"Corpus level f-Score of ${scores(0)._2}%.3f in $timer2")
    println(f"Corpus level precision of ${scores(1)._2}%.3f in $timer2")
    println(f"Corpus level recall of ${scores(2)._2}%.3f in $timer2")
    timer2.stop
    val timer3 = new Timer()
    timer3.start
    // satisficing only
    Smatch.useImprovedMapping = false
    scores = RunDagger.corpusSmatchScoreAMR(options)(allAMR, iterations, 1000000)
    println(f"Corpus level f-Score of ${scores(0)._2}%.3f in $timer3")
    println(f"Corpus level precision of ${scores(1)._2}%.3f in $timer3")
    println(f"Corpus level recall of ${scores(2)._2}%.3f in $timer3")
    timer3.stop
    val timer4 = new Timer()
    timer4.start
    // improved mapping only
    Smatch.useImprovedMapping = true
    Smatch.useSatisficing = false
    scores = RunDagger.corpusSmatchScoreAMR(options)(allAMR, iterations, 1000000)
    println(f"Corpus level f-Score of ${scores(0)._2}%.3f in $timer4")
    println(f"Corpus level precision of ${scores(1)._2}%.3f in $timer4")
    println(f"Corpus level recall of ${scores(2)._2}%.3f in $timer4")
    timer4.stop
    val timer5 = new Timer()
    timer5.start
    // movesToConsider only
    Smatch.useImprovedMapping = false
    Smatch.useSatisficing = false
    scores = RunDagger.corpusSmatchScoreAMR(options)(allAMR, iterations, movesToConsider)
    println(f"Corpus level f-Score of ${scores(0)._2}%.3f in $timer5")
    println(f"Corpus level precision of ${scores(1)._2}%.3f in $timer5")
    println(f"Corpus level recall of ${scores(2)._2}%.3f in $timer5")
    timer5.stop
        val timer6 = new Timer()
    timer6.start
    // vanilla Smatch
    Smatch.useImprovedMapping = false
    Smatch.useSatisficing = false
    scores = RunDagger.corpusSmatchScoreAMR(options)(allAMR, iterations, 10000000)
    println(f"Corpus level f-Score of ${scores(0)._2}%.3f in $timer6")
    println(f"Corpus level precision of ${scores(1)._2}%.3f in $timer6")
    println(f"Corpus level recall of ${scores(2)._2}%.3f in $timer6")
    timer6.stop
  }
}