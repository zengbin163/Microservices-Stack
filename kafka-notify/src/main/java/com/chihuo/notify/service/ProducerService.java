package com.chihuo.notify.service;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.DeleteTopicsResult;
import org.apache.kafka.clients.admin.DescribeTopicsResult;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.admin.TopicDescription;
import org.apache.kafka.common.KafkaFuture;
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
import com.alibaba.fastjson.JSONObject;

@Service
public class ProducerService {
	
	@Autowired
	private KafkaTemplate<Object, Object> kafkaTemplate;
    @Autowired
    private AdminClient adminClient;

	private static final Logger log = LoggerFactory.getLogger(ProducerService.class);
	
	/**
	 * 自定义手动创建topic和分区
	 * @param topic
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public boolean createTopic(String topicName, int partitions, short replicationFactor) throws Exception {
		if(existTopic(topicName)) {
			return false;
		}
        NewTopic topic = new NewTopic(topicName, partitions, replicationFactor);
        CreateTopicsResult result = adminClient.createTopics(Arrays.asList(topic));
        KafkaFuture<Void> future = result.all();
		return !future.isCompletedExceptionally();
	}
	
	public boolean deleteTopic(String topicName) throws Exception {
		DeleteTopicsResult result = adminClient.deleteTopics(Arrays.asList(topicName));
        KafkaFuture<Void> future = result.all();
		return !future.isCompletedExceptionally();
	}
	
	public boolean existTopic(String topicName) throws Exception {
		DescribeTopicsResult result = adminClient.describeTopics(Arrays.asList(topicName));
		KafkaFuture<Map<String, TopicDescription>> future = result.all();
		if (!future.isCompletedExceptionally() && null != future.get()) {
			return true;
		} else {
			throw new IllegalArgumentException("Topic not exists, topicName=" + topicName);
		}
	}
	
	public String findTopic(String topicName) throws Exception {
		DescribeTopicsResult result = adminClient.describeTopics(Arrays.asList(topicName));
		KafkaFuture<Map<String, TopicDescription>> future = result.all();
		if (!future.isCompletedExceptionally() && null != future.get()) {
			TopicDescription description = future.get().get(topicName);
			return description.toString();
		} else {
			throw new IllegalArgumentException("Topic not exists, topicName=" + topicName);
		}
	}
	
	public String findTopicList() throws Exception {
        ListTopicsResult listTopics = adminClient.listTopics();
        Set<String> topics = listTopics.names().get();
        return JSONObject.toJSONString(topics);
	}

	/**
	 * 基于事件监听机制的消息发送
	 *  {@linkplain 发布/订阅机制}
	 *  {@link com.chihuo.notify.config.listener.ConsumerNotifyListener}
	 * @param topic
	 * @param key
	 * @param value
	 * @return
	 */
	public String send(String topic, String key, String value) {
		Assert.notNull(topic, "topic is null");
		Assert.notNull(key, "key is null");
		Assert.notNull(value, "value is null");
		byte[] keyB = key.getBytes();
		byte[] valB = value.getBytes();
		ListenableFuture<SendResult<Object, Object>> future = this.kafkaTemplate.send(topic, keyB, valB);
		future.addCallback(new ListenableFutureCallback<SendResult<Object, Object>>() {
			@Override
			public void onFailure(Throwable throwable) {
				log.warn("消息发送失败， topic {}, msg: {}", topic, JSON.toJSONString(value));
			}

			@Override
			public void onSuccess(SendResult<Object, Object> result) {
				log.info("消息发送成功， topic {}, msg: {}", topic, JSON.toJSONString(value));
			}
		});
		return ResponseEntity.ok().build().getStatusCode().toString();
	}

}
