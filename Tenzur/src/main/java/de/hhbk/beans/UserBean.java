package de.hhbk.beans;

import de.hhbk.entities.User;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Collection;

@Named(value = "user")
@SessionScoped
public class UserBean extends BeanTemplate {
    private Collection<User> users = new ArrayList<User>();

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
}