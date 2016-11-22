import java.io.*;
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
   public String[] viewDetails(String _paperID, String _url) throws DLException {
      ArrayList<ArrayList<String>> arr = new ArrayList<>();
      String[] paperDetails = new String[6];

      // Will return the selected paper's information to the gui

      BLPapers blPapers = new BLPapers();
      arr = blPapers.fetchPaper(blPapers.getPaperID());
      for(int i = 0; i<arr.get(i).size(); i++) {
         paperDetails[i] = arr.get(1).get(i);
      }

      return paperDetails;
   }
   
   // Upload
   public void uploadPaper(String _title, String _citation, String _abstract, String _url, String _author) throws DLException {
      // Reads in text from file and then inserts the paper
      // whenever a faculty is "updating" a paper, they will just be re-writing over the old one

      String url = null;
      File pdfFile = new File(url);
      byte[] pdfData = new byte[(int) pdfFile.length()];
      DataInputStream dis = null;

      try {
         dis = new DataInputStream(new FileInputStream(pdfFile));
         dis.readFully(pdfData);  // read from file into byte[] array
         dis.close();
      } catch (FileNotFoundException fnfe) {
         fnfe.printStackTrace();
      } catch (IOException ioe) {
         ioe.printStackTrace();
      }

      BLPapers paper = new BLPapers();
      paper.setCitation(_citation);
      paper.setPaperAbstract(_abstract);
      paper.setTitle(_title);
      paper.setPDF(pdfData);
      paper.setAuthor(_author);

      paper.postPaper();
   }
   
   
}