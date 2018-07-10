package com.tom.web.areas.user.controllers;

import com.tom.core.exception.UserFriendlyException;
import com.tom.core.model.AjaxResponse;
import com.tom.core.utils.AjaxCallBacker;
import com.tom.core.utils.JWTUtil;
import com.tom.model.User;
import com.tom.service.UserService;
import com.tom.web.areas.ApiControllerBase;
import com.tom.web.areas.user.models.UserLoginInputDto;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class ApiLoginController extends ApiControllerBase {

    @Resource
    UserService userService;

    @RequestMapping(value = "/getToken", method = RequestMethod.POST)
    public AjaxResponse login(@RequestBody @Validated UserLoginInputDto inputDto, BindingResult bindingResult) {
        CheckModelStatus(bindingResult);
        AjaxResponse ajaxResponse = AjaxCallBacker.Faild();
        User user =userService.getUser(inputDto.getUserName());

        String sign =JWTUtil.sign(user.getAccount(), user.getPassword());
        Map data = new HashMap<String,String>();
        data.put("sign",sign);
        ajaxResponse.setdata(data);
        return ajaxResponse;
    }

    @RequestMapping(value = "/sign", method = RequestMethod.POST)
    public AjaxResponse sign() {

        User user = new User();
        user.setPassword("123");
        redisCacher.set("test", user);

        return AjaxCallBacker.Success(JWTUtil.sign("tom", "tom"), null);
    }

}
