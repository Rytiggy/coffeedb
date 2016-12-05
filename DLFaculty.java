import java.io.*;
import java.sql.*;
import java.util.*;

public class DLFaculty {
   // Table attributes
   private int facultyID;
   private String fName, lName, password, email;
   private MySQLDatabase database;
   
   // Constructor
   DLFaculty(String myEmail, String myPassword) {
      database = new MySQLDatabase();
      // Need to authenticate user here. If = true, is good
      email = myEmail;
      password = myPassword;
      fName = null;
      lName = null;
      facultyID = 0;
   }
   
   // Getters
   public int getFacultyID() {
      return facultyID;
   }
   public String getFName() {
      return fName;
   }
   public String getLName() {
      return lName;
   }
   public String getPassword() {
      return password;
   }
   public String getEmail() {
      return email;
   }
   
   // Populate faculty attributes
   public void fetch() throws DLException {
      database.connect();
      
      String fetchSQL = "SELECT * FROM faculty WHERE email = ?"; // this assumes user has been authenticated in constructor
      ArrayList<String> facultyAttributes = new ArrayList<String>();
      
      try {
         facultyAttributes.add(String.valueOf(facultyID));
         ArrayList<ArrayList<String>> results = database.getData(fetchSQL, facultyAttributes);
         facultyID = Integer.valueOf(results.get(1).get(0));
         fName = results.get(1).get(1);
         lName = results.get(1).get(2);         
      }
      catch (Exception e) {
         // Needs to be a DLException
      }
      database.close();
   }
}