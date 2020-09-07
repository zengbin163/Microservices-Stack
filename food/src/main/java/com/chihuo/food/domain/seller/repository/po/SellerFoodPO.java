package com.chihuo.food.domain.seller.repository.po;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SellerFoodPO {
	
    private Long uid;
    private Long sellerId;
    private Long foodId;
    private Date createTime;
    
}
