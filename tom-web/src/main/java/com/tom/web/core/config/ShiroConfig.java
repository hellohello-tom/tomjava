package com.tom.web.core.config;

import com.tom.core.redis.RedisCacher;
import com.tom.web.core.authorzation.AdminRealm;
import com.tom.web.core.authorzation.JwtRealm;
import com.tom.web.core.cache.RedisShiroCacheManager;
import com.tom.web.core.cache.RedisShiroSessionDAO;
import com.tom.web.core.filters.shiro.ApiAuthorizationFilter;
import com.tom.web.core.filters.shiro.IdentityAuthorizationFilter;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.Filter;
import java.util.*;

@Configuration
@Component
public class ShiroConfig {

    /**
     * @Auther: tom
     * @Date: 2018年7月12日
     * @Description: 定义在redis内 session的有效周期
     */
    @Value("${tom.shiro.session.expire:120000}")
    private int RedisSessionExpireTime;

    @Resource
    RedisCacher redisCacher;

    @Bean(name = "jwtRealm")
    public JwtRealm getJwtRealm() {
        JwtRealm realm = new JwtRealm();
        //realm.setCredentialsMatcher(getRetryLimitCredentialsMatcher());
        return realm;
    }

    @Bean(name = "adminRealm")
    public AdminRealm getAdminRealm(
            //@Qualifier("hashedCredentialsMatcher") HashedCredentialsMatcher matcher
    ) {
        AdminRealm realm = new AdminRealm();
        //realm.setCredentialsMatcher(matcher);
        return realm;
    }

    /**
     * @Auther: tom
     * @Date: 密码加盐
     * @Description:
     */
//    @Bean("hashedCredentialsMatcher")
//    public HashedCredentialsMatcher hashedCredentialsMatcher() {
//        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
//        //指定加密方式为MD5
//        credentialsMatcher.setHashAlgorithmName("MD5");
//        //加密次数
//        credentialsMatcher.setHashIterations(1024);
//        credentialsMatcher.setStoredCredentialsHexEncoded(true);
//        return credentialsMatcher;
//    }
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
        filterMap.put("/admin/**", "adminfilter");

        sfb.setUnauthorizedUrl("/403");
        sfb.setLoginUrl("/admin/login.html");
        sfb.setFilterChainDefinitionMap(filterMap);
        return sfb;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getSecurityManager(
            @Qualifier("jwtRealm") JwtRealm jwtRealm,
            @Qualifier("adminRealm") AdminRealm adminRealm,
            @Qualifier("sessionManager") SessionManager sessionManager,
            @Qualifier("redisCacheManager") CacheManager redisCacheManager) {
        DefaultWebSecurityManager dwm = new DefaultWebSecurityManager();
        Collection<Realm> realmList = new ArrayList<>();
        realmList.add(jwtRealm);
        realmList.add(adminRealm);
        dwm.setRealms(realmList);

        dwm.setSessionManager(sessionManager);
        dwm.setCacheManager(redisCacheManager);
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

    @Bean(name = "sessionManager")
    public SessionManager sessionManager(@Qualifier("sessionDAO") SessionDAO sessionDAO, @Qualifier
            ("redisCacheManager") CacheManager cacheManager) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(sessionDAO);
        sessionManager.setGlobalSessionTimeout(1800);
        sessionManager.setCacheManager(cacheManager);
        return sessionManager;
    }

    @Bean(name = "sessionDAO")
    public SessionDAO getSessionDAO() {
        return new RedisShiroSessionDAO(RedisSessionExpireTime, redisCacher);
    }

    @Bean(name = "redisCacheManager")
    public CacheManager redisCacheManager() {
        return new RedisShiroCacheManager();
    }
}
