package com.chihuo.coupon.infrastructure.common.listener;

import java.util.Date;
import java.util.Optional;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.chihuo.api.component.event.value.CouponEventType;
import com.chihuo.api.component.topic.Topic;
import com.chihuo.coupon.application.CouponApplicationService;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;

@Component
public class CouponNotifyListener {

	private static final Logger log = LoggerFactory.getLogger(CouponNotifyListener.class);
	private static final String TOPIC = Topic.TOPIC_COUPON_SERVICE;
	
	@Autowired
	private CouponApplicationService service;
	
	@KafkaListener(topics = { TOPIC })
	public void listen(ConsumerRecord<?, ?> record) {
		if(!TOPIC.equals(record.topic())) {
			return;
		}
		String today = DateUtil.format(new Date(), DatePattern.NORM_DATETIME_MS_FORMAT);
		Optional<String> couponEventType = Optional.ofNullable(record.key().toString());
        Optional<String> couponId = Optional.ofNullable(record.value().toString());
        if(couponEventType.isPresent() && couponId.isPresent()) {
    		log.info("Coupon service消息 ---> key:{} value:{} 接收到消息时间：{}", couponEventType.get(), couponId.get(), today);
    		CouponEventType eventType = CouponEventType.type(couponEventType.get());
    		switch (eventType) {
				case COUPON_SAVE :
					break;
				case COUPON_USE :
					this.service.use(Long.valueOf(couponId.get()));
					break;
				case COUPON_FIND :
					break;
				default :
					break;
			}
        } else {
        	log.error("the message not exists, key={}", record.key());
        }
	}

}
