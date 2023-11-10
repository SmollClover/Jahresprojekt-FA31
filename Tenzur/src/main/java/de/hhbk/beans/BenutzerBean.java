package de.hhbk.beans;

import de.hhbk.entities.Benutzer;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "benutzer")
@ViewScoped
public class BenutzerBeanEntity extends BeanEntityTemplate<Benutzer> {
    public BenutzerBeanEntity() {
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