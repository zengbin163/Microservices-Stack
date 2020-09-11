package com.chihuo.food.domain.seller.repository.po;

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
@TableName(value = "f_seller_food")
public class SellerFoodPO {
	@TableId(value = "uid")
    private Long uid;
    private Long sellerId;
    private Long foodId;
    private Date createTime;
    
}
