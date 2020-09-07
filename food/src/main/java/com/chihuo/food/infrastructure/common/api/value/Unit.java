package com.chihuo.food.infrastructure.common.api.value;

import java.util.concurrent.TimeUnit;

public class Unit {

	public static final String DAYS = "DAYS";
	public static final String HOURS = "HOURS";
	public static final String MINUTES = "MINUTES";
	public static final String SECONDS = "SECONDS";
	public static final String MILLISECONDS = "MILLISECONDS";

	public static TimeUnit of(String time) {
		return TimeUnit.valueOf(time);
	}
	
}
