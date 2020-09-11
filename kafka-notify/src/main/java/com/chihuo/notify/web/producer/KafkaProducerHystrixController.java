package com.chihuo.notify.web.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chihuo.notify.service.ProducerService;

@RestController
@RequestMapping("/producer-hystrix")
public class KafkaProducerHystrixController {
	
	@Autowired
	private ProducerService producerService;

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public String send(@RequestParam(value = "topic") String topic, @RequestParam(value = "key") String key, @RequestParam(value = "value") String value) {
    	this.producerService.send(topic, key, value);
		return ResponseEntity.ok().build().getStatusCode().toString();
    }
	
    @RequestMapping(value = "/createTopic", method = RequestMethod.POST)
	public boolean createTopic(@RequestParam(value = "topicName") String topicName, @RequestParam(value = "partitions") int partitions, @RequestParam(value = "replicationFactor") short replicationFactor) throws Exception {
		return this.producerService.createTopic(topicName, partitions, replicationFactor);
	}
    
    @RequestMapping(value = "/deleteTopic", method = RequestMethod.POST)
    public boolean deleteTopic(@RequestParam(value = "topicName") String topicName) throws Exception {
    	return this.producerService.deleteTopic(topicName);
    }

    @RequestMapping(value = "/existTopic", method = RequestMethod.POST)
	public boolean existTopic(@RequestParam(value = "topicName") String topicName) throws Exception {
		return this.producerService.existTopic(topicName);
	}

    @RequestMapping(value = "/findTopicList", method = RequestMethod.GET)
	public String findTopicList() throws Exception {
		return this.producerService.findTopicList();
	}    
    
    @RequestMapping(value = "/findTopic", method = RequestMethod.GET)
    public String findTopic(@RequestParam(value = "topicName") String topicName) throws Exception {
    	return this.producerService.findTopic(topicName);
    }
    
}
