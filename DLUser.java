import java.io.*;
import java.sql.*;
import java.util.*;
//imported for security
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
* CoffeeDB
* Gustav, Aaron, Ryan, and Jeremy
* DLUser
*/

public class DLUser {
   // Table attributes
   private int userId;
   private String fName, lName, password, email, role;
   private MySQLDatabase database;
   
   // Constructor
   DLUser() {
      database = new MySQLDatabase();
      String fName = null;
      String lName = null;
      password = null;
      email = null;
      role = null;
   }
   
   // Verify user credentials
   public boolean login(String myEmail, String myPassword) throws DLException {
      boolean isValidLogin = false;      
      String encryptedPassword = encryptPassword(myPassword);
      
      database.connect();
      // Create SQL statement using provided user credentials
      String getUserDataSQL = "SELECT * FROM user WHERE email = ? AND password = ?";
      ArrayList<String> myAttributes = new ArrayList<String>();
      myAttributes.add(myEmail);
      myAttributes.add(myPassword); // CHANGE THIS ONCE ENCRYPTION IS IMPLEMENTED
         
      // Attempt to retrieve user attributes
      ArrayList<ArrayList<String>> userData = database.getData(getUserDataSQL, myAttributes);    
      userId = Integer.valueOf(userData.get(1).get(0));
      fName = userData.get(1).get(1); 
      lName = userData.get(1).get(2);
      email = myEmail;
      password = myPassword; // CHANGE THIS ONCE ENCRYPTION IS IMPLEMENTED
      getUserRole();      
      
      database.close();
         
      // NO DLException thrown, user attributes verified         
      isValidLogin = true;
      return isValidLogin;
   }
   
   // Set this user's role   
   public void getUserRole() throws DLException {
      // Has the user been validated?
      if (email != null && password != null) {
         // User is validated, retrieve their roleId
         String getUserRoleIdSQL = "SELECT roleId FROM user_role WHERE userId = ?";
         ArrayList<String> myAttributes = new ArrayList<String>();
         myAttributes.add(String.valueOf(userId));
         ArrayList<ArrayList<String>> userData = database.getData(getUserRoleIdSQL, myAttributes);
         String roleId = userData.get(1).get(0);
         
         // Retrieve and set this user's role
         String getRoleTitleSQL = "SELECT title FROM role WHERE roleId = ?";
         myAttributes.clear();
         myAttributes.add(roleId);
         userData = database.getData(getRoleTitleSQL, myAttributes);
         
         role = userData.get(1).get(0);
      }
   }
   
   // Encrypt a password using ____
   public String encryptPassword(String myPassword) {
      String encryptedPassword = null;
      StringBuffer sb = new StringBuffer();
   
      try{
            // Encryption code
         MessageDigest mDigest = MessageDigest.getInstance("SHA1");
         byte[] result = mDigest.digest(myPassword.getBytes());
         for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
         }
      
      
      
      }
      catch(NoSuchAlgorithmException nsaE){
         System.err.println("Exception caught: " + nsaE);
         nsaE.printStackTrace();
      
      }
      //return encryptedPassword;
      return sb.toString();
   }
   
   // Getters
   public int getUserId() {
      return userId;
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
   public String getRole() {
      return role;
   }
}