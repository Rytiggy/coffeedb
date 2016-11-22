import java.io.*;
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
   public ArrayList<ArrayList<String>> viewDetails(String _paperID, String _url) throws DLException {
      ArrayList<ArrayList<String>> arr = new ArrayList<>();

      // Will return the selected paper's information to the gui

      BLPapers blPapers = new BLPapers();
      arr = blPapers.fetchPaper(blPapers.getPaperID());

      return arr;
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

      paper.postPaper();
   }
   
   
}