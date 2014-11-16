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
    
    public final static String DOCTYPE =  "<!DOCTYPE html>";
    private String styleSheetPath = null;
    
    public abstract String getHtml();
    
    public void setStyleSheetPath(String filePath) {
        this.styleSheetPath = filePath;
    }
    
    public String getStyleSheetPath() {
        return this.styleSheetPath;
    }
}