package com.tom.web.core.filters.shiro;

import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class IdentityAuthorizationFilter extends AuthorizationInfoFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(servletRequest);
        return false;

    }
}
