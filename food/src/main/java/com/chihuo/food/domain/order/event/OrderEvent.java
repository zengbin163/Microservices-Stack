package com.chihuo.food.domain.order.event;

import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.chihuo.api.component.event.DomainEvent;
import com.chihuo.food.domain.food.entity.Food;
import com.chihuo.food.infrastructure.util.IdGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class OrderEvent extends DomainEvent {

	private static final long serialVersionUID = -6350310615686614792L;
	private OrderEventType orderEventType;

	public static OrderEvent create(OrderEventType eventType, Food food) {
		OrderEvent event = new OrderEvent();
		event.setId(IdGenerator.nextId());
		event.setTimestamp(new Date());
		event.setOrderEventType(eventType);
		event.setData(JSON.toJSONString(food));
		return event;
	}
}
