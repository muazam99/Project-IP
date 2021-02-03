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
import javax.servlet.http.Part;
import jdbc.JDBCutility;

/**
 *
 * @author Muhammad Faiq
 */
@WebServlet(name = "ManageRoomController", urlPatterns = {"/ManageRoomController"})
public class ManageRoomController extends HttpServlet {

        Room room=new Room();
        
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
                
                case "Add Room Form":
                    request.getRequestDispatcher("AddRoomInterface.jsp").forward(request, response);
                    break;
                case "Delete Room":
                    try (PrintWriter out = response.getWriter()) {
                    out.println("Invalid event2!");
                } 
                     break;
                case "Update Room":
                    try (PrintWriter out = response.getWriter()) {
                    out.println("Invalid event3!");
                } 
                     break;
                case "Add Room":
                    String roomNo = request.getParameter("roomNo");
                    String roomType = request.getParameter("roomType");
                    double roomPrice = Double.parseDouble(request.getParameter("roomPrice"));
                    Part part = request.getPart("roomImage");
                    InputStream roomImage = part.getInputStream();
                    room.createRoom(roomNo,roomType,roomImage,roomPrice,request,response);
                    request.getRequestDispatcher("AddRoomInterface.jsp").forward(request, response);
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
