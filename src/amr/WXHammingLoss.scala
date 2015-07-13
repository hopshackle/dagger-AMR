package amr

import dagger.core.LossFunction

class WXHammingLoss extends LossFunction[Sentence, WangXueAction, WangXueTransitionState] {

  override def apply(gold: Sentence, test: Sentence, testActions: Array[WangXueAction], expertActions: Array[WangXueAction],
    trialAction: WangXueAction, lastExpertAction: WangXueAction): Double = {
    
    val allExpert = lastExpertAction :: expertActions.toList
    val allSample = trialAction :: testActions.toList
    
    val expertMap = allExpert.groupBy(identity).mapValues(_.size)
    val sampleMap = allSample.groupBy(identity).mapValues(_.size)
    
    val mistakes1 = (expertMap.keySet map {key => Math.max(expertMap(key) - sampleMap.getOrElse(key, 0), 0)}) reduce (_ + _)
    val mistakes2 = (sampleMap.keySet map {key => Math.max(sampleMap(key) - expertMap.getOrElse(key, 0), 0)}) reduce (_ + _)
    
    mistakes1 + mistakes2
  }

  override def apply(gold: Sentence, test: Sentence, testActions: Array[WangXueAction]): Double = {
    1.0
  }

  override def max(d: Sentence): Double = 100.0

}