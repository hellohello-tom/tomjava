package com.tom.web.core.authorzation;

import com.tom.core.utils.MD5Util;
import com.tom.model.User;
import com.tom.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
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
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //authorizationInfo.addStringPermission("admin");
        return authorizationInfo;
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
        //加盐
        String encryPassword = MD5Util.MD5(String.valueOf(usernamePasswordToken.getPassword()));
        User user = userService.login(usernamePasswordToken.getUsername(), encryPassword);
        if (user == null)
            throw new AuthenticationException("账号或密码错误");
        //写入Session

        return new SimpleAuthenticationInfo(
                usernamePasswordToken.getUsername(), usernamePasswordToken.getPassword(), getName());

    }
}
