package de.hhbk.beans;

import de.hhbk.entities.Rolle;
import de.hhbk.entities.Telefonnummer;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "telefon")
@SessionScoped
public class TelefonnummerBean extends BeanTemplate<Telefonnummer> {
    public TelefonnummerBean() {
        super(Telefonnummer.class, Rolle.NONE);
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