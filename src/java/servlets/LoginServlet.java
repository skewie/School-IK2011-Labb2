/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import DAL.DBConnector;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Toppe
 */
public class LoginServlet extends HttpServlet {
    
    private ArrayList<String> list;
    
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
        
        String user = request.getParameter("username");
        String pass = request.getParameter("password");
        //System.out.println("USER: " + user);
        //System.out.println("PASS: " + pass);
        
        
            try {
                MessageDigest md =  MessageDigest.getInstance("MD5");
                md.update(pass.getBytes());
                
                byte[] databytes = md.digest();
                
                //convert the byte to hex format method 1
                StringBuilder sb = new StringBuilder();
                for(int i = 0; i < databytes.length; i++){
                    sb.append(Integer.toString((databytes[i] & 0xff) + 0x100, 16).substring(1));
                }
                
                list = new ArrayList<>();
                try{
                    list = dbc.queryLogin(user, pass);
                    //kollar om MD5 och username är rätt eller ej
                    if(sb.toString().equals(list.get(1)) && user.equals(list.get(0))){ 
                        HttpSession session = request.getSession();
                        session.setAttribute("user", list.get(0));
                        response.sendRedirect("MusikServlet");
                    }else{
                        response.sendRedirect("inloggningssida.html");
                    }
                }catch(SQLException e){
                    System.out.println(e.getMessage());
                }
                
                
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
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
