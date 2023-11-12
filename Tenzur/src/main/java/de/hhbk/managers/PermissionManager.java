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

    public PermissionManager() {
        this.perms.put("Benutzer", Rolle.NONE);
        this.perms.put("Login", Rolle.NONE);
    }

    @NotNull
    public Rolle get(@NotNull String name) {
        Rolle r = this.perms.get(name);

        if (r == null) return Rolle.MITARBEITER;
        return r;
    }

    public Rolle current(@NotNull Session session, @NotNull long userId) {
        Benutzer result = new Benutzer().getById(session, userId);

        if (result == null) return null;
        return result.getRolle();
    }

    @NotNull
    public boolean has(@NotNull Session session, @NotNull long userId, @NotNull String name) {
        if (this.get(name) == Rolle.NONE) return true;

        Rolle result = this.current(session, userId);
        if (result == null) return false;

        return result.ordinal() >= this.get(name).ordinal();
    }
}
