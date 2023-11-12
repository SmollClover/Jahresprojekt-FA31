package de.hhbk.beans;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "emptybean")
@ViewScoped
public class EmptyBean extends BeanTemplate {
    private String titel = "Willkommen zu Tenzur!";
    public EmptyBean() {
        super("Empty");
    }

    @PostConstruct
    private void init() {
        try {
            this.checkPermission();
        } catch (Exception ignored) {}
    }

    public String getTitel() {
        return titel;
    }
}
