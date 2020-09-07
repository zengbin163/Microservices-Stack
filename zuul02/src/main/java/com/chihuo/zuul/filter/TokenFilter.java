package com.chihuo.zuul.filter;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.netflix.zuul.http.HttpServletRequestWrapper;
import com.netflix.zuul.http.ServletInputStreamWrapper;

@Component
public class TokenFilter extends ZuulFilter {

	private static Logger log = LoggerFactory.getLogger(TokenFilter.class);

	// 统计当前Zuul调用次数
	int count = 0;

	// 获取Zuul服务端口号
	@Value("${server.port}")
	private String port;

	/**
	 * 指定该Filter的类型 <br>
	 * ERROR_TYPE = "error"; <br>
	 * POST_TYPE = "post"; <br>
	 * PRE_TYPE = "pre"; <br>
	 * ROUTE_TYPE = "route"; <br>
	 */
	@Override
	public String filterType() {
		log.info("filterType()...{}", FilterConstants.PRE_TYPE);
		return FilterConstants.PRE_TYPE;
	}

	/**
	 * 指定该Filter执行的顺序（Filter从小到大执行） <br>
	 * DEBUG_FILTER_ORDER = 1; <br>
	 * FORM_BODY_WRAPPER_FILTER_ORDER = -1; <br>
	 * PRE_DECORATION_FILTER_ORDER = 5; <br>
	 * RIBBON_ROUTING_FILTER_ORDER = 10; <br>
	 * SEND_ERROR_FILTER_ORDER = 0; <br>
	 * SEND_FORWARD_FILTER_ORDER = 500; <br>
	 * SEND_RESPONSE_FILTER_ORDER = 1000; <br>
	 * SIMPLE_HOST_ROUTING_FILTER_ORDER = 100; <br>
	 * SERVLET_30_WRAPPER_FILTER_ORDER = -2; <br>
	 * SERVLET_DETECTION_FILTER_ORDER = -3; <br>
	 */
	@Override
	public int filterOrder() {
		log.info("filterOrder()...{}", FilterConstants.SEND_ERROR_FILTER_ORDER);
		return FilterConstants.SEND_ERROR_FILTER_ORDER;
	}

	/**
	 * 指定需要执行该Filter的规则 返回true则执行run()， 返回false则不执行run()
	 */
	@Override
	public boolean shouldFilter() {
		log.info("shouldFilter()...");
		return true;
	}

	/**
	 * 该Filter具体的执行活动
	 */
	@Override
	public Object run() throws ZuulException {
		try {
			RequestContext ctx = RequestContext.getCurrentContext();
			HttpServletRequest request = ctx.getRequest();
			log.info("==========TokenFilter.run 当前请求方法类型:{}，请求地址{}", request.getMethod(), request.getRequestURL().toString());
	        InputStream in = (InputStream) ctx.get("requestEntity");
	        if (in == null) {
	            in = ctx.getRequest().getInputStream();
	        }
	        String body = StreamUtils.copyToString(in, Charset.forName("UTF-8"));
	        body = "动态增加一段内容到body中: " + body;
	        byte[] bytes = body.getBytes("UTF-8");
	        ctx.setRequest(new HttpServletRequestWrapper(RequestContext.getCurrentContext().getRequest()) {
	            @Override
	            public ServletInputStream getInputStream() throws IOException {
	                return new ServletInputStreamWrapper(bytes);
	            }
	 
	            @Override
	            public int getContentLength() {
	                return bytes.length;
	            }
	 
	            @Override
	            public long getContentLengthLong() {
	                return bytes.length;
	            }
	        });
		}catch (Exception e) {
			log.error("Exception happens", e);
		}
		return null;
	}
}
