/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * En enkel mall för sessions lyssnaren. Mall från MKyong:
 * http://www.mkyong.com/servlet/a-simple-httpsessionlistener-example-active-sessions-counter/
 * @author h11tobbu
 */
public class SessionListener implements HttpSessionListener{

    private static int activeSessions;
    
    public static int getActiveSessions(){
        return activeSessions;
    }
    
    
    @Override
    public void sessionDestroyed(HttpSessionEvent arg0){
        arg0.getSession().invalidate();
        activeSessions--;
        System.out.println("Session destroyed! sessions now active: " + activeSessions);
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        activeSessions++;
        System.out.println("Session Created! sessions now active: " + activeSessions);
    }
    
}
