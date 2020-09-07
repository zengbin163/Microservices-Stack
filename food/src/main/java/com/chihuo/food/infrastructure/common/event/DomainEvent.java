package com.chihuo.food.infrastructure.common.event;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DomainEvent {
	String id;
	Date timestamp;
	String source;
	String data;
}
