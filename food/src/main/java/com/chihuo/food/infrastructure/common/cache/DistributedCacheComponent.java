package com.chihuo.food.infrastructure.common.cache;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.chihuo.food.infrastructure.common.api.RedisKey;
import com.chihuo.food.infrastructure.common.api.value.Unit;

@Component
public class DistributedCacheComponent {
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	public void set(RedisKey redisKey, Object targetId, String targetValue) {
		String key = redisKey.getKeyPrefix() + targetId;
		Long timeout = redisKey.getTimeout();
		TimeUnit unit = Unit.of(redisKey.getUnit());
		this.redisTemplate.opsForValue().set(key, targetValue, timeout, unit);
	}
	
	public String get(RedisKey redisKey, Object targetId) {
		String key = redisKey.getKeyPrefix() + targetId;
		return this.redisTemplate.opsForValue().get(key);
	}

	public void rightPush(RedisKey redisKey, Object targetId, String targetValue) {
		String key = redisKey.getKeyPrefix() + targetId;
		Long timeout = redisKey.getTimeout();
		TimeUnit unit = Unit.of(redisKey.getUnit());
		this.redisTemplate.opsForList().rightPush(key, targetValue);
		this.redisTemplate.expire(key, timeout, unit);
	}
	
	public List<String> range(RedisKey redisKey, Object targetId, long start, long end) {
		String key = redisKey.getKeyPrefix() + targetId;
		return this.redisTemplate.opsForList().range(key, start, end);
	}
	
	public void add(RedisKey redisKey, Object targetId, String targetValue, double score) {
		String key = redisKey.getKeyPrefix() + targetId;
		Long timeout = redisKey.getTimeout();
		TimeUnit unit = Unit.of(redisKey.getUnit());
	    this.redisTemplate.opsForZSet().add(key, targetValue, score);
		this.redisTemplate.expire(key, timeout, unit);
	}
	
	public Set<String> reverseRange(RedisKey redisKey, Object targetId, long start, long end) {
		String key = redisKey.getKeyPrefix() + targetId;
		Set<String> sets = this.redisTemplate.opsForZSet().reverseRange(key, start, end);
		return sets;
	}
	
	public void clear(String keysPattern) {
		Set<String> keys = this.redisTemplate.keys(keysPattern);
		if(!CollectionUtils.isEmpty(keys)) {
			this.redisTemplate.delete(keys);
		}
	}
	
}
