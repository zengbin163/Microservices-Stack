package com.chihuo.food.domain.food.event;

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
public class FoodEvent extends DomainEvent {

	private static final long serialVersionUID = 5801452233898481712L;
	private FoodEventType foodEventType;

	public static FoodEvent create(FoodEventType eventType, Food food) {
		FoodEvent event = new FoodEvent();
		event.setId(IdGenerator.nextId());
		event.setTimestamp(new Date());
		event.setFoodEventType(eventType);
		event.setData(JSON.toJSONString(food));
		return event;
	}
	
}
