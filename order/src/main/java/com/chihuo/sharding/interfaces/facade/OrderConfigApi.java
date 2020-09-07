package com.chihuo.sharding.interfaces.facade;

import java.util.Collection;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.chihuo.sharding.infrastructure.algorithm.DatabasePreciseShardingAlgorithm;
import com.chihuo.sharding.infrastructure.algorithm.OrderItemTablePreciseShardingAlgorithm;
import com.chihuo.sharding.infrastructure.algorithm.OrderTablePreciseShardingAlgorithm;

@RestController
@RequestMapping("/order-config")
public class OrderConfigApi {

	@Value("${spring.cloud.config.name}")
	private String configName;
	@Value("${spring.cloud.config.profile}")
	private String configProfile;
	
	@GetMapping("/config")
	public ResponseEntity<String> config() {
		JSONObject json = new JSONObject();
		json.put("config", configName + "-" + configProfile);
		return ResponseEntity.ok(json.toJSONString());
	}
	
    @SuppressWarnings({"unchecked", "rawtypes"})
	@RequestMapping("/dbConfig")
    @ResponseBody
    public String dbConfig(@RequestParam(value = "userId") Long userId) {
		DatabasePreciseShardingAlgorithm databasePreciseShardingAlgorithm = new DatabasePreciseShardingAlgorithm();
		OrderTablePreciseShardingAlgorithm orderTablePreciseShardingAlgorithm = new OrderTablePreciseShardingAlgorithm();
		OrderItemTablePreciseShardingAlgorithm orderItemTablePreciseShardingAlgorithm = new OrderItemTablePreciseShardingAlgorithm();
		Collection<String> availableTargetNames = null;
        PreciseShardingValue<Long> shardingValue = new PreciseShardingValue("","",userId);
        String dbName = databasePreciseShardingAlgorithm.doSharding(availableTargetNames, shardingValue);
        String orderTableName = orderTablePreciseShardingAlgorithm.doSharding(availableTargetNames, shardingValue);
        String orderItemTableName = orderItemTablePreciseShardingAlgorithm.doSharding(availableTargetNames, shardingValue);
        String dbConfig = String.format("dbName:%s   orderTableName:%s   orderItemTableName:%s", dbName, orderTableName, orderItemTableName);
        return dbConfig;
    }

}
