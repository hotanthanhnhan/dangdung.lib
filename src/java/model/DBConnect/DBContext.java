package model.DBConnect;


import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;


public interface DBContext {
    public static String serverName = "THANHNHAN";
    public static String dbName = "Library";
    public static String portNumber = "1433";
    public static String userID = "sa";
    public static String password = "sa"; 
    
    public static Connection getConnection(){
        try {
            String url = "jdbc:sqlserver://"+serverName+":"+portNumber +";databaseName="+dbName;        
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(url, userID, password);
        } catch (Exception e) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }   
    
//    Test connection
    public static void main(String[] args) {
        try{
            if(DBContext.getConnection()!=null) System.out.println("Connect successfully!");
            else System.out.println("Connect failed!");
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at model.DBContext.DBContext().getConnertion()");
        }        
    }
}


