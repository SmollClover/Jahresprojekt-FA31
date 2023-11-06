package de.hhbk.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import at.favre.lib.crypto.bcrypt.BCrypt;

@WebServlet("/api/login")
public class LoginController extends HttpServlet{
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            authenticate(request, response);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void authenticate(HttpServletRequest request, HttpServletResponse response)
    throws Exception {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println(username);
        // System.out.println(password);

        String hashedPassword = BCrypt.withDefaults().hashToString(14, password.toCharArray());

        // TODO Load Password from User Table
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), hashedPassword);

        System.out.println(result.verified);
        if(result.verified){
            Cookie auth = new Cookie("login", "true");
            auth.setMaxAge(60*60);
            response.addCookie(auth);
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