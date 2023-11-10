package de.hhbk.beans;

import de.hhbk.entities.EntityTemplate;
import de.hhbk.managers.DatabaseManager;
import org.hibernate.Session;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;

public class BeanTemplate<T extends EntityTemplate> implements Serializable {
    private Class<T> clazz = null;
    @Inject
    ServletContext ctx;
    private Collection<T> itemList = new ArrayList<T>();
    private T item = null;

    public BeanTemplate() {}

    public BeanTemplate(Class<T> clazz) {
        super();
        this.clazz = clazz;
    }

    public void loadItemList() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        DatabaseManager DB = (DatabaseManager) this.ctx.getAttribute("DB");
        Session session = DB.getSessionFactory().openSession();
        this.itemList = clazz.getDeclaredConstructor().newInstance().getList(session);
        session.close();
    }

    public Collection<T> getItemList() {
        return this.itemList;
    }

    public T getItem() {
        return this.item;
    }

    public void setItem(T item) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        this.item = item;
    }

    public void resetItem() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        this.item = clazz.getDeclaredConstructor().newInstance();
    }

    public void addItem() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if (this.itemList.contains(this.item)) return;

        DatabaseManager DB = (DatabaseManager) ctx.getAttribute("DB");
        Session session = DB.getSessionFactory().openSession();
        this.item.save(session);
        session.close();

        this.itemList.add(this.item);
        this.resetItem();
    }

    public void removeItem(T item) {
        this.itemList.remove(this.item);
    }

    protected void setMessage(String comonentId, FacesMessage.Severity type, String header, String msg) {
        FacesContext.getCurrentInstance().addMessage(comonentId, new FacesMessage(type, header, msg));
    }

    protected void setErrorMessage(String header, String msg) {
        setMessage(null, FacesMessage.SEVERITY_ERROR, header, msg);
    }

    protected void setInfoMessage(String header, String msg) {
        setMessage(null, FacesMessage.SEVERITY_INFO, header, msg);
    }
}
