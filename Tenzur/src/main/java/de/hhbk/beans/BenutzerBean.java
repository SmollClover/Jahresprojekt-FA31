package de.hhbk.beans;

import de.hhbk.entities.Benutzer;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;

@Named(value = "benutzer")
@ViewScoped
public class BenutzerBean extends BeanEntityTemplate<Benutzer> {
    @Inject
    ServletContext ctx;

    public BenutzerBean() {
        super(Benutzer.class);
    }

    @PostConstruct
    public void init() {
        try {
            this.checkPermission();
            this.loadItemList();
        } catch (Exception e) {
            e.printStackTrace();
            this.setErrorMessage("Fehler bei initialisierung", e.getMessage());
        }
    }
}