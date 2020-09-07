package com.chihuo.food.domain.food.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FoodItem {
	private Long uid;
	private Long foodId;
    private Integer categoryItemId;
    private Date createTime;
    private Date updateTime;
    
    private String itemName;
}
