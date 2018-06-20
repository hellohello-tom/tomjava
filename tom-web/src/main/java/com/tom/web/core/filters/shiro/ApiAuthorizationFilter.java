package com.tom.web.core.filters.shiro;

import com.google.common.base.Strings;
import com.tom.core.utils.HttpCode;
import com.tom.core.utils.ReponseUtil;
import com.tom.model.Users;
import com.tom.web.core.authorzation.JwtToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: tom
 * @Date: 2018年6月19日
 * @Description: 更改为jwt或oauth类似这样的支持分布式的验证模式
 */
public class ApiAuthorizationFilter extends AuthorizationInfoFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) {

        HttpServletRequest httpServletRequest = WebUtils.toHttp(servletRequest);
        String jwtToken = httpServletRequest.getHeader("Authorization");
        if (!Strings.isNullOrEmpty(jwtToken)) {
            JwtToken token = new JwtToken(jwtToken);
            try {
                getSubject(servletRequest, servletResponse).login(token);
                return true;
            } catch (AuthenticationException ex) {
                System.out.println(ex);
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        return false;
    }
}
