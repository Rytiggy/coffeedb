import java.util.ArrayList;
/**
* CoffeeDB
* @author Gustav
* @author Aaron
* @author Ryan
* @author Jeremy
* Main
*/
public class Main {

   /**
   * This is the main method which makes use the following classes:BLPapers, BLUser, DLException, DLPaperKeywords, DLPapers, DLUser
   * MySQLDatabase,PLActions, and PLGUI.
   * @param args Unused.
   * @return Nothing.
   * @throws DLException if a system exception occurred
   * @see IOException
   */
   public static void main(String [] Args) {
      
      try {
         PLActions test = new PLActions();
         
         ArrayList<BLPapers> testResults = test.search("ted test");
         for (BLPapers blPaper : testResults) {
            //System.out.println(blPaper.getPaperID());
            //System.out.println(blPaper.getPaperAbstract());
         }
         
         
         BLUser user = new BLUser();
         user.login("jhh1688@rit.edu", "test");
         
         //System.out.println("ID: "+ user.getUserId() + "| fName: " + user.getFName() + "| lName: " + user.getLName() +
         //"| role: " + user.getRole());

    

        // BLPapers paper = new BLPapers();
        // System.out.println(paper.deletePaper(2));
         

         
         /*
         BLPapers paper = new BLPapers("1");
         String[] testKeywords = paper.getKeywords();
         for(int i = 0; i < testKeywords.length; i++) {           
            System.out.println(testKeywords[i]);
         }
         /*
         paper.updatePaper(1, "t23t", "test", "test", "test");
         
         /*
         BLPapers paper = new BLPapers();
         System.out.println(paper.deletePaper(1));
         */
      } catch (DLException e) {
         //e.printStackTrace();
      }
   }
}