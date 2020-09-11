package com.chihuo.coupon.interfaces.facade.consumer;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chihuo.coupon.infrastructure.consumer.MessageProducerComponent;

@RestController
@RequestMapping("/producer")
public class MessageProducerApi {

	@Resource
    private MessageProducerComponent producer;

	@RequestMapping(value = "/send", method = RequestMethod.GET)
	public String send(
			@RequestParam(value = "topic") String topic,
			@RequestParam(value = "key") String key, 
			@RequestParam(value = "value") String value) throws Exception {
		return this.producer.send(topic, key, value);
	}

}
