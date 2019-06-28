package com.mrbook.api;

import com.alibaba.fastjson.JSON;
import com.mrbook.dao.Lottery;
import com.mrbook.dao.RecordDao;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

@WebServlet("/api/lottery")
public class LotteryServlet extends HttpServlet {
    private UserDao userDao = UserDao.getInstance();
    private RecordDao recordDao = RecordDao.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        resp.addHeader("Access-Control-Allow-Origin", "*");
        String s = "";
        ServletInputStream inputStream = req.getInputStream();
        while (!inputStream.isFinished()) {
            byte[] bytes = new byte[1024];
            inputStream.read(bytes, 0, 1024);
            s += new String(bytes, StandardCharsets.UTF_8);
        }
        Lottery lottery = JSON.parseObject(s, Lottery.class);
        List<User> users = userDao.getUsers();
        List<User> result = lottery(users, lottery.getNum(), lottery.getAwards());
        resp.getWriter().write(JSON.toJSONString(result));
    }

    private List<User> lottery(List<User> users, int number, String award) {
        List<User> result = new ArrayList<>();
        if (number >= users.size()) {
            recordDao.saveRecord(award, users.size(), Calendar.getInstance().getTime());
            return users;
        }
        Random random = new Random();
        for (int i = 0; i < number; i++) {
            result.add(users.remove(random.nextInt(users.size())));
        }
        recordDao.saveRecord(award, number, Calendar.getInstance().getTime());
        return result;
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Headers", "content-type");
    }
}
