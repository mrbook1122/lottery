package com.mrbook.api;

import com.alibaba.fastjson.JSON;
import com.mrbook.dao.UserDao;
import com.mrbook.entity.Result;
import com.mrbook.entity.User;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@WebServlet("/api/users")
public class UserServlet extends HttpServlet {
    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        this.userDao = UserDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = userDao.getAllUsers();
        resp.setContentType("application/json;charset=UTF-8");
        resp.setHeader("Access-Control-Allow-Origin", "http://localhost:63342");
        resp.getWriter().write(JSON.toJSONString(users));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletInputStream inputStream = req.getInputStream();
        String s = "";
        while (!inputStream.isFinished()) {
            byte[] bytes = new byte[1024];
            inputStream.read(bytes, 0, 1024);
            s += new String(bytes, StandardCharsets.UTF_8);
        }
        resp.setContentType("aplication/json;charset=UTF-8");
        resp.setHeader("Access-Control-Allow-Origin", "http://localhost:63342");
        User user = JSON.parseObject(s, User.class);
        if (userDao.findByNumber(user.getNumber()) != null) {
            Result result = new Result(100, "学号已存在");
            resp.getWriter().write(JSON.toJSONString(result));
        } else {
            user.setComing(true);
            userDao.save(user);
        }
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "http://localhost:63342");
        resp.setHeader("Access-Control-Allow-Headers", "content-type");
    }

}
