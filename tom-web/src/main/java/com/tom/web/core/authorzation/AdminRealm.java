package com.tom.web.core.authorzation;

import com.tom.model.User;
import com.tom.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

public class AdminRealm extends AuthorizingRealm {

    @Resource
    UserService userService;

    /**
     * @Auther: 授权
     * @Date: 2018年7月11日
     * @Description: 授权用户的角色
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * @Auther: 登录功能
     * @Date: 2018年7月10日
     * @Description:
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws
            AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;

        User user = userService.login(usernamePasswordToken.getUsername(), usernamePasswordToken.getPassword()
                .toString());
        if(user==null)
            throw new AuthenticationException("账号或密码错误");
        //写入Session

        return new SimpleAuthenticationInfo(
                usernamePasswordToken.getUsername(), usernamePasswordToken.getPassword(), getName());

    }
}
