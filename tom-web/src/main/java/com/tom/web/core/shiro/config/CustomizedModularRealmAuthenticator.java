package com.tom.web.core.shiro.config;

import com.tom.web.core.authorzation.AdminRealm;
import com.tom.web.core.authorzation.JwtToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;

import java.util.ArrayList;
import java.util.Collection;

public class CustomizedModularRealmAuthenticator extends ModularRealmAuthenticator {
    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken) throws
            AuthenticationException {
        // 判断getRealms()是否返回为空
        assertRealmsConfigured();
        // 强制转换回自定义的CustomizedToken
        // 所有Realm
        Collection<Realm> realms = getRealms();
        if (authenticationToken instanceof UsernamePasswordToken || authenticationToken instanceof JwtToken) {
            Collection<Realm> typeRealms = new ArrayList<>();
            for (Realm realm : realms) {
                if (authenticationToken instanceof UsernamePasswordToken) {
                    typeRealms.add(realm);
                } else if (realm instanceof JwtToken) {
                    typeRealms.add(realm);
                }
            }
            if (typeRealms.size() > 0) {
                realms = typeRealms;
            }
        }
        // 判断是单Realm还是多Realm
        if (realms.size() == 1)
            return doSingleRealmAuthentication(realms.iterator().next(), authenticationToken);
        else
            return doMultiRealmAuthentication(realms, authenticationToken);
    }
}
