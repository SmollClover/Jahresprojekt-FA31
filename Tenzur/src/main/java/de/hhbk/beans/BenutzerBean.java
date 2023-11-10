package de.hhbk.beans;

import de.hhbk.entities.Benutzer;
// import de.hhbk.entities.Ort;
// import de.hhbk.entities.Rolle;
// import de.hhbk.entities.Telefonnummer;
// import de.hhbk.managers.AuthorizationManager;
import de.hhbk.managers.DatabaseManager;

// import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
// import javax.inject.Inject;
import javax.inject.Named;
// import javax.persistence.JoinColumn;
// import javax.servlet.ServletContext;

import org.hibernate.Session;
// import java.util.Collection;

@Named(value = "benutzer")
@ViewScoped
public class BenutzerBean extends BeanEntityTemplate<Benutzer> {
    // Servlet Context wird geladen
    // @Inject
    // ServletContext ctx;

    // Attribute
    // private int id;
    // private String benutzername;
    // private String email;
    // private String nachname;
    // private String passwort;
    // private Rolle rolle;
    // private String strasse;
    // private String hausnummer;
    // private String vorname;
    // private int plz;
    // private String ort;

    // @JoinColumn(name = "ort_id", referencedColumnName = "id")
    // private Ort ortObj;
    
    // private Collection<Telefonnummer> telefonnummer = new ArrayList<Telefonnummer>();
    
    
    @PostConstruct
    public void init() {
        try {
            this.checkPermission();
            this.loadItemList();
        } catch (Exception e) {
            e.printStackTrace();
            this.setErrorMessage("Fehler bei initialisierung", e.getMessage());
        }
    }
    
    public void add(){
        DatabaseManager DB = (DatabaseManager) ctx.getAttribute("DB");
        Session session = DB.getSessionFactory().openSession();
        // Benutzer benutzer = new Benutzer(this.vorname, this.nachname, this.strasse, this.hausnummer, this.ortObj, this.telefonnummer, this.email, this.benutzername, this.passwort);
        // session.saveOrUpdate(benutzer);
        session.close();
    }
    public void update(){
        DatabaseManager DB = (DatabaseManager) ctx.getAttribute("DB");
        Session session = DB.getSessionFactory().openSession();
        // Benutzer benutzer = new Benutzer(this.vorname, this.nachname, this.strasse, this.hausnummer, this.ortObj, this.telefonnummer, this.email, this.benutzername, this.passwort);
        // benutzer.setId(this.id);
        // session.saveOrUpdate(benutzer);
        session.close();
    }
    public void delete(){
        DatabaseManager DB = (DatabaseManager) ctx.getAttribute("DB");
        Session session = DB.getSessionFactory().openSession();
        // Benutzer benutzer = new Benutzer(this.vorname, this.nachname, this.strasse, this.hausnummer, this.ortObj, this.telefonnummer, this.email, this.benutzername, this.passwort);
        // benutzer.setId(this.id);
        // session.delete(benutzer);
        session.close();
    }
    
    // public int getId() {
    //     return id;
    // }
    
    // public void setId(int id) {
    //     this.id = id;
    // }
    
    // public String getBenutzername() {
    //     return benutzername;
    // }
    
    // public void setBenutzername(String benutzername) {
    //     this.benutzername = benutzername;
    // }
    
    // public String getEmail() {
    //     return email;
    // }
    
    // public void setEmail(String email) {
    //     this.email = email;
    // }
    
    // public String getNachname() {
    //     return nachname;
    // }
    
    // public void setNachname(String nachname) {
    //     this.nachname = nachname;
    // }
    
    // public String getPasswort() {
    //     return passwort;
    // }
    
    // public void setPasswort(String passwort) {
    //     AuthorizationManager manager = (AuthorizationManager) ctx.getAttribute("Auth");
    //     this.passwort = manager.hashPassword(passwort);
    // }
    
    // public Rolle getRolle() {
    //     return rolle;
    // }
    
    // public void setRolle(Rolle rolle) {
    //     this.rolle = rolle;
    // }
    
    // public String getStrasse() {
    //     return strasse;
    // }
    
    // public void setStrasse(String strasse) {
    //     this.strasse = strasse;
    // }
    
    // public String getVorname() {
    //     return vorname;
    // }
    
    // public void setVorname(String vorname) {
    //     this.vorname = vorname;
    // }
    
    // public Collection<Telefonnummer> getTelefonnummer() {
    //     return telefonnummer;
    // }

    // public void setTelefonnummer(Collection<Telefonnummer> telefonnummer) {
    //     this.telefonnummer = telefonnummer;
    // }
    
    // public BenutzerBean() {
    //     super(Benutzer.class);
    // }
    
    // public String getHausnummer() {
    //     return hausnummer;
    // }
    
    // public void setHausnummer(String hausnummer) {
    //     this.hausnummer = hausnummer;
    // }
    
    // public Ort getOrt_id() {
    //     return ortObj;
    // }
    
    // public void setOrt_id(Ort ort_id) {
    //     Ort ort = new Ort(this.plz, this.ort);
    //     this.ortObj = ort;
    // }
}