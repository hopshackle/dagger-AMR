package amr
import dagger.core._

class WangXueOracle extends HeuristicPolicy[Sentence, WangXueAction, WangXueTransitionState] {
  
  def chooseTransition(data: Sentence,state: WangXueTransitionState): WangXueAction = ???

}