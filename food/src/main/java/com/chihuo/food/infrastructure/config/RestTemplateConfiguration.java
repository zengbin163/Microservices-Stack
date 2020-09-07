package com.chihuo.food.infrastructure.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {

	@LoadBalanced
	@Bean(name = "balanceTemplate")
	public RestTemplate loadBalanced(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Primary
	@Bean(name = "primaryTemplate")
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

}
