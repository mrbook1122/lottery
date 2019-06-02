package com.mrbook.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        boolean login = false;
        if (cookies != null)
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("login"))
                    login = true;
            }
        if (login) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/index.jsp");
            dispatcher.forward(req, resp);
        } else {
            String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath + "/login");
        }
    }
}
