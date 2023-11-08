package de.hhbk.listeners;

import de.hhbk.managers.DatabaseManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class DatabaseListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent event) {
        ServletContext ctx = event.getServletContext();

        DatabaseManager manager = new DatabaseManager(ctx.getInitParameter("database.IP"), ctx.getInitParameter("database.PORT"), ctx.getInitParameter("database.USERNAME"), ctx.getInitParameter("database.PASSWORD"), ctx.getInitParameter("database.STAGE"));
        ctx.setAttribute("DB", manager);

        System.out.println("Database connection initialized for Application.");
    }

    public void contextDestroyed(ServletContextEvent event) {
        ServletContext ctx = event.getServletContext();

        DatabaseManager manager = (DatabaseManager) ctx.getAttribute("DB");
        manager.close();

        System.out.println("Database connection closed for Application.");
    }
}
