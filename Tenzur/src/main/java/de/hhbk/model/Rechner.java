package de.hhbk.model;


public class Rechner extends Hardware {
    //-------------------------------------------------------------------------
    //  Var(s)
    //-------------------------------------------------------------------------
    protected String cpu = null;
    protected int arbeitsspeicher = 0;
    protected String betriebssystem = null;
    protected String typ = null;
    protected String grafikkarte = null;
    protected int ssd = 0;
    protected int hdd = 0;

    //-------------------------------------------------------------------------
    //  Constructor(s)
    //-------------------------------------------------------------------------
    public Rechner() {
        super();
    }

    public Rechner(String sn, String hersteller, String modell) {
        super();
        this.seriennummer = sn;
        this.hersteller = hersteller;
        this.modell = modell;
    }


    //-------------------------------------------------------------------------
    //  Get / Set
    //-------------------------------------------------------------------------
    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public int getArbeitsspeicher() {
        return arbeitsspeicher;
    }

    public void setArbeitsspeicher(int arbeitsspeicher) {
        this.arbeitsspeicher = arbeitsspeicher;
    }

    public String getBetriebssystem() {
        return betriebssystem;
    }

    public void setBetriebssystem(String betriebssystem) {
        this.betriebssystem = betriebssystem;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public String getGrafikkarte() {
        return grafikkarte;
    }

    public void setGrafikkarte(String grafikkarte) {
        this.grafikkarte = grafikkarte;
    }

    public int getSsd() {
        return ssd;
    }

    public void setSsd(int ssd) {
        this.ssd = ssd;
    }

    public int getHdd() {
        return hdd;
    }

    public void setHdd(int hdd) {
        this.hdd = hdd;
    }


}
