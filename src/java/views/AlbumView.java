/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.util.ArrayList;
import model.Låt;

/**
 *
 * @author h11jafva
 */
public class AlbumView extends View {

    private ArrayList<Låt> låt = null;
    private int recid = 0;
    public AlbumView(ArrayList<Låt> låt, int recid) {
        this.låt = låt;
        this.recid = recid;
    }
    
    public AlbumView(ArrayList<Låt> låt, int recid, String styleSheetPath){
        this.låt = låt;
        this.recid = recid;
        super.setStyleSheetPath(styleSheetPath);
    }
    
    @Override
    public String getHtml() {
        String html = 
            "	<section id=\"content\">\n" +
            "       <table id=\"music\">\n" +
                "<tr>\n" +
                "	<th>\n" +
                "		ID\n" +
                "	</th>\n" +
                "	<th>\n" +
                "		Titel\n" +
                "	</th>\n" +
                "	<th>\n" +
                "		Längd(sek)\n" +
                "	</th>\n" +
                "</tr>\n"
                ;
        
        for(Låt låt : this.låt){
            html = html+ "  <tr>\n" +
                "	<td style=\"border-top-left-radius: 3px;border-bottom-left-radius: 3px;\">\n" +
                            låt.getId() + "\n" +
                "	</td>\n" +
                "	<td>\n" +
                            låt.getTitle() + "\n" +
                "	</td>\n" +
                "	<td>\n" +
                            låt.getDuration() + "\n" +
                "	</td>\n" +
                "   </tr>";
        }
        
        html = html+
                            "<tr>\n" +
"	<td style=\"border: none\"></td>\n" +
"	<td style=\"background: #cecece;\"><a href=\"OrderStatusServlet?addItem="+this.recid+"\">Lägg till i kundvagn</a></td>\n" +
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
