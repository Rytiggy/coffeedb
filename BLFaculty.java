import java.util.*;

class BLFaculty { 
   DLFaculty faculty;
   boolean isAuthenticated = false;
   // Constructor.
   BLFaculty(String myEmail, String myPassword) {
      try {
         // Login / Authenticate
         faculty = new DLFaculty(myEmail, myPassword);
         
         if(faculty.login(myEmail, myPassword)) {
            isAuthenticated = true;
            faculty.fetch();
         }
         else {
            // Will connect to front end and alert user
            System.out.println("Invalid username or password.");
         }         
      }
      catch (Exception e) {
         // Need to update for DLException
      }      
   }
   
   // Other methods?
   
   // Get Papers
   public ArrayList<String> getPapers() throws DLException {
      return faculty.getPapers();
   }  
}