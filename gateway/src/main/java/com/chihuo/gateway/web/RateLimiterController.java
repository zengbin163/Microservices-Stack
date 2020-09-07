package com.chihuo.gateway.web;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicInteger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chihuo.gateway.config.aspect.annotation.RateLimitTags;

/**
 * @desc  限流测试
 * @author zengbin
 * @create 2020-08-16 15:42
 **/
@RestController
@RequestMapping(value = "/rate")
public class RateLimiterController {

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    // 10秒中，可以访问10次
    @RateLimitTags(key = "rate-limiter", time = 10, count = 10)
    @GetMapping("/limiter")
    public String limiter() {
        RedisAtomicInteger entityIdCounter = new RedisAtomicInteger("entityIdCounter", redisTemplate.getConnectionFactory());
        String date = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss.SSS");
        return date + " 累计访问次数：" + entityIdCounter.getAndIncrement();
    }
    
}
