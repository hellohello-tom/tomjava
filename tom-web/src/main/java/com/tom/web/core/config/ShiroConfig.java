package com.tom.web.core.config;

import com.tom.web.core.authorzation.AdminRealm;
import com.tom.web.core.authorzation.JwtRealm;
import com.tom.web.core.filters.shiro.ApiAuthorizationFilter;
import com.tom.web.core.filters.shiro.IdentityAuthorizationFilter;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.*;

@Configuration
public class ShiroConfig {

    @Bean(name = "jwtRealm")
    public JwtRealm getJwtRealm() {
        JwtRealm realm = new JwtRealm();
        //realm.setCredentialsMatcher(getRetryLimitCredentialsMatcher());
        return realm;
    }

    @Bean(name = "adminRealm")
    public AdminRealm getAdminRealm() {
        AdminRealm realm = new AdminRealm();
        //realm.setCredentialsMatcher(getRetryLimitCredentialsMatcher());
        return realm;
    }


    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") SecurityManager
                                                                    securityManager) {

        ShiroFilterFactoryBean sfb = new ShiroFilterFactoryBean();
        sfb.setSecurityManager(securityManager);
        sfb.setLoginUrl("/login");
        //自定义过滤器
        Map<String, Filter> filters = new HashMap<>();

        filters.put("jwtfilter", new ApiAuthorizationFilter());
        filters.put("adminfilter", new IdentityAuthorizationFilter());
        sfb.setFilters(filters);

        Map<String, String> filterMap = new LinkedHashMap<>();

        filterMap.put("/admin/login.html", "anon");
        //api相关使用jwttoken 进行判断
        filterMap.put("/api/**", "jwtfilter");
        //后台相关权限接口认证
        filterMap.put("/admin/**","adminfilter");

        sfb.setUnauthorizedUrl("/403");
        sfb.setLoginUrl("/admin/login.html");
        sfb.setFilterChainDefinitionMap(filterMap);
        return sfb;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getSecurityManager(@Qualifier("jwtRealm") JwtRealm jwtRealm,@Qualifier("adminRealm") AdminRealm adminRealm) {
        DefaultWebSecurityManager dwm = new DefaultWebSecurityManager();
        Collection<Realm> realmList = new ArrayList<>();
        realmList.add(jwtRealm);
        realmList.add(adminRealm);
        dwm.setRealms(realmList);
        //dwm.setCacheManager(getCacheManager());
        return dwm;
    }


    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(@Qualifier("securityManager")
                                                                                              SecurityManager
                                                                                              securityManager) {
        AuthorizationAttributeSourceAdvisor as = new AuthorizationAttributeSourceAdvisor();
        as.setSecurityManager(securityManager);
        return as;
    }


    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }
}
