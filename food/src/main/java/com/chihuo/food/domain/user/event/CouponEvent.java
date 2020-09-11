package com.chihuo.food.domain.user.event;

import java.util.Date;

import com.chihuo.api.component.CouponComponent;
import com.chihuo.api.component.event.DomainEvent;
import com.chihuo.api.component.event.value.CouponEventType;
import com.chihuo.food.infrastructure.util.IdGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class CouponEvent extends DomainEvent {

	private static final long serialVersionUID = 2994582717374459469L;
	private CouponEventType couponEventType;

	public static CouponEvent create(String topic, CouponEventType couponEventType, String params) {
		CouponEvent event = new CouponEvent();
		event.setId(IdGenerator.nextId());
		event.setTimestamp(new Date());
		event.setCouponEventType(couponEventType);
		event.setTopic(topic);
		event.setSource(couponEventType.name());
		event.setData(params);
		return event;
	}
	
	public static void main(String[] args) {
		System.out.println(CouponComponent.class.getName());
	}
	
}
