package de.hhbk.listeners;

import de.hhbk.managers.DatabaseManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class DatabaseListener implements ServletContextListener {
    /**
     * @param event ServletEvent 
     * Bildet eine permanente Instanz des DatabaseManager's welche in einer einzelnen Sitzung des Servers immer benutzt werden kann
     */
    public void contextInitialized(ServletContextEvent event) {
        ServletContext ctx = event.getServletContext();

        DatabaseManager manager = new DatabaseManager(ctx.getInitParameter("database.IP"), ctx.getInitParameter("database.PORT"), ctx.getInitParameter("database.USERNAME"), ctx.getInitParameter("database.PASSWORD"), ctx.getInitParameter("database.STAGE"));
        ctx.setAttribute("DB", manager);

        System.out.println("Database Manager intiliazed.");
    }

    /**
     * @param event ServletEvent 
     * Beendet die permanente Instanz des DatabaseManager's wenn der Server stoppt
     */
    public void contextDestroyed(ServletContextEvent event) {
        ServletContext ctx = event.getServletContext();

        DatabaseManager manager = (DatabaseManager) ctx.getAttribute("DB");
        manager.close();

        System.out.println("Database Manager stopped.");
    }
}
