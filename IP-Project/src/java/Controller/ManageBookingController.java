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
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import javax.servlet.annotation.MultipartConfig;
import jdbc.JDBCutility;
/**
 *
 * @author Muhammad Faiq
 */
@WebServlet(name = "ManageBookingController", urlPatterns = {"/ManageBookingController"})
@MultipartConfig(maxFileSize = 16177216)
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
        
        int bookingID,guest_adult;
        String traveldateIn,traveldateOut;
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
                    traveldateIn = request.getParameter("traveldateIn");
                    traveldateOut = request.getParameter("traveldateOut");
                    guest_adult = Integer.parseInt(request.getParameter("guest_adult"));
                    searchAvailableRooms(guest_adult,traveldateIn, traveldateOut, request, response);
                    request.getRequestDispatcher("viewRoomAvailable.jsp").forward(request, response);
                    break;                
                case "Check-In":
                    bookingID=Integer.parseInt(request.getParameter("bookingID"));
                    checkIn(bookingID,request,response);
                    viewBookedRoom(request, response);
                    request.getRequestDispatcher("viewBookRoom.jsp").forward(request, response);
                    break;
                case "Check-Out":
                    bookingID=Integer.parseInt(request.getParameter("bookingID"));
                    checkOut(bookingID,request,response);
                    viewBookedRoom(request, response);
                    request.getRequestDispatcher("viewBookRoom.jsp").forward(request, response);
                    break;
                    
                default :
                   //request.getRequestDispatcher("index.jsp").forward(request, response);
                   break;
            }
        }catch (Exception exc) {
            throw new ServletException(exc);
        }
        
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        int bookingID;
        String command = request.getParameter("command");
        
        if(command==null){
            command="";
        }
        
        try{
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
            response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
            response.setDateHeader("Expires", 0);
            
            switch(command){
                               
                case "View-Booking-Page":
                    viewBookedRoom(request, response);
                    request.getRequestDispatcher("viewBookRoom.jsp").forward(request, response);
                    break;
                default :
                   //request.getRequestDispatcher("index.jsp").forward(request, response);
                   break;
            }
        }catch (Exception exc) {
            throw new ServletException(exc);
        }
        
    }
    
    public void searchRooms(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        
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
    
    public void checkIn(int bookingID,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String sqlStatement = "UPDATE booking SET status = ? WHERE bookingID = ?";
        
        try {
            PreparedStatement preparedStatementCheckIn = con.prepareStatement(sqlStatement);
            
            preparedStatementCheckIn.setString(1, "checkIn");
            preparedStatementCheckIn.setInt(2, bookingID);
            preparedStatementCheckIn.executeUpdate();
            preparedStatementCheckIn.close();
        }
        catch(SQLException ex) {
            System.out.println(ex.getMessage());
        } 
    }
    
    public void checkOut(int bookingID,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String sqlStatement = "UPDATE booking SET status = ? WHERE bookingID = ?";
        
        try {
            PreparedStatement preparedStatementCheckIn = con.prepareStatement(sqlStatement);
            
            preparedStatementCheckIn.setString(1, "checkOut");
            preparedStatementCheckIn.setInt(2, bookingID);
            preparedStatementCheckIn.executeUpdate();
            preparedStatementCheckIn.close();
        }
        catch(SQLException ex) {
            System.out.println(ex.getMessage());
        } 
    }
    public void searchAvailableRooms(int guest_adult,String traveldateIn, String traveldateOut,HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException, ParseException{
        
        //search room that is already booked from booking table
        Date sqltraveldateIn=Date.valueOf(traveldateIn);
        Date sqltraveldateOut=Date.valueOf(traveldateOut);
        String sqlStatement ="SELECT roomID FROM booking WHERE bookingDateIn >= ? AND bookingDateOut <= ?";
        HttpSession session = request.getSession();
        session.setAttribute("guest_adult", guest_adult);
        ArrayList<Integer> roomIDBookedList = new ArrayList<Integer>();
        
        try {
                 
                PreparedStatement preparedStatementInsert  = con.prepareStatement(sqlStatement);
                preparedStatementInsert.setDate(1, sqltraveldateIn);
                preparedStatementInsert.setDate(2, sqltraveldateOut);
                
                ResultSet rs = preparedStatementInsert.executeQuery();
                
                while(rs.next())
                {
                    int roomID = rs.getInt("roomID");

                    roomIDBookedList.add(roomID);
                         
                }
                       // session.setAttribute("roomIDList", roomIDList);
                        preparedStatementInsert.close();  
            }
            catch(SQLException ex) {
                System.out.println(ex.getMessage());
            } 
            catch (NullPointerException e) {
                try (PrintWriter out = response.getWriter()) {
                        out.println("SUCCESS!");
                    } 
            }
        if(roomIDBookedList.isEmpty())
        {
            
        }
        
        //search room that is not booked from room table
        Room room=new Room();
        ArrayList<Room> roomSingleAvailable = new ArrayList<Room>();
        ArrayList<Room> roomDoubleAvailable = new ArrayList<Room>();
        ArrayList<Room> roomTripleAvailable = new ArrayList<Room>();
        ArrayList<Room> roomQuadAvailable = new ArrayList<Room>();
        String roomNo,roomType;
        int roomID1;
        double roomPrice;
        
        
        if(roomIDBookedList.isEmpty())
        {
            sqlStatement ="SELECT * FROM room";
            
            try 
            {               
                PreparedStatement preparedStatementInsert  = con.prepareStatement(sqlStatement);

                ResultSet rs = preparedStatementInsert.executeQuery();


                while(rs.next())
                {

                        roomID1 = rs.getInt("roomID");
                        roomNo = rs.getString("roomNo");
                        roomType = rs.getString("roomType");
                        Blob blob = rs.getBlob("roomImage");
                        byte roomImage[] = blob.getBytes(1, (int) blob.length());
                        roomPrice = rs.getDouble("roomPrice");
                        if(roomType.equals("Single"))
                        {
                            roomSingleAvailable.add(new Room(roomID1,roomNo,roomType,roomImage,roomPrice));
                        }
                        else if(roomType.equals("Double"))
                        {
                            roomDoubleAvailable.add(new Room(roomID1,roomNo,roomType,roomImage,roomPrice));
                        }
                        else if(roomType.equals("Triple"))
                        {
                            roomTripleAvailable.add(new Room(roomID1,roomNo,roomType,roomImage,roomPrice));
                        }
                        else if(roomType.equals("Quad"))
                        {
                            roomQuadAvailable.add(new Room(roomID1,roomNo,roomType,roomImage,roomPrice));
                        }

                }
                        session.setAttribute("roomSingleAvailable", roomSingleAvailable);
                        session.setAttribute("roomDoubleAvailable", roomDoubleAvailable);
                        session.setAttribute("roomTripleAvailable", roomTripleAvailable);
                        session.setAttribute("roomQuadAvailable", roomQuadAvailable);
                        preparedStatementInsert.close();
            }
            catch(SQLException ex) {
                System.out.println(ex.getMessage());
            } 
            catch (NullPointerException e) {
                try (PrintWriter out = response.getWriter()) {
                    out.println("SUCCESS!");
                } 
            }
        }
        else
        {
            for(int i=0;i<roomIDBookedList.size();i++){
            sqlStatement ="SELECT * FROM room WHERE roomID <> ?";
        
        
            try {

                    PreparedStatement preparedStatementInsert  = con.prepareStatement(sqlStatement);
                    preparedStatementInsert.setInt(1, roomIDBookedList.get(i));

                    ResultSet rs = preparedStatementInsert.executeQuery();

                    while(rs.next())
                    {
                        
                        roomID1 = rs.getInt("roomID");
                        roomNo = rs.getString("roomNo");
                        roomType = rs.getString("roomType");
                        Blob blob = rs.getBlob("roomImage");
                        byte roomImage[] = blob.getBytes(1, (int) blob.length());
                        roomPrice = rs.getDouble("roomPrice");
                        if(roomType.equals("Single"))
                        {
                            roomSingleAvailable.add(new Room(roomID1,roomNo,roomType,roomImage,roomPrice));
                        }
                        else if(roomType.equals("Double"))
                        {
                            roomDoubleAvailable.add(new Room(roomID1,roomNo,roomType,roomImage,roomPrice));
                        }
                        else if(roomType.equals("Triple"))
                        {
                            roomTripleAvailable.add(new Room(roomID1,roomNo,roomType,roomImage,roomPrice));
                        }
                        else if(roomType.equals("Quad"))
                        {
                            roomQuadAvailable.add(new Room(roomID1,roomNo,roomType,roomImage,roomPrice));
                        }
                    }
                        session.setAttribute("roomSingleAvailable", roomSingleAvailable);
                        session.setAttribute("roomDoubleAvailable", roomDoubleAvailable);
                        session.setAttribute("roomTripleAvailable", roomTripleAvailable);
                        session.setAttribute("roomQuadAvailable", roomQuadAvailable);
                        preparedStatementInsert.close();  
                }
                catch(SQLException ex) {
                    System.out.println(ex.getMessage());
                } 
                catch (NullPointerException e) {
                    try (PrintWriter out = response.getWriter()) {
                            out.println("SUCCESS!");
                        } 
                }
            }
        }
        
    }
}
