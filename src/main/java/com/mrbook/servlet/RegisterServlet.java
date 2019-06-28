package com.mrbook.servlet;

import com.alibaba.fastjson.JSON;
import com.mrbook.dao.LoginUserDao;
import com.mrbook.entity.LoginUser;
import com.mrbook.entity.Result;
import com.mrbook.util.ParseJSON;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private LoginUserDao loginUserDao = LoginUserDao.getLoginUserDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/register.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setContentType("application/json;charset=UTF-8");
        LoginUser loginUser = ParseJSON.parseObject(req.getInputStream(), LoginUser.class);
        if (loginUserDao.findByName(loginUser.getName()) != null) {
            Result result = new Result(1, "用户名已存在");
            resp.getWriter().write(JSON.toJSONString(result));
        } else {
            loginUserDao.save(loginUser);
            Result result = new Result(200, "注册成功");
            resp.getWriter().write(JSON.toJSONString(result));
        }
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Headers", "content-type");
    }
}
