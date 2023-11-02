package de.hhbk.model;


public class Drucker extends Hardware
{
  //-------------------------------------------------------------------------
  //  Var(s)
  //-------------------------------------------------------------------------     
    protected String technologie = null;
    protected boolean farbdruckfunktion = false;
    protected String papierformatMax = null;
    protected int seitenGesamt = 0;
    protected int restkapazitaet = 1000;
    protected int kapazitaetBetriebsmittel = 1000;


  //-------------------------------------------------------------------------
  //  Constructor(s)
  //-------------------------------------------------------------------------     
    public Drucker() { super(); }
 
    public Drucker(String sn, String hersteller, String modell) 
    { 
        super(); 
        this.seriennummer = sn;
        this.hersteller = hersteller;
        this.modell = modell; 
    }
 
    
  //-------------------------------------------------------------------------
  //  Get / Set
  //-------------------------------------------------------------------------     
    public String getTechnologie() { return technologie; }

    public void setTechnologie(String technologie) { this.technologie = technologie; }

    public boolean isFarbdruckfunktion() { return farbdruckfunktion; }

    public void setFarbdruckfunktion(boolean farbdruckfunktion) { this.farbdruckfunktion = farbdruckfunktion; }

    public String getPapierformatMax() { return papierformatMax; }

    public void setPapierformatMax(String papierformatMax) { this.papierformatMax = papierformatMax; }

    public int getSeitenGesamt() { return seitenGesamt; }

    public void setSeitenGesamt(int seitenGesamt) { this.seitenGesamt = seitenGesamt; }

    public int getRestkapazitaet() { return restkapazitaet; }

    public void setRestkapazitaet(int restkapazitaet) { this.restkapazitaet = restkapazitaet; }

    public int getKapazitaetBetriebsmittel() { return kapazitaetBetriebsmittel; }

    public void setKapazitaetBetriebsmittel(int kapazitaetBetriebsmittel) { this.kapazitaetBetriebsmittel = kapazitaetBetriebsmittel; }
 
    

}
