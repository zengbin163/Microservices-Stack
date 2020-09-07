package com.chihuo.food.interfaces.dto;

import java.util.Date;

import lombok.Data;

@Data
public class FoodDTO {
	private Long uid;
	private Integer categoryId;
	private String foodName;
	private String foodPic;
	private String foodInfo;
	private Integer[] itemArray;
	private Date createTime;
	private Date updateTime;

	private Integer current;
	private Integer size;
	private Integer firstCategoryId;
	private Integer secondCategoryId;
}
