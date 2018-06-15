package com.tom.web.core.config;

import com.tom.web.core.authorzation.LoginRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean(name = "loginRealm")
    public LoginRealm getLoginRealm() {
        LoginRealm realm = new LoginRealm();
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
        sfb.setFilters(filters);

        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/login", "verCode,anon");
        filterMap.put("/**", "authc");

        sfb.setUnauthorizedUrl("/403");

        sfb.setFilterChainDefinitionMap(filterMap);
        return sfb;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getSecurityManager(@Qualifier("loginRealm") LoginRealm loginRealm) {
        DefaultWebSecurityManager dwm = new DefaultWebSecurityManager();
        dwm.setRealm(loginRealm);
        //dwm.setCacheManager(getCacheManager());
        return dwm;
    }


    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor as=new AuthorizationAttributeSourceAdvisor();
        as.setSecurityManager(securityManager);
        return as;
    }
}
