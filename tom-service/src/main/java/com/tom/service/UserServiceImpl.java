package com.tom.service;

import com.tom.core.exception.UserFriendlyException;
import com.tom.core.service.BaseServiceImpl;
import com.tom.dao.UserMapper;
import com.tom.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {


    @Override
    public User login(String username, String password, String verCode) {
        //登录记录
        User userParams = new User();
        userParams.setAccount(username);
        userParams.setPassword(password);
        User userDetail = mapper.selectOne(userParams);
        if (userDetail == null)
            throw new UserFriendlyException("用户名或密码错误");

        //验证码验证
        //生成redis session
        return userDetail;
    }

    @Override
    public User getUser(String username) {
        User model = new User();
        model.setAccount("tom");
        model.setPassword("tom");
        return model;
    }
}
