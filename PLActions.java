import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Functions of the GUI
public class PLActions {
   
   // Search FacResearchDB for papers given user input
   public ArrayList<String> search(String searchInput) {
      
      // Sanitize and store user input 
      String[] input = searchInput.split(" ");      
      List<String> searchWords = new ArrayList<String>(Arrays.asList(input));
      // -- Sanitize input
      
      
      
      ArrayList<String> papers = new ArrayList<String>();
      
      return papers;
   }   
   
   
}