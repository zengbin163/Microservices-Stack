package com.chihuo.turbine.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class TestController {

	@GetMapping("/test")
	@HystrixCommand
	public ResponseEntity<String> test() {
		return ResponseEntity.ok("hystrix-turbine-ok");
	}

}
