package com.chihuo.food.domain.category.event;

import java.util.Date;

import com.chihuo.api.component.event.DomainEvent;
import com.chihuo.food.infrastructure.util.IdGenerator;

public class CategoryEvent extends DomainEvent {
	
	private static final long serialVersionUID = 2480093787104018357L;

	public static CategoryEvent create(){
    	CategoryEvent event = new CategoryEvent();
        event.setId(IdGenerator.nextId());
        event.setTimestamp(new Date());
        event.setData(null);
        return event;
    }
}
