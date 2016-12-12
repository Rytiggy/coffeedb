import java.sql.*;
import java.util.*;
import java.util.HashMap;

/*
* CoffeeDB
* Gustav, Aaron, Ryan, and Jeremy
* MySQLDatabase
*/
public class MySQLDatabase {
   //global var
   public static String user, password, driver,uri;
   public static Connection conn = null;

   /**
    * MySQLDatabase set up the database connections & hostname, Username, password.
    * 
    */ 
   public MySQLDatabase(){
      uri= "jdbc:mysql://localhost/facresearchdb?autoReconnect=true&useSSL=false";
      String driver="com.mysql.jdbc.Driver";
      user = "root";
      password = "student";
   }
  
   /**
    * Attempts to connect to the Mysql database with input provided 
    * @return result true/false depending on if the connection was successful
    * @throws DLException 
    */         
   public static boolean connect() throws DLException{
      boolean result;        
      try{
         conn = DriverManager.getConnection(uri,user,password);
         result = true;
      }
      catch (SQLException e) {
         result = false;
         Map<String, String> map = new HashMap<String, String>();
         map.put("Error Occurred" , "Connect Method" );
         map.put("SQLErrorCode" , Integer.toString(e.getErrorCode()));
         map.put("SQLState" , e.getSQLState());
         throw new DLException(e, map);
      }
      return result;
   }

   /**
    * Attempts to close the Mysql database with input provided 
    * @return result true / false if db closes successfully
    * @throws DLException 
    */ 
   public static boolean close() throws DLException {
      boolean result;                
      try{
         conn.close();
         result = true;
      }
      catch (SQLException e) {
         result = false;
         Map<String, String> map = new HashMap<String, String>();
         map.put("Error Occurred " , "close Method" );
         map.put("SQLErrorCode" , Integer.toString(e.getErrorCode()));
         map.put("SQLState" , e.getSQLState());
         throw new DLException(e, map);
      }
      catch ( NullPointerException e) {
         result = false;
         throw new DLException(e);
      }     
     
      return result;
   
   }
   //Add another method named getData that accepts an SQLstring and returns a 2-d ArrayList/List 
   //just like your existing getDatamethod, but also accepts a boolean value to indicate whether
   // column names should be included in the returned data the	returned structure.  
   
   /**
    * Returns the data 
    * @param mySql SQL query
    * @param addColumnNames boolean
    * @return results ArrayList
    * @throws DLException 
    */ 
   public ArrayList<ArrayList<String>> getData(String mySql , boolean addColumnNames) throws DLException {
      ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>();
            
      try { 
         Statement statment = conn.createStatement();
         ResultSet resultSet = statment.executeQuery(mySql);
         
         //Change the getData method that �accepts an SQL string and the number of fields�, to use metadata from the SQL query to determine the number of fields requested
         ResultSetMetaData resultSetMetadata = resultSet.getMetaData();
         int numOfCols = resultSetMetadata.getColumnCount();   
          
         if (addColumnNames) {
            ArrayList<String> ColumnNames = new ArrayList<String>();
            for (int i=1; i<=numOfCols; i++) {
               ColumnNames.add(resultSetMetadata.getColumnName(i));
            }
            results.add(ColumnNames);
         }
          
         while (resultSet.next()) {
            ArrayList<String> rowData = new ArrayList<String>(); 
            for (int i=1; i<=numOfCols; i++) {
               
               rowData.add(resultSet.getString(i));
            }
            results.add(rowData);  
         }
         
      } 
      catch (SQLException e) {
         Map<String, String> map = new HashMap<String, String>();
         map.put("Error Occurred " , "getData Method" );
         map.put("SQLErrorCode" , Integer.toString(e.getErrorCode()));
         map.put("SQLState" , e.getSQLState());
      
         throw new DLException(e, map);
      }
      catch ( NullPointerException e) {
         throw new DLException(e);
      }          
      catch(IndexOutOfBoundsException e){
         throw new DLException(e);
      
      }  
      return results;
   
   
   }
   /**
    * get Data method that returns the data
    * @param mySql SQL query
    * @return getData 
    * @throws DLException 
    */ 
   public ArrayList<ArrayList<String>> getData(String mySql) throws DLException {
      try {
         return getData(mySql , false);
      } 
      catch (DLException e) {
         throw new DLException(e);
      }
   
   }
   
