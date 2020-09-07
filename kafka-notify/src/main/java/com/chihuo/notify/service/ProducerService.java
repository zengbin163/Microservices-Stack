package com.chihuo.notify.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.alibaba.fastjson.JSON;
import com.chihuo.notify.context.KafkaContext;

@Service
public class ProducerService {
	
	@Autowired
	private KafkaTemplate<Object, Object> kafkaTemplate;
	private static final String TOPIC = KafkaContext.TOPIC;
	private static final Logger log = LoggerFactory.getLogger(ProducerService.class);

	/**
	 * 基于事件监听机制的消息发送
	 *  {@linkplain 发布/订阅机制}
	 *  {@link com.chihuo.notify.config.listener.KafkaListenerConfig}
	 * @param key
	 * @param value
	 * @return
	 */
	public String send(String key, String value) {
		Assert.notNull(key, "key is null");
		Assert.notNull(value, "value is null");
		byte[] keyB = key.getBytes();
		byte[] valB = value.getBytes();
		ListenableFuture<SendResult<Object, Object>> future = this.kafkaTemplate.send(TOPIC, keyB, valB);
		future.addCallback(new ListenableFutureCallback<SendResult<Object, Object>>() {
			@Override
			public void onFailure(Throwable throwable) {
				log.warn("Sending message to Kafka failed, topic {}, msg: {}", TOPIC, JSON.toJSONString(value));
			}

			@Override
			public void onSuccess(SendResult<Object, Object> result) {
				log.info("Sending message to Kafka finished, topic {}, msg: {}", TOPIC, JSON.toJSONString(value));
			}
		});
		return ResponseEntity.ok().build().getStatusCode().toString();
	}

	
}
