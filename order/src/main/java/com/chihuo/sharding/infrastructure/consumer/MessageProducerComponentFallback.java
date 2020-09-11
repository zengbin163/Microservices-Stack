package com.chihuo.sharding.infrastructure.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MessageProducerComponentFallback implements MessageProducerComponent {
	
	private static final Logger log = LoggerFactory.getLogger(MessageProducerComponentFallback.class);

	@Override
	public String send(String topic, String key, String value) throws Exception {
		log.warn("The message from MessageProducerComponentFallback,key:{},value:{}", key, value);
		return null;
	}

	@Override
	public boolean createTopic(String topicName, int partitions,
			short replicationFactor) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteTopic(String topicName) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean existTopic(String topicName) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String findTopic(String topicName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findTopicList() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
