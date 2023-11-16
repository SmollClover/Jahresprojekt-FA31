package de.hhbk.managers;

import java.util.HashMap;
import java.util.Map;

public class ValidationManager {
    /**
     * @param username die Benutzereingabe für den Benutzernamen
     * @param password die Benutzereingabe für das Passwort
     * @return eine Map, die einen Status für Erfolg oder Misserfolg und Meldungen darüber enthält, welche der Eingaben ungültig war
     */
    public Map<Boolean, String> validateLogin(String username, String password) {
        Map<Boolean, String> validation = new HashMap<>();
        return validation;
    }

    /**
     * @param username die Benutzereingabe für den Benutzernamen
     * @param password die Benutzereingabe für das Passwort
     * @return eine Map, die einen Status für Erfolg oder Misserfolg und Meldungen darüber enthält, welche der Eingaben ungültig war
     */
    public Map<Boolean, String> validateRegister(String username, String password) {
        Map<Boolean, String> validation = new HashMap<>();
        return validation;
    }
}