/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Album;
import model.Kategori;
import model.Låt;

/**
 *
 * @author h11jafva
 */
public class DBConnector {
    /*
        Klass som sköter om kommunikation mot databasen music_ik2011.
        För att objektreferens till denna klass ska bli åtkomlig från alla 
        servlets så skapas en applikationslyssnare.
    */
    
    private Connection con = null;
    
    public DBConnector(){
        connect();
    }
    
    private void connect(){
        try{
            Class.forName("com.mysql.jdbc.Driver"); //driver
            String url = "jdbc:mysql://jval.synology.me/phpMyAdmin/ik2011_labb2"; //server/db
            String username = "school"; //username
            String pass = "TobJaf"; //pass
            con = DriverManager.getConnection(url, username, pass);
            //om connection funkar
            System.out.println("Connected!");
        }catch(ClassNotFoundException | SQLException e){
            //om det inte går att connecta
            System.out.println("FAILED TO CONNECT!!! Reason:\n");
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Hämtar all data beroende på kategori id
     * @param categoryId id som identifierar kategori
     * @return arraylista av typ: album
     */
    public ArrayList<Album> queryCategoryAlbums(int categoryId) {
        ArrayList<Album> list = new ArrayList<>();
        //kallar på lagrad procedur
        try{
             /* --query--
                SELECT * 
                FROM music_recordings 
                WHERE category IN(
                        SELECT name 
                        FROM music_categories 
                        WHERE id = p_categoryId);
            */
            CallableStatement stmt = con.prepareCall("{ call musicsite_p_getCategoryAlbums('" + categoryId + "') }");
            stmt.executeQuery();
            ResultSet rs = stmt.getResultSet();
            while(rs.next()){
                Album album = new Album();
                album.setArtist(rs.getString("artist_name"));
                album.setCategory(rs.getString("category"));
                album.setImageFileName(rs.getString("image_name"));
                album.setTrackCount(rs.getInt("num_tracks"));
                album.setPrice(rs.getInt("price"));
                album.setRecordingId(rs.getInt("recording_id"));
                album.setStockCount(rs.getInt("stock_count"));
                album.setTitle(rs.getString("title"));
                //lägger till album till arraylist
                list.add(album);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return list;
    }
   
    public ArrayList<Kategori> queryGetCategories(){
        /*
        --query--
        SELECT *
        FROM music_categories;
        */
        ArrayList<Kategori> list = new ArrayList<>();
        try{
            CallableStatement stmt = con.prepareCall("{ musicsite_p_getCategories() }");
            stmt.executeQuery();
            ResultSet rs = stmt.getResultSet();
            while(rs.next()){
                Kategori kat = new Kategori();
                kat.setId(rs.getInt("id"));
                kat.setName(rs.getString("name"));
                list.add(kat);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return list;
    }
    
    public ArrayList<Låt> queryGetAlbumTracks(int recording_id){
        /*
        --query--
        SELECT * 
        FROM music_tracks 
        WHERE recording_id = p_recording_id;
        */
        ArrayList<Låt> list = new ArrayList<>();
        try{
            CallableStatement stmt = con.prepareCall("{ musicsite_p_getAlbumTracks('" + recording_id + "') }");
            stmt.executeQuery();
            ResultSet rs = stmt.getResultSet();
            while(rs.next()){
                Låt låt = new Låt();
                låt.setId(rs.getInt("id"));
                låt.setTitle(rs.getString("title"));
                låt.setRecordingId(rs.getInt("recording_id"));
                låt.setDuration(rs.getLong("duration"));
                list.add(låt);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return list;
    }
    
    
    
}
