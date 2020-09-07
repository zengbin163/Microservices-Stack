package com.chihuo.sharding;

import java.util.Collection;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import com.chihuo.sharding.infrastructure.algorithm.DatabasePreciseShardingAlgorithm;
import com.chihuo.sharding.infrastructure.algorithm.OrderItemTablePreciseShardingAlgorithm;
import com.chihuo.sharding.infrastructure.algorithm.OrderTablePreciseShardingAlgorithm;

public class AlgorithmTest {

	public static void main(String[] args) {
		printPrecise(22049262589378760L);
	}
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	public static void printPrecise(Long userId) {
		DatabasePreciseShardingAlgorithm databasePreciseShardingAlgorithm = new DatabasePreciseShardingAlgorithm();
		OrderTablePreciseShardingAlgorithm orderTablePreciseShardingAlgorithm = new OrderTablePreciseShardingAlgorithm();
		OrderItemTablePreciseShardingAlgorithm orderItemTablePreciseShardingAlgorithm = new OrderItemTablePreciseShardingAlgorithm();
		Collection<String> availableTargetNames = null;
        PreciseShardingValue<Long> shardingValue = new PreciseShardingValue("","",userId);
        String dbName = databasePreciseShardingAlgorithm.doSharding(availableTargetNames, shardingValue);
        String orderTableName = orderTablePreciseShardingAlgorithm.doSharding(availableTargetNames, shardingValue);
        String orderItemTableName = orderItemTablePreciseShardingAlgorithm.doSharding(availableTargetNames, shardingValue);
        
        System.out.println(String.format("dbName:%s,orderTableName:%s,orderItemTableName:%s", dbName,orderTableName,orderItemTableName));
	}

}
