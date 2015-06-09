package amr
import dagger.core._

class WangXueOracle extends TransitionOracle[Sentence, WangXueAction, WangXueTransitionState] {

  def acceptableActions(state: amr.WangXueTransitionState): Seq[amr.WangXueAction] = ???
  
  def chooseTransition(o: amr.Sentence,state: amr.WangXueTransitionState): amr.WangXueAction = ???

}