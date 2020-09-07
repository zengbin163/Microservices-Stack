package com.chihuo.food.interfaces.facade.health;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.chihuo.food.infrastructure.common.session.value.NoLogin;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Applications;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/food-config")
public class FoodConfigController {

	@Autowired
	private EurekaClient eurekaClient;
	@Autowired
	private DiscoveryClient discoveryClient;
	@Value("${spring.cloud.config.name}")
	private String configName;
	@Value("${spring.cloud.config.profile}")
	private String configProfile;

	@ApiOperation(value = "获取服务应用列表", notes = "获取服务应用列表详细描述")
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	@ResponseBody
	@NoLogin
	public String get() {
		Applications applications = eurekaClient.getApplications();
		return JSONObject.toJSONString(applications);
	}

	@RequestMapping(value = "/serviceurl", method = RequestMethod.GET)
	@NoLogin
	public Map<String, List<ServiceInstance>> serviceUrl() {
		Map<String, List<ServiceInstance>> msl = new HashMap<>();
		List<String> services = discoveryClient.getServices();
		for (String service : services) {
			List<ServiceInstance> sis = discoveryClient.getInstances(service);
			msl.put(service, sis);
		}
		return msl;
	}
	
	@GetMapping("/config")
	@NoLogin
	public ResponseEntity<String> config() {
		JSONObject json = new JSONObject();
		json.put("config", configName + "-" + configProfile);
		return ResponseEntity.ok(json.toJSONString());
	}

}
