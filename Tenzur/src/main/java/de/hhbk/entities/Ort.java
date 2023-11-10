package de.hhbk.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ort extends EntityTemplate<Ort> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int plz;
    private String ort;

    public Ort() {
        super(Ort.class);
    }

    public Ort(int plz, String ort) {
        this();
        this.plz = plz;
        this.ort = ort;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPlz() {
        return plz;
    }

    public void setPlz(int plz) {
        this.plz = plz;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }
}
