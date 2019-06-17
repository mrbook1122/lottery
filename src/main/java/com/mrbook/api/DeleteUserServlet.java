package com.mrbook.api;

import com.alibaba.fastjson.JSON;
import com.mrbook.dao.UserDao;
import com.mrbook.entity.User;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/api/user/delete")
public class DeleteUserServlet extends HttpServlet {
    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        this.userDao = UserDao.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletInputStream inputStream = req.getInputStream();
        StringBuilder s = new StringBuilder();
        while (!inputStream.isFinished()) {
            byte[] bytes = new byte[1024];
            inputStream.read(bytes, 0, 1024);
            s.append(new String(bytes, StandardCharsets.UTF_8));
        }
        User user = JSON.parseObject(s.toString(), User.class);
        userDao.deleteByNumber(user.getNumber());
        resp.setContentType("application/json;charset=UTF-8");
        resp.addHeader("Access-Control-Allow-Origin", "http://localhost:63342");
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "http://localhost:63342");
        resp.setHeader("Access-Control-Allow-Headers", "content-type");
    }
}
