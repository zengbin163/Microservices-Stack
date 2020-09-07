package com.chihuo.sharding.infrastructure.algorithm;

import java.util.Collection;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import com.chihuo.sharding.infrastructure.algorithm.value.Sharding;
import com.chihuo.sharding.infrastructure.algorithm.value.ShardingPrefix;

public class OrderTablePreciseShardingAlgorithm implements PreciseShardingAlgorithm<Long> {

	//表ID = userId/库数量2 % 表数量10 
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Long> shardingValue) {
        Long curValue = shardingValue.getValue();
        long factor = (curValue/Sharding.MOD_TWO.getShardingFactor()) % Sharding.MOD_TEN.getShardingFactor();
        return ShardingPrefix.TABLE_ORDER_PREFIX.getPrefix() + (factor+1);
    }

}