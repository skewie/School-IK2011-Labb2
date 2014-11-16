/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author h11jafva
 */
public abstract class View {
    
    private HashMap<String, ArrayList<Object>> hm; //TODO: Ändra arraylistans mottagande objekt-typ
    private final String DOCTYPE =  "<!DOCTYPE html>";
    private String styleSheetFileName = "";
    
    public String getHtml() {
        return
                DOCTYPE+"\n"+
                "<html lang=\"se\">" +
                getHeader()+"\n"+
                getBody()+"\n"+
                getFooter() +
                "</html>";
    }
    
    protected abstract String getHeader();
    
    protected abstract String getBody();
    
    protected abstract String getFooter();
    
    public void setStyleSheet(String fileName) {
        this.styleSheetFileName = fileName;
    }
    
    public String getStyleSheetLine() {
        return "<link type=\"text/css\" rel=\"stylesheet\" href=\".../styles/"+ this.styleSheetFileName +"\"></link>";
    }
    
    //TODO: Ändra arraylistans mottagande objekt-typ
    private void setMap(String key,ArrayList<Object> val){
        hm.put(key, val);
    }
    
    /*private HashMap<> getMap(){
        
    }*/
}
