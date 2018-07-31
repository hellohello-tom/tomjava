package com.tom.web.areas.home;

import com.tom.web.areas.AdminControllerBase;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorController extends AdminControllerBase {

    @RequestMapping(value = "/403.html")
    public String Index403() {
        return "admin/403.html";
    }

}
