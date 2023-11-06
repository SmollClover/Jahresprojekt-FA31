package de.hhbk.controllers;

import de.hhbk.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Properties;

public class DatabaseController implements ServletContextListener {
    public static Session session;
    private static SessionFactory sessionFactory;

    public DatabaseController() {

    }

    private static Properties getProperties() {
        Properties sessionProperties = new Properties();

        sessionProperties.setProperty("hibernate.auto_quote_keyword", "true");
        sessionProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        sessionProperties.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        sessionProperties.setProperty("hibernate.connection.url", String.format("jdbc:postgresql://%s:%s/tenzur", System.getenv("DATABASE_IP"), System.getenv("DATABASE_PORT")));
        sessionProperties.setProperty("hibernate.connection.username", System.getenv("DATABASE_USERNAME"));
        sessionProperties.setProperty("hibernate.connection.password", System.getenv("DATABASE_PASSWORD"));
        sessionProperties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        sessionProperties.setProperty("show_sql", "true");
        sessionProperties.setProperty("format_sql", "true");
        sessionProperties.setProperty("use_sql_comments", "true");

        return sessionProperties;
    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        try {
            sessionFactory = new Configuration()
                    .setProperties(getProperties())
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();

            session = sessionFactory.openSession();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        try {
            session.close();
            sessionFactory.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
