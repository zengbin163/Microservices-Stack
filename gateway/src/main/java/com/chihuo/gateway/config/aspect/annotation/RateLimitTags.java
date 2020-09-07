package com.chihuo.gateway.config.aspect.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 描述: 限流注解
 *
 * @author zengbin
 * @create 2020-08-16 15:24
 **/
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimitTags {

	/**
	 * 限流唯一标示
	 *
	 * @return
	 */
	String key() default "";

	/**
	 * 限流时间
	 *
	 * @return
	 */
	int time();

	/**
	 * 限流次数
	 *
	 * @return
	 */
	int count();
}
