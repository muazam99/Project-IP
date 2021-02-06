/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Admin;
import Model.Client;
import java.io.IOException;
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
@WebServlet(name = "ProfileController", urlPatterns = {"/ProfileController"})
public class ProfileController extends HttpServlet {
    
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
                editProfile(request , response);
                break;
                
                
            default :
                viewProfile(request , response);
                break;
        }
        
    }
            
    public void viewProfile(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        
        String status = "";
        String userID = request.getParameter("id");
        String userRole = request.getParameter("role");
        
        switch(userRole) {
            case "ADMIN" : {
                try{
                    HttpSession session = request.getSession();
                    Admin admin = new Admin();

                    String viewProfile = "SELECT * FROM admin WHERE adminID =" + userID ;
                    PreparedStatement preparedStatement = con.prepareStatement(viewProfile);

                    ResultSet rs = preparedStatement.executeQuery();

                    while(rs.next()){
                        String name = rs.getString("name");
                        String password = rs.getString("password");
                        String email = rs.getString("email");
                        String phone = rs.getString("phoneNo");
                        String role = rs.getString("role");
                        String picture = rs.getString("picture");

                        admin.setName(name);
                        admin.setPassword(password);
                        admin.setEmail(email);
                        admin.setPhoneNo(phone);
                        admin.setRole(role);
                        admin.setPicture(picture);

                        session.setAttribute("user", admin);
                        status = "SUCCESS";
                    }
                    if(status.equals("SUCCESS"))   //On success, you can display a message to user on Home page
                    {
                        request.getRequestDispatcher("/adminProfile.jsp").forward(request, response);
                    }
                    else
                    {
                        request.setAttribute("errMessage", status);
                        request.getRequestDispatcher("/login.jsp").forward(request, response);
                    }
                }catch(SQLException e){

                }
                break;
            }
            case "CLIENT" : {
                try{
                    HttpSession session = request.getSession();
                    Client client = new Client();

                    String viewProfile = "SELECT * FROM client WHERE clientID =" + userID ;
                    PreparedStatement preparedStatement = con.prepareStatement(viewProfile);

                    ResultSet rs = preparedStatement.executeQuery();

                    while(rs.next()){
                        String name = rs.getString("name");
                        String password = rs.getString("password");
                        String email = rs.getString("email");
                        String phone = rs.getString("phoneNo");
                        String role = rs.getString("role");
                        String picture = rs.getString("picture");

                        client.setName(name);
                        client.setPassword(password);
                        client.setEmail(email);
                        client.setPhoneNo(phone);
                        client.setRole(role);
                        client.setPicture(picture);

                        session.setAttribute("user", client);
                        status = "SUCCESS";
                    }
                    if(status.equals("SUCCESS"))   //On success, you can display a message to user on Home page
                    {
                        request.getRequestDispatcher("/clientProfile.jsp").forward(request, response);
                    }
                    else
                    {
                        request.setAttribute("errMessage", status);
                        request.getRequestDispatcher("/login.jsp").forward(request, response);
                    }
                }catch(SQLException e){

                }
                break;
            }
            case "default" : {
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        }
    }   
          
    public void editProfile(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        
        String status = "";
        PreparedStatement psEdit = null;
        String userID = request.getParameter("id");
        String userRole = request.getParameter("role");
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        
        switch(userRole) {
            case "ADMIN" : {
            
                Admin admin = new Admin();
                String editProfile = "UPDATE admin SET password = ?, email = ?, phoneNo = ? WHERE adminID = "+ userID;

                try{

                    psEdit = con.prepareStatement(editProfile);

                    psEdit.setString(1, password);
                    psEdit.setString(2, email);
                    psEdit.setString(3, phone);

                    int statusSQL = psEdit.executeUpdate();
                    psEdit.close();

                    if(statusSQL == 1){
                        request.getRequestDispatcher("/ProfileController?option=view'").forward(request, response);

                    }else{
                        request.getRequestDispatcher("/adminEditProfile.jsp").forward(request, response);

                    }

                }catch(SQLException ex){
                    System.out.println(ex.getMessage());
                        request.getRequestDispatcher("/index.jsp").forward(request, response);
                }

                break;
            }
            case "CLIENT" : {
            
                Client client = new Client();

                String editProfile = "UPDATE client SET password = ?, email = ?, phoneNo = ? WHERE clientID = "+ userID;

                try{

                    psEdit = con.prepareStatement(editProfile);

                    psEdit.setString(1, password);
                    psEdit.setString(2, email);
                    psEdit.setString(3, phone);

                    int statusSQL = psEdit.executeUpdate();
                    psEdit.close();

                    if(statusSQL == 1){
                        request.getRequestDispatcher("/ProfileController?option=view'").forward(request, response);

                    }else{
                        request.getRequestDispatcher("/clientEditProfile.jsp").forward(request, response);

                    }

                }catch(SQLException ex){
                    System.out.println(ex.getMessage());
                        request.getRequestDispatcher("/index.jsp").forward(request, response);
                }
                break;
            }
            case "default" : {
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        }
    }
}
