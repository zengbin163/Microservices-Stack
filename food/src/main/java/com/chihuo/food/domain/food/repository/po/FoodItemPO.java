package com.chihuo.food.domain.food.repository.po;

import java.util.Date;

import com.codingapi.txlcn.tc.annotation.TableId;
import com.codingapi.txlcn.tc.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "food_item")
public class FoodItemPO {
	@TableId(value = "uid")
	private Long uid;
	private Long foodId;
	private Integer categoryItemId;
	private Date createTime;
	private Date updateTime;
	
	private String itemName;
}
