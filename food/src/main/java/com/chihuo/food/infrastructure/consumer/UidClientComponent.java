package com.chihuo.food.infrastructure.consumer;

import org.springframework.cloud.openfeign.FeignClient;

import com.chihuo.api.component.UidComponent;

@FeignClient(value = "uid-service", fallback = UidClientComponentFallback.class)
public interface UidClientComponent extends UidComponent {
}