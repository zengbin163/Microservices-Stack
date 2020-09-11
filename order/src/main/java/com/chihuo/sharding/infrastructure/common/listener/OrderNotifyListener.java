package com.chihuo.sharding.infrastructure.common.listener;

import java.util.Date;
import java.util.Optional;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.chihuo.api.component.topic.Topic;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;

@Component
public class OrderNotifyListener {

	private static final Logger log = LoggerFactory.getLogger(OrderNotifyListener.class);
	private static final String TOPIC = Topic.TOPIC_ORDER_SERVICE;
	
	@KafkaListener(topics = { TOPIC })
	public void listen(ConsumerRecord<?, ?> record) {
		if(!TOPIC.equals(record.topic())) {
			return;
		}
		log.info(TOPIC + " 消息 ---> key:{} value:{}", record.key(), record.value().toString());
		String today = DateUtil.format(new Date(), DatePattern.NORM_DATETIME_MS_FORMAT);
		log.info(TOPIC + " listener 接收到消息时间：{}", today);
        Optional<String> message = Optional.ofNullable(record.value().toString());
        if(message.isPresent()) {
            Object msg = message.get();
            log.info(TOPIC + " listener消费了消息：Topic:" + TOPIC + " Message:" + msg);
        }
	}

}
