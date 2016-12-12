/*
* CoffeeDB
* Gustav, Aaron, Ryan, and Jeremy
* BLUser
*/

public class BLUser {
   DLUser user;
   // Default constructor to create a blank user
   BLUser() {
      user = new DLUser();
   }

   /**
    * login Verify user credentials
    * @param myEmail
    * @param myPassword
    * @return user.login / true if log in was successful.
    * @throws DLException
    */ 
   public boolean login(String myEmail, String myPassword) throws DLException {
      return user.login(myEmail, myPassword);
   }

   /**
    * check User
    * @param _email
    * @return exists if user exists
    * @throws DLException
    */ 
   public boolean checkUser(String _email) throws DLException {
      boolean exists = user.checkUser(_email);

      return exists;
   }
   /**
    * create Guest User
    * @param _fName
    * @param _lName
    * @param _email
    * @return succ if guest user was successfully created
    * @throws DLException
    */ 
   public boolean createGuestUser(String _fName, String _lName, String _email) throws DLException {
      System.out.println("WHAT");
     boolean succ = user.createGuestUser(_fName, _lName, _email);


      return succ;
   }
   
   // Getters
   /**
    * get User Id
    * @return user.getUserId user ID
    */ 
   public int getUserId() {
      return user.getUserId();
   }
   /**
    * get FName
    * @return user.getFName()
    */ 
   public String getFName() {
      return user.getFName();
   }
   /**
    * get LName
    * @return user.getLName()
    */ 
   public String getLName() {
      return user.getLName();
   }
   /**
    * get password
    * @return user.getPassword()
    */ 
   public String getPassword() { //DO WE WANT THIS TO BE ACCESSIBLE????
      return user.getPassword();
   }
   /**
    * get email
    * @return user.getEmail()
    */ 
   public String getEmail() {
      return user.getEmail();
   }
   /**
    * get role
    * @return user.getRole()
    */ 
   public String getRole() {
      return user.getRole();
   }
   /**
    * set User Id
    * @param userId
    */ 
   public void setUserId(int userId) {
      user.setUserId(userId);
   }
   /**
    * set fName
    * @param fName
    */ 
   public void setfName(String fName) {
      user.setfName(fName);
   }
   /**
    * set lName
    * @param lName
    */ 
   public void setlName(String lName) {
      user.setlName(lName);
   }
   /**
    * set email
    * @param email
    */ 
   public void setEmail(String email) {
      user.setEmail(email);
   }
   /**
    * set Role
    * @param role
    */ 
   public void setRole(String role) {
      user.setRole(role);
   }
}