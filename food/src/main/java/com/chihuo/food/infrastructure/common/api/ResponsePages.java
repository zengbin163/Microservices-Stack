package com.chihuo.food.infrastructure.common.api;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ResponsePages implements Serializable {

	private static final long serialVersionUID = -4857274539922461562L;

	@Value("${server.host}")
	private String host;
	@Value("${server.port}")
	private String port;
	
	public static final String INDEX = "index";
	public static final String ERROR = "404";
	
	public String getPage(String pageName) {
		return "http://" + host + ":" + port + "/" + pageName + ".html";
	}
}
