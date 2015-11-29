# dagger-AMR

This project provides code for a transition-based parsing algorithm for English sentences to AMR. It is dependent on the imitation learning code provided in the hopshackle/mr-dagger repository. Currently all training uses AROW.

Once built, the key aditional runtime dependency is the installation of WordNet, with the WNHOME environment variable pointing to this directory so that lemmas et al can be looked up.

A sample execution is:
```
  java -Xms1g -Xmx3g -jar *insert name of executable*
  --dagger.output.path ./ 
  --dagger.iterations 20 
  --dagger.print.interval 1 
  --train.data ../amr-1.0-proxy-train-half.txt 
  --validation.data ../amr-1.0-proxy-dev.txt 
  --samples 1 
  --lossFunction NaivePenaltyAbs 
  --initialExpertProb 1.0 
  --arow.iterations 10 
  --policy.decay 1.0 
  --punctuation false 
  --reentrance false 
  --algorithm Dagger
  --maxActions 200 
  --unrollExpert false 
  --oracleLoss true 
  --aligner JAMR 
  --insertProhibition true 
  --WXfeatures PACX 
  --reducedActions true 
  --threshold 0.40
  --instanceThreshold 1
  --prelimOracleRun
  --fileCache false 
  --startingClassifier false 
  --expertAfter 20 
  --expertHorizonInc 2 
  --maxTrainingSize 300 
  --WangXue true 
  --assertionChecking false
  --arow.smoothing 100 
  --discard.old.instances false 
  --debug false
  --num.cores 1 
  &> B010.txt &
```

There output in the specified output directory includes Smatch scores for each sentence at each iteration in the validation and training set, plus the AMR output for each. If debug is switched on, then the key log file is CollectInstances_debug_I.txt for iteration I. This contains information on the RollIn trajectory taken, plus the losses calculated for each RollOut action considered.

The key F-Scores are obtained by `grep` on the raw console output. ("Training" and "Validation" are useful searches.)

Key (non-obvious) parameters:
* `lossFunction` can be any combination of `NaiveSmatch, Smatch, Penalty, Abs`, suitable concatenated in that order
* `reentrance` of `true` will switch on Phase 2 of the algorithm, and consider re-entrant arcs
* `oracleLoss true` means that `lossFunction` is ignored completely, no RollOuts take place and simple 0-1 oracle loss is used. This is equivalent to Classic Dagger.
* `reducedActions` of `true` just considers the actions chosen by the expert and currently trained classifier at each step. As well as the bets option from the trained classifier, any other which are evaluated as being within `threshold` of the best option are also included as possible options.
* `WXFeatures` is any concatenation of the characters `P` for parent-features, `A` for action-features, `C` for child-features, `X` for deletion features, `W` for word-features, and `S` for shenanigans
* `maxActions` is a setting to prevent RollOuts getting into infinite loops and never terminating (or finite loops that are non-productive)
* `algorithm` can be any of `Dagger, LOLS, LOLSDet, LIDO, DILO, DILDO`
* `initialExpertProb` and `policy.decay` provide the parameterisation for expert involvement. The one unavoidable override to these is that the expert is always used on the RollOuts with probability 1.0 in the first iteration.
* `debug true` will output details of RollIn trajectories and the losses calculated for each RollOut option considered in a set of files starting `CollectInstances_debug_`.
* `prelimOracleRun` will execute a single pass on the training data using the expert to train a policy by simple imitation learning using 0-1 oracle loss. This is then used as the starting policy for the main run.
* `fileCache` set to `true` will store training instances on disk rather than in RAM. Recommended.
* `expertAfter` will RollOut with the specified algorithm for the number of steps specified, and then use the expert 100% of the time. 
* `expertHorizonInc` will increment the `expertAfter` parameter by the specified amount at each iteration after the first.
* `WangXue` should be set to `true`. `false` uses a different transition system that is not well tested.
* `maxTrainingSize` will filter out any training instances with more than the specified number of AMR nodes. These tend to have long, noisy trajectories and their removal speeds up training at the cost of a reduction in final performance.
* `instanceThreshold` specifies the alpha-bound to use in training. After this number of mis-classifications, the instance will be discarded from further training.
* `coachingLambda` will be used to multiply the score from the learned classifier for an action, which will be added to the loss calculated during training.


