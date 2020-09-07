package com.chihuo.food.infrastructure.consumer;

import org.springframework.cloud.openfeign.FeignClient;

import com.chihuo.api.component.OrderItemComponent;

@FeignClient(value = "order-service", qualifier = "orderItemService", contextId = "orderItemService", fallback = OrderItemClientComponentFallback.class)
public interface OrderItemClientComponent extends OrderItemComponent {
}