/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import model.Album;
import javax.servlet.http.HttpSession;

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
                "   <a href=\"./MusikServlet\"><nav>\n" +
            "       <div><< Back</div>\n" +
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
                        "	<td style=\"border-top-left-radius: 3px;border-bottom-left-radius: 3px;\">\n" +
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
                        "	</td>" +
                        "       <td style=\"background: #BBFFBB;border-top-right-radius: 3px;border-bottom-right-radius: 3px;\">\n" +
                        "           <a href=\"OrderStatusServlet?recid="+album.getRecordingId()+"\">Lägg till i kundvagn</a> " +
                        "       </td>\n" +
                        "</tr>";
        }
        
        html = html+
            "<tr>\n" +
"	<td style=\"border: none\"></td>\n" +
"	<td style=\"border: none\"></td>\n" +
"	<td style=\"border: none\"></td>\n" +
"	<td style=\"border: none\"></td>\n" +
"	<td style=\"border: none\"></td>\n" +
"	<td style=\"border: none\"></td>\n" +
"	<td style=\"border: none\"></td>\n" +
"	<td style=\"background: #cecece;\">\n" +
"		<a href=\"OrderStatusServlet\">Till min kundvagn</a>\n" +
"	</td>\n" +
"</tr>"+
            "       </table>\n" +
            "	</section>";
        
        return html;
    }
    
    
}
