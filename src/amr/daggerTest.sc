package amr

object daggerTest {

val classifier = SampleExpertTrajectory.testDAGGERrun
                                                  //> java.lang.NoClassDefFoundError: amr/SampleExpertTrajectory$
                                                  //| 	at amr.daggerTest$$anonfun$main$1.apply$mcV$sp(amr.daggerTest.scala:5)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$$anonfun$$exe
                                                  //| cute$1.apply$mcV$sp(WorksheetSupport.scala:76)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.redirected(W
                                                  //| orksheetSupport.scala:65)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.$execute(Wor
                                                  //| ksheetSupport.scala:75)
                                                  //| 	at amr.daggerTest$.main(amr.daggerTest.scala:3)
                                                  //| 	at amr.daggerTest.main(amr.daggerTest.scala)
                                                  //| Caused by: java.lang.ClassNotFoundException: amr.SampleExpertTrajectory$
                                                  //| 	at java.net.URLClassLoader$1.run(Unknown Source)
                                                  //| 	at java.net.URLClassLoader$1.run(Unknown Source)
                                                  //| 	at java.security.AccessController.doPrivileged(Native Method)
                                                  //| 	at java.net.URLClassLoader.findClass(Unknown Source)
                                                  //| 	at java.lang.ClassLoader.loadClass(Unknown Source)
                                                  //| 	at sun.misc.Launcher$AppClassLoader.loadClass(Unknown Source)
                                                  //| 	at java.lang.ClassLoader.loadClass(Unknown Source)
                                                  //| 	... 6 more
}