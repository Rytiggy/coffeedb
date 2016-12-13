import java.util.ArrayList;
/*
* CoffeeDB
* Gustav, Aaron, Ryan, and Jeremy
* BLPapers
*/
public class BLPapers {
   DLPapers dataLayer;
   /**
    * BLPapers default constructor
    * @throws DLException
    */ 
   public BLPapers() {
      dataLayer = new DLPapers();
   }
   /**
    * BLPapers constructor with one parameter
    * @param _paperID
    * @throws DLException
    */ 
   public BLPapers(String _paperID) throws DLException {
      dataLayer = new DLPapers(_paperID);
   }
   /**
    * create Authorship 
    * @param _users
    * @throws DLException
    * @return dataLayer.createAuthorship(_users) true / false
    */ 
   public boolean createAuthorship(BLUser[] _users) throws DLException {
       return dataLayer.createAuthorship(_users);
    }
   /**
    * Uses search input to search all papers 
    * @param searchInput
    * @return blPapers ArrayList
    * @throws DLException
    */ 
   public ArrayList<BLPapers> searchPapers(String[] searchInput) throws DLException {
      ArrayList<DLPapers> dlPapers = dataLayer.searchPapers(searchInput);
      
      // Add each dlPaper to blPapers
      ArrayList<BLPapers> blPapers = new ArrayList<BLPapers>();
      for (DLPapers dlPaper : dlPapers) {
         BLPapers blPaper = new BLPapers(dlPaper.getPaperID());
         blPapers.add(blPaper);
      }
      
      return blPapers;
   }
   /**
    * fetch Paper Attributes 
    * @throws DLException
    */ 
   public void fetchPaperAttributes() throws DLException { 
      dataLayer.fetchPaperAttributes();
   }
   /**
    * fetch All Papers  
    * @return dataLayer.fetchAllPapers
    * @throws DLException
    */ 
   public ArrayList<ArrayList<String>> fetchAllPapers() throws DLException {
      return dataLayer.fetchAllPapers();
   }
   /**
    * fetch Paper 
    * @param _ID
    * @return  return dataLayer.fetchPaper
    * @throws DLException
    */ 
    public DLPapers fetchPaper(String _ID) throws DLException {
        return dataLayer.fetchPaper();
    }
   /**
    * fetch All Keywords 
    * @param searchInput
    * @return dataLayer.fetchAllKeywords
    * @throws DLException
    */    
   public ArrayList<ArrayList<String>> fetchAllKeywords() throws DLException {
      return dataLayer.fetchAllKeywords();
   }
   /**
    * post Paper
    * @return dataLayer.postPaper true if paper was posted
    * @throws DLException
    */    
   public boolean postPaper() throws DLException {
      return dataLayer.postPaper();
   }
   /**
    * update an exisiting Paper in the DB
    * @param myPaperId
    * @param myTitle
    * @param myPaperAbstract
    * @param myCitation
    * @param myPdfPath
    * @return dataLayer.updatePaper true if paper was updated 
    * @throws DLException
    */    
   public boolean updatePaper(int myPaperId, String myTitle, String myPaperAbstract, String myCitation, String myPdfPath) throws DLException {
      return dataLayer.updatePaper(myPaperId, myTitle, myPaperAbstract, myCitation, myPdfPath);
   }
   /**
    * delete Paper from DB
    * @param myPaperId
    * @return dataLayer.deletePaper true if paper was successfully deleted
    * @throws DLException
    */    
   public boolean deletePaper(int myPaperId) throws DLException {
      return dataLayer.deletePaper(myPaperId);
   }
   /**
    * post Keywords 
    * @param _keywords
    * @throws DLException
    */  
   public String[] getKeywords() {
      return dataLayer.getKeywords();
   } 
        
   public void postKeywords(ArrayList<String> _keywords) throws DLException {
      dataLayer.postKeywords(_keywords);
   }
   /**
    * delete Keyword 
    * @param _keyword
    * @throws DLException
    */ 
   public void deleteKeyword(String _keyword) throws DLException {
       dataLayer.deleteKeyword(_keyword);
   }
   /**
    * put Keyword 
    * @param _keyword
    * @throws DLException
    */ 
   public void putKeyword(String _keyword) throws DLException {
       dataLayer.putKeyword(_keyword);
   }
   /**
    * get Title 
    * @param searchInput
    * @return dataLayer.getTitle
    * @throws DLException
    */ 
    public String getTitle() {
        return dataLayer.getTitle();
    }
   /**
    * set Title 
    * @param title
    * @return dataLayer.setTitle
    * @throws DLException
    */ 
    public void setTitle(String title) {
       dataLayer.setTitle(title);
    }
   /**
    * get Paper Abstract 
    * @return dataLayer.getPaperAbstract
    */ 
    public String getPaperAbstract() {
      return dataLayer.getPaperAbstract();
    }
   /**
    * search Paper Abstract
    * @param paperAbstract
    */ 
    public void setPaperAbstract(String paperAbstract) {
       dataLayer.setPaperAbstract(paperAbstract);
    }
   /**
    * get Citation 
    * @param searchInput
    * @return dataLayer.getCitation
    */ 
    public String getCitation() {
      return dataLayer.getCitation();
    }
   /**
    * set Citation 
    * @param citation
    */ 
    public void setCitation(String citation) {
       dataLayer.setCitation(citation);
    }
   /**
    * get Paper ID 
    * @param searchInput
    * @return dataLayer.getPaperID
    */ 
    public String getPaperID() {
        return dataLayer.getPaperID();
    }
   /**
    * set Paper ID 
    * @param paperID
    */ 
    public void setPaperID(String paperID) {
         dataLayer.setPaperID(paperID);
    }
   /**
    * set PDF 
    * @param pdf
    */ 
    public void setPDF(String pdf) {
        dataLayer.setPdfData(pdf);
    }
   /**
    * get PDF 
    * @return dataLayer.getPdfData
    */ 
    public String getPDF() {
        return dataLayer.getPdfData();
    }
   /**
    * get Users 
    * @return dataLayer.getUsers
    */ 
    public BLUser[] getUsers() {
        return dataLayer.getUsers();
    }
   /**
    * set Users
    * @param users
    */
    public void setUsers(BLUser[] users) {
        dataLayer.setUsers(users);
    }
   
}