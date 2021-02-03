/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

import Model.User;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author nurfa
 */
public class JDBCutility {
   
    Connection con = null;
    String driver;
    String url;
    String userName;
    String password;
    
    PreparedStatement RegisterPS = null;
    PreparedStatement LoginPS = null;
   
    public JDBCutility(String driver,
                       String url,
                       String userName,
                       String password) {
        this.driver = driver;
        this.url = url;
        this.userName = userName;
        this.password = password;
    }

    public JDBCutility() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public  void jdbcConnect(){
        
        try {
         
            Class.forName (driver);
            con = DriverManager.getConnection(url, userName, password);
            DatabaseMetaData dma = con.getMetaData ();
            System.out.println("\nConnected to " + dma.getURL());
            System.out.println("Driver       " + dma.getDriverName());
            System.out.println("Version      " + dma.getDriverVersion());
            System.out.println("");
            
	}
	catch (SQLException ex){
            
            while (ex != null){
		System.out.println ("SQLState: " + ex.getSQLState ());
                System.out.println ("Message:  " + ex.getMessage ());
		System.out.println ("Vendor:   " + ex.getErrorCode ());
                ex = ex.getNextException ();
		System.out.println ("");
            }

            System.out.println("Connection to the database error");
	}
	catch (java.lang.Exception ex){
            ex.printStackTrace ();
	}
    }
    
    public Connection jdbcGetConnection(){
        return con;
    }
    
    public void jdbcConClose(){
        
   	try {
         con.close();
   	}
   	catch (Exception ex){
            ex.printStackTrace ();
        }  
    }
     public void loadDriver(String driver)
	{
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	}
    
    public Connection getConnection()
	{
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, userName, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
    
     public void prepareSQLStatementRegister(){
        
        try {
           
            //create SQL statement
              String insert_client = "INSERT INTO user (name, password , email , phoneNo, role  ) VALUES (?,?,?,?,? ) "; 
              
            
            //prepare statement
           
            RegisterPS = con.prepareStatement(insert_client);    
            
        }
        catch (SQLException ex){
            
            while (ex != null){
		System.out.println ("SQLState: " + ex.getSQLState ());
                System.out.println ("Message:  " + ex.getMessage ());
		System.out.println ("Vendor:   " + ex.getErrorCode ());
                ex = ex.getNextException ();
		System.out.println ("");
            }

            System.out.println("Connection to the database error");
	}
	catch (java.lang.Exception ex){
            ex.printStackTrace ();
	}
    }
    
    public PreparedStatement getRegisterClient(){
        return RegisterPS;
    }
    
    
    //LOGIN
    
    public User checkLogin(String email, String password)throws SQLException,
            ClassNotFoundException {
        
         
        loadDriver(driver);
        Connection con = getConnection();
        
        String sql = "select * from user where email = ? and password = ?";
        PreparedStatement ps;
        
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            
            ResultSet result=ps.executeQuery();
            
            User user = null;
            
            if(result.next()){
                
                user.setName(result.getString("name"));
                user.setEmail(email);
                user.setRole(result.getString("role"));
            }
             con.close();      
            return user;  
            }
    
}


