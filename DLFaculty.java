import java.io.*;
import java.sql.*;
import java.util.*;

public class DLFaculty {
   // Table attributes.
   private int facultyID;
   private String fName, lName, password, email;
   private MySQLDatabase database;
   
   // Constructor
   DLFaculty(String myEmail, String myPassword) {
      database = new MySQLDatabase();
      
      // Set faculty attributes
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
         facultyAttributes.add(email);
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
   
   // Get all papers authored by this faculty
   public ArrayList<String> getPapers() throws DLException {
      database.connect();
      
      ArrayList<String> facultyPapers = new ArrayList<String>();
      String getPapersSQL = "SELECT paperid FROM authorship WHERE facultyid = ?";
      ArrayList<String> facultyAttributes = new ArrayList<String>();
      
      facultyAttributes.add(String.valueOf(facultyID));
      ArrayList<ArrayList<String>> results = database.getData(getPapersSQL, facultyAttributes);
       
      for(int i = 0; i < results.size(); i++) {
         facultyPapers.add(results.get(i).get(1));
      }                  // we need to figure out index out of bounds issue
      
      return facultyPapers;
   }
   
   // Verify user credentials
   public boolean login(String myEmail, String myPassword) throws DLException {
      email = myEmail;
      password = myPassword;
      
      boolean isValidLogin = false;
      database.connect();
      String getUserDataSQL = "";
      
      try { 
         // Create SQL statement using provided user credentials
         getUserDataSQL = "SELECT * FROM faculty WHERE Email = ? AND Password = ?";
         ArrayList<String> myAttributes = new ArrayList<String>();
         myAttributes.add(email);
         myAttributes.add(password);
         
         // Attempt to retrieve user attributes
         ArrayList<ArrayList<String>> userData = database.getData(getUserDataSQL, myAttributes);
         email = userData.get(1).get(3);
         password = userData.get(1).get(4);
         
         // User attributes verified         
         isValidLogin = true;
      }            
      catch (IndexOutOfBoundsException ioobe)
      {
         // Failed to validate user credentials
         //throw new DLException(ioobe, "Cannot perform operation at this time.", getUserDataSQL, "login: 83");         
      }
      database.close();
      return isValidLogin;
   }
}