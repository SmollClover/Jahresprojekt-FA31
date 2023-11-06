package de.hhbk.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Telefonnummer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int festnetz;
    private int mobil;

    public Telefonnummer() {
        super();
    }

    public Telefonnummer(int festnetz, int mobil) {
        this.festnetz = festnetz;
        this.mobil = mobil;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getFestnetz() {
        return festnetz;
    }

    public void setFestnetz(int festnetz) {
        this.festnetz = festnetz;
    }

    public int getMobil() {
        return mobil;
    }

    public void setMobil(int mobil) {
        this.mobil = mobil;
    }
}
