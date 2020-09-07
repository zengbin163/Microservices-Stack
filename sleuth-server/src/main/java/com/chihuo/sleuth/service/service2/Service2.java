package com.chihuo.sleuth.service.service2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chihuo.sleuth.service.service3.Service3;
import com.chihuo.sleuth.service.service4.Service4;

@Service
public class Service2 {

	private static final Logger log = LoggerFactory.getLogger(Service2.class);
	@Autowired
	private Service3 service3;
	@Autowired
	private Service4 service4;

	public String service2() {
		log.info("-----service2 execute:{}", "service2");
		this.service3.service3();
		this.service4.service4();
		return "service2";
	}

}
