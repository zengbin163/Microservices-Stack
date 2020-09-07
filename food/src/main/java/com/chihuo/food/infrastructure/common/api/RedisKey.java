package com.chihuo.food.infrastructure.common.api;

import com.chihuo.food.infrastructure.common.api.value.Unit;

public enum RedisKey {

	KEY_FOOD("com.chihuo.food.foodList_", 365L, Unit.DAYS),
	KEY_FOOD_STOCK("com.chihuo.food.foodStockList_", 365L, Unit.DAYS),
	KEY_USER("com.chihuo.food.userList_", 365L, Unit.DAYS),
	KEY_USER_ACCOUNT("com.chihuo.food.userAccountList_", 365L, Unit.DAYS),
	KEY_SELLER("com.chihuo.food.sellerList_", 365L, Unit.DAYS),
	KEY_SELLER_FOOD("com.chihuo.food.sellerFoodList_", 365L, Unit.DAYS),
	KEY_PROMOTION("com.chihuo.food.promotionList_", 365L, Unit.DAYS),
	
	KEY_LOCK("com.chihuo.food.distributedLocks_", 10L, Unit.SECONDS),
	;

	private String keyPrefix;
	private Long timeout;
	private String unit;

	private RedisKey() {

	}

	private RedisKey(String keyPrefix, Long timeout, String unit) {
		this.keyPrefix = keyPrefix;
		this.timeout = timeout;
		this.unit = unit;
	}

	public String getKeyPrefix() {
		return keyPrefix;
	}

	public void setKeyPrefix(String keyPrefix) {
		this.keyPrefix = keyPrefix;
	}

	public Long getTimeout() {
		return timeout;
	}

	public void setTimeout(Long timeout) {
		this.timeout = timeout;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

}
