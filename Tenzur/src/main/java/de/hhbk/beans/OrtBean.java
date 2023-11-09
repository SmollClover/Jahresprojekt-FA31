package de.hhbk.beans;

import de.hhbk.entities.Ort;
import de.hhbk.entities.Rolle;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "ort")
@SessionScoped
public class OrtBean extends BeanTemplate<Ort> {
    public OrtBean() {
        super(Ort.class, Rolle.NONE);
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