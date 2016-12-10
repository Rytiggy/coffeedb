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
   
   // Verify user credentials
   public boolean login(String myEmail, String myPassword) throws DLException {
      return user.login(myEmail, myPassword);
   }

   public boolean checkUser(String _email) throws DLException {
      boolean exists = user.checkUser(_email);

      return exists;
   }

   public boolean createGuestUser(String _fName, String _lName, String _email) throws DLException {
      System.out.println("WHAT");
     boolean succ = user.createGuestUser(_fName, _lName, _email);


      return succ;
   }
   
   // Getters
   public int getUserId() {
      return user.getUserId();
   }
   public String getFName() {
      return user.getFName();
   }
   public String getLName() {
      return user.getLName();
   }
   public String getPassword() { //DO WE WANT THIS TO BE ACCESSIBLE????
      return user.getPassword();
   }
   public String getEmail() {
      return user.getEmail();
   }
   public String getRole() {
      return user.getRole();
   }

   public void setUserId(int userId) {
      user.setUserId(userId);
   }

   public void setfName(String fName) {
      user.setfName(fName);
   }

   public void setlName(String lName) {
      user.setlName(lName);
   }

   public void setEmail(String email) {
      user.setEmail(email);
   }

   public void setRole(String role) {
      user.setRole(role);
   }
}