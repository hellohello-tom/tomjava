package com.tom.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.tom.core.service.BaseServiceImpl;
import com.tom.dao.UserMapper;
import com.tom.model.Users;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, Users> implements UserService {


    @Override
    public Users login(String username, String password, String verCode) {
        return new Users();
    }

    @Override
    public Users getUser(String username) {
        Users wrapper = new Users();
        wrapper.setAccount(username);
        return mapper.selectOne(wrapper);
    }
}
