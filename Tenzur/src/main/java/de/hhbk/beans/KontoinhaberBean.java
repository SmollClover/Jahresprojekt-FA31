package de.hhbk.beans;

import de.hhbk.entities.Kontoinhaber;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "kontoinhaber")
@SessionScoped
public class KontoinhaberBean extends BeanTemplate<Kontoinhaber> {
    public KontoinhaberBean() {
        super(Kontoinhaber.class);
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