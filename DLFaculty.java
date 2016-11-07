import java.io.*;
import java.sql.*;
import java.util.*;

public class DLFaculty {
   // Table attributes
   private String facultyID, fName, lName, password, email;
   private MySQLDatabase database;
   
   // Constructor
   DLFaculty(String myFacultyID, String myPassword) {
      database = new MySQLDatabase("jdbc:mysql://localhost/travel?autoReconnect=true&useSSL=false","root", "student");
      facultyID = myFacultyID;
      password = myPassword;
      fName = null;
      lName = null;
      email = null;  
      //user.authenticate if = true is good
   }
   
   // Getters
   public String getFacultyID() {
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
   public void fetch() {
      database.connect();
      
      String fetchSQL = "SELECT * FROM faculty WHERE id = ?";
      ArrayList<String> facultyAttributes = new ArrayList<String>();
      
      try {
         facultyAttributes.add(facultyID);
         ArrayList<ArrayList<String>> results = database.getData(fetchSQL, facultyAttributes);
         fName = results.get(1).get(1);
         lName = results.get(1).get(2);
         email = results.get(1).get(4);
      }
      catch (Exception e) {
      
      }
      database.close();
   }
}