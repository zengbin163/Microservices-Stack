package com.chihuo.zuul.fallback;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.context.RequestContext;

@Component
public class ServiceFallbackProvider implements FallbackProvider {

	private final Logger log = LoggerFactory.getLogger(ServiceFallbackProvider.class);

	@Override
	public String getRoute() {
		return "*";
	}

	@Override
	public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		return new ClientHttpResponse() {

			@Override
			public InputStream getBody() throws IOException {
				String requestURI = request.getRequestURI();
				log.info("==========ServiceFallbackProvider.fallbackResponse 解析参数处理=========={}", requestURI);
				
				JSONObject r = new JSONObject();
				r.put("state", "8888");
				r.put("msg", "系统错误，请求失败");
				log.error("降级" + r.toString());
				return new ByteArrayInputStream(r.toString().getBytes("UTF-8"));
			}

			@Override
			public HttpHeaders getHeaders() {
				HttpHeaders headers = new HttpHeaders();
				// 和body中的内容编码一致，否则容易乱码
				headers.setContentType(MediaType.APPLICATION_JSON);
				return headers;
			}

			/**
			 * 网关向api服务请求是失败了，但是消费者客户端向网关发起的请求是OK的， 不应该把api的404,500等问题抛给客户端
			 * 网关和api服务集群对于客户端来说是黑盒子
			 */
			@Override
			public HttpStatus getStatusCode() throws IOException {
				return HttpStatus.OK;
			}

			@Override
			public int getRawStatusCode() throws IOException {
				return HttpStatus.OK.value();
			}

			@Override
			public String getStatusText() throws IOException {
				return HttpStatus.OK.getReasonPhrase();
			}

			@Override
			public void close() {
			}
		};
	}

}
