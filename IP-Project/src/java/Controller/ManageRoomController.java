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
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.annotation.MultipartConfig;
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
        
        
        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            
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
                    room.searchAllRoom(request, response);
                    break;
                case "Update-Room":
                    room.searchAllRoom(request, response);
                    break;
                case "Add-Room":
                    roomNo = request.getParameter("roomNo");
                    roomType = request.getParameter("roomType");
                    roomPrice = Double.parseDouble(request.getParameter("roomPrice"));
                    part = request.getPart("roomImage");
                    roomImage = part.getInputStream();
                    room.createRoom(roomNo,roomType,roomImage,roomPrice,request,response);
                    break;
                case "Update":
                    roomID=Integer.parseInt(request.getParameter("roomID"));
                    try (PrintWriter out = response.getWriter()) {
                out.println(roomID);
            } 
                    room.searchSpecificRoom(roomID, request, response);
                    break;
                case "Delete":
                    roomID=Integer.parseInt(request.getParameter("roomID"));
                    room.deleteRoom(roomID, request, response);
                    break;
                case "Update Room Form":
                    roomID=Integer.parseInt(request.getParameter("roomID"));
                    roomNo = request.getParameter("roomNo");
                    roomType = request.getParameter("roomType");
                    roomPrice = Double.parseDouble(request.getParameter("roomPrice"));
                    part = request.getPart("roomImage");
                    roomImage = part.getInputStream();
                    room.updateRoom(roomID, roomNo, roomType, roomImage, roomPrice, request, response);
                    break;   
                default :
                   request.getRequestDispatcher("index.jsp").forward(request, response);
                   break;
            }
        }catch (Exception exc) {
            throw new ServletException(exc);
        }
        
    }
}
