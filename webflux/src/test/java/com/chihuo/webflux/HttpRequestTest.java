package com.chihuo.webflux;

import com.chihuo.webflux.util.RequestHelper;

public class HttpRequestTest {

	public static void main(String[] args) {
		String requestUrl = "http://127.0.0.1:7788/uid/hystrix/getUID?serviceName=uid-service";
		for (int i = 0; i < 1000; i++) {
			String response = RequestHelper.get(requestUrl);
			System.out.println("---------------------------------RequestHelper->response:" + response);
		}
	}

}
