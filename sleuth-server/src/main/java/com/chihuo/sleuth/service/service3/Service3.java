package com.chihuo.sleuth.service.service3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class Service3 {

	private static final Logger log = LoggerFactory.getLogger(Service3.class);

	public String service3() {
		log.info("-----service3 execute:{}", "service3");
		return "service3";
	}

}
