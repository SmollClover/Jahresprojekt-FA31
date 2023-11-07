package de.hhbk.entities;

import javax.persistence.*;

@Entity
public class Mietobjekt extends EntityTemplate<Mietobjekt> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String objektart;
    private int objektnummer;
    private String objektbeschreibung;
    private int wohnflaeche;
    private int qmPreisKalt;
    private int nebenkosten;
    private String notzifeld;
    private String strasse;
    private String strassenummer;
    @ManyToOne
    private Ort ort;

    public Mietobjekt() {
        super(Mietobjekt.class);
    }

    public Mietobjekt(String objektart, int objektnummer, String objektbeschreibung, int wohnflaeche, int qmPreisKalt, int nebenkosten, String notzifeld, String strasse, String strassenummer, Ort ort) {
        this();
        this.objektart = objektart;
        this.objektnummer = objektnummer;
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

    public String getStrassenummer() {
        return strassenummer;
    }

    public void setStrassenummer(String strassenummer) {
        this.strassenummer = strassenummer;
    }

    public Ort getOrt() {
        return ort;
    }

    public void setOrt(Ort ort) {
        this.ort = ort;
    }
}
