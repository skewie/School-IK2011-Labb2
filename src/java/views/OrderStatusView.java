/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.text.NumberFormat;
import java.util.ArrayList;
import model.Album;
import model.KundkorgsRad;

/**
 *
 * @author h11jafva
 */
public class OrderStatusView extends View {
    
    private ArrayList<KundkorgsRad> cartRows = null;

    public OrderStatusView() {
    }

    public OrderStatusView(ArrayList<KundkorgsRad> cartRows) {
        
        this.cartRows = cartRows;
    }
    
    public OrderStatusView(ArrayList<KundkorgsRad> cartRows, String styleSheetPath) {
        
        this.cartRows = cartRows;
        super.setStyleSheetPath(styleSheetPath);
        
    }
    
    
    @Override
    public String getHtml() {
       String html = 
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
                        "		Antal\n" +
                        "	</th>\n" +
                        "	<th>\n" +
                        "		Radsumma\n" +
                        "	</th>\n" +
                        "</tr>";
        
       
        if (cartRows == null) {
            html += "<tr><td colspan=\"9\">Din kundvagn är tom.</td></tr>";
        } else
        for(KundkorgsRad row : this.cartRows){
            Album album = row.getAlbum();
            
            
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
                                    NumberFormat.getCurrencyInstance().format(album.getPrice()) + "\n" +
                        "	</td>\n" +
                        "	<td>\n" +
                                    row.getAmount() + "\n" +
                        "	</td>" +
                        "       <td style=\"background: #BBFFBB;border-top-right-radius: 3px;border-bottom-right-radius: 3px;\">\n" +
                                    NumberFormat.getCurrencyInstance().format(row.getAmount() * album.getPrice()) + "\n" +
                        "       </td>\n" +
                        "</tr>";
                    
        }
        
        html = html+
                    "<tr><td colspan=\"7\"></td><td>Summa: "+KundkorgsRad.getCartTotalPrice(cartRows)+"</td></tr>"+
            "   </table>\n" +
            "</section>";
        
        return html;
    }
}
