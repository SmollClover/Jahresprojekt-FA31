package de.hhbk.model.enums;


public enum PersonAnrede 
{
    EMPTY("", "Unbekannt"), 
    MR("Herr", "Männlich"), 
    MRS("Frau", "Weiblich");
    
    private final String anrede;
    private final String geschlecht;
    
    private PersonAnrede(String a, String g) 
    { 
        this.anrede = a;
        this.geschlecht = g;
    }

    public String getAnrede() { return anrede; }

    public String getGeschlecht() { return geschlecht; }

    
    
}
