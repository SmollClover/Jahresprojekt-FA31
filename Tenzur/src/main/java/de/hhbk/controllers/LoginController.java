package de.hhbk.controllers;

import at.favre.lib.crypto.bcrypt.BCrypt;
import de.hhbk.entities.Benutzer;
import de.hhbk.managers.AuthorizationManager;
import de.hhbk.managers.DatabaseManager;
import org.hibernate.Session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

// API Schnittstelle für den Login der Bneutzer, basierend auf den JavaX Servlets
@WebServlet("/api/login")
public class LoginController extends HttpServlet {

    /**
     * @param request die request vom client
     * @param response die response zum client
     * Bei jeder GET-Anfrage an die Route leitet der Server auf die Anmeldeseite um
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/login.xhtml");
    }

    /**
     * @param request die request vom client
     * @param response die response zum client
     * Bei jeder POST-Anfrage an die Route versucht der Server, den Benutzer zu authentifizieren
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            authenticate(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param request die request vom client, sollte den Benutzernamen und das Passwort enthalten
     * @param response die response zum client
     * Bei jeder POST-Anfrage an die Route versucht der Server, den Benutzer zu authentifizieren
     * Wenn kein Benutzername oder Passwort angegeben wird, wird ein Fehler zurückgegeben
     * Wenn kein Benutzer in der Datenbank gefunden werden konnte, wird ein Fehler zurückgegeben
     * Wenn der Benutzer authentifiziert werden kann, gibt die Funktion ein verschlüsseltes JWT zurück
     */
    private void authenticate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if(username == "" || password == ""){ response.sendError(400); }

        AuthorizationManager manager = (AuthorizationManager) request.getServletContext().getAttribute("Auth");
        DatabaseManager DB = (DatabaseManager) request.getServletContext().getAttribute("DB");

        Session session = DB.getSessionFactory().openSession();
        Benutzer user = new Benutzer().searchBenutzername(session, username);

        if (user != null) {
            BCrypt.Result login_result = manager.verifyPassword(password, user.getPasswort());

            if (login_result.verified) {
                String jwt = manager.generateJWT(user.getId());

                // Response Body
                PrintWriter out = response.getWriter();
                response.setContentType("plain/text");
                response.setCharacterEncoding("UTF-8");
                out.print(jwt);
                out.flush();
            } else {
                response.sendError(400);
            }
        } else {
            response.sendError(400);
        }

    }
}