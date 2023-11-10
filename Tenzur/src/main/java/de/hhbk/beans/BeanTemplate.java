package de.hhbk.beans;

import de.hhbk.entities.Benutzer;
import de.hhbk.entities.Rolle;
import de.hhbk.managers.AuthorizationManager;
import de.hhbk.managers.DatabaseManager;
import de.hhbk.managers.PermissionManager;
import org.hibernate.Session;
import org.jose4j.jwt.MalformedClaimException;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

public class BeanTemplate implements Serializable {
    @Inject
    ServletContext ctx;
    String permName = null;

    public BeanTemplate() {
    }

    public BeanTemplate(@NotNull String permName) {
        this();
        this.permName = permName;
    }

    public void checkPermission() throws IOException, MalformedClaimException {
        PermissionManager perm = (PermissionManager) ctx.getAttribute("Perm");
        Rolle berechtigung = perm.get(permName);

        if (berechtigung == Rolle.NONE) return;

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> cookies = context.getRequestCookieMap();

        Cookie authCookie = (Cookie) cookies.get("authorization");
        if (authCookie == null) {
            context.redirect(context.getRequestContextPath());
            return;
        }

        AuthorizationManager manager = (AuthorizationManager) this.ctx.getAttribute("Auth");
        String userID = manager.validateToken(authCookie.getValue());
        if (userID == null) {
            authCookie.setMaxAge(1);
            context.addResponseCookie(authCookie.getName(), null, null);
            context.redirect(context.getRequestContextPath());
            return;
        }

        DatabaseManager DB = (DatabaseManager) this.ctx.getAttribute("DB");
        Session session = DB.getSessionFactory().openSession();
        Benutzer benutzer = new Benutzer().getById(session, Long.parseLong(userID));
        session.close();

        if (benutzer == null) {
            authCookie.setMaxAge(1);
            context.addResponseCookie(authCookie.getName(), null, null);
            context.redirect(context.getRequestContextPath());
            return;
        }

        if (benutzer.getRolle().ordinal() < berechtigung.ordinal()) context.redirect(context.getRequestContextPath());
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
