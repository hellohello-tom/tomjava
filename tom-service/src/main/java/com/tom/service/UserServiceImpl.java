package com.tom.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.tom.core.exception.UserFriendlyException;
import com.tom.core.service.BaseServiceImpl;
import com.tom.dao.UserMapper;
import com.tom.dao.sys.PermissionMapper;
import com.tom.model.Permission;
import com.tom.model.User;
import com.tom.model.dto.GetUserLoginDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {

    @Resource
    PermissionMapper permissionMapper;

    @Override
    public GetUserLoginDto login(String username, String password) {
        //登录记录
        GetUserLoginDto userInfo = mapper.login(username, password);
        if (userInfo != null) {
            Wrapper<Permission> conditions = new EntityWrapper<>();
            conditions = conditions.eq("del_status", 0);
            if (userInfo.getRoles().size() > 0) {
                List<Integer> roleIds = userInfo.getRoles().stream().map(GetUserLoginDto.GetUserLoginRoleDto::getRid)
                        .collect(Collectors.toList());
                conditions = conditions.in("role_id", roleIds);
            } else {
                conditions = conditions.where("1=1");
            }
            conditions = conditions.orNew("user_id={0}", userInfo.getId()).groupBy("action,operator_name");
            List<String> permissions = permissionMapper.selectList(conditions).stream().map
                    (Permission::getOperatorName).collect(Collectors.toList());
            userInfo.setOperators(permissions);
        }
        return userInfo;
    }

    @Override
    public User getUser(String username) {
        User model = new User();
        model.setAccount("tom");
        model.setPassword("tom");
        return model;
    }
}
