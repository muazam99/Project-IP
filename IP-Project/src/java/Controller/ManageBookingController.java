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
import java.sql.Connection;
import java.sql.PreparedStatement;
import jdbc.JDBCutility;
/**
 *
 * @author Muhammad Faiq
 */
@WebServlet(name = "ManageBookingController", urlPatterns = {"/ManageBookingController"})
public class ManageBookingController extends HttpServlet {

    Booking booking=new Booking();
    
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
    
    
}
