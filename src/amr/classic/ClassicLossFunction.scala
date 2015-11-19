package amr.classic
import amr._
import dagger.core._
import amr.ImportConcepts._

class ClassicLossFunctionFactory(lossToUse: String) extends LossFunctionFactory[Sentence, ClassicAction, ClassicTransitionState] {
  override def newLossFunction = lossToUse match {
    case "Penalty" => new ClassicLossFunctionActionPenalty
    case "PenaltyAbs" => new ClassicLossFunctionAbsPenalty
    case "Abs" => new ClassicLossFunctionAbs
    case "Hamming" => ???
    case "NaivePenaltyAbs" => new ClassicNaiveAbsSmatchWithPenalty
    case "NaiveAbs" => new ClassicNaiveAbsSmatch
    case "Naive" => new ClassicNaiveSmatch
    case "None" => new ClassicConstantLoss
    case _ => new ClassicLossFunction
  }
}

class ClassicLossFunction extends LossFunction[Sentence, ClassicAction, ClassicTransitionState] {

  override def apply(gold: Sentence, test: Sentence, testActions: Array[ClassicAction], expertActions: Array[ClassicAction],
    trialAction: ClassicAction, lastExpertAction: ClassicAction): Double = {
    apply(gold, test, testActions)
  }

  override def apply(gold: Sentence, test: Sentence, testActions: Array[ClassicAction]): Double = {
    val testAMR = test.amr match {
      case None => test.dependencyTree.toAMR
      case Some(graph) => graph
    }

    1.0 - Smatch.fScore(gold.amr.get, testAMR, 2, 1000)._1
  }

  // we set the maximum to be double the number of nodes in the dependency tree of the original sentence
  def max(gold: Sentence): Double = 1.0

}

class ClassicLossFunctionAbs extends ClassicLossFunction {

  override def apply(gold: Sentence, test: Sentence, testActions: Array[ClassicAction]): Double = {

    val testAMR = test.amr match {
      case None => test.dependencyTree.toAMR
      case Some(graph) => graph
    }

    Smatch.fScore(gold.amr.get, testAMR, 2, 1000)._4
  }
  
  // we set the maximum to be double the number of nodes in the dependency tree of the original sentence
  override def max(gold: Sentence): Double = gold.dependencyTree.nodes.size * 2

}

class ClassicLossFunctionAbsPenalty extends ClassicLossFunction {

  override def apply(gold: Sentence, test: Sentence, testActions: Array[ClassicAction]): Double = {

    val testAMR = test.amr match {
      case None => test.dependencyTree.toAMR
      case Some(graph) => graph
    }

    Smatch.fScore(gold.amr.get, testAMR, 2, 1000)._4 + (testActions.size / 2.5)
  }
  
  // we set the maximum to be double the number of nodes in the dependency tree of the original sentence
  override def max(gold: Sentence): Double = gold.dependencyTree.nodes.size * 2

}

class ClassicNaiveAbsSmatchWithPenalty extends ClassicLossFunction {
    override def apply(gold: Sentence, test: Sentence, testActions: Array[ClassicAction]): Double = {

    val testAMR = test.amr match {
      case None => test.dependencyTree.toAMR
      case Some(graph) => graph
    }

    Smatch.naiveFScore(gold.amr.get, testAMR, 1, 1000)._4 + (testActions.size / 2.5)
  }
  
  // we set the maximum to be double the number of nodes in the dependency tree of the original sentence
  override def max(gold: Sentence): Double = gold.dependencyTree.nodes.size * 2

}

class ClassicNaiveAbsSmatch extends ClassicLossFunction {
    override def apply(gold: Sentence, test: Sentence, testActions: Array[ClassicAction]): Double = {

    val testAMR = test.amr match {
      case None => test.dependencyTree.toAMR
      case Some(graph) => graph
    }

    Smatch.naiveFScore(gold.amr.get, testAMR, 1, 1000)._4 
  }
  
  // we set the maximum to be double the number of nodes in the dependency tree of the original sentence
  override def max(gold: Sentence): Double = gold.dependencyTree.nodes.size * 2

}

class ClassicNaiveSmatch extends ClassicLossFunction {
    override def apply(gold: Sentence, test: Sentence, testActions: Array[ClassicAction]): Double = {

    val testAMR = test.amr match {
      case None => test.dependencyTree.toAMR
      case Some(graph) => graph
    }

    1.0 - Smatch.naiveFScore(gold.amr.get, testAMR, 1, 1000)._1 
  }
  
  // we set the maximum to be double the number of nodes in the dependency tree of the original sentence
  override def max(gold: Sentence): Double = 1.0

}

class ClassicLossFunctionActionPenalty extends ClassicLossFunction {
  override def apply(gold: Sentence, test: Sentence, testActions: Array[ClassicAction]): Double = {
    val testAMR = test.amr match {
      case None => test.dependencyTree.toAMR
      case Some(graph) => graph
    }
    1.0 - Smatch.fScore(gold.amr.get, testAMR, 2, 500)._1 + (testActions.size / 1000.0)
  }
}

class ClassicConstantLoss extends ClassicLossFunction {
  override def apply(gold: Sentence, test: Sentence, testActions: Array[ClassicAction]): Double = {
    0.0
  }
}

