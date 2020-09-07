package com.chihuo.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

public class IgnoreGatewayGlobalFilterFactor extends AbstractGatewayFilterFactory<IgnoreGatewayGlobalFilterFactor.Config> {

	public IgnoreGatewayGlobalFilterFactor() {
		super(Config.class);
	}

	@Override
	public GatewayFilter apply(Config config) {
		return this::filter;
	}

	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		exchange.getAttributes().put(GatewayGlobalFilter.ATTRIBUTE_IGNORE_GATEWAY_GLOBAL_FILTER, true);
		return chain.filter(exchange);
	}

	public static class Config {

	}

	@Override
	public String name() {
		return "IgnoreGatewayGlobalFilter";
	}
	
}