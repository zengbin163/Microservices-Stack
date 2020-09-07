package com.chihuo.food.infrastructure.common.interceptor;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.chihuo.food.infrastructure.common.api.Response;
import com.chihuo.food.infrastructure.common.api.ResponseStatus;
import com.chihuo.food.infrastructure.common.event.DomainEvent;
import com.chihuo.food.infrastructure.common.event.EventPublisher;
import com.chihuo.food.infrastructure.common.exception.BusinessException;
import com.chihuo.food.infrastructure.common.exception.SystemException;
import com.chihuo.food.infrastructure.util.SpringContextHolder;

import cn.hutool.core.lang.UUID;

/**
 * @author Bin.Zeng1
 * @date 2019年11月12日 上午9:25:45
 **/
public class RequestHandlerExceptionResolver implements HandlerExceptionResolver, Serializable {

    private static final long serialVersionUID = 5397821535379381427L;
    private String defaultErrorView;
    private static final Logger logger = LoggerFactory.getLogger(RequestHandlerExceptionResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception) {
    	String errorMsg = null;
        if (exception.getClass().isAssignableFrom(IllegalArgumentException.class)) {
            String detailMessage = exception.getMessage();
            if(StringUtils.isEmpty(detailMessage)) {
                detailMessage = ResponseStatus.ILLEGAL_ARGUMENT.getString();
            }
            errorMsg = JSON.toJSONString(Response.failed(ResponseStatus.ILLEGAL_ARGUMENT , detailMessage), SerializerFeature.WriteMapNullValue).toString();
        }else if (exception.getClass().isAssignableFrom(NoSuchMethodException.class)) {
            String detailMessage = exception.getMessage();
            if(StringUtils.isEmpty(detailMessage)) {
                detailMessage = ResponseStatus.ILLEGAL_ARGUMENT.getString();
            }
            errorMsg = JSON.toJSONString(Response.failed(ResponseStatus.NOT_EXISTS , detailMessage), SerializerFeature.WriteMapNullValue).toString();
        }else if (exception.getClass().isAssignableFrom(HttpRequestMethodNotSupportedException.class)) {
            String detailMessage = exception.getMessage();
            if(StringUtils.isEmpty(detailMessage)) {
                detailMessage = ResponseStatus.REQUEST_METHOD_ERROR.getString();
            }
            errorMsg = JSON.toJSONString(Response.failed(ResponseStatus.REQUEST_METHOD_ERROR , detailMessage), SerializerFeature.WriteMapNullValue).toString();
        }else if (exception.getClass().isAssignableFrom(SystemException.class)) {
            SystemException ex = (SystemException)exception;
            errorMsg = JSON.toJSONString(Response.failed(ex.getResultCode() , ex.getMessage()), SerializerFeature.WriteMapNullValue).toString();
        }else if (exception.getClass().isAssignableFrom(BusinessException.class)) {
            BusinessException ex = (BusinessException)exception;
            errorMsg = JSON.toJSONString(Response.failed(ex.getResultCode() , ex.getMessage()), SerializerFeature.WriteMapNullValue).toString();
        } else {
            errorMsg = JSON.toJSONString(Response.failed(ResponseStatus.SYSTEM_ERROR , ResponseStatus.SYSTEM_ERROR.getString()), SerializerFeature.WriteMapNullValue).toString();
        }
		
        logger.error("=============RequestHandlerExceptionResolver.resolveException===========" + errorMsg);
        
        //出现异常，事件告警处理
        EventPublisher publisher = SpringContextHolder.getBean(EventPublisher.class);
        DomainEvent event = new DomainEvent();
        event.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        event.setTimestamp(new Date());
        event.setSource("RequestHandlerExceptionResolver.resolveException");
        event.setData(errorMsg);
        publisher.publish(event);

        this.printJson(response, errorMsg);
		return null;
    }
    
    private void printJson(HttpServletResponse response, String jsonData) {
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Charset", "UTF-8");
        OutputStream out;
		try {
			out = response.getOutputStream();
	        out.write(jsonData.getBytes());
	        out.flush();
	        out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public String getDefaultErrorView() {
        return defaultErrorView;
    }

    public void setDefaultErrorView(String defaultErrorView) {
        this.defaultErrorView = defaultErrorView;
    }

}

