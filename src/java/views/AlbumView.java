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

    private ArrayList<Låt> låt;
    
    public AlbumView(ArrayList<Låt> låt) {
        this.låt = låt;
    }
    
    public AlbumView(ArrayList<Låt> låt, String styleSheetPath){
        this.låt = låt;
        super.setStyleSheetPath(styleSheetPath);
    }
    
    @Override
    public String getHtml() {
        String html = 
                    "   <nav>\n" +
            "       <a href=\"./MusikServlet\"><div><< Back</div></a>\n" +
            "       <a href=\"LogoutServlet\"><div>Logga Ut</div></a>\n" +
            "	</nav>\n" +
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
                "       <td style=\"background: #BBFFBB;border-top-right-radius: 3px;border-bottom-right-radius: 3px;\">\n" +
                "           <a href=\"#nogo\">Lägg till i kundvagn</a> " +
                "       </td>\n" +
                "   </tr>";
        }
        
        html = html+
                            "<tr>\n" +
"	<td style=\"border: none\"></td>\n" +
"	<td style=\"border: none\"></td>\n" +
"	<td style=\"border: none\"></td>\n" +
"	<td style=\"background: #cecece;\">\n" +
"		<a href=\"#nogo\">Till min kundvagn</a>\n" +
"	</td>\n" +
"</tr>"+
                "       </table>\n" +
            "	</section>";
        
        return html;
    }
}
