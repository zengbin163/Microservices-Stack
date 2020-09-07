package com.chihuo.zuul.ratelimit;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.stereotype.Component;

import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.RateLimitUtils;
import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.properties.RateLimitProperties;
import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.support.DefaultRateLimitKeyGenerator;

@Component
public class RateLimitKeyGenerator extends DefaultRateLimitKeyGenerator {

	private static final Logger log = LoggerFactory.getLogger(RateLimitKeyGenerator.class);

	public RateLimitKeyGenerator(RateLimitProperties properties, RateLimitUtils rateLimitUtils) {
		super(properties, rateLimitUtils);
	}

	// 在这个方法里面写限流逻辑
	@Override
	public String key(HttpServletRequest request, Route route, RateLimitProperties.Policy policy) {
		log.info("限流RateLimitKeyGenerator.key---route={}，policy={}", route, policy);
		String name = request.getParameter("name");
		if("zhangsan".equals(name)) {
			throw new IllegalArgumentException("参数异常");
		}
		return super.key(request, route, policy);
	}
}