   /**
    * sets the Data 
    * @param table
    * @return results true if set data was successful / false if error occured 
    * @throws DLException 
    */ 
   public boolean setData(String sql) throws DLException {
      boolean results = false;
      try {
         Statement stmnt = conn.createStatement();
         int rc = stmnt.executeUpdate(sql);
         results = true;
      } 
      catch (SQLException e) {
         results = false;
         Map<String, String> map = new HashMap<String, String>();
         map.put("Error Occured" , "setData Method" );
         map.put("SQLErrorCode: " , Integer.toString(e.getErrorCode()));
         map.put("SQLState" , e.getSQLState());
      
         throw new DLException(e, map);
      }
      catch ( NullPointerException e) {
         results = false;
         throw new DLException(e);
      }
      catch(IndexOutOfBoundsException e){
         throw new DLException(e);
      
      }  
   
   
      return results;
   }
    /**
    * method that describe the Table 
    * @param table
    * @throws DLException 
    */ 
   public void descTable(String table) throws DLException {
      descTable(table, "*");
   }
   
    /**
    * method that desccribes the Table 
    * @param table
    * @param headers
    * @throws DLException 
    */ 
   public void descTable(String table, String headers ) throws DLException {
      String SQLString = "SELECT "+headers+" FROM " + table;  
      
      try {
         Statement statment = conn.createStatement();
         ResultSet resultSet = statment.executeQuery(SQLString);
         
         ResultSetMetaData resultSetMetadata = resultSet.getMetaData();
         int numOfCols = resultSetMetadata.getColumnCount();   
         System.out.println("\nNum Columns\t" + Integer.toString(numOfCols));
      
      
         System.out.printf("%-25s %-25s%n","Column Name", "Column Type and Precision");
         for(int i = 1; i <= numOfCols; i++ ){ 
            String colName = resultSetMetadata.getColumnName(i);
            String colTypeName = resultSetMetadata.getColumnTypeName(i);
            int colPercision = resultSetMetadata.getPrecision(i);
            
            System.out.printf("%-25s %-25s%n",colName, colTypeName+" "+colPercision);
            
         }   
             
      } 
      catch (SQLException sqle) {
         throw new DLException(sqle);
      }                            
   }
   
