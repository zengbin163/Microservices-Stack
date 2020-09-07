package com.chihuo.food.infrastructure.config;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.chihuo.food.infrastructure.common.interceptor.LoginHandlerInterceptor;
import com.chihuo.food.infrastructure.common.interceptor.RequestHandlerExceptionResolver;
import com.chihuo.food.infrastructure.common.interceptor.RequestHandlerMethodArgumentResolver;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
	
    @Value("${file.upload.path}")
    private String fileUploadPath;
    @Value("${file.upload.resource}")
    private String fileUploadResource;

	/**
	 * 重写converter为FastJsonConverter
	 */
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		
		FastJsonHttpMessageConverter fastJsonHttpMessageConverter = fastJsonHttpMessageConverter();
		fastJsonHttpMessageConverter.setDefaultCharset(Charset.forName("UTF-8"));
		// 自定义fastjson配置
		FastJsonConfig config = new FastJsonConfig();
		String DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
		config.setDateFormat(DEFFAULT_DATE_FORMAT);
		config.setSerializerFeatures(
				SerializerFeature.WriteMapNullValue,      // 是否输出值为null的字段,默认为false,我们将它打开
				SerializerFeature.WriteNullListAsEmpty,   // 将Collection类型字段的字段空值输出为[]
				SerializerFeature.WriteNullStringAsEmpty, // 将字符串类型字段的空值输出为空字符串
				SerializerFeature.WriteNullNumberAsZero,  // 将数值类型字段的空值输出为0
				SerializerFeature.WriteDateUseDateFormat,
				SerializerFeature.DisableCircularReferenceDetect, // 禁用循环引用
				SerializerFeature.PrettyFormat
		);
		fastJsonHttpMessageConverter.setFastJsonConfig(config);
		// 添加支持的MediaTypes;不添加时默认为*/*,也就是默认支持全部
		// 但是MappingJackson2HttpMessageConverter里面支持的MediaTypes为application/json
		// 参考它的做法, fastjson也只添加application/json的MediaType
		List<MediaType> fastMediaTypes = new ArrayList<>();
		fastMediaTypes.add(MediaType.APPLICATION_JSON);
		fastJsonHttpMessageConverter.setSupportedMediaTypes(fastMediaTypes);
		converters.add(fastJsonHttpMessageConverter);
	}
	
	@Bean
	public FastJsonHttpMessageConverter fastJsonHttpMessageConverter() {
		FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
		return fastJsonHttpMessageConverter;
	}
	
	@Bean
	public LoginHandlerInterceptor loginHandlerInterceptor() {
		return new LoginHandlerInterceptor();
	}
	
	/**
	 * 增加拦截器
	 */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // '/**' 匹配所有
        registry.addInterceptor(loginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/A","/B");
    }

    /**
     * 增加参数解析器
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    	resolvers.add(new RequestHandlerMethodArgumentResolver());
    }
    
    /**
     * 增加异常处理器
     */
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
    	resolvers.add(new RequestHandlerExceptionResolver());
    }
    
    /**
     * 设置跨域
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("POST","GET","PUT","OPTIONS","DELETE")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }
    
    /**
     * 增加资源文件路径
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	registry.addResourceHandler(fileUploadResource + "**").addResourceLocations("file:" + fileUploadPath);
    }
    
	@Bean
	public HttpRequestListener requestListener() {
		return new HttpRequestListener();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public ServletListenerRegistrationBean listenerRegister() {
		ServletListenerRegistrationBean srb = new ServletListenerRegistrationBean();
		srb.setListener(requestListener());
		return srb;
	}
	
}
