package com.chihuo.gateway.filter;

import java.net.URI;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;

import reactor.core.publisher.Mono;

@Component
public class GatewayGlobalFilter implements GlobalFilter, Ordered {

	public final static String ATTRIBUTE_IGNORE_GATEWAY_GLOBAL_FILTER = "@ignoreGatewayGlobalFilter";

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		// 跳过检测
		if (exchange.getAttribute(ATTRIBUTE_IGNORE_GATEWAY_GLOBAL_FILTER) != null) {
			return chain.filter(exchange);
		} else {
			// 简单增加一个参数
			URI uri = exchange.getRequest().getURI();
			String query = uri.getRawQuery();
			if (StringUtils.hasText(query)) {
				query = query + "&throwFilter=true";
			} else {
				query = query + "?throwFilter=true";
			}
			URI newUri = UriComponentsBuilder.fromUri(uri).replaceQuery(query).build(true).toUri();
			ServerHttpRequest request = exchange.getRequest().mutate().uri(newUri).build();
			return chain.filter(exchange.mutate().request(request).build());
		}
	}

	@Override
	public int getOrder() {
		// 在GatewayFilter之后执行
		return 10;
	}

}
