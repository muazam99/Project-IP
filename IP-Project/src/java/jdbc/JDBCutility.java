/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author nurfa
 */
public class JDBCutility {
    private static Connection con = null;
        
    public static Connection getCon() {
        try {
            if(con == null || con.isClosed()) {
                con = createConnection();
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBCutility.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return con;
    }
    
    public static Connection createConnection() {
        Connection con = null;
        try
           {
                String driver = "com.mysql.jdbc.Driver";
               
                String dbName = "faiqhoteldb";
                String host = "localhost";
                String port = "3306";
                String url = "jdbc:mysql://" + host + ":" + port + "/" + dbName + "?useTimeZone=true&serverTimezone=UTC&autoReconnect=true&useSSL=false";
                String userName = "root";
                String password = "";


                Class.forName (driver);
                con = DriverManager.getConnection(url, userName, password);
                DatabaseMetaData dma = con.getMetaData ();
                System.out.println("\nConnected to " + dma.getURL());
                System.out.println("Driver       " + dma.getDriverName());
                System.out.println("Version      " + dma.getDriverVersion());
                System.out.println("");
           }
           catch (SQLException ex)
           {
               while (ex != null)
               {
                   System.out.println ("SQLState: " +
                                       ex.getSQLState ());
                   System.out.println ("Message:  " +
                                       ex.getMessage ());
                   System.out.println ("Vendor:   " +
                                       ex.getErrorCode ());
                   ex = ex.getNextException ();
                   System.out.println ("");
               }

               System.out.println("Connection to the database error");
           }
           catch (ClassNotFoundException ex) {
           }
        return con; 
 }
    public static void jdbcConClose() {
	JDBCutility.con = null;
    }
}
