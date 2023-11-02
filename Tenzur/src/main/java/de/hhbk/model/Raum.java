package de.hhbk.model;

import java.util.ArrayList;
import java.util.List;


public class Raum extends ModelTemplate {
    //-------------------------------------------------------------------------
    //  Var(s)
    //-------------------------------------------------------------------------
    protected String bezeichnung = null;
    protected String typ = null;
    protected List<Hardware> hardwareList = null;
    protected Person betreuung = null;


    //-------------------------------------------------------------------------
    //  Constructor(s)
    //-------------------------------------------------------------------------
    public Raum() {
        super();
    }

    public Raum(String bezeichnung, String typ) {
        super();
        this.bezeichnung = bezeichnung;
        this.typ = typ;
    }


    //-------------------------------------------------------------------------
    //  Get / Set
    //-------------------------------------------------------------------------
    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public List<Hardware> getHardwareList() {
        if (hardwareList == null) {
            hardwareList = new ArrayList<>();
        }
        return hardwareList;
    }

    public void setHardwareList(List<Hardware> hardwareList) {
        this.hardwareList = hardwareList;
    }

    public Person getBetreuung() {
        return betreuung;
    }

    public void setBetreuung(Person betreuung) {
        this.betreuung = betreuung;
    }


    //-------------------------------------------------------------------------
    //  Method(s)
    //-------------------------------------------------------------------------
    public boolean hasHardware() {
        return (hardwareList != null && !hardwareList.isEmpty());
    }

    public void addHardware(Hardware h) {
        if (h != null && h.hasId()) {
            getHardwareList().add(h);
            h.setRaum(this);
        }
    }

    public void removeHardware(Hardware h) {
        if (hasHardware()) {
            getHardwareList().remove(h);
            h.setRaum(null);
        }
    }

    public boolean hasBetreuung() {
        return (this.betreuung != null);
    }

    public void addBetreuung(Person p) {
        if (p != null && p.hasId()) {
            this.betreuung = p;
        }
    }

    public void removeBetreuung() {
        this.betreuung = null;
    }


}
