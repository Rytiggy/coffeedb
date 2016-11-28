import java.sql.SQLException;
import java.util.ArrayList;

public class BLPapers {
   DLPapers dataLayer;
  
   public BLPapers() {
      dataLayer = new DLPapers();
   }
   
   public BLPapers(String _paperID) throws DLException {
      dataLayer = new DLPapers(_paperID);
   }
   
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
   
   public void fetchPaperAttributes() throws DLException { 
      dataLayer.fetchPaperAttributes();
   }
   
   public ArrayList<ArrayList<String>> fetchAllPapers() throws DLException {
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

    public void setPDF(byte[] pdf) {
        dataLayer.setPdfData(pdf);
    }

    public byte[] getPDF() {
        return dataLayer.getPdfData();
    }

    public void setAuthor(String _author) {
        dataLayer.setAuthor(_author);
    }

    public String getAuthor() {
        return dataLayer.getAuthor();
    }
   
}