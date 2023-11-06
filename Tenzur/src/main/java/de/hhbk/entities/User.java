package de.hhbk.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String rolle;
    private String vorname;
    private String nachname;
    private String strasse;
    private String strassennummer;
    @ManyToOne
    private Ort ort;
    @OneToMany
    private Collection<Telefonnummer> telefonnummer = new ArrayList<Telefonnummer>();
    private String email;
    private String benutzername;
    private String passwort;

    public User() {
        super();
    }

    public User(String rolle, String vorname, String nachname, String strasse, String strassennummer, Ort ort, Collection<Telefonnummer> telefonnummer, String email, String benutzername, String passwort) {
        this.rolle = rolle;
        this.vorname = vorname;
        this.nachname = nachname;
        this.strasse = strasse;
        this.strassennummer = strassennummer;
        this.ort = ort;
        this.telefonnummer = telefonnummer;
        this.email = email;
        this.benutzername = benutzername;
        this.passwort = passwort;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRolle() {
        return rolle;
    }

    public void setRolle(String rolle) {
        this.rolle = rolle;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getStrassennummer() {
        return strassennummer;
    }

    public void setStrassennummer(String strassennummer) {
        this.strassennummer = strassennummer;
    }

    public Ort getOrt() {
        return ort;
    }

    public void setOrt(Ort ort) {
        this.ort = ort;
    }

    public Collection<Telefonnummer> getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(Collection<Telefonnummer> telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBenutzername() {
        return benutzername;
    }

    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }
}
