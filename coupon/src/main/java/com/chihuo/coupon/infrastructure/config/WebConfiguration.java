package com.chihuo.coupon.infrastructure.config;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
	
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
	
}
