package de.hhbk.beans;

import de.hhbk.managers.AuthorizationManager;
import de.hhbk.managers.PermissionManager;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.Cookie;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;

@Named(value = "login")
@ViewScoped
public class LoginBean extends BeanTemplate {
    private String username = "";
    private String password = "";

    public LoginBean() {
        super();
    }

    /**
     * Holt den AuthManager, lädt das Auth-Cookie der Request und validiert dieses wenn es existiert
     */
    @PostConstruct
    private void init() {
        AuthorizationManager manager = (AuthorizationManager) this.ctx.getAttribute("Auth");
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> cookies = context.getRequestCookieMap();

        Cookie authCookie = (Cookie) cookies.get("authorization");
        if (authCookie == null) return;
        if (manager.validateToken(authCookie.getValue()) == null) return;

        try {
            context.redirect(context.getRequestContextPath());
        } catch (IOException ignored) {
        }
    }

    /**
     * Erstellt eine request an die Login API Route mit den Eingaben des Benutzers,
     * setzt das JWT bei Erfolg
     * und setzt den authorization Cookie bei einem Fehler auf 'null'
     */
    public void login() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        String baseUrl = context.getRequestScheme() + "://" + context.getRequestServerName() + ":" + context.getRequestServerPort() + context.getRequestContextPath();

        try {
            URL url = new URL(baseUrl + "/api/login");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            String formData = "username=" + URLEncoder.encode(this.username, StandardCharsets.UTF_8) + "&password=" + URLEncoder.encode(this.password, StandardCharsets.UTF_8);

            try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
                byte[] formDataBytes = formData.getBytes(StandardCharsets.UTF_8);
                wr.write(formDataBytes);
            }

            if (conn.getResponseCode() != 200) return;

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String line;
                StringBuilder response = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                context.addResponseCookie("authorization", response.toString(), null);
                context.redirect(context.getRequestContextPath());
            }

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
