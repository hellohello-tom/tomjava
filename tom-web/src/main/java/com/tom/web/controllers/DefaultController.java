package com.tom.web.controllers;

import com.tom.web.core.controller.TomControllerBase;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController extends TomControllerBase {
    @RequestMapping("/")
    public String index() {
        return "forward:admin/login.html";
    }
}
