package de.hhbk.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User extends EntityTemplate {
    protected String passwort = null;
    @Id
    @GeneratedValue
    private Long id;
    private long rollenId;
    private String vorname;
    private String nachname;
    private String strasse;
    private String strassennummer;
    private int plz;
    private String email;
    private String benutzername;

    public User() {
        super();
    }

    public User(long rollenId, String vorname, String nachname, String strasse, String strassennummer, int plz, String email, String benutzername, String passwort) {
        this.rollenId = rollenId;
        this.vorname = vorname;
        this.nachname = nachname;
        this.strasse = strasse;
        this.strassennummer = strassennummer;
        this.plz = plz;
        this.email = email;
        this.benutzername = benutzername;
        this.passwort = passwort;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getRollenId() {
        return rollenId;
    }

    public void setRollenId(long rollenId) {
        this.rollenId = rollenId;
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

    public int getPlz() {
        return plz;
    }

    public void setPlz(int plz) {
        this.plz = plz;
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
