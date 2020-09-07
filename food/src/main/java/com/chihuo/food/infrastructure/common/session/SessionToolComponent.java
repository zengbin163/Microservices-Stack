package com.chihuo.food.infrastructure.common.session;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.chihuo.food.infrastructure.consumer.UidClientComponent;

import cn.hutool.core.lang.UUID;

/**
 * 法苗session工具组件
 * @author zengbin
 *
 */
@Component
public class SessionToolComponent {
	
	private static final Logger log = LoggerFactory.getLogger(SessionToolComponent.class);
	private static final ThreadLocal<SessionValue> sessionThreadLocal = new ThreadLocal<SessionValue>();
	public static final String UUID_ = "uuid";
	public static final String MOBILE = "mobile";
	public static final String OPEN_ID = "openId";
	public static final String TOKEN = "token";
	public static final String SESSION_KEY = "SESSION_KEY_";

	@Resource
	private UidClientComponent uidClientComponent;
	@Autowired
    private RedisTemplate<String, String> redisTemplate;
	
	public SessionValue value(String uuid, String mobile) {
		String token = UUID.fastUUID().toString() + "@" + System.currentTimeMillis();
		SessionValue value = new SessionValue();
		String sessionId = Base64.encodeBase64String(token.getBytes());
		value.setToken(sessionId);
		value.setUuid(uuid);
		value.setMobile(mobile);
		return value;
	}
	
	public SessionValue create(String uuid, String mobile) {
		//写入session
    	SessionValue session = this.value(uuid, mobile);
    	this.redisTemplate.opsForValue().set(SESSION_KEY + session.getToken(), session.toString());
    	this.setSession(session);
    	return session;
	}
	
	public SessionValue get(String sessionId) {
		SessionValue session = this.getSession();
		if(null == session) {
			String sessionStr = this.redisTemplate.opsForValue().get(SESSION_KEY + sessionId);//session 30 天有效期
			session = JSONObject.parseObject(sessionStr, SessionValue.class);
			this.setSession(session);
		}
		return session;
	}
	
	public void delete(String sessionId) {
    	this.redisTemplate.delete(SESSION_KEY + sessionId);
    	this.clear();
	}
	
	public String jwt(HttpServletRequest request, String userId, String mobile) {
		//HttpSession创建
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute(UUID_, userId);
		httpSession.setAttribute(MOBILE, mobile);
		log.info("SessionToolComponent HttpSession创建，sessionId:{}", httpSession.getId());
		//法苗session创建
		SessionValue session = this.create(userId, mobile);
		log.info(session.toString());
    	//JWT操作
		//String jwt = JwtSign.createJWT(JwtSign.ISSUER_NAME, JwtSign.AUDIENCE, session.toString());
		//return jwt;
		return session.getToken();
	}
	
	public String userId() {
		return this.getSession().getUuid();
	}
	
	public SessionValue getSession() {
		return sessionThreadLocal.get();
	}

	public void setSession(SessionValue session) {
		sessionThreadLocal.set(session);
	}

	public void clear() {
		sessionThreadLocal.set(null);
	}
}
