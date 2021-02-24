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
import Model.*;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Locale;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;
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
                case "Book Room":
                    roomType = request.getParameter("roomType");
                    traveldateIn = request.getParameter("traveldateIn");
                    traveldateOut = request.getParameter("traveldateOut");
                    displayBookInfo(roomType, traveldateIn,  traveldateOut,request,response);
                    request.getRequestDispatcher("displayBookInfoBefore.jsp").forward(request, response);
                    break;
                case "Book Room Confirm":
                    roomType = request.getParameter("roomType");
                    traveldateIn = request.getParameter("traveldateIn");
                    traveldateOut = request.getParameter("traveldateOut");
                    displayBookInfo(roomType, traveldateIn,  traveldateOut,request,response);
                    request.getRequestDispatcher("paymentAfterBook.jsp").forward(request, response);
                    break;
                case "Payment":
                    roomType = request.getParameter("roomType");
                    traveldateIn = request.getParameter("traveldateIn");
                    traveldateOut = request.getParameter("traveldateOut");
                    totalPrice= Double.parseDouble(request.getParameter("totalPrice"));
                    clientID = Integer.parseInt(request.getParameter("clientID"));
                    Part part = request.getPart("bookImgProofImage");
                    InputStream bookImgProofImage = part.getInputStream();
                    assignRoom(roomType,traveldateIn,traveldateOut,request,response);
                    bookingID=searchBookingID( roomType, traveldateIn,  traveldateOut, request,  response);
                    payment(clientID,bookingID,bookImgProofImage,totalPrice,request,response);
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                    break;
                    
                default :
                   request.getRequestDispatcher("index.jsp").forward(request, response);
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
        session.setAttribute("traveldateIn", traveldateIn);
        session.setAttribute("traveldateOut", traveldateOut);
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
    
    public void assignRoom(String roomType,String traveldateIn,String traveldateOut,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sessionStatus = "";
        HttpSession session = request.getSession();
        int i=0;
        int roomID;
        ArrayList<Room> roomSingleAvailable = (ArrayList<Room>)session.getAttribute("roomSingleAvailable");
        ArrayList<Room> roomDoubleAvailable = (ArrayList<Room>)session.getAttribute("roomDoubleAvailable");
        ArrayList<Room> roomTripleAvailable = (ArrayList<Room>)session.getAttribute("roomTripleAvailable");
        ArrayList<Room> roomQuadAvailable = (ArrayList<Room>)session.getAttribute("roomQuadAvailable");
        Client client = (Client)session.getAttribute("CLIENT");
        int clientID = Integer.parseInt(client.getID());
        if(roomType.equals("Single"))
        {
            roomID=((Room)roomSingleAvailable.get(i)).getRoomID();
            String sqlStatement = "INSERT INTO booking(roomID, clientID, bookingDateIn, bookingDateOut, status) VALUES(?, ?, ?, ?, ?)";
            boolean insertSuccess = false;
            try 
            {
                //InputStream eventImage = part.getInputStream();
                PreparedStatement preparedStatementInsert = con.prepareStatement(sqlStatement);


                preparedStatementInsert.setInt(1, roomID);
                preparedStatementInsert.setInt(2, clientID);
                preparedStatementInsert.setString(3, traveldateIn);
                preparedStatementInsert.setString(4, traveldateOut);
                preparedStatementInsert.setString(5, "Booked");
                
                // execute insert SQL stetement
                preparedStatementInsert.executeUpdate();
                preparedStatementInsert.close();

                if (preparedStatementInsert != null)
                {
                    //insertSuccess = true;
                     
                } 
                else
                {
                    try(PrintWriter out = response.getWriter()){
                    out.println("<script>");
                    out.println("  alert('Register Not Success');");
                    out.println("</script>");

                 }  
                }
            }
            catch(SQLException ex) {
                System.out.println(ex.getMessage());
            }  
        }
        else if(roomType.equals("Double"))
        {
            roomID=((Room)roomDoubleAvailable.get(i)).getRoomID();
            String sqlStatement = "INSERT INTO booking(roomID, clientID, bookingDateIn, bookingDateOut, status) VALUES(?, ?, ?, ?, ?)";
            boolean insertSuccess = false;
            try 
            {
                //InputStream eventImage = part.getInputStream();
                PreparedStatement preparedStatementInsert = con.prepareStatement(sqlStatement);


                preparedStatementInsert.setInt(1, roomID);
                preparedStatementInsert.setInt(2, clientID);
                preparedStatementInsert.setString(3, traveldateIn);
                preparedStatementInsert.setString(4, traveldateOut);
                preparedStatementInsert.setString(5, "Booked");
                
                // execute insert SQL stetement
                preparedStatementInsert.executeUpdate();
                preparedStatementInsert.close();

                if (preparedStatementInsert != null)
                {
                    //insertSuccess = true;
                      
                } 
                else
                {
                    try(PrintWriter out = response.getWriter()){
                    out.println("<script>");
                    out.println("  alert('Register Not Success');");
                    out.println("</script>");

                 }  
                }
            }
            catch(SQLException ex) {
                System.out.println(ex.getMessage());
            }  
        }
        else if(roomType.equals("Triple"))
        {
            roomID=((Room)roomTripleAvailable.get(i)).getRoomID();
            String sqlStatement = "INSERT INTO booking(roomID, clientID, bookingDateIn, bookingDateOut, status) VALUES(?, ?, ?, ?, ?)";
            boolean insertSuccess = false;
            try 
            {
                //InputStream eventImage = part.getInputStream();
                PreparedStatement preparedStatementInsert = con.prepareStatement(sqlStatement);


                preparedStatementInsert.setInt(1, roomID);
                preparedStatementInsert.setInt(2, clientID);
                preparedStatementInsert.setString(3, traveldateIn);
                preparedStatementInsert.setString(4, traveldateOut);
                preparedStatementInsert.setString(5, "Booked");
                
                // execute insert SQL stetement
                preparedStatementInsert.executeUpdate();
                preparedStatementInsert.close();

                if (preparedStatementInsert != null)
                {
                    //insertSuccess = true;
                     
                } 
                else
                {
                    try(PrintWriter out = response.getWriter()){
                    out.println("<script>");
                    out.println("  alert('Register Not Success');");
                    out.println("</script>");

                 }  
                }
            }
            catch(SQLException ex) {
                System.out.println(ex.getMessage());
            }  
        }
        else if(roomType.equals("Quad"))
        {
            roomID=((Room)roomQuadAvailable.get(i)).getRoomID();
            String sqlStatement = "INSERT INTO booking(roomID, clientID, bookingDateIn, bookingDateOut, status) VALUES(?, ?, ?, ?, ?)";
            boolean insertSuccess = false;
            try 
            {
                //InputStream eventImage = part.getInputStream();
                PreparedStatement preparedStatementInsert = con.prepareStatement(sqlStatement);


                preparedStatementInsert.setInt(1, roomID);
                preparedStatementInsert.setInt(2, clientID);
                preparedStatementInsert.setString(3, traveldateIn);
                preparedStatementInsert.setString(4, traveldateOut);
                preparedStatementInsert.setString(5, "Booked");
                
                // execute insert SQL stetement
                preparedStatementInsert.executeUpdate();
                preparedStatementInsert.close();

                if (preparedStatementInsert != null)
                {
                    //insertSuccess = true;
                    
                } 
                else
                {
                    try(PrintWriter out = response.getWriter()){
                    out.println("<script>");
                    out.println("  alert('Register Not Success');");
                    out.println("</script>");

                 }  
                }
            }
            catch(SQLException ex) {
                System.out.println(ex.getMessage());
            }  
        }      
    }
    
    public static long betweenDates(Date firstDate, Date secondDate) throws IOException
    {
        long difference_In_Time  = secondDate.getTime() - firstDate.getTime(); 
        long difference_In_Days  = (difference_In_Time / (1000 * 60 * 60 * 24)) % 365; 
        //return ChronoUnit.DAYS.between(firstDate.toInstant(), secondDate.toInstant());
        return difference_In_Days;
    }
    
    public void displayBookInfo(String roomType,String traveldateIn, String traveldateOut,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int i=0;
        HttpSession session = request.getSession();
        ArrayList<Room> roomSingleAvailable = (ArrayList<Room>)session.getAttribute("roomSingleAvailable");
        ArrayList<Room> roomDoubleAvailable = (ArrayList<Room>)session.getAttribute("roomDoubleAvailable");
        ArrayList<Room> roomTripleAvailable = (ArrayList<Room>)session.getAttribute("roomTripleAvailable");
        ArrayList<Room> roomQuadAvailable = (ArrayList<Room>)session.getAttribute("roomQuadAvailable");
        
        
        Date sqltraveldateIn=Date.valueOf(traveldateIn);
        Date sqltraveldateOut=Date.valueOf(traveldateOut);
        long datediff=betweenDates(sqltraveldateIn,sqltraveldateOut);
        double totalPrice=0;
        double pricepernight=0;
        byte roomImage[]=((Room)roomSingleAvailable.get(i)).getRoomImage();
        if(roomType.equals("Single"))
        {
            pricepernight=((Room)roomSingleAvailable.get(i)).getRoomPrice();
            totalPrice=pricepernight*datediff;
            roomImage =((Room)roomSingleAvailable.get(i)).getRoomImage();
        }
        else if(roomType.equals("Double"))
        {
            pricepernight=((Room)roomDoubleAvailable.get(i)).getRoomPrice();
            totalPrice=pricepernight*datediff;
            roomImage =((Room)roomDoubleAvailable.get(i)).getRoomImage();
        }
        else if(roomType.equals("Triple"))
        {
            pricepernight=((Room)roomTripleAvailable.get(i)).getRoomPrice();
            totalPrice=pricepernight*datediff;
            roomImage =((Room)roomTripleAvailable.get(i)).getRoomImage();
        }
        else if(roomType.equals("Quad"))
        {
            pricepernight=((Room)roomQuadAvailable.get(i)).getRoomPrice();
            totalPrice=pricepernight*datediff;
            roomImage =((Room)roomQuadAvailable.get(i)).getRoomImage();
        }
                
        session.setAttribute("roomImage", roomImage);        
        session.setAttribute("pricepernight", pricepernight);
        session.setAttribute("totalPrice", totalPrice);
        session.setAttribute("roomType", roomType);
        session.setAttribute("traveldateIn", traveldateIn);
        session.setAttribute("traveldateOut", traveldateOut);
        session.setAttribute("datediff", datediff);
    }
    
    public int searchBookingID(String roomType,String traveldateIn, String traveldateOut,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Client client = (Client)session.getAttribute("CLIENT");
        int clientID = Integer.parseInt(client.getID());
        int roomID=-1;
        int i=0;
        ArrayList<Room> roomSingleAvailable = (ArrayList<Room>)session.getAttribute("roomSingleAvailable");
        ArrayList<Room> roomDoubleAvailable = (ArrayList<Room>)session.getAttribute("roomDoubleAvailable");
        ArrayList<Room> roomTripleAvailable = (ArrayList<Room>)session.getAttribute("roomTripleAvailable");
        ArrayList<Room> roomQuadAvailable = (ArrayList<Room>)session.getAttribute("roomQuadAvailable");
        
        if(roomType.equals("Single"))
        {
            roomID=((Room)roomSingleAvailable.get(i)).getRoomID();
        }
        else if(roomType.equals("Double"))
        {
            roomID=((Room)roomDoubleAvailable.get(i)).getRoomID();
        }
        else if(roomType.equals("Triple"))
        {
            roomID=((Room)roomTripleAvailable.get(i)).getRoomID();
        }
        else if(roomType.equals("Quad"))
        {
            roomID=((Room)roomQuadAvailable.get(i)).getRoomID();
        }
        int bookingID=-1;
        String sqlStatement ="SELECT bookingID FROM booking WHERE bookingDateIn = ? AND bookingDateOut = ? AND clientID = ? AND roomID = ?";
        ArrayList<Integer> roomIDBookedList = new ArrayList<Integer>();
        Date sqltraveldateIn=Date.valueOf(traveldateIn);
        Date sqltraveldateOut=Date.valueOf(traveldateOut);
        try {
                 
                PreparedStatement preparedStatementInsert  = con.prepareStatement(sqlStatement);
                preparedStatementInsert.setDate(1, sqltraveldateIn);
                preparedStatementInsert.setDate(2, sqltraveldateOut);
                preparedStatementInsert.setInt(3, clientID);
                preparedStatementInsert.setInt(4, roomID);
                
                ResultSet rs = preparedStatementInsert.executeQuery();
                
                while(rs.next())
                {
                    bookingID = rs.getInt("bookingID");
                         
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
        
        
        
        return bookingID;
    }
    
    public void payment(int clientID,int bookingID,InputStream bookImgProofImage, double totalPrice,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String sqlStatement = "INSERT INTO payment(clientID, bookingID, bookImgProofImage, totalPrice) VALUES(?, ?, ?, ?)";
        boolean insertSuccess = false;
        try 
        {
            //InputStream eventImage = part.getInputStream();
            PreparedStatement preparedStatementInsert = con.prepareStatement(sqlStatement);


            preparedStatementInsert.setInt(1, clientID);
            preparedStatementInsert.setInt(2, bookingID);
            preparedStatementInsert.setBlob(3, bookImgProofImage);
            preparedStatementInsert.setDouble(4, totalPrice);

            // execute insert SQL stetement
            preparedStatementInsert.executeUpdate();
            preparedStatementInsert.close();

            if (preparedStatementInsert != null)
            {
                //insertSuccess = true;
                 
            } 
            else
            {
                
            }
        }
        catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
