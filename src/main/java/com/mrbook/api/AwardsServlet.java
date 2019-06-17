package com.mrbook.api;

import com.alibaba.fastjson.JSON;
import com.mrbook.entity.Award;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/api/awards")
public class AwardsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Award> awards = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Award award = new Award();
            award.setId(i);
            award.setName("奖项" + i);
            awards.add(award);
        }
        resp.setContentType("application/json;charset=UTF-8");
        resp.getWriter().write(JSON.toJSONString(awards));
    }
}
