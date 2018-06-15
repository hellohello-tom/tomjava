package com.tom.service;

import com.tom.core.service.BaseService;
import com.tom.model.Users;


public interface UserService extends BaseService<Users> {

    /**
     * @Auther: tom
     * @Date:
     * @Description: 用户登录
     */
    Users login(String username, String password, String verCode);
}
