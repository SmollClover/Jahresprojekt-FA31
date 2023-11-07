package de.hhbk.beans;

import de.hhbk.entities.User;
import de.hhbk.managers.DatabaseManager;
import org.hibernate.Session;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.Collection;

@Named(value = "user")
@SessionScoped
public class UserBean extends BeanTemplate {
    @Inject
    private ServletContext ctx;
    private Collection<User> users = new ArrayList<User>();

    // @ManagedProperty("#{facesContext.externalContext.context}")
    private ServletContext servletContext;

    public UserBean() {
        super();
    }

    @PostConstruct
    public void init() {
        DatabaseManager DB = (DatabaseManager) ctx.getAttribute("DB");
        Session session = DB.getSessionFactory().openSession();
        this.users = new User().getList(session);
        session.close();
    }

    public String test() {
        return "Test String";
    }

    public void context() {
        // You can now access the servletContext in your managed bean methods.
        // String contextPath = servletContext.getContextPath();
        // System.out.println(contextPath);
    }
}