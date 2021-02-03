/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jdbc.JDBCutility;

/**
 *
 * @author Muaz Amir
 */
public class Room {
   
    private JDBCutility jdbcUtility;
    private Connection con;
    private String sqlStatement;
    PreparedStatement preparedStatementInsert = null;
    private int roomID;
    private String roomNo;
    private String roomType;
    private byte[] roomImage;
    private double roomPrice;

    


    public Room() {
    }
    
    public Room(int roomID, String roomNo, String roomType,byte[] roomImage, double roomPrice) {
        this.roomID = roomID;
        this.roomNo = roomNo;
        this.roomType = roomType;
        this.roomImage = roomImage;
        this.roomPrice = roomPrice;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }
    public byte[] getRoomImage() {
        return roomImage;
    }

    public void setRoomImage(byte[] roomImage) {
        this.roomImage = roomImage;
    }
    public double getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(double roomPrice) {
        this.roomPrice = roomPrice;
    }
    
    
    public void createRoom(String roomNo,String roomType, InputStream roomImage, double roomPrice, HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        
        
        String driver = "com.mysql.jdbc.Driver";
        String dbName = "faiqhoteldb";
        String url = "jdbc:mysql://localhost/" + dbName + "?";
        String userName = "root";
        String password = "";
        
        jdbcUtility = new JDBCutility(driver,
                                        url,
                                        userName,
                                        password);

        jdbcUtility.jdbcConnect();
        con = jdbcUtility.jdbcGetConnection();
        
        sqlStatement = "INSERT INTO room(roomNo, roomType, roomImage, roomPrice) VALUES(?, ?, ?, ?)";
        boolean insertSuccess = false;
        try 
        {
            //InputStream eventImage = part.getInputStream();
            preparedStatementInsert = con.prepareStatement(sqlStatement);


            preparedStatementInsert.setString(1, roomNo);
            preparedStatementInsert.setString(2, roomType);
            preparedStatementInsert.setBlob(3, roomImage);
            preparedStatementInsert.setDouble(4, roomPrice);
            
            // execute insert SQL stetement
            preparedStatementInsert.executeUpdate();
            preparedStatementInsert.close();

            if (preparedStatementInsert != null)
            {
                //insertSuccess = true;
                try(PrintWriter out = response.getWriter()){
                    out.println("<script>");
                    out.println("  alert('Register Success');");
                    out.println("</script>");
                    request.getRequestDispatcher("AddRoomInterface.jsp").forward(request, response);
                 }  
            } 
            else
            {
                try (PrintWriter out = response.getWriter()) {
                    out.println("NOSUCCESS!");
                } 
                String message = "Data added unsuccessful";
                HttpSession session = request.getSession();
                session.setAttribute("alertMsg", message);
            }
        }
        catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }  
    }
    
    public void searchAllRoom(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        
        
        String driver = "com.mysql.jdbc.Driver";
        String dbName = "faiqhoteldb";
        String url = "jdbc:mysql://localhost/" + dbName + "?";
        String userName = "root";
        String password = "";
        
        jdbcUtility = new JDBCutility(driver,
                                        url,
                                        userName,
                                        password);

        jdbcUtility.jdbcConnect();
        con = jdbcUtility.jdbcGetConnection();
        sqlStatement ="SELECT * FROM room";
        HttpSession session = request.getSession();            
        //Room room = new Room();
        ArrayList<Room> roomlistSingle = new ArrayList<Room>();
        ArrayList<Room> roomlistDouble = new ArrayList<Room>();
        ArrayList<Room> roomlistTriple = new ArrayList<Room>();
        ArrayList<Room> roomlistQuad = new ArrayList<Room>();
        
        try 
        {               
            preparedStatementInsert  = con.prepareStatement(sqlStatement);

            ResultSet rs = preparedStatementInsert.executeQuery();


            while(rs.next())
            {

                    roomID = rs.getInt("roomID");
                    roomNo = rs.getString("roomNo");
                    roomType = rs.getString("roomType");
                    Blob blob = rs.getBlob("roomImage");
                    byte roomImage[] = blob.getBytes(1, (int) blob.length());
                    roomPrice = rs.getDouble("roomPrice");
                    if(roomType.equals("Single"))
                    {
                        roomlistSingle.add(new Room(roomID,roomNo,roomType,roomImage,roomPrice));
                    }
                    else if(roomType.equals("Double"))
                    {
                        roomlistDouble.add(new Room(roomID,roomNo,roomType,roomImage,roomPrice));
                    }
                    else if(roomType.equals("Triple"))
                    {
                        roomlistTriple.add(new Room(roomID,roomNo,roomType,roomImage,roomPrice));
                    }
                    else if(roomType.equals("Quad"))
                    {
                        roomlistQuad.add(new Room(roomID,roomNo,roomType,roomImage,roomPrice));
                    }
                    
            }
                    session.setAttribute("roomlistSingle", roomlistSingle);
                    session.setAttribute("roomlistDouble", roomlistDouble);
                    session.setAttribute("roomlistTriple", roomlistTriple);
                    session.setAttribute("roomlistQuad", roomlistQuad);
                    preparedStatementInsert.close();
                    request.getRequestDispatcher("TableDeleteUpdate.jsp").forward(request, response);
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
    
    public void searchSpecificRoom(int specificroomID, HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        
        
        String driver = "com.mysql.jdbc.Driver";
        String dbName = "faiqhoteldb";
        String url = "jdbc:mysql://localhost/" + dbName + "?";
        String userName = "root";
        String password = "";
        
        jdbcUtility = new JDBCutility(driver,
                                        url,
                                        userName,
                                        password);

        jdbcUtility.jdbcConnect();
        con = jdbcUtility.jdbcGetConnection();
        sqlStatement ="SELECT * FROM room WHERE (roomID = ?)";
        HttpSession session = request.getSession();
        ArrayList<Room> updateroomlist = new ArrayList<Room>();
        try 
        {
                
            preparedStatementInsert  = con.prepareStatement(sqlStatement);
            preparedStatementInsert.setInt(1, specificroomID);

            ResultSet rs = preparedStatementInsert.executeQuery();

            while(rs.next())
            {
                roomID = rs.getInt("roomID");
                roomNo = rs.getString("roomNo");
                roomType = rs.getString("roomType");
                Blob blob = rs.getBlob("roomImage");
                byte roomImage[] = blob.getBytes(1, (int) blob.length());
                roomPrice = rs.getDouble("roomPrice");

                updateroomlist.add(new Room(roomID,roomNo,roomType,roomImage,roomPrice));
            }
                session.setAttribute("updateroomlist", updateroomlist);
                preparedStatementInsert.close();
                request.getRequestDispatcher("UpdateRoomForm.jsp").forward(request, response);
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
     public void updateRoom(int roomID, String roomNo, String roomType,InputStream roomImage, double roomPrice, HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        
        
        String driver = "com.mysql.jdbc.Driver";
        String dbName = "faiqhoteldb";
        String url = "jdbc:mysql://localhost/" + dbName + "?";
        String userName = "root";
        String password = "";
        
        jdbcUtility = new JDBCutility(driver,
                                        url,
                                        userName,
                                        password);

        jdbcUtility.jdbcConnect();
        con = jdbcUtility.jdbcGetConnection();
        sqlStatement = "UPDATE room SET roomNo = ?, roomType = ?, roomImage = ?, roomPrice = ? WHERE roomID = ?";
        
        
        try {
                //InputStream eventImage = part.getInputStream();
                preparedStatementInsert = con.prepareStatement(sqlStatement);
                
                preparedStatementInsert.setString(1, roomNo);
                preparedStatementInsert.setString(2, roomType);
                preparedStatementInsert.setBlob(3, roomImage);
                preparedStatementInsert.setDouble(4, roomPrice);
                preparedStatementInsert.setInt(5, roomID);

                /*try (PrintWriter out = response.getWriter()) {
                    out.println(eventCategory);
                }*/
            
                // execute insert SQL stetement
                preparedStatementInsert.executeUpdate();
                preparedStatementInsert.close();
                
                if (preparedStatementInsert != null)
                {
                    //insertSuccess = true;
                    String message = "Data updated successfully";
                    HttpSession session = request.getSession();
                    session.setAttribute("alertMsg", message);
                } 
                else
                {
                    String message = "Data updated unsuccessful";
                    HttpSession session = request.getSession();
                    session.setAttribute("alertMsg", message);
                }
                request.getRequestDispatcher("TableDeleteUpdate.jsp").forward(request, response);
            }
            catch(SQLException ex) {
                System.out.println(ex.getMessage());
            }  
     }
     
     public void deleteRoom(int roomID,HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        
        
        String driver = "com.mysql.jdbc.Driver";
        String dbName = "faiqhoteldb";
        String url = "jdbc:mysql://localhost/" + dbName + "?";
        String userName = "root";
        String password = "";
        
        jdbcUtility = new JDBCutility(driver,
                                        url,
                                        userName,
                                        password);

        jdbcUtility.jdbcConnect();
        con = jdbcUtility.jdbcGetConnection();
        sqlStatement = "DELETE FROM room WHERE (roomID = ?)";
            
        try 
        {
            preparedStatementInsert = con.prepareStatement(sqlStatement);


            preparedStatementInsert.setInt(1, roomID);

            // execute insert SQL stetement
            preparedStatementInsert.executeUpdate();

            if (preparedStatementInsert != null)
            {
                preparedStatementInsert.close();

                //insertSuccess = true;
                String message = "Data deleted successfully";
                HttpSession session = request.getSession();
                session.setAttribute("alertMsg", message);
            } 
            else
            {
                String message = "Data deleted unsuccessful";
                HttpSession session = request.getSession();
                session.setAttribute("alertMsg", message);
            }
            request.getRequestDispatcher("TableDeleteUpdate.jsp").forward(request, response);
        }
        catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }  
        
     }
    
}
