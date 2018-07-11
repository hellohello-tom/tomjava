package com.tom.service;

import com.tom.core.service.BaseService;
import com.tom.model.User;


public interface UserService extends BaseService<User> {

    /**
     * @Auther: tom
     * @Date:
     * @Description: 用户登录
     */
    User login(String username, String password);

    /**
     * @Auther: tom
     * @Date:
     * @Description: 获取用户信息
     */
    User getUser(String username);
}
