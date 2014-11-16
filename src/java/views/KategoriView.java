/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.util.ArrayList;
import model.Album;

/**
 *
 * @author h11jafva
 */
public class KategoriView extends View {
    
    private ArrayList<Album> albums = null;
    
    public KategoriView(ArrayList<Album> albums) {
        this.albums = albums;
    }

    @Override
    public String getHtml() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
