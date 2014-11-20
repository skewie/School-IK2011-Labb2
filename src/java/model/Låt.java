/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author h11jafva
 */
public class Låt {
    private int id;
    private int recording_id;
    private long duration;
    private String title;
    private int stock;
    private String artist;

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public int getRecordingId(){
        return this.recording_id;
    }
    
    public void setRecordingId(int id){
        this.recording_id = id;
    }
    
    public long getDuration(){
        return this.duration;
    }
    
    public void setDuration(long duration){
        this.duration = duration;
    }
    
    public int getStock(){
        return this.stock;
    }
    
    public void setStock(int stock){
        this.stock = stock;
    }
    
    public String getArtist(){
        return this.artist;
    }
    
    public void setArtist(String artist){
        this.artist = artist;
    }
    
}
