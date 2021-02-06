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
public class Room {
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
}
