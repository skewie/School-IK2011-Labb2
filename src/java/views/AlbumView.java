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
                    "   <a href=\"./MusikServlet\"><nav>\n" +
            "       <div><< Back</div>\n" +
            "	</nav></a>\n" +
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
            html = html+ "<tr>\n" +
                "	<td>\n" +
                            låt.getId() + "\n" +
                "	</td>\n" +
                "	<td>\n" +
                            låt.getTitle() + "\n" +
                "	</td>\n" +
                "	<td>\n" +
                            låt.getDuration() + "\n" +
                "	</td>\n" +
                "</tr>";
        }
        
        html = html+
                "       </table>\n" +
            "	</section>";
        
        return html;
    }
}
