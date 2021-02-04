/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Blob;
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
import Model.Room;

/**
 *
 * @author Muhammad Faiq
 */
@WebServlet(name = "Booking", urlPatterns = {"/Booking"})
public class Booking extends HttpServlet {

    private String sqlStatement;
    PreparedStatement preparedStatementInsert = null;
    
    private int bookingID;
    private int roomID;
    private int clientID;
    private String bookingDateIn;
    private String bookingDateOut;

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public String getBookingDateIn() {
        return bookingDateIn;
    }

    public void setBookingDateIn(String bookingDateIn) {
        this.bookingDateIn = bookingDateIn;
    }

    public String getBookingDateOut() {
        return bookingDateOut;
    }

    public void setBookingDateOut(String bookingDateOut) {
        this.bookingDateOut = bookingDateOut;
    }
    
    public void searchAvailableRooms(String traveldateIn, String traveldateOut,HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        Connection con = null;
        PreparedStatement ps = null;
        con = JDBCutility.getConnection();
        
        //search room that is already booked from booking table
        sqlStatement ="SELECT roomID FROM booking WHERE (bookingDateIn >= ? AND bookingDateOut <= ?)";
        HttpSession session = request.getSession();
        ArrayList<Integer> roomIDBookedList = new ArrayList<Integer>();
        
        try {
                
                preparedStatementInsert  = con.prepareStatement(sqlStatement);
                preparedStatementInsert.setString(1, traveldateIn);
                preparedStatementInsert.setString(2, traveldateOut);
                
                ResultSet rs = preparedStatementInsert.executeQuery();
                
                while(rs.next())
                {
                    
                        roomID = rs.getInt("roomID");
   
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
        
        
        //search room that is not booked from room table
        Room room=new Room();
        ArrayList<Room> roomAvailable = new ArrayList<Room>();
        String roomNo,roomType;
        int roomID1;
        double roomPrice;
        for(int i=0;i<roomIDBookedList.size();i++){
            sqlStatement ="SELECT * FROM room WHERE (roomID NOT IN ?)";
        
        
            try {

                    preparedStatementInsert  = con.prepareStatement(sqlStatement);
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

                            roomAvailable.add(new Room(roomID1,roomNo,roomType,roomImage,roomPrice));
                    }
                            session.setAttribute("roomAvailable", roomAvailable);
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
        request.getRequestDispatcher("index.jsp").forward(request, response);
        
        
        
        
    }
}
