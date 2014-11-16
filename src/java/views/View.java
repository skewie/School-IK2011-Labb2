/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

/**
 *
 * @author h11jafva
 */
public abstract class View {
    
    private final String DOCTYPE =  "<!DOCTYPE html>";
    
    private String styleSheetFileName = "";
    
    public String getHtml() {
        return
                DOCTYPE+"\n"+
                getHeader()+"\n"+
                getBody()+"\n"+
                getFooter();
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
}
