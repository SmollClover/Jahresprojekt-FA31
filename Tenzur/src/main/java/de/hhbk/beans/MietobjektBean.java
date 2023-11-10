package de.hhbk.beans;

import de.hhbk.entities.Mietobjekt;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "mietobjekt")
@SessionScoped
public class MietobjektBeanEntity extends BeanEntityTemplate<Mietobjekt> {
    public MietobjektBeanEntity() {
        super(Mietobjekt.class);
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