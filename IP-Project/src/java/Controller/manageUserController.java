/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.JDBCutility;

/**
 *
 * @author Muaz Amir
 */
@WebServlet(name = "manageUserController", urlPatterns = {"/manageUserController"})
public class manageUserController extends HttpServlet {
    
    
    private JDBCutility jdbcUtility;   
    private Connection con;
    
    @Override
    public void init() throws ServletException{
        
         String driver = "com.mysql.jdbc.Driver"; 
        String dbName = "faiqhoteldb";
        String url = "jdbc:mysql://localhost:3306/" + dbName + "?";
        String userName = "root";
        String password = "";

        jdbcUtility = new JDBCutility(driver,
                                      url,
                                      userName,
                                      password);

        jdbcUtility.jdbcConnect();
        
        //get JDC connection
        con = jdbcUtility.jdbcGetConnection();
        
        //prepare the statement once only
        //for the entire servlet lifecycle
        jdbcUtility.prepareSQLStatementRegister();
             
    }
    
    @Override
    
    public void destroy(){
        jdbcUtility.jdbcConClose();
    }
    
    
     protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
         
        
             
        
    }
     
     
     protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String command = request.getParameter("command");
        
        if(command==null){
            command="";
        }
        
        try{
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
            response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
            response.setDateHeader("Expires", 0);
            
            switch(command){
                
                case "Register":
                    Register(request , response);
                     break;
                
                case "Login":
                    Login(request , response);
                    break;
                    
                default :
                   request.getRequestDispatcher("index.jsp").forward(request, response);
                   break;
            }
        }catch (Exception exc) {
            throw new ServletException(exc);
        }
        
    }
     
     public void Register(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        
    
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phoneNo = request.getParameter("phoneNo");
        
      
        try{       
            String role = "CLIENT";
            PreparedStatement ps = jdbcUtility.getRegisterClient();
                     
            ps.setString(1, name);
            ps.setString(2, password);
            ps.setString(3, email);
            ps.setString(4, phoneNo);
            ps.setString(5, role);

            
            int insertStatus = 0;
            insertStatus = ps.executeUpdate();
            
            
             if (insertStatus == 1) {
                 try(PrintWriter out = response.getWriter()){
                      out.println("<script>");
                    out.println("  alert('Register Success');");
                    out.println("    window.location = '" + request.getContextPath() + "/NavBarController?command=Login-Page'");
                    out.println("</script>");
                 }
               
            }

        } catch (SQLException ex) {
            while (ex != null) {
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("Message:  " + ex.getMessage());
                System.out.println("Vendor:   " + ex.getErrorCode());
                ex = ex.getNextException();
                System.out.println("");
            }

            PrintWriter out = response.getWriter();

            out.println("<script>");
            out.println("    alert('Client insert failed sqlexception ');");
            out.println("    window.location = '" + request.getContextPath() + "/NavBarController?command=Register-Page'");
            out.println("</script>");
        }
        catch (java.lang.Exception ex) {
            ex.printStackTrace();

            PrintWriter out = response.getWriter();

            out.println("<script>");
            out.println("    alert('Client insert failed exception');");
            out.println("    window.location = '" + request.getContextPath() + "/NavBarController?command=Register-Page'");
            out.println("</script>");
        }
                
     }
    
    public void Login(HttpServletRequest request, HttpServletResponse response){
        
    }
    
    
    
    
    

    }


    
   
    
    
    

  

