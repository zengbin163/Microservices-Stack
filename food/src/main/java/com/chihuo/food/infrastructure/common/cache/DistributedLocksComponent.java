package com.chihuo.food.infrastructure.common.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.chihuo.food.infrastructure.common.api.RedisKey;
import com.chihuo.food.infrastructure.common.api.value.Unit;

@Component
public class DistributedLocksComponent {
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	public boolean lock(String lockId) {
	   return redisTemplate.opsForValue().setIfAbsent(lockId, "locked", RedisKey.KEY_LOCK.getTimeout(), Unit.of(RedisKey.KEY_LOCK.getUnit()));
	}
	
	public boolean release(String lockId) {
	   return this.redisTemplate.delete(lockId);
	}

}