    /**
    * display Data 
    * @param table
    * @throws DLException 
    */ 
   public void displayData(String table ) throws DLException {
      displayData(table, "*");
   }
   /**
    * display Data 
    * @param table
    * @param headers
    * @throws DLException
    */ 
   public void displayData(String table,String headers ) throws DLException {
      ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>();
   
      String mySql = "SELECT "+headers+" FROM " + table;  
    
      try { 
         Statement statment = conn.createStatement();
         ResultSet resultSet = statment.executeQuery(mySql); 
         
         //Change the getData method that �accepts an SQL string and the number of fields�, to use metadata from the SQL query to determine the number of fields requested
         ResultSetMetaData resultSetMetadata = resultSet.getMetaData();
         int numOfCols = resultSetMetadata.getColumnCount();   
      
         ArrayList<String> formatStrings = new ArrayList<String>();
         ArrayList<String> header = new ArrayList<String>();
         for (int i=1; i<=numOfCols; i++) {
            header.add(resultSetMetadata.getColumnName(i));
            formatStrings.add("%-"+resultSetMetadata.getPrecision(i)+"s ");
         }
         results.add(header);
          
         while (resultSet.next()) {
            ArrayList<String> rowData = new ArrayList<String>(); 
            for (int i=1; i<=numOfCols; i++) {
               
               rowData.add(resultSet.getString(i));
            }
            results.add(rowData);  
         }
         
         System.out.println();
         for (int i=0; i<results.size(); i++) {
            for(int x=0; x<results.get(i).size(); x++) {
               System.out.printf(formatStrings.get(x), results.get(i).get(x));
            }
            System.out.println();
         }
      
      } 
      catch (SQLException e) {
         Map<String, String> map = new HashMap<String, String>();
         map.put("Error Occurred " , "getData Method" );
         map.put("SQLErrorCode" , Integer.toString(e.getErrorCode()));
         map.put("SQLState" , e.getSQLState());
      
         throw new DLException(e, map);
      }
      catch ( NullPointerException e) {
         throw new DLException(e);
      }          
      catch(IndexOutOfBoundsException e){
         throw new DLException(e);
      }     
   }
   /**
    * prepare statement 
    * @param mySql SQL query
    * @param values ArrayList of values
    * @return result PreparedStatement for database
    * @throws DLException
    */   
   public PreparedStatement prepare(String mySql, ArrayList<String> values) throws DLException {
      PreparedStatement result = null;
      try {
         result = conn.prepareStatement(mySql);
         for (int i=0; i<values.size(); i++) {
            result.setString(i+1, values.get(i));
         }
      } 
      catch (SQLException e) {
         HashMap<String, String> map = new HashMap<String, String>();
         map.put("description", "Error while preparing statement");
         map.put("SQLErrorCode" , Integer.toString(e.getErrorCode()));
         //throw new DLException(e, map);
      }
      return result;
   
   }
   /**
    * Method getData returns the data
    * @param mySql SQL query
    * @param values ArrayList of values
    * @return results ArrayList
    * @throws DLException
    */   
   public ArrayList<ArrayList<String>> getData(String mySql, ArrayList<String> values) throws DLException {
      PreparedStatement prepStatment = prepare(mySql, values);
   
      ArrayList<ArrayList<String>> results = null;
      try {
         results = new ArrayList<ArrayList<String>>();
         Statement statment = conn.createStatement();
         ResultSet resultSet = prepStatment.executeQuery();
         ResultSetMetaData resultSetMetadata = resultSet.getMetaData();
         int numOfCols = resultSetMetadata.getColumnCount();
      
         ArrayList<String> ColumnNames = new ArrayList<String>();
         for (int i=1; i<=numOfCols; i++) {
            ColumnNames.add(resultSetMetadata.getColumnName(i));
         }
         results.add(ColumnNames);
      
         while (resultSet.next()) {
            ArrayList<String> rowData = new ArrayList<String>();
            for (int i=1; i<=numOfCols; i++) {
               rowData.add(resultSet.getString(i));
            }
         
            results.add(rowData);
         }
      }
      catch (SQLException e) {
         Map<String, String> map = new HashMap<String, String>();
         map.put("Error Occurred " , "getData Method" );
         map.put("SQLErrorCode" , Integer.toString(e.getErrorCode()));
         map.put("SQLState" , e.getSQLState());
      
         //throw new DLException(e, map);
      }
      catch ( NullPointerException e) {
         //throw new DLException(e);
      }
      catch(IndexOutOfBoundsException e){
         //throw new DLException(e);    
      }
      return results;
   
   }
   /**
    * sets the Data 
    * @param sql SQL query
    * @param values ArrayList of values
    * @return results true/false depending on success or failure of execution
    * @throws DLException
    */   
   public boolean setData(String sql, ArrayList<String> values) throws DLException {
      boolean results = false;
      if(executeStmt(sql, values) == 1){
         results = true;
      }
      else{
         results = false;
      }
      return results;                 
   }   
   /**
    * execute Stmt 
    * @param mySql SQL query
    * @param values ArrayList of values
    * @return results ResultSet
    * @throws DLException
    */    
   public int executeStmt(String mySql, ArrayList<String> values) throws DLException {
      PreparedStatement prepStatment = prepare(mySql, values);
      int results = -1;      
      try { 
         results = prepStatment.executeUpdate();
      } 
      catch (SQLException e) {
         Map<String, String> map = new HashMap<String, String>();
         map.put("Error Occurred " , "ExecuteStmt Method" );
         map.put("SQLErrorCode" , Integer.toString(e.getErrorCode()));
         map.put("SQLState" , e.getSQLState());
      
         //throw new DLException(e, map);
      }
      catch ( NullPointerException e) {
         //throw new DLException(e);
      }          
      catch(IndexOutOfBoundsException e){
         //throw new DLException(e);      
      }  
      return results;          
   }
   /**
    * second method that sets the data 
    * @param sql SQL query
    * @param values ArrayList of values
    * @return -1
    * @throws DLException
    */ 
   public int setData2(String sql, ArrayList<String> values) throws DLException {
      int results = this.executeStmt2(sql , values);
      if(results > 0){
         return results;
      }
      return -1;
   }
   /**
    * execute Stmt2 
    * @param mySql SQL query
    * @param values ArrayList of values
    * @return id ResultSet
    * @throws DLException
    */ 
   public int executeStmt2(String mySql, ArrayList<String> values) throws DLException {
      PreparedStatement prepStatment = prepare2(mySql, values);
      int id = -1;
   
      try {
         prepStatment.executeUpdate();
         ResultSet rs = prepStatment.getGeneratedKeys();
         if (rs.next()){
            id = rs.getInt(1);
         
         }
         conn.commit();
      }
      catch (SQLException e) {
         Map<String, String> map = new HashMap<String, String>();
         map.put("Error Occurred " , "ExecuteStmt Method" );
         map.put("SQLErrorCode" , Integer.toString(e.getErrorCode()));
         map.put("SQLState" , e.getSQLState());
      
         //throw new DLException(e, map);
      }
      catch ( NullPointerException e) {
         //throw new DLException(e);
      }
      catch(IndexOutOfBoundsException e){
         //throw new DLException(e);
      }
      return id;
   }
   /**
    * prepare2 PreparedStatement 
    * @param mySql SQL query
    * @param values  ArrayList of values
    * @return result PreparedStatement for database

    * @throws DLException
    */    
   public PreparedStatement prepare2(String mySql, ArrayList<String> values) throws DLException {
      PreparedStatement result = null;
      try {
         result = conn.prepareStatement(mySql, Statement.RETURN_GENERATED_KEYS);
         for (int i=0; i<values.size(); i++) {
            result.setString(i+1, values.get(i));
         }
      }
      catch (SQLException e) {
         HashMap<String, String> map = new HashMap<String, String>();
         map.put("description", "Error while preparing statement");
         map.put("SQLErrorCode" , Integer.toString(e.getErrorCode()));
         //throw new DLException(e, map);
      }
      return result;
   }
   

