package com.mrbook.servlet;

import com.alibaba.fastjson.JSON;
import com.mrbook.dao.LoginUserDao;
import com.mrbook.dto.LoginDTO;
import com.mrbook.entity.LoginUser;
import com.mrbook.entity.Result;
import com.mrbook.util.ParseJSON;

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
    private LoginUserDao loginUserDao = LoginUserDao.getLoginUserDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/login.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginDTO loginDTO = ParseJSON.parseObject(req.getInputStream(), LoginDTO.class);
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setContentType("application/json;charset=UTF-8");
        String vercode = (String) req.getSession().getAttribute("vercode");
        LoginUser loginUser = loginUserDao.findByName(loginDTO.getName());
        if (!loginDTO.getVercode().equals(vercode)) {
            Result result = new Result(23, "验证码错误");
            resp.getWriter().write(JSON.toJSONString(result));
        } else if (loginUser == null || !loginUser.getPass().equals(loginDTO.getPass())) {
            Result result = new Result(25, "用户名或密码错误！");
            resp.getWriter().write(JSON.toJSONString(result));
        } else {
            String contextPath = req.getContextPath();
            req.getSession().setAttribute("name", loginUser.getName());
            if (loginDTO.isRemember()) {
                Cookie cookie = new Cookie("login", loginDTO.getName());
                if (loginDTO.isRemember())
                    cookie.setMaxAge(1000 * 3600 * 24);
                resp.addCookie(cookie);
            }
            resp.sendRedirect(contextPath + "/");
        }
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Headers", "content-type");
    }
}
