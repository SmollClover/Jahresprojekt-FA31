package de.hhbk.beans;

import de.hhbk.entities.Mieter;
import de.hhbk.entities.Rolle;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "mieter")
@SessionScoped
public class MieterBean extends BeanTemplate<Mieter> {
    public MieterBean() {
        super(Mieter.class, Rolle.NONE);
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