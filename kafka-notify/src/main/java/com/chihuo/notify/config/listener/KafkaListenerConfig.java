package com.chihuo.notify.config.listener;

import java.util.Date;
import java.util.Optional;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.chihuo.notify.context.KafkaContext;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;

@Component
public class KafkaListenerConfig {

	private static final Logger log = LoggerFactory.getLogger(KafkaListenerConfig.class);
	private static final String TOPIC = KafkaContext.TOPIC;
	
	@KafkaListener(topics = { TOPIC })
	public void listen(ConsumerRecord<?, ?> record) {
		System.out.println("kafka的key: " + record.key());
		System.out.println("kafka的value: " + record.value().toString());
		String today = DateUtil.format(new Date(), DatePattern.NORM_DATETIME_MS_FORMAT);
		log.info("KafkaListener接收到消息时间：{}", today);
        Optional<String> message = Optional.ofNullable(record.value().toString());
        if(message.isPresent()){
            Object msg = message.get();
            log.info("KafkaListener消费了：Topic:" + "food" + ", Message:" + msg);
        }
	}

}
