package com.chihuo.food.infrastructure.common.api.value;

import com.chihuo.food.infrastructure.common.api.RedisKey;

public class Locks {
	
	public static final String USER_HONGBAO = "user_hongbao_";
	
	public static String hongBaokey(Long targetId) {
		return RedisKey.KEY_LOCK.getKeyPrefix() + USER_HONGBAO + targetId;
	}

}
