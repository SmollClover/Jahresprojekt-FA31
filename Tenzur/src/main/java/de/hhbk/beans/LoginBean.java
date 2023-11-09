package de.hhbk.beans;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.Cookie;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Named(value = "login")
@ViewScoped
public class LoginBean implements Serializable {
    public LoginBean() {
    }

    public void setLogin() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        String baseUrl = context.getRequestScheme() + "://" + context.getRequestServerName() + ":" + context.getRequestServerPort() + context.getRequestContextPath();

        try {
            URL url = new URL(baseUrl + "/api/login");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            String formData = "username=" + URLEncoder.encode("tobias", StandardCharsets.UTF_8) +
                    "&password=" + URLEncoder.encode("passwort", StandardCharsets.UTF_8);

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
                System.out.println(response.toString());
            }

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
