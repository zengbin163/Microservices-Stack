package com.chihuo.food.infrastructure.consumer;

import org.springframework.cloud.openfeign.FeignClient;

import com.chihuo.api.component.OrderComponent;

@FeignClient(value = "order-service", qualifier = "orderService", contextId = "orderService", fallback = OrderClientComponentFallback.class)
public interface OrderClientComponent extends OrderComponent {
}