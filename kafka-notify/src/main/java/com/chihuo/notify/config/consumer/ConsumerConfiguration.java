package com.chihuo.notify.config.consumer;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

@Configuration
@EnableKafka
public class ConsumerConfiguration {

	@Value("${kafka.consumer.servers}")
	private String servers;
	@Value("${kafka.consumer.group-id}")
	private String groupId;
	@Value("${kafka.consumer.topic}")
	private String topic;
	@Value("${kafka.consumer.enable-auto-commit}")
	private Boolean enableAutoCommit;
	@Value("${kafka.consumer.auto-commit-interval}")
	private Integer autoCommitInterval;
	@Value("${kafka.consumer.auto-offset-reset}")
	private String autoOffsetReset;
	@Value("${kafka.consumer.session-timeout}")
	private Integer sessionTimeout;
	@Value("${kafka.consumer.concurrency}")
	private Integer concurrency;
	@Value("${kafka.consumer.key-deserializer}")
	private String keyDeserializer;
	@Value("${kafka.consumer.value-deserializer}")
	private String valueDeserializer;

	@Bean
	public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		factory.setConcurrency(concurrency);
		factory.getContainerProperties().setPollTimeout(1500);
		return factory;
	}

	public ConsumerFactory<String, String> consumerFactory() {
		return new DefaultKafkaConsumerFactory<>(consumerConfigs());
	}

	public Map<String, Object> consumerConfigs() {
		Map<String, Object> propsMap = new HashMap<>();
		propsMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
		propsMap.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
//		propsMap.put(ConsumerConfig.topic, topic);
		propsMap.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, enableAutoCommit);
		propsMap.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, autoCommitInterval);
		propsMap.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);
		propsMap.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, sessionTimeout);
		propsMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, keyDeserializer);
		propsMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, valueDeserializer);
		return propsMap;
	}

}
