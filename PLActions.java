import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Functions of the GUI
public class PLActions {
   
   // Search FacResearchDB for papers given user input
   public ArrayList<BLPapers> search(String searchInput) throws DLException {
      // Used to retrieve all papers and keywords
      BLPapers blPapers = new BLPapers();
      
      // Sanitize and store user input 
      String[] input = searchInput.split(" ");      
      //List<String> searchWords = new ArrayList<String>(Arrays.asList(input));
      for(int i = 0; i < input.length; i++) {
         input[i].toUpperCase();
      }
      
      // Get all paper titles from DB
      ArrayList<ArrayList<String>> papers = blPapers.fetchAllPapers();
      // Get all paper keywords from DB     
      ArrayList<ArrayList<String>> paperKeywords = blPapers.fetchAllKeywords();
   
      // Match user input against keywords and titles. If a match, add the paper to the ArrayList of papers
      ArrayList<String> matchedPapers = new ArrayList<String>();
      for (int i = 0; i < papers.get(0).size(); i++) {
         // Check paper titles against user input
         
         // If we have a match, add to matchedPapers
      }
      
      for (int i = 0; i < paperKeywords.get(0).size(); i++) {
         // Check paper keywords against user input
         
         // If we have a match, add to matchedPapers
      }
      
      return matchedPapers;
   }   
   
   // Sign-in ------------------ Jeremy TODO
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