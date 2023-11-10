package de.hhbk.beans;

import de.hhbk.entities.Konto;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "konto")
@SessionScoped
public class KontoBean extends BeanEntityTemplate<Konto> {
    public KontoBean() {
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