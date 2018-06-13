package com.tom.web.areas.user.controllers;

import com.tom.core.model.AjaxResponse;
import com.tom.web.areas.ApiControllerBase;
import com.tom.web.areas.user.models.UserLoginInputDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class LoginController extends ApiControllerBase {
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public AjaxResponse login(UserLoginInputDto input) {
        return new AjaxResponse();
    }
}
