package com.tom.web.areas.user.controllers;

import com.tom.core.model.AjaxResponse;
import com.tom.core.utils.AjaxCallBacker;
import com.tom.core.utils.JWTUtil;
import com.tom.service.UserService;
import com.tom.web.areas.ApiControllerBase;
import com.tom.web.areas.user.models.UserLoginInputDto;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class LoginController extends ApiControllerBase {

    @Resource
    UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public AjaxResponse login(@Validated UserLoginInputDto inputDto, BindingResult bindingResult) {
        CheckModelStatus(bindingResult);
        userService.login(inputDto.getUserName(), inputDto.getPassWord(), inputDto.getVerCode());
        return new AjaxResponse();
    }

    @RequestMapping("/sign")
    public AjaxResponse sign() {
        return AjaxCallBacker.Success(JWTUtil.sign("tom", "tom"), null);
    }
}
