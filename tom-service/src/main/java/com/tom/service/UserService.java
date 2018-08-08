package com.tom.service;

import com.tom.core.service.BaseService;
import com.tom.model.User;
import com.tom.model.dto.GetUserLoginDto;


public interface UserService extends BaseService<User> {

    /**
     * @Auther: tom
     * @Date:
     * @Description: 用户登录
     */
    GetUserLoginDto login(String username, String password);


    /**
     * @Auther: tom
     * @Date:
     * @Description: 用户登录获取授权信息
     */
    GetUserLoginDto getUserPermission(String account);

    /**
     * @Auther: tom
     * @Date:
     * @Description: 获取用户信息
     */
    User getUser(String username, String password);
}
