import java.io.*;
import java.sql.*;
import java.util.*;
//imported for security
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
* CoffeeDB
* @author Gustav
* @author Aaron
* @author Ryan
* @author Jeremy
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
   /**
    * 
    * @param myEmail
    * @param myPassword
    * @return isValidLogin
    * @throws DLException
    *
    */
   public boolean login(String myEmail, String myPassword) throws DLException {
      boolean isValidLogin = false;      
      String encryptedPassword = encryptPassword(myPassword);
      
      database.connect();
      // Create SQL statement using provided user credentials
      String getUserDataSQL = "SELECT * FROM user WHERE email = ? AND password = ?";
      ArrayList<String> myAttributes = new ArrayList<String>();
      myAttributes.add(myEmail);
      myAttributes.add(encryptedPassword); 
      
      try {   
         // Attempt to retrieve user attributes
         ArrayList<ArrayList<String>> userData = database.getData(getUserDataSQL, myAttributes);    
         userId = Integer.valueOf(userData.get(1).get(0));
         fName = userData.get(1).get(1); 
         lName = userData.get(1).get(2);
         email = myEmail;
         password = encryptedPassword; 
         getUserRole();      
      
         database.close();
         // No exception thrown, user attributes verified
         isValidLogin = true;
      }
      catch (IndexOutOfBoundsException ioobe) {
         // Don't need to do anything. This just means it was an invalid login
      }   
   
      return isValidLogin;
   }
   
   // Set this user's role
   /**
    * 
    * @param 
    * @param
    * @return
    * @throws
    *
    */   
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

   // Encrypt a password using SHA1
   /**
    * 
    * @param 
    * @return
    * @throws
    *
    */   
   public String encryptPassword(String myPassword) {
      String encryptedPassword = null;
   
      try {
         // Encryption code
         MessageDigest mDigest = MessageDigest.getInstance("SHA1");
         byte[] result = mDigest.digest(myPassword.getBytes());
         StringBuffer sb = new StringBuffer();
         for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
         }    
         encryptedPassword = sb.toString();
      }
      catch(NoSuchAlgorithmException nsaE){
         System.err.println("Exception caught: " + nsaE);
         nsaE.printStackTrace();    
      }
      return encryptedPassword;
   }
   
   // Getters
   /**
    * 
    * @param 
    * @param
    * @return userId
    *
    */   
   public int getUserId() {
      return userId;
   }
   /**
    * 
    * @param 
    * @param
    * @return fName
    *
    */   
   public String getFName() {
      return fName;
   }
   /**
    * 
    * @param 
    * @param
    * @return lName
    *
    */      
   public String getLName() {
      return lName;
   }
   /**
    * 
    * @return password
    *
    */   
   public String getPassword() {
      return password;
   }
   /**
    * 
    * @return email
    *
    */      
   public String getEmail() {
      return email;
   }
   /**
    * 
    * @return role
    *
    */      
   public String getRole() {
      return role;
   }
}