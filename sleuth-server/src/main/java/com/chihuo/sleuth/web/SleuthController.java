package com.chihuo.sleuth.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.chihuo.sleuth.service.service1.Service1;

@RestController
@RequestMapping("/sleuth")
public class SleuthController {
	
	private static final Logger log = LoggerFactory.getLogger(SleuthController.class); 
	
	@Value("${server.port}")
	private Integer port;
	@Value("${spring.application.name}")
	private String applicationName;
	@Autowired
	private Service1 service1;

	@GetMapping("/config")
	public ResponseEntity<String> config() {
		log.info("-----SleuthController execute:{}", "SleuthController");
		JSONObject json = new JSONObject();
		json.put("serverPort", port);
		json.put("applicationName", applicationName);
		this.service1.service1();
		return ResponseEntity.ok(json.toJSONString());
		
	}

}
