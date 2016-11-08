import java.sql.SQLException;
import java.util.ArrayList;

public class BLPapers {
   DLPapers dataLayer;
  

   public BLPapers(String _paperID){
      dataLayer = new DLPapers(_paperID);
   }
   
   public boolean postPaper() {
      return dataLayer.postPaper();
   }
   
   public boolean putPaper() {
      return dataLayer.putPaper();
   }
   
   public boolean deletePaper() {
      return dataLayer.deletePaper();
   }
   
   public void fetchKeyword() {
       dataLayer.fetchKeyword();
   }

   public void postKeyword(String _keyword) {
      dataLayer.postKeyword(_keyword);
   }

   public void deleteKeyword(String _keyword) {
       dataLayer.deleteKeyword(_keyword);
   }

   public void putKeyword(String _keyword) {
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