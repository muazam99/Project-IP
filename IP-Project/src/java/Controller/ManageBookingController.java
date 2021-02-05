/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.Booking;
import Model.Room;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import jdbc.JDBCutility;
/**
 *
 * @author Muhammad Faiq
 */
@WebServlet(name = "ManageBookingController", urlPatterns = {"/ManageBookingController"})
public class ManageBookingController extends HttpServlet {

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
    
    
    
    Booking booking = new Booking();
    
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
                
                case "Search For Rooms":
                    try (PrintWriter out = response.getWriter()) {
                    out.println("Invalid event!");
                } 
                     break;
                
                case "View-Booking-Page":
                    viewBookedRoom(request, response);
                    request.getRequestDispatcher("viewBookRoom.jsp").forward(request, response);
                    break;
                
                case "Check In":
                    request.getRequestDispatcher("viewBookRoom.jsp").forward(request, response);
                    break;
                    
                case "Check Out":
                    request.getRequestDispatcher("viewBookRoom.jsp").forward(request, response);
                    break;
                    
                default :
                   request.getRequestDispatcher("index.jsp").forward(request, response);
                   break;
            }
        }catch (Exception exc) {
            throw new ServletException(exc);
        }
        
    }
    
    public void searchRooms(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String traveldateIn = request.getParameter("traveldateIn");
        String traveldateOut = request.getParameter("traveldateOut");
        booking.searchAvailableRooms(traveldateIn, traveldateOut, request, response);
        
    }
  
    public void viewBookedRoom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sessionStatus = "";
        HttpSession session = request.getSession();
        
        ArrayList<Booking> bookingList = new ArrayList<Booking>();
        
        try{
            String viewBookRoom = "SELECT * FROM booking";
            PreparedStatement preparedStatementViewBook = con.prepareStatement(viewBookRoom);
            ResultSet rs = preparedStatementViewBook.executeQuery();
            

            while (rs.next()){
                int bookingID         = rs.getInt("bookingID");
                int roomID            = rs.getInt("roomID");
                int clientID          = rs.getInt("clientID");
                String bookingDateIn  = rs.getString("bookingDateIn");
                String bookingDateOut = rs.getString("bookingDateOut");
                String status         = rs.getString("status");
                
                bookingList.add(new Booking( bookingID, roomID, clientID, bookingDateIn, bookingDateOut, status));

            }
            
            session.setAttribute("bookingList", bookingList);
            preparedStatementViewBook.close();
            
        }
        catch(SQLException e){
                sessionStatus = "INVALID ACCOUNT";
        }
        
    }
    
    public void checkIn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String sqlStatement = "UPDATE booking SET status = 'Check In'";
    }
    
    public void checkOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String sqlStatement = "UPDATE booking SET status = 'Check Out'";
    }
}
