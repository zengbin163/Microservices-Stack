package com.chihuo.food.infrastructure.common.database.dyna;

import com.chihuo.food.infrastructure.common.database.config.DataBaseSourceType;

/**
 * @author zengbin
 * @Date 2019/6/8 12:14
 */
public class DynamicDataSourceHolder {

	private static final ThreadLocal<DataBaseSourceType> CONTEXT_HOLDER = new ThreadLocal<>();

	public static void setDataSourceType(DataBaseSourceType dataSourceType) {
		CONTEXT_HOLDER.set(dataSourceType);
	}

	public static DataBaseSourceType getDataSourceType() {
		return CONTEXT_HOLDER.get();
	}

	public static void clearDataSourceTypeType() {
		CONTEXT_HOLDER.remove();
	}
}
