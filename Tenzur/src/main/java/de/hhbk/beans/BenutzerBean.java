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
    private Collection<Benutzer> itemList = new ArrayList<Benutzer>();
    private Benutzer item = null;

    public BenutzerBean() {
        super();
    }

    public Collection<Benutzer> getUsers() {
        return itemList;
    }

    @PostConstruct
    public void init() {
        DatabaseManager DB = (DatabaseManager) ctx.getAttribute("DB");
        Session session = DB.getSessionFactory().openSession();
        this.itemList = new Benutzer().getList(session);
        session.close();
    }

    public String test() {
        return "Test String";
    }

    public String test(Benutzer p) { return "Test String"; }

    public Collection<Benutzer> getItemList() {
        return itemList;
    }

    public Benutzer getItem() {
        return item;
    }
}