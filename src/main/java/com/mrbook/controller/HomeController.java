package com.mrbook.controller;

import com.mrbook.controller.base.BaseController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeController extends BaseController {
    @Override
    public String setPath() {
        return "/";
    }

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) {

    }
}
