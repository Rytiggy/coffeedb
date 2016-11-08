import java.sql.SQLException;
import java.util.ArrayList;

public class DLPapers {

    private String title, paperAbstract, citation, paperID;
    private MySQLDatabase msqlDB;;

    public DLPapers(String _paperID) {
        this.paperID = _paperID;
        msqlDB = new MySQLDatabase();
        title = null;
        paperAbstract = null;
        citation = null;
    }

    public ArrayList<ArrayList<String>> fetchPaper() throws DLException {
        ArrayList<ArrayList<String>> arr = new ArrayList();
        ArrayList<String> list = new ArrayList<String>();

        msqlDB.connect();

        list.add(this.paperID);
        String sql = "SELECT * FROM papers WHERE ID =?;";

        arr = msqlDB.getData(sql, list);
        msqlDB.close();

        return arr;
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

    public void fetchKeyword() {

    }

    public void postKeyword(String _keyword) {

    }

    public void deleteKeyword(String _keyword) {

    }

    public void putKeyword(String _keyword) {

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
}