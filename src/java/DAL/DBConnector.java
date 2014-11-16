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
        connectToDB();
    }
    
    private void connectToDB(){
        try{
            Class.forName("com.mysql.jdbc.Driver"); //driver
            String url = "jdbc:mysql://jval.synology.me.phpMyAdmin/ik2011_labb2"; //server/db
            String username = ""; //username
            String pass = ""; //pass
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
    public ArrayList<Album> queryAlbums(int categoryId) {
        ArrayList<Album> list = new ArrayList<>();
        //kallar på lagrad procedur
        try{
            //TODO: procedure är inte skapad i databas än!!
            CallableStatement stmt = con.prepareCall("{ call musicsite_p_getAlbums('" + categoryId + "') }");
            stmt.executeQuery();
            ResultSet rs = stmt.getResultSet();
            while(rs.next()){
                Album album = new Album();
                album.setArtist(rs.getString("recording_id"));
                album.setTitle(rs.getString("artist_name"));
                album.setCategory(rs.getString("category"));
                album.setImageFileName(rs.getString("image_name"));
                album.setTrackCount(rs.getInt("num_tracks"));
                album.setPrice(rs.getInt("price"));
                album.setStockCount(rs.getInt("stock_count"));
                //lägger till list
                list.add(album);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return list;
    }
}
