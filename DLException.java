import java.util.*;
import java.io.*;
import java.text.*;


public class DLException extends Exception{
   private Exception e;
   Writer writer = null;

   

//constructor that accepts a single parameter of type Exception
   public DLException(Exception e) {
      super("Unable to compleate task. Please check logs for more info.");
      log("\n" + e.getMessage() + "\n");
   }
   
//constructor that accepts a parameter of type Exception and a subclass of Map
   public DLException(Exception e, Map<String, String> errorMsg) {
      super("Unable to compleate task. Please check logs for more info.");
      String messageString = "\n" + e.getMessage() + "\n";
      
      for (Map.Entry<String,String> error : errorMsg.entrySet()) {
         messageString += error.getKey() + " : " + error.getValue() + "\n";
      } 
      log(messageString);
   }
  
 // log to log file  
   public void log(String errorMsg) {
      Date date = new Date();
      SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
      String formattedDate = "\n"+ sdf.format(date);      
      try {
         writer = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream("logfile.txt", true), "utf-8"));
         writer.write(formattedDate + ": " + errorMsg);
      } 
      catch (IOException | NullPointerException ex) {
      // report
      } 
      finally {
         try {writer.close();} 
         catch (Exception ex) {/*ignore*/}
      }
   }
   
   

}//end of class
