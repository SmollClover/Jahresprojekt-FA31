package de.hhbk.model;

import java.io.Serializable;
import javax.persistence.Embeddable;


@Embeddable
public class Adresse implements Serializable
{
  //-------------------------------------------------------------------------
  //  Var(s)
  //-------------------------------------------------------------------------     
    protected String strasse = null;
    protected String hausnummer = null;
    protected String plz = null;
    protected String ort = null;


  //-------------------------------------------------------------------------
  //  Constructor(s)
  //-------------------------------------------------------------------------     
    public Adresse() { super(); }


  //-------------------------------------------------------------------------
  //  Get / Set
  //-------------------------------------------------------------------------     
    public String getStrasse() { return strasse; }

    public void setStrasse(String strasse) { this.strasse = strasse; }

    public String getHausnummer() { return hausnummer; }

    public void setHausnummer(String hausnummer) { this.hausnummer = hausnummer; }    
    
    public String getPlz() { return plz; }

    public void setPlz(String plz) { this.plz = plz; }

    public String getOrt() { return ort; }

    public void setOrt(String ort) { this.ort = ort; }


  //-------------------------------------------------------------------------
  //  Method(s)
  //-------------------------------------------------------------------------     
    @Override
    public String toString()
    {
        return ((strasse != null) ? strasse : "") + ((hausnummer != null) ? " "+hausnummer : "") + ",  " + ((plz != null) ? plz : "") + " " + ((ort != null) ? ort : "");
    }



}