   /**
    * Start transaction method 
    * @throws DLException
    */    
   public void startTrans() throws DLException {
      try {
         conn.setAutoCommit(false);
      } 
      catch (SQLException sqle) {
         HashMap<String, String> map = new HashMap<String, String>();
         map.put("description", "Error in StartTrasns()");
         map.put("sql state", sqle.getSQLState());
         map.put("error code", Integer.toString(sqle.getErrorCode()));
         throw new DLException(sqle, map);
      }
   }
   
   /**
    * end Transaction method 
    * @throws DLException
    */    
   public void endTrans() throws DLException {
      try {
         conn.commit();
         conn.setAutoCommit(true);
      } 
      catch (SQLException sqle) {
         HashMap<String, String> map = new HashMap<String, String>();
         map.put("description", "Error in EndTrans");
         map.put("sql state", sqle.getSQLState());
         map.put("error code", Integer.toString(sqle.getErrorCode()));
         throw new DLException(sqle, map);
      }
   }
   /**
    * roll back Transaction method if intermediate values indicate a need to abort or exception/error thrown.
    * @throws DLException
    */ 
   public void rollbackTrans() throws DLException {
      try {
         conn.rollback();
      } 
      catch (SQLException sqle) {
         HashMap<String, String> map = new HashMap<String, String>();
         map.put("description", "Error in rollback() ");
         map.put("sql state", sqle.getSQLState());
         map.put("error code", Integer.toString(sqle.getErrorCode()));
         throw new DLException(sqle, map);
      }
   }                          
}