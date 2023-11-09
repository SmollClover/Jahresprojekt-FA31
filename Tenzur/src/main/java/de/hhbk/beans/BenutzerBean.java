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
        this.loadItemList();
    }

    public void loadItemList() {
        DatabaseManager DB = (DatabaseManager) ctx.getAttribute("DB");
        Session session = DB.getSessionFactory().openSession();
        this.itemList = new Benutzer().getList(session);
        session.close();
    }

    public Collection<Benutzer> getItemList() {
        return itemList;
    }

    public Benutzer getItem() {
        return item;
    }

    public void setItem(Benutzer item) {
        this.item = item;
    }

    public void resetItem() {
        this.item = new Benutzer();
    }

    public void addItem() {
        if (this.itemList.contains(this.item)) return;

        DatabaseManager DB = (DatabaseManager) ctx.getAttribute("DB");
        Session session = DB.getSessionFactory().openSession();
        this.item.save(session);
        session.close();

        this.itemList.add(this.item);
        this.resetItem();
    }

    public void removeItem(Benutzer item) {
        this.itemList.remove(this.item);
    }
}