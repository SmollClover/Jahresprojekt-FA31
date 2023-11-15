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
     * @param request the request from the client
     * @param response the response to the client
     * Upon every GET request to the route the server redirects to the login page
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/login.xhtml");
    }

    /**
     * @param request the request from the client
     * @param response the response to the client
     * Upon every POST request to the route the server tries to authenticate the user
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            authenticate(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param request the request from the client, should contain the username and password
     * @param response the response to the client
     * Upon every POST request to the route the server tries to authenticate the user
     * If no username or password are submitted returns an error
     * If no user could be found in the database returns an error
     * If the user can be authenticated return an encrypted JWT
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