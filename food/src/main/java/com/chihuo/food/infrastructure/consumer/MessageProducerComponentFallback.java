package com.chihuo.food.infrastructure.consumer;

import org.springframework.stereotype.Component;

@Component
public class MessageProducerComponentFallback implements MessageProducerComponent {
	
	@Override
	public boolean createTopic(String topicName, int partitions, short replicationFactor) throws Exception {
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

	@Override
	public String send(String topic, String key, String value)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
