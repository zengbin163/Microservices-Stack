package com.chihuo.food.infrastructure.common.event;

import java.lang.reflect.Field;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.chihuo.food.infrastructure.consumer.MessageProducerComponent;

@Service
public class EventPublisher {

	private static final Logger log = LoggerFactory.getLogger(EventPublisher.class);
	
	@Resource
    private MessageProducerComponent producer;

	public void publish(DomainEvent event) {
		log.info("=====Event begin published=====");
		log.info("Event publish param,id={},timestamp={},source={},data={}", event.getId(), event.getTimestamp(), event.getSource(), event.getData());
		String key = null;
		Field[] declaredFields = event.getClass().getDeclaredFields();
		try {
			for(Field field : declaredFields) {
				field.setAccessible(true);
				key = field.getName() + ":" + field.get(event);
				if(StringUtils.isNotBlank(key)) {
					break;
				}
			}
			if(StringUtils.isBlank(key)) {
				key = event.getId();
			}
			//send to MQ
			this.producer.send(key, JSON.toJSONString(event));
			log.info("=====Event end published=====");
		} catch (Exception e) {
			log.error("Event published exception", e);
		}	
    }
}