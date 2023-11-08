package de.hhbk.controllers;

import at.favre.lib.crypto.bcrypt.BCrypt;
import de.hhbk.managers.AuthorizationManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void authenticate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        AuthorizationManager manager = (AuthorizationManager) request.getServletContext().getAttribute("Auth");

        String hashedPassword = manager.hashPassword(password);

        // TODO Load Password from User Table by username/email
        BCrypt.Result result = manager.verifyPassword(password, hashedPassword);

        if (result.verified) {
            String jwt = manager.generateJWT();
            Cookie auth = new Cookie("authorization", jwt);
            response.addCookie(auth);

            // Response Body
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print("{\"status\": \"success\"}");
            out.flush();
        } else {
            response.sendError(400);
            
            // Response Body
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print("{\"status\": \"failed\"}");
            out.flush();
        }
    }
}