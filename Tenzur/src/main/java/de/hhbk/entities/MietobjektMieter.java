package de.hhbk.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.util.Date;

@Entity
public class MietobjektMieter extends EntityTemplate<MietobjektMieter> {
    @EmbeddedId
    private MietobjektMieterId id;
    private Date vertragsende;

    public MietobjektMieter() {
        super(MietobjektMieter.class);
    }

    public MietobjektMieter(MietobjektMieterId id, Date vertragsende) {
        this();
        this.id = id;
        this.vertragsende = vertragsende;
    }

    public MietobjektMieterId getId() {
        return id;
    }

    public void setId(MietobjektMieterId id) {
        this.id = id;
    }

    public Date getVertragsende() {
        return vertragsende;
    }

    public void setVertragsende(Date vertragsende) {
        this.vertragsende = vertragsende;
    }
}
