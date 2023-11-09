package de.hhbk.managers;

import de.hhbk.entities.Rolle;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

public class PermissionManager {
    private final Map<String, Rolle> perms = new HashMap<String, Rolle>();

    public PermissionManager() {
        this.perms.put("Benutzer", Rolle.NONE);
        this.perms.put("Login", Rolle.NONE);
        this.perms.put("Register", Rolle.NONE);
    }

    @NotNull
    public Rolle get(@NotNull String name) {
        Rolle r = this.perms.get(name);

        if (r == null) return Rolle.MITARBEITER;
        return r;
    }
}
