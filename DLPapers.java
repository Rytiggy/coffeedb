import java.sql.SQLException;
import java.util.ArrayList;

public class DLPapers {
   private String title;
   private String paperAbstract;
   private String citation;
   private String paperID;

   private String author;
   private String keyword;
   private MySQLDatabase msqlDB;

   private byte[] pdfData;
    
   // Default constructor used when paper ID is unknown
   public DLPapers() {
      paperID = null;
      msqlDB = new MySQLDatabase(); //<SHOULD NOT BE NULL NULL NULL SHOULD NTO TAKE ANY ARGS 
      title = null;
      paperAbstract = null;
      citation = null;
   }
   
   // Create a paper given an ID
   public DLPapers(String _paperID) throws DLException {
      this.paperID = _paperID;
      msqlDB = new MySQLDatabase();//<SHOULD NOT BE NULL NULL NULL SHOULD NTO TAKE ANY ARGS 
      
      // Set the paper's attributes from the DB
      fetchPaperAttributes();
   }
   
   // Get all existing papers in the database
   public ArrayList<ArrayList<String>> fetchAllPapers() throws DLException {
      msqlDB.connect();
      
      // Get all titles   
      String sql = "SELECT * FROM papers";
      ArrayList<ArrayList<String>> papers = msqlDB.getData(sql);
      
      /*  OLD CODE
      // Get all titles
      for (int i = 0; i < results.get(0).size(); i++) {
         papers.add(results.get(0).get(i)); // column 0, rows = i
      }*/
         
      return papers;
   }

   // Create a new paper
   public boolean postPaper() throws DLException {
      boolean succ = false;
   
      msqlDB.connect();
   
      ArrayList list = new ArrayList();
      list.add(this.title);
      list.add(this.author);
      list.add(this.paperAbstract);
      list.add(this.citation);
      list.add(this.pdfData);
   
      String sql = "INSERT INTO papers (title, abstract, citation, author, pdf)" +
                " VALUES (?, ?, ?, ?, ?);";
   
      if(msqlDB.setDataPDF(sql, list)) {
         succ = true;
      }
   
      msqlDB.close();
   
      return succ;
   }

   // Make changes to a paper
   public boolean putPaper() throws DLException {
      boolean succ = false;
   
      msqlDB.connect();
   
      ArrayList list = new ArrayList();
      list.add(this.paperID);
      list.add(this.title);
      list.add(this.paperAbstract);
      list.add(this.citation);
      list.add(this.paperID);
   
      String qu = "UPDATE papers SET ID=?, title=?, abstract=?, citation=? WHERE EquipID=?;";
   
      if (msqlDB.setData(qu, list)) {
         succ = true;
      }
   
      msqlDB.close();
   
      return succ;
   }

   // Delete a paper
   public boolean deletePaper() throws DLException {
      boolean succ = false;
   
      msqlDB.connect();
   
      ArrayList list = new ArrayList();
      list.add(this.paperID);
      String qu = "DELETE FROM papers WHERE ID=?;";
   
      if(msqlDB.setData(qu, list)) {
         succ = true;
      }
   
      msqlDB.close();
   
      return succ;
   }
   
   // Return a paper's attributes
   public ArrayList<ArrayList<String>> fetchPaper() throws DLException {
      ArrayList<ArrayList<String>> paperAttributes = new ArrayList();
      ArrayList<String> list = new ArrayList<String>();

      msqlDB.connect();

      list.add(this.paperID);
      String sql = "SELECT * FROM papers WHERE ID =?;";
      paperAttributes = msqlDB.getData(sql, list);
            
      msqlDB.close();

      return paperAttributes;
   }
   
   // Fetch and set this paper's attributes
   public void fetchPaperAttributes() throws DLException {
      ArrayList<ArrayList<String>> paperAttributes = new ArrayList();
      ArrayList<String> list = new ArrayList<String>();

      msqlDB.connect();

      list.add(this.paperID);
      String sql = "SELECT * FROM papers WHERE ID =?;";
      paperAttributes = msqlDB.getData(sql, list);
      
      // Set this paper's attributes
      title = paperAttributes.get(1).get(1);
      paperAbstract = paperAttributes.get(1).get(2);
      citation = paperAttributes.get(1).get(3);
      
      msqlDB.close();
   }
      
