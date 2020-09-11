package com.chihuo.food.infrastructure.common.event;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.chihuo.api.component.event.DomainEvent;
import com.chihuo.food.infrastructure.consumer.MessageProducerComponent;

@Service
public class EventPublisher {

	private static final Logger log = LoggerFactory.getLogger(EventPublisher.class);
	
	@Resource
    private MessageProducerComponent producer;

	public void publish(DomainEvent event) {
		log.info("Event publish param,id={},timestamp={},source={},data={}", event.getId(), event.getTimestamp(), event.getSource(), event.getData());
		String key = event.getSource();
		String params = event.getData();
		try {
			this.producer.send(event.getTopic(), key, params);
		} catch (Exception e) {
			log.error("EventPublisher.publish exception", e);
		}
    }
}