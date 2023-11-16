package de.hhbk.listeners;

import de.hhbk.managers.AuthorizationManager;
import org.jose4j.lang.JoseException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AuthorizationListener implements ServletContextListener {
    /**
     * @param event ServletEvent 
     * Bildet eine permanente Instanz des AuthorizationManager's welche in einer einzelnen Sitzung des Servers immer benutzt werden kann
     */
    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext ctx = event.getServletContext();
        AuthorizationManager manager = null;

        try {
            manager = new AuthorizationManager();
        } catch (JoseException e) {
            throw new RuntimeException(e);
        }
        ctx.setAttribute("Auth", manager);

        System.out.println("Authorization Manager intiliazed.");
    }
}
