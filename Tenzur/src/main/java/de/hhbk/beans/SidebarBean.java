package de.hhbk.beans;

import de.hhbk.entities.Rolle;
import de.hhbk.managers.AuthorizationManager;
import de.hhbk.managers.DatabaseManager;
import de.hhbk.managers.PermissionManager;
import org.hibernate.Session;
import org.jvnet.staxex.util.FinalArrayList;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.Cookie;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Named(value = "sidebar")
@ViewScoped
public class SidebarBean extends BeanTemplate {
    private final Collection<String> names = new FinalArrayList<String>();
    private final Map<String, Boolean> cache = new HashMap<String, Boolean>();

    public SidebarBean() {
        super();

        names.add("Dashboard");
        names.add("Immobilien");
        names.add("Mieter");
        names.add("Dokumente");
        names.add("Benutzer");
        names.add("Chat");
        names.add("Termine");
        names.add("Reports");
    }

    @PostConstruct
    public void init() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> cookies = context.getRequestCookieMap();
        Cookie authCookie = (Cookie) cookies.get("authorization");

        this.cache(authCookie);
    }

    public void cache(@NotNull Cookie authCookie) {
        AuthorizationManager auth = (AuthorizationManager) ctx.getAttribute("Auth");
        PermissionManager perm = (PermissionManager) ctx.getAttribute("Perm");
        DatabaseManager db = (DatabaseManager) ctx.getAttribute("DB");

        String userId = null;
        if (authCookie != null) {
            userId = auth.validateToken(authCookie.getValue());
        }

        Session session = db.getSessionFactory().openSession();
        for (String name : names) {
            if (perm.get(name) == Rolle.NONE) {
                this.cache.put(name, true);
                continue;
            }

            if (userId == null) {
                this.cache.put(name, false);
                continue;
            }

            boolean show = perm.has(session, Long.parseLong(userId), name);

            this.cache.put(name, show);
        }
        session.close();
    }

    public boolean render(String name) {
        Boolean show = this.cache.get(name);

        if (show == null) return true;
        return show;
    }
}
