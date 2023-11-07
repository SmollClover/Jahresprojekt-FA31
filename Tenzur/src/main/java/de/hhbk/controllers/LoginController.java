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

        // System.out.println(username);
        // System.out.println(password);

        AuthorizationManager manager = (AuthorizationManager) request.getServletContext().getAttribute("Auth");

        String hashedPassword = manager.hashPassword(password);

        // TODO Load Password from User Table by username/email
        BCrypt.Result result = manager.verifyPassword(password, hashedPassword);

        // System.out.println(result.verified);
        if (result.verified) {
            String jwt = manager.generateJWT();
            Cookie auth = new Cookie("authorization", jwt);
            response.addCookie(auth);
        } else {
            response.sendError(400);
        }

        // System.out.println(hashedPassword);

        // if (loginDao.validate(username, password)) {
        //     RequestDispatcher dispatcher = request.getRequestDispatcher("login-success.jsp");
        //     dispatcher.forward(request, response);
        // } else {
        //     throw new Exception("Login not successful..");
        // }
    }
}