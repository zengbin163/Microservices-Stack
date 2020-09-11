package com.chihuo.api.component;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface ProducerComponent {
	
	@RequestMapping("/producer/createTopic")
	public boolean createTopic(@RequestParam("topicName") String topicName, @RequestParam("partitions") int partitions, @RequestParam("replicationFactor") short replicationFactor) throws Exception;

	@RequestMapping("/producer/deleteTopic")
	public boolean deleteTopic(@RequestParam("topicName") String topicName) throws Exception;
	
	@RequestMapping("/producer/existTopic")
	public boolean existTopic(@RequestParam("topicName") String topicName) throws Exception;

	@RequestMapping("/producer/findTopic")
	public String findTopic(@RequestParam("topicName") String topicName) throws Exception;

	@RequestMapping("/producer/findTopicList")
	public String findTopicList() throws Exception;

	@RequestMapping("/producer/send")
	public String send(@RequestParam("topic") String topic, @RequestParam("key") String key, @RequestParam("value") String value) throws Exception;

}
