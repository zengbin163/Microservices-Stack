package com.chihuo.sleuth.service.service4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class Service4 {

	private static final Logger log = LoggerFactory.getLogger(Service4.class);

	public String service4() {
		log.info("-----service4 execute:{}", "service4");
		return "service4";
	}

}
