/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.util.ArrayList;
import model.Kategori;

/**
 *
 * @author h11jafva
 */
public class MusikView extends View {
    
    private ArrayList<Kategori> cats; // Mjau :)
    
    public MusikView(ArrayList<Kategori> categories){
       this.cats = categories;
    }
    
    public MusikView(ArrayList<Kategori> categories, String styleSheetPath) {
        this.cats = categories; // Mjau :D
        super.setStyleSheetPath(styleSheetPath);
    }

    @Override
    public String getHtml() {
        String html = 
            "   <nav>\n" +
            "       <a href=\"./\"><< Back To Index</a>\n" +
            "	</nav>\n" +
            "	<section id=\"content\">\n" +
            "       <table id=\"music\">\n" +
            "           <tr>\n"+
            "           <th>\n" +
            "               Välj musikkategori:\n" +
            "           </th>\n"+
            "           </tr>\n";
        
        for (Kategori cat : this.cats) {
            html = html+ "<tr><td><a href=\"KategoriServlet?catid="+ cat.getId() +"\">"+cat.getName()+"</a></td></tr>";
        }
        
        html = html+
            "       </table>\n" +
            "	</section>";
        
        return html;
    }
    
}
