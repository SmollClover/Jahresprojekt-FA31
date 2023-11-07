package de.hhbk.managers;

import de.hhbk.entities.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

public class DatabaseManager {
    private SessionFactory sessionFactory;

    public DatabaseManager() {
        try {
            this.sessionFactory = new Configuration()
                    .setProperties(getProperties())
                    .addAnnotatedClass(Ort.class)
                    .addAnnotatedClass(Kontoinhaber.class)
                    .addAnnotatedClass(Konto.class)
                    .addAnnotatedClass(Telefonnummer.class)
                    .addAnnotatedClass(Mietobjekt.class)
                    .addAnnotatedClass(Mieter.class)
                    .addAnnotatedClass(Einnahme.class)
                    .addAnnotatedClass(MietobjektMieter.class)
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void close() {
        try {
            this.sessionFactory.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private Properties getProperties() {
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
}
