package de.hhbk.beans;

import de.hhbk.entities.Mieter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "mieter")
@SessionScoped
public class MieterBeanEntity extends BeanEntityTemplate<Mieter> {
    public MieterBeanEntity() {
        super(Mieter.class);
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