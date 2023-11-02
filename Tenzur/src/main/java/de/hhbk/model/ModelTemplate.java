package de.hhbk.model;

import java.io.Serializable;


public abstract class ModelTemplate implements Serializable {
    //-------------------------------------------------------------------------
    //  Class-Var(s)
    //-------------------------------------------------------------------------
    protected static long anzahl = 0;
    //-------------------------------------------------------------------------
    //  Var(s)
    //-------------------------------------------------------------------------
    protected long id = -1;

    //-------------------------------------------------------------------------
    //  Constructor(s)
    //-------------------------------------------------------------------------
    public ModelTemplate() {
        super();
        incAnzahl();
        this.id = anzahl;
    }

    public static long getAnzahl() {
        return anzahl;
    }

    public static synchronized void incAnzahl() {
        anzahl++;
    }

    //-------------------------------------------------------------------------
    //  Get / Set
    //-------------------------------------------------------------------------
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean hasId() {
        return this.id > -1;
    }


}
