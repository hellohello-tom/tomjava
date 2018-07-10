package com.tom.web.areas.user.controllers;

import com.tom.core.model.AjaxResponse;
import com.tom.core.utils.AjaxCallBacker;
import com.tom.web.areas.ApiControllerBase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class ApiUserController extends ApiControllerBase {

    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public AjaxResponse userInfo() {
        AjaxResponse ajaxResponse = AjaxCallBacker.Faild();
        return ajaxResponse;
    }
}
