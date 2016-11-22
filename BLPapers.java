import java.sql.SQLException;
import java.util.ArrayList;

public class BLPapers {
   DLPapers dataLayer;
  
   public BLPapers() {
      dataLayer = new DLPapers();
   }
   
   public BLPapers(String _paperID){
      dataLayer = new DLPapers(_paperID);
   }
   
   public ArrayList<String> fetchAllPapers() throws DLException {
      return dataLayer.fetchAllPapers();
   }

    public ArrayList<ArrayList<String>> fetchPaper(String _ID) throws DLException {
        return dataLayer.fetchPaper();
    }
   
   public ArrayList<ArrayList<String>> fetchAllKeywords() throws DLException {
      return dataLayer.fetchAllKeywords();
   }
   
   public boolean postPaper() throws DLException {
      return dataLayer.postPaper();
   }
   
   public boolean putPaper() throws DLException {
      return dataLayer.putPaper();
   }
   
   public boolean deletePaper() throws DLException {
      return dataLayer.deletePaper();
   }
   
   public void fetchKeyword() throws DLException {
       dataLayer.fetchKeyword();
   }

   public void postKeyword(String _keyword) throws DLException {
      dataLayer.postKeyword(_keyword);
   }

   public void deleteKeyword(String _keyword) throws DLException {
       dataLayer.deleteKeyword(_keyword);
   }

   public void putKeyword(String _keyword) throws DLException {
       dataLayer.putKeyword(_keyword);
   }

    public String getTitle() {
        return dataLayer.getTitle();
    }

    public void setTitle(String title) {
       dataLayer.setTitle(title);
    }

    public String getPaperAbstract() {
      return dataLayer.getPaperAbstract();
    }

    public void setPaperAbstract(String paperAbstract) {
       dataLayer.setPaperAbstract(paperAbstract);
    }

    public String getCitation() {
      return dataLayer.getCitation();
    }

    public void setCitation(String citation) {
       dataLayer.setCitation(citation);
    }

    public String getPaperID() {
        return dataLayer.getPaperID();
    }

    public void setPaperID(String paperID) {
         dataLayer.setPaperID(paperID);
    }
   
}