package amr

object stringmetrictets {
  import com.rockymadden.stringmetric.similarity.JaroMetric

  JaroMetric.compare("state", "statement").getOrElse(0.0)
                                                  //> res0: Double = 0.8518518518518517
  JaroMetric.compare("state", "to").getOrElse(0.0)//> res1: Double = 0.5666666666666667
  JaroMetric.compare("state", "state").getOrElse(0.0)
                                                  //> res2: Double = 1.0
  JaroMetric.compare("state", "made").getOrElse(0.0)
                                                  //> res3: Double = 0.6333333333333333
  JaroMetric.compare("Tajik", "Tajikistan").getOrElse(0.0)
                                                  //> res4: Double = 0.8333333333333334
  JaroMetric.compare("-", "Afghanistan").getOrElse(0.0)
                                                  //> res5: Double = 0.0
  JaroMetric.compare("a", "and").getOrElse(0.0)   //> res6: Double = 0.7777777777777777
  JaroMetric.compare("on", "country").getOrElse(0.0)
                                                  //> res7: Double = 0.7619047619047619
}