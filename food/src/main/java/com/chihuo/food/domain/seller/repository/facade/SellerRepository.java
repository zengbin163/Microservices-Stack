package com.chihuo.food.domain.seller.repository.facade;

import java.util.List;
import java.util.Optional;

import com.chihuo.food.domain.seller.repository.po.SellerPO;

public interface SellerRepository {

	void save(SellerPO sellerPO);
	
	List<SellerPO> findSellerList();
	
	Optional<SellerPO> findSellerById(Long uid);
	
}
