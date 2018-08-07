package com.tom.web.core.authorzation;

import com.tom.core.utils.JWTUtil;
import com.tom.model.User;
import com.tom.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class JwtRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    /**
     * 必须重写，否则不进入realm领域事件
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * @Auther: tom
     * @Date: 2018年6月26日
     * @Description: 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

    String username = JWTUtil.getUsername(principalCollection.toString());
    User user = userService.getUser(username,"");
    if(user==null) throw new AuthenticationException("用户名或密码错误");

    SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
    //simpleAuthorizationInfo.addRole(user.getRole());
    //Set<String> permission = new HashSet<>(Arrays.asList(user.getPermission().split(",")));
    //simpleAuthorizationInfo.addStringPermissions(permission);
    return simpleAuthorizationInfo;
    }

    /**
     * @Auther: tom
     * @Date: 2018年6月26日
     * @Description: 认证，账号信息判断，Session验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws
            AuthenticationException {
        if (!(authenticationToken instanceof JwtToken)) return null;
        String token = (String) authenticationToken.getPrincipal();
        String userName = JWTUtil.getUsername(token);
        if (userName == null) {
            throw new AuthenticationException("User didn't existed!");
        }
        User userInfo = userService.getUser(userName,"");
        if (userInfo == null) {
            throw new AuthenticationException("User didn't existed!");
        }

        if (!JWTUtil.verify(token, userName, userInfo.getPassword())) {
            throw new AuthenticationException("Username or password error");
        }
        return new SimpleAuthenticationInfo("jwt:" + token, authenticationToken.getCredentials(), getName());
    }
}
