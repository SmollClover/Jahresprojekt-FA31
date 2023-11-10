package de.hhbk.beans;

import de.hhbk.entities.Ort;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "ort")
@SessionScoped
public class OrtBean extends BeanEntityTemplate<Ort> {
    public OrtBean() {
        super(Ort.class);
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