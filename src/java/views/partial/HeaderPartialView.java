/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.partial;

/**
 *
 * @author Jeff
 */
public class HeaderPartialView extends PartialView {
    
    private String userName = null;
    private int cartRows = 0;
    
    public HeaderPartialView(String userName) {
        this.userName = userName;
    }
    
    public HeaderPartialView(String userName, int cartRows) {
        this.userName = userName;
        this.cartRows = cartRows;
    }

    public HeaderPartialView() {
    }

    @Override
    public String getHtml() {
        
        String html = "";
        
        if (this.userName == null)
            html = "<h1><a href=\"MusikServlet\">Välkommen till MusicSite!</a></h1>\n";
        else
            html = "<h1><a href=\"MusikServlet\">Välkommen till MusicSite, "+this.userName+"!</a></h1>\n";
        
        
        String cartString = (cartRows == 0) ? "tom" :
                            (cartRows > 1) ? String.valueOf(cartRows)+" varor" : String.valueOf(cartRows)+" vara";
        
        return html+
            "   <nav>\n" +
            "       <a href=\"LogoutServlet\"><div>Logga Ut</div></a>\n" +
            "       <a href=\"OrderStatusServlet\"><div>Till kundvagnen ("+cartString+")</div></a>\n" +
            "	</nav>\n";
    }
}
