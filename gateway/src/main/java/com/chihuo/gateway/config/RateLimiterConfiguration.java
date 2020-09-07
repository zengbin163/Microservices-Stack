package com.chihuo.gateway.config;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.validation.Validator;

import com.chihuo.gateway.config.ratelimit.SystemRedisRateLimiter;

@Configuration
public class RateLimiterConfiguration {

	/**
	 * @apiNote 自定义限流类（供<网关层>限流使用）
	 * @param redisTemplate
	 * @param script
	 * @param validator
	 * @return
	 */
	@Bean
	@Primary
	public SystemRedisRateLimiter systemRedisRateLimiter(ReactiveRedisTemplate<String, String> redisTemplate, @Qualifier(SystemRedisRateLimiter.REDIS_SCRIPT_NAME) RedisScript<List<Long>> script, Validator validator){
	    return new SystemRedisRateLimiter(redisTemplate , script , validator);
	}
	
	/**
	 * @apiNote 自定义限流类（供<接口层>限流使用，读取限流脚本）
	 * @return
	 */
	@Bean
	public DefaultRedisScript<Number> redisluaScript() {
		DefaultRedisScript<Number> redisScript = new DefaultRedisScript<>();
		redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("myRateLimit.lua")));
		redisScript.setResultType(Number.class);
		return redisScript;
	}

	/**
	 * RedisTemplate
	 * @apiNote 自定义限流类（供<接口层>限流使用，初始化限流模板）
	 * @return
	 */
	@Bean
	public RedisTemplate<String, Serializable> limitRedisTemplate(LettuceConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, Serializable> template = new RedisTemplate<String, Serializable>();
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		template.setConnectionFactory(redisConnectionFactory);
		return template;
	}

}
