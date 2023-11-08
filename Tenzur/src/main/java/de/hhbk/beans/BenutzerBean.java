package de.hhbk.beans;

import de.hhbk.entities.Benutzer;
import de.hhbk.managers.DatabaseManager;
import org.hibernate.Session;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.Collection;

@Named(value = "benutzer")
@SessionScoped
public class BenutzerBean extends BeanTemplate {
    @Inject
    private ServletContext ctx;
    private Collection<Benutzer> benutzer = new ArrayList<Benutzer>();

    public Collection<Benutzer> getUsers() {
        return benutzer;
    }

    // @ManagedProperty("#{facesContext.externalContext.context}")
    private ServletContext servletContext;

    public BenutzerBean() {
        super();
    }

    @PostConstruct
    public void init() {
        DatabaseManager DB = (DatabaseManager) ctx.getAttribute("DB");
        Session session = DB.getSessionFactory().openSession();
        this.benutzer = new Benutzer().getAll(session);
        session.close();
    }

    public String test() {
        return "Test String";
    }
}