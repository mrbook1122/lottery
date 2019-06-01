package com.mrbook.controller.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseController {
    private ControllerRepository controllerRepository = ControllerRepository.getInstance();

    public abstract String setPath();

    {
        addController();
    }

    private void addController() {
        controllerRepository.addController(setPath(), this);
    }

    public abstract void process(HttpServletRequest request, HttpServletResponse response);
}
