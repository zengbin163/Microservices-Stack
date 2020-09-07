package com.chihuo.sharding.infrastructure.algorithm.value;

public enum ShardingPrefix {

	DATABASE_PREFIX("saleorder0", "database prefix"), 
	TABLE_ORDER_PREFIX("f_order_", "tables prefix"),
	TABLE_ORDER_ITEM_PREFIX("f_order_item_", "tables prefix"),
	;

	private String prefix;
	private String desc;

	private ShardingPrefix() {

	}

	private ShardingPrefix(String prefix, String desc) {
		this.prefix = prefix;
		this.desc = desc;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
