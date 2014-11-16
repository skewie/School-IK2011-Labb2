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
public class MusikView extends View {
    
    public MusikView(){
       
    }
    
    public MusikView(String styleSheetPath) {
        super.setStyleSheetPath(styleSheetPath);
    }

    @Override
    public String getHtml() {
        String html = 
            "   <nav>\n" +
            "       <a href=\"../\"><< Back To Index</a>\n" +
            "	</nav>\n" +
            "	<section id=\"content\">\n" +
            "       <table id=\"music\">\n" +
            "           <th>\n" +
            "               Genrer\n" +
            "           </th>\n" +
            "       </table>\n" +
            "	</section>";
        
        return html;
    }
    
}
