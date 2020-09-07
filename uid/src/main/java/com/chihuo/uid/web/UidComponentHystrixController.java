package com.chihuo.uid.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baidu.fsg.uid.impl.CachedUidGenerator;

@RestController
@RequestMapping("/uid/hystrix")
public class UidComponentHystrixController {

	@Autowired
	private CachedUidGenerator generator;

	@RequestMapping(value = "/getUID", method = RequestMethod.GET)
	public Long getUID() {
		return this.generator.getUID();
	}

	@RequestMapping(value = "/parseUID", method = RequestMethod.GET)
	public String parseUID(Long uid) {
		return this.generator.parseUID(uid);
	}

}
