package com.chihuo.notify.web.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.chihuo.api.component.ProducerComponent;
import com.chihuo.notify.service.ProducerService;

@RestController
public class KafkaProducerController implements ProducerComponent {
	
	@Autowired
	private ProducerService producerService;

	@Override
    public String send(String topic, String key, String value) {
    	this.producerService.send(topic, key, value);
		return ResponseEntity.ok().build().getStatusCode().toString();
    }
	
	@Override
	public boolean createTopic(String topicName, int partitions, short replicationFactor) throws Exception {
		return this.producerService.createTopic(topicName, partitions, replicationFactor);
	}
	
	@Override
	public boolean deleteTopic(String topicName) throws Exception {
		return this.producerService.deleteTopic(topicName);
	}

	@Override
	public boolean existTopic(String topicName) throws Exception {
		return this.producerService.existTopic(topicName);
	}

	@Override
	public String findTopicList() throws Exception {
		return this.producerService.findTopicList();
	}

	@Override
	public String findTopic(String topicName) throws Exception {
		return this.producerService.findTopic(topicName);
	}

}
