package com.chihuo.food.infrastructure.config;

import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class ThreadPoolConfiguration {
	
	@Value("${thread.pool.core}")
	private Integer coreSize;
	@Value("${thread.pool.max}")
	private Integer maxSize;
	@Value("${thread.pool.queue}")
	private Integer queue;
	@Value("${thread.pool.prefix}")
	private String prefix;
	
    @Bean
    public ThreadPoolTaskExecutor defaultThreadPool(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        //核心线程数量
        threadPoolTaskExecutor.setCorePoolSize(coreSize);
        //最大线程数量
        threadPoolTaskExecutor.setMaxPoolSize(maxSize);
        //队列中最大任务数
        threadPoolTaskExecutor.setQueueCapacity(queue);
        //线程名称前缀
        threadPoolTaskExecutor.setThreadNamePrefix(prefix);
        //当达到最大线程数时如何处理新任务
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //线程空闲后最大存活时间
        threadPoolTaskExecutor.setKeepAliveSeconds(60);
        //初始化线程池
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }
    
}
