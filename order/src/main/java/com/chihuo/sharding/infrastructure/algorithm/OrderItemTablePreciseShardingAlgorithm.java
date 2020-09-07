package com.chihuo.sharding.infrastructure.algorithm;

import java.util.Collection;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import com.chihuo.sharding.infrastructure.algorithm.value.Sharding;
import com.chihuo.sharding.infrastructure.algorithm.value.ShardingPrefix;

public class OrderItemTablePreciseShardingAlgorithm implements PreciseShardingAlgorithm<Long> {

    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Long> shardingValue) {
        Long curValue = shardingValue.getValue();
        long factor = (curValue/Sharding.MOD_TWO.getShardingFactor()) % Sharding.MOD_TEN.getShardingFactor();
        return ShardingPrefix.TABLE_ORDER_ITEM_PREFIX.getPrefix() + (factor+1);
    }

}