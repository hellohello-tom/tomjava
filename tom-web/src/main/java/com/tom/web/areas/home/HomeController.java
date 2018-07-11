package com.tom.web.areas.home;

import com.tom.web.areas.AdminControllerBase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController extends AdminControllerBase {

    @RequestMapping(value = "/layout")
    public String layout() {
        return "admin/layout.html";
    }

    @RequestMapping(value = "/index")
    public String index(Model model) {

        return "admin/index.html";
    }
}
