package com.tom.web.core.filters.shiro;

import com.tom.core.utils.HttpCode;
import com.tom.core.utils.ReponseUtil;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public abstract class AuthorizationInfoFilter extends AuthorizationFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) {
        HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
        ReponseUtil.ajaxFailed(httpServletResponse, HttpServletResponse.SC_FORBIDDEN, HttpCode.FORBIDDEN, "您没有权限访问");
        return false;
    }
}
