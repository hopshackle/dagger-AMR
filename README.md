# dagger-AMR

This project provides code for a transition-based parsing algorithm for English sentences to AMR. It is dependent on the imitation learning code provided in the hopshackle/mr-dagger repository. Currently all training uses AROW. Once built, the key aditional runtime dependency is the installation of WordNet, with the WNHOME environment variable pointing to this directory so that lemmas et al can be looked up.

The main Scala class used for execution is RunDagger. A wrapper java class ScalaRunner is provided that calls this (useful for some JVMs).

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

Also output are, for each iteration:
* `SmatchScores_<type>_<n>.txt`
* `AMR_prediction_<type>_<n>.txt`
* `AMR_target_<type>_<n>.txt`

Where `<n>` is the iteration number, and `<type>` can be `trng`, `val`, `test`, `instanceCollection`. The first three are for the training, validation and test sets respectively using the trained classifier. The last is from the trajectory generation stage that is used to create training data.

The key F-Scores are obtained by `grep` on the raw console output. ("Training" and "Validation" are useful searches.)

Key (non-obvious) parameters:
* `lossFunction` can be any combination of `NaiveSmatch, Smatch, Penalty, Abs`, suitable concatenated in that order
* `oracleLoss true` means that `lossFunction` is ignored completely, no RollOuts take place and simple 0-1 oracle loss is used. This is equivalent to Classic Dagger.
* `reentrance` of `true` will switch on Phase 2 of the algorithm, and consider re-entrant arcs
* `wikification` defaults to `true`. It enables the Wikification action.
* `reducedActions` defaults to `false`. Set to `true` to just consider the actions chosen by the expert and currently trained classifier at each step. As well as the bets option from the trained classifier, any other which are evaluated as being within `threshold` of the best option are also included as possible options.
* `threshold` is used with `reducedActions true` to determine which actions are RolledOut. Any action with a score from the current classifier within `threshold` of the best score will be rolled out (in addition to the expert action). If no classifier has yet been trained - so in the first iteration - a random number of actions are taken (defined by `rolloutLimit`).
* `rollOutLimit` defaults to 100, and is used with `reducedActions true`. It sets an upper bound on the number of actions that generate RollOuts (the highest scoring actions are taken until the bound is reached).
* `WXFeatures` is any concatenation of the characters `P` for parent-features, `A` for action-features, `C` for child-features, `X` for deletion features, `W` for word-features, and `S` for sibling features
* `maxActions` is a setting to prevent RollOuts getting into infinite loops and never terminating (or finite loops that are non-productive)
* `algorithm` can be any of `Dagger, LOLS, LOLSDet, LIDO, DILO, DILDO`
* `classifier` defaults to `AROW`. Other options are `PA` (Passive Aggressive) and `PERCEPTRON`.
* `initialExpertProb` and `policy.decay` provide the parameterisation for expert involvement. The one unavoidable override to these is that the expert is always used on the RollOuts with probability 1.0 in the first iteration.
* `debug true` will output details of RollIn trajectories and the losses calculated for each RollOut option considered in a set of files starting `CollectInstances_debug_`.
* `detail` defaults to `false`. If set to `true` and also `debug true`, then additional excruciating detail is switched on detailing full RollOout trajectories during instance collection.
* `prelimOracleRun` will execute a single pass on the training data using the expert to train a policy by simple imitation learning using 0-1 oracle loss. This is then used as the starting policy for the main run.
* `startingClassifier` defaults to `false`. If `true` then rather than start with an empty classifier in the first iteration, one is initialised from two files put into the location specified by `dagger.output.path`; a StartingClassifier.txt and a FeatureIndex.txt.
* `fileCache` set to `true` will store training instances on disk rather than in RAM. Recommended.
* `expertHorizon` defaults to `false`. If set to `true`, then the `expertAfter` and `expertHorizonInc` options are enabled 
* `expertAfter` will RollOut with the specified algorithm for the number of steps specified, and then use the expert 100% of the time.
* `expertHorizonInc` will increment the `expertAfter` parameter by the specified amount at each iteration after the first.
* `minTrainingSize` defaults to 100. All training examples with this numner of AMR nodes or fewer will be included in the first iteration of training.
* `trainingSizeInc` defaults to 10. This number will be added to `minTrainingSize` after each iteration to determine the maximum size of training sentences to include, as measured in number of AMR nodes.
* `maxTrainingSize` default to 100. This sets an upper ceiling on the size of training sentences to use.
* `instanceThreshold` specifies the alpha-bound to use in training. After this number of mis-classifications, the instance will be discarded from further training.
* `coachingLambda` will be used to multiply the score from the learned classifier for an action, which will be added to the loss calculated during training.
* `instanceThreshold` specifies the alpha-bound to use in training. After this number of mis-classifications, the instance will be discarded from further training.
* `logTrainingStats` defaults to `true`, and will run the trained classifier on the full training set at each iteration. To save time with large training sets, set this to `false`.
* `dropClassifier` defaults to `false`. Is set to `true` then the trained classifier is dropped after each iteration - so the next one is retrained from scratch. The default is to start from the current classifier, and train it further with all collected data.
* `previousTrainingIter` defaults to 100. This will use only training instances from the specified number of previous DAgger iterations. Setting this to 0 will therefore only use the current iteration's data. This option only works with `fileCache true`.
* `actionsPerSize` defaults to 5. This overrides `maxActions` on the training data to speed up early iterations. With the default setting, the number of AMR nodes is multiplied by 5, and this is used in place of `maxActions` if it is smaller.
* `samples` defaults to 1, and specified the number of RollOuts to generate for each action. This is relevant for v-DAgger, due to the stochasticity of the RollOuts. The final score is taken as an average of all samples.
* `num.cores` defaults to 1, and sets the number of cores that the instance collection can be split over. Trajectory generation / data collection benefits from this, but the batch classifier training is always single core.
* `random.seed` defaults to 1. Any other random seed can be set.
* `rare.feat.count` defaults to 0. If set higher, then any features with this many or fewer occurrences in the data will not be included in training.
* `shuffle` defaults to `false`. If switched on, then all instances will be shuffled before training takes place. Do not use with `fileCache true`, as this will load everything into memory and explodinate your machine.
* `preferKnown` defaults to `false`. With this setting the expert will preferentially use `VERB`, `LEMMA` and `WORD` parameters for naming nodes. This ensures there is training data that can generalise to unseen AMR concepts that happen to match english words. If set to `false`, then the expert will always use the full AMR concept name.
* `brownCluster` specified the file that specifies brown clusters to be used in features. If this option is not provided, then brown cluster features will be switched off.
* `aligner` defaults to `JAMR`, which uses the alignment code from the original JAMR system. Other values are `improved`, which uses another set of heuristics (which work OK on newswire data, but are dreadful on other data - see code for detail), and `Pourdamghani`, which uses the Pourdamghani alignment files included in the LDC corpus distribution. In this last case, the relevant file needs to be present as a <name>.txt_tok file, where <name> matches the name of the training file.

Output files that define the classifier are:
* `FinalClassifier.txt`
* `FeatureIndex.txt`
* `Classifier_<n>.txt`, where `<n>` is the iteration number.
* 
Once saved a classifier can be run over new data using `RunClassifier.scala`, or the java wrapper `ClassifierRunner.java`. These have the same parameter settings as above, although most are not used. The key ones are:

* `dagger.output.path` as above
* `train.data` needs to specify the original file of training data used to train the classifier
* `test.data` should be a semi-colon delimited list of AMR test files to be processed.
* `aligner, WXfeatures, reentrance, wikification, brownCluster, preferKnown` need to have the same values as used to train the original classifier (unfortunately the classifier file does not record these currently)
* `featureIndex` specifies the location of the FeatureIndex.txt file
* `classifier` specifies the location of the Classifier.txt file

This should then generate `AMR_prediction_base_<n>.txt` for each file specified in `test.data`, where `<n>` is just the order the files were listed.
