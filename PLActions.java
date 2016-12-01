import java.io.*;
import java.util.ArrayList;

// Functions of the GUI
public class PLActions {
   
   // Default Constructor
   public PLActions() {
   
   }
   
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
      
      ArrayList<BLPapers> matchedPapers = blPapers.searchPapers(input);
      
      /* OLD CODE -- DELETE IN FUTURE
      // Get all paper titles from DB
      ArrayList<ArrayList<String>> papers = blPapers.fetchAllPapers();
      
      // Get all paper keywords from DB     
      ArrayList<ArrayList<String>> paperKeywords = blPapers.fetchAllKeywords();
      
      // Match user input against keywords and titles. If a match, add the paper to the ArrayList of papers
      ArrayList<BLPapers> matchedPapers = new ArrayList<BLPapers>();
      for (int i = 0; i < input.length; i++) {
         for (int j = 0; i < papers.get(0).size(); j++) {
            // Check paper titles against user input
            if (input[i] == papers.get(1).get(i)) {
               // If we have a match based on paper title, create new paper object and add to matchedPapers
               BLPapers matchedPaper = new BLPapers(papers.get(0).get(i));
               matchedPapers.add(matchedPaper);
            }
         }
         for (int k = 0; k < paperKeywords.get(0).size(); k++) {
            // If we have a match based on keyword, create a new paper object and add it to matchedPapers
            if (input[i] == paperKeywords.get(1).get(k)) {
               BLPapers matchedPaper = new BLPapers(paperKeywords.get(0).get(k));
               matchedPapers.add(matchedPaper);
            }
         }       
      }
      */
          
      return matchedPapers;
   }   
   
   // Sign-in ------------------ Jeremy TODO
   public void signIn() {
      // sign the faculty user in
   }
   
   // View Details 
   public BLPapers viewDetails(String _paperID) throws DLException {
      BLPapers blPapers = new BLPapers(_paperID);
      return blPapers;
   }
   
   // Upload
   public void uploadPaper(String _title, String _citation, String _abstract, String _url, String _author) throws DLException {
      // Reads in text from file and then inserts the paper
      // whenever a faculty is "updating" a paper, they will just be re-writing over the old one

      String url = _url;
      String newUrl = "papers/"+_title+".pdf";
      File pdfFile = new File(url);
      byte[] pdfData = new byte[(int) pdfFile.length()];
      DataInputStream dis = null;
      DataOutputStream dos = null;

      try {
         dis = new DataInputStream(new FileInputStream(pdfFile));
         dis.readFully(pdfData);  // read from file into byte[] array
         dos = new DataOutputStream(new FileOutputStream(newUrl));
         dos.write(pdfData);
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
      paper.setPDF(newUrl);
      paper.setAuthor(_author);

      paper.postPaper();
   }

   public static void main(String[] args) {
      PLActions plActions = new PLActions();
      try {
         plActions.uploadPaper("Hej", "Hej", "Hej","papers/jugge.pdf","Hej");
      } catch (DLException e) {
         e.printStackTrace();
      }
   }
   
   
}