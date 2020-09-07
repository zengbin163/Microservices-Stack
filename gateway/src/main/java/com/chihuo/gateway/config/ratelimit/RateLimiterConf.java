package com.chihuo.gateway.config.ratelimit;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @apiNote <网关层> 使用配置文件的方式进行初始化
 * @author zengbin
 *
 */
@Component
@ConfigurationProperties(prefix = "ratelimiter-conf")
public class RateLimiterConf {
	
	// 处理速度
	private static final String DEFAULT_REPLENISHRATE = "default.replenishRate";
	private static final Integer DEFAULT_REPLENISHRATE_SIZE = 10;
	// 容量
	private static final String DEFAULT_BURSTCAPACITY = "default.burstCapacity";
	private static final Integer DEFAULT_BURSTCAPACITY_SIZE = 100;

	private Map<String, Integer> rateLimitMap = new ConcurrentHashMap<String, Integer>() {
		private static final long serialVersionUID = 9188652751824524782L;
		{
			put(DEFAULT_REPLENISHRATE, DEFAULT_REPLENISHRATE_SIZE);
			put(DEFAULT_BURSTCAPACITY, DEFAULT_BURSTCAPACITY_SIZE);
		}
	};

	public Map<String, Integer> getRateLimitMap() {
		return rateLimitMap;
	}

	public void setRateLimitMap(Map<String, Integer> rateLimitMap) {
		this.rateLimitMap = rateLimitMap;
	}
	
}
