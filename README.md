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
  --arow.smoothing 100 
  --discard.old.instances false 
  --debug false
  --num.cores 1 
  &> B010.txt &
```

There is a load of output written to various files for `dot` representations of the end-results of each training instance. The key F-Scores are obtained by `grep` on the raw console output. ("Training" and "Validation" are useful searches.)

Key (non-obvious) parameters:
* `lossFunction` can be any combination of `NaiveSmatch, Smatch, Penalty, Abs`, suitable concatenated in that order
* `reentrance` of `true` will switch on Phase 2 of the algorithm, and consider re-entrant arcs
* `oracleLoss true` means that `lossFunction` is ignored completely, no RollOuts take place and simple 0-1 oracle loss is used
* `reducedActions` of `true` just considers the actions chosen by the expert and currently trained classifier at each step
* `WXFeatures` is any concatenation of the characters `P` for parent-features, `A` for action-features, `C` for child-features, `X` for deletion features, `W` for word-features, and `S` for shenanigans
* `maxActions` is a setting to prevent RollOuts getting into infinite loops and never terminating (or finite loops that are non-productive)
* `algorithm` can be any of `Dagger, LOLS, LOLSDet, LIDO, DILO, DILDO`
* `initialExpertProb` and `policy.decay` provide the parameterisation for expert involvement. The one unavoidable override to these is that the expert is always used on the RollOuts with probability 1.0 in the first iteration.
* `debug true` will output details of RollIn trajectories and the losses calculated for each RollOut option considered in a set of files starting `CollectInstances_debug_`.

