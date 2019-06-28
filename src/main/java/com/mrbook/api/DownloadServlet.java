package com.mrbook.api;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/download")
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String image = req.getParameter("d");
        resp.setHeader("Content-Disposition", "attachment;fileName=" + image);
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setContentType("image/jpeg");
        RequestDispatcher dispatcher = req.getRequestDispatcher("/" + image);
        dispatcher.forward(req, resp);
    }
}
