package com.chihuo.food.domain.category.event;

import java.util.Date;

import com.chihuo.food.infrastructure.common.event.DomainEvent;
import com.chihuo.food.infrastructure.util.IdGenerator;

public class CategoryEvent extends DomainEvent {
	
    public static CategoryEvent create(){
    	CategoryEvent event = new CategoryEvent();
        event.setId(IdGenerator.nextId());
        event.setTimestamp(new Date());
        event.setData(null);
        return event;
    }
}
