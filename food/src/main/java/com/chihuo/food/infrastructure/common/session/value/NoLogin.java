package com.chihuo.food.infrastructure.common.session.value;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 注解作用在方法上 **/
@Target({ ElementType.METHOD })
/** 注解的生命周期一直程序运行时都存在VM运行期间保留注解，可以通过反射机制读取注解信息 **/
@Retention(RetentionPolicy.RUNTIME)
/** 注解包含在Javadoc中 **/
@Documented
public @interface NoLogin {

}
