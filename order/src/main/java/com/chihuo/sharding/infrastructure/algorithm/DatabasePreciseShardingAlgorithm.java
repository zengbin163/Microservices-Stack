package com.chihuo.sharding.infrastructure.algorithm;

import java.util.Collection;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import com.chihuo.sharding.infrastructure.algorithm.value.Sharding;
import com.chihuo.sharding.infrastructure.algorithm.value.ShardingPrefix;

public class DatabasePreciseShardingAlgorithm implements PreciseShardingAlgorithm<Long> {

	//库ID = userId%库数量2 
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Long> shardingValue) {
        Long curValue = shardingValue.getValue();
        long factor = curValue % Sharding.MOD_TWO.getShardingFactor();
        return ShardingPrefix.DATABASE_PREFIX.getPrefix() + (factor+1);
    }
    
}