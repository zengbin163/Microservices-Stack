package com.chihuo.sharding.interfaces.facade.consumer;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chihuo.api.component.topic.Topic;
import com.chihuo.sharding.infrastructure.consumer.MessageProducerComponent;

@RestController
@RequestMapping("/producer")
public class MessageProducerApi {

	@Resource
    private MessageProducerComponent producer;

	@RequestMapping(value = "/send", method = RequestMethod.GET)
	public String send(@RequestParam(value = "key") String key, @RequestParam(value = "value") String value) throws Exception {
	    //调用远程服务 http请求
		return this.producer.send(Topic.TOPIC_ORDER_SERVICE, key, value);
	}

}
