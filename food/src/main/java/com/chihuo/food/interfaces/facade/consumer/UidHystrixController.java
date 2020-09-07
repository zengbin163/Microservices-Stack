package com.chihuo.food.interfaces.facade.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.chihuo.food.infrastructure.common.session.value.NoLogin;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
@RequestMapping("/hystrix-consumer")
public class UidHystrixController {
	
    @Autowired
    @Qualifier(value = "balanceTemplate")
    private RestTemplate template;
	
//	public static final String URL_PREFIX = "http://localhost:8008";
	public static final String URL_PREFIX = "http://uid-service";

	@RequestMapping(value = "/uid/getUID", method = RequestMethod.GET)
	@HystrixCommand (
			// 线程池标识
			threadPoolKey = "getUID", 
			threadPoolProperties = {
					@HystrixProperty(name = "coreSize", value = "3"), // 线程池core线程核心数
					@HystrixProperty(name = "maxQueueSize", value = "100") // 队列大小
			}, 
			fallbackMethod = "getUIDFallback", // 服务降级方法
			// 使用commandProperties 可以配置熔断的一些细节信息
			commandProperties = {
					// 这里这个参数意思是熔断超时时间2s，表示过了多长时间还没结束就进行熔断
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000") 
			}
	)
	@NoLogin
	public Long getUID() {
		// 调用远程服务 http请求
		String url = URL_PREFIX + "/uid/hystrix/getUID";
		return template.getForObject(url, Long.class);
	}

	// 服务降级方法 ，这里参数与返回值需要原方法保持一直
	public Long getUIDFallback() {
		return -1L;
	}

	@RequestMapping(value = "/uid/parseUID", method = RequestMethod.GET)
	@HystrixCommand (
			// 线程池标识
			threadPoolKey = "parseUID", 
			threadPoolProperties = {
					@HystrixProperty(name = "coreSize", value = "3"), // 线程池core线程核心数
					@HystrixProperty(name = "maxQueueSize", value = "100") // 队列大小
			}, 
			fallbackMethod = "parseUIDFallback", // 服务降级方法
			// 使用commandProperties 可以配置熔断的一些细节信息
			commandProperties = {
					// 这里这个参数意思是熔断超时时间2s，表示过了多长时间还没结束就进行熔断
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000") 
			}
	)
	@NoLogin
	public String parseUID(@RequestParam(name = "uid") Long uid) {
		// 调用远程服务 http请求
		String url = URL_PREFIX + "/uid/hystrix/parseUID?uid=" + uid;
        return template.getForObject(url, String.class);
	}

	public String parseUIDFallback(@RequestParam(name = "uid") Long uid) {
		// 调用远程服务 http请求
		JSONObject json = new JSONObject();
		json.put("uid", -1);
		json.put("fallback", "服务器异常进行熔断");
		return json.toJSONString();
	}

}
