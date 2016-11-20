import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Functions of the GUI
public class PLActions {
   
   // Search FacResearchDB for papers given user input
   public ArrayList<BLPapers> search(String searchInput) {
      
      // Sanitize and store user input 
      String[] input = searchInput.split(" ");      
      //List<String> searchWords = new ArrayList<String>(Arrays.asList(input));
      for(int i = 0; i < input.length; i++) {
         input[i].toUpperCase();
      }
      
      // Get all paper titles from DB
      ArrayList<String> papers = null;
      try {
         BLPapers blPapers = new BLPapers();
         papers = blPapers.fetchAllPapers();
      }
      catch (DLException dle) {
         // print out error
      }
      // Get all paper keywords from DB
           
           
      // Match user input against keywords and titles. If a match, add the paper to the ArrayList of papers
      ArrayList<BLPapers> matchedPapers = null;
      return matchedPapers;
   }   
   
   // Sign-in ------------------ TODO
   public void signIn() {
      // sign the faculty user in
   }
   
   // View Details 
   public ArrayList<ArrayList<String>> viewDetails(int paperID) {
      // Will return the selected paper's information to the gui
      
      return null;
   }
   
   // Upload
   public void uploadPaper() {
      // Reads in text from file and then inserts the paper
      // whenever a faculty is "updating" a paper, they will just be re-writing over the old one
   }
   
   
}