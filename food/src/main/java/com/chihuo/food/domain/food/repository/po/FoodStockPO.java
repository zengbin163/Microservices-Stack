package com.chihuo.food.domain.food.repository.po;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FoodStockPO {
	
    private Long uid;
    private Long sellerId;
    private Long foodId;
    private Integer stockNum;
    private Integer version;
    private Date createTime;
    
}
