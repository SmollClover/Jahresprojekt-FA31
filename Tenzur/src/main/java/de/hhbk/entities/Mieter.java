package de.hhbk.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Mieter implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String titel;
    private String anrede;
    private String vorname;
    private String nachname;
    @OneToMany
    private Collection<Telefonnummer> telefon = new ArrayList<Telefonnummer>();
    private String email;
    @ManyToMany
    private Collection<Konto> konto = new ArrayList<Konto>();

    public Mieter() {
        super();
    }

    public Mieter(String titel, String anrede, String vorname, String nachname, Collection<Telefonnummer> telefon, String email, Collection<Konto> konto) {
        this.titel = titel;
        this.anrede = anrede;
        this.vorname = vorname;
        this.nachname = nachname;
        this.telefon = telefon;
        this.email = email;
        this.konto = konto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getAnrede() {
        return anrede;
    }

    public void setAnrede(String anrede) {
        this.anrede = anrede;
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

    public Collection<Telefonnummer> getTelefon() {
        return telefon;
    }

    public void setTelefon(Collection<Telefonnummer> telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<Konto> getKonto() {
        return konto;
    }

    public void setKonto(Collection<Konto> konto) {
        this.konto = konto;
    }
}
