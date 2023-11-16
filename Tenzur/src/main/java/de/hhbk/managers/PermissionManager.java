package de.hhbk.managers;

import de.hhbk.entities.Benutzer;
import de.hhbk.entities.Rolle;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

public class PermissionManager {
    private final Map<String, Rolle> perms = new HashMap<String, Rolle>();

    /**
     * Setzt die minimale Berechtigungs Rolle für die Seiten die ein Nutzer benötigt um mit dieser zu interagieren
     */
    public PermissionManager() {
        this.perms.put("Login", Rolle.NONE);
        this.perms.put("Empty", Rolle.MIETER);
        this.perms.put("Benutzer", Rolle.MITARBEITER);
        this.perms.put("Logout", Rolle.MIETER);
    }

    /**
     * 
     * @param name die Seite von welche die min. Rolle gelesen werden soll
     * @return die Rolle, wenn keine Rolle existiert gebe die niedrigste zuräck
     */
    @NotNull
    public Rolle get(@NotNull String name) {
        Rolle r = this.perms.get(name);

        if (r == null) return Rolle.MITARBEITER;
        return r;
    }

    /**
     * 
     * @param session die Session Instanz
     * @param userId die ID des Benutzers dessen Rolle gelesen werden soll
     * @return die Rolle falls eine gefunden wurde ansonsten 'null'
     */
    public Rolle current(@NotNull Session session, @NotNull long userId) {
        Benutzer result = new Benutzer().getById(session, userId);

        if (result == null) return null;
        return result.getRolle();
    }

    /**
     * 
     * @param session die Session Instanz
     * @param userId die ID des Benutzers dessen Rolle gelesen werden soll
     * @param name der Name der Seite die benutzt wird
     * @return 'true' wenn die Berechtigung des Benutzers ausreicht, 'false' wenn nicht
     */
    @NotNull
    public boolean has(@NotNull Session session, @NotNull long userId, @NotNull String name) {
        if (this.get(name) == Rolle.NONE) return true;

        Rolle result = this.current(session, userId);
        if (result == null) return false;

        return result.ordinal() >= this.get(name).ordinal();
    }
}
