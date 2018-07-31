package com.tom.web.areas.user.controllers;

import com.tom.web.areas.AdminControllerBase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "admin/user")
public class UserManageController extends AdminControllerBase {

    @RequestMapping(value = "index")
    public String index() {
        return "admin/user/index.html";
    }

    /**
     *
     * 功能描述: 
     *
     * @param: 用户管理
     * @return: 
     * @auther: 
     * @date:  
     */
    @RequestMapping(value = "add")
    public String add() {
        return "admin/user/index.html";
    }

    /**
     *
     * 功能描述: 
     *
     * @param: input
     * @return: 
     * @auther: 
     * @date:  
     */
    @RequestMapping(value = "add.action")
    @ResponseBody
    public String addOrUpdate(Model model) {
        return "admin/user/index.html";
    }
}
