package de.hhbk.beans;

import de.hhbk.entities.Benutzer;
import de.hhbk.entities.EntityTemplate;
import de.hhbk.entities.Rolle;
import de.hhbk.managers.AuthorizationManager;
import de.hhbk.managers.DatabaseManager;
import org.hibernate.Session;
import org.jose4j.jwt.MalformedClaimException;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class BeanTemplate<T extends EntityTemplate> implements Serializable {
    @Inject
    ServletContext ctx;
    private Class<T> clazz = null;
    private Collection<T> itemList = new ArrayList<T>();
    private T item = null;

    public BeanTemplate() {
    }

    public BeanTemplate(Class<T> clazz, Rolle berechtigung) {
        super();
        this.clazz = clazz;

        try {
            this.checkPermission(berechtigung);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkPermission(Rolle berechtigung) throws IOException, MalformedClaimException {
        if (berechtigung == Rolle.NONE) return;

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> cookies = context.getRequestCookieMap();

        Cookie authCookie = (Cookie) cookies.get("authorization");
        if (authCookie == null) context.redirect(context.getRequestContextPath());
        assert authCookie != null;

        AuthorizationManager manager = (AuthorizationManager) this.ctx.getAttribute("Auth");
        String userID = manager.validateToken(authCookie.getValue());
        if (userID == null) {
            authCookie.setMaxAge(0);
            context.addResponseCookie(authCookie.getName(), null, null);
            context.redirect(context.getRequestContextPath());
        }
        assert userID != null;

        DatabaseManager DB = (DatabaseManager) this.ctx.getAttribute("DB");
        Session session = DB.getSessionFactory().openSession();
        Benutzer benutzer = new Benutzer().getById(session, Long.parseLong(userID));
        session.close();

        if (benutzer == null) {
            authCookie.setMaxAge(0);
            context.addResponseCookie(authCookie.getName(), null, null);
            context.redirect(context.getRequestContextPath());
        }
        assert benutzer != null;

        if (benutzer.getRolle().ordinal() < berechtigung.ordinal()) context.redirect(context.getRequestContextPath());
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

    public void setItem(T item) {
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
