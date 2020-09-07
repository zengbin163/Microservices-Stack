package com.chihuo.food.domain.seller.repository.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chihuo.food.domain.seller.repository.po.SellerPO;

public interface SellerDao extends BaseMapper<SellerPO> {

	void save(@Param("sellerPO") SellerPO sellerPO);
	
	List<SellerPO> findSellerList();

	Optional<SellerPO> findSellerById(@Param("uid") Long uid);
	
}
