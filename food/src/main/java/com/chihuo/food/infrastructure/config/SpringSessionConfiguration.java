package com.chihuo.food.infrastructure.config;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.events.SessionCreatedEvent;
import org.springframework.session.events.SessionDeletedEvent;
import org.springframework.session.events.SessionExpiredEvent;

import com.chihuo.food.infrastructure.common.session.SessionToolComponent;

@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 2592000) //session有效期30天（单位：秒）
@Configuration
public class SpringSessionConfiguration {
	
	private static final Logger log = LoggerFactory.getLogger(SpringSessionConfiguration.class);
	@Resource
	private SessionToolComponent sessionToolComponent;

	/**
	 * Redis内session过期事件监听
	 * 
	 * @param expiredEvent
	 */
	@EventListener
	public void onSessionExpired(SessionExpiredEvent expiredEvent) {
		String sessionId = expiredEvent.getSessionId();
		log.info("==========SpringSessionConfiguration.onSessionExpired->HttpSession失效事件sessionId:{}", sessionId);
		this.sessionToolComponent.delete(sessionId);
		expiredEvent.getSession().removeAttribute(SessionToolComponent.UUID_);
		expiredEvent.getSession().removeAttribute(SessionToolComponent.MOBILE);
		expiredEvent.getSession().removeAttribute(SessionToolComponent.OPEN_ID);
	}

	/**
	 * Redis内session创建事件监听
	 * 
	 * @param createdEvent
	 */
	@EventListener
	public void onSessionCreated(SessionCreatedEvent createdEvent) {
		String sessionId = createdEvent.getSessionId();
		log.info("==========SpringSessionConfiguration.onSessionCreated->HttpSession创建事件sessionId:{}", sessionId);
	}

	/**
	 * Redis内session删除事件监听
	 * 
	 * @param deletedEvent
	 */
	@EventListener
	public void onSessionDeleted(SessionDeletedEvent deletedEvent) {
		String sessionId = deletedEvent.getSessionId();
		log.info("==========SpringSessionConfiguration.onSessionDeleted->HttpSession删除事件sessionId:{}", sessionId);
		this.sessionToolComponent.delete(sessionId);
		deletedEvent.getSession().removeAttribute(SessionToolComponent.UUID_);
		deletedEvent.getSession().removeAttribute(SessionToolComponent.MOBILE);
		deletedEvent.getSession().removeAttribute(SessionToolComponent.OPEN_ID);
	}

}
