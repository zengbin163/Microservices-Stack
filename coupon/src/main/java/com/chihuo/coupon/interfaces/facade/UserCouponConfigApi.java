package com.chihuo.coupon.interfaces.facade;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

@RestController
@RequestMapping("/userCoupon-config")
public class UserCouponConfigApi {

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

}
