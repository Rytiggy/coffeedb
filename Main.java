public class Main {
   public static void main(String [] Args) {
      MySQLDatabase database = new MySQLDatabase();
      try {
         System.out.println(database.connect());
      } catch (DLException e) {
         e.printStackTrace();
      }
   }
}