import java.sql.SQLException;
import java.util.ArrayList;

public class DLPapers {
   private String title, paperAbstract, citation, paperID, keyword;
   private MySQLDatabase msqlDB;
    
   // Default constructor used when paper ID is unknown
   public DLPapers() {
      paperID = null;
      msqlDB = new MySQLDatabase(); //<SHOULD NOT BE NULL NULL NULL SHOULD NTO TAKE ANY ARGS 
      title = null;
      paperAbstract = null;
      citation = null;
   }
   
   // Create a paper given an ID
   public DLPapers(String _paperID) {
      this.paperID = _paperID;
      msqlDB = new MySQLDatabase();//<SHOULD NOT BE NULL NULL NULL SHOULD NTO TAKE ANY ARGS 
      title = null;
      paperAbstract = null;
      citation = null;
   }
   
   public ArrayList<String> fetchAllPapers() throws DLException {
      ArrayList<String> papers = new ArrayList<String>();
      msqlDB.connect();
         
      String sql = "SELECT title FROM papers";
      ArrayList<ArrayList<String>> results = msqlDB.getData(sql);
         
      // Get all titles
      for (int i = 0; i < results.get(0).size(); i++) {
         papers.add(results.get(0).get(i)); // column 0, rows = i
      }
         
      return papers;
   }
   
   public boolean postPaper() throws DLException {
      boolean succ = false;
   
      msqlDB.connect();
   
      ArrayList list = new ArrayList();
      list.add(this.title);
      list.add(this.paperID);
      list.add(this.paperAbstract);
      list.add(this.citation);
   
      String sql = "INSERT INTO papers (ID, title, abstract, citation)" +
                " VALUES (?, ?, ?, ?);";
   
      if(msqlDB.setData(sql, list)) {
         succ = true;
      }
   
      msqlDB.close();
   
      return succ;
   }

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

   public ArrayList<ArrayList<String>> fetchPaper() throws DLException {
      ArrayList<ArrayList<String>> arr = new ArrayList();
      ArrayList<String> list = new ArrayList<String>();
   
      try {
         msqlDB.connect();
      } 
      catch(Exception e) { //fix this
      }
   
      list.add(this.paperID);
      String sql = "SELECT * FROM papers WHERE ID =?;";
   
      try {
         arr = msqlDB.getData(sql, list);
         msqlDB.close();
      } 
      catch(Exception e) { //Fix this
      }
   
      return arr;
   }

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

   public void fetchKeyword() throws DLException {
      ArrayList<ArrayList<String>> arr = new ArrayList();
      ArrayList<String> list = new ArrayList<String>(); 
      try {
         msqlDB.connect();
         list.add(this.keyword);
         String sql = "SELECT * FROM paper_keywords WHERE keyword = ?;";
         arr = msqlDB.getData(sql, list);
         msqlDB.setData(sql, list);
         msqlDB.close();
      }
      catch(Exception e){
         e.printStackTrace();
         msqlDB.close();
      }
   }

   public void postKeyword(String _keyword) throws DLException {
      ArrayList list = new ArrayList(); 
      try {
         msqlDB.connect();
         list.add(this.keyword);
         String sql = "INSERT INTO paper_keywords (keyword)" + "VALUES (?);";
         msqlDB.setData(sql, list);
         msqlDB.close();
      
      }
      catch(Exception e){
         e.printStackTrace();
         msqlDB.close();      
      }
   }

   public void deleteKeyword(String _keyword) throws DLException {
      try {
         msqlDB.connect();
         ArrayList list = new ArrayList();
         list.add(this.keyword);
         String sql = "DELETE FROM paper_keywords WHERE keyword = ?;";
         msqlDB.setData(sql, list);
         msqlDB.close();         
      }
      catch(Exception e){
         e.printStackTrace();
         msqlDB.close();      
      }
   }

   public void putKeyword(String _keyword) throws DLException {
      ArrayList list = new ArrayList();
      try {
         msqlDB.connect();
         list.add(this.keyword);
         String sql = "UPDATE keyword_papers SET keyword = ?;";
         msqlDB.setData(sql, list);
         msqlDB.close();
      
      }
      catch(Exception e){
         e.printStackTrace();
         msqlDB.close();    
      }   
   }
}