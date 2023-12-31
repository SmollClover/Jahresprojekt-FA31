package de.hhbk.beans;

import de.hhbk.entities.Einnahme;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "einnahme")
@SessionScoped
public class EinnahmeBean extends BeanEntityTemplate<Einnahme> {
    public EinnahmeBean() {
        super(Einnahme.class);
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