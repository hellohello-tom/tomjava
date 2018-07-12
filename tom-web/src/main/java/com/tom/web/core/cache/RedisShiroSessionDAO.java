package com.tom.web.core.cache;

import com.tom.core.cache.ICache;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;

@Component
public class RedisShiroSessionDAO extends AbstractSessionDAO {


    //超时时间
    private long expireTime = 120000;

    //缓存接口
    private ICache cache;

    public RedisShiroSessionDAO() {
        super();
    }


    public RedisShiroSessionDAO(long expireTime, ICache cache) {
        super();
        this.cache = cache;
        this.expireTime = expireTime;
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        cache.set(GetCacheKey(session.getId()), session, expireTime);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        Object redisSession = cache.get(GetCacheKey(sessionId));
        return (Session) redisSession;
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        String sessionKey = GetCacheKey(session.getId());
        if (cache.get(sessionKey) == null) {
            cache.set(GetCacheKey(session.getId()), session, expireTime);
        } else {
            //每次会话时都会更新session的失效日期，保证其登录状态
            cache.expire(sessionKey, expireTime);
        }

    }

    @Override
    public void delete(Session session) {
        cache.del(GetCacheKey(session.getId()));
    }

    @Override
    public Collection<Session> getActiveSessions() {
        return cache.getList("*");
    }

    private String GetCacheKey(Serializable key) {
        return "tom-shiro-session:" + key;
    }

}
