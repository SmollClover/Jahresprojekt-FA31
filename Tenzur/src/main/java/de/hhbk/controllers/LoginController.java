package de.hhbk.controllers;

import at.favre.lib.crypto.bcrypt.BCrypt;
import de.hhbk.entities.Benutzer;
import de.hhbk.managers.AuthorizationManager;
import de.hhbk.managers.DatabaseManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/api/login")
public class LoginController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            authenticate(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void authenticate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        AuthorizationManager manager = (AuthorizationManager) request.getServletContext().getAttribute("Auth");
        DatabaseManager DB = (DatabaseManager) request.getServletContext().getAttribute("DB");

        Session session = DB.getSessionFactory().openSession();
        Benutzer user = new Benutzer().searchBenutzername(session, username);
        System.out.println(user);

        if(user != null){
            BCrypt.Result login_result = manager.verifyPassword(password, user.getPasswort());
    
            if (login_result.verified) {
                String jwt = manager.generateJWT(user.getId());
                Cookie auth = new Cookie("authorization", jwt);
                response.addCookie(auth);
    
                // Response Body
                PrintWriter out = response.getWriter();
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                out.print("{\"status\": \"success\"}");
                out.flush();
            } else {                
                // Response Body
                PrintWriter out = response.getWriter();
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                out.print("{\"status\": \"failed\"}");
                out.flush();
            }
        }else{
            // Response Body
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print("{\"status\": \"failed\"}");
            out.flush();
        }
        
    }
}