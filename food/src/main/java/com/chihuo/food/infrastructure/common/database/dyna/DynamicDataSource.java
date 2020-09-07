package com.chihuo.food.infrastructure.common.database.dyna;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.lang.Nullable;

import com.chihuo.food.infrastructure.common.database.config.DataBaseSourceType;

/**
 * @author zengbin
 * @Date 2019/6/8 12:13
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
	@Nullable
	@Override
	protected Object determineCurrentLookupKey() {
		DataBaseSourceType dataBaseSourceType = DynamicDataSourceHolder.getDataSourceType();
		if(null == dataBaseSourceType) {
			dataBaseSourceType = DataBaseSourceType.MASTER_DATASOURCE;
		}
		return dataBaseSourceType;
	}
}
