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
@TableName(value = "f_food_stock")
public class FoodStockPO {
	@TableId(value = "uid")
    private Long uid;
    private Long sellerId;
    private Long foodId;
    private Integer stockNum;
    private Integer version;
    private Date createTime;
}
