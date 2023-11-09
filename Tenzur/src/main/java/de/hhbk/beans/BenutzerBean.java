package de.hhbk.beans;

import de.hhbk.entities.Benutzer;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "benutzer")
@SessionScoped
public class BenutzerBean extends BeanTemplate<Benutzer> {
    public BenutzerBean() {
        super(Benutzer.class);
    }

    @PostConstruct
    public void init() {
        try {
            this.loadItemList();
        } catch (Exception e) {
            e.printStackTrace();
            this.setErrorMessage("Daten konnten nicht geladen werden", e.getMessage());
        }
    }
}