package com.chihuo.gateway.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Configuration
public class KeyResolverConfiguration {
	
	private static final Logger log = LoggerFactory.getLogger(KeyResolverConfiguration.class);

	@Bean
	@Qualifier(value = "sysKeyResolver")
	public KeyResolver sysKeyResolver() {
		KeyResolver keyResolver = new KeyResolver() {
			@Override
			public Mono<String> resolve(ServerWebExchange exchange) {
				String serviceName = exchange.getRequest().getQueryParams().getFirst("serviceName");
				if (StringUtils.isEmpty(serviceName)) {
					log.warn("---------------serviceName is null");
					serviceName = "unKownHost";
				} else {
					log.info("---------------serviceName:{}", serviceName);
				}
				Mono<String> mono = Mono.just(serviceName);
				mono.subscribe(inputVal -> {
						log.info("------------------------------------------inputVal:{}", inputVal);
					}, error -> {
						log.error("------------------------------------------error:{}", error);
					}, () -> {
						log.info("------------------------------------------onComplete");
					}, subscription -> {
						Long maxValue = Long.MAX_VALUE;
						log.info("------------------------------------------maxValue:{}", maxValue);
						subscription.request(maxValue);
					}
				);
				return mono;
			}
		};
		return keyResolver;
	}
	
}
