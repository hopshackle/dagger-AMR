# dagger-AMR

This project provides code for a transition-based parsing algorithm for English sentences to AMR. It is dependent on the imitation learning code provided in the hopshackle/mr-dagger repository.

Once built, the key aditional runtime dependency is the installation of WordNet, with the WNHOME environment variable pointing to this directory so that lemmas et al can be looked up.

A sample execution is:

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
  --num.cores 1 
  &> B010.txt &
