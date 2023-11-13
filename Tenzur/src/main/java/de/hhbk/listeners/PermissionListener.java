package de.hhbk.listeners;

import de.hhbk.managers.AuthorizationManager;
import de.hhbk.managers.PermissionManager;
import org.jose4j.lang.JoseException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class PermissionListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext ctx = event.getServletContext();

        PermissionManager manager = new PermissionManager();
        ctx.setAttribute("Perm", manager);

        System.out.println("Permission Manager intiliazed.");
    }
}
