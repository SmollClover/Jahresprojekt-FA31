package de.hhbk.beans;

import de.hhbk.entities.Benutzer;
import de.hhbk.entities.Rolle;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "benutzer")
@ViewScoped
public class BenutzerBean extends BeanTemplate<Benutzer> {
    public BenutzerBean() {
        super(Benutzer.class, Rolle.NONE);
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