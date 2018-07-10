package com.tom.web.areas.home;

import com.tom.web.areas.AdminControllerBase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController extends AdminControllerBase {

    @RequestMapping(value = "/index")
    public String index(@RequestParam(required=false) String returnUrl, Model model) {
        model.addAttribute("returnUrl", returnUrl+"123");
        model.addAttribute("test1", returnUrl);
        return "admin/index.html";
    }
}
