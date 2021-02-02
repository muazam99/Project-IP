/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;

/**
 *
 * @author Muaz Amir
 */
public interface User  extends Serializable{
   
    public String getID();
    public void setID(String id);
    public String getName();
    public void setName(String name);
    public String getEmail();
    public void setEmail(String email);
    public String getPassword();
    public void setPassword(String password);
    public String getRole();
    public void setRole(String role);
    public String getPhoneNo();
    public void setPhoneNo(String phoneNo);
    
}
