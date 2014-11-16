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
    
    public HeaderPartialView(String userName) {
        
        this.userName = userName;
        
    }

    public HeaderPartialView() {
    }

    @Override
    public String getHtml() {
        if (this.userName == null)
            return "<h1>Välkommen till MusicSite!</h1>";
        else
            return "<h1>Välkommen till MusicSite, "+this.userName+"!</h1>";
    }
}
