import java.util.ArrayList;
/*
* CoffeeDB
* Gustav, Aaron, Ryan, and Jeremy
* BLPapers
*/
public class BLPapers {
   DLPapers dataLayer;
  
   public BLPapers() {
      dataLayer = new DLPapers();
   }
   /**
    * BLPapers 
    * @param _paperID
    * @throws DLException
    */ 
   public BLPapers(String _paperID) throws DLException {
      dataLayer = new DLPapers(_paperID);
   }
   /**
    * createAuthorship 
    * @param _users
    * @throws DLException
    * @return dataLayer.createAuthorship(_users) true / false
    */ 
   public boolean createAuthorship(BLUser[] _users) throws DLException {
       return dataLayer.createAuthorship(_users);
    }
   /**
    * searchPapers 
    * @param searchInput
    * @return blPapers
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
    * fetchPaperAttributes 
    * @throws DLException
    */ 
   public void fetchPaperAttributes() throws DLException { 
      dataLayer.fetchPaperAttributes();
   }
   /**
    * fetchAllPapers 
    * @return dataLayer.fetchAllPapers
    * @throws DLException
    */ 
   public ArrayList<ArrayList<String>> fetchAllPapers() throws DLException {
      return dataLayer.fetchAllPapers();
   }
   /**
    * fetchPaper 
    * @param _ID
    * @return  return dataLayer.fetchPaper
    * @throws DLException
    */ 
    public DLPapers fetchPaper(String _ID) throws DLException {
        return dataLayer.fetchPaper();
    }
   /**
    * fetchAllKeywords 
    * @param searchInput
    * @return blPapers
    * @throws DLException
    */    
   public ArrayList<ArrayList<String>> fetchAllKeywords() throws DLException {
      return dataLayer.fetchAllKeywords();
   }
   /**
    * postPaper 
    * @return dataLayer.postPaper true if paper was posted
    * @throws DLException
    */    
   public boolean postPaper() throws DLException {
      return dataLayer.postPaper();
   }
   /**
    * updatePaper 
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
    * deletePaper 
    * @param myPaperId
    * @return dataLayer.deletePaper true if paper was successfully deleted
    * @throws DLException
    */    
   public boolean deletePaper(int myPaperId) throws DLException {
      return dataLayer.deletePaper(myPaperId);
   }
   /**
    * postKeywords 
    * @param _keywords
    * @throws DLException
    */      
   public void postKeywords(ArrayList<String> _keywords) throws DLException {
      dataLayer.postKeywords(_keywords);
   }
   /**
    * deleteKeyword 
    * @param _keyword
    * @throws DLException
    */ 
   public void deleteKeyword(String _keyword) throws DLException {
       dataLayer.deleteKeyword(_keyword);
   }
   /**
    * putKeyword 
    * @param _keyword
    * @throws DLException
    */ 
   public void putKeyword(String _keyword) throws DLException {
       dataLayer.putKeyword(_keyword);
   }
   /**
    * getTitle 
    * @param searchInput
    * @return dataLayer.getTitle
    * @throws DLException
    */ 
    public String getTitle() {
        return dataLayer.getTitle();
    }
   /**
    * setTitle 
    * @param title
    * @return dataLayer.setTitle
    * @throws DLException
    */ 
    public void setTitle(String title) {
       dataLayer.setTitle(title);
    }
   /**
    * getPaperAbstract 
    * @return dataLayer.getPaperAbstract
    */ 
    public String getPaperAbstract() {
      return dataLayer.getPaperAbstract();
    }
   /**
    * searchPapers 
    * @param searchInput
    * @return blPapers
    */ 
    public void setPaperAbstract(String paperAbstract) {
       dataLayer.setPaperAbstract(paperAbstract);
    }
   /**
    * getCitation 
    * @param searchInput
    * @return dataLayer.getCitation
    */ 
    public String getCitation() {
      return dataLayer.getCitation();
    }
   /**
    * setCitation 
    * @param citation
    */ 
    public void setCitation(String citation) {
       dataLayer.setCitation(citation);
    }
   /**
    * getPaperID 
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
    * getPDF 
    * @return dataLayer.getPdfData
    */ 
    public String getPDF() {
        return dataLayer.getPdfData();
    }
   /**
    * getUsers 
    * @return dataLayer.getUsers
    */ 
    public BLUser[] getUsers() {
        return dataLayer.getUsers();
    }
   /**
    * setUsers
    * @param users
    */
    public void setUsers(BLUser[] users) {
        dataLayer.setUsers(users);
    }
   
}