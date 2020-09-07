package com.chihuo.sleuth.service.service1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chihuo.sleuth.service.service2.Service2;

@Service
public class Service1 {
	
	private static final Logger log = LoggerFactory.getLogger(Service1.class);
	@Autowired
	private Service2 service2;
	
	public String service1() {
		log.info("-----service1 execute:{}", "service1");
		service2.service2();
		return "service1";
	}

}
