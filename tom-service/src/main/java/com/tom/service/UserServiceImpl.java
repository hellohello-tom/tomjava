package com.tom.service;

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
}
