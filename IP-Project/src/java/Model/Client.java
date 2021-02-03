/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Muaz Amir
 */
public class Client{
    
    private String ID;    
    private String name;
    private String password;
    private String email;
    private String role;
    private String phoneNo;
    private String picture;
    
    private boolean bookingStatus;
    private Room bookingDetail;
    private String bookingDate;


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }
    

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getBookingDate() {
        return bookingDate;
    }


    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public String getPhoneNo() {
        return phoneNo;
    }


    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public boolean isBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(boolean bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public Room getBookingDetail() {
        return bookingDetail;
    }

    public void setBookingDetail(Room bookingDetail) {
        this.bookingDetail = bookingDetail;
    }
    
    /**
     * @return the picture
     */
    public String getPicture() {
        return picture;
    }

    /**
     * @param picture the picture to set
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }
    
    
}
