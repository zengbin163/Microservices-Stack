package com.chihuo.food.domain.seller.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Seller {
	
    private Long uid;
    private String sellerName;
    private String mobile;
    private String password;
    private Date createTime;
    
}
