package com.tom.web.core.filters.shiro;

import com.tom.core.utils.ReponseUtil;
import com.tom.model.User;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class IdentityAuthorizationFilter extends AuthorizationInfoFilter {

    /**
     * @Auther: tom
     * @Date: 2018年7月10日
     * @Description: 判断SESSION中的对象是否存在
     * 不存在时判断cookie cookie存在时重新标记Session
     * Cookie不存在跳转到登录页面或返回ajax信息
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(servletRequest);
        User user = (User) httpServletRequest.getSession().getAttribute("userInfo");
        return user != null;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException

    {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        //判断当前类型如果是ajax请求返回ajax信息，页面访问返回登录页
        if (ReponseUtil.isAjax(httpServletRequest))
            return super.onAccessDenied(request, response);
        else {
            //给出页面超时响应提醒
            WebUtils.issueRedirect(request, response, "/admin/login.html?returnUrl="+httpServletRequest.getRequestURI());
            return false;
        }
    }
}
