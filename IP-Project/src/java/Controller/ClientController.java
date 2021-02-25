 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Booking;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jdbc.JDBCutility;

/**
 *
 * @author Muaz Amir
 */
@WebServlet(name = "ClientController", urlPatterns = {"/ClientController"})
public class ClientController extends HttpServlet {

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
  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
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
                
                case "Book-Room-Page" :
                   request.getRequestDispatcher("BookRoom.jsp").forward(request, response);
                    break;
                                
                case "View-Room-Page" :
                        
                     break;
                    
                default :
                   request.getRequestDispatcher("index.jsp").forward(request, response);
                   break;
            }
        }catch (Exception exc) {
            throw new ServletException(exc);
        }
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int bookingID,guest_adult,clientID;
        String traveldateIn,traveldateOut,roomType,clientName,clientEmail,clientPhoneNo;
        String command = request.getParameter("command");
        double totalPrice;
        
        if(command==null){
            command="";
        }
        try{
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
            response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
            response.setDateHeader("Expires", 0);
        
            switch(command){
                case "My-Booking":
                    PrintWriter out= response.getWriter();
                    out.println("Hello again");
                    clientID = Integer.parseInt(request.getParameter("clientID"));
                    viewMyBooking(clientID, request, response);
                    request.getRequestDispatcher("viewMyBooking.jsp").forward(request, response);
                    break;
            }
        }
        catch (Exception exc) {
            throw new ServletException(exc);
        }
        processRequest(request, response);
    }

    public void viewMyBooking(int clientID, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String sessionStatus = "";
        HttpSession session = request.getSession();
        
        ArrayList<Booking> myBookingList = new ArrayList<Booking>();
        int bookingID,roomID;
        String status, bookingDateIn, bookingDateOut;
        String viewBookRoom = "SELECT * FROM booking WHERE clientID =? AND status != 'checkOut'";
        
        try{
            
            PreparedStatement preparedStatementViewMyBooking = con.prepareStatement(viewBookRoom);
            preparedStatementViewMyBooking.setInt(1, clientID);
            ResultSet rs = preparedStatementViewMyBooking.executeQuery();
            
            while(rs.next()){
                bookingID      = rs.getInt("bookingID");
                roomID         = rs.getInt("roomID");
                bookingDateIn  = rs.getString("bookingDateIn");
                bookingDateOut = rs.getString("bookingDateOut");
                status         = rs.getString("status");
                
                myBookingList.add(new Booking( bookingID, roomID, bookingDateIn, bookingDateOut, status));
            }
            
            session.setAttribute("myBookingList", myBookingList);
            preparedStatementViewMyBooking.close();
            
        }
        catch(SQLException e){
                sessionStatus = "INVALID ACCOUNT";
        }
    }
    
    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
