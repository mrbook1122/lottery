package com.mrbook.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class AuthenticationFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String path = req.getServletPath();
        if (path.equals("/") || path.equals("/user") || path.equals("/user/info") || path.equals("/record")) {
            Cookie[] cookies = req.getCookies();
            boolean login = false;
            if (cookies != null)
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("login"))
                        login = true;
                }
            if (!login) {
                res.sendRedirect(req.getContextPath() + "/login");
            } else chain.doFilter(req, res);
        } else chain.doFilter(req, res);
    }
}
