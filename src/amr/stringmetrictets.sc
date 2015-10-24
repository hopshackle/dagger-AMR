package amr

object stringmetrictets {
  import com.rockymadden.stringmetric.similarity._

  JaroMetric.compare("state", "statement").getOrElse(0.0)
                                                  //> res0: Double = 0.8518518518518517
                                                  JaroWinklerMetric.compare("state", "statement").getOrElse(0.0)
                                                  //> res1: Double = 0.9111111111111111
  JaroMetric.compare("state", "to").getOrElse(0.0)//> res2: Double = 0.5666666666666667
  JaroMetric.compare("state", "state").getOrElse(0.0)
                                                  //> res3: Double = 1.0
  JaroMetric.compare("state", "made").getOrElse(0.0)
                                                  //> res4: Double = 0.6333333333333333
    JaroWinklerMetric.compare("state", "made").getOrElse(0.0)
                                                  //> res5: Double = 0.6333333333333333
  JaroMetric.compare("Tajik", "Tajikistan").getOrElse(0.0)
                                                  //> res6: Double = 0.8333333333333334
  JaroMetric.compare("-", "Afghanistan").getOrElse(0.0)
                                                  //> res7: Double = 0.0
  JaroMetric.compare("a", "and").getOrElse(0.0)   //> res8: Double = 0.7777777777777777
  JaroMetric.compare("on", "country").getOrElse(0.0)
                                                  //> res9: Double = 0.7619047619047619
                                                  
  JaroMetric.compare("country", "contractor").getOrElse(0.0)
                                                  //> res10: Double = 0.7380952380952381
  JaroWinklerMetric.compare("country", "contractor").getOrElse(0.0)
                                                  //> res11: Double = 0.7904761904761906
    JaroMetric.compare("contract", "contractor").getOrElse(0.0)
                                                  //> res12: Double = 0.9333333333333332
  JaroWinklerMetric.compare("contract", "contractor").getOrElse(0.0)
                                                  //> res13: Double = 0.96
  
  JaroMetric.compare("canada", "and").getOrElse(0.0)
                                                  //> res14: Double = 0.8333333333333334
  JaroWinklerMetric.compare("canada", "and").getOrElse(0.0)
                                                  //> res15: Double = 0.8333333333333334
}