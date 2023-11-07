package de.hhbk.beans;

import de.hhbk.entities.User;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
// import javax.faces.bean.ManagedProperty;
import javax.inject.Named;
import javax.servlet.ServletContext;

import java.util.ArrayList;
import java.util.Collection;

@Named(value = "user")
// @SessionScoped
@ManagedBean
public class UserBean extends BeanTemplate {
    private Collection<User> users = new ArrayList<User>();

    // @ManagedProperty("#{facesContext.externalContext.context}")
    private ServletContext servletContext;

    public UserBean() {
        super();
    }


    // @PostConstruct
    // public void init() {
    //     resetItem();
    //     getItemList().add(new Drucker("880815", "Brother", "DCP 8490"));
    //     getItemList().add(new Drucker("990815", "Canon", "MX-925"));
    //     getItemList().add(new Drucker("222888", "HP", "DeskJet 9000"));
    // }

    @PostConstruct
    public void init() {

    }

    public String test() {
        return "Test String";
    }

    public void context() {
        // You can now access the servletContext in your managed bean methods.
        String contextPath = servletContext.getContextPath();
        System.out.println(contextPath);
    }
}