/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Client;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jdbc.JDBCutility;

/**
 *
 * @author hafizul
 */
@WebServlet(name = "profileControl", urlPatterns = {"/profileControl"})
public class profileControl extends HttpServlet {
    
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
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String option = request.getParameter("option");
        
        if(option == null){
            option = "";
        }
        
        switch(option){
            case "view" : 
                viewProfile(request , response);
                break;
                
            case "edit" :
                
                
            default :
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;
        }
        
    }
            
    public void viewProfile(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        
        String status = "";
        HttpSession session = request.getSession();
        
        try{
            String query = "SELECT * FROM user WHERE userID = 1 ";
                PreparedStatement preparedStatement = con.prepareStatement(query);

                ResultSet rs = preparedStatement.executeQuery();

                Client c = new Client();

                while(rs.next()){
                    String name = rs.getString("name");
                    String email = rs.getString("email");

                    c.setName(name);
                    c.setEmail(email);

                    session.setAttribute("user", c);
                    status = "SUCCESS";
                }
            }catch(SQLException e){
                status = "INVALID ACCOUNT";
            }  
            
            if(status.equals("SUCCESS"))   //On success, you can display a message to user on Home page
            {
                request.getRequestDispatcher("/profile.jsp").forward(request, response);
            }
            else
            {
                request.setAttribute("errMessage", status);
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        }   
          

}
