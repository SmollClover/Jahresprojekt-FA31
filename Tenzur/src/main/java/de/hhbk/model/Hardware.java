package de.hhbk.model;

import java.time.LocalDate;


public abstract class Hardware extends ModelTemplate
{ 
  //-------------------------------------------------------------------------
  //  Var(s)
  //-------------------------------------------------------------------------     
   protected String seriennummer = null;
   protected String modell = null;
   protected String hersteller = null;
   protected String status = null;
   protected int herstellergarantie = 0;
   protected LocalDate lieferdatum = LocalDate.now();
   protected Raum raum = null;

   
  //-------------------------------------------------------------------------
  //  Constructor(s)
  //-------------------------------------------------------------------------     
    public Hardware() { super(); }
 

  //-------------------------------------------------------------------------
  //  Get / Set
  //-------------------------------------------------------------------------  
    public String getSeriennummer() { return seriennummer; }

    public void setSeriennummer(String seriennummer) { this.seriennummer = seriennummer; }

    public String getModell() { return modell; }

    public void setModell(String modell) { this.modell = modell; }

    public String getHersteller() { return hersteller; }

    public void setHersteller(String hersteller) { this.hersteller = hersteller; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public int getHerstellergarantie() { return herstellergarantie; }

    public void setHerstellergarantie(int herstellergarantie) { this.herstellergarantie = herstellergarantie; }

    public LocalDate getLieferdatum() { return lieferdatum; }

    public void setLieferdatum(LocalDate lieferdatum) { this.lieferdatum = lieferdatum; }

    public Raum getRaum() { return raum; }

    public void setRaum(Raum raum) { this.raum = raum; }

    public boolean hasRaum() { return this.raum != null; }
    
  //-------------------------------------------------------------------------
  //  Method(s)
  //-------------------------------------------------------------------------   
    public LocalDate berechneGarantieende() { return this.lieferdatum.plusMonths(this.herstellergarantie); }
    
    

}
