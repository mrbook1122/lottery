package com.mrbook.servlet;

import com.alibaba.fastjson.JSON;
import com.mrbook.dao.LoginUserDao;
import com.mrbook.entity.LoginUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/test")
public class TestServlet extends HttpServlet {
    private LoginUserDao loginUserDao = LoginUserDao.getLoginUserDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        LoginUser loginUser = loginUserDao.findByName("test");
        resp.setContentType("application/json;charset=UTF-8");
        resp.getWriter().write(JSON.toJSONString(loginUser));
    }
}
