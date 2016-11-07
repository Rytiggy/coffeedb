public class Main {
   public static void main(String [] Args) {
      MySQLDatabase database = new MySQLDatabase("jdbc:mysql://localhost/facresearchdb?autoReconnect=true&useSSL=false","root", "student");
      
      System.out.println(database.connect());
   }
}