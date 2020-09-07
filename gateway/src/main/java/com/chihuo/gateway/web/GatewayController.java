package com.chihuo.gateway.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

@RestController
@RequestMapping("/gateway")
public class GatewayController {

	@Value("${server.port}")
	private Integer port;
	
	@Value("${spring.application.name}")
	private String applicationName;

	@GetMapping("/config")
	public ResponseEntity<String> config() {
		
		JSONObject json = new JSONObject();
		json.put("serverPort", port);
		json.put("applicationName", applicationName);
		return ResponseEntity.ok(json.toJSONString());
		
	}

}
