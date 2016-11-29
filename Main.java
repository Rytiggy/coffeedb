import java.util.ArrayList;

public class Main {
   public static void main(String [] Args) {
      try {
         PLActions test = new PLActions();
         
         ArrayList<BLPapers> testResults = test.search("ted test");
         for (BLPapers blPaper : testResults) {
            System.out.println(blPaper.getPaperID());
            System.out.println(blPaper.getPaperAbstract());
         }
         
      } catch (DLException e) {
         e.printStackTrace();
      }
   }
}