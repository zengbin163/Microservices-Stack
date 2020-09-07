package com.chihuo.food.interfaces.dto;

import java.util.Date;

import lombok.Data;

@Data
public class CategoryItemDTO {
	private Integer id;
	private Integer categoryId;
	private String itemName;
	private Date createTime;
	private Date updateTime;
	
	private Integer current;
	private Integer size;
	private Integer categoryTypeId;
	private Integer firstCategoryId;
	private Integer secondCategoryId;
}
