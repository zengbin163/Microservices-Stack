package com.chihuo.gateway.handler.value;

public enum Skip {
	
	COMMON_SWAGGER("swagger", "swagger文档"),
	COMMON_CSRF("csrf", "csrf"),
	COMMON_ERROR("error","error"),
	HEALTH("/health","health check"),
	FAVICON_ICO("/favicon.ico","favicon.ico"),
	;

	private String requestUrl;

	private Skip() {

	}

	private Skip(String requestUrl, String desc) {
		this.requestUrl = requestUrl;
	}
	
	public static boolean skip(String requestUrl) {
		for (Skip st : Skip.values()) {
			if(requestUrl.contains(st.requestUrl)) {
				return true;
			}
		}
		return false;
	}
}
