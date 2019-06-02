package com.mrbook.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/login.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String pass = req.getParameter("pass");
        boolean remember = false;
        if (req.getParameter("remember") != null)
            remember = true;
        String contextPath = req.getContextPath();
        if (name != null && pass != null) {
            if (name.equals("user") && pass.equals("pass")) {
                Cookie cookie = new Cookie("login", "true");
                if (remember)
                    cookie.setMaxAge(3600 * 24 * 7);
                resp.addCookie(cookie);
                resp.sendRedirect(contextPath);
                return;
            }
        }
        resp.sendRedirect(contextPath + "/login");
    }
}
