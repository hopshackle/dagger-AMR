package amr

import dagger.core.LossFunction

class WXHammingLoss extends LossFunction[Sentence, WangXueAction, WangXueTransitionState] {

  override def apply(gold: Sentence, test: Sentence, testActions: Array[WangXueAction], expertActions: Array[WangXueAction],
    trialAction: WangXueAction, lastExpertAction: WangXueAction): Double = {
    
    val allExpert = (lastExpertAction :: expertActions.toList) filter (_ != null)
    val allSample = (trialAction :: testActions.toList) filter (_ != null)
    
    val expertMap = allExpert.groupBy(identity).mapValues(_.size)
//    expertMap.keys foreach println
//    expertMap foreach println
    val sampleMap = allSample.groupBy(identity).mapValues(_.size)
//    sampleMap.keys foreach println
//    sampleMap foreach println
    val mistakes1 = expertMap map {case (key, count) => Math.max(count - sampleMap.getOrElse(key, 0), 0)}
    val mistakes2 = sampleMap map {case (key, count) => Math.max(count - expertMap.getOrElse(key, 0), 0)}
//    println(s"NonMatching Expert: $mistakes1 \nNonMatching Sample: $mistakes2")
    mistakes1.sum + mistakes2.sum
  }

  override def apply(gold: Sentence, test: Sentence, testActions: Array[WangXueAction]): Double = {
    1.0
  }

  override def max(d: Sentence): Double = 100.0

}