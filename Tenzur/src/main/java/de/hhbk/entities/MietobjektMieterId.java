package de.hhbk.entities;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class MietobjektMieterId extends EntityTemplate<MietobjektMieterId> {
    @ManyToOne
    private Mietobjekt mietobjekt;
    @ManyToOne
    private Mieter mieter;

    public MietobjektMieterId() {
        super(MietobjektMieterId.class);
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
