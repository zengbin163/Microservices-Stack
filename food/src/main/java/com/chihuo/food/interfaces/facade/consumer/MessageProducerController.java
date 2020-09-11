package com.chihuo.food.interfaces.facade.consumer;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chihuo.api.component.topic.Topic;
import com.chihuo.food.infrastructure.common.session.value.NoLogin;
import com.chihuo.food.infrastructure.consumer.MessageProducerComponent;

@RestController
@RequestMapping("/producer")
public class MessageProducerController {

	@Resource
    private MessageProducerComponent producer;

	@RequestMapping(value = "/send", method = RequestMethod.GET)
	@NoLogin
	public String send(@RequestParam(value = "key") String key, @RequestParam(value = "value") String value) throws Exception {
	    //调用远程服务 http请求
		return this.producer.send(Topic.TOPIC_COUPON_SERVICE, key, value);
	}

}
