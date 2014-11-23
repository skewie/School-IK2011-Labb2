/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import DAL.DBConnector;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Album;
import model.KundkorgsRad;
import model.Låt;
import views.OrderStatusView;
import views.ViewBuilder;

/**
 *
 * @author h11jafva
 */
public class OrderStatusServlet extends HttpServlet {
    
    int i = 0;
    //private String recid = "";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getSession().getAttribute("user") != null) {
            response.setContentType("text/html;charset=UTF-8");
            
            ServletContext context = getServletConfig().getServletContext();
            DBConnector dbc = (DBConnector) context.getAttribute(DBConnector.ATTRIBUTE_NAME);

            //Kollar om session finns i DB, annars skapar ny
            //Skapar tabell för kundvagnen om den inte finns med sessionsid och
            //användarnamns
            try {
                dbc.queryAddCartSession(request.getSession().getId(), "bert");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            //Denna metod dubbellagrar data i databases pga. sidan laddas om.
            //borde läggas sepparat från Servlet men vet inte hur.
            /*
             try{
             //amount är för nuvarande hårdkodat
             dbc.queryAddAlbumToCart(request.getSession().getId(), Integer.parseInt(request.getParameter("recid")), 1); 
             }catch(SQLException e){
             System.out.println(e.getMessage());
             }
             */
            PrintWriter out = response.getWriter();
            try {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Kundkorg</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Kundkorg</h1>");
                out.println("<a href=\"MusikServlet\" ><< Bakåt</a>");
                out.println(i);
                i++;
                out.println("<table>");
                try {
                    //test id: "9567d3ba6e8e7fc9e10cf766f51c"
                    //test id: "test"
                    //Det som ska användas egentligen: request.getSession().getId()
                    for (Album album : dbc.queryGetAlbumCart(request.getSession().getId())) {
                        out.println("<tr>");
                        out.println("<td>Album</td>");
                        out.println("<td>" + album.getArtist() + "</td>");
                        out.println("<td>" + album.getTitle() + "</td>");
                        out.println("<td>" + album.getStockCount() + "</td>");
                        out.println("</tr>");
                    }
                    for (Låt låt : dbc.queryGetTrackCart(request.getSession().getId())) {
                        out.println("<tr>");
                        out.println("<td>Låt(ar)</td>");
                        out.println("<td>" + låt.getArtist() + "</td>");
                        out.println("<td>" + låt.getTitle() + "</td>");
                        out.println("<td>" + låt.getStock() + "</td>");
                        out.println("</tr>");
                    }
                } catch (SQLException e) {
                    out.println(e.getMessage());
                }
                out.println("</table>");
                out.println("</body>");
                out.println("</html>");
            } catch (Exception e) {
                out.println("<b>Typ:</b><br>");
                out.println(e.getClass() + " - " + e.getMessage() + "<br><br>");
                out.println("<b>StackTrace:</b><br>");
                for (StackTraceElement ste : e.getStackTrace()) {
                    out.println(ste.toString() + "<br>");
                }
            }
        } else {
            response.sendRedirect("inloggningssida.html");
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<KundkorgsRad> items = (ArrayList) request.getSession().getAttribute(KundkorgsRad.ATTRIBUTE_NAME);
        String recordingId = (String) request.getParameter("addItem");
        if (recordingId != null) {
            
            DBConnector dbc = (DBConnector) request.getServletContext().getAttribute(DBConnector.ATTRIBUTE_NAME);
            if (items != null) {
                
                if (inCart(Integer.parseInt(recordingId), items)) {
                    increaseItemAmount(Integer.parseInt(recordingId), items);
                } else {
                    try {
                        items.add(new KundkorgsRad(dbc.queryAlbum(Integer.parseInt(recordingId))));
                    } catch (Exception e) {
                        response.sendRedirect(request.getHeader("referer"));
                    }
                    
                }
            } else {
                items = new ArrayList();
                try {
                    items.add(new KundkorgsRad(dbc.queryAlbum(Integer.parseInt(recordingId))));
                } catch (Exception e) {
                    response.sendRedirect(request.getHeader("referer"));
                }
            }
            
            request.getSession().setAttribute(KundkorgsRad.ATTRIBUTE_NAME, items);
            
            response.sendRedirect(request.getHeader("referer"));
            
        } else {
            if (request.getSession().getAttribute("user") != null) {
                PrintWriter out = response.getWriter();
                
                out.println(new ViewBuilder(new OrderStatusView(items, "./styles/musicservlet.css"), request.getSession()).buildPage("Din kundkorg"));
            } else {
                response.sendRedirect("inloggningssida.html");
                
            }
        }
    }
    
    private boolean inCart(int recordingId, ArrayList<KundkorgsRad> cart) {
        for (KundkorgsRad row : cart) {
            if (row.getAlbum().getRecordingId() == recordingId) {
                return true;
            }
        }
        
        return false;
    }
    
    private void increaseItemAmount(int recordingId, ArrayList<KundkorgsRad> cart) {
        for (KundkorgsRad row : cart) {
            if (row.getAlbum().getRecordingId() == recordingId) {
                row.increaseAmount();
            }
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
