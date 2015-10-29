package amr
import dagger.core._
import amr.ImportConcepts.{ concept, relation }

class WangXueLossFunctionAbs extends WangXueLossFunction {
  // Inherits everything from WXLoss - it just then uses the absolute number of errors in the Smatch
  // as the loss, rather than the F-Score

  override def apply(gold: Sentence, test: Sentence, testActions: Array[WangXueAction]): Double = {

    val testAMR = test.amr match {
      case None => test.dependencyTree.toAMR
      case Some(graph) => graph
    }

    Smatch.fScore(gold.amr.get, testAMR, 2, 1000)._4
  }
  
  // we set the maximum to be double the number of nodes in the dependency tree of the original sentence
  override def max(gold: Sentence): Double = gold.dependencyTree.nodes.size * 2

}

class WangXueLossFunctionAbsPenalty extends WangXueLossFunction {
  // Inherits everything from WXLoss - it just then uses the absolute number of errors in the Smatch
  // as the loss, rather than the F-Score

  override def apply(gold: Sentence, test: Sentence, testActions: Array[WangXueAction]): Double = {

    val testAMR = test.amr match {
      case None => test.dependencyTree.toAMR
      case Some(graph) => graph
    }

    Smatch.fScore(gold.amr.get, testAMR, 2, 1000)._4 + (testActions.size / 5.0)
  }
  
  // we set the maximum to be double the number of nodes in the dependency tree of the original sentence
  override def max(gold: Sentence): Double = gold.dependencyTree.nodes.size * 2

}

class WXNaiveAbsSmatchWithPenalty extends WangXueLossFunction {
    override def apply(gold: Sentence, test: Sentence, testActions: Array[WangXueAction]): Double = {

    val testAMR = test.amr match {
      case None => test.dependencyTree.toAMR
      case Some(graph) => graph
    }

    Smatch.naiveFScore(gold.amr.get, testAMR, 1, 1000)._4 + (testActions.size / 2.5)
  }
  
  // we set the maximum to be double the number of nodes in the dependency tree of the original sentence
  override def max(gold: Sentence): Double = gold.dependencyTree.nodes.size * 2

}

class WXNaiveAbsSmatch extends WangXueLossFunction {
    override def apply(gold: Sentence, test: Sentence, testActions: Array[WangXueAction]): Double = {

    val testAMR = test.amr match {
      case None => test.dependencyTree.toAMR
      case Some(graph) => graph
    }

    Smatch.naiveFScore(gold.amr.get, testAMR, 1, 1000)._4 
  }
  
  // we set the maximum to be double the number of nodes in the dependency tree of the original sentence
  override def max(gold: Sentence): Double = gold.dependencyTree.nodes.size * 2

}

class WXNaiveSmatch extends WangXueLossFunction {
    override def apply(gold: Sentence, test: Sentence, testActions: Array[WangXueAction]): Double = {

    val testAMR = test.amr match {
      case None => test.dependencyTree.toAMR
      case Some(graph) => graph
    }

    1.0 - Smatch.naiveFScore(gold.amr.get, testAMR, 1, 1000)._1 
  }
  
  // we set the maximum to be double the number of nodes in the dependency tree of the original sentence
  override def max(gold: Sentence): Double = 1.0

}
