package de.hhbk.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Konto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String iban;
    private String bic;
    @ManyToMany
    private Collection<Kontoinhaber> kontoinhaber = new ArrayList<Kontoinhaber>();
    private String kreditinstitut;

    public Konto() {
        super();
    }

    public Konto(String iban, String bic, Collection<Kontoinhaber> kontoinhaber, String kreditinstitut) {
        this.iban = iban;
        this.bic = bic;
        this.kontoinhaber = kontoinhaber;
        this.kreditinstitut = kreditinstitut;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public Collection<Kontoinhaber> getKontoinhaber() {
        return kontoinhaber;
    }

    public void setKontoinhaber(Collection<Kontoinhaber> kontoinhaber) {
        this.kontoinhaber = kontoinhaber;
    }

    public String getKreditinstitut() {
        return kreditinstitut;
    }

    public void setKreditinstitut(String kreditinstitut) {
        this.kreditinstitut = kreditinstitut;
    }
}
