package com.tom.service;

import com.tom.core.exception.UserFriendlyException;
import com.tom.core.service.BaseServiceImpl;
import com.tom.dao.UserMapper;
import com.tom.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {


    @Override
    public User login(String username, String password) {
        //登录记录
        User userParams = new User();
        userParams.setAccount(username);
        userParams.setPassword(password);
        return mapper.selectOne(userParams);
//        if (userDetail == null)
//
//
//        //验证码验证
//        //生成redis session
//        return userDetail;
    }

    @Override
    public User getUser(String username) {
        User model = new User();
        model.setAccount("tom");
        model.setPassword("tom");
        return model;
    }
}
