package de.hhbk.entities;

import org.hibernate.Session;

import javax.persistence.*;

@Entity
public class Benutzer extends EntityTemplate<Benutzer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    private Rolle rolle;
    private String email;
    private String benutzername;
    private String passwort;

    public Benutzer() {
        super(Benutzer.class);
        this.rolle = Rolle.NONE;
    }

    public Benutzer(String email, String benutzername, String passwort) {
        this();
        this.email = email;
        this.benutzername = benutzername;
        this.passwort = passwort;
    }

    public Benutzer(String email, String benutzername, String passwort, Rolle rolle) {
        this(email, benutzername, passwort);
        this.rolle = rolle;
    }

    public Benutzer searchBenutzername(Session session, String benutzername) {
        session.beginTransaction();
        Benutzer result = session.createQuery("FROM Benutzer b WHERE b.benutzername = :username", Benutzer.class).setParameter("username", benutzername).uniqueResult();
        session.getTransaction().commit();

        return result;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Rolle getRolle() {
        return rolle;
    }

    public void setRolle(Rolle rolle) {
        this.rolle = rolle;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBenutzername() {
        return benutzername;
    }

    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }
}
