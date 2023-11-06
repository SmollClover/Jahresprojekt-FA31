package de.hhbk.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Date;

@Entity
public class MietobjektMieter implements Serializable {
    @EmbeddedId
    private long id;
    @ManyToOne
    private Mietobjekt mietobjekt;
    @ManyToOne
    private Mieter mieter;
    private Date vertragsende;

    public MietobjektMieter() {
        super();
    }

    public MietobjektMieter(Mietobjekt mietobjekt, Mieter mieter, Date vertragsende) {
        this.mietobjekt = mietobjekt;
        this.mieter = mieter;
        this.vertragsende = vertragsende;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Date getVertragsende() {
        return vertragsende;
    }

    public void setVertragsende(Date vertragsende) {
        this.vertragsende = vertragsende;
    }
}
