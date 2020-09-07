package com.chihuo.sharding.infrastructure.algorithm.value;

public enum Sharding {

	MOD_TWO(2, "database sharding"), 
	MOD_TEN(10, "tables sharding"),
	;

	private Integer shardingFactor;
	private String desc;

	private Sharding() {

	}

	private Sharding(Integer shardingFactor, String desc) {
		this.shardingFactor = shardingFactor;
		this.desc = desc;
	}

	public Integer getShardingFactor() {
		return shardingFactor;
	}

	public void setShardingFactor(Integer shardingFactor) {
		this.shardingFactor = shardingFactor;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
