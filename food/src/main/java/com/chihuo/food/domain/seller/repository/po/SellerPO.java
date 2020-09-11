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
@TableName(value = "f_seller")
public class SellerPO {
	@TableId(value = "uid")
    private Long uid;
    private String sellerName;
    private String mobile;
    private String password;
    private Date createTime;
    
}
