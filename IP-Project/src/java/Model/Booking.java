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
    private String status;

    public Booking() {
    }

    public Booking(int bookingID, int roomID, int clientID, String bookingDateIn, String bookingDateOut, String status) {
        this.bookingID = bookingID;
        this.roomID = roomID;
        this.clientID = clientID;
        this.bookingDateIn = bookingDateIn;
        this.bookingDateOut = bookingDateOut;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
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
    
    
}
