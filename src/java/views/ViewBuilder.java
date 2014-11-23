/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.KundkorgsRad;
import views.partial.FooterPartialView;
import views.partial.HeaderPartialView;
import views.partial.PartialView;

/**
 *
 * @author Jeff
 */
public class ViewBuilder {

    private final ArrayList<String> styleSheetPaths = new ArrayList();
    private final ArrayList<View> views = new ArrayList();

    public ViewBuilder(View view) {
        if (view.getStyleSheetPath() != null) {
            addStyleSheet(view.getStyleSheetPath());
        }

        addPartialView(new HeaderPartialView());
        views.add(view);
        addPartialView(new FooterPartialView());
    }

    public ViewBuilder(View view, HttpSession session) {
        if (view.getStyleSheetPath() != null) {
            addStyleSheet(view.getStyleSheetPath());
        }

        String userName = (String) session.getAttribute("user");
        if (userName != null) {
            userName = userName.substring(0, 1).toUpperCase() + userName.substring(1); // Gör första bokstaven till en versal.

            ArrayList<KundkorgsRad> cart = (ArrayList) session.getAttribute(KundkorgsRad.ATTRIBUTE_NAME);

            if (cart != null) {
                addPartialView(new HeaderPartialView(userName, KundkorgsRad.getCartTotalItems(cart)));
            } else {
                addPartialView(new HeaderPartialView(userName));
            }
        } else
            addPartialView(new HeaderPartialView());

        views.add(view);
        addPartialView(new FooterPartialView());
    }

    public String buildPage(String title) {
        String html = View.DOCTYPE + "\n<html lang=\"se\">";
        html = html + buildHead(title);

        for (View v : views) {
            html = html + v.getHtml();
        }
        html = html + "</html>";

        return html;
    }

    private String buildHead(String title) {

        StringBuilder build = new StringBuilder();

        build.append("<head>\n");
        build.append("<title>");
        build.append(title);
        build.append("</title>");
        build.append(printStyleSheetList());
        build.append(printScriptList());
        build.append("</head>\n");

        return build.toString();
    }

    private String printStyleSheetList() {

        String html = "";

        for (String path : styleSheetPaths) {

            html = html + "<link href=\"" + path + "\" type=\"text/css\" rel=\"styleSheet\" />\n";

        }

        return html;
    }

    private String printScriptList() {
        return "";
    }

    public final void addStyleSheet(String pathToStyleSheet) {
        styleSheetPaths.add(pathToStyleSheet);
    }

    public final void addPartialView(PartialView view) {
        views.add(view);
    }
}
