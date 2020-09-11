package com.chihuo.api.component.event.value;

public enum CouponEventType {
	
	COUPON_SAVE,
	COUPON_USE,
	COUPON_FIND;

	public static CouponEventType type(String eventType) {
		for(CouponEventType couponEventType : CouponEventType.values()) {
			if(couponEventType.name().equals(eventType)) {
				return couponEventType;
			}
		}
		return null;
	}
	
}
