package de.hhbk.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Einnahme implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToMany
    private Collection<Mieter> mieter = new ArrayList<Mieter>();
    @ManyToMany
    private Collection<Mietobjekt> objekt = new ArrayList<Mietobjekt>();
    private double zahlungist;
    private double zahlungssoll;
    private double freibetrag;

    public Einnahme() {
        super();
    }

    public Einnahme(Collection<Mieter> mieter, Collection<Mietobjekt> objekt, double zahlungist, double zahlungssoll, double freibetrag) {
        this.mieter = mieter;
        this.objekt = objekt;
        this.zahlungist = zahlungist;
        this.zahlungssoll = zahlungssoll;
        this.freibetrag = freibetrag;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Collection<Mieter> getMieter() {
        return mieter;
    }

    public void setMieter(Collection<Mieter> mieter) {
        this.mieter = mieter;
    }

    public Collection<Mietobjekt> getObjekt() {
        return objekt;
    }

    public void setObjekt(Collection<Mietobjekt> objekt) {
        this.objekt = objekt;
    }

    public double getZahlungist() {
        return zahlungist;
    }

    public void setZahlungist(double zahlungist) {
        this.zahlungist = zahlungist;
    }

    public double getZahlungssoll() {
        return zahlungssoll;
    }

    public void setZahlungssoll(double zahlungssoll) {
        this.zahlungssoll = zahlungssoll;
    }

    public double getFreibetrag() {
        return freibetrag;
    }

    public void setFreibetrag(double freibetrag) {
        this.freibetrag = freibetrag;
    }
}
