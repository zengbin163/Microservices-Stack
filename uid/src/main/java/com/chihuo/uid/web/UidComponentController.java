package com.chihuo.uid.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.baidu.fsg.uid.impl.CachedUidGenerator;
import com.chihuo.api.component.UidComponent;

@RestController
public class UidComponentController implements UidComponent {

	@Autowired
	private CachedUidGenerator generator;

	@Override
	public Long getUID() {
		return this.generator.getUID();
	}

	@Override
	public String parseUID(Long uid) {
		return this.generator.parseUID(uid);
	}

}
