package com.mrbook.controller.base;

import java.util.HashMap;
import java.util.Map;

public class ControllerRepository {
    private Map<String, BaseController> controllers = new HashMap<>();
    private static ControllerRepository controllerRepository = new ControllerRepository();

    private ControllerRepository() {
    }

    public static ControllerRepository getInstance() {
        return controllerRepository;
    }

    public void addController(String path, BaseController controller) {
        controllers.put(path, controller);
    }

    public Map<String, BaseController> getControllers() {
        return controllers;
    }
}
