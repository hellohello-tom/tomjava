package com.tom.web.areas.user.controllers;

import com.tom.core.model.AjaxResponse;
import com.tom.service.UserService;
import com.tom.web.areas.ApiControllerBase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/user")
public class UserController extends ApiControllerBase {
    @Resource
    UserService userService;

    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public AjaxResponse login() {
        return new AjaxResponse();
    }
}
