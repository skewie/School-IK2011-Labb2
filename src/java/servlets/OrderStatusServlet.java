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
import javax.servlet.http.HttpSession;
import model.Låt;

/**
 *
 * @author h11jafva
 */
public class OrderStatusServlet extends HttpServlet{

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
        response.setContentType("text/html;charset=UTF-8");
        ServletContext context = getServletConfig().getServletContext();
        DBConnector dbc = (DBConnector)context.getAttribute(DBConnector.AttributeName);
        try (PrintWriter out = response.getWriter()) {
            //Skapar tabell för kundvagnen om den inte finns med sessionsid och
            //användarnamn
            try{
                dbc.queryAddCartSession(request.getSession().getId(), "bert");
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
                
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Kundkorg</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Kundkorg</h1>");
            //session ska skapas när man lägger till saker i kundkorg
            //ska förstöras när man beställt sina varor
            out.println("<table>");
            try{
                //test id: "9567d3ba6e8e7fc9e10cf766f51c"
                //Det som ska användas egentligen: request.getSession().getId()
                for(Album album : dbc.queryGetAlbumCart("9567d3ba6e8e7fc9e10cf766f51c")){ 
                    out.println("<tr>");
                    out.println("<td>Album</td>");
                    out.println("<td>" + album.getArtist() + "</td>");
                    out.println("<td>" + album.getTitle() + "</td>");
                    out.println("<td>" + album.getStockCount() + "</td>");
                    out.println("</tr>");
                }
                for(Låt låt : dbc.queryGetTrackCart("9567d3ba6e8e7fc9e10cf766f51c")){
                    out.println("<tr>");
                    out.println("<td>Låt(ar)</td>");
                    out.println("<td>" + låt.getArtist() + "</td>");
                    out.println("<td>" + låt.getTitle() + "</td>");
                    out.println("<td>" + låt.getStock() + "</td>");
                    out.println("</tr>");
                }
            }catch(SQLException e){
                out.println(e.getMessage());
            }
            out.println("</table>");
            //end
            out.println("</body>");
            out.println("</html>");
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
        processRequest(request, response);
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
