package de.hhbk.entities;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class MietobjektMieterId implements Serializable {
    @ManyToOne
    private Mietobjekt mietobjekt;
    @ManyToOne
    private Mieter mieter;

    public MietobjektMieterId() {
        super();
    }

    public MietobjektMieterId(Mietobjekt mietobjekt, Mieter mieter) {
        this();
        this.mietobjekt = mietobjekt;
        this.mieter = mieter;
    }

    public Mietobjekt getMietobjekt() {
        return mietobjekt;
    }

    public void setMietobjekt(Mietobjekt mietobjekt) {
        this.mietobjekt = mietobjekt;
    }

    public Mieter getMieter() {
        return mieter;
    }

    public void setMieter(Mieter mieter) {
        this.mieter = mieter;
    }
}
