package com.mrbook.api;

import com.alibaba.fastjson.JSON;
import com.mrbook.dao.UserDao;
import com.mrbook.entity.Result;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/api/user/update")
public class UpdateServlet extends HttpServlet {
    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        this.userDao = UserDao.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        ServletInputStream inputStream = req.getInputStream();
        String s = "";
        while (!inputStream.isFinished()) {
            byte[] bytes = new byte[1024];
            inputStream.read(bytes, 0, 1024);
            s += new String(bytes, StandardCharsets.UTF_8);
        }
        Result result = JSON.parseObject(s, Result.class);
        userDao.updateUser(result);
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Headers", "content-type");
    }
}
