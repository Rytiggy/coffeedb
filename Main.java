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
         
         
         BLUser user = new BLUser();
         user.login("jhh1688@rit.edu", "test");
         
         System.out.println("ID: "+ user.getUserId() + "| fName: " + user.getFName() + "| lName: " + user.getLName() +
         "| role: " + user.getRole());

    

         BLPapers paper = new BLPapers();
         System.out.println(paper.deletePaper(2));
         
      } catch (DLException e) {
         e.printStackTrace();
      }
   }
}