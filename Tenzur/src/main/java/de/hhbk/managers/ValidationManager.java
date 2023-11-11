package de.hhbk.managers;

import java.util.HashMap;
import java.util.Map;

public class ValidationManager {
    /**
     * @param username the user input for the username
     * @param password the user input for the password
     * @return a map containing a status for success or failure and messages which of the inputs was invalid
     */
    public Map<Boolean, String> validateLogin(String username, String password) {
        Map<Boolean, String> validation = new HashMap<>();
        return validation;
    }

    /**
     * @param username the user input for the username
     * @param password the user input for the password
     * @return a map containing a status for success or failure and messages which of the inputs was invalid
     */
    public Map<Boolean, String> validateRegister(String username, String password) {
        Map<Boolean, String> validation = new HashMap<>();
        return validation;
    }
}