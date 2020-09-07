package com.chihuo.coupon.infrastructure.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class MessageProducerComponentFallback implements MessageProducerComponent {
	
	private static final Logger log = LoggerFactory.getLogger(MessageProducerComponentFallback.class);

	@Override
	public String send(String key, String value) {
		log.warn("The message from MessageProducerComponentFallback,key:{},value:{}", key, value);
		return HttpStatus.OK.toString();
	}

}
