package de.hhbk.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Mietobjekt implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String objektart;
    private int objektnummer;
    private String objekttyp;
    private String objektbeschreibung;
    private int wohnflaeche;
    private int qmPreisKalt;
    private int nebenkosten;
    private String notzifeld;
    private String strasse;
    private int strassenummer;
    @OneToMany
    private Ort ort;

    public Mietobjekt() {
        super();
    }

    public Mietobjekt(String objektart, int objektnummer, String objekttyp, String objektbeschreibung, int wohnflaeche, int qmPreisKalt, int nebenkosten, String notzifeld, String strasse, int strassenummer, Ort ort) {
        this.objektart = objektart;
        this.objektnummer = objektnummer;
        this.objekttyp = objekttyp;
        this.objektbeschreibung = objektbeschreibung;
        this.wohnflaeche = wohnflaeche;
        this.qmPreisKalt = qmPreisKalt;
        this.nebenkosten = nebenkosten;
        this.notzifeld = notzifeld;
        this.strasse = strasse;
        this.strassenummer = strassenummer;
        this.ort = ort;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getObjektart() {
        return objektart;
    }

    public void setObjektart(String objektart) {
        this.objektart = objektart;
    }

    public int getObjektnummer() {
        return objektnummer;
    }

    public void setObjektnummer(int objektnummer) {
        this.objektnummer = objektnummer;
    }

    public String getObjekttyp() {
        return objekttyp;
    }

    public void setObjekttyp(String objekttyp) {
        this.objekttyp = objekttyp;
    }

    public String getObjektbeschreibung() {
        return objektbeschreibung;
    }

    public void setObjektbeschreibung(String objektbeschreibung) {
        this.objektbeschreibung = objektbeschreibung;
    }

    public int getWohnflaeche() {
        return wohnflaeche;
    }

    public void setWohnflaeche(int wohnflaeche) {
        this.wohnflaeche = wohnflaeche;
    }

    public int getQmPreisKalt() {
        return qmPreisKalt;
    }

    public void setQmPreisKalt(int qmPreisKalt) {
        this.qmPreisKalt = qmPreisKalt;
    }

    public int getNebenkosten() {
        return nebenkosten;
    }

    public void setNebenkosten(int nebenkosten) {
        this.nebenkosten = nebenkosten;
    }

    public String getNotzifeld() {
        return notzifeld;
    }

    public void setNotzifeld(String notzifeld) {
        this.notzifeld = notzifeld;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public int getStrassenummer() {
        return strassenummer;
    }

    public void setStrassenummer(int strassenummer) {
        this.strassenummer = strassenummer;
    }

    public Ort getOrt() {
        return ort;
    }

    public void setOrt(Ort ort) {
        this.ort = ort;
    }
}
