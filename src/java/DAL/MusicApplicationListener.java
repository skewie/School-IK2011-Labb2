/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author h11jafva
 */
public class MusicApplicationListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        
        try {
            DBConnector dbc = new DBConnector();
            context.setAttribute(DBConnector.ATTRIBUTE_NAME, dbc);
        } catch (Exception e) {
            context.log("FELMEDDELANDE: "+e.getClass()+": "+e.getMessage()+"\n"+e.getStackTrace()[0].getFileName()+" at method " + e.getStackTrace()[0].getMethodName());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        try {
            DBConnector dbc = (DBConnector)context.getAttribute(DBConnector.ATTRIBUTE_NAME);
            dbc.disconnect();
        } catch (Exception e) {
            context.log("FELMEDDELANDE: "+e.getClass()+": "+e.getMessage()+"\n"+e.getStackTrace()[0].getFileName()+" at method " + e.getStackTrace()[0].getMethodName());
        }
    }
    
}
