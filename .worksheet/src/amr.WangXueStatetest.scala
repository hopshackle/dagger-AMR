package amr

object WangXueStatetest {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(86); 

val testSentence = Sentence("This is a test.");System.out.println("""testSentence  : amr.Sentence = """ + $show(testSentence ));$skip(43); 
val WXSystem = new WangXueTransitionSystem;System.out.println("""WXSystem  : amr.WangXueTransitionSystem = """ + $show(WXSystem ));$skip(48); 

val initialState = WXSystem.init(testSentence);System.out.println("""initialState  : amr.WangXueTransitionState = """ + $show(initialState ));$skip(84); 
val testSentence2 = Sentence("The police want to arrest Michael Karras in London.");System.out.println("""testSentence2  : amr.Sentence = """ + $show(testSentence2 ));$skip(50); 

val initialState2 = WXSystem.init(testSentence2);System.out.println("""initialState2  : amr.WangXueTransitionState = """ + $show(initialState2 ))}
}
