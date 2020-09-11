package com.chihuo.food.infrastructure.common.api.value;

import com.chihuo.food.infrastructure.common.api.RedisKey;

public class Locks {
	
	public static final String USER_HONGBAO = "user_hongbao_";
	public static final String FOOD_STOCK = "food_stock_";
	
	public static String hongBaokey(Long targetId) {
		return RedisKey.KEY_LOCK.getKeyPrefix() + USER_HONGBAO + targetId;
	}
	
	public static String stockkey(Long targetId) {
		return RedisKey.KEY_LOCK.getKeyPrefix() + FOOD_STOCK + targetId;
	}

}
