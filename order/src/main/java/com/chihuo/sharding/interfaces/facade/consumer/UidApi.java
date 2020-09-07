package com.chihuo.sharding.interfaces.facade.consumer;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chihuo.sharding.infrastructure.consumer.UidClientComponent;

@RestController
@RequestMapping("/consumer")
public class UidApi {

	@Resource
    private UidClientComponent uidClientComponent;

	@RequestMapping(value = "/uid/getUID", method = RequestMethod.GET)
	public Long getUID() {
	    //调用远程服务 http请求
		return this.uidClientComponent.getUID();
	}

	@RequestMapping(value = "/uid/parseUID", method = RequestMethod.GET)
	public String parseUID(@RequestParam(name = "uid") Long uid) {
		//调用远程服务 http请求
		return this.uidClientComponent.parseUID(uid);
	}

}
