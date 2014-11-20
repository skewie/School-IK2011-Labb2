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
import model.User;

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
    public static String AttributeName = "MusicSite.DAL.DBConnector";
    
    private Connection con = null;
    
    public DBConnector() throws ClassNotFoundException, SQLException {
        connect();
    }
    
    private void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.mariadb.jdbc.Driver"); //driver - Vi använder en extern MariaDB server, därför behövs denna driver istället.
        String url = "jdbc:mariadb://jval.synology.me/ik2011_labb2"; //server/db
        String username = "school"; //username
        String pass = "TobJaf"; //pass
        con = DriverManager.getConnection(url, username, pass);
    }
    
    public void disconnect() throws SQLException {
        this.con.rollback();
        this.con.close();
    }
    
    /**
     * Hämtar all data beroende på kategori id
     * @param categoryId id som identifierar kategori
     * @return arraylista av typ: album
     */
    public ArrayList<Album> queryCategoryAlbums(int categoryId) throws SQLException {
        ArrayList<Album> list = new ArrayList<>();
        //kallar på lagrad procedur
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
        return list;
    }
   
    public ArrayList<Kategori> queryGetCategories() throws SQLException {
        /*
        --query--
        SELECT *
        FROM music_categories;
        */
        ArrayList<Kategori> list = new ArrayList<>();
        CallableStatement stmt = con.prepareCall("{ call musicsite_p_getCategories() }");
        stmt.executeQuery();
        ResultSet rs = stmt.getResultSet();
        while(rs.next()){
            Kategori kat = new Kategori();
            kat.setId(rs.getInt("id"));
            kat.setName(rs.getString("name"));
            list.add(kat);
        }
        
        return list;
    }
    
    public ArrayList<Låt> queryGetAlbumTracks(int recording_id) throws SQLException {
        /*
        --query--
        SELECT * 
        FROM music_tracks 
        WHERE recording_id = p_recording_id;
        */
        ArrayList<Låt> list = new ArrayList<>();
        
        CallableStatement stmt = con.prepareCall("{ call musicsite_p_getAlbumTracks('" + recording_id + "') }");
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
            
        return list;
    }
    
    public void queryAddCartSession(String session_id, String user_name) throws SQLException{
        /*
        -- query --
                BEGIN

                    DECLARE CheckExists INTEGER;
                    SET CheckExists = 0;

                    SELECT 1 INTO CheckExists
                    FROM kundvagn
                    WHERE session_id = p_session_id
                    LIMIT 1;

                    IF (CheckExists = 0) 
                    THEN
                        INSERT INTO kundvagn(session_id, user_name)
                        VALUES(p_session_id, p_user_name);
                    END IF;

                END;
        */
        CallableStatement stmt = con.prepareCall(
                "{ call musicsite_p_addCartSession('"+ session_id +"', '"+ user_name +"') }");
        stmt.executeQuery();
    }
    
    public ArrayList<Album> queryGetAlbumCart(String session_id) throws SQLException{
        ArrayList<Album> list = new ArrayList<>();
        CallableStatement stmt = con.prepareCall("{ call musicsite_p_getAlbumCart('"+ session_id +"') }");
        stmt.executeQuery();
        ResultSet rs = stmt.getResultSet();
        while(rs.next()){
            Album album = new Album();
            album.setArtist(rs.getString(1));
            album.setTitle(rs.getString(2));
            album.setStockCount(rs.getInt(3));
            list.add(album);
        }
        return list;
    }
    
    public ArrayList<Låt> queryGetTrackCart(String session_id) throws SQLException{
        ArrayList<Låt> list = new ArrayList<>();
        CallableStatement stmt = con.prepareCall("{ call musicsite_p_getTrackCart('"+ session_id +"') }");
        stmt.executeQuery();
        ResultSet rs = stmt.getResultSet();
        while(rs.next()){
            Låt låt = new Låt();
            låt.setArtist(rs.getString(1));
            låt.setTitle(rs.getString(2));
            låt.setStock(rs.getInt(3));
            list.add(låt);
        }
        return list;
    }
    
    
    ////////////////////////////////////////////////////////////////////////////
    /////////// SAKER SOM INTE ÄR KLARA ÄN                           ///////////
    ////////////////////////////////////////////////////////////////////////////
    
    //TODO: prosedur saknas!! måste troligen ändras
    public void queryAddAlbumToCart(String session_id, String recording_id, int amount) throws SQLException{
        CallableStatement stmt = con.prepareCall(
                "{ call musicsite_p_addAlbumToCart('"+ session_id +"', '"+ recording_id +"', '"+ amount +"') }");
        stmt.executeQuery();
    }
    
    //TODO: prosedur inte skapad än!! kräver även MD5 kryptering tror jag!
    public int queryLogin(String username, String password) throws SQLException{
        /*
        -- query --
        SELECT user_name, user_pass
        FROM users
        WHERE user_name = p_username
        AND
        user_pass = p_userpass;
        */
        int check = 0;
        CallableStatement stmt = con.prepareCall("{ call musicsite_p_login('" + username + "', '" + password + "') }");
        stmt.executeQuery();
        ResultSet rs = stmt.getResultSet();
        //resultset returnerar 1 eller 0 beroende på om det kommer tillbaka
        //ett svar som uppfyller queryn (select). 1 = finns, 0 = finns inte.
        rs.last();
        check = rs.getRow();
        return check;
    }
    
    //TODO: prosedur inte skapad än!! 
    public User queryLoginGetUser(String username, String password) throws SQLException{
        /*
        -- query --
            SELECT id, role_name, user_name
            FROM user_roles
            WHERE user_name = p_user_name;
        */
        User user = new User();
        CallableStatement stmt = con.prepareCall("{ call musicsite_p_loginGetUser() }");
        stmt.executeQuery();
        ResultSet rs = stmt.getResultSet();
        while(rs.next()){
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("user_name"));
            user.setRole(rs.getString("role_name"));
        }
        return user;
    }
    
    @Override
    public String toString() {
        return "Testar"; //To change body of generated methods, choose Tools | Templates.
    }
    
}
