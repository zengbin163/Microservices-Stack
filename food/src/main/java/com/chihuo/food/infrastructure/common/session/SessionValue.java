package com.chihuo.food.infrastructure.common.session;

import java.io.Serializable;

import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;

import lombok.Data;

@Data
public class SessionValue implements Serializable {

	private static final long serialVersionUID = 9050858286331465335L;

	private String token;
	private String uuid;
	private String mobile;
	private String personType;
	
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	
	public static SessionValue valueOf(String json) {
		if(StringUtils.isEmpty(json)) {
			return null;
		}
		return JSON.parseObject(json, SessionValue.class);
	}
	
}
