package com.tom.web.core.filters.shiro;

import com.google.common.base.Strings;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class AuthorizationInfoFilter extends AuthorizationFilter {


    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o)
            throws Exception {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String authorizationToken = req.getHeader("Authorization");

        Subject sub = getSubject(servletRequest, servletResponse);
        //sub.login(authorizationToken);
        return Strings.isNullOrEmpty(authorizationToken);

    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) {
        return true;
    }

}
