package de.hhbk.beans;

import de.hhbk.entities.Konto;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "konto")
@SessionScoped
public class KontoBeanEntity extends BeanEntityTemplate<Konto> {
    public KontoBeanEntity() {
        super(Konto.class);
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