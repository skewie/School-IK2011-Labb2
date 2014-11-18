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
    
    private ArrayList<Album> albums;
    
    public KategoriView(ArrayList<Album> album) {
        this.albums = album;
    }
    
    public KategoriView(ArrayList<Album> album, String styleSheetPath){
        this.albums = album;
        super.setStyleSheetPath(styleSheetPath);
    }

    @Override
    public String getHtml() {
        String html = 
                "   <a href=\"./\"><nav>\n" +
            "       << Back\n" +
            "	</nav></a>\n" +
            "	<section id=\"content\">\n" +
            "       <table id=\"music\">\n" +
            "           <tr>\n" +
                        "	<th>\n" +
                        "		Artist\n" +
                        "	</th>\n" +
                        "	<th>\n" +
                        "		Titel\n" +
                        "	</th>\n" +
                        "	<th>\n" +
                        "		Kategori\n" +
                        "	</th>\n" +
                        "	<th>\n" +
                        "		Bild\n" +
                        "	</th>\n" +
                        "	<th>\n" +
                        "		# Låtar\n" +
                        "	</th>\n" +
                        "	<th>\n" +
                        "		Pris\n" +
                        "	</th>\n" +
                        "	<th>\n" +
                        "		Lager\n" +
                        "	</th>\n" +
                        "</tr>";
        
        for(Album album : this.albums){
            html = html+ "<tr>\n" +
                        "	<td>\n" +
                                    album.getArtist()+ "\n" +
                        "	</td>\n" +
                        "	<td>\n" +
                                    "<a href=\"AlbumServlet?recid="+ album.getRecordingId() +"\">" + album.getTitle() + "</a>\n" +
                        "	</td>\n" +
                        "	<td>\n" +
                                    album.getCategory() + "\n" +
                        "	</td>\n" +
                        "	<td>\n" +
                                    "<img src=\""+ album.getImageFileName() +"\"/>" + "\n" +
                        "	</td>\n" +
                        "	<td>\n" +
                                    album.getTrackCount() + "\n" +
                        "	</td>\n" +
                        "	<td>\n" +
                                    album.getPrice() + "\n" +
                        "	</td>\n" +
                        "	<td>\n" +
                                    album.getStockCount() + "\n" +
                        "	</td>\n" +
                        "</tr>";
        }
        
        html = html+
            "       </table>\n" +
            "	</section>";
        
        return html;
    }
    
    
}
