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

    @Override
    protected String getHeader() {
        setStyleSheet("musicservlet.css");
        String html = 
                "<head>\n"+
                getStyleSheetLine() + "\n" +
                "</head>\n";
        return html;
    }

    @Override
    protected String getBody() {
        String html = 
                "<nav>\n" +
"		<a href=\"../\"><< Back To Index</a>\n" +
"	</nav>\n" +
"	<section id=\"content\">\n" +
"		<table id=\"music\">\n" +
"			<th>\n" +
"				Genrer\n" +
"			</th>\n" +
"		</table>\n" +
"	</section>";
        
        return html;
    }

    @Override
    protected String getFooter() {
        String html = "<footer>I'm a footer, scratch me, I'm itchy</footer>";
        return html;
    }
    
}
