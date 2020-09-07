package com.chihuo.food.infrastructure.consumer;

import org.springframework.cloud.openfeign.FeignClient;

import com.chihuo.api.component.CouponComponent;

@FeignClient(value = "coupon-service", qualifier = "couponService", contextId = "couponService", fallback = CouponClientComponentFallback.class)
public interface CouponClientComponent extends CouponComponent {
}