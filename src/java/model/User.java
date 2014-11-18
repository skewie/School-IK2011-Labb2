/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Toppe
 */
public class User {
    
    private int id;
    private String name;
    private String role;
    
    public User(){
        
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public int getId(){
        return this.id;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setRole(String role){
        this.role = role;
    }
    
    public String getRole(){
        return this.role;
    }
    
}
