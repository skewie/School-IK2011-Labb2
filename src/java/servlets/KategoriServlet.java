/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import DAL.DBConnector;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Album;
import views.KategoriView;
import views.ViewBuilder;

/**
 *
 * @author h11jafva
 */
public class KategoriServlet extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        try{
            DBConnector dbc = (DBConnector)context.getAttribute(DBConnector.AttributeName);
            ArrayList<Album> album = dbc.queryCategoryAlbums(
                Integer.parseInt(request.getParameter("catid"))); //nyckelvärdet som skickas med, ofta i form av name.
            out.println(new ViewBuilder(new KategoriView(album, "./styles/kategoriservlet.css")).buildPage("Album"));
        }catch(Exception e){
            out.println("<b>Typ:</b><br>");
            out.println(e.getClass()+" - "+e.getMessage()+"<br><br>");
            out.println("<b>StackTrace:</b><br>");
            for (StackTraceElement ste : e.getStackTrace()) {
                out.println(ste.toString()+"<br>");
            }
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
