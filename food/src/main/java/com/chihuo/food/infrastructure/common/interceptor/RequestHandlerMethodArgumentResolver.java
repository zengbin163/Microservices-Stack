package com.chihuo.food.infrastructure.common.interceptor;

import java.io.BufferedReader;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.alibaba.fastjson.JSONObject;

import cn.hutool.core.util.URLUtil;

public class RequestHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

	public static final Logger log = LoggerFactory.getLogger(LoginHandlerInterceptor.class);

	private List<String> EXCLUDE_URLS = Arrays.asList("/file/uploadJson");
	
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return true;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		// 把reqeust的body读取到StringBuilder
		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
		String requestURI = request.getRequestURI();
		log.info("==========解析参数处理=========={}", requestURI);
		if(EXCLUDE_URLS.contains(requestURI)) {
			return null;
		}
		BufferedReader reader = request.getReader();
		StringBuilder sb = new StringBuilder();
		char[] buf = new char[1024];
		int rd;
		while ((rd = reader.read(buf)) != -1) {
			sb.append(buf, 0, rd);
		}
		String jsonStr = sb.toString();
		JSONObject JSON = null;
		if (StringUtils.isNotBlank(jsonStr)) {
			JSON = JSONObject.parseObject(jsonStr);
			for (String key : JSON.keySet()) {
				Object value = JSON.get(key);
				if (null != value && value.getClass().isAssignableFrom(String.class)) {
					JSON.put(key, URLUtil.decode(value.toString()));// 针对字符串数据解码
				}
			}
			String retJson = JSON.toJSONString();
			log.info("==========请求参数=========={}", retJson);
			return JSONObject.parseObject(retJson, parameter.getParameterType());
		}
		return null;
	}

}
