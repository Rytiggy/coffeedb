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
}