   // Search and find any papers matching user search input
   public ArrayList<DLPapers> searchPapers(String[] searchInput) throws DLException {
      ArrayList<ArrayList<String>> keywordResults = new ArrayList(); // results based on keywords
      ArrayList<ArrayList<String>> titleResults = new ArrayList(); // results based on titles
      ArrayList<DLPapers> matchedPapers = new ArrayList<DLPapers>(); // Papers returned from search     
      ArrayList<String> list = new ArrayList<String>(); // search input used for prepared statements
      
      // SQL Search Queries
      String keywordSQL = "SELECT * FROM paper_keywords WHERE keyword = ?";
      String titleSQL = "SELECT * FROM papers WHERE title = ?";
      
      msqlDB.connect();
      for (int i = 0; i < searchInput.length; i++) {
         // Clear list and add next search word
         list.clear();
         list.add(searchInput[i]);      
         
         // Query paper titles and keywords for the search word
         titleResults = msqlDB.getData(titleSQL, list);
         keywordResults = msqlDB.getData(keywordSQL, list);         
         
         // If there are any query results, add the paper to the returned results
         if (titleResults.size() > 1) {
            for (int j = 1; j < titleResults.size(); j++) {
               // Create new paper for each query result
               DLPapers matchedPaper = new DLPapers(titleResults.get(j).get(0));
               matchedPapers.add(matchedPaper);
               
               // Connection closed from creating new paper, so re-open it              
               msqlDB.connect();
            }
         }
         if (keywordResults.size() > 1) {
            for (int j = 1; j < keywordResults.size(); j++) {
               // Create new paper for each query result
               DLPapers matchedPaper = new DLPapers(keywordResults.get(j).get(0));
               matchedPaper.fetchPaperAttributes();
               
               // Connection closed from creating new paper, so re-open it 
               msqlDB.connect();
            }
         }         
      }
      
      // Lastly, eliminate any duplicate papers in matchedPapers
      for (int i = 0; i < matchedPapers.size(); i++) {
         for (int j = 0; j < matchedPapers.size(); j++) {
            if(matchedPapers.get(i) == matchedPapers.get(j) && i != j) {
               matchedPapers.remove(i);
               i -= 1; 
            }
         }
      }
           
      return matchedPapers;
   }

   // Return all papers with a particular keyword
   public byte[] getPdfData() {
      return pdfData;
   }

   public void setPdfData(byte[] pdfData) {
      this.pdfData = pdfData;
   }

   public String getAuthor() {
      return author;
   }

   public void setAuthor(String author) {
      this.author = author;
   }

   // Return a keyword
   public void fetchKeyword() throws DLException {
         ArrayList<ArrayList<String>> arr = new ArrayList();
         ArrayList<String> list = new ArrayList<String>();

         msqlDB.connect();
         list.add(this.keyword);
         String sql = "SELECT * FROM paper_keywords WHERE keyword = ?;";
         arr = msqlDB.getData(sql, list);         
         
         msqlDB.close();
   }

   // Return all keywords from DB
   public ArrayList<ArrayList<String>> fetchAllKeywords() throws DLException {
      ArrayList<ArrayList<String>> keywords = new ArrayList<ArrayList<String>>();

      msqlDB.connect();
      String sql = "SELECT * FROM paper_keywords;";
      keywords = msqlDB.getData(sql);

      return keywords;
   }

   // Create a new keyword
   public void postKeyword(String _keyword) throws DLException {
         ArrayList list = new ArrayList();

         msqlDB.connect();
         list.add(_keyword);
         String sql = "INSERT INTO paper_keywords (keyword)" + "VALUES (?)";
         msqlDB.setData(sql, list);
         msqlDB.close();
   }

   // Delete all paper_keywords for a given keyword
   public void deleteKeyword(String _keyword) throws DLException {

         msqlDB.connect();
         ArrayList list = new ArrayList();
         list.add(_keyword);
         String sql = "DELETE FROM paper_keywords WHERE keyword = ?;";
         msqlDB.setData(sql, list);
         msqlDB.close();
   }

   // Make changes to a keyword -- THIS NEEDS TO BE CHANGED.
   // Should also take in an id or the keyword that is getting updated
   public void putKeyword(String _keyword) throws DLException {
         ArrayList list = new ArrayList();

         msqlDB.connect();
         list.add(_keyword);
         String sql = "UPDATE keyword_papers SET keyword = ? WHERE keyword = ?";
         list.add(this.keyword);
         msqlDB.setData(sql, list);
         msqlDB.close();
   }
   
   // Getters and setters
   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getPaperAbstract() {
      return paperAbstract;
   }

   public void setPaperAbstract(String paperAbstract) {
      this.paperAbstract = paperAbstract;
   }

   public String getCitation() {
      return citation;
   }

   public void setCitation(String citation) {
      this.citation = citation;
   }

   public String getPaperID() {
      return paperID;
   }

   public void setPaperID(String paperID) {
      this.paperID = paperID;
   }

}