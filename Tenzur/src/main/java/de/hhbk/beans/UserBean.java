package de.hhbk.beans;

import de.hhbk.entities.User;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

interface Request {
}

@Named(value = "benutzer")
@SessionScoped
public class UserBean extends BeanTemplate {

    // Attributes
    protected User user = null;

    // Methods
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

    // Get User
    public User getUser() {
        return user;
    }

    // Set User
    public void setUser(User user) {
        this.user = user;
    }

    // Load User
    public User loadUser(Integer id) {

    }

    // Save User
    public void saveUser(User user) {

    }

    // Delete User
    public void deleteUser(User user) {

    }

    // Login
    public User login(Request request) {
        return null;
    }

    // Logout
    public void logout(User user) {
    }
    // Check Permissions
}