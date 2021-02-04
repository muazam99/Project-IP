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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.Booking;
import Model.Room;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import jdbc.JDBCutility;

/**
 *
 * @author Muhammad Faiq
 */
@WebServlet(name = "ManageRoomController", urlPatterns = {"/ManageRoomController"})
@MultipartConfig(maxFileSize = 16177216)
public class ManageRoomController extends HttpServlet {

        Room room=new Room();
        private JDBCutility jdbcUtility;
        private Connection con;
        private String sqlStatement;
        PreparedStatement preparedStatementInsert = null;
        private int roomID;
        private String roomNo;
        private String roomType;
        private byte[] roomImage;
        private double roomPrice;
        
        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");    
        String command = request.getParameter("command");
        int roomID;
        String roomNo;
        String roomType;
        double roomPrice;
        Part part;
        InputStream roomImage;
        
        if(command==null){
            command="";
        }
        
        try{     
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
            response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
            response.setDateHeader("Expires", 0);
            
            switch(command){
                
                case "Add-Room-Form":
                    request.getRequestDispatcher("AddRoomInterface.jsp").forward(request, response);
                    break;
                case "Delete-Room":
                    searchAllRoom(request, response);
                    break;
                case "Update-Room":
                    searchAllRoom(request, response);
                    break;
                case "Add-Room":
                    roomNo = request.getParameter("roomNo");
                    roomType = request.getParameter("roomType");
                    roomPrice = Double.parseDouble(request.getParameter("roomPrice"));
                    part = request.getPart("roomImage");
                    roomImage = part.getInputStream();
                    createRoom(roomNo,roomType,roomImage,roomPrice,request,response);
                    break;
                case "Update":
                    roomID=Integer.parseInt(request.getParameter("roomID"));
                    searchSpecificRoom(roomID, request, response);
                    break;
                case "Delete":
                    roomID=Integer.parseInt(request.getParameter("roomID"));
                    deleteRoom(roomID, request, response);
                    break;
                case "Update Room":
                    roomID=Integer.parseInt(request.getParameter("roomID"));
                    roomNo = request.getParameter("roomNo");
                    roomType = request.getParameter("roomType");
                    roomPrice = Double.parseDouble(request.getParameter("roomPrice"));
                    part = request.getPart("roomImage");
                    roomImage = part.getInputStream();
                    updateRoom(roomID, roomNo, roomType, roomImage, roomPrice, request, response);                   
                    break;   
                default :
                   request.getRequestDispatcher("index.jsp").forward(request, response);
                   break;
            }
        }catch (Exception exc) {
            throw new ServletException(exc);
        }
        
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
                searchAllRoom(request, response);
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
            searchAllRoom(request, response);
            //request.getRequestDispatcher("TableDeleteUpdate.jsp").forward(request, response);
        }
        catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }  
        
     }
        
     
        
}
